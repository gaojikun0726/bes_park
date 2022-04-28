package com.efounder.JEnterprise.controller.interfaceUtil;

import com.alibaba.fastjson.JSONObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.config.shiro.vo.Principal;
import com.efounder.JEnterprise.model.usercenter.ESRole;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import com.efounder.JEnterprise.platform.config.OpmConfig;
import com.efounder.JEnterprise.service.usercenter.ESUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiSort;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 外部系统调用RESTful接口前调用登录
 *
 * @author LvSihan
 * @date 2019年4月12日
 * @version 1.0
 */
@RestController
@Api(value="ISSPLoginController",tags={"系统登录"})
@ApiSort(value = 0)
public class ISSPLoginController {

    @Autowired
    private ESUserService userService;

    @Resource
    private OpmConfig opmConfig;

	private static final Logger log = LoggerFactory.getLogger(ISSPLoginController.class);

	@ApiOperation(value="获取系统token", notes="外部系统调用RESTful接口前必须先获取token")
	@RequestMapping(value = "/issp/v1.0/login", method = RequestMethod.POST)
	public JSONObject login(
            @ApiParam(value = "能源标识", required = true)
            @RequestParam String appId
//	        @ApiParam(value = "用户名", required = true)
//            @RequestParam String uName,
//            @ApiParam(value = "密码", required = true)
//            @RequestParam String uPwd
    ) {

//        // 退出  使session失效。下次请求时拿到一个新的token值，而上次的sessionId【token】值失效。
//        SecurityUtils.getSubject().logout();
//        CurrentUserUtils.clear();

	    log.info("外部系统调用获取token接口！");
//        if (StringUtils.isBlank(uName) || StringUtils.isBlank(uPwd)) {
//            log.error("# 账号或密码错误");
//            return null;
//        }
		JSONObject jsonObject = new JSONObject();
        if(!opmConfig.appId.equals(appId)){
            jsonObject.put("msg","错误的系统标识");
            jsonObject.put("status","fail");
            return jsonObject;
        }

        String uName = opmConfig.loginName;

//        ESUser user = new ESUser();
//        user.setF_yhbh(uName);
//        user.setF_pass(uPwd);
        //将密码加密，验证时直接与加过密的密码做对比
        //拿到salt进行加密
        ESUser realUser = userService.findUserById(uName);
        if(realUser == null){
            log.error("外部系统调用获取token方法：用户名不存在");
            jsonObject.put("status", "fail");
            jsonObject.put("msg","接口内部错误");
            return jsonObject;
        }
//        byte[] salt = Encodes.decodeHex(realUser.getSalt());
//        byte[] hashPassword = Digests.sha1((user.getF_yhbh()+user.getF_pass()).getBytes(), salt, HASH_INTERATIONS);
        //设置用同样的salt加密后生成的密码，交给shiro进行校验
//        user.setF_pass(Encodes.encodeHex(hashPassword));
        UsernamePasswordToken token = new UsernamePasswordToken(realUser.getF_yhbh(), realUser.getF_pass());
//        UsernamePasswordToken token = new UsernamePasswordToken(uName, uPwd);
        // 获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            log.info("外部系统调用获取token方法：对用户[" + uName + "]进行登录验证..验证开始");
            currentUser.login(token);
            log.info("外部系统调用获取token方法：对用户[" + uName + "]进行登录验证..验证通过");
        } catch (Exception e) {
        	log.error(e.getMessage());
        }
        // 验证是否登录成功
		if (currentUser.isAuthenticated()) {
			Session session = currentUser.getSession(true);

            Principal principal = (Principal) currentUser.getPrincipal();

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

            log.info("外部系统调用获取token方法：用户[" + uName + "]登录成功！！");
			jsonObject.put("token", session.getId());
			jsonObject.put("status", "success");
		} else {
            token.clear();
            jsonObject.put("status", "fail");
        }
		return jsonObject;
	}
}
