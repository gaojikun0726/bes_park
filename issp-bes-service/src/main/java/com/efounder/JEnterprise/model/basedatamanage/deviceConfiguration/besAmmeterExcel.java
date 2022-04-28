package com.efounder.JEnterprise.model.basedatamanage.deviceConfiguration;

import com.core.common.BaseEntity;
import com.framework.common.utils.ExcelVOAttribute;

/**
 * @Author: wanghongjie
 * @Description:
 * @Date: Created in 14:46 2020/9/10
 * @Modified By:
 */
public class besAmmeterExcel implements BaseEntity<String> {

    private static final long serialVersionUID = 8355178720427337581L;

    @ExcelVOAttribute(name = "系统名称", column = "A")
    private String fSysName;
    @ExcelVOAttribute(name = "别名", column = "B")
    private String fNickName;
    @ExcelVOAttribute(name = "安装位置", column = "C")
    private String fAzwz;
    @ExcelVOAttribute(name = "物理地址", column = "D")
    private String fWldz;
    @ExcelVOAttribute(name = "描述", column = "E")
    private String fDescription;
    @ExcelVOAttribute(name = "电表类型编号", column = "F")
    private String fBlxbh;
    @ExcelVOAttribute(name = "通信波特率编号", column = "G")
    private String fCommRate;
    @ExcelVOAttribute(name = "通信协议编号", column = "H")
    private String fProtocolType;
    @ExcelVOAttribute(name = "采集方案编号", column = "I")
    private String fCjfabh;
    @ExcelVOAttribute(name = "通信端口", column = "J")
    private String fCommunicationPort;
    @ExcelVOAttribute(name = "园区编号", column = "K")
    private String fYqbh;
    @ExcelVOAttribute(name = "变比", column = "L")
    private String fPercentage;
    @ExcelVOAttribute(name = "数据位", column = "M")
    private String fComDataBit;
    @ExcelVOAttribute(name = "校验位", column = "N")
    private String fComParityBit;
    @ExcelVOAttribute(name = "停止位", column = "O")
    private String fComStopBit;
    @ExcelVOAttribute(name = "功能码", column = "P")
    private String fFunctionCode;

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

    public String getfAzwz() {
        return fAzwz;
    }

    public void setfAzwz(String fAzwz) {
        this.fAzwz = fAzwz;
    }

    public String getfWldz() {
        return fWldz;
    }

    public void setfWldz(String fWldz) {
        this.fWldz = fWldz;
    }

    public String getfDescription() {
        return fDescription;
    }

    public void setfDescription(String fDescription) {
        this.fDescription = fDescription;
    }

    public String getfCommRate() {
        return fCommRate;
    }

    public void setfCommRate(String fCommRate) {
        this.fCommRate = fCommRate;
    }

    public String getfProtocolType() {
        return fProtocolType;
    }

    public void setfProtocolType(String fProtocolType) {
        this.fProtocolType = fProtocolType;
    }

    public String getfCjfabh() {
        return fCjfabh;
    }

    public void setfCjfabh(String fCjfabh) {
        this.fCjfabh = fCjfabh;
    }

    public String getfCommunicationPort() {
        return fCommunicationPort;
    }

    public void setfCommunicationPort(String fCommunicationPort) {
        this.fCommunicationPort = fCommunicationPort;
    }

    public String getfYqbh() {
        return fYqbh;
    }

    public void setfYqbh(String fYqbh) {
        this.fYqbh = fYqbh;
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

    public String getfBlxbh() {
        return fBlxbh;
    }

    public void setfBlxbh(String fBlxbh) {
        this.fBlxbh = fBlxbh;
    }
}
