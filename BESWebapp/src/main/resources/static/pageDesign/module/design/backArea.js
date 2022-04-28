/**
 * 背景区域操作
 */
var BackArea = {};

layui.use(['layer'], function(){
    var layer = layui.layer;
    //新增、修改弹窗
    var addIndex,editIndex;

    //修改的背景区域Jquery
    var editJquery;

    //新增时默认的宽度、高度
    var defaultWidth = 150,defaultHeight = 150;
    $(function () {
        contextMenu();
    });

    //右键菜单
    function contextMenu(){
        $.contextMenu({
            selector: '#design_area_demo .area', //右键选择器
            callback: function(key, options) {//点击回调处理
                var $dom = options.$trigger;
                if(key === "update"){
                    //修改
                    editJquery = $dom;
                    var width = options.$trigger.css('width');
                    var height = options.$trigger.css('height');
                    width = width.replace("px","");
                    height = height.replace("px","");
                    $("#design_edit_area_width").val(width);
                    $("#design_edit_area_height").val(height);
                    BackArea.openEditWin();
                }
                if(key === "delete"){
                    //删除
                    layer.confirm('确定删除该背景区域吗?', {title:'删除提示'}, function(index){
                        $dom.remove();

                        layer.close(index);
                    });

                }
            },
            items: {//菜单列表配置
                "update": {name: "修改", icon: "fa-edit",disabled: false},
                "delete":{name:"删除",icon:'fa-trash-o'}
            }
        });
    }

    //打开新增弹窗
    BackArea.openAddWin = function () {
        addIndex = layer.open({
            type: 1,
            title:"添加背景区域",
            area:['25vw','35vh'],
            // area:['300px','240px'],
            content: $('#design_add_area_win'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                BackArea.closeAddWin();
                return false;
            }
        });
    }

    //新增弹窗--确定按钮
    BackArea.addConfirm = function(){
        var width = $("#design_add_area_width").val();
        var height = $("#design_add_area_height").val();
        var style = 'style="width:'+width+'px;height:'+height+'px;"';

        $("#design_area_demo").append('<div class="area" '+ style +'></div>');
        $( ".design_area .area" ).click(AttributeWin.clickEvent).draggable().resizable({handles:"all"});
        BackArea.closeAddWin();
    }

    //关闭新增弹窗
    BackArea.closeAddWin = function () {
        layer.close(addIndex);
        $("#design_add_area_width").val(defaultWidth);
        $("#design_add_area_height").val(defaultHeight);
    }


    //打开修改弹窗
    BackArea.openEditWin = function () {
        editIndex = layer.open({
            type: 1,
            title:"背景区域编辑",
            area:['25vw','35vh'],
            // area:['300px','200px'],
            content: $('#design_edit_area_win'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                BackArea.closeEditWin();
                return false;
            }
        });
    }

    //修改弹窗--确定按钮
    BackArea.editConfirm = function(){
        var width = $("#design_edit_area_width").val();
        var height = $("#design_edit_area_height").val();
        editJquery.css("width",width+"px");
        editJquery.css("height",height+"px");
        BackArea.closeEditWin();
    }

    //关闭修改弹窗
    BackArea.closeEditWin = function () {
        layer.close(editIndex);
        $("#design_edit_area_width").val("");
        $("#design_edit_area_height").val("");
    }
});