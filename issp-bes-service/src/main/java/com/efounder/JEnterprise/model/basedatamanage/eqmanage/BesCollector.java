package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 采集器表
 * 
 * @author LvSihan
 * 
 * @date 2018-08-13
 */
public class BesCollector implements BaseEntity<Serializable>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7883977034676236387L;

	private String fGuid;

    /**
     * 设备id
     */
	private String fSbId;

    /**
     * 系统名称:FK(BES_SBPZ_STRUCT. F_SYS_NAME)
     */
    private String fSysName;

    /**
     * 别名
     */
    private String fNickName;

    /**
     * 是否使用:0是，1否，只有使用才能与其通信
     */
    private Integer fEnabled;

    /**
     * 所属区域
     */
    private String fSsqy;

    /**
     * 安装位置
     */
    private String fAzwz;

    /**
     * 描述
     */
    private String fDescription;

    /**
     * 设备类型编号FK(BES_EQUIPMENT_MODE. F_TYPE)
     */
    private String fSblxbh;

    /**
     * 型号:FK(BES_ EQUIPMENT _TYPE. F_TYPE)
     */
    private String fType;

    /**
     * IP地址
     */
    private String fIpAddr;

    /**
     * 采集周期
     */
    private Integer fCollCycle;

    /**
     * 点类型:FK(BES_SBTREE_NODE. F_NODE_TYPE)
     */
    private String fNodeType;

    /**
     * 历史数据保存周期
     */
    private Integer fHisDataSaveCycle;

    /**
     * 采集器端口号
     */
    private String fPort;

    /**
     * 园区编号
     */
    private String fParkYqbh;

    /**
     * 状态(0:未同步,1:已同步)
     */
    private String fCollectorState;

    /**
     * 采集器在线状态（0：在线；1：离线）
     */
    private String fOnlinestate;

    /**
     * 创建日期
     */
    private Date fCrdate;

    /**
     * 修改日期
     */
    private Date fChdate;

    /**
     * 采集器在线状态（1：在线；0：离线）
     */
    private String fOnlineState;
    /**
     * 上传采样周期
     */
    private String fUploadCycle;

    /**
     * 通道id
     */
    private String fChannelId;
    /**
     * 默认网关
     */
    private String fGateway;
    /**
     * 子网掩码
     */
    private String fMask;
    /**
     * 主机ip地址
     */
    private String fIpMaster;
    /**
     * 主机端口
     */
    private String fPortMaster;

    public String getfGuid() {
        return fGuid;
    }

    public void setfGuid(String fGuid) {
        this.fGuid = fGuid == null ? null : fGuid.trim();
    }

    public String getfSbId()
    {
        return fSbId;
    }

    public void setfSbId(String fSbId)
    {
        this.fSbId = fSbId;
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

    public String getfIpAddr() {
        return fIpAddr;
    }

    public void setfIpAddr(String fIpAddr) {
        this.fIpAddr = fIpAddr == null ? null : fIpAddr.trim();
    }

    public Integer getfCollCycle() {
        return fCollCycle;
    }

    public void setfCollCycle(Integer fCollCycle) {
        this.fCollCycle = fCollCycle;
    }

    public String getfNodeType() {
        return fNodeType;
    }

    public void setfNodeType(String fNodeType) {
        this.fNodeType = fNodeType == null ? null : fNodeType.trim();
    }

    public Integer getfHisDataSaveCycle() {
        return fHisDataSaveCycle;
    }

    public void setfHisDataSaveCycle(Integer fHisDataSaveCycle) {
        this.fHisDataSaveCycle = fHisDataSaveCycle;
    }

    public String getfPort() {
        return fPort;
    }

    public void setfPort(String fPort) {
        this.fPort = fPort == null ? null : fPort.trim();
    }

    public String getfParkYqbh() {
        return fParkYqbh;
    }

    public void setfParkYqbh(String fParkYqbh) {
        this.fParkYqbh = fParkYqbh == null ? null : fParkYqbh.trim();
    }

    public String getfCollectorState() {
        return fCollectorState;
    }

    public void setfCollectorState(String fCollectorState) {
        this.fCollectorState = fCollectorState == null ? null : fCollectorState.trim();
    }

    public String getfOnlinestate() {
        return fOnlinestate;
    }

    public void setfOnlinestate(String fOnlinestate) {
        this.fOnlinestate = fOnlinestate == null ? null : fOnlinestate.trim();
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

    public String getfOnlineState() {
        return fOnlineState;
    }

    public void setfOnlineState(String fOnlineState) {
        this.fOnlineState = fOnlineState == null ? null : fOnlineState.trim();
    }

    public String getfChannelId()
    {
        return fChannelId;
    }

    public void setfChannelId(String fChannelId)
    {
        this.fChannelId = fChannelId;
    }

    public String getfGateway()
    {
        return fGateway;
    }

    public void setfGateway(String fGateway)
    {
        this.fGateway = fGateway;
    }

    public String getfMask()
    {
        return fMask;
    }

    public void setfMask(String fMask)
    {
        this.fMask = fMask;
    }

    public String getfIpMaster()
    {
        return fIpMaster;
    }

    public void setfIpMaster(String fIpMaster)
    {
        this.fIpMaster = fIpMaster;
    }

    public String getfPortMaster()
    {
        return fPortMaster;
    }

    public void setfPortMaster(String fPortMaster)
    {
        this.fPortMaster = fPortMaster;
    }

    public String getfUploadCycle() {
        return fUploadCycle;
    }

    public void setfUploadCycle(String fUploadCycle) {
        this.fUploadCycle = fUploadCycle;
    }
}