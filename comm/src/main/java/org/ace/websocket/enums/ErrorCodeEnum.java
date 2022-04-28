package org.ace.websocket.enums;

/**
 *
 */
public enum ErrorCodeEnum
{

    // 未知错误
    ERR_UNKNOWN(-10000L, "Unknown Error"),

    // 成功
    ERR_OK(0L, "OK"),

    // 密码错误
    ERR_PWD(-10001L, "Password error"),

    // HTTP session超期或者不存在
    ERR_SESSIONID(-10002L, "Http session was not exists or expired"),

    // HTTP session没有绑定 channel
    ERR_SESSIONID_NOT_BIND_CHANNEL(-10003L, "Http session was not bound a channel"),

    // Channel属性不存在
    ERR_CHANNEL_NO_PROPERTY(-10004L, "Channel not bound a property"),

    // Channel当前状态不可接收心跳消息
    ERR_HEARTBEAT_AT_INCORRECT_CHANNEL_STATUS(-10005L, "Heartbeat at incorrect channel status"),

    // TokenSN不存在
    ERR_TOKEN_SN(-10006L, "TokenSN was not exists"),

    // TokenSN没有绑定 channel
    ERR_TOKEN_SN_NOT_BIND_CHANNEL(-10007L, "TokenSN was not bound a channel");


    private long internalCode; //内部代码
    private String internalMessage; //内部代码含义

    ErrorCodeEnum(Long internalCode, String internalMessage)
    {
        this.internalCode = internalCode;
        this.internalMessage = internalMessage;
    }

    public long getInternalCode()
    {
        return internalCode;
    }

    public void setInternalCode(long internalCode)
    {
        this.internalCode = internalCode;
    }

    public String getInternalMessage()
    {
        return internalMessage;
    }

    public void setInternalMessage(String internalMessage)
    {
        this.internalMessage = internalMessage;
    }

}
