package com.efounder.JEnterprise.model.basedatamanage.deviceConfiguration;

import com.core.common.BaseEntity;
import com.framework.common.utils.ExcelVOAttribute;

/**
 * @Author: wanghongjie
 * @Description:
 * @Date: Created in 18:23 2020/9/11
 * @Modified By:
 */
public class besDDCExcel implements BaseEntity<String> {
    private static final long serialVersionUID = -5531208575364339828L;

    @ExcelVOAttribute(name = "系统名称", column = "A")
    private String fSysName;
    @ExcelVOAttribute(name = "别名", column = "B")
    private String fNickName;
    @ExcelVOAttribute(name = "所属区域", column = "C")
    private String fSsqy;
    @ExcelVOAttribute(name = "安装位置", column = "D")
    private String fAzwz;
    @ExcelVOAttribute(name = "描述", column = "E")
    private String fDescription;
    @ExcelVOAttribute(name = "IP地址", column = "F")
    private String fIpAddr;
    @ExcelVOAttribute(name = "点类型", column = "G")
    private String fNodeType;
    @ExcelVOAttribute(name = "园区编号", column = "H")
    private String fYqbh;
    @ExcelVOAttribute(name = "默认网关", column = "I")
    private String fGateway;
    @ExcelVOAttribute(name = "子网掩码", column = "J")
    private String fMask;
    @ExcelVOAttribute(name = "主机ip", column = "K")
    private String fIpMaster;
    @ExcelVOAttribute(name = "主机端口", column = "L")
    private String fPortMaster;
    @ExcelVOAttribute(name = "使能状态", column = "M")
    private String fEnabled;


    private String fGuid;
    private String fSbid;
    private String fPollStatus;
    private String fDdcState;
    private String fOnlineState;
    private String fChannelId;


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

    public String getfSsqy() {
        return fSsqy;
    }

    public void setfSsqy(String fSsqy) {
        this.fSsqy = fSsqy;
    }

    public String getfAzwz() {
        return fAzwz;
    }

    public void setfAzwz(String fAzwz) {
        this.fAzwz = fAzwz;
    }

    public String getfDescription() {
        return fDescription;
    }

    public void setfDescription(String fDescription) {
        this.fDescription = fDescription;
    }

    public String getfIpAddr() {
        return fIpAddr;
    }

    public void setfIpAddr(String fIpAddr) {
        this.fIpAddr = fIpAddr;
    }

    public String getfNodeType() {
        return fNodeType;
    }

    public void setfNodeType(String fNodeType) {
        this.fNodeType = fNodeType;
    }

    public String getfYqbh() {
        return fYqbh;
    }

    public void setfYqbh(String fYqbh) {
        this.fYqbh = fYqbh;
    }

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

    public String getfPollStatus() {
        return fPollStatus;
    }

    public void setfPollStatus(String fPollStatus) {
        this.fPollStatus = fPollStatus;
    }

    public String getfEnabled() {
        return fEnabled;
    }

    public void setfEnabled(String fEnabled) {
        this.fEnabled = fEnabled;
    }

    public String getfDdcState() {
        return fDdcState;
    }

    public void setfDdcState(String fDdcState) {
        this.fDdcState = fDdcState;
    }

    public String getfOnlineState() {
        return fOnlineState;
    }

    public void setfOnlineState(String fOnlineState) {
        this.fOnlineState = fOnlineState;
    }

    public String getfChannelId() {
        return fChannelId;
    }

    public void setfChannelId(String fChannelId) {
        this.fChannelId = fChannelId;
    }
}
