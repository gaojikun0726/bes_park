package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;


/**
 * 定时同步设备
 */
public class BesTimeTaskSyncSb implements BaseEntity<String> {

    //主键id
    private String f_id;

    //任务id
    private String f_sync_id;

    //设备id
    private String f_point_id;

    //设备名称
    private String f_point_name;

    //设备类型
    private String f_point_type;

    //全名
    private String f_point_all_name;

    //父级名称
    private String f_point_psys_name;

    public String getF_id() {
        return f_id;
    }

    public void setF_id(String f_id) {
        this.f_id = f_id;
    }

    public String getF_sync_id() {
        return f_sync_id;
    }

    public void setF_sync_id(String f_sync_id) {
        this.f_sync_id = f_sync_id;
    }

    public String getF_point_id() {
        return f_point_id;
    }

    public void setF_point_id(String f_point_id) {
        this.f_point_id = f_point_id;
    }

    public String getF_point_name() {
        return f_point_name;
    }

    public void setF_point_name(String f_point_name) {
        this.f_point_name = f_point_name;
    }

    public String getF_point_type() {
        return f_point_type;
    }

    public void setF_point_type(String f_point_type) {
        this.f_point_type = f_point_type;
    }

    public String getF_point_all_name() {
        return f_point_all_name;
    }

    public void setF_point_all_name(String f_point_all_name) {
        this.f_point_all_name = f_point_all_name;
    }

    public String getF_point_psys_name() {
        return f_point_psys_name;
    }

    public void setF_point_psys_name(String f_point_psys_name) {
        this.f_point_psys_name = f_point_psys_name;
    }
}
