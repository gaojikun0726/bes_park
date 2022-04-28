package com.efounder.JEnterprise.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.core.common.util.CurrentUserUtils;
import com.core.config.qxpz.IpOnOffConfig;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.common.util.DateUtil;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.config.shiro.vo.Principal;
import com.efounder.JEnterprise.model.basedatamanage.zzjg.ESZzjg;
import com.efounder.JEnterprise.model.systemcenter.logmanage.ESSysLoginLog;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import com.efounder.JEnterprise.service.basedatamanage.zzjg.ESZzjgService;
import com.efounder.JEnterprise.service.systemcenter.logmanage.ESSysLoginLogService;
import com.efounder.JEnterprise.service.usercenter.ESUserService;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 综合业务集成  单点登录
 * @author yujieying
 * @date 2019/10/14
 *
 */
@Controller

public class CheckLoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    
     @Autowired
	private ESUserService esUserService;
 	@Autowired
 	private ESZzjgService esZzjgService;
	@Autowired
	private ESSysLoginLogService esSysLoginLogService;
	@Autowired
	private IpOnOffConfig ipConfig;
    
  
    @RequestMapping(value = "/SSOCheckLogin", method = RequestMethod.GET)
    public String SSOCheck(HttpServletRequest req) throws Exception {
    	log.info("#单点登录开始");
        try {
            req.setCharacterEncoding("utf-8");
            // 接收参数： token   loginname   mark
            String sso_token = req.getParameter("sso_token");
            // 系统用户账号
            String loginname = req.getParameter("loginname");
            // mark标识是pc端还是移动端
            String mark = req.getParameter("mark");
            // 验证登录账号是否存在
            log.info("#验证登录账号是否存在");
            List<ESUser> userlist = esUserService.findUsers();
            List<String> user = new ArrayList<String>();
            for(ESUser esuser :userlist){
            	user.add(esuser.getF_yhbh());
            }
            if(user.contains(loginname.toString())){
            log.info("#登录账号存在---登录账号:"+loginname.toString());
            // 验证token是否有效(post 请求处理请求结果)
            String result = doPost("http://10.200.10.142:8090/xdl-core/api_v1_oauth/checkToken", sso_token);
            log.info("#验证ssotoken,返回result值:"+result.toString());
            // 有效则跳转系统首页
            if("0".equals(result.toString())) {
                //if("pc".equals(mark)) {
                    // 给账号绑定权限
                    // 跳转到主页面
                	 String username = loginname;
                	 log.info("#ssotoken通过，开始验证登录，账号为："+"admin"+"密码为"+"qljt2019");
                     UsernamePasswordToken token = new UsernamePasswordToken("admin", "qljt2019");
                     // 获取当前的Subject
                     Subject currentUser = SecurityUtils.getSubject();
                    
                         // 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
                         // 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
                         // 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
                         log.info("对用户[" + username + "]进行登录验证..验证开始");
                         currentUser.login(token);
                         log.info("对用户[" + username + "]进行登录验证..验证通过");
                     
                     // 验证是否登录成功
             		if (currentUser.isAuthenticated()) {
             			 Session session = currentUser.getSession(true);
             			 SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_KEY, session.getId());
             			 log.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
             			// 登录成功后获取 用户与角色信息 插入登录日志中
             			 addSysLoginLog(username, "0");
                         Principal principal = (Principal) currentUser.getPrincipal();
                         CurrentUserUtils.setUserId(principal.getUser().getF_id());
                         String ipConfigMark = ipConfig.getSysParameterIpable();
             			if(ipConfigMark.equals("0")){
             				 log.info("#ssotoken通过，admin登录成功，进入工作台");
             				return "redirect:/index";
             			}else{
             				 log.info("#---");
             				return "redirect:/interceptor?username="+username;
             			}
             		} else {
             			 log.info("#ssotoken通过，admin登录失败，进入登录首页");
                         token.clear();
                         return "redirect:/login";
                     }
                	
                /*}else {
                }*/
            // token无效则跳转错误页面
            }else {
               // if("pc".equals(mark)) {
                    // 跳转到登录页面
            	    log.info("#ssotoken不通过，进入登录首页");
                	return "redirect:/login";
                	//res.sendRedirect("redirect:/login");
               /* }else {
                }*/
            }
            }else{
            	 log.info("#登录账号不存在---跳到登录首页");
            	return "redirect:/login";
            }
        }catch (Exception e) {
            throw new Exception("测试单点登录验证失败");
        }
    }

 

    /**
	 * 将用户与角色信息插入登录日志中
	 * @param username 用户名
	 * @param type 登录登出类型
	 * @return
	 */
	private void addSysLoginLog(String username, String type){
        log.info("#开始将用户"+username+"与角色信息插入登录日志中");
		List<ESUser> list = esUserService.findUserByusername(username);
		ESSysLoginLog esSysLoginLog = new ESSysLoginLog();
		esSysLoginLog.setF_yhbh(list.get(0).getF_yhbh());
		esSysLoginLog.setF_username(list.get(0).getF_name());
		log.info("#用户编号："+esSysLoginLog.getF_yhbh().toString()+"用户名称"+esSysLoginLog.getF_username().toString());
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


	public String doPost(String url, String sso_token) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        /*
         * 由于GET请求的参数都是拼装在URL地址后方，所以我们要构建一个URL，带参数
         */
        HttpPost httpPost = null;
        URIBuilder uriBuilder = null;
        CloseableHttpResponse response = null;
        log.info("#进入sso_token验证权限接口");
        try {
            uriBuilder = new URIBuilder(url);
            uriBuilder.addParameter("sso_token", sso_token);
            httpPost = new HttpPost(uriBuilder.build());
            httpPost.addHeader("Content-type", "application/x-www-form-urlencoded");
            //httpPost.setHeader("Accept", "application/json");
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(10000).setConnectTimeout(20000)
                    .setConnectionRequestTimeout(10000).build();
            httpPost.setConfig(requestConfig);
            response = httpClient.execute(httpPost);
            // 处理返回结果
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new ClientProtocolException(String.format("第三方系统接口请求失败，Http返回码[%d]", response.getStatusLine().getStatusCode()));
            }
            //获取http响应体
            HttpEntity entity=response.getEntity();
            if(entity!=null) {
                String content= EntityUtils.toString(entity);
                JSONObject json2 = JSON.parseObject(content);
                return json2.getString("code");
            }
            return null;
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            try {
                response.close();
                httpPost.abort();
                httpClient.close();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        return null;
    }
    
}