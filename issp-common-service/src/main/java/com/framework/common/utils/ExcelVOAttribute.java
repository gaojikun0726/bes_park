package com.framework.common.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 杨超--YangChao
 * @version 创建时间：2018年12月10日 下午5:50:04
 * @parameter 基于AOP自定义切点
 * @version 1.0
 */

@Retention(RetentionPolicy.RUNTIME) // 注解 解释--定义生命周期--注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在；
@Target({ java.lang.annotation.ElementType.FIELD }) // --该注解用于取值--FIELD可用于域上
public @interface ExcelVOAttribute {

	/**
     * 导出到Excel中的名字.
     */
	public abstract String name();

	/**
     * 配置列的名称,对应A,B,C,D....
     */
	public abstract String column();

	/**
     * 提示信息
     */
	public abstract String prompt() default "";

	/**
     * 设置只能选择不能输入的列内容.
     */
	public abstract String[] combo() default {};

	/**
     * 是否导出数据,应对需求:有时我们需要导出一份模板,这是标题需要但内容需要用户手工填写.
     */
	public abstract boolean isExport() default true;

}
