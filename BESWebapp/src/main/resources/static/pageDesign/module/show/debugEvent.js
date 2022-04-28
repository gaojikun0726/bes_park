/**
 * 调试按钮操作
 */
var DebugEvent = {
    f_type:''
};
layui.use(['layer','form'], function(){
    var layer = layui.layer;
    var form = layui.form;
    //弹窗
    var debugWinIndex;

    // $(function(){
    // });

    //按钮的点击事件
    DebugEvent.initButtonEvent =  function (){
        $(".design_debug_btn").click(function(){
            var dataId = $(this).attr("data-id");
            if(dataId){
                // DebugEvent.debugWin(dataId);
                DebugEvent.queryPointInfo(dataId);
            }else{
                layer.msg("请先关联点");
            }
        });

    }

    //查询关联点信息
    DebugEvent.queryPointInfo = function(dataId){
        $.ajax({
            url     : _ctx + '/btnEventController/getPointInfo',
            type    : "post",
            dataType: 'json',
            data : {
                sysname : dataId
            },
            success : function(result) {
                if(result.status === '1'){
                    if(result.map){
                        var map = result.map;

                        $("#system_name").val(map.F_SYS_NAME || "");
                            $('input[name="work_mode"][value='+ map.F_WORK_MODE +']').prop("checked",true);
                            $("#f_node_attribution").val(map.F_NODE_ATTRIBUTION);
                            $("#debug_input_value").val(map.F_INIT_VAL);

                        DebugEvent.f_type = map.B_TYPE;
                        if(DebugEvent.f_type === 16){
                            DebugEvent.queryVisualType(map,dataId);
                        }else{
                            DebugEvent.getConfigSelectData(map,dataId);
                        }


                    }

                }
            },
            error : function(result) {
            }
        });
    }

    /**
     * 查询虚点类型
     * @param map 关联点信息
     * @param dataId 关联点名称
     */
    DebugEvent.queryVisualType = function(map,dataId){
        $.ajax({
            url     : _ctx + '/btnEventController/getVisualType',
            type    : "post",
            dataType: 'json',
            data : {
                sysname : dataId
            },
            success : function(result) {
                if(result && result.data){
                    var nodeType = result.data;
                    var vType = "";
                    //4,5,6,7  AI,AO,DI,DO
                    if(nodeType === "4"){
                        vType = "VAI";
                    }
                    if(nodeType === "5"){
                        vType = "VAO";
                    }
                    if(nodeType === "6"){
                        vType = "VDI";
                    }
                    if(nodeType === "7"){
                        vType = "VDO";
                    }
                    DebugEvent.getConfigSelectData(map,dataId,vType);
                }
            }});
    }

    // /**
    //  * 点击回调事件
    //  * @param map 关联点信息
    //  * @param dataId 关联点名称
    //  * @param vType 虚点类型
    //  */
    // DebugEvent.clickCallback = function(map,dataId,vType){
    //     if(DebugEvent.f_type === 11 || vType === "VAO"){
    //         //AO类型
    //         // $(".design_unit").text(map.F_ENGINEER_UNIT);
    //         // $("#debug_input_value").val(map.F_INIT_VAL);
    //         DebugEvent.debugWin();
    //     }else if(DebugEvent.f_type === 13 || vType === "VDO"){
    //         //DO类型，查询下拉框配置数据
    //         DebugEvent.getConfigSelectData(map.F_SYS_NAME,map.F_INIT_VAL);
    //     }
    // }

    //打开调试弹窗
    DebugEvent.debugWin = function debugWin(f_type){
        // todo  根据id获取name信息，填充到 系统名称input框中
        debugWinIndex = layer.open({
            type: 1,
            title:"调试",
            area:['34vw','43vh'],
            // area:['400px','300px'],
            content: $('#debugWin'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                DebugEvent.closeDebugWin();
                return false;
            },
            success:function () {
                // if(DebugEvent.f_type == "11"){
                //     //AO类型
                //     $(".debug_switch").hide();
                //     $(".debug_input").show();
                // }else if(DebugEvent.f_type == "13"){
                //     $(".debug_switch").show();
                //     $(".debug_input").hide();
                // }
                // form.render();
            }
        });
    }
    //关闭调试弹窗
    DebugEvent.closeDebugWin = function(){
        layer.close(debugWinIndex);
        $("#system_name").val("");
        $("#debugSet").find("option:first").prop("selected",true);
        $("input[name='work_mode']:eq(0)").prop("checked",true);
        $("#debug_input_value").val("");
        DebugEvent.f_type = "";
        form.render();
    }

    //调试弹窗--执行
    DebugEvent.debugExecute = function () {
        var f_init_val = "";
        if(DebugEvent.f_type + "" === "11" || DebugEvent.f_type + "" === "16"){
            //AO或VAO
            f_init_val = $("#debug_input_value").val();
        }else{
            f_init_val = $("#debugSet").val();
        }

        $.ajax({
            url : _ctx + "/api/debugPointInfo",
            // url : _ctx + "/btnEventController/debugExecute",
            type : "post",
            contentType : "application/json; charset=utf-8",
            dataType : "json",
            data : JSON.stringify({
                f_sys_name : $("#system_name").val(),
                f_work_mode : $('input[name="work_mode"]:checked').val(),
                f_init_val : f_init_val,
                f_node_attribution : $("#f_node_attribution").val()
            }),
            // beforeSend: function () {
            //     showLoad();
            // },
            success : function(result) {

                if(result.status === '0'){
                    if(result.msg){
                        layer.msg(result.msg,{icon:2});
                    }else{
                        layer.msg("执行失败",{icon:2});
                    }
                }else{
                    if(result.msg){
                        layer.msg(result.msg,{icon:1});
                    }else{
                        layer.msg("执行成功",{icon:1});
                    }
                    DebugEvent.closeDebugWin();
                }
            },
            // complete: function () {
            //     hiddenLoad();
            // },
            error : function(result) {
                layer.msg("执行失败",{icon:2});
            }
        });
        $(".layui-layer-shade").remove();//.layer.open关闭的时候，默认只关闭一个遮罩层，但是多次触发会出现多个遮罩层,,剩下的遮罩层会影响页面操作。
                                                                            // 当关闭content连接的标签是，用jquery把所有的遮罩层移除即

    }

    // //查询AO表的工程单位等信息
    // DebugEvent.getAOTableInfo = function(sysname){
    //     $.ajax({
    //         url     : _ctx + '/btnEventController/getDebugInfo',
    //         type    : "post",
    //         dataType: 'json',
    //         data : {
    //             sysname : sysname
    //         },
    //         success : function(result) {
    //             //只有AO或DO表中也存在记录时，才能作为关联点使用
    //             if(result.data && result.data.F_ENGINEER_UNIT){
    //               $(".design_unit").text(result.data.F_ENGINEER_UNIT);
    //             }
    //             DebugEvent.debugWin();
    //         },
    //         error:function(){
    //             layer.msg("操作失败",{icon:2});
    //         }
    //     });
    // }

    /**
     * 查询DO类型配置下拉框数据
     */
    DebugEvent.getConfigSelectData = function(map,dataId,vType){
        var f_init_val = map.F_INIT_VAL;
       // if(map.B_TYPE === 11 || vType === "VAO"){
        if( vType === "VAO"){
            //虚点没有工作模式
            $(".debug_work_mode").hide();
        }else{
            $(".debug_work_mode").show();
        }
        var debugSet = $("#debugSet");
        debugSet.empty();
        $.ajax({
            //selectNodesConfigSetting使用f_sys_name_old查询，修改为/selectDesignNodesConfig（f_sys_name）
            url : _ctx +"/view/basedatamanage/eqmanage/selectDesignNodesConfig",
            type : "post",
            data : {
                f_sys_name :dataId
            },
            success : function(result){
                if(result.list && result.list[0]){
                    //有配置数据，VAO/AO  VDO/DO 处理相同
                    for(var i = 0; i < result.list.length; i++){
                        debugSet.append('<option value="'+result.list[i].F_VALUE+'">'+result.list[i].F_DESC+'</option>');
                    }
                    $(".debug_switch").show();
                    $(".debug_input").hide();
                }else{
                    //没有配置数据，VDO/DO 显示默认的开关机数据，VAO/AO 显示输入框和单位
                    if(map.B_TYPE === 13 || map.B_TYPE === "13" || vType === "VDO"){
                        //没有配置，VDO/DO 采用默认配置：开机关机
                        debugSet.append('<option value="255">开机</option>').append('<option value="0">关机</option>');
                        $(".debug_switch").show();
                        $(".debug_input").hide();
                    }else{
                        $(".design_unit").text(map.F_ENGINEER_UNIT);
                        $("#debug_input_value").val(map.F_INIT_VAL);
                        $(".debug_switch").hide();
                        $(".debug_input").show();
                    }
                }
                debugSet.find("option[value="+ f_init_val+"]").prop('selected',true);
                form.render();
                DebugEvent.debugWin();
            },
            error:function(){
                layer.msg("操作失败",{icon:2});
            }
        });
    }


});
