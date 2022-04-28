package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;

/**
 * AO基础信息
 * @author
 *
 */
public class BesAoPoint implements BaseEntity<String>{

	private static final long serialVersionUID = -5439179631623866737L;

    private String fGuid; // 主键
    private String fSbid; // 设备id，用于下位机通讯
    private String fStructId; // 设备树id
    private String fSysNameOld; // 实际下发下位机的名称
    private String fSysName; // 系统名称
    private String fNickName; // 别名
    private String fEnabled; // 是否可用  不使用0，使用1，只有使用才与其通信
    private String fChannelIndex; // 通道索引
    private String fEngineerUnit; // 工程单位
    private String fNodeType; // 点类型FK(BES_SBTREE_NODE. F_NODE_TYPE)
    private String fSinnalType; // 信号类型  0-10v 0  ;0-20mA 1;4-20mA 2
    private String fMinVal; // 最小值   传感器量程最小值
    private String fMaxVal; // 最大值
    private String fAccuracy; // 精度
    private String fReversed; // 是否反向   否0，是1
    private String fWorkMode; // 工作模式   自动0，手动1
    private String fAlarmEnable; // 报警使能   不使能0，使能1
    private String fAlarmType; // 报警类型  不报警0，标准报警1，增强报警2
    private String fHighLimit; // 高限报警  对于标准报警点，该点数值所允许达到的最高限值
    private String fLowLimit; // 低限报警   对于标准报警点，该点数值所允许达到的最低限值
    private String fAlarmPriority; // 报警优先级  报警优先级，只有对于采用标准报警的点才有此配置项。危及人身安全0;严重 1;危机安全 2
    private String fInitVal; // 初始值
    private String fDescription; // 描述
    private String fYqbh; // 园区编号
    private String fCrdate; // 创建日期
    private String fChdate; // 修改日期
    private String fAoState;//同步状态(0:未同步,1:已同步)
    private String fEnergystatics; // 能耗统计（0:是；1:否）
    private String fEnergyType; // 能源类型
    private Integer recordUploadPeriod; // 能耗上传周期记录

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

    public String getfEngineerUnit() {
        return fEngineerUnit;
    }

    public void setfEngineerUnit(String fEngineerUnit) {
        this.fEngineerUnit = fEngineerUnit;
    }

    public String getfNodeType() {
        return fNodeType;
    }

    public void setfNodeType(String fNodeType) {
        this.fNodeType = fNodeType;
    }

    public String getfSinnalType() {
        return fSinnalType;
    }

    public void setfSinnalType(String fSinnalType) {
        this.fSinnalType = fSinnalType;
    }

    public String getfMinVal() {
        return fMinVal;
    }

    public void setfMinVal(String fMinVal) {
        this.fMinVal = fMinVal;
    }

    public String getfMaxVal() {
        return fMaxVal;
    }

    public void setfMaxVal(String fMaxVal) {
        this.fMaxVal = fMaxVal;
    }

    public String getfAccuracy() {
        return fAccuracy;
    }

    public void setfAccuracy(String fAccuracy) {
        this.fAccuracy = fAccuracy;
    }

    public String getfReversed() {
        return fReversed;
    }

    public void setfReversed(String fReversed) {
        this.fReversed = fReversed;
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

    public String getfHighLimit() {
        return fHighLimit;
    }

    public void setfHighLimit(String fHighLimit) {
        this.fHighLimit = fHighLimit;
    }

    public String getfLowLimit() {
        return fLowLimit;
    }

    public void setfLowLimit(String fLowLimit) {
        this.fLowLimit = fLowLimit;
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

    public String getfAoState() {
        return fAoState;
    }

    public void setfAoState(String fAoState) {
        this.fAoState = fAoState;
    }

    public String getfEnergystatics() {
        return fEnergystatics;
    }

    public void setfEnergystatics(String fEnergystatics) {
        this.fEnergystatics = fEnergystatics;
    }

    public String getfEnergyType() {
        return fEnergyType;
    }

    public void setfEnergyType(String fEnergyType) {
        this.fEnergyType = fEnergyType;
    }

    public Integer getRecordUploadPeriod() {
        return recordUploadPeriod;
    }

    public void setRecordUploadPeriod(Integer recordUploadPeriod) {
        this.recordUploadPeriod = recordUploadPeriod;
    }
}