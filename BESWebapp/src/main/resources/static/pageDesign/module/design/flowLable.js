/**
 * 流程节点标签操作
 */
var FlowLable = {};
layui.use('layer', function(){
    var layer = layui.layer;
    //新增按钮弹窗,修改按钮弹窗,按钮关联弹窗,设备树弹窗
    var addBtnIndex,editBtnIndex,relativeIndex;

    var editBtnJquery,relativeBtnJquery;

    $(function() {
        contextMenu();
    });

    //右键菜单
    function contextMenu(){
        $.contextMenu({
            selector: '.design_initial_position.design_flow_btn ', //右键选择器
            callback: function(key, options) {//点击回调处理
                var $dom = options.$trigger;
                if(key === "update"){
                    //修改
                    editBtnJquery = $dom;
                    var text = $dom.text();
                    $("#design_edit_flow_name").val(text);
                    FlowLable.openEditBtnWin();
                }
                if(key === "relative"){
                    //关联点
                    relativeBtnJquery = $dom;
                    FlowLable.openRelativeWin();

                }
                if(key === "delete"){
                    //删除
                    layer.confirm('确定删除该流程节点吗?', {title:'删除提示'}, function(index){
                        $dom.remove();

                        layer.close(index);
                    });

                }
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
    FlowLable.openAddBtnWin = function(){
        addBtnIndex = layer.open({
            type: 1,
            title:"新增流程节点",
            area:['400px','300px'],
            content: $('#design_add_flow_win'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                FlowLable.closeAddDebugWin();
                return false;
            }
        });
    }

    //新增弹窗--确定按钮
    FlowLable.addFlowBtn = function(){
        //获取页面标志，用于区分是设计页面还是模板编辑页面
        var symbolPoint = $("#symbolChildPage").val();
        var text = $("#design_add_flow_name").val();
        var randomId = PageDesign.getUUID();
        if(!FlowLable.checkData(text)){
            return false;
        }

        if("moduleChildPage" == symbolPoint){
            $("#designModule_area_demo").append('<span id = '+randomId+' class="design_initial_position design_flow_btn">'+text+'</span>');
        }else{
            $("#design_area_demo").append('<span id = '+randomId+' class="design_initial_position design_flow_btn">'+text+'</span>');
        }
        $( ".design_area .design_flow_btn" ).click(AttributeWin.clickEvent).draggable();
        //.resizable({handles:"all"})
        FlowLable.clearAddBtnWin();
    }

    /**
     * 校验数据
     * @param text
     */
    FlowLable.checkData = function(text){
        var result = CommonCheck.checkEmpty(text,"节点名称");
        return result;
    }


    //取消按钮--关闭添加弹窗
    FlowLable.closeAddFlowWin = function(){
        layer.close(addBtnIndex);
        FlowLable.clearAddBtnWin();
    }



    //清空添加按钮弹窗
    FlowLable.clearAddBtnWin = function(){
        $("#design_add_flow_name").val("");
        layer.close(addBtnIndex);
    }

    //取消按钮--关闭添加弹窗
    FlowLable.closeAddDebugWin = function(){
        layer.close(addBtnIndex);
        FlowLable.clearAddBtnWin();
    }

    /*编辑弹窗*/
    FlowLable.openEditBtnWin = function () {
        editBtnIndex = layer.open({
            type: 1,
            title:"流程节点编辑",
            area:['300px','200px'],
            content: $('#design_edit_flow_win'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                FlowLable.closeEditFlowWin();
                return false;
            }
        });
    }

    //修改弹窗--确定按钮
    FlowLable.editFlowBtn = function (){
        var btnName = $("#design_edit_flow_name").val();
        if(!FlowLable.checkData(btnName)){
            return false;
        }
        editBtnJquery.text(btnName);
        layer.close(editBtnIndex);
        //FlowBtn.clearEditBtnWin();
    }

    // 取消按钮--关闭修改弹窗
    FlowLable.closeEditDebugWin = function(){
        layer.close(editBtnIndex);
        FlowLable.clearEditBtnWin();
    }

    //清空修改弹窗
    FlowLable.clearEditBtnWin = function(){
        $("#design_edit_flow_name").val("");
    }

    // 取消按钮--关闭修改弹窗
    FlowLable.closeEditFlowWin = function(){
        layer.close(editBtnIndex);
        FlowLable.clearEditBtnWin();
    }

    //关联点弹窗
    FlowLable.openRelativeWin = function(){
        var dataId = relativeBtnJquery.attr("data-id");
        relativeIndex = layer.open({
            type: 1,
            title:"按钮关联",
            area:['400px','200px'],
            content: $('#design_flow_btn_relative'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                FlowLable.closeRelativeWin();
                return false;
            },
            success:function () {
                if(dataId){
                    $("#design_flow_btn_relative_id").val(dataId);
                    // todo 根据id查询name
                    $("#design_flow_btn_relative_name").val(dataId);
                }
            }
        });
    }
    //关闭并清空关联点弹窗
    FlowLable.closeRelativeWin = function(){
        layer.close(relativeIndex);
        $("#design_flow_btn_relative_id").val("");
        $("#design_flow_btn_relative_name").val("");
    }

    //关联点弹窗--关联按钮点击事件
    FlowLable.relativeBtn = function () {
        relativeBtnJquery.attr("data-id",$("#design_flow_btn_relative_id").val());
        FlowLable.closeRelativeWin();
    }

    //关联弹窗--选择点按钮点击事件
    FlowLable.choosePoint = function () {
        PointTree.$nodeId = $("#design_flow_btn_relative_id");
        PointTree.$nodeName = $("#design_flow_btn_relative_name");
        PointTree.openPointTreeWin();
    }


});