package com.efounder.JEnterprise.model.applicationmanage;

import java.io.Serializable;

/**
 * @Description 功能菜单定义
 * @author pql
 * @date 2018年2月9日
 *
 */
public class ESBmjg implements Serializable {
	private static final long serialVersionUID = 3624947930970250778L;
	/**
	 * 表名
	 */
	private String tableName;
	/**
	 * 表编码结构
	 */
	private String bmjg;
	/**
	 * 创建日期
	 */
	private String crdate;
	/**
	 * 修改日期
	 */
	private String chdate;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getBmjg() {
		return bmjg;
	}

	public void setBmjg(String bmjg) {
		this.bmjg = bmjg;
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

}
