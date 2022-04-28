package com.efounder.JEnterprise.platform.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.core.config.sso.SsoConfig;
import com.efounder.JEnterprise.platform.service.SsoService;
import com.efounder.JEnterprise.platform.util.InterfaceAccessUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * describe: 中台单点登录
 *
 * @author zs
 * @date 2020/12/5
 */
@Service
public class SsoServiceImpl implements SsoService {

    @Resource
    private SsoConfig ssoConfig;


    /**
     * 获取中台真实token
     * @param code
     * @param appId
     * @return
     */
    @Override
    public String getSsoToken(String code,String appId){
        //        http://172.31.7.99:8080/auth/sso/token?code=e83f6268f8aba7f7503ba3e65e961bf0&app_id=qz_opm
        String path = ssoConfig.ssoTokenUri;
        StringBuffer params = new StringBuffer();
        params.append("code=").append(code);
        params.append("&app_id=").append(appId);
        String result = InterfaceAccessUtil.doGetWithParam(path,params.toString(),"","");
        return result;
    }


    /**
     * 获取中台登录用户信息
     *
     * @param token
     * @return
     */
    @Override
    public String getSsoUser(String token) {
        //        http://172.31.7.99:8080/sys/user/info
        String path = ssoConfig.ssoUserUri;
        String result = InterfaceAccessUtil.doGetBearerToken(path,token);
        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONObject dataObject = jsonObject.getJSONObject("data");
        String username = dataObject.getString("username");
        return username;
    }
}
