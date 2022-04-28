package com.efounder.JEnterprise.model;

import com.core.common.BaseEntity;

/**
 * 
 * 类名称：BESEquipmentMold 类描述：设备类型对象 创建人：huangxianbo 修改人：huangxianbo
 * 创建时间：2018年6月8日
 * 修改人：tianjiangwei   
 * 修改日期 ：2018/7/3
 * @version 1.0.0
 *
 */

public class BESEquipmentMold implements BaseEntity<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2661190811765771058L;

	private String f_type;// 设备类型

	private String f_sbmc;// 设备名称
	
	private String f_ptype;// 父设备类型
	
	private String f_sbjcxxb;// 设备基础信息表
	
	private String f_sbsjb;// 设备数据表

	private String f_remark;// 备注

	private String f_crdate;// 创建日期

	private String f_chdate;// 修改日期
	
	public String getF_type() {
		return f_type;
	}

	public void setF_type(String f_type) {
		this.f_type = f_type;
	}

	public String getF_sbmc() {
		return f_sbmc;
	}

	public void setF_sbmc(String f_sbmc) {
		this.f_sbmc = f_sbmc;
	}

	public String getF_ptype() {
		return f_ptype;
	}

	public void setF_ptype(String f_ptype) {
		this.f_ptype = f_ptype;
	}
	
	public String getF_sbjcxxb() {
		return f_sbjcxxb;
	}

	public void setF_sbjcxxb(String f_sbjcxxb) {
		this.f_sbjcxxb = f_sbjcxxb;
	}

	public String getF_sbsjb() {
		return f_sbsjb;
	}

	public void setF_sbsjb(String f_sbsjb) {
		this.f_sbsjb = f_sbsjb;
	}

	public String getF_remark() {
		return f_remark;
	}

	public void setF_remark(String f_remark) {
		this.f_remark = f_remark;
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
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}