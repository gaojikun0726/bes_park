package com.efounder.JEnterprise.model.basedatamanage.energyinformation;

import com.core.common.BaseEntity;

import java.io.Serializable;
import java.util.Date;
/**
 * 分项与支路关系
 * @author LvSihan
 *
 */
public class BESSubitem_Branch_Rlgl implements BaseEntity<Serializable>{
 
	private static final long serialVersionUID = 1123027698430806684L;

	private String fFxbh;

    private String fZlbh;

    private Date fCrdate;

    private Date fChdate;

    private String fJl;

    private String keywords;
    
    public String getfFxbh() {
        return fFxbh;
    }

    public void setfFxbh(String fFxbh) {
        this.fFxbh = fFxbh == null ? null : fFxbh.trim();
    }

    public String getfZlbh() {
        return fZlbh;
    }

    public void setfZlbh(String fZlbh) {
        this.fZlbh = fZlbh == null ? null : fZlbh.trim();
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

    public String getfJl() {
        return fJl;
    }

    public void setfJl(String fJl) {
        this.fJl = fJl;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}