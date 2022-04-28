/**
 * 图片运行模式
 */


var ImgEvent = {
};
layui.use(['layer','form'], function(){
    var layer = layui.layer;
    var form = layui.form;


    /**
     * 初始化所有图片
     */
    ImgEvent.initImg = function(){
        var $image = $("#design_area_demo").find(".design_img");
        var idArray = [];
        for(var i = 0; i < $image.length; i++){
            var dataId = $($image[i]).attr("data-id");
            if(dataId){
                idArray.push(dataId);
            }

        }
        if(idArray.length > 0){

            $.ajax({
                url     : _ctx + '/btnEventController/queryTextboxListInfo',
                type    : "post",
                data: JSON.stringify(idArray),
                async: false,
                contentType: 'application/json;charset=UTF-8',
                success : function(result) {
                    if(result && result.map && result.map.allData){
                        var typeDIData = result.map.allData;
                        // var totalData = result.map;
                        // var typeAOData = totalData.typeAOData;
                        // var typeDIData = totalData.typeDIData;
                        // var typeDOData = totalData.typeDOData;
                        // var typeVisualData = totalData.typeVisualData;
                        // //所有类型的点统一处理
                        // typeDIData = typeDIData.concat(typeDOData,typeAOData,typeVisualData);
                        for(var i = 0 ; i < $image.length; i++){
                            var dataId = $($image[i]).attr("data-id");
                            var configData =  $($image[i]).attr("data-config");
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
                                // AO/DI/DO类型
                                for(var n = 0; n < typeDIData.length; n++){
                                    var sysname = typeDIData[n].F_SYS_NAME;
                                    var init_val = typeDIData[n].F_INIT_VAL;
                                    if(dataId === sysname){
                                       ImgEvent.configEffect(init_val,configResult,configType,$($image[i]));
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
     * 状态量关联配置解析
     */
    ImgEvent.getStateConfigResult = function(configData){
        var configResult = [];
        var array = configData.split(";");
        for(var k = 0; k < array.length;k++){
            if(!array[k]){
                continue;
            }
            var json = {};
            var item = array[k].split(",");
            var state = item[1].split("$")[1];
            var text = item[2].split("$")[1];
            var src = item[3].split("$")[1];
            json.state = state;
            json.text = text;
            json.src = src;
            configResult.push(json);
        }
        return configResult;
    }

    /**
     * 区间量关联配置解析
     */
    ImgEvent.getIntervalConfigResult = function(configData){
        var configResult = [];
        var array = configData.split(";");
        for(var k = 0; k < array.length;k++){
            if(!array[k]){
                continue;
            }
            var json = {};
            var item = array[k].split(",");
            var min = item[1].split("$")[1];
            var max = item[2].split("$")[1];
            var text = item[3].split("$")[1];
            var src = item[4].split("$")[1];
            json.min = min;
            json.max = max;
            json.text = text;
            json.src = src;
            configResult.push(json);
        }
        return configResult;
    }

    /**
     * 有关联配置，加载配置图片
     */
    ImgEvent.configEffect = function(init_val,configResult,configType,$dom){
        if(configResult.length > 0){
            if(configType === "1"){
                init_val = Number(init_val) +"";
                for(var k = 0; k < configResult.length; k++){
                    if(init_val+"" === configResult[k].state+""){
                        var src = configResult[k].src;
                        $dom.attr("src",src);
                        break;
                    }
                }
            }else{
                for(k = 0; k < configResult.length; k++){
                    var value = Number(init_val);
                    min = Number(configResult[k].min);
                    max = Number(configResult[k].max);
                    if(value >= min && value < max){
                        src = configResult[k].src;
                        $dom.attr("src",src);
                        break;
                    }
                }
            }
        }
    }


    /**
     * 处理所有图片（控制同一张gif动图的方法，暂停使用）
     */
    ImgEvent.initLabel = function (){


        var $image = $("#design_area_demo").find(".design_img");
        var idArray = [];
        for(var i = 0; i < $image.length; i++){
            var dataId = $($image[i]).attr("data-id");
            if(dataId){
                idArray.push(dataId);
            }

        }
        if(idArray.length > 0){
            $.ajax({
                url     : _ctx + '/btnEventController/queryTextboxListInfo',
                type    : "post",
                data: JSON.stringify(idArray),
                async: false,
                contentType: 'application/json;charset=UTF-8',
                success : function(result) {
                    if(result && result.map && result.map.allData){
                        var typeDIData = result.map.allData;
                        // var totalData = result.map;
                        // var typeAOData = totalData.typeAOData;
                        // var typeDIData = totalData.typeDIData;
                        // var typeDOData = totalData.typeDOData;
                        // var typeVisualData = totalData.typeVisualData;
                        // typeDIData = typeDIData.concat(typeDOData,typeAOData,typeVisualData);
                        for(var i = 0 ; i < $image.length; i++){
                            var flag = false;
                            var dataId = $($image[i]).attr("data-id");
                            var configData =  $($image[i]).attr("data-config");
                            if(configData){
                                //解析关联配置数据
                                var configResult = [];
                                var array = configData.split(";");
                                for(var k = 0; k < array.length;k++){
                                    var json = {};
                                    var item = array[k].split(",");
                                    if(item.length === 3){
                                        var state = item[1].split(":")[1];
                                        var imgState = item[2].split(":")[1];
                                        json.state = state;
                                        json.imgState = imgState;
                                        configResult.push(json);
                                    }
                                }
                                // DI/DO类型文本框
                                for(var n = 0; n < typeDIData.length; n++){
                                   var sysname = typeDIData[n].F_SYS_NAME;
                                    var init_val = typeDIData[n].F_INIT_VAL;
                                    if(dataId === sysname){
                                        for(k = 0; k < configResult.length; k++){
                                            if(init_val+"" === configResult[k].state+""){
                                                 imgState = configResult[k].imgState;
                                                if(imgState === "run"){
                                                    // $image[i].play();
                                                }
                                                if(imgState === "stop"){
                                                    // var imageElement = $image[i];
                                                    // imageElement.onload = function(){
                                                    //     var rub = new SuperGif({ gif: this} );
                                                    //     rub.load(function(){
                                                    //         rub.pause();
                                                    //     });
                                                    // }
                                                    $($image[i]).on("load",function(){
                                                            this.stop();
                                                        $(this).unbind("load");
                                                    });
                                                }
                                                if(imgState === "high"){
                                                    var imageElement = $image[i];
                                                    imageElement.onload = function(){
                                                        var style = $(this).attr("style");
                                                        var rub = new SuperGif({ gif: this} );
                                                        rub.load(function(){
                                                            var frames = rub.getFrames();
                                                            for(var i = 0; i < frames.length; i++){
                                                                //高速，延迟时间缩小一半
                                                                frames[i].delay = frames[i].delay / 2;
                                                            }
                                                        });
                                                    }
                                                }
                                                if(imgState === "low"){
                                                    imageElement = $image[i];
                                                    imageElement.onload = function(){
                                                        var rub = new SuperGif({ gif: this} );
                                                        rub.load(function(){
                                                            var frames = rub.getFrames();
                                                            for(var i = 0; i < frames.length; i++){
                                                                //低速，延迟时间放大二倍
                                                                frames[i].delay = frames[i].delay * 2;
                                                            }
                                                        });
                                                    }
                                                }
                                                break;
                                            }
                                        }
                                        flag = true;
                                    }
                                    if(flag){
                                        break;
                                    }
                                }
                                // if(flag){
                                //     continue;
                                // }
                            }
                        }
                    }

                }});
        }

    }
    //按钮的点击事件
    ImgEvent.initButtonEvent =  function (){
        $(".design_img").click(function(){
            var dataId = $(this).attr("data-id");
            if(dataId){
                ImgEvent.queryImgInfo(dataId);
            }else{
                layer.msg("请先关联点");
            }
        });

    }

    //查询关联点信息
    ImgEvent.queryImgInfo = function(dataId){
        $.ajax({
            url     : _ctx + '/btnEventController/getPointInfo',
            type    : "post",
            dataType: 'json',
            async: false,
            data : {
                sysname : dataId
            },
            success : function(result) {
                if(result.status === '1'){
                    if(result.map){
                        var map = result.map;
                        var type = map.B_TYPE;
                        if(type+"" === "13"){
                            //DO点--点击下发命令
                            ImgEvent.debugExecute(map);
                        }
                    }
                }
            },
            error : function(result) {
            }
        });

    }


    //下发命令
    ImgEvent.debugExecute = function (map) {
        var f_init_val = map.F_INIT_VAL+"";
        var work_mode = map.F_WORK_MODE;
        var f_node_attribution = map.F_NODE_ATTRIBUTION;
        var f_sysname = map.F_SYS_NAME;
        var value = "";
        if(f_init_val ==='255'|| f_init_val==="100"){
            //开或故障--》关
            value = "0";
        }else{
            //关--》开
            value = "255";
        }
        $.ajax({
            url : _ctx + "/api/debugPointInfo",
            // url : _ctx + "/btnEventController/debugExecute",
            type : "post",
            contentType : "application/json; charset=utf-8",
            dataType : "json",
            data : JSON.stringify({
                f_sys_name : f_sysname,
                f_work_mode : work_mode,
                f_init_val : value,
                f_node_attribution : f_node_attribution
            }),
            success : function(result) {
                if(result.status === '1'){
                    layer.msg("操作成功",{icon:1});
                }else if(result.msg){
                    layer.msg(result.msg,{icon:2});
                }else{
                    layer.msg("操作失败",{icon:2});
                }
            },
            error : function(result) {
                layer.msg("操作失败",{icon:2});
            }
        });

    }



    /**
     * 动图回调方法
     */
    // if(imgState === "run"){
    //     $image[i].play();
    // }
    // if(imgState === "stop"){
    //     $image[i].stop();
    // }
    // var imageElement = $image[i];
    // if(imgState === "high"){
    //     imageElement.onload = function(){
    //         var rub = new SuperGif({ gif: this} );
    //             var frames = rub.getFrames();
    //             for(var i = 0; i < frames.length; i++){
    //                 //高速，延迟时间缩小一半
    //                 frames[i].delay = frames[i].delay / 2;
    //             }
    //     }
    // }
    // if(imgState === "low"){
    //     imageElement.onload = function(){
    //         var rub = new SuperGif({ gif: this} );
    //             var frames = rub.getFrames();
    //             for(var i = 0; i < frames.length; i++){
    //                 //低速，延迟时间放大二倍
    //                 frames[i].delay = frames[i].delay * 2;
    //             }
    //     }
    // }
    // if(imgState === "middle"){
    //     $image[i].play();
    // }

});
