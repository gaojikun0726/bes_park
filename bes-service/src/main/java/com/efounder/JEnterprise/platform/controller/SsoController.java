package com.efounder.JEnterprise.platform.controller;

import com.alibaba.fastjson.JSONObject;
import com.core.common.util.CurrentUserUtils;
import com.core.config.qxpz.IpOnOffConfig;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.config.shiro.vo.Principal;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import com.efounder.JEnterprise.platform.service.SsoLoginService;
import com.efounder.JEnterprise.platform.service.SsoService;
import com.efounder.JEnterprise.platform.util.SsoTokenUtil;
import com.efounder.JEnterprise.service.usercenter.ESUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * describe: 中台自定义单点登录：实现中台访问能源免登录
 *
 * @author zs
 * @date 2020/12/4
 */
@Controller
public class SsoController {

    private static final Logger log = LoggerFactory.getLogger(SsoController.class);

    @Resource
    private SsoLoginService ssoLoginService;

    @Resource
    private SsoService ssoService;

    @Resource
    private ESUserService userService;

    @Resource
    private IpOnOffConfig ipConfig;

    /**
     * 中台点击跳转到能源登录页
     * @return
     */
    @GetMapping("/callback")
    public String redirectBack(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        String ssoToken = request.getParameter("ssotoken");
        String route = request.getParameter("route");
        String appId = request.getParameter("appid");
        String result = ssoService.getSsoToken(ssoToken,appId);
        JSONObject jsonObject = JSONObject.parseObject(result);
        String accessToken = jsonObject.getString("access_token");
        if(accessToken == null || "".equals(accessToken)){
            log.error("中台单点登录获取真实token失败");
            return "redirect:/login";
        }
        String refreshToken = jsonObject.getString("refresh_token");
        String expiresIn = jsonObject.getString("expires_in");
        Integer expiresTime = Integer.parseInt(expiresIn);

        Session session = SecurityUtils.getSubject().getSession();
        //将access_token,refresh_token,expires_in保存到session中
        SsoTokenUtil.setShiroSessionToken(session,accessToken,refreshToken,expiresTime);
//        //将access_token,refresh_token保存到Cookie中，并设置cookie最大存活时间等于expires_in
//        SsoTokenUtil.setCookieToken(response,accessToken,refreshToken,expiresTime);

        //获取当前登录用户
        String username = ssoService.getSsoUser(accessToken);


        ESUser realUser = userService.findUserById(username);
        if(realUser == null){
            redirectAttributes.addFlashAttribute("message", "该用户名不存在");
            return "redirect:/login";
        }
        //security登录设置
        UsernamePasswordToken token = new UsernamePasswordToken(realUser.getF_yhbh(), realUser.getF_pass());
        // 获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        //如果去掉验证，redirect:/index会被拦截,必须进行security登录验证
        try {
            // 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            // 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            // 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            currentUser.login(token);
            log.info("对用户[" + username + "]进行登录验证..验证通过");
        } catch (UnknownAccountException uae) {
            log.error("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            redirectAttributes.addFlashAttribute("message", "未知账户");
        } catch (IncorrectCredentialsException ice) {
            log.error("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
            redirectAttributes.addFlashAttribute("message", "密码不正确");
        } catch (LockedAccountException lae) {
            log.error("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            redirectAttributes.addFlashAttribute("message", "账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            log.error("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
            // 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            log.error("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
        }

        // 验证是否登录成功
        if (currentUser.isAuthenticated()) {
             session = currentUser.getSession(true);
            SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_KEY, session.getId());
            SecurityUtils.getSubject().getSession().setAttribute(Constants.USERNAME, username);
            log.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
            // 登录成功后获取 用户与角色信息 插入登录日志中
            ssoLoginService.addSysLoginLog(username, "0");
            Principal principal = (Principal) currentUser.getPrincipal();
            CurrentUserUtils.setUserId(principal.getUser().getF_id());
            String ipConfigMark = ipConfig.getSysParameterIpable();
            if("0".equals(ipConfigMark)){
                //不拦截
                return "redirect:/index";
            }else{
                return "redirect:/interceptor?username="+username;
            }
        } else {
            token.clear();
            return "redirect:/login";
        }
    }




}
