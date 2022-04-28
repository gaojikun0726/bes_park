package org.ace.websocket.handler.login;

import io.netty.channel.Channel;
import org.ace.websocket.bo.ChannelProperty;
import org.ace.websocket.bo.ChannelPropertyCommon;
import org.ace.websocket.constant.AttributeKeyConst;
import org.ace.websocket.core.TextWebSocketFrameHandler;
import org.ace.websocket.dto.param.LoginParam;
import org.ace.websocket.dto.result.CmdExeResult;
import org.ace.websocket.dto.result.CmdMsg;
import org.ace.websocket.dto.result.JsonMsg;
import org.ace.websocket.enums.ChannelStatus;
import org.ace.websocket.enums.ErrorCodeEnum;
import org.ace.websocket.handler.CmdHandler;
import org.ace.websocket.util.SessionUtil;

import java.util.logging.Logger;

/**
 * 登录消息的处理器
 */
public class LoginCmdHandler implements CmdHandler
{

    private static final Logger log = Logger.getLogger(LoginCmdHandler.class.getName());

    /*
     * 消息通道处理器
     */
    private TextWebSocketFrameHandler channelHandler;

    @Override
    public CmdExeResult execute(JsonMsg jsonMsg)
    {
        @SuppressWarnings("unchecked")
        CmdMsg<LoginParam> loginCmdMsg = (CmdMsg<LoginParam>) jsonMsg;

        CmdExeResult result = new CmdExeResult();
        result.setJsonMsg(jsonMsg);

        ChannelPropertyCommon cpc = channelHandler.getCtx().channel().attr(AttributeKeyConst.CHANNEL_PROPERTY_COMMON_KEY).get();

        // 登录密码校验
        if (!loginCmdMsg.getParams().getPassword().equals(cpc.getPassword()))
        {
            result.setCode(ErrorCodeEnum.ERR_PWD.getInternalCode());
            result.setMessage(ErrorCodeEnum.ERR_PWD.getInternalMessage());
            log.warning("user id:" + loginCmdMsg.getParams().getUserId() + " login failed. Password error");
            return result;
        }

        // 判断sessionId是否存在
        String httpSessionId = loginCmdMsg.getParams().getSessionId();
        if (!SessionUtil.isHttpSessionExist(httpSessionId))
        {
            result.setCode(ErrorCodeEnum.ERR_SESSIONID.getInternalCode());
            result.setMessage(ErrorCodeEnum.ERR_SESSIONID.getInternalMessage());
            log.warning("Error code:" + ErrorCodeEnum.ERR_SESSIONID.getInternalCode() + " Http session id:"
                    + httpSessionId + " " + ErrorCodeEnum.ERR_SESSIONID.getInternalMessage());
            return result;

        }

        // 绑定sessionId和channel的关系
        Channel channel = channelHandler.getCtx().channel();
        updateChannelProperty(loginCmdMsg, channel);

        result.setCode(ErrorCodeEnum.ERR_OK.getInternalCode());
        result.setMessage(ErrorCodeEnum.ERR_OK.getInternalMessage());

        log.info("user id:" + loginCmdMsg.getParams().getUserId() + " and session id:"
                + loginCmdMsg.getParams().getSessionId() + " Login webSocket ok");

        return result;

    }

    /**
     * 更新 channel 属性及状态。
     *
     * @param loginCmdMsg
     * @param channel
     */
    public void updateChannelProperty(CmdMsg<LoginParam> loginCmdMsg, Channel channel)
    {
        ChannelProperty channelProperty = channel.attr(AttributeKeyConst.CHANNEL_PROPERTY_KEY).get();
        if (channelProperty == null)
        {
            log.warning("Error: can not get the ChannelProperty when Update Channel Property");
            return;
        }

        channelProperty.setSessionId(loginCmdMsg.getParams().getSessionId());
        channelProperty.setUserId(loginCmdMsg.getParams().getUserId());
        channelProperty.setStatus(ChannelStatus.SESSION_BOUND);
        channelProperty.setStatusCount(0);

        log.info("Bind propertis to channel. session:" + loginCmdMsg.getParams().getSessionId() + " user:"
                + loginCmdMsg.getParams().getUserId());
    }

    public TextWebSocketFrameHandler getChannelHandler()
    {
        return channelHandler;
    }

    public void setChannelHandler(TextWebSocketFrameHandler channelHandler)
    {
        this.channelHandler = channelHandler;
    }

}
