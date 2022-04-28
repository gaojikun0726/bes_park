package org.ace.business.dto.edc;

/**
 * 获取电表历史数据定义
 * @author xiepufeng
 * @date 2020/4/15 7:30
 */
public class AmmeterDataHistory
{
    private Integer meterID;

    // 选择那一天，如果不存在则是获取实时数据，存在获取历史数据
    private Integer selectDay;

    public Integer getMeterID()
    {
        return meterID;
    }

    public void setMeterID(Integer meterID)
    {
        this.meterID = meterID;
    }

    public Integer getselectDay()
    {
        return selectDay;
    }

    public void setselectDay(Integer selectDay)
    {
        this.selectDay = selectDay;
    }
}
