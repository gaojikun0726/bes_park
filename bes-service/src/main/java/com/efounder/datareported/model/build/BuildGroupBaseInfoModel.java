package com.efounder.datareported.model.build;

import com.framework.common.utils.xmlprocessor.annotation.AttrXml;

/**
 * 建筑群信息
 * @author xiepufeng
 * @date 2020/11/10 9:30
 */
public class BuildGroupBaseInfoModel
{
    @AttrXml(isProperty = true)
    private String operation;

    /**
     * 建筑群名称
     */
    @AttrXml(name = "F_BuildGroupName")
    private String buildGroupName;

    /**
     * 建筑群别名
     */
    @AttrXml(name = "F_GroupAliasName")
    private String groupAliasName;

    /**
     * 建筑群描述
     */
    @AttrXml(name = "F_GroupDesc")
    private String groupDesc;

    public String getOperation()
    {
        return operation;
    }

    public void setOperation(String operation)
    {
        this.operation = operation;
    }

    public String getBuildGroupName()
    {
        return buildGroupName;
    }

    public void setBuildGroupName(String buildGroupName)
    {
        this.buildGroupName = buildGroupName;
    }

    public String getGroupAliasName()
    {
        return groupAliasName;
    }

    public void setGroupAliasName(String groupAliasName)
    {
        this.groupAliasName = groupAliasName;
    }

    public String getGroupDesc()
    {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc)
    {
        this.groupDesc = groupDesc;
    }
}
