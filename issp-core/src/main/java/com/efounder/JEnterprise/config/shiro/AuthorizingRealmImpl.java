package com.efounder.JEnterprise.config.shiro;

import com.core.config.qxpz.QxConfig;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.common.exception.BusinessException;
import com.efounder.JEnterprise.common.util.salt.Encodes;
import com.efounder.JEnterprise.config.shiro.vo.PermissionMenuVo;
import com.efounder.JEnterprise.config.shiro.vo.Principal;
import com.efounder.JEnterprise.model.auth.PermissionModule;
import com.efounder.JEnterprise.model.usercenter.ESRole;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import com.efounder.JEnterprise.service.auth.PermissionService;
import com.efounder.JEnterprise.service.usercenter.ESRoleService;
import com.efounder.JEnterprise.service.usercenter.ESUserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @author Vincent.wang
 *
 */
public class AuthorizingRealmImpl extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(AuthorizingRealmImpl.class);

    @Autowired
    private ESUserService userService;

    @Autowired
    private ESRoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private QxConfig config;

    /**
     * 认证回调函数,登录时调用.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        try {
            if (log.isDebugEnabled()) {
                log.debug("## 正在验证用户登录...");
            }
            UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
            String username = token.getUsername();
            if (StringUtils.isBlank(username)) {
                log.error("## 非法登录 .");
                throw new BusinessException("user.illegal.login.error", "非法用户身份");
            }
            ESUser user = userService.findUserById(username);
            if (null == user) {
                log.error("## 用户不存在={} .", username);
                throw new BusinessException("user.login.error", "账号或密码错误");
            }

//            if(!ssoConfig.ssoEnable){
//                //不开启单点登录，重新设置token的password值
//                token.setPassword((token.getUsername()+String.valueOf(token.getPassword())).toCharArray());
//            }else{
//                //开启单点登录时，token中的password和SimpleAuthenticationInfo中传入的password都是从数据库中取的，进行校验只是走个形式，返回一个验证结果
//                token.setPassword(user.getF_pass().toCharArray());
//            }
            byte[] salt = Encodes.decodeHex(user.getSalt());

            Principal principal = new Principal();
            principal.setUser(user);
            principal.setRoles(roleService.findRoleByUserId(user.getF_yhbh()));

            //加载功能模块
            List<PermissionModule>  moduleList = permissionService.getPermissionModules(user.getF_yhbh());
            //加载功能菜单
            Map permissionMenus = permissionService.getPermissionMenus(user.getF_yhbh(), moduleList, getLimitSQL(user));
            //加载模块+菜单【父子关系】
            List<PermissionMenuVo> list = permissionService.findModuleMenuByUserId(user.getF_yhbh(),getLimitSQL(user));

            SecurityUtils.getSubject().getSession().setAttribute(Constants.PERMISSION_MENU_SESSION, list);
            //删除没有功能菜单的模块菜单
            List<PermissionModule> delModelList = (List<PermissionModule>) permissionMenus.get("delModelList");
            moduleList.removeAll(delModelList);
            SecurityUtils.getSubject().getSession().setAttribute(Constants.PERMISSION_MODULE_ESSION, moduleList);
            SimpleAuthenticationInfo info;
//            if(ssoConfig.ssoEnable){
                info = new SimpleAuthenticationInfo(principal,user.getF_pass(),getName());
//            }else{
//                info = new SimpleAuthenticationInfo(principal, user.getF_pass(), ByteSource.Util.bytes(salt), getName());
//            }
            return info;
        } catch (AuthenticationException e) {
            log.error("# doGetAuthenticationInfo error , message={}", e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @SuppressWarnings("unchecked")
    @Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Principal principal = (Principal) principals.fromRealm(getName()).iterator().next();
		Session session = SecurityUtils.getSubject().getSession();
		Set<String> permissions = new HashSet<String>();
		Object permisObj = session.getAttribute(Constants.PERMISSION_URL);
		if (null == permisObj) {
			// 加载功能模块
			List<PermissionModule> moduleList = permissionService.getPermissionModules(principal.getUser().getF_yhbh());
			String mkGuid;
			for (int i = 0; i < moduleList.size(); i++) {
				mkGuid = moduleList.get(i).getGuid();
				// mkbh = moduleList.get(i).getMkbh();
				Collection<PermissionMenuVo> pers = permissionService
						.getPermissionMenusByModuleGUID(principal.getUser()
								.getF_yhbh(), mkGuid, getLimitSQL(principal.getUser()));
				for (PermissionMenuVo permission : pers) {
					permissions.add(permission.getUrl());
					if (CollectionUtils.isNotEmpty(permission.getChildren())) {
						for (PermissionMenuVo childrenPer : permission.getChildren()) {
							permissions.add(childrenPer.getUrl());
						}
					}
				}
			}
			session.setAttribute(Constants.PERMISSION_URL, permissions);
		} else {
			permissions = (Set<String>) permisObj;
		}
		Set<String> roleCodes = new HashSet<String>();
		Object roleNameObj = session.getAttribute(Constants.ROLE_CODE);
		if (null == roleNameObj) {
			for (ESRole role : roleService.findRoleByUserId(principal.getUser().getF_yhbh())) {
				roleCodes.add(role.getF_rolebh());
			}
			session.setAttribute(Constants.ROLE_CODE, roleCodes);
		} else {
			roleCodes = (Set<String>) roleNameObj;
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(roleCodes);
		info.setStringPermissions(permissions);
		return info;
	}

//    /**
//     * 设定Password校验的Hash算法与迭代次数.
//     */
//    @PostConstruct
//    public void initCredentialsMatcher() {
//        //为单点登录去掉security加密验证,不用单点登录时，启用加密验证
//        if(!ssoConfig.ssoEnable){
//            HashedCredentialsMatcher matcher = new HashedCredentialsMatcher("SHA-1");
//            matcher.setHashIterations(1024);
//            setCredentialsMatcher(matcher);
//        }
//    }

    /**
     * 用户功能权限过滤SQL
     *
     * @param user
     * @return
     */
    protected String getLimitSQL(ESUser user){
        StringBuffer limitSQL = new StringBuffer();
        String qxsh = config.getQxsh();
        String userGnqx = config.getUserGnqx();
        String gnqxRlgl = config.getGnqxRlgl();
        String classifyGnqxb = config.getClassifyGnqxb();
        String gnqxUseable = config.getGnqxUseable();
        if ("0".equals(gnqxUseable)) {
			return "";
		}
        limitSQL.append("EXISTS (SELECT 1 FROM ");
    	limitSQL.append(userGnqx);
    	limitSQL.append(" QX WHERE QX.F_YHBH = '");
    	limitSQL.append(user.getF_yhbh());
    	limitSQL.append("' AND QX.F_SH = '");
    	limitSQL.append(qxsh);
    	limitSQL.append("' AND QX.F_GNZDID = P.F_GUID)");

    	limitSQL.append(" OR EXISTS (SELECT 1 FROM ");
    	limitSQL.append(classifyGnqxb);
    	limitSQL.append(" GNQX WHERE EXISTS(SELECT 1 FROM ");
    	limitSQL.append(gnqxRlgl);
    	limitSQL.append(" RLGL WHERE GNQX.F_ROLEBH = RLGL.F_ROLEGUID AND RLGL.F_YHBH = '");
    	limitSQL.append(user.getF_yhbh());
		limitSQL.append("') AND GNQX.F_SH = '");
		limitSQL.append(qxsh);
		limitSQL.append("' AND GNQX.F_GNZDID = P.F_GUID)");

        return limitSQL.toString();
    }
}