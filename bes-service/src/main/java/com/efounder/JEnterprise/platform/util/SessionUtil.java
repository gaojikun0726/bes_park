package com.efounder.JEnterprise.platform.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * describe: 操作session工具类
 *
 * @author zs
 * @date 2020/11/27
 */
public class SessionUtil {


    /**
     * 将获取到accss_token等值保存到session中
     * @param request
     * @param accessToken
     * @param refreshToken
     * @param expiresIn
     */
    public static void setAccessToken(HttpServletRequest request, String accessToken, String refreshToken, String expiresIn){
        HttpSession session = request.getSession();
        session.setAttribute(SsoConstant.ACCESS_TOKEN_NAME, accessToken);
        session.setAttribute(SsoConstant.REFRESH_TOKEN_NAME, refreshToken);
//        session.setAttribute(SsoConstant.EXPIRES_IN_NAME, expiresIn);
        Integer expiresTime = Integer.parseInt(expiresIn);
        //保存token失效时间
        long maxIdleTime = System.currentTimeMillis() + expiresTime * 1000;
        session.setAttribute(SsoConstant.EXPIRATION_TIME_NAME, maxIdleTime);
    }
}
