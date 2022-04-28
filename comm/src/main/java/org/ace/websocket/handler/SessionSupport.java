package org.ace.websocket.handler;

/**
 *  Session 外部交互接口
 * @author xiepufeng
 */
public interface SessionSupport
{

    /**
     * 判断 http session 是否存在
     * @return
     */
    boolean isHttpSessionExist(String sessionId);

    /**
     *  更新 http session 时间
     * @param sessionId
     */
    void updateHttpSessionTime(String sessionId);

    /**
     * 通道关闭回调
     * @param sessionId
     */
    void channelRemoved(String sessionId);

}
