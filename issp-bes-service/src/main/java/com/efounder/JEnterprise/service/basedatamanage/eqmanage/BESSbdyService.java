package com.efounder.JEnterprise.service.basedatamanage.eqmanage;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESSbPzStruct;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;


public interface BESSbdyService {

	public ISSPReturnObject getTree();

	/**
	 * 适用于Ztree设备树，从缓存中读取设备树
	 * @return
	 */
	ISSPReturnObject getTreeFormat();

	ISSPReturnObject getTreeLazyLoad(String pSysName);

	ISSPReturnObject getTreeReload(String pSysName);

	/**
	 * 设备树结构信息
	 * @param pageNum
	 * @param keywords
	 * @return
	 */
	public PageInfo<BESSbPzStruct> findSbdyInfo(Integer pageNum, String keywords);

	/**
	 * 加载设备树
	 * @return
	 * @throws ServiceException
	 * @throws RemoteException
	 * @throws UnsupportedEncodingException
	 */
	public ISSPReturnObject loadAll() throws UnsupportedEncodingException, RemoteException, ServiceException;

	/**
	 * 加载设备树节点类型信息
	 * @return
	 */
	public ISSPReturnObject findChildByTreeNoteTypes();

	/**
	 * 根据树所选查询子节点信息
	 * @param f_type
	 * @return
	 */
	public ISSPReturnObject findChildByTreeNoteType(String f_type);

	/**
	 * 模块属性信息中模块型号下拉列表获取模块类型信息
	 * @return
	 */
	public ISSPReturnObject getModuleTypeInfo();

	/**
	 * 虚点属性信息中虚点类型下拉列表获取虚点类型信息
	 * @return
	 */
	public ISSPReturnObject getVpointTypeInfo();

	/**
	 * 根据所选节点编辑属性信息
	 * @param obj
	 * @return
	 */
	public ISSPReturnObject editSbdyInfoBySelByNode(JSONObject obj);

	//Start add by yangjx at 20191212 for 取消页面sbdy_vpoint进行应用时调用上方方法editSbdyInfoBySelByNode()。解决当数据由有变为空时上方通用方法不可行的问题
	/**
	 * 更新BES_VPOINT表信息
	 * @param obj
	 * @return
	 */
	public ISSPReturnObject updateTableBesVpoint(JSONObject obj);
	//End add by yangjx at 20191212 for 取消页面sbdy_vpoint进行应用时调用上方方法editSbdyInfoBySelByNode()。解决当数据由有变为空时上方通用方法不可行的问题


	/**
	 * 编辑采集器信息
	 * @param obj
	 * @return
	 */
	public ISSPReturnObject editSbdyInfoCollector(JSONObject obj);

	/**
	 * 根据所选节点编辑属性信息
	 * @param obj
	 * @return
	 * @throws ServiceException
	 * @throws RemoteException
	 * @throws UnsupportedEncodingException
	 */
	public ISSPReturnObject editDefaultNodeInfo(JSONObject obj) throws UnsupportedEncodingException, RemoteException, ServiceException;

	/**
	 * 更新添加默认节点的属性信息
	 * @param f_sys_name 系统名称
	 * @return
	 */
	public BESSbPzStruct getSbTreeInfoBySysName(String f_sys_name);

	/**
	 * 获取所选节点获取对应的属性信息
	 * @param f_sys_name
	 * @return
	 */
	public ISSPReturnObject findSbdyInfoBySelNode(String f_sys_name,String nodeTabName,String f_type, String f_psys_name);

	public int getModuleNodeInfoWhenEntryPage(String f_sys_name,String tableName);

	/**
	 * 生成设备类型树
	 * @return
	 */
	public ISSPReturnObject getEquipmentModuleTree(String ftype);

	/**
	 * DDC获取当前型号
	 * @param sblxbh
	 * @param type
	 * @return
	 */
	public ISSPReturnObject getCurrentEquipmentType(String sblxbh, String type);

	/**
	 * 删除设备树信息
	 * @param obj
	 * @return
	 * @throws RemoteException
	 * @throws UnsupportedEncodingException
	 * @throws ServiceException
	 */
	public ISSPReturnObject delSbTreeBySelNode(JSONObject obj) throws UnsupportedEncodingException, RemoteException, ServiceException;

	/**
	 * 获取根据模块型号下拉列表添加子节点时所需信息
	 * @return
	 */
	public ISSPReturnObject getModuleTypeAddChidNodeInfo();

	/**
   	 * 根据所选节点弹出菜单添加设备树信息
   	 * @param obj 要添加的属性信息
   	 * @return
	 * @throws ServiceException
	 * @throws RemoteException
	 * @throws UnsupportedEncodingException
   	 */
	public ISSPReturnObject addSbdyInfoByTreeBtn(JSONObject obj) throws UnsupportedEncodingException, RemoteException, ServiceException;

	/**
   	 * 新增采集器
   	 * @param obj 要添加的属性信息
   	 * @return
   	 */
	public ISSPReturnObject addCollector(JSONObject obj);

	/**
   	 * 根据干线耦合器通信地址设置获取支线通信地址设置信息
   	 * @return
   	 */
	public ISSPReturnObject getBranchAddrByTrunkId(String f_sys_name);

	/**
   	 * 获取采集器轮询周期
   	 * @return
   	 */
	public ISSPReturnObject getCollectorLoopTime(String f_sys_name);

	/**
   	 * 通过制定列名获取该列全部信息
   	 * @return
   	 */
	public ISSPReturnObject getInfoByColumnName(String colIdName, String colName, String tabName);

	/**
	 * 获取逻辑点调试信息
	 * @param f_sys_name
	 * @param nodeTabName
	 * @return
	 */
	public ISSPReturnObject getdebugNodeInfo(String f_sys_name);

	public ISSPReturnObject loadAllForJK(List<String> f_sys_name);

	public ISSPReturnObject loadAllForJK(String f_sys_name);
	/**
	 * 获取模块通信地址的前两位
	 * @param obj
	 * @return
	 */
	public ISSPReturnObject sbdy_getModuleF_ADDR(JSONObject obj);
	/**
	 * 根据f_id获取DO点的信息
	 * @param f_id
	 * @return
	 */
	public ISSPReturnObject getPointInfo(String f_id,String tabname);
	/**
	 * 获取所有DO点
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	ISSPReturnObject getAllDOInfo() throws UnsupportedEncodingException, RemoteException, ServiceException;
	/**
	 * 获取所有逻辑点信息
	 * @return
	 */
	public ISSPReturnObject getAllPointInfo();

	public ISSPReturnObject getAllPointInfo(String sysName);


	/**
	 * 处理编程一面信息
	 * @return
	 */
	public ISSPReturnObject dealProgrammInfo(HttpServletResponse response, HttpServletRequest request, MultipartFile file, String ddcbcInfoText, String ddcbc_sys_name, String ddcbc_f_type,Integer f_id);
	/**
	 *
	 * @Description: 能耗采集器远程升级
	 *
	 * @auther: wanghongjie
	 * @date: 8:49 2020/5/7
	 * @param: [response, request, file, ddcbcInfoText, f_ip]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	public ISSPReturnObject collectorUpload(HttpServletResponse response, HttpServletRequest request, MultipartFile file,String ddcbcInfoText,String f_ip);

	/**
	 * 删除下位机中设备信息
	 * @param obj
	 * @return
	 * @throws ServiceException
	 * @throws RemoteException
	 * @throws UnsupportedEncodingException
	 */
	public ISSPReturnObject delUnderInfo(JSONObject obj) throws UnsupportedEncodingException, RemoteException, ServiceException;
	/**
	 * 判断该设备是否可删除
	 * @param obj
	 * @return
	 */
	public ISSPReturnObject delEnable(JSONObject obj);

	/**
	 * 获取温控监控点位信息
	 * @param sysName
	 * @return
	 */
	public ISSPReturnObject getWkjkPointInfo(String sysName);

	ISSPReturnObject loadAllForgivenJK(HttpServletRequest request);

	/**
	 * 获取ddc实时数据(实时监控页面)
	 * @param sysName
	 * @return
	 */
	ISSPReturnObject getRvscanData(String sysName);

    ISSPReturnObject getOpcKeyAll();
	/**
	 * 根据系统名称查询节点配置设置表数据
	 * @param f_sys_name
	 * @return
	 */
	public ISSPReturnObject selectNodesConfigSetting(String f_sys_name);

	/**
	 *点击DDC下面的模块的不同AO、AI、DO、DI点，进入不同页面时，点击调试进入调试页面，进行页面设置配置时，更新节点配置设置表(先删除，再插入)；
	 */
	public int updateNodesConfigSetting(JSONObject obj);

	/**
	 * 插入节点设置配置表数据
	 * @param obj
	 * @return
	 */
	public int insertNodesConfigSetting(JSONObject obj);

	/**
	 * 获取设备树信息（未格式化的）
	 * @return
	 */
	ISSPReturnObject getAustereTree();
	/**
	 * 根据类型查询对应名称
	 * @param f_type
	 * @return
	 * tianjiangwei  2020/4/8
	 */
	public ISSPReturnObject noteNameByNoteType(String f_type);
	/**
	 * 查询虚点类型
	 * @param f_sys_name
	 * @return
	 */
	public String findNodeTypeBySysName(String f_sys_name);

	/**
	 * 根据名称查重
	 * @param type
	 * @return
	 */
	List<Map<String,Object>> judge(String type);


	/**
	 *
	 * @Description: 查询DDC编程页面的txt文本以及DDC信息
	 *
	 * @auther: wanghongjie
	 * @date: 13:52 2020/4/23
	 * @param:
	 * @return:
	 *
	 */
	public BESSbPzStruct getSbTreeAndTxtInfoBySysNameAndId(String f_sys_name,String f_id);



    String selectSYS_NAME(String SYS_NAME);

	/**
	 * 新增电表
	 * @param obj
	 * @return
	 */
    ISSPReturnObject addAmmeter(JSONObject obj);

	/**
	 * 更新能耗采集器总线
	 * @param obj
	 * @return
	 */
	ISSPReturnObject updateEnergyBus(JSONObject obj);

	/**
	 * 更新电表信息
	 * @param obj
	 * @return
	 */
    ISSPReturnObject updateAmmeter(JSONObject obj);

    /**
     * @Description: DDC控制器的新增
     * @auther: wanghongjie
     * @date: 8:32 2020/6/22
     */
	public ISSPReturnObject sbdy_ddcInfo_Insert(JSONObject obj) throws UnsupportedEncodingException, RemoteException, ServiceException;

	/**
	 * @Description: 修改DDC控制器
	 * @auther: wanghongjie
	 * @date: 14:00 2020/6/23
	 *
	 */
	public ISSPReturnObject sbdy_ddcInfo_Update(JSONObject obj);

	ISSPReturnObject getDDCInfoParam(String fSysName,String f_type);

	ISSPReturnObject sbdy_moduleInfo_insert(JSONObject obj);

    ISSPReturnObject sbdy_moduleInfo_update(JSONObject obj);

	ISSPReturnObject sbdy_DOInfo_Insert(JSONObject obj);

    ISSPReturnObject sbdy_DOInfo_Update(JSONObject obj);

    ISSPReturnObject sbdy_AOInfo_Insert(JSONObject obj);

    ISSPReturnObject sbdy_AOInfo_Update(JSONObject obj);

	ISSPReturnObject sbdy_AIInfo_Insert(JSONObject obj);

	ISSPReturnObject sbdy_AIInfo_Update(JSONObject obj);

    ISSPReturnObject sbdy_DIInfo_Insert(JSONObject obj);

    ISSPReturnObject sbdy_DIInfo_Update(JSONObject obj);

	ISSPReturnObject synchronizePoint(JSONObject obj);

	ISSPReturnObject synchronizeModule(JSONObject obj);

	ISSPReturnObject synchronizeDDC(JSONObject obj);

	// 添加虚点
	ISSPReturnObject addVirtualPoint(JSONObject obj);

	ISSPReturnObject getPointInfoFillLocal(JSONObject obj);

    ISSPReturnObject getPointParam(String tabName,String fSysName, String f_node_attribution,String f_id);

	// 更新虚点信息
    ISSPReturnObject updateVirtualPoint(JSONObject obj);

	ISSPReturnObject selectModuleByModuleID(String moduleID);

    ISSPReturnObject getModuleParam(String f_id, String fSysName,String f_node_attribution);

	ISSPReturnObject setTimeDDC(String fSysName,String f_type);

    ISSPReturnObject getTimeDDC(String fSysName,String f_type);

    ISSPReturnObject restartDDC(String fSysName,String f_type);

	ISSPReturnObject resetDDC(String fSysName,String f_type);

    ISSPReturnObject delectSbdyArea(JSONObject obj);

	ISSPReturnObject delectSbdyDDC(JSONObject obj);

	ISSPReturnObject delectSbdyLine(JSONObject obj);

	ISSPReturnObject delectSbdyModule(JSONObject obj);

	ISSPReturnObject delectSbdyPoint(JSONObject obj);

	ISSPReturnObject delectSbdyVpoint(JSONObject obj);

	/**
	 * 同步虚点信息
	 * @param f_sys_name
	 * @return
	 */
	ISSPReturnObject synVirtualPoint(String f_sys_name);

	/**
	 *
	 * @Description: 逻辑点的删除操作
	 *
	 * @auther: wanghongjie
	 * @date: 9:44 2020/7/10
	 * @param: [f_sys_name, f_sbid, f_psys_name]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
    ISSPReturnObject deletePointByF_sys_name(String f_sys_name, String f_sbid, String f_psys_name,String f_channel_index);
	/**
	 *
	 * @Description: 电表的删除逻辑
	 *
	 * @auther: wanghongjie
	 * @date: 11:28 2020/7/13
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	ISSPReturnObject delectSbdyAmmeter(JSONObject obj);

	/**
	 *
	 * @Description: 能耗总线的删除逻辑
	 *
	 * @auther: wanghongjie
	 * @date: 13:52 2020/7/13
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	ISSPReturnObject delectSbdyEDCBus(JSONObject obj);

	/**
	 *
	 * @Description: 采集器的删除逻辑
	 *
	 * @auther: wanghongjie
	 * @date: 14:01 2020/7/13
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	ISSPReturnObject delectSbdyCollector(JSONObject obj);

	/**
	 *
	 * @Description: 能耗采集区域的删除逻辑
	 *
	 * @auther: wanghongjie
	 * @date: 14:54 2020/7/13
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	ISSPReturnObject delectSbdyEdc(JSONObject obj);

	/**
	 *
	 * @Description: 设置逻辑点的值
	 *
	 * @auther: wanghongjie
	 * @date: 13:54 2020/7/14
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
    ISSPReturnObject sbdy_put_up_point(JSONObject obj);

    /**
     *
     * @Description: 新增干线或者新增支线耦合器
     *
     * @auther: wanghongjie
     * @date: 11:49 2020/7/28
     * @param: [obj]
     * @return: com.core.common.ISSPReturnObject
     *
     */
	ISSPReturnObject sbdy_coupler_Insert(JSONObject obj);

	/**
	 *
	 * @Description: 修改干线或者新增支线耦合器
	 *
	 * @auther: wanghongjie
	 * @date: 8:48 2020/7/29
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
    ISSPReturnObject sbdy_coupler_Update(JSONObject obj);

    /**
     *
     * @Description:
     *
     * @auther: wanghongjie
     * @date: 14:12 2020/7/29
     * @param: [f_sys_name]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject select_branchCoupler(String f_sys_name);

    /**
     *
     * @Description: 刚进入新增页面的时候,查出上级节点的通信地址
     *
     * @auther: wanghongjie
     * @date: 15:46 2020/7/29
     * @param: [f_sys_name]
     * @return: com.core.common.ISSPReturnObject
     *
     */
	ISSPReturnObject select_pre_branchCoupler(String f_sys_name);

	/**
	 *
	 * @Description: 根据父节点的名称,查询父节点的所属系统
	 *
	 * @auther: wanghongjie
	 * @date: 18:34 2020/7/29
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	ISSPReturnObject selectPSysNameNodeAttribution(JSONObject obj);

	/**
	 *
	 * @Description: 支线耦合器
	 *
	 * @auther: wanghongjie
	 * @date: 17:25 2020/7/30
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	ISSPReturnObject delectSbdyBranchCoupler(JSONObject obj);

	/**
	 *
	 * @Description: 干线耦合器
	 *
	 * @auther: wanghongjie
	 * @date: 17:25 2020/7/30
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	ISSPReturnObject delectSbdyTrunkCoupler(JSONObject obj);

	/**
	 *
	 * @Description: IP路由器节点
	 *
	 * @auther: wanghongjie
	 * @date: 17:25 2020/7/30
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	ISSPReturnObject delectSbdyLDC(JSONObject obj);

	/**
	 *
	 * @Description: 照明区域节点
	 *
	 * @auther: wanghongjie
	 * @date: 17:27 2020/7/30
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	ISSPReturnObject delectSbdyLAMP(JSONObject obj);

	/**
	 *
	 * @Description: 根节点
	 *
	 * @auther: wanghongjie
	 * @date: 10:03 2020/9/23
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	ISSPReturnObject delectSbdyPark(JSONObject obj);
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
	ISSPReturnObject sbdy_put_up_pointByName(JSONObject obj);


	/**
	 * 查询设计器关联模块型号
	 * @param sysname
	 * @return
	 */
	ISSPReturnObject getRelativeModuleTypeInfo(String sysname);


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
	ISSPReturnObject getf_module_type(JSONObject obj);

	/**
	 *查询关联点位类型
	 * @param sysname
	 * @return
	 */
	ISSPReturnObject getPointTypeInfo(String sysname);

	/**
	 * 查询关联温控器模块的点位信息
	 * @param sysname
	 * @return
	 */
	ISSPReturnObject getChildPointInfo(String sysname);

	/**
	 *
	 * @Description: insert_sbdy_node_first
	 *
	 * @auther: wanghongjie
	 * @date: 16:06 2020/9/22
	 * @param: [obj]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
    ISSPReturnObject insert_sbdy_node_first(String f_yqbh,String f_descrption,String f_nick_name);

    /**
     *
     * @Description: 查询园区的信息
     *
     * @auther: wanghongjie
     * @date: 16:10 2020/9/22
     * @param: [obj]
     * @return: com.core.common.ISSPReturnObject
     *
     */
	ISSPReturnObject select_park();


}
