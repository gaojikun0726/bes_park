package com.efounder.JEnterprise.model.sysmanage;

import com.core.common.BaseEntity;

import java.io.Serializable;

/**
 * 计划任务节点信息表
 * 
 * @author LvSihan
 * 
 * @date 2018-09-28
 */
public class BesScheduleinfo implements BaseEntity<Serializable> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5144258415219925116L;

	private String fId;

    /**
     * 根0、文件夹1
     */
    private String fType;

    /**
     * 名称
     */
    private String fName;

    /**
     * 父节点ID
     */
    private String fParentid;

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId == null ? null : fId.trim();
    }

    public String getfType() {
        return fType;
    }

    public void setfType(String fType) {
        this.fType = fType == null ? null : fType.trim();
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName == null ? null : fName.trim();
    }

    public String getfParentid() {
        return fParentid;
    }

    public void setfParentid(String fParentid) {
        this.fParentid = fParentid == null ? null : fParentid.trim();
    }
}