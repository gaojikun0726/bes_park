package com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration;

import lombok.Data;

@Data
public class TableDataConfigVo {
	
	/**
	 * 所配置的展示表数据的主键ID
	 */
	private String f_id;
	
	/**
	 * 所配置表数据展示对应的ddc系统名称
	 */
	private String f_ddc_sys_name;
    /**
     * 系统名称
     */
    private String f_sys_name;
    /**
     * 描述
     */
    private String f_desc;
    
    /**
     * 所展示的数据位置序号
     */
    private String f_seq;
    /**
     * 表数据标记，用以区分不同的表配置展示 eg:0:流量计，1：温度计，2：压力压差负荷计
     */
    private String f_mark;
    /**
     * 页面名称，用于区分不同的页面所配置的数据
     */
    private String f_page_name;
    /**
     * 设备id。用于区分为什么设备展示的数据配置（如：电表id)
     */
    private String f_device_id;
    /**
     * 所展示点位信息的单位 eg:A,V,kw
     */
    private String f_unit;
    
}
