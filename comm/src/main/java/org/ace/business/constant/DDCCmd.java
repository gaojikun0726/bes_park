package org.ace.business.constant;

/**
 * 下位机通信指令，DDC部分
 * @author xiepufeng
 * @date 2020/4/14 9:39
 */
public class DDCCmd
{

    // 新增一个控制器
    public final static int CONTROLLER_ADD = 10;

    // 设置控制器的所有参数
    public final static int CONTROLLER_PARAM_SET = 11;

    // 删除一个控制器，并删除和它相关的模块和点
    public final static int CONTROLLER_DELETE = 12;

    // 获取控制器的所有配置参数
    public final static int CONTROLLER_PARAM_GET = 13;

    // 远程升级
    public final static int REMOTE_UPGRADE = 14;

    // 设置控制器的时间
    public final static int CONTROLLER_TIME_SET = 15;

    // 获取控制器的时间
    public final static int CONTROLLER_TIME_GET = 16;

    // 重启控制器，相当于重启复位
    public final static int CONTROLLER_RESTART = 17;

    // 重置控制器，恢复出厂设置，并重启
    public final static int CONTROLLER_RESET = 18;

    // 新增加一个模块
    public final static int MODULE_ADD = 20;

    // 设置一个模块的所有参数
    public final static int MODULE_PARAM_SET = 21;

    // 删除一个模块，并删除和模块相关的点
    public final static int MODULE_DELETE = 22;

    // 获取一个模块的所有配置信息
    public final static int MODULE_PARAM_GET = 23;

    // 接收实点信息
    public final static int REAL_POINT_DATA_RECEIVE = 24;

    // 获取一个模块的错误代码
    public final static int MODULE_ERROR_CODE_GET  = 25;

    // 批量获取模块的错误代码
    public final static int MODULE_ERROR_CODE_GET_ALL  = 26;

    // 新增加一个逻辑点
    public final static int POINT_ADD  = 30;

    // 设置一个逻辑点的所有参数
    public final static int POINT_PARAM_SET  = 31;

    // 设置一个逻辑点的值
    public final static int POINT_VALUE_SET  = 32;

    // 设置逻辑点的值，根据点的名字
    public final static int POINT_VALUE_BY_NAME_SET  = 33;

    // 删除一个逻辑点
    public final static int POINT_DELETE = 34;

    // 获取一个逻辑点的所有配置参数
    public final static int POINT_PARAM_GET = 35;

    // 获取一个逻辑点的报警信息
    public final static int POINT_ALARM_DATA_GET  = 36;

    // 接收DDC的全部点信息
    public final static int POINT_DATA_ALL_RECEIVE = 37;

    // 接收虚点信息
    public final static int VIRTUAL_POINT_DATA_RECEIVE = 38;

    // 增加一个场景
    public final static int SCENE_ADD  = 40;

    // 设置一个场景的所有参数
    public final static int SCENE_PARAM_SET = 41;

    // 删除一个场景
    public final static int SCENE_DELETE = 42;

    // 获取一个场景的配置信息
    public final static int SCENE_PARAM_GET = 43;

    //获取场景下的单个模式信息
    public final static int SCENE_MODE_PARAM_GET = 44;

    //删除场景下的单个模式信息
    public final static int SCENE_MODE_PARAM_DELETE = 45;

    // 增加一条计划
    public final static int PLAN_ADD = 50;

    // 修改一条计划的所有参数
    public final static int PLAN_PARAM_SET = 51;

    // 删除一条计划
    public final static int PLAN_DELETE = 52;

    // 获取一条计划的所有参数
    public final static int PLAN_PARAM_GET = 53;



}
