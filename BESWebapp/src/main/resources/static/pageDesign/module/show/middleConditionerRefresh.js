/**
 * 中档温控器实时刷新
 */
var middleConditionerRefresh = {};
layui.use(['layer','form'], function(){
    var layer = layui.layer;
    var form = layui.form;


    /**
     * 当前弹窗展示的温控器的回调方法
     */
    middleConditionerRefresh.iconConditionerRegister = function(){
        var $conditioner = $("#middle_conditioner_win").find(".design_middle_conditioner.icon_win_img");
        var sysnameStr = $conditioner.attr("data-sysname");
        if(sysnameStr){
            var array = sysnameStr.split(",");
            for(var j = 0; j < array.length; j++){
                if(array[j]){
                    parent.PubSub.subscribe( array[j],middleConditionerRefresh.listConditionerCallback, 'listConditionerCallback');
                }
            }
        }
    }

    /**
     * 中档温控器--订阅事件
     */
    middleConditionerRefresh.listConditionerRegister = function(){

        var $conditioner = $("#design_area_demo").find(".design_middle_conditioner.list_img");
        for(var i = 0; i < $conditioner.length; i++){
            var sysnameStr = $($conditioner[i]).attr("data-sysname");
            if(sysnameStr){
                var array = sysnameStr.split(",");
                for(var j = 0; j < array.length; j++){
                    if(array[j]){
                        parent.PubSub.subscribe( array[j],middleConditionerRefresh.listConditionerCallback, 'listConditionerCallback');
                    }
                }

            }
        }
        // middleConditionerRefresh.iconStateRegister();
    }

    // /**
    //  * 图标温控器--图标状态回调事件注册
    //  */
    // middleConditionerRefresh.iconStateRegister  =function(){
    //     var $conditioner = $("#design_area_demo").find(".design_middle_conditioner.icon_img");
    //     for(var i = 0; i < $conditioner.length; i++){
    //         var sysname = $($conditioner[i]).attr("data-switch");
    //         if(sysname){
    //             parent.PubSub.subscribe( sysname,middleConditionerRefresh.iconStateCallback);
    //         }
    //     }
    // }
    //
    // /**
    //  * 图标温控器--图标状态回调事件
    //  */
    // middleConditionerRefresh.iconStateCallback = function(data){
    //     if(data){
    //         var name = data.name;
    //         var f_init_val = data.value;
    //         if(name.slice(-2) === "00"){
    //             middleConditionerRefresh.iconStateRefresh(name,f_init_val);
    //         }
    //     }
    // }

    /**
     * 图标温控器--图标状态刷新
     */
    middleConditionerRefresh.iconStateRefresh = function(name,f_init_val){
        var $conditioner = $(".design_middle_conditioner.icon_img");
        for(var i = 0; i < $conditioner.length; i++){
            var dataId = $($conditioner[i]).attr("data-switch");
            if(dataId === name){
                showConditioner.iconSwitchToggle($($conditioner[i]),f_init_val+"");
            }
        }
    }


    /**
     *中档温控器--回调函数
     */
    middleConditionerRefresh.listConditionerCallback = function(data){
        if(data){
            var name = data.name;
            var f_init_val = data.value;
            if(name.slice(-2) === "00"){
                middleConditionerRefresh.switchRefresh(name,f_init_val);
            }
            if(name.slice(-2) === "01"){
                middleConditionerRefresh.modeRefresh(name,f_init_val);
            }
            if(name.slice(-2) === "02"){
                middleConditionerRefresh.speedRefresh(name,f_init_val);
            }
            if(name.slice(-2) === "03"){
                middleConditionerRefresh.lockRefresh(name,f_init_val);
            }
            if(name.slice(-2) === "04"){
                middleConditionerRefresh.indoorNumRefresh(name,f_init_val);
            }
            if(name.slice(-2) === "07"){
                middleConditionerRefresh.setNumRefresh(name,f_init_val);
            }
            if(name.slice(-2) === "09"){
                middleConditionerRefresh.energyNumRefresh(name,f_init_val);
            }
            if(name.slice(-2) === "10"){
                middleConditionerRefresh.timingOpenRefresh(name,f_init_val);
            }
            if(name.slice(-2) === "11"){
                middleConditionerRefresh.timingCloseRefresh(name,f_init_val);
            }
        }
    }

    /**
     * 刷新开关数据
     * @param name
     * @param f_init_val
     */
    middleConditionerRefresh.switchRefresh = function(name,f_init_val){
        //图标温控器回调
        middleConditionerRefresh.iconStateRefresh(name,f_init_val);
        var $sysname = $(".design_middle_conditioner").find(".switchSysname");
            for(var i = 0; i < $sysname.length; i++){
            var dataId = $($sysname[i]).val();
            if(dataId === name){
                var $conditioner = $($sysname[i]).parents(".design_middle_conditioner");
                showMiddleConditioner.initSwitch($conditioner,f_init_val+"");
            }
        }
    }

    /**
     * 刷新模式数据
     * @param name
     * @param f_init_val
     */
    middleConditionerRefresh.modeRefresh = function(name,f_init_val){
        var $sysname = $(".design_middle_conditioner").find(".modeSysname");
        for(var i = 0; i < $sysname.length; i++){
            var dataId = $($sysname[i]).val();
            if(dataId === name){
                var $conditioner = $($sysname[i]).parents(".design_middle_conditioner");
                showMiddleConditioner.initMode($conditioner,f_init_val+"");
            }
        }
    }

    /**
     * 刷新风速数据
     * @param name
     * @param f_init_val
     */
    middleConditionerRefresh.speedRefresh = function(name,f_init_val){
        var $sysname = $(".design_middle_conditioner").find(".speedSysname");
        for(var i = 0; i < $sysname.length; i++){
            var dataId = $($sysname[i]).val();
            if(dataId === name){
                var $conditioner = $($sysname[i]).parents(".design_middle_conditioner");
                showMiddleConditioner.initSpeed($conditioner,f_init_val+"");
            }
        }
    }

    /**
     * 锁定状态回调
     */
    middleConditionerRefresh.lockRefresh = function(name,f_init_val){
        var $sysname = $(".design_middle_conditioner").find(".lockSysname");
        for(var i = 0; i < $sysname.length; i++){
            var dataId = $($sysname[i]).val();
            if(dataId === name){
                var $conditioner = $($sysname[i]).parents(".design_middle_conditioner");
                showMiddleConditioner.initLock($conditioner,f_init_val+"");
            }
        }
    }

    /**
     * 刷新室内温度数据
     * @param name
     * @param f_init_val
     */
    middleConditionerRefresh.indoorNumRefresh = function(name,f_init_val){
        var $sysname = $(".design_middle_conditioner").find(".indoorTemperatureSysname");
        for(var i = 0; i < $sysname.length; i++){
            var dataId = $($sysname[i]).val();
            if(dataId === name){
                var $conditioner = $($sysname[i]).parents(".design_middle_conditioner");
                showMiddleConditioner.initIndoorNum($conditioner,f_init_val+"");
            }
        }
    }

    /**
     * 刷新设定温度数据
     * @param name
     * @param f_init_val
     */
    middleConditionerRefresh.setNumRefresh = function(name,f_init_val){
        var $sysname = $(".design_middle_conditioner").find(".setTemperatureSysname");
        for(var i = 0; i < $sysname.length; i++){
            var dataId = $($sysname[i]).val();
            if(dataId === name){
                var $conditioner = $($sysname[i]).parents(".design_middle_conditioner");
                showMiddleConditioner.initSetNum($conditioner,f_init_val+"");
            }
        }
    }


    /**
     * 刷新能耗计量数据
     * @param name
     * @param f_init_val
     */
    middleConditionerRefresh.energyNumRefresh = function(name,f_init_val){
        var $sysname = $(".design_middle_conditioner").find(".energySysname");
        for(var i = 0; i < $sysname.length; i++){
            var dataId = $($sysname[i]).val();
            if(dataId === name){
                var $conditioner = $($sysname[i]).parents(".design_middle_conditioner");
                showMiddleConditioner.initEnergyNum($conditioner,f_init_val);
            }
        }
    }


    /**
     * 定时开数据刷新
     * @param name
     * @param f_init_val
     */
    middleConditionerRefresh.timingOpenRefresh = function(name,f_init_val){
        var $sysname = $(".design_middle_conditioner").find(".open_time");
        for(var i = 0; i < $sysname.length; i++){
            var dataId = $($sysname[i]).val();
            if(dataId === name){
                var $conditioner = $($sysname[i]).parents(".design_middle_conditioner");
                showMiddleConditioner.initOpenTime($conditioner,f_init_val+"");
                //是否显示时钟图标
                var close_time = showMiddleConditioner.getLowerFormat($conditioner,".timing_close_num");
                var closeNum = Number(close_time);
                var openNum = Number(f_init_val);
                showMiddleConditioner.initClock($conditioner,openNum,closeNum);
            }
        }
    }


    /**
     * 定时关数据刷新
     * @param name
     * @param f_init_val
     */
    middleConditionerRefresh.timingCloseRefresh = function(name,f_init_val){
        var $sysname = $(".design_middle_conditioner").find(".close_time");
        for(var i = 0; i < $sysname.length; i++){
            var dataId = $($sysname[i]).val();
            if(dataId === name){
                var $conditioner = $($sysname[i]).parents(".design_middle_conditioner");
                showMiddleConditioner.initCloseTime($conditioner,f_init_val+"");
                //是否显示时钟图标
                var open_time = showMiddleConditioner.getLowerFormat($conditioner,".timing_open_num");
                var openNum = Number(open_time);
                var closeNum = Number(f_init_val);
                showMiddleConditioner.initClock($conditioner,openNum,closeNum);
            }
        }
    }

    /**
     * 通信状态回调
     */
    middleConditionerRefresh.networkRefresh = function(data){
        if(data && data.length > 0){
            var moduleName = "";
            var network_value = "";
            for(var i = 0; i < data.length; i++){
                var type = data[i].f_type;
                if(type !== "9"){
                    continue;
                }
                //模块
                moduleName = data[i].f_sys_name;
                network_value = data[i].f_status;
            }
            var $conditioner = $("#design_area_demo").find(".design_middle_conditioner[data-id='"+moduleName+"']");
            if($conditioner){
                showConditioner.initNetwork($conditioner,network_value);
            }

        }
    }
});


