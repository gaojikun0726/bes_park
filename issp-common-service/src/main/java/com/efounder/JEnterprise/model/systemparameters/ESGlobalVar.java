package com.efounder.JEnterprise.model.systemparameters;

import com.core.common.BaseEntity;

import java.io.Serializable;
/**
 * 全局参数
 * @author huangxianbo
 *
 */
public class ESGlobalVar implements BaseEntity<Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3055646388366571979L;

	private String fVarBm;//参数key

    private String fVarMc;//参数名称

    private String fVarValue;//参数值

    private String fRemark;//备注

    private String  fCrdate;//创建日期

    private String  fChdate;//修改日期

	public String getfVarBm() {
		return fVarBm;
	}

	public void setfVarBm(String fVarBm) {
		this.fVarBm = fVarBm;
	}

	public String getfVarMc() {
		return fVarMc;
	}

	public void setfVarMc(String fVarMc) {
		this.fVarMc = fVarMc;
	}

	public String getfVarValue() {
		return fVarValue;
	}

	public void setfVarValue(String fVarValue) {
		this.fVarValue = fVarValue;
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


}