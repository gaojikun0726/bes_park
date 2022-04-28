package com.efounder.JEnterprise.service.opm;

import com.alibaba.fastjson.JSONArray;

import java.util.Map;

/**
 * describe: 推送设备告警信息
 *
 * @author zs
 * @date 2020/11/30
 */
public interface PushDeviceAlarm {


    /**
     * 推送设备离线信息
     * @param sysname 离线的设备sysname
     * @param alertMsg 报警信息
     */
    void pushDeviceOfflineInfo(String sysname,String alertMsg);


//    /**
//     * 获取暂时的access_token
//     * @return
//     */
//    String getTempToken();
//
//    /**
//     * 获取token
//     * @return
//     */
//     String getToken();


    /**
     * 获取报警类型接口数据
     * @return
     */
    JSONArray getAlarmTypeList(String token);


    /**
     * 获取设备报警信息：编码和名称
     * @return
     */
    Map<String,String> getDeviceAlarmInfo(String token);
}
