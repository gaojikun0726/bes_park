package com.efounder.datareported.model.build;

import com.framework.common.utils.xmlprocessor.annotation.AttrXml;

/**
 *  建筑属性通用字段
 * @author xiepufeng
 * @date 2020/11/9 14:38
 */
public class CommonModel
{
    /**
     * 建筑物节点代码
     */
    @AttrXml(name = "UploadDataCenterID")
    private String uploadDataCenterID;

    /**
     * 创建时间
     */
    @AttrXml(name = "CreateTime")
    private String createTime;

    public String getUploadDataCenterID()
    {
        return uploadDataCenterID;
    }

    public void setUploadDataCenterID(String uploadDataCenterID)
    {
        this.uploadDataCenterID = uploadDataCenterID;
    }

    public String getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }
}
