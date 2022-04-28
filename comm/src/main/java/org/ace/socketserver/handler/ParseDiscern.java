package org.ace.socketserver.handler;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.ace.socketserver.constant.Method;
import org.ace.socketserver.constant.RequestObject;
import org.ace.socketserver.constant.ResponseObject;
import org.ace.socketserver.core.SocketServerHandler;
import org.ace.socketserver.handler.heartbeat.HeartbeatCmdHandler;
import org.ace.socketserver.handler.heartbeat.HeartbeatMsgParser;
import org.ace.socketserver.handler.heartbeat.HeartbeatResultSender;
import org.ace.socketserver.handler.login.LoginCmdHandler;
import org.ace.socketserver.handler.login.LoginMsgParser;
import org.ace.socketserver.handler.login.LoginResultSender;


/**
 * @author xiepufeng
 */
public class ParseDiscern
{

    /*
     * 消息通道处理器
     */
    private SocketServerHandler channelHandler;

    public ParseDiscern(SocketServerHandler channelHandler) {
        this.channelHandler = channelHandler;
    }

    public void parseAndProcessByJsonType(JsonObject jsonObject, String rpcType)
    {

        if (null == rpcType || null == jsonObject)
        {
            return;
        }

        switch (rpcType)
        {
            case RequestObject.METHOD:
                JsonElement jsonElement = jsonObject.getAsJsonPrimitive(rpcType);
                if (null == jsonElement)
                {
                    break;
                }

                String method = jsonElement.getAsString();
                parseAndProcessByMethod(jsonObject, method);
                break;

            case ResponseObject.RESULT:
                break;

            case ResponseObject.ERROR:
                break;

            default:
                break;
        }

    }

    private  void parseAndProcessByMethod(JsonObject jsonObject, String method)
    {
        if (null == method || null == jsonObject)
        {
            return;
        }

        switch (method)
        {
            case Method.LOGIN:

                parseAndProcessLogin(jsonObject);
                break;

            case Method.EVENT:
                break;

            case Method.HEARTBEAT:
                parseAndProcessHeartbeat(jsonObject);
                break;

            default:
                break;
        }

    }

    /**
     * 业务层心跳处理
     *
     * @param jsonObject
     */
    private void parseAndProcessHeartbeat(JsonObject jsonObject)
    {
        if (null == jsonObject)
        {
            return;
        }

        // 解析并执行具体的命令
        HeartbeatMsgParser heartbeatMsgParser = new HeartbeatMsgParser();

        HeartbeatCmdHandler heartbeatCmdHandler = new HeartbeatCmdHandler();
        heartbeatCmdHandler.setChannelHandler(channelHandler);

        HeartbeatResultSender heartbeatResultSender = new HeartbeatResultSender();
        heartbeatResultSender.setChannelHandler(channelHandler);

        RpcCmdProcessor rpcCmd = new RpcCmdProcessor();

        rpcCmd.setJsonMsgParser(heartbeatMsgParser);
        rpcCmd.setCmdHandler(heartbeatCmdHandler);
        rpcCmd.setResultSendable(heartbeatResultSender);

        rpcCmd.parseAndExecute(jsonObject);
    }


    /**
     * 登录 Socket 绑定 Socket
     *
     * @param jsonObject
     */
    private void parseAndProcessLogin(JsonObject jsonObject)
    {
        if (null == jsonObject)
        {
            return;
        }

        // 解析并执行具体的命令
        LoginMsgParser loginMsgParser = new LoginMsgParser();

        LoginCmdHandler loginCmdHandler = new LoginCmdHandler();
        loginCmdHandler.setChannelHandler(channelHandler);

        LoginResultSender loginResultSender = new LoginResultSender();
        loginResultSender.setChannelHandler(channelHandler);

        RpcCmdProcessor rpcCmd = new RpcCmdProcessor();

        rpcCmd.setJsonMsgParser(loginMsgParser);
        rpcCmd.setCmdHandler(loginCmdHandler);
        rpcCmd.setResultSendable(loginResultSender);

        rpcCmd.parseAndExecute(jsonObject);

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
