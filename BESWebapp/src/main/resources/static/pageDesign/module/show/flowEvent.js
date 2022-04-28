/**
 * 流程图操作
 */
var FlowEvent = {
};
layui.use(['layer','form'], function(){
    var layer = layui.layer;
    var form = layui.form;



    FlowEvent.initFlowLabel = function (){
        var $textbox = $("#design_area_demo").find(".design_flow_btn");
        var idArray = [];
        for(var i = 0; i < $textbox.length; i++){
            var dataId = $($textbox[i]).data("id");
            idArray.push(dataId);
        }
        if(idArray.length > 0){
            $.ajax({
                url     : _ctx + '/btnEventController/queryFlowPointListInfo',
                type    : "post",
                data: JSON.stringify(idArray),
                async: false,
                contentType: 'application/json;charset=UTF-8',
                // data:{
                //     sysnameArray:idArray
                // },
                success : function(result) {
                    if(result && result.map && result.map.typeAIData){
                        var totalData = result.map;
                        var typeAIData = totalData.typeAIData;
                        var typeAOData = totalData.typeAOData;
                        var typeDIData = totalData.typeDIData;
                        var typeDOData = totalData.typeDOData;
                        typeDIData = typeDIData.concat(typeDOData);
                        typeAIData =  typeAIData.concat(typeAOData);
                        for(var i = 0 ; i < $textbox.length; i++){
                            var flag = false;
                            var dataId = $($textbox[i]).data("id");
                            //AI类型文本框
                            for(var n = 0; n < typeAIData.length;n++){
                                var sysname = typeAIData[n].F_SYS_NAME;
                                var accuracy = typeAIData[n].F_ACCURACY;
                                if(dataId === sysname){
                                    var value = typeAIData[n].F_INIT_VAL;
                                    if(accuracy){
                                        value = value/ (Math.pow(10, (accuracy)));
                                    }
                                    $($textbox[i]).text(value + typeAIData[n].F_ENGINEER_UNIT);
                                    flag = true;
                                    break;
                                }
                            }
                            if(flag){
                                continue;
                            }
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
                                // DI/DO类型文本框

                                for(n = 0; n < typeDIData.length; n++){
                                    sysname = typeDIData[n].F_SYS_NAME;
                                    var init_val = typeDIData[n].F_INIT_VAL;
                                    if(dataId === sysname){
                                        for(k = 0; k < configResult.length; k++){
                                            if(init_val == configResult[k].state){
                                                $($textbox[i]).text(configResult[k].text);
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







});