package com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration;

import lombok.Data;

@Data
public class ColdHeatSourceVo {
    /**
     * 名称
     */
    private String f_name;
    
    /**
     * 当前状态
     */
    private String f_dqzt;
    /**
     * 冷水出口温度
     */
    private String f_lsckwd;
    /**
     * 冷水入口温度
     */
    private String f_lsrkwd;
    /**
     * 冷却水出口温度
     */
    private String f_lqsckwd;
    /**
     * 冷却水入口温度
     */
    private String f_lqsrkwd;
    /**
     * 当前流量
     */
    private String f_dqll;
    
}
