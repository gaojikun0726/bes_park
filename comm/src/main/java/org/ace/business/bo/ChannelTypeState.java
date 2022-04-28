package org.ace.business.bo;

/**
 * 客户在线状态及类型
 * @author xiepufeng
 * @date 2020/7/17 9:41
 */
public class ChannelTypeState
{
    private String type;

    private Boolean state;

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public Boolean getState()
    {
        return state;
    }

    public void setState(Boolean state)
    {
        this.state = state;
    }
}
