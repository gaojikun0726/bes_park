package com.framework.common.utils.xmlprocessor.annotation;

import java.lang.annotation.*;

/**
 * @author xiepufeng
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RootXml
{

    /**
     * dom 节点名字
     */
    String value() default "";

}
