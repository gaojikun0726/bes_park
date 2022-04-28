package org.ace.business.dto.ddc;

/**
 *
 * @Description: 楼控场景信息
 *
 * @auther: wanghongjie
 * @date: 17:55 2021/8/12
 * @param:
 * @return:
 *
 */
public class ControlParamDDC {

    private Integer sceneType;// 0 控制场景

    private Integer id;//场景ID

    private Integer active;//是否使能

    private String name;//场景名称

    private String alias; //场景别名

    private ControlModeDDC controlMode;

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

    public ControlModeDDC getControlMode() {
        return controlMode;
    }

    public void setControlMode(ControlModeDDC controlMode) {
        this.controlMode = controlMode;
    }
}
