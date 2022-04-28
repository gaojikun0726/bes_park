package com.core.config.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 操作日志配置
 * 
 * @author gongfanfei
 * @datetime 2018年11月10日
 *
 */
@Component
public class OperationConfig {
	

	//是否启用操作日志
	@Value("${system.dataBase.useable}")
	private String sysDataBaseUseable;

	public String getSysDataBaseUseable() {
		return sysDataBaseUseable;
	}

	public void setSysDataBaseUseable(String sysDataBaseUseable) {
		this.sysDataBaseUseable = sysDataBaseUseable;
	}
	
	
}