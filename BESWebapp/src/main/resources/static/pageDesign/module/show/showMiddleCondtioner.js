/**
 * 中档温控器
 */
var showMiddleConditioner = {};
layui.use(['layer','form'], function(){
    var layer = layui.layer;
    var currentTimer;
    //存储模块对应的定时开关时间，用于比较，是否展示定时图标
    var timeValueJson = {};
    //存储模块对应的定时器，方便清空当前时间的定时器，进行温控器定时操作。
    var timingJson = {};
    //时间闪烁定时器
    var blinkInterval = "";
    //设定温度切换室内温度定时器
    var tempTimers = {};
    //定时状态切换成当前时间定时器
    var backTimers = {};
    //模式按键单击延时定时器
    var modeTiming = "";
    //风速按键单机延时定时器
    var speedTiming = "";
    //图标形式弹窗
    var index;


    /**
     * 当前时间定时器
     * @param $dom
     * @param moduleName
     */
    function timeChange($dom,moduleName){
        clearInterval(currentTimer);
        currentTimer = setInterval(function(){
            var time = CommonMethod.getCurrentTime();
            $dom.text(time);
        },1000);
    }

    /**
     * 加载图标和列表两种形式的温控器
     */
    showMiddleConditioner.initAll = function(){
        showMiddleConditioner.initIconConditioner();
        showMiddleConditioner.initListConditioner();
    }

    /**
     * 加载图标格式的温控器
     */
    showMiddleConditioner.initIconConditioner = function(){
        var $conditioners = $("#design_area_demo .design_middle_conditioner.icon_img");
        $conditioners.unbind("click").click(function(){
            var $conditioner = $(this);
            var dataId = $conditioner.attr("data-id");
            if(!dataId){
                layer.msg("请关联模块");
                return;
            }
            index = layer.open({
                type: 1,
                title:"",
                area:['300px','300px'],
                content: $('#middle_conditioner_win'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                cancel: function(index, layero){
                    // DebugBtn.closeAddDebugWin();
                    // return false;
                },
                success:function(){
                    $('#middle_conditioner_win .design_middle_conditioner').html($("#middle_conditioner_content").html());
                    showMiddleConditioner.getConditionerData($conditioner);
                }
            });


        });
    }

    /**
     * 未关联模块的提示信息
     */
    showMiddleConditioner.relativeTip = function($conditioner){
        $conditioner.unbind("click").click(function(){
            var dataId = $conditioner.attr("data-id");
            if(!dataId){
                layer.msg("请关联模块");
            }
        });
    }

    /**
     * 查询获取单个温控器数据
     */
    showMiddleConditioner.getConditionerData = function($conditioner){
        var idArray = [];
        var dataId = $conditioner.attr("data-id");
        if(dataId){
            idArray.push(dataId);
        }
        if(idArray.length > 0){
            $.ajax({
                url     : _ctx + '/btnEventController/queryModuleListChildInfo',
                type    : "post",
                data: JSON.stringify(idArray),
                async: false,
                contentType: 'application/json;charset=UTF-8',
                success : function(result) {
                    if (result && result.map) {
                        var moduleMap = result.map;
                        var childArray = moduleMap[dataId];
                        var $dom = $("#middle_conditioner_win .design_middle_conditioner");
                        $dom.attr("data-id",dataId);
                        showMiddleConditioner.initOneConditioner(childArray,$dom);
                        //注册回调事件
                        middleConditionerRefresh.iconConditionerRegister();
                    }
                }
            });
        }
    }

    /**
     * 加载列表温控器的数据
     */
    showMiddleConditioner.initListConditioner = function(){

        var $conditioner = $(".design_middle_conditioner.list_img");
        $conditioner.html($("#middle_conditioner_content").html());
        var idArray = [];
        for(var i = 0; i < $conditioner.length; i++){
            var dataId = $($conditioner[i]).attr("data-id");
            if(dataId){
                idArray.push(dataId);
            }else{
                $($conditioner[i]).find(".screenDiv").hide();
                showMiddleConditioner.relativeTip($($conditioner[i]));
            }
        }
        var $iconConditioners = $(".design_middle_conditioner.icon_img");
        for( i = 0; i < $iconConditioners.length; i++){
            dataId = $($iconConditioners[i]).attr("data-id");
            if(dataId){
                idArray.push(dataId);
            }
        }
        if(idArray.length > 0){
            $.ajax({
                url     : _ctx + '/btnEventController/queryModuleListChildInfo',
                type    : "post",
                data: JSON.stringify(idArray),
                async: false,
                contentType: 'application/json;charset=UTF-8',
                success : function(result) {
                    if(result && result.map){
                        var moduleMap = result.map;
                        for(i = 0; i < $conditioner.length; i++){
                            var moduleName = $($conditioner[i]).attr("data-id");
                            if(moduleName){
                                var childArray = moduleMap[moduleName];
                                showMiddleConditioner.initOneConditioner(childArray,$($conditioner[i]));
                            }

                        }
                        for(i = 0; i < $iconConditioners.length; i++){
                            moduleName = $($iconConditioners[i]).attr("data-id");
                            if(moduleName){
                                childArray = moduleMap[moduleName];
                                showConditioner.initIconState(childArray,$($iconConditioners[i]));
                            }
                        }
                    }
                },
                error:function(){

                }
            });
        }
    }

    /**
     * 加载温控器数据
     */
    showMiddleConditioner.initOneConditioner = function(childArray,$conditioner){
        var moduleName = $conditioner.attr("data-id");
        var sysnameStr = "";
        /**
         * 00开关  01模式   02风速 03锁定   04室内温度  07设定温度 09能耗计量 10定时开  11定时关
         */
        for(var j = 0; j < childArray.length; j++){
            var f_sys_name = childArray[j].F_SYS_NAME;
            if(f_sys_name.slice(-2) === "00"){
                //开关
                sysnameStr += f_sys_name+",";
                $conditioner.find(".switchSysname").val(f_sys_name);
                // showMiddleConditioner.switchToggleEvent($conditioner,f_sys_name);
                var switch_value = childArray[j].F_INIT_VAL +"";
                if(switch_value === "255"){
                    //开机
                    showMiddleConditioner.switchOpenState($conditioner);
                }else{
                    //关机或其他
                    showMiddleConditioner.switchCloseState($conditioner);
                }
            }
            if(f_sys_name.slice(-2) === "01"){
                //模式
                sysnameStr += f_sys_name+",";
                $conditioner.find(".modeSysname").val(f_sys_name);
                // showMiddleConditioner.modeChangeEvent($conditioner,f_sys_name);
                var mode_value = childArray[j].F_INIT_VAL +"";
                //加载模式状态
                showMiddleConditioner.initMode($conditioner,mode_value);
            }
            if(f_sys_name.slice(-2) === "02"){
                //风速
                sysnameStr += f_sys_name+",";
                $conditioner.find(".speedSysname").val(f_sys_name);
                // showMiddleConditioner.speedChangeEvent($conditioner,f_sys_name);
                var speed_value = Number(childArray[j].F_INIT_VAL);
                //加载风速状态
                showMiddleConditioner.initSpeed($conditioner,speed_value);

            }
            if(f_sys_name.slice(-2) === "03"){
                //锁定
                sysnameStr += f_sys_name+",";
                $conditioner.find(".lockSysname").val(f_sys_name);

                var lock_value = Number(childArray[j].F_INIT_VAL);
                showMiddleConditioner.initLock($conditioner,lock_value);
            }
            if(f_sys_name.slice(-2) === "04"){
                //室内温度
                sysnameStr += f_sys_name+",";
                $conditioner.find(".indoorTemperatureSysname").val(f_sys_name);
                var indoor_value = childArray[j].F_INIT_VAL;
                //加载室内温度数据
                showMiddleConditioner.initIndoorNum($conditioner,indoor_value);
            }
            if(f_sys_name.slice(-2) === "07"){
                //设定温度
                sysnameStr += f_sys_name+",";
                $conditioner.find(".setTemperatureSysname").val(f_sys_name);
                // showMiddleConditioner.setTemperatureUp($conditioner,f_sys_name);
                // showMiddleConditioner.setTemperatureDown($conditioner,f_sys_name);
                var set_value = childArray[j].F_INIT_VAL;
                //加载设定温度数据
                showMiddleConditioner.initSetNum($conditioner,set_value);
            }
            if(f_sys_name.slice(-2) === "09"){
                //能耗计量
                sysnameStr += f_sys_name+",";
                $conditioner.find(".energySysname").val(f_sys_name);
                var enery_value =  childArray[j].F_INIT_VAL;
                showMiddleConditioner.initEnergyNum($conditioner,enery_value);
            }
            if(f_sys_name.slice(-2) === "10"){
                //定时开
                sysnameStr += f_sys_name+",";
                $conditioner.find(".open_time").val(f_sys_name);
                var open_time = Number(childArray[j].F_INIT_VAL);
                showMiddleConditioner.initOpenTime($conditioner,open_time);
                // //用于比较定时开关时间
                // timeValueJson[moduleName]['open'] = open_time;
            }
            if(f_sys_name.slice(-2) === "11"){
                //定时关
                sysnameStr += f_sys_name+",";
                $conditioner.find(".close_time").val(f_sys_name);
                var close_time = Number(childArray[j].F_INIT_VAL);
                showMiddleConditioner.initCloseTime($conditioner,close_time);
                // //用于比较定时开关时间
                // timeValueJson[moduleName]['close'] = close_time;
            }
        }
        //比较定时开关时间，是否展示时钟图标
        showMiddleConditioner.initClock($conditioner,open_time,close_time);

        if(childArray.length > 0){
            //在线状态
            var onlineState = childArray[0].F_STATUS +"";
            showMiddleConditioner.initNetwork($conditioner,onlineState);
        }
        if(sysnameStr.length > 1){
            sysnameStr = sysnameStr.substring(0,sysnameStr.length-1);
        }
        $conditioner.attr("data-sysname",sysnameStr);
        //当前时间
        showMiddleConditioner.initTime($conditioner);
        //按键点击事件
        showMiddleConditioner.btnClickEvent($conditioner);
    }

    /**
     * 比较定时开关时间，是否展示时钟图标
     */
    showMiddleConditioner.initClock = function($conditioner,open_time,close_time){
        if(open_time + "" && close_time +""){
            if(open_time === close_time){
                $conditioner.find(".clock").hide();
            }else{
                $conditioner.find(".clock").show();
            }
        }else{
            $conditioner.find(".clock").hide();
        }
    }

    /**
     * 加载开关状态
     * @param $conditioner
     * @param switch_value
     */
    showMiddleConditioner.initSwitch = function($conditioner,switch_value){
        if(switch_value === "0"){
            //关机
            showMiddleConditioner.switchCloseState($conditioner);
        }else if(switch_value === "255"){
            //开机
            showMiddleConditioner.switchOpenState($conditioner);
        }
    }

    /**
     * 关机状态
     */
    showMiddleConditioner.switchCloseState = function($conditioner){
        $conditioner.find(".topDiv,.middleDiv").hide();
        $conditioner.find(".switchBtn").show();
        //除了开关键其他按键隐藏
        $conditioner.find(".modeBtn").hide();
        $conditioner.find(".speedBtn").hide();
        $conditioner.find(".upBtn").hide();
        $conditioner.find(".downBtn").hide();

    }

    /**
     * 开机状态
     */
    showMiddleConditioner.switchOpenState = function($conditioner){
        $conditioner.find(".topDiv,.middleDiv").show();
        $conditioner.find(".switchBtn").show();
        //除了开关键的其他按键显示
        $conditioner.find(".modeBtn").show();
        $conditioner.find(".speedBtn").show();
        $conditioner.find(".upBtn").show();
        $conditioner.find(".downBtn").show();

    }
    /**
     * 加载模式状态
     * @param $conditioner
     * @param mode_value
     */
    showMiddleConditioner.initMode = function($conditioner,mode_value){
        if(mode_value === "255"){
            //制冷
            $conditioner.find(".cooling").show();
            $conditioner.find(".heating").hide();
        }else if(mode_value === "0"){
            //制热
            $conditioner.find(".cooling").hide();
            $conditioner.find(".heating").show();
        }
    }

    /**
     * 加载风速状态
     */
    showMiddleConditioner.initSpeed = function($conditioner,speed_value){
        if(speed_value + ""){
            speed_value = Number(speed_value);
            if(speed_value === 1){
                //低速
                $conditioner.find(".high_speed").hide();
                $conditioner.find(".middle_speed").hide();
                $conditioner.find(".low_speed").show();
                $conditioner.find(".energy").hide();
                $conditioner.find(".auto").hide();
                //图标
                $conditioner.find(".low_icon").show();
                $conditioner.find(".middle_icon").hide();
                $conditioner.find(".high_icon").hide();
                //手动
                $conditioner.find(".manual").show();
            }else if(speed_value === 2){
                //中速
                $conditioner.find(".high_speed").hide();
                $conditioner.find(".middle_speed").show();
                $conditioner.find(".low_speed").hide();
                $conditioner.find(".energy").hide();
                $conditioner.find(".auto").hide();
                //图标
                $conditioner.find(".low_icon").hide();
                $conditioner.find(".middle_icon").show();
                $conditioner.find(".high_icon").hide();
                //手动
                $conditioner.find(".manual").show();
            }else if(speed_value === 3){
                //高速
                $conditioner.find(".high_speed").show();
                $conditioner.find(".middle_speed").hide();
                $conditioner.find(".low_speed").hide();
                $conditioner.find(".energy").hide();
                $conditioner.find(".auto").hide();
                //图标
                $conditioner.find(".low_icon").hide();
                $conditioner.find(".middle_icon").hide();
                $conditioner.find(".high_icon").show();
                //手动
                $conditioner.find(".manual").show();
            }else if(speed_value === 4){
                //节能
                $conditioner.find(".high_speed").hide();
                $conditioner.find(".middle_speed").hide();
                $conditioner.find(".low_speed").hide();
                $conditioner.find(".energy").show();
                $conditioner.find(".auto").show();
                //图标
                $conditioner.find(".low_icon").hide();
                $conditioner.find(".middle_icon").hide();
                $conditioner.find(".high_icon").show();
                //手动
                $conditioner.find(".manual").hide();
            }else{
                showMiddleConditioner.hideSpeed($conditioner);
            }
        }else{
            showMiddleConditioner.hideSpeed($conditioner);
        }
    }

    /**
     * 隐藏风速相关图标
     */
    showMiddleConditioner.hideSpeed = function($conditioner){
        //没有数据
        $conditioner.find(".high_speed").hide();
        $conditioner.find(".middle_speed").hide();
        $conditioner.find(".low_speed").hide();
        $conditioner.find(".energy").hide();
        $conditioner.find(".auto").hide();
        //图标
        $conditioner.find(".low_icon").hide();
        $conditioner.find(".middle_icon").hide();
        $conditioner.find(".high_icon").hide();
        //手动
        $conditioner.find(".manual").hide();
    }

    /**
     * 加载锁定状态
     */
    showMiddleConditioner.initLock = function($conditioner,lock_value){
        if(lock_value != null && lock_value !== ""){
            lock_value = Number(lock_value);
            if(lock_value === 0){
                //不锁定
                $conditioner.find(".unlock").show();
                $conditioner.find(".lock").hide();
                // $conditioner.find(".modeBtn").removeClass("notCursor");
                // $conditioner.find(".speedBtn").removeClass("notCursor");
                // $conditioner.find(".switchBtn").removeClass("notCursor");
                // $conditioner.find(".upBtn").removeClass("notCursor");
                // $conditioner.find(".downBtn").removeClass("notCursor");
            }else if(lock_value === 1){
                //部分锁定，设定温度不可调整【上下按键不可用】
                $conditioner.find(".unlock").hide();
                $conditioner.find(".lock").show();

                // $conditioner.find(".modeBtn").removeClass("notCursor");
                // $conditioner.find(".speedBtn").removeClass("notCursor");
                // $conditioner.find(".switchBtn").removeClass("notCursor");
                // $conditioner.find(".upBtn").addClass("notCursor");
                // $conditioner.find(".downBtn").addClass("notCursor");
            }else if(lock_value === 2){
                //全部锁定
                $conditioner.find(".unlock").hide();
                $conditioner.find(".lock").show();

                // $conditioner.find(".modeBtn").addClass("notCursor");
                // $conditioner.find(".speedBtn").addClass("notCursor");
                // $conditioner.find(".switchBtn").addClass("notCursor");
                // $conditioner.find(".upBtn").addClass("notCursor");
                // $conditioner.find(".downBtn").addClass("notCursor");
            }
        }
    }

    /**
     * 加载室内温度数据
     */
    showMiddleConditioner.initIndoorNum = function($conditioner,indoor_value){
        var indoor_num = Number(indoor_value).toFixed(1);
        var array = indoor_num.split(".");
        var integer = array[0];
        var decimal = "." + array[1];
        $conditioner.find(".indoor_integer").text(integer);
        $conditioner.find(".indoor_decimal").text(decimal);
    }

    /**
     * 加载设定温度数据
     */
    showMiddleConditioner.initSetNum = function($conditioner,set_value){
        var set_num = Number(set_value).toFixed(1);
        var array = set_num.split(".");
        var integer = array[0];
        var decimal = "." + array[1];
        $conditioner.find(".set_integer").text(integer);
        $conditioner.find(".set_decimal").text(decimal);
    }

    /**
     * 加载能耗计量数据
     */
    showMiddleConditioner.initEnergyNum = function($conditioner,energy_value){
        var energy_num = Number(energy_value).toFixed(0);
        var num = CommonMethod.prefixZero(energy_num,4);
        $conditioner.find(".energy_num.ahead").text(num);
    }

    /**
     * 加载网络状态
     */
    showMiddleConditioner.initNetwork = function($conditioner,onlineState){
        if(onlineState === "1"){
            //在线
            $conditioner.find(".network").show();
        }else{
            $conditioner.find(".network").hide();
        }
    }

    /**
     * 加载定时开时间
     */
    showMiddleConditioner.initOpenTime = function($conditioner,open_time){
        showMiddleConditioner.transformLowerTime($conditioner,".timing_open_num",open_time);
    }

    /**
     * 加载定时关时间
     */
    showMiddleConditioner.initCloseTime = function($conditioner,close_time){
        showMiddleConditioner.transformLowerTime($conditioner,".timing_close_num",close_time);
    }

    /**
     * 加载时间
     */
    showMiddleConditioner.initTime = function($conditioner){
        var moduleName = $conditioner.attr("data-id");
        var $dom = $conditioner.find(".current_time");
        $dom.text(CommonMethod.getCurrentTime());
        timeChange($dom,moduleName);
    }

    /**
     * 4个按键的点击事件
     */
    showMiddleConditioner.btnClickEvent = function($conditioner){

        //开关机切换
        showMiddleConditioner.switchToggleEvent($conditioner);
        //模式切换
        showMiddleConditioner.modeChangeEvent($conditioner);
        //风速切换
        showMiddleConditioner.speedChangeEvent($conditioner);

        //锁定状态切换
        showMiddleConditioner.lockChangeEvent($conditioner);
        showMiddleConditioner.unlockChangeEvent($conditioner);

        //设定温度调整
        showMiddleConditioner.setTemperatureUp($conditioner);
        showMiddleConditioner.setTemperatureDown($conditioner);

        //模式双击切换定时
        showMiddleConditioner.modeDoubleEvent($conditioner);
        //风速双击切换能耗
        showMiddleConditioner.speedDoubleEvent($conditioner);
    }

    showMiddleConditioner.successCallback = function(result){
        if(result && result.status){
            if(result.status === '0'){
                if(result.msg){
                    layer.msg(result.msg);
                }else{
                    layer.msg("执行失败");
                }
            }else{
                if(result.msg){
                    layer.msg(result.msg);
                }else{
                    layer.msg("执行成功");
                }
            }
        }
    }


    /**
     * 开关切换事件
     * @param $conditioner
     */
    showMiddleConditioner.switchToggleEvent = function($conditioner){
        var $btn = $conditioner.find(".switchBtn");
        var f_sys_name = $conditioner.find(".switchSysname").val();
        $btn.unbind("click").click(function(){
            $.ajax({
                url : _ctx + "/btnEventController/switchToggle",
                type : "post",
                dataType : "json",
                data : {
                    sysname : f_sys_name
                },
                success : function(result) {
                    showMiddleConditioner.successCallback(result);
                },error:function(){

                }
            });
        });
    }
    /**
     * 模式切换事件
     * @param $conditioner
     */
    showMiddleConditioner.modeChangeEvent = function($conditioner){
        var $btn = $conditioner.find(".modeBtn");
        var f_sys_name = $conditioner.find(".modeSysname").val();

        $btn.unbind("click").click(function(){
            // 取消上次延时未执行的方法
            if(modeTiming){
                clearTimeout(modeTiming);
            }


            //执行延时
            modeTiming = setTimeout(function() {
                //此处为单击事件要执行的代码
                $.ajax({
                    url : _ctx + "/btnEventController/lowConditionerModeToggle",
                    type : "post",
                    dataType : "json",
                    data : {
                        sysname : f_sys_name
                    },
                    success : function(result) {
                        showMiddleConditioner.successCallback(result);
                    },error:function(){

                    }
                });
            }, 250);

        });
    }

    /**
     * 风速切换
     * @param $conditioner
     */
    showMiddleConditioner.speedChangeEvent = function($conditioner){
        var $btn = $conditioner.find(".speedBtn");
        var f_sys_name = $conditioner.find(".speedSysname").val();
        $btn.unbind("click").click(function(){
            // 取消上次延时未执行的方法
            if(speedTiming){
                clearTimeout(speedTiming);
            }
            //执行延时
            speedTiming = setTimeout(function() {
                //此处为单击事件要执行的代码
                $.ajax({
                    url : _ctx + "/btnEventController/lowConditionerSpeedToggle",
                    type : "post",
                    dataType : "json",
                    data : {
                        sysname : f_sys_name
                    },
                    success : function(result) {
                        showMiddleConditioner.successCallback(result);
                    },error:function(){

                    }
                });
            }, 250);
        });
    }


    /**
     * 点击锁定图标切换为--未锁定
     * @param $conditioner
     */
    showMiddleConditioner.lockChangeEvent = function($conditioner){
        var $btn = $conditioner.find(".lock");
        var f_sys_name = $conditioner.find(".lockSysname").val();
        $btn.unbind("click").click(function(){
            $.ajax({
                url : _ctx + "/btnEventController/lowConditionerSdToggle",
                type : "post",
                dataType : "json",
                data : {
                    sysname : f_sys_name,
                    initVal:0
                },
                success : function(result) {
                    showMiddleConditioner.successCallback(result);
                },error:function(){

                }
            });
        });
    }

    /**
     * 点击未锁定图标切换为--锁定
     * @param $conditioner
     */
    showMiddleConditioner.unlockChangeEvent = function($conditioner){
        var $btn = $conditioner.find(".unlock");
        var f_sys_name = $conditioner.find(".lockSysname").val();
        $btn.unbind("click").click(function(){
            $.ajax({
                url : _ctx + "/btnEventController/lowConditionerSdToggle",
                type : "post",
                dataType : "json",
                data : {
                    sysname : f_sys_name,
                    //完全锁定
                    initVal:2
                },
                success : function(result) {
                    showMiddleConditioner.successCallback(result);
                },error:function(){

                }
            });
        });
    }

    /**
     * 切换设定温度后，几秒内如果无操作，将返回室内温度界面
     */
    showMiddleConditioner.indoorTempTimer = function($conditioner){
        var sysname = $conditioner.attr("data-id");
        //清空上次的定时器
        clearTimeout(tempTimers[sysname]);
        var timer = setTimeout(function(){
            // //将室内温度切换成设定温度
            // $conditioner.find(".indoorDiv").show();
            // $conditioner.find(".setDiv").hide();
            showMiddleConditioner.getNormalState($conditioner);
        },5000);
        tempTimers[sysname] = timer;
    }

    /**
     * 点击上箭头或下箭头，切换成设定温度状态
     */
    showMiddleConditioner.setTempState = function($conditioner){
        var result = false;
        var indoor_state = $conditioner.find(".indoorDiv").css("display");
        if(indoor_state !== "none"){
            //将室内温度切换成设定温度
            $conditioner.find(".indoorDiv").hide();
            $conditioner.find(".setDiv").show();
            result = true;
        }
        return result;
    }

    /**
     * 设定温度增加
     * @param $conditioner
     */
    showMiddleConditioner.setTemperatureUp = function($conditioner){
        var $btn = $conditioner.find(".upBtn");
        var f_sys_name = $conditioner.find(".setTemperatureSysname").val();
        $btn.unbind("click").click(function(){
            var result = showMiddleConditioner.setTempState($conditioner);
            if(result){
                //定时器
                showMiddleConditioner.indoorTempTimer($conditioner);
                // return;
            }
            //点击开关键，返回正常状态
            showMiddleConditioner.switchClick($conditioner);
            var integer = $conditioner.find(".set_integer").text();
            var decimal = $conditioner.find(".set_decimal").text();
            var num = integer  + decimal;
            $.ajax({
                url : _ctx + "/btnEventController/changeSetTemperature",
                type : "post",
                dataType : "json",
                data : {
                    sysname : f_sys_name,
                    direction:"up",
                    setNum:num
                },
                success : function(result) {
                    showMiddleConditioner.successCallback(result);
                    //定时器
                    showMiddleConditioner.indoorTempTimer($conditioner);
                },error:function(){

                }
            });
        });
    }

    /**
     * 设定温度减小
     * @param $conditioner
     */
    showMiddleConditioner.setTemperatureDown = function($conditioner){

        var $btn = $conditioner.find(".downBtn");
        var f_sys_name = $conditioner.find(".setTemperatureSysname").val();
        $btn.unbind("click").click(function(){
            var result = showMiddleConditioner.setTempState($conditioner);
            if(result){
                //定时器
                showMiddleConditioner.indoorTempTimer($conditioner);
                // return;
            }
            //点击开关键，返回正常状态
            showMiddleConditioner.switchClick($conditioner);
            var integer = $conditioner.find(".set_integer").text();
            var decimal = $conditioner.find(".set_decimal").text();
            var num = integer  + decimal;
            $.ajax({
                url : _ctx + "/btnEventController/changeSetTemperature",
                type : "post",
                dataType : "json",
                data : {
                    sysname : f_sys_name,
                    direction:"down",
                    setNum:num
                },
                success : function(result) {
                    showMiddleConditioner.successCallback(result);
                    //定时器
                    showMiddleConditioner.indoorTempTimer($conditioner);
                },error:function(){

                }
            });
        });

    }


    /**
     * 双击模式按钮：进入定时开状态
     */
    showMiddleConditioner.modeDoubleEvent = function($conditioner){
        var $btn = $conditioner.find(".modeBtn");
        //模式按钮双击进入定时开状态
        $btn.unbind("dblclick").dblclick(function(){

            showMiddleConditioner.openTimeState($conditioner);
            showMiddleConditioner.timingCloseEvent($conditioner);
            //开关键单击返回
            showMiddleConditioner.switchClick($conditioner);
            //返回正常状态的定时器
            showMiddleConditioner.backNormalTimer($conditioner);
            //清除风速的双击事件，与温控器面板保持一致
            $conditioner.find(".speedBtn").unbind("dblclick");
        });
    }

    /**
     * 风速双击切换能耗
     */
    showMiddleConditioner.speedDoubleEvent = function($conditioner){

        var $btn = $conditioner.find(".speedBtn");
        $btn.unbind("dblclick").dblclick(function(){
            // 取消上次延时未执行的方法
            clearTimeout(speedTiming);
            $conditioner.find(".timingDiv:not(.clock)").hide();
            $conditioner.find(".timing_num").hide();
            $conditioner.find(".timing_text").hide();

            $conditioner.find(".energyDiv").show();
            //上下按键移除点击事件
            $conditioner.find(".upBtn").unbind("click");
            $conditioner.find(".downBtn").unbind("click");
            //返回正常状态的定时器
            showMiddleConditioner.backNormalTimer($conditioner);
            //点击开关键退回
            showMiddleConditioner.switchClick($conditioner);
            //模式单击
            showMiddleConditioner.modeChangeEvent($conditioner);
            //模式双击
            showMiddleConditioner.modeDoubleEvent($conditioner);
        });

    }

    /**
     * 定时开或定时关状态时，几秒内若无操作，自动变为当前时间界面
     */
    showMiddleConditioner.backNormalTimer = function($conditioner){
        var sysname = $conditioner.attr("data-id");
        //清空上次的定时器
        clearTimeout(backTimers[sysname]);
        var timer = setTimeout(function(){
            showMiddleConditioner.getNormalState($conditioner);
        },5000);
        backTimers[sysname] = timer;
    }

    /**
     * 点击开关键，返回正常状态
     */
    showMiddleConditioner.switchClick = function($conditioner){
        var $btn = $conditioner.find(".switchBtn");
        $btn.unbind("click").click(function(){
            showMiddleConditioner.getNormalState($conditioner);
        });
    }

    /**
     * 设定温度、当前时间界面（正常状态）
     */
    showMiddleConditioner.getNormalState = function($conditioner){
        var sysname = $conditioner.attr("data-id");
        //显示室内温度
        $conditioner.find(".indoorDiv").show();
        $conditioner.find(".setDiv").hide();
        //定时状态切换为当前时间
        $conditioner.find(".current_time").show();
        $conditioner.find(".timing_open_num").hide();
        $conditioner.find(".timing_close_num").hide();
        $conditioner.find(".on").hide();
        $conditioner.find(".off").hide();
        $conditioner.find(".energyDiv").hide();

        //开关切换
        showMiddleConditioner.switchToggleEvent($conditioner);
        //模式切换
        showMiddleConditioner.modeChangeEvent($conditioner);
        //模式双击
        showMiddleConditioner.modeDoubleEvent($conditioner);
        //风速切换
        showMiddleConditioner.speedChangeEvent($conditioner);
        //风速双击
        showMiddleConditioner.speedDoubleEvent($conditioner);
        //设定温度调整
        showMiddleConditioner.setTemperatureUp($conditioner);
        showMiddleConditioner.setTemperatureDown($conditioner);
        //清空定时状态定时器
        clearTimeout(backTimers[sysname]);
        //清空设定温度定时器
        clearTimeout(tempTimers[sysname]);
        //取消时间闪烁
        clearInterval(blinkInterval);
        //下发定时时间
        showMiddleConditioner.sendTimingNum($conditioner);
    }

    /**
     * 下发定时时间
     */
    showMiddleConditioner.sendTimingNum = function($conditioner){
        var openSysname = $conditioner.find(".open_time").val();
        var closeSysname = $conditioner.find(".close_time").val();
        var open_time = showMiddleConditioner.getLowerFormat($conditioner,".timing_open_num");
        var close_time = showMiddleConditioner.getLowerFormat($conditioner,".timing_close_num");
        showConditioner.sendTimeData(openSysname,open_time);
        showConditioner.sendTimeData(closeSysname,close_time);
    }


    /**
     * 定时开或关状态：单击风扇按钮--调整小时或分钟切换
     */
     showMiddleConditioner.hourMinuteToggleEvent = function($conditioner,selector,timeSelector){
        var $btn = $conditioner.find(".speedBtn");
        $btn.unbind("click").click(function(){

            showMiddleConditioner.blinkEvent($conditioner,selector,timeSelector);
            var str = "";
            if(timeSelector === ".hour"){
                str = ".minute";
            }else{
                str = ".hour";
            }
            showMiddleConditioner.hourMinuteToggleEvent($conditioner,selector,str);
            //调节小时
            showMiddleConditioner.upTiming($conditioner,selector,timeSelector);
            showMiddleConditioner.downTiming($conditioner,selector,timeSelector);
            //返回正常状态的定时器
            showMiddleConditioner.backNormalTimer($conditioner);
        });
    }

    /**
     * 定时开状态：点击模式按键--变为定时关状态
     */
    showMiddleConditioner.timingCloseEvent = function($conditioner){
        var $btn = $conditioner.find(".modeBtn");
        $btn.unbind("click").click(function(){
            showMiddleConditioner.closeTimeState($conditioner);

            showMiddleConditioner.timingOpenEvent($conditioner);
            //返回正常状态的定时器
            showMiddleConditioner.backNormalTimer($conditioner);
        });
    }

    /**
     * 定时关状态下，点击模式按键--变为定时开状态
     */
    showMiddleConditioner.timingOpenEvent = function($conditioner){
        var $btn = $conditioner.find(".modeBtn");
        $btn.unbind("click").click(function(){
            showMiddleConditioner.openTimeState($conditioner);
            showMiddleConditioner.timingCloseEvent($conditioner);
            //返回正常状态的定时器
            showMiddleConditioner.backNormalTimer($conditioner);
        });
    }


    /**
     * 定时开、定时关状态，可调节的小时或分钟闪烁
     */
    showMiddleConditioner.blinkEvent = function($conditioner,selector,timeSelector){
        clearTimeout(blinkInterval);
        $conditioner.find(selector).find(".minute,.hour").show();
        blinkInterval = setInterval(function(){
            $conditioner.find(selector).find(timeSelector).toggle();
        },500);
    }


    /**
     * 定时开状态
     */
    showMiddleConditioner.openTimeState = function($conditioner){
        $conditioner.find(".current_time").hide();
        $conditioner.find(".timing_open_num").show();
        $conditioner.find(".timing_close_num").hide();
        $conditioner.find(".on").show();
        $conditioner.find(".off").hide();

        $conditioner.find(".energyDiv").hide();

        // 取消上次延时未执行的方法
        clearTimeout(modeTiming);
        //定时开：小时闪烁
        showMiddleConditioner.blinkEvent($conditioner,".timing_open_num",".hour");
        // 定时开状态：单击风扇按钮--调整小时或分钟切换
        showMiddleConditioner.hourMinuteToggleEvent($conditioner,".timing_open_num",".minute");
        showMiddleConditioner.upTiming($conditioner,".timing_open_num",".hour");
        showMiddleConditioner.downTiming($conditioner,".timing_open_num",".hour");
    }


    /**
     * 定时关状态
     */
    showMiddleConditioner.closeTimeState = function($conditioner){
        $conditioner.find(".current_time").hide();
        $conditioner.find(".timing_open_num").hide();
        $conditioner.find(".timing_close_num").show();
        $conditioner.find(".on").hide();
        $conditioner.find(".off").show();

        $conditioner.find(".energyDiv").hide();
        // 取消上次延时未执行的方法
        clearTimeout(modeTiming);
        //定时关：小时闪烁
        showMiddleConditioner.blinkEvent($conditioner,".timing_close_num",".hour");
        // 定时开状态：单击风扇按钮--调整小时或分钟切换
        showMiddleConditioner.hourMinuteToggleEvent($conditioner,".timing_close_num",".minute");
        showMiddleConditioner.upTiming($conditioner,".timing_close_num",".hour");
        showMiddleConditioner.downTiming($conditioner,".timing_close_num",".hour");
    }


    /**
     * 定时开或关状态：上三角增加小时或分钟
     */
    showMiddleConditioner.upTiming = function($conditioner,selector,timeSelector){
        var $upBtn = $conditioner.find(".upBtn");
        $upBtn.unbind("click").click(function(){
            var time = $conditioner.find(selector).find(timeSelector).text();
            time = Number(time);
            var max = 23;
            if(timeSelector === ".minute"){
                max = 59;
            }
            if(time >= max){
                time = 0;
            }else{
                time = time + 1;
            }
            if(time < 10){
                time = "0" + time;
            }
            $conditioner.find(selector).find(timeSelector).text(time);

            //返回正常状态的定时器
            showMiddleConditioner.backNormalTimer($conditioner);
        });
    }

    /**
     * 定时开或关状态：下三角减小小时或分钟
     */
    showMiddleConditioner.downTiming = function($conditioner,selector,timeSelector){
        var $upBtn = $conditioner.find(".downBtn");
        $upBtn.unbind("click").click(function(){
            var time = $conditioner.find(selector).find(timeSelector).text();
            time = Number(time);
            var max = 23;
            if(timeSelector === ".minute"){
                max = 59;
            }
            if(time > 0){
                time = time - 1;
            }else{
                time = max;
            }
            if(time < 10){
                time = "0" + time;
            }
            $conditioner.find(selector).find(timeSelector).text(time);

            //返回正常状态的定时器
            showMiddleConditioner.backNormalTimer($conditioner);
        });

    }


    /**
     * 将xx:xx 格式的时间转换成下位机时间
     */
    showMiddleConditioner.getLowerFormat = function($conditioner,selector){
        var hourStr = $conditioner.find(selector).find(".hour").text();
        var minuteStr = $conditioner.find(selector).find(".minute").text();
        var hour = Number(hourStr);
        var minute = Number(minuteStr);
        var h = hour.toString(16);
        var m = minute.toString(16);
        if( h.length < 2){
            h = "0" + h;
        }
        if(m.length < 2){
            m = "0" + m;
        }
        var time = parseInt(h+m,16);
        return time;
    }


    /**
     * 将下位机时间格式转换成xx:xx 格式的时间
     * @param $conditioner
     * @param selector
     * @param time
     */
    showMiddleConditioner.transformLowerTime = function($conditioner,selector,time){
        // var result = "00:00";
        var hourResult = "00";
        var minuteResult = "00";
        if(time){
            time = Number(time);
            var str = time.toString(16);
            for(; str.length < 4;){
                str = "0" + str;
            }
            var h = str.substring(0,2);
            var m =  str.substring(2);
            var hour = parseInt(h,16);
            var minute = parseInt(m,16);
            if(hour < 10){
                hour = "0" + hour;
            }
            if(minute < 10){
                minute = "0" + minute;
            }
            hourResult = hour;
           minuteResult = minute;
        }
        $conditioner.find(selector).find(".hour").text(hourResult);
        $conditioner.find(selector).find(".minute").text(minuteResult);
    }

    /**
     * 锁定模式切换事件
     */
    showMiddleConditioner.changeSdStatus =  function(){
        var $dom = $("#middle_conditioner_win .design_middle_conditioner");
        var f_sys_name = $dom.find(".lockSysname").val();
        var initVal = null;
        if($("#sdStateMiddle").text()==="锁定")
            initVal = "0";//0:未锁定 2：锁定
        if($("#sdStateMiddle").text()==="未锁定")
            initVal = "2";//0:未锁定 2：锁定
        if(initVal!=null){
            $.ajax({
                url : _ctx + "/btnEventController/lowConditionerSdToggle",
                type : "post",
                dataType : "json",
                data : {
                    sysname : f_sys_name,
                    initVal:initVal
                },
                success : function(result) {
                    if(result.status=="1"){//执行成功
                        if(initVal=="0"){//未锁定
                            $conditioner.find(".lock").html("");
                            $conditioner.find(".lock").html("<img src=../../static/pageDesign/images/air_conditioner/middle/unsd.png><span id = 'sdStateMiddle'>未锁定</span>");
                        }else if(initVal=="2"){//锁定
                            //锁定
                            $conditioner.find(".lock").html("");
                            $conditioner.find(".lock").html("<img src=../../static/pageDesign/images/air_conditioner/middle/sd.png><span id = 'sdStateMiddle'>锁定</span>");
                        }
                    }
                    showConditioner.successCallback(result);
                },error:function(){

                }
            });
        }

    }
});
