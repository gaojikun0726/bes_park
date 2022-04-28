package com.core.common.config;

import org.ace.socketserver.handler.SocketServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * SocketServer 配置类（上位机与下位机通信配置）
 * @author xiepufeng
 */
@Component
public class SocketServerConfigImpl implements SocketServerConfig
{

    @Value("${comm.socketServer.port}")
    private int port; // 端口

    @Value("${comm.socketServer.heartbeat.interval}")
    private int interval; // 心跳周期

    @Value("${comm.socketServer.heartbeat.timeout.intervals}")
    private int timeoutIntervals;// 心跳超时周期

    @Override
    public int getPort()
    {
        return port;
    }

    @Override
    public int getInterval()
    {
        return interval;
    }

    @Override
    public int getTimeoutIntervals()
    {
        return timeoutIntervals;
    }
}
