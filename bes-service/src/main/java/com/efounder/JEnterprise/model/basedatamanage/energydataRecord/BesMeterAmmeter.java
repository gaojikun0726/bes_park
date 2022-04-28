package com.efounder.JEnterprise.model.basedatamanage.energydataRecord;


import com.core.common.BaseEntity;
import com.framework.common.utils.ExcelVOAttribute;

/**
 * 电表
 * 
 * @author wcyong
 * 
 * @date 2018-09-01
 */
public class BesMeterAmmeter implements BaseEntity<String>{
	/**
	 * 主键
	 */
	private String fGuid;
    /**
     * 系统名称 FK(BES_SBPZ_STRUCT. F_SYS_NAME)
     */
    @ExcelVOAttribute(name = "电表名称", column = "A")
    private String fSysName;

    /**
     * 别名
     */
    @ExcelVOAttribute(name = "电表别名", column = "B")
    private String fNickName;

    /**
     * 是否使用  不使用0使用1，只有使用才能与其通信
     */
    @ExcelVOAttribute(name = "使能", column = "F")
    private String fEnabled;

    /**
     * 所属区域
     */
    private String fSsqy;

    /**
     * 安装位置
     */
    @ExcelVOAttribute(name = "安装位置", column = "D")
    private String fAzwz;

    /**
     * 物理地址
     */
    private String fWldz;

    /**
     * 描述
     */
    @ExcelVOAttribute(name = "描述", column = "E")
    private String fDescription;

    /**
     * 设备类型编号
     */
    private String fSblxbh;

    /**
     * 型号
     */
    private String fType;

    /**
     * 通信波特率编号
     */
    private String fCommRate;

    /**
     * 通信波特率名称
     */
    private String fCommRateMc;

    /**
     * 通信协议类型 
     */
    private String fProtocolType;

    /**
     * 通信协议类型名称
     */
    private String fProtocolTypeMc;

    /**
     * 采集方案编号 
     */
    private String fCjfabh;

    /**
     * 采集方案名称
     */
    private String fCjfamc;

    /**
     * 电表类型编号(1：电表 , 5：静态电表)
     */
    private String fBlxbh;

    /**
     * 电表类型名称
     */
    private String fBlxmc;

    /**
     * 通信端口
     */
    private String fCommunicationPort;

    /**
     * 园区编号
     */
    private String fYqbh;

    /**
     * 变比
     */
    private String fPercentage;

    /**
     * 同步状态(0:已同步,1:未同步)
     */
    private String fAmmeterState;

    /**
     * 电表在线状态（0：在线；1：离线）
     */
    private String fOnlineState;

    /**
     * 电表在线状态（0：在线；1：离线）有异常
     */
    private String fOnlinestate;

    /**
     * 创建日期
     */
    private String fCrdate;

    /**
     * 修改日期
     */

    private String fChdate;
    
    /**
     * 静态电表guid
     */
    
    private String fJtguid;
    /**
     * 静态电表数据
     * @return
     */
    @ExcelVOAttribute(name = "数据", column = "C")
    private String fData;
    
    public String getFguid() {
		return fGuid;
	}

	public void setFguid(String fguid) {
		this.fGuid = fguid;
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

    public String getfEnabled() {
        return fEnabled;
    }

    public void setfEnabled(String fEnabled) {
        this.fEnabled = fEnabled == null ? null : fEnabled.trim();
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

    public String getfSblxbh() {
        return fSblxbh;
    }

    public void setfSblxbh(String fSblxbh) {
        this.fSblxbh = fSblxbh == null ? null : fSblxbh.trim();
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

    public String getfCommRateMc() {
        return fCommRateMc;
    }

    public void setfCommRateMc(String fCommRateMc) {
        this.fCommRateMc = fCommRateMc == null ? null : fCommRateMc.trim();
    }

    public String getfProtocolType() {
        return fProtocolType;
    }

    public void setfProtocolType(String fProtocolType) {
        this.fProtocolType = fProtocolType == null ? null : fProtocolType.trim();
    }

    public String getfProtocolTypeMc() {
        return fProtocolTypeMc;
    }

    public void setfProtocolTypeMc(String fProtocolTypeMc) {
        this.fProtocolTypeMc = fProtocolTypeMc == null ? null : fProtocolTypeMc.trim();
    }

    public String getfCjfabh() {
        return fCjfabh;
    }

    public void setfCjfabh(String fCjfabh) {
        this.fCjfabh = fCjfabh == null ? null : fCjfabh.trim();
    }

    public String getfCjfamc() {
        return fCjfamc;
    }

    public void setfCjfamc(String fCjfamc) {
        this.fCjfamc = fCjfamc == null ? null : fCjfamc.trim();
    }

    public String getfBlxbh() {
        return fBlxbh;
    }

    public void setfBlxbh(String fBlxbh) {
        this.fBlxbh = fBlxbh == null ? null : fBlxbh.trim();
    }

    public String getfBlxmc() {
        return fBlxmc;
    }

    public void setfBlxmc(String fBlxmc) {
        this.fBlxmc = fBlxmc == null ? null : fBlxmc.trim();
    }

    public String getfCommunicationPort() {
        return fCommunicationPort;
    }

    public void setfCommunicationPort(String fCommunicationPort) {
        this.fCommunicationPort = fCommunicationPort == null ? null : fCommunicationPort.trim();
    }

    public String getfYqbh() {
        return fYqbh;
    }

    public void setfYqbh(String fYqbh) {
        this.fYqbh = fYqbh == null ? null : fYqbh.trim();
    }

    public String getfPercentage() {
        return fPercentage;
    }

    public void setfPercentage(String fPercentage) {
        this.fPercentage = fPercentage == null ? null : fPercentage.trim();
    }

    public String getfAmmeterState() {
        return fAmmeterState;
    }

    public void setfAmmeterState(String fAmmeterState) {
        this.fAmmeterState = fAmmeterState == null ? null : fAmmeterState.trim();
    }

    public String getfOnlineState() {
        return fOnlineState;
    }

    public void setfOnlineState(String fOnlineState) {
        this.fOnlineState = fOnlineState == null ? null : fOnlineState.trim();
    }

    public String getfOnlinestate() {
        return fOnlinestate;
    }

    public void setfOnlinestate(String fOnlinestate) {
        this.fOnlinestate = fOnlinestate == null ? null : fOnlinestate.trim();
    }

    public String getfCrdate() {
        return fCrdate;
    }

    public void setfCrdate(String fCrdate) {
        this.fCrdate = fCrdate;
    }

    public String getfChdate() {
        return fChdate;
    }

    public void setfChdate(String fChdate) {
        this.fChdate = fChdate;
    }

	public String getfGuid() {
		return fGuid;
	}

	public void setfGuid(String fGuid) {
		this.fGuid = fGuid;
	}

	public String getfJtguid() {
		return fJtguid;
	}

	public void setfJtguid(String fJtguid) {
		this.fJtguid = fJtguid;
	}

	public String getfData() {
		return fData;
	}

	public void setfData(String fData) {
		this.fData = fData;
	}
    
}