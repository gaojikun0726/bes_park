package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;

import java.util.Date;
/**
 * 电表基础信息
 * @author LvSihan
 *
 */
public class BESAmmeter implements BaseEntity<String>{

	private static final long serialVersionUID = -5439179651623866737L;

	private String fGuid;

	private String fSysName;//系统名称

	private String fSysNameOld;//系统名称

    private String fNickName;//别名

    private String fSbid;//设备id，用于下位机通讯

    private Integer fEnabled;//是否使用

    private String fSsqy;//所属区域

    private String fAzwz;//安装位置

    private String fWldz;//物理地址

    private String fDescription;//描述

    private String fSblxbh;//设备类型编号

    private String fType;//类型

    private String fCommRate;//通信波特率编号

    private String fCommRateMc;//通信波特率名称

    private String fProtocolType;//通信协议类型

    private String fProtocolTypeMc;//通信协议类型名称

    private String fCjfabh;//采集方案编号

    private String fCjfamc;//采集方案名称

    private String fBlxbh;//电表类型编号

    private String fBlxmc;//电表类型名称

    private String fCommunicationPort;//通信端口

    private String fYqbh; // 园区编号

    private String fPercentage;//变比

    private String fOnlinestate;

	private String fOnlineState;//电表在线状态

    private String fAmmeterState;//同步状态

    private String fComDataBit;//数据位

    private String fComParityBit;//校验位

    private String fComStopBit;//停止位

    private String fFunctionCode;//功能码

    private Date fCrdate;

    private Date fChdate;
    
    private String fOperator;

    private String fPort; // 端口

    public String getfGuid()
    {
        return fGuid;
    }

    public void setfGuid(String fGuid)
    {
        this.fGuid = fGuid;
    }

    public String getfSbid()
    {
        return fSbid;
    }

    public void setfSbid(String fSbid)
    {
        this.fSbid = fSbid;
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

    public String getfAzwz() {
        return fAzwz;
    }

    public void setfAzwz(String fAzwz) {
        this.fAzwz = fAzwz == null ? null : fAzwz.trim();
    }

    public String getfWldz() {
        return fWldz;
    }

    public void setfWldz(String fWldz) {
        this.fWldz = fWldz == null ? null : fWldz.trim();
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

    public String getfCommRate() {
        return fCommRate;
    }

    public void setfCommRate(String fCommRate) {
        this.fCommRate = fCommRate == null ? null : fCommRate.trim();
    }

    public String getfProtocolType() {
        return fProtocolType;
    }

    public void setfProtocolType(String fProtocolType) {
        this.fProtocolType = fProtocolType == null ? null : fProtocolType.trim();
    }

    public String getfCjfabh() {
        return fCjfabh;
    }

    public void setfCjfabh(String fCjfabh) {
        this.fCjfabh = fCjfabh == null ? null : fCjfabh.trim();
    }

    public String getfBlxbh() {
        return fBlxbh;
    }

    public void setfBlxbh(String fBlxbh) {
        this.fBlxbh = fBlxbh == null ? null : fBlxbh.trim();
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

	public String getfOnlinestate() {
		return fOnlinestate;
	}

	public void setfOnlinestate(String string) {
		this.fOnlinestate = string;
	}

	public String getfOperator() {
		return fOperator;
	}

	public void setfOperator(String fOperator) {
		this.fOperator = fOperator;
	}
    public String getfOnlineState() {
		return fOnlineState;
	}

	public void setfOnlineState(String fOnlineState) {
		this.fOnlineState = fOnlineState;
	}

	public String getfAmmeterState() {
		return fAmmeterState;
	}

	public void setfAmmeterState(String fAmmeterState) {
		this.fAmmeterState = fAmmeterState;
	}

    public String getfPort()
    {
        return fPort;
    }

    public void setfPort(String fPort)
    {
        this.fPort = fPort;
    }

    public String getfSysNameOld()
    {
        return fSysNameOld;
    }

    public void setfSysNameOld(String fSysNameOld)
    {
        this.fSysNameOld = fSysNameOld;
    }

    public String getfYqbh()
    {
        return fYqbh;
    }

    public void setfYqbh(String fYqbh)
    {
        this.fYqbh = fYqbh;
    }

    public String getfCommRateMc() {
        return fCommRateMc;
    }

    public void setfCommRateMc(String fCommRateMc) {
        this.fCommRateMc = fCommRateMc;
    }

    public String getfProtocolTypeMc() {
        return fProtocolTypeMc;
    }

    public void setfProtocolTypeMc(String fProtocolTypeMc) {
        this.fProtocolTypeMc = fProtocolTypeMc;
    }

    public String getfCjfamc() {
        return fCjfamc;
    }

    public void setfCjfamc(String fCjfamc) {
        this.fCjfamc = fCjfamc;
    }

    public String getfBlxmc() {
        return fBlxmc;
    }

    public void setfBlxmc(String fBlxmc) {
        this.fBlxmc = fBlxmc;
    }

    public String getfCommunicationPort() {
        return fCommunicationPort;
    }

    public void setfCommunicationPort(String fCommunicationPort) {
        this.fCommunicationPort = fCommunicationPort;
    }

    public String getfPercentage() {
        return fPercentage;
    }

    public void setfPercentage(String fPercentage) {
        this.fPercentage = fPercentage;
    }

    public String getfComDataBit() {
        return fComDataBit;
    }

    public void setfComDataBit(String fComDataBit) {
        this.fComDataBit = fComDataBit;
    }

    public String getfComParityBit() {
        return fComParityBit;
    }

    public void setfComParityBit(String fComParityBit) {
        this.fComParityBit = fComParityBit;
    }

    public String getfComStopBit() {
        return fComStopBit;
    }

    public void setfComStopBit(String fComStopBit) {
        this.fComStopBit = fComStopBit;
    }

    public String getfFunctionCode() {
        return fFunctionCode;
    }

    public void setfFunctionCode(String fFunctionCode) {
        this.fFunctionCode = fFunctionCode;
    }

    public String getfSblxbh() {
        return fSblxbh;
    }

    public void setfSblxbh(String fSblxbh) {
        this.fSblxbh = fSblxbh;
    }
}