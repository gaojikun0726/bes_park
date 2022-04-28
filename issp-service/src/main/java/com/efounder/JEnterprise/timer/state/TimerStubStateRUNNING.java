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
public class TimerStubStateRUNNING extends TimerStubState {

  /**
   *
   */
  public TimerStubStateRUNNING() {
  }

  /**
   *
   * @param timerStub TimerStub
   * @return Object
   */
  public Object cancelTimerStub(TimerStub timerStub) {
    timerStub.setState(new TimerStubStateCANCELLED());
    return null;
  }

  /**
   *
   * @return Object
   */
  public Object getTimerStubMessage() {
    return null;
  }

  /**
   *
   * @param timerStub TimerStub
   * @return Object
   */
  public Object runTimerStub(TimerStub timerStub) {
    return "不能运行一个处在运行状态的任务";
  }

  /**
   *
   * @param timerStub TimerStub
   * @return Object
   */
  public Object stopTimerStub(TimerStub timerStub) {
    timerStub.setState(new TimerStubStateSTOPPED());
    return null;
  }

  /**
   *
   * @param timerStub TimerStub
   * @return Object
   */
  public Object suspendTimerStub(TimerStub timerStub) {
    timerStub.setState(new TimerStubStateSUSPENDED());
    return null;
  }

  /**
   *
   * @return String
   */
  public String toString() {
    return this._STATE_RUNNING_;
  }

}
