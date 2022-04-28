package com.efounder.JEnterprise.collectorJob;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.core.common.constant.WebSocketEvent;
import com.core.common.util.AlarmUtil;
import com.core.common.util.LEMSUtil;
import com.core.config.qxpz.HttpSessionConfig;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BESSbdyMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BesDdcMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.CrossEquipmentMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESSbPzStruct;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesAlarmModel;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesDdc;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesPointStruct;
import com.efounder.JEnterprise.model.dataAnalysises.BesWainingInfo;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.CrossEquipmentService;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.EnerEquipmentService;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.LampEquipmentService;
import com.framework.common.utils.Validate_y;
import org.ace.websocket.handler.WebSocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * 轮询DDC、模块、逻辑点
 *
 * @author LvSihan
 * @date 2018年12月26日
 * @version 1.0
 */
@Component
public class BESPollingThread extends Thread{


	private static final Logger log = LoggerFactory.getLogger(BESPollingThread.class);
	@Autowired
	private CrossEquipmentService crossEquipmentService;
	@Autowired
	private LampEquipmentService lampEquipmentService;
	@Autowired
	private EnerEquipmentService enerEquipmentService;
	@Autowired
	private BESSbdyMapper besSbdyMapper;
	@Autowired
	private LEMSUtil lemsUtil;

	@Autowired
	private BesDdcMapper besDdcMapper;

	@Autowired
	private CrossEquipmentMapper crossEquipmentMapper;

	private static BESPollingThread besPollingThread;
	@PostConstruct
	public void init() {
		besPollingThread = this;
		besPollingThread.crossEquipmentService = this.crossEquipmentService;
		besPollingThread.lampEquipmentService = this.lampEquipmentService;
		besPollingThread.enerEquipmentService = this.enerEquipmentService;
		besPollingThread.besSbdyMapper = this.besSbdyMapper;
		besPollingThread.lemsUtil = this.lemsUtil;
		besPollingThread.besDdcMapper = this.besDdcMapper;
		besPollingThread.crossEquipmentMapper = this.crossEquipmentMapper;
	}

	/**
	 * ddc信息
	 */
	private BESSbPzStruct ddcInfo;
	/**
	 * DDC及其子节点设备信息
	 */
	private List<BESSbPzStruct> allSbList;
	private List<BESSbPzStruct> sbAllTreeList;
//	private List<JSONObject> doSbList = Collections.synchronizedList(new CopyOnWriteArrayList<JSONObject>());
//	private List<JSONObject> diSbList = Collections.synchronizedList(new CopyOnWriteArrayList<JSONObject>());
//	private List<JSONObject> aoSbList = Collections.synchronizedList(new CopyOnWriteArrayList<JSONObject>());
//	private List<JSONObject> aiSbList = Collections.synchronizedList(new CopyOnWriteArrayList<JSONObject>());
//	private List<JSONObject> vSbList;


	/*public BESPollingThread(List<BESSbPzStruct> sbAllTreeList, List<BESSbPzStruct> doSbList, List<BESSbPzStruct> diSbList,
							List<BESSbPzStruct> aoSbList, List<BESSbPzStruct> aiSbList){
		this.sbAllTreeList = sbAllTreeList;
		this.doSbList = doSbList;
		this.diSbList = diSbList;
		this.aoSbList = aoSbList;
		this.aiSbList = aiSbList;

	}*/

	private CountDownLatch mDoneSignal;

	public int alarmJudgment(Double value,String id,BesPointStruct pointList,String pointValueName,String pointLevel,String planValue){

		BesWainingInfo besWaringInfo = new BesWainingInfo();

		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
		String strTime = dateFormat.format(now);
		BesDdc DDCname = new BesDdc();
		String SBAlarm = "2";//点位报警暂定为设备报警
		String ModuleName = null;

			 DDCname = besPollingThread.besSbdyMapper.selectDDCName(id);//查询当前点位所属的DDC全部信息
		if (DDCname == null){
			DDCname = besPollingThread.besSbdyMapper.selectDDCNameByVpoint(id);//查询当前虚点所属的DDC全部信息
			ModuleName = "虚点";//如果是虚点点位,则直接显示虚点
		}else {
			ModuleName = besPollingThread.besSbdyMapper.selectModuleName(id);//查询当前点位的模块名称
		}


			String Guid = UUIDUtil.getRandom32BeginTimePK();
			besWaringInfo.setFYqbh(pointList.getfYqbh());
			besWaringInfo.setFGuid(Guid);
			besWaringInfo.setFLoction("控制器 :"+DDCname.getfSysName() + ",模块" +ModuleName + "(" + DDCname.getfIpAddr() + ")");//报警位置
			besWaringInfo.setFAlarmName(pointList.getfSysName());//报警名称,
			besWaringInfo.setFActualValue(String.valueOf(value));//实际值
			besWaringInfo.setFATime(strTime);//报警时间
			besWaringInfo.setFOpearation("1");//是否处理
			besWaringInfo.setFType(SBAlarm);//信息类型
			besWaringInfo.setFLevel(pointLevel);//报警等级
			besWaringInfo.setFPlanValue(planValue);//计划值
			besWaringInfo.setFTipInfo("当前点位"+pointValueName);//提示信息
			//判断是否存在相同的未处理报警
			AlarmUtil.insertWarningRealAmmeter(besWaringInfo);
			return 0;//报警信息添加成功,返回0
	}
	@Override
	public void run() {



		/*Map<String,Object> mapdemo = new HashedMap();
		mapdemo.put("id","q1");
		mapdemo.put("value","255");

		Map<String,Object> mapdemo1 = new HashedMap();
		mapdemo1.put("id","q5");
		mapdemo1.put("value","130");
		Map<String,Object> mapdemo2 = new HashedMap();
		mapdemo2.put("id","q9");
		mapdemo2.put("value","255");
		Map<String,Object> mapdemo3 = new HashedMap();
		mapdemo3.put("id","q10");
		mapdemo3.put("value","150");
		Map<String,Object> mapdemo4 = new HashedMap();
		mapdemo4.put("id","x1");
		mapdemo4.put("value","255");
		Map<String,Object> mapdemo5 = new HashedMap();
		mapdemo5.put("id","x2");
		mapdemo5.put("value","255");
		Map<String,Object> mapdemo6 = new HashedMap();
		mapdemo6.put("id","x3");
		mapdemo6.put("value","150");
		Map<String,Object> mapdemo7 = new HashedMap();
		mapdemo7.put("id","x4");
		mapdemo7.put("value","150");

		List<Map<String,Object>> demo = new ArrayList<Map<String,Object>>();
		demo.add(mapdemo);
		demo.add(mapdemo1);
		demo.add(mapdemo2);
		demo.add(mapdemo3);
		demo.add(mapdemo4);
		demo.add(mapdemo5);
		demo.add(mapdemo6);
		demo.add(mapdemo7);


		for (int i =0;i<demo.size();i++) {


			int pointValue = 0;//闭合状态对应的数值
			String pointLevel = null;//报警等级
			String pointValueName = null;//闭合状态对应的名称
			String planValue = null;//计划值的名称
			int alarm_high;//高限报警
			int alarm_low;//底限报警
			//查询当前点位的名称
			String id = (String) demo.get(i).get("id");
			//查询当前点位的值
			Double value = Double.parseDouble((String) demo.get(i).get("value"));
			//查询当前点位名称所属的表
			String tableName = besPollingThread.besSbdyMapper.selectTableName(id);
			//根据表名和id名称查询当前点位的全部信息
			BesPointStruct pointList = besPollingThread.besSbdyMapper.selectPointList(tableName, id);
			if (pointList.getfAlarmPriority().equals("0")) {//优先级 0对应3 一般;1对应2 重要;2对应1 紧急;
				pointLevel = "3";
			} else if (pointList.getfAlarmPriority().equals("1")) {
				pointLevel = "2";
			} else if (pointList.getfAlarmPriority().equals("2")) {
				pointLevel = "1";
			}

			//查询点位的闭合状态,如果是0则意为断开,如果是1则意为闭合
			if (tableName.equals("BES_DIGIT_OUPUT") || tableName.equals("BES_DIGIT_INPUT") || tableName.equals("BES_ANALOG_OUPUT") || tableName.equals("BES_ANALOG_INPUT") )
			{
				if (pointList.getfCloseState() != null) {
					if (pointList.getfCloseState().equals("0")) {
						pointValue = 0;
						pointValueName = "断开";
					} else if (pointList.getfCloseState().equals("1")) {
						pointValue = 255;
						pointValueName = "闭合";
					}
					planValue = String.valueOf(pointValue);//计划值
				}

				if (pointList.getfLowLimit() != null  || pointList.getfHighLimit() != null){//如果底限值或者高限值不为null

					String accuracy = pointList.getfAccuracy();//获取精度
					int accuracyNum = Integer.parseInt(accuracy);//精度转换成int
					value = value/Math.pow(10,accuracyNum);//获取精度转换后的实时值
					planValue = pointList.getfLowLimit()+"--"+pointList.getfHighLimit();//计划值

					if (pointList.getfWhetherToAlarmHigh() == 1 && pointList.getfWhetherToAlarmLow() == 0 ){//如果高限上次的值报警

						 if (value > Integer.parseInt(pointList.getfHighLimit())-5){//如果实时值高于高限值-5

							pointValueName = "高于高限";
							alarm_high = 1;
							alarm_low = 0;
							besPollingThread.besSbdyMapper.update_AO_AI_Alarm(alarm_high,alarm_low,tableName,id);

						}else if (value < Integer.parseInt(pointList.getfLowLimit())){//如果实时值低于底限值

							 pointValueName = "低于底限";
							 //往AO,AI表里面添加是否报警的值,1代表报警,0代表不报警
							 alarm_high = 0;
							 alarm_low = 1;
							 besPollingThread.besSbdyMapper.update_AO_AI_Alarm(alarm_high,alarm_low,tableName,id);
						 }else {

							 alarm_high = 0;
							 alarm_low = 0;
							besPollingThread.besSbdyMapper.update_AO_AI_Alarm(alarm_high,alarm_low,tableName,id);
						}
					}else if (pointList.getfWhetherToAlarmHigh() == 0 && pointList.getfWhetherToAlarmLow() == 1){//如果低限上次的值报警

						if (value < Integer.parseInt(pointList.getfLowLimit())+5)	{//如果实时值低于底限值+5

							pointValueName = "低于底限";
							//往AO,AI表里面添加是否报警的值,1代表报警,0代表不报警
							alarm_high = 0;
							alarm_low = 1;
							besPollingThread.besSbdyMapper.update_AO_AI_Alarm(alarm_high,alarm_low,tableName,id);

						}else if (value > Integer.parseInt(pointList.getfHighLimit())){

							pointValueName = "高于高限";
							alarm_high = 1;
							alarm_low = 0;
							besPollingThread.besSbdyMapper.update_AO_AI_Alarm(alarm_high,alarm_low,tableName,id);

						}else {

							alarm_high = 0;
							alarm_low = 0;
							besPollingThread.besSbdyMapper.update_AO_AI_Alarm(alarm_high,alarm_low,tableName,id);

						}
					}else {//如果上次的数据底限,高限都没有报警

						if (value < Integer.parseInt(pointList.getfLowLimit()))	{//如果实时值低于底限值

							pointValueName = "低于底限";
							//往AO,AI表里面添加是否报警的值,1代表报警,0代表不报警
							alarm_high = 0;
							alarm_low = 1;
							besPollingThread.besSbdyMapper.update_AO_AI_Alarm(alarm_high,alarm_low,tableName,id);

						}else if (value > Integer.parseInt(pointList.getfHighLimit())){//如果实时值高于高限值

							pointValueName = "高于高限";
							alarm_high = 1;
							alarm_low = 0;
							besPollingThread.besSbdyMapper.update_AO_AI_Alarm(alarm_high,alarm_low,tableName,id);

						}else {

							alarm_high = 0;
							alarm_low = 0;
							besPollingThread.besSbdyMapper.update_AO_AI_Alarm(alarm_high,alarm_low,tableName,id);

						}

					}


				}

			}else if (tableName.equals("BES_VPOINT") ){
				if (pointList.getfNodeType().equals("6") || pointList.getfNodeType().equals("7")){//VDO VDI 点
					if (pointList.getfCloseState() != null) {
						if (pointList.getfCloseState().equals("0")) {
							pointValue = 0;
							pointValueName = "断开";
						} else if (pointList.getfCloseState().equals("1")) {
							pointValue = 255;
							pointValueName = "闭合";
						}
						planValue = String.valueOf(pointValue);//计划值
					}
				}else {//VAO VAI点
					if (pointList.getfLowLimit() != null  || pointList.getfHighLimit() != null){//如果底限值或者高限值不为null
						String accuracy = pointList.getfAccuracy();//获取精度
						int accuracyNum = Integer.parseInt(accuracy);//精度转换成int
						value = value/Math.pow(10,accuracyNum);//获取精度转换后的实时值
						planValue = pointList.getfLowLimit()+"--"+pointList.getfHighLimit();//计划值

						if (pointList.getfWhetherToAlarmHigh() == 1 && pointList.getfWhetherToAlarmLow() == 0 ){//如果高限上次的值报警

							if (value > Integer.parseInt(pointList.getfHighLimit())-5){//如果实时值高于高限值-5

								pointValueName = "高于高限";
								alarm_high = 1;
								alarm_low = 0;
								besPollingThread.besSbdyMapper.update_AO_AI_Alarm(alarm_high,alarm_low,tableName,id);

							}else if (value < Integer.parseInt(pointList.getfLowLimit())){//如果实时值低于底限值

								pointValueName = "低于底限";
								//往AO,AI表里面添加是否报警的值,1代表报警,0代表不报警
								alarm_high = 0;
								alarm_low = 1;
								besPollingThread.besSbdyMapper.update_AO_AI_Alarm(alarm_high,alarm_low,tableName,id);
							}else {

								alarm_high = 0;
								alarm_low = 0;
								besPollingThread.besSbdyMapper.update_AO_AI_Alarm(alarm_high,alarm_low,tableName,id);
							}
						}else if (pointList.getfWhetherToAlarmHigh() == 0 && pointList.getfWhetherToAlarmLow() == 1){//如果低限上次的值报警

							if (value < Integer.parseInt(pointList.getfLowLimit())+5)	{//如果实时值低于底限值+5

								pointValueName = "低于底限";
								//往AO,AI表里面添加是否报警的值,1代表报警,0代表不报警
								alarm_high = 0;
								alarm_low = 1;
								besPollingThread.besSbdyMapper.update_AO_AI_Alarm(alarm_high,alarm_low,tableName,id);

							}else if (value > Integer.parseInt(pointList.getfHighLimit())){

								pointValueName = "高于高限";
								alarm_high = 1;
								alarm_low = 0;
								besPollingThread.besSbdyMapper.update_AO_AI_Alarm(alarm_high,alarm_low,tableName,id);

							}else {

								alarm_high = 0;
								alarm_low = 0;
								besPollingThread.besSbdyMapper.update_AO_AI_Alarm(alarm_high,alarm_low,tableName,id);

							}
						}else {//如果上次的数据底限,高限都没有报警

							if (value < Integer.parseInt(pointList.getfLowLimit()))	{//如果实时值低于底限值

								pointValueName = "低于底限";
								//往AO,AI表里面添加是否报警的值,1代表报警,0代表不报警
								alarm_high = 0;
								alarm_low = 1;
								besPollingThread.besSbdyMapper.update_AO_AI_Alarm(alarm_high,alarm_low,tableName,id);

							}else if (value > Integer.parseInt(pointList.getfHighLimit())){//如果实时值高于高限值

								pointValueName = "高于高限";
								alarm_high = 1;
								alarm_low = 0;
								besPollingThread.besSbdyMapper.update_AO_AI_Alarm(alarm_high,alarm_low,tableName,id);

							}else {

								alarm_high = 0;
								alarm_low = 0;
								besPollingThread.besSbdyMapper.update_AO_AI_Alarm(alarm_high,alarm_low,tableName,id);

							}

						}
					}
				}
			}



		if (tableName.equals("BES_DIGIT_OUPUT") || tableName.equals("BES_DIGIT_INPUT")){//DO DI点
			if (pointList.getfEnabled().equals("1") && pointList.getfAlarmEnable().equals("1")){//如果使能状态为是,报警使能状态为是

				if (value == pointValue){//如果实时值等于相应的闭合状态,则报警
					int alarmJudgment = alarmJudgment(value,id,pointList,pointValueName,pointLevel,planValue);
				}


			}
		}else if(tableName.equals("BES_ANALOG_OUPUT") || tableName.equals("BES_ANALOG_INPUT")){//AO AI点
			if (pointList.getfEnabled().equals("1") && pointList.getfAlarmEnable().equals("1")) {//如果使能状态为是,报警使能状态为是
				if (pointValueName != null) {//AO,AI,如果报警名称不为null则报警

					int alarmJudgment = alarmJudgment(value,id,pointList,pointValueName,pointLevel,planValue);
				}

			}
		}else if (tableName.equals("BES_VPOINT")){//虚点
				if (pointList.getfNodeType().equals("6") || pointList.getfNodeType().equals("7")) { //如果闭合状态的值不为null,则说明是DO,DI类型
					if (value == pointValue ) {
						int alarmJudgment = alarmJudgment(value,id,pointList,pointValueName,pointLevel,planValue);
					}
				} else if (pointValueName != null) {//如果报警提示信息不为null,则说明是AI,AO类型
						int alarmJudgment = alarmJudgment(value,id,pointList,pointValueName,pointLevel,planValue);
				}
		}
		}
*/


		log.info("开始轮询控制器=【"+ddcInfo.getF_sys_name()+"】" );
		ISSPReturnObject returnObject = new ISSPReturnObject();

		//查询ddc下所有子节点
        /*begin remove xiepufeng 模糊查询所有子ddc修改为递归查出所有子ddc，修改原因，ddc 名称一旦修改就无法查出ddc信息*/
		// List<BESSbPzStruct> allChildNodeList = besPollingThread.besSbdyMapper.searchLikeClilderList(ddcInfo.getF_sys_name());
		/*end remove xiepufeng */
//		getChildNodeList(ddcInfo.getF_sys_name(),allChildNodeList);

		/*begin add xiepufeng 递归查出所有子ddc*/
		List<BESSbPzStruct>  allChildNodeList = besPollingThread.besSbdyMapper.searchByPSysNameChildren(ddcInfo.getF_sys_name());
		/*end add xiepufeng */

		//getChildList(besPollingThread.besSbdyMapper.searchClilderList(ddcInfo.getF_sys_name()),allChildNodeList);
		//判断DDC是否在线
//		Long startTime = System.currentTimeMillis();
		if(isDdcOnline(ddcInfo)) {
			ddcInfo.setF_status("1");
//			Long endTime = System.currentTimeMillis();
//			log.info(ddcInfo.getF_sys_name()+"设备在线时间是:"+(endTime-startTime));
		}else {
			ddcInfo.setF_status("0");
//			Long endTime = System.currentTimeMillis();
//			log.info(ddcInfo.getF_sys_name()+"设备不在线时间是:"+(endTime-startTime));
		}
		//allSbList.add(ddcInfo);
		if(allChildNodeList.size()>0){
			// allSbList.add(ddcInfo);
			if(null != allSbList){
				allSbList.add(ddcInfo);
			}
			BESSbtreeThread.realTimeData.put(ddcInfo.getF_sys_name(), ddcInfo);
			addAlarm(ddcInfo);
			BESPollingThread.sendClient(ddcInfo); // 数据发往客户端
			//对子节点轮询
			for (BESSbPzStruct sbtreeList : allChildNodeList) {
				JSONObject obj = new JSONObject();
				//ddc不在线，子节点状态设置为0
				if("0".equals(ddcInfo.getF_status())){
					sbtreeList.setF_status("0");
					obj.put("f_status", "0");// 更新设备树上状态为“0”，离线
					obj.put("f_sys_name", sbtreeList.getF_sys_name());
					obj.put("tabName", "BES_SBPZ_STRUCT");
//					besPollingThread.besSbdyMapper.editSbTreeStructInfoBySysName(obj);
////						//逻辑点需要设置初始值
//					judgePointType(obj,sbtreeList,null);
					//BESSbPzStruct besSbPzStruct = getCacheObject(sbtreeList.getF_sys_name(), BESSbtreeThread.getSbtreeList());
					BESSbPzStruct besSbPzStruct = BESSbtreeThread.realTimeData.get(sbtreeList.getF_sys_name());
					if(besSbPzStruct == null){
						editDataSource(obj, sbtreeList);
					}else if(!besSbPzStruct.getF_status().equals("0")){
//						besPollingThread.besSbdyMapper.editSbTreeStructInfoBySysName(obj);
//						//逻辑点需要设置初始值
//						judgePointType(obj,sbtreeList,null);
						editDataSource(obj, sbtreeList);
					}
					judgePointType(obj,sbtreeList,null);
				}else {
					if(sbtreeList.getF_type().equals("28")) {//电表的在线状态，在能耗采集时已存入数据库
						returnObject.setStatus(sbtreeList.getF_status());
					}else if (sbtreeList.getF_type().equals("9")) {//模块
						if ("1".equals(sbtreeList.getF_node_attribution())) {
							// 与Cross下位机通讯，返回通讯结果
							try {
								returnObject = besPollingThread.crossEquipmentService
										.compareCrossModule(sbtreeList.getF_sys_name_old(), sbtreeList.getF_psys_name(),"");
							} catch (UnsupportedEncodingException | RemoteException | ServiceException e) {
								returnObject.setStatus("0");
							}
						} else if ("2".equals(sbtreeList.getF_node_attribution())) {
							// 根据该模块父节点类型，判断属于哪个系统
							String PNodeType = besPollingThread.besSbdyMapper.querySbpzStructInfo(sbtreeList.getF_psys_name());
							// 与Lamp下位机通讯，返回通讯结果
							try {
								returnObject = besPollingThread.lampEquipmentService
										.compareLampModule(sbtreeList.getF_sys_name(), sbtreeList.getF_psys_name(),"","",PNodeType);
							} catch (UnsupportedEncodingException | RemoteException | ServiceException e) {
								returnObject.setStatus("0");
							}
						}
					}else if (sbtreeList.getF_type().equals("10") || sbtreeList.getF_type().equals("11")
							|| sbtreeList.getF_type().equals("12") || sbtreeList.getF_type().equals("13")
							|| sbtreeList.getF_type().equals("16")) {
						if ("1".equals(sbtreeList.getF_node_attribution())) {
							// 与Cross下位机通讯，返回通讯结果
							try {
								returnObject = besPollingThread.crossEquipmentService.compareCrossPoint(
										sbtreeList.getF_sys_name_old(), sbtreeList.getF_psys_name(), sbtreeList.getF_sbid(),"");
							} catch (UnsupportedEncodingException | RemoteException | ServiceException e) {
								returnObject.setStatus("0");
							}
						} else if ("2".equals(sbtreeList.getF_node_attribution())) {
							// 与Lamp下位机通讯，返回通讯结果
							try {
								returnObject = besPollingThread.lampEquipmentService.compareLampPoint(
										sbtreeList.getF_sys_name(), sbtreeList.getF_psys_name(), sbtreeList.getF_sbid(),"");
							} catch (UnsupportedEncodingException | RemoteException | ServiceException e) {
								returnObject.setStatus("0");
							}
						}
					}else if(sbtreeList.getF_type().equals("6") || sbtreeList.getF_type().equals("8") || sbtreeList.getF_type().equals("27")) {
						//支线耦合器，总线，总线（fln），状态 与父节点 控制器相同
					/*	for(int i=0;i<allSbList.size();i++) {
							if(sbtreeList.getF_psys_name().equals(allSbList.get(i).getF_sys_name())) {
								returnObject.setStatus(allSbList.get(i).getF_status());
								break;
							}
						}*/

						BESSbPzStruct besSbPzStruct = BESSbtreeThread.realTimeData.get(sbtreeList.getF_psys_name());

						if(null != besSbPzStruct){
							returnObject.setStatus(besSbPzStruct.getF_status());
						}

					}
					if (returnObject.getStatus() != null && returnObject.getStatus().equals("1")) {
						sbtreeList.setF_status("1");
						obj.put("f_status", "1");// 更新设备树上状态为“1”，正常
						obj.put("f_sys_name", sbtreeList.getF_sys_name());
						obj.put("tabName", "BES_SBPZ_STRUCT");
						editDataSource(sbtreeList.getF_sys_name(), obj);
						//besPollingThread.besSbdyMapper.editSbTreeStructInfoBySysName(obj);
						Map<String, Object> retpointInfo = (Map<String, Object>) returnObject.getData();
						//逻辑点需要更新初始值
						judgePointType(obj,sbtreeList,retpointInfo);
					} else if (returnObject.getStatus() != null && returnObject.getStatus().equals("0")) {
						sbtreeList.setF_status("0");
						obj.put("f_status", "0");// 更新设备树上状态为“0”，离线
						obj.put("f_sys_name", sbtreeList.getF_sys_name());
						obj.put("tabName", "BES_SBPZ_STRUCT");
						editDataSource(sbtreeList.getF_sys_name(), obj);
						//besPollingThread.besSbdyMapper.editSbTreeStructInfoBySysName(obj);
						//逻辑点需要更新初始值
						judgePointType(obj,sbtreeList,null);
					} else {
						sbtreeList.setF_status("0");
						obj.put("f_status", "0");// 更新设备树上状态为“0”，离线
						obj.put("f_sys_name", sbtreeList.getF_sys_name());
						obj.put("tabName", "BES_SBPZ_STRUCT");
						editDataSource(sbtreeList.getF_sys_name(), obj);
						//besPollingThread.besSbdyMapper.editSbTreeStructInfoBySysName(obj);
						//逻辑点需要更新初始值
						judgePointType(obj,sbtreeList,null);
					}
				}
				//allSbList.add(sbtreeList);
				if(null != allSbList){
					allSbList.add(sbtreeList);
				}

				BESSbtreeThread.realTimeData.put(sbtreeList.getF_sys_name(), sbtreeList);
				addAlarm(sbtreeList);
				BESPollingThread.sendClient(sbtreeList);// 数据发往客户端
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}else{
			JSONObject obj = new JSONObject();
			BESSbPzStruct sbtreeList = new BESSbPzStruct();
			//ddc不在线，子节点状态设置为0
			if("0".equals(ddcInfo.getF_status())){
				sbtreeList.setF_status("0");
				sbtreeList.setF_sys_name(ddcInfo.getF_sys_name());
				obj.put("f_status", "0");// 更新设备树上状态为“0”，离线
				obj.put("f_sys_name", ddcInfo.getF_sys_name());
				obj.put("tabName", "BES_SBPZ_STRUCT");
//				besPollingThread.besSbdyMapper.editSbTreeStructInfoBySysName(obj);
////					//逻辑点需要设置初始值
//				judgePointType(obj,sbtreeList,null);
				//BESSbPzStruct besSbPzStruct = getCacheObject(ddcInfo.getF_sys_name(), BESSbtreeThread.getSbtreeList());
				BESSbPzStruct besSbPzStruct = BESSbtreeThread.realTimeData.get(ddcInfo.getF_sys_name());
				if(besSbPzStruct == null){
					editDataSource(obj, sbtreeList);
				}else if(!besSbPzStruct.getF_status().equals("0")){
//					besPollingThread.besSbdyMapper.editSbTreeStructInfoBySysName(obj);
//					//逻辑点需要设置初始值
//					judgePointType(obj,sbtreeList,null);
					editDataSource(obj, sbtreeList);
				}
				judgePointType(obj,ddcInfo,null);
			}else {
				if (ddcInfo.getF_type().equals("10") || ddcInfo.getF_type().equals("11")
						|| ddcInfo.getF_type().equals("12") || ddcInfo.getF_type().equals("13")
						|| ddcInfo.getF_type().equals("16")) {
					if ("1".equals(ddcInfo.getF_node_attribution())) {
						// 与Cross下位机通讯，返回通讯结果
						try {
							returnObject = besPollingThread.crossEquipmentService.compareCrossPoint(
									ddcInfo.getF_sys_name(), ddcInfo.getF_psys_name(), ddcInfo.getF_sbid(),"");
						} catch (UnsupportedEncodingException | RemoteException | ServiceException e) {
							returnObject.setStatus("0");
						}
					} else if ("2".equals(ddcInfo.getF_node_attribution())) {
						// 与Lamp下位机通讯，返回通讯结果
						try {
							returnObject = besPollingThread.lampEquipmentService.compareLampPoint(
									ddcInfo.getF_sys_name(), ddcInfo.getF_psys_name(), ddcInfo.getF_sbid(),"");
						} catch (UnsupportedEncodingException | RemoteException | ServiceException e) {
							returnObject.setStatus("0");
						}
					}
				}
				if (returnObject.getStatus() != null && returnObject.getStatus().equals("1")) {
					sbtreeList.setF_status("1");
					obj.put("f_status", "1");// 更新设备树上状态为“1”，正常
					obj.put("f_sys_name", ddcInfo.getF_sys_name());
					obj.put("tabName", "BES_SBPZ_STRUCT");
					editDataSource(ddcInfo.getF_sys_name(), obj);
					//besPollingThread.besSbdyMapper.editSbTreeStructInfoBySysName(obj);
					Map<String, Object> retpointInfo = (Map<String, Object>) returnObject.getData();
					//逻辑点需要更新初始值
					judgePointType(obj,ddcInfo,retpointInfo);
				} else if (returnObject.getStatus() != null && returnObject.getStatus().equals("0")) {
					sbtreeList.setF_status("0");
					obj.put("f_status", "0");// 更新设备树上状态为“0”，离线
					obj.put("f_sys_name", ddcInfo.getF_sys_name());
					obj.put("tabName", "BES_SBPZ_STRUCT");
					editDataSource(ddcInfo.getF_sys_name(), obj);
					//besPollingThread.besSbdyMapper.editSbTreeStructInfoBySysName(obj);
					//逻辑点需要更新初始值
					judgePointType(obj,ddcInfo,null);
				} else {
					sbtreeList.setF_status("0");
					obj.put("f_status", "0");// 更新设备树上状态为“0”，离线
					obj.put("f_sys_name", ddcInfo.getF_sys_name());
					obj.put("tabName", "BES_SBPZ_STRUCT");
					editDataSource(ddcInfo.getF_sys_name(), obj);
					//besPollingThread.besSbdyMapper.editSbTreeStructInfoBySysName(obj);
					//逻辑点需要更新初始值
					judgePointType(obj,ddcInfo,null);
				}
			}
			//allSbList.add(sbtreeList);
			if(null != allSbList){
				allSbList.add(sbtreeList);
			}
			BESSbtreeThread.realTimeData.put(sbtreeList.getF_sys_name(), sbtreeList);
			addAlarm(sbtreeList);
			BESPollingThread.sendClient(sbtreeList); // 发往客户端
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		log.info("结束轮询控制器=【"+ddcInfo.getF_sys_name()+"】" );
//		mDoneSignal.countDown();
	}

	/**
	 * 修改数据库
	 */
	private void editDataSource(String f_sys_name, JSONObject obj){
		//BESSbPzStruct besSbPzStruct = getCacheObject(f_sys_name, BESSbtreeThread.getSbtreeList());// TODO
		BESSbPzStruct besSbPzStruct = BESSbtreeThread.realTimeData.get(f_sys_name);
		if(besSbPzStruct == null){
			besPollingThread.besSbdyMapper.editSbTreeStructInfoBySysName(obj);
		}else if(!besSbPzStruct.getF_status().equals(obj.get("f_status"))){
			besPollingThread.besSbdyMapper.editSbTreeStructInfoBySysName(obj);
		}

	}

	private void editDataSource(JSONObject obj, BESSbPzStruct sbtreeList){
		//besPollingThread.besSbdyMapper.editSbTreeStructInfoBySysName(obj);   需要修改设备树
		BESSbPzStruct besSbPzStruct = new BESSbPzStruct();
		besSbPzStruct.setF_sys_name(obj.getString("f_sys_name"));
		besSbPzStruct.setF_status(obj.getString("f_status"));
		sbAllTreeList.add(besSbPzStruct);
		//逻辑点需要更新初始值
		//judgePointType(obj,sbtreeList,null);
	}

	private BESSbPzStruct getCacheObject(String sysName, List<BESSbPzStruct> sbTreeList){
		if(sysName == null || sbTreeList == null){
			return null;
		}else{
			for(BESSbPzStruct besSbPzStruct : sbTreeList){
				if(sysName.equals(besSbPzStruct.getF_sys_name())){//键值对相同时
					return besSbPzStruct;
				}
			}
		}
		return null;
	}
	/**
	 * 判断逻辑点类型
	 * @param obj
	 * @param besSbPzStruct
	 */
	private void judgePointType(JSONObject obj, BESSbPzStruct besSbPzStruct,Map<String, Object> retpointInfo) {
		if (besSbPzStruct.getF_type().equals("10")) {
			setPointInitval(obj,besSbPzStruct,retpointInfo,"bes_analog_input");
		}else if( besSbPzStruct.getF_type().equals("11")) {
			setPointInitval(obj,besSbPzStruct,retpointInfo,"bes_analog_ouput");
		}else if(besSbPzStruct.getF_type().equals("12")) {
			setPointInitval(obj,besSbPzStruct,retpointInfo,"bes_digit_input");
		}else if(besSbPzStruct.getF_type().equals("13")) {
			setPointInitval(obj,besSbPzStruct,retpointInfo,"bes_digit_ouput");
		}else if(besSbPzStruct.getF_type().equals("16")) {
			//查询该虚点的工程单位
			Map<String, Object> vPointInfo = besPollingThread.besSbdyMapper.queryPointInfo(obj.getString("f_sys_name"),"bes_vpoint");

			if (vPointInfo.get("F_INIT_VAL") == "" || null == vPointInfo.get("F_INIT_VAL")){
				System.out.println(vPointInfo.get("F_INIT_VAL"));
			}

			if (retpointInfo != null && retpointInfo.get("InitValue") != null) {
				if(Validate_y.noNull(vPointInfo.get("F_ENGINEER_UNIT"))) {
					retpointInfo.put("Unit", vPointInfo.get("F_ENGINEER_UNIT"));
					//将数据库中初始值保存，用以设备无返回数据时使用
					obj.put("database_initval", vPointInfo.get("F_INIT_VAL"));
				}
			}else {
				retpointInfo = new HashMap<>();
				retpointInfo.put("Unit", vPointInfo.get("F_ENGINEER_UNIT"));
				retpointInfo.put("F_ACCURACY", vPointInfo.get("F_ACCURACY"));
				//将数据库中初始值保存，用以设备无返回数据时使用
				obj.put("database_initval", vPointInfo.get("F_INIT_VAL"));
			}
			setPointInitval(obj,besSbPzStruct,retpointInfo,"bes_vpoint");
		}
	}
	/**
	 * 更新数据库中逻辑点状态和初始值
	 * @param obj
	 * @param besSbPzStruct
	 * @param retpointInfo
	 * @param pointTabName
	 */
	private void setPointInitval(JSONObject obj, BESSbPzStruct besSbPzStruct, Map<String, Object> retpointInfo, String pointTabName) {
		if (retpointInfo != null && retpointInfo.get("InitValue") != null) {
			besSbPzStruct.setF_init_val(String.valueOf(retpointInfo.get("InitValue")));
			besSbPzStruct.setChannelIndex(String.valueOf(retpointInfo.get("ChannelIndex")));
			besSbPzStruct.setUnit(String.valueOf(retpointInfo.get("Unit")));
			obj.put("f_init_val", retpointInfo.get("InitValue"));
			obj.put("tabName", pointTabName);
			if(pointTabName.equals("bes_analog_input")){
				//aiSbList.add(obj);
				BESSbtreeThread.aiSbList.add(obj);
			}else if(pointTabName.equals("bes_analog_ouput")){
				BESSbtreeThread.aoSbList.add(obj);
			}else if(pointTabName.equals("bes_digit_input")){
				BESSbtreeThread.diSbList.add(obj);
			}else if(pointTabName.equals("bes_digit_ouput")){
				BESSbtreeThread.doSbList.add(obj);
			}
			//besPollingThread.besSbdyMapper.editSbTreeStructInfoBySysName(obj);
			/*BESSbPzStruct besSbPzStructResult = getCacheObject(besSbPzStruct.getF_sys_name(), BESSbtreeThread.getAllsblist());
			if(besSbPzStructResult == null){//如果没有  更新
				besPollingThread.besSbdyMapper.editSbTreeStructInfoBySysName(obj);
			}else if(!obj.get("f_init_val").equals(besSbPzStructResult.getF_init_val())){
				besPollingThread.besSbdyMapper.editSbTreeStructInfoBySysName(obj);
			}*/
		} else {
			if(besSbPzStruct.getF_type().equals("16")) {
				if(retpointInfo.get("F_ACCURACY")!=null){
					int f_accuracy =  (int) retpointInfo.get("F_ACCURACY");
					if(f_accuracy == 0) {
						besSbPzStruct.setF_init_val(obj.getString("database_initval"));
						obj.put("f_init_val", obj.getString("database_initval"));
					}else {

						double real=0.0;
						int rawdata =  obj.getInteger("database_initval");
						real= rawdata/(Math.pow(10, (f_accuracy)));
						String realData = String.valueOf(real);
						besSbPzStruct.setF_init_val(realData);
						obj.put("f_init_val", realData);

					}
				}
				if(retpointInfo.get("Unit")!=null){
					besSbPzStruct.setUnit(String.valueOf(retpointInfo.get("Unit")));
				}
				
			}else {
				besSbPzStruct.setF_init_val("0");
				obj.put("f_init_val", "0");
				obj.put("tabName", pointTabName);
				//besPollingThread.besSbdyMapper.editSbTreeStructInfoBySysName(obj);
				//BESSbPzStruct besSbPzStructResult = getCacheObject(besSbPzStruct.getF_sys_name(), BESSbtreeThread.getAllsblist());
				BESSbPzStruct besSbPzStructResult = BESSbtreeThread.realTimeData.get(besSbPzStruct.getF_sys_name());

				if(besSbPzStructResult == null){//如果没有  更新
					besPollingThread.besSbdyMapper.editSbTreeStructInfoBySysName(obj);
				}else if(!obj.get("f_init_val").equals(besSbPzStructResult.getF_init_val())){
					besPollingThread.besSbdyMapper.editSbTreeStructInfoBySysName(obj);
				}
			}
		}
		/*if(besPollingThread.lemsUtil.getOpcuseable() == 1 && "2".equals(besSbPzStruct.getF_type()) ) {
			//OpcData_cross.editOpcInitVal(besSbPzStruct);
			OpcData_cross.editOpcHWInitVal(besSbPzStruct);
		}*/
	}
	/**
	 * 获取ddc下所有子节点
	 * @param f_sys_name
	 * @param allChildNodeList
	 */
	private void getChildNodeList(String f_sys_name, List<BESSbPzStruct> allChildNodeList) {
		List<BESSbPzStruct> childNodeList = besPollingThread.besSbdyMapper.searchClilderList(f_sys_name);
		for(BESSbPzStruct childNode : childNodeList) {
			allChildNodeList.add(childNode);
			getChildNodeList(childNode.getF_sys_name(),allChildNodeList);
		}
	}

	private void getChildList(List<BESSbPzStruct> childNodeList, List<BESSbPzStruct> allChildNodeList) {
		//List<BESSbPzStruct> childNodeList = besPollingThread.besSbdyMapper.searchClilderList(f_sys_name);
		if(childNodeList != null){
			for(int i=0; i<childNodeList.size(); i++){
				BESSbPzStruct besSbPzStruct = new BESSbPzStruct();
				besSbPzStruct=childNodeList.get(i);
				allChildNodeList.add(besSbPzStruct);
				childNodeList.remove(i);
				getChildList(besSbPzStruct.getChilderBes(), allChildNodeList);
			}
			/*for(BESSbPzStruct childNode : childNodeList) {
				allChildNodeList.add(childNode);
				getChildList(childNode.getChilderBes(),allChildNodeList);
			}*/
		}
	}
	/**
	 * 判断ddc是否在线
	 * @param ddcInfo
	 * @return
	 */
	private boolean isDdcOnline(BESSbPzStruct ddcInfo) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		JSONObject obj = new JSONObject();
		if("2".equals(ddcInfo.getF_type())){
			// 查询该控制器的详细信息
			Map<String, Object> nodeInfo = besPollingThread.besSbdyMapper.findSbdyInfoBySelNode(ddcInfo.getF_sys_name(), "bes_ddc");
			// 与Cross下位机通讯，返回通讯结果
			try {
				/*returnObject = besPollingThread.crossEquipmentService.operCrossDDC(
						(String) nodeInfo.get("F_SYS_NAME"), (String) nodeInfo.get("F_IP_ADDR"),
						(String) nodeInfo.get("F_PORT"), 2);*/
				returnObject = besPollingThread.crossEquipmentService.operCrossDDC(
						(String) nodeInfo.get("F_SYS_NAME"), (String) nodeInfo.get("F_IP_ADDR"),
						(String) nodeInfo.get("F_PORT"), 6);
			} catch (UnsupportedEncodingException | RemoteException | ServiceException e) {
				returnObject.setStatus("0");
			}
		} else if("3".equals(ddcInfo.getF_type())){
			// 查询该控制器的详细信息
			Map<String, Object> nodeInfo = besPollingThread.besSbdyMapper.findSbdyInfoBySelNode(ddcInfo.getF_sys_name(), "bes_ddc");
			// 与Lamp下位机通讯，返回通讯结果
			try {
				returnObject = besPollingThread.lampEquipmentService.getLampDDCTime(
						(String) nodeInfo.get("F_SYS_NAME"), (String) nodeInfo.get("F_IP_ADDR"),
						(String) nodeInfo.get("F_PORT"), 6);
			} catch (UnsupportedEncodingException | RemoteException | ServiceException e) {
				returnObject.setStatus("0");
			}
		} else if("26".equals(ddcInfo.getF_type())) {//采集器在线状态，能耗采集时已存入数据库
			String status = ddcInfo.getF_status();
			returnObject.setStatus(status);
		}else if("16".equals(ddcInfo.getF_type())){
			// 查询该控制器的详细信息
			Map<String, Object> nodeInfo = besPollingThread.besSbdyMapper.findSbdyInfoBySelNodeVPonit(ddcInfo.getF_psys_name(), "bes_ddc");
			// 与Lamp下位机通讯，返回通讯结果
			try {
				returnObject = besPollingThread.lampEquipmentService.getLampDDCTime(
						(String) nodeInfo.get("F_SYS_NAME"), (String) nodeInfo.get("F_IP_ADDR"),
						(String) nodeInfo.get("F_PORT"), 6);
			} catch (UnsupportedEncodingException | RemoteException | ServiceException e) {
				returnObject.setStatus("0");
			}
		}
		//发送OPC数据 // TODO

		// OpcData_cross.editOpcHWInitVal(ddcInfo);
		/*if(ddcInfo.getF_status().equals("0")){

		}*/
		//更新数据库中状态
		if (returnObject.getStatus() != null && returnObject.getStatus().equals("1")) {
			ddcInfo.setF_status("1");
			obj.put("f_status", "1");// 更新设备树上状态为“1”，正常
			obj.put("f_sys_name", ddcInfo.getF_sys_name());
			obj.put("tabName", "BES_SBPZ_STRUCT");
			BESSbPzStruct besSbPzStruct = new BESSbPzStruct();
			besSbPzStruct.setF_sys_name(obj.getString("f_sys_name"));
			besSbPzStruct.setF_status(obj.getString("f_status"));
			sbAllTreeList.add(besSbPzStruct);

			//besPollingThread.besSbdyMapper.editSbTreeStructInfoBySysName(obj);
			return true;
		} else if (returnObject.getStatus() != null && returnObject.getStatus().equals("0")) {
			ddcInfo.setF_status("0");
			obj.put("f_status", "0");// 更新设备树上状态为“0”，离线
			obj.put("f_sys_name", ddcInfo.getF_sys_name());
			obj.put("tabName", "BES_SBPZ_STRUCT");
			//besPollingThread.besSbdyMapper.editSbTreeStructInfoBySysName(obj);
			BESSbPzStruct besSbPzStruct = new BESSbPzStruct();
			besSbPzStruct.setF_sys_name(obj.getString("f_sys_name"));
			besSbPzStruct.setF_status(obj.getString("f_status"));
			sbAllTreeList.add(besSbPzStruct);
			return false;
		} else {
			ddcInfo.setF_status("0");
			obj.put("f_status", "0");// 更新设备树上状态为“0”，离线
			obj.put("f_sys_name", ddcInfo.getF_sys_name());
			obj.put("tabName", "BES_SBPZ_STRUCT");
			//besPollingThread.besSbdyMapper.editSbTreeStructInfoBySysName(obj);
			BESSbPzStruct besSbPzStruct = new BESSbPzStruct();
			besSbPzStruct.setF_sys_name(obj.getString("f_sys_name"));
			besSbPzStruct.setF_status(obj.getString("f_status"));
			sbAllTreeList.add(besSbPzStruct);
			return false;
		}
	}

	public static void addAlarm(BESSbPzStruct besSbPzStruct){

		if(null == besSbPzStruct){
			return;
		}

		String fInitVal = besSbPzStruct.getF_init_val();

		if(!"255".equals(fInitVal)){
			if(BESSbtreeThread.besAlarmModels.containsKey(besSbPzStruct.getF_sys_name())){
				BESSbtreeThread.besAlarmModels.remove(besSbPzStruct.getF_sys_name());
			}
			return;
		}

		BesAlarmModel besAlarmModel = new BesAlarmModel();

		switch (besSbPzStruct.getF_nick_name()){
			case "JZGZZT":
				besAlarmModel.setfNickName("机组故障状态");
				besAlarmModel.setfPromptMsg("机组发生故障！");
				break;
			case "CXGLBJ":
				besAlarmModel.setfNickName("初效过滤网压差报警");
				besAlarmModel.setfPromptMsg("初效过滤网压差发生告警！");
				break;
			case "FDBJ":
				besAlarmModel.setfNickName("防冻报警");
				besAlarmModel.setfPromptMsg("防冻发生告警！");
				break;
			default:
				return;
		}

		String  fSysName = besSbPzStruct.getF_sys_name();

		String type = besSbPzStruct.getF_type();

		if("2".equals(type)){
			try {
				Map<String, Object>  ddcInfo = besPollingThread.besDdcMapper.queryDDCInfo(fSysName);

				if(ddcInfo != null && !ddcInfo.isEmpty()){
					besAlarmModel.setfAzwz((String) ddcInfo.get("F_AZWZ"));  // 告警位置
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("9".equals(type)){

			Map<String, Object>  moduleInfo = besPollingThread.crossEquipmentMapper.queryModuleInfo(fSysName);

			if(moduleInfo != null && !moduleInfo.isEmpty()){
				besAlarmModel.setfAzwz((String) moduleInfo.get("F_AZWZ"));  // 告警位置
			}

		}else if("10".equals(type)
				|| "11".equals(type)
				|| "12".equals(type)
				|| "13".equals(type)
				|| "14".equals(type)
				|| "15".equals(type) ){

			try {
				Map<String, Object>  moduleInfo = besPollingThread.crossEquipmentMapper.queryModuleInfo(besSbPzStruct.getF_psys_name());

				if(moduleInfo != null && !moduleInfo.isEmpty()){
					besAlarmModel.setfAzwz((String) moduleInfo.get("F_AZWZ"));  // 告警位置
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		besAlarmModel.setfSysName(fSysName);
		besAlarmModel.setfInitVal(fInitVal);         // 实时值
		besAlarmModel.setfPlanVal("0");                                   // 计划值
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		besAlarmModel.setfAlarmTime(sdf.format(date));         // 告警时间

		BESSbtreeThread.besAlarmModels.put(fSysName, besAlarmModel);

	}

	// 发数据到客户端
	public static void sendClient(BESSbPzStruct ddcInfo) {

		List<HttpSession> sessions = HttpSessionConfig.getActiveSessions();

		if (sessions.isEmpty()){
			return;
		}

		for (HttpSession session: sessions){
			WebSocketService.postEvent(session.getId(), WebSocketEvent.DDC, ddcInfo);
		}

	}

	public BESSbPzStruct getDdcInfo() {
		return ddcInfo;
	}
	public void setDdcInfo(BESSbPzStruct ddcInfo) {
		this.ddcInfo = ddcInfo;
	}

	public List<BESSbPzStruct> getAllSbList() {
		return allSbList;
	}
	public void setAllSbList(List<BESSbPzStruct> allSbList) {
		this.allSbList = allSbList;
	}

	public List<BESSbPzStruct> getSbAllTreeList() {
		return sbAllTreeList;
	}
	public void setSbAllTreeList(List<BESSbPzStruct> sbAllTreeList) {
		this.sbAllTreeList = sbAllTreeList;
	}

//	public List<JSONObject> getDoSbList() {
//		return doSbList;
//	}
//	public void setDoSbList(List<JSONObject> doSbList) {
//		this.doSbList = doSbList;
//	}
//
//	public List<JSONObject> getDiSbList() {
//		return diSbList;
//	}
//
//	public void setDiSbList(List<JSONObject> diSbList) {
//		this.diSbList = diSbList;
//	}
//
//	public List<JSONObject> getAoSbList() {
//		return aoSbList;
//	}
//	public void setAoSbList(List<JSONObject> aoSbList) {
//		this.aoSbList = aoSbList;
//	}
//
//	public List<JSONObject> getAiSbList() {
//		return aiSbList;
//	}
//	public void setAiSbList(List<JSONObject> aiSbList) {
//		this.aiSbList = aiSbList;
//	}
//
//	public List<JSONObject> getVSbList() {
//		return vSbList;
//	}
//
//	public void setVSbList(List<JSONObject> vSbList) {
//		this.vSbList = vSbList;
//	}
//

	public CountDownLatch getmDoneSignal() {
		return mDoneSignal;
	}
	public void setmDoneSignal(CountDownLatch mDoneSignal) {
		this.mDoneSignal = mDoneSignal;
	}

}
