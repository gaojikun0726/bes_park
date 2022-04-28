package org.ace.business.dto.ldc;

import java.util.List;

/**
 * 场景模式定义
 * @author xiepufeng
 * @date 2020/6/29 8:43
 */
public class CollectModeLDC
{
    private Integer active; // 是否启用，ACTIVE_ENABLE使能，ACTIVE_DISABLE禁止

    private String name; //模式名字

    private Integer id;  //模式ID

    private List<CollectPointLDC> collectPoint; // 场景模式点信息定义

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

    public List<CollectPointLDC> getCollectPoint()
    {
        return collectPoint;
    }

    public void setCollectPoint(List<CollectPointLDC> collectPoint)
    {
        this.collectPoint = collectPoint;
    }
}
