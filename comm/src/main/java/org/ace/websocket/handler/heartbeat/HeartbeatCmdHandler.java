package org.ace.websocket.handler.heartbeat;

import io.netty.channel.Channel;
import org.ace.websocket.bo.ChannelProperty;
import org.ace.websocket.constant.AttributeKeyConst;
import org.ace.websocket.dto.param.HeartbeatParam;
import org.ace.websocket.dto.result.CmdExeResult;
import org.ace.websocket.dto.result.CmdMsg;
import org.ace.websocket.dto.result.JsonMsg;
import org.ace.websocket.enums.ChannelStatus;
import org.ace.websocket.enums.ErrorCodeEnum;
import org.ace.websocket.handler.CmdHandler;
import org.ace.websocket.util.MsgUtil;
import org.ace.websocket.util.SessionUtil;

import java.util.logging.Logger;

/**
 * 心跳消息处理器
 */
public class HeartbeatCmdHandler implements CmdHandler
{

    private static final Logger log = Logger.getLogger(HeartbeatCmdHandler.class.getName());

    @Override
    public CmdExeResult execute(JsonMsg jsonMsg)
    {
        @SuppressWarnings("unchecked")
        CmdMsg<HeartbeatParam> cmdMsg = (CmdMsg<HeartbeatParam>) jsonMsg;

        CmdExeResult result = new CmdExeResult();
        result.setJsonMsg(jsonMsg);

        // 判断sessionId是否存在
        String httpSessionId = cmdMsg.getParams().getSessionId();
        if (!SessionUtil.isHttpSessionExist(httpSessionId))
        {
            result.setCode(ErrorCodeEnum.ERR_SESSIONID.getInternalCode());
            result.setMessage(ErrorCodeEnum.ERR_SESSIONID.getInternalMessage());
            log.warning("Error code:" + ErrorCodeEnum.ERR_SESSIONID.getInternalCode() + " Http session id:"
                    + httpSessionId + " " + ErrorCodeEnum.ERR_SESSIONID.getInternalMessage());
            return result;

        }

        // 更新 http session 的超时时间，防止 http session 超时
        SessionUtil.updateHttpSessionTime(httpSessionId);

        // 根据 sessionId 查找 channel
        Channel channelSubscriber = MsgUtil.getChannelByHttpSessionId(httpSessionId);
        if (channelSubscriber == null)
        {
            result.setCode(ErrorCodeEnum.ERR_SESSIONID_NOT_BIND_CHANNEL.getInternalCode());
            result.setMessage(ErrorCodeEnum.ERR_SESSIONID_NOT_BIND_CHANNEL.getInternalMessage());
            log.warning("Error code:" + ErrorCodeEnum.ERR_SESSIONID.getInternalCode() + " Http session id:"
                    + httpSessionId + " " + ErrorCodeEnum.ERR_SESSIONID.getInternalMessage());

            return result;
        }

        // 获取 channel 属性对象
        ChannelProperty channelProperty = channelSubscriber.attr(AttributeKeyConst.CHANNEL_PROPERTY_KEY).get();
        if (channelProperty == null)
        {
            result.setCode(ErrorCodeEnum.ERR_CHANNEL_NO_PROPERTY.getInternalCode());
            result.setMessage(ErrorCodeEnum.ERR_CHANNEL_NO_PROPERTY.getInternalMessage());
            log.warning("Error code:" + ErrorCodeEnum.ERR_SESSIONID.getInternalCode() + " Http session id:"
                    + httpSessionId + " " + ErrorCodeEnum.ERR_SESSIONID.getInternalMessage());

            return result;

        }

        // 没有绑定到 http session 则不接收心跳消息
        ChannelStatus channelStatus = channelProperty.getStatus();
        if (ChannelStatus.SESSION_BOUND != channelStatus)
        {
            result.setCode(ErrorCodeEnum.ERR_HEARTBEAT_AT_INCORRECT_CHANNEL_STATUS.getInternalCode());
            result.setMessage(ErrorCodeEnum.ERR_HEARTBEAT_AT_INCORRECT_CHANNEL_STATUS.getInternalMessage());

            log.warning("Error code:" + ErrorCodeEnum.ERR_HEARTBEAT_AT_INCORRECT_CHANNEL_STATUS.getInternalCode()
                    + " Http session id:" + httpSessionId + " "
                    + ErrorCodeEnum.ERR_HEARTBEAT_AT_INCORRECT_CHANNEL_STATUS.getInternalMessage());

            return result;

        }

        // 刷新心跳计数器复位标志，通知定时器任务复位心跳计数器
        channelProperty.heartbeatRefresh();

        result.setCode(ErrorCodeEnum.ERR_OK.getInternalCode());
        result.setMessage(ErrorCodeEnum.ERR_OK.getInternalMessage());

        return result;
    }

}
