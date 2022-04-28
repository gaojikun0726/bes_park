package org.ace.httpclient.exception;

/**
 * http 自定义异常类
 * @author xiepufeng
 * @date 2020/12/25 19:01
 */
public class HttpException extends Exception
{
    private static final long serialVersionUID = 1L;

    /**
     * 服务器返回错误消息
     */
    private String msg;

    public HttpException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }
}
