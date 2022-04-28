package org.ace.websocket.dto.param;

/**
 * 登录参数定义
 */
public class LoginParam extends AbstractCmdParam
{
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 登录websocket密码
     */
    private String password;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

}
