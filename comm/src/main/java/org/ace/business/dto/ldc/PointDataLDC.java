package org.ace.business.dto.ldc;

/**
 *  逻辑点数据定义
 * @author xiepufeng
 * @date 2020/6/28 11:43
 */
public class PointDataLDC
{
    private Integer id; // 点id
    private String name; // 点名称
    private Integer value; // 点值
    private Integer workMode;//手自动状态

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
    {
        this.value = value;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getWorkMode() {
        return workMode;
    }

    public void setWorkMode(Integer workMode) {
        this.workMode = workMode;
    }
}
