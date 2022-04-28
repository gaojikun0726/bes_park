package com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage;

import com.core.common.BaseEntity;
/**
 * 电能参数(采集参数)采集方案关系
 * @author LvSihan
 *
 */
public class BESElectric_Coll_Rlgl implements BaseEntity<String>{

	private static final long serialVersionUID = -160934859749730597L;

	private String fCjfabh;

    private String fNhbh;
    
    private String fStatisticalParam;//是否作为统计分析参数

    private String fIsRate; //是否变比

	private String fCrdate;

    private String fChdate;

    public String getfCjfabh() {
        return fCjfabh;
    }

    public void setfCjfabh(String fCjfabh) {
        this.fCjfabh = fCjfabh == null ? null : fCjfabh.trim();
    }

    public String getfNhbh() {
        return fNhbh;
    }

    public void setfNhbh(String fNhbh) {
        this.fNhbh = fNhbh == null ? null : fNhbh.trim();
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

	public String getfStatisticalParam() {
		return fStatisticalParam;
	}

	public void setfStatisticalParam(String fStatisticalParam) {
		this.fStatisticalParam = fStatisticalParam;
	}


	public String getfIsRate()
	{
		return fIsRate;
	}

	public void setfIsRate(String fIsRate)
	{
		this.fIsRate = fIsRate;
	}
}