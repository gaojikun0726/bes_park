/**
 * 获取实时数据
 */
var realTimeData = {};

layui.use(['layer','form'], function(){
    var layer = layui.layer;
    var form = layui.form;

    var settingInfo = "";
    var relativePoint ,designFormulas,btnId,btnName;//关联点、计算公式、id,饼图扇形名称
    var energyEfficiencyLable;
    var num,num1,num2,pieRelativePoint,pieRelativeId;//饼图三个值

    /**
     * 所有组件的关联的订阅事件
     */
    realTimeData.allControlRegister = function(){
        realTimeData.getChannelData();
        realTimeData.getSceneData();
        realTimeData.getPointBtnData();
        realTimeData.getImgData();
        realTimeData.getTextboxData();
        realTimeData.getLabelData();
        realTimeData.getTempListData();
        realTimeData.getTempImgData();
        realTimeData.getFlowData();
        realTimeData.getEnergyEfficiencyData();
        realTimeData.getEnergyEfficiencyDataRatio();
        lowConditionerRefresh.lowConditionerRegister();
        middleConditionerRefresh.listConditionerRegister();

        realTimeData.getCurtainData();
    }


//    单通道/场景/点位置/图片/文本框/标签  所有的关联点
//    一个类型一个回调

    /**
     * 单通道按钮--订阅事件
     */
    realTimeData.getChannelData = function(){
        var $btn = $("#design_area_demo").find(".design_channel_btn");
        for(var i = 0; i < $btn.length; i++){
            var dataId = $($btn[i]).data("id");
            if(dataId){
                parent.PubSub.subscribe( dataId,realTimeData.channelCallback, 'channelCallback');
            }
        }
    }

    /**
     *单通道按钮--回调函数
     */
    realTimeData.channelCallback = function(data){
        if(data){
            var name = data.name;
            var f_init_val = data.value;
            // var unit = data.unit;

            var $btn = $("#design_area_demo").find(".design_channel_btn");
            for(var i = 0; i < $btn.length; i++){
                var dataId = $($btn[i]).data("id");
                if(dataId === name){
                    $($btn[i]).removeClass("gray").removeClass("green").removeClass("red");
                    if( f_init_val === "0"){//离线
                        $($btn[i]).addClass("gray");
                    }else if(f_init_val === "255"){//在线
                        $($btn[i]).addClass("green");
                    } else{//故障
                        $($btn[i]).addClass("red");
                    }
                    //可能配置多个相同的关联点
                    // break;
                }
            }
        }
    }


    /**
     * 场景按钮--订阅事件
     */
    realTimeData.getSceneData = function(){
        var $btn = $("#design_area_demo").find(".design_scene_btn");
        for(var i = 0; i < $btn.length; i++){
            var dataStr = $($btn[i]).data("config");
            if(dataStr){
                var array = dataStr.split(",");
                for(var j = 0; j < array.length; j++){
                    if(array[j]){
                        parent.PubSub.subscribe( array[j],realTimeData.sceneCallback, 'sceneCallback');
                    }
                }
            }
        }
    }

    /**
     * 场景按钮关联点--回调事件
     */
    realTimeData.sceneCallback = function(data){
        //场景按钮关联的任意点位的状态发生改变，会重新加载所有场景按钮的状态。
        if(data){
            SceneEvent.initBtn();
            // var name = data.name;
            // var f_init_val = data.value;
            // var $btn = $("#design_area_demo").find(".design_scene_btn");
            // //包含这个点位信息的按钮的数组
            // var btnArray = [];
            // for(var i = 0; i < $btn.length; i++){
            //     var contain = false;
            //     var dataStr = $($btn[i]).data("config");
            //     if(dataStr){
            //         var nameArray = dataStr.split(",");
            //         for(var j = 0; j < nameArray.length; j++){
            //             if(nameArray[j] === name){
            //                contain = true;
            //                btnArray.push($btn[i]);
            //                 break;
            //             }
            //         }
            //     }
            // }
        }
    }
    /**
     * 点位置按钮关联点---订阅事件
     */
    realTimeData.getPointBtnData = function(){
        var $btn = $("#design_area_demo").find(".design_point_btn");
        for(var i = 0; i < $btn.length; i++){
            var dataId = $($btn[i]).data("id");
            if(dataId){
                parent.PubSub.subscribe( dataId,realTimeData.pointBtnCallback, 'pointBtnCallback');
            }
        }
    }

    /**
     * 点位置按钮的回调
     */
    realTimeData.pointBtnCallback = function(data){
        if(data){
            var name = data.name;
            var f_init_val = data.value;
            // var unit = data.unit;

            var $btn = $("#design_area_demo").find(".design_point_btn");
            for(var i = 0; i < $btn.length; i++){
                var dataId = $($btn[i]).data("id");
                if(dataId === name){
                    $($btn[i]).removeClass("gray");
                    if( f_init_val === "0"){//离线
                        $($btn[i]).addClass("gray");
                    }else if(f_init_val === "255"){//在线
                        $($btn[i]).addClass("green");
                    } else{//故障
                        $($btn[i]).addClass("red");
                    }
                    //可能配置多个相同的关联点
                    // break;
                }
            }
        }
    }
    /**
     * 温控器列表关联模块--订阅事件
     */
    realTimeData.getTempListData = function(){

        var $div = $("#design_area_demo").find(".design_div");
        for(var i = 0; i < $div.length; i++){
            var dataId = $($div[i]).data("id");
            if(dataId){
                $.ajax({
                    url: _ctx + "/view/basedatamanage/eqmanage/getPointTypeInfo",
                    type    : "post",
                    dataType: 'json',
                    async:false,
                    data : {
                        sysname : dataId
                    },
                    success : function(result) {
                        if(result.status  =="1"){
                            if(result.list!=null){//温控器
                                if(result.list[0].f_type=="9"&&(result.list[0].modeType=="3"||result.list[0].modeType=="13")){
                                    $.ajax({
                                        url: _ctx + "/view/basedatamanage/eqmanage/getChildPointInfo",
                                        type    : "post",
                                        dataType: 'json',
                                        async:false,
                                        data : {
                                            sysname : dataId
                                        },
                                        success : function(result) {
                                            if(result.status  =="1"){
                                                if(result.list!=null){//温控器下属点位
                                                    for(var i=0;i<result.list.length;i++){
                                                        var sysName = result.list[i].f_sys_name;
                                                        parent.PubSub.subscribe( sysName,realTimeData.tempListCallback, 'tempListCallback');
                                                    }
                                                }
                                            }
                                        }
                                    });
                                }
                            }else{
                                //parent.PubSub.subscribe( dataId,realTimeData.imgCallback);
                            }
                        }
                    }
                });
            }
        }
    }


    /**
     * 温控器列表关联点的回调函数
     * @param data
     */
    realTimeData.tempListCallback = function(data){
        var name = data.name;
        var f_init_val = data.value;
        var $div = $("#design_area_demo").find(".design_div");
        for(var i = 0; i < $div.length; i++) {
            var dataId = $($div[i]).data("id");
            if(String(name).indexOf(dataId)!=-1) {
                if (name == dataId + "00") {//开关机
                    if (f_init_val == "0") {//关机
                        $("#" + dataId).find("div[class='settingsChild']").css("display", "none");
                    } else if (f_init_val == "255") {//开机
                        $("#" + dataId).find("div[class='settingsChild']").css("display", "block");
                    }
                } else if (name == dataId + "01") {//制冷制热
                    realTimeData.getTempPointInfo(name);
                    if(settingInfo.length>0){
                        for(var i = 0;i<settingInfo.length;i++){
                            if(settingInfo[i].F_INIT_VAL==f_init_val){
                                $("#" + dataId).find("p[class='module']").html(settingInfo[i].F_DESC);
                                if(settingInfo[i].F_DESC=="制冷"){
                                    $("#"+dataId).find("img[class='moduleImg']").attr("src",""+_ctx+"/static/pageDesign/icon/toolbar/cool.png");
                                }else if(settingInfo[i].F_DESC=="制热"){
                                    $("#"+dataId).find("img[class='moduleImg']").attr("src",""+_ctx+"/static/pageDesign/icon/toolbar/warm.png");
                                }
                            }
                        }
                    }
                    /*for(){

                    }*/
                   /*if (f_init_val == "255") {
                        $("#" + dataId).find("p[class='module']").html("制冷");
                        $("#" + dataId).find("img[class='moduleImg']").attr("src", "" + _ctx + "/static/pageDesign/icon/toolbar/cool.png");
                    } else if (f_init_val == "0") {
                        $("#" + dataId).find("p[class='module']").html("制热");
                        $("#" + dataId).find("img[class='moduleImg']").attr("src", "" + _ctx + "/static/pageDesign/icon/toolbar/warm.png");
                    }*/
                } else if (name == dataId + "02") {//制风速
                    realTimeData.getTempPointInfo(name);
                    if(settingInfo.length>0){
                        for(var i = 0;i<settingInfo.length;i++){
                            if(settingInfo[i].F_INIT_VAL==f_init_val){
                                $("#" + dataId).find("p[class='module']").html(settingInfo[i].F_DESC);
                                if(settingInfo[i].F_DESC=="节能"){
                                    $("#"+dataId).find("img[class='moduleTypeImg']").attr("src",""+_ctx+"/static/pageDesign/icon/toolbar/selffModule.png");
                                }else {
                                    $("#"+dataId).find("img[class='moduleTypeImg']").attr("src",""+_ctx+"/static/pageDesign/icon/toolbar/handModule.png");
                                }
                            }
                        }
                    }

                    /*if (f_init_val == "1") {
                        $("#" + dataId).find("p[class='fs']").html("低速");
                        $("#" + dataId).find("p[class='moduleType']").html("手动");
                        $("#" + dataId).find("img[class='moduleTypeImg']").attr("src", "" + _ctx + "/static/pageDesign/icon/toolbar/handModule.png");
                    } else if (f_init_val == "2") {
                        $("#" + dataId).find("p[class='fs']").html("中速");
                        $("#" + dataId).find("p[class='moduleType']").html("手动");
                        $("#" + dataId).find("img[class='moduleTypeImg']").attr("src", "" + _ctx + "/static/pageDesign/icon/toolbar/handModule.png");
                    } else if (f_init_val == "3") {
                        $("#" + dataId).find("p[class='fs']").html("高速");
                        $("#" + dataId).find("p[class='moduleType']").html("手动");
                        $("#" + dataId).find("img[class='moduleTypeImg']").attr("src", "" + _ctx + "/static/pageDesign/icon/toolbar/handModule.png");
                    } else if (f_init_val == "4") {
                        $("#" + dataId).find("p[class='fs']").html("节能");
                        $("#" + dataId).find("p[class='moduleType']").html("自动");
                        $("#" + dataId).find("img[class='moduleTypeImg']").attr("src", "" + _ctx + "/static/pageDesign/icon/toolbar/selffModule.png");
                    }*/
                } else if(name == dataId + "03"){//锁定
                    if (f_init_val == "1" || f_init_val == "2") {//1:全部锁  2:部分锁定
                        $("#" + dataId).find("div[class='sdDiv']").css("display", "block");
                    } else {
                        $("#" + dataId).find("div[class='sdDiv']").css("display", "none");
                    }
                }else if(name == dataId + "07"){//设定温度
                    $("#"+dataId).find("p[class='sdTempInfo']").html(f_init_val);
                }else if(name == dataId + "04"){//室内温度
                    $("#"+dataId).find("p[class='sdTempInfo']").html(f_init_val);
                }

            }

        }


    }


    /**
     * 查询设置配置详细信息
     */
    realTimeData.getTempPointInfo = function(f_sys_name){
        $.ajax({
            url     : _ctx + '/btnEventController/getTempPointInfo',
            type    : "post",
            dataType: 'json',
            async: false,
            data : {
                sysname : f_sys_name
            },
            success : function(result) {
                if(result.status === '1' && result.list){
                    settingInfo = result.list;
                }else{
                    layer.msg("查询失败",{icon:2});
                }
            },
            error : function(result) {
                layer.msg("查询失败",{icon:2});
            }
        });
    }

    realTimeData.getTempImgData = function(){
        var $image = $("#design_area_demo").find(".design_tempImg");
        for(var i = 0; i < $image.length; i++){
            var dataId = $($image[i]).data("id");
            if(dataId){
                $.ajax({
                    url: _ctx + "/view/basedatamanage/eqmanage/getPointTypeInfo",
                    type    : "post",
                    dataType: 'json',
                    async:false,
                    data : {
                        sysname : dataId
                    },
                    success : function(result) {
                        if(result.status  =="1"){
                            if(result.list!=null){//温控器
                                if(result.list[0].f_type=="9"&&(result.list[0].modeType=="3"||result.list[0].modeType=="13")){
                                    $.ajax({
                                        url: _ctx + "/view/basedatamanage/eqmanage/getChildPointInfo",
                                        type    : "post",
                                        dataType: 'json',
                                        async:false,
                                        data : {
                                            sysname : dataId
                                        },
                                        success : function(result) {
                                            if(result.status  =="1"){
                                                if(result.list!=null){//温控器下属点位
                                                    for(var i=0;i<result.list.length;i++){
                                                        var sysName = result.list[i].f_sys_name;
                                                        parent.PubSub.subscribe( sysName,realTimeData.tempImgCallback, 'tempImgCallback');
                                                    }
                                                }
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    }
                });
            }

        }
    }

    /**
     * 图片关联点--订阅事件
     */
     realTimeData.getImgData = function(){
        var $image = $("#design_area_demo").find(".design_img");
        for(var i = 0; i < $image.length; i++){
            var dataId = $($image[i]).data("id");
            if(dataId){
                parent.PubSub.subscribe( dataId,realTimeData.imgCallback, 'imgCallback');
            }

        }
    }

    /**
     * 窗帘关联点--订阅事件
     */
    realTimeData.getCurtainData = function(){
        var $curtain = $("#design_area_demo").find(".design_curtain");
        for(var i = 0; i < $curtain.length; i++){
            var dataId = $($curtain[i]).data("id");
            if(dataId){
                parent.PubSub.subscribe( dataId,realTimeData.curtainCallback, 'curtainCallback');
            }

        }
    };

    /**
     * 窗帘--订阅回调事件
     */
    realTimeData.curtainCallback = function(data){
        var name = data.name;
        var f_init_val = data.value;
        //有design_img类型的可能是动图生成的canvas标签，canvas元素调用stop()方法会报错
        var $curtain = $("#design_area_demo").find(".design_curtain");
        for(var i = 0 ; i < $curtain.length; i++) {
            var dataId = $($curtain[i]).data("id");
            if(dataId === name){
                CurtainEvent.iconToggle($($curtain[i]),f_init_val);
            }
        }
    };


    /**
     * 温控器图片关联点的回调函数
     * @param data
     */
    realTimeData.tempImgCallback = function(data){
        var name = data.name;
        var f_init_val = data.value;
        var $image = $("#design_area_demo").find(".design_tempImg");
        for(var i = 0; i < $image.length; i++) {
            var dataId = $($image[i]).data("id");
            if(String(name).indexOf(dataId)!=-1) {
                if (name == dataId + "00") {//开关机
                    if (f_init_val == "0") {//关机
                        $("#" + dataId).find("div[class='settingsChild']").css("display", "none");
                    } else if (f_init_val == "255") {//开机
                        $("#" + dataId).find("div[class='settingsChild']").css("display", "block");
                    }
                } else if (name == dataId + "01") {//制冷制热
                    realTimeData.getTempPointInfo(name);
                    if(settingInfo.length>0){
                        for(var i = 0;i<settingInfo.length;i++){
                            if(settingInfo[i].F_INIT_VAL==f_init_val){
                                $("#" + dataId).find("p[class='module']").html(settingInfo[i].F_DESC);
                                if(settingInfo[i].F_DESC=="制冷"){
                                    $("#"+dataId).find("img[class='moduleImg']").attr("src",""+_ctx+"/static/pageDesign/icon/toolbar/cool.png");
                                }else if(settingInfo[i].F_DESC=="制热"){
                                    $("#"+dataId).find("img[class='moduleImg']").attr("src",""+_ctx+"/static/pageDesign/icon/toolbar/warm.png");
                                }
                            }
                        }
                    }
                    /*if (f_init_val == "255") {
                        $("#" + dataId).find("p[class='module']").html("制冷");
                        $("#" + dataId).find("img[class='moduleImg']").attr("src", "" + _ctx + "/static/pageDesign/icon/toolbar/cool.png");
                    } else if (f_init_val == "0") {
                        $("#" + dataId).find("p[class='module']").html("制热");
                        $("#" + dataId).find("img[class='moduleImg']").attr("src", "" + _ctx + "/static/pageDesign/icon/toolbar/warm.png");
                    }*/
                } else if (name == dataId + "02") {//制风速
                    realTimeData.getTempPointInfo(name);
                    if(settingInfo.length>0){
                        for(var i = 0;i<settingInfo.length;i++){
                            if(settingInfo[i].F_INIT_VAL==f_init_val){
                                $("#" + dataId).find("p[class='module']").html(settingInfo[i].F_DESC);
                                if(settingInfo[i].F_DESC=="节能"){
                                    $("#"+dataId).find("img[class='moduleTypeImg']").attr("src",""+_ctx+"/static/pageDesign/icon/toolbar/selffModule.png");
                                }else {
                                    $("#"+dataId).find("img[class='moduleTypeImg']").attr("src",""+_ctx+"/static/pageDesign/icon/toolbar/handModule.png");
                                }
                            }
                        }
                    }
                    /*if (f_init_val == "1") {
                        $("#" + dataId).find("p[class='fs']").html("低速");
                        $("#" + dataId).find("p[class='moduleType']").html("手动");
                        $("#" + dataId).find("img[class='moduleTypeImg']").attr("src", "" + _ctx + "/static/pageDesign/icon/toolbar/handModule.png");
                    } else if (f_init_val == "2") {
                        $("#" + dataId).find("p[class='fs']").html("中速");
                        $("#" + dataId).find("p[class='moduleType']").html("手动");
                        $("#" + dataId).find("img[class='moduleTypeImg']").attr("src", "" + _ctx + "/static/pageDesign/icon/toolbar/handModule.png");
                    } else if (f_init_val == "3") {
                        $("#" + dataId).find("p[class='fs']").html("高速");
                        $("#" + dataId).find("p[class='moduleType']").html("手动");
                        $("#" + dataId).find("img[class='moduleTypeImg']").attr("src", "" + _ctx + "/static/pageDesign/icon/toolbar/handModule.png");
                    } else if (f_init_val == "4") {
                        $("#" + dataId).find("p[class='fs']").html("节能");
                        $("#" + dataId).find("p[class='moduleType']").html("自动");
                        $("#" + dataId).find("img[class='moduleTypeImg']").attr("src", "" + _ctx + "/static/pageDesign/icon/toolbar/selffModule.png");
                    }*/
                } else if(name == dataId + "03"){//锁定
                    if (f_init_val == "1" || f_init_val == "2") {//1:全部锁  2:部分锁定
                        $("#" + dataId).find("div[class='sdDiv']").css("display", "block");
                    } else {
                        $("#" + dataId).find("div[class='sdDiv']").css("display", "none");
                    }
                }else if(name == dataId + "07"){//设定温度
                    $("#"+dataId).find("p[class='sdTempInfo']").html(f_init_val);
                }else if(name == dataId + "04"){//室内温度
                    $("#"+dataId).find("p[class='sdTempInfo']").html(f_init_val);
                }

            }

        }


    }

    /**
     * 图片关联点的回调函数
     * @param data
     */
    realTimeData.imgCallback = function(data){
        var name = data.name;
        var f_init_val = data.value;
        //有design_img类型的可能是动图生成的canvas标签，canvas元素调用stop()方法会报错
        var $image = $("#design_area_demo").find("img.design_img");
        for(var i = 0 ; i < $image.length; i++) {
            var dataId = $($image[i]).data("id");
            var configData = $($image[i]).data("config");
            if(dataId === name){
                if(configData){
                    var configType = "";
                    var configResult = [];
                    //区分状态量和区间量
                    if(configData.indexOf("min$") >= 0){
                        //区间量
                        configType = "2";
                        //解析关联配置数据
                        configResult = ImgEvent.getIntervalConfigResult(configData);
                    }else{
                        //状态量
                        configType = "1";
                        //解析关联配置数据
                        configResult = ImgEvent.getStateConfigResult(configData);
                    }

                    ImgEvent.configEffect(f_init_val,configResult,configType,$($image[i]));
                }
            }
        }
    }

    /**
     * 文本框关联点--订阅事件
     */
     realTimeData.getTextboxData = function(){
        var $textbox = $("#design_area_demo").find(".design_textbox");
        for(var i = 0; i < $textbox.length; i++){
            var dataId = $($textbox[i]).data("id");
            if(dataId){
                parent.PubSub.subscribe( dataId,realTimeData.textboxCallback, 'textboxCallback');
            }
        }
    }

    /**
     * 文本框关联点的回调函数
     * @param data
     */
    realTimeData.textboxCallback = function(data){
        var name = data.name;
        var f_init_val = data.value;

      realTimeData.queryPointInfo(name).then(
          function(result){
              var unit = result.data.F_ENGINEER_UNIT;
              if(!unit){
                  unit = "";
              }
              realTimeData.setTextbox(name,f_init_val,unit);
          }
      );
    }

    /**
     * 回调设置文本框
     */
    realTimeData.setTextbox = function(name,f_init_val,unit){
        var $textbox = $("#design_area_demo").find(".design_textbox");
        for(var i = 0 ; i < $textbox.length; i++) {
            var dataId = $($textbox[i]).attr("data-id");
            if(dataId === name){
                var configData =  $($textbox[i]).attr("data-config");
                if(configData){
                    //解析关联配置数据
                    var configResult = [];
                    var configType = "";
                    // var array = configData.split(";");
                    //区分状态量和区间量
                    if(configData.indexOf("min:") >= 0){
                        //区间量
                        configType = "2";
                        configResult = TextboxEvent.intervalConfigResult(configData);
                    }else{
                        //状态量
                        configType = "1";
                        configResult = TextboxEvent.stateConfigResult(configData);
                    }
                    // AI/DI/DO类型
                    TextboxEvent.configEffect(f_init_val,configResult,configType,$($textbox[i]));
                }else{
                    // 没有关联配置
                    TextboxEvent.valueEffect(f_init_val,unit,$($textbox[i]));
                }
                //可能配置多个相同的关联点
                // break;
            }
        }
    }

    /**
     * 查询单个点的信息
     * @param dataId
     * @returns {Promise<any>}
     */
    realTimeData.queryPointInfo = function(dataId){
        return new Promise(function(resolve, reject){
            $.ajax({
                url     : _ctx + '/btnEventController/getDebugInfo',
                type    : "post",
                dataType: 'json',
                data : {
                    sysname : dataId
                },
                success : function(result) {
                    resolve(result);
                }});
        });
    }


    /**
     * 标签关联点--订阅事件
     */
     realTimeData.getLabelData = function(){
        var $label = $("#design_area_demo").find(".design_label");
        for(var i = 0; i < $label.length; i++){
            if($($label[i]).parent().parent().children('td').length==3){//系统能效里面
                for(var i = 0; i < $label.length; i++){
                    var dataId = $($label[i]).attr("data-id");
                    if(dataId){
                        energyEfficiencyLable = $($label[i]);
                        parent.PubSub.subscribe( dataId,realTimeData.labelEnergyEfficiencyCallback, 'labelEnergyEfficiencyCallback');
                    }
                }

            }else{
                var dataId = $($label[i]).attr("data-id");
                if(dataId){
                    parent.PubSub.subscribe( dataId,realTimeData.labelCallback, 'labelCallback');
                }
            }

        }
    }
    /**
     * 系统能效标签回调事件
     * @param data
     */
    realTimeData.labelEnergyEfficiencyCallback = function(data){
        var name = data.name;
        var f_init_val = data.value;
        var unit = data.value;

        energyEfficiencyLable.parent().parent().css("text-align","center");
        energyEfficiencyLable.parent().parent().children('td').eq(1).html(f_init_val);
        energyEfficiencyLable.parent().parent().children('td').eq(2).html(unit);
        energyEfficiencyLable.text(name);
        }

    /**
     * 标签关联点--回调函数
     * @param data
     */
    realTimeData.labelCallback = function(data){
        var name = data.name;
        var f_init_val = data.value;

        realTimeData.queryPointInfo(name).then(
            function(result){
                var unit = result.data.F_ENGINEER_UNIT;
                if(!unit){
                    unit = "";
                }
                realTimeData.setLabel(name,f_init_val,unit);
            }
        );
    }


    /**
     * 回调设置标签
     */
    realTimeData.setLabel = function(name,f_init_val,unit){
        var $label = $("#design_area_demo").find(".design_label");
        for(var i = 0 ; i < $label.length; i++){
            var dataId = $($label[i]).attr("data-id");
            if(dataId === name){
                var configData =  $($label[i]).attr("data-config");
                if(configData){
                    //解析关联配置数据
                    var configResult = [];
                    var configType = "";
                    // var array = configData.split(";");
                    //区分状态量和区间量
                    if(configData.indexOf("min:") >= 0){
                        //区间量
                        configType = "2";
                        configResult = LabelEvent.intervalConfigResult(configData);
                    }else{
                        //状态量
                        configType = "1";
                        configResult = LabelEvent.stateConfigResult(configData);
                    }
                    // AI/DI/DO类型
                    LabelEvent.configEffect(f_init_val,configResult,configType,$($label[i]));
                }else{
                    // 没有关联配置
                    LabelEvent.valueEffect(f_init_val,unit,$($label[i]))
                }
            }
        }
    }


    /**
     * 流程图关联点--订阅事件
     */
    realTimeData.getFlowData = function(){
        var $textbox = $("#design_area_demo").find(".design_flow_btn");
        for(var i = 0; i < $textbox.length; i++){
            var dataId = $($textbox[i]).data("id");
            if(dataId){
                parent.PubSub.subscribe( dataId,realTimeData.flowCallback, 'flowCallback');
            }
        }
    }

    /**
     * 流程图关联点--回调函数
     * @param data
     */
    realTimeData.flowCallback = function(data){
        var name = data.name;
        var f_init_val = data.value;
        var unit = data.unit;

        var $textbox = $("#design_area_demo").find(".design_flow_btn");
        for(var i = 0 ; i < $textbox.length; i++){
            var dataId = $($textbox[i]).data("id");
            if(dataId === name){
                var configData =  $($textbox[i]).data("config");
                if(configData){
                    //解析关联配置数据
                    var configResult = [];
                    var array = configData.split(";");
                    for(var k = 0; k < array.length;k++){
                        var json = {};
                        var item = array[k].split(",");
                        if(item.length === 3){
                            var state = item[1].split(":")[1];
                            var text = item[2].split(":")[1];
                            // var color = item[3].split(":")[1];
                            json.state = state;
                            json.text = text;
                            // json.color = color;
                            configResult.push(json);
                        }
                    }
                    // DI/DO类型
                    for(k = 0; k < configResult.length; k++){
                        if(f_init_val == configResult[k].state){
                            $($textbox[i]).text(configResult[k].text);
                            break;
                        }
                    }
                }else{
                    // AI、AO类型
                    $($textbox[i]).text(f_init_val + unit);
                }
            }
        }
    }
    /**
     * 系统能效占比订阅事件
     */
    realTimeData.getEnergyEfficiencyDataRatio = function(){
        var piePointArray = ["1","2","3"];
        if(piePointArray.length > 0) {
            $.ajax({
                url: _ctx + '/view/strongAndWeakElectricityIntegration/energyEfficiencyAssessment/queryAllPieRelativeInfo',
                type: "post",
                data: JSON.stringify(piePointArray),
                async: false,
                contentType: 'application/json;charset=UTF-8',
                success: function (result) {
                    if (result && result.map) {
                        var resultList = result.map.allPiePointData;
                        for(var i = 0;i<resultList.length;i++){
                            /*var sysnameArray = [];*/
                            if(resultList[i].relativePoint!=""&&resultList[i].expression!=""){
                                relativePoint = resultList[i].relativePoint;//关联点
                                designFormulas = resultList[i].expression;//计算公式
                                btnId = resultList[i].id;//name
                                var list = [];
                                if(typeof (relativePoint)!=undefined){
                                    list.push(relativePoint.split(","));
                                    num ="0";
                                    num1="0";
                                    num2="0";
                                    var piePointArray = ["1","2","3"];
                                    if(piePointArray.length > 0) {
                                        $.ajax({
                                            url: _ctx + '/view/strongAndWeakElectricityIntegration/energyEfficiencyAssessment/queryAllPieRelativeInfo',
                                            type: "post",
                                            data: JSON.stringify(piePointArray),
                                            async: false,
                                            contentType: 'application/json;charset=UTF-8',
                                            success: function (result) {
                                                if (result && result.map) {
                                                    var resultList = result.map.allPiePointData;
                                                    for(var i = 0;i<resultList.length;i++){
                                                        var sysnameArray = [];
                                                        if(resultList[i].relativePoint!=""&&resultList[i].expression!=""){
                                                            sysnameArray.push(resultList[i].relativePoint);
                                                            sysnameArray.push(resultList[i].id);
                                                            sysnameArray.push(resultList[i].expression);
                                                            if(sysnameArray.length > 0){
                                                                $.ajax({
                                                                    url     : _ctx + '/view/strongAndWeakElectricityIntegration/energyEfficiencyAssessment/queryEnergyEfficiencyListInfo',
                                                                    type    : "post",
                                                                    data: JSON.stringify(sysnameArray),
                                                                    async: false,
                                                                    contentType: 'application/json;charset=UTF-8',
                                                                    success : function(result) {
                                                                        if(result && result.map){
                                                                            if(resultList[i].id=="1"){//冷却泵能耗
                                                                                pieRelativePoint =resultList[i].relativePoint;
                                                                                pieRelativeId == "1";
                                                                                num = result.map.typeAllData;
                                                                            }if(resultList[i].id=="2"){//冷温泵能耗
                                                                                pieRelativePoint =resultList[i].relativePoint;
                                                                                pieRelativeId == "2";
                                                                                num1 = result.map.typeAllData;
                                                                            }if(resultList[i].id=="3"){//主机能耗
                                                                                pieRelativePoint =resultList[i].relativePoint;
                                                                                pieRelativeId == "3";
                                                                                num2 = result.map.typeAllData;
                                                                            }
                                                                        }
                                                                    }
                                                                })
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        })

                                    }
                                    if(list.length=="1"){//只有一个点时
                                        parent.PubSub.subscribe( list[0],realTimeData.energyEfficiencyPieOneCallback, 'energyEfficiencyPieOneCallback');
                                    }else{//多个点时
                                        for(var i =0;i<list.length;i++){
                                            parent.PubSub.subscribe( list[i],realTimeData.energyEfficiencyPieCallback, 'energyEfficiencyPieCallback');
                                        }
                                    }

                                }

                            }
                        }
                    }
                }
            })

        }
    }
    /**
     * 关联一个点时
     */
    realTimeData.energyEfficiencyPieOneCallback = function(data){
        var name = data.name;//名称
        var f_init_val = data.value;//数值
        var unit = data.unit;//单位
        if(pieRelativeId == "1"){
            if(pieRelativePoint==name){
                num = f_init_val;
            }
        }
        if(pieRelativeId == "2"){
            if(pieRelativePoint==name){
                num1 = f_init_val;
            }
        }
        if(pieRelativeId == "3"){
            if(pieRelativePoint==name){
                num2 = f_init_val;
            }
        }

    }
    /**
     * 关联多个点时
     */
    realTimeData.energyEfficiencyPieCallback = function(data){
        var name = data.name;//名称
        var f_init_val = data.value;//数值
        var unit = data.unit;//单位
        var dataId=relativePoint;

        var sysnameArray = [];
        sysnameArray.push(dataId);//关联点位
        sysnameArray.push("");//id   1,2,3
        sysnameArray.push(designFormulas);//计算公式
        sysnameArray.push(name);//变化的点位名称
        sysnameArray.push(f_init_val);//变化的值
        if(dataId){
            $.ajax({
                url     : _ctx + '/view/strongAndWeakElectricityIntegration/energyEfficiencyAssessment/queryRealTimeEnergyEfficiencyBtnInfo',
                type    : "post",
                data: JSON.stringify(sysnameArray),
                async: false,
                contentType: 'application/json;charset=UTF-8',
                success : function(result) {
                    if(result && result.map){
                        if(pieRelativeId == "1"){
                            num = result.map.typeAllData;
                        }
                        if(pieRelativeId == "2"){
                            num1 = result.map.typeAllData;
                        }
                        if(pieRelativeId == "3"){
                            num2 = result.map.typeAllData;
                        }
                        realTimeData.energyEfficiencyPieShow();
                    }
                }
            })

        }

    }

    realTimeData.energyEfficiencyPieShow = function(){
        var myCharts = echarts.init(document.getElementById("equipmentEnergyConsumptionRatio"));
        var numAll = Number(num)+Number(num1)+Number(num2);
        option = {
            title: [
                {
                    top:0,//此处是指title（设备能耗占比）距离上边距的距离为0px;
                    text: '设备能耗占比',
                    subtext: '',
                    x: 'left',//设备能耗占比在div中的位置
                    textStyle: {
                        color: 'black',
                        fontSize:17
                    }
                },
                {
                    top:110,//此处是多加一条title用间距顶到圆形中央显示
                    left:170,
                    text: [
                        '总累计',
                        numAll ,
                    ].join('\n'),
                    subtext: '',
                    x: 'left',//设备能耗占比在div中的位置
                    textStyle: {
                        color: 'blue',
                        fontSize:15
                    }
                },

            ],
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            series: [{
                name: '累计总能耗',
                type: 'pie',
                silent:false,
                //selectedMode: 'single',
                radius: ['0%', '25%'],

                label: {
                    normal: {
                        show:true,
                        position: 'inner',
                        formatter:'{b}\n{c}'
                    }
                },
                data: [
                    {
                        value: numAll,
                        name: '总累计',
                        itemStyle:{
                            color:'#fff' //00BFFF  中部背景颜色（圆），此处设置为了白色是为了和字体颜色一致。否则不同后会显示两个。
                        }

                    }
                ]
            },
                {
                    name: '能耗:',
                    type: 'pie',
                    radius: ['40%', '55%'],
                    label: {
                        normal: {
                            formatter: '{b|{b}}{abg|}\n {hr|}\n {c}  {per|{d}%} ',
                            //backgroundColor: '#9f9f9f',//此处背景色为动态后长方形背景色
                            backgroundColor: '#c8c8c8',
                            borderColor: '#aaa',
                            borderWidth: 1,//边宽
                            borderRadius: 4,//背景方框周边添加弧度为4px;
                            rich: {
                                hr: {
                                    borderColor: '#aaa', //长方形中心线的颜色
                                    width: '90%',
                                    borderWidth: 0.5,
                                    height: 0
                                },
                                b: {
                                    top:10,
                                    align: 'center',
                                    lineHeight: 10
                                },
                                per: {
                                    color: '#fff',//数据百分比的颜色
                                    backgroundColor: '#6b6b6b',//数据百分比的背景色
                                    padding: [2, 4],
                                    borderRadius: 2
                                }
                            }
                        }
                    },
                    data: [{
                        value: num,
                        name: '冷却泵能耗',
                        itemStyle:{
                            color:'#ea12c9',
                            normal:{
                                label:{
                                    show: true,
                                    formatter:function (params) { if(params.value==0){//为0时不显示
                                        return ' '
                                    }else{
                                        return params.value }
                                    }
                                },
                                /* labelLine: {
                                     show: true
                                 }*/
                            }
                        },
                    },
                        {
                            value: num1,
                            name: '冷温泵能耗',
                            itemStyle:{
                                color:'#4ec710',
                                normal:{
                                    label:{
                                        show: false,
                                        formatter:function (params) { if(params.value==0){//为0时不显示
                                            return ' '
                                        }else{
                                            return  8888888 }
                                        }
                                    },
                                    /*  labelLine: {
                                          show: true
                                      }*/
                                }
                            },

                        },
                        {
                            value: num2,
                            name: '主机能耗',
                            itemStyle:{
                                color:'#e09e0b',
                                normal:{
                                    label:{
                                        show: true,
                                        formatter:function (params) { if(params.value==0){//为0时不显示
                                            return ' '
                                        }else{
                                            return params.value }
                                        }
                                    },
                                    /* labelLine: {
                                         show: true
                                     }*/
                                }
                            },

                        },

                    ]
                }
            ]
        };

        myCharts.setOption(option);
    }

    /**
     * 系统能效分布订阅事件
     */
    realTimeData.getEnergyEfficiencyData = function(){
        var $btn = $("#design_area_demo").find(".design_energyEfficiency_btn");
        for(var i = 0; i < $btn.length; i++){
            relativePoint = $($btn[i]).attr("data-config");//关联点
            designFormulas = $($btn[i]).attr("design-formulas");//计算公式
            btnId = $($btn[i]).attr("id");//id
            var list = [];
            if(typeof (relativePoint)!=undefined){
                list.push(relativePoint.split(","));
                if(list.length=="1"){//只有一个点时
                    parent.PubSub.subscribe( list[0],realTimeData.energyEfficiencyOneCallback, 'energyEfficiencyOneCallback');
                }else{//多个点时
                    for(var i =0;i<list.length;i++){
                        parent.PubSub.subscribe( list[i],realTimeData.energyEfficiencyCallback, 'energyEfficiencyCallback');
                    }
                }

            }
        }

    }

    realTimeData.energyEfficiencyOneCallback = function(data){
        var name = data.name;//名称
        var f_init_val = data.value;//数值
        var unit = data.unit;//单位
        var xtzll,xthdl,lwb,lrb,btnText,cop;

        if(btnId==="536a-628b-1ac4-4653-7144"){//系统制冷量
            xtzll = f_init_val;
            btnText = '系统制冷量'+'<br>'+xtzll+unit;
        }else if(btnId==="55da-b4b2-00df-4b3f-0467"){//系统耗电量
            xthdl = f_init_val;
            btnText = '系统耗电量'+'<br>'+xthdl+unit;
        }else if(btnId==="c46d-476f-492d-66bb-e95b"){//冷温泵
            lwb = f_init_val;
            btnText = '冷温泵总耗电量'+'<br>'+lwb+unit;
        }else if(btnId==="2078-7c07-fb75-7890-2d99"){//冷热泵
            lrb = f_init_val;
            btnText =  '冷热泵总耗电量'+'<br>'+lrb+unit;
        }
        document.getElementById(btnId).innerHTML=btnText;

        if(xtzll!=""&&xthdl!=""&&typeof(xtzll)!="undefined"&&typeof (xthdl)!="undefined"&&xthdl>0){
            cop = parseFloat(xtzll/xthdl).toFixed(2);
            document.getElementById("42a9-1224-1a73-6610-34eb").innerHTML="COP"+"<br>"+cop;
        }
        if(xtzll!=""&&lwb!=""&&typeof(xtzll)!="undefined"&&typeof (lwb)!="undefined"&&lwb>0){
            var chw = parseFloat(xtzll/lwb).toFixed(2);
            document.getElementById("15fc-79dc-829c-45b6-9bff").innerHTML="WTFchw"+"<br>"+chw;
        }
        if(xtzll!=""&&lrb!=""&&typeof(xtzll)!="undefined"&&typeof (lrb)!="undefined"&&lrb>0){
            var cw = parseFloat(xtzll/lrb).toFixed(2);
            document.getElementById("e2a6-0c65-3baf-964b-3f36").innerHTML="WTFcw"+"<br>"+cw;
        }
        if(typeof(cop)!="undefined"){
            EnergyEfficiencyEvent.showSystemEnergyEfficiencyCop(cop);
        }
    }

    realTimeData.energyEfficiencyCallback = function(data){
        var name = data.name;//名称
        var f_init_val = data.value;//数值
        var unit = data.unit;//单位
        var xtzll,xthdl,lwb,lrb,btnText;
        var dataId = relativePoint;

        var sysnameArray = [];
        sysnameArray.push(dataId);//关联点位
        sysnameArray.push(btnId);//id
        sysnameArray.push(designFormulas);//计算公式
        sysnameArray.push(name);//变化的点位名称
        sysnameArray.push(f_init_val);//变化的值
        if(dataId){
            $.ajax({
                url     : _ctx + '/view/strongAndWeakElectricityIntegration/energyEfficiencyAssessment/queryRealTimeEnergyEfficiencyBtnInfo',
                type    : "post",
                data: JSON.stringify(sysnameArray),
                async: false,
                contentType: 'application/json;charset=UTF-8',
                success : function(result) {
                    if(result && result.map){
                        if(btnId==="536a-628b-1ac4-4653-7144"){//系统制冷量
                            xtzll = result.map.typeAllData;
                            btnText = '系统制冷量'+'<br>'+xtzll+"kw";
                        }else if(btnId==="55da-b4b2-00df-4b3f-0467"){//系统耗电量
                            xthdl = result.map.typeAllData;
                            btnText = '系统耗电量'+'<br>'+xthdl+"kw";
                        }else if(btnId==="c46d-476f-492d-66bb-e95b"){//冷温泵
                            lwb = result.map.typeAllData;
                            btnText = '冷温泵总耗电量'+'<br>'+lwb+"kw";
                        }else if(btnId==="2078-7c07-fb75-7890-2d99"){//冷热泵
                            lrb = result.map.typeAllData;
                            btnText =  '冷热泵总耗电量'+'<br>'+lrb+"kw";
                        }
                        document.getElementById(btnId).innerHTML=btnText;
                    }
                }
            })

        }


    }
});

