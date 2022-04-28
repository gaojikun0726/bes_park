package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;

/**
 * 
 *  告警信息
 * @author xiepufeng
 * 
 * @date 2018-09-26
 */
public class BesAlarmModel implements BaseEntity<String>{

    private int fId;

    private String fAzwz;      // 告警位置

    private String fNickName;  // 报警名称 F_NICK_NAME

    private String fSysName;   // 系统名称 F_SYS_NAME

    private String fInitVal;   // 实时值 F_INIT_VAL

    private String fPlanVal;   // 计划值  F_PLAN_VAL

    private String fPromptMsg; // 提示信息 F_PROMPT_MSG

    private String fAlarmTime; // 告警时间 F_ALARM_TIME

    private String startTime;  // 开始时间 START_TIME

    private String endTime;    // 结束时间 END_TIME

    public int getfId() {
        return fId;
    }

    public void setfId(int fId) {
        this.fId = fId;
    }

    public String getfAzwz() {
        return fAzwz;
    }

    public void setfAzwz(String fAzwz) {
        this.fAzwz = fAzwz;
    }

    public String getfNickName() {
        return fNickName;
    }

    public void setfNickName(String fNickName) {
        this.fNickName = fNickName;
    }

    public String getfInitVal() {
        return fInitVal;
    }

    public void setfInitVal(String fInitVal) {
        this.fInitVal = fInitVal;
    }

    public String getfPlanVal() {
        return fPlanVal;
    }

    public void setfPlanVal(String fPlanVal) {
        this.fPlanVal = fPlanVal;
    }

    public String getfPromptMsg() {
        return fPromptMsg;
    }

    public void setfPromptMsg(String fPromptMsg) {
        this.fPromptMsg = fPromptMsg;
    }

    public String getfAlarmTime() {
        return fAlarmTime;
    }

    public void setfAlarmTime(String fAlarmTime) {
        this.fAlarmTime = fAlarmTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getfSysName() {
        return fSysName;
    }

    public void setfSysName(String fSysName) {
        this.fSysName = fSysName;
    }
}