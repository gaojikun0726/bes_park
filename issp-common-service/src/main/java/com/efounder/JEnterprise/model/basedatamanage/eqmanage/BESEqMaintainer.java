package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;

import java.io.Serializable;

/**
 * 维护商字典
 * @author liujoujun
 *
 */
public class BESEqMaintainer implements BaseEntity<String>,Serializable{
	
	private static final long serialVersionUID = -2446397424367254660L;
	
	private String f_id;//
	
	private String f_whsmc;//维护商名称
	
	private String f_whsdz;//维护商地址
	
	private String f_whxm;//维护项目
	
	private String f_lxr;//联系人
	
	private String f_lxfs;//联系方式
	
	private String f_cz;//傳真
	
	private String f_email;//邮箱
	
	private String f_remark;//备注
	
	private String f_crdate;//创建日期

	private String f_chdate;//修改日期
	public String getF_chdate() {
		return f_chdate;
	}

	public void setF_chdate(String f_chdate) {
		this.f_chdate = f_chdate;
	}

	public String getF_id() {
		return f_id;
	}

	public void setF_id(String f_id) {
		this.f_id = f_id;
	}

	public String getF_whsmc() {
		return f_whsmc;
	}

	public void setF_whsmc(String f_whsmc) {
		this.f_whsmc = f_whsmc;
	}

	public String getF_whsdz() {
		return f_whsdz;
	}

	public void setF_whsdz(String f_whsdz) {
		this.f_whsdz = f_whsdz;
	}

	public String getF_whxm() {
		return f_whxm;
	}

	public void setF_whxm(String f_whxm) {
		this.f_whxm = f_whxm;
	}

	public String getF_lxr() {
		return f_lxr;
	}

	public void setF_lxr(String f_lxr) {
		this.f_lxr = f_lxr;
	}

	public String getF_lxfs() {
		return f_lxfs;
	}

	public void setF_lxfs(String f_lxfs) {
		this.f_lxfs = f_lxfs;
	}

	public String getF_cz() {
		return f_cz;
	}

	public void setF_cz(String f_cz) {
		this.f_cz = f_cz;
	}

	public String getF_email() {
		return f_email;
	}

	public void setF_email(String f_email) {
		this.f_email = f_email;
	}

	public String getF_remark() {
		return f_remark;
	}

	public void setF_remark(String f_remark) {
		this.f_remark = f_remark;
	}

	public String getF_crdate() {
		return f_crdate;
	}

	public void setF_crdate(String f_crdate) {
		this.f_crdate = f_crdate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
