package com.efounder.datareported.model.energy;

import com.framework.common.utils.xmlprocessor.annotation.AttrXml;

/**
 * 能耗单项小时结果信息
 * @author xiepufeng
 * @date 2020/11/10 10:51
 */
public class EnergyItemHourResultModel
{
    /**
     * 能耗小时结果代码
     */
    @AttrXml(name = "F_HourResultID")
    private String hourResultID;

    /**
     * 能源代码
     */
    @AttrXml(name = "F_EnergyItemCode")
    private String energyItemCode;

    /**
     * 开始小时
     */
    @AttrXml(name = "F_StartHour")
    private String startHour;

    /**
     * 结束小时
     */
    @AttrXml(name = "F_EndHour")
    private String endHour;

    /**
     * 两位小数
     * 小时能耗数值
     */
    @AttrXml(name = "F_HourValue")
    private String hourValue;

    /**
     * 四位小数
     * 小时当量数值
     *  hourValue * 千克标准煤
     */
    @AttrXml(name = "F_HourEquValue")
    private String hourEquValue;

    /**
     *  0：无效数据，1：有效数据
     */
    @AttrXml(name = "F_State")
    private Integer state;


    public String getHourResultID()
    {
        return hourResultID;
    }

    public void setHourResultID(String hourResultID)
    {
        this.hourResultID = hourResultID;
    }

    public String getEnergyItemCode()
    {
        return energyItemCode;
    }

    public void setEnergyItemCode(String energyItemCode)
    {
        this.energyItemCode = energyItemCode;
    }

    public String getStartHour()
    {
        return startHour;
    }

    public void setStartHour(String startHour)
    {
        this.startHour = startHour;
    }

    public String getEndHour()
    {
        return endHour;
    }

    public void setEndHour(String endHour)
    {
        this.endHour = endHour;
    }

    public String getHourValue()
    {
        return hourValue;
    }

    public void setHourValue(String hourValue)
    {
        this.hourValue = hourValue;
    }

    public String getHourEquValue()
    {
        return hourEquValue;
    }

    public void setHourEquValue(String hourEquValue)
    {
        this.hourEquValue = hourEquValue;
    }

    public Integer getState()
    {
        return state;
    }

    public void setState(Integer state)
    {
        this.state = state;
    }
}
