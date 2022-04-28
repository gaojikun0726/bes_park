package org.ace.socketserver.handler;

/**
 * Socket Server 启动参数接口
 * @author xiepufeng
 * @date 2020/2/29 14:56
 */
public interface SocketServerConfig
{
    /**
     * 端口
     * @return
     */
    int getPort();

    /**
     * 密码
     * @return
     */
    default String getPassword() {
        return null;
    }

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
