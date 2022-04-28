package com.efounder.JEnterprise.collectorJob;

import com.efounder.JEnterprise.mapper.collectorJob.BESJobManagerMapper;
import com.efounder.JEnterprise.model.collectorJob.BESSysConf;
import com.efounder.opc.OpcData_cross;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 采集器定时任务,请求所有采集数据
 * 
 * @author Administrator
 *
 */
@Component
public class CollectorJob implements Job {
	private static final Logger log = LoggerFactory.getLogger(CollectorJob.class);
	private SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static private enum STATUS {
		NONE, QUERY_COLLECTOR, CALCULATE_DATA, OTHER
	};

	static private STATUS status = STATUS.NONE;
	static private String lastRunTime = "";
	static private Lock locker = new ReentrantLock();
	private static CollectorJob collectorJob;
	public static List<Map<String, Object>> newOriginalData = new ArrayList<>();
	public static List<Map<String, Object>> interimOriginalData = new ArrayList<>();

	public static List<Map<String, String>> opcData = Collections.synchronizedList(new ArrayList<>());

	@Autowired
	private BESJobManagerMapper besJobManagerMapper;

	@PostConstruct
	public void init() {
		collectorJob = this;
		collectorJob.besJobManagerMapper = this.besJobManagerMapper;
	}

	/**
	 * 任务触发接口
	 */
	public void execute(JobExecutionContext arg) throws JobExecutionException {
		// 临界保护代码 ---
//		locker.lock();
		if (status != STATUS.NONE) {
			log.info("任务已返回，目标状态为：" + status + ",开始时间为：" + lastRunTime);
			return;
		} else {
			lastRunTime = CollectorThread.fmt.format(new Date());
			status = STATUS.QUERY_COLLECTOR;
		}
//		locker.unlock();

		try {
			log.info("开始轮询采集器!");
			final List<Map<String, Object>> lstDto = Collections.synchronizedList(new ArrayList<Map<String, Object>>());
			final List<Map<String, Object>> lstCalcDto = Collections.synchronizedList(new ArrayList<Map<String, Object>>());
			final Date now = new Date();
			List<CollectorThread> threads = new ArrayList<CollectorThread>();

			// 查询出所有采集器（在设备树添加采集器信息），表：bes_collector
			List<Map<String, Object>> collectors = collectorJob.besJobManagerMapper.queryCollector();

			// 轮询采集器线程
			for (Map<String, Object> collector : collectors) {
				CollectorThread thread = new CollectorThread();
				// 当前采集器id
				thread.setThreadId((String) collector.get("F_GUID"));
				// 当前采集器ip
				thread.setIp((String) collector.get("F_IP_ADDR"));
				// 当前采集器端口号
				thread.setPort((String) collector.get("F_PORT"));
				// 执行时时间
				thread.setThreadTime(now);
				// 电表原始数据保存集合
				thread.setLstBatchDto(lstDto);// *****
				// 电表计算数据保存集合
				thread.setLstCalcDto(lstCalcDto);// *****
				// 园区编号
				thread.setParkId((String) collector.get("F_YQBH"));
				thread.start();
				threads.add(thread);
			}
			// 等待所有线程执行完毕
			for (CollectorThread tt : threads) {
				try {
					tt.join();
				} catch (InterruptedException e) {
				}
			}

			// 批量发送 opc 数据
			OpcData_cross.sendOpcList(opcData);

			opcData.clear();

			log.info("轮询采集器完毕!");
			status = STATUS.CALCULATE_DATA;
			// 在实时数据写入之前，标记为busy

			BESSysConf besSysConf = new BESSysConf();
			besSysConf.setfRealtimedataflag("busy");
			collectorJob.besJobManagerMapper.updateCalculateTime(besSysConf);
			// 电表上次采集数据表
			collectorJob.besJobManagerMapper.deleteLastData();
			// 插入或更新原始数据信息
			collectorJob.besJobManagerMapper.insertLastData();
			// 删除实时数据
			collectorJob.besJobManagerMapper.deleteRealtimeData();
			//数据为空时不执行插入操作
	    	if (lstDto.size() > 0) {
				log.info("开始插入本次轮询实时数据");
				// 插入或更新原始数据信息
				collectorJob.besJobManagerMapper.insertRealtimeData(lstDto);// *****
				log.info("结束插入本次轮询实时数据");
			}
			// 写入完毕，标记写入的时间
			besSysConf.setfRealtimedataflag(CollectorThread.fmt.format(new Date()));
			collectorJob.besJobManagerMapper.updateCalculateTime(besSysConf);
			//数据为空时不执行插入操作
			if (lstCalcDto.size() > 0) {
				log.info("开始插入本次计算数据");
				// 插入计算源数据
				collectorJob.besJobManagerMapper.insertCalculateData(lstCalcDto); // ****
				log.info("结束插入计算数据");
			}
			
			// 计算实时数据,本时/本日/本月/本年
			log.info("开始计算本次数据");
			
			// 计算前设置状态为busy
			BESSysConf besSysConfThis = new BESSysConf();
			besSysConfThis.setfCalculatetime("busy");
			collectorJob.besJobManagerMapper.updateCalculateTime(besSysConfThis);
			// 查询所有园区编号
			List<Map<String, Object>> parkList = collectorJob.besJobManagerMapper.queryAllPark();
			List<CalculateThread> calThreads = new ArrayList<CalculateThread>();
			for (Map<String, Object> park : parkList) {
				CalculateThread calThread = new CalculateThread();
				calThread.setParkId((String) park.get("F_YQBH"));
				calThread.setParkName((String) park.get("F_YQMC"));
				calThread.start();
				calThreads.add(calThread);
			}

			for (CalculateThread ct : calThreads) {
				try {
					ct.join();
				} catch (InterruptedException e) {

				}
			}
			log.info("计算结束");

			// 计算结束后写入计算时间
			besSysConf.setfCalculatetime(fmt.format(new Date()));
			besSysConf.setfLastruntime(fmt.format(new Date()));
			collectorJob.besJobManagerMapper.updateCalculateTime(besSysConf);
			status = STATUS.OTHER;
			//数据为空时不执行插入操作
			if (lstDto.size() > 0) {
				log.info("开始插入原始数据");

				List<Map<String, String>> originalDataMaps = new ArrayList<>();

				if(newOriginalData.isEmpty()){
					originalDataMaps = collectorJob.besJobManagerMapper.queryLastDataList(lstDto);

				}


				collectorJob.besJobManagerMapper.insertEnectricData(lstDto);

				List<Map<String,Object>> monitoringList = new ArrayList<>();

				if(newOriginalData.isEmpty()){
					newOriginalData.addAll(lstDto);


					for(Map<String, Object> dataMap: lstDto)
					{
						//List<Map<String, String>> originalDataMap = collectorJob.besJobManagerMapper.queryLastData(dataMap.get("meteruuid").toString(),dataMap.get("enectric_id").toString(),dataMap.get("type").toString());

						String latestMeteruuid = dataMap.get("meteruuid").toString();
						String latestEnectricId = dataMap.get("enectric_id").toString();
						String latestType = dataMap.get("type").toString();

						for(Map<String, String> originalData: originalDataMaps){
							String beforeMeteruuid = originalData.get("F_DBSYS_NAME");
							String beforeEnectricId = originalData.get("F_DNBH");
							String beforeType = originalData.get("F_TYPE");

							if(latestMeteruuid.equals(beforeMeteruuid)
									&& latestEnectricId.equals(beforeEnectricId)
									&& latestType.equals(beforeType)){
								double d =Double.parseDouble(dataMap.get("data").toString())-Double.parseDouble(originalData.get("F_DATA"));
								dataMap.put("data",(double) Math.round(d * 100) / 100);

								monitoringList.add(dataMap);
							}
						}


					/*if(originalDataMap.get(0).get("F_CJSJ").split(" ")[0].equals(originalDataMap.get(1).get("F_CJSJ").split(" ")[0]))
					{
						dataMap.put("data",(double) Math.round(d * 100) / 100);
					}*/

					}
				}else {

					interimOriginalData.addAll(lstDto);

					for (Map<String, Object> dataMap: lstDto){

						String latestMeteruuid = dataMap.get("meteruuid").toString();
						String latestEnectricId = dataMap.get("enectric_id").toString();
						String latestType = dataMap.get("type").toString();

						for(Map<String, Object> newOriginalDataMap: newOriginalData){

							String beforeMeteruuid = newOriginalDataMap.get("meteruuid").toString();
							String beforeEnectricId = newOriginalDataMap.get("enectric_id").toString();
							String beforeType = newOriginalDataMap.get("type").toString();

							if(latestMeteruuid.equals(beforeMeteruuid)
									&& latestEnectricId.equals(beforeEnectricId)
									&& latestType.equals(beforeType)){

								double d =Double.parseDouble(dataMap.get("data").toString())-Double.parseDouble(newOriginalDataMap.get("data").toString());
								dataMap.put("data",(double) Math.round(d * 100) / 100);

								monitoringList.add(dataMap);

							}

						}

					}

					newOriginalData.clear();
					newOriginalData.addAll(interimOriginalData);
					interimOriginalData.clear();

				}
				// 插入差值数据 add by liuzhen at  20181211


				collectorJob.besJobManagerMapper.insertMonitoringData(monitoringList);
				log.info("结束插入原始数据");
			}

			log.info("开始计算系统内所有园区当前采集周期功率");
			Calendar cur = Calendar.getInstance();
			cur.setTime(now);
			List<CalculateCurrentEnergyThread> ccThreads = new ArrayList<CalculateCurrentEnergyThread>();

			for (Map<String, Object> park : parkList) {
				CalculateCurrentEnergyThread ccThread = new CalculateCurrentEnergyThread();
				ccThread.setParkId((String) park.get("F_YQBH"));
				ccThread.setParkName((String) park.get("F_YQMC"));
				ccThread.start();
				ccThreads.addAll(ccThreads);
			}

			for (CalculateCurrentEnergyThread cct : ccThreads) {
				try {
					cct.join();
				} catch (InterruptedException e) {

				}
			}
			log.info("结束计算系统内所有园区当前采集周期功率");
			status = STATUS.NONE;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			status = STATUS.NONE;
		}
	}
}
