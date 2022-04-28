package com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.deviceConfig;

/**
 * 机组
 * @author xiepufeng
 * @date 2020/11/24 17:02
 */
public class MachineSetModel
{
    private Integer id; // 主键

    private String name; // 机组名称

    private String comments; // 备注

    private String createTime; // 创建时间

    private String updateTime; // 更新时间

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
    }

    public String getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

    public String getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(String updateTime)
    {
        this.updateTime = updateTime;
    }
}
