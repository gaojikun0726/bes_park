package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;

/**
 * 场景模式信息表
 * 
 * @author wcyong
 * 
 * @date 2018-10-09
 */
public class BesZonemode implements BaseEntity<String>{
    private String fId;

    /**
     * 模式名称
     */
    private String fName;

    /**
     * 模式类型 0 表示控制私有 1 表示控制共有 2采集公有 3 采集私有
     */
    private String fType;

    /**
     * 场景模式的模式个数
     */
    private String fSize;

    /**
     * 描述信息
     */
    private String fDescription;

    /**
     * 场景对应的内容（#隔开）
     */
    private String fParam;

    /**
     * 场景ID
     */
    private String fZoneid;

    /**
     * 被使用次数
     */
    private String fUsedNum;

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId == null ? null : fId.trim();
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName == null ? null : fName.trim();
    }

    public String getfType() {
        return fType;
    }

    public void setfType(String fType) {
        this.fType = fType == null ? null : fType.trim();
    }

    public String getfSize() {
        return fSize;
    }

    public void setfSize(String fSize) {
        this.fSize = fSize == null ? null : fSize.trim();
    }

    public String getfDescription() {
        return fDescription;
    }

    public void setfDescription(String fDescription) {
        this.fDescription = fDescription == null ? null : fDescription.trim();
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

    public String getfUsedNum() {
        return fUsedNum;
    }

    public void setfUsedNum(String fUsedNum) {
        this.fUsedNum = fUsedNum == null ? null : fUsedNum.trim();
    }
}