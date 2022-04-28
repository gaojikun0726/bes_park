package com.efounder.JEnterprise.model.applicationmanage;

import com.core.common.BaseEntity;

import java.io.Serializable;

/**
 * @Description 数据权限字典维护
 * @author gongfanfei
 * @date 2018年5月8日
 *
 */
public class ESXtList implements BaseEntity<Serializable>{
	private static final long serialVersionUID = 36249479309712778L;
	
	private String f_xtbh;//系统编号
	private String f_xtmc;//系统名称
	private String f_remark;//备注
	private String f_crdate;//创建日期
	private String f_chdate;//修改日期
	public String getF_xtbh() {
		return f_xtbh;
	}
	public void setF_xtbh(String f_xtbh) {
		this.f_xtbh = f_xtbh;
	}
	public String getF_xtmc() {
		return f_xtmc;
	}
	public void setF_xtmc(String f_xtmc) {
		this.f_xtmc = f_xtmc;
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

}
