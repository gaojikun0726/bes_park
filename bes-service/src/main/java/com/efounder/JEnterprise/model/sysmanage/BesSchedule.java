package com.efounder.JEnterprise.model.sysmanage;

import com.core.common.BaseEntity;

import java.io.Serializable;
import java.util.Date;


/**
 * 计划任务属性信息表
 * 
 * @author LvSihan
 * 
 * @date 2018-09-28
 */
public class BesSchedule implements BaseEntity<Serializable>{

	private static final long serialVersionUID = -424999984981793911L;

	private String fId;

    /**
     * 系统名称
     */
    private String fSysName;

    /**
     * 别名
     */
    private String fNickName;

    /**
     * 计划开始日期
     */
    private String fStartDate;

    /**
     * 计划结束日期
     */
    private String fEndDate;

    /**
     * 计划开始时间
     */
    private Date fStartTime;

    /**
     * 计划结束时间
     */
    private Date fEndTime;

    /**
     * 是否假期，否0，是1
     */
    private String fIsholidays;

    /**
     * 周掩码，7位二进制，从低位到高位依次代表周一到周日
     */
    private String fWeekmask;

    /**
     * 场景id
     */
    private String fZoneid;

    /**
     * 场景类型 ：控制场景 0 采集场景 1
     */
    private String fZoneType;

    /**
     * 场景模式 0~10选择的模式类型
     */
    private String fZonemodeId;

    /**
     * 是否使能，否0，是1
     */
    private String fEnabled;

    /**
     * 是否跨天，否0，是1
     */
    private String fIsspan;

    /**
     * 父节点
     */
    private String fScheduleinfoId;

    /**
     * 创建时间
     */
    private Date fCrdate;

    /**
     * 修改时间
     */
    private Date fChdate;

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId == null ? null : fId.trim();
    }

    public String getfSysName() {
        return fSysName;
    }

    public void setfSysName(String fSysName) {
        this.fSysName = fSysName == null ? null : fSysName.trim();
    }

    public String getfNickName() {
        return fNickName;
    }

    public void setfNickName(String fNickName) {
        this.fNickName = fNickName == null ? null : fNickName.trim();
    }

    public String getfStartDate() {
        return fStartDate;
    }

    public void setfStartDate(String fStartDate) {
        this.fStartDate = fStartDate;
    }

    public String getfEndDate() {
        return fEndDate;
    }

    public void setfEndDate(String fEndDate) {
        this.fEndDate = fEndDate;
    }

    public Date getfStartTime() {
        return fStartTime;
    }

    public void setfStartTime(Date fStartTime) {
        this.fStartTime = fStartTime;
    }

    public Date getfEndTime() {
        return fEndTime;
    }

    public void setfEndTime(Date fEndTime) {
        this.fEndTime = fEndTime;
    }

    public String getfIsholidays() {
        return fIsholidays;
    }

    public void setfIsholidays(String fIsholidays) {
        this.fIsholidays = fIsholidays == null ? null : fIsholidays.trim();
    }

    public String getfWeekmask() {
        return fWeekmask;
    }

    public void setfWeekmask(String fWeekmask) {
        this.fWeekmask = fWeekmask == null ? null : fWeekmask.trim();
    }

    public String getfZoneid() {
        return fZoneid;
    }

    public void setfZoneid(String fZoneid) {
        this.fZoneid = fZoneid == null ? null : fZoneid.trim();
    }

    public String getfZoneType() {
        return fZoneType;
    }

    public void setfZoneType(String fZoneType) {
        this.fZoneType = fZoneType == null ? null : fZoneType.trim();
    }

    public String getfZonemodeId() {
        return fZonemodeId;
    }

    public void setfZonemodeId(String fZonemodeId) {
        this.fZonemodeId = fZonemodeId == null ? null : fZonemodeId.trim();
    }

    public String getfEnabled() {
        return fEnabled;
    }

    public void setfEnabled(String fEnabled) {
        this.fEnabled = fEnabled == null ? null : fEnabled.trim();
    }

    public String getfIsspan() {
        return fIsspan;
    }

    public void setfIsspan(String fIsspan) {
        this.fIsspan = fIsspan == null ? null : fIsspan.trim();
    }

    public String getfScheduleinfoId() {
        return fScheduleinfoId;
    }

    public void setfScheduleinfoId(String fScheduleinfoId) {
        this.fScheduleinfoId = fScheduleinfoId == null ? null : fScheduleinfoId.trim();
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
}