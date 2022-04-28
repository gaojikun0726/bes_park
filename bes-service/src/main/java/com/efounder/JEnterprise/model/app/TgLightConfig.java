package com.efounder.JEnterprise.model.app;

import lombok.Data;

/**
 * describe: 灯光配置
 *
 * @author zs
 * @date 2020/05/12
 */
@Data
public class TgLightConfig {

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
    private String tgLightAddress;

    /**
     * 灯光调光
     */
    private String tgLightTg;

    /**
     * 灯光调光系统名称
     */
    private String tgLightTgSystem;

    /**
     * 开关状态
     */
    private String tgLightStatus;
    /**
     * 开关状态系统名称
     */
    private String tgLightStatusSystem;



}
