package com.efounder.JEnterprise.zhdg.entity;

/**
 * 点位报警维护对象 seb_pointwarn
 *
 * @author YangChao
 * @date 2020-05-18
 */
public class SebPointwarn{
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 点位id--seb_point-fcode
     */
    private String pointid;

    /**
     * 点位集合
     */
    private String pointIdList;

    private String pointname;

    private String pointIp;

    /**
     * 报警id--关联seb-warntype
     */
    private Long warnid;

    private String warntype;

    /**
     * 报警阈值类型--0.是/否类型--1.上限下限类型
     */
    private String vtype;

    /**
     * 报警阈值类型1
     */
    private String vtype0;

    /**
     * 阈值下限
     */
    private String vtype1Min;

    /**
     * 阈值上限
     */
    private String vtype1Max;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPointid() {
        return pointid;
    }

    public void setPointid(String pointid) {
        this.pointid = pointid;
    }

    public String getPointIdList() {
        return pointIdList;
    }

    public void setPointIdList(String pointIdList) {
        this.pointIdList = pointIdList;
    }

    public String getPointname() {
        return pointname;
    }

    public void setPointname(String pointname) {
        this.pointname = pointname;
    }

    public String getPointIp() {
        return pointIp;
    }

    public void setPointIp(String pointIp) {
        this.pointIp = pointIp;
    }

    public Long getWarnid() {
        return warnid;
    }

    public void setWarnid(Long warnid) {
        this.warnid = warnid;
    }

    public String getWarntype() {
        return warntype;
    }

    public void setWarntype(String warntype) {
        this.warntype = warntype;
    }

    public String getVtype() {
        return vtype;
    }

    public void setVtype(String vtype) {
        this.vtype = vtype;
    }

    public String getVtype0() {
        return vtype0;
    }

    public void setVtype0(String vtype0) {
        this.vtype0 = vtype0;
    }

    public String getVtype1Min() {
        return vtype1Min;
    }

    public void setVtype1Min(String vtype1Min) {
        this.vtype1Min = vtype1Min;
    }

    public String getVtype1Max() {
        return vtype1Max;
    }

    public void setVtype1Max(String vtype1Max) {
        this.vtype1Max = vtype1Max;
    }
}
