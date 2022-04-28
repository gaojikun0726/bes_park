package org.ace.business.dto.edc;

import java.util.List;

/**
 * 电表采集参数定义
 * @author xiepufeng
 * @date 2020/4/14 17:47
 */
public class AmmeterCollectParamData
{
    // 电表序列号
    private Integer meterID;

    // 采集方法
    private CollectMethod electricDataCollectMethod;

    // 电表参数
    private AmmeterParam meterParameter;

    // 采集参数
    private List<ElectricParam> electricDataInfo;

    public Integer getMeterID()
    {
        return meterID;
    }

    public void setMeterID(Integer meterID)
    {
        this.meterID = meterID;
    }

    public CollectMethod getElectricDataCollectMethod()
    {
        return electricDataCollectMethod;
    }

    public void setElectricDataCollectMethod(CollectMethod electricDataCollectMethod)
    {
        this.electricDataCollectMethod = electricDataCollectMethod;
    }

    public AmmeterParam getMeterParameter()
    {
        return meterParameter;
    }

    public void setMeterParameter(AmmeterParam meterParameter)
    {
        this.meterParameter = meterParameter;
    }

    public List<ElectricParam> getElectricDataInfo()
    {
        return electricDataInfo;
    }

    public void setElectricDataInfo(List<ElectricParam> electricDataInfo)
    {
        this.electricDataInfo = electricDataInfo;
    }
}
