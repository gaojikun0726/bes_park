package com.efounder.JEnterprise.model.basedatamanage.deviceConfiguration;

import com.core.common.BaseEntity;
import com.framework.common.utils.ExcelVOAttribute;

/**
 * @Author: wanghongjie
 * @Description:
 * @Date: Created in 9:52 2020/9/17
 * @Modified By:
 */
public class besCouplerExcel implements BaseEntity<String> {
    private static final long serialVersionUID = -4964347669848268789L;
    @ExcelVOAttribute(name = "耦合器名称", column = "A")
    private String fSysName;
    @ExcelVOAttribute(name = "别名", column = "B")
    private String fNickName;
    @ExcelVOAttribute(name = "耦合器类型", column = "C")
    private String fNodeType;
    @ExcelVOAttribute(name = "安装位置", column = "D")
    private String fAzwz;
    @ExcelVOAttribute(name = "描述", column = "E")
    private String fDescription;
    @ExcelVOAttribute(name = "通信地址", column = "F")
    private String fAddr;
    @ExcelVOAttribute(name = "归属系统", column = "G")
    private String fBelongIprouter;


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

    public String getfBelongIprouter() {
        return fBelongIprouter;
    }

    public void setfBelongIprouter(String fBelongIprouter) {
        this.fBelongIprouter = fBelongIprouter;
    }

    public String getfType() {
        return fType;
    }

    public void setfType(String fType) {
        this.fType = fType;
    }
}
