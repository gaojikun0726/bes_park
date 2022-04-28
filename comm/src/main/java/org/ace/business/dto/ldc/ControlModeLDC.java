package org.ace.business.dto.ldc;

import java.util.List;

/**
 * @author sunzhiyuan
 * @Data 2020/12/22 16:39
 */
public class ControlModeLDC {

    private Integer active; // 是否启用，ACTIVE_ENABLE使能，ACTIVE_DISABLE禁止

    private String name; //模式名字

    private Integer id;  //模式ID

    private List<ControlPointLDC> controlPoint; // 场景模式点信息定义

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ControlPointLDC> getControlPoint() {
        return controlPoint;
    }

    public void setControlPoint(List<ControlPointLDC> controlPoint) {
        this.controlPoint = controlPoint;
    }
}
