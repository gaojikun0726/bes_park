package com.efounder.JEnterprise.platform.websocket.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * describe: PDU状态数据存储
 *
 * @author zs
 * @date 2022/1/4
 */
public class PduStatusData {

    /**
     * 存储开关状态
     */
    public static Map<String, Map> switchStatusMap = new ConcurrentHashMap<>();


    /**
     * PDU在线状态存储
     */
    public static Map<String,String> pduOnlineStatusMap = new ConcurrentHashMap<>();
}
