package com.efounder.datareported.model.build;

import com.framework.common.utils.xmlprocessor.annotation.AttrXml;
import com.framework.common.utils.xmlprocessor.annotation.RootXml;

/**
 *  建筑 Xml 模型
 * @author xiepufeng
 * @date 2020/11/9 14:33
 */
@RootXml("root")
public class BuildXmlModel
{
    @AttrXml(isInnerAttr = true)
    private CommonModel common;

    @AttrXml(isInnerAttr = true)
    private DataModel data;

    public CommonModel getCommon()
    {
        return common;
    }

    public void setCommon(CommonModel common)
    {
        this.common = common;
    }

    public DataModel getData()
    {
        return data;
    }

    public void setData(DataModel data)
    {
        this.data = data;
    }
}
