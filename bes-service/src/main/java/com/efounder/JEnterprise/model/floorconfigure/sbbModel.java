package com.efounder.JEnterprise.model.floorconfigure;


/**
 * 配置人员表
 */
public class sbbModel {

    private String id; //ID
    private String sbbh; //设备编号
    private String sbmc; //设备名称
    private String sblb; //设备类别
    private String sgnf; //申购年份
    private String gldw; //关联单位
    private String sbr; //申报人

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

    public String getSgnf() {
        return sgnf;
    }

    public void setSgnf(String sgnf) {
        this.sgnf = sgnf;
    }

    public String getGldw() {
        return gldw;
    }

    public void setGldw(String gldw) {
        this.gldw = gldw;
    }

    public String getSbr() {
        return sbr;
    }

    public void setSbr(String sbr) {
        this.sbr = sbr;
    }
}
