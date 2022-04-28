package com.efounder.JEnterprise.model.basedatamanage.energyinformation;

import com.core.common.BaseEntity;

import java.io.Serializable;

/**
 * @description 园区能耗类型
 * @author yujieying
 * @datetime 2018年7月20日
 */
public class BESPark_EnergyType implements BaseEntity<Serializable>{

	private static final long serialVersionUID = 1213123L;

	
	private String f_yqbh;// 园区编号
	private String f_nybh;// 能源编号
	private String f_crdate;// 创建日期
	private String f_chdate;// 修改日期
	public String getF_yqbh() {
		return f_yqbh;
	}
	public void setF_yqbh(String f_yqbh) {
		this.f_yqbh = f_yqbh;
	}
	public String getF_nybh() {
		return f_nybh;
	}
	public void setF_nybh(String f_nybh) {
		this.f_nybh = f_nybh;
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
