package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: wanghongjie
 * @Description:模块表
 * @Date: Created in 16:18 2020/7/6
 * @Modified By:
 */
public class BesModule implements BaseEntity<Serializable> {
    /**
     * 模块的设备id
     */
    private String fSbid;
    /**
     * 设备配置的fguid
     */
    private String fStructId;
    /**
     * 实际下发下位机的名称
     */
    private String fSysNameOld;
    /**
     * 系统名称
     */
    private String fSysName;
    /**
     * 别名
     */
    private String fNickName;
    /**
     * 点类型FK
     */
    private String fNodeType;
    /**
     * 是否使用
     */
    private String fEnabled;
    /**
     * 模块型号
     */
    private String fType;
    /**
     * 安装位置
     */
    private String fAzwz;
    /**
     * 描述
     */
    private String fDescription;
    /**
     * 通信地址
     */
    private String fAddr;
    /**
     * 模块同步状态
     */
    private String fModuleState;
    /**
     * 在线状态
     */
    private String fOnlineState;
    /**
     * 园区编号
     */
    private String fYqbh;


    /**
     * 创建日期
     */
    private Date fCrdate;

    /**
     * 修改日期
     */
    private Date fChdate;

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

    public String getfModuleState() {
        return fModuleState;
    }

    public void setfModuleState(String fModuleState) {
        this.fModuleState = fModuleState;
    }

    public String getfOnlineState() {
        return fOnlineState;
    }

    public void setfOnlineState(String fOnlineState) {
        this.fOnlineState = fOnlineState;
    }

    public String getfYqbh() {
        return fYqbh;
    }

    public void setfYqbh(String fYqbh) {
        this.fYqbh = fYqbh;
    }

    public Date getfCrdate() {
        return fCrdate;
    }

    public void setfCrdate(Date fCrdate) {
        this.fCrdate = fCrdate;
    }

    public Date getfChdate() {
        return fChdate;
    }

    public void setfChdate(Date fChdate) {
        this.fChdate = fChdate;
    }
}
