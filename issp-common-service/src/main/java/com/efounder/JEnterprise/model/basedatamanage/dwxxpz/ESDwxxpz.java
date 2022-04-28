package com.efounder.JEnterprise.model.basedatamanage.dwxxpz;

import com.core.common.tree.IBaseTree;

import java.io.Serializable;

public class ESDwxxpz implements IBaseTree,Serializable{

	private static final long serialVersionUID = 3624947930970250778L;

	private String f_id;//id

	private String f_dwxxbh;//组织机构编号

	private String f_dwxxmc;//组织机构名称

	private String f_pdwxxbh;//父组织机构编号

	public String getF_dwxxbh() {
		return f_dwxxbh;
	}

	public void setF_dwxxbh(String f_dwxxbh) {
		this.f_dwxxbh = f_dwxxbh;
	}

	public String getF_dwxxmc() {
		return f_dwxxmc;
	}

	public void setF_dwxxmc(String f_dwxxmc) {
		this.f_dwxxmc = f_dwxxmc;
	}

	public String getF_pdwxxbh() {
		return f_pdwxxbh;
	}

	public void setF_pdwxxbh(String f_pdwxxbh) {
		this.f_pdwxxbh = f_pdwxxbh;
	}

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


	public String getF_remark() {
		return f_remark;
	}

	public void setF_remark(String f_remark) {
		this.f_remark = f_remark;
	}

	@Override
	public String getPId() {
		return this.f_pdwxxbh;
	}

	@Override
	public String getCId() {
		return this.f_dwxxbh;
	}

	@Override
	public String getCMc() {
		return this.f_dwxxmc;
	}

	@Override
	public String getCJs() {
		return this.f_js;
	}

}
