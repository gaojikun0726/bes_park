package com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration;

import lombok.Data;

@Data
public class IntegrationPageDivNumConfigVo {
    
    /**
     * 配置的设备id
     */
    private String f_equipment_id;
    
    /**
     * 页面配置的展示div数目
     */
    private int f_div_num;
    
    /**
     * 配置所处的页面类型：所属类型(1：冷冻水  2：冷却水  3：冷热机组  4：冷却塔  5：电表)
     */
    private String f_type_id;
    
}
