package com.efounder.JEnterprise.model.collectorJob;

import com.core.common.BaseEntity;

import java.io.Serializable;

/**
 * 短信报警表
 * 
 * @author LvSihan
 * 
 * @date 2018-08-23
 */
public class BesNoteAlarm implements BaseEntity<Serializable>{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4113687113769420401L;

	private String fGuid;

    /**
     * 电话号码
     */
    private String fPhone;

    /**
     * 报警信息
     */
    private String fText;

    /**
     * 处理状态 0：已处理，1：未处理
     */
    private String fState;

    public String getfGuid() {
        return fGuid;
    }

    public void setfGuid(String fGuid) {
        this.fGuid = fGuid == null ? null : fGuid.trim();
    }

    public String getfPhone() {
        return fPhone;
    }

    public void setfPhone(String fPhone) {
        this.fPhone = fPhone == null ? null : fPhone.trim();
    }

    public String getfText() {
        return fText;
    }

    public void setfText(String fText) {
        this.fText = fText == null ? null : fText.trim();
    }

    public String getfState() {
        return fState;
    }

    public void setfState(String fState) {
        this.fState = fState == null ? null : fState.trim();
    }
}