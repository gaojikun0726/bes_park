package com.efounder.JEnterprise.model.systemcenter.Interfaceconfig;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @Author: wanghongjie
 * @Description:设备功能（接口管理模块）
 * @Date: Created in 14:56 2020/12/10
 * @Modified By:
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class DeviceFunctionPointModel implements Serializable
{

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Integer id;

    /**
     * 设备功能id
     */
    private Integer deviceFunctionId;

    /**
     * 所属设备id
     */
    private String deviceId;

    /**
     * 属性名称
     */
    private String name;


    /**
     * 系统名称(old)
     */
    private String sysName;

    /**
     * 实时数据
     */
    private String value;

    /**
     * 备注
     */
    private String comments;

    private String createTime; // 创建时间

    private String updateTime; // 更新时间

    private String deviceName;//设备(名称)

    private String deviceFunctionName;//设备功能(名称)

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceFunctionName() {
        return deviceFunctionName;
    }

    public void setDeviceFunctionName(String deviceFunctionName) {
        this.deviceFunctionName = deviceFunctionName;
    }

    public Integer getDeviceFunctionId() {
        return deviceFunctionId;
    }

    public void setDeviceFunctionId(Integer deviceFunctionId) {
        this.deviceFunctionId = deviceFunctionId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
