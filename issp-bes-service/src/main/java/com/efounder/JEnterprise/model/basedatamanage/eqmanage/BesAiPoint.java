package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;

/**
 * AI基础信息
 * @author
 *
 */
public class BesAiPoint implements BaseEntity<String>{

	private static final long serialVersionUID = -5439179631623866737L;

    private String f_guid; // 主键
    private String f_sbid; // 设备id，用于下位机通讯
    private String f_struct_id; // 设备树id
    private String f_sys_name_old; // 实际下发下位机的名称
    private String f_sys_name; // 系统名称
    private String f_nick_name; // 别名
    private String f_enabled; // 是否可用  不使用0，使用1，只有使用才与其通信
    private String f_channel_index; // 通道索引
    private String f_engineer_unit; // 工程单位
    private String f_node_type; // 点类型FK(BES_SBTREE_NODE. F_NODE_TYPE)
    private String f_sinnal_type; // 信号类型  0-10v 0  ;0-20mA 1;4-20mA 2
    private String f_min_val; // 最小值   传感器量程最小值
    private String f_max_val; // 最大值
    private String f_accuracy; // 精度
    private String f_reversed; // 是否反向   否0，是1
    private String f_work_mode; // 工作模式   自动0，手动1
    private String f_alarm_enable; // 报警使能   不使能0，使能1
    private String f_alarm_type; // 报警类型  不报警0，标准报警1，增强报警2
    private String f_high_limit; // 高限报警  对于标准报警点，该点数值所允许达到的最高限值
    private String f_low_limit; // 低限报警   对于标准报警点，该点数值所允许达到的最低限值
    private String f_alarm_priority; // 报警优先级  报警优先级，只有对于采用标准报警的点才有此配置项。危及人身安全0;严重 1;危机安全 2
    private String f_init_val; // 初始值
    private String f_description; // 描述
    private String f_yqbh; // 园区编号
    private String f_energystatics; // 能耗统计（0:是；1:否）
    private String f_staticstime; // 统计周期（0:15分钟；1:1小时；2:1天）
    private String f_crdate; // 创建日期
    private String f_chdate; // 修改日期
    private String f_ai_state; // 同步状态(0:未同步,1:已同步)
    private String f_energy_type; // 能源类型
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

    public String getF_struct_id() {
        return f_struct_id;
    }

    public void setF_struct_id(String f_struct_id) {
        this.f_struct_id = f_struct_id;
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

    public String getF_enabled() {
        return f_enabled;
    }

    public void setF_enabled(String f_enabled) {
        this.f_enabled = f_enabled;
    }

    public String getF_channel_index() {
        return f_channel_index;
    }

    public void setF_channel_index(String f_channel_index) {
        this.f_channel_index = f_channel_index;
    }

    public String getF_engineer_unit() {
        return f_engineer_unit;
    }

    public void setF_engineer_unit(String f_engineer_unit) {
        this.f_engineer_unit = f_engineer_unit;
    }

    public String getF_node_type() {
        return f_node_type;
    }

    public void setF_node_type(String f_node_type) {
        this.f_node_type = f_node_type;
    }

    public String getF_sinnal_type() {
        return f_sinnal_type;
    }

    public void setF_sinnal_type(String f_sinnal_type) {
        this.f_sinnal_type = f_sinnal_type;
    }

    public String getF_min_val() {
        return f_min_val;
    }

    public void setF_min_val(String f_min_val) {
        this.f_min_val = f_min_val;
    }

    public String getF_max_val() {
        return f_max_val;
    }

    public void setF_max_val(String f_max_val) {
        this.f_max_val = f_max_val;
    }

    public String getF_accuracy() {
        return f_accuracy;
    }

    public void setF_accuracy(String f_accuracy) {
        this.f_accuracy = f_accuracy;
    }

    public String getF_reversed() {
        return f_reversed;
    }

    public void setF_reversed(String f_reversed) {
        this.f_reversed = f_reversed;
    }

    public String getF_work_mode() {
        return f_work_mode;
    }

    public void setF_work_mode(String f_work_mode) {
        this.f_work_mode = f_work_mode;
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

    public String getF_alarm_priority() {
        return f_alarm_priority;
    }

    public void setF_alarm_priority(String f_alarm_priority) {
        this.f_alarm_priority = f_alarm_priority;
    }

    public String getF_init_val() {
        return f_init_val;
    }

    public void setF_init_val(String f_init_val) {
        this.f_init_val = f_init_val;
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

    public String getF_ai_state() {
        return f_ai_state;
    }

    public void setF_ai_state(String f_ai_state) {
        this.f_ai_state = f_ai_state;
    }

    public String getF_energy_type() {
        return f_energy_type;
    }

    public void setF_energy_type(String f_energy_type) {
        this.f_energy_type = f_energy_type;
    }

    public Integer getRecordUploadPeriod() {
        return recordUploadPeriod;
    }

    public void setRecordUploadPeriod(Integer recordUploadPeriod) {
        this.recordUploadPeriod = recordUploadPeriod;
    }
}