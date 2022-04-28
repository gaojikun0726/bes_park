package org.ace.websocket.handler;

/**
 * WebSocket 配置参数
 * @author xiepufeng
 */
public interface WebSocketConfig {

    /**
     * 端口
     * @return
     */
    int getPort();

    /**
     * 路径
     * @return
     */
    String getPath();

    /**
     * 密码
     * @return
     */
    String getPassword();

    /**
     * 心跳周期
     * @return
     */
    int getInterval();

    /**
     * 心跳超时周期
     * @return
     */
    int getTimeoutIntervals();
}
