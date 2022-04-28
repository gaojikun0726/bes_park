package com.efounder.JEnterprise.model.basedatamanage.energydataReport;
import com.core.common.BaseEntity;
import com.framework.common.utils.ExcelVOAttribute;

public class BESDatecenterType implements BaseEntity<String>{

	private String  F_DATACENTER_ID;//主键
	@ExcelVOAttribute(name = "数据中心代码", column = "A")
	private String  F_DATACENTER_CODE;//数据中心代码
	@ExcelVOAttribute(name = "数据中心名称", column = "B")
	private String	F_DATACENTER_NAME;//数据中心名称
	@ExcelVOAttribute(name = "数据中心IP地址", column = "C")
    private String  F_DATACENTER_IP;//数据中心IP地址
	@ExcelVOAttribute(name = "数据中心端口号", column = "D")
	private String  F_DATACENTER_PORT;//数据中心端口号
	private String  F_DATACENTER_TYPE;//数据中心类型
	@ExcelVOAttribute(name = "数据类型名称", column = "E", combo = { "数据中心", "数据中转站"})
	private String  LXMC;//类型名称
	@ExcelVOAttribute(name = "主管单位", column = "F")
	private String  F_GOVERNING_BODY;//主管单位
	@ExcelVOAttribute(name = "描述", column = "G")
	private String  F_DATACENTER_DESCRIBE;//描述
	@ExcelVOAttribute(name = "联系人", column = "H")
	private String  F_CONTACTPERSON;//联系人
	@ExcelVOAttribute(name = "联系电话", column = "I")
	private String  F_PHONE;//联系电话
	@ExcelVOAttribute(name = "创建时间", column = "J")
	private String  F_CRDATE;//创建时间
	@ExcelVOAttribute(name = "建筑群名称", column = "K")
	private String  F_BUILDGROUPNAME;//建筑群名称
	@ExcelVOAttribute(name = "建筑群别名", column = "L")
	private String  F_GROUPALIASNAME;//建筑群别名
	@ExcelVOAttribute(name = "建筑群描述", column = "M")
	private String  F_GROUPDESC;//建筑群描述

	
	public String getF_DATACENTER_ID() {
		return F_DATACENTER_ID;
	}
	public void setF_DATACENTER_ID(String f_DATACENTER_ID) {
		F_DATACENTER_ID = f_DATACENTER_ID;
	}
	public String getF_DATACENTER_CODE() {
		return F_DATACENTER_CODE;
	}
	public void setF_DATACENTER_CODE(String f_DATACENTER_CODE) {
		F_DATACENTER_CODE = f_DATACENTER_CODE;
	}
	public String getF_DATACENTER_NAME() {
		return F_DATACENTER_NAME;
	}
	public void setF_DATACENTER_NAME(String f_DATACENTER_NAME) {
		F_DATACENTER_NAME = f_DATACENTER_NAME;
	}
	public String getF_DATACENTER_IP() {
		return F_DATACENTER_IP;
	}
	public void setF_DATACENTER_IP(String f_DATACENTER_IP) {
		F_DATACENTER_IP = f_DATACENTER_IP;
	}
	public String getF_DATACENTER_PORT() {
		return F_DATACENTER_PORT;
	}
	public void setF_DATACENTER_PORT(String f_DATACENTER_PORT) {
		F_DATACENTER_PORT = f_DATACENTER_PORT;
	}
	public String getF_DATACENTER_TYPE() {
		return F_DATACENTER_TYPE;
	}
	public void setF_DATACENTER_TYPE(String f_DATACENTER_TYPE) {
		F_DATACENTER_TYPE = f_DATACENTER_TYPE;
	}
	public String getF_GOVERNING_BODY() {
		return F_GOVERNING_BODY;
	}
	public void setF_GOVERNING_BODY(String f_GOVERNING_BODY) {
		F_GOVERNING_BODY = f_GOVERNING_BODY;
	}
	public String getF_DATACENTER_DESCRIBE() {
		return F_DATACENTER_DESCRIBE;
	}
	public void setF_DATACENTER_DESCRIBE(String f_DATACENTER_DESCRIBE) {
		F_DATACENTER_DESCRIBE = f_DATACENTER_DESCRIBE;
	}
	public String getF_CONTACTPERSON() {
		return F_CONTACTPERSON;
	}
	public void setF_CONTACTPERSON(String f_CONTACTPERSON) {
		F_CONTACTPERSON = f_CONTACTPERSON;
	}
	public String getF_PHONE() {
		return F_PHONE;
	}
	public void setF_PHONE(String f_PHONE) {
		F_PHONE = f_PHONE;
	}
	public String getLXMC() {
		return LXMC;
	}
	public void setLXMC(String lXMC) {
		LXMC = lXMC;
	}
	public String getF_CRDATE()
	{
		return F_CRDATE;
	}

	public void setF_CRDATE(String f_CRDATE)
	{
		F_CRDATE = f_CRDATE;
	}

	public String getF_BUILDGROUPNAME() {
		return F_BUILDGROUPNAME;
	}

	public void setF_BUILDGROUPNAME(String f_BUILDGROUPNAME) {
		F_BUILDGROUPNAME = f_BUILDGROUPNAME;
	}

	public String getF_GROUPALIASNAME() {
		return F_GROUPALIASNAME;
	}

	public void setF_GROUPALIASNAME(String f_GROUPALIASNAME) {
		F_GROUPALIASNAME = f_GROUPALIASNAME;
	}

	public String getF_GROUPDESC() {
		return F_GROUPDESC;
	}

	public void setF_GROUPDESC(String f_GROUPDESC) {
		F_GROUPDESC = f_GROUPDESC;
	}

}