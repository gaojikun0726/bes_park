package com.efounder.JEnterprise.model.systemcenter.logmanage;

import com.core.common.BaseEntity;

import java.io.Serializable;
/**
 * 系统操作日志
 * @author liuhoujun
 * @date 2018/10/30
 *
 */
public class ESSysOperateLog implements BaseEntity<Serializable>{
	
	private static final long serialVersionUID = 1430995344563456879L;
	
	private String f_id; //日志ID
	private String f_yhbh;//用户编号
	private String f_type;//操作类型 0：增加 1：删除 2 修改 3：查询
	private String f_table_name;//数据表名
	private String f_table_id;//表主键
	private String f_comment;//表注释
	private String f_operation_time;//操作时间
	private String f_crdate;//创建时间
	private String f_chdate;//修改时间
	
	private String f_name;//这是作为临时数据来用
	
	public String getF_id() {
		return f_id;
	}
	public void setF_id(String f_id) {
		this.f_id = f_id;
	}
	public String getF_yhbh() {
		return f_yhbh;
	}
	public void setF_yhbh(String f_yhbh) {
		this.f_yhbh = f_yhbh;
	}
	public String getF_type() {
		return f_type;
	}
	public void setF_type(String f_type) {
		this.f_type = f_type;
	}
	public String getF_table_name() {
		return f_table_name;
	}
	public void setF_table_name(String f_table_name) {
		this.f_table_name = f_table_name;
	}
	public String getF_table_id() {
		return f_table_id;
	}
	public void setF_table_id(String f_table_id) {
		this.f_table_id = f_table_id;
	}
	public String getF_comment() {
		return f_comment;
	}
	public void setF_comment(String f_comment) {
		this.f_comment = f_comment;
	}
	public String getF_operation_time() {
		return f_operation_time;
	}
	public void setF_operation_time(String f_operation_time) {
		this.f_operation_time = f_operation_time;
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
	
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	
	
}
