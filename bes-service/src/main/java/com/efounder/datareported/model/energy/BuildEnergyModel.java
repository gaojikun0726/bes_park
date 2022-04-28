package com.efounder.datareported.model.energy;

import com.framework.common.utils.xmlprocessor.annotation.AttrXml;

import java.util.List;

/**
 * 建筑能耗信息
 * @author xiepufeng
 * @date 2020/11/9 15:05
 */
public class BuildEnergyModel
{
    /**
     * 数据中心代码
     */
    @AttrXml(isProperty = true)
    private String id;

    /**
     * 能耗单项小时结果信息
     */
    @AttrXml(name = "EnergyItemHourResult", isInnerAttr = true)
    private List<EnergyItemHourResultModel> energyItemHourResult;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public List<EnergyItemHourResultModel> getEnergyItemHourResult()
    {
        return energyItemHourResult;
    }

    public void setEnergyItemHourResult(List<EnergyItemHourResultModel> energyItemHourResult)
    {
        this.energyItemHourResult = energyItemHourResult;
    }
}
