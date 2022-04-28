package com.efounder.JEnterprise.model.BesWorkbench;

public class BESWorkbenchCrossType {
	
	private String f_id;                //主键
	private String f_gztzzjg_id;        //工作台组织机构id 关联id
	private String f_name;              //cross 工作台展示name
	private String f_type;             //类型
	private String f_description;       //描述
	private String F_CRDATE;          //添加时间
	private String F_CHDATE;          //修改时间
	
	
	public String getF_id() {
		return f_id;
	}
	public void setF_id(String f_id) {
		this.f_id = f_id;
	}
	public String getF_gztzzjg_id() {
		return f_gztzzjg_id;
	}
	public void setF_gztzzjg_id(String f_gztzzjg_id) {
		this.f_gztzzjg_id = f_gztzzjg_id;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public String getF_type() {
		return f_type;
	}
	public void setF_type(String f_type) {
		this.f_type = f_type;
	}
	public String getF_description() {
		return f_description;
	}
	public void setF_description(String f_description) {
		this.f_description = f_description;
	}
	public String getF_CRDATE() {
		return F_CRDATE;
	}
	public void setF_CRDATE(String f_CRDATE) {
		F_CRDATE = f_CRDATE;
	}
	public String getF_CHDATE() {
		return F_CHDATE;
	}
	public void setF_CHDATE(String f_CHDATE) {
		F_CHDATE = f_CHDATE;
	}
	
}
