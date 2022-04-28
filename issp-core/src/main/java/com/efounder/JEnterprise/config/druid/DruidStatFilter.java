package com.efounder.JEnterprise.config.druid;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 *   
 * 类名称：DruidStatFilter
 * 类描述：druid数据源状态监控的过滤器
 * 创建人：wujf
 * 修改人：wujf
 * 修改时间：2018年1月3日 下午6:48:13
 * 修改备注：
 * @version 1.0.0 
 *
 */
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*", initParams = { @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")// 忽略资源
})
public class DruidStatFilter extends WebStatFilter {

}
