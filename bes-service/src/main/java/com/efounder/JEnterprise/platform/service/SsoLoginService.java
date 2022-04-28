package com.efounder.JEnterprise.platform.service;

import com.efounder.JEnterprise.model.usercenter.ESUser;
import org.apache.shiro.session.Session;

/**
 * describe: 处理单点登录本地验证以及添加日志
 *
 * @author zs
 * @date 2020/12/2
 */
public interface SsoLoginService {


    /**
     * 获取当前登录用户
     * @param accessToken
     * @return
     */
    String getUser(String accessToken);

    /**
     * 单点登录时，进行本系统验证
     * 用户名/密码都是从数据库中查询的，走个形式，只是为了通过系统拦截
     * @param session
     * @param accessToken
     * @param username
     */
     void ssoLoginVerify(Session session, String accessToken,String username);




    /**
     * 将用户与角色信息插入登录日志中
     * 供多处调用，避免重复定义
     * @param username 用户名
     * @param type 登录登出类型
     * @return
     */
     void addSysLoginLog(String username, String type);


    /**
     * 获取加密后的密码，用于通过shiro验证
     * @param user
     * @return
     */
    String getHashPassword(ESUser user);

}
