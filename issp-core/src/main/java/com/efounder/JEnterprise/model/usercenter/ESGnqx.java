package com.efounder.JEnterprise.model.usercenter;

import com.core.common.BaseEntity;

import java.io.Serializable;

/**
 * @Description 功能权限字典维护
 * @author gongfanfei
 * @date 2018年5月30日
 *
 */
public class ESGnqx implements BaseEntity<Serializable>{
	private static final long serialVersionUID = 136212370250723L;
	/**
	 * 权限编码
	 */
	private String f_qxbh;
	/**
	 * 权限名称
	 */
	private String f_qxmc;
	/**
	 * 子系统编号
	 */
	private String f_xtbh;
	/**
	 * 数据表名
	 */
	private String f_tabn;
	/**
	 * 编码字段
	 */
	private String f_bhzd;
	/**
	 * 名称字段
	 */
	private String f_mczd;
	/**
	 * 是否使用   ‘‘0’不使用 ‘1’使用数据权限
	 */
	private String f_sfsy;
	/**
	 * 用户权限表
	 */
	private String f_user_qxb;
	/**
	 * 用户组权限表
	 */
	private String f_yhz_qxb;
	/**
	 * 角色权限表
	 */
	private String f_role_qxb;
	/**
	 * 岗位权限表
	 */
	private String f_gw_qxb;
	/**
	 * 创建日期
	 */
	private String f_crdate;
	/**
	 * 修改日期
	 */
	private String f_chdate;
	public String getF_qxbh() {
		return f_qxbh;
	}
	public void setF_qxbh(String f_qxbh) {
		this.f_qxbh = f_qxbh;
	}
	public String getF_qxmc() {
		return f_qxmc;
	}
	public void setF_qxmc(String f_qxmc) {
		this.f_qxmc = f_qxmc;
	}
	public String getF_xtbh() {
		return f_xtbh;
	}
	public void setF_xtbh(String f_xtbh) {
		this.f_xtbh = f_xtbh;
	}
	public String getF_tabn() {
		return f_tabn;
	}
	public void setF_tabn(String f_tabn) {
		this.f_tabn = f_tabn;
	}
	public String getF_bhzd() {
		return f_bhzd;
	}
	public void setF_bhzd(String f_bhzd) {
		this.f_bhzd = f_bhzd;
	}
	public String getF_mczd() {
		return f_mczd;
	}
	public void setF_mczd(String f_mczd) {
		this.f_mczd = f_mczd;
	}
	public String getF_sfsy() {
		return f_sfsy;
	}
	public void setF_sfsy(String f_sfsy) {
		this.f_sfsy = f_sfsy;
	}
	
	public String getF_user_qxb() {
		return f_user_qxb;
	}
	public void setF_user_qxb(String f_user_qxb) {
		this.f_user_qxb = f_user_qxb;
	}
	public String getF_yhz_qxb() {
		return f_yhz_qxb;
	}
	public void setF_yhz_qxb(String f_yhz_qxb) {
		this.f_yhz_qxb = f_yhz_qxb;
	}
	public String getF_role_qxb() {
		return f_role_qxb;
	}
	public void setF_role_qxb(String f_role_qxb) {
		this.f_role_qxb = f_role_qxb;
	}
	public String getF_gw_qxb() {
		return f_gw_qxb;
	}
	public void setF_gw_qxb(String f_gw_qxb) {
		this.f_gw_qxb = f_gw_qxb;
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
