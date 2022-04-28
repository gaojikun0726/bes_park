package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;

/**
 * AI基础信息
 * @author
 *
 */
public class BesDoPoint implements BaseEntity<String>{

	private static final long serialVersionUID = -5439179631623866737L;

    private String fGuid; // 主键
    private String fSbid; // 设备id，用于下位机通讯
    private String fStructId; // 设备树id
    private String fSysNameOld; // 实际下发下位机的名称
    private String fSysName; // 系统名称
    private String fNickName; // 别名
    private String fEnabled; // 是否可用  不使用0，使用1，只有使用才与其通信
    private String fChannelIndex; // 通道索引
    private String fNodeType; // 点类型FK(BES_SBTREE_NODE. F_NODE_TYPE)
    private String fReversed; // 是否反向   否0，是1
    private String fSourced;//是否有源   否0，是1
    private String fWorkMode; // 工作模式   自动0，手动1
    private String fAlarmEnable; // 报警使能   不使能0，使能1
    private String fAlarmType; // 报警类型  不报警0，标准报警1，增强报警2
    private String fCloseState;//闭合状态 闭合正常，0不闭合，1闭合
    private String fAlarmPriority; // 报警优先级  报警优先级，只有对于采用标准报警的点才有此配置项。危及人身安全0;严重 1;危机安全 2
    private String fInitVal; // 初始值
    private String fDescription; // 描述
    private String fYqbh; // 园区编号
    private String fCrdate; // 创建日期
    private String fChdate; // 修改日期
    private String fDoState;//同步状态(0:未同步,1:已同步)
    private String fFaultState;//故障状态(0:否;1:是)

    public String getfGuid() {
        return fGuid;
    }

    public void setfGuid(String fGuid) {
        this.fGuid = fGuid;
    }

    public String getfSbid() {
        return fSbid;
    }

    public void setfSbid(String fSbid) {
        this.fSbid = fSbid;
    }

    public String getfStructId() {
        return fStructId;
    }

    public void setfStructId(String fStructId) {
        this.fStructId = fStructId;
    }

    public String getfSysNameOld() {
        return fSysNameOld;
    }

    public void setfSysNameOld(String fSysNameOld) {
        this.fSysNameOld = fSysNameOld;
    }

    public String getfSysName() {
        return fSysName;
    }

    public void setfSysName(String fSysName) {
        this.fSysName = fSysName;
    }

    public String getfNickName() {
        return fNickName;
    }

    public void setfNickName(String fNickName) {
        this.fNickName = fNickName;
    }

    public String getfEnabled() {
        return fEnabled;
    }

    public void setfEnabled(String fEnabled) {
        this.fEnabled = fEnabled;
    }

    public String getfChannelIndex() {
        return fChannelIndex;
    }

    public void setfChannelIndex(String fChannelIndex) {
        this.fChannelIndex = fChannelIndex;
    }

    public String getfNodeType() {
        return fNodeType;
    }

    public void setfNodeType(String fNodeType) {
        this.fNodeType = fNodeType;
    }

    public String getfReversed() {
        return fReversed;
    }

    public void setfReversed(String fReversed) {
        this.fReversed = fReversed;
    }

    public String getfSourced() {
        return fSourced;
    }

    public void setfSourced(String fSourced) {
        this.fSourced = fSourced;
    }

    public String getfWorkMode() {
        return fWorkMode;
    }

    public void setfWorkMode(String fWorkMode) {
        this.fWorkMode = fWorkMode;
    }

    public String getfAlarmEnable() {
        return fAlarmEnable;
    }

    public void setfAlarmEnable(String fAlarmEnable) {
        this.fAlarmEnable = fAlarmEnable;
    }

    public String getfAlarmType() {
        return fAlarmType;
    }

    public void setfAlarmType(String fAlarmType) {
        this.fAlarmType = fAlarmType;
    }

    public String getfCloseState() {
        return fCloseState;
    }

    public void setfCloseState(String fCloseState) {
        this.fCloseState = fCloseState;
    }

    public String getfAlarmPriority() {
        return fAlarmPriority;
    }

    public void setfAlarmPriority(String fAlarmPriority) {
        this.fAlarmPriority = fAlarmPriority;
    }

    public String getfInitVal() {
        return fInitVal;
    }

    public void setfInitVal(String fInitVal) {
        this.fInitVal = fInitVal;
    }

    public String getfDescription() {
        return fDescription;
    }

    public void setfDescription(String fDescription) {
        this.fDescription = fDescription;
    }

    public String getfYqbh() {
        return fYqbh;
    }

    public void setfYqbh(String fYqbh) {
        this.fYqbh = fYqbh;
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

    public String getfDoState() {
        return fDoState;
    }

    public void setfDoState(String fDoState) {
        this.fDoState = fDoState;
    }

    public String getfFaultState() {
        return fFaultState;
    }

    public void setfFaultState(String fFaultState) {
        this.fFaultState = fFaultState;
    }
}