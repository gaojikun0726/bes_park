package org.ace.business.constant;

/**
 * 点报警状态定义
 * @author xiepufeng
 * @date 2020/6/28 14:29
 */
public class AlarmState
{
    public final static int ALARM_STATE_OK = 0; //没有报警

    public final static int ALARM_STATE_HIGH_LIMIT = 1; // 高限报警

    public final static int ALARM_STATE_LOW_LIMIT = 2; // 低限报警

    public final static int ALARM_STATE_ON = 3; // 闭合报警

    public final static int ALARM_STATE_OFF = 4; // 断开报警
}
