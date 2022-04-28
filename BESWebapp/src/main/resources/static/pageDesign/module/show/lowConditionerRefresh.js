/**
 * 低档温控器实时刷新
 */
var lowConditionerRefresh = {};
layui.use(['layer','form'], function(){
    var layer = layui.layer;
    var form = layui.form;


    /**
     * 当前弹窗展示的温控器的回调方法
     */
    lowConditionerRefresh.iconConditionerRegister = function(){
        var $conditioner = $("#conditioner_win").find(".design_low_conditioner.icon_win_img");
        var sysnameStr = $conditioner.attr("data-sysname");
        if(sysnameStr){
            var array = sysnameStr.split(",");
            for(var j = 0; j < array.length; j++){
                if(array[j]){
                    parent.PubSub.subscribe( array[j],lowConditionerRefresh.lowConditionerCallback, 'lowConditionerCallback');
                }
            }
        }
    }

    /**
     * 低档温控器--订阅事件
     */
    lowConditionerRefresh.lowConditionerRegister = function(){

        var $conditioner = $("#design_area_demo").find(".design_low_conditioner.list_img");
        for(var i = 0; i < $conditioner.length; i++){
            var sysnameStr = $($conditioner[i]).attr("data-sysname");
            if(sysnameStr){
                var array = sysnameStr.split(",");
                for(var j = 0; j < array.length; j++){
                    if(array[j]){
                        parent.PubSub.subscribe( array[j],lowConditionerRefresh.lowConditionerCallback, 'lowConditionerCallback');
                    }
                }

            }
        }
        // lowConditionerRefresh.iconStateRegister();
    }


    // /**
    //  * 图标温控器--图标状态回调事件注册
    //  */
    // lowConditionerRefresh.iconStateRegister  =function(){
    //     var $conditioner = $("#design_area_demo").find(".design_low_conditioner.icon_img");
    //     for(var i = 0; i < $conditioner.length; i++){
    //         var sysname = $($conditioner[i]).attr("data-switch");
    //         if(sysname){
    //             parent.PubSub.subscribe( sysname,middleConditionerRefresh.iconStateCallback);
    //         }
    //     }
    // }

    // /**
    //  * 图标温控器--图标状态回调事件
    //  */
    // lowConditionerRefresh.iconStateCallback = function(data){
    //     if(data){
    //         var name = data.name;
    //         var f_init_val = data.value;
    //         if(name.slice(-2) === "00"){
    //             lowConditionerRefresh.iconStateRefresh(name,f_init_val);
    //         }
    //     }
    // }

    /**
     * 图标温控器--图标状态刷新
     */
    lowConditionerRefresh.iconStateRefresh = function(name,f_init_val){
        var $conditioner = $(".design_low_conditioner.icon_img");
        for(var i = 0; i < $conditioner.length; i++){
            var dataId = $($conditioner[i]).attr("data-switch");
            if(dataId === name){
                showConditioner.iconSwitchToggle($($conditioner[i]),f_init_val+"");
            }
        }
    }
    /**
     *低档温控器--回调函数
     */
    lowConditionerRefresh.lowConditionerCallback = function(data){
        if(data){
            var name = data.name;
            var f_init_val = data.value;
            if(name.slice(-2) === "00"){
                lowConditionerRefresh.switchRefresh(name,f_init_val);
            }
            if(name.slice(-2) === "01"){
                lowConditionerRefresh.modeRefresh(name,f_init_val);
            }
            if(name.slice(-2) === "02"){
                lowConditionerRefresh.speedRefresh(name,f_init_val);
            }
            if(name.slice(-2) === "03"){
                lowConditionerRefresh.lockRefresh(name,f_init_val);
            }
            if(name.slice(-2) === "04"){
                lowConditionerRefresh.indoorNumRefresh(name,f_init_val);
            }
            if(name.slice(-2) === "07"){
                lowConditionerRefresh.setNumRefresh(name,f_init_val);
            }
            if(name.slice(-2) === "10"){
                lowConditionerRefresh.timingOpenRefresh(name,f_init_val);
            }
            if(name.slice(-2) === "11"){
                lowConditionerRefresh.timingCloseRefresh(name,f_init_val);
            }
        }
    }

    /**
     * 刷新开关数据
     * @param name
     * @param f_init_val
     */
    lowConditionerRefresh.switchRefresh = function(name,f_init_val){
        //图标温控器回调
        lowConditionerRefresh.iconStateRefresh(name,f_init_val);
        var $sysname = $(".design_low_conditioner").find(".switchSysname");
        for(var i = 0; i < $sysname.length; i++){
            var dataId = $($sysname[i]).val();
            if(dataId === name){
                var $conditioner = $($sysname[i]).parents(".design_low_conditioner");
                showConditioner.initSwitch($conditioner,f_init_val+"");
            }
        }
    }

    /**
     * 刷新模式数据
     * @param name
     * @param f_init_val
     */
    lowConditionerRefresh.modeRefresh = function(name,f_init_val){
        var $sysname = $(".design_low_conditioner").find(".modeSysname");
        for(var i = 0; i < $sysname.length; i++){
            var dataId = $($sysname[i]).val();
            if(dataId === name){
                var $conditioner = $($sysname[i]).parents(".design_low_conditioner");
                showConditioner.initMode($conditioner,f_init_val+"");
            }
        }
    }

    /**
     * 刷新风速数据
     * @param name
     * @param f_init_val
     */
    lowConditionerRefresh.speedRefresh = function(name,f_init_val){
        var $sysname = $(".design_low_conditioner").find(".speedSysname");
        for(var i = 0; i < $sysname.length; i++){
            var dataId = $($sysname[i]).val();
            if(dataId === name){
                var $conditioner = $($sysname[i]).parents(".design_low_conditioner");
                showConditioner.initSpeed($conditioner,f_init_val+"");
            }
        }
    }


    /**
     * 锁定状态回调
     */
    lowConditionerRefresh.lockRefresh = function(name,f_init_val){
        var $sysname = $(".design_low_conditioner").find(".lockSysname");
        for(var i = 0; i < $sysname.length; i++){
            var dataId = $($sysname[i]).val();
            if(dataId === name){
                var $conditioner = $($sysname[i]).parents(".design_low_conditioner");
                showConditioner.initLock($conditioner,f_init_val+"");
            }
        }
    }

    /**
     * 刷新室内温度数据
     * @param name
     * @param f_init_val
     */
    lowConditionerRefresh.indoorNumRefresh = function(name,f_init_val){
        var $sysname = $(".design_low_conditioner").find(".indoorTemperatureSysname");
        for(var i = 0; i < $sysname.length; i++){
            var dataId = $($sysname[i]).val();
            if(dataId === name){
                var $conditioner = $($sysname[i]).parents(".design_low_conditioner");
                showConditioner.initIndoorNum($conditioner,f_init_val+"");
            }
        }
    }

    /**
     * 刷新设定温度数据
     * @param name
     * @param f_init_val
     */
    lowConditionerRefresh.setNumRefresh = function(name,f_init_val){
        var $sysname = $(".design_low_conditioner").find(".setTemperatureSysname");
        for(var i = 0; i < $sysname.length; i++){
            var dataId = $($sysname[i]).val();
            if(dataId === name){
                var $conditioner = $($sysname[i]).parents(".design_low_conditioner");
                showConditioner.initSetNum($conditioner,f_init_val+"");
            }
        }
    }


    /**
     * 定时开数据刷新
     * @param name
     * @param f_init_val
     */
    lowConditionerRefresh.timingOpenRefresh = function(name,f_init_val){
        var $sysname = $(".design_low_conditioner").find(".open_time");
        for(var i = 0; i < $sysname.length; i++){
            var dataId = $($sysname[i]).val();
            if(dataId === name){
                var $conditioner = $($sysname[i]).parents(".design_low_conditioner");
                showConditioner.initOpenTime($conditioner,f_init_val+"");
            }
        }
    }


    /**
     * 定时关数据刷新
     * @param name
     * @param f_init_val
     */
    lowConditionerRefresh.timingCloseRefresh = function(name,f_init_val){
        var $sysname = $(".design_low_conditioner").find(".close_time");
        for(var i = 0; i < $sysname.length; i++){
            var dataId = $($sysname[i]).val();
            if(dataId === name){
                var $conditioner = $($sysname[i]).parents(".design_low_conditioner");
                showConditioner.initCloseTime($conditioner,f_init_val+"");
            }
        }
    }

    /**
     * 通信状态回调
     */
    lowConditionerRefresh.networkRefresh = function(data){
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
            var $conditioner = $("#design_area_demo").find(".design_low_conditioner[data-id='"+moduleName+"']");
            if($conditioner){
                showConditioner.initNetwork($conditioner,network_value);
            }

        }
    }
});


