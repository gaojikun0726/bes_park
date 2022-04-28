package com.efounder.JEnterprise.model.basedatamanage.energyinformation;

import java.util.Date;


/**
 * 支路电表操作日志
 *
 */
public class BESBranchAmmeterLog {

    private static final long serialVersionUID = -5038006023734065582L;

    private String fDbsysName;

    private String fZlbh;

    private Date fCrdate;

    private String fOperator;

    private String fType;

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

    public String getfOperator() {
        return fOperator;
    }

    public void setfOperator(String fOperator) {
        this.fOperator = fOperator;
    }

    public String getfType() {
        return fType;
    }

    public void setfType(String fType) {
        this.fType = fType;
    }
}
