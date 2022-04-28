/**
 * 系统能效按钮添加
 */
var AddEnergyEfficiency = {

};

layui.use(['layer','form','jquery'], function(){
    var layer = layui.layer;
    var form = layui.form;
    var $ = layui.jquery;
    var addEnergyEfficiencyIndex,editBtnJquery,relativeEneryEfficiencyWinData,relativeIndex,editBtnIndex;
    var pieRelativeEneryEfficiencyWinData;
    var defaultWidth = 50,defaultHeight = 26;

    $(function() {
        initMenu();

    });

    //新增弹窗
    AddEnergyEfficiency.openEnergyEfficiencyWin = function(){
        addEnergyEfficiencyIndex = layer.open({
            type: 1,
            title:"新增系统能效按钮",
            area:['400px','300px'],
            content: $('#design_addEnergyEfficiencyWin'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                AddEnergyEfficiency.closeEnergyEfficiencyWin();
                return false;
            }
        });
    }
    /**
     * 关闭添加弹框
     */
    AddEnergyEfficiency.closeEnergyEfficiencyWin = function(){
        layer.close(addEnergyEfficiencyIndex);
    }

    //右键菜单
    function initMenu(){
        $.contextMenu({
            selector: '.design_initial_position.design_energyEfficiency_btn ', //右键选择器
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
                    $("#design_edit_energyEfficiency_btn_name").val(btnName);
                    $("#design_edit_energyEfficiency_btn_width").val(btnWidth);
                    $("#design_edit_energyEfficiency_btn_height").val(btnHeight);
                    AddEnergyEfficiency.openEditBtnWin();
                }
                if(key === "relative"){
                    //关联点
                    relativeEneryEfficiencyWinData = $dom;
                    AddEnergyEfficiency.openRelativeWin();

                }
               if(key === "delete"){
                    //删除
                    layer.confirm('对不起，不可删除?', {title:'删除提示'}, function(index){
                       /* $dom.remove();*/

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

    //添加按钮
    AddEnergyEfficiency.addBtn = function (){
        var randomId = PageDesign.getUUID();
        //获取页面标志，用于区分是设计页面还是模板编辑页面
        var symbolPoint = $("#symbolChildPage").val();
        var btnName = $("#design_add_energyEfficiency_btn_name").val();
        var btnWidth = $("#design_add_energyEfficiency_btn_width").val();
        var btnHeight = $("#design_add_energyEfficiency_btn_height").val();
        var style = 'style="width:'+btnWidth+'px;height:'+btnHeight+'px;"';

        //向表格的选中单元格内添加元素
        if(AddEnergyEfficiency.selectedTable){
            AddTable.selectedTable.append('<a class="design_initial_position design_energyEfficiency_btn" '+style+'>'+btnName+'</a>');
            $( ".design_area .design_energyEfficiency_btn" ).click(AttributeWin.clickEvent).draggable({containment: "parent"});
            AddEnergyEfficiency.closeAddWin();
            return;
        }

        if("moduleChildPage" == symbolPoint){
            $("#designModule_area_demo").append('<a id = '+randomId+' class="design_initial_position design_energyEfficiency_btn" '+style+' type="button">'+btnName+'</a>');
        }else{
            $("#ynzb").append('<a id = '+randomId+' class="design_initial_position design_energyEfficiency_btn" '+style+' type=button">'+btnName+'</a>');
        }
        $( ".design_area .design_energyEfficiency_btn" ).click(AttributeWin.clickEvent).draggable();
        AddEnergyEfficiency.closeAddWin();
    }

    //关闭并重置添加弹窗
    AddEnergyEfficiency.closeAddWin = function(){
        $("#design_add_energyEfficiency_btn_name").val("");
        $("#design_add_energyEfficiency_btn_width").val(defaultWidth);
        $("#design_add_energyEfficiency_btn_height").val(defaultHeight);
        layer.close(addEnergyEfficiencyIndex);
    }

    AddEnergyEfficiency.openRelativeWin = function(){
        document.getElementById("btnRelative").style.display = "inline";
        document.getElementById("btnCancel").style.display = "inline";
        document.getElementById("pieRelative").style.display = "none ";
        document.getElementById("pieCancel").style.display = "none ";
        var energyEfficiencyDataId = relativeEneryEfficiencyWinData.attr("data-configPoint");
        var nameData = relativeEneryEfficiencyWinData.attr("data-config");
        var expression = relativeEneryEfficiencyWinData.attr("design-formulas");
        relativeIndex = layer.open({
            type: 1,
            title:"按钮关联",
            area:['450px','350px'],
            content: $('#design_energyEfficiency_btn_relative'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                AddEnergyEfficiency.closeRelativeWin();
                return false;
            },
            success:function () {
                form.render();
                $("#expression").val(expression);
                $("#design_energyEfficiency_btn_relative_name").val(energyEfficiencyDataId);
                if(expression !=null){
                    //var  singlePoint = energyEfficiencyDataId.split(';');
                    var singlePoint = nameData.split(',');
                    if(singlePoint && singlePoint.length > 0){
                        AddEnergyEfficiency.initConfigTable(singlePoint);
                    }
                }
            }
        });
    }

    /**
     * 加载配置表格内容
     * @param singlePoint
     */
    AddEnergyEfficiency.initConfigTable = function(singlePoint){
        for(var  i = 0;i < singlePoint.length;i++){
            if(!singlePoint[i]){
                continue;
            }
            var trEle = '<tr>';
            var inpTd =  '<td><input style = "width: 226px;"   type="text" value = '+singlePoint[i]+' id="'+singlePoint[i]+'_energyEfficiency"  readonly autocomplete="off" class="layui-input scene_config_name"></td>';
            $("#inputPointTable").append(trEle+inpTd+"</tr>");
        }
        form.render();
        AddEnergyEfficiency.fillData(singlePoint);

    }

    /**
     * 配置表格回填数据
     */
    AddEnergyEfficiency.fillData = function(singlePoint){
        for(var  i = 0;i<singlePoint.length-1;i++){
            var array = singlePoint[i].split(",");
            if(array && array.length === 2){
                var nameItem = array[0].split(":");
                var sysname = nameItem[1];
                $("#inputPointTable tr:eq("+(i+1)+") td:eq(0) input").val(sysname);
            }

        }
        form.render();
        $("#inputPointTable tr:eq(1) td:eq(0) input").addClass("design_scene_selected_input");

        AddEnergyEfficiency.initSelectedEvent();
    }

    /**
     * 加载表格选中行点击事件
     */
    AddEnergyEfficiency.initSelectedEvent = function(){
        $("#inputPointTable td:even input.layui-input").unbind("click").click(function(){
            var $tr = $(this).parent().parent("tr");
            $("#inputPointTable tr:even input.design_scene_selected_input").removeClass("design_scene_selected_input");
            $tr.find("td:eq(0) input").addClass("design_scene_selected_input");
        });
    }

    /**
     * 删除选中的点
     */
    AddEnergyEfficiency.removePoint = function(){
        var selected = $("#inputPointTable td:even input.design_scene_selected_input");
        var $tr = selected.parent().parent("tr");
        $tr.remove();
       $("#inputPointTable tr:eq(0) td:eq(0) input").addClass("design_scene_selected_input");
        var $table = $("#inputPointTable");
        var len = $table.find("tr").length - 1;
        var nameData = "";
        for(var i = 1; i <= len; i++){
            var sysname = $table.find("tr:eq("+i+") td:eq(0) input ").val();
            nameData += sysname + ",";
        }
        if(nameData.length > 1){
            nameData = nameData.substring(0,nameData.length-1);
        }
       /* $("#design_energyEfficiency_btn_relative_name").val(nameData);*/
        $("#expression").val("");//计算公式清空

    }

    AddEnergyEfficiency.closeRelativeWin = function(){
        layer.close(relativeIndex);
        $("#design_energyEfficiency_btn_relative_id").val("");
        $("#design_energyEfficiency_relative_name").val("");
        $("#inputPointTable").empty();
    }



    /**
     * 关联按钮点击事件--保存配置信息
     */
    AddEnergyEfficiency.relativeBtnClick = function(){
        //var  strVal = $("#expression").val();
        var count=($("#expression").val().split('$')).length-1;//获取计算公式中需要输入的参数个数
        var $table = $("#inputPointTable");
        var len = $table.find("tr").length ;
        if($("#design_energyEfficiency_btn_relative_name").val()==""){
            layer.msg("点名称不能为空",{icon:0});
            return;
        }
        if(count!=len){
            layer.msg("计算公式参数与关联点位个数不匹配，请重新输入",{icon:0});
            $("#expression").val("")
            return;
        }
      /*  if(len === 0){
            layer.msg("请选择关联点",{icon:0});
            return;
        }*/
        var configData = "";
        var nameData =  "";
        for(var i = 0; i <= len-1; i++){
            var sysname = $table.find("tr:eq("+i+") td:eq(0) input ").val();
            var item = "sysname:"+sysname+";";
            configData += item;
            nameData += sysname + ",";
        }
     if(nameData.length > 1){
            nameData = nameData.substring(0,nameData.length-1);
        }
     relativeEneryEfficiencyWinData.attr("data-configPoint",$("#design_energyEfficiency_btn_relative_name").val()).attr("data-config",nameData).attr("design-formulas",$("#expression").val());
     /*var  randomId = PageDesign.getUUID();
     relativeEneryEfficiencyWinData.attr("id",randomId);*/

        AddEnergyEfficiency.closeRelativeWin();
    }



    //关联弹窗--选择点按钮点击事件
    AddEnergyEfficiency.choosePoint = function () {
        PointTree.$nodeId = $("#design_energyEfficiency_btn_relative_id");
        PointTree.$nodeName = $("input[name='design_energyEfficiency_btn_relative_name']");
        PointTree.openPointTreeWin();
    }

    /**
     * 修改窗口
     */
    AddEnergyEfficiency.openEditBtnWin = function(){
        editBtnIndex = layer.open({
            type: 1,
            title:"修改按钮",
            area:['400px','300px'],
            content: $('#design_edit_energyEfficiency_btn_win'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                $("#design_edit_energyEfficiency_btn_name").val("");
                $("#design_edit_energyEfficiency_btn_width").val("");
                $("#design_edit_energyEfficiency_btn_height").val("");
                layer.close(editBtnIndex);
                return false;
            }
        });
    };

    //修改弹窗--确定按钮
    AddEnergyEfficiency.editBtn = function (){
        var btnName = $("#design_edit_energyEfficiency_btn_name").val();
        var btnWidth = $("#design_edit_energyEfficiency_btn_width").val();
        var btnHeight = $("#design_edit_energyEfficiency_btn_height").val();
        editBtnJquery.css("width",btnWidth+"px");
        editBtnJquery.css("height",btnHeight+"px");
        editBtnJquery.text(btnName);
        AddEnergyEfficiency.closeEditWin();
    }


    // 关闭并清空修改弹窗
    AddEnergyEfficiency.closeEditWin = function(){
        $("#design_edit_energyEfficiency_btn_name").val("");
        $("#design_edit_energyEfficiency_btn_width").val("");
        $("#design_edit_energyEfficiency_btn_height").val("");
        layer.close(editBtnIndex);
    }





});