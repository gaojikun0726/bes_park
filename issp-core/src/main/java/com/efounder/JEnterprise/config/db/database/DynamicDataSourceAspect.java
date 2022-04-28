package com.efounder.JEnterprise.config.db.database;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 
 * 类名称：DynamicDataSourceAspect
 * 类描述：切换数据源Advice
 * 创建人：wujf
 * 修改人：wujf
 * 修改时间：2018年1月3日 下午6:58:13
 * 修改备注：
 * @version 1.0.0 
 *
 */
@Aspect
@Order(-1) // 保证该AOP在@Transactional之前执行
@Component
public class DynamicDataSourceAspect {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

   
    /*
     * @Before("@annotation(ds)")
     * 的意思是：
     *
     * @Before：在方法执行之前进行执行：
     * @annotation(targetDataSource)：
     * 会拦截注解targetDataSource的方法，否则不拦截;
     */
    @Before("@annotation(ds)")
    public void changeDataSource(JoinPoint point, TargetDataSource ds) throws Throwable {
        String dsId = ds.value().getKey();
        if (!DynamicDataSourceContextHolder.containsDataSource(dsId)) {
            logger.error("数据源[{}]不存在，使用默认数据源 > {}", ds.value().getKey(), point.getSignature());
        } else {
            logger.debug("Use DataSource : {} > {}", ds.value().getKey(), point.getSignature());
            DynamicDataSourceContextHolder.setDataSourceType(ds.value().getKey());
        }
    }

    @After("@annotation(ds)")
    public void restoreDataSource(JoinPoint point, TargetDataSource ds) {
    	//方法执行完毕之后，销毁当前数据源信息，进行垃圾回收。
        logger.debug("Revert DataSource : {} > {}", ds.value().getKey(), point.getSignature());
        DynamicDataSourceContextHolder.clearDataSourceType();
    }

}
