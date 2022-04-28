package com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage;

import com.core.common.BaseEntity;
/**
 * 采集方案
 * @author LvSihan
 *
 */
public class BESCollMethod implements BaseEntity<String>{

	private static final long serialVersionUID = -6339761028066131148L;

	private String fCjfabh;

    private String fCjfamc;

    private String fZzjgbh;

    private String fNybh;

    private String fCrdate;

    private String fChdate;

    public String getfCjfabh() {
        return fCjfabh;
    }

    public void setfCjfabh(String fCjfabh) {
        this.fCjfabh = fCjfabh == null ? null : fCjfabh.trim();
    }

    public String getfCjfamc() {
        return fCjfamc;
    }

    public void setfCjfamc(String fCjfamc) {
        this.fCjfamc = fCjfamc == null ? null : fCjfamc.trim();
    }

    public String getfZzjgbh() {
        return fZzjgbh;
    }

    public void setfZzjgbh(String fZzjgbh) {
        this.fZzjgbh = fZzjgbh == null ? null : fZzjgbh.trim();
    }

    public String getfNybh() {
        return fNybh;
    }

    public void setfNybh(String fNybh) {
        this.fNybh = fNybh == null ? null : fNybh.trim();
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