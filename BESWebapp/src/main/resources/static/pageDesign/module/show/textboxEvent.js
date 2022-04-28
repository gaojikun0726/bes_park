/**
 * 文本框运行模式
 */


var TextboxEvent = {
};
layui.use(['layer','form'], function(){
    var layer = layui.layer;
    var form = layui.form;

    /**
     * 更改配置数据获取逻辑--初始化方法
     */
    TextboxEvent.initTextboxNew = function(){
        var $textbox = $("#design_area_demo").find(".design_textbox");
        var idArray = [];
        for(var i = 0; i < $textbox.length; i++){
            var dataId = $($textbox[i]).attr("data-id");
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
                        var typeDIData = result.map.allData;
                        // var totalData = result.map;
                        // var typeAIData = totalData.typeAIData;
                        // var typeDIData = totalData.typeDIData;
                        // var typeDOData = totalData.typeDOData;
                        // var typeVisualData = totalData.typeVisualData;
                        // typeDIData = typeDIData.concat(typeDOData,typeAIData,typeVisualData);
                        for(var i = 0 ; i < $textbox.length; i++){
                            var dataId = $($textbox[i]).attr("data-id");
                            var configType = "";
                            var configData =  $($textbox[i]).attr("data-config");
                            if(configData){
                                //解析关联配置数据
                                var configResult = [];
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
                                // AI/DI/DO类型文本框
                                for(var n = 0; n < typeDIData.length; n++){
                                    var nodeData = typeDIData[n];
                                    var sysname = nodeData.F_SYS_NAME;
                                    var init_val = nodeData.F_INIT_VAL;
                                    if(dataId === sysname){
                                        TextboxEvent.configEffect(init_val,configResult,configType,$($textbox[i]));
                                    }
                                }
                            }else{
                                //没有配置数据，直接显示f_init_val
                                // AI/DI/DO类型文本框
                                for(n = 0; n < typeDIData.length; n++){
                                    nodeData = typeDIData[n];
                                    sysname = nodeData.F_SYS_NAME;
                                    init_val = nodeData.F_INIT_VAL;
                                    var unit = nodeData.F_ENGINEER_UNIT;
                                    if(dataId === sysname){
                                        TextboxEvent.valueEffect(init_val,unit,$($textbox[i]));
                                    }

                                }
                            }
                        }
                    }
                }});
        }
    }

    /**
     * 状态量关联配置解析
     */
    TextboxEvent.stateConfigResult = function(configData){
        var configResult = [];
        var array = configData.split(";");
        for(var k = 0; k < array.length;k++){
            var json = {};
            var item = array[k].split("$");
            if(item.length === 4){
                var state = item[1].split(":")[1];
                var text = item[2].split(":")[1];
                var color = item[3].split(":")[1];
                json.state = state;
                json.text = text;
                json.color = color;
                configResult.push(json);
            }
        }
        return configResult;
    }


    /**
     * 区间量关联配置解析
     */
    TextboxEvent.intervalConfigResult = function(configData){
        var configResult = [];
        var array = configData.split(";");
        for(var k = 0; k < array.length;k++){
            var json = {};
            var item = array[k].split("$");
            if(item.length === 5){
                var min = item[1].split(":")[1];
                var max = item[2].split(":")[1];
                var text = item[3].split(":")[1];
                var color = item[4].split(":")[1];
                json.min = min;
                json.max = max;
                json.text = text;
                json.color = color;
                configResult.push(json);
            }
        }
        return configResult;
    }


    /**
     * 有关联配置，加载配置文本
     */
    TextboxEvent.configEffect = function(init_val,configResult,configType,$dom){
        for(var k = 0; k < configResult.length; k++){
            if(configType === "2"){
                var left = Number(configResult[k].min);
                var right = Number(configResult[k].max);
                var val = Number(init_val);

                if(val >= left && val < right){
                    //区间左闭右开
                    $dom.text(configResult[k].text).css("background-color",configResult[k].color);
                    break;
                }
            } else if(init_val+"" === configResult[k].state){
                $dom.text(configResult[k].text).css("background-color",configResult[k].color);
                break;
            }
        }
    }


    /**
     * 无关联配置，加载点位f_init_val数据
     */
    TextboxEvent.valueEffect = function(init_val,unit,$dom){
        var value = init_val;
        if(unit){
            $dom.text( value + unit);
        }else{
            $dom.text( value);
        }

    }

});