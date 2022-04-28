/**
 * 标签操作
 */
var AddLabel = {
    //关联点的节点类型
    nodeType:"",
    //关联点的单位
    unit:"",
};

layui.use(['layer','form'], function(){
    var layer = layui.layer;
    var form = layui.form;
    //新增、修改弹窗,关联弹窗
    var addIndex,editIndex,relativeIndex;

    //修改的标签Jquery
    var editJquery;
    //关联的标签Jquery
    var relativeJquery;

    $(function () {
        contextMenu();
        AddLabel.initEvent();
    });

    //右键菜单
    function contextMenu(){
        $.contextMenu({
            selector: '#design_area_demo .design_label', //右键选择器
            callback: function(key, options) {//点击回调处理
                var $dom = options.$trigger;
                if(key === "update"){
                    //修改
                    editJquery = $dom;
                    var text = $dom.text();
                    $("#design_edit_label_text").val(text);
                    AddLabel.openEditWin();
                }
                if(key === "relative"){
                    //关联点
                    relativeJquery = $dom;
                    AddLabel.openRelativeWin();

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

    //打开新增弹窗
    AddLabel.openAddWin = function () {
        addIndex = layer.open({
            type: 1,
            title:"添加标签",
            area:['26vw','24vh'],
            // area:['300px','200px'],
            content: $('#design_add_label_win'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                AddLabel.closeAddWin();
                return false;
            }
        });
    }

    //新增弹窗--确定按钮
    AddLabel.addConfirm = function(){
        //获取页面标志，用于区分是设计页面还是模板编辑页面
        var symbolPoint = $("#symbolChildPage").val();
        var text = $("#design_add_label_text").val();
        var randomId = PageDesign.getUUID();
        if(!AddLabel.checkData(text)){
            return false;
        }

        //向表格的选中单元格内添加元素
        if(AddTable.selectedTable){
            AddLabel.appendChildBtn(AddTable.selectedTable,text);
            return;
        }
        //向背景区域添加子元素
        if(BackColor.selectedArea){
            AddLabel.appendChildBtn(BackColor.selectedArea,text);
            return;
        }

        if("moduleChildPage" == symbolPoint){
            $("#designModule_area_demo").append('<span id = '+randomId+' class="design_initial_position design_label">'+text+'</span>');
        }else{
            $("#design_area_demo").append('<span id = '+randomId+' class="design_initial_position design_label">'+text+'</span>');
        }
        $( ".design_area .design_label" ).unbind("click").click(AttributeWin.clickEvent).draggable();
        $(".design_label").attr("tabindex",0);
        CopyPaste.copyLabel();
        //.resizable({handles:"all"})
        AddLabel.closeAddWin();
    }

    /**
     * 向表格单元格或背景区域添加子元素
     * @param $parent 表格单元格或背景区域
     * @param text 标签名称
     */
    AddLabel.appendChildBtn = function($parent,text){
        $parent.append('<span class="design_initial_position design_label">'+text+'</span>');
        $parent.find( ".design_label" ).unbind("click").click(AttributeWin.clickEvent).draggable({containment: "parent"});
        AddLabel.closeAddWin();
    }


    /**
     * 校验数据
     * @param text
     */
    AddLabel.checkData = function(text){
        var result = CommonCheck.checkEmpty(text,"标签文本");
       return result;
    }

    //关闭新增弹窗
    AddLabel.closeAddWin = function () {
        layer.close(addIndex);
        $("#design_add_label_text").val("");
    }


    //打开修改弹窗
    AddLabel.openEditWin = function () {
        editIndex = layer.open({
            type: 1,
            title:"标签编辑",
            area:['300px','200px'],
            content: $('#design_edit_label_win'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                AddLabel.closeEditWin();
                return false;
            }
        });
    }

    //修改弹窗--确定按钮
    AddLabel.editConfirm = function(){
        var text = $("#design_edit_label_text").val();
        if(!AddLabel.checkData(text)){
            return false;
        }
        editJquery.text(text);
        AddLabel.closeEditWin();
    }

    //关闭修改弹窗
    AddLabel.closeEditWin = function () {
        layer.close(editIndex);
        $("#design_edit_label_text").val("");
    }


    //关联点弹窗
    AddLabel.openRelativeWin = function(){
        var dataId = relativeJquery.attr("data-id");
        relativeIndex = layer.open({
            type: 1,
            title:"标签关联",
            area:['48vw','63vh'],
            // area:['550px','500px'],
            content: $('#design_label_relative'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                AddLabel.closeRelativeWin();
                return false;
            },
            success:function () {
                AddLabel.resetConfig();
                if(dataId){
                    $("#design_label_relative_id").val(dataId);

                    $("#design_label_relative_name").val(dataId);
                    // AddLabel.getNodeInfo(dataId);
                    AddLabel.initConfigHtml();
                }
                form.render();
            }
        });
    }

    // /**
    //  * 查询关联点信息
    //  */
    // AddLabel.getNodeInfo = function(dataId){
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
    //                 AddLabel.getConfigHtml(dataId);
    //             }
    //         }});
    // }


    // /**
    //  * 查询节点对应配置下拉框数据
    //  */
    // AddLabel.getConfigHtml = function(sysname){
    //
    //     $.ajax({
    //         url : _ctx +"/view/basedatamanage/eqmanage/selectNodesConfigSetting",
    //         type : "post",
    //         data : {
    //             f_sys_name :sysname
    //         },
    //         success : function(result){
    //             var $table = $("#design_label_relative_table");
    //             if(result.list && result.list[0]){
    //
    //                 $table.html("");
    //                 var head = '<tr><td>点状态</td>  <td>关系</td>  <td>显示文本</td></tr>';
    //                 //配置下拉框
    //                 var configHtml = '';
    //                 for(var i = 0; i < result.list.length; i++){
    //                     configHtml += '<option value="'+result.list[i].F_VALUE+'">'+result.list[i].F_DESC+'</option>';
    //                 }
    //                 var content = "";
    //                 for(var k = 0; k < result.list.length; k++){
    //                     content+=' <tr><td><select class="label_relative_select label_state" >';
    //                     content += configHtml;
    //                     content += '</select></td><td>等于</td><td><input class="layui-input label_text" value="'+result.list[k].F_DESC+'"></td>';
    //                     content+= '</tr>';
    //
    //                 }
    //                 $table.append(head+content);
    //             }else{
    //                 //如果没有配置数据，显示默认数据
    //                 $table.html($("#design_label_default_content").html());
    //             }
    //             // //为保存加载配置数据
    //             // AddLabel.initConfigEvent();
    //             form.render();
    //
    //             //回显配置数据
    //             AddLabel.fillConfigData();
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
    // AddLabel.fillConfigData = function(){
    //     var $table = $("#design_label_relative_table");
    //     var configData = relativeJquery.attr("data-config");
    //     if(configData){
    //         var array = configData.split(";");
    //         for(var k = 0; k < array.length;k++){
    //             var item = array[k].split(",");
    //             if(item.length === 3){
    //                 var state = item[1].split(":")[1];
    //                 var text = item[2].split(":")[1];
    //                 $(this).find("tr:eq("+(k+1)+") .label_state option[selected=selected]").attr("selected",false);
    //                 $table.find("tr:eq("+(k+1)+") .label_state option[value="+state+"]").attr("selected","selected");
    //                 $table.find("tr:eq("+(k+1)+") .label_text").val(text);
    //             }
    //         }
    //         form.render();
    //     }
    // }

    //关闭并清空关联点弹窗
    AddLabel.closeRelativeWin = function(){
        $("#design_label_relative_id").val("");
        $("#design_label_relative_name").val("");
        // $("#design_label_relative_table").html("");
        AddLabel.resetConfig();
        form.render();
        layer.close(relativeIndex);
    }

    /**
     * 还原关联配置初始状态
     */
    AddLabel.resetConfig = function(){
        $("#design_label_relative .configDiv .dataDiv").html("");
        $("#design_label_relative .configDiv .intervalDataDiv").html("");
        $("#design_label_relative input[name=labelConfigType][type=radio][value='1']").prop("checked",true);
    }

    //关联点弹窗--关联按钮点击事件
    AddLabel.relativeBtn = function () {
        var dataId = $("#design_label_relative_id").val();
        if(!dataId){
            layer.msg("请选择关联点",{icon:0});
            return;
        }
        relativeJquery.attr("data-id",dataId).attr("title",dataId);
        //将关联配置保存到页面上
        var $table = $("#design_label_relative_table");
        var len = $table.find("tr").length - 1;
        var configData = "";
        for(var i = 1; i <= len; i++){
            var state = $table.find("tr:eq("+i+") .label_state").val();
            var text = $table.find("tr:eq("+i+") .label_text").val();
            // var color = $table.find("tr:eq("+i+") .label_color").val();
            var item = "dataId:"+dataId+",state:"+state+",text:"+text+";";
            configData += item;
        }
        relativeJquery.attr("data-config",configData);
        AddLabel.closeRelativeWin();
    }

    //关联弹窗--选择点按钮点击事件
    AddLabel.choosePoint = function () {
        PointTree.$nodeId = $("#design_label_relative_id");
        PointTree.$nodeName = $("#design_label_relative_name");
        PointTree.openPointTreeWin();
    }

    /**
     * 判断标签将要关联的点是否满足条件(新)
     */
    AddLabel.verifyLabelNew = function(){
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
                    // AddLabel.nodeType = map.B_TYPE;
                    // AddLabel.unit = map.F_ENGINEER_UNIT;
                    if(map.B_TYPE === 16){
                        AddLabel.queryVisualType(map,sysname);
                    }else{
                        AddLabel.initTreeConfigData(map,sysname);
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
    AddLabel.queryVisualType = function(map,sysname){
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
                    AddLabel.initTreeConfigData(map,sysname,vType);
                }
            }});
    }

    /**
     * 判断标签将要关联的点是否满足条件
     */
    AddLabel.verifyLabel = function(){
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
                        //不是AI类型
                        //查询标签配置，类似调试按钮DO类型
                        // AddLabel.getNodeConfigData(sysname);
                        //选中状态量
                        $("#design_label_relative input[name=labelConfigType][type=radio][value='1']").prop("checked",true);
                        form.render();
                        AddLabel.initTreeConfigData(sysname,result.data.F_NODE_TYPE);
                    }else{
                        // var $table = $("#design_label_relative_table");
                        // $table.html("").hide();
                        $("#design_label_relative .configDiv .dataDiv").html("");
                        $("#design_label_relative .configDiv .intervalDataDiv").html("");
                        AddLabel.nodeType = result.data.F_NODE_TYPE;
                        AddLabel.unit = result.data.F_ENGINEER_UNIT;
                    }
                    PointTree.closeTreeWin();
                    return true;
                }
            }});

    }


    // /**
    //  * 查询节点对应配置下拉框数据
    //  */
    // AddLabel.getNodeConfigData = function(sysname){
    //
    //     $.ajax({
    //         //selectNodesConfigSetting使用f_sys_name_old查询，修改为/selectDesignNodesConfig（f_sys_name）
    //         url : _ctx +"/view/basedatamanage/eqmanage/selectDesignNodesConfig",
    //         type : "post",
    //         data : {
    //             f_sys_name :sysname
    //         },
    //         success : function(result){
    //             var $table = $("#design_label_relative_table");
    //             if(result.list && result.list[0]){
    //
    //                 $table.html("");
    //                 var head = '<tr><td>点状态</td>  <td>关系</td>  <td>显示文本</td></tr>';
    //                 //配置下拉框
    //                 var configHtml = '';
    //                 for(var i = 0; i < result.list.length; i++){
    //                     configHtml += '<option value="'+result.list[i].F_VALUE+'">'+result.list[i].F_DESC+'</option>';
    //                 }
    //                 var content = "";
    //                 for(var k = 0; k < result.list.length; k++){
    //                     content+=' <tr><td><select class="label_relative_select label_state">';
    //                     content += configHtml;
    //                     content += '</select></td><td>等于</td><td><input class="layui-input label_text" value="'+result.list[k].F_DESC+'"></td>';
    //                     content+= '</tr>';
    //                 }
    //                 $table.append(head+content);
    //             }else{
    //                 //如果没有配置数据，显示默认数据
    //                 $table.html($("#design_label_default_content").html());
    //             }
    //
    //             //下拉框默认选中
    //             var selectArray = $table.find("select.label_state");
    //             for(i = 0; i < selectArray.length; i++){
    //                 $(selectArray[i]).find("option:eq("+i+")").prop("selected",true);
    //             }
    //
    //             $table.show();
    //             form.render();
    //         },
    //         error:function(){
    //             layer.msg("操作失败",{icon:2});
    //         }
    //     });
    // }


    /**
     * 关联点弹窗---关联按钮（新）
     */
    AddLabel.relativePointBtn = function(){
        var dataId = $("#design_label_relative_id").val();
        if(!dataId){
            layer.msg("请选择关联点",{icon:0});
            return;
        }
        var labelConfigType = $("#design_label_relative input[name=labelConfigType]:checked").val();
        if(labelConfigType === "1"){
            //状态量
           AddLabel.stateRelative();
        }else if(labelConfigType === "2"){
            //区间量
            AddLabel.intervalRelative();
        }
    }


    /**
     * 状态量关联方法
     */
    AddLabel.stateRelative = function(){
        var dataId = $("#design_label_relative_id").val();
        var configData = "";
        var rows = $("#design_label_relative .configDiv .dataDiv .configRow");
        for(var i = 0; i < rows.length; i++){
            var row = $(rows[i]);
            var text = row.find("input.text").val();
            var num = row.find("input.num").val();
            var result = AddLabel.stateCheck(text,num);
            if(!result){
                return;
            }
            var item = "dataId:"+dataId+"$state:"+num+"$text:"+text+";";
            configData += item;
        }
        relativeJquery.attr("data-id",dataId).attr("title",dataId);
        relativeJquery.attr("data-config",configData);
        AddLabel.closeRelativeWin();
    }

    /**
     * 区间量关联方法
     */
    AddLabel.intervalRelative = function(){
        var dataId = $("#design_label_relative_id").val();
        var configData = "";
        var rows = $("#design_label_relative .configDiv .intervalDataDiv .configRow");
        var intervals = [];
        for(var i = 0; i < rows.length; i++){
            var row = $(rows[i]);
            var text = row.find("input.text").val();
            var min = row.find("input.min").val();
            var max = row.find("input.max").val();
            var result = AddLabel.intervalCheck(text,min,max);
            if(!result){
                return;
            }
            intervals.push([Number(min),Number(max)]);
            var item = "dataId:"+dataId+"$min:"+min +"$max:"+max+"$text:"+text+";";
            configData += item;
        }
        //判断区间范围是否重叠
        var overlap = AddTextbox.checkOverlap(intervals);
        if(overlap){
            return;
        }
        relativeJquery.attr("data-id",dataId).attr("title",dataId);
        relativeJquery.attr("data-config",configData);
        AddLabel.closeRelativeWin();
    }


    /**
     * 状态量校验
     */
    AddLabel.stateCheck = function(text,num){
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
        return true;
    }

    /**
     * 区间量校验
     */
    AddLabel.intervalCheck = function(text,min,max,color){
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
        return true;
    }

    /**
     * 配置弹窗--注册相关监听事件
     */
    AddLabel.initEvent = function(){
        form.on('radio(labelConfigType)', function(data){
            // AddLabel.toggleDiv();
            if(data.value === "1"){
                $("#design_label_relative .configDiv .stateDiv").show();
                $("#design_label_relative .configDiv .intervalDiv").hide();
                $("#design_label_relative .configDiv .dataDiv").show();
                $("#design_label_relative .configDiv .intervalDataDiv").hide();
            }else if(data.value === "2"){
                $("#design_label_relative .configDiv .stateDiv").hide();
                $("#design_label_relative .configDiv .intervalDiv").show();
                $("#design_label_relative .configDiv .dataDiv").hide();
                $("#design_label_relative .configDiv .intervalDataDiv").show();
            }
        });
    }

    /**
     * 切换两种类型的配置显示
     */
    AddLabel.toggleDiv = function(){
        $("#design_label_relative .configDiv .stateDiv").toggle();
        $("#design_label_relative .configDiv .intervalDiv").toggle();
        $("#design_label_relative .configDiv .dataDiv").toggle();
        $("#design_label_relative .configDiv .intervalDataDiv").toggle();
    }


    /**
     * 状态量--新增一行
     */
    AddLabel.addStateRow = function(){
        var row = '<div class="configRow">\n' +
            '                        <input type="text" class="form-control text">\n' +
            '                        <input type="text" class="form-control num">\n' +
            '                    </div>';
        $("#design_label_relative .dataDiv").append(row);
        form.render();
        AddLabel.initStateEvent();
    }

    /**
     * 状态量--删除一行
     */
    AddLabel.deleteStateRow = function(){
        $("#design_label_relative .configDiv .dataDiv input.selected_input").parent(".configRow").remove();
    }


    /**
     * 状态量配置数据--选中行点击事件
     */
    AddLabel.initStateEvent = function(){
        $("#design_label_relative .configDiv .dataDiv input").unbind("click").click(function(){
            $("#design_label_relative .configDiv .dataDiv input").removeClass("selected_input");
            $(this).addClass("selected_input");
        });
    }


    /**
     * 区间量--新增一行
     */
    AddLabel.addIntervalRow = function(){
        var dataId = $("#design_label_relative_id").val();
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
                        if(f_unit){
                            unit = f_unit;
                        }
                    }
                    AddLabel.getIntervalRowHtml(unit);
                }});
        }else{
            //没有关联点，单位为空
            AddLabel.getIntervalRowHtml(unit);
        }

    }

    /**
     * 新增区间配置行时---拼接行html
     * @param unit
     */
    AddLabel.getIntervalRowHtml = function(unit){
        var row = '<div class="configRow">\n' +
            '                        <input type="text" class="form-control text">\n' +
            '                        <div class="numDiv">\n' +
            '                            <input type="text" class="min">\n' +
            '                            <span>-</span>\n' +
            '                            <input type="text" class="max">\n' +
            '                            <span class="unit">'+unit+'</span>\n' +
            '                        </div>\n' +
            '                    </div>';
        $("#design_label_relative .intervalDataDiv").append(row);
        form.render();
        AddLabel.initIntervalEvent();
    }

    /**
     * 区间量配置数据--选中行点击事件
     */
    AddLabel.initIntervalEvent = function(){
        $("#design_label_relative .configDiv .intervalDataDiv input").unbind("click").click(function(){
            $("#design_label_relative .configDiv .intervalDataDiv input").removeClass("selected_input");
            $(this).addClass("selected_input");
        });
    }
    /**
     * 区间量--删除一行
     */
    AddLabel.deleteIntervalRow = function(){
        $("#design_label_relative .configDiv .intervalDataDiv input.selected_input").parent(".configRow").remove();
    }


    /**
     * 初始化设备树的配置数据
     */
    AddLabel.initTreeConfigData = function(map,sysname,vType){
        AddLabel.resetConfig();
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
                AddLabel.getConfigHtml(result);
            }else if(map.B_TYPE === "12" || map.B_TYPE === "13" || vType === "VDI" || vType === "VDO"){
                //DI/DO类型的点有默认配置，开（255）关（0）两种状态
                var content = '<div class="configRow">\n' +
                    '                            <input type="text" class="form-control text" value="开">\n' +
                    '                            <input type="text" class="form-control num" value="255">\n' +
                    '                        </div>\n';
                $("#design_label_relative .dataDiv").append(content);
                content =  '<div class="configRow">\n' +
                    '                            <input type="text" class="form-control text" value="关">\n' +
                    '                            <input type="text" class="form-control num" value="0">\n' +
                    '                        </div>';
                $("#design_label_relative .dataDiv").append(content);
                form.render();
                AddLabel.stateShow();
                AddLabel.initStateEvent();
            }

        });
    }


    /**
     * 拼接配置数据html
     */
    AddLabel.getConfigHtml = function(result){
        var list = result.list;
        for(var i = 0; i < list.length; i++){
            var row = '<div class="configRow">\n' +
                '                        <input type="text" class="form-control text" value="'+list[i].F_DESC+'">\n' +
                '                        <input type="text" class="form-control num" value="'+list[i].F_VALUE+'">\n' +
                '                    </div>';
            $("#design_label_relative .dataDiv").append(row);
            form.render();
        }
        AddLabel.stateShow();
        AddLabel.initStateEvent();
    }


    /**
     * 打开弹窗时--回显配置数据
     */
    AddLabel.initConfigHtml = function(){
        var dataId = relativeJquery.attr("data-id");
        var configData = relativeJquery.attr("data-config");
        if(configData){
            //解析关联配置数据
            var configResult = [];
            // var array = configData.split(";");
            //区分状态量和区间量
            if(configData.indexOf("min:") >= 0){
                //区间量
                configResult = LabelEvent.intervalConfigResult(configData);
                AddLabel.initIntervalHtml(dataId,configResult);
            }else{
                configResult = LabelEvent.stateConfigResult(configData);
                for(var i = 0; i < configResult.length; i++){
                    var row = '<div class="configRow">\n' +
                        '                        <input type="text" class="form-control text" value="'+configResult[i].text+'">\n' +
                        '                        <input type="text" class="form-control num" value="'+configResult[i].state+'">\n' +
                        '                    </div>';
                    $("#design_label_relative .dataDiv").append(row);
                    form.render();
                }
                AddLabel.initStateEvent();
               AddLabel.stateShow();
            }
        }
    }

    AddLabel.initIntervalHtml = function(dataId,configResult){
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
                            '                    </div>';
                        $("#design_label_relative .intervalDataDiv").append(row);
                        form.render();
                    }
                    AddLabel.initIntervalEvent();
                   AddLabel.intervalShow();
                }
            }});
    }

    /**
     * 显示状态量
     */
    AddLabel.stateShow = function(){
        $("#design_label_relative input[type=radio][name=labelConfigType][value='1']").prop("checked",true);
        $("#design_label_relative .configDiv .stateDiv").show();
        $("#design_label_relative .configDiv .intervalDiv").hide();
        $("#design_label_relative .configDiv .dataDiv").show();
        $("#design_label_relative .configDiv .intervalDataDiv").hide();
        form.render();
    }

    /**
     * 显示区间量
     */
    AddLabel.intervalShow = function(){
        $("#design_label_relative input[type=radio][name=labelConfigType][value='2']").prop("checked",true);
        $("#design_label_relative .configDiv .stateDiv").hide();
        $("#design_label_relative .configDiv .intervalDiv").show();
        $("#design_label_relative .configDiv .dataDiv").hide();
        $("#design_label_relative .configDiv .intervalDataDiv").show();
        form.render();
    }
});
