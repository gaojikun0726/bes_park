package com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.deviceConfig;

import java.io.Serializable;

/**
 *
 * 电表配置
 *
 * @author xiepufeng
 *
 */
public class AmmeterConfigModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer    id;                   // 主键
    private String     name;                 // 电表名
    private String     cabinetName;          // 机柜名称
    private String     instantEnergy;        // 瞬时能耗
    private String     totalEnergy;          // 累计能耗
    private String     aPhaseVoltage;        // A相电流
    private String     bPhaseVoltage;        // B相电流
    private String     cPhaseVoltage;        // C相电流
    private String     aPhaseCurrent;        // A相电压
    private String     bPhaseCurrent;        // B相电压
    private String     cPhaseCurrent;        // C相电压
    private String     instantEnergyDisplay; // 瞬时能耗显示名称
    private String     totalEnergyDisplay;   // 累计能耗显示名称
    private String     aPhaseVoltageDisplay; // A相电流显示名称
    private String     bPhaseVoltageDisplay; // B相电流显示名称
    private String     cPhaseVoltageDisplay; // C相电流显示名称
    private String     aPhaseCurrentDisplay; // A相电压显示名称
    private String     bPhaseCurrentDisplay; // B相电压显示名称
    private String     cPhaseCurrentDisplay; // C相电压显示名称


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

    public String getCabinetName() {
        return cabinetName;
    }

    public void setCabinetName(String cabinetName) {
        this.cabinetName = cabinetName;
    }

    public String getInstantEnergy() {
        return instantEnergy;
    }

    public void setInstantEnergy(String instantEnergy) {
        this.instantEnergy = instantEnergy;
    }

    public String getTotalEnergy() {
        return totalEnergy;
    }

    public void setTotalEnergy(String totalEnergy) {
        this.totalEnergy = totalEnergy;
    }

    public String getaPhaseVoltage() {
        return aPhaseVoltage;
    }

    public void setaPhaseVoltage(String aPhaseVoltage) {
        this.aPhaseVoltage = aPhaseVoltage;
    }

    public String getbPhaseVoltage() {
        return bPhaseVoltage;
    }

    public void setbPhaseVoltage(String bPhaseVoltage) {
        this.bPhaseVoltage = bPhaseVoltage;
    }

    public String getcPhaseVoltage() {
        return cPhaseVoltage;
    }

    public void setcPhaseVoltage(String cPhaseVoltage) {
        this.cPhaseVoltage = cPhaseVoltage;
    }

    public String getaPhaseCurrent() {
        return aPhaseCurrent;
    }

    public void setaPhaseCurrent(String aPhaseCurrent) {
        this.aPhaseCurrent = aPhaseCurrent;
    }

    public String getbPhaseCurrent() {
        return bPhaseCurrent;
    }

    public void setbPhaseCurrent(String bPhaseCurrent) {
        this.bPhaseCurrent = bPhaseCurrent;
    }

    public String getcPhaseCurrent() {
        return cPhaseCurrent;
    }

    public void setcPhaseCurrent(String cPhaseCurrent) {
        this.cPhaseCurrent = cPhaseCurrent;
    }

    public String getInstantEnergyDisplay() {
        return instantEnergyDisplay;
    }

    public void setInstantEnergyDisplay(String instantEnergyDisplay) {
        this.instantEnergyDisplay = instantEnergyDisplay;
    }

    public String getTotalEnergyDisplay() {
        return totalEnergyDisplay;
    }

    public void setTotalEnergyDisplay(String totalEnergyDisplay) {
        this.totalEnergyDisplay = totalEnergyDisplay;
    }

    public String getaPhaseVoltageDisplay() {
        return aPhaseVoltageDisplay;
    }

    public void setaPhaseVoltageDisplay(String aPhaseVoltageDisplay) {
        this.aPhaseVoltageDisplay = aPhaseVoltageDisplay;
    }

    public String getbPhaseVoltageDisplay() {
        return bPhaseVoltageDisplay;
    }

    public void setbPhaseVoltageDisplay(String bPhaseVoltageDisplay) {
        this.bPhaseVoltageDisplay = bPhaseVoltageDisplay;
    }

    public String getcPhaseVoltageDisplay() {
        return cPhaseVoltageDisplay;
    }

    public void setcPhaseVoltageDisplay(String cPhaseVoltageDisplay) {
        this.cPhaseVoltageDisplay = cPhaseVoltageDisplay;
    }

    public String getaPhaseCurrentDisplay() {
        return aPhaseCurrentDisplay;
    }

    public void setaPhaseCurrentDisplay(String aPhaseCurrentDisplay) {
        this.aPhaseCurrentDisplay = aPhaseCurrentDisplay;
    }

    public String getbPhaseCurrentDisplay() {
        return bPhaseCurrentDisplay;
    }

    public void setbPhaseCurrentDisplay(String bPhaseCurrentDisplay) {
        this.bPhaseCurrentDisplay = bPhaseCurrentDisplay;
    }

    public String getcPhaseCurrentDisplay() {
        return cPhaseCurrentDisplay;
    }

    public void setcPhaseCurrentDisplay(String cPhaseCurrentDisplay) {
        this.cPhaseCurrentDisplay = cPhaseCurrentDisplay;
    }
}
