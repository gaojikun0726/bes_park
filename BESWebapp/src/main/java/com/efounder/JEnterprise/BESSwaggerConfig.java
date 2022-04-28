package com.efounder.JEnterprise;

import com.framework.zcsoft.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2 // 启用 Swagger
@EnableSwaggerBootstrapUI
public class BESSwaggerConfig extends WebMvcConfigurerAdapter {

	/**
	 * SpringBoot默认已经将classpath:/META-INF/resources/和classpath:/META-INF/resources/webjars/映射
	 * 所以该方法不需要重写，如果在SpringMVC中，可能需要重写定义（我没有尝试）
	 * 重写该方法需要 extends WebMvcConfigurerAdapter
	 *
	 */
	    @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("api-docs.html")
	                .addResourceLocations("classpath:/META-INF/resources/");

	        registry.addResourceHandler("/webjars/**")
	                .addResourceLocations("classpath:/META-INF/resources/webjars/");
	    }

	@SuppressWarnings("unchecked")
	@Bean
	public Docket isspApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("开发用户API文档")
				.genericModelSubstitutes(DeferredResult.class)
				//                .genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(false)
				.forCodeGeneration(true)
				.pathMapping("/")// base，最终调用接口后会和paths拼接在一起
				.select()
				.paths(or(regex("/(issp|api)/.*")))//过滤的接口 .paths(PathSelectors.any())
				.build()
				.apiInfo(isspApiInfo());
	}


	private ApiInfo isspApiInfo() {
		return new ApiInfoBuilder()
				.title("智慧建筑云（BES）平台微服务接口文档")//大标题
				.description("BES's REST API, all the applications could access the Object model data via JSON.")//详细描述
				.version("1.0")//版本
				.termsOfServiceUrl("http://www.sdzckj.com/")
				.contact(new Contact("山东正晨科技有限公司", "http://www.sdzckj.com/", "344899305@qq.com"))//作者
				.license("The Apache License, Version 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
				.build();
	}

}
