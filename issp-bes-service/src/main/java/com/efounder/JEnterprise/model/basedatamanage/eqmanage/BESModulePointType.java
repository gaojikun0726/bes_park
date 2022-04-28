package com.efounder.JEnterprise.model.basedatamanage.eqmanage;


import com.core.common.BaseEntity;
import com.framework.common.utils.ExcelVOAttribute;

/**
 * 
 * 类名称：BESModulePointType 类描述：模块点类型对象 创建人：huangxianbo 创建时间：2018年6月12日 
 * 
 * @version 1.0.0
 *
 */

public class BESModulePointType implements BaseEntity<String>{

	private static final long serialVersionUID = -457180312251681810L;

	// ID
	private String fId;

	// 点类型
	@ExcelVOAttribute(name = "模块点类型",column = "A",prompt = "必填项")
	private String fModulepointType;

	// 描述
	@ExcelVOAttribute(name="模块点描述",column = "B",prompt = "必填项")
	private String fDescription;

	// 创建日期
	private String fCrdate;

	// 修改日期
	private String fChdate;

	public String getfId() {
		return fId;
	}

	public void setfId(String fId) {
		this.fId = fId;
	}

	public String getfModulepointType() {
		return fModulepointType;
	}

	public void setfModulepointType(String fModulepointType) {
		this.fModulepointType = fModulepointType;
	}

	public String getfDescription() {
		return fDescription;
	}

	public void setfDescription(String fDescription) {
		this.fDescription = fDescription;
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

	@Override
	public String toString() {
		return "[fModulepointType='" + fModulepointType +"', fDescription='" + fDescription + "']";
	}

}