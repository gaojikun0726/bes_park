package org.ace.business.dto.edc;

/**
 * 采集方法定义
 * @author xiepufeng
 * @date 2020/4/14 18:47
 */
public class CollectMethod
{
    // 采集方案ID
    private Integer collectMethodID;

    // 电能参数个数，最多100个
    private Integer collectCount;

    public Integer getCollectMethodID()
    {
        return collectMethodID;
    }

    public void setCollectMethodID(Integer collectMethodID)
    {
        this.collectMethodID = collectMethodID;
    }

    public Integer getCollectCount()
    {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount)
    {
        this.collectCount = collectCount;
    }
}
