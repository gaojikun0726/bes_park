package com.efounder.JEnterprise.timer;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class TimerThread implements Runnable {
  /**
   *
   * @param timerStub TimerStub
   * @return TimerThread
   */
  public static TimerThread getInstance(TimerManager timerManager,TimerStub timerStub) {
	    TimerThread tt = new TimerThread();
	    tt.timerStub = timerStub;
	    tt.timerManager = timerManager;
	    return tt;
  }
  /**
   *
   */
  protected TimerThread() {
  }
  /**
   *
   */
  private TimerManager timerManager = null;
  /**
   *
   */
  private TimerStub timerStub;
  /**
   *
   */
  public void run() {
	    try {
	      timerStub.setRunning(true);
	      // 执行服务
	      TimerManager.callTaskProcess(timerStub);
	    } catch ( Exception ex ) {
	      ex.printStackTrace();
	    } finally {
	      // 执行完后，清除循环计数器
	      timerStub.setLoopCount(0);
	      timerStub.setExecuteCount(0);
	      // 设置非运行标志
	      timerStub.setRunning(false);
	    }
	  }
  
public TimerStub getTimerStub() {
	return timerStub;
}
public void setTimerStub(TimerStub timerStub) {
	this.timerStub = timerStub;
}
}
