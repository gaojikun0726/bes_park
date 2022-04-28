package org.ace.socketserver.core;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.ace.socketserver.bo.ChannelProperty;
import org.ace.socketserver.constant.AttributeKeyConst;
import org.ace.socketserver.enums.ChannelStatus;
import org.ace.socketserver.handler.MessageHandler;
import org.ace.socketserver.handler.ParseDiscern;
import org.ace.socketserver.util.MsgUtil;

import java.util.ServiceLoader;
import java.util.logging.Logger;

/**
 * netty socket 消息处理回调类
 * @author xiepufeng
 */
public class SocketServerHandler extends SimpleChannelInboundHandler<String>
{

    private static final Logger log = Logger.getLogger(SocketServerHandler.class.getName());

    private ChannelHandlerContext ctx;

    private MessageHandler messageHandler;

    private ParseDiscern parseDiscern;

    {
        ServiceLoader<MessageHandler> load = ServiceLoader.load(MessageHandler.class);

        for (MessageHandler item : load)
        {
            messageHandler = item;
            break;
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception
    {
        if (null != messageHandler)
        {
            messageHandler.messageReceived(ctx, msg);
            return;
        }

        Channel channel = ctx.channel();

        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement;
        JsonObject jsonObject;
        try
        {
            jsonElement = jsonParser.parse(msg);
            jsonObject = jsonElement.getAsJsonObject();


            // 判断消息类型，共三中类型，即：method, result, error
            String type = MsgUtil.getJsonType(jsonObject);
            if (null == type)
            {
                channel.close();
                return;
            }

            if (null == parseDiscern) {
                parseDiscern = new ParseDiscern(this);
            }

            parseDiscern.parseAndProcessByJsonType(jsonObject, type);

        }
        catch (JsonSyntaxException | IllegalStateException e)
        {
            e.printStackTrace();
            channel.close();
        }
    }

    /**
     * 通道被添加回调（首先调用）
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception
    {
        this.ctx = ctx;
        Channel channel = ctx.channel();
        System.out.println("Client:" + channel.remoteAddress() + " entering");
        MsgUtil.channels.add(channel);
    }

    /**
     * 连接关掉的时候回调
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception
    {
        this.ctx = null;
        Channel channel = ctx.channel();
        System.out.println("Client:" + channel.remoteAddress() + " leave");
        MsgUtil.channels.remove(channel);
    }

    /**
     * 通道变成活动状态时回调
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
        // 设置 channel 属性
        Channel channel = ctx.channel();

        ChannelProperty channelProperty = new ChannelProperty();
        channelProperty.setStatus(ChannelStatus.INITIAL);
        channelProperty.setStatusCount(0);

        channel.attr(AttributeKeyConst.CHANNEL_PROPERTY_KEY).set(channelProperty);

        log.info("Channel active. remote Address:" + channel.remoteAddress());

        System.out.println("Client:" + channel.remoteAddress() + " online");

    }

    /**
     * 通道变成不活动状态时回调
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception
    {

        if (null != messageHandler)
        {
            messageHandler.channelInactive(ctx);
        }

        // 清空 channel 属性
        Channel channel = ctx.channel();
        channel.attr(AttributeKeyConst.CHANNEL_PROPERTY_KEY).set(null);
        log.info("Channel deactived. remote Address:" + channel.remoteAddress());

        System.out.println("Client:" + channel.remoteAddress() + " offline");
    }

    /**
     * 异常发生时调用
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        Channel channel = ctx.channel();
        System.out.println("Client:" + channel.remoteAddress() + " exception");
        // 当出现异常就关闭连接
        cause.printStackTrace();
        //ctx.close();
    }

    public ChannelHandlerContext getCtx()
    {
        return ctx;
    }

    public void setCtx(ChannelHandlerContext ctx)
    {
        this.ctx = ctx;
    }

    public MessageHandler getMessageHandler()
    {
        return messageHandler;
    }

    public void setMessageHandler(MessageHandler messageHandler)
    {
        this.messageHandler = messageHandler;
    }

    public ParseDiscern getParseDiscern()
    {
        return parseDiscern;
    }

    public void setParseDiscern(ParseDiscern parseDiscern)
    {
        this.parseDiscern = parseDiscern;
    }
}
