package com.efounder.datareported.model.build;

import com.framework.common.utils.xmlprocessor.annotation.AttrXml;

/**
 * @author xiepufeng
 * @date 2020/11/9 15:03
 */
public class DataCenterBaseInfoModel
{
    /**
     * 数据中心代码
     */
    @AttrXml(name = "F_DataCenterID")
    private String dataCenterID;

    /**
     * 数据中心名称
     */
    @AttrXml(name = "F_DataCenterName")
    private String dataCenterName;

    /**
     * 数据中心类型 1-数据中心 2-数据中心中转站
     */
    @AttrXml(name = "F_DataCenterType")
    private Integer dataCenterType;

    /**
     * 数据中心管理单位名称
     */
    @AttrXml(name = "F_DataCenterManager")
    private String dataCenterManager;

    /**
     * 数据中心描述
     */
    @AttrXml(name = "F_DataCenterDesc")
    private String dataCenterDesc;

    /**
     * 业主联系人
     */
    @AttrXml(name = "F_DataCenterContract")
    private String dataCenterContract;

    /**
     * 数据中心联系方式
     */
    @AttrXml(name = "F_DataCenterTel")
    private String dataCenterTel;

    public String getDataCenterID()
    {
        return dataCenterID;
    }

    public void setDataCenterID(String dataCenterID)
    {
        this.dataCenterID = dataCenterID;
    }

    public String getDataCenterName()
    {
        return dataCenterName;
    }

    public void setDataCenterName(String dataCenterName)
    {
        this.dataCenterName = dataCenterName;
    }

    public Integer getDataCenterType()
    {
        return dataCenterType;
    }

    public void setDataCenterType(Integer dataCenterType)
    {
        this.dataCenterType = dataCenterType;
    }

    public String getDataCenterManager()
    {
        return dataCenterManager;
    }

    public void setDataCenterManager(String dataCenterManager)
    {
        this.dataCenterManager = dataCenterManager;
    }

    public String getDataCenterDesc()
    {
        return dataCenterDesc;
    }

    public void setDataCenterDesc(String dataCenterDesc)
    {
        this.dataCenterDesc = dataCenterDesc;
    }

    public String getDataCenterContract()
    {
        return dataCenterContract;
    }

    public void setDataCenterContract(String dataCenterContract)
    {
        this.dataCenterContract = dataCenterContract;
    }

    public String getDataCenterTel()
    {
        return dataCenterTel;
    }

    public void setDataCenterTel(String dataCenterTel)
    {
        this.dataCenterTel = dataCenterTel;
    }
}
