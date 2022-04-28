package com.framework.zcsoft.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
        basePackages = {"com.framework.zcsoft.web"}
)
public class SwaggerBootstrapUIConfiguration {



}
