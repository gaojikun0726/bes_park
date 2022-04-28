package com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage;

import com.core.common.BaseEntity;
/**
 * 能效类型
 * @author LvSihan
 *
 */
public class BESEnergyType implements BaseEntity<String>{

	private static final long serialVersionUID = -7325984092628833715L;
	
	private String fGuid;

	private String fNybh;

    private String fNymc;

    private String fPrice;

    private String fCoalAmount;

    private String fCo2;

    private String fUnit;

    private String fCrdate;

    private String fChdate;

    public String getfNybh() {
        return fNybh;
    }

    public void setfNybh(String fNybh) {
        this.fNybh = fNybh == null ? null : fNybh.trim();
    }

    public String getfNymc() {
        return fNymc;
    }

    public void setfNymc(String fNymc) {
        this.fNymc = fNymc == null ? null : fNymc.trim();
    }

    public String getfPrice() {
        return fPrice;
    }

    public void setfPrice(String fPrice) {
        this.fPrice = fPrice == null ? null : fPrice.trim();
    }

    public String getfCoalAmount() {
        return fCoalAmount;
    }

    public void setfCoalAmount(String fCoalAmount) {
        this.fCoalAmount = fCoalAmount == null ? null : fCoalAmount.trim();
    }

    public String getfCo2() {
        return fCo2;
    }

    public void setfCo2(String fCo2) {
        this.fCo2 = fCo2 == null ? null : fCo2.trim();
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

	public String getfGuid() {
		return fGuid;
	}

	public void setfGuid(String fGuid) {
		this.fGuid = fGuid;
	}

    public String getfUnit() {
        return fUnit;
    }

    public void setfUnit(String fUnit) {
        this.fUnit = fUnit;
    }
}