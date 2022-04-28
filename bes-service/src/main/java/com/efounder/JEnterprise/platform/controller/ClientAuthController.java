package com.efounder.JEnterprise.platform.controller;

import com.alibaba.fastjson.JSONObject;
import com.core.config.qxpz.IpOnOffConfig;
import com.core.config.qxpz.QxConfig;
import com.core.config.sso.SsoConfig;
import com.efounder.JEnterprise.platform.service.SsoLoginService;
import com.efounder.JEnterprise.platform.util.InterfaceAccessUtil;
import com.efounder.JEnterprise.platform.util.SsoTokenUtil;
import com.efounder.JEnterprise.service.auth.PermissionService;
import com.efounder.JEnterprise.service.basedatamanage.zzjg.ESZzjgService;
import com.efounder.JEnterprise.service.systemcenter.logmanage.ESSysLoginLogService;
import com.efounder.JEnterprise.service.usercenter.ESRoleService;
import com.efounder.JEnterprise.service.usercenter.ESUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * describe: Oauth2实现授权码访问统一登录界面，实现免登录
 *
 * @author zs
 * @date 2020/11/21
 */
@Controller
//@RequestMapping("/client")
public class ClientAuthController {

    private static final Logger log = LoggerFactory.getLogger(ClientAuthController.class);

    @Autowired
    private ESUserService userService;

    @Autowired
    private ESRoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private QxConfig config;

    @Autowired
    private ESSysLoginLogService esSysLoginLogService;
    @Autowired
    private ESZzjgService esZzjgService;

    @Autowired
    private IpOnOffConfig ipConfig;

    /**
     * 单点登录相关配置值
     */
    @Autowired
    private SsoConfig ssoConfig;

    @Resource
    private SsoLoginService ssoLoginService;


    /**
     * 进入统一验证登录页
     * @param request
     * @param response
     */
    @GetMapping("/ssoLogin")
    public void ssoLogin(HttpServletRequest request, HttpServletResponse response){
//        String codeUrl = "http://172.16.26.7:8991/auth/oauth/authorize?client_id=ql_nengyuan&redirect_uri=http://172.31.14.209:8082/BESServer/callback&response_type=code";
        String codeUrl = ssoConfig.getCodeUrl();
        try {
            response.sendRedirect(codeUrl);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("单点登录获取token报错：" + e.getMessage());
        }
    }

    /**
     * 跳转到登录页
     * @return
     */
//    @GetMapping("/callback")
    public String redirectBack(HttpServletRequest request, HttpServletResponse response){
        String code = request.getParameter("code");
        String tokenUrl = ssoConfig.getAccessTokenUrl();
//        String path  = "http://172.16.26.7:8991/auth/oauth/token";
//        String params = "client_id=ql_nengyuan&client_secret=qz_opm&grant_type=authorization_code";
//        //bceD4c
//        params += "&code="+code;
//        params += "&redirect_uri=http://172.31.14.209:8082/BESServer/callback";
        String params = ssoConfig.getAccessTokenParams(code);
        String result = InterfaceAccessUtil.doPostParam(tokenUrl,params);
        if(result == null || "".equals(result)){
            log.error("单点登录获取token失败，重新获取code");
            return "redirect:/ssoLogin";
        }
        JSONObject jsonObject = JSONObject.parseObject(result);
        String accessToken = jsonObject.getString("access_token");
        if(accessToken == null || "".equals(accessToken)){
            log.error("单点登录获取token失败，重新获取code");
            return "redirect:/ssoLogin";
        }
        String refreshToken = jsonObject.getString("refresh_token");
        String expiresIn = jsonObject.getString("expires_in");
        Integer expiresTime = Integer.parseInt(expiresIn);

        Session session = SecurityUtils.getSubject().getSession();
        //将access_token,refresh_token,expires_in保存到session中
        SsoTokenUtil.setShiroSessionToken(session,accessToken,refreshToken,expiresTime);
        //将access_token,refresh_token保存到Cookie中，并设置cookie最大存活时间等于expires_in
        SsoTokenUtil.setCookieToken(response,accessToken,refreshToken,expiresTime);

        //获取登录用户
        String username = ssoLoginService.getUser(accessToken);
        //获取token后，进行本系统登录验证
        ssoLoginService.ssoLoginVerify(session,accessToken,username);

        //return "/index"; 浏览器url类似：http://172.31.14.209:8082/BESServer/callback?code=IxKgqv
        //重定向，浏览器url正常 ：http://172.31.14.209:8082/BESServer/index
        return "redirect:/index";
    }





}
