package com.efounder.JEnterprise.common.authority.service.xss;

/**
 *   
 * 类名称：XSSSecurityConfig
 * 类描述：安全过滤配置信息类
 * 创建人：wujf
 * 修改人：wujf
 * 修改时间：2018年1月3日 下午4:53:25
 * 修改备注：
 * @version 1.0.0 
 *
 */
public class XSSSecurityConfig {
	  
	/**
	 * CHECK_HEADER：是否开启header校验
	 */
	public static boolean IS_CHECK_HEADER; 
	
	/**
	 * CHECK_PARAMETER：是否开启parameter校验
	 */
	public static boolean IS_CHECK_PARAMETER;

    /**
     * CHECK_URL,是否开启检查特殊url
     */
    public static boolean IS_CHECK_URL;

	/**
	 * IS_LOG：是否记录日志
	 */
	public static boolean IS_LOG;
	
	/**
	 * IS_LOG：是否中断操作
	 */
	public static boolean IS_CHAIN;
	
	/**
	 * REPLACE：是否开启替换
	 */
	public static boolean REPLACE;
	

}
