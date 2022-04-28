package com.efounder.JEnterprise.model.app;

import lombok.Data;

/**
 * describe:插座配置
 *
 * @author wzx
 * @date 2020年9月24日9:36:59
 */
@Data
public class SocketConfigModel {

    /**
     * 主键
     */
    private int id;

    /**
     * 插座名称
     */
    private String name;

    /**
     * 插座地址
     */
    private String socketAddress;

    /**
     * 插座开关
     */
    private String socketSwitch;

    /**
     * 能耗值
     */
    private String energyConsumptionValue;

    /**
     * 插座开关
     */
    private String socketSwitchDisplay;

    /**
     * 能耗值
     */
    private String energyConsumptionValueDisplay;





}
