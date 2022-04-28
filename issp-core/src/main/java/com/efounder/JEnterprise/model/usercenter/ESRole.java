package com.efounder.JEnterprise.model.usercenter;

import com.core.common.BaseEntity;

import java.io.Serializable;

/**
 * @description 用户角色
 * @author gongfanfei
 * @datetime 2018年5月3日
 */
public class ESRole implements BaseEntity<Serializable>{

	private static final long serialVersionUID = 1L;

	
	private String f_guid;	// 角色uid
	private String f_rolebh;// 角色编号
	private String f_rolemc;// 角色名称
	private String f_remark;// 备注
	private String f_crdate;// 创建日期
	private String f_chdate;// 修改日期

	private Integer scopeType; // 数据权限类型

	
	public String getF_guid() {
		return f_guid;
	}

	public void setF_guid(String f_guid) {
		this.f_guid = f_guid;
	}

	public String getF_rolebh() {
		return f_rolebh;
	}

	public void setF_rolebh(String f_rolebh) {
		this.f_rolebh = f_rolebh;
	}

	public String getF_rolemc() {
		return f_rolemc;
	}

	public void setF_rolemc(String f_rolemc) {
		this.f_rolemc = f_rolemc;
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

	public Integer getScopeType()
	{
		return scopeType;
	}

	public void setScopeType(Integer scopeType)
	{
		this.scopeType = scopeType;
	}
}
