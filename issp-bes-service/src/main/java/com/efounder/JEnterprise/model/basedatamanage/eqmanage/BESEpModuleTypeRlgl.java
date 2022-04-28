package com.efounder.JEnterprise.model.basedatamanage.eqmanage;


import com.core.common.BaseEntity;

/**
 * 
 * 类名称：BESEpModuleTypeRlgl 类描述：设备树节点和模块点类型关系对象 创建人：huangxianbo 创建时间：2018年6月12日 
 * 
 * @version 1.0.0
 *
 */

public class BESEpModuleTypeRlgl implements BaseEntity<String>{


	/**
	 * 
	 */
	private static final long serialVersionUID = 5450535293259868717L;

	private String fEpTreenodeType;// 设备树节点类型

	private String fModulepointId;// 模块点类型
	
	private String fCrdate;// 创建日期

	private String fChdate;// 修改日期

	public String getfEpTreenodeType() {
		return fEpTreenodeType;
	}

	public void setfEpTreenodeType(String fEpTreenodeType) {
		this.fEpTreenodeType = fEpTreenodeType;
	}

	public String getfModulepointId() {
		return fModulepointId;
	}

	public void setfModulepointId(String fModulepointId) {
		this.fModulepointId = fModulepointId;
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

	
}