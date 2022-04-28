package com.efounder.JEnterprise.model.basedatamanage.zzjg;

import com.core.common.tree.IBaseTree;

import java.io.Serializable;

public class ESZzjg implements IBaseTree,Serializable{
	
	private static final long serialVersionUID = 3624947930970250778L;
	
	private String f_id;//id
	
	private String f_zzjgbh;//组织机构编号
	
	private String f_zzjgmc;//组织机构名称
	
	private String f_pzzjgbh;//父组织机构编号
	
	private String f_remark;//备注
	
	private String f_js;//级数
	
	private String f_mx;//明细
	
	private String f_crdate;//创建日期
	
	private String f_chdate;//修改日期

	public String getF_id() {
		return f_id;
	}

	public void setF_id(String f_id) {
		this.f_id = f_id;
	}

	public String getF_zzjgbh() {
		return f_zzjgbh;
	}

	public void setF_zzjgbh(String f_zzjgbh) {
		this.f_zzjgbh = f_zzjgbh;
	}

	public String getF_zzjgmc() {
		return f_zzjgmc;
	}

	public void setF_zzjgmc(String f_zzjgmc) {
		this.f_zzjgmc = f_zzjgmc;
	}

	public String getF_js() {
		return f_js;
	}

	public void setF_js(String f_js) {
		this.f_js = f_js;
	}

	public String getF_mx() {
		return f_mx;
	}

	public void setF_mx(String f_mx) {
		this.f_mx = f_mx;
	}

	public String getF_crdate() {
		return f_crdate;
	}

	public void setF_crdate(String f_crdate) {
		this.f_crdate = f_crdate;
	}

	public String getF_chdate() {
		return f_chdate;
	}

	public void setF_chdate(String f_chdate) {
		this.f_chdate = f_chdate;
	}

	public String getF_pzzjgbh() {
		return f_pzzjgbh;
	}

	public void setF_pzzjgbh(String f_pzzjgbh) {
		this.f_pzzjgbh = f_pzzjgbh;
	}

	public String getF_remark() {
		return f_remark;
	}

	public void setF_remark(String f_remark) {
		this.f_remark = f_remark;
	}

	@Override
	public String getPId() {
		return this.f_pzzjgbh;
	}

	@Override
	public String getCId() {
		return this.f_zzjgbh;
	}

	@Override
	public String getCMc() {
		return this.f_zzjgmc;
	}

	@Override
	public String getCJs() {
		return this.f_js;
	}

}
