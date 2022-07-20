package com.efounder.JEnterprise.model.systemcenter.Interfaceconfig;

import java.io.Serializable;

/**
 * @Author: wanghongjie
 * @Description:设备异常日志
 * @Date: Created in 14:56 2020/12/10
 * @Modified By:
 */
public class DeviceExceptionLogModel implements Serializable
{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 设备类型id
     */
    private String deviceType;
    /**
     * 设备类型名称
     */
    private String deviceTypeName;

    /**
     * 设备功能类型
     */
    private String deviceFunctionType;
    /**
     * 设备功能类型名称
     */
    private String deviceFunctionTypeName;

    /**
     * 异常信息
     */
    private String exceptionLog;

    /**
     * 异常时间
     */
    private String exceptionTime;

    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 设备id
     */
    private String deviceId;
    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 区域id
     */
    private String positionId;
    /**
     * 区域名称
     */
    private String positionName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    public String getDeviceFunctionType() {
        return deviceFunctionType;
    }

    public void setDeviceFunctionType(String deviceFunctionType) {
        this.deviceFunctionType = deviceFunctionType;
    }

    public String getDeviceFunctionTypeName() {
        return deviceFunctionTypeName;
    }

    public void setDeviceFunctionTypeName(String deviceFunctionTypeName) {
        this.deviceFunctionTypeName = deviceFunctionTypeName;
    }

    public String getExceptionLog() {
        return exceptionLog;
    }

    public void setExceptionLog(String exceptionLog) {
        this.exceptionLog = exceptionLog;
    }

    public String getExceptionTime() {
        return exceptionTime;
    }

    public void setExceptionTime(String exceptionTime) {
        this.exceptionTime = exceptionTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}
