package org.ace.socketserver.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.ace.business.constant.Code;
import org.ace.business.dto.ReceiveMsg;
import org.ace.business.handler.CrcUtil;
import org.ace.socketserver.bo.ChannelProperty;
import org.ace.socketserver.constant.AttributeKeyConst;
import org.ace.socketserver.constant.RequestObject;
import org.ace.socketserver.constant.ResponseObject;

import java.lang.reflect.Type;
import java.util.logging.Logger;

/**
 * @author xiepufeng
 */
public class MsgUtil
{

    private static final Logger log = Logger.getLogger(MsgUtil.class.getName());

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 获取消息的类型
     *
     * @param jsonObject
     * @return
     */
    public static String getJsonType(JsonObject jsonObject)
    {
        if (null == jsonObject)
        {
            return null;
        }

        if (jsonObject.has(RequestObject.METHOD))
        {
            return RequestObject.METHOD;
        }

        if (jsonObject.has(ResponseObject.RESULT))
        {
            return ResponseObject.RESULT;
        }

        if (jsonObject.has(ResponseObject.ERROR))
        {
            return ResponseObject.ERROR;
        }

        return null;
    }

    /**
     * 根据 tokenSN 获取其绑定的 channel
     *
     * @param tokenSN
     * @return
     */
    public static Channel getChannelByTokenSN(String tokenSN)
    {
        if (tokenSN == null || tokenSN.trim().isEmpty())
        {
            log.warning("Parameter error: tokenSN is null");
            return null;
        }

        Channel channelTarget = null;
        for (Channel channel : channels)
        {

            ChannelProperty channelProperty = channel.attr(AttributeKeyConst.CHANNEL_PROPERTY_KEY).get();
            if (channelProperty == null || !tokenSN.equalsIgnoreCase(channelProperty.getTokenSN()))
            {
                continue;
            }

            channelTarget = channel;
            break;
        }

        if (channelTarget == null)
        {
            log.warning("Channel bound by  tokenSN:" + tokenSN + " is not exists");
            return null;
        }

        return channelTarget;
    }


    /**
     * 解析地址并以键值对的方式存储到 Map 中，key （'host', 'port'）
     *
     * @param address
     * @return
     */
    /*public static Map<String, String> parseAddress(String address)
    {
        if (null == address || address.isEmpty())
        {
            return null;
        }

        Map<String, String> formatAddress  = new HashMap<>();

        String[] splitAddress = address.split("/|:");

        int splitAddressLength =  splitAddress.length;

        if (splitAddressLength > 1)
        {
            String host = splitAddress[1];
            formatAddress.put(Address.HOST, host);
        }

        if (splitAddressLength > 2)
        {
            String port = address.split("/|:")[2];
            formatAddress.put(Address.PORT, port);
        }


        return formatAddress;

    }*/

    /**
     * 给客户端发送响应数据包
     * @param index
     */
    public static void respond(ChannelHandlerContext ctx, Integer index)
    {
        if (null == ctx || null == index)
        {
            return;
        }

        Gson gson = new Gson();
        Type type = new TypeToken<ReceiveMsg>()
        {
        }.getType();

        ReceiveMsg receiveMsg = new ReceiveMsg();

        receiveMsg.setCode(Code.SUCCEED);

        receiveMsg.setIndex(index);

        String msg = gson.toJson(receiveMsg, type);

        msg = CrcUtil.addVerifyCRC(msg);

        if (null == msg)
        {
            return;
        }

        ctx.channel().writeAndFlush(msg);
    }
}
