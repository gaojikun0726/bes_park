package com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage;


import com.framework.common.utils.ExcelVOAttribute;

/**
 * 灯光配置
 *
 */
public class BESLight {

    private static final long serialVersionUID = -2480898723218422107L;

    @ExcelVOAttribute(name = "灯光名称", column = "A")
    private String name;
    @ExcelVOAttribute(name = "灯光地址", column = "B")
    private String lightAddress;
    @ExcelVOAttribute(name = "灯光开关", column = "C")
    private String lightSwitchSystem;
    @ExcelVOAttribute(name = "灯光状态", column = "D")
    private String lightStatusSystem;

    //灯光开关展示名称
    private String lightSwitch;
    //灯光状态展示名称
    private String lightStatus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLightAddress() {
        return lightAddress;
    }

    public void setLightAddress(String lightAddress) {
        this.lightAddress = lightAddress;
    }

    public String getLightSwitch() {
        return lightSwitch;
    }

    public void setLightSwitch(String lightSwitch) {
        this.lightSwitch = lightSwitch;
    }

    public String getLightSwitchSystem() {
        return lightSwitchSystem;
    }

    public void setLightSwitchSystem(String lightSwitchSystem) {
        this.lightSwitchSystem = lightSwitchSystem;
    }

    public String getLightStatus() {
        return lightStatus;
    }

    public void setLightStatus(String lightStatus) {
        this.lightStatus = lightStatus;
    }

    public String getLightStatusSystem() {
        return lightStatusSystem;
    }

    public void setLightStatusSystem(String lightStatusSystem) {
        this.lightStatusSystem = lightStatusSystem;
    }
}
