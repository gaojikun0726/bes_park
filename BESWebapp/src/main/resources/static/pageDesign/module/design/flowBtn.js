/**
 * 调试按钮操作
 */
var FlowBtn = {};
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
            selector: '.design_initial_position.design_flow_btn ', //右键选择器
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
                    $("#design_edit_flow_name").val(btnName);
                    $("#design_edit_flow_width").val(btnWidth);
                    $("#design_edit_flow_height").val(btnHeight);
                    FlowBtn.openEditBtnWin();
                }
                if(key === "relative"){
                    //关联点
                    relativeBtnJquery = $dom;
                    FlowBtn.openRelativeWin();

                }
                if(key === "delete"){
                    //删除
                    layer.confirm('确定删除该按钮吗?', {title:'删除提示'}, function(index){
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
    FlowBtn.openAddBtnWin = function(){

        addBtnIndex = layer.open({
            type: 1,
            title:"新增按钮",
            area:['400px','300px'],
            content: $('#design_add_flow_win'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                FlowBtn.closeAddDebugWin();
                return false;
            }
        });
    }


    //添加调试按钮
    FlowBtn.addFlowBtn = function (){
        var randomId = PageDesign.getUUID();
        //获取页面标志，用于区分是设计页面还是模板编辑页面
        var symbolPoint = $("#symbolChildPage").val();
        var btnName = $("#design_add_flow_name").val();
        var btnWidth = $("#design_add_flow_width").val();
        var btnHeight = $("#design_add_flow_height").val();

        if(!FlowBtn.checkAll(btnName,btnWidth,btnHeight)){
            return false;
        }
        var style = 'style="width:'+btnWidth+'px;height:'+btnHeight+'px;"';
        if("moduleChildPage" == symbolPoint){
            $("#designModule_area_demo").append('<a id = '+randomId+' class="btn btn-primary design_initial_position design_flow_btn" '+style+' type="button">'+btnName+'</a>');
        }else{
            $("#design_area_demo").append('<a id = '+randomId+' class="btn btn-primary design_initial_position design_flow_btn" '+style+' type="button">'+btnName+'</a>');
        }
        $( ".design_area .design_flow_btn" ).click(AttributeWin.clickEvent).draggable(

        );
        layer.close(addBtnIndex);
        FlowBtn.clearAddBtnWin();
    }


    //取消按钮--关闭添加弹窗
    FlowBtn.closeAddFlowWin = function(){
        layer.close(addBtnIndex);
        FlowBtn.clearAddBtnWin();
    }



    /**
     * 校验数据
     * @param btnName
     * @param btnWidth
     * @param btnHeight
     * @returns {boolean}
     */
    FlowBtn.checkAll = function(btnName,btnWidth,btnHeight){
        var result = CommonCheck.checkLength(btnName,"按钮名称",nameLength);
        if(result){
            result = FlowBtn.checkData(btnWidth,"按钮宽度");
            if(result){
                result = FlowBtn.checkData(btnHeight,"按钮高度");
            }
        }
        return result;
    }

    /**
     * 校验单个数据
     * @param value 需要校验的数据值
     * @param name 提示名称
     */
    FlowBtn.checkData = function(value,name){

        var result = CommonCheck.checkNumber(value,name);
        if(result){
            result = CommonCheck.checkLength(value,name,maxlength);
        }
        return result;
    }

    //清空添加按钮弹窗
    FlowBtn.clearAddBtnWin = function(){
        $("#design_add_flow_name").val("");
        $("#design_add_flow_width").val(defaultWidth);
        $("#design_add_flow_height").val(defaultHeight);
    }

    //取消按钮--关闭添加弹窗
    FlowBtn.closeAddDebugWin = function(){
        layer.close(addBtnIndex);
        FlowBtn.clearAddBtnWin();
    }

    //打开修改弹窗
    FlowBtn.openEditBtnWin = function(){
        editBtnIndex = layer.open({
            type: 1,
            title:"修改按钮",
            area:['400px','300px'],
            content: $('#design_edit_flow_win'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                FlowBtn.closeEditFlowWin();
                return false;
            }
        });
    }

    //修改弹窗--确定按钮
    FlowBtn.editFlowBtn = function (){
        var btnName = $("#design_edit_flow_name").val();
        var btnWidth = $("#design_edit_flow_width").val();
        var btnHeight = $("#design_edit_flow_height").val();
        if(!FlowBtn.checkAll(btnName,btnWidth,btnHeight)){
            return false;
        }
        editBtnJquery.css("width",btnWidth+"px");
        editBtnJquery.css("height",btnHeight+"px");
        editBtnJquery.text(btnName);
        layer.close(editBtnIndex);
        //FlowBtn.clearEditBtnWin();
    }

    // 取消按钮--关闭修改弹窗
    FlowBtn.closeEditDebugWin = function(){
        layer.close(editBtnIndex);
        FlowBtn.clearEditBtnWin();
    }

    //清空修改弹窗
    FlowBtn.clearEditBtnWin = function(){
        $("#design_edit_flow_name").val("");
        $("#design_edit_flow_width").val("");
        $("#design_edit_flow_height").val("");
    }

    // 取消按钮--关闭修改弹窗
    FlowBtn.closeEditFlowWin = function(){
        layer.close(editBtnIndex);
        FlowBtn.clearEditBtnWin();
    }

    //关联点弹窗
    FlowBtn.openRelativeWin = function(){
        var dataId = relativeBtnJquery.attr("data-id");
        relativeIndex = layer.open({
            type: 1,
            title:"按钮关联",
            area:['400px','200px'],
            content: $('#design_flow_btn_relative'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                FlowBtn.closeRelativeWin();
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
    FlowBtn.closeRelativeWin = function(){
        layer.close(relativeIndex);
        $("#design_flow_btn_relative_id").val("");
        $("#design_flow_btn_relative_name").val("");
    }

    //关联点弹窗--关联按钮点击事件
    FlowBtn.relativeBtn = function () {
        relativeBtnJquery.attr("data-id",$("#design_flow_btn_relative_id").val());
        FlowBtn.closeRelativeWin();
    }

    //关联弹窗--选择点按钮点击事件
    FlowBtn.choosePoint = function () {
        PointTree.$nodeId = $("#design_flow_btn_relative_id");
        PointTree.$nodeName = $("#design_flow_btn_relative_name");
        PointTree.openPointTreeWin();
    }

   


});