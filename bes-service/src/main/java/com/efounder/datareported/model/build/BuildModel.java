package com.efounder.datareported.model.build;

import com.framework.common.utils.xmlprocessor.annotation.AttrXml;

/**
 * @author xiepufeng
 * @date 2020/11/9 15:05
 */
public class BuildModel
{
    /**
     * 建筑节点代码
     */
    @AttrXml(isProperty = true)
    private String id;

    /**
     * 建筑物节点信息
     */
    @AttrXml(name = "BuildBaseInfo", isInnerAttr = true)
    private BuildBaseInfoModel buildBaseInfo;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public BuildBaseInfoModel getBuildBaseInfo()
    {
        return buildBaseInfo;
    }

    public void setBuildBaseInfo(BuildBaseInfoModel buildBaseInfo)
    {
        this.buildBaseInfo = buildBaseInfo;
    }
}
