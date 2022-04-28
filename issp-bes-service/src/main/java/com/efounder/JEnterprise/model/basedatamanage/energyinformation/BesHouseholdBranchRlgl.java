package com.efounder.JEnterprise.model.basedatamanage.energyinformation;

import com.core.common.BaseEntity;

import java.io.Serializable;

/**
 * 分户支路关系表
 * 
 * @author suhang
 * 
 * @date 2018-07-18
 */
public class BesHouseholdBranchRlgl implements BaseEntity<Serializable> {
    /**
     * 创建日期
     */
    private String fCrdate;

    /**
     * 修改日期
     */
    private String fChdate;
    
    /**
     * 分户编号 FK(BES_HOUSEHOLD_CONF. F_FHBH)
     */
    private String fFhbh;

    /**
     * 支路编号 FK(BES_BRANCH_CONF. F_ZLBH)
     */
    private String fZlbh;

    private String fJl;
    
    public String getfFhbh() {
        return fFhbh;
    }

    public void setfFhbh(String fFhbh) {
        this.fFhbh = fFhbh == null ? null : fFhbh.trim();
    }

    public String getfZlbh() {
        return fZlbh;
    }

    public void setfZlbh(String fZlbh) {
        this.fZlbh = fZlbh == null ? null : fZlbh.trim();
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

    public String getfJl() {
        return fJl;
    }

    public void setfJl(String fJl) {
        this.fJl = fJl;
    }
}