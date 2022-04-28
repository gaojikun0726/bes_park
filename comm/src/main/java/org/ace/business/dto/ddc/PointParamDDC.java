package org.ace.business.dto.ddc;

/**
 * 逻辑点参数定义（实点AI、实点AO、实点DO、实点DI、虚点AI、虚点AO、虚点DI、虚点DO）
 * @author xiepufeng
 * @date 2020/6/28 16:44
 */
public class PointParamDDC
{

    private Integer  pointType;      // 点类型
    private Integer  id;             // 点的ID
    private Integer  active;         // 是否启用，ACTIVE_ENABLE使能，ACTIVE_DISABLE禁止
    private String   name;           // [32]点的名字
    private String   alias;          // [64]点的别名
    private String   description;    // [64]描述
    private Integer  initValue;      // 值
    private Integer  alarmActive;    // 报警是否启用，ALARM_ACTIVE_ENABLE使能，ALARM_ACTIVE_DISABLE禁止
    private Integer  alarmType;      // 报警类型，不报警、标准报警、加强报警
    private Integer  alarmPriority;  // 报警优先级


    // AI AO DI DO
    private Integer  moduleID;       // 点所在模块的ID号
    // AI AO DI DO
    private Integer  channelIndex;   // 点所在通道索引
    // AI AO DI DO
    private Integer  workMode;       // 工作模式，手动模式或自动模式
    // AI AO DI DO
    private Integer  polarity;       // 极性，正向或反向
    // AI AO
    private Integer  lineType;       // 有效输入类型，包括0-10V、0-20mA、4-20mA
    // AI AO 虚点AI 虚点AO
    private String   unit;           // [12]工程单位，如℉、℃、KWh
    // AI AO 虚点AI 虚点AO
    private Integer  precision;      // 精度
    // AI AO 虚点AI 虚点AO
    private Integer  alarmHighValue; // 高限报警值
    // AI AO 虚点AI 虚点AO
    private Integer  alarmLowValue;  // 底限报警值
    // AI AO
    private Integer  highRange;      // 最高阀值
    // AI AO
    private Integer  lowRange;       // 最低阀值
    // DO 虚点DI 虚点DO
    private Integer  alarmTrigger;   // 报警触发  开或关
    // DI
    private Integer  activePassive;  // 有源无源

    public Integer getPointType()
    {
        return pointType;
    }

    public void setPointType(Integer pointType)
    {
        this.pointType = pointType;
    }

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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Integer getInitValue()
    {
        return initValue;
    }

    public void setInitValue(Integer initValue)
    {
        this.initValue = initValue;
    }

    public Integer getAlarmActive()
    {
        return alarmActive;
    }

    public void setAlarmActive(Integer alarmActive)
    {
        this.alarmActive = alarmActive;
    }

    public Integer getAlarmType()
    {
        return alarmType;
    }

    public void setAlarmType(Integer alarmType)
    {
        this.alarmType = alarmType;
    }

    public Integer getAlarmPriority()
    {
        return alarmPriority;
    }

    public void setAlarmPriority(Integer alarmPriority)
    {
        this.alarmPriority = alarmPriority;
    }

    public Integer getModuleID()
    {
        return moduleID;
    }

    public void setModuleID(Integer moduleID)
    {
        this.moduleID = moduleID;
    }

    public Integer getChannelIndex()
    {
        return channelIndex;
    }

    public void setChannelIndex(Integer channelIndex)
    {
        this.channelIndex = channelIndex;
    }

    public Integer getWorkMode()
    {
        return workMode;
    }

    public void setWorkMode(Integer workMode)
    {
        this.workMode = workMode;
    }

    public Integer getPolarity()
    {
        return polarity;
    }

    public void setPolarity(Integer polarity)
    {
        this.polarity = polarity;
    }

    public Integer getLineType()
    {
        return lineType;
    }

    public void setLineType(Integer lineType)
    {
        this.lineType = lineType;
    }

    public String getUnit()
    {
        return unit;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public Integer getPrecision()
    {
        return precision;
    }

    public void setPrecision(Integer precision)
    {
        this.precision = precision;
    }

    public Integer getAlarmHighValue()
    {
        return alarmHighValue;
    }

    public void setAlarmHighValue(Integer alarmHighValue)
    {
        this.alarmHighValue = alarmHighValue;
    }

    public Integer getAlarmLowValue()
    {
        return alarmLowValue;
    }

    public void setAlarmLowValue(Integer alarmLowValue)
    {
        this.alarmLowValue = alarmLowValue;
    }

    public Integer getHighRange()
    {
        return highRange;
    }

    public void setHighRange(Integer highRange)
    {
        this.highRange = highRange;
    }

    public Integer getLowRange()
    {
        return lowRange;
    }

    public void setLowRange(Integer lowRange)
    {
        this.lowRange = lowRange;
    }

    public Integer getAlarmTrigger()
    {
        return alarmTrigger;
    }

    public void setAlarmTrigger(Integer alarmTrigger)
    {
        this.alarmTrigger = alarmTrigger;
    }

    public Integer getActivePassive()
    {
        return activePassive;
    }

    public void setActivePassive(Integer activePassive)
    {
        this.activePassive = activePassive;
    }
}
