package org.ace.websocket.dto.param;

public abstract class AbstractCmdParam
{
    /**
     * http会话ID
     */
    private String sessionId;

    public String getSessionId()
    {
        return sessionId;
    }

    public void setSessionId(String sessionId)
    {
        this.sessionId = sessionId;
    }
}
