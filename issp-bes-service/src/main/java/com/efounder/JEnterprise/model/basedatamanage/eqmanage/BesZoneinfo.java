package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2018-09-26
 */
@Data
public class BesZoneinfo implements BaseEntity<String>{
    private String fId;

    /**
     * 名称
     */
    private String fName;

    /**
     * 0 代表根 1代表文件夹 2 代表 控制场景 3 代表采集场景
     */
    private String fType;

    /**
     * 父节点名称
     */
    private String fParentid;

    /**
     * 场景ID
     */
    private String fZoneid;
    
    private List list;

    /**
     * 使能状态
     */
    private String fEnabled;

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

    public String getfType() {
        return fType;
    }

    public void setfType(String fType) {
        this.fType = fType == null ? null : fType.trim();
    }

    public String getfParentid() {
        return fParentid;
    }

    public void setfParentid(String fParentid) {
        this.fParentid = fParentid == null ? null : fParentid.trim();
    }

    public String getfZoneid() {
        return fZoneid;
    }

    public void setfZoneid(String fZoneid) {
        this.fZoneid = fZoneid == null ? null : fZoneid.trim();
    }

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

    
}