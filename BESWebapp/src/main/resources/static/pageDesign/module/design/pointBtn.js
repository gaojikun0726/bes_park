/**
 * 点位置按钮操作
 */
var PointBtn = {};
layui.use('layer', function(){
    var layer = layui.layer;
    //新增按钮弹窗,修改按钮弹窗,按钮关联弹窗
    var addBtnIndex,editBtnIndex,relativeIndex;

    var editBtnJquery,relativeBtnJquery;
    var defaultWidth = 30,defaultHeight = 30;

    $(function() {
        contextMenu();
    });

    //右键菜单
    function contextMenu(){
        $.contextMenu({
            selector: '.design_initial_position.design_point_btn ', //右键选择器
            callback: function(key, options) {//点击回调处理
                var $dom = options.$trigger;
                if(key === "update"){
                    //修改
                    editBtnJquery = $dom;
                    var btnName = options.$trigger.text();
                    var btnWidth = options.$trigger.css('width');
                    // var btnHeight = options.$trigger.css('height');
                    btnWidth = btnWidth.replace("px","");
                    // btnHeight = btnHeight.replace("px","");
                    $("#design_edit_point_btn_name").val(btnName);
                    $("#design_edit_point_btn_width").val(btnWidth);
                    // $("#design_edit_point_btn_height").val(btnHeight);
                    PointBtn.openEditBtnWin();
                }
                if(key === "relative"){
                    //关联点
                    relativeBtnJquery = $dom;
                    PointBtn.openRelativeWin();

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
                //fa-retweet fa-exchange
                "relative": {name: "关联点", icon: "fa-paperclip",disabled:false},
                "delete":{name:"删除",icon:'fa-trash-o'}
            }
        });
    }



    //新增弹窗
    PointBtn.openAddBtnWin = function(){

        addBtnIndex = layer.open({
            type: 1,
            title:"新增按钮",
            area:['34vw','38vh'],
            // area:['400px','300px'],
            content: $('#design_add_point_win'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                PointBtn.closeAddWin();
                return false;
            }
        });
    }


    //添加按钮
    PointBtn.addConfirm = function (){
        //获取页面标志，用于区分是设计页面还是模板编辑页面
        var symbolPoint = $("#symbolChildPage").val();
        var btnName = $("#design_add_point_btn_name").val();
        var btnWidth = $("#design_add_point_btn_width").val();
        var randomId = PageDesign.getUUID();
        // var btnHeight = $("#design_add_point_btn_height").val();
        var style = 'style="width:'+btnWidth+'px;height:'+btnWidth+'px;"';
        //        $("#design_area_demo").append('<a class=" design_initial_position design_point_btn green" '+style+' type="button"><span class="point_text">'+btnName+'</span></a>');

        //向表格的选中单元格内添加元素
        if(AddTable.selectedTable){
            PointBtn.appendChildBtn(AddTable.selectedTable,style,btnName);
            return;
        }
        //向背景区域添加子元素
        if(BackColor.selectedArea){
            PointBtn.appendChildBtn(BackColor.selectedArea,style,btnName);
            return;
        }

        if("moduleChildPage" == symbolPoint){
            $("#designModule_area_demo").append('<a id  = '+randomId+'  class=" design_initial_position design_point_btn gray" '+style+'>'+btnName+'</a>');
        }else{
            $("#design_area_demo").append('<a id  = '+randomId+'  class=" design_initial_position design_point_btn gray" '+style+'>'+btnName+'</a>');
        }
        $( ".design_area .design_point_btn" ).unbind("click").click(AttributeWin.clickEvent).draggable();
        $(".design_point_btn").attr("tabindex",0);
        CopyPaste.copyPoint();
        layer.close(addBtnIndex);
        PointBtn.clearAddWin();

    }

    /**
     * 向表格单元格或背景区域添加子元素
     * @param $parent 表格单元格或背景区域
     * @param style 样式
     * @param btnName 按钮名称
     */
    PointBtn.appendChildBtn = function($parent,style,btnName){
        $parent.append('<a class="design_initial_position design_point_btn gray" '+style+'>'+btnName+'</a>');
        $parent.find( " .design_point_btn" ).unbind("click").click(AttributeWin.clickEvent).draggable({containment: "parent"});
        layer.close(addBtnIndex);
        PointBtn.clearAddWin();
    }

    //清空添加按钮弹窗
    PointBtn.clearAddWin = function(){
        $("#design_add_point_btn_name").val("");
        $("#design_add_point_btn_width").val(defaultWidth);
        // $("#design_add_point_btn_height").val(defaultHeight);
    }

    //取消按钮--关闭添加弹窗
    PointBtn.closeAddWin = function(){
        layer.close(addBtnIndex);
        PointBtn.clearAddWin();
    }

    //打开修改弹窗
    PointBtn.openEditBtnWin = function(){
        editBtnIndex = layer.open({
            type: 1,
            title:"修改按钮",
            area:['34vw','38vh'],
            // area:['400px','300px'],
            content: $('#design_edit_point_win'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                PointBtn.closeEditWin();
                return false;
            }
        });
    }

    //修改弹窗--确定按钮
    PointBtn.editConfirm = function (){
        var btnName = $("#design_edit_point_btn_name").val();
        var btnWidth = $("#design_edit_point_btn_width").val();
        // var btnHeight = $("#design_edit_point_btn_height").val();
        editBtnJquery.css("width",btnWidth+"px");
        editBtnJquery.css("height",btnWidth+"px");
        editBtnJquery.css("line-height",btnWidth+"px");
        // editBtnJquery.html("<span class='point_text'>"+btnName+"</span>");
        editBtnJquery.html(btnName);
        layer.close(editBtnIndex);
        PointBtn.closeEditWin();
    }


    //关闭并清空修改弹窗
    PointBtn.closeEditWin = function(){
        layer.close(editBtnIndex);
        $("#design_edit_point_btn_name").val("");
        $("#design_edit_point_btn_width").val("");
        // $("#design_edit_point_btn_height").val("");
    }

    //关联点弹窗
    PointBtn.openRelativeWin = function(){
        var dataId = relativeBtnJquery.attr("data-id");
        relativeIndex = layer.open({
            type: 1,
            title:"按钮关联",
            area:['34vw','38vh'],
            // area:['400px','200px'],
            content: $('#design_point_btn_relative'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                PointBtn.closeRelativeWin();
                return false;
            },
            success:function () {
                if(dataId){
                    $("#design_point_btn_relative_id").val(dataId);
                    // todo 根据id查询name
                    $("#design_point_btn_relative_name").val(dataId);
                }
            }
        });
    }
    //关闭并清空关联点弹窗
    PointBtn.closeRelativeWin = function(){
        layer.close(relativeIndex);
        $("#design_point_btn_relative_id").val("");
        $("#design_point_btn_relative_name").val("");
    }

    //关联点弹窗--关联按钮点击事件
    PointBtn.relativeBtn = function () {
        var dataId = $("#design_point_btn_relative_id").val();
        if(!dataId){
            layer.msg("请选择关联点",{icon:0});
            return;
        }
        relativeBtnJquery.attr("data-id",dataId).attr("title",dataId);
        PointBtn.closeRelativeWin();
    }

    //关联弹窗--选择点按钮点击事件
    PointBtn.choosePoint = function (e) {
        PointTree.$nodeId = $("#design_point_btn_relative_id");
        PointTree.$nodeName = $("#design_point_btn_relative_name");
        PointTree.openPointTreeWin();
    }


});