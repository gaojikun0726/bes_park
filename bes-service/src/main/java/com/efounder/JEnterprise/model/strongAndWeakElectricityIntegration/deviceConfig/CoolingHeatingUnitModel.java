package com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.deviceConfig;

import java.io.Serializable;

/**
 *
 * 冷热机组配置
 *
 * @author xiepufeng
 *
 */
public class CoolingHeatingUnitModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer    id;                                 // 主键

    private String     name;                               // 冷热机主名称

    private String     currentState;                       // 冷热机主当前状态

    private String     freezeSupplyWaterTemperature;       // 冷冻水供水温度

    private String     freezeReturnWaterTemperature;       // 冷冻水回水温度

    private String     coolingSupplyWaterTemperature;      // 冷却水供水温度

    private String     coolingReturnWaterTemperature;      // 冷却水回水温度

    private String     currentFlow;                        // 当前流量

    private String     currentStateAlias;                  // 冷热机主当前状态（显示名称）

    private String     freezeSupplyWaterTemperatureAlias;  // 冷冻水供水温度（显示名称）

    private String     freezeReturnWaterTemperatureAlias;  // 冷冻水回水温度（显示名称）

    private String     coolingSupplyWaterTemperatureAlias; // 冷却水供水温度（显示名称）

    private String     coolingReturnWaterTemperatureAlias; // 冷却水回水温度（显示名称）

    private String     currentFlowAlias;                   // 当前流量（显示名称）

    private String     electric_meter_number;              //电表的id

    private Integer    ids;                    // 主键
    private String     names;                 // 电表名
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

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public String getFreezeSupplyWaterTemperature() {
        return freezeSupplyWaterTemperature;
    }

    public void setFreezeSupplyWaterTemperature(String freezeSupplyWaterTemperature) {
        this.freezeSupplyWaterTemperature = freezeSupplyWaterTemperature;
    }

    public String getFreezeReturnWaterTemperature() {
        return freezeReturnWaterTemperature;
    }

    public void setFreezeReturnWaterTemperature(String freezeReturnWaterTemperature) {
        this.freezeReturnWaterTemperature = freezeReturnWaterTemperature;
    }

    public String getCoolingReturnWaterTemperature() {
        return coolingReturnWaterTemperature;
    }

    public void setCoolingReturnWaterTemperature(String coolingReturnWaterTemperature) {
        this.coolingReturnWaterTemperature = coolingReturnWaterTemperature;
    }

    public String getCoolingSupplyWaterTemperature() {
        return coolingSupplyWaterTemperature;
    }

    public void setCoolingSupplyWaterTemperature(String coolingSupplyWaterTemperature) {
        this.coolingSupplyWaterTemperature = coolingSupplyWaterTemperature;
    }


    public String getCurrentFlow() {
        return currentFlow;
    }

    public void setCurrentFlow(String currentFlow) {
        this.currentFlow = currentFlow;
    }

    public String getCurrentStateAlias() {
        return currentStateAlias;
    }

    public void setCurrentStateAlias(String currentStateAlias) {
        this.currentStateAlias = currentStateAlias;
    }

    public String getFreezeSupplyWaterTemperatureAlias() {
        return freezeSupplyWaterTemperatureAlias;
    }

    public void setFreezeSupplyWaterTemperatureAlias(String freezeSupplyWaterTemperatureAlias) {
        this.freezeSupplyWaterTemperatureAlias = freezeSupplyWaterTemperatureAlias;
    }

    public String getFreezeReturnWaterTemperatureAlias() {
        return freezeReturnWaterTemperatureAlias;
    }

    public void setFreezeReturnWaterTemperatureAlias(String freezeReturnWaterTemperatureAlias) {
        this.freezeReturnWaterTemperatureAlias = freezeReturnWaterTemperatureAlias;
    }

    public String getCoolingSupplyWaterTemperatureAlias() {
        return coolingSupplyWaterTemperatureAlias;
    }

    public void setCoolingSupplyWaterTemperatureAlias(String coolingSupplyWaterTemperatureAlias) {
        this.coolingSupplyWaterTemperatureAlias = coolingSupplyWaterTemperatureAlias;
    }

    public String getCoolingReturnWaterTemperatureAlias() {
        return coolingReturnWaterTemperatureAlias;
    }

    public void setCoolingReturnWaterTemperatureAlias(String coolingReturnWaterTemperatureAlias) {
        this.coolingReturnWaterTemperatureAlias = coolingReturnWaterTemperatureAlias;
    }

    public String getCurrentFlowAlias() {
        return currentFlowAlias;
    }

    public void setCurrentFlowAlias(String currentFlowAlias) {
        this.currentFlowAlias = currentFlowAlias;
    }

    public String getElectric_meter_number() {
        return electric_meter_number;
    }

    public void setElectric_meter_number(String electric_meter_number) {
        this.electric_meter_number = electric_meter_number;
    }

    public Integer getIds() {
        return ids;
    }

    public void setIds(Integer ids) {
        this.ids = ids;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
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
}
