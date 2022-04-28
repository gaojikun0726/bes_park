package org.ace.business.dto.ldc;

/**
 *  照明模块参数定义
 * @author xiepufeng
 * @date 2020/6/28 8:56
 */
public class ModuleParamLDC
{
    private String name; // 模块名称

    private String alias; // 模块别名

    private String description; // 模块的描述信息

    private String location; // 模块的安装位置

    private Integer modelID; // 模块型号编码

    private Integer areaNum; // 模块所在干线号

    private Integer branchNum; // 模块所在支线号

    private Integer slaveAddress; // 模块的通信地址

    private Integer active; // 模块的使能状态 1 使能 0 不使能

    private Integer id; // 模块的识别码

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAlias()
    {
        return alias;
    }

    public void setAlias(String alias)
    {
        this.alias = alias;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public Integer getModelID()
    {
        return modelID;
    }

    public void setModelID(Integer modelID)
    {
        this.modelID = modelID;
    }

    public Integer getAreaNum()
    {
        return areaNum;
    }

    public void setAreaNum(Integer areaNum)
    {
        this.areaNum = areaNum;
    }

    public Integer getBranchNum()
    {
        return branchNum;
    }

    public void setBranchNum(Integer branchNum)
    {
        this.branchNum = branchNum;
    }

    public Integer getSlaveAddress()
    {
        return slaveAddress;
    }

    public void setSlaveAddress(Integer slaveAddress)
    {
        this.slaveAddress = slaveAddress;
    }

    public Integer getActive()
    {
        return active;
    }

    public void setActive(Integer active)
    {
        this.active = active;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }
}
