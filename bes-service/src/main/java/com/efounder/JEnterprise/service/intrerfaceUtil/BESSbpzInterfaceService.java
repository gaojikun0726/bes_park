package com.efounder.JEnterprise.service.intrerfaceUtil;

import com.core.common.ISSPReturnObject;

import java.util.List;
import java.util.Map;

public interface BESSbpzInterfaceService
{

    /**
     * 获取设备类型信息
     *
     * @return
     */
    ISSPReturnObject getDeviceTypes();

    /**
     * 获取设备信息
     *
     * @return
     */
    ISSPReturnObject getDevices();

    /**
     * 获取设备功能信息
     * @return
     */
    ISSPReturnObject getDeviceFunctions();

    /**
     * 获取功能值
     * @return
     */
    ISSPReturnObject getFunctionValues();

    /**
     * 获取所有的功能点状态
     * @return
     */
    ISSPReturnObject getFunctionPointStates(String deviceId, Integer deviceFunctionId);

    /**
     * 设备功能控制
     * @param deviceId
     * @param deviceFunctionId
     * @param value
     * @return
     */
    ISSPReturnObject setDeviceControl(String deviceId, Integer deviceFunctionId, String value);

    /**
     * 获取电表信息
     * @return
     */
    ISSPReturnObject getAmmeterInfo();

    List<Map<String, Object>> getSbdyList();

    List<Map<String, Object>> getDdcl(String CLName);

    List<Map<String, Object>> getDdclList();

    List<Map<String, Object>> getKtpzList();

    List<Map<String, Object>> getKtpz(String ktName);

    List<Map<String, Object>> getWkqpzList();

    List<Map<String, Object>> getWkqpz(String wkqName);

    List<Map<String, Object>> getSingleLevel(String fSysName);

    List<Map<String, Object>> getPLevel(String f_PSYS_NAME);

    List<Map<String, Object>> getMonitoring();

    List<Map<String, Object>> getFreshairconfigList();

    List<Map<String, Object>> getFreshairconfig(String windName);


    List<Map<String, Object>> getAlloffList(String systemName);


    /**
     * 根据灯光名称获取灯光配置信息
     *
     * @param lightName
     * @return
     */
    List<Map<String, Object>> getLight(String lightName);

    /**
     * 获取所有灯光配置信息
     *
     * @return
     */
    List<Map<String, Object>> getLightList();

    /**
     * 根据灯光名称获取灯光配置信息
     *
     * @param lightName
     * @return
     */
    List<Map<String, Object>> getTgLight(String lightName);

    /**
     * 获取所有灯光配置信息
     *
     * @return
     */
    List<Map<String, Object>> getTgLightList();

    List<Map<String, Object>> getSocket(String socketName);

    List<Map<String, Object>> getSocketList();

    List<Map<String, Object>> getAirconditioningUnit(String airconditioningUintName);

    List<Map<String, Object>> getAirconditioningUnitList();

    /**
     * 获取设备实时值
     * @param fSysName
     * @return
     */
    ISSPReturnObject getRealTime(String fSysName);
}
