package org.ace.socketserver.handler.heartbeat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.ace.socketserver.core.SocketServerHandler;
import org.ace.socketserver.dto.param.HeartbeatError;
import org.ace.socketserver.dto.result.CmdExeResult;
import org.ace.socketserver.dto.result.ErrorMsg;
import org.ace.socketserver.dto.result.HeartbeatResult;
import org.ace.socketserver.dto.result.ResultMsg;
import org.ace.socketserver.enums.ErrorCodeEnum;
import org.ace.socketserver.handler.AbstractSender;
import org.ace.socketserver.handler.ResultSendable;

import java.lang.reflect.Type;
import java.util.logging.Logger;

public class HeartbeatResultSender extends AbstractSender implements ResultSendable
{

    private static final Logger log = Logger.getLogger(HeartbeatResultSender.class.getName());

    @Override
    public void send(CmdExeResult result)
    {
        if (result.getCode() != ErrorCodeEnum.ERR_OK.getInternalCode())
        {
            HeartbeatError heartbeatError = new HeartbeatError();
            heartbeatError.setCode(result.getCode());
            heartbeatError.setMessage(result.getMessage());

            ErrorMsg<HeartbeatError> errorMsg = new ErrorMsg<>();
            errorMsg.setError(heartbeatError);

            Gson gson = new Gson();
            Type type = new TypeToken<ErrorMsg<HeartbeatError>>()
            {
            }.getType();
            String msg = gson.toJson(errorMsg, type);

            SocketServerHandler socketServerHandler = getChannelHandler();
            if (socketServerHandler == null || socketServerHandler.getCtx() == null)
            {
                log.warning("heartbeat fail, because the channel maybe closed.");
                return;
            }

            socketServerHandler.getCtx().channel().writeAndFlush(msg);
            return;
        }

        HeartbeatResult heartbeatResult = new HeartbeatResult();
        heartbeatResult.setMessage(result.getMessage());

        ResultMsg<HeartbeatResult> resultMsg = new ResultMsg<>();
        resultMsg.setResult(heartbeatResult);

        Gson gson = new Gson();
        Type type = new TypeToken<ResultMsg<HeartbeatResult>>()
        {
        }.getType();

        String msg = gson.toJson(resultMsg, type);
        SocketServerHandler socketServerHandler = getChannelHandler();
        if (socketServerHandler == null || socketServerHandler.getCtx() == null)
        {
            log.warning("Heartbeat fail, because the channel maybe closed.");
            return;
        }

        socketServerHandler.getCtx().channel().writeAndFlush(msg);
    }

}
