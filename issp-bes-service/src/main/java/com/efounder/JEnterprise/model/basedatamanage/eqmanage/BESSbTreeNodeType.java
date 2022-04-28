package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;
import com.framework.common.utils.ExcelVOAttribute;

/**
 * 
 * 类名称：BESSbTreeNoteType
 * 类描述：设备树节点类型对象
 * 创建人：huangxianbo
 * 修改人：huangxianbo
 * 修改时间：2018年5月2日 
 * 修改备注：
 * @version 1.0.0 
 *
 */

public class BESSbTreeNodeType implements BaseEntity<String>{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -1039810717659922908L;

	@ExcelVOAttribute(name = "节点类型",column = "A")
	private String f_node_type;//节点类型

	@ExcelVOAttribute(name = "节点名称",column = "B")
	private String f_node_name;//节点名称

	@ExcelVOAttribute(name = "功能名称(以,隔开)",column = "C")
	private String f_fun_mcs;//节点功能名称

	@ExcelVOAttribute(name = "新增节点类型(以,隔开)",column = "D")
	private String f_fun_nodetype;//新增节点类型

	@ExcelVOAttribute(name = "维护url",column = "E")
	private String f_edit_url;//维护url

	@ExcelVOAttribute(name = "节点数量上限",column = "F")
	private String f_max_count;//节点数量上限

	@ExcelVOAttribute(name = "实体表名",column = "G")
	private String f_node_table;//实体表名

	@ExcelVOAttribute(name = "在线图片路径",column = "H")
	private String f_online_img;//在线图片路径

	@ExcelVOAttribute(name = "离线图片路径",column = "I")
	private String f_offline_img;//离线图片路径

	@ExcelVOAttribute(name = "备注",column = "J")
	private String f_remark;//备注
	
    private String f_crdate;//创建日期
    
    private String f_chdate;//修改日期

	public String getF_node_type() {
		return f_node_type;
	}

	public void setF_node_type(String f_node_type) {
		this.f_node_type = f_node_type;
	}

	public String getF_node_name() {
		return f_node_name;
	}

	public void setF_node_name(String f_node_name) {
		this.f_node_name = f_node_name;
	}

	public String getF_fun_mcs() {
		return f_fun_mcs;
	}

	public void setF_fun_mcs(String f_fun_mcs) {
		this.f_fun_mcs = f_fun_mcs;
	}

	public String getF_edit_url() {
		return f_edit_url;
	}

	public void setF_edit_url(String f_edit_url) {
		this.f_edit_url = f_edit_url;
	}

	public String getF_max_count() {
		return f_max_count;
	}

	public void setF_max_count(String f_max_count) {
		this.f_max_count = f_max_count;
	}

	public String getF_node_table() {
		return f_node_table;
	}

	public void setF_node_table(String f_node_table) {
		this.f_node_table = f_node_table;
	}
	
	public String getF_fun_nodetype() {
		return f_fun_nodetype;
	}

	public void setF_fun_nodetype(String f_fun_nodetype) {
		this.f_fun_nodetype = f_fun_nodetype;
	}

	public String getF_online_img() {
		return f_online_img;
	}

	public void setF_online_img(String f_online_img) {
		this.f_online_img = f_online_img;
	}

	public String getF_offline_img() {
		return f_offline_img;
	}

	public void setF_offline_img(String f_offline_img) {
		this.f_offline_img = f_offline_img;
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