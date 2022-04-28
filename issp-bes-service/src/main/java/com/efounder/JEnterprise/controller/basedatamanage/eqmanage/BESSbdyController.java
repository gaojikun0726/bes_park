package com.efounder.JEnterprise.controller.basedatamanage.eqmanage;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.core.common.constant.BesNodeType;
import com.efounder.JEnterprise.initializer.*;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.*;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.*;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionPointModel;
import com.efounder.JEnterprise.platform.service.AssetsInfoService;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BESSbdyService;
import com.efounder.JEnterprise.service.systemcenter.interfaceconfig.DeviceFunctionPointService;
import com.efounder.JEnterprise.util.DDCrelevant;
import com.efounder.opc.OpcData;
import com.efounder.opc.OpcData_cross;
import org.ace.business.constant.PointType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;
import java.io.*;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author huangxianbo 设备树定义
 *
 */
@Controller
@RequestMapping(value = "/view/basedatamanage/eqmanage")
public class BESSbdyController {
	private static final Logger log = LoggerFactory.getLogger(BESSbdyController.class);

	@Autowired
	private BESSbdyService besSbdyService;
	@Autowired
	private OpcData opcData;

	@Autowired
	private DDCrelevant ddCrelevant;

	@Autowired
	private BESSbdyMapper besSbdyMapper;

	@Autowired
	private CrossEquipmentMapper crossEquipmentMapper;

		@Resource
	private AssetsInfoService assetsInfoService;

	@Resource
	private BesCollectorMapper besCollectorMapper;

	@Autowired
	private SbPzStructCache sbPzStructCache;

	@Autowired
	private CollectorCache collectorCache;

	@Autowired
	private DdcCache ddcCache;

	@Autowired
	private AmmeterCache ammeterCache;

	@Autowired
	private VirtualPointCache virtualPointCache;

	@Autowired
	private AiPointCache aiPointCache;

	@Autowired
	private AoPointCache aoPointCache;

	@Autowired
	private DiPointCache diPointCache;

	@Autowired
	private DoPointCache doPointCache;

	@Autowired
	private DeviceFunctionPointCache deviceFunctionPointCache;

	@Autowired
	private DeviceFunctionPointService deviceFunctionPointService;

	/**
	 * opc数据开关
	 */
	@Value("${system.parameter.opcuseable}")
    private int opcuseable;
	/**
	 * 获取设备定树结构信息
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/sbdy", method = RequestMethod.GET)
	public String getSbdy(ModelMap map) {
		log.info("#BESSbdyController获取设备树结构信息");

		/*begin remove xiepufeng 未使用代码*/
		/*PageInfo<BESSbPzStruct> page = besSbdyService.findSbdyInfo(null, null);
		map.put("page", page);*/
		/*end remove xiepufeng */



		return "besview/basedatamanage/eqmanage/eqconfiguration/sbdy";
	}

	/**
	 * 生成设备定义树
	 * @return
	 */
	@RequestMapping(value = "/sbdy_tree", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getTree(Map<String, String> map) {
		log.info("BESSbdyController生成设备定义树");
		ISSPReturnObject returnObject = besSbdyService.getTree();
		return returnObject;
	}

	/**
	 * 生成设备定义树
	 * @return
	 */
	@RequestMapping(value = "/sbdy_tree_lazyLoad", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getTreeLazyLoad(String pSysName) {
		log.info("BESSbdyController获取设备结构树数据（懒加载）");
		return besSbdyService.getTreeLazyLoad(pSysName);
	}

	/**
	 * 刷新设备树缓存
	 * @return
	 */
	@RequestMapping(value = "/sbdy_tree_reload", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getTreeReload(String pSysName) {
		log.info("BESSbdyController刷新设备树缓存");
		return besSbdyService.getTreeReload(pSysName);
	}

	/**
	 * 适用于Ztree设备树，从缓存中读取设备树
	 * @return
	 */
	@RequestMapping(value = "/getTreeFormat", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getTreeFormat() {
		log.info("BESSbdyController缓存读取设备定义树");
		ISSPReturnObject returnObject = besSbdyService.getTreeFormat();
		return returnObject;
	}

	/**
	 * 生成设备定义树（未格式化）
	 * @return
	 */
	@RequestMapping(value = "/austere_devices_tree", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getAustereTree(Map<String, String> map) {
		log.info("BESSbdyController生成设备定义树");
		ISSPReturnObject returnObject = besSbdyService.getAustereTree();
		return returnObject;
	}

	/**
	 * 加载设备树节点类型信息
	 *
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/sbdy_noteTypeInfos", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getSbTreeNodeTypeInfo(Map<String, String> map) {
		log.info("BESSbdyController加载设备树节点类型信息");
		ISSPReturnObject returnObject = besSbdyService.findChildByTreeNoteTypes();// 从数据库获取所有资源

		return returnObject;
	}

	/**
	 * 初始化编程页面
	 *
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/sbdy_ddcInfoBc", method = RequestMethod.POST)
	public String initddcInfoBc(ModelMap map,String f_sys_name,String f_id) {
		log.info("BESSbdyController加载页面ddc编程页面");
		// 获取设备树信息
		BESSbPzStruct besSbPzStruct = besSbdyService.getSbTreeAndTxtInfoBySysNameAndId(f_sys_name,f_id);
		map.put("besSbPzStruct", besSbPzStruct);
		return "besview/basedatamanage/eqmanage/eqconfiguration/sbdy_ddcbc";
	}

	/**
	 * 根据树所选节点查询设备树节点类型信息（维护url、实体表名、多级菜单等）
	 *
	 * @param f_type
	 * @return
	 */
	@RequestMapping(value = "/sbdy_noteInfoByNoteType", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getChildData(String f_type) {
		ISSPReturnObject returnObject = besSbdyService.findChildByTreeNoteType(f_type);

		return returnObject;
	}
	/**
	 * 根据类型查询对应名称
	 * @param f_type
	 * @return
	 * tianjiangwei
	 */
	@RequestMapping(value = "/noteNameByNoteType", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject noteNameByNoteType(String f_type) {
		ISSPReturnObject returnObject = besSbdyService.noteNameByNoteType(f_type);

		return returnObject;
	}

	/**
	 * 选中节点后根据系统名称获取设备树属性信息
	 *
	 * @param variableUrl 维护url
	 * @param f_sys_name 系统名称
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/{variableUrl}", method = RequestMethod.POST)
	public String getAttrInfo(@PathVariable String variableUrl, String f_sys_name, String f_psys_name, String nodeTabName, ModelMap map,String f_type) {
		log.info("BESSbdyController根据系统名称获取设备树属性信息");
		//判断f_type 类型
		if("26".equals(f_type)||"28".equals(f_type)) {
			map.put("f_xh_type", "3");
		}
		// 获取设备树信息
		BESSbPzStruct besSbPzStruct = besSbdyService.getSbTreeInfoBySysName(f_sys_name);
		map.put("besSbPzStruct", besSbPzStruct);
		// 获取所选节点获取对应的属性信息
		ISSPReturnObject returnObject = besSbdyService.findSbdyInfoBySelNode(f_sys_name, nodeTabName,f_type, f_psys_name);
		map.put("returnObject", returnObject);

		return "/besview/basedatamanage/eqmanage/eqconfiguration/" + variableUrl;
	}

	/**
	 * 点击DDC下面的模块的不同AO、AI、DO、DI点，进入不同页面时，查看不同表数据验证是否已同步，同步后则相应的表中有数据
	 * @param f_sys_name
	 * @return
	 */
//	@RequestMapping(value = "/getdubugNodeInfo/{variableUrl}", method = RequestMethod.POST)
	@RequestMapping(value = "/getModuleNodeInfoWhenEntryPage",method = RequestMethod.POST)
	@ResponseBody
	public int getModuleNodeInfoWhenEntryPage(String f_sys_name,String tableName) {
		log.info("BESSbdyController.getModuleNodeInfoWhenEntryPage:根据系统名称查询验证是否数据已同步");
		//ISSPReturnObject returnObject = besSbdyService.getdebugNodeInfo(f_sys_name);
		int returnCount = besSbdyService.getModuleNodeInfoWhenEntryPage(f_sys_name,tableName);
		//return "/besview/basedatamanage/eqmanage/eqconfiguration/" + variableUrl;
		return returnCount;
	}

	/**
	 * 点击调试按钮后，加载对应的调试页面
	 * @param f_sys_name
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/getdubugNodeInfo/{variableUrl}", method = RequestMethod.POST)
	public String getdubugNodeInfo(String f_sys_name,@PathVariable String variableUrl, ModelMap map) {
		log.info("BESSbdyController根据系统名称获取逻辑点调试信息");
		ISSPReturnObject returnObject = besSbdyService.getdebugNodeInfo(f_sys_name);
		map.put("returnObject", returnObject);

		return "/besview/basedatamanage/eqmanage/eqconfiguration/" + variableUrl;
	}

	/**
	 * 模块属性信息中模块型号下拉列表获取模块类型信息
	 * @return
	 */
	@RequestMapping(value = "/sbdy_getModuleTypeInfo", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getModuleTypeInfo() {
		ISSPReturnObject returnObject = besSbdyService.getModuleTypeInfo();
		return returnObject;
	}

	/**
	 * 虚点属性信息中虚点类型下拉列表获取虚点类型信息
	 * @return
	 */
	@RequestMapping(value = "/sbdy_getVpointTypeInfo", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getVpointTypeInfo() {
		ISSPReturnObject returnObject = besSbdyService.getVpointTypeInfo();
		return returnObject;
	}

	/**
	 * 获取根据模块型号下拉列表添加子节点时所需信息
	 * @return
	 */
	@RequestMapping(value = "/sbdy_getModuleTypeAddChidNodeInfo", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getModuleTypeAddChidNodeInfo() {
		ISSPReturnObject returnObject = besSbdyService.getModuleTypeAddChidNodeInfo();

		return returnObject;
	}

	/**
	 * 根据所选节点更新对应的属性信息
	 * @param obj 更新的对象
	 * @return
	 */
	@RequestMapping(value = "/sbdy_Info_editBySelNode", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject editSbdyInfoBySelByNode(@RequestBody JSONObject obj) {
		ISSPReturnObject returnObject = besSbdyService.editSbdyInfoBySelByNode(obj);// 根据页面信息更新
//		if(opcuseable == 1) {
//			//OPC数据表更新该条数据
//			opcData.editOpcData(obj.get("f_sys_name").toString());
//		}
		/*添加到缓存设备结构树 start*/

		if ("1".equals(returnObject.getStatus()))
		{
			BESSbPzStruct sbPzStruct = besSbdyMapper.sbpzQueryPollStatus(obj.getString("f_sys_name"));

			if(sbPzStruct != null)
			{
				sbPzStructCache.updateOneSbPzStructCache(sbPzStruct);
			}
		}

		/*添加到缓存设备结构树 end*/


		return returnObject;
	}


	/**
	 * 更新能耗采集器总线
	 * @param obj 更新的对象
	 * @return
	 */
	@RequestMapping(value = "/updateEnergyBus", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject updateEnergyBus(@RequestBody JSONObject obj) {
		ISSPReturnObject returnObject = besSbdyService.updateEnergyBus(obj);

		/*更新到缓存设备结构树 start*/

		if ("1".equals(returnObject.getStatus()))
		{
			BESSbPzStruct sbPzStruct = besSbdyMapper.sbpzQueryPollStatus(obj.getString("f_sys_name"));

			if(sbPzStruct != null)
			{
				sbPzStructCache.updateOneSbPzStructCache(sbPzStruct);
			}
		}

		/*更新到缓存设备结构树 end*/
		return returnObject;
	}

	/**
	 * 更新电表信息
	 * @param obj 更新的对象
	 * @return
	 */
	@RequestMapping(value = "/updateAmmeter", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject updateAmmeter(@RequestBody JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		String assetsCode = obj.getString("assets_code");
		String fGuid = obj.getString("f_guid");
//		boolean result = assetsInfoService.verifyAssetsCode(assetsCode,fGuid);
//		if(result){
			returnObject = besSbdyService.updateAmmeter(obj);// 根据页面信息更新
//		}else{
//			returnObject.setStatus("0");
//			returnObject.setMsg("资产编码不可用或重复");
//		}
		return returnObject;
	}

	//Start add by yangjx at 20191212 for 取消页面sbdy_vpoint进行应用时调用上方方法editSbdyInfoBySelByNode()。解决当数据由有变为空时上方通用方法不可行的问题
	/**
	 * 更新BES_VPOINT虚点信息，由editSbdyInfoBySelByNode()方法改进
	 * @param obj 更新的对象
	 * @return
	 */
	@RequestMapping(value = "/sbdy_Info_updateTableBesVpoint", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject updateTableBesVpoint(@RequestBody JSONObject obj) {
		ISSPReturnObject returnObject = besSbdyService.updateTableBesVpoint(obj);// 根据页面信息更新
		return returnObject;
	}
	//End add by yangjx at 20191212 for 取消页面sbdy_vpoint进行应用时调用上方方法editSbdyInfoBySelByNode()。解决当数据由有变为空时上方通用方法不可行的问题


	/**
	 * 根据所选节点更新对应的属性信息
	 * @param obj 更新的对象
	 * @return
	 */
	@RequestMapping(value = "/sbdy_Info_editCollector", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject editSbdyInfoCollector(@RequestBody JSONObject obj) {
		ISSPReturnObject returnObject = besSbdyService.editSbdyInfoCollector(obj);// 根据页面信息更新

		if ("1".equals(returnObject.getStatus()))
		{
			/*更新到缓存设备结构树 start*/
			BESSbPzStruct sbPzStruct = besSbdyMapper.sbpzQueryPollStatus(obj.getString("f_sys_name"));

			if(sbPzStruct != null)
			{
				sbPzStruct.setF_status(sbPzStructCache.getCachedElement(sbPzStruct.getF_sys_name()).getF_status());
				sbPzStructCache.updateOneSbPzStructCache(sbPzStruct);
			}
			/*更新到缓存设备结构树 end*/

			/*更新采集器缓存 start*/
			BesCollector besCollector = besCollectorMapper.selectBySysName(obj.getString("f_sys_name"));

			if(besCollector != null)
			{
				besCollector.setfOnlineState(collectorCache.getCachedElement(besCollector.getfSysName()).getfOnlineState());
				collectorCache.updateOneCollectorCache(besCollector);
			}
			/*更新采集器缓存 end*/
		}



		return returnObject;
	}

	/**
	 * 更新添加默认节点的属性信息
	 * @param obj 更新的对象
	 * @return
	 * @throws ServiceException
	 * @throws RemoteException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/sbdy_editDefaultNodeInfo", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject editDefaultNodeInfo(@RequestBody JSONObject obj) throws UnsupportedEncodingException, RemoteException, ServiceException {
		// 根据页面信息更新
		ISSPReturnObject returnObject = besSbdyService.editDefaultNodeInfo(obj);
//		if(opcuseable == 1) {
//			//添加到opc数据表
//			opcData_cross.addOpcData(obj.get("f_sys_name").toString());
//		}

		return returnObject;
	}

	/**
	 * 获取设备树最新状态
	 * @return
	 * @throws ServiceException
	 * @throws RemoteException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/sbdy_treeIconRefresh", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getTreeUpdatedInfo() throws UnsupportedEncodingException, RemoteException, ServiceException {
		log.info("BESSbdyController获取设备树最新状态");
		ISSPReturnObject returnObject = besSbdyService.loadAll();// 从数据库获取所有资源
		return returnObject;
	}
	/**
	 * 获取所有DO点
	 * @return
	 * @throws ServiceException
	 * @throws RemoteException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getAllDOInfo", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getAllDOInfo() throws UnsupportedEncodingException, RemoteException, ServiceException {
		log.info("BESSbdyController获取设备树最新状态");
		ISSPReturnObject returnObject = besSbdyService.getAllDOInfo();// 从数据库获取所有资源
		return returnObject;
	}
	/**
	 * 获取所有逻辑点信息
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/getAllPointInfo", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getAllPointInfo() throws UnsupportedEncodingException, RemoteException, ServiceException {
		log.info("BESSbdyController获取设备树最新状态");
		ISSPReturnObject returnObject = besSbdyService.getAllPointInfo();// 从数据库获取所有资源
		return returnObject;
	}

	/**
	 * 获取所有逻辑点信息
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/getAllPointInfoBySysName", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getAllPointInfoBySysName(String sysName) throws UnsupportedEncodingException, RemoteException, ServiceException {
		log.info("BESSbdyController获取设备树最新状态");
		ISSPReturnObject returnObject = besSbdyService.getAllPointInfo(sysName);// 从数据库获取所有资源
		return returnObject;
	}
	/*Start delete by xiepufeng at 2020年3月13日 remark 未使用代码*/
/*
	*//**
	 * 获取温控器逻辑点信息
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 * @throws ServiceException
	 *//*
	@RequestMapping(value = "/getWkjkPointInfoBySysName", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getPointInfoBySysName(String sysName) throws UnsupportedEncodingException, RemoteException, ServiceException {
		log.info("BESSbdyController获取设备树最新状态");
		ISSPReturnObject returnObject = besSbdyService.getWkjkPointInfo(sysName);// 从数据库获取所有资源
		return returnObject;
	}*/
	/*End delete by xiepufeng at 2020年3月13日*/
	/**
	 * 根据系统名称获取树和关联的点信息（没有参数则获取全部树关联的全部数据）
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/sbdy_treeIconForJK", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject sbdy_treeIconForJK(String[] f_sys_name) throws UnsupportedEncodingException, RemoteException, ServiceException {
		log.info("BESSbdyController获取设备树最新状态");
		List<String> f_sys_names = null;
		if (null != f_sys_name)
		{
			f_sys_names = Arrays.asList(f_sys_name);
		}
		ISSPReturnObject returnObject = besSbdyService.loadAllForJK(f_sys_names);// 从数据库获取所有资源
		return returnObject;
	}

	/**
	 * 获取ddc实时数据(实时监控页面)
	 * @param sysName
	 * @return
	 */
	@RequestMapping(value = "/getRvscanData", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getRvscanData(String sysName){
		log.info("BESSbdyController获取 ddc 实时数据");
		ISSPReturnObject returnObject = besSbdyService.getRvscanData(sysName);
		return returnObject;
	}

	@RequestMapping(value = "/sbdy_treeIconForJKById", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject sbdy_treeIconForJKById(String sysName) throws UnsupportedEncodingException, RemoteException, ServiceException {
		log.info("BESSbdyController获取设备树最新状态");
		ISSPReturnObject returnObject = besSbdyService.loadAllForJK(sysName);// 从数据库获取所有资源
		return returnObject;
	}


	/**
	* @Description: 特定id筛选
	* @return returnObject
	* @author YangChao
	* @date 2019/8/21 14:28
	*/
	@RequestMapping(value = "/SbdyTreeIconForgivenJK", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject SbdyTreeIconForgivenJK(HttpServletRequest request) throws UnsupportedEncodingException, RemoteException, ServiceException {
		log.info("BESSbdyController获取设备树最新状态");
		ISSPReturnObject returnObject = besSbdyService.loadAllForgivenJK(request);// 从数据库获取所有资源
		return returnObject;
	}
	/**
	 * 根据所选节点弹出菜单添加设备树信息
	 * @param obj 要添加的属性信息
	 * @return
	 * @throws ServiceException
	 * @throws RemoteException
	 * @throws UnsupportedEncodingException
	 */
//	@RequestMapping(value = "/sbdy_Info_addByTreeBtn", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject addSbdyInfoByTreeBtn(@RequestBody JSONObject obj) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = besSbdyService.addSbdyInfoByTreeBtn(obj);
//		if(opcuseable == 1) {
//			//添加到opc数据表
//			opcData.addOpcData(obj.get("f_sys_name").toString());
//		}
		return returnObject;
	}



    /**
     * 根据所选节点弹出菜单添加设备树信息
     * @param obj 要添加的属性信息
     * @return
     * @throws ServiceException
     * @throws RemoteException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/sbdy_Info_addByTreeBtn", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject newaddSbdyInfoByTreeBtn(@RequestBody JSONObject obj) throws UnsupportedEncodingException, RemoteException, ServiceException {
        ISSPReturnObject returnObject ;
        if (obj.get("f_type").toString().equals("2")){
			returnObject = ddCrelevant.judgeDDC(obj,besSbdyService.addSbdyInfoByTreeBtn(obj));
        }else if (obj.get("f_type").toString().equals("23")){
			returnObject = ddCrelevant.judge(obj);
		}else if (obj.get("f_type").toString().equals("40")){//wanghongjie start 20200620 批量添加线路
			returnObject = ddCrelevant.addzongxia(obj);
		}else {
            returnObject = besSbdyService.addSbdyInfoByTreeBtn(obj);
        }

		/*添加到缓存设备结构树 start*/

		if ("1".equals(returnObject.getStatus()))
		{
			BESSbPzStruct sbPzStruct = besSbdyMapper.sbpzQueryPollStatus(obj.getString("f_sys_name"));

			if(sbPzStruct != null)
			{
				sbPzStructCache.addOneSbPzStructCache(sbPzStruct);
			}
		}

		/*添加到缓存设备结构树 end*/

        return returnObject;
    }


	/**
	 * 新增电表
	 * @param obj
	 * @return
	 */
	@RequestMapping(value = "/sbdy_Info_addAmmeter", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject addAmmeter(@RequestBody JSONObject obj) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		String assetsCode = obj.getString("assets_code");
		String fGuid = obj.getString("f_guid");
//		boolean result = assetsInfoService.verifyAssetsCode(assetsCode,fGuid);
//		if(result){
			returnObject = besSbdyService.addAmmeter(obj);
//		}else{
//			returnObject.setStatus("0");
//			returnObject.setMsg("资产编码不可用或重复");
//		}
		return returnObject;
	}

	/**
	 * 新增采集器
	 * @param obj 要添加的属性信息
	 * @return
	 */
	@RequestMapping(value = "/sbdy_Info_addCollector", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject addCollector(@RequestBody JSONObject obj) {
		ISSPReturnObject returnObject = besSbdyService.addCollector(obj);
//		if(opcuseable == 1) {
//			//添加到opc数据表
//			opcData.addOpcData(obj.get("f_sys_name").toString());
//		}

		return returnObject;
	}

	/**
	 * 新增虚点
	 * @param obj 要添加的属性信息
	 * @return
	 */
	@RequestMapping(value = "/sbdy_Info_addVirtualPoint", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject addVirtualPoint(@RequestBody JSONObject obj) {
		ISSPReturnObject returnObject = besSbdyService.addVirtualPoint(obj);

		return returnObject;
	}

	/**
	 * 更新虚点信息
	 * @param obj 要添加的属性信息
	 * @return
	 */
	@RequestMapping(value = "/sbdy_Info_updateVirtualPoint", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject editVirtualPoint(@RequestBody JSONObject obj) {
		ISSPReturnObject returnObject = besSbdyService.updateVirtualPoint(obj);

		return returnObject;
	}

	/**
	 * 同步虚点信息
	 * @param f_sys_name
	 * @return
	 */
	@RequestMapping(value = "/synVirtualPoint", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject synVirtualPoint(String f_sys_name) {
		log.info("#同步虚点信息");
		ISSPReturnObject returnObject = besSbdyService.synVirtualPoint(f_sys_name);
		return returnObject;
	}

	/**
	 * 获取 opc key
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/getOpcKeyAll", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getOpcKeyAll() {
		ISSPReturnObject returnObject = besSbdyService.getOpcKeyAll();
		return returnObject;
	}

	/**
	 * 删除设备树信息
	 * @param obj
	 * @return
	 * @throws RemoteException
	 * @throws UnsupportedEncodingException
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/sbtree_delBySelNode", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject delSbTreeBySelNode(@RequestBody JSONObject obj) throws UnsupportedEncodingException, RemoteException, ServiceException {
		//TODO 照明区域删除异常

		log.info("#BESSbdyController删除设备树信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();

		//查询当前节点的类型
		BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElementBySysNameOld(obj.getString("f_sys_name"));

		if (!obj.getString("fNodeType").equals(besSbPzStruct.getF_type())) {
			obj.put("fNodeType",besSbPzStruct.getF_type());
		}

		//判断是否可删除
		ISSPReturnObject returnDelEnable = besSbdyService.delEnable(obj);
		if(returnDelEnable.getStatus().equals("1")) {
			if(opcuseable == 1) {
				//在opc数据表中删除
				opcData.delOpcData(obj.get("f_sys_name").toString());
			}
			//ISSPReturnObject returnObject_under = besSbdyService.delUnderInfo(obj);
			if (obj.getString("fNodeType").equals(BesNodeType.PARK)){//根节点
				returnObject = besSbdyService.delectSbdyPark(obj);
			}
			else if (obj.getString("fNodeType").equals(BesNodeType.AREA)){//区域节点
				returnObject = besSbdyService.delectSbdyArea(obj);
			}
			else if (obj.getString("fNodeType").equals(BesNodeType.EDC)){//能耗采集节点
				returnObject = besSbdyService.delectSbdyEdc(obj);
			}
			else if (obj.getString("fNodeType").equals(BesNodeType.ILLUMINATION)){//照明控制节点
				returnObject = besSbdyService.delectSbdyLAMP(obj);
			}
			else if (obj.getString("fNodeType").equals(BesNodeType.LAMP)){//照明区域节点
				returnObject = besSbdyService.delectSbdyLAMP(obj);
			}
			else if (obj.getString("fNodeType").equals(BesNodeType.DDC)){//DDC控制器的节点
				returnObject = besSbdyService.delectSbdyDDC(obj);
			}
			else if (obj.getString("fNodeType").equals(BesNodeType.LINE)){//线路节点
				returnObject = besSbdyService.delectSbdyLine(obj);
			}
			else if (obj.getString("fNodeType").equals(BesNodeType.MODULE)){//模块节点
				returnObject = besSbdyService.delectSbdyModule(obj);
			}
			else if (obj.getString("fNodeType").equals(BesNodeType.AI) || obj.getString("fNodeType").equals(BesNodeType.AO) ||
					 obj.getString("fNodeType").equals(BesNodeType.DI) || obj.getString("fNodeType").equals(BesNodeType.DO)	||
					 obj.getString("fNodeType").equals(BesNodeType.UI)){//模块下的点位节点
				returnObject = besSbdyService.delectSbdyPoint(obj);
			}
			else if (obj.getString("fNodeType").equals(BesNodeType.VPOINT)){//虚点节点
				returnObject = besSbdyService.delectSbdyVpoint(obj);
			}
			else if (obj.getString("fNodeType").equals(BesNodeType.COLLECTOR)){//采集器节点
				returnObject = besSbdyService.delectSbdyCollector(obj);
			}
			else if (obj.getString("fNodeType").equals(BesNodeType.BUS_EDC)){//电表的总线节点
				returnObject = besSbdyService.delectSbdyEDCBus(obj);
			}
			else if (obj.getString("fNodeType").equals(BesNodeType.AMMETER)){//电表的节点
				returnObject = besSbdyService.delectSbdyAmmeter(obj);
			}
			else if (obj.getString("fNodeType").equals(BesNodeType.SPUR_COUPLER)){//支线耦合器
				returnObject = besSbdyService.delectSbdyBranchCoupler(obj);
			}
			else if (obj.getString("fNodeType").equals(BesNodeType.TRUNK_COUPLER)){//干线耦合器
				returnObject = besSbdyService.delectSbdyTrunkCoupler(obj);
			}
			else if (obj.getString("fNodeType").equals(BesNodeType.IP_ROUTER)){//IP路由器节点
				returnObject = besSbdyService.delectSbdyLDC(obj);
			}

//			returnObject = besSbdyService.delSbTreeBySelNode(obj);
			//if(returnObject_under.getStatus().equals("0") && returnObject.getStatus().equals("1")) {
			//	returnObject.setMsg("上位机删除成功，下位机删除失败");
			//}else
			if(returnObject.getStatus().equals("0")) {
				returnObject.setMsg("删除失败");
			}else
			{

				String f_sys_name = obj.getString("f_sys_name");

				List<BESSbPzStruct> besSbPzStructs = sbPzStructCache.getCascadeSubordinate(f_sys_name);

				besSbPzStructs.forEach(item -> {

					String type = item.getF_type();
					String sysName = item.getF_sys_name();

					String sysNameOld = item.getF_sys_name_old();

					// 删除采集器缓存
					if (BesNodeType.COLLECTOR.equals(type))
					{
						collectorCache.deleteOneCollectorCache(sysName);
						return;
					}

					// 删除ddc 或者 ip路由器缓存
					if (BesNodeType.IP_ROUTER.equals(type) || BesNodeType.DDC.equals(type))
					{
						ddcCache.deleteOneDdcCache(sysName);
						return;
					}

					/*删除电表数据缓存 start*/
					if (BesNodeType.AMMETER.equals(type))
					{
						ammeterCache.deleteOneAmmeterCache(sysName);
						return;
					}
					/*删除电表数据缓存 end*/


					if (BesNodeType.VPOINT.equals(type))
					{
						// 删除电表缓存
						ammeterCache.deleteOneAmmeterCache(sysName);

						// 删除虚点缓存
						virtualPointCache.deleteOneVirtualPointCache(sysName);

						List<DeviceFunctionPointModel> deviceFunctionPointModelList = deviceFunctionPointCache.getByDeviceFunctionSysName(sysName);

						if (deviceFunctionPointModelList.size() > 0) {
							//走删除功能点位逻辑
							DeviceFunctionPointModel deviceFunctionPointModel = new DeviceFunctionPointModel();
							deviceFunctionPointModel.setId(deviceFunctionPointModelList.get(0).getId());
							deviceFunctionPointService.delete(deviceFunctionPointModel);
						}
						return;
					}

					if (BesNodeType.AI.equals(type) || BesNodeType.AO.equals(type) || BesNodeType.DI.equals(type) || BesNodeType.DO.equals(type))
					{
						if (BesNodeType.AI.equals(type)) {
							// 删除电表缓存
							ammeterCache.deleteOneAmmeterCache(sysName);

							// 删除ai点缓存
							aiPointCache.deleteOneAiPointCache(sysName);
						}
						if (BesNodeType.AO.equals(type)) {
							// 删除AO点缓存
							aoPointCache.deleteOneAoPointCache(sysName);

						}
						if (BesNodeType.DI.equals(type)) {
							// 删除DI点缓存
							diPointCache.deleteOneDiPointCache(sysName);

						}
						if (BesNodeType.DO.equals(type)) {
							// 删除DO点缓存
							doPointCache.deleteOneDoPointCache(sysName);

						}

						List<DeviceFunctionPointModel> deviceFunctionPointModelList = deviceFunctionPointCache.getByDeviceFunctionSysName(sysNameOld);

						if (deviceFunctionPointModelList.size() > 0) {
							//走删除功能点位逻辑
							DeviceFunctionPointModel deviceFunctionPointModel = new DeviceFunctionPointModel();
							deviceFunctionPointModel.setId(deviceFunctionPointModelList.get(0).getId());
							deviceFunctionPointService.delete(deviceFunctionPointModel);
						}
						return;
					}

				});

				// 清除缓存
				if (StringUtils.hasText(f_sys_name))
				{
					sbPzStructCache.deleteCascade(f_sys_name);
				}

			}
		}else {
			returnObject.setStatus("0");
			returnObject.setMsg(returnDelEnable.getMsg());
		}
		return returnObject;
	}

	/**
	 *
	 * @Description:逻辑点的删除操作
	 *
	 * @auther: wanghongjie
	 * @date: 9:31 2020/7/10
	 * @param: [f_sys_name, f_psys_name, f_sbid, f_node_attribution]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/reSetPoint", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject reSetPoint(String f_sys_name,String f_sys_name_old,String f_psys_name,String f_sbid,String f_channel_index) throws UnsupportedEncodingException, RemoteException, ServiceException {
		log.info("#获取逻辑点的值");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, Object> pointMap = new HashMap<>();
		if(f_sbid == "") {
			String tabName = besSbdyMapper.findNodeTable(f_sys_name);
			// 查询该逻辑虚点属性信息
			pointMap = crossEquipmentMapper.queryPointInfo(tabName, f_sys_name);
			f_sbid = (String) pointMap.get("F_SBID");

		}
		List<DeviceFunctionPointModel> deviceFunctionPointModelList = deviceFunctionPointCache.getByDeviceFunctionSysName(f_sys_name_old);

		if (deviceFunctionPointModelList.size() > 0) {
			//走删除功能点位逻辑
			DeviceFunctionPointModel deviceFunctionPointModel = new DeviceFunctionPointModel();
			deviceFunctionPointModel.setId(deviceFunctionPointModelList.get(0).getId());
			deviceFunctionPointService.delete(deviceFunctionPointModel);
		}

		returnObject = besSbdyService.deletePointByF_sys_name(f_sys_name,f_sbid,f_psys_name,f_channel_index);


		if ("1".equals(returnObject.getStatus()))
		{
			// 更新设备结构树缓存
			BESSbPzStruct sbPzStruct = besSbdyMapper.sbpzQueryPollStatus(f_sys_name);
			sbPzStructCache.updateOneSbPzStructCache(sbPzStruct);

			/*删除逻辑点缓存 start*/
			aiPointCache.deleteOneAiPointCache(f_sys_name);
			aoPointCache.deleteOneAoPointCache(f_sys_name);
			diPointCache.deleteOneDiPointCache(f_sys_name);
			doPointCache.deleteOneDoPointCache(f_sys_name);
			/*删除逻辑点缓存 end*/
		}

		return returnObject;
	}

	/**
	 * 生成设备类型树
	 * @return
	 */
	@RequestMapping(value = "/sbdy_getEquipmentModuleTree", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getEquipmentModuleTree(Map<String, String> map,String ftype) {
		ISSPReturnObject returnObject = besSbdyService.getEquipmentModuleTree(ftype);
		return returnObject;
	}

	/**
	 * 根据树所选节点查询设备树节点类型信息（维护url、实体表名、多级菜单等）
	 *
	 * @param sblxbh
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/sbdy_getCurrentEquipmentType", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getCurrentEquipmentType(String sblxbh, String type) {
		ISSPReturnObject returnObject = besSbdyService.getCurrentEquipmentType(sblxbh, type);

		return returnObject;
	}

	/**
	 * 根据干线耦合器通信地址设置获取支线通信地址设置信息
	 * @return
	 */
	@RequestMapping(value = "/sbdy_getBranchAddrByTrunkId", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getBranchAddrByTrunkId(String f_sys_name) {
		ISSPReturnObject returnObject = besSbdyService.getBranchAddrByTrunkId(f_sys_name);

		return returnObject;
	}
	/**
	 * 获取模块通信地址前两位用于页面展示
	 * @return
	 */
	@RequestMapping(value = "/sbdy_getModuleF_ADDR", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject sbdy_getModuleF_ADDR(@RequestBody JSONObject obj) {
		ISSPReturnObject returnObject = besSbdyService.sbdy_getModuleF_ADDR(obj);
		return returnObject;
	}
	/**
	 * 获取采集器轮询周期
	 * @return
	 */
	@RequestMapping(value = "/sbdy_getCollectorLoopTime", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getCollectorLoopTime(String f_sys_name) {
		ISSPReturnObject returnObject = besSbdyService.getCollectorLoopTime(f_sys_name);

		return returnObject;
	}

	/**
	 * 通过制定列名获取该列全部信息
	 * @param tabName 表名
	 * @return
	 */
	@RequestMapping(value = "/sbdy_getInfoByColumnName", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getInfoByColumnName(String colIdName, String colName, String tabName) {
		ISSPReturnObject returnObject = besSbdyService.getInfoByColumnName(colIdName, colName, tabName);

		return returnObject;
	}

	/**
	 * 编程界面提交内容
	 * @param file
	 * @return
	 */
	 //wanghongjie 增加HttpServletResponse和HttpServletRequest传参
	@RequestMapping(value="/ddcbc_upload", method=RequestMethod.POST )
	@ResponseBody
	public  ISSPReturnObject ddcbcUpload(HttpServletResponse response, HttpServletRequest request,@RequestParam("file") MultipartFile file, String ddcbcInfoText,String ddcbc_sys_name,String ddcbc_f_type,Integer f_id) throws IOException {
		ISSPReturnObject returnObject =besSbdyService.dealProgrammInfo(response,request,file,ddcbcInfoText,ddcbc_sys_name,ddcbc_f_type,f_id);
		return returnObject;
    }
	/**
	 *
	 * @Description: 能耗采集器的远程升级
	 *
	 * @auther: wanghongjie
	 * @date: 15:58 2020/5/6
	 * @param: [response, request, file, ddcbcInfoText, ddcbc_sys_name, ddcbc_f_type, f_id]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value="/collectorUpload", method=RequestMethod.POST )
	@ResponseBody
	public  ISSPReturnObject collectorUpload(HttpServletResponse response, HttpServletRequest request,@RequestParam("file") MultipartFile file,String ddcbcInfoText,String f_ip) throws IOException {
		ISSPReturnObject returnObject =besSbdyService.collectorUpload(response,request,file,ddcbcInfoText,f_ip);
		return returnObject;
	}


    /**
     *
     * @Description: 编程界面下载内容
     *
     * @auther: wanghongjie
     * @date: 10:08 2020/4/17
     * @param:
	 *
     */
    @RequestMapping(value = "/ddcdownload", method = RequestMethod.POST)
	@ResponseBody
	public void ddcdownload(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		//  接受的是UTF-8
		ISSPReturnObject isspReturnObject=new ISSPReturnObject();
		/*req.setCharacterEncoding("utf-8");
		//获取文件的根目录
		String rootPath = System.getProperty("user.dir");
		String realPath = rootPath+"/issp-bes-service/src/main/java/com/core/common/ppcl/program.txt";
		//获取文件名
		String filename = "program.txt";
		File file = null;
		HttpHeaders headers =null;*/
		try {
			response.reset();
			response.setContentType("text/plain");
			//测量数据.txt为下载后的文件名
			response.setHeader("Content-Disposition",
					"attachment;  filename=" + new String("program.txt".getBytes("GBK"), "ISO8859-1"));
			InputStream in = null;
			try {
				String rootPath = System.getProperty("user.dir");
				String realPath = rootPath+"/issp-bes-service/src/main/java/com/core/common/ppcl/program.txt";
				in = new FileInputStream(realPath);
				int len = 0;
				byte[] buffer = new byte[1024];
				response.setCharacterEncoding("GBK");
				OutputStream out = response.getOutputStream();
				while ((len = in.read(buffer)) > 0) {
					out.write(buffer, 0, len);
				}
			}catch (FileNotFoundException e){
				System.out.println(e);
			}finally {
				if (in != null) {
					try {
						in.close();
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			}
		} catch (IOException  e) {
			throw new RuntimeException(e);

		}
	}
	/**
	 * 同步OPC数据
	 * @return
	 */
	@RequestMapping(value="/syncOpc", method=RequestMethod.POST )
	@ResponseBody
	public ISSPReturnObject syncOpc() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		if(opcuseable == 1) {
			//OPC数据表更新该条数据
			OpcData.syncOpc();
			returnObject.setMsg("同步成功");
			returnObject.setStatus("1");
		}else {
			returnObject.setMsg("OPC未开启");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	/**
	 * 设计器页面：查询点位配置列表
	 * 设计器使用的是f_sys_name，而配置表BES_NODE_CONFIG_SETTING保存的是f_sys_name_old，所以需要多查询一步。
	 * @param f_sys_name 点位名称
	 * @return
	 */
	@RequestMapping(value = "/selectDesignNodesConfig", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject selectDesignNodesConfig(String f_sys_name) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String,Object> pointMap = besSbdyMapper.sbpzStructPointMapByName(f_sys_name);
		if(pointMap != null){
			String old_name = String.valueOf(pointMap.get("F_SYS_NAME_OLD"));
			log.info("BESSbdyController.selectDesignNodesConfig:查询节点配置设置表");
			returnObject = besSbdyService.selectNodesConfigSetting(old_name);
		}
		return returnObject;
	}


	/**
	 * 点击DDC下面的模块的不同AO、AI、DO、DI点，点击调试进入调试页面后查询节点配置设置表数据
	 * @param f_sys_name
	 * @return
	 */
	//@PostMapping(value="/selectNodesConfigSetting")
	@RequestMapping(value = "/selectNodesConfigSetting", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject selectNodesConfigSetting(String f_sys_name) {
		log.info("BESSbdyController.selectNodesConfigSetting:查询节点配置设置表");
		ISSPReturnObject returnObject = besSbdyService.selectNodesConfigSetting(f_sys_name);
		return returnObject;
	}

	/**
	 * 点击DDC下面的模块的不同AO、AI、DO、DI点，点击调试进入调试页面，进行页面设置配置时，更新节点配置设置表(先删除，再插入)；
	 * @return
	 */
	@RequestMapping(value = "/updateNodesConfigSetting", method = RequestMethod.POST)
	@ResponseBody
	public int updateNodesConfigSetting(@RequestBody JSONObject obj) {
		log.info("BESSbdyController.updateNodesConfigSetting:根据系统名称更新节点配置设置表");
		int returnCount = besSbdyService.updateNodesConfigSetting(obj);
		return returnCount;
	}

	/**
	 * 点击DDC下面的模块的不同AO、AI、DO、DI点，点击调试进入调试页面，进行页面设置配置时，插入节点配置设置表
	 * @return
	 */
	//TODO
	@RequestMapping(value = "/insertNodesConfigSetting", method = RequestMethod.POST)
	@ResponseBody
	public int insertNodesConfigSetting(@RequestBody JSONObject obj) {
		log.info("BESSbdyController.insertNodesConfigSetting:根据系统名称插入节点配置设置表");
		int returnCount = besSbdyService.insertNodesConfigSetting(obj);
		return returnCount;
	}

	/**
	 * @Description: DDC控制器新增
	 * @auther: wanghongjie
	 * @date: 17:20 2020/6/20
	 */
	@RequestMapping(value = "/sbdy_ddcInfo_Insert", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject sbdy_ddcInfo_Insert(@RequestBody JSONObject obj)throws UnsupportedEncodingException, RemoteException, ServiceException{
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("BESSbdyController.sbdy_ddcInfo_Insert:DDC控制器新增");
		if (obj.get("f_type").toString().equals("2")){
			returnObject = ddCrelevant.judgeDDC(obj,besSbdyService.sbdy_ddcInfo_Insert(obj));
		}else if (obj.get("f_type").equals("3")) {
			returnObject = ddCrelevant.judgeDDCLamp(obj,besSbdyService.sbdy_ddcInfo_Insert(obj));
		}


		return returnObject;
	}

	/**
	 * @Description: DDC控制器的修改
	 * @auther: wanghongjie
	 * @date: 13:57 2020/6/23
	 *
	 */
	@RequestMapping(value = "/sbdy_ddcInfo_Update", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject sbdy_ddcInfo_Update(@RequestBody JSONObject obj){
		log.info("BESSbdyController.sbdy_ddcInfo_Update:DDC控制器的修改");
		ISSPReturnObject returnObject = besSbdyService.sbdy_ddcInfo_Update(obj);

		return returnObject;

	}

	/**
	 *
	 * @Description: DDC给下位机下发指令，获取控制器参数
	 *
	 * @auther: wanghongjie
	 * @date: 17:45 2020/6/28
	 * @param: [fSysName]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/getDDCInfoParam",method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getDDCInfoParam(String fSysName,String f_type){
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("BESSbdyController.getDDCInfoParam:给下位机下发指令，获取控制器参数");
		return besSbdyService.getDDCInfoParam(fSysName,f_type);
	}

	/**
	 * @Description: DDC控制器新增
	 * @auther: wanghongjie
	 * @date: 17:20 2020/6/20
	 */
	@RequestMapping(value = "/sbdy_moduleInfo_insert", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject sbdy_moduleInfo_insert(@RequestBody JSONObject obj){
		log.info("BESSbdyController.sbdy_moduleInfo_insert:DDC控制器新增");
		ISSPReturnObject returnObject = besSbdyService.sbdy_moduleInfo_insert(obj);

		String sysName = obj.getString("f_sys_name");

		if ("1".equals(returnObject.getStatus()) && StringUtils.hasText(sysName))
		{
			/*添加到缓存设备结构树 start*/

			BESSbPzStruct sbPzStruct = besSbdyMapper.sbpzQueryPollStatus(sysName);

			if(sbPzStruct == null) return returnObject;

			// 添加模块缓存
			sbPzStructCache.addOneSbPzStructCache(sbPzStruct);

			List<BESSbPzStruct> childPointInfo = besSbdyMapper.getChildPointInfo(sysName);

			if (childPointInfo == null || childPointInfo.isEmpty()) return returnObject;

			// 添加模块子节点缓存
			childPointInfo.forEach(item -> sbPzStructCache.addOneSbPzStructCache(item));

			/*添加到缓存设备结构树 end*/
		}


		return	returnObject;
	}

	/**
	 * @Description: 模块的修改
	 * @auther: wanghongjie
	 * @date: 17:46 2020/6/30
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 */
	@RequestMapping(value = "/sbdy_moduleInfo_update", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject sbdy_moduleInfo_update(@RequestBody JSONObject obj){
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("BESSbdyController.sbdy_moduleInfo_update:模块的修改");

		returnObject = besSbdyService.sbdy_moduleInfo_update(obj);

		String sysName = obj.getString("f_sys_name");

		if ("1".equals(returnObject.getStatus()) && StringUtils.hasText(sysName))
		{
			/*更新到缓存设备结构树 start*/

			BESSbPzStruct sbPzStruct = besSbdyMapper.sbpzQueryPollStatus(sysName);

			if(sbPzStruct == null) return returnObject;

			// 更新模块缓存
			sbPzStructCache.updateOneSbPzStructCache(sbPzStruct);

			/*更新到缓存设备结构树 end*/
		}

		return	returnObject;
	}

	/**
	 *
	 * @Description: DO点新增
	 *
	 * @auther: wanghongjie
	 * @date: 13:42 2020/7/2
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/sbdy_DOInfo_Insert", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject sbdy_DOInfo_Insert(@RequestBody JSONObject obj){
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("BESSbdyController.sbdy_DOInfo_Insert:DO点新增");
		returnObject = besSbdyService.sbdy_DOInfo_Insert(obj);

		return	returnObject;
	}

	/**
	 *
	 * @Description: DO点修改
	 *
	 * @auther: wanghongjie
	 * @date: 17:19 2020/7/2
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/sbdy_DOInfo_Update", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject sbdy_DOInfo_Update(@RequestBody JSONObject obj){
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("BESSbdyController.sbdy_DOInfo_Insert:DO点新增");

		returnObject = besSbdyService.sbdy_DOInfo_Update(obj);

		return	returnObject;
	}

	/**
	 *
	 * @Description: AO点新增
	 *
	 * @auther: wanghongjie
	 * @date: 9:51 2020/7/3
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/sbdy_AOInfo_Insert", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject sbdy_AOInfo_Insert(@RequestBody JSONObject obj){
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("BESSbdyController.sbdy_AOInfo_Insert:AO点新增");

		returnObject = besSbdyService.sbdy_AOInfo_Insert(obj);

		return	returnObject;
	}

	/**
	 *
	 * @Description: AO点修改
	 *
	 * @auther: wanghongjie
	 * @date: 11:21 2020/7/3
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/sbdy_AOInfo_Update", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject sbdy_AOInfo_Update(@RequestBody JSONObject obj){
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("BESSbdyController.sbdy_AOInfo_Update:AO点修改");

		returnObject = besSbdyService.sbdy_AOInfo_Update(obj);

		return	returnObject;
	}

	/**
	 *
	 * @Description: AI点新增
	 *
	 * @auther: wanghongjie
	 * @date: 15:18 2020/7/3
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/sbdy_AIInfo_Insert", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject sbdy_AIInfo_Insert(@RequestBody JSONObject obj){
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("BESSbdyController.sbdy_AIInfo_Insert:AI点新增");

		returnObject = besSbdyService.sbdy_AIInfo_Insert(obj);

		return	returnObject;
	}

	/**
	 *
	 * @Description: AI点修改
	 *
	 * @auther: wanghongjie
	 * @date: 8:52 2020/7/4
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/sbdy_AIInfo_Update", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject sbdy_AIInfo_Update(@RequestBody JSONObject obj){
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("BESSbdyController.sbdy_AIInfo_Update:AI点修改");

		returnObject = besSbdyService.sbdy_AIInfo_Update(obj);

		return	returnObject;
	}

	/**
	 *
	 * @Description: DI点新增
	 *
	 * @auther: wanghongjie
	 * @date: 11:47 2020/7/4
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/sbdy_DIInfo_Insert", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject sbdy_DIInfo_Insert(@RequestBody JSONObject obj){
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("BESSbdyController.sbdy_DIInfo_Insert:DI点新增");

		returnObject = besSbdyService.sbdy_DIInfo_Insert(obj);

		return returnObject;
	}


	/**
	 *
	 * @Description: DI点修改
	 *
	 * @auther: wanghongjie
	 * @date: 14:08 2020/7/4
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/sbdy_DIInfo_Update", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject sbdy_DIInfo_Update(@RequestBody JSONObject obj){
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("BESSbdyController.sbdy_DIInfo_Update:DI点修改");

		returnObject = besSbdyService.sbdy_DIInfo_Update(obj);

		return	returnObject;
	}

	/**
	 *
	 * @Description: 同步逻辑点
	 *
	 * @auther: wanghongjie
	 * @date: 15:33 2020/7/4
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/synchronizePoint", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject synchronizePoint(@RequestBody JSONObject obj){
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("BESSbdyController.synchronizePoint:同步逻辑点");
		return	besSbdyService.synchronizePoint(obj);
	}

	/**
	 *
	 * @Description: 同步模块点
	 *
	 * @auther: wanghongjie
	 * @date: 9:39 2020/7/6
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/synchronizeModule", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject synchronizeModule(@RequestBody JSONObject obj){
		log.info("BESSbdyController.synchronizeModule:同步模块点");
		return	besSbdyService.synchronizeModule(obj);
	}

	/**
	 *
	 * @Description: 同步DDC
	 *
	 * @auther: wanghongjie
	 * @date: 14:20 2020/7/6
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/synchronizeDDC", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject synchronizeDDC(@RequestBody JSONObject obj){
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("BESSbdyController.synchronizeDDC:同步DDC");
		return	besSbdyService.synchronizeDDC(obj);
	}

	/**
	 *
	 * @Description: 获取上位机逻辑点信息
	 *
	 * @auther: wanghongjie
	 * @date: 14:28 2020/7/7
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/getPointInfoFillLocal", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getPointInfoFillLocal(@RequestBody JSONObject obj){
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("BESSbdyController.getPointInfo:获取上位机逻辑点信息");
		return	besSbdyService.getPointInfoFillLocal(obj);
	}

	/**
	 *
	 * @Description: 数据对比的时候,给下位机发送指令
	 *
	 * @auther: wanghongjie
	 * @date: 8:50 2020/7/8
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/getPointParam", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getPointParam(String tabName,String fSysName,String f_node_attribution,String f_id){
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("BESSbdyController.getPointParam:数据对比的时候,给下位机发送指令");
		return	besSbdyService.getPointParam(tabName,fSysName,f_node_attribution,f_id);
	}

	/**
	 *
	 * @Description: 根据id查询模块表的当前点位的全部信息
	 *
	 * @auther: wanghongjie
	 * @date: 13:39 2020/7/8
	 * @param: [moduleID]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/selectModuleByModuleID", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject selectModuleByModuleID(String moduleID){
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("BESSbdyController.selectModuleByModuleID:根据id查询模块表的当前点位的全部信息");
		return	besSbdyService.selectModuleByModuleID(moduleID);
	}
	/**
	 *
	 * @Description: 数据对比的时候,给下位机发送获取一个模块的所有配置信息的命令
	 *
	 * @auther: wanghongjie
	 * @date: 17:11 2020/7/8
	 * @param: [moduleID]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/getModuleParam", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getModuleParam(String f_id,String fSysName,String f_node_attribution){
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("BESSbdyController.getModuleParam:模块数据对比");
		return	besSbdyService.getModuleParam(f_id,fSysName,f_node_attribution);
	}

	/**
	 *
	 * @Description: DDC设置时间
	 *
	 * @auther: wanghongjie
	 * @date: 8:40 2020/7/9
	 * @param: [fSysName]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/setTimeDDC", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject setTimeDDC(String fSysName,String f_type){
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("BESSbdyController.setTimeDDC:DDC设置时间");
		return	besSbdyService.setTimeDDC(fSysName,f_type);
	}

	/**
	 *
	 * @Description: DDC获取时间
	 *
	 * @auther: wanghongjie
	 * @date: 8:59 2020/7/9
	 * @param: [fSysName]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/getTimeDDC", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getTimeDDC(String fSysName,String f_type){
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("BESSbdyController.getTimeDDC:DDC获取时间");
		return	besSbdyService.getTimeDDC(fSysName,f_type);
	}

	/**
	 *
	 * @Description: 重启DDC控制器
	 *
	 * @auther: wanghongjie
	 * @date: 9:10 2020/7/9
	 * @param: [fSysName]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/restartDDC", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject restartDDC(String fSysName,String f_type){
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("BESSbdyController.restartDDC:重启DDC控制器");
		return	besSbdyService.restartDDC(fSysName,f_type);
	}

	/**
	 *
	 * @Description: 重置DDC控制器
	 *
	 * @auther: wanghongjie
	 * @date: 9:14 2020/7/9
	 * @param: [fSysName]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/resetDDC", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject resetDDC(String fSysName,String f_type){
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("BESSbdyController.resetDDC:重置DDC控制器");
		return	besSbdyService.resetDDC(fSysName,f_type);
	}

	/**
	 *
	 * @Description: 设置逻辑点的值
	 *
	 * @auther: wanghongjie
	 * @date: 9:14 2020/7/9
	 * @param: [fSysName]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/sbdy_put_up_point", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject sbdy_put_up_point(@RequestBody JSONObject obj){
		log.info("BESSbdyController.sbdy_put_up_point:设置逻辑点的值");
		return	besSbdyService.sbdy_put_up_point(obj);
	}

	/**
	 *
	 * @Description: 设置逻辑点的值(根据名字)
	 *
	 * @auther: wanghongjie
	 * @date: 14:50 2020/8/7
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/sbdy_put_up_pointByName", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject sbdy_put_up_pointByName(@RequestBody JSONObject obj){
		log.info("BESSbdyController.sbdy_put_up_pointByName:设置逻辑点的值(根据名字)");
		return	besSbdyService.sbdy_put_up_pointByName(obj);
	}

	/**
	 *
	 * @Description: 新增干线或者新增支线耦合器
	 *
	 * @auther: wanghongjie
	 * @date: 11:47 2020/7/28
	 * @param:
	 * @return:
	 *
	 */
	@RequestMapping(value = "/sbdy_coupler_Insert", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject sbdy_coupler_Insert(@RequestBody JSONObject obj){
		log.info("BESSbdyController.sbdy_coupler_Insert:新增干线或者新增支线耦合器");

		ISSPReturnObject returnObject = besSbdyService.sbdy_coupler_Insert(obj);

		/*添加到缓存设备结构树 start*/

		if ("1".equals(returnObject.getStatus()))
		{
			BESSbPzStruct sbPzStruct = besSbdyMapper.sbpzQueryPollStatus(obj.getString("f_sys_name"));

			if(sbPzStruct != null)
			{
				sbPzStructCache.addOneSbPzStructCache(sbPzStruct);
			}
		}

		/*添加到缓存设备结构树 end*/
		return	returnObject;
	}

	/**
	 *
	 * @Description: 修改干线或者新增支线耦合器
	 *
	 * @auther: wanghongjie
	 * @date: 8:47 2020/7/29
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/sbdy_coupler_Update", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject sbdy_coupler_Update(@RequestBody JSONObject obj){
		log.info("BESSbdyController.sbdy_coupler_Update:修改干线或者新增支线耦合器");

		ISSPReturnObject returnObject = besSbdyService.sbdy_coupler_Update(obj);


		/*更新到缓存设备结构树 start*/

		if ("1".equals(returnObject.getStatus()))
		{
			BESSbPzStruct sbPzStruct = besSbdyMapper.sbpzQueryPollStatus(obj.getString("f_sys_name"));

			if(sbPzStruct != null)
			{
				sbPzStructCache.updateOneSbPzStructCache(sbPzStruct);
			}
		}

		/*更新到缓存设备结构树 end*/

		return	returnObject;
	}


	/**
	 *
	 * @Description: 查询支线耦合器上一级的通信地址以及模块上两级的通信地址
	 *
	 * @auther: wanghongjie
	 * @date: 14:05 2020/7/29
	 * @param: [f_sys_name]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/select_branchCoupler", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject select_branchCoupler(String f_sys_name){
		log.info("BESSbdyController.select_branchCoupler:查询支线耦合器上一级的通信地址以及模块上两级的通信地址");
		return	besSbdyService.select_branchCoupler(f_sys_name);
	}

	/**
	 *
	 * @Description: 刚进入新增页面的时候,查出上级节点的通信地址
	 *
	 * @auther: wanghongjie
	 * @date: 15:45 2020/7/29
	 * @param: [f_sys_name]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/select_pre_branchCoupler", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject select_pre_branchCoupler(String f_sys_name){
		log.info("BESSbdyController.select_pre_branchCoupler:刚进入新增页面的时候,查出上级节点的通信地址");
		return	besSbdyService.select_pre_branchCoupler(f_sys_name);
	}

	/**
	 *
	 * @Description: 根据父节点的名称,查询父节点的所属系统
	 *
	 * @auther: wanghongjie
	 * @date: 18:31 2020/7/29
	 * @param: [f_sys_name]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/selectPSysNameNodeAttribution", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject selectPSysNameNodeAttribution(@RequestBody JSONObject obj){
		log.info("BESSbdyController.selectPSysNameNodeAttribution:根据父节点的名称,查询父节点的所属系统");
		return	besSbdyService.selectPSysNameNodeAttribution(obj);
	}

	/**
	 *
	 * @Description: 查询关联模块的模块型号
	 *
	 * @auther: tianjiangwei
	 * @date: 09:24 2020/8/12
	 *
	 */
	@RequestMapping(value = "/getRelativeModuleTypeInfo", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getRelativeModuleTypeInfo(String sysname){
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("BESSbdyController.getRelativeModuleTypeInfo:查询关联模块型号");
		return	besSbdyService.getRelativeModuleTypeInfo(sysname);
	}


	/**
	 *
	 * @Description: 查询关联点位类型
	 *
	 * @auther: tianjiangwei
	 * @date: 09:24 2020/8/12
	 *
	 */
	@RequestMapping(value = "/getPointTypeInfo", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getPointTypeInfo(String sysname){
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("BESSbdyController.getRelativeModuleTypeInfo:查询关联点位类型");
		return	besSbdyService.getPointTypeInfo(sysname);
	}

	/**
	 *
	 * @Description: 查询模块的点位信息
	 *
	 * @auther: tianjiangwei
	 * @date: 09:24 2020/8/12
	 *
	 */
	@RequestMapping(value = "/getChildPointInfo", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getChildPointInfo(String sysname){
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("BESSbdyController.getRelativeModuleTypeInfo:查询关联点位");
		return	besSbdyService.getChildPointInfo(sysname);
	}

	/**
	 *
	 * @Description: 模块数据对比获取上位机的模块型号
	 *
	 * @auther: wanghongjie
	 * @date: 10:27 2020/8/12
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/getf_module_type", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getf_module_type(@RequestBody JSONObject obj){
		log.info("BESSbdyController.getf_module_type:模块数据对比获取上位机的模块型号");
		return	besSbdyService.getf_module_type(obj);
	}

	/**
	 *
	 * @Description: 查询园区的信息
	 *
	 * @auther: wanghongjie
	 * @date: 16:09 2020/9/22
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/select_park", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject select_park(){
		log.info("BESSbdyController.select_park:查询园区的信息");
		return	besSbdyService.select_park();
	}

	/**
	 *
	 * @Description: 添加设备配置的根节点
	 *
	 * @auther: wanghongjie
	 * @date: 16:06 2020/9/22
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/insert_sbdy_node_first", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject insert_sbdy_node_first(String f_yqbh,String f_descrption,String f_nick_name){
		log.info("BESSbdyController.insert_sbdy_node_first:添加设备配置的根节点");
		return	besSbdyService.insert_sbdy_node_first(f_yqbh,f_descrption,f_nick_name);
	}

}
