package com.efounder.JEnterprise.model.usercenter;

import com.core.common.BaseEntity;

import java.io.Serializable;

/**
 * @description 功能字典
 * @author tianjiangwei
 * @datetime 2018年9月11日
 */
public class ESGnzdManage implements BaseEntity<String> ,Serializable{

	private static final long serialVersionUID = -8543241213123L;

	
	private String f_guid;// 
	private String f_gnbh;// 功能编号
	private String f_gnmc;// 功能名称
	private String f_sh;// es_user_gnqx 是否审核
	private String f_sort;// 菜单排序
	private String f_js;//级数
	private String f_pgnbh;//父GUID
	private String f_pgnmc;//父功能名称
	private String f_gnzdid;//es_user_gnqx 功能字典ID
	private String f_sq;//是否授权
	private String f_mkmc;//es_home_module 模块名称
	private String f_mkguid;//模块id
	private String f_parentid;//父节点id
	
	
	public String getF_parentid() {
		return f_parentid;
	}
	public void setF_parentid(String f_parentid) {
		this.f_parentid = f_parentid;
	}
	public String getF_mkmc() {
		return f_mkmc;
	}
	public void setF_mkmc(String f_mkmc) {
		this.f_mkmc = f_mkmc;
	}
	public String getF_mkguid() {
		return f_mkguid;
	}
	public void setF_mkguid(String f_mkguid) {
		this.f_mkguid = f_mkguid;
	}
	public String getF_sq() {
		return f_sq;
	}
	public void setF_sq(String f_sq) {
		this.f_sq = f_sq;
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
	public String getF_pgnmc() {
		return f_pgnmc;
	}
	public void setF_pgnmc(String f_pgnmc) {
		this.f_pgnmc = f_pgnmc;
	}
	public String getF_guid() {
		return f_guid;
	}
	public void setF_guid(String f_guid) {
		this.f_guid = f_guid;
	}
	public String getF_gnbh() {
		return f_gnbh;
	}
	public void setF_gnbh(String f_gnbh) {
		this.f_gnbh = f_gnbh;
	}
	public String getF_gnmc() {
		return f_gnmc;
	}
	public void setF_gnmc(String f_gnmc) {
		this.f_gnmc = f_gnmc;
	}
	public String getF_sort() {
		return f_sort;
	}
	public void setF_sort(String f_sort) {
		this.f_sort = f_sort;
	}
	public String getF_js() {
		return f_js;
	}
	public void setF_js(String f_js) {
		this.f_js = f_js;
	}
	public String getF_pgnbh() {
		return f_pgnbh;
	}
	public void setF_pgnbh(String f_pgnbh) {
		this.f_pgnbh = f_pgnbh;
	}
	
	
}
