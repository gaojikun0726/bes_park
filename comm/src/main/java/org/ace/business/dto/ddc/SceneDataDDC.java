package org.ace.business.dto.ddc;

/**
 * 场景数据参数定义
 * @author xiepufeng
 * @date 2020/6/29 8:38
 */
public class SceneDataDDC
{
    // 0 控制场景 1 采集场景
    private Integer sceneType;

    private Integer id; // 场景的ID

    private Integer active; // 是否启用，ACTIVE_ENABLE使能，ACTIVE_DISABLE禁止

    private String name; // 场景名字

    private String alias; // 场景别名

    private ControlModeDDC controlMode; // 场景模式 内部有10个场景模式 组

    private Integer sceneId;//下位机上来的场景id

    private Integer modeId;//下位机上来的模式id

    public Integer getSceneType()
    {
        return sceneType;
    }

    public void setSceneType(Integer sceneType)
    {
        this.sceneType = sceneType;
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

    public ControlModeDDC getControlMode() {
        return controlMode;
    }

    public void setControlMode(ControlModeDDC controlMode) {
        this.controlMode = controlMode;
    }

    public Integer getSceneId() {
        return sceneId;
    }

    public void setSceneId(Integer sceneId) {
        this.sceneId = sceneId;
    }

    public Integer getModeId() {
        return modeId;
    }

    public void setModeId(Integer modeId) {
        this.modeId = modeId;
    }
}
