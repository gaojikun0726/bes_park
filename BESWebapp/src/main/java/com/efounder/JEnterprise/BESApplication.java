package com.efounder.JEnterprise;

import com.core.ApplicationContextProvider;
import com.efounder.JEnterprise.config.db.database.DynamicDataSourceRegister;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;

@EnableCaching
@Import({ DynamicDataSourceRegister.class })
@EnableTransactionManagement
@SpringBootApplication
@EnableZuulProxy
@ServletComponentScan
@EnableScheduling//Add by yangjx at 20200416 for 应用SpringBoot的@Scheduled定时器任务
@ComponentScan(basePackages={
        "com.superdog","com.efounder",
        "com.core.common.util",
        "com.core.config.qxpz",
        "com.core.config.db",
        "com.core.config.sso",
        "com.core.common.conn.db",
        "com.core.common.config"})
public class BESApplication extends SpringBootServletInitializer {
	
	@Bean
    public ApplicationContextProvider getApplicationContextProvider() {
        return new ApplicationContextProvider();
    }
    public static void main(String[] args) {
        SpringApplication.run(BESApplication.class, args);
    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {

        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                
                // ERROR PAGE
                ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/401.html");
                ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, "/error/403.html");
                ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404.html");
                ErrorPage error405Page = new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/error/405.html");
                ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500.html");
                container.addErrorPages(error401Page, error403Page, error404Page,error405Page, error500Page);
                
//                MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
//                mappings.add("woff","application/x-font-woff");
//                mappings.add("eot","application/vnd.ms-fontobject");
//                mappings.add("ttf","application/x-font-ttf");
//                container.setMimeMappings(mappings);
                
            }
        };
    }
    
    
    @Bean
    public EmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory() throws IOException {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        tomcat.addAdditionalTomcatConnectors(httpConnector());
        return tomcat;
    }
    
    public Connector httpConnector() throws IOException {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        Http11NioProtocol http11NioProtocol = (Http11NioProtocol) connector.getProtocolHandler();
        connector.setPort(8082);
        //设置最大线程数
        http11NioProtocol.setMaxThreads(50);
        //设置初始线程数  最小空闲线程数
        http11NioProtocol.setMinSpareThreads(20);
        //设置超时
        http11NioProtocol.setConnectionTimeout(5000);
        return connector;
    }
}
