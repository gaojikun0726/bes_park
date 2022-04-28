package com.efounder.JEnterprise.model.app;

import java.io.Serializable;

/**
 *
 * 电表配置
 *
 * @author xiepufeng
 *
 */
public class AirconditioningConfigModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer    id;                   // 主键
    private String     name;                 // 空调名称
    private String     ktAddress;          // 空调位置
    private String     ktMode;        // 空调模式
    private String     ktWindspeed;          // 空调风速
    private String     ktSwitch;        // 空调开关
    private String     ktTemperature;        // 空调显示温度


    private String     ktxsTemperature;        // 空调温度
    private String     ktModeSystem;        // 空调模式
    private String     ktWindspeedSystem;          // 空调风速
    private String     ktSwitchSystem;        // 空调开关
    private String     ktTemperatureSystem;        // 空调温度
    private String     ktxsTemperatureSystem;        // 空调显示温度

    public String getKtxsTemperature() {
        return ktxsTemperature;
    }

    public void setKtxsTemperature(String ktxsTemperature) {
        this.ktxsTemperature = ktxsTemperature;
    }

    public String getKtxsTemperatureSystem() {
        return ktxsTemperatureSystem;
    }

    public void setKtxsTemperatureSystem(String ktxsTemperatureSystem) {
        this.ktxsTemperatureSystem = ktxsTemperatureSystem;
    }


    public String getKtModeSystem() {
        return ktModeSystem;
    }

    public void setKtModeSystem(String ktModeSystem) {
        this.ktModeSystem = ktModeSystem;
    }

    public String getKtWindspeedSystem() {
        return ktWindspeedSystem;
    }

    public void setKtWindspeedSystem(String ktWindspeedSystem) {
        this.ktWindspeedSystem = ktWindspeedSystem;
    }

    public String getKtSwitchSystem() {
        return ktSwitchSystem;
    }

    public void setKtSwitchSystem(String ktSwitchSystem) {
        this.ktSwitchSystem = ktSwitchSystem;
    }

    public String getKtTemperatureSystem() {
        return ktTemperatureSystem;
    }

    public void setKtTemperatureSystem(String ktTemperatureSystem) {
        this.ktTemperatureSystem = ktTemperatureSystem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKtAddress() {
        return ktAddress;
    }

    public void setKtAddress(String ktAddress) {
        this.ktAddress = ktAddress;
    }

    public String getKtMode() {
        return ktMode;
    }

    public void setKtMode(String ktMode) {
        this.ktMode = ktMode;
    }

    public String getKtWindspeed() {
        return ktWindspeed;
    }

    public void setKtWindspeed(String ktWindspeed) {
        this.ktWindspeed = ktWindspeed;
    }

    public String getKtSwitch() {
        return ktSwitch;
    }

    public void setKtSwitch(String ktSwitch) {
        this.ktSwitch = ktSwitch;
    }

    public String getKtTemperature() {
        return ktTemperature;
    }

    public void setKtTemperature(String ktTemperature) {
        this.ktTemperature = ktTemperature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
