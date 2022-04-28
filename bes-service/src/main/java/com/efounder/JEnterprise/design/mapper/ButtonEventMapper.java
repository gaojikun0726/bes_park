package com.efounder.JEnterprise.design.mapper;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


/**
 * 运行模式后台方法
 */
@Mapper
public interface ButtonEventMapper {

    /**
     * 查询关联点信息
     * @param map 关联点表名，关联点系统名称
     * @return
     */
    Map<String,Object> queryRelativePointInfo(Map<String, Object> map);



    /**
     * 查询设置下拉框的内容
     * @param f_sys_name
     * @return
     */
    List<Object> getSettingsInfo(String f_sys_name);


    /**
     * 查询点位置、单通道、多场景关联点位信息
     * @param sysname
     * @return
     */
    String getRelatedInfo(String sysname);


    /**
     * 区分所有点的f_type节点类型
     * @param map 节点sysname数组
     * @return
     */
    List<Map> getNodeListType(Map<String, Object> map);

//    /**
//     * 查询模块列表所有子节点的类型
//     * @param map 节点sysname数组
//     * @return
//     */
//    @Deprecated
//    List<Map> getModuleListChildrenType(Map<String, Object> map);

    /**
     * 查询模块列表的所有子节点
     * @param map 节点sysname数组
     * @return
     */
    List<Map> getModuleChildrenList(Map<String, Object> map);

    /**
     * 查询各种类型的节点的信息
     * @param map 节点sysname集合
     * @return
     */
    List<Map> queryTypeNodeList(Map<String, Object> map);

    /**
     * 查询温控器模块在线状态
     * @param sysname
     * @return
     */
    String getModuleState(String sysname);

    /**
     * 查询温控器关联点位信息
     * @param sysname
     * @return
     */
    List<Map<String, Object>> getTempPointInfo(String sysNameOld);

    /**
     * 查询模块的(AO/AI)子节点的信息，和模块名
     * @param map 子节点名称集合
     * @return
     */
    List<Map> queryModuleAnalogChildren(Map<String, Object> map);

    /**
     * 查询模块的(DI/DO)子节点的信息，和模块名
     * @param map 子节点名称集合
     * @return
     */
    List<Map> queryModuleDigitChildren(Map<String, Object> map);

    /**
     *
     * 查询系统别名
     * @param sysname
     * @return
     */
    String getPointSysNameOld(String sysname);

    /**
     * 查询温控器关联点位信息
     * @param map
     * @return
     */
    Map<String, Object> queryRelativeTempPointInfo(Map<String, Object> map);
    /**
     * 区分流程图所有点的f_type节点类型
     * @param map 节点sysname数组
     * @return
     */
    List<Map> getFlowNodeListType(Map<String, Object> map);

    /**
     * 查询流程图各种类型的节点的信息
     * @param queryMap
     * @return
     */
    List<Map> queryFlowTypeNodeList(Map<String, Object> queryMap);
//    /**
//     * 查询多个点位的配置信息
//     * @param map f_sys_name集合
//     * @return
//     */
//    List<Map> queryConfigByList(Map<String, Object> map);

    /**
     * 查询模块类型
     * @param sysname f_sys_name
     * @return
     */
    String queryModuleType(String sysname);


    /**
     * 查询虚点的具体类型
     * @param sysname f_sys_name
     * @return
     */
    String getVisualType(String sysname);

}
