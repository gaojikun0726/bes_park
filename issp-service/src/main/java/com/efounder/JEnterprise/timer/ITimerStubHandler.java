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
public interface ITimerStubHandler {

  public Object runTimerStub(String timerID);

  public Object runTimerStubNow(String timerID);

  public Object suspendTimerStub(String timerID);

  public Object stopTimerStub(String timerID);

  public Object cancelTimerStub(String timerID);

  public Object runTimerStub(TimerStub timerStub);

  public Object runTimerStubNow(TimerStub timerStub);

  public Object suspendTimerStub(TimerStub timerStub);

  public Object stopTimerStub(TimerStub timerStub);

  public Object cancelTimerStub(TimerStub timerStub);

  public Object getTimerStubMessage(String timerID);

  public Object getTimerStubMessage(TimerStub timerStub);

}
