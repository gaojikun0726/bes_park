package com.core.common.config;

import com.core.ApplicationContextProvider;
import com.core.config.qxpz.HttpSessionConfig;
import com.efounder.JEnterprise.initializer.SubRealTimeDataCache;
import com.google.auto.service.AutoService;
import org.ace.websocket.handler.SessionSupport;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author xiepufeng
 */
@AutoService(SessionSupport.class)
public class WebSocketSessionSupport implements SessionSupport
{

    private WebSocketConfigImpl webSocketConfig = ApplicationContextProvider.getBean(WebSocketConfigImpl.class);

    private SubRealTimeDataCache subRealTimeDataCache = ApplicationContextProvider.getBean(SubRealTimeDataCache.class);

    /**
     * 判断 http session 是否存在
     * @param sessionId
     * @return
     */
    @Override
    public boolean isHttpSessionExist(String sessionId)
    {
        if (null == sessionId)
        {
            return false;
        }

        List<HttpSession> sessions = HttpSessionConfig.getActiveSessions();

        if (sessions.isEmpty()){
            return false;
        }

        for (HttpSession session: sessions)
        {
            if (sessionId.equals(session.getId()))
            {
                return true;
            }
        }

        return false;
    }

    /**
     *  更新 http session 时间
     * @param sessionId
     */
    @Override
    public void updateHttpSessionTime(String sessionId)
    {
        List<HttpSession> sessions = HttpSessionConfig.getActiveSessions();

        if (sessions.isEmpty()){
            return;
        }

        for (HttpSession session: sessions){

            if (sessionId.equals(session.getId()))
            {
                session.setMaxInactiveInterval(session.getMaxInactiveInterval() + webSocketConfig.getInterval()); // 重新设置 session 超时时间 30 分钟
                return;
            }
        }
    }

    /**
     * 通道关闭回调
     * @param sessionId
     */
    @Override
    public void channelRemoved(String sessionId) {
        subRealTimeDataCache.unsubscribeCacheBySessionId(sessionId);
    }
}
