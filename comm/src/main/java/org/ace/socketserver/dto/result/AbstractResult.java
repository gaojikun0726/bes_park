package org.ace.socketserver.dto.result;

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
