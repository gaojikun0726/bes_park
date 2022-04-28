package com.efounder.JEnterprise.model.usercenter;

import com.core.common.BaseEntity;

import java.io.Serializable;

/**
 * 
 * 类名称：UserGroupRlgl
 * 类描述：用户组和用户关系
 * 创建人：gongfanfei
 * 修改人：gongfanfei
 * 修改时间：2018年4月28日 
 * 修改备注：
 * @version 1.0.0 
 *
 */
public class UserGrouprlgl implements BaseEntity<Serializable>{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 345234234131231L;
	private String f_zbh;//编号
	 private String f_yhbh;//编号
	 private String f_crdate;// 创建日期
	 private String f_chdate;// 修改日期
	public String getF_zbh() {
		return f_zbh;
	}
	public void setF_zbh(String f_zbh) {
		this.f_zbh = f_zbh;
	}
	public String getF_yhbh() {
		return f_yhbh;
	}
	public void setF_yhbh(String f_yhbh) {
		this.f_yhbh = f_yhbh;
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
