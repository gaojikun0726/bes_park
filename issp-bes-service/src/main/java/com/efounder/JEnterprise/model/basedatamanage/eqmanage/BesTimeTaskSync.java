package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;


/**
 * 定时同步任务
 */
public class BesTimeTaskSync implements BaseEntity<String> {

    //主键id
    private String f_id;

    //任务名称
    private String f_job_name;

    //设备名称
    private String f_equipment_name;

    //cron表达式
    private String f_cron;

    //状态(0未启用,1启用)
    private String f_status;

    //备注
    private String f_remark;

    //定时任务执行id
    private String f_job_id;

    public String getF_id() {
        return f_id;
    }

    public void setF_id(String f_id) {
        this.f_id = f_id;
    }

    public String getF_job_name() {
        return f_job_name;
    }

    public void setF_job_name(String f_job_name) {
        this.f_job_name = f_job_name;
    }

    public String getF_equipment_name() {
        return f_equipment_name;
    }

    public void setF_equipment_name(String f_equipment_name) {
        this.f_equipment_name = f_equipment_name;
    }

    public String getF_cron() {
        return f_cron;
    }

    public void setF_cron(String f_cron) {
        this.f_cron = f_cron;
    }

    public String getF_status() {
        return f_status;
    }

    public void setF_status(String f_status) {
        this.f_status = f_status;
    }

    public String getF_remark() {
        return f_remark;
    }

    public void setF_remark(String f_remark) {
        this.f_remark = f_remark;
    }

    public String getF_job_id() {
        return f_job_id;
    }

    public void setF_job_id(String f_job_id) {
        this.f_job_id = f_job_id;
    }
}
