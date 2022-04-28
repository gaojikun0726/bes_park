package com.efounder.JEnterprise.model.systemcenter.Interfaceconfig;

import java.io.Serializable;

/**
 * @author xiepufeng
 * @date 2020/12/18 15:06
 */
public class FunctionPointStateModel  implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 设备功能id
     */
    private Integer deviceFunctionId;

    /**
     * 系统名称
     */
    private String sysName;

    /**
     * 实时数据
     */
    private String value;

    public String getDeviceId()
    {
        return deviceId;
    }

    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
    }

    public Integer getDeviceFunctionId()
    {
        return deviceFunctionId;
    }

    public void setDeviceFunctionId(Integer deviceFunctionId)
    {
        this.deviceFunctionId = deviceFunctionId;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }
}
