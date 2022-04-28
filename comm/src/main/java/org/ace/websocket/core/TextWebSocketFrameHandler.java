package org.ace.websocket.core;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.ace.websocket.bo.ChannelProperty;
import org.ace.websocket.constant.AttributeKeyConst;
import org.ace.websocket.constant.RequestObject;
import org.ace.websocket.enums.ChannelStatus;
import org.ace.websocket.handler.ParseDiscern;
import org.ace.websocket.handler.SessionSupport;
import org.ace.websocket.util.MsgUtil;

import java.util.ServiceLoader;
import java.util.logging.Logger;

/**
 * 消息通道处理器
 */
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>
{
    private static final Logger log = Logger.getLogger(TextWebSocketFrameHandler.class.getName());

    private ChannelHandlerContext ctx;

    private ParseDiscern parseDiscern;

    /**
     *  读取客户端的请求，返回客户响应
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception
    {

        Channel channel = ctx.channel();

        /* Begin. JsonParser 的用法:适用于系统间的消息交互 。 注意与下面JsonReader的区别。*/
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement;
        JsonObject jsonObject;
        try
        {
            jsonElement = jsonParser.parse(msg.text());
            jsonObject = jsonElement.getAsJsonObject();

            // 1.校验版本信息
            String jsonrpcVersion = MsgUtil.getJsonrpcVersion(jsonObject);
            if (null == jsonrpcVersion
                    || !jsonrpcVersion.equalsIgnoreCase(RequestObject.JSONRPC_VERSION))
            {
                channel.close();
                return;
            }

            // TODO:jsonrpcVersion具体值表示接口的版本号，用于接口的前向兼容。

            // 2.校验消息id
            Long rpcId = MsgUtil.getJsonrpcId(jsonObject);
            if (null == rpcId || rpcId < 1)
            {
                channel.close();
                return;
            }

            // 3.判断消息类型，共三中类型，即：method, result, error
            String rpcType = MsgUtil.getJsonrpcType(jsonObject);
            if (null == rpcType)
            {
                channel.close();
                return;
            }

            if (null == parseDiscern) {
                parseDiscern = new ParseDiscern(this);
            }

            parseDiscern.parseAndProcessByJsonRpcType(jsonObject, rpcType);

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

        ChannelProperty channelProperty = channel.attr(AttributeKeyConst.CHANNEL_PROPERTY_KEY).get();

        String sessionId = channelProperty.getSessionId();

        // 判断sessionId是否存在
        ServiceLoader<SessionSupport> load = ServiceLoader.load(SessionSupport.class);

        for (SessionSupport item : load)
        {
            item.channelRemoved(sessionId);
        }

        System.out.println("Client:" + channel.remoteAddress() + " leave");
        MsgUtil.channels.remove(channel);
    }

    /**
     * 通过变成活动状态时回调
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
     * 通道变成不活跃时回调
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception
    {
        // 清空 channel 属性
        Channel channel = ctx.channel();

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
        ctx.close();
    }


    public ChannelHandlerContext getCtx()
    {
        return ctx;
    }

    public void setCtx(ChannelHandlerContext ctx)
    {
        this.ctx = ctx;
    }

}
