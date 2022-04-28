package com.efounder.JEnterprise.config.druid;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 *   
 * 类名称：DruidStatViewServlet
 * 类描述：druid数据源状态监控
 * 创建人：wujf
 * 修改人：wujf
 * 修改时间：2018年1月3日 下午6:48:36
 * 修改备注：
 * @version 1.0.0 
 *
 */
@WebServlet(urlPatterns = "/druid/*", initParams = { @WebInitParam(name = "allow", value = ""), // IP白名单 (没有配置或者为空，则允许所有访问)
    @WebInitParam(name = "deny", value = "192.168.1.200"), // IP黑名单 (存在共同时，deny优先于allow)
//    @WebInitParam(name = "loginUsername", value = "admin"), // 用户名
//    @WebInitParam(name = "loginPassword", value = "123456"), // 密码
    @WebInitParam(name = "resetEnable", value = "false")// 禁用HTML页面上的“Reset All”功能
})
public class DruidStatViewServlet extends StatViewServlet {

    private static final long serialVersionUID = 1776463055477495407L;

}
