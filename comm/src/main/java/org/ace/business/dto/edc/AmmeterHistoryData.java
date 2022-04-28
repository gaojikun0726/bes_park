package org.ace.business.dto.edc;

import java.util.List;

/**
 * 接收历史数据定义
 * @author xiepufeng
 * @date 2020/4/15 7:30
 */
public class AmmeterHistoryData
{
    private Integer hisDataHourCount;

    private List<AmmeterData> meterElectricHisData;

    public Integer getHisDataHourCount()
    {
        return hisDataHourCount;
    }

    public void setHisDataHourCount(Integer hisDataHourCount)
    {
        this.hisDataHourCount = hisDataHourCount;
    }

    public List<AmmeterData> getMeterElectricHisData()
    {
        return meterElectricHisData;
    }

    public void setMeterElectricHisData(List<AmmeterData> meterElectricHisData)
    {
        this.meterElectricHisData = meterElectricHisData;
    }
}
