package org.ace.business.dto.edc;

/**
 * 电表参数定义
 * @author xiepufeng
 * @date 2020/4/14 19:16
 */
public class AmmeterParam
{
    // 物理地址
    private String phyAddr;

    // 通信规约类型
    private Integer comAgreementType;

    // 电表的使能状态
    private Integer active;

    // 表的描述信息
    private String description;

    // 通信速率
    private Integer comRate;

    // 通信端口号
    private Integer comPort;

    // 通信数据位
    private Integer comDataBit;

    // 通信校验位
    private Integer comParityBit;

    // 通信停止位
    private Integer comStopBit;

    // 表类型
    private Integer meterType;

    // 表的别名
    private String alias;

    // 表序列号
    private Integer meterID;

    // 电表的安装位置
    private String location;

    // 功能码
    private Integer functionCode;


    public String getPhyAddr()
    {
        return phyAddr;
    }

    public void setPhyAddr(String phyAddr)
    {
        this.phyAddr = phyAddr;
    }

    public Integer getComAgreementType()
    {
        return comAgreementType;
    }

    public void setComAgreementType(Integer comAgreementType)
    {
        this.comAgreementType = comAgreementType;
    }

    public Integer getActive()
    {
        return active;
    }

    public void setActive(Integer active)
    {
        this.active = active;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Integer getComRate()
    {
        return comRate;
    }

    public void setComRate(Integer comRate)
    {
        this.comRate = comRate;
    }

    public Integer getComPort()
    {
        return comPort;
    }

    public void setComPort(Integer comPort)
    {
        this.comPort = comPort;
    }

    public Integer getComDataBit()
    {
        return comDataBit;
    }

    public void setComDataBit(Integer comDataBit)
    {
        this.comDataBit = comDataBit;
    }

    public Integer getComParityBit()
    {
        return comParityBit;
    }

    public void setComParityBit(Integer comParityBit)
    {
        this.comParityBit = comParityBit;
    }

    public Integer getComStopBit()
    {
        return comStopBit;
    }

    public void setComStopBit(Integer comStopBit)
    {
        this.comStopBit = comStopBit;
    }

    public Integer getMeterType()
    {
        return meterType;
    }

    public void setMeterType(Integer meterType)
    {
        this.meterType = meterType;
    }

    public String getAlias()
    {
        return alias;
    }

    public void setAlias(String alias)
    {
        this.alias = alias;
    }

    public Integer getMeterID()
    {
        return meterID;
    }

    public void setMeterID(Integer meterID)
    {
        this.meterID = meterID;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public Integer getFunctionCode()
    {
        return functionCode;
    }

    public void setFunctionCode(Integer functionCode)
    {
        this.functionCode = functionCode;
    }
}
