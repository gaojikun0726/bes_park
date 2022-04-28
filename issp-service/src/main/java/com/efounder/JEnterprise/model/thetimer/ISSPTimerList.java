package com.efounder.JEnterprise.model.thetimer;

import com.core.common.BaseEntity;

import java.io.Serializable;
/**
 * 定时器列表实体类
 * @author LvSihan
 *
 */
public class ISSPTimerList implements BaseEntity<Serializable>{

	private static final long serialVersionUID = -5836318596587558347L;
	
	private String fTimerBh;//定时器编号FK
	
	private String fTimerName;//定时器名称
	
	private String fTimerClassbh;//定时器对象编号
	
	private String fTimerTypebh;//定时器类型
	
	private String fLoopTime;//循环间隔时间
	
	private String fFixedTime;//固定时间
	
	private String fVarTime;//变动时间
	
	private String fCrdate;//创建日期
	
	private String fChdate;//修改日期
	/**
	 * 服务接口URL地址
	 */
	private String F_SERVICE_URL;
	/**
	 * 服务接口名称
	 */
	private String F_SERVICE_MC;
	/**
	 * 执行方法
	 */
	private String F_METHOD;
	
	public String getfTimerBh() {
		return fTimerBh;
	}

	public void setfTimerBh(String fTimerBh) {
		this.fTimerBh = fTimerBh;
	}

	public String getfTimerName() {
		return fTimerName;
	}

	public void setfTimerName(String fTimerName) {
		this.fTimerName = fTimerName;
	}

	public String getfTimerClassbh() {
		return fTimerClassbh;
	}

	public void setfTimerClassbh(String fTimerClassbh) {
		this.fTimerClassbh = fTimerClassbh;
	}

	public String getfTimerTypebh() {
		return fTimerTypebh;
	}

	public void setfTimerTypebh(String fTimerTypebh) {
		this.fTimerTypebh = fTimerTypebh;
	}

	public String getfLoopTime() {
		return fLoopTime;
	}

	public void setfLoopTime(String fLoopTime) {
		this.fLoopTime = fLoopTime;
	}

	public String getfFixedTime() {
		return fFixedTime;
	}

	public void setfFixedTime(String fFixedTime) {
		this.fFixedTime = fFixedTime;
	}

	public String getfVarTime() {
		return fVarTime;
	}

	public void setfVarTime(String fVarTime) {
		this.fVarTime = fVarTime;
	}

	public String getfCrdate() {
		return fCrdate;
	}

	public void setfCrdate(String fCrdate) {
		this.fCrdate = fCrdate;
	}

	public String getfChdate() {
		return fChdate;
	}

	public void setfChdate(String fChdate) {
		this.fChdate = fChdate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getF_SERVICE_URL() {
		return F_SERVICE_URL;
	}

	public void setF_SERVICE_URL(String f_SERVICE_URL) {
		F_SERVICE_URL = f_SERVICE_URL;
	}

	public String getF_SERVICE_MC() {
		return F_SERVICE_MC;
	}

	public void setF_SERVICE_MC(String f_SERVICE_MC) {
		F_SERVICE_MC = f_SERVICE_MC;
	}

	public String getF_METHOD() {
		return F_METHOD;
	}

	public void setF_METHOD(String f_METHOD) {
		F_METHOD = f_METHOD;
	}


}
