package com.efounder.JEnterprise.platform.filter;

import com.alibaba.fastjson.JSONObject;
import com.core.config.sso.SsoConfig;
import com.efounder.JEnterprise.platform.util.InterfaceAccessUtil;
import com.efounder.JEnterprise.platform.util.SessionUtil;
import com.efounder.JEnterprise.platform.util.SsoConstant;
import com.efounder.JEnterprise.platform.util.SsoTokenUtil;
import org.apache.commons.lang.ObjectUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * describe: 单点登录请求过滤
 *
 * @author zs
 * @date 2020/11/24
 */
// 忽略资源 , initParams = { @WebInitParam(name = "exclusions", value = "/static,*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico")} 这样配置无法讲这些url排除在外
//@WebFilter(filterName = "ssoFilter", urlPatterns = "/*")
public class SsoFilter implements Filter {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private SsoConfig ssoConfig;

    @Resource
    private ShiroFilterFactoryBean shiroFilterFactoryBean;

    /**
     * 过滤器需要忽略的文件资源类型、访问请求、接口地址前缀、退出地址
     */
    private String[] ignorePattern = {"/api","/api/v1.0","/issp/v1.0","/api/public","/logout","/static",".jpg",".png",".gif",".bmp",".js",".css",".ico"};

    /**
     * 所有需要不拦截的请求模式
     */
    private List<String> filterPattern;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //获取所有需要放行的资源
        List<String> patternList = getPermitUrl();
        List<String> stringList = Arrays.asList(ignorePattern);
        patternList.addAll(stringList);
        filterPattern = patternList;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //单点登录开启，过滤请求
        //ssoConfig是否可以注入
        if(!ssoConfig.ssoEnable){
            //未开启单点登录
            chain.doFilter(request,response);
            return;
        }

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String url = httpRequest.getRequestURI();
        for(String pattern : filterPattern){
            if(url.contains(pattern)){
                //直接放行
                chain.doFilter(request,response);
                return;
            }
        }

        //已登录，直接放行，如何判断已登录，去session中取access_token，去中台校验token正确性，如果正确，放行，如果不正确，重定向到ssoLogin，请求code
        //未登录，重定向到ssoLogin，请求code
        HttpSession httpSession = httpRequest.getSession();
        //如果是/ssoLogin等请求，放行;如果是其他的，禁止通行
        String accessToken = ObjectUtils.toString(httpSession.getAttribute(SsoConstant.ACCESS_TOKEN_NAME));
        String refreshToken = ObjectUtils.toString(httpSession.getAttribute(SsoConstant.REFRESH_TOKEN_NAME));
        String expirationStr = ObjectUtils.toString(httpSession.getAttribute(SsoConstant.EXPIRATION_TIME_NAME));
        boolean flag = false;

        //本地Session中access_token已存在，且【accessToken没过期或者refreshToken成功】，放行请求
        if(accessToken != null && !"".equals(accessToken)){
            boolean valid = isTokenValid(expirationStr);
            if(!valid){
                //access_token过期了
                boolean refreshResult = getRefreshTokenResult(httpRequest,refreshToken);
                if(refreshResult){
                    //access_token刷新成功
                    flag = true;
                }
            }else{
                //access_token有效
                flag = true;
            }
        }
        if(flag){
            chain.doFilter(httpRequest,httpResponse);
            return;
        }
        String contextPath = httpRequest.getContextPath();
        //重定向
        httpResponse.sendRedirect(contextPath + "/ssoLogin");
//        //转发
//        httpRequest.getRequestDispatcher(contextPath + "/ssoLogin");

        //设置session有效期与access_token一致
        //重新获取到access_token，需要更新session的存活时间
        //todo 如果session过期了呢，需要重新登录吗? 如果本地清缓存或者session失效，会怎样？
    }


    @Override
    public void destroy() {

    }

    /**
     * access_token是否过期
     * @param expirationStr
     * @return
     */
    public boolean isTokenValid(String expirationStr){
        boolean result = false;
        if(expirationStr != null && !"".equals(expirationStr)){
            Long expirationTime = Long.valueOf(expirationStr);
            if(System.currentTimeMillis() <= expirationTime){
              result = true;
            }
        }
        return result;
    }

    /**
     * 刷新token，返回结果：成功或失败
     * @param refreshToken
     * @return
     */
    public boolean getRefreshTokenResult(HttpServletRequest request, String refreshToken){
        String result = refreshToken(refreshToken);
        JSONObject jsonObject = JSONObject.parseObject(result);
        String accessToken = jsonObject.getString("access_token");
        if(accessToken == null || "".equals(accessToken)){
            log.error("单点登录刷新token失败");
            return false;
        }
        String refreshTokenNew = jsonObject.getString("refresh_token");
        String expiresIn = jsonObject.getString("expires_in");
        SessionUtil.setAccessToken(request,accessToken,refreshTokenNew,expiresIn);

        return true;
    }

    /**
     * 刷新token方法
     * @param refreshToken
     * @return
     */
    public String refreshToken(String refreshToken){
        String url = ssoConfig.getRefreshTokenUrl(refreshToken);
        String result = InterfaceAccessUtil.doGet(url);
        return result;
    }

    /**
     * 获取配置的所有放行的url匹配模式
     * @return
     */
    public List<String> getPermitUrl(){
        Map<String, String> map = shiroFilterFactoryBean.getFilterChainDefinitionMap();
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if("anon".equals(entry.getValue())){
                list.add(entry.getKey());
            }
        }
        return list;
    }

}
