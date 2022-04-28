package org.ace.business.dto.edc;

/**
 * 控制器数据参数定义
 * @author xiepufeng
 * @date 2020/4/14 16:21
 */
public class ControllerDataEDC
{
    // 控制器的ID，必须唯一
    private Integer id;

    // 控制器的名字
    private String name;

    // 控制器别名
    private String alias;

    // 控制器的描述信息
    private String description;

    // 控制器的安装位置
    private String location;

    // 区域信息
    private String zone;

    // 控制器的使能状态，只有使能时才会正常工作，才会与下位机通信
    private Integer active;

    // 采样周期 单位为分钟
    private Integer samplePeriod;

    // 上传数据采样周期 单位为分钟
    private Integer upDataSamplePeriod;

    // 历史数据保存周期，单位为小时
    private Integer hisDataSavePeriod;

    // 能耗采集器IP地址
    private String ip;

    // 能耗采集器子网掩码
    private String mask;

    // 能耗采集器网关
    private String gateWay;

    // 能耗采集器服务器IP地址
    private String serverIP;

    // 能耗采集器服务器端口号
    private Integer serverPort;

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

    public String getZone()
    {
        return zone;
    }

    public void setZone(String zone)
    {
        this.zone = zone;
    }

    public Integer getActive()
    {
        return active;
    }

    public void setActive(Integer active)
    {
        this.active = active;
    }

    public Integer getSamplePeriod()
    {
        return samplePeriod;
    }

    public void setSamplePeriod(Integer samplePeriod)
    {
        this.samplePeriod = samplePeriod;
    }

    public Integer getUpDataSamplePeriod()
    {
        return upDataSamplePeriod;
    }

    public void setUpDataSamplePeriod(Integer upDataSamplePeriod)
    {
        this.upDataSamplePeriod = upDataSamplePeriod;
    }

    public Integer getHisDataSavePeriod()
    {
        return hisDataSavePeriod;
    }

    public void setHisDataSavePeriod(Integer hisDataSavePeriod)
    {
        this.hisDataSavePeriod = hisDataSavePeriod;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getMask()
    {
        return mask;
    }

    public void setMask(String mask)
    {
        this.mask = mask;
    }

    public String getGateWay()
    {
        return gateWay;
    }

    public void setGateWay(String gateWay)
    {
        this.gateWay = gateWay;
    }

    public String getServerIP()
    {
        return serverIP;
    }

    public void setServerIP(String serverIP)
    {
        this.serverIP = serverIP;
    }

    public Integer getServerPort()
    {
        return serverPort;
    }

    public void setServerPort(Integer serverPort)
    {
        this.serverPort = serverPort;
    }
}
