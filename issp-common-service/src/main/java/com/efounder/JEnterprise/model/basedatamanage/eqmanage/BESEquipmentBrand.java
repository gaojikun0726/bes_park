package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;

import java.io.Serializable;
/**
 * 设备品牌
 * @author LvSihan
 *
 */
public class BESEquipmentBrand implements BaseEntity<Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6041705335918578813L;

	private String id;//ID

    private String f_brandbh;//品牌编号

    private String f_brandmc;//品牌名称

    private String f_scsid;//生产商

    private String f_sblx;//设备类型

    private String  f_crdate;//创建日期

    private String  f_chdate;//修改日期
    private String  f_sbmc;//设备类型名称


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getF_brandbh() {
		return f_brandbh;
	}

	public void setF_brandbh(String f_brandbh) {
		this.f_brandbh = f_brandbh;
	}

	public String getF_brandmc() {
		return f_brandmc;
	}

	public void setF_brandmc(String f_brandmc) {
		this.f_brandmc = f_brandmc;
	}

	public String getF_scsid() {
		return f_scsid;
	}

	public void setF_scsid(String f_scsid) {
		this.f_scsid = f_scsid;
	}

	public String getF_sblx() {
		return f_sblx;
	}

	public void setF_sblx(String f_sblx) {
		this.f_sblx = f_sblx;
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

	public String getF_sbmc() {
		return f_sbmc;
	}

	public void setF_sbmc(String f_sbmc) {
		this.f_sbmc = f_sbmc;
	}


}