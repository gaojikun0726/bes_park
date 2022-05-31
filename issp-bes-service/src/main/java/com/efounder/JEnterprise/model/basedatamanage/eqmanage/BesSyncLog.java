package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;

/**
 * @author liuwg
 * @Data 2022/05/30 10:32
 * 定时同步设备树执行日志表
 */
public class BesSyncLog implements BaseEntity<String> {

    private String f_id; //id

    private String f_sync_name; //定时任务名称

    private String f_point_name; //点位名称

    private String f_point_ip; //点位ip

    private String f_sync_status; //下发状态

    private String f_sync_time; //下发时间

    private String f_callback_status; //回调状态

    private String f_callback_time; //回调状态

    public String getF_id() {
        return f_id;
    }

    public void setF_id(String f_id) {
        this.f_id = f_id;
    }

    public String getF_sync_name() {
        return f_sync_name;
    }

    public void setF_sync_name(String f_sync_name) {
        this.f_sync_name = f_sync_name;
    }

    public String getF_point_name() {
        return f_point_name;
    }

    public void setF_point_name(String f_point_name) {
        this.f_point_name = f_point_name;
    }

    public String getF_point_ip() {
        return f_point_ip;
    }

    public void setF_point_ip(String f_point_ip) {
        this.f_point_ip = f_point_ip;
    }

    public String getF_sync_status() {
        return f_sync_status;
    }

    public void setF_sync_status(String f_sync_status) {
        this.f_sync_status = f_sync_status;
    }

    public String getF_callback_status() {
        return f_callback_status;
    }

    public void setF_callback_status(String f_callback_status) {
        this.f_callback_status = f_callback_status;
    }

    public String getF_sync_time() {
        return f_sync_time;
    }

    public void setF_sync_time(String f_sync_time) {
        this.f_sync_time = f_sync_time;
    }

    public String getF_callback_time() {
        return f_callback_time;
    }

    public void setF_callback_time(String f_callback_time) {
        this.f_callback_time = f_callback_time;
    }
}
