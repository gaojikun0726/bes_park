package com.efounder.JEnterprise.commhandler;

import org.apache.shiro.SecurityUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiepufeng
 * @date 2020/5/15 8:06
 */
public class MsgSubPubHandler extends TimerTask
{

    public final static int INTERVAL = 60 * 60 * 1000;

    public final static Map<Integer, List<MsgSubParam>> subMsg = new ConcurrentHashMap<>();

    @Override
    public void run()
    {
        try
        {
            cleanSubMsg();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    // 添加订阅消息
    public static void addSubMsg(Integer index, String channelID)
    {
        if (null == index || !StringUtils.hasText(channelID))
        {
            return;
        }

        MsgSubParam msgSubParam = new MsgSubParam();

        String sessionId;

        try
        {
            sessionId = SecurityUtils.getSubject().getSession().getId().toString();

        } catch (Exception e)
        {
            return;
        }

        Long time = new Date().getTime();

        msgSubParam.setIndex(index);
        msgSubParam.setChannelID(channelID);
        msgSubParam.setSessionId(sessionId);
        msgSubParam.setCreateTime(time);

        List<MsgSubParam> msgSubParams = subMsg.get(index);

        if (null == msgSubParams)
        {
            msgSubParams = Collections.synchronizedList(new LinkedList<>());
        }

        msgSubParams.add(msgSubParam);
        subMsg.put(index, msgSubParams);

    }

    // 添加订阅消息
    public static void addSubMsg(Integer index, String channelID, String identity)
    {
        if (null == index || !StringUtils.hasText(channelID) || !StringUtils.hasText(identity))
        {
            return;
        }

        MsgSubParam msgSubParam = new MsgSubParam();

        String sessionId;

        try
        {
            sessionId = SecurityUtils.getSubject().getSession().getId().toString();

        } catch (Exception e)
        {
            return;
        }

        Long time = new Date().getTime();

        msgSubParam.setIndex(index);
        msgSubParam.setChannelID(channelID);
        msgSubParam.setSessionId(sessionId);
        msgSubParam.setCreateTime(time);
        msgSubParam.setIdentity(identity);

        List<MsgSubParam> msgSubParams = subMsg.get(index);

        if (null == msgSubParams)
        {
            msgSubParams = Collections.synchronizedList(new LinkedList<>());
        }

        msgSubParams.add(msgSubParam);
        subMsg.put(index, msgSubParams);

    }

    // 清理订阅消息
    public static void cleanSubMsg()
    {
        Long currentTime = new Date().getTime();

        Iterator<Map.Entry<Integer, List<MsgSubParam>>> subMsgIterable = subMsg.entrySet().iterator();

        while (subMsgIterable.hasNext())
        {
            Map.Entry<Integer, List<MsgSubParam>> entry = subMsgIterable.next();

            List<MsgSubParam> msgSubParams = entry.getValue();

            if (null == msgSubParams || msgSubParams.isEmpty())
            {
                subMsgIterable.remove();
                continue;
            }

            Iterator<MsgSubParam> it = msgSubParams.iterator();

            while (it.hasNext())
            {
                MsgSubParam msgSubParam = it.next();

                Long subTime = currentTime - msgSubParam.getCreateTime();

                // 如果发布的消息在超过过了一分钟，则删除这个订阅消息
                if (subTime > 60 * 1000)
                {
                    it.remove();
                }
            }

            if (msgSubParams.isEmpty())
            {
                subMsgIterable.remove();
            }

        }
    }

    public static String pubMsg(Integer index, String channelID)
    {

        if (null == index || !StringUtils.hasText(channelID))
        {
            return "";
        }

        List<MsgSubParam> msgSubParams = subMsg.get(index);

        if (null == msgSubParams || msgSubParams.isEmpty())
        {
            return "";
        }


        for (MsgSubParam msgSubParam : msgSubParams)
        {
            if (index.equals(msgSubParam.getIndex()) && channelID.equals(msgSubParam.getChannelID()))
            {

                String sessionId = msgSubParam.getSessionId();

                msgSubParams.remove(msgSubParam);

                return sessionId;
            }
        }

        return "";
    }

    public static String pubMsg(Integer index, String channelID, String identity)
    {

        if (null == index || !StringUtils.hasText(channelID) || !StringUtils.hasText(identity))
        {
            return "";
        }

        List<MsgSubParam> msgSubParams = subMsg.get(index);

        if (null == msgSubParams || msgSubParams.isEmpty())
        {
            return "";
        }


        for (MsgSubParam msgSubParam : msgSubParams)
        {
            if (index.equals(msgSubParam.getIndex())
                    && channelID.equals(msgSubParam.getChannelID())
                    && identity.equals(msgSubParam.getIdentity()))
            {

                String sessionId = msgSubParam.getSessionId();

                msgSubParams.remove(msgSubParam);

                return sessionId;
            }
        }

        return "";
    }
}
