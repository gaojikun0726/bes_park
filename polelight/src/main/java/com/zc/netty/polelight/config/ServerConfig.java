package com.zc.netty.polelight.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * describe: 服务端配置项
 *
 * @author zs
 * @date 2021/11/18
 */
@Component
public class ServerConfig {

    @Value("${pdu.server.port}")
    public Integer serverPort;

    /**
     * 推送开关状态url
     */
    @Value("${pdu.url.switch_status_url}")
    public String switchStatusUrl;
}
