package com.efounder.datareported.model.build;

import com.framework.common.utils.xmlprocessor.annotation.AttrXml;

import java.util.List;

/**
 * 建筑物参数
 * @author xiepufeng
 * @date 2020/11/9 14:40
 */
public class DataModel
{
    @AttrXml(name = "DataCenterBaseInfo", isInnerAttr = true)
    private DataCenterBaseInfoModel dataCenterBaseInfo;

    @AttrXml(name = "Build", isInnerAttr = true)
    private List<BuildModel> build;

    @AttrXml(name = "BuildGroup", isInnerAttr = true)
    private BuildGroupModel buildGroup;

    public DataCenterBaseInfoModel getDataCenterBaseInfo()
    {
        return dataCenterBaseInfo;
    }

    public void setDataCenterBaseInfo(DataCenterBaseInfoModel dataCenterBaseInfo)
    {
        this.dataCenterBaseInfo = dataCenterBaseInfo;
    }

    public List<BuildModel> getBuild()
    {
        return build;
    }

    public void setBuild(List<BuildModel> build)
    {
        this.build = build;
    }

    public BuildGroupModel getBuildGroup()
    {
        return buildGroup;
    }

    public void setBuildGroup(BuildGroupModel buildGroup)
    {
        this.buildGroup = buildGroup;
    }
}
