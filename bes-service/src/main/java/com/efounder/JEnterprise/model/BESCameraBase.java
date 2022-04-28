package com.efounder.JEnterprise.model;

import com.core.common.BaseEntity;

/**
 * @description 线路
 * @author menghaixiao
 *
 */
public class BESCameraBase implements BaseEntity<BESCameraBase> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 10154412045120210L;
	
	private String f_id;//线路guid
	private String f_xlbh;//线路编号
	private String f_xlmc;//线路名称
	private String f_qdmc;//起点名称
	private String f_qdzh;//起点桩号
	private String f_zdmc;//终点名称
	private String f_zdzh;//终点桩号
	private String f_crdate;//创建时间
	private String f_chdate;//更新时间
	public String getF_id() {
		return f_id;
	}
	public void setF_id(String f_id) {
		this.f_id = f_id;
	}
	public String getF_xlbh() {
		return f_xlbh;
	}
	public void setF_xlbh(String f_xlbh) {
		this.f_xlbh = f_xlbh;
	}
	public String getF_xlmc() {
		return f_xlmc;
	}
	public void setF_xlmc(String f_xlmc) {
		this.f_xlmc = f_xlmc;
	}
	public String getF_qdmc() {
		return f_qdmc;
	}
	public void setF_qdmc(String f_qdmc) {
		this.f_qdmc = f_qdmc;
	}
	public String getF_qdzh() {
		return f_qdzh;
	}
	public void setF_qdzh(String f_qdzh) {
		this.f_qdzh = f_qdzh;
	}
	public String getF_zdmc() {
		return f_zdmc;
	}
	public void setF_zdmc(String f_zdmc) {
		this.f_zdmc = f_zdmc;
	}
	public String getF_zdzh() {
		return f_zdzh;
	}
	public void setF_zdzh(String f_zdzh) {
		this.f_zdzh = f_zdzh;
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
