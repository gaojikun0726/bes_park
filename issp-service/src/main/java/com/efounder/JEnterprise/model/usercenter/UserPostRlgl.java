package com.efounder.JEnterprise.model.usercenter;

import com.core.common.BaseEntity;

import java.io.Serializable;

/**
 * 
 * 类名称：UserPostRlgl
 * 类描述：岗位和用户关系
 * 创建人：gongfanfei
 * 修改人：gongfanfei
 * 修改时间：2018年5月4日 
 * 修改备注：
 * @version 1.0.0 
 *
 */
public class UserPostRlgl implements BaseEntity<Serializable>{
	 private static final long serialVersionUID = 1616160649813303L;
	 
	 
	 private String f_gwguid;//岗位GUIID编号
	 private String f_yhbh;//用户编号
	 private String f_crdate;// 创建日期
	 private String f_chdate;// 修改日期

	 
	public String getF_gwguid() {
		return f_gwguid;
	}
	public void setF_gwguid(String f_gwguid) {
		this.f_gwguid = f_gwguid;
	}
	public String getF_yhbh() {
		return f_yhbh;
	}
	public void setF_yhbh(String f_yhbh) {
		this.f_yhbh = f_yhbh;
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
