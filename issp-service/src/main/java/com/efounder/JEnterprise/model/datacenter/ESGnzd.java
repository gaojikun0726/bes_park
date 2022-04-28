package com.efounder.JEnterprise.model.datacenter;

import com.core.common.tree.IBaseTree;

import java.io.Serializable;

/**
 * @Description  功能菜单定义
 * @author pql
 * @date 2018年2月2日
 *
 */
public class ESGnzd  implements IBaseTree,Serializable{
	private static final long serialVersionUID = 3624947930970250778L;
	/**
	 * GUID
	 */
	private String guid;
	/**
	 * 功能菜单编号
	 */
	private String gnbh;
	/**
	 * 功能菜单名称
	 */
	private String gnmc;
	/**
	 * 菜单样式图标名
	 */
	private String cssClass;
	/**
	 * 菜单url
	 */
	private String url;
	/**
	 * 是否隐藏：1=有效，0=无效
	 */
	private String hide;
	/**
	 * 模块编号
	 */
	private String mkbh;
	/**
	 * 排序
	 */
	private String sort;
	/**
	 * 级数
	 */
	private String js;
	/**
	 * 明细
	 */
	private String mx;
	/**
	 * 父功能菜单编号
	 */
	private String pgnbh;
	/**
	 * 0:带导航区域全屏;1:只内容区域全屏
	 */
	private String fullScreenType;
	/**
	 * 0:DIV;1:newpage
	 */
	private String openType;
	/**
	 * tab页是否可关闭
	 */
	private String tabcloseable;
	/**
	 * 菜单参数列
	 */
	private String params;
	/**
	 * 创建日期
	 */
	private String crdate;
	/**
	 * 修改日期
	 */
	private String chdate;
	
	public String getGnbh() {
		return gnbh;
	}
	public void setGnbh(String gnbh) {
		this.gnbh = gnbh;
	}
	public String getGnmc() {
		return gnmc;
	}
	public void setGnmc(String gnmc) {
		this.gnmc = gnmc;
	}
	
	public String getCssClass() {
		return cssClass;
	}
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMkbh() {
		return mkbh;
	}
	public void setMkbh(String mkbh) {
		this.mkbh = mkbh;
	}
	
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getJs() {
		return js;
	}
	public void setJs(String js) {
		this.js = js;
	}
	public String getMx() {
		return mx;
	}
	public void setMx(String mx) {
		this.mx = mx;
	}
	public String getPgnbh() {
		return pgnbh;
	}
	public void setPgnbh(String pgnbh) {
		this.pgnbh = pgnbh;
	}

	public String getCrdate() {
		return crdate;
	}
	public void setCrdate(String crdate) {
		this.crdate = crdate;
	}
	public String getChdate() {
		return chdate;
	}
	public void setChdate(String chdate) {
		this.chdate = chdate;
	}
	public String getHide() {
		return hide;
	}
	public void setHide(String hide) {
		this.hide = hide;
	}
	public String getFullScreenType() {
		return fullScreenType;
	}
	public void setFullScreenType(String fullScreenType) {
		this.fullScreenType = fullScreenType;
	}
	public String getOpenType() {
		return openType;
	}
	public void setOpenType(String openType) {
		this.openType = openType;
	}
	
	public String getTabcloseable() {
		return tabcloseable;
	}
	public void setTabcloseable(String tabcloseable) {
		this.tabcloseable = tabcloseable;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	@Override
	public String getPId() {
		// TODO Auto-generated method stub
		return this.pgnbh;
	}
	@Override
	public String getCId() {
		// TODO Auto-generated method stub
		return  this.gnbh;
	}
	@Override
	public String getCMc() {
		// TODO Auto-generated method stub
		return this.gnmc;
	}
	@Override
	public String getCJs() {
		// TODO Auto-generated method stub
		return this.js;
	}
}
