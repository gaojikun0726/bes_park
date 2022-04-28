package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.tree.IBaseTree;

import java.io.Serializable;
import java.util.List;

/**
 *
 * 类名称：BESQydy
 * 类描述：设备树结构对象
 * 创建人：huangxianbo
 * 修改人：huangxianbo
 * 修改时间：2018年4月28日
 * 修改备注：
 * @version 1.0.0
 *
 */
public class BESSbPzStruct implements IBaseTree, Serializable {

	private static final long serialVersionUID = 1L;

	private String f_id;//设备树自增id

	private String f_sbid;//设备id（用于和下位机通讯）

	private String f_sys_name;//系统名称

	private String f_sys_name_old;//系统名称

    private String f_nick_name;//别名

    private String f_allpath;//全路径

    private String f_description;//描述

    private String f_type;//节点类型
    private String f_type_name;//节点类型名称

    private int f_issyn;//是否同步

    private String f_psys_name;//父系统名称

    private String f_status;//状态

    private int f_to_opc;//父系统名称

    private int f_opc_wr;//添加到OPC是否可写

    private String f_crdate;//创建日期

    private String f_chdate;//修改日期

    private String f_xh_type;//型号类型

    private String f_init_val;//初始值，用于监控界面

    private String ChannelIndex;//通道索引，用于监控界面

    private String f_node_attribution;//节点所属系统

    private String unit;//工程单位

	private List<BESSbPzStruct> childerBes;

	private String f_txt;

	private String f_energy_type;

	private String f_accuracy; // 精度

	@Override
	public String getPId() {
		return this.f_psys_name;
	}

	@Override
	public String getCId() {
		return this.f_sys_name;
	}

	@Override
	public String getCMc() {
		return this.f_nick_name;
	}

	@Override
	public String getCJs() {
		return null;
	}

	public String getF_id() {
		return f_id;
	}

	public void setF_id(String f_id) {
		this.f_id = f_id;
	}

	public String getF_sbid() {
		return f_sbid;
	}

	public void setF_sbid(String f_sbid) {
		this.f_sbid = f_sbid;
	}

	public String getF_sys_name() {
		return f_sys_name;
	}

	public void setF_sys_name(String f_sys_name) {
		this.f_sys_name = f_sys_name;
	}

	public String getF_sys_name_old() {
		return f_sys_name_old;
	}

	public void setF_sys_name_old(String f_sys_name_old) {
		this.f_sys_name_old = f_sys_name_old;
	}

	public String getF_nick_name() {
		return f_nick_name;
	}

	public void setF_nick_name(String f_nick_name) {
		this.f_nick_name = f_nick_name;
	}

	public String getF_allpath() {
		return f_allpath;
	}

	public void setF_allpath(String f_allpath) {
		this.f_allpath = f_allpath;
	}

	public String getF_description() {
		return f_description;
	}

	public void setF_description(String f_description) {
		this.f_description = f_description;
	}

	public String getF_type() {
		return f_type;
	}

	public void setF_type(String f_type) {
		this.f_type = f_type;
	}

	public String getF_type_name() {
		return f_type_name;
	}

	public void setF_type_name(String f_type_name) {
		this.f_type_name = f_type_name;
	}

	public int getF_issyn() {
		return f_issyn;
	}

	public void setF_issyn(int f_issyn) {
		this.f_issyn = f_issyn;
	}

	public String getF_psys_name() {
		return f_psys_name;
	}

	public void setF_psys_name(String f_psys_name) {
		this.f_psys_name = f_psys_name;
	}

	public String getF_status() {
		return f_status;
	}

	public void setF_status(String f_status) {
		this.f_status = f_status;
	}

	public int getF_to_opc() {
		return f_to_opc;
	}

	public void setF_to_opc(int f_to_opc) {
		this.f_to_opc = f_to_opc;
	}

	public int getF_opc_wr() {
		return f_opc_wr;
	}

	public void setF_opc_wr(int f_opc_wr) {
		this.f_opc_wr = f_opc_wr;
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

	public String getF_xh_type() {
		return f_xh_type;
	}

	public void setF_xh_type(String f_xh_type) {
		this.f_xh_type = f_xh_type;
	}

	public String getF_init_val() {
		return f_init_val;
	}

	public void setF_init_val(String f_init_val) {
		this.f_init_val = f_init_val;
	}

	public String getChannelIndex() {
		return ChannelIndex;
	}

	public void setChannelIndex(String channelIndex) {
		ChannelIndex = channelIndex;
	}

	public String getF_node_attribution() {
		return f_node_attribution;
	}

	public void setF_node_attribution(String f_node_attribution) {
		this.f_node_attribution = f_node_attribution;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public List<BESSbPzStruct> getChilderBes() {
		return childerBes;
	}

	public void setChilderBes(List<BESSbPzStruct> childerBes) {
		this.childerBes = childerBes;
	}

	public String getF_txt() {
		return f_txt;
	}

	public void setF_txt(String f_txt) {
		this.f_txt = f_txt;
	}

	public String getF_energy_type() {
		return f_energy_type;
	}

	public void setF_energy_type(String f_energy_type) {
		this.f_energy_type = f_energy_type;
	}

	public String getF_accuracy() {
		return f_accuracy;
	}

	public void setF_accuracy(String f_accuracy) {
		this.f_accuracy = f_accuracy;
	}
}
