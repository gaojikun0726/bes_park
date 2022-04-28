package com.core.config.sso;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * describe: 获取配置文件的配置值
 *
 * @author zs
 * @date 2020/11/24
 */
@Component
public class SsoConfig {

    /**
     * 是否开启oauth2的单点登录功能，默认不开启
     */
    @Value("${sso.oauth2.enable:false}")
    public Boolean ssoEnable;

    @Value("${sso.oauth2.client.client_id}")
    public String clientId;

    @Value("${sso.oauth2.client.client_secret}")
    public String clientSecret;

    @Value("${sso.oauth2.client.grant_type}")
    public String grantType;

    @Value("${sso.oauth2.client.redirect_uri}")
    public String redirectUri;

    @Value("${sso.oauth2.client.access-token-uri}")
    public String accessTokenUri;

    @Value("${sso.oauth2.client.user-authorization-uri}")
    public String userAuthorizationUri;

    @Value("${sso.oauth2.client.check-token-endpoint-uri}")
    public String checkTokenUri;

    @Value("${sso.oauth2.resource.user-info-uri}")
    public String userInfoUri;

    @Value("${sso.sso_token_uri}")
    public String ssoTokenUri;

    @Value("${sso.sso_user_uri}")
    public String ssoUserUri;

    @Value("${sso.logout_url}")
    public String logoutUrl;

//    /**
//     * 暂时使用，获取测试用的ssoToken
//     */
//    @Value("${sso.test_ssotoken_url}")
//    public String testSsoTokenUrl;


    /**
     * 授权码模式--返回获取code的url
     * get请求
     * @return
     */
    public String getCodeUrl(){
        StringBuffer url = new StringBuffer();
        url.append(userAuthorizationUri).append("?");
        url.append("client_id=").append(clientId);
        url.append("&redirect_uri=").append(redirectUri);
        url.append("&response_type=code");
        return url.toString();
    }

    /**
     * 返回获取token的url
     * post请求
     * @return
     */
    public String getAccessTokenUrl(){
        return accessTokenUri;
    }

    /**
     * 授权码模式--返回获取token所需的请求参数
     * post请求
     * @return
     */
    public String getAccessTokenParams(String code){
        StringBuffer params = new StringBuffer();
        params.append("client_id=").append(clientId);
        params.append("&client_secret=").append(clientSecret);
        params.append("&grant_type=").append(grantType);
        params.append("&code=").append(code);
        params.append("&redirect_uri=").append(redirectUri);
        return params.toString();
    }

    /**
     * 返回获取user信息的url
     * @param accessToken token
     * @return
     */
    public String getUserInfoUrl(String accessToken){
        StringBuffer url = new StringBuffer();
        url.append(userInfoUri).append("?");
        url.append("access_token=").append(accessToken);
        return url.toString();
    }


    /**
     * 刷新token--返回刷新token的url
     * get请求
     * @param refreshToken
     * @return
     */
    public String getRefreshTokenUrl(String refreshToken){
        //http://172.16.26.7:8991/auth/oauth/token?grant_type=refresh_token&client_id=qz_opm5&client_secret=qz_opm&refresh_token=c3d3eb9e-dca9-4e0f-9ef1-b0d21272a211
        StringBuffer url = new StringBuffer();
        url.append(accessTokenUri).append("?");
        url.append("grant_type=refresh_token");
        url.append("&client_id=").append(clientId);
        url.append("&client_secret=").append(clientSecret);
        url.append("&refresh_token=").append(refreshToken);
        return url.toString();
    }

    /**
     * 密码模式--获取token
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public String getTokenByPasswordUrl(String username,String password){
        //http://172.16.26.7:8991/auth/oauth/token?username=admin&password=admin&grant_type=password&client_id=ql_nengyuan&client_secret=qz_opm
        StringBuffer url = new StringBuffer();
        url.append(accessTokenUri).append("?");
        url.append("client_id=").append(clientId);
        url.append("&client_secret=").append(clientSecret);
        url.append("&grant_type=password");
        url.append("&username=").append(username);
        url.append("&password=").append(password);
        return url.toString();
    }


    /**
     * 客户端凭证模式--获取token的url
     * @return
     */
    public String getTokenByClientCredentials(){
//        http://172.16.26.7:8991/auth/oauth/token?client_id=ql_nengyuan&client_secret=qz_opm&grant_type=client_credentials
        StringBuffer url = new StringBuffer();
        url.append(accessTokenUri).append("?");
        url.append("client_id=").append(clientId);
        url.append("&client_secret=").append(clientSecret);
        url.append("&grant_type=client_credentials");
        return url.toString();
    }

    /**
     * 简化模式--获取token
     * @return
     */
    public String getTokenByImplicit(){
//        http://172.16.26.7:8991/auth/oauth/authorize?client_id=ql_nengyuan&client_secret=qz_opm&response_type=token&redirect_uri=http://172.31.14.209:8082/BESServer/callback
        StringBuffer url = new StringBuffer();
        url.append(userAuthorizationUri).append("?");
        url.append("client_id=").append(clientId);
        url.append("&client_secret=").append(clientSecret);
        url.append("&response_type=token");
        url.append("&redirect_uri=").append(redirectUri);
        return url.toString();
    }
}
