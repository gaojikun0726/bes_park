/**
 * 温控器列表操作
 */
var TempIconController = {};
layui.use('layer', function(){
    var layer = layui.layer;
    //新增按钮弹窗,修改按钮弹窗,按钮关联弹窗
    var addBtnIndex,editBtnIndex,relativeIndex;

    var editTempListJquery,relativeTempListJquery;
    var defaultWidth = 30,defaultHeight = 30;

    $(function() {
        loadRightMenu();
    });


    //右键菜单
    function loadRightMenu(){
        $.contextMenu({
            selector: '#design_area_demo .design_tempImg', //右键选择器
            callback: function(key, options) {//点击回调处理
                var $dom = options.$trigger;
                /*if(key === "update"){
                    //修改
                    editTempListJquery = $dom;
                    var btnName = options.$trigger.text();
                    var btnWidth = options.$trigger.css('width');
                    // var btnHeight = options.$trigger.css('height');
                    btnWidth = btnWidth.replace("px","");
                    // btnHeight = btnHeight.replace("px","");
                    $("#design_edit_point_btn_name").val(btnName);
                    $("#design_edit_point_btn_width").val(btnWidth);
                    // $("#design_edit_point_btn_height").val(btnHeight);
                    TempListController.openEditBtnWin();
                }*/
                if(key === "relative"){
                    //关联点
                    relativeTempListJquery = $dom;
                    TempIconController.openRelativeWin();

                }
                if(key === "delete"){
                    //删除
                    layer.confirm('确定删除该列表吗?', {title:'删除提示'}, function(index){
                        $dom.remove();

                        layer.close(index);
                    });

                }

            },

            items: {//菜单列表配置
                "update": {name: "修改", icon: "fa-edit",disabled: false},
                //fa-retweet fa-exchange
                "relative": {name: "关联点位", icon: "fa-paperclip",disabled:false},
                "delete":{name:"删除",icon:'fa-trash-o'}
            }
        });
    }


    //关联模块弹窗
    TempIconController.openRelativeWin = function(){
        var dataId = relativeTempListJquery.attr("data-id");
        relativeIndex = layer.open({
            type: 1,
            title:"图标关联",
            area:['400px','200px'],
            content: $('#design_temp_icon_relative'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                TempIconController.closeRelativeWin();
                return false;
            },
            success:function () {
                if(dataId){
                  /*  var oldMode = $("#design_temp_icon_relative_id").val();
                    var allRelativeModule = $("#iconTempModule").val();//温控器关联的所有模块*/
                    $("#design_temp_icon_relative_id").val(dataId);

                    /*$('#settingsTable').attr('id', dataId);*/
                    // todo 根据id查询name
                    $("#design_temp_icon_relative_name").val(dataId);
                }
            }
        });
    }


    //关闭并清空关联模块弹窗
    TempIconController.closeRelativeWin = function(){
        layer.close(relativeIndex);
        $("#design_temp_icon_relative_id").val("");
        $("#design_temp_icon_relative_name").val("");
    }

    //关联点弹窗--关联按钮点击事件
    TempIconController.relativeTempList = function () {
        relativeTempListJquery.attr("data-id",$("#design_temp_icon_relative_id").val());
        TempIconController.closeRelativeWin();
    }
    //关联弹窗--选择点按钮点击事件
    TempIconController.chooseModule = function (e) {
        PointTree.$nodeId = $("#design_temp_icon_relative_id");
        PointTree.$nodeName = $("#design_temp_icon_relative_name");
        PointTree.openPointTreeWin();
    }


    /**
     * 关联图片之前校验节点是否满足条件
     */
    TempIconController.verifyTemp = function(){
        var sysname = PointTree.$nodeId.val();
        /*$('#settingsTable').attr('id', sysname);*/
        $.ajax({
            url     : _ctx + '/btnEventController/getDebugInfo',
            type    : "post",
            dataType: 'json',
            data : {
                sysname : sysname
            },
            success : function(result) {
                //只有DI或DO表中也存在记录时，才能作为关联点使用
                if(result.status === "0"){
                    layer.msg("请先配置该点信息",{icon:2});
                    return false;
                }else{
                    if(result.data){
                        PointTree.closeTreeWin();
                        return true;
                    }
                    return true;
                }
            }});
    }



});