package org.ace.business.dto;

/**
 * 时间参数定义
 * @author xiepufeng
 * @date 2020/4/14 17:16
 */
public class TimeData
{
    // 年份表示2000年以后的年数如12代表2012年
    private Integer year;

    // 1-12
    private Integer month;

    // 1-31
    private Integer day;

    // 星期几,取值范围MONDAY-SUNDAY(1-7)
    private Integer wday;

    // 0-23
    private Integer hour;

    // 0-59
    private Integer minute;

    // 0-59
    private Integer second;

    public Integer getYear()
    {
        return year;
    }

    public void setYear(Integer year)
    {
        this.year = year;
    }

    public Integer getMonth()
    {
        return month;
    }

    public void setMonth(Integer month)
    {
        this.month = month;
    }

    public Integer getDay()
    {
        return day;
    }

    public void setDay(Integer day)
    {
        this.day = day;
    }

    public Integer getWday()
    {
        return wday;
    }

    public void setWday(Integer wday)
    {
        this.wday = wday;
    }

    public Integer getHour()
    {
        return hour;
    }

    public void setHour(Integer hour)
    {
        this.hour = hour;
    }

    public Integer getMinute()
    {
        return minute;
    }

    public void setMinute(Integer minute)
    {
        this.minute = minute;
    }

    public Integer getSecond()
    {
        return second;
    }

    public void setSecond(Integer second)
    {
        this.second = second;
    }
}
