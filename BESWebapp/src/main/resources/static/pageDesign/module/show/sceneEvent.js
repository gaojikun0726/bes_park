/**
 * 添加单通道按钮操作
 */
var SceneEvent = {};
layui.use(['layer','form'], function(){
    var layer = layui.layer;
    var form = layui.form;
    //照明系统 灯--关闭状态值
    var closeState = "0";
    //弹窗
    var sceneWinIndex;
    var sysList = new Array();　//创建一个数组存放sys_name
    var workModeList = new Array();　//创建一个数组存放工作状态
    var nodeAttrList = new Array();　//创建一个数组存放点类型


    $(function(){
        form.render();

    });


    /**
     * 场景按钮初始化
     */
    SceneEvent.initBtn = function (){

        var $btn = $("#design_area_demo").find(".design_scene_btn");
        var idArray = [];
        for(var i = 0; i < $btn.length; i++){
            var dataStr = $($btn[i]).attr("data-config");
            if(dataStr){
                var array = dataStr.split(",");
                idArray = idArray.concat(array);
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
                        var typeDOData = result.map.allData;
                        // var totalData = result.map;
                        // var typeVisualData = totalData.typeVisualData;
                        // var typeDOData = totalData.typeDOData;
                        // typeDOData = typeDOData.concat(typeVisualData);
                        for( i = 0 ; i < $btn.length; i++){
                            var dataStr = $($btn[i]).attr("data-config");
                            if(dataStr){
                                var nameArray = dataStr.split(",");
                                var configData =  $($btn[i]).attr("data-configscene");
                                if(configData){
                                    //解析关联配置数据
                                    var configResult = [];
                                    var array = configData.split(";");
                                    for(var k = 0; k < array.length;k++){
                                        var json = {};
                                        var item = array[k].split(",");
                                        if(item.length === 2){
                                            var sysname = item[0].split(":")[1];
                                            var state = item[1].split(":")[1];
                                            json.state = state;
                                            json.sysname = sysname;
                                            configResult.push(json);
                                        }
                                    }
                                    var realData = [];
                                   for(var j = 0; j < nameArray.length; j++){
                                       for(var n = 0; n < typeDOData.length;n++){
                                           var name = typeDOData[n].F_SYS_NAME;
                                           if(nameArray[j] === name){
                                               realData.push(typeDOData[n]);
                                               break;
                                           }
                                       }
                                   }
                                   //点的值与配置值相同的点的个数
                                   var consistent = 0;
                                   //状态为关的点的个数
                                   var closeNum = 0;
                                   for(j = 0; j < realData.length; j++){
                                       name = realData[j].F_SYS_NAME;
                                       var f_init_val = realData[j].F_INIT_VAL + "";
                                       for(k = 0; k < configResult.length; k++){
                                           var configName = configResult[k].sysname;
                                           var configState = configResult[k].state;
                                           if(name === configName){
                                               if(configState+"" === f_init_val){
                                                   consistent++;
                                               }
                                               if(f_init_val === closeState){
                                                   closeNum++;
                                               }
                                               break;
                                           }
                                       }
                                   }
                                   //去除所有的背景颜色样式，回调时会用
                                    $($btn[i]).removeClass("gray").removeClass("red").removeClass("green");
                                    if(consistent === realData.length){
                                        //与配置值一致，绿色
                                        $($btn[i]).addClass("green");
                                    }else if(closeNum === realData.length){
                                       //全关，灰色
                                       $($btn[i]).addClass("gray");
                                   }else{
                                        $($btn[i]).addClass("brown");
                                   }
                                }
                            }
                        }
                    }
                }
            });
        }
    }



    //按钮的点击事件
    SceneEvent.initButtonEvent =  function (){
        $(".design_scene_btn").click(function(){
            var sceneDataInfo = $(this).attr("data-configScene");
            if(sceneDataInfo){
                // SceneEvent.querySceneInfo(sceneDataInfo);
                SceneEvent.sceneBtnToggle(sceneDataInfo);
            }else{
                layer.msg("请先关联点");
            }
        });

    }

    /**
     * 场景按钮切换命令
     */
    SceneEvent.sceneBtnToggle = function(sceneDataInfo){
        $.ajax({
            url     : _ctx + '/btnEventController/sceneBtnToggle',
            type    : "post",
            dataType: 'json',
            async: false,
            data : {
                sceneData : sceneDataInfo
            },
            success : function(result) {
                if(result && result.map){
                    var message = "";
                    // if(result.map.success && result.map.success + "" !== "0"){
                    //     message += result.map.success+"个通道操作成功";
                    // }
                    // if(result.map.fail && result.map.fail + "" !== "0"){
                    //     message += result.map.fail+"个通道操作失败";
                    // }
                    // if(result.map.error && result.map.error + "" !== "0"){
                    //     message += result.map.fail+"个通道出现故障";
                    // }

                    //，出现故障【"+result.map.error+"】"
                        layer.msg("通道数：操作成功【"+result.map.success+"】，操作失败【"+result.map.fail+"】",{icon:1});
                }else{
                    layer.msg("操作失败",{icon:2});
                }
            },
            error:function(){
                layer.msg("操作失败",{icon:2});
            }
        });
    }



    //查询关联点信息
    SceneEvent.querySceneInfo = function(sceneData){
         sysList = [];　//创建一个数组存放sys_name
         workModeList = [];　//创建一个数组存放工作状态
         nodeAttrList = [];　//创建一个数组存放点类型
        $.ajax({
            url     : _ctx + '/btnEventController/getScenePointInfo',
            type    : "post",
            dataType: 'json',
            async: false,
            data : {
                sysname : sceneData
            },
            success : function(result) {
                $("#showSceneTable tr").not(':eq(0)').empty();
                $("#trShowEle").remove();
                if(result.length>0){
                    for(var i = 0;i<result.length;i++){
                        var status = result[i].status;
                        if(status ==='1'){
                            var resultMap = result[i].pointMap;
                            var trEle = '<tr id = "trShowEle"></tr>';
                            var inpTd =  '<td><input type="text" id="'+resultMap.F_SYS_NAME+'_scene"   value = "'+resultMap.F_SYS_NAME+'"  readonly autocomplete="off" class="layui-input"></td>';
                            //var selTd = '<td><select class="relative_select" id = "'+resultMap.F_SYS_NAME+'_select"><option  value ="0">闭合</option> <option value = "255">断开</option></select></td>';
                            var selTd = '<td><select class="relative_select" id = "'+resultMap.F_SYS_NAME+'_select"></select></td>';
                            $("#showSceneTable").append(trEle+inpTd+selTd);
                            form.render();
                            sysList.push(resultMap.F_SYS_NAME);//向list中添加sys_name
                            workModeList.push(resultMap.F_WORK_MODE);
                            nodeAttrList.push(resultMap.F_NODE_ATTRIBUTION);
                        }
                    }
                   // SceneEvent.sceneWin();
                    SceneEvent.loadSettings(sysList)
                }

            },
            error : function(result) {
            }
        });

    }


    //设置下拉框内容加载
    SceneEvent.loadSettings = function(sysList){
        if(sysList.length>0){
            for(var i= 0;i<sysList.length;i++){
                $("#"+sysList[i]+"_select option").remove();
            }
            $.ajax({
                contentType : "application/json; charset=utf-8",
                type: "post",
                url: _ctx + '/btnEventController/getSceneSettingsInfo',
                dataType : "json",
                data : JSON.stringify(sysList),
                success: function(data){
                    if(data.list.length>0){
                        for(var i = 0;i<sysList.length;i++){
                            for(;i<data.list.length;i++){
                                var obj=data.list[i];
                                if(obj.length>0){
                                    for(var k = 0;k<obj.length;k++){
                                        $("#"+sysList[i]+"_select").append('<option value="'+obj[k].f_value+'">'+obj[k].f_desc+'</option>');
                                    }
                                }else{
                                   continue;
                                }
                            }
                        }
                        SceneEvent.debugExecute();
                        //SceneEvent.sceneWin(sysList);
                    }
                },
                error : function(result) {
                    layer.msg(result.msg,{icon:2});
                }
            })
            form.render();
        }


        };


    //打开调试弹窗
    /*SceneEvent.sceneWin = function pointWin(){
        // todo  根据id获取name信息，填充到 系统名称input框中
        sceneWinIndex = layer.open({
            type: 1,
            title:"调试",
            area:['400px','300px'],
            content: $('#sceneWin'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                SceneEvent.closeSceneWin();
                return false;
            },
            success:function (data) {
                // $("#system_name").val(dataId);
            }
        });
    }*/
    //关闭调试弹窗
    /*SceneEvent.closeSceneWin = function(){
        layer.close(sceneWinIndex);
        //$("#systemPoint_name").val("");
        form.render();
    }*/

    //调试弹窗--执行
    SceneEvent.debugExecute = function () {
        var scenePointList = new Array();
        for(var i= 0;i<sysList.length;i++){
            for(;i<workModeList.length;i++){
                for(;i<nodeAttrList.length;i++){
                    var scenePointInfo = new Array();
                    scenePointInfo.push($("#"+sysList[i]+"_scene").val());//状态
                    scenePointInfo.push($("#"+sysList[i]+"_select  option:selected" ).val());//状态
                    scenePointInfo.push(workModeList[i]);//工作模式
                    scenePointInfo.push(nodeAttrList[i]);//点类型
                    scenePointList.push(scenePointInfo);
                }
            }
        }
        $.ajax({
            contentType : "application/json; charset=utf-8",
            type: "post",
            url: _ctx +"/api/debugScenePointInfo",
            dataType : "json",
            data : JSON.stringify(scenePointList),
            success : function(result) {
                if(result.status === '0'){
                  layer.msg(result.msg,{icon:2});
                }else{
                    layer.msg(result.msg,{icon:1});
                    //SceneEvent.closeSceneWin();
                }
            },
            error : function(result) {
                layer.msg(result.msg,{icon:2});
            }
        });

    }


});
