/**
 * 区域管理
 */
var DesignArea = {
    $tree:$("#areaTree"),
    infoIndex:"",
    sortIndex:""
};

layui.use(['layer','form','jquery'], function(){
    var layer = layui.layer;
    var form = layui.form;
    var $ = layui.jquery;

    /**
     * 加载树
     */
    DesignArea.initTree = function(){
        this.$tree.jstree({
            core: {
                check_callback: true,
                data: function (obj, callback) {
                    $.ajax({
                        url: _ctx+'/view/pageDesign/queryAreaTree',
                        type: 'post',
                        dataType: 'json',
                        async:false,
                        success:function (data) {
                            callback.call(this, data);
                        },
                        error:function () {

                        }
                    });

                },
                themes: {
                    // "stripes": true,
                    "dots":false,
                    "ellipsis":true
                },
            },
            // search : DesignArea.treeSearch(),
            contextmenu:{
                select_node:true,
                show_at_node:true,
                'items' : DesignArea.contextmenu
            },
            plugins: ['contextmenu','types','search'],
            // plugins: ['types'],
            types: {
                "floor":{'icon':_ctx+"/static/pageDesign/icon/tree/house_16.png"},
                // "root":{'icon':"/manage/images/jstree/root3.png"},
                // "leaf": {'icon': "/manage/images/jstree/leaf2.png"},
                // "root":{"icon": "fa fa-folder icon-state-success"}
            }
        });
        this.$tree.bind('activate_node.jstree', function (obj, e) {
            if(e.node.parent === '#'){
                //点击根节点，如果有子节点，默认显示第一个子节点
                if(e.node.children.length > 0){
                    var nodeId = e.node.children[0];
                }

            }else{
                nodeId = e.node.id;
            }
            if($("#design_mode").css("display") === "none"||$("#designFlow_mode").css("display") === "none"){
                //设计模式下
                var path = "view";
                DesignArea.saveDesignContent(nodeId);
            }else{
                path = "displayView";
                var src = _ctx+"/view/pageDesign/"+path+"?areaId="+nodeId;
                $("#design_frame_one").attr("src",src);
                $("#areaId").val(nodeId);
            }
        });

        // 所有节点都加载完后
        this.$tree.on("ready.jstree", function (event, data) {
            // data.instance.open_all();
            var obj = data.instance.get_node(event.target.firstChild.firstChild.lastChild);
            // data.instance.select_node(obj);
            if(obj.children.length > 0){
                var nodeId = obj.children[0];
                var src = _ctx+"/view/pageDesign/displayView?areaId="+nodeId;
                //默认选中第一个子节点
                DesignArea.$tree.jstree('select_node',nodeId  , true);
                $("#design_frame_one").attr("src",src);
                $("#areaId").val(nodeId);
            }

            // var rootID = data.instance.get_node(event.target.firstChild.firstChild.lastChild).id;
            // DesignArea.parentId = rootID;
            // DesignArea.initTable();
            data.instance.open_node(1); // 展开root节点
            // data.instance.clear_state(); // <<< 这句清除jstree保存的选中状态
            // $tree.jstree("deselect_all", true);
        });
    };

    /**
     * 右键菜单
     * @param node
     */
    DesignArea.contextmenu = function (node) {
        //不同节点菜单不一样
        var items = {
            'item1': {
                'label': '新增',
                'icon': _ctx+"/static/pageDesign/images/jstree/menu/5011.png",
                'action': function () {
                    var title = "新增";
                    function addCallback(node){
                        $("#parentId").val(node.id);
                        $("#parentName").val(node.text);
                    }
                    DesignArea.openInfoWin(title,node,addCallback);

                }
            }
            , 'item2': {
                'label': '修改',
                'icon': _ctx+"/static/pageDesign/images/jstree/menu/53.png",
                'action': function () {
                    var title = "修改";
                    function editCallback(node){
                        $("#areaIdAdd").val(node.id);
                        $("#areaName").val(node.text);
                        var parent = DesignArea.$tree.jstree("get_node", node.parent);
                        $("#parentName").val(parent.text);
                        // $("#parentName").hide();
                    }
                    DesignArea.openInfoWin(title,node,editCallback);
                }
            }
            , 'item3': {
                'label': '删除',
                'icon': _ctx+"/static/pageDesign/images/jstree/menu/57.png",
                'action': function () {

                   DesignArea.deleteNode(node);
                }
            },
            'item4':{
                'label':"复制",
                'icon': _ctx+"/static/pageDesign/images/jstree/menu/copy.png",
                'action': function () {

                    DesignArea.openCopyWin(node);
                }
            }
            // 'item4': {
            //     'label': '下级排序',
            //     'icon': _ctx+"/static/pageDesign/images/jstree/menu/5111.png",
            //     'action': function () {
            //         // DesignArea.typeOrder(node);
            //     }
            // }

        };


        if(typeof node !== "undefined"){
            if(node.original.parent === '#'){
                //根节点右键菜单
                // delete items.item1;
                // delete items.item2;
                delete items.item3;
                // delete items.item4;
            }
            // if(node.children.length !== 0){
            //     //有下级节点不允许删除
            //     delete items.item3;
            // }else{
            //     // delete items.item4;
            // }
            return items;
        }

    };


    /**
     * 打开复制弹窗
     */
    DesignArea.openCopyWin = function(node){
        DesignArea.copyIndex = layer.open({
            type: 1,
            area: ['40vw', '56vh'],
            // area: ['600px', '400px'],
            fix: false, //不固定
            maxmin: true,
            shadeClose: true,
            shade: 0.4,
            title: "页面内容复制",
            content: $("#copyWin"),
            cancel: function(index, layero){
                DesignArea.closeCopyWin();
                return false;
            },
            success:function(){
                $("#copyFromId").val(node.id);
                $("#copyFromName").val(node.text);

                DesignArea.initCopyTree();
            }
        });
    }

    DesignArea.closeCopyWin = function(){
        $("#copyFromId").val("");
        $("#copyFromName").val("");
        layer.close(DesignArea.copyIndex);
    }

    /**
     * 加载复制树
     */
    DesignArea.initCopyTree = function(){
        var copyTree = $("#areaCopyTree");
        if(copyTree.jstree(true)){
            copyTree.jstree(true).destroy();
        }
        copyTree.jstree({
            core: {
                check_callback: true,
                data: function (obj, callback) {
                    $.ajax({
                        url: _ctx+'/view/pageDesign/queryAreaTree',
                        type: 'post',
                        dataType: 'json',
                        async:false,
                        success:function (data) {
                            callback.call(this, data);
                        },
                        error:function () {

                        }
                    });

                },
                themes: {
                    // "stripes": true,
                    "dots":false,
                    "ellipsis":true
                },
            },
            plugins: ['types','search'],
            // plugins: ['types'],
            types: {
                "floor":{'icon':_ctx+"/static/pageDesign/icon/tree/house_16.png"},
            }
        });
        copyTree.bind('activate_node.jstree', function (obj, e) {

        });

        // 所有节点都加载完后
        copyTree.on("ready.jstree", function (event, data) {
            data.instance.open_all();
        });
    }

    /**
     * 确定复制
     */
    DesignArea.copy = function(){
        var copyFromId = $("#copyFromId").val();
        var copyFromName =  $("#copyFromName").val();
        var treeNode = $("#areaCopyTree").jstree(true).get_selected(true); //获取所有选中的节点对象
        if(treeNode && treeNode.length > 0){
            var selectedId = treeNode[0].id;
            var selectedName = treeNode[0].text;
            if(selectedId === copyFromId){
                layer.msg("同一个页面不可复制",{icon:0});
                return;
            }
        }else{
            layer.msg("请选中一个节点",{icon:0});
            return ;
        }

        layer.confirm("从【"+copyFromName+"】复制到【"+selectedName+"】，该操作无法恢复，确定要复制？", function () {
            $.ajax({
                url: _ctx + "/view/pageDesign/copyHtml",
                type: 'post',
                data: {
                    copyId:copyFromId,
                    targetId: selectedId
                },
                dataType:'json',
                // contentType : "application/json; charset=utf-8",
                async: false,
                success: function (result) {

                    if(result && result.status === "1"){
                        layer.msg("复制成功",{icon:1});
                        DesignArea.closeCopyWin();
                    }else{
                        layer.msg("复制失败",{icon:2});
                    }

                }, error: function () {
                    layer.msg("复制失败", {icon: 2});
                }
            });
        });
    }

    /**
     * 打开新增/修改弹窗
     * @param title
     * @param node
     * @param callback
     */
    DesignArea.openInfoWin = function(title,node,callback){
        DesignArea.infoIndex = layer.open({
            type: 1,
            area: ['32vw', '30vh'],
            // area: ['500px', '300px'],
            fix: false, //不固定
            maxmin: true,
            shadeClose: true,
            shade: 0.4,
            title: title,
            content: $("#infoWin"),
            cancel: function(index, layero){
                DesignArea.closeInfoWin();
                return false;
            },
            success:function(){
                callback(node);
                // $("#parentId").val(node.id);
                // $("#parentName").val(node.text);
            }
        });
    }

    /**
     * 保存区域信息
     */
    DesignArea.save = function(){
        var areaIdAdd = $("#areaIdAdd").val();
        var parentId = $("#parentId").val();
        var areaName = $("#areaName").val();
        var url = _ctx +"/view/pageDesign/addArea";
        if(areaIdAdd){
            url = _ctx +"/view/pageDesign/editArea";
        }
        $.ajax({
            url : url,
            type : "post",
            data : JSON.stringify({
                id:areaIdAdd,
                parent:parentId,
                name:areaName,
            }),
            dataType:'json',
            contentType : "application/json; charset=utf-8",
            success : function(result){
                if(result && result.status === "1"){
                    layer.msg("保存成功",{icon:1});
                    DesignArea.closeInfoWin();
                    DesignArea.refreshTree();
                }else{
                    layer.msg("保存失败",{icon:2});
                }
            },error:function () {
                layer.msg("保存失败",{icon:2});
            }
        });
    }


    /**
     * 关闭并清空弹窗
     */
    DesignArea.closeInfoWin = function(){
        layer.close(DesignArea.infoIndex);
        $("#infoWin").hide();
        $("#parentId").val("");
        $("#parentName").val("");
        $("#areaIdAdd").val("");
        $("#areaName").val("");
    }

    /**
     * 刷新树
     */
    DesignArea.refreshTree = function(){
        this.$tree.jstree(true).refresh();
    }

    /**
     * 删除节点
     * @param node
     */
    DesignArea.deleteNode = function(node){
        layer.confirm("删除之后将无法恢复，您确定要删除？", function () {
            $.ajax({
                url: _ctx + "/view/pageDesign/hideChildrenArea",
                type: 'post',
                data: JSON.stringify({
                    id: node.id
                }),
                dataType:'json',
                contentType : "application/json; charset=utf-8",
                async: false,
                success: function (result) {

                    if(result && result.status === "1"){
                        layer.msg("删除成功",{icon:1});
                        $.jstree.destroy();
                        DesignArea.initTree();
                        // $("#design_frame_one").attr("src","");

                    }else{
                        layer.msg("删除失败",{icon:2});
                    }

                }, error: function () {
                    layer.msg("删除失败", {icon: 2});
                }
            });
        });
    }

    /**
     * 切换之前是否保存页面
     */
    DesignArea.saveDesignContent = function(nodeId){
        layer.confirm('是否保存当前页面?', {title:' 保存提示'}, function(index){
            SaveDesign.saveContent($("#areaId").val());
            $.when(SaveDesign.method).done(function () {
                //要执行的操作
                DesignArea.changeFrame(nodeId);
            });
            layer.close(index);
        },function(index){
            layer.close(index);
            DesignArea.changeFrame(nodeId);

        });
    }

    /**
     * 切换frame路径
     */
    DesignArea.changeFrame = function(nodeId){
        var areaId = nodeId;
        if(areaId.indexOf("_lct")>0){//流程图页面
            $("#design_frame_two").prop("src",_ctx + "/view/pageDesign/view?areaId="+areaId);
        }else if(areaId.indexOf("_xtnx")>0){
            $("#design_frame_energyEfficiency").prop("src",_ctx + "/view/pageDesign/view?areaId="+areaId);
        }else{
            $("#design_frame_one").prop("src",_ctx + "/view/pageDesign/view?areaId="+areaId);
        }
        $("#areaId").val(nodeId);
    }

    $(function () {
        DesignArea.initTree();
    });


    /**
     * 刷新页面或关闭页面的浏览器监听事件
     */
    /*如果是设计模式下，离开页面之前浏览器给出提示*/
    window.onunload = function(event){
        if($("#design_mode").css("display") === "none"){
            //如果是设计模式下
            event.preventDefault();
            return false;
        }

    }

    window.onbeforeunload = function(event){
        if($("#design_mode").css("display") === "none"){
            //如果是设计模式下
            event.preventDefault();
            return false;
        }
    }


    /**
     * 隐藏区域树
     */
    DesignArea.hideArea = function(){
        var $parent = $(".design_area_tree_div");
        $parent .css("width","3.5%");
        $parent.find(".design_area_nav .Subtitle").hide();
        $parent.find("#areaTree").hide();
        $(".design_area_right_div").css("width","96.5%");
        $parent.find(".show_area").show();
        $parent.find(".hide_area").hide();
    }

    /**
     * 显示区域树
     */
    DesignArea.showArea = function(){
        var $parent = $(".design_area_tree_div");
        $parent.css("width","14.4%");
        $parent.find(".design_area_nav .Subtitle").show();
        $parent.find("#areaTree").show();
        $(".design_area_right_div").css("width","85.5%");
        $parent.find(".show_area").hide();
        $parent.find(".hide_area").show();
    }

});