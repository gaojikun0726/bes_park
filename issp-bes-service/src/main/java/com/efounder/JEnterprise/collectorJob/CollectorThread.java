package com.efounder.JEnterprise.collectorJob;

import com.core.common.ISSPReturnObject;
import com.core.common.metatype.Dto;
import com.core.common.metatype.impl.BaseDto;
import com.core.common.util.LEMSUtil;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BESAmmeterMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BESSbdyMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BesCollectorMapper;
import com.efounder.JEnterprise.mapper.collectorJob.BESJobManagerMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESAmmeter;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesCollector;
import com.efounder.JEnterprise.model.collectorJob.BESSysConf;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.EnerEquipmentService;
import com.efounder.opc.OpcData_cross;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * 轮询采集器线程类
 * @Description:轮询所有采集器，和采集器挂载的电表，并获取当前数据，写入数据库
 * @author LvSihan
 * @version:
 * @date:2018年8月28日
 */
@Component
public class CollectorThread extends Thread {
	private static final Logger log = LoggerFactory.getLogger(CollectorThread.class);

	private String parkId;//园区id
	private Date threadTime;//执行时时间
	private String threadId;//采集器id
	private List<Map<String,Object>> lstBatchDto;//批量插入原始数据库
	private List<Map<String,Object>> lstCalcDto;//批量插入计算原始库
	private List<Map<String,Object>> ammeters;//当前采集器下电表
	private String ip;//当前采集器ip
	private String port; // 当前采集器端口号
	static public SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//标准时间格式化


	private static CollectorThread collectorThread;
	@Autowired
	private BESJobManagerMapper besJobManagerMapper;
	@Autowired
	private BESAmmeterMapper besAmmeterMapper;
	@Autowired
	private BesCollectorMapper besCollectorMapper;
	@Autowired
	private EnerEquipmentService enerEquipmentService;
	@Autowired
	private BESSbdyMapper besSbdyMapper;
	@Autowired
	private LEMSUtil lemsUtil;
	@PostConstruct
	public void init() {
		collectorThread = this;
		collectorThread.besJobManagerMapper = this.besJobManagerMapper;
		collectorThread.besCollectorMapper = this.besCollectorMapper;
		collectorThread.enerEquipmentService = this.enerEquipmentService;
		collectorThread.besSbdyMapper = this.besSbdyMapper;
		collectorThread.lemsUtil = this.lemsUtil;
	}



	public CollectorThread() {
	}

	/**
	 * 线程函数
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void run() {
		// 当前采集器id
		String collectorId = threadId;
		// 当前园区编号
		String parkid = parkId;

		// 格式化执行时时间
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(threadTime);

		// 根据采集器 id 查出采集器系统名称； 操作表：bes_collector （这里可以优化）
		String collectorname = collectorThread.besJobManagerMapper.queryCollectorName(collectorId);
		log.info("启动采集任务,开始轮询采集器=【"+collectorname+"】；采集器id=【" + collectorId+"】");

		// 查询采集器下的所有电表
		Dto dtoIn = new BaseDto();
		dtoIn.put("id", collectorId);

		// 查询采集器下的所有电表
		ammeters = collectorThread.besJobManagerMapper.queryAmmeter(collectorname);

//		if (ammeters.size() > 0) {
//			ip = ammeters.get(0).getAsString("ip");
//			port = ammeters.get(0).getAsString("port");
//		}
		// System.out.printf("%s 任务触发  时间:%s\n", collectorId, time);

		//采集器是否在线   0:离线，1：在线
		String collectorStatus = "0";
		try {
			com.alibaba.fastjson.JSONObject obj = new com.alibaba.fastjson.JSONObject();
			if(isColleactorOnline(this.ip,this.port,collectorname)){
				// 在线
				collectorStatus = "1";
				obj.put("f_status", collectorStatus);// 更新设备树上状态为“1”，在线
				obj.put("f_sys_name", collectorname);
				obj.put("tabName", "BES_SBPZ_STRUCT");
			}else {
				// 不在线
				obj.put("f_status", collectorStatus);// 更新设备树上状态为“0”，离线
				obj.put("f_sys_name", collectorname);
				obj.put("tabName", "BES_SBPZ_STRUCT");
			}

			// 更新表中采集器状态
			collectorThread.besSbdyMapper.editSbTreeStructInfoBySysName(obj);
			if(collectorThread.lemsUtil.getOpcuseable() == 1) {
				//OPC数据表更新该采集器状态
//				OpcData.editOpcOne(obj);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		boolean success = false;


		// 对每个电表轮询
		for (Map<String, Object> dto : ammeters) {
			//采集器不在线，直接返回 add by wujf at 20171214
			if("0".equals(collectorStatus)){

				BESAmmeter besAmmeter = new BESAmmeter();
				besAmmeter.setfSysName((String) dto.get("uuid"));
				besAmmeter.setfOnlineState("1");

				// 更新电表状态（电表不在线）
				collectorThread.besAmmeterMapper.updateByPrimaryKeySelective(besAmmeter);

				break;
			}


			String type = (String) dto.get("type");
			String id = (String) dto.get("id");

			// 如果电表是静态电表,则直接写入数据0
			if (type.equals("5")) {
				Dto dtoNew = getSingleDto((String)dto.get("uuid"), "0", time, "1", null, parkid,"kwh","电能");

				lstBatchDto.add(dtoNew);
				lstCalcDto.add(dtoNew);
				continue;
			}

			// 电表数据
			List<Map> data = null;
			// 超过3次通讯失败,则视为当前采集器网络中断,不再请求数据
			for(int faildCnt = 0;faildCnt < 3;faildCnt++) {//判断不正确，此处为DDC下所有电表轮训通信。应判断单一电表通信失败3次则终止
				try {
					data = queryAmmeterData(ip, id, port);
				} catch (ServiceException e) {
					e.printStackTrace();
				}
				if(data == null) {
					continue;
				}else {
					break;
				}
			}



			BESAmmeter besAmmeter = new BESAmmeter();
			besAmmeter.setfSysName((String) dto.get("id"));
			// 保存电表数据
			if (saveAmmeterData(collectorId, id, data, time, lstBatchDto, lstCalcDto, parkid)) {
				success = true;
				besAmmeter.setfOnlineState("1");//在线
			} else {
				besAmmeter.setfOnlineState("0");//离线
			}
			collectorThread.besAmmeterMapper.updateByPrimaryKeySelective(besAmmeter);//更新电表状态
			collectorThread.besAmmeterMapper.updateByPrimaryKeySelectiveZt(besAmmeter);//更新电表状态
		}
		 //判断通讯结果
		if (success) {
			// 获取数据库中该采集器上次通信成功时间
			Map<String,Object> dto = collectorThread.besJobManagerMapper.queryCollectorStatus(null,collectorId);

			if (dto != null) {
				// 采集器状态0：离线；1：在线
				String status = (String) dto.get("F_COLLSTATUS");
				// 上次通信成功的时间
				String successTime = (String) dto.get("F_LASTSUCCESS_TIME");

				if (status.equals("0")) {
					 log.info("检测到数据丢失,执行数据补回,采集器=【"+collectorname+"】；采集器id=【" + collectorId+"】");
					// 检测到上次通讯失败，计算中间时间
					if (successTime.isEmpty()) {
						successTime = "1999-01-01 00:00:00";
					}
					Date lastTime = null;
					try {
						lastTime = dateFormat.parse(successTime);
					} catch (ParseException e) {
					}
					if (lastTime == null){
						return ;
					}
					// 更新数据库中采集器通信成功
					saveCollectorStatus(collectorId, time,collectorStatus);
					try {
						repairData(lastTime, threadTime, parkid);
					} catch (ServiceException e) {
						e.printStackTrace();
					}
				}
			}
			// 更新数据库中采集器通信成功
			saveCollectorStatus(collectorId, time, collectorStatus);
		} else {
			// 设置采集器状态为无法链接
			saveCollectorStatus(collectorId, null, collectorStatus);
		}
		log.info("采集任务结束,采集器=【"+collectorname+"】；采集器id=【" + collectorId+"】");
	}

	/**
	 * 数据打包封装
	 * @param uuid
	 * @param data
	 * @param time
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Dto getSingleDto(String uuid, String data, String time, String type, String enectric_id, String parkid,String unit,String dnmc){
		Dto dtoNew = new BaseDto();
		dtoNew.put("id", UUIDUtil.getRandom32BeginTimePK());
		dtoNew.put("meteruuid", uuid);
		dtoNew.put("data", data);
		dtoNew.put("l_time", time);
		dtoNew.put("type", type);
		dtoNew.put("enectric_id", enectric_id);
		dtoNew.put("unit", unit);
		dtoNew.put("dnmc", dnmc);

		return dtoNew;
	}

	public static Dto getSingleDto2(String uuid, String data, String time, String type, String enectric_id, String parkid,String unit,String dnmc, String f_nick_name){
		Dto dtoNew = new BaseDto();
		dtoNew.put("id", UUIDUtil.getRandom32BeginTimePK());
		dtoNew.put("meteruuid", uuid);
		dtoNew.put("data", data);
		dtoNew.put("l_time", time);
		dtoNew.put("type", type);
		dtoNew.put("enectric_id", enectric_id);
		dtoNew.put("unit", unit);
		dtoNew.put("dnmc", dnmc);
		dtoNew.put("f_nick_name", f_nick_name);
		return dtoNew;
	}


	/**
	 * 补历史数据到原始表 modi by wujf at 20171214
	 * @param start
	 * @param end
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public void repairData(Date start, Date end, String parkid) throws ServiceException{
		Calendar t1 = Calendar.getInstance();
		Calendar t2 = Calendar.getInstance();
		t1.setTime(start);
		t2.setTime(end);
		// 判断时间最大范围，最大30天
		long diff = end.getTime() - start.getTime();// 这样得到的差值是微秒级别
		long days = diff / (1000 * 60 * 60 * 24);
		if (days > 30) {
			t1.setTimeInMillis(t2.getTimeInMillis());
			t1.add(Calendar.DAY_OF_MONTH, -1);
		}
		t1.add(Calendar.HOUR_OF_DAY, 1);
		formatTime(t1);
		formatTime(t2);

		/*dayData数据格式：
		 * 		key:'day',value:{
	                 key:'hour',value:[
	                                 dto1, dto2 ,...
	                 ]
				}
		*/
		Dto dayData = new BaseDto();
		SimpleDateFormat dayFmt = new SimpleDateFormat("yyyy-MM-dd");

		for (; t1.before(t2); t1.add(Calendar.DAY_OF_MONTH, 1)){
			String day = dayFmt.format(t1.getTime());
//			String time = fmt.format(t1.getTime());

			if (!dayData.containsKey(day)){
				diff = end.getTime() - t1.getTimeInMillis();// 这样得到的差值是微秒级别
				days = diff / (1000 * 60 * 60 * 24);
				Dto curDay = new BaseDto();
				//请求该天的历史数据
				for (Map<String, Object> ammeter : ammeters){
					String id = (String) ammeter.get("id");
					Dto curData = queryHistoryData(ip, id, days, port, parkid);
					if(curData == null || curData.isEmpty()) {
						log.info(ip, port, id, days);
						return;
					}
					for (Object key : curData.keySet()) {
						String keystr = (String)key;
						keystr = keystr.substring(0, 10);

						List<Dto> lstCurData = (List<Dto>) curData.get(key);

						if (lstCurData == null || lstCurData.isEmpty()) {
							continue;
						}
						curDay.put(key, lstCurData);
					}
				}
				dayData.put(day, curDay);
			}
			//取出一天的历史数据
			Dto dto = (Dto) dayData.get(day);
			if (dto == null || dto.isEmpty()){
				continue;
			}

			for (Object key : dto.keySet()) {
				List<Dto> ammetersData = (List<Dto>) dto.get(key);
				if (ammetersData == null || ammetersData.isEmpty()) {
					continue;
				}

				//遍历电表数据
				String type,l_time;
				for(Dto dtoIn: ammetersData){
					l_time = dtoIn.getAsString("l_time");
					type = dtoIn.getAsString("type");

					try {
						Date ltime = fmt.parse( l_time);
						if(ltime.getTime()>start.getTime() && ltime.getTime() < end.getTime()){

							try {
								if(Double.parseDouble((String) dtoIn.get("data")) > 10000000){
									continue;
								}
							} catch (NumberFormatException e) {
								e.printStackTrace();
								continue;
							}

							lstBatchDto.add(dtoIn);

							if (type.equals("1")){
								lstCalcDto.add(dtoIn);
							}
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		}
		//数据补回后，要更新计算，具体方式是：先在数据库中存放一个更新计算的时间范围，待到0点执行
		//如果多个采集器同时发生补数据，则将多个时间范围合并(SQL判断并合并)

		BESSysConf besSysConf = new BESSysConf();
		besSysConf.setfHisStartTime(fmt.format(start));
		collectorThread.besJobManagerMapper.updateCalculateTime(besSysConf);
		besSysConf = new BESSysConf();
		besSysConf.setfHisEndTime(fmt.format(end));
		collectorThread.besJobManagerMapper.updateCalculateTime(besSysConf);
	}

	/**
	 * 补历史数据到原始表 源程序逻辑
	 * @param start
	 * @param end
	 */
	public void repairDataBak(Date start, Date end, String parkid){
//		Calendar t1 = Calendar.getInstance();
//		Calendar t2 = Calendar.getInstance();
//		t1.setTime(start);
//		t2.setTime(end);
//		// 判断时间最大范围，最大30天
//		long diff = end.getTime() - start.getTime();// 这样得到的差值是微秒级别
//		long days = diff / (1000 * 60 * 60 * 24);
//		if (days > 30) {
//			t1.setTimeInMillis(t2.getTimeInMillis());
//			t1.add(Calendar.DAY_OF_MONTH, -30);
//		}
//		t1.add(Calendar.HOUR_OF_DAY, 1);
//		formatTime(t1);
//		formatTime(t2);
//
//		/*dayData数据格式：
//		 * 		key:'day',value:{
//	                 key:'hour',value:[
//	                                 dto1, dto2 ,...
//	                 ]
//				}
//		*/
//		Dto dayData = new BaseDto();
//		SimpleDateFormat dayFmt = new SimpleDateFormat("yyyy-MM-dd");
//
//		for (; t1.before(t2); t1.add(Calendar.HOUR_OF_DAY, 1)){
//			String day = dayFmt.format(t1.getTime());
//			String time = fmt.format(t1.getTime());
//
//			if (!dayData.containsKey(day)){
//				diff = end.getTime() - t1.getTimeInMillis();// 这样得到的差值是微秒级别
//				days = diff / (1000 * 60 * 60 * 24);
//				Dto curDay = new BaseDto();
//				//请求该天的历史数据
//				for (Dto ammeter : ammeters){
//					String id = ammeter.getAsString("id");
//					Dto curData = queryHistoryData(ip, id, days, port, parkid);
//
//					for (Object key : curData.keySet()) {
//						String keystr = (String)key;
//						keystr = keystr.substring(0, 10);
//
//						if (!keystr.equalsIgnoreCase(day)) {
//							continue;
//						}
//						List<Dto> lstCurData = (List<Dto>) curData.get(key);
//
//						if (lstCurData == null || lstCurData.isEmpty()) {
//							continue;
//						}
//						curDay.put(key, lstCurData);
//					}
//				}
//				dayData.put(day, curDay);
//			}
//			//取出当天内当前小时的数据
//			Dto dto = (Dto) dayData.get(day);
//			if (dto == null || dto.isEmpty() || !dto.containsKey(time)){
//				continue;
//			}
//			List<Dto> ammetersData = (List<Dto>) dto.get(time);
//
//			if (ammetersData == null){
//				continue;
//			}
//			lstBatchDto.addAll(ammetersData);
//		}
//		//数据补回后，要更新计算，具体方式是：先在数据库中存放一个更新计算的时间范围，待到0点执行
//		//如果多个采集器同时发生补数据，则将多个时间范围合并(SQL判断并合并)
//		Dto dto = new BaseDto();
//		dto.put("his_start_time", fmt.format(start));
//		g4Dao.update("CalculateData.updateCalculateTime", dto);
//
//		dto = new BaseDto();
//		dto.put("his_end_time", fmt.format(end));
//		g4Dao.update("CalculateData.updateCalculateTime", dto);
	}

	/**
	 * 根据不同时间点格式化时间，并且向前推进一个时间单位
	 * @param cal
	 * @param field
	 */
	public static void formatTime(Calendar cal){
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
	}
	/**
	 * 查询电表数据
	 *
	 * @param ip
	 *            采集器ip
	 * @param id
	 *            电表id
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("rawtypes")
	public static List<Map> queryAmmeterData(String ip, String id, String port) throws ServiceException {
		try {
			Map<String,Object> pMap = new HashMap<>();
			pMap.put("IPAddr", ip);//ip地址
			pMap.put("Port", port);//端口号
			pMap.put("MeterID",id);
			// 向服务请求数据
			Dto retDto = collectorThread.enerEquipmentService.operEnergyController(pMap,"15");
			String errmsg = retDto.getAsString("errmsg");

			if (!errmsg.equals(LEMSConstants.Service_Success)) {
				return null;
			}
			// 获取电能数据
			retDto = (Dto) retDto.get("MeterElectricData");
			//如果返回的电表ID和请求的不同，则返回null---LvSihan 2018-11-29
			String retMeterID = retDto.getAsString("MeterID");
			if(!retMeterID.equals(id)) {
				return null;
			}
			return parseData(retDto, null);
		} catch (UnsupportedEncodingException | RemoteException e) {
		}
		return null;
	}

	/**
	 * 采集器是否在线
	 * @param ip
	 * @param port
	 * @return
	 */
	public static boolean isColleactorOnline(String ip, String port,String collectorname) throws ServiceException {
		try {
			String param = ip + "@@" + port;
			// 向服务请求数据
			ISSPReturnObject retDto = collectorThread.enerEquipmentService.operEnergyCollector(collectorname, ip, port, 2);

			String errmsg = retDto.getMsg();
			log.info("启动采集任务,采集器是否在线=【"+param+"】"+errmsg);

			if(!errmsg.equals(LEMSConstants.Service_Success)) {
				return false;
			}else {
				return true;
			}

		} catch (UnsupportedEncodingException | RemoteException e) {
		}
		return false;
	}


	/**
	 * 查询历史数据
	 * @param ip
	 * @param id
	 * @param day
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Dto queryHistoryData(String ip, String id, long day, String port, String parkid) throws ServiceException{
		String index = "16";
		Map<String,Object> pMap = new HashMap<>();
		pMap.put("IPAddr", ip);//ip地址
		pMap.put("Port", port);//端口号
		pMap.put("MeterID",id);
		pMap.put("Selectday", day);
		// 向服务请求数据
		Dto retDto;
		try {
			retDto = collectorThread.enerEquipmentService.operEnergyController(pMap,index);
		} catch (UnsupportedEncodingException | RemoteException e) {
			return null;
		}
		String errmsg = retDto.getAsString("errmsg");

		if (retDto == null || !errmsg.equals(LEMSConstants.Service_Success)) {
			return null;
		}
		if (JSONNull.getInstance() == retDto.get("MeterElectricHisData") || retDto.get("MeterElectricHisData") == null) {
			return null;
		}
		JSONObject hisDatas = (JSONObject) retDto.get("MeterElectricHisData");
		//解析历史数据,判断是否在有效的时间段内
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, (int) -day);
		Dto dto = new BaseDto();
		Iterator it = hisDatas.keys();
		while (it.hasNext()){
			String key = (String)it.next();
			JSONObject value = (JSONObject) hisDatas.get(key);
			Date time = new Date();
			Dto data = new BaseDto();

			Iterator itchild = value.keys();

			while (itchild.hasNext()) {
				String kk = (String)itchild.next();
				data.put(kk, value.getString(kk));
			}

			List<Map> lstData = parseData(data, time);

			List<Map<String,Object>> lstEnectric = collectorThread.besJobManagerMapper.queryEnectricId(id);
			if (lstData == null || lstData.isEmpty()
					|| lstEnectric == null || lstEnectric.size() != lstData.size()){
				continue;
			}
			long timeMill = time.getTime();
			timeMill /= 1000 * 60 * 60;
			timeMill *= 1000 * 60 * 60;
			time.setTime(timeMill);
			String timeStr = fmt.format(time);
			List<Dto> datas = new ArrayList<Dto>();



			for (int i = 0; i < lstData.size(); ++i){
				Dto enec = (Dto) lstEnectric.get(i);
				String unit = (String)enec.get("F_UNIT");
				String dnmc = (String)enec.get("F_DNMC");
				String percentage = (String)enec.get("F_PERCENTAGE");
				String ElectricData = collectorThread.enerEquipmentService.analysisRawData(id,
						(String)lstData.get(i).get("ElectricData"),percentage,unit);

				String ElectricTime = (String)lstData.get(i).get("ElectricTime");
				Dto d = getSingleDto(enec.getAsString("uuid"), ElectricData, ElectricTime, enec.getAsString("type"), enec.getAsString("id"), parkid,unit,dnmc);
				datas.add(d);
			}
			dto.put(timeStr, datas);
		}
		return dto;
	}

	/**
	 * 解析单个节点
	 *
	 * @param dtoSrc
	 * @param time
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected static List<Map> parseData(Dto dtoSrc, Date time) {
		if (dtoSrc == null) {
			return null;
		}
		// 数据个数
		if (dtoSrc.getAsInteger("CollectCount") <= 0) {
			return null;
		}
		// 时间是否有效
		int year = dtoSrc.getAsInteger("DateYear");
		int month = dtoSrc.getAsInteger("DateMonth");
		int date = dtoSrc.getAsInteger("DateDay");
		int hourOfDay = dtoSrc.getAsInteger("TimeHour");
		int minute = dtoSrc.getAsInteger("TimeMinute");
		int second = dtoSrc.getAsInteger("TimeSecond");

		if (year == 0 && month == 0 && date == 0) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.set(year + 2000, month - 1, date, hourOfDay, minute, second);
		if (time != null) {
			time.setTime(cal.getTimeInMillis());
		}

		String data = dtoSrc.getAsString("ElectricData");
		String[] datas = data.split(",");
		List<Map> realData = new ArrayList<Map>();
		// 非空数据检查
		for (String str : datas) {
			Map ElectricData = new HashMap();

			ElectricData.put("ElectricTime", fmt.format(cal.getTime()));

			if (str != null && !str.isEmpty()) {
				ElectricData.put("ElectricData", str);
				realData.add(ElectricData);
			}
		}
		return realData;
	}

	// /**
	// * 设置该电表数据为数据库中最近一次的数据
	// * @param id
	// * @param time
	// * @param count
	// */
	// @SuppressWarnings("unchecked")
	// protected static void setLastData(String id, String time, int count){
	// Dto dto = new BaseDto();
	// dto.put("id", id);
	// dto.put("count", count);
	// List<Dto> lstDto =
	// g4Dao.queryForList("CollectorJob.queryAmmeterLastData", dto);
	//
	// if (lstDto.size() != count){
	// return ;
	// }
	// //检查一组数据的时间是否都相同，如果不同，代表数据异常，放弃操作
	// String l_time = null;
	// for (Dto d:lstDto){
	// if (l_time == null){
	// l_time = d.getAsString("l_time");
	// }
	// else if (!l_time.equals(d.getAsString("l_time"))){
	// return ;
	// }
	// }
	// //复制并写入数据
	// for (Dto d:lstDto){
	// d.put("l_time", time);
	// d.put("id", IDHelper.getUUID());
	// g4Dao.insert("CollectorJob.insertEnectricData", d);
	// }
	// }

	/**
	 * 保存采集器状态，如果记录不存在则新建记录，如果存在则更新记录
	 *
	 * @param id
	 * @param time
	 * @param status
	 */
	protected static void saveCollectorStatus(String collector_id, String time,
			String status) {
		// 以采集器id查询状态
		Map<String,Object> dto = collectorThread.besJobManagerMapper.queryCollectorStatus(null,collector_id);
		String fId = "";
		String fCollector_id = "";
		String fSuccess_time = "";
		String fStatus = "";
		if (dto == null) {
			// 新建记录
			fId = UUIDUtil.getRandom32BeginTimePK();
			fCollector_id = collector_id;
			if (time != null) {
				fSuccess_time = time;
			}
			fStatus = status;
			collectorThread.besJobManagerMapper.insertCollectorStatus(fId,fCollector_id,fSuccess_time,fStatus);
		} else {
			// 更新记录
			fId = (String)dto.get("F_ID");
			if (time != null) {
				fSuccess_time = time;
			}
			fStatus = status;
			collectorThread.besJobManagerMapper.updateCollectorStatus(fSuccess_time,fStatus,fId);
		}

		BesCollector besCollector = new BesCollector();
		besCollector.setfGuid(collector_id);
		besCollector.setfOnlineState(fStatus);
		collectorThread.besCollectorMapper.updateCollector(besCollector);
	}

	/**
	 * 保存电表数据，返回与采集器通讯结果
	 * @param collectorId 采集器id
	 * @param id 电表id
	 * @param data 电表数据
	 * @param time
	 * @param lstDto 批量插入原始数据库
	 * @param lstCalcDto 批量插入计算原始库
	 * @param parkid 园区id
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean saveAmmeterData(String collectorId, String id, List<Map> data,
			String time, List<Map<String,Object>> lstDto, List<Map<String,Object>> lstCalcDto, String parkid) {
		if (data == null) {
			// setLastData(id, time, lstEnectric.size());
			return false;
		}

//		F_NHBH ID, // 电能参数编号
//		F_SYS_NAME UUID, // 系统名称
//		F_NICK_NAME, // 别名
//		F_STATISTICAL_PARAM TYPE , // 是否作为统计分析参数(是：1，否：0)
//		F_UNIT, // 单位
//		F_DNMC, // 能耗名称
//		F_PERCENTAGE // 变比


		List<Map<String,Object>> lstEnectric = collectorThread.besJobManagerMapper.queryEnectricId(id);
		// 电能参数个数必须一致
		if (lstEnectric == null || lstEnectric.size() != data.size()) {
			// setLastData(id, time, lstEnectric.size());
			log.info("电能参数个数异常,采集器id=" + collectorId + "电表id=" + id);
			return true;
		}
		String [] dataArray = new String[lstEnectric.size()];
		String [] unitArray = new String[lstEnectric.size()];
		String [] descArray = new String[lstEnectric.size()];
		Dto dtoIn = new BaseDto();
		// 解析每个原始数据并保存到数据库
		for (int i = 0; i < lstEnectric.size(); ++i) {
			Map<String, Object> dto2 = lstEnectric.get(i);
			String enectricId = (String) dto2.get("ID");// 电能参数编号
			String uuid = (String)dto2.get("UUID");// 系统名称
			String type = (String)dto2.get("TYPE");// 是否作为统计分析参数(是：1，否：0)
			String unit = (String)dto2.get("F_UNIT");// 单位
			String dnmc = (String)dto2.get("F_DNMC");// 能耗名称
			String f_nick_name = (String) dto2.get("F_NICK_NAME");// 别名
			String percentage = (String)dto2.get("F_PERCENTAGE");// 变比

			//解析原始数据
			String value = collectorThread.enerEquipmentService.analysisRawData(enectricId,
					(String)data.get(i).get("ElectricData"),percentage,unit);

			if (value == null || value.isEmpty()) {
				log.info("解析原始数据异常,采集器id=" + collectorId + "电表id=" + id);
				continue;
			}
			// System.out.printf("电表%s.解析电能参数id=%s.原始数据=%s.解析数据=%s\n", id,
			// enectricId, data.get(i), value);

			time = (String)data.get(i).get("ElectricTime");
			dtoIn = getSingleDto2(uuid, value, time, type, enectricId, parkid,unit,dnmc,f_nick_name);


			/**
			 * 查询该电表最近3次保存的数据，
			 * 将最新数据与数据库最后一条对比,如果比上一条数据小，再和数据库倒数第二条对比
			 * 和倒数第二条数据对比，如果比倒数第二条还小，则认定为错误数据，不插入；如果>倒数第二条,<最后一条，则插入
			 */
			//如果参数的单位是kwh（用电度数），则进行数据过滤
			if("kwh".equals(unit.toLowerCase())) {
				//查询该电表最近3次保存的数据，
				List<Map<String,String>> AmmeterCalculateData = collectorThread.besJobManagerMapper.queryAmmeterLastCalculateData(uuid);
				String newdata = String.valueOf(dtoIn.getAsString("data"));
				//如果数据库已有该电表数据，则判断
				if(AmmeterCalculateData.size() > 0) {
					//将最新数据与数据库最后一条对比,如果比上一条数据小，再和数据库倒数第二条对比
					String lastone = String.valueOf(AmmeterCalculateData.get(0).get("F_DATA"));
					boolean lastonestate = Double.doubleToLongBits(Double.parseDouble(newdata)) < Double.doubleToLongBits(Double.parseDouble(lastone));
					if(lastonestate) {
						//和倒数第二条数据对比，如果比倒数第二条还小，则认定为错误数据，不插入；如果>倒数第二条,<最后一条，则插入
						// TODO 这里存在数组下标越界异常
						if(AmmeterCalculateData.size() > 1){
							String lasttwo = String.valueOf(AmmeterCalculateData.get(1).get("F_DATA"));
							boolean lasttwostate = Double.doubleToLongBits(Double.parseDouble(newdata)) < Double.doubleToLongBits(Double.parseDouble(lasttwo));
							if(lasttwostate) {
								log.info("采集器id="+collectorId+"电表id="+id+",本次数据"+String.valueOf(dtoIn.getAsString("data"))+
										"小于上两次数据"+String.valueOf(AmmeterCalculateData.get(0).get("F_DATA"))+","+String.valueOf(AmmeterCalculateData.get(1).get("F_DATA"))+"不执行插入操作");
								break;
							}
						}

					}
				}
			}
			// TODO


			/**
			 * 如果从下位机获取的数据超大就舍弃
			 */
			try {
				if(Double.parseDouble((String) dtoIn.get("data")) > 10000000){
					continue;
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				continue;
			}



			lstDto.add(dtoIn);
			if (type.equals("1")){ // 是否作为统计分析参数(是：1，否：0)
				lstCalcDto.add(dtoIn);
			}
			dataArray[i] = dtoIn.getAsString("data");
			unitArray[i] = dtoIn.getAsString("unit");
			descArray[i] = dtoIn.getAsString("dnmc");
			if(collectorThread.lemsUtil.getOpcuseable() == 1) {

				OpcData_cross.editOpcInitVal_energySingl(dtoIn,dataArray[i],unitArray[i],descArray[i]);
//			OpcData.editOpcInitVal(besSbPzStruct);
			}
		}
//		if(collectorThread.lemsUtil.getOpcuseable() == 1) {
//
//			OpcData_cross.editOpcInitVal_energy(dtoIn,dataArray,unitArray,descArray);
////			OpcData.editOpcInitVal(besSbPzStruct);
//		}
		return true;
	}

	public String getParkId() {
		return parkId;
	}

	public void setParkId(String parkId) {
		this.parkId = parkId;
	}

	public Date getThreadTime() {
		return threadTime;
	}

	public void setThreadTime(Date threadTime) {
		this.threadTime = threadTime;
	}

	public String getThreadId() {
		return threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

	public List<Map<String, Object>> getLstBatchDto() {
		return lstBatchDto;
	}

	public void setLstBatchDto(List<Map<String, Object>> lstBatchDto) {
		this.lstBatchDto = lstBatchDto;
	}

	public List<Map<String, Object>> getLstCalcDto() {
		return lstCalcDto;
	}

	public void setLstCalcDto(List<Map<String, Object>> lstCalcDto) {
		this.lstCalcDto = lstCalcDto;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

}


