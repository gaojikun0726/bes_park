package com.efounder.JEnterprise.model.basedatamanage.energyinformation;

import com.core.common.BaseEntity;

import java.io.Serializable;
/**
 * 园区表
 * @author sunhao
 *创建时间  2018-7-3
 */
public class BESPark implements BaseEntity<Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6041705335918578563L;

	private String f_yqbh;//园区编号

	private String f_user_name;//用户名称

	private String f_zzjg_id;//组织机构id

    private String f_yqmc;//园区名称

    private String f_node_code;//节点名称

    private String f_all_area;//总面积

    private String f_monitor_area;//监测面积

    private String f_heat_area;//供暖面积
    
    private String f_person_nums;//总人口

    private String f_build_time;//建筑时间
    
    private String f_equipment_runtime;//设备运行时间

    private String f_contact_name;//联系人
   
    private String f_contact_phone;//联系电话

    private String f_contact_email;//联系人邮箱

    private String  f_crdate;//创建日期

    private String  f_chdate;//修改日期

    private String  f_longitude;//经度

    private String  f_latitude;//纬度

	public String getF_yqbh() {
		return f_yqbh;
	}

	public void setF_yqbh(String f_yqbh) {
		this.f_yqbh = f_yqbh;
	}

	public String getF_node_code() {
		return f_node_code;
	}

	public void setF_node_code(String f_node_code) {
		this.f_node_code = f_node_code;
	}

	public String getF_yqmc() {
		return f_yqmc;
	}

	public void setF_yqmc(String f_yqmc) {
		this.f_yqmc = f_yqmc;
	}

	public String getF_all_area() {
		return f_all_area;
	}

	public void setF_all_area(String f_all_area) {
		this.f_all_area = f_all_area;
	}

	public String getF_monitor_area() {
		return f_monitor_area;
	}

	public void setF_monitor_area(String f_monitor_area) {
		this.f_monitor_area = f_monitor_area;
	}

	public String getF_heat_area() {
		return f_heat_area;
	}

	public void setF_heat_area(String f_heat_area) {
		this.f_heat_area = f_heat_area;
	}

	public String getF_person_nums() {
		return f_person_nums;
	}

	public void setF_person_nums(String f_person_nums) {
		this.f_person_nums = f_person_nums;
	}

	public String getF_build_time() {
		return f_build_time;
	}

	public void setF_build_time(String f_build_time) {
		this.f_build_time = f_build_time;
	}

	public String getF_contact_name() {
		return f_contact_name;
	}

	public void setF_contact_name(String f_contact_name) {
		this.f_contact_name = f_contact_name;
	}

	public String getF_contact_phone() {
		return f_contact_phone;
	}

	public void setF_contact_phone(String f_contact_phone) {
		this.f_contact_phone = f_contact_phone;
	}

	public String getF_contact_email() {
		return f_contact_email;
	}

	public void setF_contact_email(String f_contact_email) {
		this.f_contact_email = f_contact_email;
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

	public String getF_equipment_runtime() {
		return f_equipment_runtime;
	}

	public void setF_equipment_runtime(String f_equipment_runtime) {
		this.f_equipment_runtime = f_equipment_runtime;
	}

	public String getF_longitude() {
		return f_longitude;
	}

	public void setF_longitude(String f_longitude) {
		this.f_longitude = f_longitude;
	}

	public String getF_latitude() {
		return f_latitude;
	}

	public void setF_latitude(String f_latitude) {
		this.f_latitude = f_latitude;
	}

	public String getF_user_name() {
		return f_user_name;
	}

	public void setF_user_name(String f_user_name) {
		this.f_user_name = f_user_name;
	}

	public String getF_zzjg_id() {
		return f_zzjg_id;
	}

	public void setF_zzjg_id(String f_zzjg_id) {
		this.f_zzjg_id = f_zzjg_id;
	}
}