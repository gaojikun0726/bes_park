package com.efounder.JEnterprise.model.usercenter;

import com.core.common.BaseEntity;

import java.io.Serializable;

/**
 * @description 岗位自定义
 * @author gongfanfei
 * @datetime 2018年5月4日
 */
public class ESPost implements BaseEntity<Serializable>{

	private static final long serialVersionUID = 1L;

	
	private String f_guid;// 角色guid
	private String f_gwbh;// 角色编号
	private String f_gwmc;// 角色名称
	private String f_remark;// 备注
	private String f_zzjgbh;// 组织机构 编码
	private String f_zzjgmc;// 组织机构名称
	private String f_crdate;// 创建日期
	private String f_chdate;// 修改日期
	
	public String getF_zzjgmc() {
		return f_zzjgmc;
	}
	public void setF_zzjgmc(String f_zzjgmc) {
		this.f_zzjgmc = f_zzjgmc;
	}
	public String getF_guid() {
		return f_guid;
	}
	public void setF_guid(String f_guid) {
		this.f_guid = f_guid;
	}
	public String getF_gwbh() {
		return f_gwbh;
	}
	public void setF_gwbh(String f_gwbh) {
		this.f_gwbh = f_gwbh;
	}
	public String getF_gwmc() {
		return f_gwmc;
	}
	public void setF_gwmc(String f_gwmc) {
		this.f_gwmc = f_gwmc;
	}
	public String getF_remark() {
		return f_remark;
	}
	public void setF_remark(String f_remark) {
		this.f_remark = f_remark;
	}
	public String getF_zzjgbh() {
		return f_zzjgbh;
	}
	public void setF_zzjgbh(String f_zzjgbh) {
		this.f_zzjgbh = f_zzjgbh;
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
