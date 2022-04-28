package org.ace.websocket.dto.result;

/**
 * 消息体定义
 */
public abstract class JsonMsg
{

    /**
     * 接口版本号
     */
    private String jsonrpc;

    /**
     * 消息唯一标识，含随机值
     */
    private Long id;

    public String getJsonrpc()
    {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc)
    {
        this.jsonrpc = jsonrpc;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

}
