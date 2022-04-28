package com.efounder.JEnterprise.config.db.database;

import java.lang.annotation.*;

/**
 *   
 * 类名称：TargetDataSource
 * 类描述：在方法上使用，用于指定使用哪个数据源
 * 创建人：wujf
 * 修改人：wujf
 * 修改时间：2018年1月3日 下午4:35:35
 * 修改备注：
 * @version 1.0.0 
 *
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    
    /** 数据源名称 */
    DataSourceEnum value();
}
