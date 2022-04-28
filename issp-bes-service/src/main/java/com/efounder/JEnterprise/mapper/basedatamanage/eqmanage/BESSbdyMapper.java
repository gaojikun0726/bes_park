package com.efounder.JEnterprise.mapper.basedatamanage.eqmanage;

import com.alibaba.fastjson.JSONObject;
import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.BESEquipmentMold;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESPark;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BESSbdyMapper extends BaseMapper<String, BESSbPzStruct> {

	List<BESSbPzStruct> findSbdyInfo(@Param("sbdy_in") String sbdy_in);
	/**
	 * 加载设备定义树
	 * @param checkDataLimit
	 * @return
	 */
	List<BESSbPzStruct> loadAll(@Param("checkDataLimit") String checkDataLimit);
	List<BESSbPzStruct> loadDOAll(@Param("checkDataLimit") String checkDataLimit);

	/**
	 * 查询子节点信息
	 * @param
	 * @return
	 */
	List<BESSbTreeNodeType> findChildByTreeNoteType(@Param("f_node_type") String f_type);
	int getModuleNodeInfoWhenEntryPage(@Param("f_sys_name") String f_sys_name,@Param("tableName") String tableName);

	/**
	 * 模块属性信息中模块型号下拉列表获取模块类型信息
	 * @return
	 */
	List<BESModuleType> getModuleTypeInfo();

	/**
	 * 虚点属性信息中虚点类型下拉列表获取虚点类型信息
	 * @return
	 */
	List<BESVPointType> getVpointTypeInfo();
	/*
	 *
	 * @Description: 验证ip是否重复
	 *
	 * @auther: wanghongjie
	 * @date: 17:00 2020/3/13
	 * @param: [ipTxt]
	 * @return: java.util.List<com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESVPointType>
	 *
	 */
	List<BesDdc> editSbdyInfoBySelByNodeByIP(@Param("ipTxt") String ipTxt,@Param("tabName") String tabName);

	String editSbdyInfoBySelByNodeByF_guid(@Param("f_guid")String f_guid);

	String editSbdyInfoBySelByNodeByF_guid_channel_id(@Param("f_guid")String f_guid);

	String editSbdyInfoBySelByNodeByF_guidCollector(@Param("f_guid")String f_guid);

	String isAmmeter(@Param("f_sys_name")String f_sys_name);
	/**
	 * 通过系统名称获取设备树信息
	 * @param f_sys_name
	 * @return
	 */
	BESSbPzStruct getSbTreeInfoBySysName(@Param("f_sys_name") String f_sys_name);
	BESSbPzStruct getSbTreeDataBySysName(@Param("f_sys_name") String f_sys_name);
	BESSbPzStruct getSbTreeInfoBySysNameChange(@Param("f_sys_name") String f_sys_name,@Param("tabName") String tabName);
	/**
	 * 获取所选节点获取对应的属性信息
	 * @return
	 */
	Map<String, Object> findSbdyInfoBySelNode(@Param("f_sys_name") String f_sys_name,@Param("nodeTabName") String nodeTabName);
	Map<String, Object> findSbdyInfoBySelNodeChange(@Param("f_sys_name") String f_sys_name,@Param("nodeTabName") String nodeTabName);
	Map<String, Object> findSbdyInfoByRootId(@Param("f_sys_name") String f_sys_name,@Param("tabName") String tabName);

	/**
	 * 根据所选节点弹出菜单添加设备树信息
	 * @param obj
	 * @return
	 */
	int addSbTreeStructInfo(@Param("obj") JSONObject obj);

	/**
	 * 根据系统名称添加其他表属性信息
	 * @param obj
	 * @return
	 */
	int addSbTreeAttrInfo(@Param("obj") JSONObject obj);

	/**
	 * 根据系统名称编辑设备树信息
	 * @param obj
	 * @return
	 * 王红杰
	 */
	int editSbTreeStructInfoBySysName(@Param("obj") JSONObject obj);
	int editSbTreeStructInfo(@Param("obj") JSONObject obj);
	int editSbTreeStructInfoBySysNameChange(@Param("obj") JSONObject obj);
	int editSbTreeStructInfoBySysNameXT(@Param("obj") JSONObject obj);//BES系统添加数据库的XML
	int editSbTreeStructInfoBySysName1(@Param("obj") JSONObject obj);
	int editSbTreeStructInfoBySysNamexh(@Param("obj") JSONObject obj);
	int editSbTreeStructInfoBySysName2(@Param("obj") JSONObject obj);
	public Map<String, Object> listpoint(@Param("old_f_sys_name") String old_f_sys_name);

	//Start add by yangjx at 21091212
	/**
	 * 根据系统名称编辑设备树信息
	 * @param obj
	 * @return
	 */
	int updateTableBesVpoint(@Param("obj") JSONObject obj);
	//End add by yangjx at 21091212

	/**
	 * 根据系统名称编辑属性信息
	 * @param obj
	 * @return
	 */
	int editSbTreeAttrInfoBySysName(@Param("obj") JSONObject obj);

	int editSbTreeAttrInfoByGuid(@Param("obj") JSONObject obj);

	/**
	 * 根据所选节点删除设备树信息
	 * @param obj
	 * @return
	 */
	int delSbTreeBySysName(@Param("f_sys_name") String f_sys_name, @Param("nodeTabName") String nodeTabName);

	/**
	 * 通过系统名称获取该节点下的所有子节点信息
	 * @param f_sys_name 父系统名称
	 * @return
	 */
	List<BESSbPzStruct> getSbChildNodeBySysName(@Param("f_sys_name") String f_sys_name);

	List<BESSbPzStruct> searchClilderList(@Param("f_sys_name") String f_sys_name);

	/*start xiepufeng 2020年3月12日 */
	// List<BESSbPzStruct> searchLikeClilderList(@Param("f_sys_name") String f_sys_name);
	/*end*/

	/*Start delete by xiepufeng at 2020年3月13日 根据 ddc 系统名称查出所有的子节点包括当前节点*/
	// List<BESSbPzStruct> searchLikeClilder(@Param("f_sys_name") String f_sys_name);
	/*End*/

	/*Start update by xiepufeng at 2020年3月13日 根据 ddc 系统名称查出所有的子节点包括当前节点*/
	List<BESSbPzStruct> searchChildren(@Param("f_sys_name") String f_sys_name);
	/*End*/

	/**
	 * 根据系统名称批量删除
	 * @param f_sys_names 系统名称
	 * @param nodeTabName 表名
	 * @return
	 */
	int batchDelete(@Param("f_sys_names") List<String> f_sys_names, @Param("nodeTabName") String nodeTabName);

	/**
	 * 获取父类型匹配的设备型号信息
	 * @param f_type 设备型号
	 * @return
	 */
	List<BESEquipmentType> getEquipmentTypeById(@Param("f_type") String f_type);

	/**
	 * 根据ID获取一条设备型号信息
	 * @param f_type 设备型号
	 * @return
	 */
	BESEquipmentType getEquipmentTypeInfoById(@Param("f_type") String f_type);

	/**
	 * 根据ID获取一条设备类型信息
	 * @param f_type 设备类型
	 * @return
	 */
	BESEquipmentMold getEquipmentMoldInfoById(@Param("f_type") String f_type);

	/**
	 * 获取模块点类型信息
	 * @return
	 */
	List<BESModulePointType> getModulePointTypeInfo();

	/**
	 * 获取设备树节点和模块点类型关系信息
	 * @return
	 */
	List<BESEpModuleTypeRlgl> getEpModuleTypeRlglInfo();

	/**
	 *  批量插入默认节点
	 * @param nodeList 节点List
	 * @return
	 */
	int batchInsert(@Param("list") List<BESSbPzStruct> list);

	/**
	 * 获取所选节点获取对应的属性信息
	 * @return
	 */
	Object getCollectorLoopTime(@Param("f_sys_name") String f_sys_name);

	/**
	 * 通过GUID更新采集器轮询周期
	 * @return
	 */
	int updateCollectorLoopTime(@Param("f_guid") String f_guid,@Param("f_loop_time") String f_loop_time);

	/**
	 * 向定时器表中插入更新采集器所需信息
	 * @return
	 */
	int addCollectorIsspTimerListInfo(@Param("f_guid") String f_guid, @Param("f_loop_time") String f_loop_time);

	/**
	 * 数据采集定时任务管理表中插入更新采集器所需信息
	 * @return
	 */
	int addCollectorSjcjTaskInfo(@Param("f_guid") String f_guid);

	/**
	 * 通过制定列名获取该列全部信息
	 * @return
	 */
	List<Map<String, String>> getInfoByColumnName(@Param("colIdName") String colIdName, @Param("colName") String colName, @Param("tabName") String tabName);
	/**
	 * 获得最大id
	 * @return
	 */
	String queryMaxId(@Param("nodeTabName") String nodeTabName);

	/**
	 * 查询该节点的实体表名
	 * @param string
	 * @return
	 */
	String findNodeTable(String f_sys_name);
	String findNodeTableChange(String f_sys_name);
	String findNodeTableByRootIdtabName(String f_sys_name);
	/**
	 * 王红杰
	 * */
	String findNodeTableDO(@Param("DO") String DO,@Param("node_type") String node_type);
	String findNodeTableDI(@Param("DI") String DI,@Param("node_type") String node_type);
	String findNodeTableAO(@Param("AO") String AO,@Param("node_type") String node_type);
	String findNodeTableAI(@Param("AI") String AI,@Param("node_type") String node_type);
	String findNodeTableDDC(@Param("DDC") String DDC);
	String findNodeTablemodule(@Param("module") String module);
	String findNodeTablecollector(@Param("collector") String collector);
	String findNodeTabledebugDO(@Param("debugDO") String debugDO);
	String findNodeTabledebugAO(@Param("debugAO") String debugAO);

/*	String findNodeTableammeter(@Param("all") String all);*/
	/**
	 * 查询模块所在的线路
	 * @param string
	 * @return
	 */
	List<Map<String, Object>> queryLines(String f_psys_name);
	/**
	 * 获取设备树的最大id
	 * @param string
	 * @return
	 */
	String queryStructMaxId();
	/**
	 * 查询Cross下所有模块
	 * @param f_sys_name
	 * @return
	 */
	public List<Map<String, String>> queryCrossModule(String f_sys_name);
	/**
	 * 查询模块父节点的类型
	 * @param f_psys_name
	 * @return
	 */
	public String querySbpzStructInfo(String f_psys_name);
	/**
	 * 查询逻辑点所在模块的父节点类型(通过逻辑点父节点)
	 * @param f_psys_name
	 * @return
	 */
	public String queryPointModPtype(String f_psys_name);
	/**
	 * 查询逻辑点所在模块的父节点类型(通过逻辑点fSysname)
	 * @param fSysname
	 * @return
	 */
	public String queryPointModPtypeByfSysname(String fSysname);
	/**
	 * 查询支路耦合器的父节点
	 * @param f_sys_name
	 * @return
	 */
	Map<String, Object> querybranchCouplerpNode(String f_sys_name);
	/**
	 * 通过id查询设备树节点信息
	 * @param tabname
	 * @return
	 */
	Map<String, Object> queryPointInfo(@Param("f_sys_name") String f_sys_name,@Param("tabname")String tabname);
	/**
	 * 通过父节点
	 * @param f_psys_name
	 * @return
	 */
	Map<String, Object> queryNodeInfo(String f_psys_name);
	/**
	 * 查询采集器状态
	 * @param f_guid
	 * @return
	 */
	String getCollectorStatus(String f_guid);
	/**
	 * 查询所有DO点信息
	 * @return
	 */
	List<Map<String, String>> loadAllDO();
	/**
	 * 查询所有逻辑点信息
	 * @return
	 */
	List<Map<String,String>> getAllPointInfo();

	List<Map<String,String>> getAllPointInfoBySysName(@Param("sysName") String sysName);
	/**
	 * 查询逻辑点状态
	 * @return
	 */
	Map<String,String> getPointState(String f_sys_name);
	/**
	 * 查询所有ddc
	 * @return
	 */
	List<BESSbPzStruct> queryAllDdc();
	/**
	 * 查询采集器中最大id
	 * @return
	 */
	String queryCollectorMaxId();
	/**
	 * 查询电表中最大id
	 * @return
	 */
	String queryAmmeterMaxId();

	/**
	 * 通过sbid和父节点查询模块信息
	 * @param pSysName
	 * @return
	 */
	List<Map<String, Object>> queryModuleInfo(@Param("f_id") String f_id,@Param("pSysName")String pSysName);

	/**
	 *根据父节点名称获取模块信息
	 * @param pSysName 父级名称
	 * @return
	 */
	List<Map<String, Object>> queryLampModule(String pSysName);
	/**
	 * 查询电表支路关系
	 * @param f_sys_name
	 * @return
	 */
	int queryBranchAmmeterRlgl(String f_sys_name);
	/**
	 * 查询虚点名称
	 * @param f_id
	 * @return
	 */
	String findVpointname(String f_id);

	void importControlInfo(@Param("fId")String fId,@Param("f_sys_name")String f_sys_name, @Param("f_button_name")String f_button_name,
						   @Param("nodePage")String nodePage,@Param("f_button_type")String f_button_type);
	void delControlInfo(@Param("nodePage")String nodePage);
	/**
	 * 查询当前页面是否保存
	 * @param nodePage
	 * @return
	 */
	int getControlInfo(String nodePage);
	/**
	 * 获取温控监控点位信息
	 * @param
	 * @return
	 */
	List<Map<String, String>> getWkjkPointInfoBySysName(@Param("sysName") String sysName);

	/*Start delete by xiepufeng at 2020年3月13日 remark 支持系统名称可修改，摒弃 ddc 系统名称模糊查询*/
	// public int editPollStatusBySysName(@Param("fSysName") String fSysName,@Param("fPollStatus") String fPollStatus);
	/*End delete by xiepufeng at 2020年3月13日 */

	int updatePollStatusBySysName(@Param("fSysName") String fSysName,@Param("fPollStatus") String fPollStatus);

	public BESSbPzStruct sbpzQueryPollStatus(@Param("fSysName") String fSysName);

	int getSumSbCount(@Param("fSysName") String f_sys_name);

	List<Map<String,String>> getAllDdc();

	List<Map<String, String>> getSbpzList(@Param("f_sys_name")String f_sys_name);

	boolean fSbidUpdate(@Param("fSysName") String fSysName, @Param("f_sbid") int f_sbid);

	boolean ffSbidUpdate(@Param("table") String table, @Param("f_sys_name") String f_sys_name);

	int editSBTreeInfo(@Param("sbTreeList") List<BESSbPzStruct> sbTreeList);

	int editPointInfo(@Param("pointList") List<JSONObject> pointList);
	int editPointInfoChange(@Param("pointList") List<JSONObject> pointList);

	int editPointInfo2(@Param("besList") List<BESSbPzStruct> besList);

	int updateBatch(@Param("list") List<BESSbPzStruct> besList);

	int updateBes(BESSbPzStruct besSbPzStruct2);
	/**
	 * 通过id查询设备树虚点信息
	 * @param f_psys_name
	 * @return
	 */
	Map<String, Object> findSbdyInfoBySelNodeVPonit(@Param("f_psys_name") String f_psys_name,@Param("nodeTabName") String nodeTabName);

	/**
	 *
	 * @return
	 */
	List<Map<String, String>> selectNodesConfigSetting(String f_sys_name);

	/**
	 * 根据系统名称删除节点配置设置表中的数据
	 * @param f_sys_name
	 * @return
	 */
	int deleteNodesConfigSettingBySysName(String f_sys_name);

	/**
	 * 插入节点配置设置表数据
	 * @param besNodeConfigList
	 * @return
	 */
	int insertNodesConfigSetting(List<BesNodeConfig> besNodeConfigList);

	/**
	 * xiepufeng
	 * 2020年3月12日
	 * 根据 f_psys_name 查询出 ddc 信息
	 * @param pSysName
	 * @return
	 */
	List<BESSbPzStruct> searchByPSysNameChildren(@Param("f_psys_name") String pSysName);

	/**
	 * 根据下级节点类型查询对应名称
	 * @param f_type
	 * @return
	 * tianjiangwei  2020/4/8
	 */
	List<BESSbTreeNodeType> noteNameByNoteType(@Param("f_type") String f_type);
	/**
	 * 查询虚点类型
	 * @param f_sys_name
	 * @param nodeTabName
	 * @return
	 */
	String findNodeTypeBySysName(@Param("f_sys_name") String f_sys_name);

	/**
	 * 根据名称查重
	 * @param type
	 * @return
	 */
	List<Map<String,Object>> judge(String type);

	/**
	 *
	 * @Description: 插入程序的txt文件到bes_txt表
	 *
	 * @auther: wanghongjie
	 * @date: 16:11 2020/4/23
	 * @param: [b, f_id]
	 * @return: void
	 *
	 */
	void importProgrammInfo(@Param("ddcbcInfoTextNext") String ddcbcInfoTextNext, @Param("f_id")Integer f_id);

	/**
	 *
	 * @Description: 获取DDC的信息以及关联的程序的txt文件
	 *
	 * @auther: wanghongjie
	 * @date: 15:01 2020/4/23
	 * @param: [f_sys_name, f_id]
	 * @return: com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESSbPzStruct
	 *
	 */
	BESSbPzStruct getSbTreeAndTxtInfoBySysNameAndId(@Param("f_sys_name") String f_sys_name,@Param("f_id")String f_id);
	/**
	 *
	 * @Description: 查询bes_txt_begin表里面是否有关联的DDC的id
	 *
	 * @auther: wanghongjie
	 * @date: 16:11 2020/4/23
	 * @param:
	 * @return:
	 *
	 */
	List<BesTxt> selectProgrammInfo(@Param("f_id")Integer f_id);

	/**
	 *
	 * @Description: 修改bes_txt_begin表里面的txt文本信息 输入的文本
	 *
	 * @auther: wanghongjie
	 * @date: 16:56 2020/4/23
	 * @param:
	 * @return:
	 *
	 */
	int updateProgrammInfo(@Param("ddcbcInfoTextNext") String ddcbcInfoTextNext, @Param("f_id")Integer f_id);
	/**
	 *
	 * @Description: 修改bes_txt_begin表里面的txt文本信息    上传的file文件
	 *
	 * @auther: wanghongjie
	 * @date: 18:27 2020/4/27
	 * @param:
	 * @return:
	 *
	 */
	int updateProgrammInfoFile(@Param("txtend") String txtend, @Param("f_id")Integer f_id);
	/**
	 *
	 * @Description: 插入上传的file文件到bes_txt_begin表
	 *
	 * @auther: wanghongjie
	 * @date: 18:28 2020/4/27
	 * @param:
	 * @return:
	 *
	 */
	void importProgrammInfoFile(@Param("txtend") String txtend, @Param("f_id")Integer f_id);

	/**
	 *
	 * @Description: 查询bes_txt_end表里面是否有关联的DDC的id
	 *
	 * @auther: wanghongjie
	 * @date: 16:11 2020/4/23
	 * @param:
	 * @return:
	 *
	 */
	List<BesTxt> selectProgrammInfoByEnd(@Param("id")Integer id);

	/**
	 *
	 * @Description: 修改bes_txt_end表里面的txt文本信息    编译好的文件
	 *
	 * @auther: wanghongjie
	 * @date: 18:27 2020/4/27
	 * @param:
	 * @return:
	 *
	 */
	int updateProgrammInfoByEnd(@Param("txtend") String txtend, @Param("id")Integer id);

	/**
	 *
	 * @Description: 插入编译好的文件到bes_txt_end表
	 *
	 * @auther: wanghongjie
	 * @date: 18:28 2020/4/27
	 * @param:
	 * @return:
	 *
	 */
	void importProgrammInfoByEnd(@Param("txtend") String txtend, @Param("id")Integer id);
	/**
	 * @Description: 修改采集器的信息
	 * @auther: wanghongjie
	 * @date: 11:11 2020/5/8
	 * @param:
	 * @return:
	 *
	 */
	int editSbTreeStructInfoCollector(@Param("obj") JSONObject obj);
	int editSbTreeStructInfoSbpzStruct(@Param("obj") JSONObject obj);

	Map<String,Object> selectSYS_NAME(String SYS_NAME);

	/**
	 * @Description: 查询当前名称所属的表
	 * @auther: wanghongjie
	 * @date: 16:08 2020/6/15
	 * @param: [id]
	 * @return: java.lang.String
	 *
	 */
	String selectTableName(String id);

	/**
	 * @Description: 查询当前点位的全部信息
	 * @auther: wanghongjie
	 * @date: 16:08 2020/6/15
	 * @param: [tableName, id]
	 * @return: java.util.List<com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesPointStruct>
	 */
	BesPointStruct selectPointList(@Param("tableName") String tableName,@Param("id") String id);

	/**
	 * @Description: 查询当前点位所属的DDC全部信息
	 * @auther: wanghongjie
	 * @date: 18:03 2020/6/15
	 * @param: [id]
	 * @return: java.lang.String
	 */
	BesDdc selectDDCName(String id);

	/**
	 * @Description: 查询当前点位所属的模块名
	 * @auther: wanghongjie
	 * @date: 18:05 2020/6/15
	 * @param: [id]
	 * @return: java.lang.String
	 */
	String selectModuleName(String id);

	/**
	 * @Description: 查询当前虚点所属的DDC全部信息
	 * @auther: wanghongjie
	 * @date: 15:36 2020/6/16
	 */
	BesDdc selectDDCNameByVpoint(String id);

	/**
	 * @Description: 插入是否报警的标识
	 * @auther: wanghongjie
	 * @date: 10:05 2020/6/18
	 *
	 */
	int update_AO_AI_Alarm(@Param("alarm_high") int alarm_high,@Param("alarm_low") int alarm_low,@Param("tableName") String tableName,@Param("id") String id);

	/**
	 * @Description: 查询bes_ddc表的最大的f_sbid
	 * @auther: wanghongjie
	 * @date: 9:06 2020/6/22
	 *
	 */
	String select_f_sbid_By_Bes_Ddc();
	
	/**
	 * @Description: ddc控制器添加到bes_sbdy_strust表
	 * @auther: wanghongjie
	 * @date: 10:03 2020/6/22 
	 */
	int sbdy_ddcInfo_insert_bes_sbpz_struct(@Param("obj") JSONObject obj);

	/**
	 * @Description: ddc控制器添加到bes_ddc表
	 * @auther: wanghongjie
	 * @date: 10:01 2020/6/23
	 *
	 */
	int sbdy_ddcInfo_insert_bes_DCC(@Param("obj") JSONObject obj);
	/**
	 * @Description: ip是否重复
	 * @auther: wanghongjie
	 * @date: 9:02 2020/6/23
	 */
	int getSizeByIpAddrBesDdc(String f_ip_addr);

	int getSizeByIpAddrBesCollector(String f_ip_addr);

	/**
	 * @Description:修改DDC控制器的信息
	 * @auther: wanghongjie
	 * @date: 14:55 2020/6/23
	 * @param: [obj]
	 * @return: int
	 */
	int sbdy_ddcInfo_update_bes_sbpz_struct(@Param("obj") JSONObject obj);

	int sbdy_ddcInfo_update_bes_DDC(@Param("obj") JSONObject obj);

	/**
	 *
	 * @Description: 根据名称查询当前ddc控制器的信息
	 *
	 * @auther: wanghongjie
	 * @date: 17:52 2020/6/28
	 * @param: [fSysName]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
    Map<String, Object> DDCInfoMap(String fSysName);

	/**
	 * @Description: 查询模块表的最大的sbid
	 * @auther: wanghongjie
	 * @date: 9:06 2020/6/30
	 *
	 */
	String select_f_sbid_By_Bes_Module();

	/**
	 *
	 * @Description: 根据父节点系统名称查询总线名称
	 *
	 * @auther: wanghongjie
	 * @date: 14:05 2020/6/30
	 * @param: [f_psys_name]
	 * @return: java.lang.String
	 *
	 */
	String selectFlnName(String f_psys_name);

	/**
	 *
	 * @Description: 将模块信息添加到设备配置表中
	 *
	 * @auther: wanghongjie
	 * @date: 15:19 2020/6/30
	 * @param: [obj]
	 * @return: int
	 *
	 */
	int addSbTreeModuleInfo(@Param("obj") JSONObject obj);

	/**
	 *
	 * @Description: 将模块信息添加到模块表中
	 *
	 * @auther: wanghongjie
	 * @date: 15:20 2020/6/30
	 * @param: [obj]
	 * @return: int
	 *
	 */
	int addbesModule(@Param("obj") JSONObject obj);

	/**
	 *
	 * @Description: 查询当前ddc下的所有的模块的通信地址
	 *
	 * @auther: wanghongjie
	 * @date: 17:08 2020/6/30
	 * @param: [f_sys_name_old_ddc]
	 * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *
	 */
	List<Map<String, Object>> f_addrList(@Param("f_sys_name_old_ddc") String f_sys_name_old_ddc);

	/**
	 *
	 * @Description:修改[设备树结构表]的信息
	 *
	 * @auther: wanghongjie
	 * @date: 9:00 2020/7/1
	 * @param: [obj]
	 * @return: int
	 *
	 */
	int updateSbdyModule(@Param("obj") JSONObject obj);

	/**
	 *
	 * @Description: 修改模块表的信息
	 *
	 * @auther: wanghongjie
	 * @date: 9:01 2020/7/1
	 * @param: [obj]
	 * @return: int
	 *
	 */
	int updatebesModule(@Param("obj") JSONObject obj);

	/**
	 *
	 * @Description: 根据系统名称更新设备树节点状态
	 *
	 * @auther: wanghongjie
	 * @date: 15:17 2020/7/2
	 * @param: [state, sysName]
	 * @return: int
	 *
	 */
	int updateNodeStateBySysName(@Param("state") String state, @Param("sysName") String sysName);

	int batchUpdateNodeStateBySysName(@Param("list")List<BESSbPzStruct> list, @Param("state") String state);

	/**
	 *
	 * @Description: 修改点位在设备配置表中的信息
	 *
	 * @auther: wanghongjie
	 * @date: 15:17 2020/7/2
	 * @param: [obj]
	 * @return: java.lang.Integer
	 *
	 */
	Integer updatesbdyByPoint(@Param("obj") JSONObject obj);
	/**
	 *
	 * @Description: 将信息添加到DO表中
	 * 
	 * @auther: wanghongjie
	 * @date: 15:34 2020/7/2 
	 * @param: [obj]
	 * @return: java.lang.Integer
	 *
	 */
	Integer insertDOPointTable(@Param("obj") JSONObject obj);

	/**
	 *
	 * @Description: 查询当前点位所属的ddc
	 *
	 * @auther: wanghongjie
	 * @date: 16:29 2020/7/2
	 * @param: [name]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	Map<String, Object> queryDDCMapByPoint(@Param("f_id") String f_id);

	/**
	 *
	 * @Description: 修改DO表的相应的点位信息
	 *
	 * @auther: wanghongjie
	 * @date: 17:40 2020/7/2
	 * @param: [obj]
	 * @return: java.lang.Integer
	 *
	 */
	Integer updateDOPointTable(@Param("obj") JSONObject obj);

	/**
	 *
	 * @Description: 根据修改的系统名称查询设备配置的信息
	 *
	 * @auther: wanghongjie
	 * @date: 10:15 2020/7/3
	 * @param: [name]
	 * @return: com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESSbPzStruct
	 *
	 */
	BESSbPzStruct getSbTreeDataBySysNamePoint(@Param("name") String name);

	/**
	 *
	 * @Description: 将信息添加到AO表中
	 *
	 * @auther: wanghongjie
	 * @date: 10:25 2020/7/3
	 * @param: [obj]
	 * @return: java.lang.Integer
	 *
	 */
	Integer insertAOPointTable(@Param("obj") JSONObject obj);

	/**
	 *
	 * @Description: 修改AO表的相应的点位信息
	 *
	 * @auther: wanghongjie
	 * @date: 11:41 2020/7/3
	 * @param: [obj]
	 * @return: java.lang.Integer
	 *
	 */
	Integer updateAOPointTable(@Param("obj") JSONObject obj);

	/**
	 *
	 * @Description: AI 点的新增
	 *
	 * @auther: wanghongjie
	 * @date: 15:28 2020/7/3
	 * @param: [obj]
	 * @return: java.lang.Integer
	 *
	 */
    Integer insertAIPointTable(@Param("obj") JSONObject obj);

    /**
     *
     * @Description: AI 点的修改
     *
     * @auther: wanghongjie
     * @date: 9:26 2020/7/4
     * @param: [obj]
     * @return: java.lang.Integer
     *
     */
    Integer updateAIPointTable(@Param("obj") JSONObject obj);

    /**
     *
     * @Description: DI点添加
     *
     * @auther: wanghongjie
     * @date: 13:55 2020/7/4
     * @param: [obj]
     * @return: java.lang.Integer
     *
     */
    Integer insertDIPointTable(@Param("obj") JSONObject obj);

    /**
     *
     * @Description: DI点修改
     *
     * @auther: wanghongjie
     * @date: 14:33 2020/7/4
     * @param: [obj]
     * @return: java.lang.Integer
     *
     */
	Integer updateDIPointTable(@Param("obj") JSONObject obj);

	/**
	 *
	 * @Description: 查询当前模块的F_ID
	 *
	 * @auther: wanghongjie
	 * @date: 10:22 2020/7/7
	 * @param: [name]
	 * @return: java.lang.String
	 *
	 */
    String selectIdByModule(@Param("name") String name);

	/**
	 * 查询父节点信息
	 * @param f_sys_name
	 * @return
	 */
	Map<String, Object> getParentNodeInfo(@Param("f_sys_name") String f_sys_name);

	/**
	 * 添加设备树信息
	 * @param obj
	 * @return
	 */
	int addSbTreeInfo(@Param("obj") JSONObject obj);
	/**
	 * 更新设备树信息
	 * @param obj
	 * @return
	 */
	int updateSbTreeInfo(@Param("obj") JSONObject obj);

	/**
	 * 添加虚点信息
	 * @param obj
	 * @return
	 */
	int addVirtualPointInfo(@Param("obj") JSONObject obj);
	/**
	 * 更新虚点信息
	 * @param obj
	 * @return
	 */
	int updateVirtualPointInfo(@Param("obj") JSONObject obj);

	/**
	 *
	 * @Description: 根据id查询模块表的当前点位的全部信息
	 *
	 * @auther: wanghongjie
	 * @date: 13:48 2020/7/8
	 * @param: [moduleID]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	Map<String, Object> selectModuleMap(@Param("moduleID") String moduleID);

	/**
	 *
	 * @Description: 根据节点名称查询节点下所有的子节点
	 *
	 * @auther: wanghongjie
	 * @date: 11:32 2020/7/9
	 * @param: [f_sys_name]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
    List<Map<String, Object>> selectNodeMap(@Param("f_sys_name") String f_sys_name);

    /**
     *
     * @Description: 删除当前选中的节点
     *
     * @auther: wanghongjie
     * @date: 11:49 2020/7/9
     * @param: [f_sys_name]
     * @return: int
     *
     */
	int deleteNodeByFSysNameOld(@Param("f_sys_name") String f_sys_name);

	/**
	 *
	 * @Description: 删除点位表中的点位节点
	 *
	 * @auther: wanghongjie
	 * @date: 14:16 2020/7/9
	 * @param: [f_sys_name]
	 * @return: int
	 *
	 */
	int deleteTableNodeByFSysNameOld(@Param("tabName") String tabName,@Param("f_sys_name") String f_sys_name);

	/**
	 *
	 * @Description: 查询当前点位在所在的表中是否存在
	 *
	 * @auther: wanghongjie
	 * @date: 15:28 2020/7/9
	 * @param: [tabName, f_sys_name]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	List<Map<String, Object>> selectPointMap(@Param("tabName") String tabName, @Param("f_sys_name") String f_sys_name);


	/**
	 *
	 * @Description: 查询节点的表名
	 *
	 * @auther: wanghongjie
	 * @date: 9:51 2020/7/10
	 * @param: [f_sys_name]
	 * @return: java.lang.String
	 *
	 */
	String findNodeTableByFSysName(String f_sys_name);
	/**
	 *
	 * @Description: 根据名称和表名删除节点的数据
	 *
	 * @auther: wanghongjie
	 * @date: 9:54 2020/7/10
	 * @param: [f_sys_name, tabName]
	 * @return: int
	 *
	 */
	int delSbTreeByFSysName(@Param("f_sys_name") String f_sys_name,@Param("tabName") String tabName);

	/**
	 *
	 * @Description: 修改设备配置表的点位信息
	 *
	 * @auther: wanghongjie
	 * @date: 10:20 2020/7/10
	 * @param: [f_sys_name]
	 * @return: int
	 *
	 */
	int updateSbTreeByFSysName(@Param("f_sys_name_sbdy") String f_sys_name_sbdy,@Param("f_nick_name")  String f_nick_name,
							   @Param("path")  String path,@Param("f_type") String f_type,@Param("f_descruption") String f_descruption);

	/**
	 *
	 * @Description: 查询该逻辑点所在的控制器的ip
	 *
	 * @auther: wanghongjie
	 * @date: 11:39 2020/7/10
	 * @param: [f_sys_name_sbdy]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	Map<String, Object> selectcontrollerPoint(@Param("f_sys_name_sbdy")  String f_sys_name_sbdy);
	
	/**
	 *
	 * @Description: 查询当前模块所属的ip
	 * 
	 * @auther: wanghongjie
	 * @date: 13:56 2020/7/10
	 * @param: [f_sys_name]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	Map<String, Object> selectDDCByModuleFSysName(String f_sys_name);


	/**
	 *
	 * @Description: 查询当前ddc在ddc表中的信息
	 *
	 * @auther: wanghongjie
	 * @date: 14:54 2020/7/10
	 * @param: [f_sys_name]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	Map<String, Object> ddcPointMapByDDCTable(String f_sys_name);

	/**
	 *
	 * @Description: 获取当前虚点在虚点表的详细信息
	 *
	 * @auther: wanghongjie
	 * @date: 16:53 2020/7/10
	 * @param: [f_sys_name]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	Map<String, Object> VPointTableMap(String f_sys_name);

	/**
	 *
	 * @Description:获取当前虚点所在的ip详细信息
	 *
	 * @auther: wanghongjie
	 * @date: 16:56 2020/7/10
	 * @param: [f_sys_name]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	Map<String, Object> VPointMap(String f_sys_name);

	/**
	 *
	 * @Description: 根据名称和表名删除设置配置表中相关的数据
	 *
	 * @auther: wanghongjie
	 * @date: 11:47 2020/7/13
	 * @param: [f_sys_name]
	 * @return: void
	 *
	 */
	void delNodeCconfigSettingByFSysName(String f_sys_name);

	/**
	 *
	 * @Description: 更改数据库的当前模式为手动
	 *
	 * @auther: wanghongjie
	 * @date: 15:30 2020/7/14
	 * @param: [tabName, updateWorkMode]
	 * @return: void
	 *
	 */
	void updatePointByWorkMode(@Param("tabName") String tabName,@Param("updateWorkMode") String updateWorkMode,@Param("id") String id);

	/**
	 *
	 * @Description: 查询父节点的系统名称标识
	 *
	 * @auther: wanghongjie
	 * @date: 14:42 2020/7/28
	 * @param: [f_psys_name]
	 * @return: java.lang.String
	 *
	 */
    String selectFNodeAttributionByPsysName(String f_psys_name);

    /**
     *
     * @Description: 新增干线或者新增支线耦合器
     *
     * @auther: wanghongjie
     * @date: 15:19 2020/7/28
     * @param: [obj]
     * @return: void
     *
     */
	int sbdy_couplerInfo_insert_bes_coupler(@Param("obj") JSONObject obj);

	/**
	 *
	 * @Description: 判断通信地址是否重复
	 *
	 * @auther: wanghongjie
	 * @date: 9:47 2020/7/29
	 * @param: [f_psys_name]
	 * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *
	 */
	List<Map<String, Object>> selectCouplerList(String f_psys_name);

	/**
	 *
	 * @Description: 查询当前支路或者干线下是否有子节点,如果有的话,则提示不能修改
	 *
	 * @auther: wanghongjie
	 * @date: 10:10 2020/7/29
	 * @param: [f_sys_name]
	 * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *
	 */
	List<Map<String, Object>> selectCouplerChildList(String f_sys_name);

	/**
	 *
	 * @Description: 查询当前通信地址是否修改
	 *
	 * @auther: wanghongjie
	 * @date: 10:15 2020/7/29
	 * @param: [f_sys_name]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	Map<String, Object> selectCouplerMap(String f_sys_name);

	/**
	 *
	 * @Description: 修改数据库节点信息
	 *
	 * @auther: wanghongjie
	 * @date: 10:24 2020/7/29
	 * @param: [obj]
	 * @return: int
	 *
	 */
	int updateCouplerSbdyStruct(@Param("obj") JSONObject obj);

	int updateCoupler(@Param("obj") JSONObject obj);

	/**
	 *
	 * @Description: 查询当前LDC下所有的模块的通信地址
	 *
	 * @auther: wanghongjie
	 * @date: 11:33 2020/7/30
	 * @param: [f_sys_name_old_ddc]
	 * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *
	 */
	List<Map<String, Object>> selectLDCModuleList(String f_sys_name_old_ddc);

	/**
	 *
	 * @Description: 查询当前模块所属的ip(照明)
	 *
	 * @auther: wanghongjie
	 * @date: 9:46 2020/7/31
	 * @param: [f_sys_name]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	Map<String, Object> selectLDCByModuleFSysName(String f_sys_name);

	/**
	 *
	 * @Description: 查询当前点位所属的ddc
	 *
	 * @auther: wanghongjie
	 * @date: 14:19 2020/7/31
	 * @param: [f_id]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	Map<String, Object> queryLDCMapByPoint(String f_id);

	/**
	 *
	 * @Description: 查询该逻辑点所在的控制器的ip
	 *
	 * @auther: wanghongjie
	 * @date: 18:58 2020/7/31
	 * @param: [f_sys_name]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	Map<String, Object> selectcontrollerPointByDelete(@Param("f_sys_name_old") String f_sys_name_old);

	/**
	 *
	 * @Description: 查询该逻辑点所在的控制器的ip(照明的虚点点位)
	 *
	 * @auther: wanghongjie
	 * @date: 9:04 2020/8/1
	 * @param: [name]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	Map<String, Object> selectcontrollerByLDCVpoint(String name);


	/**
	 *
	 * @Description: 更改实点点位的实时值
	 *
	 * @auther: wanghongjie
	 * @date: 18:43 2020/8/4
	 * @param: [tabName, id, value]
	 * @return: void
	 *
	 */
	void updatePointByid(@Param("tabName") String tabName,@Param("id") String id,@Param("value") Integer value);

	/**
	 *
	 * @Description: 更改虚点点位的实时值
	 *
	 * @auther: wanghongjie
	 * @date: 18:43 2020/8/4
	 * @param: [tabName, id, value]
	 * @return: void
	 *
	 */
	void updateVPointByid(@Param("tabName")String tabName,@Param("id") String id,@Param("value") Integer value);

	/**
	 *
	 * @Description: 根据名称查询点位信息以及表名
	 *
	 * @auther: wanghongjie
	 * @date: 11:04 2020/8/7
	 * @param: [f_sys_name_old]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	Map<String, Object> sbpzStructPointMapByName(String f_sys_name_old);

	/**
	 *
	 * @Description: 如果模块的通信地址在当前ddc下有相同的地址,则在页面提示通信地址重复,添加失败
	 *
	 * @auther: wanghongjie
	 * @date: 15:08 2020/8/7
	 * @param: [f_psys_name]
	 * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *
	 */
	List<Map<String, Object>> f_addrListByPName(String f_psys_name);

	/**
	 * 根据系统名称获取树和关联的点信息（没有参数则获取全部树关联的全部数据）
	 * @return
	 */
	List<Map<String, Object>> getTreeInfoAndPointData(@Param("f_sys_names") List<String> f_sys_names);

	String  getRelativeModuleTypeInfo(String sysname);

	/**
	 * 查询温控器节点下面所有的子节点
	 * @param sysname
	 * @return
	 */
	List<BESSbPzStruct> findChildNodeInfo(String sysname);

	/***
	 * 查询关联点位类型
	 * @param sysname
	 * @return
	 */
	List<Map<String, Object>> getPointTypeInfo(String sysname);

	/**
	 * 查询关联模块的点位信息
	 * @param sysname
	 * @return
	 */
	List<BESSbPzStruct> getChildPointInfo(String sysname);
	/**
	 * 查询流程图节节点的实体表名
	 * @param string
	 * @return
	 */
	String findFlowNodeTable(String sysname);


    /**
     *
     * @Description: 查询点位的信息
     *
     * @auther: wanghongjie
     * @date: 15:51 2020/8/27
     * @param: [f_sys_name_old]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     *
     */
	Map<String, Object> sbpzStructPointMapsByName(@Param("f_node_table") String f_node_table,@Param("f_sys_name_old") String f_sys_name_old);

	/**
	 *
	 * @Description: 查询园区的信息
	 *
	 * @auther: wanghongjie
	 * @date: 16:11 2020/9/22
	 * @param: []
	 * @return: java.util.List<com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESPark>
	 *
	 */
    List<BESPark> select_park();

    /**
     *
     * @Description: 添加根节点信息
     *
     * @auther: wanghongjie
     * @date: 17:49 2020/9/22
     * @param: [f_yqbh, f_descrption, f_nick_name, f_poll_status, f_allpath, f_type, f_issyn, f_psys_name, f_status]
     * @return: java.lang.Boolean
     *
     */
    Boolean insert_sbdy_node_first(@Param("f_yqbh") String f_yqbh,@Param("f_descrption")  String f_descrption,@Param("f_nick_name")  String f_nick_name,
								   @Param("f_poll_status") String f_poll_status,@Param("f_allpath") String f_allpath,@Param("f_type") String f_type,
								   @Param("f_issyn") String f_issyn,@Param("f_psys_name") String f_psys_name,@Param("f_status") String f_status);

    /**
     *
     * @Description: 根据类型为0查询设备配置表的信息
     *
     * @auther: wanghongjie
     * @date: 17:47 2020/9/22
     * @param: [f_type]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     *
     */
	List<Map<String, Object>> selectPointListByF_type(String f_type);

	/**
	 *
	 * @Description: 查询父节点为当前根节点的节点信息
	 *
	 * @auther: wanghongjie
	 * @date: 9:24 2020/9/23
	 * @param: [f_sys_name]
	 * @return: java.util.List<com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESSbPzStruct>
	 *
	 */
	List<BESSbPzStruct> selectPointType(String f_sys_name);

	//查询父节点的父节点名称
	String selectYqbhByFsysName(Object f_psys_name);

	/**
	 * 获取所有数据
	 * @return
	 */
	List<BESSbPzStruct> findAll();

	void batchInsertCopy(@Param("list") List<BESSbPzStruct> list);
}
