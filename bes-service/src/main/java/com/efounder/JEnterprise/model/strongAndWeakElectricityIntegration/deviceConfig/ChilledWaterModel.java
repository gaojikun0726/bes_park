package com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.deviceConfig;

import java.io.Serializable;

public class ChilledWaterModel implements Serializable {


    private static final long serialVersionUID = 9002719394291931646L;
    private Integer    f_ID;               // 主键
    private String     f_TYPE_ID;          // 所属机组的id
    private String     f_NAME;             // 冷却水泵名称
    private String     f_NHLJ;             // 能耗累计（设备树标识）
    private String     f_NHLJ_alias;       // 能耗累计（显示名称）
    private String     f_SSGL;             // 瞬时功率（设备树标识）
    private String     f_SSGL_alias ;      // 瞬时功率（显示名称）
    private String     f_AXDL;             // A相电流（设备树标识）
    private String     f_AXDL_alias   ;    // A相电流（显示名称）
    private String     f_BXDL         ;    // B相电流（设备树标识）
    private String     f_BXDL_alias   ;    // B相电流（显示名称）
    private String     f_CXDL         ;    // C相电流（设备树标识）
    private String     f_CXDL_alias   ;    // C相电流（显示名称）
    private String     f_SBGZMS       ;    // 设备工作模式（设备树标识）
    private String     f_SBGZMS_alias ;    // 设备工作模式（显示名称）
    private String     f_SBGZZT       ;    // 设备故障状态（设备树标识）
    private String     f_SBGZZT_alias ;    // 设备故障状态（显示名称）
    private String     f_SBQQXH       ;    // 设备请求信号（设备树标识）
    private String     f_SBQQXH_alias ;    // 设备请求信号（显示名称）
    private String     f_SBYXSJ       ;    // 设备运行时间（设备树标识）
    private String     f_SBYXSJ_alias ;    // 设备运行时间（显示名称）

    private String     f_SFKDW;            // 水阀开到位（设备树标识）
    private String     f_SFKDW_alias;      // 水阀开到位（显示名称）
    private String     f_SFGDW;            // 水阀关到位（设备树标识）
    private String     f_SFGDW_alias;      // 水阀关到位（显示名称）
    private String     f_BPYXZT;           // 变频器运行状态（设备树标识）
    private String     f_BPYXZT_alias;     // 变频器运行状态（显示名称）
    private String     f_BPGZZT;           //变频器故障状态（设备树标识）
    private String     f_BPGZZT_alias;     //变频器故障状态（显示名称）
    private String     f_BPQPLFK;          //变频器频率反馈（设备树标识）
    private String     f_BPQPLFK_alias;    //变频器频率反馈（显示名称）
    private String     f_SLZT;             //水流状态（设备树标识）
    private String     f_SLZT_alias;       //水流状态（显示名称）
    private String     f_SFGZZT;           //水阀故障状态（设备树标识）
    private String     f_SFGZZT_alias;     //水阀故障状态（显示名称）
    private String     f_SBYXZT;           //水泵运行状态（设备树标识）
    private String     f_SBYXZT_alias;     //水泵运行状态（显示名称）
    private String     f_YXZSD;            //运行指示灯（设备树标识）
    private String     f_YXZSD_alias;      //运行指示灯（显示名称）
    private String     f_GZZSD;            //故障指示灯（设备树标识）
    private String     f_GZZSD_alias;      //故障指示灯（显示名称）
    private String     f_Electric_meter_number;       //
    private Integer    id;                   // 主键
    private String     name;                 // 电表名
    private String     cabinet_name;          // 机柜名称
    private String     instant_energy;        // 瞬时能耗
    private String     total_energy;          // 累计能耗
    private String     a_phase_voltage;        // A相电流
    private String     b_phase_voltage;        // B相电流
    private String     c_phase_voltage;        // C相电流
    private String     a_phase_current;        // A相电压
    private String     b_phase_current;        // B相电压
    private String     c_phase_current;        // C相电压
    private String     instant_energy_display; // 瞬时能耗显示名称
    private String     total_energy_display;   // 累计能耗显示名称
    private String     a_phase_voltage_display; // A相电流显示名称
    private String     b_phase_voltage_display; // B相电流显示名称
    private String     c_phase_voltage_display; // C相电流显示名称
    private String     a_phase_current_display; // A相电压显示名称
    private String     b_phase_current_display; // B相电压显示名称
    private String     c_phase_current_display; // C相电压显示名称

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCabinet_name() {
        return cabinet_name;
    }

    public void setCabinet_name(String cabinet_name) {
        this.cabinet_name = cabinet_name;
    }

    public String getInstant_energy() {
        return instant_energy;
    }

    public void setInstant_energy(String instant_energy) {
        this.instant_energy = instant_energy;
    }

    public String getTotal_energy() {
        return total_energy;
    }

    public void setTotal_energy(String total_energy) {
        this.total_energy = total_energy;
    }

    public String getA_phase_voltage() {
        return a_phase_voltage;
    }

    public void setA_phase_voltage(String a_phase_voltage) {
        this.a_phase_voltage = a_phase_voltage;
    }

    public String getB_phase_voltage() {
        return b_phase_voltage;
    }

    public void setB_phase_voltage(String b_phase_voltage) {
        this.b_phase_voltage = b_phase_voltage;
    }

    public String getC_phase_voltage() {
        return c_phase_voltage;
    }

    public void setC_phase_voltage(String c_phase_voltage) {
        this.c_phase_voltage = c_phase_voltage;
    }

    public String getA_phase_current() {
        return a_phase_current;
    }

    public void setA_phase_current(String a_phase_current) {
        this.a_phase_current = a_phase_current;
    }

    public String getB_phase_current() {
        return b_phase_current;
    }

    public void setB_phase_current(String b_phase_current) {
        this.b_phase_current = b_phase_current;
    }

    public String getC_phase_current() {
        return c_phase_current;
    }

    public void setC_phase_current(String c_phase_current) {
        this.c_phase_current = c_phase_current;
    }

    public String getInstant_energy_display() {
        return instant_energy_display;
    }

    public void setInstant_energy_display(String instant_energy_display) {
        this.instant_energy_display = instant_energy_display;
    }

    public String getTotal_energy_display() {
        return total_energy_display;
    }

    public void setTotal_energy_display(String total_energy_display) {
        this.total_energy_display = total_energy_display;
    }

    public String getA_phase_voltage_display() {
        return a_phase_voltage_display;
    }

    public void setA_phase_voltage_display(String a_phase_voltage_display) {
        this.a_phase_voltage_display = a_phase_voltage_display;
    }

    public String getB_phase_voltage_display() {
        return b_phase_voltage_display;
    }

    public void setB_phase_voltage_display(String b_phase_voltage_display) {
        this.b_phase_voltage_display = b_phase_voltage_display;
    }

    public String getC_phase_voltage_display() {
        return c_phase_voltage_display;
    }

    public void setC_phase_voltage_display(String c_phase_voltage_display) {
        this.c_phase_voltage_display = c_phase_voltage_display;
    }

    public String getA_phase_current_display() {
        return a_phase_current_display;
    }

    public void setA_phase_current_display(String a_phase_current_display) {
        this.a_phase_current_display = a_phase_current_display;
    }

    public String getB_phase_current_display() {
        return b_phase_current_display;
    }

    public void setB_phase_current_display(String b_phase_current_display) {
        this.b_phase_current_display = b_phase_current_display;
    }

    public String getC_phase_current_display() {
        return c_phase_current_display;
    }

    public void setC_phase_current_display(String c_phase_current_display) {
        this.c_phase_current_display = c_phase_current_display;
    }

    public Integer getF_ID() {
        return f_ID;
    }

    public void setF_ID(Integer f_ID) {
        this.f_ID = f_ID;
    }

    public String getF_TYPE_ID() {
        return f_TYPE_ID;
    }

    public void setF_TYPE_ID(String f_TYPE_ID) {
        this.f_TYPE_ID = f_TYPE_ID;
    }

    public String getF_NAME() {
        return f_NAME;
    }

    public void setF_NAME(String f_NAME) {
        this.f_NAME = f_NAME;
    }


    public String getF_SFKDW() {
        return f_SFKDW;
    }

    public void setF_SFKDW(String f_SFKDW) {
        this.f_SFKDW = f_SFKDW;
    }

    public String getF_SFKDW_alias() {
        return f_SFKDW_alias;
    }

    public void setF_SFKDW_alias(String f_SFKDW_alias) {
        this.f_SFKDW_alias = f_SFKDW_alias;
    }

    public String getF_SFGDW() {
        return f_SFGDW;
    }

    public void setF_SFGDW(String f_SFGDW) {
        this.f_SFGDW = f_SFGDW;
    }

    public String getF_SFGDW_alias() {
        return f_SFGDW_alias;
    }

    public void setF_SFGDW_alias(String f_SFGDW_alias) {
        this.f_SFGDW_alias = f_SFGDW_alias;
    }

    public String getF_BPYXZT() {
        return f_BPYXZT;
    }

    public void setF_BPYXZT(String f_BPYXZT) {
        this.f_BPYXZT = f_BPYXZT;
    }

    public String getF_BPYXZT_alias() {
        return f_BPYXZT_alias;
    }

    public void setF_BPYXZT_alias(String f_BPYXZT_alias) {
        this.f_BPYXZT_alias = f_BPYXZT_alias;
    }

    public String getF_BPGZZT() {
        return f_BPGZZT;
    }

    public void setF_BPGZZT(String f_BPGZZT) {
        this.f_BPGZZT = f_BPGZZT;
    }

    public String getF_BPGZZT_alias() {
        return f_BPGZZT_alias;
    }

    public void setF_BPGZZT_alias(String f_BPGZZT_alias) {
        this.f_BPGZZT_alias = f_BPGZZT_alias;
    }

    public String getF_BPQPLFK() {
        return f_BPQPLFK;
    }

    public void setF_BPQPLFK(String f_BPQPLFK) {
        this.f_BPQPLFK = f_BPQPLFK;
    }

    public String getF_BPQPLFK_alias() {
        return f_BPQPLFK_alias;
    }

    public void setF_BPQPLFK_alias(String f_BPQPLFK_alias) {
        this.f_BPQPLFK_alias = f_BPQPLFK_alias;
    }

    public String getF_SLZT() {
        return f_SLZT;
    }

    public void setF_SLZT(String f_SLZT) {
        this.f_SLZT = f_SLZT;
    }

    public String getF_SLZT_alias() {
        return f_SLZT_alias;
    }

    public void setF_SLZT_alias(String f_SLZT_alias) {
        this.f_SLZT_alias = f_SLZT_alias;
    }

    public String getF_SFGZZT() {
        return f_SFGZZT;
    }

    public void setF_SFGZZT(String f_SFGZZT) {
        this.f_SFGZZT = f_SFGZZT;
    }

    public String getF_SFGZZT_alias() {
        return f_SFGZZT_alias;
    }

    public void setF_SFGZZT_alias(String f_SFGZZT_alias) {
        this.f_SFGZZT_alias = f_SFGZZT_alias;
    }

    public String getF_SBYXZT() {
        return f_SBYXZT;
    }

    public void setF_SBYXZT(String f_SBYXZT) {
        this.f_SBYXZT = f_SBYXZT;
    }

    public String getF_SBYXZT_alias() {
        return f_SBYXZT_alias;
    }

    public void setF_SBYXZT_alias(String f_SBYXZT_alias) {
        this.f_SBYXZT_alias = f_SBYXZT_alias;
    }

    public String getF_YXZSD() {
        return f_YXZSD;
    }

    public void setF_YXZSD(String f_YXZSD) {
        this.f_YXZSD = f_YXZSD;
    }

    public String getF_YXZSD_alias() {
        return f_YXZSD_alias;
    }

    public void setF_YXZSD_alias(String f_YXZSD_alias) {
        this.f_YXZSD_alias = f_YXZSD_alias;
    }

    public String getF_GZZSD() {
        return f_GZZSD;
    }

    public void setF_GZZSD(String f_GZZSD) {
        this.f_GZZSD = f_GZZSD;
    }

    public String getF_GZZSD_alias() {
        return f_GZZSD_alias;
    }

    public void setF_GZZSD_alias(String f_GZZSD_alias) {
        this.f_GZZSD_alias = f_GZZSD_alias;
    }

    public String getF_Electric_meter_number() {
        return f_Electric_meter_number;
    }

    public void setF_Electric_meter_number(String f_Electric_meter_number) {
        this.f_Electric_meter_number = f_Electric_meter_number;
    }

    public String getF_NHLJ() {
        return f_NHLJ;
    }

    public void setF_NHLJ(String f_NHLJ) {
        this.f_NHLJ = f_NHLJ;
    }

    public String getF_NHLJ_alias() {
        return f_NHLJ_alias;
    }

    public void setF_NHLJ_alias(String f_NHLJ_alias) {
        this.f_NHLJ_alias = f_NHLJ_alias;
    }

    public String getF_SSGL() {
        return f_SSGL;
    }

    public void setF_SSGL(String f_SSGL) {
        this.f_SSGL = f_SSGL;
    }

    public String getF_SSGL_alias() {
        return f_SSGL_alias;
    }

    public void setF_SSGL_alias(String f_SSGL_alias) {
        this.f_SSGL_alias = f_SSGL_alias;
    }

    public String getF_AXDL() {
        return f_AXDL;
    }

    public void setF_AXDL(String f_AXDL) {
        this.f_AXDL = f_AXDL;
    }

    public String getF_AXDL_alias() {
        return f_AXDL_alias;
    }

    public void setF_AXDL_alias(String f_AXDL_alias) {
        this.f_AXDL_alias = f_AXDL_alias;
    }

    public String getF_BXDL() {
        return f_BXDL;
    }

    public void setF_BXDL(String f_BXDL) {
        this.f_BXDL = f_BXDL;
    }

    public String getF_BXDL_alias() {
        return f_BXDL_alias;
    }

    public void setF_BXDL_alias(String f_BXDL_alias) {
        this.f_BXDL_alias = f_BXDL_alias;
    }

    public String getF_CXDL() {
        return f_CXDL;
    }

    public void setF_CXDL(String f_CXDL) {
        this.f_CXDL = f_CXDL;
    }

    public String getF_CXDL_alias() {
        return f_CXDL_alias;
    }

    public void setF_CXDL_alias(String f_CXDL_alias) {
        this.f_CXDL_alias = f_CXDL_alias;
    }

    public String getF_SBGZMS() {
        return f_SBGZMS;
    }

    public void setF_SBGZMS(String f_SBGZMS) {
        this.f_SBGZMS = f_SBGZMS;
    }

    public String getF_SBGZMS_alias() {
        return f_SBGZMS_alias;
    }

    public void setF_SBGZMS_alias(String f_SBGZMS_alias) {
        this.f_SBGZMS_alias = f_SBGZMS_alias;
    }

    public String getF_SBGZZT() {
        return f_SBGZZT;
    }

    public void setF_SBGZZT(String f_SBGZZT) {
        this.f_SBGZZT = f_SBGZZT;
    }

    public String getF_SBGZZT_alias() {
        return f_SBGZZT_alias;
    }

    public void setF_SBGZZT_alias(String f_SBGZZT_alias) {
        this.f_SBGZZT_alias = f_SBGZZT_alias;
    }

    public String getF_SBQQXH() {
        return f_SBQQXH;
    }

    public void setF_SBQQXH(String f_SBQQXH) {
        this.f_SBQQXH = f_SBQQXH;
    }

    public String getF_SBQQXH_alias() {
        return f_SBQQXH_alias;
    }

    public void setF_SBQQXH_alias(String f_SBQQXH_alias) {
        this.f_SBQQXH_alias = f_SBQQXH_alias;
    }

    public String getF_SBYXSJ() {
        return f_SBYXSJ;
    }

    public void setF_SBYXSJ(String f_SBYXSJ) {
        this.f_SBYXSJ = f_SBYXSJ;
    }

    public String getF_SBYXSJ_alias() {
        return f_SBYXSJ_alias;
    }

    public void setF_SBYXSJ_alias(String f_SBYXSJ_alias) {
        this.f_SBYXSJ_alias = f_SBYXSJ_alias;
    }
}
