package com.efounder.JEnterprise.design.service;

import com.core.common.ISSPReturnObject;

import java.util.List;
import java.util.Map;

/**
 * 按钮的关联事件方法
 */
public interface ButtonEventService {


    /**
     * 查询关联点的信息
     * @return
     */
    ISSPReturnObject getPointInfo(String sysname);

    /**
     * 获取关联点的信息
     * @param sysname 关联点的sysname值
     * @return
     */
     Map<String, Object> getOnePointInfo(String sysname);


    /**
     * 设置按钮内容加载
     * @param f_sys_name
     * @return
     */
    ISSPReturnObject getSettingsInfo(String f_sys_name);

    /**
     * 查询点点位置、单通道、多场景关联点位信息
     * @param sysname
     * @return
     */
    String  getRelatedInfo(String sysname);


    /**
     * 查询所有文本框关联点信息
     * @param sysnameArray 所有关联点sysname数组
     * @return
     */
    ISSPReturnObject queryTextboxListInfo(String[] sysnameArray);


//    /**
//     * 查询多个点的配置数据
//     * @param sysnameArray 多个点的sysname数组
//     * @return
//     */
//    ISSPReturnObject queryConfigByArray(String[] sysnameArray);

    /**
     * 查询多场景关联点信息
     * @param sysname
     * @return
     */
    String getScenePointInfo(String sysname);

    /**
     * 查询多场景状态下拉框
     * @param sysNameList
     * @return
     */
    ISSPReturnObject getSceneSettingsInfo(List<String> sysNameList);

//    /**
//     * 场景按钮切换命令
//     * @param array 场景按钮关联的点的sys_name数组
//     * @return
//     */
//    ISSPReturnObject sceneBtnToggle(String[] array);


    /**
     * 场景按钮切换命令
     * @param sceneData 场景按钮关联的点的配置数据
     * @return
     */
    ISSPReturnObject sceneBtnToggle(String sceneData);

    /**
     * 查询温控器模块对应的点位信息
     * @param sysname
     * @return
     */
    ISSPReturnObject getTempModuleInfo(String sysname);

    /**
     * 查询温控器模块的在线情况
     * @param sysname
     * @return
     */
    ISSPReturnObject getModuleState(String sysname);

    /**
     * 查询温控器关联点位详情
     * @param sysname
     * @return
     */
    ISSPReturnObject getTempPointInfo(String sysname);

    /**
     * 查询所有流程节点关联点位信息
     * @param sysnameArray
     * @return
     */
    ISSPReturnObject queryFlowPointListInfo(String[] sysnameArray);

    /**
     * 查询温控器关联模块列表对应的点位的信息
     * @param sysnameArray 模块f_sys_name数组
     * @return
     */
    ISSPReturnObject queryModuleListChildInfo(String[] sysnameArray);

    /**
     * 低档温控器开关切换
     * @param sysname 开关f_sys_name
     * @return
     */
    ISSPReturnObject switchToggle(String sysname);

    /**
     * 低档温控器模式切换
     * @param sysname 模式f_sys_name
     * @return
     */
    ISSPReturnObject lowConditionerModeToggle(String sysname);

    /**
     * 低档温控器风速切换
     * @param sysname 风速f_sys_name
     * @return
     */
    ISSPReturnObject lowConditionerSpeedToggle(String sysname);

    /**
     * 低档温控器调整设定温度
     * @param sysname 设定温度f_sys_name
     * @param direction 设定方向
     * @return
     */
    ISSPReturnObject changeSetTemperature(String sysname, String direction, String setNum);


    /**
     * 获取下位机数据
     * @param sysname 模块f_sys_name
     * @return
     */
    ISSPReturnObject getLowerData(String sysname);


    /**
     * 查询模块类型
     * @param sysname f_sys_name
     * @return
     */
    ISSPReturnObject queryModuleType(String sysname);


    /**
     * 查询虚点的具体类型
     * @param sysname f_sys_name
     * @return
     */
    ISSPReturnObject getVisualType(String sysname);

    /**
     * 低档温控器锁定功能切换
     * @param sysname
     * @param initVal
     * @return
     */
    ISSPReturnObject lowConditionerSdToggle(String sysname, String initVal);
}
