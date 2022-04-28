/**
 * 文本框操作
 */
var AddTextbox = {
    //关联点的节点类型
    nodeType:"",
    //关联点的单位
    unit:"",
    //颜色选择器预设颜色
    colors:['#ffffff','#2A7BC1','#009966','#00CC00','#FFFF66','#CC3333','#FF6666','#FF9966','#CCCCCC','#999999']
};

layui.use(['layer','form','colorpicker'], function(){
    var colorpicker = layui.colorpicker;
    var layer = layui.layer;
    var form = layui.form;
    //新增、修改弹窗
    var relativeIndex,editIndex;

    //修改的文本框Jquery
    var editJquery;
    //关联的文本框Jquery
    var relativeJquery;


    $(function () {
        contextMenu();
        AddTextbox.initEvent();
    });

    //右键菜单jinru
    function contextMenu(){
        $.contextMenu({
            selector: '#design_area_demo .design_textbox', //右键选择器
            callback: function(key, options) {//点击回调处理
                var $dom = options.$trigger;
                if(key === "update"){
                    //修改
                    //修改
                    editJquery = $dom;
                    var width = options.$trigger.css('width');
                    var height = options.$trigger.css('height');
                    width = width.replace("px","");
                    height = height.replace("px","");
                    $("#design_edit_textbox_width").val(width);
                    $("#design_edit_textbox_height").val(height);
                    AddTextbox.openEditWin();
                }
                if(key === "relative"){
                    //关联点
                    relativeJquery = $dom;
                    AddTextbox.openRelativeWin();

                }
                if(key === "delete"){
                    //删除
                    layer.confirm('确定删除该标签吗?', {title:'删除提示'}, function(index){
                        $dom.remove();

                        layer.close(index);
                    });

                }
            },
            items: {//菜单列表配置
                "update": {name: "修改", icon: "fa-edit",disabled: false},
                "relative": {name: "关联点", icon: "fa-paperclip",disabled:false},
                "delete":{name:"删除",icon:'fa-trash-o'}
            }
        });
    }


    /**
     * 添加文本框
     */
    AddTextbox.addTextbox = function (){
        //获取页面标志，用于区分是设计页面还是模板编辑页面
        var symbolPoint = $("#symbolChildPage").val();
        var randomId = PageDesign.getUUID();

        //向表格的选中单元格内添加元素
        if(AddTable.selectedTable){
            AddTextbox.appendChildBtn(AddTable.selectedTable);
            return;
        }
        //向背景区域添加子元素
        if(BackColor.selectedArea){
            AddTextbox.appendChildBtn(BackColor.selectedArea);
            return;
        }

        if("moduleChildPage" == symbolPoint){
            $("#designModule_area_demo").append('<div  id = '+randomId+' class="design_initial_position design_textbox"></div>');
        }else{
            $("#design_area_demo").append('<div  id = '+randomId+' class="design_initial_position design_textbox"></div>');
        }
        $( ".design_area .design_textbox" ).unbind("click").click(AttributeWin.clickEvent).draggable().resizable({handles:"all"});
        $(".design_textbox").attr("tabindex",0);
        CopyPaste.copyTextbox();
    }


    /**
     * 向表格单元格或背景区域添加子元素
     * @param $parent 表格单元格或背景区域
     */
    AddTextbox.appendChildBtn = function($parent){
        $parent.append('<div class="design_initial_position design_textbox"></div>');
        $parent.find( ".design_textbox" ).unbind("click").click(AttributeWin.clickEvent).draggable({containment: "parent"}).resizable({handles:"all"});
    }

    //打开修改弹窗
    AddTextbox.openEditWin = function () {
        editIndex = layer.open({
            type: 1,
            title:"文本框编辑",
            area:['26vw','32vh'],
            // area:['300px','230px'],
            content: $('#design_edit_textbox_win'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                AddTextbox.closeEditWin();
                return false;
            }
        });
    }

    //修改弹窗--确定按钮
    AddTextbox.editConfirm = function(){
        var width = $("#design_edit_textbox_width").val();
        var height = $("#design_edit_textbox_height").val();
        editJquery.css("width",width+"px");
        editJquery.css("height",height+"px");
        layer.close(editIndex);
        AddTextbox.closeEditWin();
    }

    //关闭修改弹窗
    AddTextbox.closeEditWin = function () {
        layer.close(editIndex);
        $("#design_edit_textbox_width").val("");
        $("#design_edit_textbox_height").val("");
    }


    //关联点弹窗
    AddTextbox.openRelativeWin = function(){
        var dataId = relativeJquery.attr("data-id");
        relativeIndex = layer.open({
            type: 1,
            title:"文本框关联",
            area:['58vw','65vh'],
            // area:['600px','500px'],
            content: $('#design_textbox_relative'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                AddTextbox.closeRelativeWin();
                return false;
            },
            success:function () {
                AddTextbox.resetConfig();
                if(dataId){
                    $("#design_textbox_relative_id").val(dataId);
                    $("#design_textbox_relative_name").val(dataId);
                    AddTextbox.initConfigHtml();
                }
                form.render();
            }
        });
    }

    // /**
    //  * 查询关联点信息
    //  */
    // AddTextbox.getNodeInfo = function(dataId){
    //     $.ajax({
    //         url     : _ctx + '/btnEventController/getDebugInfo',
    //         type    : "post",
    //         dataType: 'json',
    //         data : {
    //             sysname : dataId
    //         },
    //         success : function(result) {
    //             if(result.data && result.data.F_NODE_TYPE !== "10"){
    //                 //不是AI类型
    //                 //查询下拉框配置，类似调试按钮DO类型
    //                 AddTextbox.getNodeConfigData(dataId);
    //             }
    //         }});
    // }
    //
    //
    // /**
    //  * 查询节点对应配置下拉框数据
    //  */
    // AddTextbox.getNodeConfigData = function(sysname){
    //
    //     $.ajax({
    //         //selectNodesConfigSetting使用f_sys_name_old查询，修改为/selectDesignNodesConfig（f_sys_name）
    //         url : _ctx +"/view/basedatamanage/eqmanage/selectDesignNodesConfig",
    //         type : "post",
    //         data : {
    //             f_sys_name :sysname
    //         },
    //         success : function(result){
    //             var $table = $("#design_textbox_relative_table");
    //             if(result.list && result.list[0]){
    //
    //                 $table.html("");
    //                 var head = '<tr><td>点状态</td>  <td>关系</td>  <td>显示文本</td>  <td>颜色</td></tr>';
    //                 var colorHtml = '<td><select class="textbox_color"><option value="#fff">白色</option><option value="green">绿色</option><option value="red">红色</option><option value="gray">灰色</option><option value="yellow">黄色</option><option value="orange">橙色</option></select></td>';
    //                 //配置下拉框
    //
    //                 var content = "";
    //                 for(var k = 0; k < result.list.length; k++){
    //                     content+=' <tr><td><select class="textbox_relative_select textbox_state" >';
    //                     // for(var i = 0; i < result.list.length; i++){
    //                     //     $table.append('<option value="'+result.list[i].F_VALUE+'">'+result.list[i].F_DESC+'</option>');
    //                     // }
    //                     var configHtml = '';
    //                     for(var i = 0; i < result.list.length; i++){
    //                         if(i === k){
    //                             configHtml += '<option selected value="'+result.list[i].F_VALUE+'">'+result.list[i].F_DESC+'</option>';
    //                         }else{
    //                             configHtml += '<option value="'+result.list[i].F_VALUE+'">'+result.list[i].F_DESC+'</option>';
    //                         }
    //                     }
    //                     content += configHtml;
    //                     content += '</select></td><td>等于</td><td><input class="layui-input textbox_text" value="'+result.list[k].F_DESC+'"></td>';
    //                     content+= colorHtml;
    //                     content+= '</tr>';
    //                     // $table.append(configHtml);
    //                     // $table.append();
    //                     // $table.append(colorHtml);
    //                     // $table.append('</tr>');
    //                 }
    //                 $table.append(head+content);
    //             }else{
    //                 //如果没有配置数据，显示默认数据
    //                 $table.html($("#design_textbox_default_content").html());
    //             }
    //             // //为保存加载配置数据
    //             // AddTextbox.initConfigEvent();
    //             form.render();
    //
    //             //回显配置数据
    //             AddTextbox.fillConfigData();
    //
    //             $table.show();
    //
    //         },
    //         error:function(){
    //             layer.msg("操作失败",{icon:2});
    //         }
    //     });
    // }
    //
    // /**
    //  * 回显配置数据
    //  */
    // AddTextbox.fillConfigData = function(){
    //     var $table = $("#design_textbox_relative_table");
    //     var configData = relativeJquery.attr("data-config");
    //         if(configData){
    //             var array = configData.split(";");
    //             for(var k = 0; k < array.length;k++){
    //                 var item = array[k].split(",");
    //                 if(item.length === 4){
    //                     var state = item[1].split(":")[1];
    //                     var text = item[2].split(":")[1];
    //                     var color = item[3].split(":")[1];
    //                     $(this).find("tr:eq("+(k+1)+") .textbox_state option[selected=selected]").attr("selected",false);
    //                     $table.find("tr:eq("+(k+1)+") .textbox_state option[value="+state+"]").attr("selected","selected");
    //                     // $table.find("tr:eq("+(k+1)+") .textbox_state").val(state);
    //                     $table.find("tr:eq("+(k+1)+") .textbox_text").val(text);
    //                     $(this).find("tr:eq("+(k+1)+") .textbox_color option[selected=selected]").attr("selected",false);
    //                     $table.find("tr:eq("+(k+1)+") .textbox_color option[value="+color+"]").attr("selected","selected");
    //                     // $table.find("tr:eq("+(k+1)+") .textbox_color").val(color);
    //                 }
    //             }
    //             form.render();
    //         }
    // }
    // /**
    //  * 下拉框和input框保存改变的值
    //  */
    // AddTextbox.initConfigEvent = function(){
    //     // form.on('select', function(data){
    //     //     var classList = data.elem.classList;
    //     //     if(classList.contains("textbox_state") || classList.contains("textbox_color")){
    //     //         $(this).find("option[value="+data.value+"]").attr("selected",true);
    //     //     }
    //     //     // console.log(data.elem); //得到select原始DOM对象
    //     //     // console.log(data.value); //得到被选中的值
    //     //     // console.log(data.othis); //得到美化后的DOM对象
    //     // });
    //
    //     $("#design_textbox_relative_table select").unbind("change").change(function(event){
    //         // $(this).find("option[selected=true]").removeAttribute("selected");
    //         $(this).find("option[selected=selected]").attr("selected",false);
    //         var classList = event.currentTarget.classList;
    //         if(classList.contains("textbox_state") || classList.contains("textbox_color")){
    //             var value = $(this).val();
    //             // $(this).attr("value",value);
    //             $(this).find("option[value="+value+"]").attr("selected",true);
    //         }
    //
    //     });
    //
    //     $("#design_textbox_relative_table .textbox_text").unbind("blur").blur(function(){
    //         var value = $(this).val();
    //         $(this).attr("value",value);
    //     });
    // }

    //关闭并清空关联点弹窗
    AddTextbox.closeRelativeWin = function(){
        layer.close(relativeIndex);
        $("#design_textbox_relative_id").val("");
        $("#design_textbox_relative_name").val("");
        // $("#design_textbox_relative_table").html("");
       AddTextbox.resetConfig();
        form.render();
    }

    /**
     * 还原关联配置初始状态
     */
    AddTextbox.resetConfig = function(){
        $("#design_textbox_relative .intervalDataDiv").html("");
        $("#design_textbox_relative .dataDiv").html("");
        $("#design_textbox_relative input[type=radio][name=configType][value='1']").prop("checked",true);
    }

    //关联点弹窗--关联按钮点击事件
    AddTextbox.relativeBtn = function () {
        var dataId = $("#design_textbox_relative_id").val();
        if(!dataId){
            layer.msg("请选择关联点",{icon:0});
            return;
        }
        relativeJquery.attr("data-id",dataId).attr("title",dataId);
        //将关联配置保存到页面上
        var $table = $("#design_textbox_relative_table");
        var len = $table.find("tr").length - 1;
        var configData = "";
        for(var i = 1; i <= len; i++){
            var state = $table.find("tr:eq("+i+") .textbox_state").val();
            var text = $table.find("tr:eq("+i+") .textbox_text").val();
            var color = $table.find("tr:eq("+i+") .textbox_color").val();
            var item = "dataId:"+dataId+",state:"+state+",text:"+text+",color:"+color+";";
            configData += item;
        }
        relativeJquery.attr("data-config",configData);
        // var html = $table.html();
        // var configSaveTable = $("#design_area_demo").find(".design_config_textbox_table[data-id="+dataId+"]");
        // if(configSaveTable){
        //     configSaveTable.html(html);
        // }else{
        //     $("#design_area_demo").append('<table class="design_config_textbox_table" data-id="'+dataId+'" style="display: none">'+html+'</table>');
        // }
         // $("#design_area_demo").append('<ul style="display:none;" class="design_config_textbox" data-id="'+dataId+'">' +
        //     '<li><span class="state"></span><span class="text"></span><span class="color"></span></li>'+
        //     '</ul>');
        AddTextbox.closeRelativeWin();
    }

    //关联弹窗--选择点按钮点击事件
    AddTextbox.choosePoint = function () {
        PointTree.$nodeId = $("#design_textbox_relative_id");
        PointTree.$nodeName = $("#design_textbox_relative_name");
        PointTree.openPointTreeWin();
    }


    /**
     * 关联点弹窗---关联按钮（新）
     */
    AddTextbox.relativePointBtn = function(){
        var dataId = $("#design_textbox_relative_id").val();
        if(!dataId){
            layer.msg("请选择关联点",{icon:0});
            return;
        }
        var configType = $("#design_textbox_relative input[name=configType]:checked").val();
        if(configType === "1"){
            //状态量
          AddTextbox.stateRelative();
        }else if(configType === "2"){
            //区间量
           AddTextbox.intervalRelative();
        }
    }

    /**
     * 状态量关联方法
     */
    AddTextbox.stateRelative = function(){
        var dataId = $("#design_textbox_relative_id").val();
        var configData = "";
        var rows = $("#design_textbox_relative .configDiv .dataDiv .configRow");
        for(var i = 0; i < rows.length; i++){
            var row = $(rows[i]);
            var text = row.find("input.text").val();
            var num = row.find("input.num").val();
            var color = row.find(".textboxBgColor span.layui-colorpicker-trigger-span").css("background-color");
            var result = AddTextbox.stateCheck(text,num,color);
            if(!result){
                return;
            }
            var item = "dataId:"+dataId+"$state:"+num+"$text:"+text+"$color:"+color+";";
            configData += item;
        }
        relativeJquery.attr("data-id",dataId).attr("title",dataId);
        relativeJquery.attr("data-config",configData);
        AddTextbox.closeRelativeWin();
    }


    /**
     * 区间量关联方法
     */
    AddTextbox.intervalRelative = function(){
        var dataId = $("#design_textbox_relative_id").val();
        var configData = "";
        var rows = $("#design_textbox_relative .configDiv .intervalDataDiv .configRow");
        var intervals = [];
        for(var i = 0; i < rows.length; i++){
            var row = $(rows[i]);
            var text = row.find("input.text").val();
            var min = row.find("input.min").val();
            var max = row.find("input.max").val();
            var color = row.find(".textboxBgColor span.layui-colorpicker-trigger-span").css("background-color");
            var result = AddTextbox.intervalCheck(text,min,max,color);
            if(!result){
                return;
            }
            intervals.push([Number(min),Number(max)]);
            var item = "dataId:"+dataId+"$min:"+min +"$max:"+max+"$text:"+text+"$color:"+color+";";
            configData += item;
        }
        //判断区间范围是否重叠
        var overlap = AddTextbox.checkOverlap(intervals);
        if(overlap){
            return;
        }
        relativeJquery.attr("data-id",dataId).attr("title",dataId);
        relativeJquery.attr("data-config",configData);
        AddTextbox.closeRelativeWin();
    }

    /**
     * 状态量校验
     */
    AddTextbox.stateCheck = function(text,num,color){
        var result = false;
        if(!text){
            layer.msg("显示文本不可为空",{icon:0});
            return result;
        }
        if(!CommonCheck.verifySpecialChar(text,"显示文本")){
            //为空或者包含特殊符号（可能影响config配置字符串解析，[$ , ; :]）
            return result;
        }
        if(!num){
            layer.msg("数值不可为空",{icon:0});
            return result;
        }
        if(!CommonCheck.verifyRealNumber(num,"数值")){
            //为空或者不是数字
            return;
        }
        if(!color){
            layer.msg("背景颜色不可为空",{icon:0});
            return result;
        }
        return true;
    }

    /**
     * 区间量校验
     */
    AddTextbox.intervalCheck = function(text,min,max,color){
        var result = false;
        if(!text){
            layer.msg("显示文本不可为空",{icon:0});
            return result;
        }
        if(!CommonCheck.verifySpecialChar(text,"显示文本")){
            //为空或者包含特殊符号（可能影响config配置字符串解析，[$ , ; :]）
            return result;
        }
        if(!min){
            layer.msg("区间范围不可为空",{icon:0});
            return result;
        }
        if(!CommonCheck.verifyRealNumber(min,"区间范围")){
            //为空或者不是数字
            return;
        }
        if(!max){
            layer.msg("区间范围不可为空",{icon:0});
            return result;
        }
        if(!CommonCheck.verifyRealNumber(max,"区间范围")){
            //为空或者不是数字
            return;
        }
        if(Number(min) > Number(max)){
            layer.msg("区间范围无效",{icon:0});
            return;
        }
        if(!color){
            layer.msg("背景颜色不可为空",{icon:0});
            return result;
        }
        return true;
    }

    /**
     * 检查区间是否有重叠，（端点不算）
     */
    AddTextbox.checkOverlap = function(intervals){
        var overlap = false;
        for(var i = 0 ; i < intervals.length; i++){
            var first = intervals[i];
            for(var j = i + 1; j < intervals.length; j++){
                var second = intervals[j];
                if(!(first[1] <= second[0] || first[0] >= second[1])){
                    layer.msg("区间范围不可重叠",{icon:0});
                  return true;
                }
            }
        }
        return overlap;
    }

    /**
     * 配置弹窗--注册相关监听事件
     */
    AddTextbox.initEvent = function(){
        form.on('radio(configType)', function(data){
            if(data.value === "1"){
                $("#design_textbox_relative .configDiv .stateDiv").show();
                $("#design_textbox_relative .configDiv .intervalDiv").hide();
                $("#design_textbox_relative .configDiv .dataDiv").show();
                $("#design_textbox_relative .configDiv .intervalDataDiv").hide();
            }else if(data.value === "2"){
                $("#design_textbox_relative .configDiv .stateDiv").hide();
                $("#design_textbox_relative .configDiv .intervalDiv").show();
                $("#design_textbox_relative .configDiv .dataDiv").hide();
                $("#design_textbox_relative .configDiv .intervalDataDiv").show();
            }
        });

        $("#design_textbox_relative .configDiv .textboxBgColor").each(function(index){
            var childNum = index + 1;
            var selector = '#design_textbox_relative .configDiv .configRow:nth-child('+childNum+') > .bgDiv > .textboxBgColor';
            //渲染
            colorpicker.render({
                elem: selector  //绑定元素
                // elem: '.textboxBgColor'  //绑定元素
                // ,color:bgColor
                    ,color:"#ffffff"
                // ,color:"#2A7BC1"
                ,predefine: true
                ,colors:AddTextbox.colors
                // ,colors: ['#fff','#2A7BC1','#F00','#0F0','#00F','rgb(255, 69, 0)']
                ,change: function(color){
                    // console.log(color);
                }
                ,done: function(color){
                    // console.log(color);
                    //譬如你可以在回调中把得到的 color 赋值给表单
                }
            });
        });
    }



    /**
     * 加载颜色插件
     */
    AddTextbox.initBgColor = function(colorValue){
        if(!colorValue){
            colorValue = "#ffffff";
        }
        //渲染
        colorpicker.render({
            elem: '#design_textbox_relative .textboxBgColor.add'  //绑定元素
            ,color:colorValue
            // ,color:"#2A7BC1"
            ,predefine: true
            ,colors:AddTextbox.colors
            // ,colors: ['#fff','#2A7BC1','#F00','#0F0','#00F','rgb(255, 69, 0)']
            ,change: function(color){
            }
            ,done: function(color){
            }
        });
        $("#design_textbox_relative .textboxBgColor.add").removeClass("add");
    }

    /**
     * 状态量--新增一行
     */
    AddTextbox.addStateRow = function(){
        var row = '<div class="configRow">\n' +
            '                        <input type="text" class="form-control text">\n' +
            '                        <input type="text" class="form-control num">\n' +
            '                        <div class="bgDiv"><div class="textboxBgColor add"></div></div>\n' +
            '                    </div>';
        // var row = '<div class="configRow">'+
        //     '<input type="text" class="form-control text">' +
        //     '<input type="number" class="form-control num">'+
        //     '</div>';
        $("#design_textbox_relative .dataDiv").append(row);
        form.render();
        AddTextbox.initStateEvent();
        AddTextbox.initBgColor();
    }

    /**
     * 状态量--删除一行
     */
    AddTextbox.deleteStateRow = function(){
        $("#design_textbox_relative .configDiv .dataDiv input.selected_input").parent(".configRow").remove();
    }


    /**
     * 状态量配置数据--选中行点击事件
     */
    AddTextbox.initStateEvent = function(){
        $("#design_textbox_relative .configDiv .dataDiv input").unbind("click").click(function(){
            $("#design_textbox_relative .configDiv .dataDiv input").removeClass("selected_input");
            $(this).addClass("selected_input");
        });
    }


    /**
     * 区间量--新增一行
     */
    AddTextbox.addIntervalRow = function(){
        var dataId = $("#design_textbox_relative_id").val();
        var unit = "";
        if(dataId){
            //如果已有关联点，查询关联点单位
            $.ajax({
                url     : _ctx + '/btnEventController/getDebugInfo',
                type    : "post",
                dataType: 'json',
                data : {
                    sysname : dataId
                },
                success : function(result) {
                    if(result.data){
                        var f_unit = result.data.F_ENGINEER_UNIT;
                        if(f_unit) {
                            unit = f_unit;
                        }
                    }
                    AddTextbox.getIntervalRowHtml(unit);
                }});
        }else{
            //没有关联点，单位为空
            AddTextbox.getIntervalRowHtml(unit);
        }

    }

    /**
     * 新增区间配置行时---拼接行html
     * @param unit
     */
    AddTextbox.getIntervalRowHtml = function(unit){
        var row = '<div class="configRow">\n' +
            '                        <input type="text" class="form-control text">\n' +
            '                        <div class="numDiv">\n' +
            '                            <input type="text" class="min">\n' +
            '                            <span>-</span>\n' +
            '                            <input type="text" class="max">\n' +
            '                            <span class="unit">'+unit+'</span>\n' +
            '                        </div>\n' +
            '                        <div class="bgDiv"><div class="textboxBgColor add"></div></div>\n' +
            '                    </div>';
        // var row = '<div class="configRow">' +
        //     '<input type="text" class="form-control text">' +
        //     '<input type="number" class="form-control num min">' +
        //     '<input type="number" class="form-control num max">' +
        //     '</div>';
        $("#design_textbox_relative .intervalDataDiv").append(row);
        form.render();
        AddTextbox.initIntervalEvent();
        AddTextbox.initBgColor();
    }

    /**
     * 区间量配置数据--选中行点击事件
     */
    AddTextbox.initIntervalEvent = function(){
        $("#design_textbox_relative .configDiv .intervalDataDiv input").unbind("click").click(function(){
            $("#design_textbox_relative .configDiv .intervalDataDiv input").removeClass("selected_input");
            $(this).addClass("selected_input");
        });
    }
    /**
     * 区间量--删除一行
     */
    AddTextbox.deleteIntervalRow = function(){
        $("#design_textbox_relative .configDiv .intervalDataDiv input.selected_input").parent(".configRow").remove();
    }
    /**
     * 判断文本框将要关联的点是否满足条件
     */
    AddTextbox.verifyTextbox = function(){
        var sysname = PointTree.$nodeId.val();
        $.ajax({
            url     : _ctx + '/btnEventController/getDebugInfo',
            type    : "post",
            dataType: 'json',
            data : {
                sysname : sysname
            },
            success : function(result) {
                //只有AI/DI/DO表中也存在记录时，才能作为关联点使用
                if(result.status === "0"){
                    layer.msg("请先配置该点信息",{icon:2});
                    return false;
                }else{
                    if(result.data && result.data.F_NODE_TYPE !== "10"){
                        //不是AI类型，AI类型在设备树不可配置，只能配置区间量
                        //查询下拉框配置，类似调试按钮DO类型
                        // PointTree.getNodeConfigData(sysname);
                        // //选中状态量
                        // $("#design_textbox_relative input[name=configType][type=radio][value='1']").prop("checked",true);
                        // form.render();
                        AddTextbox.initTreeConfigData(sysname,result.data.F_NODE_TYPE);
                    }else{
                        $("#design_textbox_relative .configDiv .dataDiv").html("");
                        $("#design_textbox_relative .configDiv .intervalDataDiv").html("");
                        AddTextbox.nodeType = result.data.F_NODE_TYPE;
                        AddTextbox.unit = result.data.F_ENGINEER_UNIT;
                        // var $table = $("#design_textbox_relative_table");
                        //                         // $table.html("").hide();
                    }
                    PointTree.closeTreeWin();
                    return true;
                }
            }});

    }

    /**
     * 判断文本框将要关联的点是否满足条件(新)
     */
    AddTextbox.verifyTextboxNew = function(){
        var sysname = PointTree.$nodeId.val();
        $.ajax({
            url     : _ctx + '/btnEventController/getPointInfo',
            type    : "post",
            dataType: 'json',
            data : {
                sysname : sysname
            },
            success : function(result) {
                if(result.status === '1' && result.map){
                    var map = result.map;
                    // AddTextbox.nodeType = map.B_TYPE;
                    // AddTextbox.unit = map.F_ENGINEER_UNIT;
                    if(map.B_TYPE === 16){
                        AddTextbox.queryVisualType(map,sysname);
                    }else{
                        AddTextbox.initTreeConfigData(map,sysname);
                    }
                    PointTree.closeTreeWin();
                }else{
                    layer.msg("请先配置该点信息",{icon:2});
                }
            },
            error : function(result) {
            }
        });
    }

    /**
     * 查询虚点类型
     * @param map 关联点信息
     * @param sysname 关联点名称
     */
    AddTextbox.queryVisualType = function(map,sysname){
        $.ajax({
            url     : _ctx + '/btnEventController/getVisualType',
            type    : "post",
            dataType: 'json',
            data : {
                sysname : sysname
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
                    AddTextbox.initTreeConfigData(map,sysname,vType);
                }
            }});
    }


    /**
     * 初始化设备树的配置数据
     */
    AddTextbox.initTreeConfigData = function(map,sysname,vType){
        AddTextbox.resetConfig();
        new Promise(function (resolve, reject){
            $.ajax({
                //selectNodesConfigSetting使用f_sys_name_old查询，修改为/selectDesignNodesConfig（f_sys_name）
                url : _ctx +"/view/basedatamanage/eqmanage/selectDesignNodesConfig",
                type : "post",
                data : {
                    f_sys_name :sysname
                },
                success : function(data){
                    resolve(data);
                }
            });
        }).then(function(result){
            if(result && result.list){
                //有配置数据
                AddTextbox.getConfigHtml(result);
            }else if(map.B_TYPE === "12" || map.B_TYPE === "13" || vType === "VDO"  || vType === "VDI"){
                //DI/DO类型的点有默认配置，开（255）关（0）两种状态
                var content = '<div class="configRow">\n' +
                    '                            <input type="text" class="form-control text" value="开">\n' +
                    '                            <input type="text" class="form-control num" value="255">\n' +
                    '                            <div class="bgDiv"><div class="textboxBgColor add"></div></div>\n' +
                    '                        </div>\n';
                $("#design_textbox_relative .dataDiv").append(content);
                form.render();
                AddTextbox.initBgColor();
                   content =  '<div class="configRow">\n' +
                    '                            <input type="text" class="form-control text" value="关">\n' +
                    '                            <input type="text" class="form-control num" value="0">\n' +
                    '                            <div class="bgDiv"><div class="textboxBgColor add"></div></div>\n' +
                    '                        </div>';
                $("#design_textbox_relative .dataDiv").append(content);
                form.render();
                //加载颜色插件
                AddTextbox.initBgColor();
                AddTextbox.stateShow();
                //状态量配置数据--选中行点击事件
                AddTextbox.initStateEvent();
            }

        });
    }


    /**
     * 拼接配置数据html
     */
    AddTextbox.getConfigHtml = function(result){
        var list = result.list;
        for(var i = 0; i < list.length; i++){
            var row = '<div class="configRow">\n' +
                '                        <input type="text" class="form-control text" value="'+list[i].F_DESC+'">\n' +
                '                        <input type="text" class="form-control num" value="'+list[i].F_VALUE+'">\n' +
                '                        <div class="bgDiv"><div class="textboxBgColor add"></div></div>\n' +
                '                    </div>';
            $("#design_textbox_relative .dataDiv").append(row);
            form.render();
            AddTextbox.initBgColor();
        }
        AddTextbox.stateShow();
        AddTextbox.initStateEvent();
    }


    /**
     * 打开弹窗时--回显配置数据
     */
    AddTextbox.initConfigHtml = function(){
        var dataId = relativeJquery.attr("data-id");
        var configData = relativeJquery.attr("data-config");
        if(configData){
            var configType = "";
            //解析关联配置数据
            var configResult = [];
            var array = configData.split(";");
            //区分状态量和区间量
            if(configData.indexOf("min:") >= 0){
                //区间量
                configType = "2";
                configResult = TextboxEvent.intervalConfigResult(configData);
                AddTextbox.initIntervalHtml(dataId,configResult);
            }else{
                configType = "1";
              configResult = TextboxEvent.stateConfigResult(configData);
                for(var i = 0; i < configResult.length; i++){
                    var row = '<div class="configRow">\n' +
                        '                        <input type="text" class="form-control text" value="'+configResult[i].text+'">\n' +
                        '                        <input type="text" class="form-control num" value="'+configResult[i].state+'">\n' +
                        '                        <div class="bgDiv"><div class="textboxBgColor add"></div></div>\n' +
                        '                    </div>';
                    $("#design_textbox_relative .dataDiv").append(row);
                    form.render();
                    AddTextbox.initBgColor(configResult[i].color);
                }
                AddTextbox.initStateEvent();
              AddTextbox.stateShow();
            }
        }
    }

    AddTextbox.initIntervalHtml = function(dataId,configResult){
        $.ajax({
            url     : _ctx + '/btnEventController/getDebugInfo',
            type    : "post",
            dataType: 'json',
            data : {
                sysname : dataId
            },
            success : function(result) {
                if(result.data){
                    var unit = result.data.F_ENGINEER_UNIT;
                    if(!unit){
                        unit = "";
                    }
                    for(var i = 0; i < configResult.length; i++){
                        var row = '<div class="configRow">\n' +
                            '                        <input type="text" class="form-control text" value="'+configResult[i].text+'">\n' +
                            '                        <div class="numDiv">\n' +
                            '                            <input type="text" class="min" value="'+configResult[i].min+'">\n' +
                            '                            <span>-</span>\n' +
                            '                            <input type="text" class="max" value="'+configResult[i].max+'">\n' +
                            '                            <span class="unit">'+unit+'</span>\n' +
                            '                        </div>\n' +
                            '                        <div class="bgDiv"><div class="textboxBgColor add"></div></div>\n' +
                            '                    </div>';
                        $("#design_textbox_relative .intervalDataDiv").append(row);
                        form.render();
                        AddTextbox.initBgColor(configResult[i].color);
                    }
                    AddTextbox.initIntervalEvent();
                   AddTextbox.intervalShow();
                }
            }});
    }

    /**
     * 显示状态量
     */
    AddTextbox.stateShow = function(){
        $("#design_textbox_relative input[type=radio][name=configType][value='1']").prop("checked",true);
        $("#design_textbox_relative .configDiv .stateDiv").show();
        $("#design_textbox_relative .configDiv .intervalDiv").hide();
        $("#design_textbox_relative .configDiv .dataDiv").show();
        $("#design_textbox_relative .configDiv .intervalDataDiv").hide();
        form.render();
    }

    /**
     * 显示区间量
     */

    AddTextbox.intervalShow =function(){
        $("#design_textbox_relative input[type=radio][name=configType][value='2']").prop("checked",true);
        $("#design_textbox_relative .configDiv .stateDiv").hide();
        $("#design_textbox_relative .configDiv .intervalDiv").show();
        $("#design_textbox_relative .configDiv .dataDiv").hide();
        $("#design_textbox_relative .configDiv .intervalDataDiv").show();
        form.render();
    }
});
