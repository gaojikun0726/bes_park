package com.efounder.JEnterprise.platform.service;

/**
 * describe: 中台单点登录
 *
 * @author zs
 * @date 2020/12/5
 */
public interface SsoService {

    /**
     * 获取中台真实token
     * @param code
     * @param appId
     * @return
     */
    String getSsoToken(String code,String appId);


    /**
     * 获取中台登录用户信息
     * @param token
     * @return
     */
    String getSsoUser(String token);
}
