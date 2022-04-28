package com.efounder.JEnterprise.mapper.basedatamanage.eqmanage;

import com.efounder.JEnterprise.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CrossEquipmentMapper extends BaseMapper<String, Object>{

	public Map<String, Object> queryDDCInfoByModule(@Param("f_psys_name")String f_psys_name);

	public String queryBusByModule(@Param("fSysName")String fSysName);

	public Map<String, Object> queryModuleInfo(@Param("f_sys_name")String f_sys_name);

	public Map<String, Object> queryDDCInfoByPoint(@Param("old_f_sys_name")String old_f_sys_name);
	public Map<String, Object> queryDDCInfoByPointChange(@Param("f_sys_name")String f_sys_name);

	public Map<String, Object> queryModuleInfoByPoint(@Param("f_sys_name")String f_sys_name);
	public Map<String, Object> queryModuleInfoByPointChange(@Param("f_sys_name")String f_sys_name);

	public Map<String, Object> queryPointInfo(@Param("tabName")String tabName,@Param("f_sys_name")String f_sys_name);
	public Map<String, Object> queryPointInfoChange(@Param("tabName")String tabName,@Param("f_sys_name")String f_sys_name);
	/**
	 * 王红杰
	 * */
	public Map<String, Object> queryPointInfoDO(@Param("tabNameDO")String tabNameDO,@Param("DO")String DO);
	public Map<String, Object> queryPointInfoDI(@Param("tabNameDI")String tabNameDI,@Param("DI")String DI);
	public Map<String, Object> queryPointInfoAO(@Param("tabNameAO")String tabNameAO,@Param("AO")String AO);
	public Map<String, Object> queryPointInfoAI(@Param("tabNameAI")String tabNameAI,@Param("AI")String AI);
	public Map<String, Object> queryPointInfoDDC(@Param("tabNameDDC")String tabNameDDC,@Param("DDC")String DDC);
	public Map<String, Object> queryPointInfomodule(@Param("tabNamemodule")String tabNamemodule,@Param("module")String module);
	public Map<String, Object> queryPointInfocollector(@Param("tabNamecollector")String tabNamecollector,@Param("collector")String collector);
	public Map<String, Object> queryPointInfoxh(@Param("xh_id")String xh_id);
	public Map<String, Object> getPointInfoSBDY(@Param("f_sys_name")String f_sys_name);
	public Map<String, Object> queryPointInfoammeter(@Param("tabNameammeter")String tabNameammeter,@Param("f_sys_name_ammeter")Object f_sys_name_ammeter);
	public Map<String, Object> queryPointInfoammeterown(@Param("tabNameammeter")String tabNameammeter,@Param("all")String all);

	public Map<String, Object> queryDDCInfoByVPoint(@Param("f_psys_name")String f_psys_name);

	public Map<String, Object> queryDDCInfoByPointxd(@Param("f_sys_name")String f_sys_name);

	public Map<String, Object> queryLampDDCInfoByModule(@Param("f_psys_name")String f_psys_name);

	public Map<String, Object> queryLampDDCInfoByPoint(@Param("old_f_sys_name")String old_f_sys_name);
	////wanghongjie 修改根据f_id查询逻辑点所属IP路由器的IP地址
	public Map<String, Object> queryLampDDCInfoByPointByFid(@Param("f_id")String f_id);

	public Map<String, Object> queryLampDDCInfoByCoupler(@Param("f_psys_name")String f_psys_name);

	/*
	 *
	 * @Description: 判断当前系统名称在相应的四种点位表里面有没有,有的话将f_sys_name输入框设置成不能输入
	 *
	 * @auther: wanghongjie
	 * @date: 16:22 2020/4/11
	 * @param:
	 * @return:
	 *
	 */
	public int getInfo_f_sys_name(@Param("f_sys_name")String f_sys_name,@Param("tabName")String tabName);
	/**
	 * 查询支路耦合器下模块所在ip路由器的信息
	 * @param f_sys_name
	 * @return
	 */
	public Map<String, Object> queryDDCByModuleBranchCoupler(@Param("f_sys_name")String f_sys_name);

	/**
	 * 查询下下级信息
	 * @param pSysName 父级名称
	 * @return
	 */
	public List<Map<String,Object>>  queryCrossModule(String pSysName);
	/**
	 * 查询下级信息
	 * @param pSysName 父级名称
	 * @return
	 */
	public List<Map<String,Object>>  queryModule(String pSysName);
    /**
     * 根据虚点ddc查询相应的子虚点
     * @param f_sbid
     * @return
     */
	public Map<String, Object> queryimPoitntByDDCinfo(@Param("f_psys_name")String f_psys_name);

    /**
     * 照明系统  新增支线干线信息查询所属ip路由器信息
     * @param string
     * @return
     * tianjiangwei  2020/4/8
     */
	public Map<String, Object> queryIpInfo(@Param("f_psys_name")String f_psys_name);


	public Map<String, Object> f_node_attribution(@Param("f_sys_name")String f_sys_name,@Param("tabname")String tabname);

	public List<Map<String, Object>> queryStaticsEnergyPointByMinute();

	public List<Map<String, Object>> queryStaticsEnergyPointByHour();

	public List<Map<String, Object>> queryStaticsEnergyPointByDay();

	List<Map<String,Object>> queryBusInfo(@Param("f_sys_name")String f_sys_name);

	List<Map<String,Object>> querypointList(@Param("f_sys_name")String f_sys_name);

	List<Map<String,Object>> queryModelInfo(@Param("f_psys_name") String f_psys_name);

	Map<String, Object> selectBesModule(@Param("name") String name);

	String selectFln(@Param("name") String name);

	String selectTabName(@Param("f_type") String f_type);

	Map<String, Object> selectPointMap(@Param("tabName") String tabName,@Param("f_sys_name") String f_sys_name);

	Map<String, Object> selectModuleMap(@Param("f_sys_name") String f_sys_name);

	List<Map<String, Object>> selectPointList(String f_psys_name);

	String selectType(String f_sys_name);

	List<Map<String, Object>> selectVpointList(String f_psys_name);
	/**
	 *
	 * @Description: 根据名称查询虚点表的点位信息
	 *
	 * @auther: wanghongjie
	 * @date: 16:18 2020/7/2
	 * @param: [name]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	Map<String, Object> selectVpointMap(String name);

	/**
	 *
	 * @Description: 根据系统名称查询虚点表的点位信息
	 *
	 * @auther:
	 * @date: 16:18 2020/7/2
	 * @param: [name]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	Map<String, Object> findVpointBySysName(String name);

	/**
	 *
	 * @Description: 根据设备配置的f_id查询所属的模块信息
	 *
	 * @auther: wanghongjie
	 * @date: 16:57 2020/7/2
	 * @param: [f_id]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	Map<String, Object> selectModuleMapById(String f_id);

	/**
	 *
	 * @Description: 查询模块表的信息
	 *
	 * @auther: wanghongjie
	 * @date: 9:58 2020/7/6
	 * @param: [tabName, old_f_sys_name]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
    Map<String, Object> moduleMap(@Param("f_sbid_module") String f_sbid_module);

    /**
     *
     * @Description: 查询模块下的子节点
     *
     * @auther: wanghongjie
     * @date: 11:19 2020/7/6
     * @param: [name]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     *
     */
	List<Map<String, Object>> pointMapList(@Param("name") String name);

	/**
	 *
	 * @Description: 查询点位的信息
	 *
	 * @auther: wanghongjie
	 * @date: 11:58 2020/7/6
	 * @param: [tabName, pointName]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	Map<String, Object> pointMap(@Param("tabName") String tabName,@Param("pointName") String pointName);

	/**
	 *
	 * @Description: 查询模块所属LDC控制器
	 *
	 * @auther: wanghongjie
	 * @date: 10:23 2020/7/30
	 * @param: [f_psys_name]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	Map<String, Object> queryLDCInfoByModule(String f_psys_name);

	/**
	 *
	 * @Description:
	 *
	 * @auther: wanghongjie
	 * @date: 15:59 2020/7/31
	 * @param:
	 * @return:
	 *
	 */
	Map<String, Object> queryLDCInfoByModuleByfSysName(String fSysName);

	/**
	 *
	 * @Description: 根据id查询当前点位的表名
	 *
	 * @auther: wanghongjie
	 * @date: 8:25 2020/8/5
	 * @param: [id]
	 * @return: java.lang.String
	 *
	 */
    String selectTableName(Integer id);

    /**
     *
     * @Description: 查询虚点的点位信息
     *
     * @auther: wanghongjie
     * @date: 8:37 2020/8/5
     * @param: [id]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     *
     */
	Map<String, Object> selectVPointMapByID(Integer id);

	/**
	 *
	 * @Description: 查询实点的点位信息
	 *
	 * @auther: wanghongjie
	 * @date: 8:39 2020/8/5
	 * @param: [tabName, id]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	Map<String, Object> selectPointMapByID(@Param("tabName") String tabName,@Param("id") Integer id);

	/**
	 *
	 * @Description: 根据前台传的模块型号编码查询模块类型表中的F_TYPE_CODE,这个字段才是下发到下位机的模块型号编码
	 *
	 * @auther: wanghongjie
	 * @date: 11:11 2020/8/10
	 * @param: [modelID]
	 * @return: java.lang.Integer
	 *
	 */
    Integer queryTypeCodeByModuleType(Integer modelID);

    /**
     *
     * @Description: 根据父节点的名称查询模块类型表的信息
     *
     * @auther: wanghongjie
     * @date: 11:27 2020/8/10
     * @param: [f_psys_name]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     *
     */
    Map<String, Object> selectModelTypeByPsysName(String f_psys_name);
}
