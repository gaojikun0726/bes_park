/**
 * xiepufeng
 */

(function ($)
{

    var resultEnum = {
        SUCCEED: 0,// 成功
        ERR_CRC: 2, // crc 校验错误
        ERR_PARAM: 3, // 参数错误
        ERR_UNKNOWN: 4, // 未知错误
    };

    // 获取 websocket 登陆信息方法
    function getWebSocketLoginInfo(callback)
    {
        $.ajax({
            type: 'GET',
            url: _ctx + '/wsLoginInfo',
            dataType: 'json',
            success: function (result)
            {

                if (typeof callback !== 'function')
                {
                    console.warn('callback not function');
                    return;
                }

                var status = result && result.status;

                if (status !== '1')
                {
                    console.warn('status not equal to 1');
                }

                var data = result.data;

                if (!data)
                {
                    console.warn('data is null');
                    return;
                }

                callback(data);

            },
            error: function (error)
            {
                console.error(error);
            }
        });
    }

    // 获取 websocket 登陆信息并建立 websocket 连接
    getWebSocketLoginInfo(function (data)
    {

        if (!data)
        {
            console.warn('data is null');
            return;
        }

        var host = data.host;
        var port = data.port;
        var path = data.path;
        var sessionId = data.sessionId;
        var userId = data.userId;
        var password = data.password;
        var interval = data.interval;

        if (!host || !port || !path || !sessionId || !userId || !password || !interval)
        {
            console.warn('Invalid parameter');
            return;
        }

        // 建立 websocket 连接
        socket({
            url: 'ws://' + location.hostname + ':' + port + path,
            sessionId: sessionId,
            userId: userId,
            password: password,
            keepalive: interval * 1000
        });
    });


    // websocket 消息回调事件
    socket.onmessage = function (data)
    {

        var method = data.method;

        if (method !== 'event')
        {
            return;
        }

        var params = data.params;
        var subEvent = params.subEvent;
        var content = params.content;
        switch (subEvent)
        {
            case 'EDC':
                edcDataHandle(content);
                break;
            case 'DDC':
                ddcDataHandle(content);
                break;
            case 'LDC':
                ldcDataHandle(content);
                break;
            case 'ALARM'://报警
                fdcDataHandle(content);
                break;
            case 'DEVICE_STATE': // 设备状态信息
                deviceStateHandle(content);
                break;
            case 'RemoteUpgrade': // 远程升级
                RemoteUpgrade(content);
                break;
            default:
        }
    };

    // 处理能耗采集器数据
    function edcDataHandle(response)
    {
        if (null == response)
        {
            return;
        }

        var index = response.index;
        var result = response.code;

        switch (index)
        {
            // 新增一个控制器
            case Cmd.CONTROLLER_ADD:

                var state;

                if (result === resultEnum.SUCCEED)
                {
                    state = true;
                    toastr.success('能耗采集器同步成功');

                } else
                {

                    state = false;
                    toastr.warning('能耗采集器同步失败');

                }

                if (typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo !== 'undefined'
                    && typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPage  !== 'undefined')
                {
                    basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPage(state);
                }
                break;
            // 设置一个控制器
            case Cmd.CONTROLLER_PARAM_SET:

                var state;

                if (result === resultEnum.SUCCEED)
                {
                    state = true;
                    toastr.success('能耗采集器同步成功');

                } else
                {

                    state = false;
                    toastr.warning('能耗采集器同步失败');

                }

                if (typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo !== 'undefined'
                    && typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPage !== 'undefined')
                {
                    basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPage(state);
                }

                break;
            // 删除一个控制器，并删除和它相关的电表
            case Cmd.CONTROLLER_DELETE:

                if (result === resultEnum.SUCCEED)
                {
                    toastr.success('能耗采集器删除成功');


                } else
                {

                    toastr.warning('能耗采集器删除失败');

                }
                break;
            // 远程升级
            case Cmd.REMOTE_UPGRADE:

                if (result === resultEnum.SUCCEED)
                {
                    toastr.success('远程升级下发成功');

                } else
                {
                    toastr.warning('远程升级下发失败');
                }
                break;

            // 设置控制器的时间
            case Cmd.CONTROLLER_TIME_SET:

                if (result === resultEnum.SUCCEED)
                {
                    toastr.success('能耗采集器时间设置成功');

                } else
                {
                    toastr.warning('能耗采集器时间设置失败');
                }
                break;

            // 重启控制器，相当于重启复位
            case Cmd.CONTROLLER_RESTART:

                if (result === resultEnum.SUCCEED)
                {
                    toastr.success('能耗采集器重启成功');
                    $('#ip_warn_hint_img').hide();

                } else
                {
                    toastr.warning('能耗采集器重启失败');
                }
                break;

            // 获取控制器的时间
            case Cmd.CONTROLLER_TIME_GET:

                if (result === resultEnum.SUCCEED)
                {

                    var data = response.data;
                    if (typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo !== 'undefined'
                        && typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.showCollectorTime !== 'undefined')
                    {

                        basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.showCollectorTime(data);
                    }
                    toastr.success('获取能耗采集器时间成功');

                } else
                {
                    toastr.warning('获取能耗采集器时间失败');
                }
                break;


            // 重置控制器，恢复出厂设置，并重启
            case Cmd.CONTROLLER_RESET:

                if (result === resultEnum.SUCCEED)
                {
                    // 设置页面未同步
                    if (typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo !== 'undefined'
                        && typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPage !== 'undefined')
                    {

                        basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPage(false);
                    }
                    toastr.success('能耗采集器重置成功');

                } else
                {
                    toastr.warning('能耗采集器重置失败');
                }
                break;

            // 获取控制器的所有配置参数
            case Cmd.CONTROLLER_PARAM_GET:

                if (result === resultEnum.SUCCEED)
                {
                    var data = response.data || {};

                    if (typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo !== 'undefined'
                        && typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.handleLowerData !== 'undefined')
                    {

                        basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.handleLowerData(data);
                    }
                    toastr.success('获取能耗采集器配置参数成功');

                } else
                {
                    toastr.warning('获取能耗采集器配置参数失败');
                }
                break;
            // 新增加一个电表信息
            case Cmd.AMMETER_ADD:

                var state;

                if (result === resultEnum.SUCCEED)
                {
                    state = true;
                    toastr.success('电表同步成功');

                } else
                {
                    state = false;
                    toastr.warning('电表同步失败');
                }

                if (typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo !== 'undefined'
                    && typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPage !== 'undefined')
                {
                    basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPage(state);
                }

                break;
            // 删除一个电表信息
            case Cmd.AMMETER_DELETE:

                if (result === resultEnum.SUCCEED)
                {
                    toastr.success('电表删除成功');

                } else
                {
                    toastr.warning('电表删除失败,请重置当前能耗采集器');
                }
                break;
            // 设置一个电表信息
            case Cmd.AMMETER_SET:

                var state;

                if (result === resultEnum.SUCCEED)
                {
                    state = true;
                    toastr.success('电表同步成功');


                } else
                {
                    state = false;
                    toastr.warning('电表同步失败');
                }

                if (typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo !== 'undefined'
                    && typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPage !== 'undefined')
                {
                    basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPage(state);
                }

                break;
            // 获取一个电表的所有配置信息
            case Cmd.AMMETER_GET:

                if (result === resultEnum.SUCCEED)
                {
                    var data = response.data;

                    if (typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo !== 'undefined'
                        && typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.handleLowerData !== 'undefined')
                    {

                        basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.handleLowerData(data);

                    }
                    toastr.success('获取电表的配置信息成功');

                } else
                {
                    toastr.warning('获取电表的配置信息失败');
                }

                break;
            // 电表获取实时数据
            case Cmd.AMMETER_REALTIME_DATA_GET:

                if (result === resultEnum.SUCCEED)
                {
                    var data = response.data;

                    if (typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo !== 'undefined'
                        && typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.receiveAmmeterRealTimeData !== 'undefined')
                    {

                        basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.receiveAmmeterRealTimeData(data);
                    }
                    toastr.success('电表实时数据获取成功');

                } else
                {
                    toastr.warning('电表实时数据获取失败');
                }

                break;
            // 电表获取历史数据
            case Cmd.AMMETER_HISTORY_DATA_GET:

                if (result === resultEnum.SUCCEED)
                {
                    var data = response.data;

                    if (typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo !== 'undefined'
                        && typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.receiveAmmeterHistoryData !== 'undefined')
                    {

                        basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.receiveAmmeterHistoryData(data);

                    }
                    toastr.success('电表历史数据获取成功');

                } else
                {
                    toastr.warning('电表历史数据获取失败');
                }

                break;
        }

    }

    // 分发ddc数据
    function ddcDataHandle(response)
    {
        if (null == response)
        {
            return;
        }

        var index = response.index;
        var result = response.code;

        switch (index)
        {
            //新增一个DDC
            case Cmd.CONTROLLER_ADD_DDC :

                var state;

                if (result === resultEnum.SUCCEED)
                {
                    toastr.success('DDC控制器同步成功');
                    state = true;

                } else
                {
                    toastr.warning('DDC控制器同步失败');
                    state = false;
                }

                // 设置页面已同步
                if (typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo !== 'undefined'
                    && typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPageByDDC !== 'undefined')
                {
                    basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPageByDDC(state);
                }

                break;
            //设置控制器的所有的参数
            case Cmd.CONTROLLER_PARAM_SET_DDC :

                var state;

                if (result === resultEnum.SUCCEED)
                {
                    toastr.success('DDC控制器同步成功');
                    state = true;

                } else
                {
                    toastr.warning('DDC控制器同步失败');
                    state = false;
                }

                // 设置页面已同步
                if (typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo !== 'undefined'
                    && typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPageByDDC !== 'undefined')
                {
                    basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPageByDDC(state);
                }
                break;
            // 删除一个控制器，并删除和它相关的模块和点
            case Cmd.CONTROLLER_DELETE_DDC :

                break;
            // 获取控制器的所有配置参数
            case Cmd.CONTROLLER_PARAM_GET_DDC :
                if (result === resultEnum.SUCCEED)
                {
                    if (typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo !== 'undefined'
                        && typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.handleLowerData !== 'undefined')
                    {
                        var data = response.data;
                        basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.handleLowerData(data);
                    }

                    toastr.success('获取DDC控制器配置参数成功');
                } else
                {
                    toastr.warning('获取DDC控制器配置参数失败');
                }
                break;
            // 远程升级
            case Cmd.REMOTE_UPGRADE_DDC :

                if (result === resultEnum.SUCCEED)
                {
                    toastr.success('远程升级下发成功');

                } else
                {
                    toastr.warning('远程升级下发失败');
                }
                break;
            // 设置控制器的时间
            case Cmd.CONTROLLER_TIME_SET_DDC :

                if (result === resultEnum.SUCCEED)
                {
                    toastr.success('DDC控制器设置时间成功');
                } else
                {
                    toastr.warning('DDC控制器设置时间失败');
                }
                break;
            // 获取控制器的时间
            case Cmd.CONTROLLER_TIME_GET_DDC :

                if (result === resultEnum.SUCCEED)
                {
                    var data = response.data;

                    if (typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo !== 'undefined'
                        && typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.showDDCTime !== 'undefined')
                    {
                        basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.showDDCTime(data);
                    }

                    toastr.success('DDC控制器获取时间成功');
                } else
                {
                    toastr.warning('DDC控制器获取时间失败');
                }
                break;
            // 重启控制器，相当于重启复位
            case Cmd.CONTROLLER_RESTART_DDC :
                if (result === resultEnum.SUCCEED)
                {
                    $('#ip_warn_hint_img').hide();
                    toastr.success('重启控制器成功');
                } else
                {
                    toastr.warning('重启控制器失败');
                }
                break;
            // 重置控制器，恢复出厂设置，并重启
            case Cmd.CONTROLLER_RESET_DDC :

                if (result === resultEnum.SUCCEED)
                {
                    state = true;
                    toastr.success('重置控制器成功');

                    if (typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo !== 'undefined'
                        && typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPageByDDC !== 'undefined')
                    {
                        // 设置页面同步状态
                        basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPageByDDC(false);
                    }
                } else
                {
                    toastr.warning('重置控制器失败');
                }

                break;
            // 新增加一个模块
            case Cmd.MODULE_ADD :

                var state;

                if (result === resultEnum.SUCCEED)
                {
                    state = true;
                    toastr.success('DDC模块同步成功');

                } else
                {
                    state = false;
                    toastr.warning('DDC模块同步失败');
                }

                if (typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo !== 'undefined'
                    && typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPageModule !== 'undefined')
                {
                    // 设置页面未同步
                    basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPageModule(state);
                }
                break;
            // 设置一个模块的所有参数
            case Cmd.MODULE_PARAM_SET :

                var state;

                if (result === resultEnum.SUCCEED)
                {
                    state = true;
                    toastr.success('DDC模块同步成功');

                } else
                {
                    state = false;
                    toastr.warning('DDC模块同步失败');
                }

                if (typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo !== 'undefined'
                    && typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPageModule !== 'undefined')
                {
                    // 设置页面未同步
                    basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPageModule(state);
                }
                break;
            // 删除一个模块，并删除和模块相关的点
            case Cmd.MODULE_DELETE :

                if (result === resultEnum.SUCCEED)
                {
                    toastr.success('DDC模块删除成功');

                } else
                {
                    toastr.warning('DDC模块删除失败');
                }


                break;
            // 获取一个模块的所有配置信息
            case Cmd.MODULE_PARAM_GET :
                if (result === resultEnum.SUCCEED)
                {
                    var data = response.data;
                    if (typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo !== 'undefined'
                        && typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.handleLowerData !== 'undefined')
                    {

                        basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.handleLowerData(data);
                    }
                    toastr.success('获取DDC模块配置参数成功');
                } else
                {
                    toastr.warning('获取DDC模块配置参数失败');
                }
                break;
            // 接收实点信息
            case Cmd.REAL_POINT_DATA_RECEIVE :

                var list = response.data || [];

                list.forEach((item) => {

                    var name = item.name;

                    PubSub.publish(name, item);
                });

                /*if (typeof basedatamanage_eqmanage_eqconfiguration_sbdy !== 'undefined'
                    && typeof basedatamanage_eqmanage_eqconfiguration_sbdy.refreshTree !== 'undefined')
                {

                    basedatamanage_eqmanage_eqconfiguration_sbdy.refreshTree(list);
                }*/


                break;
            // 获取一个模块的错误代码
            case Cmd.MODULE_ERROR_CODE_GET :

                var data = response.data;
                // 更新设备树状态
                if (typeof basedatamanage_eqmanage_eqconfiguration_sbdy !== 'undefined'
                    && typeof basedatamanage_eqmanage_eqconfiguration_sbdy.onlineStateHandle !== 'undefined')
                {
                    basedatamanage_eqmanage_eqconfiguration_sbdy.onlineStateHandle(data);
                }
                //获取iframe内部的变量

                var designFrameOne = window.frames['design_frame_one'];

                if (typeof designFrameOne === 'undefined')
                {
                    return;
                }
                var contentWindow = designFrameOne.contentWindow;
                var lowConditionerRefresh = contentWindow.lowConditionerRefresh;
                var middleConditionerRefresh = contentWindow.middleConditionerRefresh;
                //低档温控器模块通信状态回调
                if(typeof lowConditionerRefresh !== "undefined"){
                    lowConditionerRefresh.networkRefresh(data);
                }
                //中档温控器模块通信状态回调
                if(typeof middleConditionerRefresh !== "undefined"){
                    middleConditionerRefresh.networkRefresh(data);
                }
                break;

            // 批量获取模块的错误代码
            case Cmd.MODULE_ERROR_CODE_GET_ALL :

                var data = response.data;
                // 更新设备树状态
                if (typeof basedatamanage_eqmanage_eqconfiguration_sbdy !== 'undefined'
                    && typeof basedatamanage_eqmanage_eqconfiguration_sbdy.onlineStateHandle !== 'undefined')
                {
                    basedatamanage_eqmanage_eqconfiguration_sbdy.onlineStateHandle(data);
                }
                //获取iframe内部的变量

                var designFrameOne = window.frames['design_frame_one'];

                if (typeof designFrameOne === 'undefined')
                {
                    return;
                }
                var contentWindow = designFrameOne.contentWindow;
                var lowConditionerRefresh = contentWindow.lowConditionerRefresh;
                var middleConditionerRefresh = contentWindow.middleConditionerRefresh;
                //低档温控器模块通信状态回调
                if(typeof lowConditionerRefresh !== "undefined"){
                    lowConditionerRefresh.networkRefresh(data);
                }
                //中档温控器模块通信状态回调
                if(typeof middleConditionerRefresh !== "undefined"){
                    middleConditionerRefresh.networkRefresh(data);
                }
                break;

            // 新增加一个逻辑点
            case Cmd.POINT_ADD :

                var state;

                if (result === resultEnum.SUCCEED)
                {
                    state = true;
                    toastr.success('同步成功');

                } else
                {
                    state = false;
                    toastr.warning('同步失败');
                }
                // 设置页面未同步
                if (typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo !== 'undefined'
                    && typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPage !== 'undefined')
                {
                    basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPage(state);
                }

                break;
            // 设置一个逻辑点的所有参数
            case Cmd.POINT_PARAM_SET :
                var state;

                if (result === resultEnum.SUCCEED)
                {
                    state = true;
                    toastr.success('同步成功');

                } else
                {
                    state = false;
                    toastr.warning('同步失败');
                }

                if (typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo !== 'undefined'
                    && typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPage !== 'undefined')
                {
                    basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPage(state);
                }

                break;
            // 设置一个逻辑点的值
            case Cmd.POINT_VALUE_SET :

                if (result === resultEnum.SUCCEED)
                {
                    toastr.success('设置成功');

                } else
                {
                    toastr.warning('设置失败');
                }
                break;
            // 设置逻辑点的值，根据点的名字
            case Cmd.POINT_VALUE_BY_NAME_SET :

                if (result === resultEnum.SUCCEED)
                {
                    toastr.success('设置成功');

                } else
                {
                    toastr.warning('设置失败');
                }
                break;
            // 删除一个逻辑点
            case Cmd.POINT_DELETE :
                if (result === resultEnum.SUCCEED)
                {
                    toastr.success('删除成功');

                } else
                {
                    toastr.warning('删除失败,请重置当前DDC控制器');
                }
                break;
            // 获取一个逻辑点的所有配置参数
            case Cmd.POINT_PARAM_GET :
                if (result === resultEnum.SUCCEED)
                {
                    var data = response.data;

                    if (typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo !== 'undefined'
                        && typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.handleLowerData !== 'undefined')
                    {
                        basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.handleLowerData(data);
                    }

                    toastr.success('获取点位配置参数成功');
                } else
                {
                    toastr.warning('获取点位配置参数失败');
                }
                break;
            // 获取一个逻辑点的报警信息
            case Cmd.POINT_ALARM_DATA_GET :

                break;
            // 接收DDC的全部点信息
            case Cmd.POINT_DATA_ALL_RECEIVE :

                var list = response.data || [];

                list.forEach((item) => {

                    var name = item.name;

                    PubSub.publish(name, item);
                });

                /*if (typeof basedatamanage_eqmanage_eqconfiguration_sbdy !== 'undefined'
                    && typeof basedatamanage_eqmanage_eqconfiguration_sbdy.refreshTree !== 'undefined')
                {

                    basedatamanage_eqmanage_eqconfiguration_sbdy.refreshTree(list);
                }*/

                break;
            // 接收虚点信息
            case Cmd.VIRTUAL_POINT_DATA_RECEIVE :
                var list = response.data || [];

                list.forEach((item) => {

                    var name = item.name;

                    PubSub.publish(name, item);
                });

               /* if (typeof basedatamanage_eqmanage_eqconfiguration_sbdy !== 'undefined'
                    && typeof basedatamanage_eqmanage_eqconfiguration_sbdy.refreshTree !== 'undefined')
                {

                    basedatamanage_eqmanage_eqconfiguration_sbdy.refreshTree(list);
                }*/
                break;
//设置一个场景的所有参数
            case Cmd.SCENE_PARAM_SET :
                if (result === resultEnum.SUCCEED)
                {
                    toastr.success('同步成功');

                } else
                {
                    toastr.warning('同步失败');
                }
                break;

            //删除一个场景
            case  Cmd.SCENE_DELETE :
                if (result === resultEnum.SUCCEED)
                {
                    toastr.success('删除成功');

                } else
                {
                    toastr.warning('删除失败');
                }
                break;

            //获取场景下的单个模式信息
            case Cmd.SCENE_MODE_PARAM_GET_DDC :

                if (result === resultEnum.SUCCEED)
                {
                    var  data = response.data;

                    if (typeof basedatamanage_eqmanage_sceneConfig !== 'undefined'
                        && typeof basedatamanage_eqmanage_sceneConfig.contrastModeValue !== 'undefined')
                    {
                        basedatamanage_eqmanage_sceneConfig.contrastModeValue(data);
                    }

                    toastr.success('获取场景模式信息成功!');
                }else
                {
                    toastr.warning('获取场景模式信息失败');
                }
                break;

            //删除场景下的单个模式信息
            case Cmd.SCENE_MODE_PARAM_DELETE_DDC :

                if (result === resultEnum.SUCCEED)
                {
                    toastr.success('删除场景模式信息成功');

                } else
                {
                    toastr.warning('删除失败,请重置当前LDC控制器');
                }
                break;

            //新增一条计划
            case Cmd.PLAN_ADD :
                if (result === resultEnum.SUCCEED)
                {
                    toastr.success('新增计划成功');

                } else
                {
                    toastr.warning('新增计划失败');
                }
                break;

            //删除一条计划
            case Cmd.PLAN_DELETE :

                if (result === resultEnum.SUCCEED)
                {
                    toastr.success('删除计划成功');

                } else
                {
                    toastr.warning('删除计划失败');
                }
                break;

            //修改一条计划
            case Cmd.PLAN_PARAM_SET :

                if (result === resultEnum.SUCCEED)
                {
                    toastr.success('同步计划成功');

                } else
                {
                    toastr.warning('同步计划失败');
                }
                break;

            //获取一条计划所有参数
            case Cmd.PLAN_PARAM_GET :

                if (result === resultEnum.SUCCEED)
                {
                    var  data = response.data;

                    if (typeof basedatamanage_eqmanage_planConfig !== 'undefined'
                        && typeof basedatamanage_eqmanage_planConfig.contrastPlanValue !== 'undefined')
                    {
                        basedatamanage_eqmanage_planConfig.contrastPlanValue(data);
                    }

                    toastr.success('获取计划信息成功!');
                }else
                {
                    toastr.warning('获取计划信息失败');
                }
                break;


        }
    }
    // 分发ldc数据
    function ldcDataHandle(response)
    {

        if (null == response)
        {
            return;
        }

        var index = response.index;
        var result = response.code;

        switch (index)
        {
            //新增一个IP路由器
            case Cmd.CONTROLLER_ADD_LDC :

                var state;

                if (result === resultEnum.SUCCEED)
                {
                    toastr.success('IP路由器同步成功');
                    state = true;

                } else
                {
                    toastr.warning('IP路由器同步失败');
                    state = false;
                }

                // 设置页面已同步
                if (typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo !== 'undefined'
                    && typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPageByDDC !== 'undefined')
                {
                    basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPageByDDC(state);
                }

                break;
            // 设置IP路由器的所有参数
            case Cmd.CONTROLLER_PARAM_SET_LDC :

                var state;

                if (result === resultEnum.SUCCEED)
                {
                    toastr.success('IP路由器同步成功');
                    state = true;

                } else
                {
                    toastr.warning('IP路由器同步失败');
                    state = false;
                }

                // 设置页面已同步
                if (typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo !== 'undefined'
                    && typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPageByDDC !== 'undefined')
                {
                    basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPageByDDC(state);
                }

                break;
            // 删除一个控制器，并删除和它相关的模块和点
            case Cmd.CONTROLLER_DELETE_LDC :

                if (result === resultEnum.SUCCEED)
                {
                    toastr.success('IP路由器删除成功');

                } else
                {
                    toastr.warning('IP路由器删除失败');
                }

                break;
            // 获取IP路由器的所有配置参数
            case Cmd.CONTROLLER_PARAM_GET_LDC :
                if (result === resultEnum.SUCCEED)
                {

                    if (typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo !== 'undefined'
                        && typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.handleLowerData !== 'undefined')
                    {
                        var data = response.data;
                        basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.handleLowerData(data);
                    }

                    toastr.success('获取IP路由器的配置参数成功');
                } else
                {
                    toastr.warning('获取IP路由器的配置参数失败');
                }
                break;
            // 远程升级
            case Cmd.REMOTE_UPGRADE_LDC :

                if (result === resultEnum.SUCCEED)
                {
                    toastr.success('远程升级下发成功');

                } else
                {
                    toastr.warning('远程升级下发失败');
                }
                break;
                break;
            // 设置IP路由器的时间
            case Cmd.CONTROLLER_TIME_SET_LDC :
                if (result === resultEnum.SUCCEED)
                {
                    toastr.success('设置IP路由器时间成功');
                } else
                {
                    toastr.warning('设置IP路由器时间失败');
                }
                break;
            // 获取IP路由器的时间
            case Cmd.CONTROLLER_TIME_GET_LDC :
                if (result === resultEnum.SUCCEED)
                {

                    if (typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo !== 'undefined'
                        && typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.showDDCTime !== 'undefined')
                    {
                        var data = response.data;
                        basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.showDDCTime(data);
                    }

                    toastr.success('获取IP路由器的时间成功');
                } else
                {
                    toastr.warning('获取IP路由器的时间失败');
                }
                break;
            // 重启IP路由器，相当于复位
            case Cmd.CONTROLLER_RESTART_LDC :
                if (result === resultEnum.SUCCEED)
                {
                    $('#ip_warn_hint_img').hide();
                    toastr.success('重启IP路由器成功');
                } else
                {
                    toastr.warning('重启IP路由器失败');
                }
                break;
            // 重置IP路由器，恢复出厂设置，并重启
            case Cmd.CONTROLLER_RESET_LDC :

                var state;

                if (result === resultEnum.SUCCEED)
                {
                    if (typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo !== 'undefined'
                        && typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPageByDDC !== 'undefined')
                    {
                        // 设置页面未同步
                        basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPageByDDC(false);
                    }
                    toastr.success('IP路由器重置成功');
                } else
                {
                    toastr.warning('IP路由器重置失败');
                }

                break;
            // 新增加一个模块
            case Cmd.MODULE_ADD_LDC :

                var state;

                if (result === resultEnum.SUCCEED)
                {
                    state = true;
                    toastr.success('照明模块同步成功');

                } else
                {
                    state = false;
                    toastr.warning('照明模块同步失败');
                }

                if (typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo !== 'undefined'
                    && typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPageModule !== 'undefined')
                {
                    // 设置页面同步状态
                    basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPageModule(state);
                }
                break;
            // 设置一个模块的所有参数
            case Cmd.MODULE_PARAM_SET_LDC :

                var state;

                if (result === resultEnum.SUCCEED)
                {
                    state = true;
                    toastr.success('照明模块同步成功');

                } else
                {
                    state = false;
                    toastr.warning('照明模块同步失败');
                }

                if (typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo !== 'undefined'
                    && typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPageModule !== 'undefined')
                {
                    // 设置页面同步状态
                    basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPageModule(state);
                }
                break;
            // 删除一个模块，并删除和模块相关的点
            case Cmd.MODULE_DELETE_LDC :

                if (result === resultEnum.SUCCEED)
                {
                    toastr.success('照明模块删除成功');

                } else
                {
                    toastr.warning('照明模块删除失败');
                }

                break;
            // 获取一个模块的所有配置信息
            case Cmd.MODULE_PARAM_GET_LDC :
                if (result === resultEnum.SUCCEED)
                {
                    var data = response.data;
                    if (typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo !== 'undefined'
                        && typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.handleLowerData !== 'undefined')
                    {

                        basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.handleLowerData(data);
                    }
                    toastr.success('获取照明模块配置参数成功');
                } else
                {
                    toastr.warning('获取照明模块配置参数失败');
                }
                break;
            // 接收实点信息
            case Cmd.REAL_POINT_DATA_RECEIVE_LDC :

                var list = response.data || [];

                list.forEach((item) => {

                    var name = item.name;

                    PubSub.publish(name, item);
                });

                /*if (typeof basedatamanage_eqmanage_eqconfiguration_sbdy !== 'undefined'
                    && typeof basedatamanage_eqmanage_eqconfiguration_sbdy.refreshTree !== 'undefined')
                {

                    basedatamanage_eqmanage_eqconfiguration_sbdy.refreshTree(list);
                }*/


                break;
            // 获取一个模块的错误代码
            case Cmd.MODULE_ERROR_CODE_GET_LDC :

                var data = response.data;
                // 更新设备树状态
                if (typeof basedatamanage_eqmanage_eqconfiguration_sbdy !== 'undefined'
                    && typeof basedatamanage_eqmanage_eqconfiguration_sbdy.onlineStateHandle !== 'undefined')
                {
                    basedatamanage_eqmanage_eqconfiguration_sbdy.onlineStateHandle(data);
                }
                break;

            // 批量接收一个模块的错误代码
            case Cmd.MODULE_ERROR_CODE_GET_LDC_ALL :

                var data = response.data;
                // 更新设备树状态
                if (typeof basedatamanage_eqmanage_eqconfiguration_sbdy !== 'undefined'
                    && typeof basedatamanage_eqmanage_eqconfiguration_sbdy.onlineStateHandle !== 'undefined')
                {
                    basedatamanage_eqmanage_eqconfiguration_sbdy.onlineStateHandle(data);
                }
                break;
            // 新增加一个逻辑点
            case Cmd.POINT_ADD_LDC :

                var state;

                if (result === resultEnum.SUCCEED)
                {
                    state = true;
                    toastr.success('同步成功');

                } else
                {
                    state = false;
                    toastr.warning('同步失败');
                }
                // 设置页面未同步
                if (typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo !== 'undefined'
                    && typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPage !== 'undefined')
                {
                    basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPage(state);
                }

                break;
            // 设置一个逻辑点的所有参数
            case Cmd.POINT_PARAM_SET_LDC :

                var state;

                if (result === resultEnum.SUCCEED)
                {
                    state = true;
                    toastr.success('同步成功');

                } else
                {
                    state = false;
                    toastr.warning('同步失败');
                }

                if (typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo !== 'undefined'
                    && typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPage !== 'undefined')
                {
                    basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setSyncPage(state);
                }

                break;
            // 设置一个逻辑点的值
            case Cmd.POINT_VALUE_SET_LDC :

                if (result === resultEnum.SUCCEED)
                {
                    toastr.success('设置成功');

                } else
                {
                    toastr.warning('设置失败');
                }
                break;
            // 设置逻辑点的值，根据点的名字
            case Cmd.POINT_VALUE_BY_NAME_SET_LDC :

                if (result === resultEnum.SUCCEED)
                {
                    toastr.success('设置成功');

                } else
                {
                    toastr.warning('设置失败');
                }
                break;
            // 删除一个逻辑点
            case Cmd.POINT_DELETE_LDC :
                if (result === resultEnum.SUCCEED)
                {
                    toastr.success('删除成功');

                } else
                {
                    toastr.warning('删除失败,请重置当前DDC控制器');
                }
                break;
            // 获取一个逻辑点的所有配置参数
            case Cmd.POINT_PARAM_GET_LDC :

                if (result === resultEnum.SUCCEED)
                {
                    var data = response.data;

                    if (typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo !== 'undefined'
                        && typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.handleLowerData !== 'undefined')
                    {
                        basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.handleLowerData(data);
                    }

                    toastr.success('获取点位配置参数成功');
                } else
                {
                    toastr.warning('获取点位配置参数失败');
                }
                break;
            // 获取一个逻辑点的报警信息
            case Cmd.POINT_ALARM_DATA_GET_LDC :

                break;
            // IP路由器的全部点信息
            case Cmd.POINT_DATA_ALL_RECEIVE_LDC :

                var list = response.data || [];

                list.forEach((item) => {

                    var name = item.name;

                    PubSub.publish(name, item);
                });

               /* if (typeof basedatamanage_eqmanage_eqconfiguration_sbdy !== 'undefined'
                    && typeof basedatamanage_eqmanage_eqconfiguration_sbdy.refreshTree !== 'undefined')
                {

                    basedatamanage_eqmanage_eqconfiguration_sbdy.refreshTree(list);
                }*/

                break;
            // 接收虚点信息
            case Cmd.VIRTUAL_POINT_DATA_RECEIVE_LDC :

                var list = response.data || [];

                list.forEach((item) => {

                    var name = item.name;

                    PubSub.publish(name, item);
                });

                /*if (typeof basedatamanage_eqmanage_eqconfiguration_sbdy !== 'undefined'
                    && typeof basedatamanage_eqmanage_eqconfiguration_sbdy.refreshTree !== 'undefined')
                {

                    basedatamanage_eqmanage_eqconfiguration_sbdy.refreshTree(list);
                }*/

                break;

            //增加一个场景
            case Cmd.SCENE_ADD_LDC :
                if (result === resultEnum.SUCCEED)
            {
                toastr.success('保存成功');

            } else
            {
                toastr.warning('保存失败');
            }
                break;

            //设置(修改)一个场景
            case Cmd.SCENE_PARAM_SET_LDC :
                if (result === resultEnum.SUCCEED)
            {
                toastr.success('修改成功');

            } else
            {
                toastr.warning('修改失败');
            }
                break;

            //删除一个场景
            case  Cmd.SCENE_DELETE_LDC :
                if (result === resultEnum.SUCCEED)
            {
                toastr.success('删除成功');

            } else
            {
                toastr.warning('删除失败');
            }
            break;

            //获取场景下的单个模式信息
            case Cmd.SCENE_MODE_PARAM_GET_LDC :

                if (result === resultEnum.SUCCEED)
             {
                 var  data = response.data;

                 if (typeof basedatamanage_eqmanage_sceneConfig !== 'undefined'
                     && typeof basedatamanage_eqmanage_sceneConfig.contrastModeValue !== 'undefined')
                 {
                     basedatamanage_eqmanage_sceneConfig.contrastModeValue(data);
                 }

                 toastr.success('获取场景模式信息成功!');
             }else
             {
                 toastr.warning('获取场景模式信息失败');
             }
                break;

             //删除场景下的单个模式信息
            case Cmd.SCENE_MODE_PARAM_DELETE_LDC :

                if (result === resultEnum.SUCCEED)
                {
                    toastr.success('删除场景模式信息成功');

                } else
                {
                    toastr.warning('删除失败,请重置当前LDC控制器');
                }
                break;

             //新增一条计划
            case Cmd.PLAN_ADD_LDC :
                if (result === resultEnum.SUCCEED)
                {
                    toastr.success('新增计划成功');

                } else
                {
                    toastr.warning('新增计划失败');
                }
                break;

             //删除一条计划
            case Cmd.PLAN_DELETE_LDC :

                if (result === resultEnum.SUCCEED)
                {
                    toastr.success('删除计划成功');

                } else
                {
                    toastr.warning('删除计划失败');
                }
                break;

            //修改一条计划
            case Cmd.PLAN_PARAM_SET_LDC :

                if (result === resultEnum.SUCCEED)
                {
                    toastr.success('同步计划成功');

                } else
                {
                    toastr.warning('同步计划失败');
                }
                break;

             //获取一条计划所有参数
            case Cmd.PLAN_PARAM_GET_LDC :

                if (result === resultEnum.SUCCEED)
                {
                    var  data = response.data;

                    if (typeof basedatamanage_eqmanage_planConfig !== 'undefined'
                        && typeof basedatamanage_eqmanage_planConfig.contrastPlanValue !== 'undefined')
                    {
                        basedatamanage_eqmanage_planConfig.contrastPlanValue(data);
                    }

                    toastr.success('获取计划信息成功!');
                }else
                {
                    toastr.warning('获取计划信息失败');
                }
                break;

        }
    }

    function fdcDataHandle(data)
    {
        var warnCount = parseInt(data.noRecoverCount);
        if (warnCount != 0)
        {
            $("#warningIcon").attr("class", "earlyWarning_red");
            $("#warningInfoCount").text(warnCount);
        } else
        {
            $("#warningIcon").attr("class", "earlyWarning_blue");
            $("#warningInfoCount").text(warnCount);
        }
        if (switchState === true && warnCount > 0)
        {

            if ($('#alarm_audio').html())
            {
                return;
            }

            $('#alarm_audio').html(
                '      <audio id="alarm_audio" autoplay="autoplay" loop="loop">\n' +
                '            <source src="static/audio/alarm.mp3" type="audio/mpeg">\n' +
                '        </audio>')
        }
    }

    // 设备状态处理
    function deviceStateHandle(response)
    {
        if (!response)
        {
            console.warn('设备状态数据异常：数据不存在');
            return;
        }

        var code = response.code;
        var ip = response.ip;
        var data = response.data;

        /*if (code === 0)
        {
            toastr.warning(ip + ' 离线');

        }else if (code === 1)
        {
            toastr.success(ip + ' 上线');
        }*/

        // 更新控制器状态
        if (typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo !== 'undefined'
            && typeof basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.onlineStateHandle !== 'undefined')
        {
            basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.onlineStateHandle(ip, code);
        }

        // 更新设备树状态
        if (typeof basedatamanage_eqmanage_eqconfiguration_sbdy !== 'undefined'
            && typeof basedatamanage_eqmanage_eqconfiguration_sbdy.onlineStateHandle !== 'undefined')
        {
            basedatamanage_eqmanage_eqconfiguration_sbdy.onlineStateHandle(data);
        }

    }

    function RemoteUpgrade(data) {
        toastr.warning(data);
    }


}(jQuery));
