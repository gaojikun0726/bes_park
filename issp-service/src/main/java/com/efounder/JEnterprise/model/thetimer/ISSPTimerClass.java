package com.efounder.JEnterprise.model.thetimer;

import com.core.common.BaseEntity;

import java.io.Serializable;

public class ISSPTimerClass implements BaseEntity<Serializable>{

	private static final long serialVersionUID = -5365005606666962071L;
	
	private String fClassBh;//对象编号
	
	private String fClassName;//对象名称
	
	private String fClassPath;//类路径
	
	private String fCrdate;//创建日期
	
	private String fChdate;//修改日期

	public String getfClassBh() {
		return fClassBh;
	}

	public void setfClassBh(String fClassBh) {
		this.fClassBh = fClassBh;
	}

	public String getfClassName() {
		return fClassName;
	}

	public void setfClassName(String fClassName) {
		this.fClassName = fClassName;
	}

	public String getfClassPath() {
		return fClassPath;
	}

	public void setfClassPath(String fClassPath) {
		this.fClassPath = fClassPath;
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
