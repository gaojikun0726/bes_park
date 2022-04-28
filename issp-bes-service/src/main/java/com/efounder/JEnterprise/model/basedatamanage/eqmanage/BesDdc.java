package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * DDC表
 * 
 * @author LvSihan 
 * @date 2018年9月11日  
 * @version 1.0
 */
public class BesDdc implements BaseEntity<Serializable>{
	
	private static final long serialVersionUID = 8889285623560896660L;

	/**
	 * F_FUID
	 */
	private String fGuid;
	/**
     * 系统名称
     */
    private String fSysName;

	/**
     * 系统名称
     */
    private String fSysNameOld;

    /**
     * 别名
     */
    private String fNickName;

    /**
     * 是否使用   不使用0使用1，只有使用才能与其通信
     */
    private Integer fEnabled;

    /**
     * 所属区域   已经配置的区域（存区域系统名称）
     */
    private String fSsqy;

    /**
     * 节点类型FK(BES_SBTREE_NODE. F_NODE_TYPE)
     */
    private String fNodeType;

    /**
     * 设备类型编号fk（bes_equipment.f_type）
     */
    private String fSblxbh;

    /**
     * 安装位置
     */
    private String fAzwz;

    /**
     * 描述
     */
    private String fDescription;

    /**
     * 型号
     */
    private String fType;

    /**
     * IP地址
     */
    private String fIpAddr;

    /**
     * 端口号
     */
    private String fPort;
    
    /**
     * ddc数据同步状态
     */
    private String fDdcState;
    
    /**
     * ddc在线状态
     */
    private String fDdcOnlinestate;

    /**
     * 园区编号
     */
    private String fYqbh;

	/**
     * 创建日期
     */
    private Date fCrdate;

    /**
     * 修改日期
     */
    private Date fChdate;
    /**
     * 设备ID
     * @return
     */
    private String fSbid;

    private String fGateway;

    private String fMask;

    private String fIpMaster;

    private String fPortMaster;

    private String fChannelId;

    private Integer fSavePeriod; // 能耗数据保存周期

    public String getfGateway() {
        return fGateway;
    }

    public void setfGateway(String fGateway) {
        this.fGateway = fGateway;
    }

    public String getfMask() {
        return fMask;
    }

    public void setfMask(String fMask) {
        this.fMask = fMask;
    }

    public String getfIpMaster() {
        return fIpMaster;
    }

    public void setfIpMaster(String fIpMaster) {
        this.fIpMaster = fIpMaster;
    }

    public String getfPortMaster() {
        return fPortMaster;
    }

    public void setfPortMaster(String fPortMaster) {
        this.fPortMaster = fPortMaster;
    }

    public String getfChannelId() {
        return fChannelId;
    }

    public void setfChannelId(String fChannelId) {
        this.fChannelId = fChannelId;
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

    public Integer getfEnabled() {
        return fEnabled;
    }

    public void setfEnabled(Integer fEnabled) {
        this.fEnabled = fEnabled;
    }

    public String getfSsqy() {
        return fSsqy;
    }

    public void setfSsqy(String fSsqy) {
        this.fSsqy = fSsqy == null ? null : fSsqy.trim();
    }

    public String getfNodeType() {
        return fNodeType;
    }

    public void setfNodeType(String fNodeType) {
        this.fNodeType = fNodeType == null ? null : fNodeType.trim();
    }

    public String getfSblxbh() {
        return fSblxbh;
    }

    public void setfSblxbh(String fSblxbh) {
        this.fSblxbh = fSblxbh == null ? null : fSblxbh.trim();
    }

    public String getfAzwz() {
        return fAzwz;
    }

    public void setfAzwz(String fAzwz) {
        this.fAzwz = fAzwz == null ? null : fAzwz.trim();
    }

    public String getfDescription() {
        return fDescription;
    }

    public void setfDescription(String fDescription) {
        this.fDescription = fDescription == null ? null : fDescription.trim();
    }

    public String getfType() {
        return fType;
    }

    public void setfType(String fType) {
        this.fType = fType == null ? null : fType.trim();
    }

    public String getfIpAddr() {
        return fIpAddr;
    }

    public void setfIpAddr(String fIpAddr) {
        this.fIpAddr = fIpAddr == null ? null : fIpAddr.trim();
    }

    public String getfPort() {
        return fPort;
    }

    public void setfPort(String fPort) {
        this.fPort = fPort == null ? null : fPort.trim();
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

	public String getfGuid() {
		return fGuid;
	}

	public void setfGuid(String fGuid) {
		this.fGuid = fGuid;
	}
    public String getfDdcState() {
		return fDdcState;
	}

	public void setfDdcState(String fDdcState) {
		this.fDdcState = fDdcState;
	}

	public String getfDdcOnlinestate() {
		return fDdcOnlinestate;
	}

	public void setfDdcOnlinestate(String fDdcOnlinestate) {
		this.fDdcOnlinestate = fDdcOnlinestate;
	}

	public String getfSbid() {
		return fSbid;
	}

	public void setfSbid(String fSbid) {
		this.fSbid = fSbid;
	}

    public String getfSysNameOld() {
        return fSysNameOld;
    }

    public void setfSysNameOld(String fSysNameOld) {
        this.fSysNameOld = fSysNameOld;
    }

    public String getfYqbh() {
        return fYqbh;
    }

    public void setfYqbh(String fYqbh) {
        this.fYqbh = fYqbh;
    }

    public Integer getfSavePeriod() {
        return fSavePeriod;
    }

    public void setfSavePeriod(Integer fSavePeriod) {
        this.fSavePeriod = fSavePeriod;
    }
}