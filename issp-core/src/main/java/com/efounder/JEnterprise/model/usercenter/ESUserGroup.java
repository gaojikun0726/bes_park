package com.efounder.JEnterprise.model.usercenter;

import com.core.common.BaseEntity;

import java.io.Serializable;

/**
 * 
 * 类名称：UserGroup
 * 类描述：用户组对象
 * 创建人：yanyonghui
 * 修改人：yanyonghui
 * 修改时间：2018年1月16日 
 * 修改备注：
 * @version 1.0.0 
 *
 */
public class ESUserGroup implements BaseEntity<Serializable>{
	 private static final long serialVersionUID = 3624947930970250778L;
	 private String f_zbh;//编号
	 private String f_zmc;//名称
	 private String f_pzbh;//父编号
	 private String f_remark;//备注
	 private String f_js;//级数
	 private String f_mx;//明细
	 private String f_crdate;// 创建日期
	 private String f_chdate;// 修改日期
	public String getF_zbh() {
		return f_zbh;
	}
	public void setF_zbh(String f_zbh) {
		this.f_zbh = f_zbh;
	}
	public String getF_zmc() {
		return f_zmc;
	}
	public void setF_zmc(String f_zmc) {
		this.f_zmc = f_zmc;
	}
	public String getF_pzbh() {
		return f_pzbh;
	}
	public void setF_pzbh(String f_pzbh) {
		this.f_pzbh = f_pzbh;
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
	public String getF_chdate() {
		return f_chdate;
	}
	public void setF_chdate(String f_chdate) {
		this.f_chdate = f_chdate;
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

}
