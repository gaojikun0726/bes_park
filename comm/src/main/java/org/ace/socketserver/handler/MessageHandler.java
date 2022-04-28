package org.ace.socketserver.handler;

import io.netty.channel.ChannelHandlerContext;

/**
 * @author xiepufeng
 */
public interface MessageHandler
{
    void messageReceived(ChannelHandlerContext ctx, String message);

    // 通道不活跃时回调
    void channelInactive(ChannelHandlerContext ctx) throws Exception;
}
