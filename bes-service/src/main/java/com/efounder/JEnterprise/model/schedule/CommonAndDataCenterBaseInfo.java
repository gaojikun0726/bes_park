package com.efounder.JEnterprise.model.schedule;

import lombok.Data;

@Data
public class CommonAndDataCenterBaseInfo {
	
	/**
	 * 建筑代码,对应生成文件：项目节点id F_DataCenterID
	 */
	private String f_buding_code;
	
	/**
	 * 建筑字母别名,对应生成文件：建筑物别名  F_GroupAliasName
	 */
	
	private String f_buding_letter;
	/**
	 * 建筑名称,对应生成文件：建筑名称  F_BuildGroupName
	 */
	private String f_buding_name;
	
	/**
	 * 描述,对应生成文件：描述  F_GroupDesc
	 */
	private String f_describe;

	/**
	 * 数据中心名称,对应生成文件：项目名称           F_DataCenterName
	 */
	private String f_datacenter_name;
	
	/**
	 * 数据中心类型,对应生成文件：dataCeneterType F_DataCenterType
	 */
	private String f_datacenter_type;
	
	/**
	 * 主管单位,对应生成文件：主管单位(项目业主) F_DataCenterManager
	 */
	private String f_governing_body;
	
	/**
	 * 描述,对应生成文件：项目描述  F_DataCenterDesc
	 */
	private String f_datacenter_describe;
	
	/**
	 * 联系人,对应生成文件：联系人 (业主联系人) F_DataCenterContract
	 */
	private String f_contactperson;
	
	/**
	 * 联系电话,对应生成文件：联系方式 (业主联系电话) F_DataCenterTel
	 */
	private String f_phone;
	
	
}
