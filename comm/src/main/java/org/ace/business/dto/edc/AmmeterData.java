package org.ace.business.dto.edc;

/**
 * 电表数据定义
 * @author xiepufeng
 * @date 2020/4/14 11:35
 */
public class AmmeterData implements Comparable<AmmeterData>
{
    // 电表序列号
    private Integer meterID;

    // 最多一百个电能参数
    private String electricData;

    // 实际采集的电能参数个数
    private Integer collectCount;

    // 年
    private Integer dateYear;

    // 月
    private Integer dateMonth;

    // 日
    private Integer dateDay;

    // 时
    private Integer timeHour;

    // 分
    private Integer timeMinute;

    // 秒
    private Integer timeSecond;

    public Integer getMeterID()
    {
        return meterID;
    }

    public void setMeterID(Integer meterID)
    {
        this.meterID = meterID;
    }

    public String getElectricData()
    {
        return electricData;
    }

    public void setElectricData(String electricData)
    {
        this.electricData = electricData;
    }

    public Integer getCollectCount()
    {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount)
    {
        this.collectCount = collectCount;
    }

    public Integer getDateYear()
    {
        return dateYear;
    }

    public void setDateYear(Integer dateYear)
    {
        this.dateYear = dateYear;
    }

    public Integer getDateMonth()
    {
        return dateMonth;
    }

    public void setDateMonth(Integer dateMonth)
    {
        this.dateMonth = dateMonth;
    }

    public Integer getDateDay()
    {
        return dateDay;
    }

    public void setDateDay(Integer dateDay)
    {
        this.dateDay = dateDay;
    }

    public Integer getTimeHour()
    {
        return timeHour;
    }

    public void setTimeHour(Integer timeHour)
    {
        this.timeHour = timeHour;
    }

    public Integer getTimeMinute()
    {
        return timeMinute;
    }

    public void setTimeMinute(Integer timeMinute)
    {
        this.timeMinute = timeMinute;
    }

    public Integer getTimeSecond()
    {
        return timeSecond;
    }

    public void setTimeSecond(Integer timeSecond)
    {
        this.timeSecond = timeSecond;
    }

    @Override
    public int compareTo(AmmeterData ammeterData)
    {
        if (null == ammeterData)
        {
            return 0;
        }

        Integer year = ammeterData.getDateYear();
        Integer month = ammeterData.getDateMonth();
        Integer day = ammeterData.getDateDay();
        Integer hour = ammeterData.getTimeHour();
        Integer minute = ammeterData.getTimeMinute();
        Integer second = ammeterData.getTimeSecond();

        if (!this.dateYear.equals(year))
        {
            return this.dateYear - year;
        }

        if (!this.dateMonth.equals(month))
        {
            return this.dateMonth - month;
        }

        if (!this.dateDay.equals(day))
        {
            return this.dateDay - day;
        }

        if (!this.timeHour.equals(hour))
        {
            return this.timeHour - hour;
        }

        if (!this.timeMinute.equals(minute))
        {
            return this.timeMinute - minute;
        }

        return this.timeSecond - second;
    }
}
