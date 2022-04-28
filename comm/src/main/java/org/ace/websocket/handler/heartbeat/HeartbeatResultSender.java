package org.ace.websocket.handler.heartbeat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.ace.websocket.core.TextWebSocketFrameHandler;
import org.ace.websocket.dto.param.HeartbeatError;
import org.ace.websocket.dto.param.HeartbeatParam;
import org.ace.websocket.dto.result.*;
import org.ace.websocket.enums.ErrorCodeEnum;
import org.ace.websocket.handler.AbstractSender;
import org.ace.websocket.handler.ResultSendable;

import java.lang.reflect.Type;
import java.util.logging.Logger;

/**
 * 心跳消息发送器
 */
public class HeartbeatResultSender extends AbstractSender implements ResultSendable
{

    private static final Logger log = Logger.getLogger(HeartbeatResultSender.class.getName());

    @Override
    public void send(CmdExeResult result)
    {
        @SuppressWarnings("unchecked")
        CmdMsg<HeartbeatParam> cmdMsg = (CmdMsg<HeartbeatParam>) result.getJsonMsg();
        if (result.getCode() != ErrorCodeEnum.ERR_OK.getInternalCode())
        {
            HeartbeatError heartbeatError = new HeartbeatError();
            heartbeatError.setCode(result.getCode());
            heartbeatError.setMessage(result.getMessage());

            ErrorMsg<HeartbeatError> errorMsg = new ErrorMsg<>();
            errorMsg.setJsonrpc(cmdMsg.getJsonrpc());
            errorMsg.setId(cmdMsg.getId());
            errorMsg.setError(heartbeatError);

            Gson gson = new Gson();
            Type type = new TypeToken<ErrorMsg<HeartbeatError>>()
            {
            }.getType();
            String msg = gson.toJson(errorMsg, type);

            TextWebSocketFrameHandler textWebSocketFrameHandler = getChannelHandler();
            if (textWebSocketFrameHandler == null || textWebSocketFrameHandler.getCtx() == null)
            {
                log.warning("heartbeat fail, because the channel maybe closed.");
                return;
            }

            textWebSocketFrameHandler.getCtx().channel().writeAndFlush(new TextWebSocketFrame(msg));
            return;
        }

        HeartbeatResult heartbeatResult = new HeartbeatResult();
        heartbeatResult.setMessage(result.getMessage());

        ResultMsg<HeartbeatResult> resultMsg = new ResultMsg<>();
        resultMsg.setJsonrpc(cmdMsg.getJsonrpc());
        resultMsg.setId(cmdMsg.getId());
        resultMsg.setResult(heartbeatResult);

        Gson gson = new Gson();
        Type type = new TypeToken<ResultMsg<HeartbeatResult>>()
        {
        }.getType();

        String msg = gson.toJson(resultMsg, type);
        TextWebSocketFrameHandler textWebSocketFrameHandler = getChannelHandler();
        if (textWebSocketFrameHandler == null || textWebSocketFrameHandler.getCtx() == null)
        {
            log.warning("Heartbeat fail, because the channel maybe closed. Heartbeat id:" + cmdMsg.getId());
            return;
        }

        textWebSocketFrameHandler.getCtx().channel().writeAndFlush(new TextWebSocketFrame(msg));
    }

}
