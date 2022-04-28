package com.efounder.JEnterprise.controller.basedatamanage.eqmanage;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BESSbdyMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.CrossEquipmentMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESSbPzStruct;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESSbTreeNodeType;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BESSbdyService;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.CrossEquipmentService;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.EnerEquipmentService;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.LampEquipmentService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiSort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 设备树（能耗采集、楼控、灯光）
 * 与下位机通讯
 * 移动端接口
 * @author LvSihan
 * @date 2018年11月7日
 * @version 1.0
 */
@RestController
@Api(value="BESoperatingInterfaceController",tags={"设备操作"})
@ApiSort(value = 2)
//@Controller
//@RequestMapping("/view/basedatamanage/eqmanage/BESSbpzController")
public class BESSbpzController {
	private static final Logger log = LoggerFactory.getLogger(BESSbpzController.class);
	@Autowired
	private CrossEquipmentService crossEquipmentService;
	@Autowired
	private LampEquipmentService lampEquipmentService;
	@Autowired
	private EnerEquipmentService enerEquipmentService;
	@Autowired
	private BESSbdyMapper besSbdyMapper;
	@Autowired
	private CrossEquipmentMapper crossEquipmentMapper;
	@Autowired
	private BESSbdyService besSbdyService;
	/**
	 * DDC远程操作(设置时间/重启/远程升级)
	 * @param fIp
	 * @param fPort
	 * @param fIndex
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/operDDCController", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject operDDCController(String fSysName,String fIp,String fPort,int fIndex,String fNodeType,String f_node_attribution) throws UnsupportedEncodingException, RemoteException, ServiceException {
		log.info("#DDC远程操作远程操作");
		log.info("#DDC远程操作远程操作");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		if(f_node_attribution.equals("1")) {
			returnObject = crossEquipmentService.operCrossDDC(fSysName,fIp,fPort, fIndex);
		}else if(f_node_attribution.equals("2")){
			returnObject = lampEquipmentService.operLampDDC(fSysName,fIp,fPort, fIndex);
		}else {
			returnObject = enerEquipmentService.operEnergyCollector(fSysName,fIp,fPort, fIndex);
		}
		return returnObject;
	}

		/* Start add by xiepufeng at 2020年5月18日
	reason 1、原来远程升级和设置时间重启功能耦合在一起了，为了减少耦合每个功能都独立出来
	       2、上位机下位机通信升级
	*/
	/**
	 * 能耗采集器远程升级
	 * @param fSysName
	 * @return
	 */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/remoteUpgradeEdc", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject remoteUpgradeEdc(String fSysName) {
		log.info("#能耗采集器远程升级");
		return enerEquipmentService.remoteUpgradeEdc(fSysName);
	}

	/**
	 *
	 * @Description: DDC远程升级
	 *
	 * @auther: wanghongjie
	 * @date: 10:34 2020/8/14
	 * @param: [fSysName]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/remoteUpgradeDdc", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject remoteUpgradeDdc(String fSysName) {
		log.info("#DDC远程升级");
		return crossEquipmentService.remoteUpgradeDdc(fSysName);
	}
	/**
	 * 设置能耗采集器时间
	 * @param fSysName
	 * @return
	 */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/setTimeEdc", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject setTimeEdc(String fSysName) {
		log.info("#设置能耗采集器时间");
		return enerEquipmentService.setTimeEdc(fSysName);
	}
	/**
	 * 重启能耗采集器
	 * @param fSysName
	 * @return
	 */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/restartEdc", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject restartEdc(String fSysName) {
		log.info("#重启能耗采集器");
		return enerEquipmentService.restartEdc(fSysName);
	}
	/*End add by xiepufeng at 2020年5月18日*/


	/**
	 * 重置DDC
	 * @param fSysName
	 * @param fIp
	 * @param fPort
	 * @param fIndex
	 * @param fNodeType
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/reSetDDC", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject reSetDDC(String fSysName,String fIp,String fPort,int fIndex,String fNodeType,String f_node_attribution) throws UnsupportedEncodingException, RemoteException, ServiceException {
		log.info("#DDC重置");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		//查询该节点的实体表名--add by LvSihan
		String tabName = besSbdyMapper.findNodeTable(fSysName);
		//获取该DDC设备树信息
		Map<String,Object> ddcStructInfo = besSbdyMapper.findSbdyInfoBySelNode(fSysName, "bes_sbpz_struct");
		//开始删除DDC及其子节点
		Map<String, String> tabNameMap = new HashMap<>();//(key:节点类型  value：表名)
		List<BESSbTreeNodeType> nodeTypeInfoList = besSbdyMapper.findChildByTreeNoteType(null);//从数据库获取所有资源
		for (BESSbTreeNodeType besSbTreeNodeType : nodeTypeInfoList)// 将所有节点类型对应的表名放入map
			tabNameMap.put(besSbTreeNodeType.getF_fun_nodetype(), besSbTreeNodeType.getF_node_table());
		Map<String, List<String>> idsMap=new HashMap<>();//(key:节点类型  value：List<系统名称>)
		List<String> f_sys_names=new ArrayList<>();//要删除的所有节点id(f_sys_name)
		getChildNode((String) ddcStructInfo.get("F_SYS_NAME"), f_sys_names, idsMap);//获取节点下所有子节点id(f_sys_name)
		int childCount = 0;
		int childCounts = 0;
		if (f_sys_names.size() > 0) {// 如果含有子节点
			childCount = besSbdyMapper.batchDelete(f_sys_names, "bes_sbpz_struct");// 根据节点id(f_sys_name)批量删除
			for (String nodeType : idsMap.keySet()) {// 根据子节点包含的表名循环删除对应属性信息
				String delTabName = tabNameMap.get(nodeType);
				if (delTabName != null && !("".equals(delTabName)) && !("bes_sbpz_struct".equals(delTabName.toLowerCase())))
					childCounts = besSbdyMapper.batchDelete(idsMap.get(nodeType), delTabName);
			}
		}
		//删除该节点对应的信息
		String delTabName = tabName;
		int delChildCount = 0;
		if (delTabName != null && !("".equals(delTabName)) && !("bes_sbpz_struct".equals(delTabName.toLowerCase()))) {
			delChildCount = besSbdyMapper.delSbTreeBySysName((String) ddcStructInfo.get("F_SYS_NAME"), delTabName);//根据所选节点删除设备树信息
		}
		int delCount = besSbdyMapper.delSbTreeBySysName((String) ddcStructInfo.get("F_SYS_NAME"), "BES_SBPZ_STRUCT");//根据所选节点删除设备树信息
		if(delCount + delChildCount + childCount + childCounts >=1){
			//根据节点类型判断，2属于楼宇Cross，3属于照明lamp
			if(f_node_attribution.equals("1")) {
				returnObject = crossEquipmentService.operCrossDDC(fSysName,fIp,fPort, fIndex);
			}else {
				returnObject = lampEquipmentService.operLampDDC(fSysName,fIp,fPort, fIndex);
			}
			return returnObject;
		}else {
			returnObject.setStatus("0");
			returnObject.setMsg("重置失败");
		}
		return returnObject;
	}
	/**
	 * 根据系统名称获取该节点下的所有子节点
	 * @param f_sys_name 系统名称
	 * @param f_sys_names 系统名称List
	 * @param idsMap
	 */
	private void getChildNode(String f_sys_name, List<String> f_sys_names, Map<String, List<String>> idsMap) {
		List<BESSbPzStruct> childNodeList = besSbdyMapper.getSbChildNodeBySysName(f_sys_name);//检索所选节点是否含有子节点
		List<String> list = null;
		if(childNodeList!=null && !childNodeList.isEmpty()){
			for (BESSbPzStruct besSbPzStruct : childNodeList) {
				if (idsMap.get(besSbPzStruct.getF_type()) == null) {
					list = new ArrayList<>();
					idsMap.put(besSbPzStruct.getF_type(), list);
				} else {
					list = idsMap.get(besSbPzStruct.getF_type());
				}
				list.add(besSbPzStruct.getF_sys_name());
				f_sys_names.add(besSbPzStruct.getF_sys_name());
				getChildNode(besSbPzStruct.getF_sys_name(), f_sys_names, idsMap);
			}
		}
	}


	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/sbpzQueryPollStatus", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject sbpzQueryPollStatus(String fSysName) throws UnsupportedEncodingException, RemoteException, ServiceException {
		log.info("#DDC时候可以轮询");
		ISSPReturnObject isspReturnObject = new ISSPReturnObject();
		BESSbPzStruct besSbPzStruct = new BESSbPzStruct();
		try{
			besSbPzStruct = besSbdyMapper.sbpzQueryPollStatus(fSysName);
			isspReturnObject.setStatus("1");
			if(besSbPzStruct == null){
				besSbPzStruct = new BESSbPzStruct();
			}
			isspReturnObject.setData(besSbPzStruct);
		}catch (Exception e){
			isspReturnObject.setStatus("0");
		}
		return isspReturnObject;
	}

	private void getChildNodeList(String f_sys_name, List<BESSbPzStruct> allChildNodeList) {
		//List<BESSbPzStruct> childNodeList = besSbdyMapper.getSbChildNodeBySysName(f_sys_name);
		/* start remove xiepufeng 2020年3月13日 错误的赋值操作*/
        // allChildNodeList= besSbdyMapper.searchLikeClilder(f_sys_name);
		/*end*/
		/**/
		/* start add xiepufeng 2020年3月13日 对当前集合操作并不是赋值 */
		allChildNodeList.addAll(besSbdyMapper.searchChildren(f_sys_name));
		/*end*/
		//getSbChildNodeBySysName(f_sys_name);
		/*for(BESSbPzStruct childNode : childNodeList) {
			allChildNodeList.add(childNode);
			//getChildNodeList(childNode.getF_sys_name(),allChildNodeList);

		}*/
	}
	/**
	 * 获取DDC时间
	 * @param fIp
	 * @param fPort
	 * @param fIndex
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/getDDCTime", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getDDCTime(String fSysName,String fIp,String fPort,int fIndex,String fNodeType,String f_node_attribution) throws UnsupportedEncodingException, RemoteException, ServiceException {
		log.info("#获取DDC时间");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		//根据节点类型判断，2属于楼宇Cross，3属于照明lamp
		if(f_node_attribution.equals("1")) {
			//获取Lamp照明IP路由器时间，index为6
			returnObject = crossEquipmentService.getCrossDDCTime(fSysName,fIp,fPort, fIndex);
		}else if(f_node_attribution.equals("2")){
			//获取Lamp照明IP路由器时间，index为6
			returnObject = lampEquipmentService.getLampDDCTime(fSysName,fIp,fPort, fIndex);
		}else {
			//获取能耗系统采集器时间，index为5
			returnObject = enerEquipmentService.getEnergyCollectorTime(fSysName,fIp,fPort, fIndex);
		}
		return returnObject;
	}

	/*Start add by xiepufeng at 2020年5月19日 remark 获取能耗采集器时间*/
	/**
	 * 获取能耗采集器时间
	 * @param fSysName
	 * @return
	 */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/getTimeEdc", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getTimeEdc(String fSysName) {
		log.info("#获取能耗采集器时间");
		return enerEquipmentService.getTimeEdc(fSysName);
	}
	/*End add by xiepufeng at 2020年5月19日 remark 获取能耗采集器时间*/

	/*Start add by xiepufeng at 2020年5月19日*/
	/**
	 * 重置能耗采集器
	 * @param fSysName
	 * @return
	 */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/resetEdc", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject resetEdc(String fSysName) {
		log.info("#重置能耗采集器");
		return enerEquipmentService.resetEdc(fSysName);
	}
	/*End add by xiepufeng at 2020年5月19日*/

	/**
	 * 同步DDC数据
	 * @param f_sys_name
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/synDDC", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject synDDC(String f_sys_name,String fNodeType,String f_node_attribution) throws UnsupportedEncodingException, RemoteException, ServiceException {
		log.info("#同步DDC");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		//根据节点类型判断，2属于楼宇Cross，3属于照明lamp
		if(f_node_attribution.equals("1")) {
			//Cross设置DDC控制器的所有参数
			returnObject = crossEquipmentService.synCrossDDC(f_sys_name);
		}else if(f_node_attribution.equals("2")){
			//Lamp设置IP路由器的所有参数
			returnObject = lampEquipmentService.synLampDDC(f_sys_name);
		}else {
			//energy设置采集器的所有参数
			returnObject = enerEquipmentService.synEnergyCollector(f_sys_name);
		}
		return returnObject;
	}

	/**
	 * 获取下位机DDC数据
	 * @param f_guid
	 * @param fIp
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/compareDDC", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject compareDDC(String fIp,String fPort,String fSysName,String f_guid,String fNodeType,String f_node_attribution) throws UnsupportedEncodingException, RemoteException, ServiceException {
		log.info("#获取下位机DDC数据");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		//根据节点类型判断，2属于楼宇Cross，3属于照明lamp
		if (f_node_attribution.equals("1")) {
			//Cross获取DDC控制器的所有配置参数
			returnObject = crossEquipmentService.compareCrossDDC(fSysName,f_guid, fIp, fPort);
		}else if(f_node_attribution.equals("2")){
			//Lamp获取IP路由器的所有配置参数
			returnObject = lampEquipmentService.compareLampDDC(fSysName,f_guid, fIp, fPort);
		}else {
			//能耗获取collector的所有配置参数
			returnObject = enerEquipmentService.compareEnergyCollector(fSysName,f_guid, fIp, fPort);
		}
		return returnObject;
	}

	/*Start add by xiepufeng at 2020年5月21日*/
	/**
	 * 给下位机下发指令，获取控制器参数
	 * @param fSysName
	 * @return
	 */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/getControllerParam", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getControllerParam(String fSysName) {
		log.info("#给下位机下发指令，获取控制器参数");
		return enerEquipmentService.getControllerParam(fSysName);
	}
	/*End add by xiepufeng at 2020年5月21日*/

	/**
	 * 同步模块数据
	 * @param f_sys_name
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/synModule", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject synModule(String f_sys_name,String f_psys_name,String nodeLevel,String f_node_attribution) throws UnsupportedEncodingException, RemoteException, ServiceException {
		log.info("#同步模块/电表");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		//根据节点级数判断，6属于楼宇Cross，4属于照明lamp
		if (f_node_attribution.equals("1")) {
			// Cross设置模块所有信息
			returnObject = crossEquipmentService.synCrossModule(f_sys_name, f_psys_name, f_node_attribution);
		} else if(f_node_attribution.equals("2")){
			// Lamp设置模块所有信息
			returnObject = lampEquipmentService.synLampModule(f_sys_name, f_psys_name, f_node_attribution);
		}else if(f_node_attribution.equals("2")){
			// Lamp设置模块所有信息
			returnObject = crossEquipmentService.synCrossModule(f_sys_name, f_psys_name, "1");
		}
		return returnObject;
	}
/*	*//**
	 * 同步电表数据
	 * @param f_sys_name
	 * @param f_psys_name
	 * @param f_node_attribution
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 * @throws ServiceException
	 *//*
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/synAmmeter", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject synAmmeter(String f_sys_name,String f_psys_name,String f_node_attribution) throws UnsupportedEncodingException, RemoteException, ServiceException {
		log.info("#同步电表");
		//能耗设置电表的所有数据
		ISSPReturnObject returnObject = enerEquipmentService.synEnergyAmmeter(f_sys_name, f_psys_name, f_node_attribution);
		return returnObject;
	}*/
	/**
	 * 同步电表数据
	 * @param f_sys_name
	 * @return
	 */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/synAmmeter", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject synAmmeter(String f_sys_name) {
		log.info("#同步电表");
		ISSPReturnObject returnObject = enerEquipmentService.synAmmeter(f_sys_name);
		return returnObject;
	}

	/**
	 * 获取下位机模块数据
	 * @param f_sys_name
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/compareModule", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject compareModule(String f_sys_name,String f_psys_name,String nodeLevel,String f_node_attribution) throws UnsupportedEncodingException, RemoteException, ServiceException {
		log.info("#获取下位机模块数据");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		//根据节点级数判断，6属于楼宇Cross，4属于照明lamp
		if (f_node_attribution.equals("1")) {
			// Cross获取模块所有信息
			returnObject = crossEquipmentService.compareCrossModule(f_sys_name, f_psys_name,f_node_attribution);
		} else if(f_node_attribution.equals("2")) {
			// Lamp获取模块所有信息
			returnObject = lampEquipmentService.compareLampModule(f_sys_name, f_psys_name,nodeLevel,f_node_attribution,"");
		}
		return returnObject;
	}
	/**
	 * 获取下位机电表数据
	 * @param f_sys_name
	 * @param f_psys_name
	 * @param nodeLevel
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/compareAmmeter", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject compareAmmeter(String f_sys_name,String f_psys_name,String f_node_attribution) throws UnsupportedEncodingException, RemoteException, ServiceException {
		log.info("#获取下位机电表数据");
		//能耗获取电表的所有信息
		ISSPReturnObject returnObject = enerEquipmentService.compareEnergyAmmeter(f_sys_name, f_psys_name,f_node_attribution);
		return returnObject;
	}

	/**
	 * 给下位机下发指令，获取电表参数
	 * @param f_sys_name
	 * @return
	 */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/getAmmeterParam", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getAmmeterParam(String f_sys_name) {
		log.info("#给下位机下发指令，获取电表参数");
		ISSPReturnObject returnObject = enerEquipmentService.getAmmeterParam(f_sys_name);
		return returnObject;
	}

	/**
	 * 获取电表实时数据
	 * @param f_sys_name
	 * @return
	 */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/getAmmeterRealTimeData", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getAmmeterRealTimeData(String f_sys_name) {
		log.info("#获取电表实时数据");
		ISSPReturnObject returnObject = enerEquipmentService.getAmmeterRealTimeData(f_sys_name);
		return returnObject;
	}
	/**
	 * 获取电表历史数据
	 * @param fSysName
	 * @return
	 */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/getAmmeterHistoryData", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getAmmeterHistoryData(String fSysName, Integer selectDay) {
		log.info("#获取电表历史数据");
		ISSPReturnObject returnObject = enerEquipmentService.getAmmeterHistoryData(fSysName, selectDay);
		return returnObject;
	}

	/**
	 * 通过电表id查询对应的采集参数信息
	 * @param f_sys_name
	 * @return
	 */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/queryElectricParamsByAmmeter", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject queryElectricParamsByAmmeter(String f_sys_name) {
		log.info("#电表信息对比");
		ISSPReturnObject returnObject = enerEquipmentService.queryElectricParamsByAmmeter(f_sys_name);
		return returnObject;
	}
	/**
	 * 电能参数信息对比
	 * @param f_sys_name
	 * @return
	 * @throws RemoteException
	 * @throws UnsupportedEncodingException
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/compareEnectric", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject compareEnectric(String f_sys_name) throws UnsupportedEncodingException, RemoteException, ServiceException {
		log.info("#电表电能参数信息对比");
		ISSPReturnObject returnObject = enerEquipmentService.compareEnectric(f_sys_name);
		return returnObject;
	}
	/**
	 * 获取能耗参数数据
	 * @param f_sys_name
	 * @param Selectday
	 * @return
	 * @throws RemoteException
	 * @throws UnsupportedEncodingException
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/queryMeterHisData", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject queryMeterHisData(String f_sys_name,String selectday) throws UnsupportedEncodingException, RemoteException, ServiceException {
		log.info("#获取电能参数数据");
		ISSPReturnObject returnObject = enerEquipmentService.queryMeterHisData(f_sys_name,selectday);
		return returnObject;
	}
	/**
	 * 解析能耗数据
	 * @param ElectricData
	 * @param gridata
	 * @param meteruuid
	 * @param histime
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/queryMeterHisDataGrid", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject queryMeterHisDataGrid(String ElectricData,String gridata,String meteruuid, String histime, String type) throws Exception {
		log.info("#解析电能参数数据");
		ISSPReturnObject returnObject = enerEquipmentService.queryMeterHisDataGrid(ElectricData,gridata,meteruuid,histime,type);
		return returnObject;
	}

	/**
	 * 调试一个逻辑点的初始值值
	 * @param f_sys_name
	 * @param f_guid
	 * @param f_work_mode
	 * @param f_init_val
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	@ApiOperation(value="DO点调试", notes="DO点调试")
	@RequestMapping(value = "/api/debugPointInfo", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject debugPointInfo(@RequestBody JSONObject obj){
		log.info("BESSbdyController.debugPointInfo:设置逻辑点的值");
		return	besSbdyService.sbdy_put_up_point(obj);
	}

	/**
	 * 调试一个逻辑点的初始值值
	 * @param f_sys_name
	 * @param f_guid
	 * @param f_work_mode
	 * @param f_init_val
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 * @throws ServiceException
	 */
//	@ApiOperation(value="DO点调试", notes="DO点调试")
	@RequestMapping(value = "/api/debugPointInfoList", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject debugPointInfoList(@RequestBody JSONObject obj){
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("BESSbdyController.debugPointInfo:设置逻辑点的值");
		//组装数据
		Map<String, Object> map = JSONObject.toJavaObject(obj, Map.class);
		String JSONObjectStr = map.get("list").toString().replace("=",":");
		List<Map<String,String>> list = JSONArray.parseObject(JSONObjectStr,List.class);
		//遍历下发
		for(Map map2 : list){
			for (Object key : map2.keySet()) {
				String JSONObjectStr1= "{\r\n\"f_init_val\":\"" + map2.get(key) + "\",\r\n\"f_work_mode\":\"1\",\r\n\"f_sys_name\":\"" + key + "\"}";
				JSONObject jsonObject = JSONObject.parseObject(JSONObjectStr1);
				ISSPReturnObject returnObject1 = besSbdyService.sbdy_put_up_point(jsonObject);
				if("0".equals(returnObject1.getStatus())){
					returnObject.setStatus("0");
					returnObject.setMsg("下发过程出现失败——>Key-"+key+":value-"+map2.get(key));
					return returnObject;
				}
			}
		}
		returnObject.setStatus("1");
		returnObject.setMsg("下发成功");
		return returnObject;
	}


	/**
	 *多场景按钮调试
	 * @return
	 */
	@RequestMapping(value = "/api/debugScenePointInfo", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject debugScenePointInfo(@RequestBody List<List> sysNameList) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		String fInitVal = "";
		if(sysNameList.size()>0){
			for(int i = 0;i<sysNameList.size();i++){
				List<String>pointInfoList = sysNameList.get(i);
				JSONObject obj = new JSONObject();
				if(pointInfoList!=null&&pointInfoList.size()>0){
						obj.put("f_sys_name",pointInfoList.get(0));//系统名称
						if("0".equals(pointInfoList.get(1))){
							fInitVal = "255";
						}else{
							fInitVal = "0";
						}
						obj.put("f_init_val",fInitVal);//初始值
					    obj.put("f_work_mode",pointInfoList.get(2));//工作模式
					    obj.put("f_node_attribution",pointInfoList.get(3));//节点级数
					//debugPointInfoxd(obj)是虚点的方法
					    debugPointInfo(obj);
				}

			}
		}

		return null;
	}

	/**
     * 调试一个逻辑点的初始值值
     * @param f_sys_name
     * @param f_guid
     * @param f_work_mode
     * @param f_init_val
     * @return
     * @throws UnsupportedEncodingException
     * @throws RemoteException
     * @throws ServiceException
     */
    @ApiOperation(value="DO点调试", notes="DO点调试")
    @RequestMapping(value = "/api/debugPointInfoxd", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject debugPointInfoxd(@RequestBody JSONObject obj)
            throws UnsupportedEncodingException, RemoteException, ServiceException {
        log.info("#调试逻辑点");
        ISSPReturnObject returnObject = new ISSPReturnObject();
        // 根据逻辑点节点级数判断，7属于楼宇Cross，5属于照明lamp
        if (obj.getString("f_node_attribution").equals("1")) {
            // Cross调试逻辑点数据
            returnObject = crossEquipmentService.debugCrossPointInfoxd(obj);
        } else {
            // Lamp调试逻辑点数据
            returnObject = lampEquipmentService.debugLampPointList(obj);
        }
        return returnObject;
    }

	/**
	 * 同步逻辑点数据
	 * @param f_sys_name
	 * @param f_psys_name
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/synPoint", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject synPoint(String f_sys_name,String f_psys_name,String f_node_attribution) throws UnsupportedEncodingException, RemoteException, ServiceException {
		log.info("#同步逻辑点");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		//根据逻辑点节点级数判断，7属于楼宇Cross，5属于照明lamp
		if (f_node_attribution.equals("1")) {
			// Cross同步逻辑点数据
			returnObject = crossEquipmentService.synCrossPoint(f_sys_name, f_psys_name);
		}else {
			// Lamp同步逻辑点数据
			returnObject = lampEquipmentService.synLampPoint(f_sys_name, f_psys_name);
		}
		return returnObject;
	}


	/**
	 * 调试开关按钮
	 * @param obj
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/debugPointList", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject debugPointList(@RequestBody JSONObject obj)
			throws UnsupportedEncodingException, RemoteException, ServiceException {
		log.info("#调试开关按钮");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		returnObject = lampEquipmentService.debugLampPointList(obj);
		return returnObject;
	}
	/**
	 * 设置逻辑点的值（通过点名字）
	 * @param obj
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/setPointInfo", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject setPointInfo(@RequestBody JSONObject obj)
			throws UnsupportedEncodingException, RemoteException, ServiceException {
		log.info("#设置逻辑点的值");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		//通过逻辑点所在模块父节点的类型，判断属于什么系统
		String modulePType = besSbdyMapper.queryPointModPtypeByfSysname((String) obj.get("f_sys_name"));
		//23属于楼宇Cross，3属于照明lamp
		if(modulePType != "" && modulePType != null && modulePType.equals("23")) {
			returnObject = crossEquipmentService.setCrossPointInfo(obj);
		} else {
			// Lamp调试逻辑点数据
			returnObject = lampEquipmentService.setLampPointInfo(obj);
		}
		return returnObject;
	}
	/**
	 * 获取数据库中逻辑点的数据
	 * @param f_sys_name
	 * @param f_psys_name
	 * @return
	 */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/getPointInfo", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getPointInfo(String f_sys_name,String f_psys_name) {
		log.info("#获取数据库中逻辑点的参数");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		returnObject = lampEquipmentService.getPointInfo(f_sys_name, f_psys_name);
		return returnObject;
	}
	/**
	 * 获取数据库中逻辑点的数据
	 * @param f_sys_name
	 * @param f_psys_name
	 * @return
	 */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/getVirtualPointInfo", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getVirtualPointInfo(String f_sys_name) {
		log.info("#获取数据库中虚点的参数");
		return lampEquipmentService.getVirtualPointInfo(f_sys_name);
	}
	/*
	 *
	 * @Description: 判断当前系统名称在相应的四种点位表里面有没有,有的话将f_sys_name输入框设置成不能输入
	 * 
	 * @auther: wanghongjie
	 * @date: 16:10 2020/4/11
	 * @param: [f_sys_name, f_psys_name]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/info_f_sys_name", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject info_f_sys_name(String f_sys_name,String tabName) {
		log.info("#获取数据库中逻辑点的参数");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		returnObject = lampEquipmentService.getInfo_f_sys_name(f_sys_name, tabName);
		return returnObject;
	}
	/**
	* 王红杰
	* */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/getPointInfo1", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getPointInfo1(String f_sys_name,String f_guid,String f_id,String f_node_type) {
		log.info("#获取数据库中逻辑点的参数");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		returnObject = lampEquipmentService.getPointInfo1(f_sys_name,f_guid,f_id,f_node_type);
		return returnObject;
	}

	/**
	 * 王红杰
	 * */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/getPointInfoAI", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getPointInfoAI(String f_sys_name,String f_sys_name_old) {
		log.info("#获取数据库中逻辑点的参数");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		returnObject = lampEquipmentService.getPointInfoAI(f_sys_name,f_sys_name_old);
		return returnObject;
	}

	/**
	 * 王红杰  根据系统名称查询树节点的名称
	 * */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/getPointInfoSBDY", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getPointInfoAI(String f_sys_name) {
		log.info("#获取数据库中逻辑点的参数");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		returnObject = lampEquipmentService.getPointInfoSBDY(f_sys_name);
		return returnObject;
	}
	/**
	 * 逻辑点数据对比
	 * @param f_sys_name
	 * @param f_psys_name
	 * @param f_guid
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/comparePoint", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject comparePoint(String f_sys_name,String f_psys_name,String f_sbid,String f_node_attribution,String f_flag) throws UnsupportedEncodingException, RemoteException, ServiceException {
		log.info("#获取下位机逻辑点的参数");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		//根据逻辑点节点级数判断，7属于楼宇Cross，5属于照明lamp
		if ("1".equals(f_node_attribution)) {
			// Cross获取逻辑点数据
			returnObject = crossEquipmentService.compareCrossPoint(f_sys_name, f_psys_name, f_sbid,f_flag);
		} else {
			// Lamp获取逻辑点数据
			returnObject = lampEquipmentService.compareLampPoint(f_sys_name, f_psys_name, f_sbid,f_flag);
		}
		return returnObject;
	}

	/**
	 * 虚点数据对比
	 * @param f_sys_name
	 * @return
	 */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/compareVirtualPoint", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject compareVirtualPoint(String f_sys_name,String f_psys_name,String f_sbid,String f_node_attribution,String f_flag) {
		log.info("#获取下位机逻辑点的参数");
		return crossEquipmentService.compareVirtualPoint(f_sys_name);
	}

	/**
	 * 删除逻辑点（保留设备树信息，只删除对应属性表）
	 * @param f_sys_name
	 * @param f_psys_name
	 * @param f_guid
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/reSetPoint", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject reSetPoint(String f_sys_name,String f_psys_name,String f_sbid,String f_node_attribution) throws UnsupportedEncodingException, RemoteException, ServiceException {
		log.info("#获取逻辑点的值");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> pointMap = new HashMap<>();
		if(f_sbid == "") {
			String tabName = besSbdyMapper.findNodeTable(f_sys_name);
			// 查询该逻辑虚点属性信息
			pointMap = crossEquipmentMapper.queryPointInfo(tabName, f_sys_name);
			f_sbid = (String) pointMap.get("F_SBID");

		}
		//根据逻辑点节点级数判断，7属于楼宇Cross，5属于照明lamp
		if (f_node_attribution.equals("1")) {
			returnObject = crossEquipmentService.reSetPoint(f_sys_name,f_psys_name,f_sbid,pointMap);
		}else {
			returnObject = lampEquipmentService.reSetLampPoint(f_sys_name,f_psys_name,f_sbid);
		}
		return returnObject;
	}
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/importControlInfo", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject importControlInfo(@RequestBody JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			JSONArray sysNameList = obj.getJSONArray("f_sys_name");
			JSONArray buttonNameList = obj.getJSONArray("f_button_name");
			String nodePage = obj.getString("nodePage");//所属页面
			//先删除
			besSbdyMapper.delControlInfo(nodePage);
			//再导入
			for(int i=0;i<sysNameList.size();i++) {
				JSONObject sysName = sysNameList.getJSONObject(i);
				JSONObject buttonName = buttonNameList.getJSONObject(i);
				String fSysnames = sysName.getString("f_sys_name");//系统名称
				String[] f_sys_name  = fSysnames.split(",");
				String f_button_type = "";//按钮类型
				if(f_sys_name.length>1) {
					f_button_type = "multiple";
				}else if(f_sys_name.length == 1){
					f_button_type = "single";
				}
				String fId = UUIDUtil.getRandom32PK();//ID

				besSbdyMapper.importControlInfo(fId,fSysnames,buttonName.getString("f_button_name"),nodePage,f_button_type);
			}
			returnObject.setStatus("1");
			returnObject.setMsg("成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnObject.setStatus("0");
			returnObject.setMsg("失败");
		}
		return returnObject;
	}
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/getControlInfo", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getControlInfo(String nodePage) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			int count = besSbdyMapper.getControlInfo(nodePage);
			if(count>0) {
				returnObject.setStatus("1");
				returnObject.setMsg("成功");
			}else {
				returnObject.setStatus("0");
				returnObject.setMsg("成功");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnObject.setStatus("0");
			returnObject.setMsg("失败");
		}
		return returnObject;
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

	/*
	 *
	 * @Description: 获取相应的点位的f_node_attribution
	 *
	 * @auther: wanghongjie
	 * @date: 10:53 2020/4/15
	 * @param: [f_sys_name, f_guid, f_id, f_node_type]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/view/basedatamanage/eqmanage/BESSbpzController/f_node_attribution", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject f_node_attribution(String f_sys_name,String tabname) {
		log.info("#获取数据库中逻辑点的参数");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		returnObject = lampEquipmentService.f_node_attribution(f_sys_name,tabname);
		return returnObject;
	}
}
