package com.efounder.JEnterprise.service.basedatamanage.eqmanage.impl;

import com.core.common.ISSPReturnObject;
import com.core.common.json.JsonHelper;
import com.core.common.metatype.Dto;
import com.core.common.metatype.impl.BaseDto;
import com.core.common.util.AlarmUtil;
import com.core.common.util.DataUtil;
import com.core.common.util.G4Utils;
import com.core.common.util.LEMSUtil;
import com.efounder.JEnterprise.collectorJob.LEMSConstants;
import com.efounder.JEnterprise.commhandler.MsgSubPubHandler;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.mapper.basedatamanage.enegrycollectionmanage.BESElectricParamsMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BESAmmeterMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BesCollectorMapper;
import com.efounder.JEnterprise.mapper.collectorJob.BESJobManagerMapper;
import com.efounder.JEnterprise.mapper.dataAnalysises.BesWainingRealInfoMapper;
import com.efounder.JEnterprise.model.dataAnalysises.BesWainingInfo;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.EnerEquipmentService;
import net.sf.json.JSONObject;
import org.ace.business.constant.EDCCmd;
import org.ace.business.dto.edc.AmmeterCollectParamData;
import org.ace.business.dto.edc.AmmeterParam;
import org.ace.business.dto.edc.ControllerDataEDC;
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
/**
 * 能耗系统
 * 与下位机通讯实现类
 * 
 * @author LvSihan 
 * @date 2018年11月7日  
 * @version 1.0
 */
@Service("EnerEquipmentService")
public class EnerEquipmentServiceImpl implements EnerEquipmentService{
	@Autowired
	private BESJobManagerMapper besJobManagerMapper;
	@Autowired
	private BesCollectorMapper besCollectorMapper;
	@Autowired
	private BESAmmeterMapper besAmmeterMapper;
	@Autowired
	private BESElectricParamsMapper besElectricParamsMapper;
	@Autowired
	private BesWainingRealInfoMapper besWainingRealInfoMapper;
	private static final Logger log = LoggerFactory.getLogger(EnerEquipmentServiceImpl.class);


	/**
	 * (设置时间2/重启3/重置4/远程升级10)
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException  
	 */
	@SuppressWarnings("static-access")
	@Override
	public ISSPReturnObject operEnergyCollector(String fSysName,String fIp, String fPort, int fIndex) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> pMap = new HashMap<>();
		Map<String, Object> data = new HashMap<>();
		if(fIp == "") {
			//获取该Collector属性信息
			Map<String,Object> collectorMap = besCollectorMapper.queryCollectorInfo(fSysName);
			fIp = (String) collectorMap.get("F_IP_ADDR");
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
		}
//		else{//3、10
//			pMap.put("data", data);
//		}
		pMap.put("IPAddr", fIp);//ip地址
		pMap.put("Port", fPort);//端口号	
		//与下位机通讯
		Dto retDto = operEnergyController(pMap,String.valueOf(fIndex));
		String reStr = retDto.getAsString("errmsg");
		if (reStr.equals(LEMSConstants.Service_Success)) {
			returnObject.setStatus("1");
			returnObject.setMsg(reStr);
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg(reStr);
		}
		return 	returnObject;
	}
	/**
	 * 获取collector控制器时间
	 */
	@Override
	public ISSPReturnObject getEnergyCollectorTime(String fSysName, String fIp, String fPort, int fIndex) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> pMap = new HashMap<>();
		if(fIp == "") {
			//获取该Collector属性信息
			Map<String,Object> collectorMap = besCollectorMapper.queryCollectorInfo(fSysName);
			fIp = (String) collectorMap.get("F_IP_ADDR");
		}
		pMap.put("IPAddr", fIp);//ip地址
		pMap.put("Port", fPort);//端口号	
		//与下位机通讯
		Dto retDto = operEnergyController(pMap,String.valueOf(fIndex));
		String errmsg = retDto.getAsString("errmsg");
		if (errmsg.equals(LEMSConstants.Service_Success)) {
			Dto timeDto = (Dto) retDto.get("tm");
			String year = String.valueOf(timeDto.getAsInteger("tm_year"));
			String mon = String.valueOf(timeDto.getAsInteger("tm_mon"));
			String day = timeDto.getAsString("tm_mday");
			String hour = timeDto.getAsString("tm_hour");
			String min = timeDto.getAsString("tm_min");
			String sec = timeDto.getAsString("tm_sec");
			String dataTime = year + "-" + mon + "-" + day + "  " + hour + ":" + min + ":" + sec;
			returnObject.setData(dataTime);
			returnObject.setStatus("1");
			returnObject.setMsg(errmsg);
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg(errmsg);
		}
		return 	returnObject;
	}

	// Start delete by xiepufeng at 2020年5月9日 for reason 上位机与下位机通信改变
	/**
	 * 同步采集器信息
	 *//*
	@Override
	public ISSPReturnObject synEnergyCollector(String f_sys_name) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> data = new HashMap<>();
		Map<String, Object> pMap = new HashMap<>();
		//通过f_sys_name查询该collector属性信息
		Map<String,Object> collectorMap = besCollectorMapper.queryCollectorInfo(f_sys_name);
		//通讯所需数据
		data.put("ID", collectorMap.get("F_SBID"));
		data.put("Name", collectorMap.get("F_SYS_NAME"));//系统名称
		data.put("Alias", collectorMap.get("F_NICK_NAME"));//别名
		data.put("Description", collectorMap.get("F_DESCRIPTION"));//描述
		data.put("Location", collectorMap.get("F_AZWZ"));//安装位置
		data.put("Zone", collectorMap.get("F_SSQY"));//所属区域
		data.put("Active", collectorMap.get("F_ENABLED"));
		data.put("SamplePeriod", collectorMap.get("F_COLL_CYCLE"));
		data.put("HisdataSavePeriod", collectorMap.get("F_HIS_DATA_SAVE_CYCLE"));
		pMap.put("IPAddr", collectorMap.get("F_IP_ADDR"));
		pMap.put("Port", collectorMap.get("F_PORT"));
		pMap.put("CollectControllerParameter", data);

		List<Map<String,Object>> ammeters = besJobManagerMapper.queryAmmeterByCollector(f_sys_name);
		for(Map<String,Object> map:ammeters)
		{
			synEnergyAmmeter(map.get("F_SYS_NAME").toString(),map.get("F_PSYS_NAME").toString(),"");
		}

		//与下位机通讯
		Dto retDto = operEnergyController(pMap,"1");
		String reStr = retDto.getAsString("errmsg");
		if (reStr.equals(LEMSConstants.Service_Success)) {
			//通讯成功修改collector在线状态和同步状态
			BesCollector besCollector = new BesCollector();
			besCollector.setfGuid((String)collectorMap.get("F_GUID"));
			besCollector.setfCollectorState(new String("0"));
			besCollector.setfOnlineState(new String("0"));
			besCollectorMapper.updateCollector(besCollector);

			returnObject.setStatus("1");
			returnObject.setMsg("同步成功");
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg("同步失败");
		}
		return 	returnObject;
	}*/
	// End delete by xiepufeng at 2020年5月9日 for reason 上位机与下位机通信改变


	// Start replace by xiepufeng at 2020年5月9日 for reason 上位机与下位机通信改变
	/**
	 * 同步采集器信息
	 */
	@Override
	public ISSPReturnObject synEnergyCollector(String f_sys_name) throws UnsupportedEncodingException, RemoteException, ServiceException {

		ISSPReturnObject returnObject = new ISSPReturnObject();

		// 根据系统名称从数据库中查询出采集器信息
		Map<String,Object> collectorMap = besCollectorMapper.queryCollectorInfo(f_sys_name);

		// 设备id
		Integer sbId = Integer.parseInt((String) collectorMap.get("F_SBID"));
		// 系统名称
		String name = (String) collectorMap.get("F_SYS_NAME");
		// 别名
		String alias = (String) collectorMap.get("F_NICK_NAME");
		// 描述
		String description = (String) collectorMap.get("F_DESCRIPTION");
		// 安装位置
		String location = (String) collectorMap.get("F_AZWZ");
		// 所属区域
		String zone = (String) collectorMap.get("F_SSQY");
		// 使能状态
		Integer active = Integer.parseInt((String) collectorMap.get("F_ENABLED"));
		// 上传采样周期
		Integer upDataSamplePeriod = Integer.parseInt((String) collectorMap.get("F_UPLOAD_CYCLE"));
		// 采集周期
		Integer samplePeriod = Integer.parseInt((String) collectorMap.get("F_COLL_CYCLE"));
		// 历史数据保存周期
		Integer hisDataSavePeriod = Integer.parseInt((String) collectorMap.get("F_HIS_DATA_SAVE_CYCLE"));
		// ip 地址
		String ip = (String) collectorMap.get("F_IP_ADDR");

		// 默认网关
		String gateway = (String) collectorMap.get("F_GATEWAY");

		// 子网掩码
		String mask = (String) collectorMap.get("F_MASK");

		// 主机ip
		String ipMaster = (String) collectorMap.get("F_IP_MASTER");

		// 主机端口
		String portMaster = (String) collectorMap.get("F_PORT_MASTER");

		// 通道id
		String channelId = (String) collectorMap.get("F_CHANNEL_ID");

		returnObject.setData(ip);

		if (!StringUtils.hasText(gateway)
				|| !StringUtils.hasText(mask)
				|| !StringUtils.hasText(ipMaster)
				|| !StringUtils.hasText(portMaster))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("请完善表单信息");
			return 	returnObject;
		}


		ControllerDataEDC controllerData = new ControllerDataEDC();

		controllerData.setId(sbId);
		controllerData.setName(name);
		controllerData.setAlias(alias);
		controllerData.setDescription(description);
		controllerData.setLocation(location);
		controllerData.setZone(zone);
		controllerData.setActive(active);
		controllerData.setSamplePeriod(samplePeriod);
		controllerData.setUpDataSamplePeriod(upDataSamplePeriod);
		controllerData.setHisDataSavePeriod(hisDataSavePeriod);
		controllerData.setGateWay(gateway);
		controllerData.setMask(mask);
		controllerData.setServerIP(ipMaster);
		controllerData.setServerPort(Integer.parseInt(portMaster));
		controllerData.setIp(ip);

		// 同步能耗控制器参数到下位机
		boolean sendState = SendMsgHandler.setControllerEDC(channelId, controllerData);

		if (!sendState)
		{
			returnObject.setStatus("0");
			returnObject.setMsg("下发失败");
			return 	returnObject;
		}
		// 添加订阅消息
		MsgSubPubHandler.addSubMsg(EDCCmd.CONTROLLER_PARAM_SET, ip);
		returnObject.setStatus("1");
		returnObject.setMsg("下发成功");
		return 	returnObject;
	}
	// End replace by xiepufeng at 2020年5月9日 for reason 上位机与下位机通信改变


	/**
	 * 获取采集器所有参数
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ISSPReturnObject compareEnergyCollector(String fSysName, String f_guid, String fIp, String fPort) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> pMap = new HashMap<>();
		if(fIp == "") {
			//获取该Collector属性信息
			Map<String,Object> collectorMap = besCollectorMapper.queryCollectorInfo(fSysName);
			fIp = (String) collectorMap.get("F_IP_ADDR");
		}
		pMap.put("IPAddr", fIp);// ip地址
		pMap.put("Port", fPort);// 端口号
		//与下位机通讯，设置index为6
		Dto retDto = operEnergyController(pMap, "6");
		String errmsg = retDto.getAsString("errmsg");
		if (errmsg.equals(LEMSConstants.Service_Success)) {
			Map<String, Object> xDto = (Map<String, Object>) retDto.get("CollectControllerParameter");
			xDto.put("Active", xDto.get("Active").equals(1) ? "是" : "否");
			xDto.put("SamplePeriod", xDto.get("SamplePeriod") + "分钟");
			xDto.put("HisdataSavePeriod", xDto.get("HisdataSavePeriod") + "小时");
			returnObject.setData(xDto);
			returnObject.setStatus("1");
			returnObject.setMsg(errmsg);
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg(errmsg);
		}
		return returnObject;
	}
	/**
	 * 同步电表
	 */
	/*@Override
	public ISSPReturnObject synEnergyAmmeter(String f_sys_name, String f_psys_name, String f_node_attribution) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> pMap = new HashMap<>();
		Map<String, Object> data = new HashMap<>();
		//查询电表所在的采集器信息
		Map<String, Object> collectorInfo = besJobManagerMapper.querycollectorInfoByAmmeter(f_psys_name);
		//查询该电表的详细信息
		Map<String, Object> nodeInfo = besJobManagerMapper.queryAmmeterInfo(f_sys_name);
		String collector_method_id = (String) nodeInfo.get("F_CJFABH");
		List<Map<String,Object>> paramsLs = besJobManagerMapper.queryAmmeter_params_schemeid(collector_method_id);
		Map<String,Object> paramsDto = assParams(paramsLs, collector_method_id);
		data.put("Active", nodeInfo.get("F_ENABLED"));
		data.put("Description", nodeInfo.get("F_DESCRIPTION"));
		data.put("ComRate", nodeInfo.get("F_COMM_RATE"));//通信波特率编号
		data.put("Alias", nodeInfo.get("F_NICK_NAME"));//别名
		data.put("MeterID", nodeInfo.get("F_SYS_NAME"));
		data.put("PhyAddr", nodeInfo.get("F_WLDZ"));
		data.put("ComPort", nodeInfo.get("F_COMMUNICATION_PORT"));
		data.put("Location", nodeInfo.get("F_AZWZ"));
		data.put("MeterType", nodeInfo.get("F_TYPE"));//型号
		data.put("ComAgreementType", nodeInfo.get("F_PROTOCOL_TYPE"));//通信协议类型
		pMap.put("MeterParameter", data);
		pMap.put("MeterID", nodeInfo.get("F_SYS_NAME"));
//		pMap.put("f_sys_name",nodeInfo.get("F_SYS_NAME"));//系统名称
		pMap.put("IPAddr", collectorInfo.get("F_IP_ADDR"));
		pMap.put("Port", collectorInfo.get("F_PORT"));
		pMap.putAll(paramsDto);
		Dto retDto = operEnergyController(pMap, "12");//同步电表信息，index设置为12		
		String errmsg = retDto.getAsString("errmsg");
		if (errmsg.equals(LEMSConstants.Service_Success)) {
			BESAmmeter besAmmeter = new BESAmmeter();
			besAmmeter.setfOnlineState("0");
			besAmmeter.setfAmmeterState("0");
			besAmmeter.setfSysName(f_sys_name);
			besAmmeterMapper.updateByPrimaryKeySelective(besAmmeter);
			returnObject.setStatus("1");
			returnObject.setMsg(errmsg);
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg(errmsg);
		}
		return returnObject;
	}*/

	/**
	 * 同步电表参数
	 * @param f_sys_name
	 * @return
	 */
	@Override
	public ISSPReturnObject synAmmeter(String f_sys_name)
	{
		ISSPReturnObject result = new ISSPReturnObject();

		if (!StringUtils.hasText(f_sys_name))
		{
			result.setMsg("参数错误");
			result.setStatus("0");
			return result;
		}

		Map<String, Object> ammeter = besJobManagerMapper.queryAmmeterInfo(f_sys_name);

		//查询电表所在的采集器信息
		Map<String, Object> collectorInfo = besJobManagerMapper.queryCollectorByAmmeterSysName(f_sys_name);

		if (null == collectorInfo || collectorInfo.isEmpty())
		{
			result.setStatus("0");
			result.setMsg("数据异常");
			return result;
		}

		String channelId = (String) collectorInfo.get("F_CHANNEL_ID");

		Integer meterID = Integer.parseInt((String) ammeter.get("F_SBID"));

		String alias = (String) ammeter.get("F_NICK_NAME"); // 电表别名
		Integer active = Integer.parseInt((String) ammeter.get("F_ENABLED")); // 使能状态
		Integer meterType = Integer.parseInt((String)ammeter.get("F_BLXBH")); // 电表类型编号
		Integer comPort = Integer.parseInt((String)ammeter.get("F_COMMUNICATION_PORT")); // 通信端口
		Integer comRate = Integer.parseInt((String)ammeter.get("F_COMM_RATE")); // 通信波特率
		Integer comAgreementType = Integer.parseInt((String)ammeter.get("F_PROTOCOL_TYPE")); //通信协议
		String phyAddr = (String) ammeter.get("F_WLDZ"); // 物理地址
		String location = (String) ammeter.get("F_AZWZ"); //安装位置
		String description = (String) ammeter.get("F_DESCRIPTION"); //描述
		Integer comDataBit = Integer.parseInt((String)ammeter.get("F_COM_DATA_BIT")); // 数据位
		Integer comParityBit = Integer.parseInt((String)ammeter.get("F_COM_PARITY_BIT")); // 校验位
		Integer comStopBit = Integer.parseInt((String)ammeter.get("F_COM_STOP_BIT")); // 停止位
		Integer functionCode = Integer.parseInt((String)ammeter.get("F_FUNCTION_CODE")); // 功能码


		AmmeterCollectParamData ammeterCollectParam = new AmmeterCollectParamData();

		ammeterCollectParam.setMeterID(meterID); // 电表序列号

		AmmeterParam ammeterParam = new AmmeterParam();

		ammeterParam.setPhyAddr(phyAddr); // 物理地址
		ammeterParam.setComAgreementType(comAgreementType); // 通信规约类型
		ammeterParam.setActive(active); // 电表的使能状态
		ammeterParam.setDescription(description); // 表的描述信息
		ammeterParam.setComRate(comRate); // 通信速率
		ammeterParam.setComPort(comPort); // 通信端口号
		ammeterParam.setComDataBit(comDataBit); // 通信数据位
		ammeterParam.setComParityBit(comParityBit); // 通信校验位
		ammeterParam.setComStopBit(comStopBit); // 通信停止位
		ammeterParam.setMeterType(meterType); // 表类型
		ammeterParam.setAlias(alias); // 表的别名
		ammeterParam.setMeterID(meterID); // 表序列号
		ammeterParam.setLocation(location); // 电表的安装位置
		ammeterParam.setFunctionCode(functionCode); // 功能码

		ammeterCollectParam.setMeterParameter(ammeterParam);


	/*	// 同步数据到下位机
		if (!SendMsgHandler.setAmmeterEDC(channelId, ammeterCollectParam))
		{
			result.setStatus("1");
			result.setMsg("保存成功，下发失败");

			return result;
		}

		// 添加订阅消息
		MsgSubPubHandler.addSubMsg(EDCCmd.AMMETER_SET, channelId);*/

		// 删除电表
		if (!SendMsgHandler.deleteAmmeterEDC(channelId, meterID))
		{
			result.setStatus("0");
			result.setMsg("下发失败");
			return result;
		}

		// 添加订阅消息
		MsgSubPubHandler.addSubMsg(EDCCmd.AMMETER_DELETE, channelId);

		// 同步数据到下位机
		if (!SendMsgHandler.addAmmeterEDC(channelId, ammeterCollectParam))
		{

			result.setStatus("0");
			result.setMsg("下发失败");
			return result;
		}

		// 添加订阅消息
		MsgSubPubHandler.addSubMsg(EDCCmd.AMMETER_ADD, channelId);

		result.setStatus("1");
		result.setMsg("下发成功");

		return result;
	}


	/**
	 * 给下位机下发指令，获取电表参数
	 * @param fSysName
	 * @return
	 */
	@Override
	public ISSPReturnObject getAmmeterParam(String fSysName)
	{
		ISSPReturnObject returnObject = new ISSPReturnObject();

		if (!StringUtils.hasText(fSysName))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("参数错误");
			return returnObject;
		}

		Map<String, Object> ammeterIn = besJobManagerMapper.queryAmmeterInfo(fSysName);

		if (null == ammeterIn || ammeterIn.isEmpty())
		{
			returnObject.setStatus("0");
			returnObject.setMsg("电表不存在");
			return returnObject;
		}

		Integer meterID = Integer.parseInt((String)ammeterIn.get("F_SBID"));

		//获取该Collector属性信息
		Map<String,Object> collectorMap = besJobManagerMapper.queryCollectorByAmmeterSysName(fSysName);

		if (null == collectorMap || collectorMap.isEmpty())
		{
			returnObject.setStatus("0");
			returnObject.setMsg("能耗采集器不存在");
			return returnObject;
		}

		String channelID = (String) collectorMap.get("F_CHANNEL_ID");

		if (!StringUtils.hasText(channelID))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("无效的 channelID 地址");
			return returnObject;
		}


		boolean sendState = SendMsgHandler.getAmmeterEDC(channelID, meterID);

		if (!sendState)
		{
			returnObject.setStatus("0");
			returnObject.setMsg("下发失败");
			return returnObject;
		}

		MsgSubPubHandler.addSubMsg(EDCCmd.AMMETER_GET, channelID);

		returnObject.setStatus("1");
		returnObject.setMsg("下发成功");
		return returnObject;
	}

	/**
	 * 给下位机下发指令，获取电表实时数据
	 * @param f_sys_name
	 * @return
	 */
	@Override
	public ISSPReturnObject getAmmeterRealTimeData(String f_sys_name)
	{
		ISSPReturnObject returnObject = new ISSPReturnObject();

		if (!StringUtils.hasText(f_sys_name))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("参数错误");
			return returnObject;
		}

		Map<String, Object> ammeterIn = besJobManagerMapper.queryAmmeterInfo(f_sys_name);

		if (null == ammeterIn || ammeterIn.isEmpty())
		{
			returnObject.setStatus("0");
			returnObject.setMsg("电表不存在");
			return returnObject;
		}

		Integer meterID = Integer.parseInt((String)ammeterIn.get("F_SBID"));

		//获取该Collector属性信息
		Map<String,Object> collectorMap = besJobManagerMapper.queryCollectorByAmmeterSysName(f_sys_name);

		if (null == collectorMap || collectorMap.isEmpty())
		{
			returnObject.setStatus("0");
			returnObject.setMsg("能耗采集器不存在");
			return returnObject;
		}

		String channelID = (String) collectorMap.get("F_CHANNEL_ID");

		if (!StringUtils.hasText(channelID))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("无效的 channelID 地址");
			return returnObject;
		}


		boolean sendState = SendMsgHandler.getAmmeterRealtimeDataEDC(channelID, meterID);

		if (!sendState)
		{
			returnObject.setStatus("0");
			returnObject.setMsg("下发失败");
			return returnObject;
		}

		MsgSubPubHandler.addSubMsg(EDCCmd.AMMETER_REALTIME_DATA_GET, channelID);

		returnObject.setStatus("1");
		returnObject.setMsg("下发成功");
		return returnObject;
	}

	/**
	 * 给下位机下发指令，获取电表历史数据
	 * @param fSysName
	 * @return
	 */
	@Override
	public ISSPReturnObject getAmmeterHistoryData(String fSysName,Integer selectDay)
	{
		ISSPReturnObject returnObject = new ISSPReturnObject();

		if (!StringUtils.hasText(fSysName) || null == selectDay)
		{
			returnObject.setStatus("0");
			returnObject.setMsg("参数错误");
			return returnObject;
		}

		Map<String, Object> ammeterIn = besJobManagerMapper.queryAmmeterInfo(fSysName);

		if (null == ammeterIn || ammeterIn.isEmpty())
		{
			returnObject.setStatus("0");
			returnObject.setMsg("电表不存在");
			return returnObject;
		}

		Integer meterID = Integer.parseInt((String)ammeterIn.get("F_SBID"));

		//获取该Collector属性信息
		Map<String,Object> collectorMap = besJobManagerMapper.queryCollectorByAmmeterSysName(fSysName);

		if (null == collectorMap || collectorMap.isEmpty())
		{
			returnObject.setStatus("0");
			returnObject.setMsg("能耗采集器不存在");
			return returnObject;
		}

		String channelID = (String) collectorMap.get("F_CHANNEL_ID");

		if (!StringUtils.hasText(channelID))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("无效的 channelID 地址");
			return returnObject;
		}


		boolean sendState = SendMsgHandler.getAmmeterHistoryDataEDC(channelID, meterID, selectDay);

		if (!sendState)
		{
			returnObject.setStatus("0");
			returnObject.setMsg("下发失败");
			return returnObject;
		}

		MsgSubPubHandler.addSubMsg(EDCCmd.AMMETER_HISTORY_DATA_GET, channelID);

		returnObject.setStatus("1");
		returnObject.setMsg("下发成功");
		return returnObject;
	}


	/**
	 * 获取电表所有参数
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ISSPReturnObject compareEnergyAmmeter(String f_sys_name, String f_psys_name,String f_node_attribution) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> pMap = new HashMap<>();
		//通过电表sys_name获取对应的采集器信息
		Map<String,Object> collectorInfo = besJobManagerMapper.queryCollectorInfoByAmmeterId(f_sys_name);
		//通过电表sys_name获取该条电表信息
		Map<String,Object> mDto = besJobManagerMapper.queryAmmeterInfo(f_sys_name);		
		pMap.put("IPAddr", collectorInfo.get("F_IP_ADDR"));//ip地址
		pMap.put("Port", collectorInfo.get("F_PORT"));//端口号
		pMap.put("MeterID",mDto.get("F_SYS_NAME"));//设备id
//		pMap.put("f_sys_name",f_sys_name);//系统名称
		Dto retDto = operEnergyController(pMap, "14");//同步电表信息，index设置为14		
		String errmsg = retDto.getAsString("errmsg");
		if (errmsg.equals(LEMSConstants.Service_Success)) {
			Map<String,Object> collectMeth = (Map<String, Object>) retDto.get("ElectricDataCollectMethod");
			Map<String,Object> meterDto = (Map<String, Object>)  retDto.get("MeterParameter");
			String MeterType = besJobManagerMapper.queryAmmeterModelById((int)meterDto.get("MeterType"));			
			String ComRate = besJobManagerMapper.queryComRateById((int)meterDto.get("ComRate"));
			String ComAgreementType = besJobManagerMapper.queryComAgreementTypeById((int)meterDto.get("ComAgreementType"));
			String acquisition_scheme_name_xwj = besJobManagerMapper
					.queryCollectorMetnodById((int)collectMeth.get("CollectMethodID"));
			meterDto.put("MeterType", MeterType);
			meterDto.put("ComRate", ComRate);
			meterDto.put("ComAgreementType", ComAgreementType);
			meterDto.put("acquisition_scheme_name_xwj", acquisition_scheme_name_xwj);
			meterDto.put("Active", meterDto.get("Active").equals("1") ? "是" : "否");
			returnObject.setData(meterDto);
			returnObject.setStatus("1");
			returnObject.setMsg(errmsg);
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg(errmsg);
		}
		return returnObject;
	}
	/**
	 * 通过电表id查询对应的采集参数信息
	 */
	@Override
	public ISSPReturnObject queryElectricParamsByAmmeter(String f_sys_name) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<Map<String,Object>> electricParams = besJobManagerMapper.queryElectricParamsByAmmeter(f_sys_name);
		returnObject.setData(electricParams);
		return returnObject;
	}

	/* Start add by xiepufeng at 2020年5月18日
	remark 能耗采集器远程升级指令下发
	reason 1、原来远程升级和设置时间重启功能耦合在一起了，为了减少耦合每个功能都独立出来
	       2、上位机下位机通信升级
	*/
	/**
	 * 能耗采集器远程升级
	 * @param fSysName
	 * @return
	 */
	@Override
	public ISSPReturnObject remoteUpgradeEdc(String fSysName)
	{

		ISSPReturnObject returnObject = new ISSPReturnObject();

		if (!StringUtils.hasText(fSysName))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("参数错误");
			return returnObject;
		}

		//获取该Collector属性信息
		Map<String,Object> collectorMap = besCollectorMapper.queryCollectorInfo(fSysName);

		if (null == collectorMap || collectorMap.isEmpty())
		{
			returnObject.setStatus("0");
			returnObject.setMsg("能耗采集器不存在");
			return returnObject;
		}

		String channelID = (String) collectorMap.get("F_CHANNEL_ID");

		if (!StringUtils.hasText(channelID))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("无效的 channelID 地址");
			return returnObject;
		}

		boolean sendState = SendMsgHandler.remoteUpgradeEDC(channelID);

		if (!sendState)
		{
			returnObject.setStatus("0");
			returnObject.setMsg("下发失败");
			return returnObject;
		}

		MsgSubPubHandler.addSubMsg(EDCCmd.REMOTE_UPGRADE, channelID);

		returnObject.setStatus("1");
		returnObject.setMsg("下发成功");
		return returnObject;
	}

	/**
	 * 设置能耗采集器时间
	 * @param fSysName
	 * @return
	 */
	@Override
	public ISSPReturnObject setTimeEdc(String fSysName)
	{

		ISSPReturnObject returnObject = new ISSPReturnObject();

		if (!StringUtils.hasText(fSysName))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("参数错误");
			return returnObject;
		}

		//获取该Collector属性信息
		Map<String,Object> collectorMap = besCollectorMapper.queryCollectorInfo(fSysName);

		if (null == collectorMap || collectorMap.isEmpty())
		{
			returnObject.setStatus("0");
			returnObject.setMsg("能耗采集器不存在");
			return returnObject;
		}

		String channelID = (String) collectorMap.get("F_CHANNEL_ID");

		if (!StringUtils.hasText(channelID))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("无效的 channelID 地址");
			return returnObject;
		}


		boolean sendState = SendMsgHandler.setControllerTimeEDC(channelID, DataUtil.getTimeDataObject());

		if (!sendState)
		{
			returnObject.setStatus("0");
			returnObject.setMsg("下发失败");
			return returnObject;
		}

		MsgSubPubHandler.addSubMsg(EDCCmd.CONTROLLER_TIME_SET, channelID);

		returnObject.setStatus("1");
		returnObject.setMsg("下发成功");
		return returnObject;

	}

	/**
	 * 重启能耗采集器
	 * @param fSysName
	 * @return
	 */
	@Override
	public ISSPReturnObject restartEdc(String fSysName)
	{
		ISSPReturnObject returnObject = new ISSPReturnObject();

		if (!StringUtils.hasText(fSysName))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("参数错误");
			return returnObject;
		}

		//获取该Collector属性信息
		Map<String,Object> collectorMap = besCollectorMapper.queryCollectorInfo(fSysName);

		if (null == collectorMap || collectorMap.isEmpty())
		{
			returnObject.setStatus("0");
			returnObject.setMsg("能耗采集器不存在");
			return returnObject;
		}

		String channelID = (String) collectorMap.get("F_CHANNEL_ID");

		if (!StringUtils.hasText(channelID))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("无效的 channelID 地址");
			return returnObject;
		}

		boolean sendState = SendMsgHandler.restartControllerEDC(channelID);

		if (!sendState)
		{
			returnObject.setStatus("0");
			returnObject.setMsg("下发失败");
			return returnObject;
		}

		MsgSubPubHandler.addSubMsg(EDCCmd.CONTROLLER_RESTART, channelID);

		returnObject.setStatus("1");
		returnObject.setMsg("下发成功");
		return returnObject;
	}

	/*End add by xiepufeng at 2020年5月18日*/

	/*Start add by xiepufeng at 2020年5月19日 remark 获取能耗采集器时间*/

	/**
	 * 获取能耗采集器时间
	 * @param fSysName
	 * @return
	 */
	@Override
	public ISSPReturnObject getTimeEdc(String fSysName)
	{
		ISSPReturnObject returnObject = new ISSPReturnObject();

		if (!StringUtils.hasText(fSysName))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("参数错误");
			return returnObject;
		}

		//获取该Collector属性信息
		Map<String,Object> collectorMap = besCollectorMapper.queryCollectorInfo(fSysName);

		if (null == collectorMap || collectorMap.isEmpty())
		{
			returnObject.setStatus("0");
			returnObject.setMsg("能耗采集器不存在");
			return returnObject;
		}

		String channelID = (String) collectorMap.get("F_CHANNEL_ID");

		if (!StringUtils.hasText(channelID))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("无效的 channelID 地址");
			return returnObject;
		}

		boolean sendState = SendMsgHandler.getControllerTimeEDC(channelID);

		if (!sendState)
		{
			returnObject.setStatus("0");
			returnObject.setMsg("下发失败");
			return returnObject;
		}

		MsgSubPubHandler.addSubMsg(EDCCmd.CONTROLLER_TIME_GET, channelID);

		returnObject.setStatus("1");
		returnObject.setMsg("下发成功");
		return returnObject;
	}

	/*End add by xiepufeng at 2020年5月19日 remark 获取能耗采集器时间*/

	/*Start add by xiepufeng at 2020年5月19日*/

	/**
	 * 重置能耗采集器
	 * @param fSysName
	 * @return
	 */
	@Override
	public ISSPReturnObject resetEdc(String fSysName)
	{
		ISSPReturnObject returnObject = new ISSPReturnObject();

		if (!StringUtils.hasText(fSysName))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("参数错误");
			return returnObject;
		}

		//获取该Collector属性信息
		Map<String,Object> collectorMap = besCollectorMapper.queryCollectorInfo(fSysName);

		if (null == collectorMap || collectorMap.isEmpty())
		{
			returnObject.setStatus("0");
			returnObject.setMsg("能耗采集器不存在");
			return returnObject;
		}

		String channelID = (String) collectorMap.get("F_CHANNEL_ID");

		if (!StringUtils.hasText(channelID))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("无效的 channelID 地址");
			return returnObject;
		}

		boolean sendState = SendMsgHandler.resetControllerEDC(channelID);

		if (!sendState)
		{
			returnObject.setStatus("0");
			returnObject.setMsg("下发失败");
			return returnObject;
		}

		MsgSubPubHandler.addSubMsg(EDCCmd.CONTROLLER_RESET, channelID);

		returnObject.setStatus("1");
		returnObject.setMsg("下发成功");
		return returnObject;
	}

	/*End add by xiepufeng at 2020年5月19日*/

	/*Start add by xiepufeng at 2020年5月21日*/
	/**
	 * 给下位机下发指令，获取控制器参数
	 * @param fSysName
	 * @return
	 */
	@Override
	public ISSPReturnObject getControllerParam(String fSysName)
	{
		ISSPReturnObject returnObject = new ISSPReturnObject();

		if (!StringUtils.hasText(fSysName))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("参数错误");
			return returnObject;
		}

		//获取该Collector属性信息
		Map<String,Object> collectorMap = besCollectorMapper.queryCollectorInfo(fSysName);

		if (null == collectorMap || collectorMap.isEmpty())
		{
			returnObject.setStatus("0");
			returnObject.setMsg("能耗采集器不存在");
			return returnObject;
		}

		String channelID = (String) collectorMap.get("F_CHANNEL_ID");

		if (!StringUtils.hasText(channelID))
		{
			returnObject.setStatus("0");
			returnObject.setMsg("无效的 channelID 地址");
			return returnObject;
		}

		boolean sendState = SendMsgHandler.getControllerEDC(channelID);

		if (!sendState)
		{
			returnObject.setStatus("0");
			returnObject.setMsg("下发失败");
			return returnObject;
		}

		MsgSubPubHandler.addSubMsg(EDCCmd.CONTROLLER_PARAM_GET, channelID);

		returnObject.setStatus("1");
		returnObject.setMsg("下发成功");
		return returnObject;
	}

	/*End add by xiepufeng at 2020年5月21日*/

	/**
	 * 电能参数对比
	 * @throws ServiceException 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ISSPReturnObject compareEnectric(String f_sys_name) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> pMap = new HashMap<>();
		//通过电表sys_name获取对应的采集器信息
		Map<String,Object> collectorInfo = besJobManagerMapper.queryCollectorInfoByAmmeterId(f_sys_name);
		//通过电表sys_name获取该条电表信息
		Map<String,Object> mDto = besJobManagerMapper.queryAmmeterInfo(f_sys_name);	
		pMap.put("IPAddr", collectorInfo.get("F_IP_ADDR"));//ip地址
		pMap.put("Port", collectorInfo.get("F_PORT"));//端口号
		pMap.put("MeterID",mDto.get("F_SYS_NAME"));//设备id
//		pMap.put("f_sys_name",f_sys_name);//系统名称
		Dto retDto = operEnergyController(pMap, "14");
		String errmsg = retDto.getAsString("errmsg");
		if (errmsg.equals(LEMSConstants.Service_Success)) {
			List<Map<String,Object>> electricLs = retDto.getAsList("ElectricDataInfo");
			for(Map<String,Object> el : electricLs) {
				int dataEncodeType = (int) el.get("DataEncodeType");
				if (0 == dataEncodeType) {
					el.put("DataEncodeType", "bcd编码");
				} else if (1 == dataEncodeType) {
					el.put("DataEncodeType", "dec编码");
				} else if (2 == dataEncodeType) {
					el.put("DataEncodeType", "其他");
				}
			}
			returnObject.setData(electricLs);
		}		
		return returnObject;
	}
	/**
	 * 获取电能参数数据（历史）
	 * @throws ServiceException 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ISSPReturnObject queryMeterHisData(String f_sys_name,String selectday) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> pMap = new HashMap<>();
		String index="";
		//通过电表sys_name获取对应的采集器信息
		Map<String,Object> collectorInfo = besJobManagerMapper.queryCollectorInfoByAmmeterId(f_sys_name);
		//通过电表sys_name获取该条电表信息
		Map<String,Object> mDto = besJobManagerMapper.queryAmmeterInfo(f_sys_name);
		Dto retDto = new BaseDto();
		pMap.put("IPAddr", collectorInfo.get("F_IP_ADDR"));//ip地址
		pMap.put("Port", collectorInfo.get("F_PORT"));//端口号
		pMap.put("MeterID",mDto.get("F_SYS_NAME"));//设备id
//		pMap.put("f_sys_name",f_sys_name);//系统名称
		if (G4Utils.isEmpty(selectday)) {
			index = "15";
			retDto = operEnergyController(pMap, index);
		} else {
			index = "16";
			pMap.put("Selectday", selectday);
			retDto = operEnergyController(pMap, index);
		}
		
		String errmsg = retDto.getAsString("errmsg");
		if (errmsg.equals(LEMSConstants.Service_Success)) {
			if (index.equals("15")) {
				retDto = (Dto) retDto.get("MeterElectricData");
				if (!G4Utils.isEmpty(retDto)) {
					if(retDto.getAsString("MeterID").equals(mDto.get("F_SYS_NAME"))) {
						String nyr = retDto.getAsInteger("DateYear") + "-" + retDto.getAsInteger("DateMonth") + "-" + retDto.getAsInteger("DateDay");
						String sfm = retDto.getAsInteger("TimeHour") + ":" + retDto.getAsInteger("TimeMinute") + ":" + retDto.getAsInteger("TimeSecond");
						String histime = nyr + "  " + sfm;
						String metername = (String) mDto.get("F_NICK_NAME");
						
						retDto.put("metername", metername);
						retDto.put("histime", histime);
						retDto.put("errmsg", errmsg);
						returnObject.setData(retDto);
						returnObject.setStatus("1");
						returnObject.setMsg(errmsg);
					}else {
						returnObject.setStatus("0");
						returnObject.setMsg("下位机返回数据有误");
					}					
				} else {
					returnObject.setStatus("0");
					returnObject.setMsg(errmsg);
				}
			} else {
				Dto hisDto = (Dto) retDto.get("MeterHisElectricData");
				if (!G4Utils.isEmpty(hisDto)) {
					String json = retDto.getAsString("MeterElectricHisData");
					hisDto.put("griddata", json);
					hisDto.put("errmsg", errmsg);
					String metername = (String) mDto.get("F_NICK_NAME");
					hisDto.put("metername", metername);
					returnObject.setData(hisDto);
					returnObject.setStatus("1");
				} else {
					returnObject.setMsg(errmsg);
					returnObject.setStatus("0");
				}				
			}
		} else {
			returnObject.setMsg(errmsg);
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	/**
	 * 解析原始数据
	 * @param ElectricData
	 * @param gridata
	 * @param meteruuid
	 * @param histime
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ISSPReturnObject queryMeterHisDataGrid(String ElectricData, String gridata, String meteruuid, String histime,
			String type) throws Exception {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<Map<String, Object>> ls = besJobManagerMapper.queryElectricParamsByAmmeter(meteruuid);
		if (G4Utils.isNotEmpty(ls)) {
			if (type.equals("selectday")) {
				List<Dto> eList = new ArrayList<Dto>();
				if (G4Utils.isNotEmpty(gridata)) {
					List<Dto> dataList = (List<Dto>) JsonHelper.parseJson2List(gridata);
					for (Dto dataDto : dataList) {
						String nyr = String.valueOf(dataDto.getAsInteger("DateYear")) + "-"
								+ String.valueOf(dataDto.getAsInteger("DateMonth")) + "-"
								+ String.valueOf(dataDto.getAsInteger("DateDay"));
						String aTime = ((rangeInDefined(dataDto.getAsInteger("TimeHour"), 0, 9))
								? "0" + String.valueOf(dataDto.getAsInteger("TimeHour"))
								: String.valueOf(dataDto.getAsInteger("TimeHour")));
						String sfm = aTime + ":" + String.valueOf(dataDto.getAsInteger("TimeMinute")) + ":"
								+ String.valueOf(dataDto.getAsInteger("TimeSecond"));
						String time = nyr + "  " + sfm;
						String electricData[] = dataDto.getAsString("ElectricData").split(",");
						for (int i = 0; i < electricData.length - 1; i++) {
							Dto pDto = new BaseDto();
							pDto.putAll(ls.get(i));
							String enectricId = pDto.getAsString("F_DNBH");
							String unit = pDto.getAsString("F_UNIT");
							String percentage = pDto.getAsString("F_PERCENTAGE");//原始数据计算变比，后期数据分析不在处理---Add by LvSihan 2019.1.14
							String anlData = analysisRawData(enectricId, electricData[i + 1],percentage,unit);
							pDto.put("rawdata", anlData);
							pDto.put("time", time);
							eList.add(pDto);
						}
					}
					returnObject.setData(eList);
					returnObject.setStatus("1");
				}
			} else {
				List<Map<String, Object>> retls = new ArrayList<Map<String, Object>>();
				String electricData[] = ElectricData.split(",");
				String time = histime;
				if (electricData.length == 1) {
					JsonHelper.encodeList2PageJson(null, null, null);
				} else {
					for (int i = 0; i < electricData.length - 1; i++) {
						Map<String, Object> retdata = ls.get(i);
						String enectricId = String.valueOf(retdata.get("F_DNBH"));
						String unit = String.valueOf(retdata.get("F_UNIT"));
						String percentage = String.valueOf(retdata.get("F_PERCENTAGE"));;//原始数据计算变比，后期数据分析不在处理---Add by LvSihan 2019.1.14
						String anlData = analysisRawData(enectricId , electricData[i + 1],percentage,unit);
						retdata.put("rawdata", anlData);
						retdata.put("time", time);
						retls.add(retdata);
					}
					returnObject.setData(retls);
					returnObject.setStatus("1");
				}
			}
		} else {
			returnObject.setMsg("上位机未配置采集参数");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	/**
	 * 能耗采集与下位机通讯
	 * @param eqMap
	 * @param index
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	@SuppressWarnings({ "static-access", "unchecked" })
	public Dto operEnergyController(Map<String,Object> eqMap,String index) throws UnsupportedEncodingException, RemoteException, ServiceException {
		Map<String, Object> pMap = new HashMap<>();
		Map<String, Object> data = new HashMap<>();
		//判断操作类型
		if(index.equals("0")) {//新增一个采集器			
			data.put("ID", eqMap.get("f_sbid"));
			data.put("Name", eqMap.get("attr_f_sys_name"));//系统名称
			data.put("Alias", eqMap.get("attr_f_nick_name"));//别名
			data.put("Description", eqMap.get("attr_f_description"));//描述
			data.put("Location", eqMap.get("attr_f_azwz"));//安装位置
			data.put("Zone", eqMap.get("attr_f_ssqy"));//所属区域
			data.put("Active", eqMap.get("attr_f_enabled"));
			data.put("SamplePeriod", eqMap.get("attr_f_coll_cycle"));
			data.put("HisdataSavePeriod", eqMap.get("attr_f_his_data_save_cycle"));
			pMap.put("CollectControllerParameter", data);
			pMap.put("IPAddr", eqMap.get("attr_f_ip_addr"));
			pMap.put("Port", eqMap.get("attr_f_port"));
		}else if(index.equals("11")){//新增一个电表			
			//查询电表所在的采集器信息
			Map<String, Object> collectorInfo = besJobManagerMapper.querycollectorInfoByAmmeter( (String) eqMap.get("f_psys_name"));
			String collector_method_id = (String) eqMap.get("attr_f_cjfabh");
			List<Map<String,Object>> paramsLs = besJobManagerMapper.queryAmmeter_params_schemeid(collector_method_id);
			Map<String,Object> paramsDto = assParams(paramsLs, collector_method_id);
			data.put("Active", eqMap.get("attr_f_enabled"));
			data.put("Description", eqMap.get("attr_f_description"));
			data.put("ComRate", eqMap.get("attr_f_comm_rate"));//通信波特率编号
			data.put("Alias", eqMap.get("attr_f_nick_name"));//别名
			data.put("MeterID", eqMap.get("attr_f_sys_name"));
			data.put("PhyAddr", eqMap.get("attr_f_wldz"));
			data.put("ComPort", eqMap.get("attr_f_communication_port"));
			data.put("Location", eqMap.get("attr_f_azwz"));
			data.put("MeterType", eqMap.get("attr_f_blxbh"));//型号
			data.put("ComAgreementType", eqMap.get("attr_f_protocol_type"));//通信协议类型
			pMap.put("MeterParameter", data);
			pMap.put("MeterID", eqMap.get("attr_f_sys_name"));
//			pMap.put("f_sys_name",eqMap.get("attr_f_sys_name"));//系统名称
			pMap.put("IPAddr", collectorInfo.get("F_IP_ADDR"));
			pMap.put("Port", collectorInfo.get("F_PORT"));
			pMap.putAll(paramsDto);
		}else if(index.equals("9")) {//删除采集器			
			pMap.put("IPAddr", eqMap.get("F_IP_ADDR"));
			pMap.put("Port", eqMap.get("F_PORT"));
			eqMap.put("IPAddr", pMap.get("IPAddr"));
		}else {
			pMap = eqMap;
		}
		pMap.put("index", index);
		pMap.put("item", "energy");
		String jsonStr = JsonHelper.encodeObject2Json(pMap);
		LemsService  lemService = new LemsService();
		String	reStr = null;
		reStr = lemService.getInfo(new String(jsonStr.getBytes("GBK"), "ISO-8859-1"), LEMSUtil.getLEMService_address(), LEMSUtil.getServiceTimeOut());
		//新增时请求一次
//		if(index.equals("0"))
//		{
//			reStr = lemService.getInfo(new String(jsonStr.getBytes("GBK"), "ISO-8859-1"), LEMSUtil.getLEMService_address(), LEMSUtil.getServiceTimeOut());
//		}
//		else
//		{
//			// 超过3次通讯失败,则视为当前采集器网络中断,不再请求数据
//			for(int faildCnt = 0;faildCnt < 3;faildCnt++) {
//				reStr = lemService.getInfo(new String(jsonStr.getBytes("GBK"), "ISO-8859-1"), LEMSUtil.getLEMService_address(), LEMSUtil.getServiceTimeOut());
//				if(reStr.equals("999")) {
//					continue;
//				}else {
//					break;
//				}
//			}
//
//		}
		if (reStr.equals("999")) {
			if (index.equals("0")) {
				String location = "采集器：" + eqMap.get("attr_f_sys_name") + "（" + eqMap.get("attr_f_ip_addr") + "）";
				String alarmname = returnOperByIndex(index);
				String tipinfo = location + "，执行操作" + alarmname + "时，与下位机通讯失败";
				insertAlarmInfoWithSave(index,alarmname, location, tipinfo, new String("3"),(String) eqMap.get("f_yqbh"),LEMSConstants.LEVELIMPORTENT);
			}else if(index.equals("11")){
				//查询电表所在的采集器信息
				Map<String, Object> collectorInfo = besJobManagerMapper.querycollectorInfoByAmmeter( (String) eqMap.get("f_psys_name"));
				String location = "采集器：" + collectorInfo.get("F_SYS_NAME") + "（" + collectorInfo.get("F_IP_ADDR") + "）";
				String alarmname = returnOperByIndex(index);
				String tipinfo = location + "，执行操作" + alarmname + "时，与下位机通讯失败";
				insertAlarmInfoWithSave(index,alarmname, location, tipinfo, new String("3"),(String) eqMap.get("f_yqbh"),LEMSConstants.LEVELIMPORTENT);
			}else {
				insertAlarmInfo(index, eqMap, new String("3"),"",LEMSConstants.LEVELIMPORTENT);
			}
			Dto msgDto = new BaseDto();
			msgDto.put("errmsg", "通讯失败");
			return msgDto;
		}
		Dto retDto = parseReturnStr(new String(reStr.getBytes("ISO-8859-1"), "GBK"));
		String str = retDto.getAsString("errmsg");
		if(!str.equals(LEMSConstants.Service_Success)) {
				if (index.equals("0")) {
				String location = "采集器：" + eqMap.get("attr_f_sys_name") + "（" + eqMap.get("attr_f_ip_addr") + "）";
				String alarmname = returnOperByIndex(index);
				String tipinfo = location + "，执行操作" + alarmname + "时，返回错误代码：" + str;
				insertAlarmInfoWithSave(index,alarmname, location, tipinfo, new String("2"),(String) eqMap.get("f_yqbh"),LEMSConstants.LEVELIMPORTENT);
				} else {
					if(LEMSConstants.Service_Err.equals(str) || LEMSConstants.ServiceException.equals(str) )
					{
						insertAlarmInfo(index, eqMap, new String("2"), str,LEMSConstants.LEVELIMPORTENT);
					}
					else
					{
						insertAlarmInfo(index, eqMap, new String("2"), str,LEMSConstants.LEVELCOMMON);
					}

				}
		}
		return retDto;	
	}
	/**
	 * map封电能参数
	 * @param paramsLs
	 * @param collector_method_id
	 * @return
	 */
	public Map<String, Object> assParams(List<Map<String, Object>> paramsLs, String collector_method_id) {
		Map<String, Object> allDto = new HashMap<>();
		for (int i = 0; i < paramsLs.size(); i++) {
			Map<String, Object> pDto = new HashMap<>();
			pDto.put("ElectricID", paramsLs.get(i).get("F_DNBH"));
			pDto.put("ElectricName", paramsLs.get(i).get("F_DNMC"));
			pDto.put("OffsetAddr", paramsLs.get(i).get("F_PYDZ"));
			pDto.put("DataLength", paramsLs.get(i).get("F_DATA_LENGTH"));
			pDto.put("DataEncodeType", paramsLs.get(i).get("F_BMGZ"));
			pDto.put("UintType", paramsLs.get(i).get("F_UNIT"));
			pDto.put("PointLocation", paramsLs.get(i).get("F_SCALING_POSITION"));	
			allDto.put(String.valueOf(i), pDto);
		}
		Map<String, Object> pDto = new HashMap<>();
		Map<String, Object> aDto = new HashMap<>();
		aDto.put("CollectMethodID", collector_method_id);
		aDto.put("CollectCount", String.valueOf(paramsLs.size()));
		pDto.put("ElectricDataCollectMethod", aDto);
		pDto.put("ElectricDataInfo", allDto);
		return pDto;
	}

	/**
	 * 解析原始数据
	 * @param enectric_id 能耗编号
	 * @param RawData 电表数据
	 * @param percentage 变比
	 * @param unit 单位
	 * @return
	 */
	@SuppressWarnings({ "unused" })
	public String analysisRawData(String enectric_id,String RawData,String percentage,String unit){
		Dto Dto = new BaseDto();
		String data_encode="";
		String scaling_position="";
		double real=0.0;
		String realData= "";
		try {
//			Dto paramDto = (org.g4studio.core.metatype.Dto) g4Dao.queryForObject("SubMeasureTop.selectInfoByEnectricId", enectric_id);
			Map<String,Object> paramDto = besElectricParamsMapper.selectInfoByEnectricId(enectric_id);
			if (G4Utils.isNotEmpty(paramDto)) {
					data_encode = (String) paramDto.get("F_BMGZ"); // 编码规则
					scaling_position = (String) paramDto.get("F_SCALING_POSITION");// 小数点位置

				//将获取的String类型的小数点位置，转换成int类型
				int scalingPosition=Integer.parseInt(scaling_position);// 小数点位置
				//logger.error("原始数据解析，原始数据转int::::::::::::" + RawData);
				//直接将原始数据转换成int类型
				int rawdata=Integer.parseInt(RawData);
				
				//判断编码规则
				if(data_encode.equals("0")){//bcd编码
					//16转10
					//int decimalData=Integer.parseInt(RawData,16);
					//10转16
					String decimalData=Integer.toHexString(rawdata);
					//logger.error("原始数据解析，原始数据转int::::::::::::" + decimalData);
					rawdata=Integer.parseInt(decimalData);					
				}
				//变比处理---原始数据(电能)计算变比，后期数据分析不在处理---Add by LvSihan 2019.1.14
				if("kwh".equals(unit.toLowerCase())) {
					if(percentage != null && percentage != "" && !percentage.equals("0")) {
						int percent = Integer.parseInt(percentage);
						rawdata *= percent;
					}
				}
				//最后将转换好的数，乘以小数点位置，获取到最后的数
				real= rawdata/(Math.pow(10, (scalingPosition)));
				realData=real+"";
			} else {
				// 为空时的逻辑
			}
		} catch (Exception e) {
			return null;
		}
		return realData;
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
	public static boolean rangeInDefined(int current, int min, int max) {  
        return Math.max(min, current) == Math.min(current, max);  
    }
	/**
	 * 保存报警信息
	 * @param index
	 * @param location
	 * @param tipinfo
	 * @param alarmtype
	 */
	public void insertAlarmInfoWithSave(String index,String alarmname, String location, String tipinfo, String alarmtype,String yqbh,String level) {
		//要添加的报警信息
		BesWainingInfo besWaringInfo = new BesWainingInfo();
		besWaringInfo.setFGuid(UUIDUtil.getRandom32BeginTimePK());
		besWaringInfo.setFLoction(location);//报警位置
		besWaringInfo.setFTipInfo(tipinfo);//提示信息
		besWaringInfo.setFAlarmName(alarmname);//报警名称
		besWaringInfo.setFATime( G4Utils.getCurrentTime());//报警时间
		besWaringInfo.setFType("3");//信息类型
		besWaringInfo.setFYqbh(yqbh);
		besWaringInfo.setFLevel(level);
//		besWainingRealInfoMapper.insertWarningInfo(besWaringInfo);
		//判断是否存在相同的未处理报警
		AlarmUtil.insertWarningReal(besWaringInfo);
		log.info(tipinfo);
//		LEMSUtil.pushAlarm(parkid);
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
		if(Integer.valueOf(index)<= 10) {
			//通过ip地址查询采集器id
			Map<String,Object> collectorInfo = besJobManagerMapper.getCollectorInfo((String)eqMap.get("IPAddr"));
			aDto = returnAlarmInfo(index, "collector", collectorInfo, alarmtype, msg);
		}else {
			//通过电表id获取电表信息
//			Map<String,Object> meterInfo = besJobManagerMapper.queryAmmeterInfo((String)eqMap.get("attr_f_sys_name"));
//			aDto = returnAlarmInfo(index, "meter", meterInfo, alarmtype, msg);
		}		
		//要添加的报警信息
		BesWainingInfo besWaringInfo = new BesWainingInfo();
		besWaringInfo.setFGuid(UUIDUtil.getRandom32BeginTimePK());
		besWaringInfo.setFLoction((String) aDto.get("location"));//报警位置
		besWaringInfo.setFTipInfo((String) aDto.get("tipinfo"));//提示信息
		besWaringInfo.setFAlarmName((String) aDto.get("alarmname"));//报警名称
		besWaringInfo.setFATime( G4Utils.getCurrentTime());//报警时间
		besWaringInfo.setFType(alarmtype);//信息类型
		besWaringInfo.setFYqbh((String) aDto.get("yqbh"));//园区编号
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
	 * 报警信息
	 * @param index
	 * @param type
	 * @param Info
	 * @param alarmtype
	 * @param msg
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> returnAlarmInfo(String index, String type, Map<String, Object> Info, String alarmtype, String msg) {
		String alarmname = returnOperByIndex(index);
		Map<String,Object> infoDto = new HashMap<>();
		if (alarmtype.equals("2")) {
			if (type.equals("meter")) {
				infoDto = getMeterWaringInfo(Info, alarmtype, index, msg);
			} else {
				infoDto = getCollectorWaringInfo(Info, alarmtype, index, msg);
			}
		} else {
			if (type.equals("meter")) {
				infoDto = getMeterWaringInfo(Info, alarmtype, index, msg);
			} else {
				infoDto = getCollectorWaringInfo(Info, alarmtype, index, msg);
			}
		}
		infoDto.put("alarmname", alarmname);
		return infoDto;
	}
	/**
	 * 查询电表报警信息
	 * @param info
	 * @param alarmtype
	 * @param index
	 * @param msg
	 * @return
	 */
	public Map<String, Object> getMeterWaringInfo(Map<String, Object> info, String alarmtype, String index, String msg) {
		String alarmname = returnOperByIndex(index);
		//通过电表sys_name获取对应的采集器信息
		Map<String,Object> pDto = besJobManagerMapper.queryCollectorInfoByAmmeterId((String) info.get("F_SYS_NAME"));		
		String location = "采集器：" + pDto.get("F_SYS_NAME") + "（" + pDto.get("F_IP_ADDR") + "）";
		String location_meter = "电表：" + info.get("F_NICK_NAME") + "（" + info.get("F_SYS_NAME") + "）";
		String yqbh = (String) pDto.get("F_YQBH");
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
	 * 查询采集器报警信息
	 * @param info
	 * @param alarmtype
	 * @param index
	 * @param msg
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Dto getCollectorWaringInfo(Map<String, Object> info, String alarmtype, String index, String msg) {
		Dto pDto = new BaseDto();
		String alarmname = returnOperByIndex(index);
		if (info == null) {
			pDto.put("location", new String("采集器联网搜索"));
			if (alarmtype.equals("3")) {
				pDto.put("tipinfo", new String("执行：") + alarmname + "时，与下位机通讯失败");
			} else {
				pDto.put("tipinfo", new String("执行：") + alarmname + "时，返回错误代码：" + msg);
			}
		} else {
			String location = "采集器：" + info.get("F_SYS_NAME") + "（" + info.get("F_IP_ADDR") + "）";
			pDto.put("location", location);
			if (alarmtype.equals("3")) {
				String tipinfo = location + "，执行操作" + alarmname + "时，与下位机通讯失败";
				pDto.put("tipinfo", tipinfo);
			} else {
				String tipinfo = location + "，执行操作" + alarmname + "时，返回错误代码：" + msg;
				pDto.put("tipinfo", tipinfo);
			}
		}
		String yqbh = (String) info.get("F_YQBH");
		pDto.put("yqbh", yqbh);
		return pDto;
	}
	public String returnOperByIndex(String index) {
		int n = Integer.valueOf(index);
		String msg = "";
		switch (n) {
			case 0:
				msg = "新增采集器";
				break;
			case 1:
				msg = "修改采集器参数";
				break;
			case 2:
				msg = "设置采集器时间";
				break;
			case 3:
				msg = "重新启动采集器";
				break;
			case 4:
				msg = "采集器恢复出厂设置";
				break;
			case 5:
				msg = "获取采集器时间";
				break;
			case 6:
				msg = "获取采集器所有参数信息";
				break;
			case 7:
				msg = "采集器联网搜索";
				break;
			case 8:
				msg = "根据MAC地址修改采集器信息";
				break;
			case 9:
				msg = "删除采集器";
				break;
			case 10:
				msg = "采集器远程升级";
				break;
			case 11:
				msg = "新增电表";
				break;
			case 12:
				msg = "修改电表参数";
				break;
			case 13:
				msg = "删除电表";
				break;
			case 14:
				msg = "获取电表的所有参数";
				break;
			case 15:
				msg = "获取电表的电能参数";
				break;
			case 16:
				msg = "获取电表的历史电能参数";
				break;
			default:
				msg = "与下位机通讯失败";
		}
		return msg;
	}
}
