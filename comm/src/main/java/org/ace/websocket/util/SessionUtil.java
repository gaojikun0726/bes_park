package org.ace.websocket.util;

import org.ace.websocket.handler.SessionSupport;

import java.util.ServiceLoader;
import java.util.logging.Logger;

public class SessionUtil
{
    private static final Logger log = Logger.getLogger(SessionUtil.class.getName());

    private static SessionSupport sessionSupport;

    /**
     * 判断 http session 是否存在
     *
     * @param httpSessionId
     * @return
     */
    public static boolean isHttpSessionExist(String httpSessionId)
    {
        if (httpSessionId == null || httpSessionId.isEmpty())
        {
            log.warning("Parameter Error. Http session id is null.");
            return false;
        }

        if (null != sessionSupport)
        {
            return sessionSupport.isHttpSessionExist(httpSessionId);
        }

        // 判断sessionId是否存在
        ServiceLoader<SessionSupport> load = ServiceLoader.load(SessionSupport.class);

        for (SessionSupport item : load)
        {
            sessionSupport = item;
            return item.isHttpSessionExist(httpSessionId);
        }

        return false;
    }

    /**
     * 更新 http session 时间
     *
     * @param httpSessionId
     */
    public static void updateHttpSessionTime(String httpSessionId)
    {
        if (httpSessionId == null || httpSessionId.isEmpty())
        {
            log.warning("Parameter Error. Http session id is null.");
            return;
        }

        if (null != sessionSupport)
        {
            sessionSupport.updateHttpSessionTime(httpSessionId);
            return;
        }

        ServiceLoader<SessionSupport> load = ServiceLoader.load(SessionSupport.class);

        for (SessionSupport item : load)
        {
            sessionSupport = item;
            item.updateHttpSessionTime(httpSessionId);
            return;
        }

    }

}
