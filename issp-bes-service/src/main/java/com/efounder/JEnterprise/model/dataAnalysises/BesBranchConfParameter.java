package com.efounder.JEnterprise.model.dataAnalysises;

import com.core.common.BaseEntity;

/**
 * @Author: wanghongjie
 * @Description:
 * @Date: Created in 18:57 2021/6/3
 * @Modified By:支路报警规则
 */
public class BesBranchConfParameter implements BaseEntity<String> {

    private String fId;

    private String fAlertid;

    private String fElednbh;

    private String fAmmsysName;

    private String fSortno;

    private String fCrdate;

    private String fChdate;

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }

    public String getfAlertid() {
        return fAlertid;
    }

    public void setfAlertid(String fAlertid) {
        this.fAlertid = fAlertid;
    }

    public String getfElednbh() {
        return fElednbh;
    }

    public void setfElednbh(String fElednbh) {
        this.fElednbh = fElednbh;
    }

    public String getfAmmsysName() {
        return fAmmsysName;
    }

    public void setfAmmsysName(String fAmmsysName) {
        this.fAmmsysName = fAmmsysName;
    }

    public String getfSortno() {
        return fSortno;
    }

    public void setfSortno(String fSortno) {
        this.fSortno = fSortno;
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
