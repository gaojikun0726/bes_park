package com.efounder.JEnterprise.model.systemcenter.Interfaceconfig;

import java.io.Serializable;

/**
 * @Author: wanghongjie
 * @Description:设备功能（接口管理模块）
 * @Date: Created in 14:56 2020/12/10
 * @Modified By:
 */
public class DeviceFunctionModel implements Serializable
{

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Integer id;

    /**
     * 设备类型id
     */
    private String deviceTypeId;

    /**
     * 功能代码
     */
    private String code;

    /**
     * 功能名称
     */
    private String name;

    /**
     * @Description: 单位
     */
    private String unit;

    /**
     * 备注
     */
    private String comments;

    private String createTime; // 创建时间

    private String updateTime; // 更新时间

    private String deviceType;//设备类型名称

    private String deviceFunctionPointID;//功能点位ID

    private String functionPointName;//功能点位的名称

    private String value;//功能点位的值

    private String sysName;//功能点位的系统名称

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(String deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }


    public String getFunctionPointName() {
        return functionPointName;
    }

    public void setFunctionPointName(String functionPointName) {
        this.functionPointName = functionPointName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getDeviceFunctionPointID() {
        return deviceFunctionPointID;
    }

    public void setDeviceFunctionPointID(String deviceFunctionPointID) {
        this.deviceFunctionPointID = deviceFunctionPointID;
    }
}
