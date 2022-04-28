package com.efounder.datareported.model.build;

import com.framework.common.utils.xmlprocessor.annotation.AttrXml;

/**
 * @author xiepufeng
 * @date 2020/11/9 17:45
 */
public class BuildGroupModel
{

    /**
     * 数据中心代码
     */
    @AttrXml(isProperty = true)
    private String id;

    /**
     * 建筑群基本信息
     */
    @AttrXml(name = "BuildGroupBaseInfo", isInnerAttr = true)
    private BuildGroupBaseInfoModel buildGroupBaseInfo;

    /**
     * 建筑群关联的建筑信息
     */
    @AttrXml(name = "BuildGroupRelaInfo", isInnerAttr = true)
    private BuildGroupRelateInfoModel buildGroupRelateInfo;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public BuildGroupBaseInfoModel getBuildGroupBaseInfo()
    {
        return buildGroupBaseInfo;
    }

    public void setBuildGroupBaseInfo(BuildGroupBaseInfoModel buildGroupBaseInfo)
    {
        this.buildGroupBaseInfo = buildGroupBaseInfo;
    }

    public BuildGroupRelateInfoModel getBuildGroupRelateInfo()
    {
        return buildGroupRelateInfo;
    }

    public void setBuildGroupRelateInfo(BuildGroupRelateInfoModel buildGroupRelateInfo)
    {
        this.buildGroupRelateInfo = buildGroupRelateInfo;
    }
}
