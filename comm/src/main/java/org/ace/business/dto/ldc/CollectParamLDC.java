package org.ace.business.dto.ldc;

/**
 * @author sunzhiyuan
 * @Data 2020/12/22 17:18
 */
public class CollectParamLDC {

    private Integer sceneType;// 1 采集场景

    private Integer id;//场景ID

    private Integer active;//是否使能

    private String name;//场景名称

    private String alias; //场景别名

    private CollectModeLDC collectMode;

    public Integer getSceneType() {
        return sceneType;
    }

    public void setSceneType(Integer sceneType) {
        this.sceneType = sceneType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public CollectModeLDC getCollectMode() {
        return collectMode;
    }

    public void setCollectMode(CollectModeLDC collectMode) {
        this.collectMode = collectMode;
    }
}
