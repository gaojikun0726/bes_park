package org.ace.socketserver.handler.login;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.ace.socketserver.core.SocketServerHandler;
import org.ace.socketserver.dto.param.LoginError;
import org.ace.socketserver.dto.param.LoginParam;
import org.ace.socketserver.dto.result.*;
import org.ace.socketserver.enums.ErrorCodeEnum;
import org.ace.socketserver.handler.AbstractSender;
import org.ace.socketserver.handler.ResultSendable;

import java.lang.reflect.Type;
import java.util.logging.Logger;

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
            errorMsg.setError(loginError);

            Gson gson = new Gson();
            Type type = new TypeToken<ErrorMsg<LoginError>>()
            {
            }.getType();
            String msg = gson.toJson(errorMsg, type);

            SocketServerHandler socketServerHandler = getChannelHandler();
            if (socketServerHandler == null)
            {
                return;
            }

            socketServerHandler.getCtx().channel().writeAndFlush(msg);
            return;
        }

        LoginResult loginResult = new LoginResult();
        loginResult.setMessage(result.getMessage());

        ResultMsg<LoginResult> resultMsg = new ResultMsg<>();
        resultMsg.setResult(loginResult);

        Gson gson = new Gson();
        Type type = new TypeToken<ResultMsg<LoginResult>>()
        {
        }.getType();

        String msg = gson.toJson(resultMsg, type);
        SocketServerHandler socketServerHandler = getChannelHandler();
        if (socketServerHandler == null || socketServerHandler.getCtx() == null)
        {
            log.warning("Login fail, because the channel maybe closed. login user tokenSN:" + loginCmd.getParams().getTokenSN());
            return;
        }

        socketServerHandler.getCtx().channel().writeAndFlush(msg);
    }
}
