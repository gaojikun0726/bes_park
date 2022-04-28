package com.efounder.JEnterprise.model.BesWorkbench;

import com.core.common.BaseEntity;

/**
 * 
 * 
 * @author ycc
 * 
 * @date 2018-11-30
 */
public class BesGztzzjgTree implements BaseEntity<String> {
    /**
     * 主键
     */
    private String fId;

    /**
     * 名称
     */
    private String fName;

    /**
     * 父级ID
     */
    private String fPid;

    /**
     * 创建时间
     */
    private String fCrdate;

    /**
     * 修改时间
     */
    private String fChdate;

    /**
     * imgurl地址
     */
    private String fImgurl;

    /**
     * 前台布局类型0.园区 1.楼 2.层 3.室
     */
    private String fLx;

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId == null ? null : fId.trim();
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName == null ? null : fName.trim();
    }

    public String getfPid() {
        return fPid;
    }

    public void setfPid(String fPid) {
        this.fPid = fPid == null ? null : fPid.trim();
    }

    public String getfCrdate() {
        return fCrdate;
    }

    public void setfCrdate(String f_addtime) {
        this.fCrdate = f_addtime;
    }

    public String getfChdate() {
        return fChdate;
    }

    public void setfChdate(String fChdate) {
        this.fChdate = fChdate;
    }

    public String getfImgurl() {
        return fImgurl;
    }

    public void setfImgurl(String fImgurl) {
        this.fImgurl = fImgurl == null ? null : fImgurl.trim();
    }

    public String getfLx() {
        return fLx;
    }

    public void setfLx(String fLx) {
        this.fLx = fLx == null ? null : fLx.trim();
    }
}