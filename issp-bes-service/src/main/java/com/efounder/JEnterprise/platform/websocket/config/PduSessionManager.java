package com.efounder.JEnterprise.platform.websocket.config;

import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * describe: PDU websocket连接管理
 *
 * @author zs
 * @date 2021/2/5
 */
public class PduSessionManager {
    /**
     * 存储客户端websocket连接信息，便于服务器返回数据
     */

    public static Map<String,WebSocketSession> sessionMap = new ConcurrentHashMap<>();

}
