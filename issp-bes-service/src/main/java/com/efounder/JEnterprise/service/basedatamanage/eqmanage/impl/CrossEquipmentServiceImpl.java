package com.efounder.JEnterprise.service.basedatamanage.eqmanage.impl;

import com.core.common.ISSPReturnObject;
import com.core.common.json.JsonHelper;
import com.core.common.metatype.Dto;
import com.core.common.metatype.impl.BaseDto;
import com.core.common.util.AlarmUtil;
import com.core.common.util.G4Utils;
import com.core.common.util.LEMSUtil;
import com.efounder.JEnterprise.collectorJob.BESSbtreeThread;
import com.efounder.JEnterprise.collectorJob.LEMSConstants;
import com.efounder.JEnterprise.commhandler.MsgSubPubHandler;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BESSbdyMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BesDdcMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.CrossEquipmentMapper;
import com.efounder.JEnterprise.mapper.dataAnalysises.BesWainingRealInfoMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESSbPzStruct;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESSbTreeNodeType;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesPointStruct;
import com.efounder.JEnterprise.model.dataAnalysises.BesWainingInfo;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.CrossEquipmentService;
import com.framework.common.utils.Validate_y;
import net.sf.json.JSONObject;
import org.ace.business.constant.DDCCmd;
import org.ace.business.constant.LDCCmd;
import org.ace.business.dto.ddc.ControllerDataDDC;
import org.ace.business.handler.SendMsgHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import testGsoap.LemsService;

import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.*;

//import com.efounder.JEnterprise.service.basedatamanage.eqmanage.LemsService;
/**
 * 楼控系统
 * 与下位机通讯实现类
 *
 * @author LvSihan 
 * @date 2018年11月7日  
 * @version 1.0
 */
@Service("CrossEquipmentService")
public class CrossEquipmentServiceImpl implements CrossEquipmentService {
	@Autowired
	private BesDdcMapper besDdcMapper;
	@Autowired
	private CrossEquipmentMapper crossEquipmentMapper;
	@Autowired
	private BESSbdyMapper besSbdyMapper;
	@Autowired
	private BesWainingRealInfoMapper besWainingRealInfoMapper;

	private static final Logger log = LoggerFactory.getLogger(LampEquipmentServiceImpl.class);


	/**
	 * (设置时间2/重启3/重置4/远程升级30)
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException  
	 */
	@SuppressWarnings("static-access")
	@Override
	public ISSPReturnObject operCrossDDC(String fSysName,String fIp, String fPort, int fIndex) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> data = new HashMap<>();
		Map<String, Object> pMap = new HashMap<>();
		if(fIp == "") {
			//获取该DDC属性信息
			Map<String,Object> ddcStructInfo = besSbdyMapper.findSbdyInfoBySelNode(fSysName, "bes_ddc");
			fIp = (String) ddcStructInfo.get("F_IP_ADDR");
		}
		if (fIndex == 2) {
			// 设置时间，获取当前系统时间
			Calendar c = Calendar.getInstance();
			data.put("year", c.get(Calendar.YEAR));
			data.put("month", c.get(Calendar.MONTH) + 1);
			data.put("day", c.get(Calendar.DATE));
			data.put("wday", c.get(c.DAY_OF_WEEK) - 1);
			data.put("hour", c.get(Calendar.HOUR_OF_DAY));
			data.put("minute", c.get(Calendar.MINUTE));
			data.put("second", c.get(Calendar.SECOND));
			pMap.put("data", data);
		}else{//3、4、30
			pMap.put("data", data);
		}
		pMap.put("IPAddr", fIp);//ip地址
		pMap.put("Port", fPort);//端口号	
		//与下位机通讯
		Dto retDto = operCrossController(pMap,fIndex);
		int reStr = retDto.getAsInteger("code");
		if (reStr == 0) {
			returnObject.setStatus("1");
			returnObject.setMsg("操作成功");
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg(judgecode(reStr));
		}
		return 	returnObject;
	}
	/**
	 * 获取DDC控制器时间
	 */
	@Override
    @SuppressWarnings({ "unchecked", "unused" })
	public ISSPReturnObject getCrossDDCTime(String fSysName,String fIp, String fPort, int fIndex) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> data = new HashMap<>();
		Map<String, Object> pMap = new HashMap<>();
		if(fIp == "") {
			//获取该DDC属性信息
			Map<String,Object> ddcStructInfo = besSbdyMapper.findSbdyInfoBySelNode(fSysName, "bes_ddc");
			fIp = (String) ddcStructInfo.get("F_IP_ADDR");
		}
		pMap.put("data", data);
		pMap.put("IPAddr", fIp);//ip地址
		pMap.put("Port", fPort);//端口号	
		//与下位机通讯
		Dto retDto = operCrossController(pMap,fIndex);
		int reStr = retDto.getAsInteger("code");
		if (reStr == 0) {
			Map<String,Object> timeMap = (Map<String, Object>) retDto.get("data");
			String year = String.valueOf(timeMap.get("year"));
			String mon = String.valueOf(timeMap.get("month"));
			String day = String.valueOf(timeMap.get("day"));
			String wday = String.valueOf(timeMap.get("wday"));
			String hour = String.valueOf( timeMap.get("hour"));
			String min = String.valueOf(timeMap.get("minute"));
			String sec = String.valueOf(timeMap.get("second"));
			String dataTime = year + "-" + mon + "-" + day + "  " + hour + ":" + min + ":" + sec;
			returnObject.setData(dataTime);
			returnObject.setStatus("1");
			returnObject.setMsg("操作成功");
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg(judgecode(reStr));
		}
		return 	returnObject;
	}
	/**
	 * Cross设置DDC控制器的所有参数
	 */
	@Override
    public ISSPReturnObject synCrossDDC(String f_sys_name) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> data = new HashMap<>();
		Map<String, Object> pMap = new HashMap<>();
		int flnID ;
		//通过f_sys_name查询该DDC属性信息
		Map<String,Object> ddcMap = besDdcMapper.queryDDCMap(f_sys_name);//查询当前ddc的全部信息

		String name = (String) ddcMap.get("F_SYS_NAME_OLD");//控制器的名字
		String alias = (String) ddcMap.get("F_NICK_NAME");//控制器别名
		String description = (String) ddcMap.get("F_DESCRIPTION");//控制器的描述信息
		String location = (String) ddcMap.get("F_AZWZ");//控制器的安装位置
		String zone = (String) ddcMap.get("F_SSQY");//区域信息
		Integer id = (Integer) ddcMap.get("F_GUID");//控制器的ID,必须唯一
		Integer active = Integer.parseInt(ddcMap.get("F_ENABLED").toString());//控制器的使能状态，只有使能时才会正常工作，才会与下位机通信
		String ip = (String) ddcMap.get("F_IP_ADDR");

		ControllerDataDDC controllerData = new ControllerDataDDC();
		controllerData.setName(name);
		controllerData.setAlias(alias);
		controllerData.setDescription(description);
		controllerData.setZone(zone);
		controllerData.setId(id);
		controllerData.setActive(active);

		// 同步数据到下位机
		boolean sendState = SendMsgHandler.setControllerDDC(ip, controllerData);

		if (!sendState)
		{
			returnObject.setStatus("0");
			returnObject.setMsg("下发失败");

			return returnObject;
		}

		// 添加订阅消息
		MsgSubPubHandler.addSubMsg(DDCCmd.CONTROLLER_PARAM_SET, ip);
		returnObject.setStatus("1");
		returnObject.setMsg("下发成功");

		List<Map<String,Object>> busList   = crossEquipmentMapper.queryBusInfo(f_sys_name);//查询当前ddc下所有总线的信息

		List<Map<String,Object>> pointList = crossEquipmentMapper.querypointList(f_sys_name);//查询当前ddc下所有的点位信息
		if (busList.size() > 0){
			for (Map<String,Object> bus : busList){
				String f_psys_name = (String) bus.get("F_SYS_NAME_OLD");
				if (bus.get("F_TYPE").equals(8)){//总线


					returnObject = synCrossDDCModule(f_psys_name);//模块

				} else if (bus.get("F_TYPE").equals(24)){//虚点总线
					returnObject = synCrossDDCVpoint(f_psys_name);//虚点
				}
			}
		}

		if (pointList.size() > 0){//当前ddc下的点位有数据的时候
			for (Map<String,Object> point : pointList){

				String pointName = (String) point.get("F_SYS_NAME_OLD");

				returnObject = synCrossDDCPoint(pointName);


			}
		}






		BesPointStruct besPointStruct = new BesPointStruct();

		// 同步能耗控制器参数到下位机
//		boolean sendState = SendMsgHandler.setControllerEDC(channelId, controllerData);
//
//		MsgSubPubHandler.addSubMsg(EDCCmd.CONTROLLER_PARAM_SET, channelId);

		//通讯所需数据
		/*data.put("Name", ddcMap.get("F_SYS_NAME"));//系统名称
		data.put("Alias", ddcMap.get("F_NICK_NAME"));//别名
		data.put("Description", ddcMap.get("F_DESCRIPTION"));//描述
		data.put("Location", ddcMap.get("F_AZWZ"));//安装位置
		data.put("Zone", ddcMap.get("F_SSQY"));//区域信息
		data.put("ID", Integer.parseInt((String) ddcMap.get("F_SBID")));//
		data.put("Active", ddcMap.get("F_ENABLED"));//使能		
		pMap.put("IPAddr", ddcMap.get("F_IP_ADDR"));//ip地址
		pMap.put("Port", ddcMap.get("F_PORT"));//端口号
		pMap.put("data", data);


		List<Map<String,Object>> crossModules = crossEquipmentMapper.queryCrossModule(f_sys_name);
		for(Map<String,Object> map: crossModules)
		{
			String ftype = map.get("F_TYPE").toString();
			if("23".equals(ftype))
			{
				List<Map<String,Object>> points =crossEquipmentMapper.queryCrossModule(map.get("F_SYS_NAME").toString());
				for(Map<String,Object> pointMap:points)
				{
					synCrossPoint(pointMap.get("F_SYS_NAME").toString()	,pointMap.get("F_PSYS_NAME").toString());
				}
				List<Map<String,Object>> modules =crossEquipmentMapper.queryModule(map.get("F_SYS_NAME").toString());
				for(Map<String,Object> module:modules)
				{
					synCrossModule(module.get("F_SYS_NAME").toString()	,module.get("F_PSYS_NAME").toString(),"");
				}
			}
			else
			{
				synCrossPoint(map.get("F_SYS_NAME").toString()	,map.get("F_PSYS_NAME").toString());
			}

		}


		//与下位机通讯
		Dto retDto = operCrossController(pMap,1);
		int reStr = retDto.getAsInteger("code");
		if (reStr == 0) {
			//通讯成功修改DDC在线状态和同步状态
			BesDdc besddcinfo = new BesDdc();
			besddcinfo.setfGuid(String.valueOf(ddcMap.get("F_GUID")));
			besddcinfo.setfSysName((String) ddcMap.get("F_SYS_NAME"));
			besddcinfo.setfDdcState("0");
			besddcinfo.setfDdcOnlinestate("0");
			besDdcMapper.updateByPrimaryKeySelective(besddcinfo);
			returnObject.setStatus("1");
			returnObject.setMsg("操作成功");
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg(judgecode(reStr));
		}*/
		return 	returnObject;
	}



	/**
	 *
	 * @Description: 模块点的同步
	 *
	 * @auther: wanghongjie
	 * @date: 16:47 2020/6/24
	 * @param: [f_psys_name]
	 * @return: void
	 *
	 */
	public ISSPReturnObject synCrossDDCModule(String f_psys_name){
		ISSPReturnObject returnObject = new ISSPReturnObject();
		int flnID ;
		List<Map<String,Object>> modelList = crossEquipmentMapper.queryModelInfo(f_psys_name);//查询当前ddc下所有模块的信息

		if (modelList.size() > 0){//如果是批量同步的话

			for (Map<String,Object> model : modelList){

				String name = (String) model.get("F_SYS_NAME_OLD");//名称
				Map<String,Object> modelInfo = crossEquipmentMapper.selectBesModule(name);//查询模块表的信息
				String fln = crossEquipmentMapper.selectFln(name);
				String alias     	= (String) modelInfo.get("F_NICK_NAME");//别名
				String description 	= (String) modelInfo.get("F_DESCRIPTION");//模块的描述信息
				String location 	= (String) modelInfo.get("F_AZWZ");//模块的安装位置
				int modelID 		= (Integer) modelInfo.get("F_TYPE");//模块型号编码
				if (fln.equals("PNP")){
					flnID = 1;//模块挂在哪条FLN总线上，可能取值PNP,FLN1、FLN2、FLN3、FLN4 (1,2,3,4,5)
				}else if (fln.equals("FLN1")){
					flnID = 2;
				}else if (fln.equals("FLN2")){
					flnID = 3;
				}else if (fln.equals("FLN3")){
					flnID = 4;
				}else if (fln.equals("FLN4")){
					flnID = 5;
				}
				int slaveAddress 	= (Integer) modelInfo.get("F_ADDR");//模块的通信地址
				int active 			= (Integer) modelInfo.get("F_ENABLED");//模块的使能状态 ACTIVE_ENABLE  使能  ACTIVE_DISABLE 禁用
				int id 				= (Integer) modelInfo.get("F_GUID");//模块的识别码


			}
		}else {//单个模块的同步
			Map<String,Object> modelInfo = crossEquipmentMapper.selectBesModule(f_psys_name);//查询模块表的信息
			if (modelInfo !=  null){
				String fln = crossEquipmentMapper.selectFln(f_psys_name);
				String alias     	= (String) modelInfo.get("F_NICK_NAME");//别名
				String description 	= (String) modelInfo.get("F_DESCRIPTION");//模块的描述信息
				String location 	= (String) modelInfo.get("F_AZWZ");//模块的安装位置
				int modelID 		= (Integer) modelInfo.get("F_TYPE");//模块型号编码
				if (fln.equals("PNP")){
					flnID = 1;//模块挂在哪条FLN总线上，可能取值PNP,FLN1、FLN2、FLN3、FLN4 (1,2,3,4,5)
				}else if (fln.equals("FLN1")){
					flnID = 2;
				}else if (fln.equals("FLN2")){
					flnID = 3;
				}else if (fln.equals("FLN3")){
					flnID = 4;
				}else if (fln.equals("FLN4")){
					flnID = 5;
				}
				int slaveAddress 	= (Integer) modelInfo.get("F_ADDR");//模块的通信地址
				int active 			= (Integer) modelInfo.get("F_ENABLED");//模块的使能状态 ACTIVE_ENABLE  使能  ACTIVE_DISABLE 禁用
				int id 				= (Integer) modelInfo.get("F_GUID");//模块的识别码

				//查询模块下所有的点位信息
				List<Map<String,Object>> pointLists = crossEquipmentMapper.selectPointList(f_psys_name);
				if (pointLists.size() > 0){
					for (Map<String,Object> pointList : pointLists){
						String f_sys_name = (String) pointList.get("F_SYS_NAME_OLD");

						returnObject = synCrossDDCPoint(f_sys_name);
					}
				}
			}



		}
		return returnObject;
	}

	/**
	 *
	 * @Description: DDC下点位的同步
	 *
	 * @auther: wanghongjie
	 * @date: 16:50 2020/6/24
	 * @param: [f_sys_name]
	 * @return: void
	 *
	 */
	private ISSPReturnObject synCrossDDCPoint(String f_sys_name) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Integer pointType;		//点类型
		Integer id;				//点的ID
		Integer active;			//是否启用，ACTIVE_ENABLE使能，ACTIVE_DISABLE禁止
		String name;		//点的名字
		String alias;		//点的别名
		String description;	//描述
		Integer moduleID;		//点所在模块的ID号
		Integer channelIndex;	//点所在通道索引
		Integer workMode;		//工作模式，手动模式或自动模式
		Integer polarity;		//极性，正向或反向
		Integer initValue;		//值
		Integer alarmActive; 	//报警是否启用，ALARM_ACTIVE_ENABLE使能，ALARM_ACTIVE_DISABLE禁止
		Integer alarmType;		//报警类型，不报警、标准报警、加强报警
		Integer alarmPriority;	//报警优先级
		Integer alarmTrigger;	//报警触发  开或关
		Integer activePassive;	//有源无源
		Integer lineType;		//有效输入类型，包括0-10V、0-20mA、4-20mA
		String unit;		//[12]工程单位，如℉、℃、KWh
		Integer highRange;		//最高阀值
		Integer lowRange;		//最低阀值
		Integer precision;		//精度
		Integer alarmHighValue;	//高限报警值
		Integer alarmLowValue;	//底限报警值

		String f_type = crossEquipmentMapper.selectType(f_sys_name);
		String tabName = crossEquipmentMapper.selectTabName(f_type);
		Map<String,Object> pointMap = crossEquipmentMapper.selectPointMap(tabName,f_sys_name);//点位的信息
		Map<String,Object> moduleMap = crossEquipmentMapper.selectModuleMap(f_sys_name);//点位所在的模块的信息
		if (pointMap.size() > 0 && moduleMap.size()>0){
			id 				= (Integer) pointMap.get("F_GUID");				//暂定自增的f_guid,用着的时候修改为F_SBID
			active 			= (Integer) pointMap.get("F_ENABLED");			//使能状态
			name 			= (String) pointMap.get("F_SYS_NAME_OLD"	);	//系统名称
			alias		 	= (String) pointMap.get("F_NICK_NAME");			//别名
			description 	= (String) pointMap.get("F_DESCRIPTION")	;	//描述
			moduleID 		= (Integer) moduleMap.get("F_GUID");			//点所在模块的ID号
			channelIndex 	= (Integer) moduleMap.get("F_ADDR");			//点所在通道索引
			workMode 		= (Integer) pointMap.get("F_WORK_MODE");		//工作模式，手动模式或自动模式
			polarity 		= (Integer) pointMap.get("F_REVERSED");			//极性，正向或反向
			alarmActive 	= (Integer) pointMap.get("F_ALARM_ENABLE");		//报警是否启用
			alarmType 		= (Integer) pointMap.get("F_ALARM_TYPE");		//报警类型
			alarmPriority 	= (Integer) pointMap.get("F_ALARM_PRIORITY");	//报警优先级
			if (tabName.equals("BES_ANALOG_INPUT")){
				pointType 		= 0;
				initValue 		= 0;											//初始值默认为0
				lineType 		= (Integer) pointMap.get("F_SINNAL_TYPE");		//有效输入类型，包括0-10V、0-20mA、4-20mA
				unit 			= (String) pointMap.get("F_ENGINEER_UNIT");		//工程单位，如℉、℃、KWh
				highRange 		= (Integer) pointMap.get("F_MAX_VAL");			//最高阀值
				lowRange 		= (Integer) pointMap.get("F_MIN_VAL");			//最低阀值
				precision 		= (Integer) pointMap.get("F_ACCURACY");			//精度
				alarmHighValue 	= (Integer) pointMap.get("F_HIGH_LIMIT");		//高限报警值
				alarmLowValue 	= (Integer) pointMap.get("F_LOW_LIMIT");		//底限报警值

			}else if (tabName.equals("BES_ANALOG_OUPUT")){
				pointType 		= 1;
				initValue 		= (Integer) pointMap.get("F_INIT_VAL");			//初始值
				lineType 		= (Integer) pointMap.get("F_SINNAL_TYPE");		//有效输入类型，包括0-10V、0-20mA、4-20mA
				unit 			= (String)  pointMap.get("F_ENGINEER_UNIT");	//工程单位，如℉、℃、KWh
				highRange 		= (Integer) pointMap.get("F_MAX_VAL");			//最高阀值
				lowRange 		= (Integer) pointMap.get("F_MIN_VAL");			//最低阀值
				precision 		= (Integer) pointMap.get("F_ACCURACY");			//精度
				alarmHighValue 	= (Integer) pointMap.get("F_HIGH_LIMIT");		//高限报警值
				alarmLowValue 	= (Integer) pointMap.get("F_LOW_LIMIT");		//底限报警值
			}else if (tabName.equals("BES_DIGIT_OUPUT")){
				pointType 		= 2;
				initValue 		= (Integer) pointMap.get("F_INIT_VAL");			//初始值
				alarmTrigger    = (Integer) pointMap.get("F_CLOSE_STATE");		//闭合状态
			}else if (tabName.equals("BES_DIGIT_INPUT")){
				pointType 		= 3;
				initValue 		= 0;											//初始值
				alarmTrigger    = (Integer) pointMap.get("F_CLOSE_STATE");		//闭合状态
				activePassive   = (Integer) pointMap.get("F_SOURCED");			//是否有源
			}
		}
	return returnObject;
	}
	/**
	 *
	 * @Description: 虚点的点位同步
	 *
	 * @auther: wanghongjie
	 * @date: 10:02 2020/6/28
	 * @param: [f_psys_name]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	public ISSPReturnObject synCrossDDCVpoint(String f_psys_name){
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Integer pointType;		//点类型
		Integer id;				//点的ID
		Integer active;			//是否启用，ACTIVE_ENABLE使能，ACTIVE_DISABLE禁止
		String name;		//点的名字
		String alias;		//点的别名
		String description;	//描述
		Integer initValue;		//值
		Integer alarmActive; 	//报警是否启用，ALARM_ACTIVE_ENABLE使能，ALARM_ACTIVE_DISABLE禁止
		Integer alarmType;		//报警类型，不报警、标准报警、加强报警
		Integer alarmPriority;	//报警优先级
		Integer alarmTrigger;	//报警触发  开或关
		String unit;		//[12]工程单位，如℉、℃、KWh
		Integer precision;		//精度
		Integer alarmHighValue;	//高限报警值
		Integer alarmLowValue;	//底限报警值
		//根据虚点线路的名称查询下面所有点位的虚点信息
		List<Map<String,Object>> vpointLists = crossEquipmentMapper.selectVpointList(f_psys_name);
		if (vpointLists.size() > 0){//批量同步的时候
			for (Map<String,Object> vpointList : vpointLists){
				name 			= (String) vpointList.get("F_SYS_NAME_OLD"	);	//系统名称
				//根据系统名称查询虚电表的点位信息
				Map<String,Object> vpointMap = crossEquipmentMapper.selectVpointMap(name);
				pointType		= (Integer) vpointMap.get("F_NODE_TYPE");		//点类型
				id 				= (Integer) vpointMap.get("F_GUID");			//暂定自增的f_guid,用着的时候修改为F_SBID
				active 			= (Integer) vpointMap.get("F_ENABLED");		//使能状态
				alias		 	= (String) vpointMap.get("F_NICK_NAME");		//别名
				description 	= (String) vpointMap.get("F_DESCRIPTION")	;	//描述
				alarmActive 	= (Integer) vpointMap.get("F_ALARM_ENABLE");	//报警是否启用
				alarmType 		= (Integer) vpointMap.get("F_ALARM_TYPE");		//报警类型
				alarmPriority 	= (Integer) vpointMap.get("F_ALARM_PRIORITY");	//报警优先级
				initValue       = (Integer) vpointMap.get("F_INIT_VAL");		//初始值
				if (vpointMap.get("F_NODE_TYPE").equals("4") || vpointMap.get("F_NODE_TYPE").equals("5")){//AI,AO点
					precision =	(Integer) vpointMap.get("F_ACCURACY");//精度
					alarmHighValue = (Integer) vpointMap.get("F_HIGH_LIMIT");//高限报警值
					alarmLowValue = (Integer) vpointMap.get("F_LOW_LIMIT");//底限报警值
				}else if (vpointMap.get("F_NODE_TYPE").equals("6") || vpointMap.get("F_NODE_TYPE").equals("7")){//DI,DO点
					alarmTrigger = (Integer) vpointMap.get("F_CLOSE_STATE");//闭合状态
				}
			}
		}else {//单个虚点点位同步的时候
			name = f_psys_name;
			Map<String,Object> vpointMap = crossEquipmentMapper.selectVpointMap(name);
			if (vpointMap != null) {
				if (vpointMap.get("F_NODE_TYPE").equals("4") || vpointMap.get("F_NODE_TYPE").equals("5")){//AI,AO点
					precision =	(Integer) vpointMap.get("F_ACCURACY");//精度
//					alarmHighValue = (Integer) vpointList.get("F_HIGH_LIMIT");//高限报警值
					alarmLowValue = (Integer) vpointMap.get("F_LOW_LIMIT");//底限报警值
				}else if (vpointMap.get("F_NODE_TYPE").equals("6") || vpointMap.get("F_NODE_TYPE").equals("7")){//DI,DO点
					alarmTrigger = (Integer) vpointMap.get("F_CLOSE_STATE");//闭合状态
				}
			}else {//批量同步时,虚点总线下没有虚点点位,返回消息
				returnObject.setMsg("无虚点点位");
			}

		}

		return returnObject;
	}

	/**
	 * 获取DDC控制器的所有配置参数
	 */
	@Override
    @SuppressWarnings("unchecked")
	public ISSPReturnObject compareCrossDDC(String fSysName,String f_guid, String fIp, String fPort)throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> data = new HashMap<>();
		Map<String, Object> pMap = new HashMap<>();
		if(fIp == "") {
			//获取该DDC属性信息
			Map<String,Object> ddcStructInfo = besSbdyMapper.findSbdyInfoBySelNode(fSysName, "bes_ddc");
			fIp = (String) ddcStructInfo.get("F_IP_ADDR");
		}
		pMap.put("data", data);
		pMap.put("IPAddr", fIp);// ip地址
		pMap.put("Port", fPort);// 端口号
		//与下位机通讯，设置index为7
		Dto retDto = operCrossController(pMap, 7);
		int reStr = retDto.getAsInteger("code");
		if (reStr == 0) {
			Map<String, Object> xDto = (Map<String, Object>) retDto.get("data");
			xDto.put("Active", xDto.get("Active").equals(1) ? "是" : "否");
			returnObject.setData(xDto);
			returnObject.setStatus("1");
			returnObject.setMsg("操作成功");
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg(judgecode(reStr));
		}
		return returnObject;
	}
	/**
	 * Cross设置模块所有信息
	 */
	@Override
    public ISSPReturnObject synCrossModule(String f_sys_name,String f_psys_name,String f_node_attribution) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		//获取该模块属性信息
		Map<String,Object> moduleMap = crossEquipmentMapper.queryModuleInfo(f_sys_name);
		Map<String, Object> pMap = new HashMap<>();
		Map<String, Object> data = new HashMap<>();
		//查询模块所属DDC控制器的IP地址
		Map<String, Object> ddcinfo = crossEquipmentMapper.queryDDCInfoByModule(f_psys_name);
		//查询模块所属总线
//		String crossBus = crossEquipmentMapper.queryBusByModule((String)moduleMap.get("F_SYS_NAME"));	
		//查询该模块位于第几条线路
	   			int FlnID = 0;
	   			List<Map<String,Object>> lines = besSbdyMapper.queryLines(f_psys_name);
	   			for(int i=0;i<lines.size();i++) {
	   				if(f_psys_name.equals(lines.get(i).get("F_SYS_NAME"))) {
	   					FlnID=i+1;
	   				}
	   			}
		pMap.put("IPAddr", ddcinfo.get("F_IP_ADDR"));//ip地址
		pMap.put("Port", ddcinfo.get("F_PORT"));//端口号
		data.put("Name", moduleMap.get("F_SYS_NAME_OLD"));//系统名称
		data.put("Alias", moduleMap.get("F_NICK_NAME"));//别名
		data.put("Description", moduleMap.get("F_DESCRIPTION"));//描述
		data.put("Location", moduleMap.get("F_AZWZ"));//安装位置
		data.put("ModelID", moduleMap.get("F_TYPE"));//模块型号
		data.put("FlnID", FlnID);//模块挂在哪条FLN总线上
		data.put("SlaveAddress", moduleMap.get("F_ADDR"));//模块的通信地址
		data.put("ID", moduleMap.get("F_SBID"));//
		data.put("Active", moduleMap.get("F_ENABLED"));
		pMap.put("data", data);

		Dto retDto = operCrossController(pMap, 11);//同步模块信息，index设置为11
		int reStr = retDto.getAsInteger("code");
		if (reStr == 0) {
			com.alibaba.fastjson.JSONObject obj = new com.alibaba.fastjson.JSONObject();
			obj.put("attrTabName", "BES_MODULE");
			obj.put("attr_f_sys_name", f_sys_name);
			obj.put("attr_f_module_state", "0");
			obj.put("attr_f_online_state", "0");
			besSbdyMapper.editSbTreeAttrInfoBySysName(obj);
			returnObject.setStatus("1");
			returnObject.setMsg("操作成功");
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg(judgecode(reStr));
		}
		return returnObject;
	}
	/**
	 * Cross获取模块所有信息
	 * @param f_sys_name
	 * @param f_psys_name
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	@Override
    @SuppressWarnings("unchecked")
	public ISSPReturnObject compareCrossModule(String f_sys_name,String f_psys_name,String f_node_attribution) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> pMap = new HashMap<>();
		Map<String, Object> data = new HashMap<>();
		//查询模块所属DDC控制器的IP地址
		Map<String, Object> ddcinfo = crossEquipmentMapper.queryDDCInfoByModule(f_psys_name);
		//查询该模块信息
		Map<String,Object> moduleMap = crossEquipmentMapper.queryModuleInfo(f_sys_name);
		pMap.put("IPAddr", ddcinfo.get("F_IP_ADDR"));//ip地址
		pMap.put("Port", ddcinfo.get("F_PORT"));//端口号
		data.put("ID",Integer.parseInt((String) moduleMap.get("F_SBID")) );//
		pMap.put("data", data);
		Dto retDto = operCrossController(pMap, 13);//获取一个模块的所有配置信息，index设置为13
		int reStr = retDto.getAsInteger("code");
		if (reStr == 0) {
			Map<String,Object> dataMap =  (Map<String, Object>) retDto.get("data");
			if(Validate_y.noNull(dataMap)) {
				dataMap.put("Active", dataMap.get("Active").equals(1) ? "是" : "否");
				returnObject.setData(dataMap);
				returnObject.setStatus("1");
				returnObject.setMsg("操作成功");
			}else {
				returnObject.setStatus("0");
				returnObject.setMsg("下位机无数据");
			}			
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg(judgecode(reStr));
		}
		return returnObject;		
	}
	/**
	 * Cross设置一个逻辑点的所有参数
	 */
	@Override
	public ISSPReturnObject synCrossPoint(String f_sys_name, String f_psys_name) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> pointMap = new HashMap<>();//该逻辑点属性信息
		Map<String, Object> ddcinfo = new HashMap<>();//所属DDC控制器信息
		Map<String, Object> moduleinfo = new HashMap<>();//所属模块信息
		Map<String, Object> data = new HashMap<>();//通讯所需
		Map<String, Object> pMap = new HashMap<>();//通讯所需

		//查询该节点的实体表名
		//String tabName = besSbdyMapper.findNodeTable(f_sys_name);
		String tabName = besSbdyMapper.findNodeTableChange(f_sys_name);
		if("".equals(tabName)){
			return returnObject;
		}

		try {
			// 查询该逻辑虚点属性信息
			pointMap = crossEquipmentMapper.queryPointInfoChange(tabName, f_sys_name);
		}catch (Exception e){
			e.printStackTrace();
		}

		if(null == pointMap){
			return returnObject;
		}

		if (tabName.equals("BES_VPOINT")) {
			// 查询该逻辑虚点属性信息
			//pointMap = crossEquipmentMapper.queryPointInfo(tabName, f_sys_name);
			// 查询逻辑虚点所属DDC控制器的IP地址
			ddcinfo = crossEquipmentMapper.queryDDCInfoByVPoint(f_psys_name);
			
			data.put("PointType", Integer.parseInt((String) pointMap.get("F_NODE_TYPE")));//点类型
		} else {
			// 查询该逻辑点属性信息
			//pointMap = crossEquipmentMapper.queryPointInfo(tabName, f_sys_name);
			//查询逻辑点所属DDC控制器的IP地址
			ddcinfo = crossEquipmentMapper.queryDDCInfoByPoint(f_sys_name);
			//查询逻辑点所属模块的信息
			moduleinfo = crossEquipmentMapper.queryModuleInfoByPointChange(f_sys_name);


			try {
				data.put("PointType", Integer.parseInt(((String) pointMap.get("F_NODE_TYPE")).substring(1)));//点类型
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		data.put("ID", Integer.parseInt((String) pointMap.get("F_SBID")));//点的f_sbid
		data.put("Active", pointMap.get("F_ENABLED"));
		data.put("Name", pointMap.get("F_SYS_NAME_OLD"));//系统名称
		data.put("Alias", pointMap.get("F_NICK_NAME"));//别名
		data.put("Description", pointMap.get("F_DESCRIPTION"));//描述		
		if(moduleinfo.get("F_SBID") != null) {
			data.put("ModuleID", Integer.parseInt((String) moduleinfo.get("F_SBID")));//点所在模块id
		}
		if(pointMap.get("F_CHANNEL_INDEX") != null) {
			data.put("ChannelIndex", pointMap.get("F_CHANNEL_INDEX"));//点所在通道索引
		}
		if(pointMap.get("F_WORK_MODE") != null) {
			data.put("WorkMode", pointMap.get("F_WORK_MODE"));//工作模式				
		}
		if(pointMap.get("F_REVERSED") != null) {
			data.put("Polarity", pointMap.get("F_REVERSED"));//极性，正向或反向
		}		
		data.put("AlarmActive", pointMap.get("F_ALARM_ENABLE"));//报警是否启用
		data.put("AlarmType", pointMap.get("F_ALARM_TYPE"));//报警类型
		data.put("AlarmPriority", pointMap.get("F_ALARM_PRIORITY"));//报警优先级
		if(pointMap.get("F_INIT_VAL") != null) {
			//Start add by yangjx at 20191206 for 虚点精度返回数据有误问题优化处理
			if(pointMap.get("F_ACCURACY") != null) {
				Integer accuracy = Integer.valueOf((Integer) pointMap.get("F_ACCURACY"));
				Integer initVal = new Double(Double.parseDouble(pointMap.get("F_INIT_VAL").toString()) * Math.pow(10, accuracy)).intValue();
				data.put("InitValue", initVal);
			}else {
				//End add by yangjx at 20191206 for 虚点精度返回数据有误问题优化处理
				data.put("InitValue", pointMap.get("F_INIT_VAL"));//值
			}
		}else {
			data.put("InitValue", 0);//值
		}
		if(pointMap.get("F_SINNAL_TYPE") != null) {
			data.put("LineType", pointMap.get("F_SINNAL_TYPE"));//有效输入类型，包括0-10V、0-20mA、4-20mA   
		}
		if(pointMap.get("F_ENGINEER_UNIT") != null) {
			data.put("Unit", pointMap.get("F_ENGINEER_UNIT"));//工程单位，如℉、℃、KWh   F_ENGINEER_UNIT
		}
		if(pointMap.get("F_MAX_VAL") != null) {
			data.put("HighRange", rangeByPrecision(String.valueOf(pointMap.get("F_MAX_VAL")),String.valueOf(pointMap.get("F_ACCURACY"))));//最大值
		}
		if(pointMap.get("F_MIN_VAL") != null) {
			data.put("LowRange", rangeByPrecision(String.valueOf(pointMap.get("F_MIN_VAL")),String.valueOf(pointMap.get("F_ACCURACY"))));//最小值
		}
		if(pointMap.get("F_ACCURACY") != null) {
			data.put("Precision", pointMap.get("F_ACCURACY"));//精度
		}
		if(pointMap.get("F_HIGH_LIMIT") != null && pointMap.get("F_HIGH_LIMIT") != "") {
			data.put("AlarmHighValue", pointMap.get("F_HIGH_LIMIT"));//高限报警值
		}else {
			data.put("AlarmHighValue", 0);
		}
		if(pointMap.get("F_LOW_LIMIT") != null && pointMap.get("F_LOW_LIMIT") != "") {
			data.put("AlarmLowValue", pointMap.get("F_LOW_LIMIT"));//底限报警值 
		}else {
			data.put("AlarmHighValue", 0);
		}
		if(pointMap.get("F_CLOSE_STATE") != null) {
			data.put("AlarmTrigger", pointMap.get("F_CLOSE_STATE"));//报警触发
		}
		if(pointMap.get("F_SOURCED") != null) {
			data.put("ActivePassive", pointMap.get("F_SOURCED"));//有源无源
		}			
		pMap.put("data", data);
		pMap.put("IPAddr", ddcinfo.get("F_IP_ADDR"));//ip地址
		pMap.put("Port", ddcinfo.get("F_PORT"));//端口号
		
		Dto retDto = operCrossController(pMap, 16);//同步逻辑点信息，index设置为16		
		int reStr = retDto.getAsInteger("code");
		if (reStr == 0) {//同步成功，设置同步状态和在线状态
//			com.alibaba.fastjson.JSONObject obj = new com.alibaba.fastjson.JSONObject();
//			obj.put("attrTabName", tabName);
//			obj.put("attr_f_sys_name", f_sys_name);
//			obj.put("attr_f_module_state", "0");
//			obj.put("attr_f_online_state", "0");
//			besSbdyMapper.editSbTreeAttrInfoBySysName(obj);			
			returnObject.setStatus("1");
			returnObject.setMsg("操作成功");
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg(judgecode(reStr));
		}
		return returnObject;
	}
	/**
	 * 根据精度调整范围值
	 * @param range
	 * @param F_ACCURACY
	 * @return
	 */
	private String rangeByPrecision(String range,String F_ACCURACY) {
		int f_accuracy = Integer.parseInt(F_ACCURACY);
		double	range_int =Double.parseDouble(range) * (Math.pow(10, (f_accuracy)));
		return String.valueOf(range_int);
	}
	/**
	 * Cross获取逻辑点数据
	 */
	@Override
    @SuppressWarnings("unchecked")
	public ISSPReturnObject compareCrossPoint(String f_sys_name,String f_psys_name,String f_sbid,String f_flag) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();		
		Map<String, Object> pMap = new HashMap<>();
		Map<String, Object> data = new HashMap<>();
		Map<String, Object> ddcinfo = new HashMap<>();
		// 查询该节点的实体表名
		String tabname = besSbdyMapper.findNodeTable(f_sys_name);
		// 查询该逻辑点属性信息
		Map<String, Object> pointMap = crossEquipmentMapper.queryPointInfo(tabname, f_sys_name);
		if(tabname != null && tabname.equals("BES_VPOINT")) {
			// 查询逻辑虚点所属DDC控制器的IP地址
			ddcinfo = crossEquipmentMapper.queryDDCInfoByVPoint(f_psys_name);
		}else {
			//查询逻辑实点所属DDC控制器的IP地址
			ddcinfo = crossEquipmentMapper.queryDDCInfoByPoint(f_sys_name);
		}				
		pMap.put("IPAddr", ddcinfo.get("F_IP_ADDR"));//ip地址
		pMap.put("Port", ddcinfo.get("F_PORT"));//端口号
		data.put("ID", Integer.parseInt(f_sbid));//
		pMap.put("data", data);
		Dto retDto = operCrossController(pMap, 20);//获取一个逻辑点的所有配置参数，index设置为20
		int reStr = retDto.getAsInteger("code");
		if (reStr == 0) {
			if (Validate_y.noNull(retDto.get("data"))) {
				Map<String, Object> xDto = (Map<String, Object>) retDto.get("data");
				if ("0".equals(xDto.get("WorkMode"))) {
					xDto.put("WorkMode", "自动");
				} else {
					xDto.put("WorkMode", "手动");
				}

				if ("0".equals(xDto.get("Polarity"))) {
					xDto.put("Polarity", "反向");
				} else {
					xDto.put("Polarity", "正向");
				}

				if ("0".equals(xDto.get("AlarmPriority"))) {
					xDto.put("AlarmPriority", "严重");
				} else {
					xDto.put("AlarmPriority", "危及安全");
				}

				if ("0".equals(xDto.get("AlarmType"))) {
					xDto.put("AlarmType", "不报警");
				} else if ("1".equals(xDto.get("AlarmType"))) {
					xDto.put("AlarmType", "标准报警");
				} else {
					xDto.put("AlarmType", "增强报警");
				}

				if (xDto.get("PointType") != null) {
					if(tabname != null && tabname.equals("BES_VPOINT") && !pointMap.get("F_NODE_TYPE").equals("7")) {
						String vpointname = besSbdyMapper.findVpointname(xDto.get("PointType").toString());
						if (vpointname != null ) {
							xDto.put("PointType", vpointname);
						}
					}else {
						List<BESSbTreeNodeType> nodeTypeList = besSbdyMapper
								.findChildByTreeNoteType("1"+xDto.get("PointType").toString());
						if (nodeTypeList != null && nodeTypeList.size() > 0) {
							xDto.put("PointType", nodeTypeList.get(0).getF_node_name());
						}	
					}
					

				}

				if ("0".equals(xDto.get("AlarmTrigger"))) {
					xDto.put("AlarmTrigger", "不闭合");
				} else {
					xDto.put("AlarmTrigger", "闭合");
				}

				if ("0".equals(xDto.get("AlarmActive"))) {
					xDto.put("AlarmActive", "不使能");
				} else {
					xDto.put("AlarmActive", "使能");
				}

//				// 查询逻辑点所属模块的信息
				Map<String, Object>  moduleinfo = crossEquipmentMapper.queryModuleInfoByPoint(f_sys_name);
				if(Validate_y.noNull(moduleinfo)) {
					xDto.put("ModuleID",moduleinfo.get("F_NICK_NAME"));
				}				
				xDto.put("Active", xDto.get("Active").equals(1) ? "是" : "否");
				//逻辑虚点，精度转换
				if(tabname != null && tabname.equals("BES_VPOINT") && !pointMap.get("F_NODE_TYPE").equals("7")) {
					if(pointMap.get("F_ACCURACY")!=null){
						int f_accuracy =  (int) pointMap.get("F_ACCURACY");
						if(f_accuracy == 0) {
							xDto.put("InitValue", xDto.get("InitValue"));
						}else {
							double real=0.0;
							int rawdata =  (int) xDto.get("InitValue");
							real= rawdata/(Math.pow(10, (f_accuracy)));
							String realData = String.valueOf(real);
							xDto.put("InitValue", realData);
						}
					}
					
										
				}else {
					if(pointMap != null && Validate_y.noNull(pointMap.get("F_ACCURACY"))) {
						int f_accuracy =  (int) pointMap.get("F_ACCURACY");
						if(f_accuracy == 0) {
							xDto.put("InitValue", xDto.get("InitValue"));
						}else {
							double real=0.0;
							//int rawdata =  (int) xDto.get("InitValue");
							try {
								Integer rawdata = Integer.valueOf((xDto.get("InitValue").toString()));
								real= rawdata/(Math.pow(10, (f_accuracy)));
								String realData = String.valueOf(real);
								xDto.put("InitValue", realData);
							}catch (Exception e){
								e.printStackTrace();
							}


						}						
					}					
				}
				returnObject.setData(xDto);
				returnObject.setStatus("1");
				returnObject.setMsg("操作成功");
			}else {
				returnObject.setStatus("0");
				returnObject.setMsg("下位机无数据");
			}			
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg(judgecode(reStr));
		}
		return returnObject;		
	}

	/**
	 * 虚点数据对比
	 * @param f_sys_name
	 * @return
	 */
	@Override
	public ISSPReturnObject compareVirtualPoint(String f_sys_name)
	{
		ISSPReturnObject result = new ISSPReturnObject();

		if (!StringUtils.hasText(f_sys_name))
		{
			result.setStatus("0");
			result.setMsg("参数错误");
			return result;
		}

		Map<String, Object> virtualPoint = crossEquipmentMapper.findVpointBySysName(f_sys_name);

		if(null == virtualPoint || virtualPoint.isEmpty())
		{
			result.setMsg("参数错误");
			result.setStatus("0");
			return result;
		}

		String id = (String)virtualPoint.get("F_SBID");


		Map<String, Object> treeNodeInfo = crossEquipmentMapper.getPointInfoSBDY(f_sys_name);

		String pSysName = (String) treeNodeInfo.get("F_PSYS_NAME");

		// 获取 tree ddc 信息
		Map<String, Object> treeDdcInfo = besSbdyMapper.getParentNodeInfo(pSysName);

		Map<String, Object>  ddcInfo = besDdcMapper.queryDDCInfo((String) treeDdcInfo.get("F_SYS_NAME"));

		String channelId = (String) ddcInfo.get("F_CHANNEL_ID");

		if (!StringUtils.hasText(channelId))
		{
			result.setMsg("参数错误");
			result.setStatus("0");
			return result;
		}

		if (treeNodeInfo.get("F_NODE_ATTRIBUTION").equals("1")) {//楼控

			boolean sendState = SendMsgHandler.getPointParamDDC(channelId, Integer.valueOf(id));

			if (!sendState)
			{
				result.setStatus("0");
				result.setMsg("下发失败");

				return result;
			}

			// 添加订阅消息
			MsgSubPubHandler.addSubMsg(DDCCmd.POINT_PARAM_GET, channelId);

			result.setStatus("1");
			result.setMsg("下发成功");

		} else if (treeNodeInfo.get("F_NODE_ATTRIBUTION").equals("2")) {//照明

			boolean sendState = SendMsgHandler.getPointParamLDC(channelId, Integer.valueOf(id));

			if (!sendState)
			{
				result.setStatus("0");
				result.setMsg("下发失败");

				return result;
			}

			// 添加订阅消息
			MsgSubPubHandler.addSubMsg(LDCCmd.POINT_PARAM_GET, channelId);

			result.setStatus("1");
			result.setMsg("下发成功");
		}

		return result;
	}

	/**
	 * 设置一个逻辑点的值
	 */
	@Override
    public ISSPReturnObject debugCrossPointInfo(com.alibaba.fastjson.JSONObject obj) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		//查询该节点的实体表名
		String tabName = besSbdyMapper.findNodeTableChange(obj.getString("f_sys_name"));
		// 查询该逻辑点属性信息
		Map<String, Object> pointMap = crossEquipmentMapper.queryPointInfoChange(tabName, obj.getString("f_sys_name"));
		Map<String, Object> ddcinfo = new HashMap<String, Object>();
		if("BES_VPOINT".equals(tabName)){
			//查询逻辑点所属IP路由器的IP地址
			ddcinfo = crossEquipmentMapper.queryDDCInfoByPointxd(obj.getString("f_sys_name"));
		}else{
			//查询逻辑点所属IP路由器的IP地址
			ddcinfo = crossEquipmentMapper.queryDDCInfoByPointChange(obj.getString("f_sys_name"));
		}
		
		Map<String, Object> pMap = new HashMap<>();
		Map<String, Object> data = new HashMap<>();
		Integer initVal = Integer.parseInt(obj.getString("f_init_val"));
		//温控器的AO点特殊处理  F_TYPE == 3代表温控器     B_TYPE == 11  代表AO点
		if(("11").equals(pointMap.get("B_TYPE").toString())
				|| ("10").equals(pointMap.get("B_TYPE").toString())){
			try {
				Integer accuracy = Integer.valueOf((Integer) pointMap.get("F_ACCURACY"));
				initVal = new Double(Double.parseDouble(obj.getString("f_init_val")) * Math.pow(10, accuracy)).intValue();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		data.put("ID", Integer.parseInt(String.valueOf(pointMap.get("F_SBID"))));//点的f_sbid
		if(obj.containsKey("f_work_mode")){
			data.put("WorkMode", Integer.parseInt(obj.getString("f_work_mode")));
		}
		
		data.put("Value", initVal);//系统名称
		pMap.put("data", data);
		pMap.put("IPAddr", ddcinfo.get("F_IP_ADDR"));//ip地址
		pMap.put("Port", ddcinfo.get("F_PORT"));//端口号
		Dto retDto = operCrossController(pMap, 17);//设置一个逻辑点的值  ，index设置为17	
		int reStr = retDto.getAsInteger("code");
		if (reStr == 0) {//同步成功，更新本地数据库
//			com.alibaba.fastjson.JSONObject jobj = new com.alibaba.fastjson.JSONObject();
			
//			jobj.put("attr_f_sys_name", obj.getString("f_sys_name"));
//			jobj.put("f_work_mode", obj.getString("f_work_mode"));
//			jobj.put("f_init_val", obj.getString("f_init_val"));
			obj.put("tabName", tabName);
			//besSbdyMapper.editSbTreeStructInfoBySysName(obj);
			besSbdyMapper.editSbTreeStructInfoBySysNameChange(obj);
			returnObject.setStatus("1");
			returnObject.setMsg("操作成功");
			//更新缓存--SbtreeList
		/*	for(int i=0;i<BESSbtreeThread.getSbtreeList().size();i++) {// TODO
				if(obj.getString("f_sys_name").equals(BESSbtreeThread.getSbtreeList().get(i).getF_sys_name())) {// TODO
					BESSbtreeThread.getSbtreeList().get(i).setF_init_val(obj.getString("f_init_val"));// TODO
					BESSbtreeThread.getSbtreeList().get(i).setF_status("1");// TODO
					break;
				}
			}*/
			BESSbPzStruct besSbPzStruct = BESSbtreeThread.realTimeData.get(obj.getString("f_sys_name"));

			if(null != besSbPzStruct){
				besSbPzStruct.setF_init_val(obj.getString("f_init_val"));
				besSbPzStruct.setF_status("1");
			}

			//更新缓存--Allsblist(没有则清空，防止覆盖页面状态)
		/*	boolean ishave = false;
			for(int j=0;j<BESSbtreeThread.getAllsblist().size();j++) {
				if(obj.getString("f_sys_name").equals(BESSbtreeThread.getAllsblist().get(j).getF_sys_name())) {
					BESSbtreeThread.getAllsblist().get(j).setF_init_val(obj.getString("f_init_val"));
					BESSbtreeThread.getAllsblist().get(j).setF_status("1");
					ishave = true;
					break;
				}else {
					ishave = false;
				}
			}
			if(!ishave) {
				BESSbtreeThread.getAllsblist().clear();
			}*/
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg(judgecode(reStr));
		}
		return returnObject;		
	}

    /**
     * 设置一个逻辑点的值
     */
    @Override
    public ISSPReturnObject debugCrossPointInfoxd(com.alibaba.fastjson.JSONObject obj) throws UnsupportedEncodingException, RemoteException, ServiceException {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        //查询该节点的实体表名
        String tabName = besSbdyMapper.findNodeTable(obj.getString("f_sys_name"));
        // 查询该逻辑点属性信息
        Map<String, Object> pointMap = crossEquipmentMapper.queryPointInfo(tabName, obj.getString("f_sys_name"));
        //查询逻辑点所属IP路由器的IP地址
        Map<String, Object> ddcinfo = crossEquipmentMapper.queryDDCInfoByPointxd(obj.getString("f_sys_name"));
        Map<String, Object> pMap = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        Integer initVal = 0;
        //温控器的AO点特殊处理  F_TYPE == 3代表温控器     B_TYPE == 11  代表AO点
        if(pointMap.get("F_TYPE").equals("3") && pointMap.get("B_TYPE").equals(11)){
            String[] initValAttr = obj.getString("f_init_val").split("\\.");
            if(initValAttr.length > 1){//代表有小数
                initVal = Integer.parseInt(initValAttr[0]+initValAttr[1].substring(0,1));
            }else{//没有小数  如果两位  后边加个0
                if(initValAttr[0].length() == 2){//如果两位  后边加个0
                    initVal = Integer.parseInt(initValAttr[0]+"0");
                }else{
                    initVal = Integer.parseInt(initValAttr[0]);
                }
            }
        }else{
            initVal = Integer.parseInt(obj.getString("f_init_val"));
        }
        data.put("ID", Integer.parseInt(String.valueOf(pointMap.get("F_SBID"))));//点的f_sbid
        data.put("WorkMode", Integer.parseInt(obj.getString("f_work_mode")));
        data.put("Value", initVal);//系统名称
        pMap.put("data", data);
        pMap.put("IPAddr", ddcinfo.get("F_IP_ADDR"));//ip地址
        pMap.put("Port", ddcinfo.get("F_PORT"));//端口号
        Dto retDto = operCrossController(pMap, 17);//设置一个逻辑点的值  ，index设置为17
        int reStr = retDto.getAsInteger("code");
        if (reStr == 0) {//同步成功，更新本地数据库
//			com.alibaba.fastjson.JSONObject jobj = new com.alibaba.fastjson.JSONObject();

//			jobj.put("attr_f_sys_name", obj.getString("f_sys_name"));
//			jobj.put("f_work_mode", obj.getString("f_work_mode"));
//			jobj.put("f_init_val", obj.getString("f_init_val"));
            obj.put("tabName", tabName);
            besSbdyMapper.editSbTreeStructInfoBySysName(obj);
            returnObject.setStatus("1");
            returnObject.setMsg("操作成功");
            //更新缓存--SbtreeList
         /*   for(int i=0;i<BESSbtreeThread.getSbtreeList().size();i++) {// TODO
                if(obj.getString("f_sys_name").equals(BESSbtreeThread.getSbtreeList().get(i).getF_sys_name())) {// TODO
                    BESSbtreeThread.getSbtreeList().get(i).setF_init_val(obj.getString("f_init_val"));// TODO
                    BESSbtreeThread.getSbtreeList().get(i).setF_status("1");// TODO
                    break;
                }
            }*/

			BESSbPzStruct besSbPzStruct = BESSbtreeThread.realTimeData.get(obj.getString("f_sys_name"));

			if(null != besSbPzStruct){
				besSbPzStruct.setF_init_val(obj.getString("f_init_val"));
				besSbPzStruct.setF_status("1");
			}

            //更新缓存--Allsblist(没有则清空，防止覆盖页面状态)
      /*      boolean ishave = false;
            for(int j=0;j<BESSbtreeThread.getAllsblist().size();j++) {
                if(obj.getString("f_sys_name").equals(BESSbtreeThread.getAllsblist().get(j).getF_sys_name())) {
                    BESSbtreeThread.getAllsblist().get(j).setF_init_val(obj.getString("f_init_val"));
                    BESSbtreeThread.getAllsblist().get(j).setF_status("1");
                    ishave = true;
                    break;
                }else {
                    ishave = false;
                }
            }
            if(!ishave) {
                BESSbtreeThread.getAllsblist().clear();
            }*/
        } else {
            returnObject.setStatus("0");
            returnObject.setMsg(judgecode(reStr));
        }
        return returnObject;
    }


	public static void main(String[] args){
		Integer initVal = 0;
		String[] initValAttr = new String("23").split("\\.");
		//String[] initValAttr = obj.getString("f_init_val").split(".");
		if(initValAttr.length > 1){//代表有小数
			initVal = Integer.parseInt(initValAttr[0]+initValAttr[1].substring(0,1));
		}else{//没有小数  如果两位  后边加个0
			if(initValAttr[0].length() == 2){//如果两位  后边加个0
				initVal = Integer.parseInt(initValAttr[0]+"0");
			}else{
				initVal = Integer.parseInt(initValAttr[0]);
			}
		}
		System.out.println(initVal);
	}
	/**
	 * Cross楼控与下位机通讯
	 * @param eqMap
	 * @param index
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	@Deprecated
	@SuppressWarnings({ "static-access", "unchecked" })
	public Dto operCrossController(Map<String,Object> eqMap, int index) throws UnsupportedEncodingException, RemoteException, ServiceException {

		if (true)
		{
			log.info("此方法已废弃");
			return null;
		}

		Map<String, Object> pMap = new HashMap<>();
		Map<String, Object> data = new HashMap<>();
		//判断操作类型
		if(index == 0) {//新增一个IP路由器
			data.put("Name", eqMap.get("attr_f_sys_name"));//系统名称
			data.put("Alias", eqMap.get("attr_f_nick_name"));//别名
			data.put("Description", eqMap.get("attr_f_description"));//描述
			data.put("Location", eqMap.get("attr_f_azwz"));//安装位置
			data.put("Zone", eqMap.get("attr_f_ssqy"));//区域信息
			data.put("ID", eqMap.get("f_sbid"));//
			data.put("Active", eqMap.get("attr_f_enabled"));//使能
			pMap.put("IPAddr", eqMap.get("attr_f_ip_addr"));//ip地址
			pMap.put("Port", eqMap.get("attr_f_port"));//端口号
			pMap.put("data", data);
		}else if(index == 10){//新增一个模块
			data.put("Name", eqMap.get("attr_f_sys_name"));//系统名称
			data.put("Alias", eqMap.get("attr_f_nick_name"));//别名
			data.put("Description", eqMap.get("attr_f_description"));//描述
			data.put("Location", eqMap.get("attr_f_azwz"));//安装位置
			data.put("ModelID", Integer.parseInt((String) eqMap.get("attr_f_type")));//模块型号
			data.put("FlnID", eqMap.get("FlnID"));//模块挂在哪条FLN总线上
			data.put("SlaveAddress",  Integer.parseInt((String) eqMap.get("attr_f_addr")));//模块的通信地址
			data.put("ID",  eqMap.get("f_sbid"));//
			data.put("Active", eqMap.get("attr_f_enabled"));
			pMap.put("IPAddr", eqMap.get("IPAddr"));//ip地址
			pMap.put("Port", eqMap.get("Port"));//端口号
			pMap.put("data", data);
		}else if(index == 15) {//新增一个逻辑点
			data.put("PointType",Integer.parseInt((String) eqMap.get("attr_f_node_type")));//点类型
			data.put("ID", eqMap.get("f_sbid"));//点的f_sbid
			data.put("Active", "".equals(Integer.parseInt((String)eqMap.get("attr_f_enabled")))?1:Integer.parseInt((String)eqMap.get("attr_f_enabled")));
			data.put("Name", eqMap.get("attr_f_sys_name"));//系统名称
			data.put("Alias", eqMap.get("attr_f_nick_name"));//别名
			data.put("Description", eqMap.get("attr_f_description"));//描述
			if(eqMap.get("moduleFguid") != null) {
				data.put("ModuleID", Integer.parseInt((String)eqMap.get("moduleFguid")));//点所在模块id
			}
			if(eqMap.get("attr_f_channel_index") != null) {
				data.put("ChannelIndex", Integer.parseInt((String)eqMap.get("attr_f_channel_index")));//点所在通道索引
			}
			if(eqMap.get("attr_f_work_mode") != null) {
				data.put("WorkMode", Integer.parseInt((String)eqMap.get("attr_f_work_mode")));//工作模式
			}
			if(eqMap.get("attr_f_reversed") != null) {
				data.put("Polarity", Integer.parseInt((String)eqMap.get("attr_f_reversed")));//极性，正向或反向
			}

			data.put("AlarmActive", Integer.parseInt((String)eqMap.get("attr_f_alarm_enable")));//报警是否启用
			data.put("AlarmType", Integer.parseInt((String)eqMap.get("attr_f_alarm_type")));//报警类型
			data.put("AlarmPriority", Integer.parseInt((String)eqMap.get("attr_f_alarm_priority")));//报警优先级
			if(eqMap.get("attr_f_init_val") != null && eqMap.get("attr_f_init_val") != "") {
				data.put("InitValue", Integer.parseInt((String)eqMap.get("attr_f_init_val")));//值
			}else {
				data.put("InitValue", 0);//值
			}
			if(eqMap.get("attr_f_sinnal_type") != null) {
				data.put("LineType", Integer.parseInt((String)eqMap.get("attr_f_sinnal_type")));//有效输入类型，包括0-10V、0-20mA、4-20mA
			}
			if(eqMap.get("attr_f_engineer_unit") != null) {
				data.put("Unit", eqMap.get("attr_f_engineer_unit"));//工程单位，如℉、℃、KWh   F_ENGINEER_UNIT
			}
			if(eqMap.get("attr_f_max_val") != null) {
				data.put("HighRange", Double.parseDouble((String)eqMap.get("attr_f_max_val")));//最高阀值
			}
			if(eqMap.get("attr_f_min_val") != null) {
				data.put("LowRange", Integer.parseInt((String)eqMap.get("attr_f_min_val")));//最低阀值
			}
			if(eqMap.get("attr_f_accuracy") != null) {
				data.put("Precision", Integer.parseInt((String)eqMap.get("attr_f_accuracy")));//精度
			}
			if(eqMap.get("attr_f_high_limit") != null && eqMap.get("attr_f_high_limit") != "") {
				data.put("AlarmHighValue", Integer.parseInt((String)eqMap.get("attr_f_high_limit")));//高限报警值
			}else {
				data.put("AlarmHighValue", 0);//高限报警值
			}
			if(eqMap.get("attr_f_low_limit") != null && eqMap.get("attr_f_low_limit") != "") {
				data.put("AlarmLowValue", Integer.parseInt((String)eqMap.get("attr_f_low_limit")));//底限报警值
			}else {
				data.put("AlarmLowValue", 0);//底限报警值
			}
			if(eqMap.get("attr_f_close_state") != null) {
				data.put("AlarmTrigger", Integer.parseInt((String)eqMap.get("attr_f_close_state")));//报警触发
			}
			if(eqMap.get("attr_f_sourced") != null) {
				data.put("ActivePassive", Integer.parseInt((String)eqMap.get("attr_f_sourced")));//有源无源
			}

			pMap.put("IPAddr", eqMap.get("DDCIPAddr"));//ip地址
			pMap.put("Port", eqMap.get("DDCPort"));//端口号
			pMap.put("data", data);
		}else if(index == 5 ) {
			pMap.put("IPAddr", eqMap.get("F_IP_ADDR"));//ip地址
			pMap.put("Port", eqMap.get("F_PORT"));//端口号
			pMap.put("data", data);
		}else if(index == 24){// 删除一个场景
			pMap.put("IPAddr", eqMap.get("IPAddr"));//ip地址
			pMap.put("Port", eqMap.get("Port"));//端口号
			pMap.put("item", "cross");
			pMap.put("index", 24);
			data.put("ID",eqMap.get("Cjid"));
			pMap.put("data", data);
		}else {
			pMap = eqMap;
		}
		pMap.put("index", index);
		pMap.put("item", "cross");
		String jsonStr = JsonHelper.encodeObject2Json(pMap);
		log.info("jsonStr==>"+jsonStr);
		LemsService lemService = new LemsService();
		String reStr = lemService.getInfo(new String(jsonStr.getBytes("GBK"), "ISO-8859-1"),
				LEMSUtil.getLEMService_address(), LEMSUtil.getServiceTimeOut());
		if (reStr.equals("999")) {
			if (index == 0) {
				String location = "控制器：" + eqMap.get("attr_f_sys_name") + "（" + eqMap.get("attr_f_ip_addr") + "）";
				String alarmname = returnOperByIndex(index+"");
				String tipinfo = location + "，执行操作" + alarmname + "时，与下位机通讯失败";
				String  yqbh = (String) eqMap.get("f_yqbh");
				insertAlarmInfoWithSave(index+"", alarmname,location, tipinfo, yqbh, new String("3"),LEMSConstants.LEVELIMPORTENT);
			} else {
				insertAlarmInfo(index+"", eqMap, new String("3"),"",LEMSConstants.LEVELIMPORTENT);
//				String parkid = getParkid(new String("collector"), besddc.getfGuid());
//				insertAlarmInfo(index, "collector", besddc.getfGuid(), new String("3"), "", parkid);
			}
			Dto msgDto = new BaseDto();
			msgDto.put("code", 1);
			return msgDto;
		}
		Dto retDto = parseReturnStr(new String(reStr.getBytes("ISO-8859-1"), "GBK"));
		if(retDto.getAsInteger("code") != null) {
			int str = retDto.getAsInteger("code");
			if (str != 0) {
				if (index == 0) {
					String location = "控制器：" + eqMap.get("attr_f_sys_name") + "（" + eqMap.get("attr_f_ip_addr") + "）";
					String alarmname = returnOperByIndex(index+"");
					String tipinfo = location + "，执行操作" + alarmname + "时，与下位机通讯失败";
					String  yqbh = (String) eqMap.get("f_yqbh");
					insertAlarmInfoWithSave(index+"", alarmname,location, tipinfo, yqbh, new String("2"),LEMSConstants.LEVELIMPORTENT);
				} else {
					if(str == 5)
					{
						insertAlarmInfo(index+"", eqMap, new String("2"),judgecode(str),LEMSConstants.LEVELCOMMON);
					}
					else
					{
						insertAlarmInfo(index+"", eqMap, new String("2"),judgecode(str),LEMSConstants.LEVELIMPORTENT);

					}
//				String parkid = getParkid(new String("collector"), besddc.getfGuid());
//				insertAlarmInfo(index, "collector", besddc.getfGuid(), new String("3"), "", parkid);
				}
			}
		}
		return retDto;
	}

	/**
	 * 设置一个逻辑点的值（通过名字）
	 */
	@Override
    public ISSPReturnObject setCrossPointInfo(com.alibaba.fastjson.JSONObject obj) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		//查询该节点的实体表名
		String tabName = besSbdyMapper.findNodeTable(obj.getString("f_sys_name"));
		//查询逻辑点所属IP路由器的IP地址
		Map<String, Object> ddcinfo = crossEquipmentMapper.queryDDCInfoByPoint(obj.getString("f_sys_name"));
		Map<String, Object> pMap = new HashMap<>();
		Map<String, Object> data = new HashMap<>();
		data.put("Name", obj.getString("f_sys_name"));//点的名字
		data.put("Value", Integer.parseInt(obj.getString("f_init_val")));//值
		pMap.put("data", data);
		pMap.put("IPAddr", ddcinfo.get("F_IP_ADDR"));//ip地址
		pMap.put("Port", ddcinfo.get("F_PORT"));//端口号
		Dto retDto = operCrossController(pMap, 18);//设置一个逻辑点的值  ，index设置为18	
		int reStr = retDto.getAsInteger("code");
		if (reStr == 0) {//设置成功，更新本地数据库
			com.alibaba.fastjson.JSONObject jobj = new com.alibaba.fastjson.JSONObject();
			jobj.put("tabName", tabName);
			jobj.put("f_sys_name", obj.getString("f_sys_name"));
			jobj.put("f_work_mode", obj.getString("f_work_mode"));
			jobj.put("f_init_val", obj.getString("f_init_val"));
			besSbdyMapper.editSbTreeStructInfoBySysName(jobj);			
			returnObject.setStatus("1");
			returnObject.setMsg("操作成功");
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg(judgecode(reStr));
		}
		return returnObject;		
	}	
	/**
	 * 删除逻辑点
	 * 王红杰修改
	 */
	@Override
    public ISSPReturnObject reSetPoint(String f_sys_name,String f_psys_name,String f_sbid, Map<String, Object> pointMap) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> pMap = new HashMap<>();
		Map<String, Object> data = new HashMap<>();
		Map<String, Object> ddcinfo = new HashMap<>();
		String tabName = besSbdyMapper.findNodeTable(f_sys_name);
		int count = besSbdyMapper.delSbTreeBySysName(f_sys_name, tabName);
		int countVpoint;//删除虚点能耗配置节点
		if(count >0) {
			if(tabName.equals("BES_VPOINT")) {//start changed by tianjiangwei2020/04/20 虚点能耗配置信息重置
				String nodeType = besSbdyMapper.findNodeTypeBySysName(f_sys_name);
				if("4".equals(nodeType)){
					countVpoint = besSbdyMapper.delSbTreeBySysName(f_sys_name, "bes_ammeter");
					if(countVpoint>0){
						// 查询逻辑虚点所属DDC控制器的IP地址
						ddcinfo = crossEquipmentMapper.queryDDCInfoByVPoint(f_psys_name);
						pMap.put("IPAddr", ddcinfo.get("F_IP_ADDR"));//ip地址
						pMap.put("Port", ddcinfo.get("F_PORT"));//端口号
						if(f_sbid !=null)
						{
							data.put("ID", Integer.parseInt(f_sbid));//
						}

						pMap.put("data", data);
						Dto retDto = operCrossController(pMap, 19);//删除一个逻辑点，index设置为19
						int reStr = retDto.getAsInteger("code");
						if (reStr == 0) {
							returnObject.setStatus("1");
							returnObject.setMsg("刪除成功");
						} else {
							returnObject.setStatus("0");
							returnObject.setMsg(judgecode(reStr));
						}
						return 	returnObject;
					}
				}else{
					// 查询逻辑虚点所属DDC控制器的IP地址
					ddcinfo = crossEquipmentMapper.queryDDCInfoByVPoint(f_psys_name);
					pMap.put("IPAddr", ddcinfo.get("F_IP_ADDR"));//ip地址
					pMap.put("Port", ddcinfo.get("F_PORT"));//端口号
					if(f_sbid !=null)
					{
						data.put("ID", Integer.parseInt(f_sbid));//
					}
	
					pMap.put("data", data);
					Dto retDto = operCrossController(pMap, 19);//删除一个逻辑点，index设置为19
					int reStr = retDto.getAsInteger("code");
					if (reStr == 0) {
						returnObject.setStatus("1");
						returnObject.setMsg("刪除成功");
					} else {
						returnObject.setStatus("0");
						returnObject.setMsg(judgecode(reStr));
					}
					return 	returnObject;
				}//end
			  }else{
					//查询逻辑点所属DDC控制器的IP地址
					ddcinfo = crossEquipmentMapper.queryDDCInfoByPoint(f_sys_name);
					pMap.put("IPAddr", ddcinfo.get("F_IP_ADDR"));//ip地址
					pMap.put("Port", ddcinfo.get("F_PORT"));//端口号
					if(f_sbid !=null)
					{
						data.put("ID", Integer.parseInt(f_sbid));//
					}
		
					pMap.put("data", data);
					Dto retDto = operCrossController(pMap, 19);//删除一个逻辑点，index设置为19
					int reStr = retDto.getAsInteger("code");
					if (reStr == 0) {
						returnObject.setStatus("1");
						returnObject.setMsg("刪除成功");
					} else {
						returnObject.setStatus("0");
						returnObject.setMsg(judgecode(reStr));
					}
					return 	returnObject;
					}
		}		
		returnObject.setStatus("0");
		returnObject.setMsg("刪除失败！");
		return 	returnObject;
	}
	/**
	 * 重置逻辑点为初始状态
	 */
	private void reSetPointInitial(Map<String, Object> pointMap) {
		
	}
	/**
	 * 下位机数据格式转换
	 * @param reStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Dto parseReturnStr(String reStr) {
		Dto pDto = new BaseDto();
		if (G4Utils.isNotEmpty(reStr)) {
			pDto = getOjectFromJson(reStr);
		} else {
			pDto.put("errmsg", LEMSConstants.Service_Err);
		}
		return pDto;
	}

	/**
	 * Oject2son
	 * @param jsonStr
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "static-access", "unchecked" })
	public Dto getOjectFromJson(String jsonStr) {
		Dto pDto = new BaseDto();
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		for (Iterator iter = jsonObject.keys(); iter.hasNext();) {
			String key = (String) iter.next();
			if ("tm".equals(key) || "CollectControllerParameter".equals(key) || "MeterParameter".equals(key)
					|| "MeterElectricData".equals(key) || "ElectricDataCollectMethod".equals(key)
					|| "MeterHisElectricData".equals(key)) {
				JSONObject jsb = jsonObject.fromObject(jsonObject.get(key));
				Dto tDto = new BaseDto();
				for (Iterator titer = jsb.keys(); titer.hasNext();) {
					String tkey = (String) titer.next();
					tDto.put(tkey, jsb.get(tkey));
				}
				pDto.put(key, tDto);
			} else if ("CollectControllerInfo".equals(key) || "ElectricDataInfo".equals(key)) {
				List<Dto> ls = new ArrayList<Dto>();
				JSONObject jsb = jsonObject.fromObject(jsonObject.get(key));
				for (Iterator titer = jsb.keys(); titer.hasNext();) {
					String tkey = (String) titer.next();
					Dto collDto = new BaseDto();
					JSONObject jsonCollector = jsonObject.fromObject(jsb.get(tkey));
					for (Iterator titColl = jsonCollector.keys(); titColl.hasNext();) {
						String collKey = (String) titColl.next();
						collDto.put(collKey, jsonCollector.get(collKey));
					}
					ls.add(collDto);
				}
				pDto.put(key, ls);
			} else {
				pDto.put(key, jsonObject.get(key));
			}
		}
		return pDto;
	}
	
	public String judgecode(int code) {
		if(code == 1) {
			return "与服务器建立连接失败";
		}else if(code == 2) {
			return "数据发送失败";
		}else if(code == 3) {
			return "数据接收失败";
		}else if(code == 4) {
			return "请求被服务器拒绝";
		}else if(code == 5){
			return "其他错误";
		}
		return null;
	}

	/**
	 * 保存报警信息
	 * @param index
	 * @param location
	 * @param tipinfo
	 * @param alarmname
	 */
	public void insertAlarmInfoWithSave(String index,String alarmname, String location, String tipinfo, String yqbh,String type,String level) {
		//要添加的报警信息
		BesWainingInfo besWaringInfo = new BesWainingInfo();
		besWaringInfo.setFGuid(UUIDUtil.getRandom32BeginTimePK());
		besWaringInfo.setFLoction(location);//报警位置
		besWaringInfo.setFTipInfo(tipinfo);//提示信息
		besWaringInfo.setFAlarmName(alarmname);//报警名称
		besWaringInfo.setFATime( G4Utils.getCurrentTime());//报警时间
		besWaringInfo.setFType(type);//信息类型
		besWaringInfo.setFYqbh(yqbh);
		besWaringInfo.setFLevel(level);
//		besWainingRealInfoMapper.insertWarningInfo(besWaringInfo);
		//判断是否存在相同的未处理报警
		AlarmUtil.insertWarningReal(besWaringInfo);
		log.info(tipinfo);
//		LEMSUtil.pushAlarm(parkid);
	}

	/**
	 * 查询采集器报警信息
	 * @param info
	 * @param alarmtype
	 * @param index
	 * @param msg
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Dto getCollectorWaringInfo(Map<String, Object> info, String alarmtype, String index, String msg,Map<String, Object> emap) {
		Dto pDto = new BaseDto();
		String alarmname = returnOperByIndex(index);
		if (info == null) {
			pDto.put("location", new String("控制器联网搜索"));
			if (alarmtype.equals("3")) {
				pDto.put("tipinfo", new String("执行：") + alarmname + "时，与下位机通讯失败");
			} else {
				pDto.put("tipinfo", new String("执行：") + alarmname + "时，返回错误代码：" + msg);
			}
		} else {
			String location = "控制器：" + info.get("F_SYS_NAME") + "（" + info.get("F_IP_ADDR") + "）";
			pDto.put("location", location);
			if (alarmtype.equals("3")) {
				String tipinfo = location + "，执行操作" + alarmname + "时，与下位机通讯失败";
				pDto.put("tipinfo", tipinfo);
			} else {
				String tipinfo = location + "，执行操作" + alarmname + "时，返回错误代码：" + msg;
				pDto.put("tipinfo", tipinfo);
			}
		}
		String yqbh = "";
		if("5".equals(index))
		{
			yqbh = (String) emap.get("F_YQBH");

		}
		else
		{
			yqbh = (String) info.get("F_YQBH");
		}

		pDto.put("yqbh", yqbh);
		return pDto;
	}

	/**
	 * 查询逻辑点报警信息
	 * @param info
	 * @param alarmtype
	 * @param index
	 * @param msg
	 * @return
	 */
	public Map<String, Object> getPointWaringInfo(Map<String, Object> info, String alarmtype, String index, String msg) {
		String alarmname = returnOperByIndex(index);

		Map<String,Object> pDto = new HashMap<>();
		String location = "";
		String location_meter = "";
		String yqbh = "";
		if("15".equals(index))
		{
			Map<String,Object> collectorInfo = besDdcMapper.queryDDCInfoByIp((String)info.get("DDCIPAddr"));
			location = "控制器：" + collectorInfo.get("F_NICK_NAME").toString()+ ",模块"+info.get("f_allpath").toString().split(">")[1]+ "（" + info.get("DDCIPAddr") + "）";
			location_meter = "逻辑点：" + info.get("attr_f_nick_name") + "（" + info.get("f_sys_name") + "）";
			yqbh = (String) info.get("f_yqbh");

		}
		else
		{
			Map<String,Object> collectorInfo = besDdcMapper.queryDDCInfoByIp((String)info.get("IPAddr"));
			Map<String,String> infoMap = (Map<String, String>) info.get("data");
			List<Map<String, Object>> pointInfos  = besSbdyMapper.queryModuleInfo( String.valueOf(infoMap.get("ID")), "");
			Map<String,Object> pointInfo =null;
			Map<String,Object> parentInfo =null;
			for(int i =0; i<pointInfos.size(); i++)
			{
				if(pointInfos.get(i).get("F_NODE_ATTRIBUTION").equals("1"))
				{

					parentInfo  = besSbdyMapper.querybranchCouplerpNode((String) pointInfos.get(i).get("F_PSYS_NAME"));
                    if(("1").equals(parentInfo.get("F_SBID")))
					{
						if(collectorInfo.get("F_SYS_NAME").equals(parentInfo.get("F_SYS_NAME").toString()))
						{
							pointInfo = pointInfos.get(i);
						}
					}else
					{
						parentInfo  = besSbdyMapper.querybranchCouplerpNode((String) parentInfo.get("F_PSYS_NAME"));
						if(collectorInfo.get("F_SYS_NAME").equals(parentInfo.get("F_SYS_NAME").toString()))
						{
							pointInfo = pointInfos.get(i);
						}
					}
				}

			}

			Map<String,Object> moduleInfo =null;
			if(pointInfo != null && pointInfo.size()>0)
			{
				moduleInfo = besSbdyMapper.querybranchCouplerpNode((String) pointInfo.get("F_SYS_NAME"));
				location = "控制器：" + collectorInfo.get("F_SYS_NAME") +"模块"+moduleInfo.get("F_NICK_NAME")+ "（" + collectorInfo.get("F_IP_ADDR") + "）";
				location_meter = "逻辑点：" + pointInfo.get("F_NICK_NAME") + "（" + pointInfo.get("F_NICK_NAME") + "）";
			}
			yqbh = (String) collectorInfo.get("F_YQBH");
		}

		pDto.put("yqbh",yqbh);
		pDto.put("location", location);
		if (alarmtype.equals("3")) {
			String tipinfo = location_meter + "，执行操作" + alarmname + "时，与下位机通讯失败";
			pDto.put("tipinfo", tipinfo);
		} else {
			String tipinfo = location_meter + "，执行操作" + alarmname + "时，返回错误代码：" + msg;
			pDto.put("tipinfo", tipinfo);
		}
		return pDto;
	}


	/**
	 * 查询模块报警信息
	 * @param info
	 * @param alarmtype
	 * @param index
	 * @param msg
	 * @return
	 */
	public Map<String, Object> getModuleWaringInfo(Map<String, Object> info, String alarmtype, String index, String msg) {
		String alarmname = returnOperByIndex(index);

		Map<String,Object> pDto = new HashMap<>();
		String location = "";
		String location_meter = "";
		String yqbh = "";
		if("10".equals(index))//新增新增模块时
		{
			location = "控制器：" + info.get("f_psys_name") + "（" + info.get("IPAddr") + "）";
			location_meter = "模块：" + info.get("attr_f_nick_name") + "（" + info.get("attr_f_sys_name") + "）";
			yqbh = (String) info.get("f_yqbh");

		}
		else
		{
			Map<String,Object> collectorInfo = besDdcMapper.queryDDCInfoByIp((String)info.get("IPAddr"));
			location = "控制器：" + collectorInfo.get("F_SYS_NAME") + "（" + collectorInfo.get("F_IP_ADDR") + "）";
			Map<String,String> infoMap = (Map<String, String>) info.get("data");
			List<Map<String, Object>> moduleInfo = besSbdyMapper.queryModuleInfo( String.valueOf(infoMap.get("ID")), collectorInfo.get("F_SYS_NAME")+"");
			if(moduleInfo != null && moduleInfo.size()>0)
			{
				location_meter = "模块：" + moduleInfo.get(0).get("F_NICK_NAME") + "（" + moduleInfo.get(0).get("F_SYS_NAME") + "）";
			}
			yqbh = (String) collectorInfo.get("F_YQBH");
		}
		pDto.put("yqbh",yqbh);
		pDto.put("location", location);
		if (alarmtype.equals("3")) {
			String tipinfo = location_meter + "执行操作" + alarmname + "时，与下位机通讯失败";
			pDto.put("tipinfo", tipinfo);
		} else {
			String tipinfo = location_meter + "执行操作" + alarmname + "时，返回错误代码：" + msg;
			pDto.put("tipinfo", tipinfo);
		}
		return pDto;
	}

	/**
	 * 报警信息
	 * @param index
	 * @param type
	 * @param Info
	 * @param alarmtype
	 * @param msg
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> returnAlarmInfo(String index, String type, Map<String, Object> Info, String alarmtype, String msg,Map<String, Object> emap) {
		String alarmname = returnOperByIndex(index);
		Map<String,Object> infoDto = new HashMap<>();
		if (type.equals("module")) {
			infoDto = getModuleWaringInfo(Info, alarmtype, index, msg);
		}
		else if (type.equals("point"))
		{
			infoDto = getPointWaringInfo(Info, alarmtype, index, msg);
		}
		else
		{
			infoDto = getCollectorWaringInfo(Info, alarmtype, index, msg,emap);
		}

		infoDto.put("alarmname", alarmname);
		return infoDto;
	}



	/**
	 * 保存报警信息
	 * @param index
	 * @param eqMap
	 * @param alarmtype
	 * @param msg
	 */
	public void insertAlarmInfo(String index, Map<String, Object> eqMap, String alarmtype, String msg,String level) {
		Map<String,Object> aDto = new HashMap<>();
		if(Integer.valueOf(index)<= 7 || Integer.valueOf(index) == 30)
		{
			//通过ip地址查询采集器id
			try {
				Map<String,Object> collectorInfo = besDdcMapper.queryDDCInfoByIp((String)eqMap.get("IPAddr"));
				aDto = returnAlarmInfo(index, "collector", collectorInfo, alarmtype, msg,eqMap);
			}catch (Exception e){
				e.printStackTrace();
			}

		}
		else if(Integer.valueOf(index) >= 10 && Integer.valueOf(index) <= 14)
		{

			aDto = returnAlarmInfo(index, "module", eqMap, alarmtype, msg,eqMap);

		}
		else if (Integer.valueOf(index) >= 15 && Integer.valueOf(index) <= 21) {
			aDto = returnAlarmInfo(index, "point", eqMap, alarmtype, msg,eqMap);

			//通过电表id获取电表信息
			//Map<String,Object> meterInfo = besJobManagerMapper.queryAmmeterInfo((String)eqMap.get("MeterID"));
			//aDto = returnAlarmInfo(index, "meter", meterInfo, alarmtype, msg);
		}
		//要添加的报警信息
		BesWainingInfo besWaringInfo = new BesWainingInfo();
		besWaringInfo.setFGuid(UUIDUtil.getRandom32BeginTimePK());
		besWaringInfo.setFLoction((String) aDto.get("location"));//报警位置
		besWaringInfo.setFTipInfo((String) aDto.get("tipinfo"));//提示信息
		besWaringInfo.setFAlarmName((String) aDto.get("alarmname"));//报警名称
		besWaringInfo.setFATime( G4Utils.getCurrentTime());//报警时间
		besWaringInfo.setFType(alarmtype);//信息类型
		besWaringInfo.setFYqbh((String) aDto.get("yqbh"));
		besWaringInfo.setFLevel(level);
//		besWainingRealInfoMapper.insertWarningInfo(besWaringInfo);
		//判断是否存在相同的未处理报警
		AlarmUtil.insertWarningReal(besWaringInfo);
		//邮件、短信推送
//		LEMSUtil.pushAlarm(parkid);
//		AlarmInfoService alarmInfoService = (AlarmInfoService) SpringBeanLoader.getSpringBean("alarmInfoService");
//		aDto.put("parkid", parkid);
//		alarmInfoService.alarmWaysSystem(aDto);
	}
	/**
	 * 根据index返回相应操作信息
	 * @param index
	 * @return
	 */
	public String returnOperByIndex(String index) {
		int n = Integer.valueOf(index);
		String msg = "";
		switch (n) {
			case 0:
				msg = "新增一个控制器";
				break;
			case 1:
				msg = "设置控制器的所有参数";
				break;
			case 2:
				msg = "设置控制器的时间";
				break;
			case 3:
				msg = "重启控制器";
				break;
			case 4:
				msg = "重置控制器";
				break;
			case 5:
				msg = "删除一个控制器";
				break;
			case 6:
				msg = "获取控制器的时间";
				break;
			case 7:
				msg = "获取控制器的所有配置参数";
				break;

			case 10:
				msg = "新增一个模块";
				break;
			case 11:
				msg = "设置一个模块的所有参数";
				break;
			case 12:
				msg = "删除一个模块";
				break;
			case 13:
				msg = "获取一个模块的所有配置信息";
				break;
			case 14:
				msg = "获取一个模块的错误代码";
				break;
			case 15:
				msg = "新增一个逻辑点";
				break;
			case 16:
				msg = "设置一个逻辑点的所有参数";
				break;
			case 17:
				msg = "设置逻辑点的值";
				break;
			case 18:
				msg = "设置逻辑点的值，根据点的名字";
				break;
			case 19:
				msg = "删除一个逻辑点";
				break;
			case 20:
				msg = "获取一个逻辑点的所有配置参数";
				break;
			case 21:
				msg = "获取一个逻辑点的报警信息";
				break;

			case 22:
				msg = "增加一个场景";
				break;
			case 23:
				msg = "设置一个场景的所有参数";
				break;
			case 24:
				msg = "删除一个场景";
				break;
			case 25:
				msg = "增加一条计划";
				break;
			case 26:
				msg = "修改一条计划的所有参数";
				break;
			case 27:
				msg = "删除一条计划";
				break;
			case 28:
				msg = "获取一条计划的所有参数";
				break;

			case 30:
				msg = "远程升级";
				break;

			default:
				msg = "与下位机通讯失败";
		}
		return msg;
	}

	/**
	 *
	 * @Description: DDC远程升级
	 *
	 * @auther: wanghongjie
	 * @date: 10:38 2020/8/14
	 * @param: [fSysName]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Override
	public ISSPReturnObject remoteUpgradeDdc(String fSysName) {
		ISSPReturnObject returnObject = new ISSPReturnObject();

		if (!StringUtils.hasText(fSysName))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("参数错误");
			return returnObject;
		}

		//获取该Collector属性信息
		Map<String,Object> DDCMap = besDdcMapper.queryDDCInfo(fSysName);

		if (null == DDCMap || DDCMap.isEmpty())
		{
			returnObject.setStatus("0");
			returnObject.setMsg("DDC控制器不存在");
			return returnObject;
		}

		String channelID = (String) DDCMap.get("F_CHANNEL_ID");

		if (!StringUtils.hasText(channelID))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("无效的 channelID 地址");
			return returnObject;
		}

		Map<String,Object> pNodeInfo = besSbdyMapper.queryNodeInfo(fSysName);
		String f_node_attribution = (String) pNodeInfo.get("F_NODE_ATTRIBUTION");

		if (f_node_attribution.equals("1")) {//楼控

			boolean sendState = SendMsgHandler.upgradeRemoteDDC(channelID);

			if (!sendState)
			{
				returnObject.setStatus("0");
				returnObject.setMsg("下发失败");
				return returnObject;
			}

			MsgSubPubHandler.addSubMsg(DDCCmd.REMOTE_UPGRADE, channelID);

			returnObject.setStatus("1");
			returnObject.setMsg("下发成功");
		} else if (f_node_attribution.equals("2")) {//照明

			boolean sendState = SendMsgHandler.upgradeRemoteLDC(channelID);

			if (!sendState)
			{
				returnObject.setStatus("0");
				returnObject.setMsg("下发失败");
				return returnObject;
			}

			MsgSubPubHandler.addSubMsg(LDCCmd.REMOTE_UPGRADE, channelID);

			returnObject.setStatus("1");
			returnObject.setMsg("下发成功");
		}

		return returnObject;
	}
}
