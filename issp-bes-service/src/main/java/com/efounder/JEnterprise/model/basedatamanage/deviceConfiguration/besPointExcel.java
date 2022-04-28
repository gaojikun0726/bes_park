package com.efounder.JEnterprise.model.basedatamanage.deviceConfiguration;

import com.core.common.BaseEntity;
import com.framework.common.utils.ExcelVOAttribute;

/**
 * @Author: wanghongjie
 * @Description:
 * @Date: Created in 10:06 2020/9/14
 * @Modified By:
 */
public class besPointExcel implements BaseEntity<String> {
    private static final long serialVersionUID = -7498519345044968297L;

    @ExcelVOAttribute(name = "点位类型", column = "A")
    private String pointTypeName;
    @ExcelVOAttribute(name = "系统名称", column = "B")
    private String fSysNameOld;
    @ExcelVOAttribute(name = "别名", column = "C")
    private String fNickName;
    @ExcelVOAttribute(name = "使能状态", column = "D")
    private String fEnabled;
    @ExcelVOAttribute(name = "描述", column = "E")
    private String fDescription;
    @ExcelVOAttribute(name = "通道索引", column = "F")
    private String fChannelIndex;
    @ExcelVOAttribute(name = "工作模式", column = "G")
    private String fWorkMode;
    @ExcelVOAttribute(name = "正反向", column = "H")
    private String fReversed;
    @ExcelVOAttribute(name = "初始值", column = "I")
    private String fInitVal;
    @ExcelVOAttribute(name = "有效输入类型", column = "J")
    private String fSinnalType;
    @ExcelVOAttribute(name = "工程单位", column = "K")
    private String fEngineerUnit;
    @ExcelVOAttribute(name = "最高阀值", column = "L")
    private String fMaxVal;
    @ExcelVOAttribute(name = "最低阀值", column = "M")
    private String fMinVal;
    @ExcelVOAttribute(name = "精度", column = "N")
    private String fAccuracy;
    @ExcelVOAttribute(name = "有源无源", column = "O")
    private String fSourced;
    @ExcelVOAttribute(name = "报警是否启用", column = "P")
    private String fAlarmEnable;
    @ExcelVOAttribute(name = "报警类型", column = "Q")
    private String fAlarmType;
    @ExcelVOAttribute(name = "报警优先级", column = "R")
    private String fAlarmPriority;
    @ExcelVOAttribute(name = "高限报警值", column = "S")
    private String fHighLimit;
    @ExcelVOAttribute(name = "底限报警值", column = "T")
    private String fLowLimit;
    @ExcelVOAttribute(name = "报警触发", column = "U")
    private String fCloseState;
    @ExcelVOAttribute(name = "故障状态", column = "V")
    private String fFaultState;
    @ExcelVOAttribute(name = "园区编号", column = "W")
    private String fYqbh;
    @ExcelVOAttribute(name = "能耗是否统计", column = "X")
    private String fEnergystatics;

    private String fSbid;
    private String fStructId;
    private String fNodeType;
    private String fPointState;
    private String fSysName;

    public String getPointTypeName() {
        return pointTypeName;
    }

    public void setPointTypeName(String pointTypeName) {
        this.pointTypeName = pointTypeName;
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

    public String getfDescription() {
        return fDescription;
    }

    public void setfDescription(String fDescription) {
        this.fDescription = fDescription;
    }

    public String getfChannelIndex() {
        return fChannelIndex;
    }

    public void setfChannelIndex(String fChannelIndex) {
        this.fChannelIndex = fChannelIndex;
    }

    public String getfWorkMode() {
        return fWorkMode;
    }

    public void setfWorkMode(String fWorkMode) {
        this.fWorkMode = fWorkMode;
    }

    public String getfReversed() {
        return fReversed;
    }

    public void setfReversed(String fReversed) {
        this.fReversed = fReversed;
    }

    public String getfInitVal() {
        return fInitVal;
    }

    public void setfInitVal(String fInitVal) {
        this.fInitVal = fInitVal;
    }

    public String getfSinnalType() {
        return fSinnalType;
    }

    public void setfSinnalType(String fSinnalType) {
        this.fSinnalType = fSinnalType;
    }

    public String getfEngineerUnit() {
        return fEngineerUnit;
    }

    public void setfEngineerUnit(String fEngineerUnit) {
        this.fEngineerUnit = fEngineerUnit;
    }

    public String getfMaxVal() {
        return fMaxVal;
    }

    public void setfMaxVal(String fMaxVal) {
        this.fMaxVal = fMaxVal;
    }

    public String getfMinVal() {
        return fMinVal;
    }

    public void setfMinVal(String fMinVal) {
        this.fMinVal = fMinVal;
    }

    public String getfAccuracy() {
        return fAccuracy;
    }

    public void setfAccuracy(String fAccuracy) {
        this.fAccuracy = fAccuracy;
    }

    public String getfSourced() {
        return fSourced;
    }

    public void setfSourced(String fSourced) {
        this.fSourced = fSourced;
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

    public String getfAlarmPriority() {
        return fAlarmPriority;
    }

    public void setfAlarmPriority(String fAlarmPriority) {
        this.fAlarmPriority = fAlarmPriority;
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

    public String getfCloseState() {
        return fCloseState;
    }

    public void setfCloseState(String fCloseState) {
        this.fCloseState = fCloseState;
    }

    public String getfEnabled() {
        return fEnabled;
    }

    public void setfEnabled(String fEnabled) {
        this.fEnabled = fEnabled;
    }

    public String getfFaultState() {
        return fFaultState;
    }

    public void setfFaultState(String fFaultState) {
        this.fFaultState = fFaultState;
    }

    public String getfYqbh() {
        return fYqbh;
    }

    public void setfYqbh(String fYqbh) {
        this.fYqbh = fYqbh;
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

    public String getfNodeType() {
        return fNodeType;
    }

    public void setfNodeType(String fNodeType) {
        this.fNodeType = fNodeType;
    }

    public String getfSysNameOld() {
        return fSysNameOld;
    }

    public void setfSysNameOld(String fSysNameOld) {
        this.fSysNameOld = fSysNameOld;
    }

    public String getfPointState() {
        return fPointState;
    }

    public void setfPointState(String fPointState) {
        this.fPointState = fPointState;
    }

    public String getfEnergystatics() {
        return fEnergystatics;
    }

    public void setfEnergystatics(String fEnergystatics) {
        this.fEnergystatics = fEnergystatics;
    }
}
