package org.ace.socketserver.core;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * 初始化器
 * @author xiepufeng
 */
public class SocketServerChannelInitializer extends ChannelInitializer<SocketChannel>
{
    @Override
    protected void initChannel(SocketChannel ch) throws Exception
    {
        ChannelPipeline pipeline = ch.pipeline();
        // 解码器 LineBasedFrameDecoder，使用换行符\n或者\r\n作为依据，遇到\n或者\r\n都认为是一条完整的消息。
        pipeline.addLast(new LineBasedFrameDecoder(Integer.MAX_VALUE));
        // 编码器LineBasedFrameEncoder，自动对每天消息末端添加换行符
        pipeline.addLast(new LineBasedFrameEncoder("\n"));
        // 解码器 StringDecoder，将字符串自动转成utf8编码
        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
        // 编码器 StringEncoder，将字符串自动转成utf8编码
        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
        // 消息处理器
        pipeline.addLast(new SocketServerHandler());
    }
}
