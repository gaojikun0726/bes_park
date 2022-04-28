package com.efounder.JEnterprise.config.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 *   
 * 类名称：DruidAutoConfiguration
 * 类描述：Druid单数据源配置
 * 创建人：wujf
 * 修改人：wujf
 * 修改时间：2018年1月3日 下午6:46:50
 * 修改备注：
 * @version 1.0.0 
 *
 */
@Configuration
//@EnableTransactionManagement
public class DruidAutoConfiguration implements EnvironmentAware {

    private RelaxedPropertyResolver propertyResolver;

    @Override
    public void setEnvironment(Environment env) {
        this.propertyResolver = new RelaxedPropertyResolver(env, "druid.datasource.");
    }
    
    @Bean
	public ServletRegistrationBean druidServlet() { // 主要实现WEB监控的配置处理
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(
				new DruidStatViewServlet(), "/druid/*"); // 现在要进行druid监控的配置处理操作
		servletRegistrationBean.addInitParameter("allow",""); // 白名单
		servletRegistrationBean.addInitParameter("deny", ""); // 黑名单
//		servletRegistrationBean.addInitParameter("loginUsername", "admin"); // 用户名
//		servletRegistrationBean.addInitParameter("loginPassword", "123456"); // 密码
		servletRegistrationBean.addInitParameter("resetEnable", "false"); // 是否可以重置数据源
		return servletRegistrationBean ;
	}
//	@Bean
//	public FilterRegistrationBean filterRegistrationBean() {
//		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean() ;
//		filterRegistrationBean.setFilter(new WebStatFilter());
//		filterRegistrationBean.addUrlPatterns("/*"); // 所有请求进行监控处理
//		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.css,/druid/*");
//		return filterRegistrationBean ;
//	}
//	@Bean
//	@ConfigurationProperties(prefix = "spring.datasource")
//	public DataSource druidDataSource() {
//		return new DruidDataSource();
//	}
	
    @Bean(destroyMethod = "close", initMethod = "init")
    @Primary
    public DataSource writeDataSource() {

        // url;// 数据库URL
        // username;// 数据库账号
        // password;// 数据库密码
        // driverClassName;// 数据库驱动类
        // initialSize;// 初始化时建立物理连接的个数
        // maxActive;// 最大连接数
        // minIdle;// 最小连接数
        // maxWait;// 获取连接时最大等待时间，单位毫秒
        // useUnfairLock;// 配置了maxWait之后，缺省启用公平锁，并发效率会有所下降，所以使用非公平锁
        // validationQuery;// 检测连接是否有效SQL
        // testOnBorrow;// 申请连接时执行validationQuery检测连接是否有效，设置为true会影响性能
        // testOnReturn;// 退还连接时执行validationQuery检测连接是否有效，设置为true会影响性能
        // testWhileIdle;// 申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效
        // timeBetweenEvictionRunsMillis;// 两个含义：1、Destroy线程会检测连接的间隔时间 2、testWhileIdle的判断依据
        // minEvictableIdleTimeMillis;// 一个连接在池中最小生存的时间，单位是毫秒
        // filters;// 属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：监控统计用的filter:stat 日志用的filter:log4j 防御sql注入的filter:wall
    	
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(propertyResolver.getProperty("url"));
        dataSource.setUsername(propertyResolver.getProperty("username"));
        dataSource.setPassword(propertyResolver.getProperty("password"));
        dataSource.setDriverClassName(propertyResolver.getProperty("driverClassName"));
        dataSource.setInitialSize(Integer.parseInt(propertyResolver.getProperty("initialSize")));
        dataSource.setMaxActive(Integer.parseInt(propertyResolver.getProperty("maxActive")));
        dataSource.setMinIdle(Integer.parseInt(propertyResolver.getProperty("minIdle")));
        dataSource.setMaxWait(Integer.parseInt(propertyResolver.getProperty("maxWait")));
        dataSource.setUseUnfairLock(Boolean.valueOf(propertyResolver.getProperty("useUnfairLock")));
        dataSource.setValidationQuery(propertyResolver.getProperty("validationQuery"));
        dataSource.setTestOnBorrow(Boolean.valueOf(propertyResolver.getProperty("testOnBorrow")));
        dataSource.setTestOnReturn(Boolean.valueOf(propertyResolver.getProperty("testOnReturn")));
        dataSource.setTestWhileIdle(Boolean.valueOf(propertyResolver.getProperty("testWhileIdle")));
        dataSource.setTimeBetweenEvictionRunsMillis(Integer.parseInt(propertyResolver.getProperty("timeBetweenEvictionRunsMillis")));
        dataSource.setMinEvictableIdleTimeMillis(Integer.parseInt(propertyResolver.getProperty("minEvictableIdleTimeMillis")));
        try {
            dataSource.setFilters(propertyResolver.getProperty("Filters"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dataSource;
    }
}
