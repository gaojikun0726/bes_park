package com.efounder.JEnterprise.zhdg.websocket;

import com.alibaba.fastjson.JSONObject;
import com.efounder.JEnterprise.platform.websocket.config.PduSessionManager;
import com.efounder.JEnterprise.platform.websocket.config.PduStatusData;
import com.efounder.JEnterprise.zhdg.util.ThreadPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;

/**
 * describe: 推送pdu数据给websocket连接
 *
 * @author zs
 * @date 2021/12/31
 */
@Component
public class PduThread {

    private static final Logger log = LogManager.getLogger(PduThread.class);

    /**
     * 每隔一分钟向前台推送一次PDU状态数据
     */
    public void pushThread(){
        ThreadPool.executor.execute(()->{
            while(true){
                try {
                    pushData();
                    Thread.sleep(60000);
//                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    log.error("推送pdu数据给websocket连接报错："+e.getMessage());
                }
            }

        });
    }


    private void pushData(){

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("statusData", PduStatusData.switchStatusMap);
        jsonObject.put("onlineData",PduStatusData.pduOnlineStatusMap);

        TextMessage textMessage = new TextMessage(jsonObject.toString());
        PduSessionManager.sessionMap.forEach((sessionId, wbSession)->{
            try {
                wbSession.sendMessage(textMessage);
            } catch (IOException e) {
                log.error("向前台推送pdu状态数据报错:"+e.getMessage());
            }
        });
    }
}
