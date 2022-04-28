package org.ace.socketserver.dto.param;

public abstract class AbstractError
{
    private Long code;

    private String message;

    public Long getCode()
    {
        return code;
    }

    public void setCode(Long code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

}
