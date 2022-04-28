package com.efounder.JEnterprise.model.systemcenter.logmanage;

import com.core.common.BaseEntity;

import java.io.Serializable;

/**
 * 系统权限日志
 * @author liuhoujun
 * @date 2018/11/06
 *
 */
public class ESSysQxOperateLog implements BaseEntity<Serializable>{
	private static final long serialVersionUID = -4759069996208716114L;
	
	private String id ;//ID
	private String fOptype;//'操作类型 0：查看 1:新增 2：修改 3：删除\r\n 11:功能权限分配 12功能权限审核\r\n 13功能权限取消分配',
	private String fOptype1;//'操作类型 0：查看 1:新增 2：修改 3：删除\r\n 11:功能权限分配 12功能权限审核\r\n 13功能权限取消分配',
	private String fOpcontent;//'操作内容'
	private String fOpname;//'操作人'
	private String fOptime;//操作时间
	private String fCrdate;//创建时间
	private String fChdate;//修改时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getfOptype() {
		return fOptype;
	}
	public void setfOptype(String fOptype) {
		this.fOptype = fOptype;
	}
	public String getfOpcontent() {
		return fOpcontent;
	}
	public void setfOpcontent(String fOpcontent) {
		this.fOpcontent = fOpcontent;
	}
	
	public String getfOptype1() {
		return fOptype1;
	}
	public void setfOptype1(String fOptype1) {
		this.fOptype1 = fOptype1;
	}
	public String getfOpname() {
		return fOpname;
	}
	public void setfOpname(String fOpname) {
		this.fOpname = fOpname;
	}
	public String getfOptime() {
		return fOptime;
	}
	public void setfOptime(String fOptime) {
		this.fOptime = fOptime;
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
