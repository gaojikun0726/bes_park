package com.efounder.JEnterprise.model.thetimer;

import com.core.common.BaseEntity;

import java.io.Serializable;
/**
 * 定时器管理实体类
 * @author LvSihan
 *
 */
public class ISSPTimerManage implements BaseEntity<Serializable>{

	private static final long serialVersionUID = -2496376297473532799L;
	
	private String fTimerBh;//定时器编号FK
	
	private String fTimerName;//定时器名称
	
	private String fStartTime;//启动时间
	
	private String fStopTime;//停止时间
	
	private String fTimerState;//运行状态 0：运行 1：停止
	
	private String fTimerParams;//操作参数 0：启动 1：停止2：立即启动
	
	private String fCrdate;//创建日期
	
	private String fChdate;//修改日期
	
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
	public String getfStartTime() {
		return fStartTime;
	}
	public void setfStartTime(String fStartTime) {
		this.fStartTime = fStartTime;
	}
	public String getfStopTime() {
		return fStopTime;
	}
	public void setfStopTime(String fStopTime) {
		this.fStopTime = fStopTime;
	}
	public String getfTimerState() {
		return fTimerState;
	}
	public void setfTimerState(String fTimerState) {
		this.fTimerState = fTimerState;
	}
	public String getfTimerParams() {
		return fTimerParams;
	}
	public void setfTimerParams(String fTimerParams) {
		this.fTimerParams = fTimerParams;
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

}
