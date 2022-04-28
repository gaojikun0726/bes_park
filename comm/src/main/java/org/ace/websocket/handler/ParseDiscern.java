package org.ace.websocket.handler;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.ace.websocket.constant.Method;
import org.ace.websocket.constant.RequestObject;
import org.ace.websocket.constant.ResponseObject;
import org.ace.websocket.core.TextWebSocketFrameHandler;
import org.ace.websocket.handler.heartbeat.HeartbeatCmdHandler;
import org.ace.websocket.handler.heartbeat.HeartbeatMsgParser;
import org.ace.websocket.handler.heartbeat.HeartbeatResultSender;
import org.ace.websocket.handler.login.LoginCmdHandler;
import org.ace.websocket.handler.login.LoginMsgParser;
import org.ace.websocket.handler.login.LoginResultSender;

/**
 * 消息识别器
 * @author xiepufeng
 */
public class ParseDiscern {

    /*
     * 消息通道处理器
     */
    private TextWebSocketFrameHandler channelHandler;


    public ParseDiscern(TextWebSocketFrameHandler channelHandler) {
        this.channelHandler = channelHandler;
    }

    public void parseAndProcessByJsonRpcType(JsonObject jsonObject, String rpcType)
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

    private void parseAndProcessByMethod(JsonObject jsonObject, String method)
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

        /* Begin. 测试代码：  JsonParser的用法*/
        //        if (!jsonObject.has(JsonMsgConst.Header.PARAMS))
        //        {
        //            return;
        //        }
        //
        //        JsonObject params = jsonObject.getAsJsonObject(JsonMsgConst.Header.PARAMS);
        /* End. 测试代码：  JsonParser的用法*/
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

        HeartbeatResultSender heartbeatResultSender = new HeartbeatResultSender();
        heartbeatResultSender.setChannelHandler(channelHandler);

        RpcCmdProcessor rpcCmd = new RpcCmdProcessor();
        rpcCmd.setJsonMsgParser(heartbeatMsgParser);
        rpcCmd.setCmdHandler(heartbeatCmdHandler);
        rpcCmd.setResultSendable(heartbeatResultSender);

        rpcCmd.parseAndExecute(jsonObject);
    }


    /**
     * 登录 WebSocket 绑定 WebSocket 连接到 http session。
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

    public TextWebSocketFrameHandler getChannelHandler() {
        return channelHandler;
    }

    public void setChannelHandler(TextWebSocketFrameHandler channelHandler) {
        this.channelHandler = channelHandler;
    }
}
