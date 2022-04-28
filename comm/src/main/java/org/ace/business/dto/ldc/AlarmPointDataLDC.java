package org.ace.business.dto.ldc;

/**
 *  逻辑点的报警信息定义
 * @author xiepufeng
 * @date 2020/6/28 18:13
 */
public class AlarmPointDataLDC
{
    private Integer id;

    private Integer currentValue;      // 获取的当前值

    private Integer alarmState;        //报警状态

    private Integer alarmTriggerValue; //首次报警触发值

    private Integer alarmMaxValue;     //报警最大值(即发生过的最大一次报警值)

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getCurrentValue()
    {
        return currentValue;
    }

    public void setCurrentValue(Integer currentValue)
    {
        this.currentValue = currentValue;
    }

    public Integer getAlarmState()
    {
        return alarmState;
    }

    public void setAlarmState(Integer alarmState)
    {
        this.alarmState = alarmState;
    }

    public Integer getAlarmTriggerValue()
    {
        return alarmTriggerValue;
    }

    public void setAlarmTriggerValue(Integer alarmTriggerValue)
    {
        this.alarmTriggerValue = alarmTriggerValue;
    }

    public Integer getAlarmMaxValue()
    {
        return alarmMaxValue;
    }

    public void setAlarmMaxValue(Integer alarmMaxValue)
    {
        this.alarmMaxValue = alarmMaxValue;
    }
}
