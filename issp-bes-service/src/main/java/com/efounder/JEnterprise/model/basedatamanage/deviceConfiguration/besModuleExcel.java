package com.efounder.JEnterprise.model.basedatamanage.deviceConfiguration;

import com.core.common.BaseEntity;
import com.framework.common.utils.ExcelVOAttribute;

/**
 * @Author: wanghongjie
 * @Description:
 * @Date: Created in 15:34 2020/9/12
 * @Modified By:
 */
public class besModuleExcel implements BaseEntity<String> {
    private static final long serialVersionUID = 1274967545527177049L;

    @ExcelVOAttribute(name = "系统名称", column = "A")
    private String fSysName;
    @ExcelVOAttribute(name = "别名", column = "B")
    private String fNickName;
    @ExcelVOAttribute(name = "节点类型", column = "C")
    private String fNodeType;
    @ExcelVOAttribute(name = "模块型号", column = "D")
    private String fModuleType;
    @ExcelVOAttribute(name = "安装位置", column = "E")
    private String fAzwz;
    @ExcelVOAttribute(name = "描述", column = "F")
    private String fDescription;
    @ExcelVOAttribute(name = "通信地址", column = "G")
    private String fAddr;
    @ExcelVOAttribute(name = "园区编号", column = "H")
    private String fYqbh;
    @ExcelVOAttribute(name = "使能状态", column = "I")
    private String fEnabled;

    private String fSbid;
    private String fStructId;
    private String fType;


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

    public String getfModuleType() {
        return fModuleType;
    }

    public void setfModuleType(String fModuleType) {
        this.fModuleType = fModuleType;
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

    public String getfAddr() {
        return fAddr;
    }

    public void setfAddr(String fAddr) {
        this.fAddr = fAddr;
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

    public String getfEnabled() {
        return fEnabled;
    }

    public void setfEnabled(String fEnabled) {
        this.fEnabled = fEnabled;
    }

    public String getfType() {
        return fType;
    }

    public void setfType(String fType) {
        this.fType = fType;
    }
}
