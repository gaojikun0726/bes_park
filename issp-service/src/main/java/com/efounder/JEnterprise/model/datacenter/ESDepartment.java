package com.efounder.JEnterprise.model.datacenter;

import java.io.Serializable;

/**
 * @Description 部门定义
 * @author sunhao
 * @date 2018年5月30日
 *
 */
public class ESDepartment implements Serializable {
	private static final long serialVersionUID = 35654970250778L;
	/**
	 * 部门编号
	 */
	private String f_bmbh;
	/**
	 * 部门名称
	 */
	private String f_bmmc;
	/**
	 * 组织机构编号
	 */
	private String f_zzjgbh;
	/**
	 * 创建日期
	 */
	private String f_crdate;
	/**
	 * 修改日期
	 */
	private String f_chdate;
	
	public String getF_bmbh() {
		return f_bmbh;
	}
	public void setF_bmbh(String f_bmbh) {
		this.f_bmbh = f_bmbh;
	}
	public String getF_bmmc() {
		return f_bmmc;
	}
	public void setF_bmmc(String f_bmmc) {
		this.f_bmmc = f_bmmc;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
