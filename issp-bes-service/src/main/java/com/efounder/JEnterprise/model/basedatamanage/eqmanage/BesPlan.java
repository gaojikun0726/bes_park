package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;

/**
 * @author sunzhiyuan
 * @Data 2020/10/19 16:25
 */
public class BesPlan implements BaseEntity<String> {

    //计划名称1
    private String f_id;

    //计划别名2
    private String f_sysname;

    //计划别名3
    private String f_nickname;

    //计划开始日期4
    private String f_startday;

    //计划开始时间5
    private String f_startime;

    //计划开始日期6
    private String f_enday;

    //计划开始时间7
    private String f_endtime;

    //是否替代日(1是 0 否)8
    private String f_planType;

    //周掩码9
    private String f_weekmask;

    //场景信息Id10
    private String f_sceneInfoId;

    //场景类型11
    private String f_scenetype;

    //模式信息ID12
    private String f_modeInfoId;

    //是否启用(1使能 0不使能)13
    private String f_active;

    //执行方式(0按天执行 1持续执行)14
    private String f_execut;

    //计划Id15
    private String f_planId;

    //是否同步(1是 0否)
    private String f_synchro;

    //是否执行(1执行 0停止)
    private String f_invoke;

    public String getF_id() {
        return f_id;
    }

    public void setF_id(String f_id) {
        this.f_id = f_id;
    }

    public String getF_sysname() {
        return f_sysname;
    }

    public void setF_sysname(String f_sysname) {
        this.f_sysname = f_sysname;
    }

    public String getF_nickname() {
        return f_nickname;
    }

    public void setF_nickname(String f_nickname) {
        this.f_nickname = f_nickname;
    }

    public String getF_startday() {
        return f_startday;
    }

    public void setF_startday(String f_startday) {
        this.f_startday = f_startday;
    }

    public String getF_startime() {
        return f_startime;
    }

    public void setF_startime(String f_startime) {
        this.f_startime = f_startime;
    }

    public String getF_enday() {
        return f_enday;
    }

    public void setF_enday(String f_enday) {
        this.f_enday = f_enday;
    }

    public String getF_endtime() {
        return f_endtime;
    }

    public void setF_endtime(String f_endtime) {
        this.f_endtime = f_endtime;
    }

    public String getF_planType() {
        return f_planType;
    }

    public void setF_planType(String f_planType) {
        this.f_planType = f_planType;
    }

    public String getF_weekmask() {
        return f_weekmask;
    }

    public void setF_weekmask(String f_weekmask) {
        this.f_weekmask = f_weekmask;
    }

    public String getF_sceneInfoId() {
        return f_sceneInfoId;
    }

    public void setF_sceneInfoId(String f_sceneInfoId) {
        this.f_sceneInfoId = f_sceneInfoId;
    }

    public String getF_scenetype() {
        return f_scenetype;
    }

    public void setF_scenetype(String f_scenetype) {
        this.f_scenetype = f_scenetype;
    }

    public String getF_modeInfoId() {
        return f_modeInfoId;
    }

    public void setF_modeInfoId(String f_modeInfoId) {
        this.f_modeInfoId = f_modeInfoId;
    }

    public String getF_active() {
        return f_active;
    }

    public void setF_active(String f_active) {
        this.f_active = f_active;
    }

    public String getF_execut() {
        return f_execut;
    }

    public void setF_execut(String f_execut) {
        this.f_execut = f_execut;
    }

    public String getF_planId() {
        return f_planId;
    }

    public void setF_planId(String f_planId) {
        this.f_planId = f_planId;
    }

    public String getF_invoke() { return f_invoke; }

    public void setF_invoke(String f_invoke) { this.f_invoke = f_invoke; }

    public String getF_synchro() {
        return f_synchro;
    }

    public void setF_synchro(String f_synchro) {
        this.f_synchro = f_synchro;
    }
}
