package com.efounder.JEnterprise.platform.controller;

import com.core.common.util.CurrentUserUtils;
import com.core.config.sso.SsoConfig;
import com.efounder.JEnterprise.config.shiro.vo.Principal;
import com.efounder.JEnterprise.platform.service.SsoLoginService;
import com.efounder.JEnterprise.platform.util.InterfaceAccessUtil;
import com.efounder.JEnterprise.platform.util.SsoConstant;
import com.efounder.JEnterprise.platform.util.SsoTokenUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * describe: 单点登录退出控制器
 *
 * @author zs
 * @date 2020/12/3
 */
@Controller
public class SsoLogoutController {

    @Resource
    private SsoLoginService ssoLoginService;

    @Resource
    private SsoConfig ssoConfig;

    /**
     * 退出：走中台的退出接口和本系统的退出代码
     * @param request
     * @param response
     * @return
     */
//    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Principal principal = (Principal) SecurityUtils.getSubject().getPrincipal();
        // 将用户与角色信息插入登录日志中
        ssoLoginService.addSysLoginLog(principal.getUser().getF_yhbh(), "1");
        // 退出
        SecurityUtils.getSubject().logout();
        CurrentUserUtils.clear();
        Cookie[] cookies = request.getCookies();
        boolean flag = false;
        String accessToken = "";
      for(Cookie cookie : cookies){
            String cName = cookie.getName();
            String cValue = cookie.getValue();
            if(SsoConstant.ACCESS_TOKEN_NAME.equals(cName)){
                //是从中台访问过来的，有access_token
                accessToken = cValue;
               flag = true;
               break;
            }
        }
        if(flag){
            String result = InterfaceAccessUtil.doPostBearerToken(ssoConfig.logoutUrl,accessToken);
            //删除保存token的cookie
            SsoTokenUtil.removeCookieToken(response);
        }
        return "redirect:/login";
    }

}
