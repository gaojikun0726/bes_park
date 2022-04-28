package org.ace.business.constant;

/**
 * 下位机通信指令，采集器部分
 * ECDC（Energy consumption data collector）
 * @author xiepufeng
 * @date 2020/4/14 9:50
 */
public class EDCCmd
{

    // 新增一个控制器
    public final static int CONTROLLER_ADD = 70;

    // 设置控制器的所有参数
    public final static int CONTROLLER_PARAM_SET = 71;

    // 获取控制器的所有配置参数
    public final static int CONTROLLER_PARAM_GET = 72;

    // 删除一个控制器，并删除和它相关的电表
    public final static int CONTROLLER_DELETE = 73;

    // 远程升级
    public final static int REMOTE_UPGRADE = 74;

    // 设置控制器的时间
    public final static int CONTROLLER_TIME_SET = 75;

    // 获取控制器的时间
    public final static int CONTROLLER_TIME_GET = 76;

    // 重启控制器，相当于重启复位
    public final static int CONTROLLER_RESTART = 77;

    // 重置控制器，恢复出厂设置，并重启
    public final static int CONTROLLER_RESET = 78;

    // 新增加一个电表信息
    public final static int AMMETER_ADD = 80;

    // 删除一个电表
    public final static int AMMETER_DELETE = 81;

    // 设置一个电表的所有参数
    public final static int AMMETER_SET  = 82;

    // 获取一个电表的所有配置信息
    public final static int AMMETER_GET = 83;

    // 新增一个电表的采集方案
    public final static int AMMETER_COLLECTION_SCHEME_ADD = 84;

    // 设置一个电表的采集方案
    public final static int AMMETER_COLLECTION_SCHEME_SET = 85;

    // 删除一个电表的采集方案
    public final static int AMMETER_COLLECTION_SCHEME_DELETE = 86;

    // 电表获取实时数据
    public final static int AMMETER_REALTIME_DATA_GET = 87;

    // 电表获取历史数据
    public final static int AMMETER_HISTORY_DATA_GET = 88;

    // 接收实时数据
    public final static int AMMETER_REALTIME_DATA_RECEIVE = 89;

    // 接收断点数据
    public final static int AMMETER_RESUME_DATA_RECEIVE = 90;

    // 接收报警数据
    public final static int ALARM_DATA_RECEIVE = 91;

}
