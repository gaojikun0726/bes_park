package com.efounder.JEnterprise.model.usercenter;

import com.core.common.BaseEntity;

import java.io.Serializable;

/**
 * @description 岗位功能权限管理
 * @author gongfanfei
 * @datetime 2018年5月21日
 */
public class ESUserPostGnqxManage implements BaseEntity<Serializable>{

	private static final long serialVersionUID = -2212223322412113123L;

	
	private String f_gwguid;// 岗位编号
	private String f_gnzdid;// 功能字典编号
	private String f_sh;// 审核
	private String f_crdate;// 创建日期
	private String f_chdate;// 修改日期

	public String getF_gwguid() {
		return f_gwguid;
	}
	public void setF_gwguid(String f_gwguid) {
		this.f_gwguid = f_gwguid;
	}
	
	public String getF_gnzdid() {
		return f_gnzdid;
	}
	public void setF_gnzdid(String f_gnzdid) {
		this.f_gnzdid = f_gnzdid;
	}
	public String getF_sh() {
		return f_sh;
	}
	public void setF_sh(String f_sh) {
		this.f_sh = f_sh;
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

}
