package com.efounder.JEnterprise.model.basedatamanage.deviceConfiguration;

import com.core.common.BaseEntity;
import com.framework.common.utils.ExcelVOAttribute;


/**
 * @Author: wanghongjie
 * @Description:
 * @Date: Created in 11:39 2020/8/26
 * @Modified By:设备配置表的信息
 */
public class sbdyStruct implements BaseEntity<String> {
    private static final long serialVersionUID = -4503397473229347683L;
    @ExcelVOAttribute(name = "节点类型", column = "A")
    private String fType;
    @ExcelVOAttribute(name = "系统名称", column = "B")
    private String fSysname;
    @ExcelVOAttribute(name = "别名", column = "C")
    private String fNickname;
    @ExcelVOAttribute(name = "描述", column = "D")
    private String fDescription;
    @ExcelVOAttribute(name = "所属系统", column = "E")
    private String fNodeattribution;
    @ExcelVOAttribute(name = "父节点名称", column = "F")
    private String fPsysname;

    public String getfType() {
        return fType;
    }

    public void setfType(String fType) {
        this.fType = fType;
    }

    public String getfSysname() {
        return fSysname;
    }

    public void setfSysname(String fSysname) {
        this.fSysname = fSysname;
    }

    public String getfNickname() {
        return fNickname;
    }

    public void setfNickname(String fNickname) {
        this.fNickname = fNickname;
    }

    public String getfDescription() {
        return fDescription;
    }

    public void setfDescription(String fDescription) {
        this.fDescription = fDescription;
    }

    public String getfNodeattribution() {
        return fNodeattribution;
    }

    public void setfNodeattribution(String fNodeattribution) {
        this.fNodeattribution = fNodeattribution;
    }

    public String getfPsysname() {
        return fPsysname;
    }

    public void setfPsysname(String fPsysname) {
        this.fPsysname = fPsysname;
    }
}
