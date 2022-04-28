package org.ace.websocket.handler;

import org.ace.websocket.core.WebSocketServer;
import org.ace.websocket.handler.heartbeat.HeartbeatDetector;

import java.util.Timer;

/**
 * WebSocket 启动包装类
 */
public class WebSocketRunner implements Runnable
{

    private WebSocketConfig config;

    public WebSocketRunner(WebSocketConfig config) {
        this.config = config;
    }

    @Override
    public void run()
    {
        try
        {

            Timer timer = new Timer("WebSocket-server-timer", true);

            HeartbeatDetector heartbeatDetector = new HeartbeatDetector(
                    config.getInterval(),
                    config.getTimeoutIntervals());

            int interval = heartbeatDetector.getInterval() * 1000;

            timer.schedule(heartbeatDetector, interval, interval);

            WebSocketServer webSocketServer = new WebSocketServer(
                    config.getPort(),
                    config.getPath(),
                    config.getPassword());

            webSocketServer.run();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
