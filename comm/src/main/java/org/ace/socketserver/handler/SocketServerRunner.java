package org.ace.socketserver.handler;

import org.ace.socketserver.core.SocketServer;
import org.ace.socketserver.handler.heartbeat.HeartbeatDetector;

import java.util.Timer;

public class SocketServerRunner implements Runnable
{

    private SocketServerConfig config;

    public SocketServerRunner(SocketServerConfig config) {
        this.config = config;
    }

    @Override
    public void run()
    {
        try
        {

            /*Start 启动心跳检测器*/
            Timer timer = new Timer("SocketServer-server-timer", true);

            HeartbeatDetector heartbeatDetector = new HeartbeatDetector(
                    config.getInterval(),
                    config.getTimeoutIntervals());

            int interval = heartbeatDetector.getInterval() * 1000;

            timer.schedule(heartbeatDetector, interval, interval);
            /*End 心跳检测器*/


            /*Start socket 服务*/
            SocketServer socketServer = new SocketServer(
                    config.getPort(),
                    config.getPassword());

            socketServer.run();
            /*End socket 服务*/

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
