package com.core.config.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 数据库连接信息
 * 
 * @author Alvin
 * @datetime 2018年5月23日
 *
 */
@Component
public class ISSPDBConfig {

	@Value("${druid.datasource.url}")
	private String url;

	@Value("${druid.datasource.driverClassName}")
	private String driverClassName;

	@Value("${druid.datasource.username}")
	private String username;

	@Value("${druid.datasource.password}")
	private String password;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
