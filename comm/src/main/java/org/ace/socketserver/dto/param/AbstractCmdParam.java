package org.ace.socketserver.dto.param;

public abstract class AbstractCmdParam
{
    private String tokenSN;

    public String getTokenSN()
    {
        return tokenSN;
    }

    public void setTokenSN(String tokenSN)
    {
        this.tokenSN = tokenSN;
    }
}
