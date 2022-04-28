package com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage;

import com.framework.common.utils.ExcelVOAttribute;


/**
 * 空调接口配置
 *
 */
public class BESAirconditioning {

    private static final long serialVersionUID = -2480898723218422107L;

    @ExcelVOAttribute(name = "空调名称", column = "A")
    private String name;
    @ExcelVOAttribute(name = "空调位置", column = "B")
    private String airconditioningAddress;
    @ExcelVOAttribute(name = "空调模式", column = "C")
    private String airconditioningModeSystem;
    @ExcelVOAttribute(name = "空调风速", column = "D")
    private String airconditioningWindspeedSystem;
    @ExcelVOAttribute(name = "空调开关", column = "E")
    private String airconditioningSwitchSystem;
    @ExcelVOAttribute(name = "空调温度", column = "F")
    private String airconditioningTemperatureSystem;
    @ExcelVOAttribute(name = "空调显示温度", column = "G")
    private String airconditioningDisplaytemperatureSystem;

    //空调模式展示名称
    private String airconditioningMode;
    //空调风速展示名称
    private String airconditioningWindspeed;
    //空调开关展示名称
    private String airconditioningSwitch;
    //空调温度展示名称
    private String airconditioningTemperature;
    //空调显示温度展示名称
    private String airconditioningDisplaytemperature;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAirconditioningAddress() {
        return airconditioningAddress;
    }

    public void setAirconditioningAddress(String airconditioningAddress) {
        this.airconditioningAddress = airconditioningAddress;
    }

    public String getAirconditioningModeSystem() {
        return airconditioningModeSystem;
    }

    public void setAirconditioningModeSystem(String airconditioningModeSystem) {
        this.airconditioningModeSystem = airconditioningModeSystem;
    }

    public String getAirconditioningWindspeedSystem() {
        return airconditioningWindspeedSystem;
    }

    public void setAirconditioningWindspeedSystem(String airconditioningWindspeedSystem) {
        this.airconditioningWindspeedSystem = airconditioningWindspeedSystem;
    }

    public String getAirconditioningSwitchSystem() {
        return airconditioningSwitchSystem;
    }

    public void setAirconditioningSwitchSystem(String airconditioningSwitchSystem) {
        this.airconditioningSwitchSystem = airconditioningSwitchSystem;
    }

    public String getAirconditioningTemperatureSystem() {
        return airconditioningTemperatureSystem;
    }

    public void setAirconditioningTemperatureSystem(String airconditioningTemperatureSystem) {
        this.airconditioningTemperatureSystem = airconditioningTemperatureSystem;
    }

    public String getAirconditioningDisplaytemperatureSystem() {
        return airconditioningDisplaytemperatureSystem;
    }

    public void setAirconditioningDisplaytemperatureSystem(String airconditioningDisplaytemperatureSystem) {
        this.airconditioningDisplaytemperatureSystem = airconditioningDisplaytemperatureSystem;
    }

    public String getAirconditioningMode() {
        return airconditioningMode;
    }

    public void setAirconditioningMode(String airconditioningMode) {
        this.airconditioningMode = airconditioningMode;
    }

    public String getAirconditioningWindspeed() {
        return airconditioningWindspeed;
    }

    public void setAirconditioningWindspeed(String airconditioningWindspeed) {
        this.airconditioningWindspeed = airconditioningWindspeed;
    }

    public String getAirconditioningSwitch() {
        return airconditioningSwitch;
    }

    public void setAirconditioningSwitch(String airconditioningSwitch) {
        this.airconditioningSwitch = airconditioningSwitch;
    }

    public String getAirconditioningTemperature() {
        return airconditioningTemperature;
    }

    public void setAirconditioningTemperature(String airconditioningTemperature) {
        this.airconditioningTemperature = airconditioningTemperature;
    }

    public String getAirconditioningDisplaytemperature() {
        return airconditioningDisplaytemperature;
    }

    public void setAirconditioningDisplaytemperature(String airconditioningDisplaytemperature) {
        this.airconditioningDisplaytemperature = airconditioningDisplaytemperature;
    }
}
