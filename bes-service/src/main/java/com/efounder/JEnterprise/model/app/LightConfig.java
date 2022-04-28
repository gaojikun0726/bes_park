package com.efounder.JEnterprise.model.app;

import lombok.Data;

/**
 * describe: 灯光配置
 *
 * @author zs
 * @date 2020/05/12
 */
@Data
public class LightConfig {

    /**
     * 主键
     */
    private int id;

    /**
     * 灯光名称
     */
    private String name;

    /**
     * 灯光地址
     */
    private String lightAddress;

    /**
     * 灯光开关
     */
    private String lightSwitch;

    /**
     * 灯光开关
     */
    private String lightSwitchSystem;

    /**
     * 开关状态
     */
    private String lightStatus;
    /**
     * 开关状态系统名称
     */
    private String lightStatusSystem;



}
