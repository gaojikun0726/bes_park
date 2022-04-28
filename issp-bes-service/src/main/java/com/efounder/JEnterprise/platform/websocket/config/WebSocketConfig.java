package com.efounder.JEnterprise.platform.websocket.config;

import com.efounder.JEnterprise.platform.websocket.handler.AppWebSocketHandler;
import com.efounder.JEnterprise.platform.websocket.handler.PduWebSocketHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * describe: 开启WebSocket支持
 *
 * @author zs
 * @date 2021/2/5
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private Logger log = LogManager.getLogger(WebSocketConfig.class);

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        String mapping = "/screen/wb";
        webSocketHandlerRegistry.addHandler(webSocketHandler(),mapping).setAllowedOrigins("*");
        log.info("WebSocket已注册，WB地址：" + mapping);


        String mappingPdu = "/screen/wb/pdu";
        webSocketHandlerRegistry.addHandler(pduWebSocketHandler(),mappingPdu).setAllowedOrigins("*");
        log.info("WebSocket已注册，WB地址：" + mappingPdu);
    }

    /**
     * 处理大屏数据websocket
     * @return
     */
    @Bean
    public WebSocketHandler webSocketHandler(){
        return new AppWebSocketHandler();
    }

    /**
     * 处理PDU数据websocket
     * @return
     */
    @Bean
    public WebSocketHandler pduWebSocketHandler(){
        return new PduWebSocketHandler();
    }
}
