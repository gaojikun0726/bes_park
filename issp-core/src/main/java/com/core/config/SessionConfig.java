package com.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400*30) //设置Session失效时间，使用Redis Session之后，原Boot的server.session.timeout属性不再生效
public class SessionConfig {
}
