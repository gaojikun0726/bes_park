package com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration;

import lombok.Data;

@Data
public class CoolingTowerVo {
    /**
     * 名称
     */
    private String f_name;
    
    /**
     * 当前状态
     */
    private String f_dqzt;
    /**
     * 当前功率
     */
    private String f_dqgl;
    /**
     * 累计能耗
     */
    private String f_ljnh;
    /**
     * 累计运行时间
     */
    private String f_ljyxsj;
    
}
