package com.efounder.JEnterprise.platform.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.core.common.util.CurrentUserUtils;
import com.core.config.sso.SsoConfig;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.common.util.DateUtil;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.common.util.salt.Digests;
import com.efounder.JEnterprise.common.util.salt.Encodes;
import com.efounder.JEnterprise.config.shiro.vo.Principal;
import com.efounder.JEnterprise.model.basedatamanage.zzjg.ESZzjg;
import com.efounder.JEnterprise.model.systemcenter.logmanage.ESSysLoginLog;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import com.efounder.JEnterprise.platform.service.SsoLoginService;
import com.efounder.JEnterprise.platform.util.InterfaceAccessUtil;
import com.efounder.JEnterprise.service.basedatamanage.zzjg.ESZzjgService;
import com.efounder.JEnterprise.service.systemcenter.logmanage.ESSysLoginLogService;
import com.efounder.JEnterprise.service.usercenter.ESUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.efounder.JEnterprise.service.usercenter.impl.ESUserServiceImpl.HASH_INTERATIONS;

/**
 * describe: 处理单点登录本地验证以及添加日志
 *
 * @author zs
 * @date 2020/12/2
 */
@Service
public class SsoLoginServiceImpl implements SsoLoginService {

    @Autowired
    private ESUserService userService;

    @Autowired
    private ESSysLoginLogService esSysLoginLogService;
    @Autowired
    private ESZzjgService esZzjgService;

    @Resource
    private SsoConfig ssoConfig;


    /**
     * 获取当前登录用户
     * @param accessToken
     * @return
     */
    @Override
   public String getUser(String accessToken){
       String userUrl = ssoConfig.getUserInfoUrl(accessToken);
//        String userUrl = "http://172.16.26.7:8080/sys/user/oauth/info?access_token="+accessToken;
       String userResult = InterfaceAccessUtil.doGet(userUrl);
        JSONObject nameObject = JSONObject.parseObject(userResult);
        JSONObject nameJson = nameObject.getJSONObject("name");
        String username = nameJson.getString("username");
//       ESUser user = SsoTokenUtil.getUserInfo(userResult);
       return username;
   }

    /**
     * 单点登录时，进行本系统验证
     * 用户名/密码都是从数据库中查询的，走个形式，只是为了通过系统拦截
     * @param session
     * @param accessToken
     */
    @Override
    public void ssoLoginVerify(Session session,String accessToken,String username){

        ESUser realUser = userService.findUserById(username);
        //security登录设置
        UsernamePasswordToken token = new UsernamePasswordToken(realUser.getF_yhbh(), realUser.getF_pass());
//        UsernamePasswordToken token = new UsernamePasswordToken(user.getF_yhbh(), "123456");
        // 获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        //如果去掉验证，redirect:/index会被拦截,必须进行security登录验证
        currentUser.login(token);
//        Session session = currentUser.getSession(true);
        session.setAttribute(Constants.SESSION_KEY, session.getId());
        session.setAttribute(Constants.USERNAME, username);
        //将用户与角色信息插入登录日志中
        addSysLoginLog(username, "0");
        //todo 起到什么作用？
        // 对接中台的用户数据后，f_id就有值了
        Principal principal = (Principal) currentUser.getPrincipal();
        CurrentUserUtils.setUserId(principal.getUser().getF_id());
        //        //ip拦截
//        String ipConfigMark = ipConfig.getSysParameterIpable();
    }



    /**
     * 将用户与角色信息插入登录日志中
     * 供多处调用，避免重复定义
     * @param username 用户名
     * @param type 登录登出类型
     * @return
     */
    @Override
    public void addSysLoginLog(String username, String type){
        List<ESUser> list = userService.findUserByusername(username);
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


    /**
     * 获取加密后的密码，用于通过shiro验证
     * @param user
     * @return
     */
    @Override
    public String getHashPassword(ESUser user){
        //将密码加密，验证时直接与加过密的密码做对比
        //拿到salt进行加密
        ESUser realUser = userService.findUserById(user.getF_yhbh());
        byte[] salt = Encodes.decodeHex(realUser.getSalt());
        byte[] hashPassword = Digests.sha1((user.getF_yhbh()+user.getF_pass()).getBytes(), salt, HASH_INTERATIONS);
        //设置用同样的salt加密后生成的密码，交给shiro进行校验
        String password = Encodes.encodeHex(hashPassword);
        return password;
    }



}
