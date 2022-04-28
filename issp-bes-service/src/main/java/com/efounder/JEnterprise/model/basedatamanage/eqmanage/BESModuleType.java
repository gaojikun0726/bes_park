package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;
/**
 * 模块型号
 * @author LvSihan
 *
 */
public class BESModuleType implements BaseEntity<String>{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5336628803358883097L;
	
	private String fModuleId;//

	private String fModuleType;//模块型号

	private String fTypeCode;//模块型号代码

    private String fDescription;//模块描述

    private Integer fPointAmount;//模块点数

    private String fPointTypeCl;//点集合

    private String fCrdate;//创建日期

    private String fChdate;//修改日期
    
    private String fId;
    
    private String fModulepointType;

	private StringBuffer sb;


	public String getfModulepointType() {
		return fModulepointType;
	}

	public void setfModulepointType(String fModulepointType) {
		this.fModulepointType = fModulepointType;
	}

	public String getfModuleType() {
        return fModuleType;
    }

    public void setfModuleType(String fModuleType) {
        this.fModuleType = fModuleType == null ? null : fModuleType.trim();
    }

    public String getfDescription() {
        return fDescription;
    }

    public void setfDescription(String fDescription) {
        this.fDescription = fDescription == null ? null : fDescription.trim();
    }

    public Integer getfPointAmount() {
        return fPointAmount;
    }

    public void setfPointAmount(Integer fPointAmount) {
        this.fPointAmount = fPointAmount;
    }

    public String getfPointTypeCl() {
        return fPointTypeCl;
    }

    public void setfPointTypeCl(String fPointTypeCl) {
        this.fPointTypeCl = fPointTypeCl == null ? null : fPointTypeCl.trim();
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

	public String getfId() {
		return fId;
	}

	public void setfId(String fId) {
		this.fId = fId;
	}

	public void setLinshi(StringBuffer sb) {
		this.setSb(sb);		
	}

	public StringBuffer getSb() {
		return sb;
	}

	public void setSb(StringBuffer sb) {
		this.sb = sb;
	}

	public String getfModuleId() {
		return fModuleId;
	}

	public void setfModuleId(String fModuleId) {
		this.fModuleId = fModuleId;
	}


	public String getfTypeCode()
	{
		return fTypeCode;
	}

	public void setfTypeCode(String fTypeCode)
	{
		this.fTypeCode = fTypeCode;
	}
}