package com.efounder.datareported.model.energy;

import com.framework.common.utils.xmlprocessor.annotation.AttrXml;

import java.util.List;

/**
 * 建筑能耗信息集合
 * @author xiepufeng
 * @date 2020/11/9 14:40
 */
public class EnergyDataModel
{
    @AttrXml(name = "Build", isInnerAttr = true)
    private List<BuildEnergyModel> build;

    public List<BuildEnergyModel> getBuild()
    {
        return build;
    }

    public void setBuild(List<BuildEnergyModel> build)
    {
        this.build = build;
    }
}
