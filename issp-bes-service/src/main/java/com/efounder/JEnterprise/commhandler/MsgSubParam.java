package com.efounder.JEnterprise.commhandler;

/**
 * @author xiepufeng
 * @date 2020/5/15 11:02
 */
public class MsgSubParam
{
    private Integer index; //指令

    private String channelID; // ip 地址

    private String sessionId; // sessionId

    private Long createTime; // 创建时间

    private String identity;

    public Integer getIndex()
    {
        return index;
    }

    public void setIndex(Integer index)
    {
        this.index = index;
    }

    public String getChannelID()
    {
        return channelID;
    }

    public void setChannelID(String channelID)
    {
        this.channelID = channelID;
    }

    public String getSessionId()
    {
        return sessionId;
    }

    public void setSessionId(String sessionId)
    {
        this.sessionId = sessionId;
    }

    public Long getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Long createTime)
    {
        this.createTime = createTime;
    }

    public String getIdentity()
    {
        return identity;
    }

    public void setIdentity(String identity)
    {
        this.identity = identity;
    }
}
