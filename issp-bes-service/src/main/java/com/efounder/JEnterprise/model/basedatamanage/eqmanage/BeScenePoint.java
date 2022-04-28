package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;

/**
 * @author sunzhiyuan
 * @Data 2020/9/15 9:18
 */
//模式值单位表
public class BeScenePoint implements BaseEntity<String> {

    //主键
    private int f_id;
    //点位名称
    private String f_pointname;

    //点位类型
    private String f_pointype;

    //点位Id
    private String f_pointId;

    //场景Id
    private String f_sceneInfoId;

    //模式Id
    private String f_scenemodeId;

    //点位值
    private String f_value;

    //点位的单位
    private String f_unit;

    //点位的详细信息
    private String f_pointnameDetailed;

    public int getF_id() {
        return f_id;
    }

    public void setF_id(int f_id) {
        this.f_id = f_id;
    }

    public String getF_pointId() {
        return f_pointId;
    }

    public void setF_pointId(String f_pointId) {
        this.f_pointId = f_pointId;
    }

    public String getF_pointname(String s) {
        return f_pointname;
    }

    public void setF_pointname(String f_pointname) {
        this.f_pointname = f_pointname;
    }

    public String getF_pointype() {
        return f_pointype;
    }

    public void setF_pointype(String f_pointype) {
        this.f_pointype = f_pointype;
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

    public String getF_pointname() {
        return f_pointname;
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

    public String getF_pointnameDetailed() {
        return f_pointnameDetailed;
    }

    public void setF_pointnameDetailed(String f_pointnameDetailed) {
        this.f_pointnameDetailed = f_pointnameDetailed;
    }
}
