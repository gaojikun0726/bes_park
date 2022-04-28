package org.ace.business.dto.ldc;

/**
 *  计划参数定义
 * @author xiepufeng
 * @date 2020/6/29 10:40
 */
public class PlanParamLDC
{
    private Integer id;               //计划的ID号
    private Integer active;           //是否启用，ACTIVE_ENABLE使能，ACTIVE_DISABLE禁止
    private String  name;             //[32]计划的名字
    private String  alias;            //[64]别名
    private Integer planType;         //计划类型  常规计划或节假日计划
    private Integer startDateYear;    //开始日期年，如14
    private Integer startDateMonth;   //开始日期月，1-12
    private Integer startDateDay;     //开始日期日，1-31
    private Integer endDateYear;      //结束日期年
    private Integer endDateMonth;     //结束日期月
    private Integer endDateDay;       //结束日期日
    private Integer startTimeHour;    // 开始时间时，0-23
    private Integer startTimeMinute;  //开始时间分0-59
    private Integer startTimeSecond;  //开始时间秒0-59
    private Integer endTimeHour;      //结束时间时
    private Integer endTimeMinute;    //结束时间分
    private Integer endTimeSecond;    //结束时间秒
    private Integer executionWay;     //执行方式（每天执行、持续执行）
    private Integer weekMask; 	      //周掩码
    private Integer sceneType;        //场景类型，控制场景或采集场景
    private Integer sceneID;          //场景ID号
    private Integer modeID;           //模式ID号
    private Integer defaultModeID;    //默认模式ID

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getActive()
    {
        return active;
    }

    public void setActive(Integer active)
    {
        this.active = active;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAlias()
    {
        return alias;
    }

    public void setAlias(String alias)
    {
        this.alias = alias;
    }

    public Integer getPlanType()
    {
        return planType;
    }

    public void setPlanType(Integer planType)
    {
        this.planType = planType;
    }

    public Integer getStartDateYear()
    {
        return startDateYear;
    }

    public void setStartDateYear(Integer startDateYear)
    {
        this.startDateYear = startDateYear;
    }

    public Integer getStartDateMonth()
    {
        return startDateMonth;
    }

    public void setStartDateMonth(Integer startDateMonth)
    {
        this.startDateMonth = startDateMonth;
    }

    public Integer getStartDateDay() {
        return startDateDay;
    }

    public void setStartDateDay(Integer startDateDay) {
        this.startDateDay = startDateDay;
    }

    public Integer getEndDateYear()
    {
        return endDateYear;
    }

    public void setEndDateYear(Integer endDateYear)
    {
        this.endDateYear = endDateYear;
    }

    public Integer getEndDateMonth()
    {
        return endDateMonth;
    }

    public void setEndDateMonth(Integer endDateMonth)
    {
        this.endDateMonth = endDateMonth;
    }

    public Integer getEndDateDay()
    {
        return endDateDay;
    }

    public void setEndDateDay(Integer endDateDay)
    {
        this.endDateDay = endDateDay;
    }

    public Integer getStartTimeHour()
    {
        return startTimeHour;
    }

    public void setStartTimeHour(Integer startTimeHour)
    {
        this.startTimeHour = startTimeHour;
    }

    public Integer getStartTimeMinute()
    {
        return startTimeMinute;
    }

    public void setStartTimeMinute(Integer startTimeMinute)
    {
        this.startTimeMinute = startTimeMinute;
    }

    public Integer getStartTimeSecond()
    {
        return startTimeSecond;
    }

    public void setStartTimeSecond(Integer startTimeSecond)
    {
        this.startTimeSecond = startTimeSecond;
    }

    public Integer getEndTimeHour()
    {
        return endTimeHour;
    }

    public void setEndTimeHour(Integer endTimeHour)
    {
        this.endTimeHour = endTimeHour;
    }

    public Integer getEndTimeMinute()
    {
        return endTimeMinute;
    }

    public void setEndTimeMinute(Integer endTimeMinute)
    {
        this.endTimeMinute = endTimeMinute;
    }

    public Integer getEndTimeSecond()
    {
        return endTimeSecond;
    }

    public void setEndTimeSecond(Integer endTimeSecond)
    {
        this.endTimeSecond = endTimeSecond;
    }

    public Integer getExecutionWay()
    {
        return executionWay;
    }

    public void setExecutionWay(Integer executionWay)
    {
        this.executionWay = executionWay;
    }

    public Integer getWeekMask()
    {
        return weekMask;
    }

    public void setWeekMask(Integer weekMask)
    {
        this.weekMask = weekMask;
    }

    public Integer getSceneType()
    {
        return sceneType;
    }

    public void setSceneType(Integer sceneType)
    {
        this.sceneType = sceneType;
    }

    public Integer getSceneID()
    {
        return sceneID;
    }

    public void setSceneID(Integer sceneID)
    {
        this.sceneID = sceneID;
    }

    public Integer getModeID()
    {
        return modeID;
    }

    public void setModeID(Integer modeID)
    {
        this.modeID = modeID;
    }

    public Integer getDefaultModeID()
    {
        return defaultModeID;
    }

    public void setDefaultModeID(Integer defaultModeID)
    {
        this.defaultModeID = defaultModeID;
    }
}
