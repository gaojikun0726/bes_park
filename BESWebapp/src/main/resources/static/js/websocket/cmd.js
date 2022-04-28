/**
 * 指令
 * @type {{}}
 */
var Cmd = {

    // 新增一个控制器
    CONTROLLER_ADD: 70,

    // 设置控制器的所有参数
    CONTROLLER_PARAM_SET: 71,

    // 获取控制器的所有配置参数
    CONTROLLER_PARAM_GET: 72,

    // 删除一个控制器，并删除和它相关的电表
    CONTROLLER_DELETE: 73,

    // 远程升级
    REMOTE_UPGRADE: 74,

    // 设置控制器的时间
    CONTROLLER_TIME_SET: 75,

    // 获取控制器的时间
    CONTROLLER_TIME_GET: 76,

    // 重启控制器，相当于重启复位
    CONTROLLER_RESTART: 77,

    // 重置控制器，恢复出厂设置，并重启
    CONTROLLER_RESET: 78,

    // 新增加一个电表信息
    AMMETER_ADD: 80,

    // 删除一个电表
    AMMETER_DELETE: 81,

    // 设置一个电表的所有参数
    AMMETER_SET: 82,

    // 获取一个电表的所有配置信息
    AMMETER_GET: 83,

    // 新增一个电表的采集方案
    AMMETER_COLLECTION_SCHEME_ADD: 84,

    // 设置一个电表的采集方案
    AMMETER_COLLECTION_SCHEME_SET: 85,

    // 删除一个电表的采集方案
    AMMETER_COLLECTION_SCHEME_DELETE: 86,

    // 电表获取实时数据
    AMMETER_REALTIME_DATA_GET: 87,

    // 电表获取历史数据
    AMMETER_HISTORY_DATA_GET: 88,

    // 接收实时数据
    AMMETER_REALTIME_DATA_RECEIVE: 89,

    // 接收断点数据
    AMMETER_RESUME_DATA_RECEIVE: 90,

    // 接收报警数据
    ALARM_DATA_RECEIVE: 91,

    // 新增一个控制器
    CONTROLLER_ADD_DDC: 10,

    // 设置控制器的所有参数
    CONTROLLER_PARAM_SET_DDC: 11,

    // 删除一个控制器，并删除和它相关的模块和点
    CONTROLLER_DELETE_DDC: 12,

    // 获取控制器的所有配置参数
    CONTROLLER_PARAM_GET_DDC: 13,

    // 远程升级
    REMOTE_UPGRADE_DDC: 14,

    // 设置控制器的时间
    CONTROLLER_TIME_SET_DDC: 15,

    // 获取控制器的时间
    CONTROLLER_TIME_GET_DDC: 16,

    // 重启控制器，相当于重启复位
    CONTROLLER_RESTART_DDC: 17,

    // 重置控制器，恢复出厂设置，并重启
    CONTROLLER_RESET_DDC: 18,

    // 新增加一个模块
    MODULE_ADD: 20,

    // 设置一个模块的所有参数
    MODULE_PARAM_SET: 21,

    // 删除一个模块，并删除和模块相关的点
    MODULE_DELETE: 22,

    // 获取一个模块的所有配置信息
    MODULE_PARAM_GET: 23,

    // 接收实点信息
    REAL_POINT_DATA_RECEIVE: 24,

    // 获取一个模块的错误代码
    MODULE_ERROR_CODE_GET : 25,

    // 批量获取模块的错误代码
    MODULE_ERROR_CODE_GET_ALL : 26,

    // 新增加一个逻辑点
    POINT_ADD : 30,

    // 设置一个逻辑点的所有参数
    POINT_PARAM_SET : 31,

    // 设置一个逻辑点的值
    POINT_VALUE_SET : 32,

    // 设置逻辑点的值，根据点的名字
    POINT_VALUE_BY_NAME_SET : 33,

    // 删除一个逻辑点
    POINT_DELETE: 34,

    // 获取一个逻辑点的所有配置参数
    POINT_PARAM_GET: 35,

    // 获取一个逻辑点的报警信息
    POINT_ALARM_DATA_GET : 36,

    // 接收DDC的全部点信息
    POINT_DATA_ALL_RECEIVE: 37,

    // 接收虚点信息
    VIRTUAL_POINT_DATA_RECEIVE: 38,

    // 增加一个场景
    SCENE_ADD : 40,

    // 设置一个场景的所有参数
    SCENE_PARAM_SET: 41,

    // 删除一个场景
    SCENE_DELETE: 42,

    // 获取一个场景的配置信息
    SCENE_PARAM_GET: 43,

    //获取场景下的单个模式信息
    SCENE_MODE_PARAM_GET_DDC:44,

    //删除场景下的单个模式信息
    SCENE_MODE_PARAM_DELETE_DDC:45,

    // 增加一条计划
    PLAN_ADD: 50,

    // 修改一条计划的所有参数
    PLAN_PARAM_SET: 51,

    // 删除一条计划
    PLAN_DELETE: 52,

    // 获取一条计划的所有参数
    PLAN_PARAM_GET: 53,

    // 新增一个IP路由器
    CONTROLLER_ADD_LDC: 110,

    // 设置IP路由器的所有参数
    CONTROLLER_PARAM_SET_LDC: 111,

    // 删除一个IP路由器，并删除和它相关的模块和点
    CONTROLLER_DELETE_LDC: 112,

    // 获取IP路由器的所有配置参数
    CONTROLLER_PARAM_GET_LDC: 113,

    // 远程升级
    REMOTE_UPGRADE_LDC: 114,

    // 设置IP路由器的时间
    CONTROLLER_TIME_SET_LDC: 115,

    // 获取IP路由器的时间
    CONTROLLER_TIME_GET_LDC: 116,

    // 重启IP路由器，相当于复位
    CONTROLLER_RESTART_LDC: 117,

    // 重置IP路由器，恢复出厂设置，并重启
    CONTROLLER_RESET_LDC: 118,

    // 新增加一个模块
    MODULE_ADD_LDC: 120,

    // 设置一个模块的所有参数
    MODULE_PARAM_SET_LDC: 121,

    // 删除一个模块，并删除和模块相关的点
    MODULE_DELETE_LDC: 122,

    // 获取一个模块的所有配置信息
    MODULE_PARAM_GET_LDC: 123,

    // 接收实点信息
    REAL_POINT_DATA_RECEIVE_LDC: 124,

    // 获取一个模块的错误代码
    MODULE_ERROR_CODE_GET_LDC : 125,

    // 批量接收一个模块的错误代码
    MODULE_ERROR_CODE_GET_LDC_ALL : 126,

    // 新增加一个逻辑点
    POINT_ADD_LDC : 130,

    // 设置一个逻辑点的所有参数
    POINT_PARAM_SET_LDC : 131,

    // 设置一个逻辑点的值
    POINT_VALUE_SET_LDC : 132,

    // 设置逻辑点的值，根据点的名字
    POINT_VALUE_BY_NAME_SET_LDC : 133,

    // 删除一个逻辑点
    POINT_DELETE_LDC: 134,

    // 获取一个逻辑点的所有配置参数
    POINT_PARAM_GET_LDC: 135,

    // 获取一个逻辑点的报警信息
    POINT_ALARM_DATA_GET_LDC : 136,

    // IP路由器的全部点信息
    POINT_DATA_ALL_RECEIVE_LDC: 137,

    // 接收虚点信息
    VIRTUAL_POINT_DATA_RECEIVE_LDC: 138,

    // 增加一个场景
    SCENE_ADD_LDC : 140,

    // 设置一个场景的所有参数
    SCENE_PARAM_SET_LDC: 141,

    // 删除一个场景
    SCENE_DELETE_LDC: 142,

    // 获取一个场景的配置信息
    SCENE_PARAM_GET_LDC: 143,

    // 增加一条计划
    PLAN_ADD_LDC: 150,

    // 修改一条计划的所有参数
    PLAN_PARAM_SET_LDC: 151,

    // 删除一条计划
    PLAN_DELETE_LDC: 152,

    // 获取一条计划的所有参数
    PLAN_PARAM_GET_LDC: 153,

    //获取场景下的单个模式信息
    SCENE_MODE_PARAM_GET_LDC:144,

    //删除场景下的单个模式信息
    SCENE_MODE_PARAM_DELETE_LDC:145,
};


Object.freeze(Cmd);