package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;

import java.io.Serializable;

public class BESProducerManage implements BaseEntity<String> ,Serializable{

	/**
	 * @author 于洁英
	 * @decription 生产商管理
	 */
	private static final long serialVersionUID = 47474754858361L;

	private String fId;//ID
	private String fScsmc;//生产商名称
	private String fScsdz;//生产商地址
	private String fScslxr;//联系人
	private String fScslxfs;//联系方式
	private String fCrdate;//创建日期
	private String fChdate;//修改日期
	public String getfId() {
		return fId;
	}
	public void setfId(String fId) {
		this.fId = fId;
	}
	public String getfScsmc() {
		return fScsmc;
	}
	public void setfScsmc(String fScsmc) {
		this.fScsmc = fScsmc;
	}
	public String getfScsdz() {
		return fScsdz;
	}
	public void setfScsdz(String fScsdz) {
		this.fScsdz = fScsdz;
	}
	public String getfScslxr() {
		return fScslxr;
	}
	public void setfScslxr(String fScslxr) {
		this.fScslxr = fScslxr;
	}
	public String getfScslxfs() {
		return fScslxfs;
	}
	public void setfScslxfs(String fScslxfs) {
		this.fScslxfs = fScslxfs;
	}
	public String getfCrdate() {
		return fCrdate;
	}
	public void setfCrdate(String fCrdate) {
		this.fCrdate = fCrdate;
	}
	public String getfChdate() {
		return fChdate;
	}
	public void setfChdate(String fChdate) {
		this.fChdate = fChdate;
	}
	
	
	
	
}
