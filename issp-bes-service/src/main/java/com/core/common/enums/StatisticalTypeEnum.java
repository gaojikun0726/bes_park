package com.core.common.enums;

/**
 * @author xiepufeng
 * @date 2020/6/17 15:08
 */
public enum StatisticalTypeEnum
{
    HOUR(0, "按小时统计"),
    DAY(1, "按天统计"),
    MONTH(2, "按月统计"),
    YEAR(3, "按年统计");

    private Integer code;
    private String name;

    StatisticalTypeEnum(Integer code, String name)
    {
        this.code = code;
        this.name = name;
    }

    public Integer getCode()
    {
        return code;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
