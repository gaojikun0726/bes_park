package com.core.common.security;

import java.io.Serializable;

public class LimitParamObject implements Serializable {

	private static final long serialVersionUID = 1L;

	// 权限编号
	private String qxbh;
	// 权限列
	private String pscol;
	// 用户编号
	private String yhbh;
	// 标志位
	private String pibzw;

	public String getQxbh() {
		return qxbh;
	}

	public void setQxbh(String qxbh) {
		this.qxbh = qxbh;
	}

	public String getPscol() {
		return pscol;
	}

	public void setPscol(String pscol) {
		this.pscol = pscol;
	}

	public String getYhbh() {
		return yhbh;
	}

	public void setYhbh(String yhbh) {
		this.yhbh = yhbh;
	}

	public String getPibzw() {
		return pibzw;
	}

	public void setPibzw(String pibzw) {
		this.pibzw = pibzw;
	}

}