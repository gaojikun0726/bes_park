package com.efounder.JEnterprise.controller;

import com.core.common.ISSPReturnObject;
import com.core.common.util.CurrentUserUtils;
import com.core.config.qxpz.IpOnOffConfig;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.common.util.DateUtil;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.common.util.salt.Digests;
import com.efounder.JEnterprise.common.util.salt.Encodes;
import com.efounder.JEnterprise.config.shiro.vo.Principal;
import com.efounder.JEnterprise.initializer.SubRealTimeDataCache;
import com.efounder.JEnterprise.model.basedatamanage.zzjg.ESZzjg;
import com.efounder.JEnterprise.model.systemcenter.logmanage.ESSysLoginLog;
import com.efounder.JEnterprise.model.usercenter.ESRole;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import com.efounder.JEnterprise.service.basedatamanage.zzjg.ESZzjgService;
import com.efounder.JEnterprise.service.systemcenter.logmanage.ESSysLoginLogService;
import com.efounder.JEnterprise.service.usercenter.ESRoleService;
import com.efounder.JEnterprise.service.usercenter.ESUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import static com.efounder.JEnterprise.service.usercenter.impl.ESUserServiceImpl.HASH_INTERATIONS;
import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

@Controller
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private ESUserService esUserService;
	@Autowired
	private ESSysLoginLogService esSysLoginLogService;
	@Autowired
	private ESZzjgService esZzjgService;
	@Autowired
	private IpOnOffConfig ipConfig;

	@Autowired
	private ESRoleService roleService;

	@Resource
	private SubRealTimeDataCache subRealTimeDataCache;

	@Value("${comm.websocket.port.mapping}")
	private int port;
	@Autowired
	private ESUserService userService;

	@Value("${comm.websocket.path}")
	private String path;

	@Value("${comm.websocket.password}")
	private String password;

	@Value("${comm.websocket.heartbeat.interval}")
	private int interval;
	
    @RequestMapping(value = "login", method = RequestMethod.GET)
    String login(Model model) {
        model.addAttribute("user", new ESUser());
        log.info("#去登录");
        return "login";
    }

	// 免密登录接口
	@RequestMapping(value = "/npsLogin", method = RequestMethod.GET)
	String npsLogin(@ModelAttribute("userForm") ESUser user,RedirectAttributes redirectAttributes) {
		log.info("#第三方免密登录接口");
		log.info("第三方登录账号:"+user.getF_yhbh());
		log.info("第三方登录密码:"+user.getF_pass());
		//ESUser user = new ESUser();
		//user.setF_yhbh("admin");
		//user.setF_pass("123456");
		if (null == user || StringUtils.isBlank(user.getF_yhbh()) || StringUtils.isBlank(user.getF_pass())) {
			log.error("# 账号或密码错误");
			return "redirect:/login";
		}

		String username = user.getF_yhbh();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getF_yhbh(), user.getF_pass());
		// 获取当前的Subject
		Subject currentUser = SecurityUtils.getSubject();
		try {
			// 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
			// 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
			// 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
			log.info("对用户[" + username + "]进行登录验证..验证开始");
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
			Session session = currentUser.getSession(true);
			SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_KEY, session.getId());
			log.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
			// 登录成功后获取 用户与角色信息 插入登录日志中
			addSysLoginLog(username, "0");
			String ipConfigMark = ipConfig.getSysParameterIpable();
			if(ipConfigMark.equals("0")){
				return "redirect:/index";
			}else{
				return "redirect:/interceptor?username="+username;
			}
		} else {
			token.clear();
			return "redirect:/login";
		}
	}

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("userForm") ESUser user, RedirectAttributes redirectAttributes) {
        log.info("# 登录中 ");
        if (null == user || StringUtils.isBlank(user.getF_yhbh()) || StringUtils.isBlank(user.getF_pass())) {
            log.error("# 账号或密码错误");
			redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
            return "redirect:/login";
        }

		//将密码加密，验证时直接与加过密的密码做对比
		//拿到salt进行加密
		ESUser realUser = userService.findUserById(user.getF_yhbh());
        if(realUser == null){
			redirectAttributes.addFlashAttribute("message", "该用户名不存在");
			return "redirect:/login";
		}
		byte[] salt = Encodes.decodeHex(realUser.getSalt());
		byte[] hashPassword = Digests.sha1((user.getF_yhbh()+user.getF_pass()).getBytes(), salt, HASH_INTERATIONS);
		//设置用同样的salt加密后生成的密码，交给shiro进行校验
		user.setF_pass(Encodes.encodeHex(hashPassword));
        String username = user.getF_yhbh();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getF_yhbh(), user.getF_pass());
        // 获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            // 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            // 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            // 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            log.info("对用户[" + username + "]进行登录验证..验证开始");
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
			Session session = currentUser.getSession(true);
			SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_KEY, session.getId());
			SecurityUtils.getSubject().getSession().setAttribute(Constants.USERNAME, username);
			log.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
			// 登录成功后获取 用户与角色信息 插入登录日志中
			addSysLoginLog(username, "0");
            Principal principal = (Principal) currentUser.getPrincipal();
            CurrentUserUtils.setUserId(principal.getUser().getF_id());
            String ipConfigMark = ipConfig.getSysParameterIpable();

            // 角色添加到 session
			Set<String> roleCodes = new HashSet<>();

			List<ESRole> esRoles = principal.getRoles();

			if (esRoles != null && !esRoles.isEmpty())
			{

				for (ESRole role : esRoles) {
					roleCodes.add(role.getF_rolebh());
				}
				session.setAttribute(Constants.ROLE_CODE, roleCodes);

			}

			// 组织添加到 session
			session.setAttribute(Constants.GROUP_CODE, principal.getUser().getF_zzjgbh());

			if(ipConfigMark.equals("0")){
				return "redirect:/index";
			}else{
				return "redirect:/interceptor?username="+username;
			}
		} else {
            token.clear();
            return "redirect:/login";
        }
    }

    //IP拦截器
    @RequestMapping("/interceptor")
	public String getIP(String username) throws UnknownHostException{
		InetAddress address = InetAddress.getLocalHost();
		String ip= address.getHostAddress(); //获取IP地址
		boolean findip =esUserService.interceptIP(ip);
		if(findip){
		//IP地址正确，跳转到主页面
			return "redirect:/interceptorUser?username="+username;
		}else{
		//IP地址不正确，跳转到登录页面
			return "redirect:/login";
		}
	}
    
    //用户名拦截
    @RequestMapping("/interceptorUser")
    public String username(String username) throws UnknownHostException{
    	InetAddress address = InetAddress.getLocalHost();
		String ip= address.getHostAddress(); //获取IP地址
		String login=esUserService.username(ip); 
		String name=username;
		//判断用户名是否符合
		boolean status = login.contains(name);        
		if(status){ 
			//正确进入主页面
				return "redirect:/index";
			}else{   
			//不正确回到登录页面
				return "redirect:/login";
			}
    }

    @RequestMapping("/logout")
	public String logout() {
    	Principal principal = (Principal) SecurityUtils.getSubject().getPrincipal();

		// 根据 sessionId 取消订阅到缓存
		subRealTimeDataCache.unsubscribeCacheBySessionId(SecurityUtils.getSubject().getSession().getId().toString());
    	// 将用户与角色信息插入登录日志中
    	addSysLoginLog(principal.getUser().getF_yhbh(), "1");
		// 退出
		SecurityUtils.getSubject().logout();
		CurrentUserUtils.clear();
		return "redirect:/login";
	}
    
    @RequestMapping(value ="/abnormallogout")
	public void abnormallogout() {
    	Principal principal = (Principal) SecurityUtils.getSubject().getPrincipal();
    	// 将用户与角色信息插入登录日志中
    	addSysLoginLog(principal.getUser().getF_yhbh(), "2");
}
    
	/**
	 * 将用户与角色信息插入登录日志中
	 * @param username 用户名
	 * @param type 登录登出类型
	 * @return
	 */
	private void addSysLoginLog(String username, String type){
		List<ESUser> list = esUserService.findUserByusername(username);
		ESSysLoginLog esSysLoginLog = new ESSysLoginLog();
		esSysLoginLog.setF_yhbh(list.get(0).getF_yhbh());
		esSysLoginLog.setF_username(list.get(0).getF_name());
		// 获取角色信息
		String rolebh = "";
		String rolemc = "";
		for (ESUser euser : list) {
			if (euser.getF_rolebh() != null)
				rolebh = rolebh + "/" + euser.getF_rolebh();
			if (euser.getF_rolemc() != null)
				rolemc = rolemc + "/" + euser.getF_rolemc();
		}
		String bh = "".equals(rolebh) ? null : rolebh.substring(2, rolebh.length() - 1);
		String mc = "".equals(rolemc) ? null : rolemc.substring(1, rolemc.length());
		esSysLoginLog.setF_rolebh(bh);
		esSysLoginLog.setF_rolemc(mc);
		// 自动生成guid
		esSysLoginLog.setF_id(UUIDUtil.getRandom32BeginTimePK());
		// 0表示登录
		esSysLoginLog.setF_type(type);
		esSysLoginLog.setF_logintime(DateUtil.getCurrTime());
		// 根据组织机构编号获取zzjgid
		List<ESZzjg> zzjglist = esZzjgService.getId(list.get(0).getF_zzjgbh());
		if(zzjglist != null && zzjglist.size() > 0){
			esSysLoginLog.setF_zzjgid(zzjglist.get(0).getF_id());
		}
		// 将用户与角色信息插入登录日志中
		esSysLoginLogService.addSysLoginLogid(esSysLoginLog);
	}
	
    @RequestMapping("/403")
    public String unauthorizedRole() {
        log.info("------没有权限-------");
        return "403";
    }
    
    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "issp_left/{menuId}", method = RequestMethod.GET)
    public String leftMenu(@PathVariable String menuId, ModelMap map) {
    	log.info("加载左侧树");
    	Map attribute = (Map) SecurityUtils.getSubject().getSession().getAttribute(Constants.PERMISSION_MENU_SESSION);
    	map.addAttribute("menu_session", (List) attribute.get(menuId));
		return "issp_left";
    }

	@RequestMapping(value = "/issp_left", method = RequestMethod.GET)
	public String getLeftMenu(ModelMap map) {
		log.info("加载左侧树");
		List attribute = (ArrayList) SecurityUtils.getSubject().getSession().getAttribute(Constants.PERMISSION_MENU_SESSION);
		map.addAttribute("menu_session", attribute);
		return "issp_left";
    }
    
    @RequestMapping(value = "issperror", method = RequestMethod.GET)
    public String isspErrorPage() {
    	return "common/error";
    }

    /**
     * webSocket 登录信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/wsLoginInfo", method = RequestMethod.GET)
    public ISSPReturnObject wsLoginInfo() throws UnknownHostException
	{

		ISSPReturnObject result  = new ISSPReturnObject();

		Map data = new HashMap();

		Session session = SecurityUtils.getSubject().getSession();
		String userId = (String) session.getAttribute(Constants.USERNAME);

		InetAddress inetAddress = InetAddress.getLocalHost();
		String ip = inetAddress.getHostAddress();

		data.put("sessionId", session.getId());
		data.put("userId", userId);
		data.put("host", ip);
		data.put("port", port);
		data.put("path", path);
		data.put("password", password);
		data.put("interval", interval);

		result.setStatus("1");
		result.setData(data);

    	return result;
    }

}