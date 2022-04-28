package org.ace.websocket.dto.param;

/**
 * 事件消息体定义
 * @param <T>
 */
public class EventParam<T>
{
    private String subEvent;
    /*
     * 事件参数
     */
    private T content;

    public T getContent()
    {
        return content;
    }

    public void setContent(T content)
    {
        this.content = content;
    }

    public String getSubEvent()
    {
        return subEvent;
    }

    public void setSubEvent(String subEvent)
    {
        this.subEvent = subEvent;
    }

}
