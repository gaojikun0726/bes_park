package com.efounder.JEnterprise.model.collectorJob;

import com.core.common.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 采集器系统配置
 * @author LvSihan
 * 
 * @date 2018-08-22
 */
public class BESSysConf implements BaseEntity<Serializable>{
    /**
	 * 
	 */
	private static final long serialVersionUID = -387360709419405163L;

	private String fGuid;

    /**
     * 报警总开关：0：短信；1：邮件；2：全选；3：全不选；
     */
    private String fAlarmoperation;

    /**
     * 导出时间
     */
    private Date fImportime;

    /**
     * 导入时间
     */
    private Date fExportime;

    /**
     * sql文件路径
     */
    private String fSqlfile;

    /**
     * 采集器轮询周期
     */
    private String fCollectionCycle;

    /**
     * 分户能耗计划轮询周期
     */
    private String fHousePlanCycle;

    /**
     * 最近一次计算完毕的时间 busy:正在计算 time:上次计算完毕的时间
     */
    private String fCalculatetime;

    /**
     * 最近一次计算完毕的时间
     */
    private String fLastruntime;

    /**
     * 补数据开始时间
     */
    private String fStarttime;

    /**
     * 补数据结束时间
     */
    private String fEndtime;

    /**
     * 实时数据状态 busy:正在写入 time:上次写入完毕的时间
     */
    private String fRealtimedataflag;

    /**
     * 历史数据开始时间
     */
    private String fHisStartTime;

    /**
     * 历史数据结束时间
     */
    private String fHisEndTime;

    public String getfGuid() {
        return fGuid;
    }

    public void setfGuid(String fGuid) {
        this.fGuid = fGuid == null ? null : fGuid.trim();
    }

    public String getfAlarmoperation() {
        return fAlarmoperation;
    }

    public void setfAlarmoperation(String fAlarmoperation) {
        this.fAlarmoperation = fAlarmoperation == null ? null : fAlarmoperation.trim();
    }

    public Date getfImportime() {
        return fImportime;
    }

    public void setfImportime(Date fImportime) {
        this.fImportime = fImportime;
    }

    public Date getfExportime() {
        return fExportime;
    }

    public void setfExportime(Date fExportime) {
        this.fExportime = fExportime;
    }

    public String getfSqlfile() {
        return fSqlfile;
    }

    public void setfSqlfile(String fSqlfile) {
        this.fSqlfile = fSqlfile == null ? null : fSqlfile.trim();
    }

    public String getfCollectionCycle() {
        return fCollectionCycle;
    }

    public void setfCollectionCycle(String fCollectionCycle) {
        this.fCollectionCycle = fCollectionCycle == null ? null : fCollectionCycle.trim();
    }

    public String getfHousePlanCycle() {
        return fHousePlanCycle;
    }

    public void setfHousePlanCycle(String fHousePlanCycle) {
        this.fHousePlanCycle = fHousePlanCycle == null ? null : fHousePlanCycle.trim();
    }

    public String getfCalculatetime() {
        return fCalculatetime;
    }

    public void setfCalculatetime(String fCalculatetime) {
        this.fCalculatetime = fCalculatetime == null ? null : fCalculatetime.trim();
    }

    public String getfLastruntime() {
        return fLastruntime;
    }

    public void setfLastruntime(String fLastruntime) {
        this.fLastruntime = fLastruntime == null ? null : fLastruntime.trim();
    }

    public String getfStarttime() {
        return fStarttime;
    }

    public void setfStarttime(String fStarttime) {
        this.fStarttime = fStarttime == null ? null : fStarttime.trim();
    }

    public String getfEndtime() {
        return fEndtime;
    }

    public void setfEndtime(String fEndtime) {
        this.fEndtime = fEndtime == null ? null : fEndtime.trim();
    }

    public String getfRealtimedataflag() {
        return fRealtimedataflag;
    }

    public void setfRealtimedataflag(String fRealtimedataflag) {
        this.fRealtimedataflag = fRealtimedataflag == null ? null : fRealtimedataflag.trim();
    }

    public String getfHisStartTime() {
        return fHisStartTime;
    }

    public void setfHisStartTime(String fHisStartTime) {
        this.fHisStartTime = fHisStartTime == null ? null : fHisStartTime.trim();
    }

    public String getfHisEndTime() {
        return fHisEndTime;
    }

    public void setfHisEndTime(String fHisEndTime) {
        this.fHisEndTime = fHisEndTime == null ? null : fHisEndTime.trim();
    }

}
