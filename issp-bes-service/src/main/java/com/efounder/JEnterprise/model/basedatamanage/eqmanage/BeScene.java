package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;

/**
 * @author sunzhiyuan
 * @Data 2020/9/15 9:13
 */
//场景表
public class BeScene implements BaseEntity<String> {

    private String f_id;

    private String f_name;

    private String f_nickname;

    private String f_type;

    private String f_discription;

    private String f_sceneInfoId;

    private String f_scenemodeId;

    private String f_active;

    public String getF_id() {
        return f_id;
    }

    public void setF_id(String f_id) {
        this.f_id = f_id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getF_nickname() {
        return f_nickname;
    }

    public void setF_nickname(String f_nickname) {
        this.f_nickname = f_nickname;
    }

    public String getF_type() {
        return f_type;
    }

    public void setF_type(String f_type) {
        this.f_type = f_type;
    }

    public String getF_discription() {
        return f_discription;
    }

    public void setF_discription(String f_discription) {
        this.f_discription = f_discription;
    }

    public String getF_sceneInfoId() {
        return f_sceneInfoId;
    }

    public void setF_sceneInfoId(String f_sceneInfoId) {
        this.f_sceneInfoId = f_sceneInfoId;
    }

    public String getF_scenemodeId() {
        return f_scenemodeId;
    }

    public void setF_scenemodeId(String f_scenemodeId) {
        this.f_scenemodeId = f_scenemodeId;
    }

    public String getF_active() {
        return f_active;
    }

    public void setF_active(String f_active) {
        this.f_active = f_active;
    }
}
