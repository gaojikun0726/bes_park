package com.efounder.JEnterprise.model.basedatamanage.energyinformation;

import com.alibaba.fastjson.JSONArray;
import com.core.common.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 园区表
 * @author sunhao
 *创建时间  2018-7-3
 */
public class BESStrategy implements BaseEntity<Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6041705335918578563L;

	private String f_id;//园区编号

	private String f_name; //策略名称

	private String f_type; //场景信息类型0:根 1:策略

	private String f_pId;  //父节点

	private String f_cron;  //cron表达式

	private String f_status;  //状态(0未启用,1启用)

	private String f_range;  //报表时间

	private String f_email;  //邮箱地址

	private String f_user_name;//用户名称

	private String f_user_code;//用户id

	private String f_job_id; //定时任务id

	private Integer f_cron_job_type;

	private String f_cron_start_date;


	public String getF_id() {
		return f_id;
	}

	public void setF_id(String f_id) {
		this.f_id = f_id;
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

	public String getF_pId() {
		return f_pId;
	}

	public void setF_pId(String f_pId) {
		this.f_pId = f_pId;
	}

	public String getF_cron() {
		return f_cron;
	}

	public void setF_cron(String f_cron) {
		this.f_cron = f_cron;
	}

	public String getF_status() {
		return f_status;
	}

	public void setF_status(String f_status) {
		this.f_status = f_status;
	}

	public String getF_range() {
		return f_range;
	}

	public void setF_range(String f_range) {
		this.f_range = f_range;
	}

	public String getF_email() {
		return f_email;
	}

	public void setF_email(String f_email) {
		this.f_email = f_email;
	}

	public String getF_job_id() {
		return f_job_id;
	}

	public void setF_job_id(String f_job_id) {
		this.f_job_id = f_job_id;
	}

	public String getF_user_name() {
		return f_user_name;
	}

	public void setF_user_name(String f_user_name) {
		this.f_user_name = f_user_name;
	}

	public String getF_user_code() {
		return f_user_code;
	}

	public void setF_user_code(String f_user_code) {
		this.f_user_code = f_user_code;
	}

	public Integer getF_cron_job_type() {
		return f_cron_job_type;
	}

	public void setF_cron_job_type(Integer f_cron_job_type) {
		this.f_cron_job_type = f_cron_job_type;
	}

	public String getF_cron_start_date() {
		return f_cron_start_date;
	}

	public void setF_cron_start_date(String f_cron_start_date) {
		this.f_cron_start_date = f_cron_start_date;
	}
}