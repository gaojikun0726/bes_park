package com.efounder.JEnterprise.platform.websocket.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.efounder.JEnterprise.initializer.SbPzStructCache;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESSbPzStruct;
import com.efounder.JEnterprise.platform.websocket.config.SessionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * describe: websocket连接处理类
 *
 * @author zs
 * @date 2021/2/5
 */
@Component
public class AppWebSocketHandler implements WebSocketHandler {
    private static final Logger log = LogManager.getLogger(AppWebSocketHandler.class);

    @Autowired
    private SbPzStructCache sbPzStructCache;

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus arg1) throws Exception{
        log.info("正常日志："+session.getRemoteAddress()+"断开连接！");
        SessionManager.sessionMap.remove(session.getId());
        SessionManager.sessionIdNameDataMap.remove(session.getId());
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception{
        log.info("正常日志："+session.getRemoteAddress()+"打开连接！");
        SessionManager.sessionMap.put(session.getId(),session);
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
            String names = jsonObject.getString("names");
            SessionManager.sessionIdNameDataMap.put(conn.getId(),names);
            List<Map> list = new ArrayList<>();
            //返回缓存中的数据
            JSONArray jsonArray = (JSONArray) JSONArray.parse(names);
            jsonArray.forEach(name ->{
                String sysname = (String) name;
                BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElement(sysname);
                if(besSbPzStruct == null){
                    return;
                }
                String value = besSbPzStruct.getF_init_val();
                String unit = besSbPzStruct.getUnit();
                Map<String,String> map = new HashMap<>();
                map.put("name",sysname);
                map.put("value",value);
                map.put("unit",unit);
                list.add(map);
            });
            jsonObject.put("list",list);
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
