package com.efounder.JEnterprise.platform.util;

/**
 * describe: 单点登录常量值
 *
 * @author zs
 * @date 2020/11/27
 */
public class SsoConstant {


    /**
     * 保存access_token和refresh_token时的名称，比如保存到session中，用于存取
     */
    public static final String ACCESS_TOKEN_NAME = "access_token";
    public static final String REFRESH_TOKEN_NAME = "refresh_token";
    public static final String EXPIRES_IN_NAME = "expires_in";
    public static final String EXPIRATION_TIME_NAME = "expirationTime";
}
