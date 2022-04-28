package org.ace.business.dto.ddc;

/**
 * 场景模式点信息定义
 * @author xiepufeng
 * @date 2020/6/29 8:50
 */
public class ControlPointDDC
{
    private Integer active; // 是否启用，ACTIVE_ENABLE使能，ACTIVE_DISABLE禁止

    private Integer pointID; // 计划所对应点的ID号

    private Integer runValue; // 运行值,数字量0或255，模拟量是个数值

    // 采集场景
    private Integer collectID; // 采集方式，时间间隔采集、值采集

    // 采集场景
    private Integer timeUnit; // 时间间隔单位

    // 采集场景
    private Integer value; // 采集时间间隔值、变化量的值

    public Integer getActive()
    {
        return active;
    }

    public void setActive(Integer active)
    {
        this.active = active;
    }

    public Integer getPointID()
    {
        return pointID;
    }

    public void setPointID(Integer pointID)
    {
        this.pointID = pointID;
    }

    public Integer getRunValue()
    {
        return runValue;
    }

    public void setRunValue(Integer runValue)
    {
        this.runValue = runValue;
    }

    public Integer getCollectID()
    {
        return collectID;
    }

    public void setCollectID(Integer collectID)
    {
        this.collectID = collectID;
    }

    public Integer getTimeUnit()
    {
        return timeUnit;
    }

    public void setTimeUnit(Integer timeUnit)
    {
        this.timeUnit = timeUnit;
    }

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
    {
        this.value = value;
    }
}
