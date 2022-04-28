package com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage;


import com.framework.common.utils.ExcelVOAttribute;

/**
 * 插座配置
 *
 */
public class BESSocket {

    private static final long serialVersionUID = -2480898723218422107L;

    @ExcelVOAttribute(name = "插座名称", column = "A")
    private String name;
    @ExcelVOAttribute(name = "插座地址", column = "B")
    private String socketAddress;
    @ExcelVOAttribute(name = "插座开关", column = "C")
    private String socketSwitch;
    @ExcelVOAttribute(name = "能耗值", column = "D")
    private String energyConsumptionValue;

    //插座开关展示名称
    private String socketSwitchDisplay;
    //能耗值展示名称
    private String energyConsumptionValueDisplay;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSocketAddress() {
        return socketAddress;
    }

    public void setSocketAddress(String socketAddress) {
        this.socketAddress = socketAddress;
    }

    public String getSocketSwitch() {
        return socketSwitch;
    }

    public void setSocketSwitch(String socketSwitch) {
        this.socketSwitch = socketSwitch;
    }

    public String getEnergyConsumptionValue() {
        return energyConsumptionValue;
    }

    public void setEnergyConsumptionValue(String energyConsumptionValue) {
        this.energyConsumptionValue = energyConsumptionValue;
    }

    public String getSocketSwitchDisplay() {
        return socketSwitchDisplay;
    }

    public void setSocketSwitchDisplay(String socketSwitchDisplay) {
        this.socketSwitchDisplay = socketSwitchDisplay;
    }

    public String getEnergyConsumptionValueDisplay() {
        return energyConsumptionValueDisplay;
    }

    public void setEnergyConsumptionValueDisplay(String energyConsumptionValueDisplay) {
        this.energyConsumptionValueDisplay = energyConsumptionValueDisplay;
    }
}
