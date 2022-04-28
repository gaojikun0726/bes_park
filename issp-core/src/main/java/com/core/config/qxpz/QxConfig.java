package com.core.config.qxpz;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 权限配置
 * 
 * @author gongfanfei
 * @datetime 2018年8月29日
 *
 */
@Component
public class QxConfig {

	@Value("${system.parameter.qxsh}")
	private String qxsh;
	@Value("${system.parameter.gnqx.useable}")
	private String gnqxUseable;

	@Value("${system.parameter.gnqxRlgl}")
	private String gnqxRlgl;// 用户功能权限表

	@Value("${system.parameter.userGnqx}")
	private String userGnqx;// 用户功能权限表

	@Value("${system.parameter.userSjqxb}")
	private String userSjqxb;// 用户数据权限表

	@Value("${system.parameter.groupGnqxb}")
	private String groupGnqxb;// 用户组功能权限表
	
	@Value("${system.parameter.postGnqxb}")
	private String postGnqxb;// 岗位功能权限表
	
	@Value("${system.parameter.classifyGnqxb}")
	private String classifyGnqxb;// 用户角色/用户组/岗位功能权限表

	@Value("${system.parameter.sjqxsh}")
	private String sjqxsh;//数据权限审核
	@Value("${system.parameter.sjqx.useable}")
	private String sjqxUseable;//数据权限启用
	
	@Value("${system.parameter.classifySjqxb}")
	private String classifySjqxb;// 用户角色/用户组/岗位数据权限表
	

	public String getPostGnqxb() {
		return postGnqxb;
	}

	public void setPostGnqxb(String postGnqxb) {
		this.postGnqxb = postGnqxb;
	}

	public String getGroupGnqxb() {
		return groupGnqxb;
	}

	public void setGroupGnqxb(String groupGnqxb) {
		this.groupGnqxb = groupGnqxb;
	}

	public String getQxsh() {
		return qxsh;
	}

	public void setQxsh(String qxsh) {
		this.qxsh = qxsh;
	}

	public String getGnqxRlgl() {
		return gnqxRlgl;
	}

	public void setGnqxRlgl(String gnqxRlgl) {
		this.gnqxRlgl = gnqxRlgl;
	}

	public String getUserGnqx() {
		return userGnqx;
	}

	public void setUserGnqx(String userGnqx) {
		this.userGnqx = userGnqx;
	}

	public String getUserSjqxb() {
		return userSjqxb;
	}

	public void setUserSjqxb(String userSjqxb) {
		this.userSjqxb = userSjqxb;
	}

	public String getClassifyGnqxb() {
		return classifyGnqxb;
	}

	public void setClassifyGnqxb(String classifyGnqxb) {
		this.classifyGnqxb = classifyGnqxb;
	}

	public String getClassifySjqxb() {
		return classifySjqxb;
	}

	public void setClassifySjqxb(String classifySjqxb) {
		this.classifySjqxb = classifySjqxb;
	}

	public String getGnqxUseable() {
		return gnqxUseable;
	}

	public void setGnqxUseable(String gnqxUseable) {
		this.gnqxUseable = gnqxUseable;
	}

	public String getSjqxsh() {
		return sjqxsh;
	}

	public void setSjqxsh(String sjqxsh) {
		this.sjqxsh = sjqxsh;
	}

	public String getSjqxUseable() {
		return sjqxUseable;
	}

	public void setSjqxUseable(String sjqxUseable) {
		this.sjqxUseable = sjqxUseable;
	}
	
}