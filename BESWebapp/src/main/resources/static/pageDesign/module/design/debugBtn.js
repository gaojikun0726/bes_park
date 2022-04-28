/**
 * 调试按钮操作
 */
var DebugBtn = {};
layui.use('layer', function(){
    var layer = layui.layer;
    //新增按钮弹窗,修改按钮弹窗,按钮关联弹窗,设备树弹窗
    var addBtnIndex,editBtnIndex,relativeIndex,deviceTreeIndex;

    var editBtnJquery,relativeBtnJquery;
    var defaultWidth = 80,defaultHeight = 40;
    //允许填写的宽度，高度的最大长度
    var maxlength = 10;
    var nameLength = 20;

    $(function() {
        contextMenu();
    });

    //右键菜单
    function contextMenu(){
        $.contextMenu({
            selector: '.design_initial_position.design_debug_btn ', //右键选择器
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
                    $("#design_edit_btn_name").val(btnName);
                    $("#design_edit_btn_width").val(btnWidth);
                    $("#design_edit_btn_height").val(btnHeight);
                    DebugBtn.openEditBtnWin();
                }
                if(key === "relative"){
                    //关联点
                    relativeBtnJquery = $dom;
                    DebugBtn.openRelativeWin();

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
    DebugBtn.openAddBtnWin = function(){

        addBtnIndex = layer.open({
            type: 1,
            title:"新增按钮",
            area:['34vw','40vh'],
            // area:['400px','300px'],
            content: $('#design_addBtnWin'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                DebugBtn.closeAddDebugWin();
                return false;
            }
        });
    }


    //添加调试按钮
    DebugBtn.addDebugBtn = function (){
        var randomId = PageDesign.getUUID();
        //获取页面标志，用于区分是设计页面还是模板编辑页面
        var symbolPoint = $("#symbolChildPage").val();
        var btnName = $("#design_add_btn_name").val();
        var btnWidth = $("#design_add_btn_width").val();
        var btnHeight = $("#design_add_btn_height").val();

        if(!DebugBtn.checkAll(btnName,btnWidth,btnHeight)){
            return false;
        }
        var style = 'style="width:'+btnWidth+'px;height:'+btnHeight+'px;"';

        //向表格的选中单元格内添加元素
        if(AddTable.selectedTable){
            DebugBtn.appendChildBtn(AddTable.selectedTable,style,btnName);
            return;
        }

        //向背景区域添加子元素
        if(BackColor.selectedArea){
            DebugBtn.appendChildBtn(BackColor.selectedArea,style,btnName);
            return;
        }

        if("moduleChildPage" == symbolPoint){
            $("#designModule_area_demo").append('<a id = '+randomId+' class="btn btn-primary design_initial_position design_debug_btn" '+style+' type="button">'+btnName+'</a>');
        }else{
            $("#design_area_demo").append('<a id = '+randomId+' class="btn btn-primary design_initial_position design_debug_btn" '+style+' type="button">'+btnName+'</a>');
        }
        $( ".design_area .design_debug_btn" ).unbind("click").click(AttributeWin.clickEvent).draggable(
            // {stop: function( event, ui ) {
            //         $("#moduleLayout").val("");
            //     }}
        );
        $(".design_debug_btn").attr("tabindex",0);
        CopyPaste.copyDebug();
        //    .prop("draggable",true)
        layer.close(addBtnIndex);
        DebugBtn.clearAddBtnWin();
    }


    /**
     * 向表格单元格或背景区域添加子元素
     * @param $parent 表格单元格或背景区域
     * @param style 样式
     * @param btnName 按钮名称
     */
    DebugBtn.appendChildBtn = function($parent,style,btnName){
        $parent.append('<a class="btn btn-primary design_initial_position design_debug_btn" '+style+'>'+btnName+'</a>');
        $parent.find( ".design_debug_btn" ).unbind("click").click(AttributeWin.clickEvent).draggable({containment: "parent"});
        layer.close(addBtnIndex);
        DebugBtn.clearAddBtnWin();
    }

    // /**
    //  * 校验名称
    //  * @param value 需要校验的数据值
    //  * @param name 提示名称
    //  */
    // DebugBtn.checkName = function(value,name){
    //     var result = CommonCheck.checkLength(value,name,nameLength);
    //     return result;
    // }

    /**
     * 校验数据
     * @param btnName
     * @param btnWidth
     * @param btnHeight
     * @returns {boolean}
     */
     DebugBtn.checkAll = function(btnName,btnWidth,btnHeight){
         var result = CommonCheck.checkLength(btnName,"按钮名称",nameLength);
         if(result){
             result = DebugBtn.checkData(btnWidth,"按钮宽度");
             if(result){
                 result = DebugBtn.checkData(btnHeight,"按钮高度");
             }
         }
         return result;
     }

    /**
     * 校验单个数据
     * @param value 需要校验的数据值
     * @param name 提示名称
     */
    DebugBtn.checkData = function(value,name){

        var result = CommonCheck.checkNumber(value,name);
        if(result){
            result = CommonCheck.checkLength(value,name,maxlength);
        }
      return result;
    }

    //清空添加按钮弹窗
    DebugBtn.clearAddBtnWin = function(){
        $("#design_add_btn_name").val("");
        $("#design_add_btn_width").val(defaultWidth);
        $("#design_add_btn_height").val(defaultHeight);
    }

    //取消按钮--关闭添加弹窗
    DebugBtn.closeAddDebugWin = function(){
        layer.close(addBtnIndex);
        DebugBtn.clearAddBtnWin();
    }

    //打开修改弹窗
    DebugBtn.openEditBtnWin = function(){
        editBtnIndex = layer.open({
            type: 1,
            title:"修改按钮",
            area:['34vw','40vh'],
            // area:['400px','300px'],
            content: $('#design_editBtnWin'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                DebugBtn.closeEditDebugWin();
                return false;
            }
        });
    }

    //修改弹窗--确定按钮
    DebugBtn.editDebugBtn = function (){
        var btnName = $("#design_edit_btn_name").val();
        var btnWidth = $("#design_edit_btn_width").val();
        var btnHeight = $("#design_edit_btn_height").val();
        if(!DebugBtn.checkAll(btnName,btnWidth,btnHeight)){
            return false;
        }
        editBtnJquery.css("width",btnWidth+"px");
        editBtnJquery.css("height",btnHeight+"px");
        editBtnJquery.text(btnName);
        layer.close(editBtnIndex);
        DebugBtn.clearEditBtnWin();
    }

    //清空修改弹窗
    DebugBtn.clearEditBtnWin = function(){
        $("#design_edit_btn_name").val("");
        $("#design_edit_btn_width").val("");
        $("#design_edit_btn_height").val("");
    }

    // 取消按钮--关闭修改弹窗
    DebugBtn.closeEditDebugWin = function(){
        layer.close(editBtnIndex);
        DebugBtn.clearEditBtnWin();
    }

    //关联点弹窗
    DebugBtn.openRelativeWin = function(){
        var dataId = relativeBtnJquery.attr("data-id");
        relativeIndex = layer.open({
            type: 1,
            title:"按钮关联",
            area:['34vw','24vh'],
            // area:['400px','200px'],
            content: $('#design_debug_btn_relative'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                DebugBtn.closeRelativeWin();
                return false;
            },
            success:function () {
                if(dataId){
                    $("#design_debug_btn_relative_id").val(dataId);
                    // todo 根据id查询name
                    $("#design_debug_btn_relative_name").val(dataId);
                }
            }
        });
    }
    //关闭并清空关联点弹窗
    DebugBtn.closeRelativeWin = function(){
        layer.close(relativeIndex);
        $("#design_debug_btn_relative_id").val("");
        $("#design_debug_btn_relative_name").val("");
    }

    //关联点弹窗--关联按钮点击事件
    DebugBtn.relativeBtn = function () {
        var dataId = $("#design_debug_btn_relative_id").val();
        if(!dataId){
            layer.msg("请选择关联点",{icon:0});
            return;
        }
        relativeBtnJquery.attr("data-id",dataId).attr("title",dataId);
        DebugBtn.closeRelativeWin();
    }

    //关联弹窗--选择点按钮点击事件
    DebugBtn.choosePoint = function () {
        PointTree.$nodeId = $("#design_debug_btn_relative_id");
        PointTree.$nodeName = $("#design_debug_btn_relative_name");
        PointTree.openPointTreeWin();
    }


});