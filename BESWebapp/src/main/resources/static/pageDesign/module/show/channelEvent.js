/**
 * 添加单通道按钮操作
 */
var ChannelEvent = {};
layui.use(['layer','form'], function(){
    var layer = layui.layer;
    var form = layui.form;
    //弹窗
    var channelWinIndex;

    $(function(){
        form.render();

    });

    /**
     * 单通道按钮初始化
     */
    ChannelEvent.initBtn = function (){

        var $btn = $("#design_area_demo").find(".design_channel_btn");
        var idArray = [];
        for(var i = 0; i < $btn.length; i++){
            var dataId = $($btn[i]).attr("data-id");
            idArray.push(dataId);
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
                        var typeDOData = result.map.allData;
                        // var totalData = result.map;
                        // var typeVisualData = totalData.typeVisualData;
                        // var typeDOData = totalData.typeDOData;
                        // typeDOData = typeDOData.concat(typeVisualData);
                        for( i = 0 ; i < $btn.length; i++){
                            var dataId = $($btn[i]).attr("data-id");
                            for(var n = 0; n < typeDOData.length;n++){
                                var sysname = typeDOData[n].F_SYS_NAME;
                                var f_init_val = typeDOData[n].F_INIT_VAL + "";
                                if(dataId === sysname){
                                    $($btn[i]).removeClass("gray").removeClass("green").removeClass("red");
                                    if( f_init_val === "0"){//离线
                                        $($btn[i]).addClass("gray");
                                    }else if(f_init_val === "255"){//在线
                                        $($btn[i]).addClass("green");
                                    } else{//故障
                                        $($btn[i]).addClass("red");
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            });
        }
    }

    //按钮的点击事件
    ChannelEvent.initButtonEvent =  function (){
        $(".design_channel_btn").click(function(){
            var dataId = $(this).attr("data-id");
            if(dataId){
                //ChannelEvent.pointWin(dataId);
                ChannelEvent.queryChannelInfo(dataId);
            }else{
                layer.msg("请先关联点");
            }
        });

    }

    //查询关联点信息
    ChannelEvent.queryChannelInfo = function(dataId){
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
                        // $("#systemChannel_name").val(map.F_SYS_NAME);
                        // $("#f_node_attribution").val(map.F_NODE_ATTRIBUTION);
                        // $("#f_workMode").val(map.F_WORK_MODE);
                        // $("#f_initVal").val(map.F_INIT_VAL);
                        form.render();
                        ChannelEvent.debugExecute(map);
                    }

                }
            },
            error : function(result) {
            }
        });

    }


    //调试弹窗--执行
    ChannelEvent.debugExecute = function (map) {
        var f_init_val = map.F_INIT_VAL+"";
        var work_mode = map.F_WORK_MODE;
        var f_node_attribution = map.F_NODE_ATTRIBUTION;
        var f_sysname = map.F_SYS_NAME;
        var value = "";
        if(f_init_val ==='255'||f_init_val=="100"){
            //开
            value = "0";
        // }else if(f_init_val === '0'){
        //     value = "255";
        // }else if(f_init_val === '125'){
        //     value = "255";
        }else{
            //关
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


});