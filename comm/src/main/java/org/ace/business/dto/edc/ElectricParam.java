package org.ace.business.dto.edc;

/**
 * 电能参数定义
 * @author xiepufeng
 * @date 2020/4/14 18:59
 */
public class ElectricParam
{
    // 单位类型
    private String unitType;

    // 小数点位置 解析规则
    private Integer pointLocation;

    // 编码规则 BCD编码，或者是10进制编码
    private Integer dataEncodeType;

    // 能耗名称
    private String electricName;

    // 寄存器偏移地址
    private Long offsetAddr;

    // 数据长度 字节数
    private Integer dataLength;

    // 能耗参数 id
    private Integer electricID;

    // 数据类型
    private Integer dataType;

    // 解码顺序
    private Integer codeSeq;

    // 电表序列号
    private Integer meterID;

    public Integer getPointLocation()
    {
        return pointLocation;
    }

    public void setPointLocation(Integer pointLocation)
    {
        this.pointLocation = pointLocation;
    }

    public Integer getDataEncodeType()
    {
        return dataEncodeType;
    }

    public void setDataEncodeType(Integer dataEncodeType)
    {
        this.dataEncodeType = dataEncodeType;
    }

    public String getElectricName()
    {
        return electricName;
    }

    public void setElectricName(String electricName)
    {
        this.electricName = electricName;
    }

    public Long getOffsetAddr()
    {
        return offsetAddr;
    }

    public void setOffsetAddr(Long offsetAddr)
    {
        this.offsetAddr = offsetAddr;
    }

    public Integer getDataLength()
    {
        return dataLength;
    }

    public void setDataLength(Integer dataLength)
    {
        this.dataLength = dataLength;
    }

    public Integer getElectricID()
    {
        return electricID;
    }

    public void setElectricID(Integer electricID)
    {
        this.electricID = electricID;
    }

    public Integer getDataType()
    {
        return dataType;
    }

    public void setDataType(Integer dataType)
    {
        this.dataType = dataType;
    }

    public Integer getCodeSeq()
    {
        return codeSeq;
    }

    public void setCodeSeq(Integer codeSeq)
    {
        this.codeSeq = codeSeq;
    }

    public String getUnitType()
    {
        return unitType;
    }

    public void setUnitType(String unitType)
    {
        this.unitType = unitType;
    }

    public Integer getMeterID()
    {
        return meterID;
    }

    public void setMeterID(Integer meterID)
    {
        this.meterID = meterID;
    }
}
