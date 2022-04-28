package com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration;

import lombok.Data;

@Data
public class AliveDivConfigVo {
	
	/**
	 * 所配置div对应的ddc系统名称
	 */
	private String f_ddc_sys_name;
    /**
     * 系统名称
     */
    private String f_sys_name;
    /**
     * div序号
     */
    private String f_div_seq;
    
    /**
     * 相对顶部位移距离
     */
    private String f_move_top;
    /**
     * 相对左侧位移距离
     */
    private String f_move_left;
}
