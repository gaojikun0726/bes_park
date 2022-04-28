package com.efounder.JEnterprise.model.basedatamanage.energydataReport;

import com.core.common.BaseEntity;

public class BESDatecenter_Type implements BaseEntity<String>{

	private String F_DATACENTER_TYPEID;//ID
	private String F_DATACENTER_TYPE;//名称
	
	
	public String getF_DATACENTER_TYPEID() {
		return F_DATACENTER_TYPEID;
	}
	public void setF_DATACENTER_TYPEID(String f_DATACENTER_TYPEID) {
		F_DATACENTER_TYPEID = f_DATACENTER_TYPEID;
	}
	public String getF_DATACENTER_TYPE() {
		return F_DATACENTER_TYPE;
	}
	public void setF_DATACENTER_TYPE(String f_DATACENTER_TYPE) {
		F_DATACENTER_TYPE = f_DATACENTER_TYPE;
	}
	
	
}
