package com.efounder.JEnterprise.model.floorconfigure;


/**
 * 配置人员表
 */
public class rybModel {

    private String id; //ID
    private String rybh; //人员编号
    private String rymc; //人员名称
    private String ryxb; //人员性别
    private String tjrq; //添加日期
    private String gldw; //关联单位
    private String sbr; //申报人

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRybh() {
        return rybh;
    }

    public void setRybh(String rybh) {
        this.rybh = rybh;
    }

    public String getRymc() {
        return rymc;
    }

    public void setRymc(String rymc) {
        this.rymc = rymc;
    }

    public String getRyxb() {
        return ryxb;
    }

    public void setRyxb(String ryxb) {
        this.ryxb = ryxb;
    }

    public String getTjrq() {
        return tjrq;
    }

    public void setTjrq(String tjrq) {
        this.tjrq = tjrq;
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
