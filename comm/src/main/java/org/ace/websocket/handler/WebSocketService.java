package org.ace.websocket.handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.ace.websocket.bo.ChannelProperty;
import org.ace.websocket.constant.AttributeKeyConst;
import org.ace.websocket.constant.Method;
import org.ace.websocket.constant.RequestObject;
import org.ace.websocket.dto.param.EventParam;
import org.ace.websocket.dto.result.CmdMsg;
import org.ace.websocket.util.MsgUtil;

import java.lang.reflect.Type;
import java.util.logging.Logger;

/**
 * 给客户端发送消息
 */
public class WebSocketService
{
    private static final Logger log = Logger.getLogger(WebSocketService.class.getName());

    /**
     * 给指定客户端发送消息
     * @param subscriber
     * @param subEvent
     * @param content
     * @param <T>
     * @return
     */
    public static<T> boolean postEvent(String subscriber, String subEvent, T content)
    {
        if (subscriber == null || subscriber.isEmpty() || subEvent == null || subEvent.isEmpty())
        {
            return false;
        }

        Channel channel = MsgUtil.getChannelByHttpSessionId(subscriber);

        if (channel == null)
        {
            log.warning("Post event failed, because the channel for event subscriber:" + subscriber + " is not exists");
            return false;
        }

        ChannelProperty channelProperty = channel.attr(AttributeKeyConst.CHANNEL_PROPERTY_KEY).get();
        channelProperty.increaseEventId();
        long eventId = channelProperty.getEventId();

        EventParam<T> eventParam = new EventParam<>();
        eventParam.setSubEvent(subEvent);
        eventParam.setContent(content);

        CmdMsg<EventParam<T>> eventCmd = new CmdMsg<>();
        eventCmd.setJsonrpc(RequestObject.JSONRPC_VERSION);
        eventCmd.setMethod(Method.EVENT);
        eventCmd.setParams(eventParam);
        eventCmd.setId(eventId);

        Gson gson = new Gson();
        Type type = new TypeToken<CmdMsg<EventParam<T>>>()
        {
        }.getType();

        String msg = gson.toJson(eventCmd, type);
        channel.writeAndFlush(new TextWebSocketFrame(msg));

        return true;

    }

    /**
     * 群发消息
     * @param subEvent
     * @param content
     * @param <T>
     * @return
     */
    public static<T> boolean postEvent(String subEvent, T content)
    {
        if (subEvent == null || subEvent.isEmpty())
        {
            return false;
        }

        EventParam<T> eventParam = new EventParam<>();
        eventParam.setSubEvent(subEvent);
        eventParam.setContent(content);

        CmdMsg<EventParam<T>> eventCmd = new CmdMsg<>();
        eventCmd.setJsonrpc(RequestObject.JSONRPC_VERSION);
        eventCmd.setMethod(Method.EVENT);
        eventCmd.setParams(eventParam);

        Gson gson = new Gson();
        Type type = new TypeToken<CmdMsg<EventParam<T>>>()
        {
        }.getType();

        String msg = gson.toJson(eventCmd, type);
        MsgUtil.channels.writeAndFlush(new TextWebSocketFrame(msg));

        return true;

    }

}
