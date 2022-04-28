package org.ace.websocket.dto.result;

/**
 * 结果体定义
 * @param <T>
 */
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
