package com.efounder.datareported.model.build;

import com.framework.common.utils.xmlprocessor.annotation.AttrXml;

import java.util.List;

/**
 * 建筑群关联的建筑
 * @author xiepufeng
 * @date 2020/11/10 9:34
 */
public class BuildGroupRelateInfoModel
{
    @AttrXml(isProperty = true)
    private String operation;

    /**
     * 建筑物节点代码
     */
    @AttrXml(name = "F_BuildID")
    private List<String> buildID;

    public String getOperation()
    {
        return operation;
    }

    public void setOperation(String operation)
    {
        this.operation = operation;
    }

    public List<String> getBuildID()
    {
        return buildID;
    }

    public void setBuildID(List<String> buildID)
    {
        this.buildID = buildID;
    }
}
