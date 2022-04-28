package org.ace.business.handler;

import org.ace.business.dto.ReceiveMsg;
import org.ace.business.dto.TimeData;
import org.ace.business.dto.ddc.*;
import org.ace.business.dto.edc.*;
import org.ace.business.dto.ldc.*;

import java.util.List;

/**
 * 客户端事件回调接口
 * @author xiepufeng
 * @date 2020/4/16 15:35
 */
public interface ClientMsgReceive
{
    /**
     *
     * @param ip 控制器 ip 地址
     * @param state 在线状态 true 在线， false 离线
     */
    void controllerState(String ip, Boolean state);

    /**
     * 心跳回调函数
     * @param ip
     */
    void heartbeatCallback(String ip);


    /***************************************** EDC (能耗) *******************************************/

    // 远程升级
    void remoteUpgradeEDC(ReceiveMsg msg);

    // 新增一个控制器返回回调
    void controllerAddEDC(ReceiveMsg msg);

    // 设置控制器的所有参数
    void controllerSetEDC(ReceiveMsg msg);

    // 获取控制器的所有配置参数
    void controllerGetEDC(ReceiveMsg<ControllerDataEDC> controllerData);

    // 设置控制器的时间
    void controllerTimeSetEDC(ReceiveMsg msg);

    // 获取控制器的时间
    void controllerTimeGetEDC(ReceiveMsg<TimeData> msg);

    // 重启控制器，相当于重启复位
    void controllerRestartEDC(ReceiveMsg msg);

    // 重置控制器，恢复出厂设置，并重启
    void controllerResetEDC(ReceiveMsg msg);

    // 删除一个控制器，并删除和它相关的电表
    void controllerDeleteEDC(ReceiveMsg msg);

    // 新增加一个电表信息
    void ammeterAddEDC(ReceiveMsg<AmmeterCollectParamData> msg);

    // 删除一个电表
    void ammeterDeleteEDC(ReceiveMsg<AmmeterCollectParamData> msg);

    // 设置一个电表的所有参数
    void ammeterSetEDC(ReceiveMsg<AmmeterCollectParamData> msg);

    // 获取一个电表的所有配置信息
    void ammeterGetEDC(ReceiveMsg<AmmeterCollectParamData> msg);

    // 新增一个电表的采集方案
    void ammeterCollectionSchemeAddEDC(ReceiveMsg<ElectricParam> msg);

    // 设置一个电表的采集方案
    void ammeterCollectionSchemeSetEDC(ReceiveMsg<ElectricParam> msg);

    // 删除一个电表的采集方案
    void ammeterCollectionSchemeDeleteEDC(ReceiveMsg<ElectricParam> msg);

    // 获取电表实时数据
    void ammeterRealtimeDataGetEDC(ReceiveMsg<AmmeterData> msg);

    // 获取历史数据
    void ammeterHistoryDataGetEDC(ReceiveMsg<AmmeterHistoryData> msg);

    // 接收告警数据
    void alarmDataReceiveEDC(ReceiveMsg<List<AlarmData>> msg);

    // 接收电表实时数据
    void ammeterRealtimeDataReceiveEDC(ReceiveMsg<List<AmmeterData>> msg);

    // 接收电表断点续传数据
    void ammeterResumeDataReceiveEDC(ReceiveMsg<List<AmmeterData>> msg);


    /*************************************** DDC（楼控）*****************************************/

    // 新增一个控制器返回回调（DDC）
    void controllerAddDDC(ReceiveMsg msg);

    // 设置控制器的所有参数（DDC）
    void controllerSetDDC(ReceiveMsg msg);

    // 删除一个控制器，并删除和它相关的模块和点（DDC）
    void controllerDeleteDDC(ReceiveMsg msg);

    // 获取控制器的所有配置参数（DDC）
    void controllerGetDDC(ReceiveMsg<ControllerDataDDC> controllerData);

    // 远程升级（DDC）
    void remoteUpgradeDDC(ReceiveMsg msg);

    // 设置控制器的时间（DDC）
    void controllerTimeSetDDC(ReceiveMsg msg);

    // 获取控制器的时间（DDC）
    void controllerTimeGetDDC(ReceiveMsg<TimeData> msg);

    // 重启控制器，相当于重启复位（DDC）
    void controllerRestartDDC(ReceiveMsg msg);

    // 重置控制器，恢复出厂设置，并重启（DDC）
    void controllerResetDDC(ReceiveMsg msg);

    // 新增加一个模块（DDC）
    void moduleAddDDC(ReceiveMsg<ModuleParamDDC> msg);

    // 设置一个模块的所有参数（DDC）
    void moduleSetDDC(ReceiveMsg<ModuleParamDDC> msg);

    // 删除一个模块，并删除和模块相关的点（DDC）
    void moduleDeleteDDC(ReceiveMsg<ModuleParamDDC> msg);

    // 获取一个模块的所有配置信息（DDC）
    void moduleGetDDC(ReceiveMsg<ModuleParamDDC> msg);

    // 接收DDC实点更新的点信息（DDC）
    void realPointDataReceiveDDC(ReceiveMsg<List<PointDataDDC>> msg);

    // 获取一个模块的错误代码（DDC）
    void moduleErrorCodeGetDDC(ReceiveMsg<ErrorCodeDDC> msg);

    // 批量获取模块的错误代码（DDC）
    void moduleErrorCodeGetDDC_ALL(ReceiveMsg<List<ErrorCodeDDC>> msg);

    // 新增加一个逻辑点（DDC）
    void pointAddDDC(ReceiveMsg<PointParamDDC> msg);

    // 设置一个逻辑点的所有参数（DDC）
    void pointParamSetDDC(ReceiveMsg<PointParamDDC> msg);

    // 设置一个逻辑点的值（DDC）
    void pointValueSetDDC(ReceiveMsg<PointDataDDC> msg);

    // 设置一个逻辑点的值（DDC）
    void pointValueByNameSetDDC(ReceiveMsg<PointParamDDC> msg);

    // 删除一个逻辑点（DDC）
    void pointDeleteDDC(ReceiveMsg<PointParamDDC> msg);

    // 获取一个逻辑点的所有配置参数（DDC）
    void pointParamGetDDC(ReceiveMsg<PointParamDDC> msg);

    // 获取一个逻辑点的报警信息（DDC）
    void pointAlarmDataGetDDC(ReceiveMsg<AlarmPointDataDDC> msg);

    // 接收DDC的全部点信息（DDC）
    void pointDataAllReceiveDDC(ReceiveMsg<List<PointDataDDC>> msg);

    // 接收虚点信息（DDC）
    void virtualPointDataReceiveDDC(ReceiveMsg<List<PointDataDDC>> msg);

    // 增加一个场景
    void sceneAddDDC(ReceiveMsg<SceneDataDDC> msg);

    // 设置一个场景的所有参数（DDC）
    void sceneParamSetDDC(ReceiveMsg<SceneDataDDC> msg);

    // 删除一个场景（DDC）
    void sceneDeleteDDC(ReceiveMsg<SceneDataDDC> msg);

    // 获取一个场景的配置信息（DDC）
    void sceneParamGetDDC(ReceiveMsg<SceneParamDDC> msg);

    //获取场景下的单个模式信息
    void controlParamDDC(ReceiveMsg<SceneDataDDC> msg);

    //删除场景下的单个模式信息
    void sceneModeParamDeleteDDC(ReceiveMsg<SceneParamDDC> msg);

    // 增加一条计划（DDC）
    void planAddDDC(ReceiveMsg<PlanParamDDC> msg);

    // 修改一条计划的所有参数（DDC）
    void planParamSetDDC(ReceiveMsg<PlanParamDDC> msg);

    // 删除一条计划（DDC）
    void planDeleteDDC(ReceiveMsg<PlanParamDDC> msg);

    // 获取一条计划的所有参数（DDC）
    void planParamGetDDC(ReceiveMsg<PlanParamDDC> msg);


    /*************************************** LDC（照明）*****************************************/

    // 新增一个IP路由器返回回调（LDC）
    void controllerAddLDC(ReceiveMsg msg);

    // 设置IP路由器的所有参数（LDC）
    void controllerSetLDC(ReceiveMsg msg);

    // 删除一个IP路由器，并删除和它相关的模块和点（LDC）
    void controllerDeleteLDC(ReceiveMsg msg);

    // 获取IP路由器的所有配置参数（LDC）
    void controllerGetLDC(ReceiveMsg<ControllerDataLDC> controllerData);

    // 远程升级（LDC）
    void remoteUpgradeLDC(ReceiveMsg msg);

    // 设置IP路由器的时间（LDC）
    void controllerTimeSetLDC(ReceiveMsg msg);

    // 获取IP路由器的时间（LDC）
    void controllerTimeGetLDC(ReceiveMsg<TimeData> msg);

    // 重启IP路由器，相当于重启复位（LDC）
    void controllerRestartLDC(ReceiveMsg msg);

    // 重置IP路由器，恢复出厂设置，并重启（LDC）
    void controllerResetLDC(ReceiveMsg msg);

    // 新增加一个模块（LDC）
    void moduleAddLDC(ReceiveMsg<ModuleParamLDC> msg);

    // 设置一个模块的所有参数（LDC）
    void moduleSetLDC(ReceiveMsg<ModuleParamLDC> msg);

    // 删除一个模块，并删除和模块相关的点（LDC）
    void moduleDeleteLDC(ReceiveMsg<ModuleParamLDC> msg);

    // 获取一个模块的所有配置信息（LDC）
    void moduleGetLDC(ReceiveMsg<ModuleParamLDC> msg);

    // 接收LDC实点更新的点信息（LDC）
    void realPointDataReceiveLDC(ReceiveMsg<List<PointDataLDC>> msg);

    // 获取一个模块的错误代码（LDC）
    void moduleErrorCodeGetLDC(ReceiveMsg<ErrorCodeLDC> msg);

    // 批量接收一个模块的错误代码（LDC）
    void moduleErrorCodeGetLDCALL(ReceiveMsg<List<ErrorCodeLDC>> msg);

    // 新增加一个逻辑点（LDC）
    void pointAddLDC(ReceiveMsg<PointParamLDC> msg);

    // 设置一个逻辑点的所有参数（LDC）
    void pointParamSetLDC(ReceiveMsg<PointParamLDC> msg);

    // 设置一个逻辑点的值（LDC）
    void pointValueSetLDC(ReceiveMsg<PointDataLDC> msg);

    // 设置一个逻辑点的值（LDC）
    void pointValueByNameSetLDC(ReceiveMsg<PointParamLDC> msg);

    // 删除一个逻辑点（LDC）
    void pointDeleteLDC(ReceiveMsg<PointParamLDC> msg);

    // 获取一个逻辑点的所有配置参数（LDC）
    void pointParamGetLDC(ReceiveMsg<PointParamLDC> msg);

    // 获取一个逻辑点的报警信息（LDC）
    void pointAlarmDataGetLDC(ReceiveMsg<AlarmPointDataLDC> msg);

    // 接收LDC的全部点信息（LDC）
    void pointDataAllReceiveLDC(ReceiveMsg<List<PointDataLDC>> msg);

    // 接收虚点信息（LDC）
    void virtualPointDataReceiveLDC(ReceiveMsg<List<PointDataLDC>> msg);

    // 增加一个场景
    void sceneAddLDC(ReceiveMsg<SceneDataLDC> msg);

    // 设置一个场景的所有参数（LDC）
    void sceneParamSetLDC(ReceiveMsg<SceneDataLDC> msg);

    // 删除一个场景（LDC）
    void sceneDeleteLDC(ReceiveMsg<SceneDataLDC> msg);

    // 获取一个场景的配置信息（LDC）
    void sceneParamGetLDC(ReceiveMsg<SceneParamLDC> msg);

    // 增加一条计划（LDC）
    void planAddLDC(ReceiveMsg<PlanParamLDC> msg);

    // 修改一条计划的所有参数（LDC）
    void planParamSetLDC(ReceiveMsg<PlanParamLDC> msg);

    // 删除一条计划（LDC）
    void planDeleteLDC(ReceiveMsg<PlanParamLDC> msg);

    // 获取一条计划的所有参数（LDC）
    void planParamGetLDC(ReceiveMsg<PlanParamLDC> msg);

    // 获取一条场景里面模式的所有参数（LDC）
    void controlParamLDC(ReceiveMsg<ControlParamLDC> msg);

    //新增一个场景模式(LDC)
    //void sceneModeParamAddLDC(ReceiveMsg<SceneModeParamLDC> msg);

    //删除一个场景模式(LDC)
    void sceneModeParamDeleteLDC(ReceiveMsg<SceneModeParamLDC> msg);


}
