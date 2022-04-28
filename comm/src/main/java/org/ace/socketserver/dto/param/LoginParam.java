package org.ace.socketserver.dto.param;

public class LoginParam extends AbstractCmdParam
{
    /**
     * 登录socket密码
     */
    private String password;

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

}
