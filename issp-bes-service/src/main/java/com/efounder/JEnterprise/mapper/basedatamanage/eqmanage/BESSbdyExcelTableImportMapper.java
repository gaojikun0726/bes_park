package com.efounder.JEnterprise.mapper.basedatamanage.eqmanage;

import com.efounder.JEnterprise.model.basedatamanage.deviceConfiguration.*;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESAmmeter;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESSbPzStruct;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesCollector;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: wanghongjie
 * @Description:
 * @Date: Created in 8:37 2020/9/18
 * @Modified By:
 */
@Mapper
public interface BESSbdyExcelTableImportMapper  {

    /**
     * 通过系统名称获取设备树信息
     * @param f_sys_name
     * @return
     */
    BESSbPzStruct getSbTreeInfoBySysName(@Param("f_sys_name") String f_sys_name);

    /**
     * @Description: ip是否重复
     * @auther: wanghongjie
     * @date: 9:02 2020/6/23
     */
    int getSizeByIpAddrBesDdc(String f_ip_addr);

    int getSizeByIpAddrBesCollector(String f_ip_addr);

    /**
     * 查询采集器中最大id
     * @return
     */
    String queryCollectorMaxId();

    /**
     *
     * @Description: Excel表格导入,导入能耗控制器节点的信息到bes_controller表
     *
     * @auther: wanghongjie
     * @date: 14:39 2020/9/2
     * @param: [besCollector]
     * @return: boolean
     *
     */
    boolean add_sbdyStructCollector(BesCollector besCollector);

    /**
     *
     * @Description: Excel表格导入,导入能耗总线节点的信息到bes_bus表
     *
     * @auther: wanghongjie
     * @date: 15:29 2020/9/2
     * @param: [besBusExcel]
     * @return: boolean
     *
     */
    boolean add_sbdyStructBus(besBusExcel besBusExcel);

    /**
     *
     * @Description: Excel表格导入,导入能耗电表节点的信息到bes_ammeter表
     *
     * @auther: wanghongjie
     * @date: 15:32 2020/9/10
     * @param:
     * @return:
     *
     */
    boolean add_sbdyStructAmmeter(BESAmmeter besAmmeter);

    /**
     *
     * @Description: Excel表格导入,导入ddc控制器节点的信息到bes_ddc表
     *
     * @auther: wanghongjie
     * @date: 8:55 2020/9/12
     * @param: [besDDCExcel]
     * @return: boolean
     *
     */
    boolean add_sbdyStructDDC(besDDCExcel besDDCExcel);

    /**
     *
     * @Description: Excel表格导入,根据模块的名称查询模块类型表的f_id
     *
     * @auther: wanghongjie
     * @date: 16:30 2020/9/12
     * @param: [f_module_type]
     * @return: java.lang.String
     *
     */
    String selectModuleTypeId(String f_module_type);

    /**
     *
     * @Description: Excel表格导入,查询当前模块的点集合
     *
     * @auther: wanghongjie
     * @date: 16:59 2020/9/12
     * @param: [f_sys_name]
     * @return: java.lang.String
     *
     */
    String selectPointTypeClByModule(String f_sys_name);

    /**
     *
     * @Description: Excel表格导入,导入模块节点的信息到bes_module表
     *
     * @auther: wanghongjie
     * @date: 17:07 2020/9/12
     * @param: [besModuleExcel]
     * @return: boolean
     *
     */
    boolean add_sbdyStructModule(besModuleExcel besModuleExcel);

    /**
     *
     * @Description: 添加耦合器节点
     *
     * @auther: wanghongjie
     * @date: 10:36 2020/9/17
     * @param: [besCouplerExcel]
     * @return: java.lang.Boolean
     *
     */
    Boolean add_sbdyStructCoupler(besCouplerExcel besCouplerExcel);

    /**
     *
     * @Description: Excel导入,根据通信波特率编号查询通信波特率名称
     *
     * @auther: wanghongjie
     * @date: 16:02 2020/9/10
     * @param: [f_comm_rate]
     * @return: java.lang.String
     *
     */
    String selectF_comm_rate_mc(String f_comm_rate);
    /**
     *
     * @Description: 根据通信协议类型编号查询通信协议名称
     *
     * @auther: wanghongjie
     * @date: 16:04 2020/9/10
     * @param: [f_protocol_type]
     * @return: java.lang.String
     *
     */
    String selectF_protocol_type_mc(String f_protocol_type);

    /**
     *
     * @Description: 根据采集方案编号查询采集方案名称
     *
     * @auther: wanghongjie
     * @date: 16:04 2020/9/10
     * @param: [f_cjfabh]
     * @return: java.lang.String
     *
     */
    String selectF_cjfabh_mc(String f_cjfabh);

    /**
     *
     * @Description: 根据电表类型编号查询电表类型名称
     *
     * @auther: wanghongjie
     * @date: 13:51 2020/9/14
     * @param: [f_blxbh]
     * @return: java.lang.String
     *
     */
    String selectF_blxbh_mc(String f_blxbh);

    /**
     * 查询电表中最大id
     * @return
     */
    String queryAmmeterMaxId();

    /**
     *
     * @Description: 根据父节点名称查询数据库中是否有这个名称
     *
     * @auther: wanghongjie
     * @date: 16:24 2020/8/26
     * @param: [getfPsysname]
     * @return: boolean
     *
     */
    Map<String,Object> selectSbdyByPsysName(String fPsysname);

    /**
     *
     * @Description: 查询该节点的父节点的类型
     *
     * @auther: wanghongjie
     * @date: 16:14 2020/9/14
     * @param: [getfPsysname]
     * @return: java.lang.String
     *
     */
    String selectPSysNameType(String getfPsysname);

    /**
     *
     * @Description: 查询当前模块下所有的点位
     *
     * @auther: wanghongjie
     * @date: 8:38 2020/9/15
     * @param: [getfPsysname]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     *
     */
    List<Map<String, Object>> selectModule_pointList(String getfPsysname);

    /**
     *
     * @Description: 修改设备配置的点位信息
     *
     * @auther: wanghongjie
     * @date: 10:13 2020/9/15
     * @param: [f_sys_name_old, f_nick_name, allpath, f_description, f_type]
     * @return: java.lang.Boolean
     *
     */
    Boolean updateStructPoint(@Param("f_idBySbdyStruct") Integer f_idBySbdyStruct,@Param("f_sys_name_old") String f_sys_name_old,@Param("f_nick_name") String f_nick_name,@Param("allpath") String allpath,
                              @Param("f_description") String f_description,@Param("f_type") String f_type);

    /**
     *
     * @Description: 添加点位信息到相应的节点表中
     *
     * @auther: wanghongjie
     * @date: 10:32 2020/9/15
     * @param: [besPoint]
     * @return: java.lang.Boolean
     *
     */
    Boolean insertPointMapToNodeTable_DO_DI(@Param("tabName") String tabName,@Param("besPoint") besPointExcel besPoint);

    /**
     *
     * @Description: 添加点位信息到相应的节点表中
     *
     * @auther: wanghongjie
     * @date: 14:55 2020/9/15
     * @param: [tabName, besPoint]
     * @return: java.lang.Boolean
     *
     */
    Boolean insertPointMapToNodeTable_AO_AI(@Param("tabName") String tabName,@Param("besPoint") besPointExcel besPoint);

    /**
     *
     * @Description: 判断当前点位的通道索引是否符合模块的模块型号,首先查出模块的点集合
     *
     * @auther: wanghongjie
     * @date: 18:28 2020/9/15
     * @param: [getfPsysname]
     * @return: java.lang.String
     *
     */
    Map<String,Object> selectF_POINT_TYPE_CL(String getfPsysname);

    /**
     *
     * @Description: 添加虚点DO,DI点位信息到虚点表
     *
     * @auther: wanghongjie
     * @date: 14:46 2020/9/16
     * @param: [besPoint]
     * @return: java.lang.Boolean
     *
     */
    Boolean insertVPointMapToNodeTable_DO_DI(besPointExcel besPoint);

    /**
     *
     * @Description: 添加虚点AO,AI点位信息到虚点表
     *
     * @auther: wanghongjie
     * @date: 15:27 2020/9/16
     * @param: [besPoint]
     * @return: java.lang.Boolean
     *
     */
    Boolean insertVPointMapToNodeTable_AO_AI(besPointExcel besPoint);

    /**
     *
     * @Description: 根据耦合器节点点位的父节点名称查询父节点下所有的点位
     *
     * @auther: wanghongjie
     * @date: 11:30 2020/9/17
     * @param: [getfPsysname]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     *
     */
    List<Map<String, Object>> selectLightCouplerList(String getfPsysname);

    /**
     *
     * @Description: excel表格导入，添加数据到bes_sbdy_struct表中
     *
     * @auther: wanghongjie
     * @date: 15:07 2020/8/26
     * @param: [sbdy]
     * @return: boolean
     *
     */
    boolean add_sbdyStruct(BESSbPzStruct besSbPzStruct);

    /**
     * @Description: 查询bes_ddc表的最大的f_sbid
     * @auther: wanghongjie
     * @date: 9:06 2020/6/22
     *
     */
    String select_f_sbid_By_Bes_Ddc();

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

    public Map<String, Object> queryDDCInfoByModule(@Param("f_psys_name")String f_psys_name);

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
     * @Description: 查询模块表的最大的sbid
     * @auther: wanghongjie
     * @date: 9:06 2020/6/30
     *
     */
    String select_f_sbid_By_Bes_Module();

    /**
     *
     * @Description: 查询相应的点位表中是否存在这个点位
     *
     * @auther: wanghongjie
     * @date: 9:48 2020/7/14
     * @param: [f_sys_name_old]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     *
     */
    Map<String, Object> selectPointMap(@Param("tabName") String tabName,@Param("f_sys_name_old") String f_sys_name_old);

    /**
     *
     * @Description: 新增临时表,导入完成后,替换掉bes_sbdy_struct表中的数据
     *
     * @auther: wanghongjie
     * @date: 16:59 2021/11/8
     * @param: [besSbPzStruct]
     * @return: void
     *
     */

    Boolean add_sbdyStructCopy(BESSbPzStruct besSbPzStruct);

    /**
     *
     * @Description: 产业园临时用
     *
     * @auther: wanghongjie
     * @date: 8:30 2021/11/9
     * @param: [f_idBySbdyStruct, f_sys_name_old, f_nick_name, allpath, f_description, f_type]
     * @return: void
     *
     */
    Boolean updateStructPointCopy(@Param("f_idBySbdyStruct") Integer f_idBySbdyStruct,@Param("f_sys_name_old") String f_sys_name_old,@Param("f_nick_name") String f_nick_name,@Param("allpath") String allpath,
                               @Param("f_description") String f_description,@Param("f_type") String f_type);

}
