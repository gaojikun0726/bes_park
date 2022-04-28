package org.ace.socketserver.dto.result;

public class ResultMsg<T> extends JsonMsg
{
    private T result;

    public T getResult()
    {
        return result;
    }

    public void setResult(T result)
    {
        this.result = result;
    }

}
