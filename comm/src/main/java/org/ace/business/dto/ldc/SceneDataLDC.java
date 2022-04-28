package org.ace.business.dto.ldc;

/**
 * 场景数据参数定义
 * @author xiepufeng
 * @date 2020/6/29 8:38
 */
public class SceneDataLDC
{
    private Integer id; // 场景的ID

    private Integer active; // 是否启用，ACTIVE_ENABLE使能，ACTIVE_DISABLE禁止

    private String name; // 场景名字

    private String alias; // 场景别名

    private CollectModeLDC collectMode; // 场景模式 内部有10个场景模式 组

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

    public CollectModeLDC getCollectMode()
    {
        return collectMode;
    }

    public void setCollectMode(CollectModeLDC collectMode)
    {
        this.collectMode = collectMode;
    }
}
