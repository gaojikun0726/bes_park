package com.efounder.JEnterprise.model.basedatamanage.energyinformation;

import com.core.common.BaseEntity;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * 
 * 
 * @author suhang
 * 
 * @date 2018-07-30
 */
public class BesBranchConfAlert implements BaseEntity<Serializable>{
    /**
     * 数据id
     */
    private String fId;

    /**
     * 关联支路id
     */
    private String fZlbh;

    /**
     * 报警名称
     */
    private String fAlertname;

    /**
     * 运算公式
     */
    private String fOperator;

    /**
     * 范围类型0最高阈值；1最小阈值；2阈值范围；3准确值
     */
    private String fRangetype;

    /**
     * 不高于
     */
    private String fNomore;

    /**
     * 不低于
     */
    private String fNoless;

    /**
     * 等于
     */
    private String fEqual;

    /**
     * 使能：0、使能；1、否
     */
    private String fActive;

    private String fCrdate;

    private String fChdate;

    //参数输入
    private String fparameter;

    //电表id
    private  String fdbid;

    //父节点Id
    private  String[] pId;

    //报警等级
    private String fgrade;

    //报警类型Id
    private BigInteger fAlertypeId;

    //报警类型名称
    private String fAlertTypeName;

    public String getfAlertTypeName() {
        return fAlertTypeName;
    }

    public void setfAlertTypeName(String fAlertTypeName) {
        this.fAlertTypeName = fAlertTypeName;
    }

    public BigInteger getfAlertypeId() {
        return fAlertypeId;
    }

    public void setfAlertypeId(BigInteger fAlertypeId) {
        this.fAlertypeId = fAlertypeId;
    }

    public String getFgrade() {
        return fgrade;
    }

    public void setFgrade(String fgrade) {
        this.fgrade = fgrade;
    }

    public String[] getpId() {
        return pId;
    }

    public void setpId(String[] pId) {
        this.pId = pId;
    }

    public String[] getFsortno() {
        return fsortno;
    }

    public void setFsortno(String[] fsortno) {
        this.fsortno = fsortno;
    }

    //输入参数得id
    private  String[] fsortno;

//    public String getFsortno() {
//        return fsortno;
//    }
//
//    public void setFsortno(String fsortno) {
//        this.fsortno = fsortno;
//    }

    public String getFdbid() {
        return fdbid;
    }

    public void setFdbid(String fdbid) {
        this.fdbid = fdbid;
    }

//  private Long fdbid;
//
//    public Long getFdbid() {
//        return fdbid;
//    }
//
//    public void setFdbid(Long fdbid) {
//        this.fdbid = fdbid;
//    }

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId == null ? null : fId.trim();
    }

    public String getfZlbh() {
        return fZlbh;
    }

    public void setfZlbh(String fZlbh) {
        this.fZlbh = fZlbh == null ? null : fZlbh.trim();
    }

    public String getfAlertname() {
        return fAlertname;
    }

    public void setfAlertname(String fAlertname) {
        this.fAlertname = fAlertname == null ? null : fAlertname.trim();
    }

    public String getfOperator() {
        return fOperator;
    }

    public void setfOperator(String fOperator) {
        this.fOperator = fOperator == null ? null : fOperator.trim();
    }

    public String getfRangetype() {
        return fRangetype;
    }

    public void setfRangetype(String fRangetype) {
        this.fRangetype = fRangetype == null ? null : fRangetype.trim();
    }

    public String getfNomore() {
        return fNomore;
    }

    public void setfNomore(String fNomore) {
        this.fNomore = fNomore == null ? null : fNomore.trim();
    }

    public String getfNoless() {
        return fNoless;
    }

    public void setfNoless(String fNoless) {
        this.fNoless = fNoless == null ? null : fNoless.trim();
    }

    public String getfEqual() {
        return fEqual;
    }

    public void setfEqual(String fEqual) {
        this.fEqual = fEqual == null ? null : fEqual.trim();
    }

    public String getfActive() {
        return fActive;
    }

    public void setfActive(String fActive) {
        this.fActive = fActive == null ? null : fActive.trim();
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

    public String getFparameter() {
        return fparameter;
    }

    public void setFparameter(String fparameter) {
        this.fparameter = fparameter;
    }
}