/**
 * 单通道按钮操作
 */
var ChannelBtn = {};
layui.use('layer', function(){
    var layer = layui.layer;
    //新增按钮弹窗,修改按钮弹窗,按钮关联弹窗
    var addBtnIndex,editBtnIndex,relativeIndex;

    var editBtnJquery,relativeBtnJquery;
    var defaultWidth = 50,defaultHeight = 26;

    $(function() {
        contextMenu();
    });

    //右键菜单
    function contextMenu(){
        $.contextMenu({
            selector: '.design_initial_position.design_channel_btn ', //右键选择器
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
                    $("#design_edit_channel_btn_name").val(btnName);
                    $("#design_edit_channel_btn_width").val(btnWidth);
                    $("#design_edit_channel_btn_height").val(btnHeight);
                    ChannelBtn.openEditBtnWin();
                }
                if(key === "relative"){
                    //关联点
                    relativeBtnJquery = $dom;
                    ChannelBtn.openRelativeWin();

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
    ChannelBtn.openAddBtnWin = function(){

        addBtnIndex = layer.open({
            type: 1,
            title:"新增按钮",
            area:['34vw','40vh'],
            // area:['400px','300px'],
            content: $('#design_add_channel_win'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                ChannelBtn.closeAddWin();
                return false;
            }
        });
    }


    //添加调试按钮
    ChannelBtn.addConfirm = function (){
        //获取页面标志，用于区分是设计页面还是模板编辑页面
        var symbolPoint = $("#symbolChildPage").val();
        var randomId = PageDesign.getUUID();
        var btnName = $("#design_add_channel_btn_name").val();
        var btnWidth = $("#design_add_channel_btn_width").val();
        var btnHeight = $("#design_add_channel_btn_height").val();
        var style = 'style="width:'+btnWidth+'px;height:'+btnHeight+'px;"';

        //向表格的选中单元格内添加元素
        if(AddTable.selectedTable){
           ChannelBtn.appendChildBtn(AddTable.selectedTable,style,btnName);
            return;
        }
        //向背景区域添加子元素
        if(BackColor.selectedArea){
            ChannelBtn.appendChildBtn(BackColor.selectedArea,style,btnName);
            return;
        }

        if("moduleChildPage" == symbolPoint){
            $("#designModule_area_demo").append('<a id = '+randomId+'  class="design_initial_position design_channel_btn gray" '+style+' type="button">'+btnName+'</a>');
        }else{
            $("#design_area_demo").append('<a id = '+randomId+'  class=" design_initial_position design_channel_btn gray" '+style+' type="button">'+btnName+'</a>');
        }
        //$("#design_area_demo").append('<a id = '+randomId+'  class="btn btn-primary design_initial_position design_channel_btn" '+style+' type="button">'+btnName+'</a>');
        $( ".design_area .design_channel_btn" ).unbind("click").click(AttributeWin.clickEvent).draggable();
        $(".design_channel_btn").attr("tabindex",0);
        CopyPaste.copyChannel();
        layer.close(addBtnIndex);
        ChannelBtn.clearAddWin();
    }


    /**
     * 向表格单元格或背景区域添加子元素
     * @param $parent 表格单元格或背景区域
     * @param style 样式
     * @param btnName 按钮名称
     */
    ChannelBtn.appendChildBtn = function($parent,style,btnName){
        $parent.append('<a class="design_initial_position design_channel_btn gray" '+style+'>'+btnName+'</a>');
        $parent.find( ".design_channel_btn" ).unbind("click").click(AttributeWin.clickEvent).draggable({containment: "parent"});
        layer.close(addBtnIndex);
        ChannelBtn.clearAddWin();
    }

    //清空添加按钮弹窗
    ChannelBtn.clearAddWin = function(){
        $("#design_add_channel_btn_name").val("");
        $("#design_add_channel_btn_width").val(defaultWidth);
        $("#design_add_channel_btn_height").val(defaultHeight);
    }

    //取消按钮--关闭添加弹窗
    ChannelBtn.closeAddWin = function(){
        layer.close(addBtnIndex);
        ChannelBtn.clearAddWin();
    }

    //打开修改弹窗
    ChannelBtn.openEditBtnWin = function(){
        editBtnIndex = layer.open({
            type: 1,
            title:"修改按钮",
            area:['34vw','40vh'],
            // area:['400px','300px'],
            content: $('#design_edit_channel_win'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                ChannelBtn.closeEditWin();
                return false;
            }
        });
    }

    //修改弹窗--确定按钮
    ChannelBtn.editConfirm = function (){
        var btnName = $("#design_edit_channel_btn_name").val();
        var btnWidth = $("#design_edit_channel_btn_width").val();
        var btnHeight = $("#design_edit_channel_btn_height").val();
        editBtnJquery.css("width",btnWidth+"px");
        editBtnJquery.css("height",btnHeight+"px");
        editBtnJquery.text(btnName);
        layer.close(editBtnIndex);
        ChannelBtn.closeEditWin();
    }


    //关闭并清空修改弹窗
    ChannelBtn.closeEditWin = function(){
        layer.close(editBtnIndex);
        $("#design_edit_channel_btn_name").val("");
        $("#design_edit_channel_btn_width").val("");
        $("#design_edit_channel_btn_height").val("");
    }

    //关联点弹窗
    ChannelBtn.openRelativeWin = function(){
        var dataId = relativeBtnJquery.attr("data-id");
        relativeIndex = layer.open({
            type: 1,
            title:"按钮关联",
            area:['34vw','24vh'],
            // area:['400px','200px'],
            content: $('#design_channel_btn_relative'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                ChannelBtn.closeRelativeWin();
                return false;
            },
            success:function () {
                if(dataId){
                    $("#design_channel_btn_relative_id").val(dataId);
                    // todo 根据id查询name
                    $("#design_channel_btn_relative_name").val(dataId);
                }
            }
        });
    }
    //关闭并清空关联点弹窗
    ChannelBtn.closeRelativeWin = function(){
        layer.close(relativeIndex);
        $("#design_channel_btn_relative_id").val("");
        $("#design_channel_btn_relative_name").val("");
    }

    //关联点弹窗--关联按钮点击事件
    ChannelBtn.relativeBtn = function () {
        var dataId = $("#design_channel_btn_relative_id").val();
        if(!dataId){
            layer.msg("请选择关联点",{icon:0});
            return;
        }
        relativeBtnJquery.attr("data-id",dataId).attr("title",dataId);
        ChannelBtn.closeRelativeWin();
    }

    //关联弹窗--选择点按钮点击事件
    ChannelBtn.choosePoint = function () {
        PointTree.$nodeId = $("#design_channel_btn_relative_id");
        PointTree.$nodeName = $("#design_channel_btn_relative_name");
        PointTree.openPointTreeWin();
    }


});