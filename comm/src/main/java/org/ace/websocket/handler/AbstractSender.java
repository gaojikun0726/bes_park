package org.ace.websocket.handler;


import org.ace.websocket.core.TextWebSocketFrameHandler;

/**
 * 抽象消息发送器
 */
public class AbstractSender
{
    /*
     * 消息通道处理器
     */
    private TextWebSocketFrameHandler channelHandler;

    public TextWebSocketFrameHandler getChannelHandler()
    {
        return channelHandler;
    }

    public void setChannelHandler(TextWebSocketFrameHandler channelHandler)
    {
        this.channelHandler = channelHandler;
    }

}
