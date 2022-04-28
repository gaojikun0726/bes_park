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
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BESSbdyMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BesDdcMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.CrossEquipmentMapper;
import com.efounder.JEnterprise.mapper.collectorJob.BESCalculateDataMapper;
import com.efounder.JEnterprise.mapper.dataAnalysises.BesWainingRealInfoMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESSbPzStruct;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESSbTreeNodeType;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesDdc;
import com.efounder.JEnterprise.model.dataAnalysises.BesWainingInfo;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.LampEquipmentService;
import com.framework.common.utils.Validate_y;
import net.sf.json.JSONObject;
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

@Service("LampEquipmentService")
public class LampEquipmentServiceImpl implements LampEquipmentService{

	@Autowired
	private BesDdcMapper besDdcMapper;
	@Autowired
	private CrossEquipmentMapper crossEquipmentMapper;
	@Autowired
	private BESSbdyMapper besSbdyMapper;
	@Autowired
	private BESSbdyServiceImpl besSbdyServiceImpl;
	@Autowired
	private BesWainingRealInfoMapper besWainingRealInfoMapper;
	@Autowired
	private BESCalculateDataMapper besCalculateDataMapper;

	private static final Logger log = LoggerFactory.getLogger(LampEquipmentServiceImpl.class);


	/**
	 * (设置时间2/重启3/重置4/远程升级30)
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException  
	 */
	@SuppressWarnings("static-access")
	@Override
	public ISSPReturnObject operLampDDC(String fSysName,String fIp, String fPort, int fIndex) throws UnsupportedEncodingException, RemoteException, ServiceException {
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
		Dto retDto = operLampController(pMap,fIndex);
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
	 * 获取IP路由器时间
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public ISSPReturnObject getLampDDCTime(String fSysName,String fIp, String fPort, int fIndex) throws UnsupportedEncodingException, RemoteException, ServiceException {
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
		Dto retDto = operLampController(pMap,fIndex);
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
	 * Lamp设置IP路由器的所有参数
	 */
	public ISSPReturnObject synLampDDC(String f_sys_name) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> data = new HashMap<>();
		Map<String, Object> pMap = new HashMap<>();
		//通过f_sys_name查询该DDC属性信息
		Map<String,Object> ddcMap = besDdcMapper.queryDDCInfo(f_sys_name);
		//通讯所需数据
		data.put("Name", ddcMap.get("F_SYS_NAME"));//系统名称
		data.put("Alias", ddcMap.get("F_NICK_NAME"));//别名
		data.put("Description", ddcMap.get("F_DESCRIPTION"));//描述
		data.put("Location", ddcMap.get("F_AZWZ"));//安装位置
		data.put("Zone", ddcMap.get("F_SSQY"));//区域信息
		data.put("ID", Integer.parseInt((String) ddcMap.get("F_SBID")));//
		data.put("Active", ddcMap.get("F_ENABLED"));//使能		
		pMap.put("IPAddr", ddcMap.get("F_IP_ADDR"));//ip地址
		pMap.put("Port", ddcMap.get("F_PORT"));//端口号
		pMap.put("data", data);

		List<Map<String,Object>> lampModules = besSbdyMapper.queryLampModule(f_sys_name);
		for(Map<String,Object> map: lampModules)
		{
			String ftype = map.get("F_TYPE").toString();
			if("3".equals(ftype))
			{
				synLampModule(map.get("F_SYS_NAME").toString(),map.get("F_PSYS_NAME").toString(),"");
				List<Map<String,Object>> points = besSbdyMapper.queryLampModule(map.get("F_SYS_NAME").toString());
				for(Map<String,Object> point: points)
				{
					synLampPoint(point.get("F_SYS_NAME").toString(),point.get("F_PSYS_NAME").toString());
				}
			}
			else
			{
				List<Map<String,Object>> modules = besSbdyMapper.queryLampModule(map.get("F_PSYS_NAME").toString());
				for(Map<String,Object> modulesMap: modules)
				{
					synLampModule(modulesMap.get("F_SYS_NAME").toString(),modulesMap.get("F_PSYS_NAME").toString(),"");
					List<Map<String,Object>> points = besSbdyMapper.queryLampModule(modulesMap.get("F_SYS_NAME").toString());
					for(Map<String,Object> point: points)
					{
						synLampPoint(point.get("F_SYS_NAME").toString(),point.get("F_PSYS_NAME").toString());
					}
				}
			}

		}


		//与下位机通讯
		Dto retDto = operLampController(pMap,1);
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
		}
		return 	returnObject;
	}
	/**
	 * 获取IP路由器的所有配置参数
	 */
	@SuppressWarnings("unchecked")
	public ISSPReturnObject compareLampDDC(String fSysName,String f_guid, String fIp, String fPort)throws UnsupportedEncodingException, RemoteException, ServiceException {
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
		Dto retDto = operLampController(pMap, 7);
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
	 * Lamp设置模块所有信息
	 */
	public ISSPReturnObject synLampModule(String f_sys_name,String f_psys_name,String f_node_attribution) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		//获取该模块属性信息
		Map<String,Object> moduleMap = crossEquipmentMapper.queryModuleInfo(f_sys_name);
		Map<String, Object> pMap = new HashMap<>();
		Map<String, Object> data = new HashMap<>();
		Map<String, Object> ddcinfo = new HashMap<>();
		//查询逻辑点所属模块的父节点属性
		String pNodeType = besSbdyMapper.querySbpzStructInfo(f_psys_name);
		if(pNodeType.equals("6")) {//"6"为支路耦合器节点类型
			ddcinfo = crossEquipmentMapper.queryDDCByModuleBranchCoupler((String) moduleMap.get("F_SYS_NAME"));
		}else if(pNodeType.equals("3"))	{
			//查询模块所属DDC控制器的IP地址
			ddcinfo = crossEquipmentMapper.queryLampDDCInfoByModule(f_psys_name);
		}
		
		Map<String, Object> map = besSbdyServiceImpl.ModuleF_ADDR(f_psys_name);
		//与下位机通讯所需要的数据
		data.put("Name", moduleMap.get("F_SYS_NAME"));//系统名称
		data.put("Alias", moduleMap.get("F_NICK_NAME"));//别名
		data.put("Description", moduleMap.get("F_DESCRIPTION"));//描述
		data.put("Location", moduleMap.get("F_AZWZ"));//安装位置
		data.put("ModelID", Integer.parseInt((String) moduleMap.get("F_TYPE")));//模块型号
		data.put("AreaNum",  Integer.parseInt((String)map.get("trunkCoupler_F_ADDR")));//区域号1-255 
		data.put("BranchNum",  Integer.parseInt((String)map.get("branchCoupler_F_ADDR")));//支路号1-255
		data.put("SlaveAddress", Integer.parseInt((String)moduleMap.get("F_ADDR")));//模块的通信地址
		if(moduleMap.get("F_SBID")!=null)
		{
			data.put("ID", Integer.parseInt((String)moduleMap.get("F_SBID")));//
		}
		data.put("Active", moduleMap.get("F_ENABLED"));
		pMap.put("IPAddr", ddcinfo.get("F_IP_ADDR"));//ip地址
		pMap.put("Port", ddcinfo.get("F_PORT"));//端口号
		pMap.put("data", data);
		Dto retDto = operLampController(pMap, 11);//同步模块信息，index设置为11
		
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
	 * Lamp获取模块所有信息
	 * @param f_sys_name
	 * @param f_psys_name
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public ISSPReturnObject compareLampModule(String f_sys_name,String f_psys_name,String nodeLevel,String f_node_attribution,String PNodeType) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> pMap = new HashMap<>();
		Map<String, Object> data = new HashMap<>();
		Map<String, Object> ddcinfo = new HashMap<>();
		//查询模块所属DDC控制器的IP地址
		if(nodeLevel.equals("5") || PNodeType.equals("6")) {
			ddcinfo = crossEquipmentMapper.queryLampDDCInfoByCoupler(f_psys_name);
		}else {
			ddcinfo = crossEquipmentMapper.queryLampDDCInfoByModule(f_psys_name);
		}
		
		//查询该模块信息
		Map<String,Object> moduleMap = crossEquipmentMapper.queryModuleInfo(f_sys_name);
		pMap.put("IPAddr", ddcinfo.get("F_IP_ADDR"));//ip地址
		pMap.put("Port", ddcinfo.get("F_PORT"));//端口号
		if(moduleMap.get("F_SBID")!=null)
		{
			data.put("ID",Integer.parseInt((String) moduleMap.get("F_SBID")) );//
		}
		pMap.put("data", data);
		Dto retDto = operLampController(pMap, 13);//获取一个模块的所有配置信息，index设置为13
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
	 * 获取数据库中逻辑点的参数
	 * 王红杰
	 */
	@Override
	public ISSPReturnObject getPointInfo1(String f_sys_name, String f_guid, String f_id, String f_node_type) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> pointMap = new HashMap<>();//该逻辑点在树中属性信息
		Map<String, Object> pointMapown = new HashMap<>();//该逻辑点相应表属性信息
		String all = f_guid;
		String allf_id = f_id;
		String f_node_typeDDC = f_node_type;
		String DO = all.replace(",", "");
		String DI = all.replace(",", "");
		String AO = all.replace(",", "");
		String AI = all.replace(",", "");
		String DDC = all.replace(",", "");
		String module = all.replace(",", "");//模块点
		String node_type = f_node_typeDDC;//四种点位的类型
		String collector = f_guid;//DDC点
		String debugDO = f_guid.replace(",", "");//DO点调试
		String debugAO = f_guid.replace(",", "");//AO点调试
		String ammeter = all;

		//int q=Integer.parseInt(s);
		String tabNameDO = besSbdyMapper.findNodeTableDO(DO, node_type);
		String tabNameDI = besSbdyMapper.findNodeTableDI(DI, node_type);
		String tabNameAO = besSbdyMapper.findNodeTableAO(AO, node_type);
		String tabNameAI = besSbdyMapper.findNodeTableAI(AI, node_type);
		String tabNameDDC = besSbdyMapper.findNodeTableDDC(DDC);
		String tabNamemodule = besSbdyMapper.findNodeTablemodule(module);
		String tabNamecollector = besSbdyMapper.findNodeTablecollector(collector);
		String tabNamedebugDO = besSbdyMapper.findNodeTabledebugDO(debugDO);
		String tabNamedebugAO = besSbdyMapper.findNodeTabledebugAO(debugAO);
		String tabNameammeter = "bes_ammeter";


		if (tabNameDO != null && tabNameDI == null && tabNameAO == null && tabNameAI == null) {
			pointMap = crossEquipmentMapper.queryPointInfoDO(tabNameDO, DO);
		} else if (tabNameDO == null && tabNameDI != null && tabNameAO == null && tabNameAI == null) {
			pointMap = crossEquipmentMapper.queryPointInfoDI(tabNameDI, DI);
		} else if (tabNameDO == null && tabNameDI == null && tabNameAO != null && tabNameAI == null) {
			pointMap = crossEquipmentMapper.queryPointInfoAO(tabNameAO, AO);
		} else if (tabNameDO == null && tabNameDI == null && tabNameAO == null && tabNameAI != null) {
			pointMap = crossEquipmentMapper.queryPointInfoAI(tabNameAI, AI);
		} else if (tabNameDO == null && tabNameDI == null && tabNameAO == null && tabNameAI == null && tabNameDDC != null) {
			pointMap = crossEquipmentMapper.queryPointInfoDDC(tabNameDDC, DDC);
		} else if (tabNameDO == null && tabNameDI == null && tabNameAO == null && tabNameAI == null && tabNameDDC == null && tabNamemodule != null) {
			pointMap = crossEquipmentMapper.queryPointInfomodule(tabNamemodule, module);
		} else if (tabNameDO == null && tabNameDI == null && tabNameAO == null && tabNameAI == null && tabNameDDC == null
				&& tabNamemodule == null && tabNamecollector != null) {
			pointMap = crossEquipmentMapper.queryPointInfocollector(tabNamecollector, collector);
		} else if (tabNameDO == null && tabNameDI == null && tabNameAO == null && tabNameAI == null && tabNameDDC == null
				&& tabNamemodule == null && tabNamecollector == null && tabNamedebugDO != null) {
			pointMap = crossEquipmentMapper.queryPointInfocollector(tabNamedebugDO, debugDO);
		} else if (tabNameDO == null && tabNameDI == null && tabNameAO == null && tabNameAI == null && tabNameDDC == null
				&& tabNamemodule == null && tabNamecollector == null && tabNamedebugDO == null && tabNamedebugAO != null) {
			pointMap = crossEquipmentMapper.queryPointInfocollector(tabNamedebugAO, debugAO);
		} else if (tabNameDO == null && tabNameDI == null && tabNameAO == null && tabNameAI == null && tabNameDDC == null
				&& tabNamemodule == null && tabNamecollector == null && tabNamedebugDO == null && tabNameammeter != null) {
			pointMapown = crossEquipmentMapper.queryPointInfoammeterown(tabNameammeter, all);
			Object f_sys_name_ammeter = pointMapown.get("F_SYS_NAME_OLD");
			pointMap = crossEquipmentMapper.queryPointInfoammeter(tabNameammeter, f_sys_name_ammeter);
		}
		{
			if (f_id != null && f_id != "") {
				String xh_id = allf_id.replace(",", "");

				pointMap = crossEquipmentMapper.queryPointInfoxh(xh_id);
			}
		}
		try {

			returnObject.setData(pointMap);
			returnObject.setMsg("查询成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("查询失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject getPointInfoAI(String f_sys_name, String f_sys_name_old) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> pointMap = new HashMap<>();//该逻辑点属性信息
		return returnObject;
	}

	@Override
	public ISSPReturnObject getPointInfoSBDY(String f_sys_name) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> pointMap = new HashMap<>();//该逻辑点属性信息
		pointMap = crossEquipmentMapper.getPointInfoSBDY(f_sys_name);
		if (pointMap == null) {
			return returnObject;
		}

		try {

			returnObject.setData(pointMap);
			returnObject.setMsg("查询成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("查询失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	/*
	 *
	 * @Description: 判断当前系统名称在相应的四种点位表里面有没有,有的话将f_sys_name输入框设置成不能输入
	 *
	 * @auther: wanghongjie
	 * @date: 16:18 2020/4/11
	 * @param:
	 * @return:
	 *
	 */

	@Override
	public ISSPReturnObject getInfo_f_sys_name(String f_sys_name, String tabName) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		int num = crossEquipmentMapper.getInfo_f_sys_name(f_sys_name, tabName);
		if (num > 0) {
			returnObject.setStatus("1");//相应的点位表里面有数据的话,则输入框不能输入
		} else {
			returnObject.setStatus("0");//相应的点位表里面有数据的话,则输入框可以输入
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject getPointInfo(String f_sys_name, String f_psys_name) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> pointMap = new HashMap<>();//该逻辑点属性信息
		Map<String, Object> moduleinfo = new HashMap<>();//所属模块信息
		// 查询该节点的实体表名
		String tabName = besSbdyMapper.findNodeTable(f_sys_name);
		try {
			// 查询该逻辑点属性信息
			pointMap = crossEquipmentMapper.queryPointInfo(tabName, f_sys_name);

			//add by liuzhen at 20190228 显示字段具体名称
			if("0".equals(pointMap.get("F_WORK_MODE")))
			{
				pointMap.put("F_WORK_MODE","自动");
			}
			else
			{
				pointMap.put("F_WORK_MODE","手动");
			}

			if("0".equals(pointMap.get("F_REVERSED")))
			{
				pointMap.put("F_REVERSED","反向");
			}
			else
			{
				pointMap.put("F_REVERSED","正向");
			}

			if("0".equals(pointMap.get("F_ALARM_PRIORITY")))
			{
				pointMap.put("F_ALARM_PRIORITY","严重");
			}
			else
			{
				pointMap.put("F_ALARM_PRIORITY","危及安全");
			}

			if("0".equals(pointMap.get("F_ALARM_PRIORITY")))
			{
				pointMap.put("F_ALARM_PRIORITY","严重");
			}
			else
			{
				pointMap.put("F_ALARM_PRIORITY","危及安全");
			}

			if("0".equals(pointMap.get("F_ALARM_TYPE")))
			{
				pointMap.put("F_ALARM_TYPE","不报警");
			}
			else if("1".equals(pointMap.get("F_ALARM_TYPE")))
			{
				pointMap.put("F_ALARM_TYPE","标准报警");
			}
			else
			{
				pointMap.put("F_ALARM_TYPE","增强报警");
			}
			if(pointMap.get("F_NODE_TYPE") != null ) {
				List<BESSbTreeNodeType> nodeTypeList = besSbdyMapper.findChildByTreeNoteType(pointMap.get("F_NODE_TYPE").toString());
				if(nodeTypeList!=null && nodeTypeList.size()>0)
				{
					pointMap.put("F_NODE_TYPE",nodeTypeList.get(0).getF_node_name());
				}

			}

			if("0".equals(pointMap.get("F_CLOSE_STATE")))
			{
				pointMap.put("F_CLOSE_STATE","不闭合");
			}
			else
			{
				pointMap.put("F_CLOSE_STATE","闭合");
			}


			if("0".equals(pointMap.get("F_ALARM_ENABLE")))
			{
				pointMap.put("F_ALARM_ENABLE","不使能");
			}
			else
			{
				pointMap.put("F_ALARM_ENABLE","使能");
			}

			if(!tabName.equals("BES_VPOINT")) {
				//查询逻辑点所属模块的信息
				moduleinfo = crossEquipmentMapper.queryModuleInfoByPoint(f_sys_name);
				pointMap.put("ModuleID",moduleinfo.get("F_NICK_NAME"));
			}						
			returnObject.setData(pointMap);
			returnObject.setMsg("查询成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("查询失败");
			returnObject.setStatus("0");
		}		
		return returnObject;
	}
	/**
	 * Lamp设置一个逻辑点的所有参数
	 */
	@Override
	public ISSPReturnObject synLampPoint(String f_sys_name, String f_psys_name) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> pointMap = new HashMap<>();//该逻辑点属性信息
		Map<String, Object> ddcinfo = new HashMap<>();//所属DDC控制器信息
		Map<String, Object> moduleinfo = new HashMap<>();//所属模块信息
		Map<String, Object> data = new HashMap<>();//通讯所需
		Map<String, Object> pMap = new HashMap<>();//通讯所需
		//查询该节点的实体表名
		String tabName = besSbdyMapper.findNodeTable(f_sys_name);
		// 查询该逻辑点属性信息
		pointMap = crossEquipmentMapper.queryPointInfo(tabName, f_sys_name);
		if (tabName.equals("BES_VPOINT")) {			
			// 查询逻辑虚点所属IP路由器的IP地址
			ddcinfo = crossEquipmentMapper.queryDDCInfoByVPoint(f_sys_name);
		} else {
//			//查询逻辑点所属IP路由器的IP地址
//			ddcinfo = crossEquipmentMapper.queryLampDDCInfoByPoint(f_sys_name);
			//查询逻辑点所属模块的信息
			moduleinfo = crossEquipmentMapper.queryModuleInfoByPoint(f_sys_name);
			
			//查询逻辑点所属模块的父节点属性
			String pNodeType = besSbdyMapper.queryPointModPtype((String) moduleinfo.get("F_SYS_NAME"));
			if(pNodeType.equals("6")) {//"6"为支路耦合器节点类型
				ddcinfo = crossEquipmentMapper.queryDDCByModuleBranchCoupler((String) moduleinfo.get("F_SYS_NAME"));
			}else if(pNodeType.equals("3"))	{
				//查询逻辑点所属IP路由器的IP地址
				ddcinfo = crossEquipmentMapper.queryLampDDCInfoByPoint(f_sys_name);
			}
		}
		if(pointMap != null)
		{
			//data.put("PointType", Integer.parseInt(((String) pointMap.get("F_NODE_TYPE")).substring(1)));//点类型
			data.put("PointType", Integer.parseInt(((String) pointMap.get("F_NODE_TYPE"))));//点类型虚点同步时 tianjiangwei
			data.put("ID", Integer.parseInt((String) pointMap.get("F_SBID")));//点的f_sbid
			data.put("Active", pointMap.get("F_ENABLED"));
			data.put("Name", pointMap.get("F_SYS_NAME"));//系统名称
			data.put("Alias", pointMap.get("F_NICK_NAME"));//别名
			data.put("Description", pointMap.get("F_DESCRIPTION"));//描述
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
				data.put("InitValue", pointMap.get("F_INIT_VAL"));//值
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
				data.put("HighRange", pointMap.get("F_MAX_VAL"));//最高阀值
			}
			if(pointMap.get("F_MIN_VAL") != null) {
				data.put("LowRange", pointMap.get("F_MIN_VAL"));//最低阀值
			}
			if(pointMap.get("F_ACCURACY") != null) {
				data.put("Precision", pointMap.get("F_ACCURACY"));//精度
			}
			if(pointMap.get("F_HIGH_LIMIT") != null) {
				data.put("AlarmHighValue", pointMap.get("F_HIGH_LIMIT"));//高限报警值
			}
			if(pointMap.get("F_LOW_LIMIT") != null) {
				data.put("AlarmLowValue", pointMap.get("F_LOW_LIMIT"));//底限报警值
			}
			if(pointMap.get("F_CLOSE_STATE") != null) {
				data.put("AlarmTrigger", pointMap.get("F_CLOSE_STATE"));//报警触发
			}
			if(pointMap.get("F_SOURCED") != null) {
				data.put("ActivePassive", pointMap.get("F_SOURCED"));//有源无源
			}
		}

		if(moduleinfo.get("F_SBID") != null) {
			data.put("ModuleID", Integer.parseInt((String) moduleinfo.get("F_SBID")));//点所在模块id
		}

		pMap.put("data", data);
		pMap.put("IPAddr", ddcinfo.get("F_IP_ADDR"));//ip地址
		pMap.put("Port", ddcinfo.get("F_PORT"));//端口号
		
		Dto retDto = operLampController(pMap, 16);//同步逻辑点信息，index设置为16		
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
	 * Lamp获取逻辑点数据
	 */
	@SuppressWarnings("unchecked")
	public ISSPReturnObject compareLampPoint(String f_sys_name,String f_psys_name,String f_sbid,String  f_flag) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> pMap = new HashMap<>();
		Map<String, Object> data = new HashMap<>();
		Map<String, Object> ddcinfo = new HashMap<>();
		// 查询该节点的实体表名
		String tabname = besSbdyMapper.findNodeTable(f_sys_name);
		if(tabname != null && tabname.equals("BES_VPOINT")) {
			// 查询逻辑虚点所属DDC控制器的IP地址
			ddcinfo = crossEquipmentMapper.queryDDCInfoByVPoint(f_sys_name);
		}else {
			//查询逻辑点所属模块的信息
			Map<String, Object> moduleinfo = crossEquipmentMapper.queryModuleInfoByPoint(f_sys_name);
			
			//查询逻辑点所属模块的父节点属性
			String pNodeType = besSbdyMapper.queryPointModPtype((String) moduleinfo.get("F_SYS_NAME"));
			if(pNodeType.equals("6")) {//"6"为支路耦合器节点类型
				ddcinfo = crossEquipmentMapper.queryDDCByModuleBranchCoupler((String) moduleinfo.get("F_SYS_NAME"));
			}else if(pNodeType.equals("3"))	{
				//查询逻辑点所属IP路由器的IP地址
				ddcinfo = crossEquipmentMapper.queryLampDDCInfoByPoint(f_sys_name);
			}
		}		
		pMap.put("IPAddr", ddcinfo.get("F_IP_ADDR"));//ip地址
		pMap.put("Port", ddcinfo.get("F_PORT"));//端口号
		data.put("ID", Integer.parseInt(f_sbid));//
		pMap.put("data", data);
		Dto retDto = operLampController(pMap, 20);//获取一个逻辑点的所有配置参数，index设置为20
		int reStr = retDto.getAsInteger("code");
		if (reStr == 0) {
			if (Validate_y.noNull(retDto.get("data"))) {
				Map<String, Object> xDto =  (Map<String, Object>) retDto.get("data");
				//根据精度计算初始值
				String f_initValue = null;
				f_initValue = xDto.get("InitValue").toString();
				String initValue = f_initValue;
				if("0".equals(xDto.get("Precision").toString())){
					xDto.put("InitValue",initValue);
				}else{
					if("1".equals(xDto.get("Precision").toString())){
						double d=Double.valueOf(initValue).doubleValue();
						initValue = String.format("%.1f", d/ 10);
						xDto.put("InitValue",initValue);
					}else if("2".equals(xDto.get("Precision").toString())){
						double d=Double.valueOf(initValue).doubleValue();
						initValue = String.format("%.2f", d/ 100);
						xDto.put("InitValue",initValue);
					}else if("3".equals(xDto.get("Precision").toString())){
						double d=Double.valueOf(initValue).doubleValue();
						initValue = String.format("%.3f", d/ 1000);
						xDto.put("InitValue",initValue);
					}else if("4".equals(xDto.get("Precision").toString())){
						double d=Double.valueOf(initValue).doubleValue();
						initValue = String.format("%.4f", d/ 10000);
						xDto.put("InitValue",initValue);
					}else if("5".equals(xDto.get("Precision").toString())){
						double d=Double.valueOf(initValue).doubleValue();
						initValue = String.format("%.5f", d/ 100000);
						xDto.put("InitValue",initValue);
					}
				}
				if(tabname != null && tabname.equals("BES_ANALOG_INPUT")||("BES_VPOINT").equals(tabname)){//能耗统计电表数据插入（虚点+ai实点）
					if("1".equals(f_flag)){
						String f_data = xDto.get("InitValue").toString();
						String f_yqbh = ddcinfo.get("F_YQBH").toString();
						String f_id = UUIDUtil.getRandom32BeginTimePK();
						if(!"0".equals(f_initValue)) {//采集数据不为空时数据插入电表数据表
							Boolean flag = besCalculateDataMapper.intAmmeterData(f_sys_name,f_data,f_yqbh,f_id);
						}
					}
				}
				//add by liuzhen at 20190228 显示字段具体名称
				if("0".equals(xDto.get("WorkMode")))
				{
					xDto.put("WorkMode","自动");
				}
				else
				{
					xDto.put("WorkMode","手动");
				}


				if("0".equals(xDto.get("Polarity")))
				{
					xDto.put("Polarity","反向");
				}
				else
				{
					xDto.put("Polarity","正向");
				}

				if("0".equals(xDto.get("AlarmPriority")))
				{
					xDto.put("AlarmPriority","严重");
				}
				else
				{
					xDto.put("AlarmPriority","危及安全");
				}


				if("0".equals(xDto.get("AlarmType")))
				{
					xDto.put("AlarmType","不报警");
				}
				else if("1".equals(xDto.get("AlarmType")))
				{
					xDto.put("AlarmType","标准报警");
				}
				else
				{
					xDto.put("AlarmType","增强报警");
				}

				if(xDto.get("PointType") != null ) {
					if(tabname != null && tabname.equals("BES_VPOINT")) {
						String vpointname = besSbdyMapper.findVpointname(xDto.get("PointType").toString());
						if (vpointname != null ) {
							xDto.put("PointType", vpointname);
						}
					}else {
						List<BESSbTreeNodeType> nodeTypeList = besSbdyMapper.findChildByTreeNoteType(xDto.get("PointType").toString());
						if(nodeTypeList!=null && nodeTypeList.size()>0)
						{
							xDto.put("PointType",nodeTypeList.get(0).getF_node_name());
						}
					}						
				}

				if("0".equals(xDto.get("AlarmTrigger")))
				{
					xDto.put("AlarmTrigger","不闭合");
				}
				else
				{
					xDto.put("AlarmTrigger","闭合");
				}


				if("0".equals(xDto.get("AlarmActive")))
				{
					xDto.put("AlarmActive","不使能");
				}
				else
				{
					xDto.put("AlarmActive","使能");
				}


				//查询逻辑点所属模块的信息
				Map<String, Object>  moduleinfo = crossEquipmentMapper.queryModuleInfoByPoint(f_sys_name);
				xDto.put("ModuleID",moduleinfo.get("F_NICK_NAME"));

				xDto.put("Active", xDto.get("Active").equals(1) ? "是" : "否");
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
	 * 删除逻辑点
	 */
	public ISSPReturnObject reSetLampPoint(String f_sys_name,String f_psys_name,String f_sbid) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> pMap = new HashMap<>();
		Map<String, Object> data = new HashMap<>();
		Map<String, Object> ddcinfo = new HashMap<>();
		String tabName = besSbdyMapper.findNodeTable(f_sys_name);
		int count = 0;
		if(!tabName.equals("BES_ANALOG_INPUT")){
			count = besSbdyMapper.delSbTreeBySysName(f_sys_name, tabName);
		}else{
			// 查询逻辑虚点所属IP路由器的IP地址
			//判断是否可删除
			int ammeterDel =0;
			com.alibaba.fastjson.JSONObject obj = new com.alibaba.fastjson.JSONObject();
			obj.put("fNodeType","10");
			obj.put("f_sys_name",f_sys_name);
			ISSPReturnObject returnDelEnable = besSbdyServiceImpl.delEnable(obj);
			if(returnDelEnable.getStatus().equals("1")){
				ammeterDel = besSbdyMapper.delSbTreeBySysName(f_sys_name, "bes_ammeter");
				count = besSbdyMapper.delSbTreeBySysName(f_sys_name, tabName);
			}else{
				returnObject.setStatus("0");
				returnObject.setMsg(returnDelEnable.getMsg());
				return returnObject;
			}
		}
		if(count >0) {
			if(tabName.equals("BES_VPOINT")) {
				// 查询逻辑虚点所属IP路由器的IP地址
				ddcinfo = crossEquipmentMapper.queryDDCInfoByVPoint(f_sys_name);
			}
			//查询逻辑点所属模块的信息
			Map<String, Object> moduleinfo = crossEquipmentMapper.queryModuleInfoByPoint(f_sys_name);
			//查询逻辑点所属模块的父节点属性
			String pNodeType = besSbdyMapper.queryPointModPtype((String) moduleinfo.get("F_SYS_NAME"));
			if(pNodeType.equals("6")) {//"6"为支路耦合器节点类型
				ddcinfo = crossEquipmentMapper.queryDDCByModuleBranchCoupler((String) moduleinfo.get("F_SYS_NAME"));
			}else if(pNodeType.equals("3"))	{
				//查询逻辑点所属IP路由器的IP地址
				ddcinfo = crossEquipmentMapper.queryLampDDCInfoByPoint(f_sys_name);
			}
//			//查询逻辑点所属IP路由器的IP地址
//			ddcinfo = crossEquipmentMapper.queryLampDDCInfoByPoint(f_sys_name);
			pMap.put("IPAddr", ddcinfo.get("F_IP_ADDR"));//ip地址
			pMap.put("Port", ddcinfo.get("F_PORT"));//端口号
			data.put("ID", Integer.parseInt(f_sbid));//
			pMap.put("data", data);
			Dto retDto = operLampController(pMap, 19);//删除一个逻辑点，index设置为19
			int reStr = retDto.getAsInteger("code");
			if (reStr == 0) {
				returnObject.setStatus("1");
				returnObject.setMsg("重置成功");
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg(judgecode(reStr));
			}
			return 	returnObject;
		}		
		returnObject.setStatus("0");
		returnObject.setMsg("重置失败！");
		return 	returnObject;
	}
	/**
	 * 设置一个逻辑点的值
	 */
	public ISSPReturnObject debugLampPointInfo(com.alibaba.fastjson.JSONObject obj) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> ddcinfo = new HashMap<>();
//		if("".equals(obj.getString("f_sys_name"))){
//			Map<String,Object> pointInfo = besSbdyMapper.queryPointInfo(obj.getString("f_id"),obj.getString("tabname"));
//			obj.put("f_sys_name", pointInfo.get("F_SYS_NAME"));
//		}		
		//查询该节点的实体表名
		String tabName = besSbdyMapper.findNodeTable(obj.getString("f_sys_name"));
		// 查询该逻辑点属性信息
		Map<String, Object> pointMap = crossEquipmentMapper.queryPointInfo(tabName, obj.getString("f_sys_name"));
		//查询逻辑点所属模块的信息
		Map<String, Object> moduleinfo = crossEquipmentMapper.queryModuleInfoByPoint(obj.getString("f_sys_name"));		
		//查询逻辑点所属模块的父节点属性
		String pNodeType = besSbdyMapper.queryPointModPtype((String) moduleinfo.get("F_SYS_NAME"));
		if(pNodeType.equals("6")) {//"6"为支路耦合器节点类型
			ddcinfo = crossEquipmentMapper.queryDDCByModuleBranchCoupler((String) moduleinfo.get("F_SYS_NAME"));
		}else if(pNodeType.equals("3"))	{
			//查询逻辑点所属IP路由器的IP地址
			ddcinfo = crossEquipmentMapper.queryLampDDCInfoByPoint(obj.getString("f_sys_name"));
		}
		Map<String, Object> pMap = new HashMap<>();
		Map<String, Object> data = new HashMap<>();
		data.put("ID", Integer.parseInt(String.valueOf(pointMap.get("F_SBID"))));//点的f_sbid
		data.put("WorkMode", Integer.parseInt(obj.getString("f_work_mode")));
		data.put("Value", Integer.parseInt(obj.getString("f_init_val")));//系统名称
		pMap.put("data", data);
		pMap.put("IPAddr", ddcinfo.get("F_IP_ADDR"));//ip地址
		pMap.put("Port", ddcinfo.get("F_PORT"));//端口号
		Dto retDto = operLampController(pMap, 17);//设置一个逻辑点的值  ，index设置为17	
		int reStr = retDto.getAsInteger("code");
		if (reStr == 0) {//同步成功，更新本地数据库
//			com.alibaba.fastjson.JSONObject jobj = new com.alibaba.fastjson.JSONObject();
//			jobj.put("attrTabName", tabName);
//			jobj.put("attr_f_sys_name", obj.getString("f_sys_name"));
//			jobj.put("f_work_mode", obj.getString("f_work_mode"));
//			jobj.put("f_init_val", obj.getString("f_init_val"));
			obj.put("tabName", tabName);
			besSbdyMapper.editSbTreeStructInfoBySysName(obj);			
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
	 * 调试开关按钮
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	@Override
	public ISSPReturnObject debugLampPointList(com.alibaba.fastjson.JSONObject obj) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		String fSysnames = obj.getString("f_sys_name");
		String[] f_sys_name  = fSysnames.split(",");
//		int[] pointStateList = new int[fid.length]; 
		for(int i=0;i< f_sys_name.length;i++) {
			obj.put("f_sys_name", f_sys_name[i]);
			returnObject = debugLampPointInfo(obj);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return returnObject;
	}

	/**
	 * 获取虚点信息
	 * @param f_sys_name
	 * @return
	 */
	@Override
	public ISSPReturnObject getVirtualPointInfo(String f_sys_name)
	{
		ISSPReturnObject result = new ISSPReturnObject();

		if (!StringUtils.hasText(f_sys_name))
		{
			result.setStatus("0");
			result.setMsg("参数错误");
			return result;
		}

		Map<String, Object> virtualPointInfo = crossEquipmentMapper.findVpointBySysName(f_sys_name);

		if (null == virtualPointInfo || virtualPointInfo.isEmpty())
		{
			result.setStatus("0");
			result.setMsg("数据不存在");
			return result;
		}

		result.setStatus("1");
		result.setData(virtualPointInfo);
		result.setMsg("查询成功");
		return result;
	}

	/**
	 * 设置一个逻辑点的值（通过名字）
	 */
	public ISSPReturnObject setLampPointInfo(com.alibaba.fastjson.JSONObject obj) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> ddcinfo = new HashMap<>();
		//查询该节点的实体表名
		String tabName = besSbdyMapper.findNodeTable(obj.getString("f_sys_name"));
		//查询逻辑点所属模块的信息
		Map<String, Object> moduleinfo = crossEquipmentMapper.queryModuleInfoByPoint(obj.getString("f_sys_name"));
		
		//查询逻辑点所属模块的父节点属性
		String pNodeType = besSbdyMapper.queryPointModPtype((String) moduleinfo.get("F_SYS_NAME"));
		if(pNodeType.equals("6")) {//"6"为支路耦合器节点类型
			ddcinfo = crossEquipmentMapper.queryDDCByModuleBranchCoupler((String) moduleinfo.get("F_SYS_NAME"));
		}else if(pNodeType.equals("3"))	{
			//查询逻辑点所属IP路由器的IP地址
			ddcinfo = crossEquipmentMapper.queryLampDDCInfoByPoint(obj.getString("f_sys_name"));
		}
		
//		//查询逻辑点所属IP路由器的IP地址
//		Map<String, Object> ddcinfo = crossEquipmentMapper.queryLampDDCInfoByPoint(obj.getString("f_sys_name"));
		Map<String, Object> pMap = new HashMap<>();
		Map<String, Object> data = new HashMap<>();
		data.put("Name", obj.getString("f_sys_name"));//点的f_sys_name
		data.put("Value", Integer.parseInt(obj.getString("f_init_val")));//
		pMap.put("data", data);
		pMap.put("IPAddr", ddcinfo.get("F_IP_ADDR"));//ip地址
		pMap.put("Port", ddcinfo.get("F_PORT"));//端口号
		Dto retDto = operLampController(pMap, 18);//设置一个逻辑点的值  ，index设置为18
		int reStr = retDto.getAsInteger("code");
		if (reStr == 0) {//同步成功，更新本地数据库
			com.alibaba.fastjson.JSONObject jobj = new com.alibaba.fastjson.JSONObject();
			obj.put("attrTabName", tabName);
			obj.put("attr_f_sys_name", jobj.getString("f_sys_name"));
			obj.put("f_init_val", jobj.getString("f_init_val"));
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
	 * Lamp照明与下位机通讯
	 * @param eqMap
	 * @param index
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	@Deprecated
	@SuppressWarnings({ "static-access", "unchecked" })
	public Dto operLampController(Map<String,Object> eqMap, int index) throws UnsupportedEncodingException, RemoteException, ServiceException {
		Map<String, Object> pMap = new HashMap<>();
		Map<String, Object> data = new HashMap<>();
		Map<String, Object> ddcinfo = new HashMap<>();
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
			data.put("AreaNum", Integer.parseInt((String)eqMap.get("trunkCoupler_F_ADDR")));//区域号1-255 
			data.put("BranchNum", Integer.parseInt((String)eqMap.get("branchCoupler_F_ADDR")));//支路号1-255
			data.put("SlaveAddress",  Integer.parseInt((String) eqMap.get("attr_f_addr")));//模块的通信地址
			data.put("ID",  eqMap.get("f_sbid"));//
			data.put("Active", eqMap.get("attr_f_enabled"));
			pMap.put("IPAddr", eqMap.get("IPAddr"));//ip地址
			pMap.put("Port", eqMap.get("Port"));//端口号
			pMap.put("data", data);
		}else if(index == 15) {//新增一个逻辑点
			data.put("PointType",Integer.parseInt((String) eqMap.get("attr_f_node_type")));//点类型
			data.put("ID", eqMap.get("f_sbid"));//点的f_sbid
			data.put("Active", Integer.parseInt((String)eqMap.get("attr_f_enabled")));
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
			if(eqMap.get("attr_f_init_val") != null) {
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
				data.put("HighRange", Integer.parseInt((String)eqMap.get("attr_f_max_val")));//最高阀值
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
		}else {
			pMap = eqMap;
		}
		pMap.put("index", index);
		pMap.put("item", "lamp");
		String jsonStr = JsonHelper.encodeObject2Json(pMap);
		LemsService  lemService = new LemsService();
		String	reStr = lemService.getInfo(new String(jsonStr.getBytes("GBK"), "ISO-8859-1"), LEMSUtil.getLEMService_address(), LEMSUtil.getServiceTimeOut());
		if (reStr.equals("999") ) {
			if (index == 0) {
				String location = "IP路由器：" + eqMap.get("attr_f_sys_name") + "（" + eqMap.get("attr_f_ip_addr") + "）";
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
					String location = "IP路由器：" + eqMap.get("attr_f_sys_name") + "（" + eqMap.get("attr_f_ip_addr") + "）";
					String alarmname = returnOperByIndex(index+"");
					String tipinfo = location + "，执行操作" + alarmname + "时，与下位机通讯失败";
					String  yqbh = (String) eqMap.get("f_yqbh");
					insertAlarmInfoWithSave(index+"", alarmname,location, tipinfo, yqbh, new String("2"),LEMSConstants.LEVELIMPORTENT);
				} else {
					if(str == 5 )
					{
						insertAlarmInfo(index+"", eqMap, new String("2"),judgecode(str),LEMSConstants.LEVELCOMMON);
					}
					else
					{
						insertAlarmInfo(index+"", eqMap, new String("2"),judgecode(str),LEMSConstants.LEVELIMPORTENT);

					}

				}
			}
		}
		return retDto;	
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

	/**
	 * 保存报警信息
	 * @param index
	 * @param location
	 * @param tipinfo
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
			pDto.put("location", new String("IP路由器联网搜索"));
			if (alarmtype.equals("3")) {
				pDto.put("tipinfo", new String("执行：") + alarmname + "时，与下位机通讯失败");
			} else {
				pDto.put("tipinfo", new String("执行：") + alarmname + "时，返回错误代码：" + msg);
			}
		} else {
			String location = "IP路由器：" + info.get("F_SYS_NAME") + "（" + info.get("F_IP_ADDR") + "）";
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
			location = "IP路由器：" + info.get("f_allpath").toString().split(">")[3]+ ",模块"+info.get("f_allpath").toString().split(">")[4]+ "（" + info.get("DDCIPAddr") + "）";
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
				if(pointInfos.get(i).get("F_NODE_ATTRIBUTION").equals("2"))
				{

					parentInfo  = besSbdyMapper.querybranchCouplerpNode((String) pointInfos.get(i).get("F_PSYS_NAME"));

					if (null == parentInfo || parentInfo.isEmpty())
                    {
                        continue;
                    }

					if(("1").equals(parentInfo.get("F_SBID")))
					{
						if(collectorInfo.get("F_SYS_NAME").equals(parentInfo.get("F_SYS_NAME").toString()))
						{
							pointInfo = pointInfos.get(i);
						}
					}else
					{
						parentInfo  = besSbdyMapper.querybranchCouplerpNode((String) parentInfo.get("F_SYS_NAME"));

						if (null == parentInfo || parentInfo.isEmpty())
						{
							continue;
						}

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
				location = "IP路由器：" + collectorInfo.get("F_SYS_NAME") +"模块"+moduleInfo.get("F_NICK_NAME")+ "（" + collectorInfo.get("F_IP_ADDR") + "）";
				location_meter = "逻辑点：" + pointInfo.get("F_NICK_NAME") + "（" + pointInfo.get("F_SYS_NAME") + "）";
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

		Map<String,Object> ddcinfo = null;
		String pNodeType = besSbdyMapper.querySbpzStructInfo((String) info.get("f_psys_name"));
		if("6".equals(pNodeType)) {//"6"为支路耦合器节点类型
			ddcinfo = crossEquipmentMapper.queryLampDDCInfoByCoupler((String) info.get("f_psys_name"));
		}else if("3".equals(pNodeType))	{
			//查询逻辑点所属IP路由器的IP地址
			ddcinfo = crossEquipmentMapper.queryLampDDCInfoByModule((String) info.get("f_psys_name"));
		}
		Map<String,Object> pDto = new HashMap<>();
		String location = "";
		String location_meter = "";
		String yqbh = "";
		if("10".equals(index))//新增新增模块时
		{
			 location = "IP路由器：" + ddcinfo.get("F_SYS_NAME") + "（" + ddcinfo.get("F_IP_ADDR") + "）";
			 location_meter = "模块：" + info.get("f_nick_name") + "（" + info.get("f_sys_name") + "）";
			 yqbh = (String) info.get("f_yqbh");

		}
		else
		{
			Map<String,Object> collectorInfo = besDdcMapper.queryDDCInfoByIp((String)info.get("IPAddr"));
			location = "IP路由器：" + collectorInfo.get("F_SYS_NAME") + "（" + collectorInfo.get("F_IP_ADDR") + "）";
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
			Map<String,Object> collectorInfo = besDdcMapper.queryDDCInfoByIp((String)eqMap.get("IPAddr"));
			aDto = returnAlarmInfo(index, "collector", collectorInfo, alarmtype, msg,eqMap);
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
	 * 根据index返回相应操作信息
	 * @param index
	 * @return
	 */
	public String returnOperByIndex(String index) {
		int n = Integer.valueOf(index);
		String msg = "";
		switch (n) {
			case 0:
				msg = "新增一个IP路由器";
				break;
			case 1:
				msg = "设置IP路由器的所有参数";
				break;
			case 2:
				msg = "设置IP路由器的时间";
				break;
			case 3:
				msg = "重启IP路由器";
				break;
			case 4:
				msg = "重置IP路由器";
				break;
			case 5:
				msg = "删除一个IP路由器";
				break;
			case 6:
				msg = "获取IP路由器的时间";
				break;
			case 7:
				msg = "获取IP路由器的所有配置参数";
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
	 * @Description: 获取相应的点位的f_node_attribution
	 * @auther: wanghongjie
	 * @date: 11:00 2020/4/15
	 * @param: [f_sys_name]
	 * @return: com.core.common.ISSPReturnObject
	 */
	@Override
	public ISSPReturnObject f_node_attribution(String f_sys_name, String tabname) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> pointMap = new HashMap<>();//该逻辑点属性信息
		pointMap = crossEquipmentMapper.f_node_attribution(f_sys_name,tabname);
		if (pointMap == null) {
			return returnObject;
		}

		try {

			returnObject.setData(pointMap);
			returnObject.setMsg("查询成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("查询失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
}
