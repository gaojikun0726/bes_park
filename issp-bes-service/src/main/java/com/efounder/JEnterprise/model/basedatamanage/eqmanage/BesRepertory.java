package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2018-10-09
 */
public class BesRepertory implements BaseEntity<String>{
    private String fId;

    /**
     * 点的名称
     */
    private String fNodeName;

    /**
     * 点的单位
     */
    private String fUnit;

    /**
     * 点的id
     */
    private String fNodeId;

    /**
     * 场景模式的模式个数
     */
    private String fSize;

    /**
     * 每个指令表对应配置的内容（#隔开）
     */
    private String fParam;

    /**
     * 采集方式ID集合（数字0-9）和param一一对应 0 变化量采集 1 时间间隔
     */
    private String fZoneid;

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId == null ? null : fId.trim();
    }

    public String getfNodeName() {
        return fNodeName;
    }

    public void setfNodeName(String fNodeName) {
        this.fNodeName = fNodeName == null ? null : fNodeName.trim();
    }

    public String getfUnit() {
        return fUnit;
    }

    public void setfUnit(String fUnit) {
        this.fUnit = fUnit == null ? null : fUnit.trim();
    }

    public String getfNodeId() {
        return fNodeId;
    }

    public void setfNodeId(String fNodeId) {
        this.fNodeId = fNodeId == null ? null : fNodeId.trim();
    }

    public String getfSize() {
        return fSize;
    }

    public void setfSize(String fSize) {
        this.fSize = fSize == null ? null : fSize.trim();
    }

    public String getfParam() {
        return fParam;
    }

    public void setfParam(String fParam) {
        this.fParam = fParam == null ? null : fParam.trim();
    }

    public String getfZoneid() {
        return fZoneid;
    }

    public void setfZoneid(String fZoneid) {
        this.fZoneid = fZoneid == null ? null : fZoneid.trim();
    }
}