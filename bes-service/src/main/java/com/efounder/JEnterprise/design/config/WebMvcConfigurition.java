package com.efounder.JEnterprise.design.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * describe:虚拟路径映射配置类
 *
 * @author zs
 * @date 2019/12/17
 */
@Configuration
public class WebMvcConfigurition extends WebMvcConfigurerAdapter {

    /**
     * 请求 url 中的资源映射，不推荐写死在代码中，最好提供可配置，如 /file/**
     */
    @Value("${uploadFile.resourceHandler}")
    private String resourceHandler;

    /**
     * 上传文件保存的本地目录，使用@Value获取全局配置文件中配置的属性值，如 F:/file/
     */
    @Value("${uploadFile.location}")
    private String location;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //少了最后的斜杠不生效
        registry.addResourceHandler(resourceHandler)
                .addResourceLocations("file:" + location+"/");

        //配置静态资源映射,不用写/static了
        registry.addResourceHandler("/article/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/template/**").addResourceLocations("classpath:/static/");
    }

    /**
     * 默认路径访问页面，比如 192.168.1.106:8080
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/index");
    }
}
