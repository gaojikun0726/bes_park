package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;

/**
 * 虚点基础信息
 * @author
 *
 */
public class BesVirtualPoint implements BaseEntity<String>{

	private static final long serialVersionUID = -5439179631623866737L;

    private String f_guid; // 主键
    private String f_sbid; // 设备树id
    private String f_sys_name_old; // 实际下发下位机的名称
    private String f_sys_name; // 系统名称
    private String f_nick_name; // 别名
    private String f_node_type; // 虚点类型
    private String f_point_type; // 虚点类型（暂时弃用）
    private String f_enabled; // 是否可用
    private String f_init_val; // 初始值
    private String f_accuracy; // 精度
    private String f_engineer_unit; // 工程单位
    private String f_alarm_enable; // 报警使能
    private String f_alarm_type; // 报警类型
    private String f_high_limit; // 高限报警
    private String f_low_limit; // 低限报警
    private String f_close_state; // 闭合状态 闭合正常，0不闭合，1闭合
    private String f_alarm_priority; // 报警优先级 报警优先级，只有对于采用标准报警的点才有此配置项。危及人身安全0;严重 1;危机安全 2
    private String f_description; // 描述
    private String f_yqbh; // 园区编号
    private String f_energystatics; // 能耗统计（0：是；1：否）
    private String f_staticstime; // 统计周期（0:15分钟；1:1小时；2:1天）
    private String f_crdate; // 创建日期
    private String f_chdate; // 修改日期
    private String f_energy_type; // 能源类型
    private String f_sync_state; // 同步状态(0:未同步,1:已同步)
    private String f_fault_state; // 故障状态（0：否；1：是）
    private Integer recordUploadPeriod; // 能耗上传周期记录

    public String getF_guid() {
        return f_guid;
    }

    public void setF_guid(String f_guid) {
        this.f_guid = f_guid;
    }

    public String getF_sbid() {
        return f_sbid;
    }

    public void setF_sbid(String f_sbid) {
        this.f_sbid = f_sbid;
    }

    public String getF_sys_name_old() {
        return f_sys_name_old;
    }

    public void setF_sys_name_old(String f_sys_name_old) {
        this.f_sys_name_old = f_sys_name_old;
    }

    public String getF_sys_name() {
        return f_sys_name;
    }

    public void setF_sys_name(String f_sys_name) {
        this.f_sys_name = f_sys_name;
    }

    public String getF_nick_name() {
        return f_nick_name;
    }

    public void setF_nick_name(String f_nick_name) {
        this.f_nick_name = f_nick_name;
    }

    public String getF_node_type() {
        return f_node_type;
    }

    public void setF_node_type(String f_node_type) {
        this.f_node_type = f_node_type;
    }

    public String getF_point_type() {
        return f_point_type;
    }

    public void setF_point_type(String f_point_type) {
        this.f_point_type = f_point_type;
    }

    public String getF_enabled() {
        return f_enabled;
    }

    public void setF_enabled(String f_enabled) {
        this.f_enabled = f_enabled;
    }

    public String getF_init_val() {
        return f_init_val;
    }

    public void setF_init_val(String f_init_val) {
        this.f_init_val = f_init_val;
    }

    public String getF_accuracy() {
        return f_accuracy;
    }

    public void setF_accuracy(String f_accuracy) {
        this.f_accuracy = f_accuracy;
    }

    public String getF_engineer_unit() {
        return f_engineer_unit;
    }

    public void setF_engineer_unit(String f_engineer_unit) {
        this.f_engineer_unit = f_engineer_unit;
    }

    public String getF_alarm_enable() {
        return f_alarm_enable;
    }

    public void setF_alarm_enable(String f_alarm_enable) {
        this.f_alarm_enable = f_alarm_enable;
    }

    public String getF_alarm_type() {
        return f_alarm_type;
    }

    public void setF_alarm_type(String f_alarm_type) {
        this.f_alarm_type = f_alarm_type;
    }

    public String getF_high_limit() {
        return f_high_limit;
    }

    public void setF_high_limit(String f_high_limit) {
        this.f_high_limit = f_high_limit;
    }

    public String getF_low_limit() {
        return f_low_limit;
    }

    public void setF_low_limit(String f_low_limit) {
        this.f_low_limit = f_low_limit;
    }

    public String getF_close_state() {
        return f_close_state;
    }

    public void setF_close_state(String f_close_state) {
        this.f_close_state = f_close_state;
    }

    public String getF_alarm_priority() {
        return f_alarm_priority;
    }

    public void setF_alarm_priority(String f_alarm_priority) {
        this.f_alarm_priority = f_alarm_priority;
    }

    public String getF_description() {
        return f_description;
    }

    public void setF_description(String f_description) {
        this.f_description = f_description;
    }

    public String getF_yqbh() {
        return f_yqbh;
    }

    public void setF_yqbh(String f_yqbh) {
        this.f_yqbh = f_yqbh;
    }

    public String getF_energystatics() {
        return f_energystatics;
    }

    public void setF_energystatics(String f_energystatics) {
        this.f_energystatics = f_energystatics;
    }

    public String getF_staticstime() {
        return f_staticstime;
    }

    public void setF_staticstime(String f_staticstime) {
        this.f_staticstime = f_staticstime;
    }

    public String getF_crdate() {
        return f_crdate;
    }

    public void setF_crdate(String f_crdate) {
        this.f_crdate = f_crdate;
    }

    public String getF_chdate() {
        return f_chdate;
    }

    public void setF_chdate(String f_chdate) {
        this.f_chdate = f_chdate;
    }

    public String getF_energy_type() {
        return f_energy_type;
    }

    public void setF_energy_type(String f_energy_type) {
        this.f_energy_type = f_energy_type;
    }

    public String getF_sync_state() {
        return f_sync_state;
    }

    public void setF_sync_state(String f_sync_state) {
        this.f_sync_state = f_sync_state;
    }

    public String getF_fault_state() {
        return f_fault_state;
    }

    public void setF_fault_state(String f_fault_state) {
        this.f_fault_state = f_fault_state;
    }

    public Integer getRecordUploadPeriod() {
        return recordUploadPeriod;
    }

    public void setRecordUploadPeriod(Integer recordUploadPeriod) {
        this.recordUploadPeriod = recordUploadPeriod;
    }
}