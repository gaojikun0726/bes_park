package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;

/**
 * @Author: wanghongjie
 * @Description:所有点位表的model
 * @Date: Created in 14:33 2020/6/15
 * @Modified By:
 */
public class BesPointStruct implements BaseEntity<String> {
  private int fGuid;              //
  private String fSbid;           //设备id，用于下位机通讯
  private String fStructId;       //设备树id
  private String fSysNameOld;     //下发到下位机的系统名称
  private String fSysName;        //系统名称,用于维持设备树
  private String fNickName;       //别名
  private String fNodeType;       //点类型
  private String fChannelIndex;   //通道索引
  private String fEnabled;        //是否可用
  private String fReversed;       //是否反向
  private String fSourced;        //是否有源
  private String fWorkMode;       //工作模式
  private String fAlarmEnable;    //报警使能
  private String fAlarmType;      //报警类型
  private String fCloseState;     //闭合状态
  private String fAlarmPriority;  //报警优先级
  private String fInitVal;        //初始值
  private String fDescription;    //描述
  private String fYqbh;           //园区编号
  private String fCrdate;         //创建日期
  private String fChdate;         //修改日期

  private String fEngineerUnit;   //工程单位
  private String fSinnalType;     //信号类型
  private String fMinVal;         //最大值
  private String fMaxVal;         //最小值
  private String fAccuracy;       //精度
  private String fHighLimit;      //高限报警
  private String fLowLimit;       //低限报警
  private String fEnergystatics;  //能耗统计（0:是；1:否）
  private String fStaticstime;    //统计周期（0:15分钟；1:1小时；2:1天）

  private int fWhetherToAlarmHigh;    //是否报警的点位标识(高限)
  private int fWhetherToAlarmLow;    //是否报警的点位标识(底限)

  private String fAiState;      //AI同步状态(0:未同步,1:已同步)
  private String fEnergyType;   //能源类型
  private String fAoState;      //AO同步状态(0:未同步,1:已同步)
  private String fDiState;      //DI同步状态(0:未同步,1:已同步)
  private String fDoState;      //DO同步状态(0:未同步,1:已同步)
  private String fFaultState;   //故障状态(0:否;1:是)
  private String fSyncState;    //虚点状态(0:未同步,1:已同步)

  public int getfGuid() {
    return fGuid;
  }

  public void setfGuid(int fGuid) {
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

  public String getfNodeType() {
    return fNodeType;
  }

  public void setfNodeType(String fNodeType) {
    this.fNodeType = fNodeType;
  }

  public String getfChannelIndex() {
    return fChannelIndex;
  }

  public void setfChannelIndex(String fChannelIndex) {
    this.fChannelIndex = fChannelIndex;
  }

  public String getfEnabled() {
    return fEnabled;
  }

  public void setfEnabled(String fEnabled) {
    this.fEnabled = fEnabled;
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

  public String getfEngineerUnit() {
    return fEngineerUnit;
  }

  public void setfEngineerUnit(String fEngineerUnit) {
    this.fEngineerUnit = fEngineerUnit;
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

  public String getfEnergystatics() {
    return fEnergystatics;
  }

  public void setfEnergystatics(String fEnergystatics) {
    this.fEnergystatics = fEnergystatics;
  }

  public String getfStaticstime() {
    return fStaticstime;
  }

  public void setfStaticstime(String fStaticstime) {
    this.fStaticstime = fStaticstime;
  }

  public int getfWhetherToAlarmHigh() {
    return fWhetherToAlarmHigh;
  }

  public void setfWhetherToAlarmHigh(int fWhetherToAlarmHigh) {
    this.fWhetherToAlarmHigh = fWhetherToAlarmHigh;
  }

  public int getfWhetherToAlarmLow() {
    return fWhetherToAlarmLow;
  }

  public void setfWhetherToAlarmLow(int fWhetherToAlarmLow) {
    this.fWhetherToAlarmLow = fWhetherToAlarmLow;
  }

  public String getfAiState() {
    return fAiState;
  }

  public void setfAiState(String fAiState) {
    this.fAiState = fAiState;
  }

  public String getfEnergyType() {
    return fEnergyType;
  }

  public void setfEnergyType(String fEnergyType) {
    this.fEnergyType = fEnergyType;
  }

  public String getfAoState() {
    return fAoState;
  }

  public void setfAoState(String fAoState) {
    this.fAoState = fAoState;
  }

  public String getfDiState() {
    return fDiState;
  }

  public void setfDiState(String fDiState) {
    this.fDiState = fDiState;
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

  public String getfSyncState() {
    return fSyncState;
  }

  public void setfSyncState(String fSyncState) {
    this.fSyncState = fSyncState;
  }
}
