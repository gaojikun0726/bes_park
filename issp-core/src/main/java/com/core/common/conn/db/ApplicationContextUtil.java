package com.core.common.conn.db;

/**
 * @CkassName: ApplicationContextUtil
 * @Author: YangChao
 * @Date: 2019/6/4 15:46
 * @Descruotuib:配置类，解决定时任务无法注入的问题
 * @Version: 1.0
 **/
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Auther: YangTao
 * @Date: 2019/2/21 0021
 * 配置类，解决定时任务无法注入的问题
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext appCtx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appCtx = applicationContext;
    }
    /**
     * 获取applicationcontext对象
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return appCtx;
    }
    /**
     * 返回相应的spring管理的bean对象
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        return appCtx.getBean(beanName);
    }

}