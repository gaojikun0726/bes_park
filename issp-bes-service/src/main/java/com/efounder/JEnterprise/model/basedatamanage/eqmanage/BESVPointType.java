package com.efounder.JEnterprise.model.basedatamanage.eqmanage;


import com.core.common.BaseEntity;

/**
 * 
 * 类名称：BESVPointType 类描述：虚点类型对象 创建人：huangxianbo 创建时间：2018年6月15日 
 * 
 * @version 1.0.0
 *
 */

public class BESVPointType implements BaseEntity<String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4397732942195904732L;

	private String id;// ID

	private String fVpointType;// 虚点类型
	
	private String fIsInput;// 是否属于输入
	
	private String fDescription;// 描述
	
	private String fCrdate;// 创建日期

	private String fChdate;// 修改日期

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getfVpointType() {
		return fVpointType;
	}

	public void setfVpointType(String fVpointType) {
		this.fVpointType = fVpointType;
	}

	public String getfIsInput() {
		return fIsInput;
	}

	public void setfIsInput(String fIsInput) {
		this.fIsInput = fIsInput;
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

}