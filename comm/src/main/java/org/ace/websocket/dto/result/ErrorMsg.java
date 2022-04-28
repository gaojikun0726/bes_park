package org.ace.websocket.dto.result;

/**
 * 异常消息结果定义
 * @param <T>
 */
public class ErrorMsg<T> extends JsonMsg
{
    private T error;

    public T getError()
    {
        return error;
    }

    public void setError(T error)
    {
        this.error = error;
    }

}
