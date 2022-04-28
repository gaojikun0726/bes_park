package org.ace.business.constant;

/**
 * 下位机通信指令，照明部分
 * @author xiepufeng
 * @date 2020/4/14 9:39
 */
public class LDCCmd
{

    // 新增一个IP路由器
    public final static int CONTROLLER_ADD = 110;

    // 设置IP路由器的所有参数
    public final static int CONTROLLER_PARAM_SET = 111;

    // 删除一个IP路由器，并删除和它相关的模块和点
    public final static int CONTROLLER_DELETE = 112;

    // 获取IP路由器的所有配置参数
    public final static int CONTROLLER_PARAM_GET = 113;

    // 远程升级
    public final static int REMOTE_UPGRADE = 114;

    // 设置IP路由器的时间
    public final static int CONTROLLER_TIME_SET = 115;

    // 获取IP路由器的时间
    public final static int CONTROLLER_TIME_GET = 116;

    // 重启IP路由器，相当于复位
    public final static int CONTROLLER_RESTART = 117;

    // 重置IP路由器，恢复出厂设置，并重启
    public final static int CONTROLLER_RESET = 118;

    // 新增加一个模块
    public final static int MODULE_ADD = 120;

    // 设置一个模块的所有参数
    public final static int MODULE_PARAM_SET = 121;

    // 删除一个模块，并删除和模块相关的点
    public final static int MODULE_DELETE = 122;

    // 获取一个模块的所有配置信息
    public final static int MODULE_PARAM_GET = 123;

    // 接收实点信息
    public final static int REAL_POINT_DATA_RECEIVE = 124;

    // 接收一个模块的错误代码
    public final static int MODULE_ERROR_CODE_GET  = 125;

    // 批量接收一个模块的错误代码
    public final static int MODULE_ERROR_CODE_GET_ALL  = 126;

    // 新增加一个逻辑点
    public final static int POINT_ADD  = 130;

    // 设置一个逻辑点的所有参数
    public final static int POINT_PARAM_SET  = 131;

    // 设置一个逻辑点的值
    public final static int POINT_VALUE_SET  = 132;

    // 设置逻辑点的值，根据点的名字
    public final static int POINT_VALUE_BY_NAME_SET  = 133;

    // 删除一个逻辑点
    public final static int POINT_DELETE = 134;

    // 获取一个逻辑点的所有配置参数
    public final static int POINT_PARAM_GET = 135;

    // 获取一个逻辑点的报警信息
    public final static int POINT_ALARM_DATA_GET  = 136;

    // IP路由器的全部点信息
    public final static int POINT_DATA_ALL_RECEIVE = 137;

    // 接收虚点信息
    public final static int VIRTUAL_POINT_DATA_RECEIVE = 138;

    // 增加一个场景
    public final static int SCENE_ADD  = 140;

    // 设置一个场景的所有参数
    public final static int SCENE_PARAM_SET = 141;

    // 删除一个场景
    public final static int SCENE_DELETE = 142;

    // 获取一个场景的配置信息
    public final static int SCENE_PARAM_GET = 143;

    // 增加一条计划
    public final static int PLAN_ADD = 150;

    // 修改一条计划的所有参数
    public final static int PLAN_PARAM_SET = 151;

    // 删除一条计划
    public final static int PLAN_DELETE = 152;

    // 获取一条计划的所有参数
    public final static int PLAN_PARAM_GET = 153;

    //获取场景下的单个模式信息
    public final static int SCENE_MODE_PARAM_GET = 144;

    //删除场景下的单个模式信息
    public final static int SCENE_MODE_PARAM_DELETE = 145;
}
