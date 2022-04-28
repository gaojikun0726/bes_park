/**
 * 添加点位置按钮操作
 */
var PointEvent = {};
layui.use(['layer','form'], function(){
    var layer = layui.layer;
    var form = layui.form;
    //弹窗
    var pointWinIndex;

    //保存点击的点位置按钮jquery对象
    var $clickBtn;

    $(function(){
        form.render();

    });

    /**
     * 点位置按钮初始化
     */
    PointEvent.initBtn = function (){

        var $btn = $("#design_area_demo").find(".design_point_btn");
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
                                    $($btn[i]).removeClass("gray");
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
    PointEvent.initButtonEvent =  function (){
        $(".design_point_btn").click(function(){
            var dataId = $(this).attr("data-id");
            $clickBtn = $(this);
            // $(this).addClass();
            // document.getElementById(dataId).setAttribute("class","design_initial_position design_point_btn green ui-draggable ui-draggable-handle");
            if(dataId){
                PointEvent.queryPointInfo(dataId);
            }else{
                layer.msg("请先关联点");
            }
        });

    }

    //查询关联点信息
    PointEvent.queryPointInfo = function(dataId){
        $.ajax({
            url     : _ctx + '/btnEventController/getPointInfo',
            type    : "post",
            dataType: 'json',
            async: false,
            data : {
                sysname : dataId
            },
            success : function(result) {
                if(result.status === '1' && result.map){
                        var map = result.map;
                        PointEvent.debugExecute(dataId,map);
                        // $("#systemPoint_name").val(map.F_SYS_NAME);
                        // $("#f_node_attribution").val(map.F_NODE_ATTRIBUTION);
                        // $("#f_workMode").val(map.F_WORK_MODE);
                        // $("#f_initValue").val(map.F_INIT_VAL);
                        // form.render();
                }else{
                    layer.msg("操作失败",{icon:2});
                }
            },
            error : function(result) {
                layer.msg("操作失败",{icon:2});
            }
        });

    }



    //调试弹窗--执行
    PointEvent.debugExecute = function (dataId,map) {

        /**
         *  如果点击一个按钮，没查询到点位信息，而$("#f_initValue")保存了上一个按钮的数据，
         *  此处用$("#f_initValue")就会取到其他按钮的数据，就会发生问题
         *  var f_init_val = $("#f_initValue").val() + "";
         */

        //初始值
        var f_init_val = map.F_INIT_VAL + "";
        var sysname = map.F_SYS_NAME;
        //工作模式
        var work_mode = map.F_WORK_MODE;
        //系统区分值：楼宇控制
        var f_node_attribution = map.F_NODE_ATTRIBUTION;
        //切换的状态值
        var value = "";
        if( f_init_val === '255'){
            value = "0";
        // }else if(f_init_val === '0'){
        //     value = "255";
        // }else if(f_init_val === '125'){
        //     value = "255";
        }else{
            value = "255";
        }
        $.ajax({
            url : _ctx + "/api/debugPointInfo",
            // url : _ctx + "/btnEventController/debugExecute",
            type : "post",
            contentType : "application/json; charset=utf-8",
            dataType : "json",
            data : JSON.stringify({
                f_sys_name : sysname,
                f_work_mode : work_mode,
                f_init_val : value,
                f_node_attribution : f_node_attribution
            }),
            success : function(result) {
                if(result.status === '1'){
                    $clickBtn.removeClass("gray").removeClass("red").removeClass("green");
                    if( value === "0"){//离线
                        $clickBtn.addClass("gray");
                    }else if(value === "255"){//在线
                        $clickBtn.addClass("green");
                    } else if(value === "125"){//故障
                        $clickBtn.addClass("red");
                    }
                    layer.msg("操作成功",{icon:1});
                    //点位置按钮切换命令后，刷新整个页面---改成 订阅和回调
                    // DisplayPage.getContent();
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