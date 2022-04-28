package org.ace.business.dto.ddc;

import java.util.List;

/**
 * 场景模式定义
 * @author xiepufeng
 * @date 2020/6/29 8:43
 */
public class ControlModeDDC
{
    private Integer id; // 场景的ID

    private Integer active; // 是否启用，ACTIVE_ENABLE使能，ACTIVE_DISABLE禁止

    private String name; // 场景名字

    private List<ControlPointDDC> controlPoint; // 场景模式点信息定义

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

    public List<ControlPointDDC> getControlPoint() {
        return controlPoint;
    }

    public void setControlPoint(List<ControlPointDDC> controlPoint) {
        this.controlPoint = controlPoint;
    }
}
