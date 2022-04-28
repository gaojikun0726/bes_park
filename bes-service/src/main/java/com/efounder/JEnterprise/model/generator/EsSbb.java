package com.efounder.JEnterprise.model.generator;

import java.io.Serializable;

/**
 * es_sbb
 * @author
 */

public class EsSbb implements Serializable {
    /**
     * ID
     */
    private String id;

    /**
     * 设备编号
     */
    private String sbbh;

    /**
     * 设备名称
     */
    private String sbmc;

    /**
     * 设备类别
     */
    private String sblb;

    /**
     * 公司
     */
    private String gldw;

    /**
     * 用户所属组织机构  FK(ES_ZZJG.F_ZZJGBH)
     */
    private String fZzjgbh;

    /**
     * 所属园区编号
     */
    private String fYqbh;

    /**
     * 创建日期
     */
    private String fCrdate;

    /**
     * 修改日期
     */
    private String fChdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSbbh() {
        return sbbh;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public String getSbmc() {
        return sbmc;
    }

    public void setSbmc(String sbmc) {
        this.sbmc = sbmc;
    }

    public String getSblb() {
        return sblb;
    }

    public void setSblb(String sblb) {
        this.sblb = sblb;
    }

    public String getGldw() {
        return gldw;
    }

    public void setGldw(String gldw) {
        this.gldw = gldw;
    }

    public String getfZzjgbh() {
        return fZzjgbh;
    }

    public void setfZzjgbh(String fZzjgbh) {
        this.fZzjgbh = fZzjgbh;
    }

    public String getfYqbh() {
        return fYqbh;
    }

    public void setfYqbh(String fYqbh) {
        this.fYqbh = fYqbh;
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

    public String getId(Object o) {
       return this.id = (String) o;
    }
}
