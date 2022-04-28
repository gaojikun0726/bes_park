package com.efounder.JEnterprise.model.systemcenter.Interfaceconfig;

import java.io.Serializable;

/**
 * @Author: wanghongjie
 * @Description:设备功能值（接口管理模块）
 * @Date: Created in 14:56 2021/05/28
 * @Modified By:
 */
public class DeviceFunctionValueModel implements Serializable
{

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Integer id;

    /**
     * 设备功能id
     */
    private String deviceFunctionId;

    /**
     * 功能值
     */
    private String value;

    /**
     * 名称
     */
    private String name;

    /**
     * 备注
     */
    private String comments;


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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDeviceFunctionId() {
        return deviceFunctionId;
    }

    public void setDeviceFunctionId(String deviceFunctionId) {
        this.deviceFunctionId = deviceFunctionId;
    }
}
