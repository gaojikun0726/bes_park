package com.efounder.JEnterprise.model.auth;

import com.core.common.BaseEntity;

/**
 * 
 * 类名称：PermissionModule
 * 类描述：模块对象
 * 创建人：wujf
 * 修改人：wujf
 * 修改时间：2018年1月2日 下午6:46:58
 * 修改备注：
 * @version 1.0.0 
 *
 */
public class PermissionModule implements BaseEntity<String> {

    private static final long serialVersionUID = -7141829387338999544L;

    private String guid;

    public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	/** 模块编号 **/
    private String mkbh;
    
    public String getMkbh() {
		return mkbh;
	}

	public void setMkbh(String mkbh) {
		this.mkbh = mkbh;
	}

	/** 模块名称 **/
    private String mkmc;

    public String getMkmc() {
		return mkmc;
	}

	public void setMkmc(String mkmc) {
		this.mkmc = mkmc;
	}

	/** 模块样式图标名称 **/
    private String cssClass;

    public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	/** 所属系统 **/
    private String xtbh;

	public String getXtbh() {
		return xtbh;
	}

	public void setXtbh(String xtbh) {
		this.xtbh = xtbh;
	}
    
	/**
	 * 是否显示左侧菜单
	 */
	private String showLeftMenus;

	public String getShowLeftMenus() {
		return showLeftMenus;
	}

	public void setShowLeftMenus(String showLeftMenus) {
		this.showLeftMenus = showLeftMenus;
	}
}
