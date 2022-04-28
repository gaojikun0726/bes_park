package com.efounder.JEnterprise.model.applicationmanage;

import com.core.common.BaseEntity;


public class ESHomeModule implements BaseEntity{
	
	//private static final long serialVersionUID = 35654970250778L;
	
	private String guid;//
	private String f_mkbh;//系统模块编号
	private String f_mkmc;//系统模块名称
	private String f_css_class;//系统图标
	private String f_xtbh;//系统编号 fk
	private String f_showleftmenus;//是否显示左侧菜单；1：显示；0：不显示
	private String f_crdate;//创建日期
	private String f_chdate;//修改日期
	private String f_sort;
	
	public String getF_sort() {
		return f_sort;
	}
	public void setF_sort(String f_sort) {
		this.f_sort = f_sort;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getF_mkbh() {
		return f_mkbh;
	}
	public void setF_mkbh(String f_mkbh) {
		this.f_mkbh = f_mkbh;
	}
	public String getF_mkmc() {
		return f_mkmc;
	}
	public void setF_mkmc(String f_mkmc) {
		this.f_mkmc = f_mkmc;
	}
	public String getF_css_class() {
		return f_css_class;
	}
	public void setF_css_class(String f_css_class) {
		this.f_css_class = f_css_class;
	}
	public String getF_xtbh() {
		return f_xtbh;
	}
	public void setF_xtbh(String f_xtbh) {
		this.f_xtbh = f_xtbh;
	}
	public String getF_showleftmenus() {
		return f_showleftmenus;
	}
	public void setF_showleftmenus(String f_showleftmenus) {
		this.f_showleftmenus = f_showleftmenus;
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
