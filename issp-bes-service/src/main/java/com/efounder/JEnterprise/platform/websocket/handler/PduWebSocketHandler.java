package com.efounder.JEnterprise.platform.websocket.handler;

import com.alibaba.fastjson.JSONObject;
import com.efounder.JEnterprise.platform.websocket.config.PduSessionManager;
import com.efounder.JEnterprise.platform.websocket.config.PduStatusData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

/**
 * describe: websocket连接处理类
 *
 * @author zs
 * @date 2021/2/5
 */
@Component
public class PduWebSocketHandler implements WebSocketHandler {
    private static final Logger log = LogManager.getLogger(PduWebSocketHandler.class);

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus arg1) throws Exception{
        log.info("正常日志："+session.getRemoteAddress()+"断开连接！");
        PduSessionManager.sessionMap.remove(session.getId());
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception{
        log.info("正常日志："+session.getRemoteAddress()+"打开连接！");
        PduSessionManager.sessionMap.put(session.getId(),session);
    }

    @Override
    public void handleMessage(WebSocketSession conn, WebSocketMessage<?> message) throws Exception {
        log.info("websocket接收到消息："+message.getPayload());
        String msg = message.getPayload().toString();
        if("HeartBeat".equals(msg)){
            //心跳信息
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msg","heartbeat");
            conn.sendMessage(new TextMessage(jsonObject.toString()));
            return;
        }
        JSONObject jsonObject = JSONObject.parseObject(msg);
        String cmdType = jsonObject.getString("cmdType");


        if("read_all".equals(cmdType)){
            //获取全部数据
            jsonObject.put("statusData", PduStatusData.switchStatusMap);
            jsonObject.put("onlineData",PduStatusData.pduOnlineStatusMap);
            TextMessage textMessage = new TextMessage(jsonObject.toString());
            conn.sendMessage(textMessage);
        }

    }


    @Override
    public void handleTransportError(WebSocketSession session, Throwable arg1) throws Exception {
        if(session.isOpen()){
            session.close();
        }
        log.error( "出错日志: IP:" +session.getRemoteAddress()+"  "+ arg1.getMessage() );
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

}
