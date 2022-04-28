package com.efounder.JEnterprise.model.systemcenter.logmanage;

import com.core.common.BaseEntity;

import java.io.Serializable;

/**
 * 系统登录日志
 * @author yujieying
 * @date 2018/09/26
 */
public class ESSysLoginLog implements BaseEntity<Serializable> {

	private static final long serialVersionUID = 5799362772943887072L;
	
    private String f_id;//日志ID
    
	private String f_yhbh;//登录账号
	
	private String f_username;//用户名
	
	private String f_zzjgid;//组织机构id
	
	private String f_zzjgmc;//组织机构名称
	
	private String f_rolebh;//角色编号
	
	private String f_rolemc;//角色名称
	
	private String f_type;//操作类型
	private String f_type1;//操作类型
	
	private String f_logintime;//登录时间
	
	private String f_crdate;//创建时间
	
	private String f_chdate;//修改时间


	public String getF_yhbh() {
		return f_yhbh;
	}

	public String setF_yhbh(String f_yhbh) {
		return this.f_yhbh = f_yhbh;
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

	public String getF_logintime() {
		return f_logintime;
	}

	public void setF_logintime(String f_logintime) {
		this.f_logintime = f_logintime;
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


	public String getF_id() {
		return f_id;
	}

	public void setF_id(String f_id) {
		this.f_id = f_id;
	}

	public String getF_username() {
		return f_username;
	}

	public void setF_username(String f_username) {
		this.f_username = f_username;
	}

	public String getF_type() {
		return f_type;
	}

	public void setF_type(String f_type) {
		this.f_type = f_type;
	}

	public String getF_zzjgid() {
		return f_zzjgid;
	}

	public void setF_zzjgid(String f_zzjgid) {
		this.f_zzjgid = f_zzjgid;
	}

	public String getF_zzjgmc() {
		return f_zzjgmc;
	}

	public void setF_zzjgmc(String f_zzjgmc) {
		this.f_zzjgmc = f_zzjgmc;
	}

	public String getF_type1() {
		return f_type1;
	}

	public void setF_type1(String f_type1) {
		this.f_type1 = f_type1;
	}



	

   
}