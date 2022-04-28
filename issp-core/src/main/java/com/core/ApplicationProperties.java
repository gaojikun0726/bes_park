package com.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**  
 *   
 * 类名称：ApplicationProperties
 * 类描述：
 * 创建人：wujf
 * 修改人：wujf
 * 修改时间：2017年12月26日 上午9:18:06
 * 修改备注：
 * @version 1.0.0 
 *   
 */
@Component
public class ApplicationProperties {
	
	/**  
	 * message:hello word
	 *  
	 * @since 1.0.0
	 */  
	@Value("${welcome.message}")
    private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	/**  
	 * ApplicationName:应用名称  
	 *  
	 * @since 1.0.0
	 */  
	@Value("${JEnterprise.ApplicationName}")
	private String ApplicationName;

	public String getApplicationName() {
		return ApplicationName;
	}
	
	
	public void setApplicationName(String applicationName) {
		ApplicationName = applicationName;
	}
   


}
