/**
 * 添加电动窗帘
 */
var AddCurtain = {

};
layui.use(['layer'], function(){
    var layer = layui.layer;
    //新增、修改、关联点弹窗
    var addIndex,editIndex,relativeIndex;

    //新增时默认的宽度、高度
    var defaultWidth = 30,defaultHeight = 30;

    //修改操作点击的图片，关联操作点击的图片
    var editJquery,relativeJquery;

    $(function(){
        contextMenu();
    });


    //右键菜单
    function contextMenu(){
        $.contextMenu({
            selector: '#design_area_demo .design_curtain', //右键选择器
            callback: function(key, options) {//点击回调处理
                var $dom = options.$trigger;
                if(key === "relative"){
                    //关联点
                    relativeJquery = $dom;
                    AddCurtain.openRelativeWin();

                }
                if(key === "delete"){
                    //删除
                    layer.confirm('确定删除该电动窗帘吗?', {title:'删除提示'}, function(index){
                        $dom.remove();
                        layer.close(index);
                    });

                }
            },
            items: {//菜单列表配置
                "relative": {name: "关联点", icon: "fa-paperclip",disabled:false},
                "delete":{name:"删除",icon:'fa-trash-o'}
            }
        });
    }

    //关联点弹窗
    AddCurtain.openRelativeWin = function(){
        var dataId = relativeJquery.attr("data-id");
        var dataName = relativeJquery.attr("title");
        relativeIndex = layer.open({
            type: 1,
            title:"电动窗帘关联",
            area:['34vw','24vh'],
            // area:['400px','200px'],
            content: $('#design_curtain_relative'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                AddCurtain.closeRelativeWin();
                return false;
            },
            success:function () {
                if(dataId){
                    $("#design_curtain_relative_id").val(dataId);
                    // todo 根据id查询name
                    $("#design_curtain_relative_name").val(dataName);
                }
            }
        });
    };

    //关闭并清空关联点弹窗
    AddCurtain.closeRelativeWin = function(){
        layer.close(relativeIndex);
        $("#design_curtain_relative_id").val("");
        $("#design_curtain_relative_name").val("");
    };

    //关联点弹窗--关联按钮点击事件
    AddCurtain.relativeBtn = function () {
        var dataId = $("#design_curtain_relative_id").val();
        var dataName = $("#design_curtain_relative_name").val();
        if(!dataId){
            layer.msg("请选择关联点",{icon:0});
            return;
        }
        relativeJquery.attr("data-id",dataId).attr("title",dataName);
        AddCurtain.closeRelativeWin();
    };

    //关联弹窗--选择点按钮点击事件
    AddCurtain.choosePoint = function () {
        PointTree.$nodeId = $("#design_curtain_relative_id");
        PointTree.$nodeName = $("#design_curtain_relative_name");
        PointTree.openPointTreeWin();
    };


    /**
     * 添加窗帘图标
     */
    AddCurtain.addCurtain = function(){
        //图标模式，页面上展示低档温控器的小图标
        $("#design_area_demo").append('<div class="design_curtain design_initial_position">'
            +'<img class="blue_img" src="'+_ctx+'/static/pageDesign/icon/legend/curtain_blue.png">'
            +'<img class="gray_img" src="'+_ctx+'/static/pageDesign/icon/legend/curtain_gray.png"></div>');
        $(".design_curtain").draggable();
    };

    //判断将要关联调试按钮的点是否满足条件
    AddCurtain.verifyPoint = function(){
        $.ajax({
            url     : _ctx + '/btnEventController/getDebugInfo',
            type    : "post",
            dataType: 'json',
            data : {
                sysname : PointTree.$nodeId.val()
            },
            success : function(result) {
                //只有AO或DO表中也存在记录时，才能作为关联点使用
                if(result.status === "0"){
                    layer.msg("请先配置该点信息",{icon:2});
                    return false;
                }else{
                    PointTree.closeTreeWin();
                    return true;
                }
            }});
    }

    // //新增弹窗--确定按钮
    // AddCurtain.addConfirm = function(){
    //     var width = $("#design_add_curtain_width").val();
    //     var height = $("#design_add_curtain_height").val();
    //     var style = 'style="width:'+width+'px;height:'+height+'px;"';
    //
    //     //第一次添加图片
    //     var imgHtml = '<img class="design_curtain_img" src="'+ defaultImg +'" style>';
    //
    //
    //     $("#design_area_demo").append('<div class="area" '+ style +'></div>');
    //     $( ".design_area .area" ).click(AttributeWin.clickEvent).draggable().resizable({handles:"all"});
    //     BackArea.closeAddWin();
    // }
    //
    // //关闭新增弹窗
    // AddCurtain.closeAddWin = function () {
    //     layer.close(addIndex);
    //     $("#design_add_curtain_width").val(defaultWidth);
    //     $("#design_add_curtain_height").val(defaultHeight);
    // }


    // /**
    //  * 打开新增弹窗
    //  */
    // AddCurtain.openAddWin = function(){
    //     addIndex = layer.open({
    //         type: 1,
    //         title:"添加电动窗帘",
    //         area:['25vw','35vh'],
    //         // area:['300px','240px'],
    //         content: $('#design_add_curtain_win'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
    //         cancel: function(index, layero){
    //             AddCurtain.closeAddWin();
    //             return false;
    //         }
    //     });
    //
    // };



});

