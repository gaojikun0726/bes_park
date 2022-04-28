package org.ace.websocket.dto.result;

/**
 * 抽象消息结果定义
 */
public abstract class AbstractResult
{
    /*
     * 消息内容
     */
    private String message;

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

}
