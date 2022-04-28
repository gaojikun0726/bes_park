package com.efounder.JEnterprise.model.app;

import java.io.Serializable;

/**
 *
 * 温控器配置
 *
 * @author wzx
 *
 */
public class WkqConfigModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer    id;                   // 主键
    private String     name;                 // 空调名称
    private String     wkqAddress;          // 空调位置
    private String     wkqMode;        // 空调模式
    private String     wkqWindspeed;          // 空调风速
    private String     wkqSwitch;        // 空调开关
    private String     wkqTemperature;        // 空调显示温度


    private String     wkqxsTemperature;        // 空调温度
    private String     wkqModeSystem;        // 空调模式
    private String     wkqWindspeedSystem;          // 空调风速
    private String     wkqSwitchSystem;        // 空调开关
    private String     wkqTemperatureSystem;        // 空调温度
    private String     wkqxsTemperatureSystem;        // 空调显示温度

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWkqAddress() {
        return wkqAddress;
    }

    public void setWkqAddress(String wkqAddress) {
        this.wkqAddress = wkqAddress;
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

    public String getWkqxsTemperature() {
        return wkqxsTemperature;
    }

    public void setWkqxsTemperature(String wkqxsTemperature) {
        this.wkqxsTemperature = wkqxsTemperature;
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

    public String getWkqxsTemperatureSystem() {
        return wkqxsTemperatureSystem;
    }

    public void setWkqxsTemperatureSystem(String wkqxsTemperatureSystem) {
        this.wkqxsTemperatureSystem = wkqxsTemperatureSystem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
