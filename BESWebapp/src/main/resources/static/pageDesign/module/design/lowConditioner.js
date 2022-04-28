/**
 * 低档温控器
 */

var lowConditioner = {
    //温控器类型，low 低档， middle 中档
    type:""
};

layui.use(['layer','form'], function(){
    var layer = layui.layer;
    var form = layui.form;
    //修改弹窗,关联点弹窗
    var editIndex,relativeIndex;
    //修改操作点击的图片，关联操作点击的图片
    var editJquery,relativeJquery;
    $(function(){
        contextMenu();
    });


    //右键菜单
    function contextMenu(){
        $.contextMenu({
            selector: '#design_area_demo .design_low_conditioner,#design_area_demo .design_middle_conditioner', //右键选择器
            callback: function(key, options) {//点击回调处理
                var $dom = options.$trigger;
                if(key === "relative"){
                    //关联点
                    relativeJquery = $dom;
                    var classList = $dom[0].classList;
                    if(classList.contains("design_middle_conditioner")){
                        lowConditioner.type = "middle";
                    }else if(classList.contains("design_low_conditioner")){
                        lowConditioner.type = "low";
                    }
                    lowConditioner.openRelativeWin();

                }
                if(key === "delete"){
                    //删除
                    layer.confirm('确定删除该温控器吗?', {title:'删除提示'}, function(index){
                        $dom.remove();

                        layer.close(index);
                    });

                }
            },
            items: {//菜单列表配置
                "relative": {name: "关联模块", icon: "fa-paperclip",disabled:false},
                "delete":{name:"删除",icon:'fa-trash-o'}
            }
        });
    }


    //关联模块弹窗
    lowConditioner.openRelativeWin = function(){
        var dataId = relativeJquery.attr("data-id");
        relativeIndex = layer.open({
            type: 1,
            title:"关联模块",
            area:['34vw','24vh'],
            // area:['400px','200px'],
            content: $('#design_low_conditioner_relative'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                lowConditioner.closeRelativeWin();
                return false;
            },
            success:function () {
                if(dataId){
                    $("#design_low_conditioner_relative_id").val(dataId);
                    // todo 根据id查询name
                    $("#design_low_conditioner_relative_name").val(dataId);
                }
            }
        });
    }
    //关闭并清空关联点弹窗
    lowConditioner.closeRelativeWin = function(){
        layer.close(relativeIndex);
        $("#design_low_conditioner_relative_id").val("");
        $("#design_low_conditioner_relative_name").val("");
    }

    //关联点弹窗--关联点击事件
    lowConditioner.relativeBtn = function () {
        var dataId = $("#design_low_conditioner_relative_id").val();
        if(!dataId){
            layer.msg("请选择关联点",{icon:0});
            return;
        }
        relativeJquery.attr("data-id",dataId).attr("title",dataId);
        lowConditioner.closeRelativeWin();
    }

    //关联弹窗--选择模块点击事件
    lowConditioner.choosePoint = function () {
        PointTree.$nodeId = $("#design_low_conditioner_relative_id");
        PointTree.$nodeName = $("#design_low_conditioner_relative_name");
        PointTree.openPointTreeWin();
    }

    /**
     * 查询模块类型 3 低档温控器
     */
    lowConditioner.queryModuleType = function(f_sys_name){

        return new Promise(function (resolve, reject){
            $.ajax({
                url: _ctx + "/btnEventController/queryModuleType",
                type    : "post",
                dataType: 'json',
                async:false,
                data : {
                    sysname : f_sys_name
                },
                success : function(data) {
                    resolve(data);
                }});
        });
    }


    /**
     * 选中节点时校验
     */
    lowConditioner.verifySelectedNode = function(node){
        var flag = false;
        lowConditioner.queryModuleType(node.f_sys_name).then(function(result){
            if(result.status  ==="1"){
                var type = lowConditioner.type;
                if(type === "low"){
                    if(result.data !== "3"){
                        layer.msg("只有低档温控器模块可以关联",{icon:0});
                    }else{
                        flag = true;
                    }
                }
                if(type === "middle"){
                    if(result.data !== "13"){
                        layer.msg("只有中档温控器模块可以关联",{icon:0});
                    }else{
                        flag = true;
                    }
                }
                if(PointTree.$nodeId && PointTree.$nodeName){
                    if(flag){
                        PointTree.$nodeId.val(node.f_sys_name);
                        PointTree.$nodeName.val(node.f_nick_name);
                    }else{
                        PointTree.$nodeId.val("");
                        PointTree.$nodeName.val("");
                    }
                }
            }
        });
    }

    /**
     * 模块关联--确定按钮
     */
    lowConditioner.confirmModule = function(f_sys_name){

        lowConditioner.queryModuleType(f_sys_name).then(function(result){
            var flag = false;
            if(result.status  ==="1"){
                var type = lowConditioner.type;
                if(type === "low"){
                    if(result.data !== "3"){
                        layer.msg("只有低档温控器模块可以关联",{icon:0});
                    }else{
                        flag = true;
                    }
                }
                if(type === "middle"){
                    if(result.data !== "13"){
                        layer.msg("只有中档温控器模块可以关联",{icon:0});
                    }else{
                        flag = true;
                    }
                }
                if(flag){
                    PointTree.closeTreeWin();
                }
            }
        });
    }

});