package com.efounder.JEnterprise.collectorJob;

import com.alibaba.fastjson.JSONObject;
import com.core.common.util.LEMSUtil;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BESSbdyMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BesAlarmManageMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESSbPzStruct;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesAlarmModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
/**
 *
 * 说明：由于能耗和照明空调系统不同，在线状态标识有所不同，具体查看注释或相关表结构
 * @author LvSihan
 * @date 2018年12月17日
 * @version 1.0
 */
@Component
public class BESSbtreeThread extends Thread {
	private static final Logger log = LoggerFactory.getLogger(BESSbtreeThread.class);
	@Autowired
	private BESSbdyMapper besSbdyMapper;
	@Autowired
	private LEMSUtil lEMSUtil;
	private static BESSbtreeThread besSbtreeThread;

	@Autowired
	private BesAlarmManageMapper besAlarmManageMapper;

	//public static List<BESSbPzStruct> SbtreeList = new CopyOnWriteArrayList<>();
	private static final List<BESSbPzStruct> allSbList = Collections.synchronizedList(new CopyOnWriteArrayList<BESSbPzStruct>());

	public static final List<BESSbPzStruct> sbAllTreeList = Collections.synchronizedList(new CopyOnWriteArrayList<BESSbPzStruct>());
	public static final List<JSONObject> doSbList = Collections.synchronizedList(new CopyOnWriteArrayList<JSONObject>());
	public static final List<JSONObject> diSbList = Collections.synchronizedList(new CopyOnWriteArrayList<JSONObject>());
	public static final List<JSONObject> aoSbList = Collections.synchronizedList(new CopyOnWriteArrayList<JSONObject>());
	public static final List<JSONObject> aiSbList = Collections.synchronizedList(new CopyOnWriteArrayList<JSONObject>());
	public static final List<BESSbPzStruct> vSbList = Collections.synchronizedList(new CopyOnWriteArrayList<BESSbPzStruct>());
	//private static final List<BESSbPzStruct> allSbList = new ArrayList<BESSbPzStruct>();

	public static final Map<String, BESSbPzStruct> realTimeData = new ConcurrentHashMap<>();

	public static final Map<String, BesAlarmModel> besAlarmModels = new HashMap<>();

	@PostConstruct
	public void init() {
		besSbtreeThread = this;
		besSbtreeThread.besSbdyMapper = this.besSbdyMapper;
		besSbtreeThread.lEMSUtil = this.lEMSUtil;
//		besSbtreeThread.besAlarmManageMapper = this.besAlarmManageMapper;
	}
	@Override
	public void run() {
		while (true) {
			log.info("开始轮询");

			/*List<BESSbPzStruct> besList = new ArrayList<>();
			BESSbPzStruct besSbPzStruct = new BESSbPzStruct();
			besSbPzStruct.setF_status("1");
			besSbPzStruct.setF_sys_name("123123");
			besList.add(besSbPzStruct);
			BESSbPzStruct besSbPzStruct2 = new BESSbPzStruct();
			besSbPzStruct2.setF_status("1");
			besSbPzStruct2.setF_sys_name("123123");
			besList.add(besSbPzStruct2);
			besSbdyMapper.editSBTreeInfo(besList);*/

			Date startDate = new Date();
			//获取设备树上所有DDC控制器节点
			List<BESSbPzStruct> ddcList = besSbtreeThread.besSbdyMapper.queryAllDdc();
			List<BESPollingThread> threads = new ArrayList<>();
			for (BESSbPzStruct ddc : ddcList) {
				/*if("0".equals(ddc.getF_poll_status())){//当前DDC不需要轮询
					continue;
				}*/
				//BESPollingThread thread = new BESPollingThread(sbAllTreeList, doSbList, diSbList, aoSbList, aiSbList);
				BESPollingThread thread = new BESPollingThread();
				thread.setDdcInfo(ddc);
				//thread.setAllSbList(allSbList);
				thread.setSbAllTreeList(sbAllTreeList);
//				thread.setAiSbList(aiSbList);
//				thread.setDiSbList(diSbList);
//				thread.setAoSbList(aoSbList);
//				thread.setDiSbList(doSbList);
				thread.start();
				threads.add(thread);
			}
			/*CountDownLatch countDownLatch = new CountDownLatch(threads.size());
			try {
				countDownLatch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
			for (BESPollingThread pt : threads) {
				try {
					pt.join();//等待所有线程执行结束
				} catch (InterruptedException e) {

				}
			}

			threads.clear();
			threads = null;

			try {
				if(!besAlarmModels.isEmpty()){

					besSbtreeThread.besAlarmManageMapper.insertBatch(new ArrayList<>(besAlarmModels.values()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}


			try {
				if(sbAllTreeList.size() > 0){
					besSbtreeThread.besSbdyMapper.editSBTreeInfo(sbAllTreeList);
					sbAllTreeList.clear();
				}

			}catch (Exception e){
				sbAllTreeList.clear();
				e.printStackTrace();
			}

			try {
				if(aoSbList.size() > 0){
					//besSbtreeThread.besSbdyMapper.editPointInfo(aoSbList);
					besSbtreeThread.besSbdyMapper.editPointInfoChange(aoSbList);
					aoSbList.clear();
				}
			}catch (Exception e){
				aoSbList.clear();
				e.printStackTrace();
			}
			try {
				if(aiSbList.size() > 0){
					//besSbtreeThread.besSbdyMapper.editPointInfo(aiSbList);
					besSbtreeThread.besSbdyMapper.editPointInfoChange(aiSbList);
					aiSbList.clear();
				}
			}catch (Exception e){
				aiSbList.clear();
				e.printStackTrace();
			}
			try {
				if(doSbList.size() > 0){
					//besSbtreeThread.besSbdyMapper.editPointInfo(doSbList);
					besSbtreeThread.besSbdyMapper.editPointInfoChange(doSbList);
					doSbList.clear();
				}
			}catch (Exception e){
				doSbList.clear();
				e.printStackTrace();
			}
			try {

				if(diSbList.size() > 0){
					//besSbtreeThread.besSbdyMapper.editPointInfo(diSbList);
					besSbtreeThread.besSbdyMapper.editPointInfoChange(diSbList);
					diSbList.clear();
				}
			}catch (Exception e){
				diSbList.clear();
				e.printStackTrace();
			}

			/*SbtreeList.clear();
			SbtreeList.addAll(allSbList);//放入集合缓存
			allSbList.clear();*/
			Date endDate = new Date();
			log.info("轮询时间为*******************************"+(endDate.getTime()-startDate.getTime()));
			log.info("结束轮询");
			try {
				Thread.sleep(besSbtreeThread.lEMSUtil.getPolltime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
	public static List<BESSbPzStruct> getAllsblist() {
		return allSbList;
	}
/*	public static java.util.List<BESSbPzStruct> getSbtreeList() {
		return SbtreeList;
	}*/

/*	public static java.util.List<BESSbPzStruct> getSbtreeList1() {
		return allSbList;
	}*/

/*	public static void setSbtreeList(java.util.List<BESSbPzStruct> sbtreeList) {
		SbtreeList = sbtreeList;
	}*/

}
