package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;

/**
 * @author sunzhiyuan
 * @Data 2020/9/15 9:13
 */
//值/单位表
public class BeSceneValue implements BaseEntity<String> {

    private Integer f_id;

    private String f_value; //值

    private String f_unit; //单位

    private String f_pointId; //点位Id

    private String f_modeId; //模式Id

    private String f_pointname; //模式名称

    private Integer f_scenepointId; //场景点位Id

    private String f_selectype; //采集方式

    public Integer getF_id() {
        return f_id;
    }

    public void setF_id(Integer f_id) {
        this.f_id = f_id;
    }

    public Integer getF_scenepointId() {
        return f_scenepointId;
    }

    public void setF_scenepointId(Integer f_scenepointId) {
        this.f_scenepointId = f_scenepointId;
    }

    public String getF_pointname() {
        return f_pointname;
    }

    public void setF_pointname(String f_pointname) {
        this.f_pointname = f_pointname;
    }

    public String getF_value() {
        return f_value;
    }

    public void setF_value(String f_value) {
        this.f_value = f_value;
    }

    public String getF_unit() {
        return f_unit;
    }

    public void setF_unit(String f_unit) {
        this.f_unit = f_unit;
    }

    public String getF_pointId() {
        return f_pointId;
    }

    public void setF_pointId(String f_pointId) {
        this.f_pointId = f_pointId;
    }

    public String getF_modeId() {
        return f_modeId;
    }

    public void setF_modeId(String f_modeId) {
        this.f_modeId = f_modeId;
    }

    public String getF_selectype() {
        return f_selectype;
    }

    public void setF_selectype(String f_selectype) {
        this.f_selectype = f_selectype;
    }
}
