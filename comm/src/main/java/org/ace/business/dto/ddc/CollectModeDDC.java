package org.ace.business.dto.ddc;

import java.util.List;

/**
 * 场景模式定义
 * @author xiepufeng
 * @date 2020/6/29 8:43
 */
public class CollectModeDDC
{
    private Integer id; // 场景的ID

    private Integer active; // 是否启用，ACTIVE_ENABLE使能，ACTIVE_DISABLE禁止

    private String name; // 场景名字

    private List<CollectPointDDC> collectPoint; // 场景模式点信息定义

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

    public List<CollectPointDDC> getCollectPoint()
    {
        return collectPoint;
    }

    public void setCollectPoint(List<CollectPointDDC> collectPoint)
    {
        this.collectPoint = collectPoint;
    }
}
