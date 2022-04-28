package com.efounder.JEnterprise.config.shiro;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * 类名称：ShiroConfiguration
 * 类描述：Shiro 配置
 * 创建人：wujf
 * 修改人：wujf
 * 修改时间：2018年1月3日 下午6:55:50
 * 修改备注：
 * @version 1.0.0
 *
 */
@SuppressWarnings("all")
@Configuration
public class ShiroConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);

    @Bean
    public EhCacheManager getEhCacheManager() {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManagerConfigFile("classpath:conf/ehcache-shiro.xml");
        return em;
    }

    //配置自定义的权限登录器
    @Bean(name = "myShiroRealm")
    public AuthorizingRealmImpl myShiroRealm(EhCacheManager cacheManager) {
        AuthorizingRealmImpl realm = new AuthorizingRealmImpl();
        realm.setCacheManager(cacheManager);
        return realm;
    }

    /**
     * 注册DelegatingFilterProxy（Shiro）
     * 集成Shiro有2种方法：
     * 1. 按这个方法自己组装一个FilterRegistrationBean（这种方法更为灵活，可以自己定义UrlPattern，
     * 在项目使用中你可能会因为一些很但疼的问题最后采用它， 想使用它你可能需要看官网或者已经很了解Shiro的处理原理了）
     * 2. 直接使用ShiroFilterFactoryBean（这种方法比较简单，其内部对ShiroFilter做了组装工作，无法自己定义UrlPattern，
     * 默认拦截 /*）
     */
    // @Bean
    // public FilterRegistrationBean filterRegistrationBean() {
    // FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
    // filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
    // // 该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
    // filterRegistration.addInitParameter("targetFilterLifecycle", "true");
    // filterRegistration.setEnabled(true);
    // filterRegistration.addUrlPatterns("/*");// 可以自己灵活的定义很多，避免一些根本不需要被Shiro处理的请求被包含进来
    // return filterRegistration;
    // }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    //配置核心安全事务管理器
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(AuthorizingRealmImpl myShiroRealm) {
        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
        dwsm.setRealm(myShiroRealm);
        // <!-- 用户授权/认证信息Cache, 采用EhCache 缓存 -->
        dwsm.setCacheManager(getEhCacheManager());
        return dwsm;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
    }

    /**
     * 加载shiroFilter权限控制规则（从数据库读取然后配置）
     */
    private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean) {
        // authc：该过滤器下的页面必须验证后才能访问，它是Shiro内置的一个拦截器org.apache.shiro.web.filter.authc.FormAuthenticationFilter
        // anon：它对应的过滤器里面是空的,什么都没做

        /////////////////////// 下面这些规则配置最好配置到配置文件中 ///////////////////////
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        logger.info("##################从数据库读取权限规则，加载到shiroFilter中##################");

        // filterChainDefinitionMap.put("/user/edit/**", "authc,perms[user:edit]");// 这里为了测试，固定写死的值，也可以从数据库或其他配置中读取

        filterChainDefinitionMap.put("/view/basedatamanage/eqmanage/getOpcKeyAll", "anon");
        filterChainDefinitionMap.put("/dataCenter/**", "anon"); // 节能办能耗上传，外部调用接口
        filterChainDefinitionMap.put("/static/**", "anon");// anon 可以理解为不拦截
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/callback", "anon");
        filterChainDefinitionMap.put("/ssoLogin", "anon");
        filterChainDefinitionMap.put("/SSOCheckLogin", "anon");
        // 免密登录
        filterChainDefinitionMap.put("/npsLogin","anon");
		filterChainDefinitionMap.put("/bes/npsLogin", "anon");
        filterChainDefinitionMap.put("/issp/v1.0/login", "anon");
        // 加密狗
        filterChainDefinitionMap.put("/ConfigInfo", "anon");
        filterChainDefinitionMap.put("/Auth", "anon");
		filterChainDefinitionMap.put("/SuperDog/superdog", "anon");
		/**智慧灯杆-netty接收数据*/
        filterChainDefinitionMap.put("/netty/receive/list", "anon");
        filterChainDefinitionMap.put("/netty/receive/cleanlist", "anon");
        //智慧灯杆大屏、音柱等接口
        filterChainDefinitionMap.put("/api/public/pole/**", "anon");
        //屏幕展示数据接口
        filterChainDefinitionMap.put("/api/public/screen/**", "anon");
        //APP数据接口
        filterChainDefinitionMap.put("/api/public/app/**", "anon");
        //放开大屏websocket请求
        filterChainDefinitionMap.put("/screen/wb/**","anon");

        filterChainDefinitionMap.put("/**", "authc");

        filterChainDefinitionMap.put("/logout", "logout");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    }

    /**
     * ShiroFilter<br/>
     * 注意这里参数中的 StudentService 和 IScoreDao 只是一个例子，因为我们在这里可以用这样的方式获取到相关访问数据库的对象，
     * 然后读取数据库相关配置，配置到 shiroFilterFactoryBean 的访问规则中。实际项目中，请使用自己的Service来处理业务逻辑。
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new MShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的连接
        //shiroFilterFactoryBean.setSuccessUrl("/user");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        loadShiroFilterChain(shiroFilterFactoryBean);
        return shiroFilterFactoryBean;
    }

}
