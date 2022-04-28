package com.efounder.JEnterprise.timer.state;

import com.efounder.JEnterprise.timer.TimerStub;

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
public abstract class TimerStubState {

  /**
   *
   */
  public static final String _STATE_SUSPENDED_ = "SUSPENDED";
  /**
   *参数"1",启动
   */
  public static final String _STATE_RUNNING_ = "1";
  /**
   *参数"0",停止
   */
  public static final String _STATE_STOPPED_ = "0";
  /**
   * 参数"2",立即启动
   */
  public static final String _STATE_STARTNOW_ = "2";
  /**
   *
   */
  public static final String _STATE_CANCELLED_ = "CANCELLED";

  /**
   * 任务状态
   * ----挂起:SUSPENDED
   * ----运行:RUNNING
   * ----停止:STOPPED
   * ----立即启动:STARTNOW
   * ----取消:CANCELLED
   */
  public TimerStubState() {
  }

  /**
   *
   * @param timerStub TimerStub
   * @return Object
   */
  public abstract Object suspendTimerStub(TimerStub timerStub);

  /**
   *
   * @param timerStub TimerStub
   * @return Object
   */
  public abstract Object runTimerStub(TimerStub timerStub);

  /**
   *
   * @param timerStub TimerStub
   * @return Object
   */
  public abstract Object stopTimerStub(TimerStub timerStub);

  /**
   *
   * @param timerStub TimerStub
   * @return Object
   */
  public abstract Object cancelTimerStub(TimerStub timerStub);

  /**
   *
   * @return Object
   */
  public abstract Object getTimerStubMessage();

}
