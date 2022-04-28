package com.efounder.JEnterprise.model.basedatamanage.eqmanage;

import com.core.common.BaseEntity;

/**
 * 电表原始数据表
 * 
 * @author wcyong
 * 
 * @date 2018-11-01
 */
public class OriginalData implements BaseEntity<String> {
    /**
     * 标识符
     */
    private String fId;

    /**
     * 电表系统名称 FK(BES_AMMETER. F_SYS_NAME)
     */
    private String fDbsysName;

    /**
     * 电能编号 FK(BES_ELECTRIC_PARAMS. F_NHBH)
     */
    private String fDnbh;

    /**
     * 原始数据
     */
    private Double fData;

    /**
     * 采集时间
     */
    private String fCjsj;

    /**
     * 数据类型（比如：能耗、电压、电流）
     */
    private String fType;

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId == null ? null : fId.trim();
    }

    public String getfDbsysName() {
        return fDbsysName;
    }

    public void setfDbsysName(String fDbsysName) {
        this.fDbsysName = fDbsysName == null ? null : fDbsysName.trim();
    }

    public String getfDnbh() {
        return fDnbh;
    }

    public void setfDnbh(String fDnbh) {
        this.fDnbh = fDnbh == null ? null : fDnbh.trim();
    }

    public String getfType() {
        return fType;
    }

    public void setfType(String fType) {
        this.fType = fType == null ? null : fType.trim();
    }

    public Double getfData() {
        return fData;
    }

    public void setfData(Double fData) {
        this.fData = fData;
    }

    public String getfCjsj() {
        return fCjsj;
    }

    public void setfCjsj(String fCjsj) {
        this.fCjsj = fCjsj;
    }
}