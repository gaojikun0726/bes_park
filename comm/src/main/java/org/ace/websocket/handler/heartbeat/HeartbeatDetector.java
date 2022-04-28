package org.ace.websocket.handler.heartbeat;

import io.netty.channel.Channel;
import org.ace.websocket.bo.ChannelProperty;
import org.ace.websocket.constant.AttributeKeyConst;
import org.ace.websocket.enums.ChannelStatus;
import org.ace.websocket.enums.ErrorCodeEnum;
import org.ace.websocket.util.MsgUtil;

import java.util.TimerTask;
import java.util.logging.Logger;

/**
 * 心跳检测器
 */
public class HeartbeatDetector extends TimerTask
{

    private static final Logger log = Logger.getLogger(HeartbeatDetector.class.getName());

    /*
     * 周期，默认30秒
     */
    private int interval = 30;

    /*
     * 超时周期数,默认为5个周期
     */
    private int timeoutIntervals = 5;

    public HeartbeatDetector(int interval, int timeoutIntervals) {
        this.interval = interval;
        this.timeoutIntervals = timeoutIntervals;
    }

    @Override
    public void run()
    {
        for (Channel channel : MsgUtil.channels)
        {
            ChannelProperty channelProperty = channel.attr(AttributeKeyConst.CHANNEL_PROPERTY_KEY).get();
            if (channelProperty == null)
            {
                log.warning("Error code:" + ErrorCodeEnum.ERR_UNKNOWN
                        + " get Channel Property is null when Heartbeat Timer Task");

                channel.close();
                continue;
            }

            // INITIAL 状态下绑定 http session 超时处理
            ChannelStatus channelStatus = channelProperty.getStatus();
            if (channelStatus == ChannelStatus.INITIAL)
            {
                int statusCount = channelProperty.getStatusCount() + 1;
                if (statusCount > 1)
                {
                    // 超过一个周期没有绑定session的话，则关闭 channel
                    log.warning("Error: Channel not bind a session within the time period. remote address:"
                            + channel.remoteAddress());
                    channel.close();

                }
                else
                {
                    channelProperty.setStatusCount(statusCount);
                }

                continue;
            }

            // 如果有心跳，则复位心跳超时计数器为0
            if (channelProperty.isResetCount())
            {
                channelProperty.setResetCount(false);
                channelProperty.setCount(0);
                continue;
            }

            // 判断心跳是否超时，超时则关闭。
            int count = channelProperty.getCount() + 1;
            if (count >= timeoutIntervals)
            {
                log.warning("Error: channel was overdue, session id:" + channelProperty.getSessionId() + " user id:"
                        + channelProperty.getUserId());
                // 超时，关闭通道
                channel.close();
            }
            else
            {
                channelProperty.setCount(count);

            }
        }
    }


    public final int getInterval()
    {
        return interval;
    }

    public final void setInterval(int interval)
    {
        this.interval = interval;
    }

    public final int getTimeoutIntervals()
    {
        return timeoutIntervals;
    }

    public final void setTimeoutIntervals(int timeoutIntervals)
    {
        this.timeoutIntervals = timeoutIntervals;
    }

}
