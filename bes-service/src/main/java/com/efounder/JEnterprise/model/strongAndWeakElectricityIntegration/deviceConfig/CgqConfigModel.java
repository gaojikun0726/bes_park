package com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.deviceConfig;

import java.io.Serializable;

/**
 *
 * 传感器配置
 *
 * @author wzx
 *
 */
public class CgqConfigModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer    id;                   // 主键
    private String     name;                 // 传感器名
    private String     cgqType;          // 传感器类型
    private String     cgqDqz;        // 传感器当前值
    private String     cgqDqzDisplay;          // 传感器当前值展示

    public void setCgqType(String cgqType) {
        this.cgqType = cgqType;
    }

    public String getCgqDqz() {
        return cgqDqz;
    }

    public void setCgqDqz(String cgqDqz) {
        this.cgqDqz = cgqDqz;
    }

    public String getCgqDqzDisplay() {
        return cgqDqzDisplay;
    }

    public void setCgqDqzDisplay(String cgqDqzDisplay) {
        this.cgqDqzDisplay = cgqDqzDisplay;
    }

    public String getCgqType() {
        return cgqType;
    }

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


}
