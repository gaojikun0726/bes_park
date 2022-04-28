/**
 * 展示温控器
 */
var showConditioner = {};

layui.use(['layer','form'], function(){
    var layer = layui.layer;
    //存储模块对应的定时器，方便清空当前时间的定时器，进行温控器定时操作。
    var timingJson = {};
    //时间闪烁定时器
    var blinkInterval = "";
    //模式按键单击延时定时器
    var modeTiming = "";
    //图标形式弹窗
    var index;
    $(function(){

    });

    function timeChange($dom,moduleName){
        var timing = setInterval(function(){
            var time = CommonMethod.getCurrentTime();
            $dom.text(time);
        },1000);
        timingJson[moduleName] = timing;
    }


    /**
     * 加载图标和列表两种形式的温控器
     */
    showConditioner.initAll = function(){
        showConditioner.initIconConditioner();
        showConditioner.initListConditioner();
    }

    /**
     * 加载图标格式的温控器
     */
    showConditioner.initIconConditioner = function(){
        var $conditioners = $(".design_low_conditioner.icon_img");
        $conditioners.unbind("click").click(function(){
            var $conditioner = $(this);
            var dataId = $conditioner.attr("data-id");
            if(!dataId){
                layer.msg("请关联模块");
                return;
            }
            $('#conditioner_win .design_low_conditioner').html($("#low_conditioner_content").html());
            index = layer.open({
                type: 1,
                title:"",
                area:['300px','300px'],
                content: $('#conditioner_win'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                cancel: function(index, layero){
                    // DebugBtn.closeAddDebugWin();
                    // return false;
                },
                success:function(){

                    showConditioner.getConditionerData($conditioner);
                }
            });


        });
    }

    /**
     * 查询获取单个温控器数据
     */
    showConditioner.getConditionerData = function($conditioner){
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
                        var $dom = $("#conditioner_win .design_low_conditioner");
                        $dom.attr("data-id",dataId);
                        showConditioner.initOneConditioner(childArray,$dom);
                        //注册回调事件
                        lowConditionerRefresh.iconConditionerRegister();
                    }
                }
            });
        }
    }

    /**
     * 加载列表温控器的数据
     */
    showConditioner.initListConditioner = function(){

        var $conditioner = $(".design_low_conditioner.list_img");
        $conditioner.html($("#low_conditioner_content").html());
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
        var $iconConditioners = $(".design_low_conditioner.icon_img");
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
                                showConditioner.initOneConditioner(childArray,$($conditioner[i]));
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
     * 图标温控器加载开关机分表代表的图片
     */
    showConditioner.initIconState = function(childArray,$conditioner){
        var sysname = "";
        /**
         * 00开关
         */
        for(var j = 0; j < childArray.length; j++){
            var f_sys_name = childArray[j].F_SYS_NAME;
            if(f_sys_name.slice(-2) === "00"){
                //开关
                sysname = f_sys_name;
                var switch_value = childArray[j].F_INIT_VAL +"";
                showConditioner.iconSwitchToggle($conditioner,switch_value);
            }
        }
        $conditioner.attr("data-switch",sysname);
    }

    /**
     * 图标模式温控器图标切换
     */
    showConditioner.iconSwitchToggle = function($conditioner,switch_value){
        if(switch_value === "0"){
            //关机
            $conditioner.find(".blue_img").hide();
            $conditioner.find(".gray_img").show();
        }else if(switch_value === "255"){
            //开机
            $conditioner.find(".blue_img").show();
            $conditioner.find(".gray_img").hide();
        }
    }

    /**
     * 加载温控器数据
     */
    showConditioner.initOneConditioner = function(childArray,$conditioner){
        var sysnameStr = "";
        /**
         * 00开关  01模式   02风速   04室内温度  07设定温度 10定时开  11定时关
         */
        for(var j = 0; j < childArray.length; j++){
            var f_sys_name = childArray[j].F_SYS_NAME;
            if(f_sys_name.slice(-2) === "00"){
                //开关
                sysnameStr += f_sys_name+",";
                $conditioner.find(".switchSysname").val(f_sys_name);
                showConditioner.switchToggleEvent($conditioner);
                var switch_value = childArray[j].F_INIT_VAL +"";
                showConditioner.initSwitch($conditioner,switch_value);
            }
            if(f_sys_name.slice(-2) === "01"){
                //模式
                sysnameStr += f_sys_name+",";
                $conditioner.find(".modeSysname").val(f_sys_name);
                showConditioner.modeChangeEvent($conditioner);
                //todo 暂时隐藏双击事件，低档温控器没有定时功能
                // showConditioner.timingEvent($conditioner);
                var mode_value = childArray[j].F_INIT_VAL +"";
                //加载模式状态
                showConditioner.initMode($conditioner,mode_value);
            }
            if(f_sys_name.slice(-2) === "02"){
                //风速
                sysnameStr += f_sys_name+",";
                $conditioner.find(".speedSysname").val(f_sys_name);
                showConditioner.speedChangeEvent($conditioner);
                var speed_value = Number(childArray[j].F_INIT_VAL);
                //加载风速状态
                showConditioner.initSpeed($conditioner,speed_value);

            }
            if(f_sys_name.slice(-2) === "03"){
                //锁定
                sysnameStr += f_sys_name+",";
                $conditioner.find(".lockSysname").val(f_sys_name);
                showConditioner.lockChangeEvent($conditioner);
                showConditioner.unlockChangeEvent($conditioner);
                var lock_value = Number(childArray[j].F_INIT_VAL);
                showConditioner.initLock($conditioner,lock_value);
            }
            if(f_sys_name.slice(-2) === "04"){
                //室内温度
                sysnameStr += f_sys_name+",";
                $conditioner.find(".indoorTemperatureSysname").val(f_sys_name);
                var indoor_value = childArray[j].F_INIT_VAL;
                //加载室内温度数据
                showConditioner.initIndoorNum($conditioner,indoor_value);
            }
            if(f_sys_name.slice(-2) === "07"){
                //设定温度
                sysnameStr += f_sys_name+",";
                $conditioner.find(".setTemperatureSysname").val(f_sys_name);
                showConditioner.setTemperatureUp($conditioner);
                showConditioner.setTemperatureDown($conditioner);
                var set_value = childArray[j].F_INIT_VAL;
                //加载设定温度数据
                showConditioner.initSetNum($conditioner,set_value);
            }
            if(f_sys_name.slice(-2) === "10"){
                //定时开
                sysnameStr += f_sys_name+",";
                $conditioner.find(".open_time").val(f_sys_name);
                var open_time = Number(childArray[j].F_INIT_VAL);
                showConditioner.transformLowerTime($conditioner,".timing_open_num",open_time);
            }
            if(f_sys_name.slice(-2) === "11"){
                //定时关
                sysnameStr += f_sys_name+",";
                $conditioner.find(".close_time").val(f_sys_name);
                var close_time = Number(childArray[j].F_INIT_VAL);
                showConditioner.transformLowerTime($conditioner,".timing_close_num",close_time);
            }
        }
        if(childArray.length > 0){
            //在线状态
            var onlineState = childArray[0].F_STATUS +"";
            showConditioner.initNetwork($conditioner,onlineState);
        }
        if(sysnameStr.length > 1){
            sysnameStr = sysnameStr.substring(0,sysnameStr.length-1);
        }
        $conditioner.attr("data-sysname",sysnameStr);
        //当前时间
        showConditioner.initTime($conditioner);
    }

    /**
     * 加载开关状态
     * @param $conditioner
     * @param switch_value
     */
    showConditioner.initSwitch = function($conditioner,switch_value){
        if(switch_value === "0"){
            //关机
            showConditioner.switchCloseState($conditioner);
        }else if(switch_value === "255"){
            //开机
            showConditioner.switchOpenState($conditioner);
        }
    }

    /**
     * 加载模式状态
     * @param $conditioner
     * @param mode_value
     */
    showConditioner.initMode = function($conditioner,mode_value){
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
    showConditioner.initSpeed = function($conditioner,speed_value){
        if(speed_value + ""){
            speed_value = Number(speed_value);
            if(speed_value === 1){
                //低速
                $conditioner.find(".high_speed").hide();
                $conditioner.find(".middle_speed").hide();
                $conditioner.find(".low_speed").show();
                $conditioner.find(".energy").hide();
                $conditioner.find(".auto").hide();
            }else if(speed_value === 2){
                //中速
                $conditioner.find(".high_speed").hide();
                $conditioner.find(".middle_speed").show();
                $conditioner.find(".low_speed").hide();
                $conditioner.find(".energy").hide();
                $conditioner.find(".auto").hide();
            }else if(speed_value === 3){
                //高速
                $conditioner.find(".high_speed").show();
                $conditioner.find(".middle_speed").hide();
                $conditioner.find(".low_speed").hide();
                $conditioner.find(".energy").hide();
                $conditioner.find(".auto").hide();
            }else if(speed_value === 4){
                //节能
                $conditioner.find(".high_speed").hide();
                $conditioner.find(".middle_speed").hide();
                $conditioner.find(".low_speed").hide();
                $conditioner.find(".energy").show();
                $conditioner.find(".auto").show();
            }
        }else{
            //没有数据
            $conditioner.find(".high_speed").hide();
            $conditioner.find(".middle_speed").hide();
            $conditioner.find(".low_speed").hide();
            $conditioner.find(".energy").hide();
            $conditioner.find(".auto").hide();
        }
    }

    /**
     * 加载锁定状态
     */
    showConditioner.initLock = function($conditioner,lock_value){
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
    showConditioner.initIndoorNum = function($conditioner,indoor_value){
        var indoor_num = Number(indoor_value).toFixed(1);
        $conditioner.find(".indoor_num").text(indoor_num);
    }

    /**
     * 加载设定温度数据
     */
    showConditioner.initSetNum = function($conditioner,set_value){
        var set_num = Number(set_value).toFixed(1);
        $conditioner.find(".set_num").text(set_num);
    }

    /**
     * 加载网络状态
     */
    showConditioner.initNetwork = function($conditioner,onlineState){
        if(onlineState === "1"){
            //在线
            $conditioner.find(".network").show();
        }else{
            $conditioner.find(".network").hide();
        }
    }

    /**
     * 加载时间
     */
    showConditioner.initTime = function($conditioner){
        var moduleName = $conditioner.attr("data-id");
        var $dom = $conditioner.find(".timing_num");
        $dom.text(CommonMethod.getCurrentTime());
        timeChange($dom,moduleName);
    }

    /**
     * 加载定时开时间
     */
    showConditioner.initOpenTime = function($conditioner,open_time){
        showConditioner.transformLowerTime($conditioner,".timing_open_num",open_time);
    }

    /**
     * 加载定时关时间
     */
    showConditioner.initCloseTime = function($conditioner,close_time){
        showConditioner.transformLowerTime($conditioner,".timing_close_num",close_time);
    }

    /**
     * 定时开、定时关状态，时间闪烁
     * @param $conditioner
     */
    showConditioner.blinkEvent = function($conditioner){
        blinkInterval = setInterval(function(){
            $conditioner.find(".timingDiv").toggle();
        },500);
    }


    /**
     * 取消时间闪烁
     */
    showConditioner.cancelBlinkEvent = function($conditioner){
        clearInterval(blinkInterval);
        $conditioner.find(".timing_num").show();
        $conditioner.find(".timing_open_num").hide();
        $conditioner.find(".timing_close_num").hide();
        $conditioner.find(".timing_open").hide();
        $conditioner.find(".timing_close").hide();
        $conditioner.find(".timingDiv").show();
        showConditioner.initTime($conditioner);
    }

    showConditioner.successCallback = function(result){
        if(result && result.status){
            if(result.status === '0'){
                if(result.msg){
                    // layer.msg(result.msg,{icon:2});
                    layer.msg(result.msg);
                }else{
                    // layer.msg("执行失败",{icon:2});
                    layer.msg("执行失败");
                }
            }else{
                if(result.msg){
                    // layer.msg(result.msg,{icon:1});
                    layer.msg(result.msg);
                }else{
                    // layer.msg("执行成功",{icon:1});
                    layer.msg("执行成功");
                }
            }
        }
    }

    /**
     * 关机状态
     */
    showConditioner.switchCloseState = function($conditioner){
        $conditioner.find(".grayBg").show();
        $conditioner.find(".blueBg").hide();
        $conditioner.find(".screenDiv>div").hide();
        //除了开关键其他按键移除点击事件
        $conditioner.find(".modeBtn").unbind("click");
        $conditioner.find(".speedBtn").unbind("click");
        $conditioner.find(".upBtn").unbind("click");
        $conditioner.find(".downBtn").unbind("click");

    }

    /**
     * 开机状态
     */
    showConditioner.switchOpenState = function($conditioner){
        $conditioner.find(".grayBg").hide();
        $conditioner.find(".blueBg").show();
        $conditioner.find(".screenDiv>div").show();
        //除了开关键的其他按键添加点击事件
        //模式切换
        showConditioner.modeChangeEvent($conditioner);
        //风速切换
        showConditioner.speedChangeEvent($conditioner);
        //设定温度调整
        showConditioner.setTemperatureUp($conditioner);
        showConditioner.setTemperatureDown($conditioner);

    }

    /**
     * 开关切换事件
     * @param $conditioner
     */
    showConditioner.switchToggleEvent = function($conditioner){
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
                    showConditioner.successCallback(result);
                },error:function(){

                }
            });
        });
    }


    /**
     * 模式切换事件
     * @param $conditioner
     */
    showConditioner.modeChangeEvent = function($conditioner){
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
                        showConditioner.successCallback(result);
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
    showConditioner.speedChangeEvent = function($conditioner){
        var $btn = $conditioner.find(".speedBtn");
        var f_sys_name = $conditioner.find(".speedSysname").val();
        $btn.unbind("click").click(function(){
            $.ajax({
                url : _ctx + "/btnEventController/lowConditionerSpeedToggle",
                type : "post",
                dataType : "json",
                data : {
                    sysname : f_sys_name
                },
                success : function(result) {
                    showConditioner.successCallback(result);
                },error:function(){

                }
            });
        });
    }

    /**
     * 点击锁定图标切换为--未锁定
     * @param $conditioner
     */
    showConditioner.lockChangeEvent = function($conditioner){
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
                    showConditioner.successCallback(result);
                },error:function(){

                }
            });
        });
    }

    /**
     * 点击未锁定图标切换为--锁定
     * @param $conditioner
     */
    showConditioner.unlockChangeEvent = function($conditioner){
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
                    showConditioner.successCallback(result);
                },error:function(){

                }
            });
        });
    }

    /**
     * 设定温度增加
     * @param $conditioner
     */
    showConditioner.setTemperatureUp = function($conditioner){
        var $btn = $conditioner.find(".upBtn");
        var f_sys_name = $conditioner.find(".setTemperatureSysname").val();
        $btn.unbind("click").click(function(){
            var setNum = $conditioner.find(".set_num").text();
            $.ajax({
                url : _ctx + "/btnEventController/changeSetTemperature",
                type : "post",
                dataType : "json",
                data : {
                    sysname : f_sys_name,
                    direction:"up",
                    setNum:setNum
                },
                success : function(result) {
                    showConditioner.successCallback(result);
                },error:function(){

                }
            });
        });
    }

    /**
     * 设定温度减小
     * @param $conditioner
     */
    showConditioner.setTemperatureDown = function($conditioner){
        var $btn = $conditioner.find(".downBtn");
        var f_sys_name = $conditioner.find(".setTemperatureSysname").val();
        $btn.unbind("click").click(function(){
            var setNum = $conditioner.find(".set_num").text();
            $.ajax({
                url : _ctx + "/btnEventController/changeSetTemperature",
                type : "post",
                dataType : "json",
                data : {
                    sysname : f_sys_name,
                    direction:"down",
                    setNum:setNum
                },
                success : function(result) {
                    showConditioner.successCallback(result);
                },error:function(){

                }
            });
        });
    }

    /**
     * 定时开状态
     */
    showConditioner.openTimeState = function($conditioner){
        $conditioner.find(".timing_num").hide();
        $conditioner.find(".timing_open_num").show();
        $conditioner.find(".timing_close_num").hide();
        $conditioner.find(".timing_open").show();
        $conditioner.find(".timing_close").hide();
        showConditioner.upTimingHour($conditioner,".timing_open_num");
        showConditioner.downTimingMinute($conditioner,".timing_open_num");
    }


    /**
     * 定时关状态
     */
    showConditioner.closeTimeState = function($conditioner){
        $conditioner.find(".timing_num").hide();
        $conditioner.find(".timing_open_num").hide();
        $conditioner.find(".timing_close_num").show();
        $conditioner.find(".timing_open").hide();
        $conditioner.find(".timing_close").show();
        showConditioner.upTimingHour($conditioner,".timing_close_num");
        showConditioner.downTimingMinute($conditioner,".timing_close_num");
    }

    /**
     * 双击模式按钮：进入定时开状态
     * 定时开、定时关、时间调整
     */
    showConditioner.timingEvent = function($conditioner){
        var $btn = $conditioner.find(".modeBtn");
        //模式按钮双击进入定时开状态
        $btn.unbind("dblclick").dblclick(function(){

            // 取消上次延时未执行的方法
            clearTimeout(modeTiming);
            //时间闪烁
            showConditioner.blinkEvent($conditioner);
            //定时开状态
           showConditioner.openTimeState($conditioner);
            var moduleName = $conditioner.attr("data-id");
            clearInterval(timingJson[moduleName]);

            // 定时开状态：单击风扇按钮--变为定时关状态
            showConditioner.timingCloseEvent($conditioner);
            //定时开、定时关、调整时间状态时，单击模式按钮--退出，回到正常状态
            showConditioner.exitTimingState($conditioner);
        });
        //定时开状态，up down按钮点击事件更改

    }

    /**
     * 定时开、定时关、调整时间状态时，单击模式按钮--退出，回到正常状态
     * @param $conditioner
     */
    showConditioner.exitTimingState = function($conditioner){
        var $btn = $conditioner.find(".modeBtn");
        $btn.unbind("click").click(function(){
            //模式切换
            showConditioner.modeChangeEvent($conditioner);
            //风速切换
            showConditioner.speedChangeEvent($conditioner);
            //设定温度调整
            showConditioner.setTemperatureUp($conditioner);
            showConditioner.setTemperatureDown($conditioner);
            //取消时间闪烁
            showConditioner.cancelBlinkEvent($conditioner);
            //下发定时时间
            var openSysname = $conditioner.find(".open_time").val();
            var closeSysname = $conditioner.find(".close_time").val();
            var open_time = showConditioner.getLowerFormat($conditioner,".timing_open_num");
            var close_time = showConditioner.getLowerFormat($conditioner,".timing_close_num");
            showConditioner.sendTimeData(openSysname,open_time);
            showConditioner.sendTimeData(closeSysname,close_time);
        });
    }

    /**
     * 定时开状态：上三角调整定时--增加小时
     */
    showConditioner.upTimingHour = function($conditioner,selector){
        var $upBtn = $conditioner.find(".upBtn");
        $upBtn.unbind("click").click(function(){

            var time = $conditioner.find(selector).text();
            var array = time.split(":");
            if(array.length === 2){
                var hour = Number(array[0]);
                var minute = array[1];
                if(hour === 23){
                    hour = 0;
                }else{
                    hour = hour + 1;
                }
                if(hour < 10){
                    hour = "0" + hour;
                }
                var result = hour + ":" + minute;
                $conditioner.find(selector).text(result);
            }
        });
    }

    /**
     * 定时开状态：下三角调整定时--增加分钟
     */
    showConditioner.downTimingMinute = function($conditioner,selector){
        var $upBtn = $conditioner.find(".downBtn");
        $upBtn.unbind("click").click(function(){
            var time = $conditioner.find(selector).text();
            var array = time.split(":");
            if(array.length === 2){
                var hour = Number(array[0]);
                var minute = Number(array[1]);
                if(minute === 59){
                    hour = hour + 1;
                    minute = 0;
                }else{
                    minute = minute + 1;
                }
                if(hour < 10){
                    hour = "0" + hour;
                }
                if(minute < 10){
                    minute = "0" + minute;
                }

                var result = hour + ":" + minute;
                $conditioner.find(selector).text(result);
            }
        });

    }

    /**
     * 定时开状态：单击风扇按钮--变为定时关状态
     */
    showConditioner.timingCloseEvent = function($conditioner){
        var $btn = $conditioner.find(".speedBtn");
        $btn.unbind("click").click(function(){
            showConditioner.closeTimeState($conditioner);

            showConditioner.timingOpenEvent($conditioner);
        });
    }

    /**
     * 定时关状态下，点击风扇按键--变为定时开状态
     */
    showConditioner.timingOpenEvent = function($conditioner){
        var $btn = $conditioner.find(".speedBtn");
        $btn.unbind("click").click(function(){
            showConditioner.openTimeState($conditioner);
            showConditioner.timingCloseEvent($conditioner);
        });
    }


    /**
     * 将xx:xx 格式的时间转换成下位机时间
     */
    showConditioner.getLowerFormat = function($conditioner,selector){
        var timeStr = $conditioner.find(selector).text();
        var array = timeStr.split(":");
        var hour = Number(array[0]);
        var minute = Number(array[1]);
        var h = hour.toString(16);
        var m = minute.toString(16);
        if( h.length < 2){
            h = "0" + h;
        }
        if(m.length < 2){
            m = "0" + m;
        }
        // var time = "0x" + h + m;
        var time = parseInt(h+m,16);
        return time;
    }

    /**
     * 将下位机时间格式转换成xx:xx 格式的时间
     * @param $conditioner
     * @param selector
     * @param time
     */
    showConditioner.transformLowerTime = function($conditioner,selector,time){
        var result = "00:00";
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
            result = hour + ":" + minute;
        }
        $conditioner.find(selector).text(result);

    }

    // /**
    //  * 定时关状态：点击风扇按钮--变为调整时间模式
    //  */
    // showConditioner.changeTimeEvent = function(){
    //
    // }

    // /**
    //  * 从下位机获取数据
    //  */
    // showConditioner.getLowerData = function($conditioner,selector){
    //     var sysname = $conditioner.find(selector).val();
    //     $.ajax({
    //         url : _ctx + "/btnEventController/getLowerData",
    //         type : "post",
    //         dataType : "json",
    //         data : {
    //             sysname : sysname
    //         },
    //         success : function(result) {
    //             if(result && result.data){
    //
    //             }
    //             // $conditioner.find(".timing_num").text("00:00");
    //         },error:function(){
    //
    //         }
    //     });
    // }

    /**
     * 向下位机发送定时数据
     */
    showConditioner.sendTimeData = function(sysname,time){
        $.ajax({
            url : _ctx + "/api/debugPointInfo",
            type : "post",
            contentType : "application/json; charset=utf-8",
            dataType : "json",
            data : JSON.stringify({
                f_sys_name : sysname,
                // f_work_mode : $('input[name="work_mode"]:checked').val(),
                f_init_val : time
            }),
            success : function(result) {

                showConditioner.successCallback();
            },
            error : function(result) {
                layer.msg("执行失败",{icon:2});
            }
        });
    }


    /**
     * 锁定模式切换事件
     */
    showConditioner.changeSdStatus =  function(){
        var $dom = $("#conditioner_win .design_low_conditioner");
        var f_sys_name = $dom.find(".lockSysname").val();
        var initVal = null;
        if($("#sdState").text()==="锁定")
            initVal = "0";//0:未锁定 2：锁定
        if($("#sdState").text()==="未锁定")
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
                            $conditioner.find(".lock").html("<img src=../../static/pageDesign/images/air_conditioner/low/unlock.png><span id = 'sdState'>未锁定</span>");
                        }else if(initVal=="2"){//锁定
                            //锁定
                            $conditioner.find(".lock").html("");
                            $conditioner.find(".lock").html("<img src=../../static/pageDesign/images/air_conditioner/low/lock.png><span id = 'sdState'>锁定</span>");
                        }
                    }
                    showConditioner.successCallback(result);
                },error:function(){

                }
            });
        }

    }

});
