package com.efounder.JEnterprise.config.swagger;

import com.google.common.base.Predicate;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * 这个配置不再使用，对应的service或者webapp中自己增加
 *
 */
//@Configuration
//@EnableSwagger2 // 启用 Swagger
public class SwaggerConfig {
	
	/**
	 * SpringBoot默认已经将classpath:/META-INF/resources/和classpath:/META-INF/resources/webjars/映射
	 * 所以该方法不需要重写，如果在SpringMVC中，可能需要重写定义（我没有尝试）
	 * 重写该方法需要 extends WebMvcConfigurerAdapter
	 * 
	 */
	//    @Override
	//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	//        registry.addResourceHandler("swagger-ui.html")
	//                .addResourceLocations("classpath:/META-INF/resources/");
	//
	//        registry.addResourceHandler("/webjars/**")
	//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
	//    }

	/**
	 * 可以定义多个组，比如本类中定义把test和demo区分开了
	 * （访问页面就可以看到效果了） 
	 *
	 */
	@Bean
	public Docket testApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("开发用户API文档")
				.genericModelSubstitutes(DeferredResult.class)
				//                .genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(false)
				.forCodeGeneration(true)
				.pathMapping("/")// base，最终调用接口后会和paths拼接在一起
				.select()
				.paths(or(regex("/api/.*")))//过滤的接口 .paths(PathSelectors.any())
				.build()
				.apiInfo(testApiInfo());
		
		
        
	}

	@Bean
	public Docket demoApi() {
		
		Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
            @Override
            public boolean apply(RequestHandler input) {
                Class<?> declaringClass = input.getClass();//.declaringClass();
                if (declaringClass == BasicErrorController.class)// 排除
                    return false;
                if(declaringClass.isAnnotationPresent(RestController.class)) // 被注解的类
                    return true;
                if(input.isAnnotatedWith(ResponseBody.class)) // 被注解的方法
                    return true;
                return false;
            }
        };
        
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("企业用户API文档")
				.genericModelSubstitutes(DeferredResult.class)
				//              .genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(false)
				.forCodeGeneration(false)
				.pathMapping("/")
				.select()
				.paths(or(regex("/demo/.*")))//过滤的接口 
				.apis(predicate) //.apis(RequestHandlerSelectors.basePackage("com.didispace.web"))
				.build()
				.apiInfo(demoApiInfo());
	}

	private ApiInfo testApiInfo() {
		return new ApiInfoBuilder()
				.title("齐鲁交通物联网（GHEE）平台微服务接口文档")//大标题
				.description("BES's REST API, all the applications could access the Object model data via JSON.")//详细描述
				.version("1.0")//版本
				.termsOfServiceUrl("http://www.sdzckj.com/")
				.contact(new Contact("山东正晨科技有限公司", "http://www.sdzckj.com/", "344899305@qq.com"))//作者
				.license("The Apache License, Version 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
				.build();
	}

	private ApiInfo demoApiInfo() {
		return new ApiInfoBuilder()
				.title("齐鲁交通物联网（GHEE）平台微服务接口文档")//大标题
				.description("BES's REST API, all the applications could access the Object model data via JSON.")//详细描述
				.version("1.0")//版本
				.termsOfServiceUrl("http://www.sdzckj.com/")
				
				.contact(new Contact("山东正晨科技有限公司", "http://www.sdzckj.com/", "344899305@qq.com"))//作者
				.license("The Apache License, Version 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
				.build();
	}
}
