package org.ace.socketserver.core;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.Charset;

/**
 * 自定义解码器
 * @author xiepufeng
 */
public class LineBasedFrameEncoder extends MessageToByteEncoder<ByteBuf>
{

    // 分隔符
    private String separator;

    public LineBasedFrameEncoder(String separator)
    {
        if (null == separator || separator.isEmpty())
        {
            separator = "\n";
        }

        this.separator = separator;
    }

    public LineBasedFrameEncoder(Class<? extends ByteBuf> outboundMessageType, String separator)
    {
        super(outboundMessageType);
        this.separator = separator;
    }


    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception
    {
        msg.writeBytes(Unpooled.copiedBuffer(separator, Charset.forName("utf-8")));
        out.writeBytes(msg);
    }
}
