package org.ace.websocket.handler.login;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.ace.websocket.core.TextWebSocketFrameHandler;
import org.ace.websocket.dto.param.LoginError;
import org.ace.websocket.dto.param.LoginParam;
import org.ace.websocket.dto.result.*;
import org.ace.websocket.enums.ErrorCodeEnum;
import org.ace.websocket.handler.AbstractSender;
import org.ace.websocket.handler.ResultSendable;

import java.lang.reflect.Type;
import java.util.logging.Logger;

/**
 * 登录消息发送器
 */
public class LoginResultSender extends AbstractSender implements ResultSendable
{

    private static final Logger log = Logger.getLogger(LoginResultSender.class.getName());

    @Override
    public void send(CmdExeResult result)
    {

        @SuppressWarnings("unchecked")
        CmdMsg<LoginParam> loginCmd = (CmdMsg<LoginParam>) result.getJsonMsg();
        if (result.getCode() != ErrorCodeEnum.ERR_OK.getInternalCode())
        {
            LoginError loginError = new LoginError();
            loginError.setCode(result.getCode());
            loginError.setMessage(result.getMessage());

            ErrorMsg<LoginError> errorMsg = new ErrorMsg<>();
            errorMsg.setJsonrpc(loginCmd.getJsonrpc());
            errorMsg.setId(loginCmd.getId());
            errorMsg.setError(loginError);

            Gson gson = new Gson();
            Type type = new TypeToken<ErrorMsg<LoginError>>()
            {
            }.getType();
            String msg = gson.toJson(errorMsg, type);

            TextWebSocketFrameHandler textWebSocketFrameHandler = getChannelHandler();
            if (textWebSocketFrameHandler == null)
            {
                return;
            }

            textWebSocketFrameHandler.getCtx().channel().writeAndFlush(new TextWebSocketFrame(msg));
            return;
        }

        LoginResult loginResult = new LoginResult();
        loginResult.setMessage(result.getMessage());

        ResultMsg<LoginResult> resultMsg = new ResultMsg<>();
        resultMsg.setJsonrpc(loginCmd.getJsonrpc());
        resultMsg.setId(loginCmd.getId());
        resultMsg.setResult(loginResult);

        Gson gson = new Gson();
        Type type = new TypeToken<ResultMsg<LoginResult>>()
        {
        }.getType();

        String msg = gson.toJson(resultMsg, type);
        TextWebSocketFrameHandler textWebSocketFrameHandler = getChannelHandler();
        if (textWebSocketFrameHandler == null || textWebSocketFrameHandler.getCtx() == null)
        {
            log.warning("Login fail, because the channel maybe closed. login id:" + loginCmd.getId());
            return;
        }

        textWebSocketFrameHandler.getCtx().channel().writeAndFlush(new TextWebSocketFrame(msg));
    }

}
