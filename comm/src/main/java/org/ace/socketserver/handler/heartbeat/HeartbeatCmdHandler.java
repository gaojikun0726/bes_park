package org.ace.socketserver.handler.heartbeat;

import io.netty.channel.Channel;
import org.ace.socketserver.bo.ChannelProperty;
import org.ace.socketserver.constant.AttributeKeyConst;
import org.ace.socketserver.core.SocketServerHandler;
import org.ace.socketserver.dto.param.HeartbeatParam;
import org.ace.socketserver.dto.result.CmdExeResult;
import org.ace.socketserver.dto.result.CmdMsg;
import org.ace.socketserver.dto.result.JsonMsg;
import org.ace.socketserver.enums.ChannelStatus;
import org.ace.socketserver.enums.ErrorCodeEnum;
import org.ace.socketserver.handler.CmdHandler;
import org.ace.socketserver.util.MsgUtil;
import org.ace.socketserver.util.TokenUtil;

import java.util.logging.Logger;

public class HeartbeatCmdHandler implements CmdHandler
{

    private static final Logger log = Logger.getLogger(HeartbeatCmdHandler.class.getName());

    /*
     * 消息通道处理器
     */
    private SocketServerHandler channelHandler;

    @Override
    public CmdExeResult execute(JsonMsg jsonMsg)
    {
        @SuppressWarnings("unchecked")
        CmdMsg<HeartbeatParam> cmdMsg = (CmdMsg<HeartbeatParam>) jsonMsg;

        CmdExeResult result = new CmdExeResult();
        result.setJsonMsg(jsonMsg);

        // 判断 tokenSN 是否存在
        String tokenSN = cmdMsg.getParams().getTokenSN();

        if (!TokenUtil.isTokenExist(tokenSN))
        {
            result.setCode(ErrorCodeEnum.ERR_TOKENSN.getInternalCode());
            result.setMessage(ErrorCodeEnum.ERR_TOKENSN.getInternalMessage());
            log.warning("Error code:" + ErrorCodeEnum.ERR_TOKENSN.getInternalCode() + " tokenSN:"
                    + tokenSN + " " + ErrorCodeEnum.ERR_TOKENSN.getInternalMessage());
            return result;

        }

        // 根据 tokenSN 查找 channel
        Channel channelSubscriber = MsgUtil.getChannelByTokenSN(tokenSN);
        if (channelSubscriber == null)
        {
            result.setCode(ErrorCodeEnum.ERR_TOKENSN_NOT_BIND_CHANNEL.getInternalCode());
            result.setMessage(ErrorCodeEnum.ERR_TOKENSN_NOT_BIND_CHANNEL.getInternalMessage());
            log.warning("Error code:" + ErrorCodeEnum.ERR_TOKENSN.getInternalCode() + " tokenSN:"
                    + tokenSN + " " + ErrorCodeEnum.ERR_TOKENSN.getInternalMessage());

            return result;
        }

        // 获取 channel 属性对象
        ChannelProperty channelProperty = channelSubscriber.attr(AttributeKeyConst.CHANNEL_PROPERTY_KEY).get();
        if (channelProperty == null)
        {
            result.setCode(ErrorCodeEnum.ERR_CHANNEL_NO_PROPERTY.getInternalCode());
            result.setMessage(ErrorCodeEnum.ERR_CHANNEL_NO_PROPERTY.getInternalMessage());
            log.warning("Error code:" + ErrorCodeEnum.ERR_TOKENSN.getInternalCode() + " tokenSN:"
                    + tokenSN + " " + ErrorCodeEnum.ERR_TOKENSN.getInternalMessage());

            return result;

        }

        // 没有绑定到 http session 则不接收心跳消息
        ChannelStatus channelStatus = channelProperty.getStatus();
        if (ChannelStatus.TOKENSN_BOUND != channelStatus)
        {
            result.setCode(ErrorCodeEnum.ERR_HEARTBEAT_AT_INCORRECT_CHANNEL_STATUS.getInternalCode());
            result.setMessage(ErrorCodeEnum.ERR_HEARTBEAT_AT_INCORRECT_CHANNEL_STATUS.getInternalMessage());

            log.warning("Error code:" + ErrorCodeEnum.ERR_HEARTBEAT_AT_INCORRECT_CHANNEL_STATUS.getInternalCode()
                    + " tokenSN:" + tokenSN + " "
                    + ErrorCodeEnum.ERR_HEARTBEAT_AT_INCORRECT_CHANNEL_STATUS.getInternalMessage());

            return result;

        }

        // 刷新心跳计数器复位标志，通知定时器任务复位心跳计数器
        channelProperty.heartbeatRefresh();

        result.setCode(ErrorCodeEnum.ERR_OK.getInternalCode());
        result.setMessage(ErrorCodeEnum.ERR_OK.getInternalMessage());

        return result;
    }

    public SocketServerHandler getChannelHandler()
    {
        return channelHandler;
    }

    public void setChannelHandler(SocketServerHandler channelHandler)
    {
        this.channelHandler = channelHandler;
    }
}
