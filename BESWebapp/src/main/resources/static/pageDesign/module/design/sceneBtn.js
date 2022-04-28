/**
 * 场景按钮操作
 */
var SceneBtn = {};
layui.use(['layer','form'], function(){
    var layer = layui.layer;
    var form = layui.form;
    //新增按钮弹窗,修改按钮弹窗,按钮关联弹窗
    var addBtnIndex,editBtnIndex,relativeIndex;

    var editBtnJquery,relativeBtnJquery;
    var mapSysName = new Map();
    var defaultWidth = 50,defaultHeight = 26;

    //关联的弹窗
    var relativeSceneWinData;

    $(function() {
        contextMenu();
        Map();

    });

        function Map() {
            var obj = {};
            this.put = function (key, value) {
                obj[key] = value;//把键值绑定到obj对象上
            }
            //size方法，获取Map容器的个数
            this.size = function () {
                var count = 0;
                for (var attr in obj) {
                    count++;
                }
                return count;
            }
            //get方法，根据key获取value的值
            this.get = function (key) {
                if (obj[key] || obj[key] === 0 || obj[key] === false) {
                    return obj[key]
                } else {
                    return null;
                }
            }
            //remove方法,删除方法
            this.remove = function (key) {
                if (obj[key] || obj[key] === 0 || obj[key] === false) {
                    delete obj[key]
                }
            }
            //each方法,遍历方法
            this.eachMap = function (callBack) {
                for (var attr in obj) {
                    callBack(attr, obj[attr])
                }
            }
        }
    //右键菜单
    function contextMenu(){
        $.contextMenu({
            selector: '.design_initial_position.design_scene_btn ', //右键选择器
            callback: function(key, options) {//点击回调处理
                var $dom = options.$trigger;
                if(key === "update"){
                    //修改
                    editBtnJquery = $dom;
                    var btnName = options.$trigger.text();
                    var btnWidth = options.$trigger.css('width');
                    var btnHeight = options.$trigger.css('height');
                    btnWidth = btnWidth.replace("px","");
                    btnHeight = btnHeight.replace("px","");
                    $("#design_edit_scene_btn_name").val(btnName);
                    $("#design_edit_scene_btn_width").val(btnWidth);
                    $("#design_edit_scene_btn_height").val(btnHeight);
                    SceneBtn.openEditBtnWin();
                }
                if(key === "relative"){
                    //关联点
                    relativeSceneWinData = $dom;
                    SceneBtn.openRelativeWin();

                }
                if(key === "delete"){
                    //删除
                    layer.confirm('确定删除该按钮吗?', {title:'删除提示'}, function(index){
                        $dom.remove();

                        layer.close(index);
                    });

                }
                // var m = "clicked: " + key;
                // window.console && console.log(m) || alert(m);
            },
            items: {//菜单列表配置
                "update": {name: "修改", icon: "fa-edit",disabled: false},
                "relative": {name: "关联点", icon: "fa-paperclip",disabled:false},
                "delete":{name:"删除",icon:'fa-trash-o'}
            }
        });
    }



    //新增弹窗
    SceneBtn.openAddBtnWin = function(){

        addBtnIndex = layer.open({
            type: 1,
            title:"新增按钮",
            area:['34vw','40vh'],
            // area:['400px','300px'],
            content: $('#design_add_scene_btn_win'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                SceneBtn.closeAddWin();
                return false;
            }
        });
    }


    //添加按钮
    SceneBtn.addBtn = function (){
        var randomId = PageDesign.getUUID();
        //获取页面标志，用于区分是设计页面还是模板编辑页面
        var symbolPoint = $("#symbolChildPage").val();
        var btnName = $("#design_add_scene_btn_name").val();
        var btnWidth = $("#design_add_scene_btn_width").val();
        var btnHeight = $("#design_add_scene_btn_height").val();
        var style = 'style="width:'+btnWidth+'px;height:'+btnHeight+'px;"';

        //向表格的选中单元格内添加元素
        if(AddTable.selectedTable){
            SceneBtn.appendChildBtn(AddTable.selectedTable,style,btnName);
            return;
        }
        //向背景区域添加子元素
        if(BackColor.selectedArea){
            SceneBtn.appendChildBtn(BackColor.selectedArea,style,btnName);
            return;
        }

        if("moduleChildPage" == symbolPoint){
            $("#designModule_area_demo").append('<a id = '+randomId+' class="design_initial_position design_scene_btn gray" '+style+' type="button">'+btnName+'</a>');
        }else{
            $("#design_area_demo").append('<a id = '+randomId+' class="design_initial_position design_scene_btn gray" '+style+' type="button">'+btnName+'</a>');
        }
        $( ".design_area .design_scene_btn" ).unbind("click").click(AttributeWin.clickEvent).draggable();
        $(".design_scene_btn").attr("tabindex",0);
        CopyPaste.copyScene();
        SceneBtn.closeAddWin();
    }

    /**
     * 向表格单元格或背景区域添加子元素
     * @param $parent 表格单元格或背景区域
     * @param style 样式
     * @param btnName 按钮名称
     */
    SceneBtn.appendChildBtn = function($parent,style,btnName){
        $parent.append('<a class="design_initial_position design_scene_btn gray" '+style+'>'+btnName+'</a>');
        $parent.find( ".design_scene_btn" ).unbind("click").click(AttributeWin.clickEvent).draggable({containment: "parent"});
        SceneBtn.closeAddWin();
    }

    //关闭并重置添加弹窗
    SceneBtn.closeAddWin = function(){
        $("#design_add_scene_btn_name").val("");
        $("#design_add_scene_btn_width").val(defaultWidth);
        $("#design_add_scene_btn_height").val(defaultHeight);
        layer.close(addBtnIndex);
    }

    //打开修改弹窗
    SceneBtn.openEditBtnWin = function(){
        editBtnIndex = layer.open({
            type: 1,
            title:"修改按钮",
            area:['34vw','40vh'],
            // area:['400px','300px'],
            content: $('#design_edit_scene_btn_win'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                SceneBtn.closeEditWin();
                return false;
            }
        });
    }

    //修改弹窗--确定按钮
    SceneBtn.editBtn = function (){
        var btnName = $("#design_edit_scene_btn_name").val();
        var btnWidth = $("#design_edit_scene_btn_width").val();
        var btnHeight = $("#design_edit_scene_btn_height").val();
        editBtnJquery.css("width",btnWidth+"px");
        editBtnJquery.css("height",btnHeight+"px");
        editBtnJquery.text(btnName);
        SceneBtn.closeEditWin();
    }


    // 关闭并清空修改弹窗
    SceneBtn.closeEditWin = function(){
        $("#design_edit_scene_btn_name").val("");
        $("#design_edit_scene_btn_width").val("");
        $("#design_edit_scene_btn_height").val("");
        layer.close(editBtnIndex);
    }

    //关联点弹窗
    SceneBtn.openRelativeWin = function(){
        var sceneDataId = relativeSceneWinData.attr("data-configScene");
        var nameData = relativeSceneWinData.attr("data-config");
        relativeIndex = layer.open({
            type: 1,
            title:"按钮关联",
            area:['38vw','42vh'],
            // area:['450px','350px'],
            content: $('#design_scene_btn_relative'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                SceneBtn.closeRelativeWin();
                return false;
            },
            success:function () {
                form.render();
                // $("#inputTable").append("<tr><td>点状态</td><td>状态</td></tr>");
                // $("#inputTable tr").not(':eq(0)').empty();
                if(sceneDataId !=null){
                    var  singlePoint = sceneDataId.split(';');
                    if(singlePoint && singlePoint.length > 0){
                        SceneBtn.initConfigTable(singlePoint);
                        $("#design_scene_btn_relative_name").val(nameData);
                    }
                }
            }
        });
    }

    /**
     * 加载配置表格内容
     * @param singlePoint
     */
    SceneBtn.initConfigTable = function(singlePoint){
        for(var  i = 0;i < singlePoint.length;i++){
            if(!singlePoint[i]){
               continue;
            }
            var trEle = '<tr>';
            // var checkbox = '<td><input type="checkbox" class="layui-input scene_checkbox"></td>';
            var inpTd =  '<td><input type="text" id="'+singlePoint[i]+'_scene"  readonly autocomplete="off" class="layui-input scene_config_name"></td>';
            var selTd = '<td><select class="relative_select" id = "'+singlePoint[i]+'_select"><option  value ="255">闭合</option> <option value = "0">断开</option></select></td>';
            $("#inputTable").append(trEle+inpTd+selTd+"</tr>");
        }
        form.render();
        SceneBtn.fillData(singlePoint);

    }

    /**
     * 配置表格回填数据
     */
    SceneBtn.fillData = function(singlePoint){
        for(var  i = 0;i<singlePoint.length-1;i++){
            var array = singlePoint[i].split(",");
            if(array && array.length === 2){
                var nameItem = array[0].split(":");
                var stateItem = array[1].split(":");
                var sysname = nameItem[1];
                var state = stateItem[1];
                $("#inputTable tr:eq("+(i)+") td:eq(0) input").val(sysname);
                $("#inputTable tr:eq("+(i)+") td:eq(1) select").val(state);
            }

        }
        form.render();
        $("#inputTable tr:eq(0) td:eq(0) input").addClass("design_scene_selected_input");

        SceneBtn.initSelectedEvent();
    }

    /**
     * 加载表格选中行点击事件
     */
    SceneBtn.initSelectedEvent = function(){
        $("#inputTable td:even input.layui-input").unbind("click").click(function(){
            var $tr = $(this).parent().parent("tr");
            $("#inputTable td:even input.design_scene_selected_input").removeClass("design_scene_selected_input");
            $tr.find("td:eq(0) input").addClass("design_scene_selected_input");
        });
    }

    //关闭并清空关联点弹窗
    SceneBtn.closeRelativeWin = function(){
        layer.close(relativeIndex);
        $("#design_scene_btn_relative_id").val("");
        $("#design_scene_btn_relative_name").val("");
        $("#inputTable").empty();
    }

    /**
     * 关联按钮点击事件--保存配置信息
     */
    SceneBtn.relativeBtnClick = function(){
        var $table = $("#inputTable");
        var len = $table.find("tr").length;
        if(len === 0){
            layer.msg("请选择关联点",{icon:0});
            return;
        }
        var configData = "";
        var nameData = "";
        for(var i = 0; i < len; i++){
            var sysname = $table.find("tr:eq("+i+") td:eq(0) input ").val();
            var state = $table.find("tr:eq("+i+") td:eq(1) select ").val();
            var item = "sysname:"+sysname+",state:"+state+";";
            configData += item;
            nameData += sysname + ",";
        }
        if(nameData.length > 1){
            nameData = nameData.substring(0,nameData.length-1);
        }
        relativeSceneWinData.attr("data-configScene",configData).attr("data-config",nameData).attr("title",nameData);
       var  randomId = PageDesign.getUUID();
        relativeSceneWinData.attr("id",randomId);
        SceneBtn.closeRelativeWin();
    }

    // //关联点弹窗--关联按钮点击事件
    // SceneBtn.relativeBtn = function () {
    //     var tab = document.getElementById("inputTable");//获取table对象
    //     var inpEle = tab.getElementsByTagName("input"); //获取table中所有的文本输入控件
    //     var configData = "";
    //     for(var i = 0;i<inpEle.length;i++){
    //         if(i % 2 === 0){//取下标为偶数的值
    //             mapSysName.put(inpEle[i].id,inpEle[i].value);
    //             var item = inpEle[i].value+",";//字符串java文本
    //             configData += item;
    //         }
    //     }
    //     relativeSceneWinData.attr("data-configScene",configData);
    //     SceneBtn.closeRelativeWin();
    // }

    //关联弹窗--选择点按钮点击事件
    SceneBtn.choosePoint = function () {
        CheckboxPointTree.$nodeId = $("#design_scene_btn_relative_id");
        CheckboxPointTree.$nodeName = $("input[name='design_scene_btn_relative_name']");
        CheckboxPointTree.openPointTreeWin();
    }

    /**
     * 删除选中的点
     */
    SceneBtn.removePoint = function(){
        var selected = $("#inputTable td:even input.design_scene_selected_input");
        var $tr = selected.parent().parent("tr");
        $tr.remove();
        $("#inputTable tr:eq(0) td:eq(0) input").addClass("design_scene_selected_input");
        var $table = $("#inputTable");
        var len = $table.find("tr").length - 1;
        var nameData = "";
        for(var i = 0; i <= len; i++){
            var sysname = $table.find("tr:eq("+i+") td:eq(0) input ").val();
            nameData += sysname + ",";
        }
        if(nameData.length > 1){
            nameData = nameData.substring(0,nameData.length-1);
        }
        $("#design_scene_btn_relative_name").val(nameData);
    }


});