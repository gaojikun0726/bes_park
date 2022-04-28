package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;

/**
 * @author sunzhiyuan
 * @Data 2020/9/15 9:18
 */
//其他模式表
public class BesModeOther implements BaseEntity<String> {

    private String f_id;

    private String f_modename;//模式名称

    private String f_modeunit;//模式单位

    private String f_scenemodeId;//场景模式Id

    private String f_modetype;//场景类型(0 控制私有 1控制公有 2采集私有 3采集公有)

    private String f_sceneInfoId;//场景Id

    public String getF_id() {
        return f_id;
    }

    public void setF_id(String f_id) {
        this.f_id = f_id;
    }

    public String getF_modename() {
        return f_modename;
    }

    public void setF_modename(String f_modename) {
        this.f_modename = f_modename;
    }

    public String getF_modeunit() {
        return f_modeunit;
    }

    public void setF_modeunit(String f_modeunit) {
        this.f_modeunit = f_modeunit;
    }

    public String getF_scenemodeId() {
        return f_scenemodeId;
    }

    public void setF_scenemodeId(String f_scenemodeId) {
        this.f_scenemodeId = f_scenemodeId;
    }

    public String getF_modetype() {
        return f_modetype;
    }

    public void setF_modetype(String f_modetype) {
        this.f_modetype = f_modetype;
    }

    public String getF_sceneInfoId() {
        return f_sceneInfoId;
    }

    public void setF_sceneInfoId(String f_sceneInfoId) {
        this.f_sceneInfoId = f_sceneInfoId;
    }
}
