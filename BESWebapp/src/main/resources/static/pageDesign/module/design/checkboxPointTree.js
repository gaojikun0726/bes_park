/**
 * 点的树
 */
var CheckboxPointTree = {
    $nodeId:"",
    $nodeName:""
};

layui.use(['layer','form'], function(){

    var layer = layui.layer;
    var form = layui.form;
    var pointTreeIndex;

    //设备树数据
    var treeData = "";
    //设备树
    var tree;

    /**
     * 设备树只在进入页面时查询一次数据，然后每次打开设备树弹窗，重新生成一回设备树，
     * 如果想刷新设备树的数据，需要重新进入页面
     *  如果每次打开弹窗就查询数据，容易出现树完全没加载的bug
     *  如果打开弹窗不重新生成树，选中回显容易出问题
     *  2020-07-02 by zs
     */
    $(function() {

        // initTree();
        CheckboxPointTree.getTreeData();
    });

    // 初始化设备类型树
    function initTree() {

        var setting = {
            view: {
                showIcon: false,
                fontCss: function ( treeId, treeNode ) {
                    return ( treeNode.highlight )
                        ? {color:'#A60000', 'font-weight':'bold'}
                        : {color:'#D1E3F9', 'font-weight':'normal'};
                }
            },
            check:{
                chkStyle:"checkbox",
                enable:true
            },
            data: {
                simpleData: {
                    enable:true,
                    idKey: 'f_sys_name',
                    pIdKey: 'f_psys_name',
                    rootPId: null
                },
                key: {
                    name: 'f_nick_name', // zTree 节点数据保存节点名称的属性名称。
                    // url:_ctx + "/view/basedatamanage/eqmanage/austere_devices_tree"
                }
            },
            callback: {
                onClick:function(event, treeId, node){
                    tree.expandNode(node, true, true, true);
                },
                onCheck : function (event, treeId, node) {
                    if(CheckboxPointTree.$nodeId){
                        var selectedId = CheckboxPointTree.$nodeId.attr("id");
                        if(node.f_type === "16"){
                            //虚点类型
                            CheckboxPointTree.visualPointCallback(selectedId,node);
                        }else{
                            CheckboxPointTree.pointCallback(selectedId,node);
                        }
                    }
                },
                onExpand:function(event, treeId, treeNode){
                    console.log(treeNode.name);
                },
                // beforeClick: function(treeId, treeNode, clickFlag) {
                //     return !treeNode.isParent;//当是父节点 返回false 不让选取
                // },
                beforeCheck:function(treeId, treeNode){
                    // return !treeNode.isParent;//当是父节点 返回false 不让选中
                    if(treeNode.isParent){
                        return false;
                    }
                    if(CheckboxPointTree.$nodeId){
                        var selectedId = CheckboxPointTree.$nodeId.attr("id");
                        if(treeNode.f_type === "16"){
                            //虚点类型
                            CheckboxPointTree.visualPointCallback(selectedId,treeNode);
                        }else{
                            CheckboxPointTree.pointCallback(selectedId,treeNode);
                        }
                    }
                }

            }
        };

        tree = $.fn.zTree.init($("#design_checkbox_tree"), setting,treeData.list);
        CheckboxPointTree.loadTree();

    }

    /**
     * 选中虚点类型的回调方法
     * @param selectedId 区分关联点的元素类型的id
     * @param node 选中的树节点
     */
    CheckboxPointTree.visualPointCallback = function(selectedId,node){
        $.ajax({
            url     : _ctx + '/btnEventController/getVisualType',
            type    : "post",
            dataType: 'json',
            data : {
                sysname : node.f_sys_name
            },
            success : function(result) {
                if(result && result.data){
                    var nodeType = result.data;
                    var vType = "";
                    //4,5,6,7  AI,AO,DI,DO
                    if(nodeType === "4"){
                        vType = "VAI";
                    }
                    if(nodeType === "5"){
                        vType = "VAO";
                    }
                    if(nodeType === "6"){
                        vType = "VDI";
                    }
                    if(nodeType === "7"){
                        vType = "VDO";
                    }
                    CheckboxPointTree.pointCallback(selectedId,node,vType);
                }
            }});
    }


    /**
     * 选中的节点类型的回调
     * @param selectedId 区分关联点的元素类型的id
     * @param node 选中的树节点
     * @param vType 虚点类型
     */
    CheckboxPointTree.pointCallback = function(selectedId,node,vType){
        var flag = false;
        if(selectedId.indexOf("scene") !== -1){
            if( node.f_type === "13" || vType === "VDO"){
                //DO
                var nodeName = node.f_sys_name;

                $.ajax({
                    url     : _ctx + '/btnEventController/getRelatedInfo',
                    type    : "post",
                    dataType: 'json',
                    async:false,
                    data : {
                        sysname : nodeName
                    },
                    success : function(result) {
                        //只有DO表中也存在记录时，才能作为关联点使用
                        if (result.status == '1') {
                            flag =  true;
                        }else
                        {
                            layer.msg("请先配置该点信息", {icon: 2});
                            flag =  false;
                            tree.checkNode(node,false,false);
                        }
                    }
                });
            }else{
                layer.msg("只有DO类型的点可以关联场景按钮",{icon:0});
                tree.checkNode(node,false,false);
                return;
            }
        }

        // if(CheckboxPointTree.$nodeId && CheckboxPointTree.$nodeName){
        //     if(flag){
        //         CheckboxPointTree.$nodeId.val(node.f_sys_name);
        //         CheckboxPointTree.$nodeName.val(node.f_nick_name);
        //     }else{
        //         CheckboxPointTree.$nodeId.val("");
        //         CheckboxPointTree.$nodeName.val("");
        //     }
        // }
    }



    /**
     * 获得树的数据
     */
    CheckboxPointTree.getTreeData = function(){
        $.ajax({
            type    : "post",
            url     : _ctx + "/view/basedatamanage/eqmanage/getTreeFormat",
            dataType: "json",
            success: function (result) {
              treeData = result;
              initTree();
            }
        });
    }

    /**
     * 填充树的数据
     */
    CheckboxPointTree.loadTree = function(){
        // var treeList = treeData.list || [];
        // tree.addNodes( null, treeList );


        // tree.expandAll(true);

        var treeObj = $.fn.zTree.getZTreeObj("design_checkbox_tree");
        //选中的树节点回显
        treeObj.checkAllNodes(false);
        if(CheckboxPointTree.$nodeId && CheckboxPointTree.$nodeId.val()){
            var selectedId = CheckboxPointTree.$nodeId.val();
            var selectedNode = treeObj.getNodeByParam("f_sys_name", selectedId);
            treeObj.selectNode(selectedNode);
        }

        var nodes = treeObj.getNodes();
        //展开第一层节点
        if (nodes.length>0) {
            treeObj.expandNode(nodes[0], true, false, true);
        }
    };

    //点击选择点，打开树弹窗
    CheckboxPointTree.openPointTreeWin = function () {
        // initTree();
        pointTreeIndex = layer.open({
            type: 1,
            title:"点的树状表",
            area:['500px','450px'],
            content: $('#design_checkbox_tree_win'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                CheckboxPointTree.closeTreeWin();
                return false;
            },
            success:function(){
                // initTree();
                // if(tree && tree.tree){
                //     tree.tree.destroy();
                // }
                // initTree();
                var treeObj = $.fn.zTree.getZTreeObj("design_checkbox_tree");
                treeObj.checkAllNodes(false);
                //选中的树节点回显
                if(CheckboxPointTree.$nodeName && CheckboxPointTree.$nodeName.val()){
                    var selectedId = CheckboxPointTree.$nodeName.val();
                    var nameArray = selectedId.split(",");
                    for(var i = 0; i < nameArray.length; i++){
                        var name = nameArray[i];
                        if(!name){
                            continue;
                        }
                        var node = treeObj.getNodeByParam("f_sys_name", name);
                        tree.checkNode(node,true,true);
                        tree.expandNode(node,true,true,true);
                        tree.selectNode(node);
                    }

                }
            }
        });
    }

    //树--确定按钮
    CheckboxPointTree.confirmBtn = function () {
        // var f_sys_name = CheckboxPointTree.$nodeId.val();
        // if(f_sys_name){
            //校验调试按钮关联
            var selectedId = CheckboxPointTree.$nodeName.attr("id");
            //点位置、单通道、多场景按钮关联点位校验
            if(selectedId.indexOf("scene") !== -1||selectedId.indexOf("point") !== -1||selectedId.indexOf("channel") !== -1){
                $("#inputTable").html("");
                var nodes = tree.getCheckedNodes();
                var nameStr = "";
                  for(var k = 0; k < nodes.length; k++){
                      var node = nodes[k];
                      if(node.isParent){
                          //只选中叶子节点
                          continue;
                      }
                      var nodeName = node.f_sys_name;
                        nameStr += nodeName + ",";

                      var tab = document.getElementById("inputTable");//获取table对象
                      var inpEle = tab.getElementsByTagName("input"); //获取table中所有的文本输入控件
                      var i = inpEle.length/2;

                      var trEle = '<tr>';
                      var inpTd =  '<td><input type="text" id="'+nodeName+'_scene'+i+'"   readonly autocomplete="off" class="layui-input"></td>';
                      var selTd = '<td><select class="relative_select"><option value="255">闭合</option> <option value="0">断开</option></select></td>';
                      $("#inputTable").append(trEle+inpTd+selTd+"</tr>");
                      form.render();
                      $('#'+nodeName+'_scene'+i).val(nodeName);
                  }
                if(nameStr.length > 1){
                    nameStr = nameStr.substring(0,nameStr.length-1);
                }
                $("#design_scene_btn_relative_name").val(nameStr);
                //加载表格选中行点击事件
                SceneBtn.initSelectedEvent();

                CheckboxPointTree.closeTreeWin();
                // CheckboxPointTree.verifySceneBtn(selectedId,"");//校验是否配置该点信息
            }
        // }else{
        //     layer.msg("请选择需要关联的点",{icon:0});
        // }

    }

    //树--取消按钮
    CheckboxPointTree.closeTreeWin = function () {
        CheckboxPointTree.$nodeId = "";
        CheckboxPointTree.$nodeName = "";
        layer.close(pointTreeIndex);
    }

     //判断要关联的点位是否符合条件(点位置按钮、单通道按钮、多场景按钮)
    CheckboxPointTree.verifySceneBtn = function(ele,nodeName){
        var sysName;
        if(ele.indexOf("scene") !== -1)
            sysName = nodeName;
        else
             sysName = CheckboxPointTree.$nodeId.val();

        $.ajax({
            url     : _ctx + '/btnEventController/getRelatedInfo',
            type    : "post",
            dataType: 'json',
            async:false,
            data : {
                sysname : sysName
            },
            success : function(result) {
                //只有DO表中也存在记录时，才能作为关联点使用
                if (result.status == '1') {
                    if (ele.indexOf("point") !== -1 || ele.indexOf("channel") !== -1) {
                        CheckboxPointTree.closeTreeWin();
                        return true;
                    } else {
                        CheckboxPointTree.closeTreeWin();
                        var tab = document.getElementById("inputTable");//获取table对象
                        var inpEle = tab.getElementsByTagName("input"); //获取table中所有的文本输入控件
                        var i = inpEle.length/2;

                        var trEle = '<tr>';
                        var inpTd =  '<td><input type="text" id="'+sysName+'_scene'+i+'"   readonly autocomplete="off" class="layui-input"></td>';
                        var selTd = '<td><select class="relative_select"><option value="255">闭合</option> <option value="0">断开</option></select></td>';
                        $("#inputTable").append(trEle+inpTd+selTd+"</tr>");
                        form.render();
                        $('#'+sysName+'_scene'+i).val(sysName);
                        /*var tab = document.getElementById("inputTable");//获取table对象
                        var inpEle = tab.getElementsByTagName("input"); //获取table中所有的文本输入控件
                        for(var j = 0;j<inpEle.length-1;j++){
                            if(nodeName ==inpEle[j].value ){
                                layer.msg("该点已被关联",{icon:0});
                                var flag = false;
                                break;
                            }
                        }*/
                        SceneBtn.initSelectedEvent();
                        return true;
                    }
                }else
                    {
                        layer.msg("请先配置该点信息", {icon: 2});
                        return false;
                    }
                }
        });
    }

});
