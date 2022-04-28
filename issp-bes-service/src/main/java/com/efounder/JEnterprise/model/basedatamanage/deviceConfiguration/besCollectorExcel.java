package com.efounder.JEnterprise.model.basedatamanage.deviceConfiguration;

import com.core.common.BaseEntity;
import com.framework.common.utils.ExcelVOAttribute;

/**
 * @Author: wanghongjie
 * @Description:
 * @Date: Created in 10:28 2020/9/2
 * @Modified By:
 */
public class besCollectorExcel implements BaseEntity<String> {

    private static final long serialVersionUID = -8222656874382683079L;

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
    @ExcelVOAttribute(name = "采集周期", column = "G")
    private String fCollCycle;
    @ExcelVOAttribute(name = "点类型", column = "H")
    private String fNodeType;
    @ExcelVOAttribute(name = "历史数据保存周期", column = "I")
    private String fHisDataSaveCycle;
    @ExcelVOAttribute(name = "园区编号", column = "J")
    private String fYqbh;
    @ExcelVOAttribute(name = "上传采样周期", column = "K")
    private String fUploadCycle;
    @ExcelVOAttribute(name = "默认网关", column = "L")
    private String fGateway;
    @ExcelVOAttribute(name = "子网掩码", column = "M")
    private String fMask;
    @ExcelVOAttribute(name = "主机ip", column = "N")
    private String fIpMaster;
    @ExcelVOAttribute(name = "主机端口", column = "O")
    private String fPortMaster;


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

    public String getfCollCycle() {
        return fCollCycle;
    }

    public void setfCollCycle(String fCollCycle) {
        this.fCollCycle = fCollCycle;
    }

    public String getfNodeType() {
        return fNodeType;
    }

    public void setfNodeType(String fNodeType) {
        this.fNodeType = fNodeType;
    }

    public String getfHisDataSaveCycle() {
        return fHisDataSaveCycle;
    }

    public void setfHisDataSaveCycle(String fHisDataSaveCycle) {
        this.fHisDataSaveCycle = fHisDataSaveCycle;
    }

    public String getfYqbh() {
        return fYqbh;
    }

    public void setfYqbh(String fYqbh) {
        this.fYqbh = fYqbh;
    }

    public String getfUploadCycle() {
        return fUploadCycle;
    }

    public void setfUploadCycle(String fUploadCycle) {
        this.fUploadCycle = fUploadCycle;
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
}
