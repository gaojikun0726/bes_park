package org.ace.socketserver.handler;

import org.ace.socketserver.core.SocketServerHandler;


public class AbstractSender
{
    /*
     * 消息通道处理器
     */
    private SocketServerHandler channelHandler;


    public SocketServerHandler getChannelHandler()
    {
        return channelHandler;
    }

    public void setChannelHandler(SocketServerHandler channelHandler)
    {
        this.channelHandler = channelHandler;
    }


}
