package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;

/**
 * @author sunzhiyuan
 * @Data 2020/9/9 18:00
 */
//场景模式表
public class BeSceneMode implements BaseEntity<String> {

    private String f_id;//模式ID

    private String f_modename; //模式名称

    private String f_sceneInfoId; //场景信息Id

    private String f_modetype; //场景模式类型

    private String f_enable; //是否使能

    private String f_synchro; //是否同步

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

    public String getF_sceneInfoId() {
        return f_sceneInfoId;
    }

    public void setF_sceneInfoId(String f_sceneInfoId) {
        this.f_sceneInfoId = f_sceneInfoId;
    }

    public String getF_modetype() {
        return f_modetype;
    }

    public void setF_modetype(String f_modetype) {
        this.f_modetype = f_modetype;
    }

    public String getF_enable() {
        return f_enable;
    }

    public void setF_enable(String f_enable) {
        this.f_enable = f_enable;
    }

    public String getF_synchro() {
        return f_synchro;
    }

    public void setF_synchro(String f_synchro) {
        this.f_synchro = f_synchro;
    }
}
