package org.ace.websocket.bo;

import org.ace.websocket.enums.ChannelStatus;

/**
 * Channel对应的属性数据定义
 *
 * @author xiepufeng
 *
 */
public class ChannelProperty
{
    /*
     * Channel 绑定的会话ID
     */
    private String sessionId;

    /*
     * Channel 绑定的用户ID
     */
    private String userId;

    /*
     * Channel 绑定的用户tokenSN
     */
    private String tokenSN;

    /*
     * channel状态
     */
    private ChannelStatus status;

    /*
     * channel当前状态保持周期计数器
     */
    private int statusCount;

    /*
     * 事件序号，自1开始递增
     */
    private long eventId;

    /*
     * 命令序号，自1开始递增
     */
    private long cmdId;

    /*
     * 心跳超时计数器（注意：该变量只能在一个线程中访问）
     */
    private int count = 0;

    /*
     * 超时计数器归零标志
     */
    private volatile boolean resetCount = false;

    /**
     * 通知定时任务线程重置count标志(支持高并发)。
     */
    public void heartbeatRefresh()
    {
        if (!resetCount)
        {
            resetCount = true;
        }
    }

    /**
     * 事件序号递增。
     */
    public synchronized final void increaseEventId()
    {
        if (eventId >= Long.MAX_VALUE)
        {
            eventId = 0;
        }
        else
        {
            eventId = eventId + 1;
        }
    }

    /**
     * 事件序号递增。
     */
    public synchronized final void increaseCmdId()
    {
        if (cmdId >= Long.MAX_VALUE)
        {
            cmdId = 0;
        }
        else
        {
            cmdId = cmdId + 1;
        }
    }

    public String getSessionId()
    {
        return sessionId;
    }

    public void setSessionId(String sessionId)
    {
        this.sessionId = sessionId;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getTokenSN()
    {
        return tokenSN;
    }

    public void setTokenSN(String tokenSN)
    {
        this.tokenSN = tokenSN;
    }

    public final ChannelStatus getStatus()
    {
        return status;
    }

    public final void setStatus(ChannelStatus status)
    {
        this.status = status;
    }

    public synchronized final long getEventId()
    {
        return eventId;
    }

    public synchronized final void setEventId(long eventId)
    {
        this.eventId = eventId;
    }

    public synchronized final long getCmdId()
    {
        return cmdId;
    }

    public synchronized final void setCmdId(long cmdId)
    {
        this.cmdId = cmdId;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public boolean isResetCount()
    {
        return resetCount;
    }

    public void setResetCount(boolean resetCount)
    {
        this.resetCount = resetCount;
    }

    public final int getStatusCount()
    {
        return statusCount;
    }

    public final void setStatusCount(int statusCount)
    {
        this.statusCount = statusCount;
    }

}
