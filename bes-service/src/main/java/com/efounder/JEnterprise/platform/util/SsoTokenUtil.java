package com.efounder.JEnterprise.platform.util;



import com.alibaba.fastjson.JSONObject;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import org.apache.shiro.session.Session;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * describe: sso token操作类
 *
 * @author zs
 * @date 2020/12/2
 */
public class SsoTokenUtil {


    /**
     * 将获取到accss_token等值保存到cookie中
     * @param response
     * @param accessToken
     * @param refreshToken
     * @param expiresTime
     */
    public static void setCookieToken(HttpServletResponse response, String accessToken, String refreshToken, int expiresTime){
        //将access_token,refresh_token保存到Cookie中，并设置cookie最大存活时间等于expires_in
//        Cookie cookie = new Cookie(SsoConstant.ACCESS_TOKEN_NAME, accessToken);
//        //Sets the maximum age of the cookie in seconds.
//        //cookie存活时间,单位是秒
//        cookie.setMaxAge(expiresTime);
//        response.addCookie(cookie);
//
//        Cookie refreshCookie = new Cookie(SsoConstant.REFRESH_TOKEN_NAME, refreshToken);
//        refreshCookie.setMaxAge(expiresTime);
//        response.addCookie(refreshCookie);
        addCookie(response,SsoConstant.ACCESS_TOKEN_NAME,accessToken,expiresTime);
        addCookie(response,SsoConstant.REFRESH_TOKEN_NAME,refreshToken,expiresTime);
    }

    /**
     * 移除单点登录保存token的cookie
     * @param response
     */
    public static void removeCookieToken(HttpServletResponse response){
       removeCookie(response,SsoConstant.ACCESS_TOKEN_NAME);
       removeCookie(response,SsoConstant.REFRESH_TOKEN_NAME);
    }

    /**
     * 添加cookie
     * @param response
     * @param key
     * @param value
     * @param maxAge
     */
    public static void addCookie(HttpServletResponse response,String key,String value,int maxAge){
        Cookie cookie = new Cookie(key, value);
        //cookie存活时间,单位是秒
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 删除cookie
     * @param response
     * @param key cookie 的key值
     */
    public static void removeCookie(HttpServletResponse response,String key){
        //删除名称为key的Cookie
        Cookie newCookie=new Cookie(key,null);

        //立即删除型
        newCookie.setMaxAge(0);

        //项目所有目录均有效，这句很关键，否则不敢保证删除
        newCookie.setPath("/");

        //重新写入，将覆盖之前的
        response.addCookie(newCookie);
    }


    /**
     * 将获取到accss_token等值保存到session中
     * @param request
     * @param accessToken
     * @param refreshToken
     * @param expiresIn
     */
    public static void setHttpSessionToken(HttpServletRequest request, String accessToken, String refreshToken, String expiresIn){
        HttpSession session = request.getSession();
        session.setAttribute(SsoConstant.ACCESS_TOKEN_NAME, accessToken);
        session.setAttribute(SsoConstant.REFRESH_TOKEN_NAME, refreshToken);
//        session.setAttribute(SsoConstant.EXPIRES_IN_NAME, expiresIn);
        Integer expiresTime = Integer.parseInt(expiresIn);
        //保存token失效时间
        long maxIdleTime = System.currentTimeMillis() + expiresTime * 1000;
        session.setAttribute(SsoConstant.EXPIRATION_TIME_NAME, maxIdleTime);
        session.setMaxInactiveInterval(expiresTime * 1000);
    }


    /**
     * 将获取的中台的登录用户信息转成本系统的用户对象
     * @param result
     * @return
     */
    public static ESUser getUserInfo(String result){
        ESUser esUser = new ESUser();
        JSONObject nameObject = JSONObject.parseObject(result);
        JSONObject nameJson = nameObject.getJSONObject("name");
        String username = nameJson.getString("username");
        String id = nameJson.getString("id");
        esUser.setF_id(Long.parseLong(id));
        esUser.setF_name(username);
        esUser.setF_yhbh(username);
        return esUser;
    }



    /**
     * 将access_token,refresh_token,expires_in保存到session中
     * @param session
     * @param accessToken
     * @param refreshToken
     * @param expiresTime
     */
    public static void setShiroSessionToken(Session session, String accessToken, String refreshToken, Integer expiresTime){
        session.setAttribute(SsoConstant.ACCESS_TOKEN_NAME, accessToken);
        session.setAttribute(SsoConstant.REFRESH_TOKEN_NAME, refreshToken);
//        session.setAttribute(SsoConstant.EXPIRES_IN_NAME, expiresIn);
        //保存token失效时间
        long maxIdleTime = System.currentTimeMillis() + expiresTime * 1000;
        session.setAttribute(SsoConstant.EXPIRATION_TIME_NAME, maxIdleTime);
        //设置session的生效时间，单位毫秒
        session.setTimeout(expiresTime * 1000);
    }
}
