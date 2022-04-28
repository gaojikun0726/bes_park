package org.ace.business.dto;

/**
 * 回复消息体定义
 * @author xiepufeng
 */
public class ReceiveMsg<T> extends JsonMsg<T>
{
    // 0 成功 1 失败
    private Integer code;

    public Integer getCode()
    {
        return code;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }
}
