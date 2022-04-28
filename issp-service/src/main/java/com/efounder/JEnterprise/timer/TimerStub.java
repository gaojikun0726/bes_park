package com.efounder.JEnterprise.timer;

import com.efounder.JEnterprise.timer.state.TimerStubState;
import com.efounder.JEnterprise.timer.state.TimerStubStateCANCELLED;
import com.efounder.JEnterprise.timer.state.TimerStubStateSTOPPED;

import java.util.Date;
import java.util.Map;

public class TimerStub {
	
	public TimerStub() {
	}

	// 循环执行,单位:分钟
	public static final String _TIMER_TYPE_LOOP_ = "LOOP";
	// 固定时间,格式:fixedTime="yyyy-MM-dd HH:mm:ss"
	public static final String _TIMER_TYPE_DATE_ = "DATE";
	// 每天指定时间执行,格式:fixedTime="HH:mm:ss"
	public static final String _TIMER_TYPE_EDAY_ = "DAY";
	// 每周指定时间执行,格式:fixedTime="HH:mm:ss" property="week=6,7"
	public static final String _TIMER_TYPE_WEEK_ = "WEEK";
	// 每月指定时间执行,格式:fixedTime="HH:mm:ss" property="month=1"
	public static final String _TIMER_TYPE_MONTH_ = "MONTH";
	// 每月指定时间执行,格式:fixedTime="HH:mm:ss" property="month=1"
	public static final String _TIMER_TYPE_YEAR_ = "YEAR";
	// 只在前台点击“立即执行”时执行一次
	public static final String _TIMER_TYPE_ONCE = "ONCE";
	
	private String timerType;
	/**
	 *
	 */
	private String timerCaption;
	/**
	 * 以分钟为单位的时间，默认为10分钟
	 */
	private int loopTimer = 10;
	/**
	 * 计数器，计数器，当 loopTimer == loopCount时，会调用服务
	 */
	private int loopCount = 0;

	private String eaiServer;
	private String serviceKey;
	private String userName;
	private String userPass;
	private String userCaption;
	private Map<String, String> timerProperty;
	
	private String F_CLASS_PATH;//类路径
	private String F_METHOD;//方法名称
	private String F_SERVICE_URL;//服务接口URL地址
	private String F_SERVICE_MC;//服务接口名称
	

	// 已经执行过的次数
	private int executeCount;
	// 固定时间，格式：HH:mm/HH:mm:SS，当timerType="date"时和varTime共同构成fixedTimer
	private String fixedTime;
	// 变量时间，格式：不同的时间类型不一样，如timerType="week"时，1,2即每周一和二
	// timerType="date"时，可能为YYYY-mm-DD
	private String varTime;

	private Date fixedTimer;
	private String timerID;
	
	protected boolean running = false;
	
	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean v) {
		running = v;
	}
	  
	public void setLoopTimer(int loopTimer) {
		this.loopTimer = loopTimer;
	}
	/**
	 * 定时器任务的状态
	 */
	protected TimerStubState state;

	public TimerStubState getState() {
		return this.state;
	}

	/**
	 *
	 * @param state
	 * TimerStubState
	 */
	public void setState(TimerStubState state) {
		this.state = state;
		processLoopCount();
	}

	/**
	 * 如果状态是停止或者取消，则count清零
	 */
	protected void processLoopCount() {
		if (getState() instanceof TimerStubStateSTOPPED || getState() instanceof TimerStubStateCANCELLED) {
			setLoopCount(0);
		}
	}
	
	
	
	
	public void setTimerType(String timerType) {
		this.timerType = timerType;
	}

	public void setTimerCaption(String timerCaption) {
		this.timerCaption = timerCaption;
	}

	public void setLoopCount(int loopCount) {
		this.loopCount = loopCount;
	}

	public void setEaiServer(String eaiServer) {
		this.eaiServer = eaiServer;
	}

	public void setServiceKey(String serviceKey) {
		this.serviceKey = serviceKey;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public void setUserCaption(String userCaption) {
		this.userCaption = userCaption;
	}

	public void setTimerProperty(Map<String, String> timerProperty) {
		this.timerProperty = timerProperty;
	}
	
	public String getTimerType() {
		return timerType;
	}

	public String getTimerCaption() {
		return timerCaption;
	}

	public int getLoopTimer() {
		return loopTimer;
	}

	public int getLoopCount() {
		return loopCount;
	}

	public String getEaiServer() {
		return eaiServer;
	}

	public String getServiceKey() {
		return serviceKey;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public String getUserCaption() {
		return userCaption;
	}

	public Map<String, String> getTimerProperty() {
		return timerProperty;
	}
	public int getExecuteCount() {
		return executeCount;
	}

	public void setExecuteCount(int executeCount) {
		this.executeCount = executeCount;
	}

	public String getFixedTime() {
		return fixedTime;
	}

	public void setFixedTime(String fixedTime) {
		this.fixedTime = fixedTime;
	}

	public String getVarTime() {
		return varTime;
	}

	public void setVarTime(String varTime) {
		this.varTime = varTime;
	}
	public Date getFixedTimer() {
		return fixedTimer;
	}

	public void setFixedTimer(Date fixedTimer) {
		this.fixedTimer = fixedTimer;
	}

	public String getTimerID() {
		return timerID;
	}

	public void setTimerID(String timerID) {
		this.timerID = timerID;
	}

	public String getF_CLASS_PATH() {
		return F_CLASS_PATH;
	}

	public void setF_CLASS_PATH(String f_CLASS_PATH) {
		F_CLASS_PATH = f_CLASS_PATH;
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
