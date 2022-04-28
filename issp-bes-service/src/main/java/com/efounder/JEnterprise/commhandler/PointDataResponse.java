package com.efounder.JEnterprise.commhandler;

/**
 * @author xiepufeng
 * @date 2020/7/28 14:31
 */
public class PointDataResponse
{
    private Integer id; // 点id

    private String name; // 点名称（系统名称）

    private String sysNameOld; // 对应数据库 F_SYS_NAME_OLD 字段

    private String alias; // 点别名

    private String value; // 点值

    private String unit; // 单位

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
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

    public String getUnit()
    {
        return unit;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public String getSysNameOld()
    {
        return sysNameOld;
    }

    public void setSysNameOld(String sysNameOld)
    {
        this.sysNameOld = sysNameOld;
    }
}
