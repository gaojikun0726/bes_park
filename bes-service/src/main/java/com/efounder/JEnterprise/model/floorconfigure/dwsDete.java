package com.efounder.JEnterprise.model.floorconfigure;

/**
 * 配置单位
 */
public class dwsDete {

    private String id; //ID
    private String dwmc; //单位名称
    private String dwbh; //单位编号
    private String dwzt; //单位状态
    private String sjdw; //上级单位
    private String cjsj; //创建时间
    private String cjry; //创建人员


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    public String getDwbh() {
        return dwbh;
    }

    public void setDwbh(String dwbh) {
        this.dwbh = dwbh;
    }

    public String getDwzt() {
        return dwzt;
    }

    public void setDwzt(String dwzt) {
        this.dwzt = dwzt;
    }

    public String getSjdw() {
        return sjdw;
    }

    public void setSjdw(String sjdw) {
        this.sjdw = sjdw;
    }

    public String getCjsj() {
        return cjsj;
    }

    public void setCjsj(String cjsj) {
        this.cjsj = cjsj;
    }

    public String getCjry() {
        return cjry;
    }

    public void setCjry(String cjry) {
        this.cjry = cjry;
    }
}
