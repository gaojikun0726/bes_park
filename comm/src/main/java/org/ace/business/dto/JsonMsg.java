package org.ace.business.dto;

/**
 *  json 消息体定义
 * @author xiepufeng
 */
public class JsonMsg<T>
{
    // ip 地址
    private String ip;

    private String uuid;

    private T data;

    // 命令标识
    private Integer index;

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getUuid()
    {
        return uuid;
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }

    public Integer getIndex()
    {
        return index;
    }

    public void setIndex(Integer index)
    {
        this.index = index;
    }
}
