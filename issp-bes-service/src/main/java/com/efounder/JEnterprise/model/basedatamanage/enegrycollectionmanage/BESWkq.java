package com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage;


import com.framework.common.utils.ExcelVOAttribute;

/**
 * 温控器配置
 *
 */
public class BESWkq {

    private static final long serialVersionUID = -2480898723218422107L;

    @ExcelVOAttribute(name = "温控器名称", column = "A")
    private String name;
    @ExcelVOAttribute(name = "温控器位置", column = "B")
    private String wkqAddress;
    @ExcelVOAttribute(name = "温控器模式", column = "C")
    private String wkqModeSystem;
    @ExcelVOAttribute(name = "温控器风速", column = "D")
    private String wkqWindspeedSystem;
    @ExcelVOAttribute(name = "温控器开关", column = "E")
    private String wkqSwitchSystem;
    @ExcelVOAttribute(name = "温控器设定温度", column = "F")
    private String wkqTemperatureSystem;
    @ExcelVOAttribute(name = "温控器显示温度", column = "G")
    private String wkqDisplaytemperatureSystem;

    //温控器模式展示名称
    private String wkqMode;
    //温控器风速展示名称
    private String wkqWindspeed;
    //温控器开关展示名称
    private String wkqSwitch;
    //温控器设定温度展示名称
    private String wkqTemperature;
    //温控器显示温度展示名称
    private String wkqDisplaytemperature;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWkqAddress() {
        return wkqAddress;
    }

    public void setWkqAddress(String wkqAddress) {
        this.wkqAddress = wkqAddress;
    }

    public String getWkqModeSystem() {
        return wkqModeSystem;
    }

    public void setWkqModeSystem(String wkqModeSystem) {
        this.wkqModeSystem = wkqModeSystem;
    }

    public String getWkqWindspeedSystem() {
        return wkqWindspeedSystem;
    }

    public void setWkqWindspeedSystem(String wkqWindspeedSystem) {
        this.wkqWindspeedSystem = wkqWindspeedSystem;
    }

    public String getWkqSwitchSystem() {
        return wkqSwitchSystem;
    }

    public void setWkqSwitchSystem(String wkqSwitchSystem) {
        this.wkqSwitchSystem = wkqSwitchSystem;
    }

    public String getWkqTemperatureSystem() {
        return wkqTemperatureSystem;
    }

    public void setWkqTemperatureSystem(String wkqTemperatureSystem) {
        this.wkqTemperatureSystem = wkqTemperatureSystem;
    }

    public String getWkqDisplaytemperatureSystem() {
        return wkqDisplaytemperatureSystem;
    }

    public void setWkqDisplaytemperatureSystem(String wkqDisplaytemperatureSystem) {
        this.wkqDisplaytemperatureSystem = wkqDisplaytemperatureSystem;
    }

    public String getWkqMode() {
        return wkqMode;
    }

    public void setWkqMode(String wkqMode) {
        this.wkqMode = wkqMode;
    }

    public String getWkqWindspeed() {
        return wkqWindspeed;
    }

    public void setWkqWindspeed(String wkqWindspeed) {
        this.wkqWindspeed = wkqWindspeed;
    }

    public String getWkqSwitch() {
        return wkqSwitch;
    }

    public void setWkqSwitch(String wkqSwitch) {
        this.wkqSwitch = wkqSwitch;
    }

    public String getWkqTemperature() {
        return wkqTemperature;
    }

    public void setWkqTemperature(String wkqTemperature) {
        this.wkqTemperature = wkqTemperature;
    }

    public String getWkqDisplaytemperature() {
        return wkqDisplaytemperature;
    }

    public void setWkqDisplaytemperature(String wkqDisplaytemperature) {
        this.wkqDisplaytemperature = wkqDisplaytemperature;
    }
}
