package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;
import lombok.Data;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2018-09-29
 */
@Data
public class BesZone implements BaseEntity<String>{
    private String fId;

    /**
     * 场景类型：控制场景 0、采集场景1
     */
    private String fType;

    /**
     * 场景名称
     */
    private String fSysName;

    /**
     * 别名
     */
    private String fNickName;

    /**
     * 描述
     */
    private String fDescription;

    /**
     * 场景模式id
     */
    private String fZonemodeId;

    /**
     * 使能:不使用0使用1
     */
    private String fEnabled;
    
    /**
     * 存父级id 关联存储
     */
    private String fParentid;
    
    /**
     * IP地址
     */
    private String IPAddr;
    
    /**
     * 端口号
     */
    private String Port;
    
    /**
     * ID	关联ddc表
     */
    private String parentid;

    /**
     * 操作类型 C新增 U修改
     */
    private String operateType;

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

    public String getfDescription() {
        return fDescription;
    }

    public void setfDescription(String fDescription) {
        this.fDescription = fDescription == null ? null : fDescription.trim();
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

	public String getfParentid() {
		return fParentid;
	}

	public void setfParentid(String fParentid) {
		this.fParentid = fParentid;
	}

	public String getIPAddr() {
		return IPAddr;
	}

	public void setIPAddr(String iPAddr) {
		IPAddr = iPAddr;
	}

	public String getPort() {
		return Port;
	}

	public void setPort(String port) {
		Port = port;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

    public String getOperateType() {

        return operateType;
    }

    public void setOperateType(String operateType) {

        this.operateType = operateType;
    }
    
}