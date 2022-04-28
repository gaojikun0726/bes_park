package org.ace.business.dto.edc;

/**
 * 采集参数定义
 * @author xiepufeng
 * @date 2020/4/14 17:47
 */
public class CollectParamData
{
    // 电表序列号
    private Integer meterID;

    // 采集方法
    private CollectMethod electricDataCollectMethod;

    // 采集参数
    private ElectricParam electricDataInfo;

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

    public ElectricParam getElectricDataInfo()
    {
        return electricDataInfo;
    }

    public void setElectricDataInfo(ElectricParam electricDataInfo)
    {
        this.electricDataInfo = electricDataInfo;
    }
}
