package com.efounder.JEnterprise.model.thetimer;

import com.core.common.BaseEntity;

import java.io.Serializable;

public class ISSPTimerType implements BaseEntity<Serializable>{

	private static final long serialVersionUID = -7917915731646679119L;
	
	private String fType;//类型 :LOOP,DATE,DAY,WEEK,MONTH
	
	private String fName;//名称:循环，固定时间，每天，每周，每月
	
	private String fRemark;//备注
	
	private String fCrdate;//创建日期
	
	private String fChdate;//修改日期

	public String getfType() {
		return fType;
	}

	public void setfType(String fType) {
		this.fType = fType;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getfRemark() {
		return fRemark;
	}

	public void setfRemark(String fRemark) {
		this.fRemark = fRemark;
	}

	public String getfCrdate() {
		return fCrdate;
	}

	public void setfCrdate(String fCrdate) {
		this.fCrdate = fCrdate;
	}

	public String getfChdate() {
		return fChdate;
	}

	public void setfChdate(String fChdate) {
		this.fChdate = fChdate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
