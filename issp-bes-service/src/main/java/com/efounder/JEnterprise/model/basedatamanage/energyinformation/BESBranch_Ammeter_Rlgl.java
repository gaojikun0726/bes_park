package com.efounder.JEnterprise.model.basedatamanage.energyinformation;

import com.core.common.BaseEntity;

import java.util.Date;
/**
 * 支路与电表关系
 * @author LvSihan
 *
 */
public class BESBranch_Ammeter_Rlgl  implements BaseEntity<String>{

	private static final long serialVersionUID = -5038006023734065582L;

	private String fDbsysName;

    private String fZlbh;
    
	private Date fCrdate;

    private Date fChdate;
    
    private String fOperator;

	private String fJl;
    
    public String getfDbsysName() {
		return fDbsysName;
	}

	public void setfDbsysName(String fDbsysName) {
		this.fDbsysName = fDbsysName;
	}

	public String getfZlbh() {
		return fZlbh;
	}

	public void setfZlbh(String fZlbh) {
		this.fZlbh = fZlbh;
	}

    public Date getfCrdate() {
        return fCrdate;
    }

    public void setfCrdate(Date fCrdate) {
        this.fCrdate = fCrdate;
    }

    public Date getfChdate() {
        return fChdate;
    }

    public void setfChdate(Date fChdate) {
        this.fChdate = fChdate;
    }

	public String getfOperator() {
		return fOperator;
	}

	public void setfOperator(String fOperator) {
		this.fOperator = fOperator;
	}

	public String getfJl() {
		return fJl;
	}

	public void setfJl(String fJl) {
		this.fJl = fJl;
	}
}