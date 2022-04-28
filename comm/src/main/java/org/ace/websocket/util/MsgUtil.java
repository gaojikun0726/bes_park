package org.ace.websocket.util;

import com.google.gson.JsonObject;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.ace.websocket.bo.ChannelProperty;
import org.ace.websocket.constant.AttributeKeyConst;
import org.ace.websocket.constant.RequestObject;
import org.ace.websocket.constant.ResponseObject;

import java.util.logging.Logger;

/**
 * @author xiepufeng
 */
public class MsgUtil {

    private static final Logger log = Logger.getLogger(MsgUtil.class.getName());

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 获取消息ID
     *
     * @param jsonObject
     * @return
     */
    public static Long getJsonrpcId(JsonObject jsonObject)
    {
        if (null == jsonObject)
        {
            return null;
        }

        if (jsonObject.has(RequestObject.ID))
        {
            return jsonObject.getAsJsonPrimitive(RequestObject.ID).getAsLong();
        }

        return null;

    }


    /**
     * 获取消息的类型
     *
     * @param jsonObject
     * @return
     */
    public static String getJsonrpcType(JsonObject jsonObject)
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
     * 提取jsonrpc版本信息
     *
     * @param jsonObject
     * @return
     */
    public static String getJsonrpcVersion(JsonObject jsonObject)
    {
        if (null == jsonObject)
        {
            return null;
        }

        if (jsonObject.has(RequestObject.JSONRPC))
        {
            return jsonObject.getAsJsonPrimitive(RequestObject.JSONRPC).getAsString();
        }

        return null;

    }

    /**
     * 根据 http session id 获取其绑定的 channel
     *
     * @param httpSessionId
     * @return
     */
    public static Channel getChannelByHttpSessionId(String httpSessionId)
    {
        if (httpSessionId == null || httpSessionId.trim().isEmpty())
        {
            log.warning("Parameter error: httpSessionId is null");
            return null;
        }

        Channel channelTarget = null;
        for (Channel channel : channels)
        {

            ChannelProperty channelProperty = channel.attr(AttributeKeyConst.CHANNEL_PROPERTY_KEY).get();
            if (channelProperty == null || !httpSessionId.equalsIgnoreCase(channelProperty.getSessionId()))
            {
                continue;
            }

            channelTarget = channel;
            break;
        }

        if (channelTarget == null)
        {
            log.warning("Channel bound by  httpSessionId:" + httpSessionId + " is not exists");
            return null;
        }

        return channelTarget;
    }

}
