package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;
import com.core.common.tree.IBaseTree;

import java.io.Serializable;

/**
 * 
 * 类名称：BESEquipmentType 类描述：设备型号对象 创建人：lvsihan 修改人：huangxianbo 修改时间：2018年6月7日 修改备注：表结构发生变化，增加字段
 * 
 * @version 1.0.0
 *
 */

public class BESEquipmentType implements BaseEntity<String>, IBaseTree, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2446397424367254660L;

	private String f_type;// 设备型号

	private String f_sbmc;// 设备名称

	private String f_sblxbh;// 设备类型编号
	
	private String f_brandid;// 品牌
	
	private String f_remark;// 备注
	
	private String f_crdate;// 创建日期

	private String f_chdate;// 修改日期
	
	private String f_sblxmc;// 设备类型名称

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

	public String getF_sblxbh() {
		return f_sblxbh;
	}

	public void setF_sblxbh(String f_sblxbh) {
		this.f_sblxbh = f_sblxbh;
	}

	public String getF_brandid() {
		return f_brandid;
	}

	public void setF_brandid(String f_brandid) {
		this.f_brandid = f_brandid;
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

	@Override
	public String getPId() {
		return this.f_sblxbh;
	}

	@Override
	public String getCId() {
		return this.f_type;
	}

	@Override
	public String getCMc() {
		return this.f_sbmc;
	}

	@Override
	public String getCJs() {
		return null;
	}

	public String getF_sblxmc() {
		return f_sblxmc;
	}

	public void setF_sblxmc(String f_sblxmc) {
		this.f_sblxmc = f_sblxmc;
	}

}