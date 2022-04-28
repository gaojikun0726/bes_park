package org.ace.socketserver.dto.result;

/**
 * 命令执行结果
 *
 * @author xiepufeng
 *
 */
public class CmdExeResult
{
    /*
     * 命令执行状态码 0 表示成功， <0 表示失败
     */
    private Long code;

    private String message;

    private JsonMsg jsonMsg;

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Long getCode()
    {
        return code;
    }

    public void setCode(Long code)
    {
        this.code = code;
    }

    public JsonMsg getJsonMsg()
    {
        return jsonMsg;
    }

    public void setJsonMsg(JsonMsg jsonMsg)
    {
        this.jsonMsg = jsonMsg;
    }

}
