package com.efounder.JEnterprise.model.systemcenter.Interfaceconfig;

import java.io.Serializable;

/**
 * @Author: wanghongjie
 * @Description:设备配置（接口管理模块）
 * @Date: Created in 14:56 2020/12/10
 * @Modified By:
 */
public class DeviceConfigurationModel implements Serializable
{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 设备类型id
     */
    private String deviceTypeId;

    /**
     * 设备代码
     */
    private String code;

    /**
     * 设备名称
     */
    private String name;

    /**
     * 位置
     */
    private String site;

    /**
     * 备注
     */
    private String comments;

    private String createTime; // 创建时间

    private String updateTime; // 更新时间

    private String deviceType;//设备类型名称

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
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
}
