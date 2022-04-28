package org.ace.business.handler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.ace.business.constant.Cmd;
import org.ace.business.dto.ReceiveMsg;
import org.ace.socketserver.bo.ChannelProperty;
import org.ace.socketserver.constant.AttributeKeyConst;
import org.ace.socketserver.enums.ChannelStatus;
import org.ace.socketserver.util.MsgUtil;

import java.lang.reflect.Type;


/**
 * @author xiepufeng
 * @date 2020/10/9 9:28
 */
public class TokenHandler
{
    // IP 绑定
    public static void handler(ChannelHandlerContext ctx, JsonObject jsonObject)
    {
        if (null == ctx || null == jsonObject)
        {
            return;
        }

        // 绑定 IP
        if (!bind(ctx, jsonObject))
        {
            return;
        }

        // 给客户端发送响应数据包
        MsgUtil.respond(ctx, Cmd.IP_BIND);

        // 回调
        ReceiptMsgHandler.threadPoolExecutor.execute(() -> callback(ctx));
    }


    // 绑定 IP
    public static boolean bind(ChannelHandlerContext ctx, JsonObject jsonObject)
    {

        if (null == ctx || null == jsonObject || jsonObject.isJsonNull())
        {
            return false;
        }

        Type type = new TypeToken<ReceiveMsg>()
        {
        }.getType();

        Gson gson = new Gson();

        ReceiveMsg msg = gson.fromJson(jsonObject, type);

        String ip = msg.getIp();

        if (ip == null || ip == "")
        {
            return false;
        }

        // 设置 channel 属性
        Channel channel = ctx.channel();

        ChannelProperty channelProperty = channel.attr(AttributeKeyConst.CHANNEL_PROPERTY_KEY).get();

        channelProperty.setTokenSN(ip);
        channelProperty.setStatus(ChannelStatus.TOKENSN_BOUND);

        System.out.println("ip:" + ip + " binding success");

        return true;

    }

    private static void callback(ChannelHandlerContext ctx)
    {
        if (ctx == null)
        {
            return;
        }

        ClientMsgReceive clientMsgReceive = ReceiptMsgHandler.clientMsgReceive;

        // 设置控制器状态为在线
        if (null != clientMsgReceive)
        {
            // 设置 channel 属性
            Channel channel = ctx.channel();

            ChannelProperty channelProperty = channel.attr(AttributeKeyConst.CHANNEL_PROPERTY_KEY).get();

            if (channelProperty == null)
            {
                return;
            }

            String ip = channelProperty.getTokenSN();

            clientMsgReceive.controllerState(ip, true);
        }
    }
}
