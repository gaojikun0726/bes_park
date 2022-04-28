package org.ace.socketserver.handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.netty.channel.Channel;
import org.ace.socketserver.constant.Method;
import org.ace.socketserver.dto.param.EventParam;
import org.ace.socketserver.dto.result.CmdMsg;
import org.ace.socketserver.util.MsgUtil;

import java.lang.reflect.Type;
import java.util.logging.Logger;

public class SocketServerService
{
    private static final Logger log = Logger.getLogger(SocketServerService.class.getName());

    public static<T> boolean postEvent(String subscriber, String subEvent, T content)
    {
        if (subscriber == null || subscriber.isEmpty() || subEvent == null || subEvent.isEmpty())
        {
            return false;
        }

        Channel channel = MsgUtil.getChannelByTokenSN(subscriber);

        if (channel == null)
        {
            log.warning("Post event failed, because the channel for event subscriber:" + subscriber + " is not exists");
            return false;
        }

        /*ChannelProperty channelProperty = channel.attr(AttributeKeyConst.CHANNEL_PROPERTY_KEY).get();
        channelProperty.increaseEventId();
        long eventId = channelProperty.getEventId();*/

        EventParam<T> eventParam = new EventParam<>();
        eventParam.setSubEvent(subEvent);
        eventParam.setContent(content);

        CmdMsg<EventParam<T>> eventCmd = new CmdMsg<>();
        eventCmd.setMethod(Method.EVENT);
        eventCmd.setParams(eventParam);

        Gson gson = new Gson();
        Type type = new TypeToken<CmdMsg<EventParam<T>>>()
        {
        }.getType();

        String msg = gson.toJson(eventCmd, type);
        channel.writeAndFlush(msg);

        return true;

    }

    public static boolean postEvent(String subscriber, String content)
    {
        if (subscriber == null || subscriber.isEmpty() || content == null || content.isEmpty())
        {
            return false;
        }

        Channel channel = MsgUtil.getChannelByTokenSN(subscriber);

        if (channel == null)
        {
            log.warning("Post event failed, because the channel for event subscriber:" + subscriber + " is not exists");
            return false;
        }

        channel.writeAndFlush(content);

        return true;
    }

}
