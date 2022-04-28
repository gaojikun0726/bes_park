package com.efounder.JEnterprise.model.systemcenter.logmanage;

import com.core.common.BaseEntity;

import java.io.Serializable;

/**
 * 系统操作日志详细信息
 * @author liuhoujun
 * @date 2018/11/1
 *
 */
public class ESSysOperateLogContent implements BaseEntity<Serializable> {
	private static final long serialVersionUID = 6046200861724248804L;
	
	private String fId; //ID
	private String fLogId;//操作日志ID
	private String fTbKey;//表字段
	private String fTbValue;//表字段值
	private String fCurrenttbValue;//表字段当前值
	private String fComment;//注释
	private String fCrdate;//创建时间
	private String fChdate;//修改时间
	public String getfId() {
		return fId;
	}
	public void setfId(String fId) {
		this.fId = fId;
	}
	public String getfLogId() {
		return fLogId;
	}
	public void setfLogId(String fLogId) {
		this.fLogId = fLogId;
	}
	public String getfTbKey() {
		return fTbKey;
	}
	public void setfTbKey(String fTbKey) {
		this.fTbKey = fTbKey;
	}
	public String getfTbValue() {
		return fTbValue;
	}
	public void setfTbValue(String fTbValue) {
		this.fTbValue = fTbValue;
	}
	public String getfCurrenttbValue() {
		return fCurrenttbValue;
	}
	public void setfCurrenttbValue(String fCurrenttbValue) {
		this.fCurrenttbValue = fCurrenttbValue;
	}
	public String getfComment() {
		return fComment;
	}
	public void setfComment(String fComment) {
		this.fComment = fComment;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
