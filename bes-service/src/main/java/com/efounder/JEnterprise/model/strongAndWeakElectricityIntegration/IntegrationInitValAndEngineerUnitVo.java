package com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration;

import lombok.Data;

@Data
public class IntegrationInitValAndEngineerUnitVo {
    
    /**
     * 系统名称
     */
    private String f_sys_name;

    private String f_sys_name_old;

    /**
     * 精度
     */
    private String f_accuracy;

    /**
     * 数值
     */
    private String f_init_val;
    
    /**
     * 单位
     */
    private String f_engineer_unit;
    
}
