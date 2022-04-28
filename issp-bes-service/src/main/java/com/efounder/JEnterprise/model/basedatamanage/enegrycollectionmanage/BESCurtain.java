package com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage;


import com.framework.common.utils.ExcelVOAttribute;

/**
 * 电动窗帘配置
 *
 */
public class BESCurtain {

    private static final long serialVersionUID = -2480898723218422107L;

    @ExcelVOAttribute(name = "名称", column = "A")
    private String name;
    @ExcelVOAttribute(name = "窗帘位置", column = "B")
    private String curtainAddress;
    @ExcelVOAttribute(name = "开关", column = "C")
    private String curtainSwitchSystem;
    @ExcelVOAttribute(name = "开度控制", column = "D")
    private String curtainKdkzSystem;
    @ExcelVOAttribute(name = "暂停功能", column = "E")
    private String curtainStopSystem;

    //开关展示名称
    private String curtainSwitch;
    //开度控制展示名称
    private String curtainKdkz;
    //暂停功能展示名称
    private String curtainStop;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurtainAddress() {
        return curtainAddress;
    }

    public void setCurtainAddress(String curtainAddress) {
        this.curtainAddress = curtainAddress;
    }

    public String getCurtainSwitch() {
        return curtainSwitch;
    }

    public void setCurtainSwitch(String curtainSwitch) {
        this.curtainSwitch = curtainSwitch;
    }

    public String getCurtainSwitchSystem() {
        return curtainSwitchSystem;
    }

    public void setCurtainSwitchSystem(String curtainSwitchSystem) {
        this.curtainSwitchSystem = curtainSwitchSystem;
    }

    public String getCurtainKdkz() {
        return curtainKdkz;
    }

    public void setCurtainKdkz(String curtainKdkz) {
        this.curtainKdkz = curtainKdkz;
    }

    public String getCurtainKdkzSystem() {
        return curtainKdkzSystem;
    }

    public void setCurtainKdkzSystem(String curtainKdkzSystem) {
        this.curtainKdkzSystem = curtainKdkzSystem;
    }

    public String getCurtainStop() {
        return curtainStop;
    }

    public void setCurtainStop(String curtainStop) {
        this.curtainStop = curtainStop;
    }

    public String getCurtainStopSystem() {
        return curtainStopSystem;
    }

    public void setCurtainStopSystem(String curtainStopSystem) {
        this.curtainStopSystem = curtainStopSystem;
    }
}
