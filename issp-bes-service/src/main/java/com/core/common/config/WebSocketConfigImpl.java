package com.core.common.config;

import org.ace.websocket.handler.WebSocketConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * WebSocket 配置类
 * @author xiepufeng
 */
@Component
public class WebSocketConfigImpl implements WebSocketConfig
{

    @Value("${comm.websocket.port}")
    private int port;

    @Value("${comm.websocket.path}")
    private String path;

    @Value("${comm.websocket.password}")
    private String password;

    @Value("${comm.websocket.heartbeat.interval}")
    private int interval;

    @Value("${comm.websocket.heartbeat.timeout.intervals}")
    private int intervals;

    @Override
    public int getPort()
    {
        return port;
    }

    @Override
    public String getPath()
    {
        return path;
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public int getInterval()
    {
        return interval;
    }

    @Override
    public int getTimeoutIntervals()
    {
        return intervals;
    }

}
