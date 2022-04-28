package com.efounder.JEnterprise.model.systemcenter.Interfaceconfig;

/**
 *
 * 控制器状态
 *
 * @author xiepufeng
 * @date 2021/3/15 10:08
 */
public class ControllerStateModel
{
    public static final int ON_LINE = 1; // 在线

    public static final int OFF_LINE = 0; // 离线

    public static final int EDC = 1; // 能耗控制器

    public static final int DDC = 2; // DDC 控制器

    public static final int LDC = 3; // 照明控制器控制器

    public Integer state; // 在线状态

    public String ip;

    public Integer type;

    public Integer getState()
    {
        return state;
    }

    public void setState(Integer state)
    {
        this.state = state;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public Integer getType()
    {
        return type;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }
}
