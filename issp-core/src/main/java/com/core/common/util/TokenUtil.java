package com.core.common.util;

import com.alibaba.fastjson.JSONObject;
import com.core.common.conn.restful.ISSPURLConnection;
import com.efounder.JEnterprise.common.constants.Constants;
import org.apache.shiro.SecurityUtils;

public class TokenUtil {
	/**
	 * 通过用户名密码获取token
	 * 
	 * @return
	 */
	public static String getTokenByUserAndPass(){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("uName", "admin");
		jsonObject.put("uPwd", "admin123");

		String rw1 = ISSPURLConnection.processRequest("http://124.128.225.22:18080/QLJTIOTWebapp/issp/v1.0/login", "post", jsonObject);
		JSONObject parseObject = JSONObject.parseObject(rw1);
		String token = (String) parseObject.get("token");
		return token;
	}
	
	/**
	 * 获取当前登录用户token
	 * 
	 * @return
	 */
	public static String getCurrentLoginToken(){
		return (String) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_KEY);
	}
}
