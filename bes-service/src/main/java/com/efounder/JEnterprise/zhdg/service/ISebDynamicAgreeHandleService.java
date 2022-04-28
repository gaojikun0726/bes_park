package com.efounder.JEnterprise.zhdg.service;

import com.efounder.JEnterprise.zhdg.entity.SebWarnRecord;
import com.efounder.JEnterprise.zhdg.util.AjaxResult;

import java.util.List;
import java.util.Map;

/**
 * 动态协议解析处理接口
 *
 * @author YangChao
 * @date 2020-04-16
 */
public interface ISebDynamicAgreeHandleService
{

    /**
     * 协议解析，数据处理
     */
    void DynamicAgreeHandle(String clientIp, String deviceId, String data) throws Exception;

    /**
     * 获取动态在线实时数据
     */
    Map<String, Object> GetRealPoint() throws Exception;

    /**
     * 获取实时数据
     */
    List<Map<String,String>> GetRealDataList();

    /**
     * 获取气象状态实时数据
     */
    Map<String,String> getWeatherStatusData();

    /**
     * 获取实时报警信息
     */
    List<Map<String,String>> GetRealWarn(SebWarnRecord warn) throws Exception;

    /**
     * 根据设备id获取设备实时数据
     */
    Map<String,String> getPointInfo(String deviceId) throws Exception;

    /**
     * 下发指令
     */
    AjaxResult sendMsg(String DeviceId, String datas, String orderCode, String digits) throws Exception;

    /**
     * 清空实时数据缓存
     */
    void cleanRealPointData() throws Exception;

    /**
     * 定时--修改点位在线状态
     */
    void updatePointStatus();

    /**
     * netty心跳清空缓存
     */
    void CleanRealData(String deviceId, String clientIp) throws Exception;

    /**
     * 清空缓存报警
     */
    void cleanWarn();

    /**
     * 时间校准指令下发
     */
    void TimeCalibration() throws Exception;

    /**
     * 重新加载初始化数据
     */
    void reload();
}
