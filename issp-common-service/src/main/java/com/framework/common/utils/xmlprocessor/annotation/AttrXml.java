package com.framework.common.utils.xmlprocessor.annotation;

import java.lang.annotation.*;

/**
 * @author xiepufeng
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AttrXml
{
    /**
     * dom 节点名字
     */
    String name() default "";

    /**
     * 是否忽略此属性
     */
    boolean ignore() default false;

    /**
     * 是否为标签参数
     */
    boolean isProperty() default false;

    /**
     * 是否为内部属性
     */
    boolean isInnerAttr() default false;
}
