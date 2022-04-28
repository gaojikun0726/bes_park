/**
 * 点的树
 */
var PointTree = {
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
        PointTree.getTreeData();
    });

    // 初始化设备类型树
    function initTree() {
        // if(tree && tree.tree){
        //     tree.tree.destroy();
        // }
        tree = new Tree({
            container: 'design_point_tree',
            idKey    : 'f_sys_name',
            pIdKey   : 'f_psys_name',
            name     : 'f_nick_name',
            setting: {
                view: {
                    showIcon: false,
                    fontCss: function ( treeId, treeNode ) {
                        return ( treeNode.highlight )
                            ? {color:'#A60000', 'font-weight':'bold'}
                            : {color:'#333', 'font-weight':'normal'};
                    }
                }
            },
            callback: function (node) {
                var flag = false;
                if(PointTree.$nodeId){
                    var selectedId = PointTree.$nodeId.attr("id");
                    if(selectedId.indexOf("low_conditioner") !== -1){
                        if(node.f_type === "9"){
                            lowConditioner.verifySelectedNode(node);
                            return;
                        }else{
                            layer.msg("只有模块类型的节点可以关联",{icon:0});
                        }
                    }
                    if(node.f_type === "16"){
                        //虚点类型
                        PointTree.visualPointCallback(selectedId,node);
                    }else{
                        PointTree.pointCallback(selectedId,node);
                    }

                }
                // if(PointTree.$nodeId && PointTree.$nodeName){
                //     if(flag){
                //         PointTree.$nodeId.val(node.f_sys_name);
                //         PointTree.$nodeName.val(node.f_nick_name);
                //     }else{
                //         PointTree.$nodeId.val("");
                //         PointTree.$nodeName.val("");
                //     }
                // }

            }
        });
        PointTree.loadTree();

    }

    /**
     * 选中虚点类型的回调方法
     * @param selectedId 区分关联点的元素类型的id
     * @param node 选中的树节点
     */
    PointTree.visualPointCallback = function(selectedId,node){
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
                    PointTree.pointCallback(selectedId,node,vType);
                }
            }});
    }


    /**
     * 选中的节点类型的回调
     * @param selectedId 区分关联点的元素类型的id
     * @param node 选中的树节点
     * @param vType 虚点类型
     */
    PointTree.pointCallback = function(selectedId,node,vType){
        var flag = false;
        if(selectedId.indexOf("curtain") !== -1){
            //AO点-窗帘行程关联窗帘图标
            if(node.f_type === "11"){
                flag = true;
            }else{
                layer.msg("只有AO类型的点可以关联",{icon:0});
            }
        }
        if(selectedId.indexOf("debug") !== -1){
            //调试按钮：只有AO/DO类型的点才可以进行调试（AO11  DO13）
            if(node.f_type === "11" || node.f_type === "13" || vType === "VAO" || vType === "VDO"){
                //_ctx +"/view/basedatamanage/eqmanage/getdubugNodeInfo/" + variableUrl,
                // if(PointTree.verifyDebugBtn(node)){
                flag = true;
                // }
            }else{
                layer.msg("只有AO/DO类型的点可以关联",{icon:0});
            }

        }else if(selectedId.indexOf("point") !== -1||selectedId.indexOf("channel") !== -1){
            //点位置按钮、单通道按钮、场景按钮：只有DO类型的点才可以进行调试（DO 13）
            if( node.f_type === "13" || vType === "VDO"){
                flag = true;
            }else{
                layer.msg("只有DO类型的点可以关联",{icon:0});
            }
        }
        if(selectedId.indexOf("scene") !== -1){
            if( node.f_type === "13" || vType === "VDO"){
                //DO
                var nodeName = node.f_sys_name;
                var sceneSelectedId = PointTree.$nodeId.attr("id");

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
                            if(flag){
                                var tab = document.getElementById("inputTable");//获取table对象
                                var inpEle = tab.getElementsByTagName("input"); //获取table中所有的文本输入控件
                                for(var j = 0;j<inpEle.length-1;j++){
                                    if(j%2 ==0){
                                        if(nodeName ==inpEle[j].value ){
                                            layer.msg("该点已被关联",{icon:0});
                                            flag = false;
                                        }
                                    }
                                }
                            }
                            if(flag){
                                PointTree.closeTreeWin();
                                var tab = document.getElementById("inputTable");//获取table对象
                                var inpEle = tab.getElementsByTagName("input"); //获取table中所有的文本输入控件
                                var i = inpEle.length/2;

                                var trEle = '<tr>';
                                var inpTd =  '<td><input type="text" id="'+nodeName+'_scene'+i+'"   readonly autocomplete="off" class="layui-input"></td>';
                                var selTd = '<td><select class="relative_select"><option value="255">闭合</option> <option value="0">断开</option></select></td>';
                                $("#inputTable").append(trEle+inpTd+selTd+"</tr>");
                                form.render();
                                $('#'+nodeName+'_scene'+i).val(nodeName);
                                //加载表格选中行点击事件
                                SceneBtn.initSelectedEvent();
                            }
                        }else
                        {
                            layer.msg("请先配置该点信息", {icon: 2});
                            flag =  false;
                        }
                    }
                });
                flag = flag;
            }else{
                layer.msg("只有DO类型的点可以关联场景按钮",{icon:0});
                return;
            }
        }
        if(selectedId.indexOf("textbox") !== -1 || selectedId.indexOf("label") !== -1){
            //文本框  关联 AI DI DO 类型的点  10 12 13
            if(node.f_type === "10" || node.f_type === "12" || node.f_type === "13"  || vType === "VAI"  || vType === "VDI"|| vType === "VDO"){
                flag = true;
            }else{
                layer.msg("只有AI/DI/DO类型的点可以关联",{icon:0});
            }
        }
        // if(selectedId.indexOf("label") !== -1){
        //     //标签  关联 AI DI DO 类型的点  10 12 13
        //     if(node.f_type === "10" || node.f_type === "12" || node.f_type === "13"){
        //         flag = true;
        //     }else{
        //         layer.msg("只有AI/DI/DO类型的点可以关联标签",{icon:0});
        //     }
        // }
        if(selectedId.indexOf("img") !== -1){
            //图片  关联 AO DI DO 类型的点 11 12 13
            if(node.f_type === "12" || node.f_type === "13" || node.f_type === "11" || vType === "VAO" || vType === "VDI" || vType === "VDO"){
                flag = true;
            // } else if(node.f_type === "9"){
            //     //查询关联模块的模块类型
            //     $.ajax({
            //         url: _ctx + "/view/basedatamanage/eqmanage/getRelativeModuleTypeInfo",
            //         type    : "post",
            //         dataType: 'json',
            //         async:false,
            //         data : {
            //             sysname : node.f_sys_name
            //         },
            //         success : function(result) {
            //             if(result.status  =="1"){
            //                 if(result.data == "3"){//低配温控器
            //                     /*$('#settingsTable').attr('id',node.f_sys_name);*/
            //                     flag = true;
            //                 }else if(result.data == "13"){//高配温控器
            //                     /*$('#settingsTable').attr('id',node.f_sys_name);*/
            //                     flag = true;
            //                 }else{
            //                     layer.msg("只有DI/DO、温控器模块可以关联图片",{icon:0});
            //                 }
            //             }
            //         }
            //     });

            }else{
                layer.msg("只有AO/DI/DO类型可以关联图片",{icon:0});
            }
        }
        // if(selectedId.indexOf("temp") !== -1){
        //     //图片  关联 DI DO 类型的点 12 13,,,温控器模块9
        //     flag = true;
        //     var $textbox = $("#design_area_demo").find(".design_tempImg");
        //     var sysList="";
        //     for(var i = 0; i < $textbox.length; i++){
        //         sysList += $($textbox[i]).data("id")+",";
        //     }
        //     if(sysList.indexOf(node.f_sys_name)>-1){
        //         layer.msg("该模块已被关联，请重新选择",{icon:0});
        //         flag = false;
        //     }
        //     if(flag){
        //         if(node.f_type === "9"){
        //             //查询关联模块的模块类型
        //             $.ajax({
        //                 url: _ctx + "/view/basedatamanage/eqmanage/getRelativeModuleTypeInfo",
        //                 type    : "post",
        //                 dataType: 'json',
        //                 async:false,
        //                 data : {
        //                     sysname : node.f_sys_name
        //                 },
        //                 success : function(result) {
        //                     if(result.status  =="1"){
        //                         if(result.data == "3"){//低配温控器
        //
        //                             flag = true;
        //                         }else if(result.data == "13"){//高配温控器
        //
        //                             flag = true;
        //                         }else{
        //                             layer.msg("只有DI/DO、温控器模块可以关联图片",{icon:0});
        //                         }
        //                     }
        //                 }
        //             });
        //
        //         }
        //         else{
        //             layer.msg("只有温控器模块可以关联",{icon:0});
        //         }
        //     }
        //
        // }
        if(selectedId.indexOf("flow") !== -1) {
            //流程图按钮  关联 DI DO AI AO 类型的点 12 13
            if (node.f_type === "10" || node.f_type === "11" || node.f_type === "12" || node.f_type === "13" || node.f_type ==="16") {
                flag = true;
            } else {
                layer.msg("只有AI/AO、DI/DO可以关联", {icon: 0});
            }
        }
        if(selectedId.indexOf("energyEfficiency") !== -1){
            if( node.f_type === "10"|| node.f_type === "11"||node.f_type === "12" || node.f_type === "13"||node.f_type === "16"){//16为虚点其他ao\ai\do\di点
                var nodeName = node.f_sys_name;

                $.ajax({
                    url     : _ctx + '/btnEventController/getDebugInfo',
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
                            if(flag){
                                var tab = document.getElementById("inputPointTable");//获取table对象
                                var inpEle = tab.getElementsByTagName("input"); //获取table中所有的文本输入控件
                                for(var j = 0;j<inpEle.length;j++){
                                    if(nodeName ==inpEle[j].value ){
                                        layer.msg("该点已被关联",{icon:0});
                                        flag = false;
                                    }
                                }
                            }
                            if(flag){
                                PointTree.closeTreeWin();
                                var tab = document.getElementById("inputPointTable");//获取table对象
                                var inpEle = tab.getElementsByTagName("input"); //获取table中所有的文本输入控件
                                var i = inpEle.length;

                                var trEle = '<tr>';
                                var inpTd =  '<td><input type="text" id="'+nodeName+'_energyEfficiency'+i+'"   readonly autocomplete="off" class="layui-input"></td>';
                                $("#inputPointTable").append(trEle+inpTd+"</tr>");
                                form.render();
                                $('#'+nodeName+'_energyEfficiency'+i).val(nodeName);
                                //加载表格选中行点击事件
                                AddEnergyEfficiency.initSelectedEvent();
                            }
                        }else
                        {
                            layer.msg("请先配置该点信息", {icon: 2});
                            flag =  false;
                        }
                    }
                });
                flag = flag;
            }else{
                layer.msg("只有AO、AI/DO、DI类型的点可以进行调试",{icon:0});
                return;
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



    /**
     * 获得树的数据
     */
    PointTree.getTreeData = function(){
        $.ajax({
            type    : "post",
            url: _ctx + "/view/basedatamanage/eqmanage/getTreeFormat",
            // url     : _ctx + "/view/basedatamanage/eqmanage/austere_devices_tree",
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
    PointTree.loadTree = function(){
        var treeList = treeData.list || [];
        tree.loadData(treeList);
        // tree.tree.expandAll(true);

        var treeObj = $.fn.zTree.getZTreeObj("design_point_tree");
        //选中的树节点回显
        if(PointTree.$nodeId && PointTree.$nodeId.val()){
            var selectedId = PointTree.$nodeId.val();
            var selectedNode = treeObj.getNodeByParam("f_sys_name", selectedId);
            treeObj.selectNode(selectedNode);
        }

        // var nodes = treeObj.getNodes();
        // //展开第一层节点
        // if (nodes.length>0) {
        //     treeObj.expandNode(nodes[0], true, false, true);
        // }
    }

    //点击选择点，打开树弹窗
    PointTree.openPointTreeWin = function () {
        //数据量大，为确保树加载添加判断
        var treeObj = $.fn.zTree.getZTreeObj("design_point_tree");
        if(!treeObj){
            //如果树没加载,再加载一遍树
            initTree();
        }
        // initTree();
        pointTreeIndex = layer.open({
            type: 1,
            title:"点的树状表",
            area:['500px','450px'],
            content: $('#design_point_tree_win'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                PointTree.closeTreeWin();
                return false;
            },
            success:function(){
                // initTree();
                // if(tree && tree.tree){
                //     tree.tree.destroy();
                // }
                // initTree();
                var treeObj = $.fn.zTree.getZTreeObj("design_point_tree");
                //选中的树节点回显
                if(PointTree.$nodeId && PointTree.$nodeId.val()){
                    var selectedId = PointTree.$nodeId.val();
                    var selectedNode = treeObj.getNodeByParam("f_sys_name", selectedId);
                    treeObj.selectNode(selectedNode);
                }
            }
        });
    }

    //树--确定按钮
    PointTree.confirmBtn = function () {
        var f_sys_name = PointTree.$nodeId.val();
        if(f_sys_name){
            //校验调试按钮关联
            var selectedId = PointTree.$nodeId.attr("id");
            if(selectedId.indexOf("low_conditioner") !== -1){
                //低档温控器
              lowConditioner.confirmModule(f_sys_name);
              return;
            }
            if(selectedId.indexOf("curtain") !== -1){
                AddCurtain.verifyPoint();
            }
            if(selectedId.indexOf("debug") !== -1){
                PointTree.verifyDebugBtn();
            }
            if(selectedId.indexOf("textbox") !== -1){
                AddTextbox.verifyTextboxNew();
            }//点位置、单通道、多场景按钮关联点位校验
            if(selectedId.indexOf("scene") !== -1||selectedId.indexOf("point") !== -1||selectedId.indexOf("channel") !== -1){
                PointTree.verifySceneBtn(selectedId,"");//校验是否配置该点信息
            }
            if(selectedId.indexOf("label") !== -1){
                AddLabel.verifyLabelNew();
            }
            if(selectedId.indexOf("img") !== -1){
                AddImg.verifyImageNew();
            }
            if(selectedId.indexOf("temp") !== -1){
                TempListController.verifyTemp();
            }
            if(selectedId.indexOf("flow") !== -1){
                PointTree.verifyDebugBtn();
            }
            if(selectedId.indexOf("energyEfficiency") !== -1){
                PointTree.verifyEnergyEfficiencyBtn();
            }
        }else{
            layer.msg("请选择需要关联的点",{icon:0});
        }

    }

    //树--取消按钮
    PointTree.closeTreeWin = function () {
        PointTree.$nodeId = "";
        PointTree.$nodeName = "";
        layer.close(pointTreeIndex);
    }
     /*判断系统能耗点位是否符合条件*/
    PointTree.verifyEnergyEfficiencyBtn = function(){
            var sysName =  PointTree.$nodeId.val();
            $.ajax({
                url     : _ctx + '/btnEventController/getDebugInfo',
                type    : "post",
                dataType: 'json',
                async:false,
                data : {
                    sysname : sysName
                },
                success : function(result) {
                    //只有DO表中也存在记录时，才能作为关联点使用
                    if (result.status == '1') {
                            PointTree.closeTreeWin();
                            var tab = document.getElementById("inputPointTable");//获取table对象
                            var inpEle = tab.getElementsByTagName("input"); //获取table中所有的文本输入控件
                            var i = inpEle.length;

                            var trEle = '<tr>';
                            var inpTd =  '<td><input type="text" id="'+sysName+'_energyEfficiency'+i+'"   readonly autocomplete="off" class="layui-input"></td>';
                            $("#inputPointTable").append(trEle+inpTd+"</tr>");
                            form.render();
                            $('#'+sysName+'_energyEfficiency'+i).val(sysName);
                            AddEnergyEfficiency.initSelectedEvent();
                            return true;

                    }else
                    {
                        layer.msg("请先配置该点信息", {icon: 2});
                        return false;
                    }
                }
            });
    }
     //判断要关联的点位是否符合条件(点位置按钮、单通道按钮、多场景按钮)
    PointTree.verifySceneBtn = function(ele,nodeName){
        var sysName;
        if(ele.indexOf("scene") !== -1)
            sysName = nodeName;
        else
             sysName = PointTree.$nodeId.val();

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
                        PointTree.closeTreeWin();
                        return true;
                    } else {
                        PointTree.closeTreeWin();
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

    //判断将要关联调试按钮的点是否满足条件
    PointTree.verifyDebugBtn = function(){
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



    // /**
    //  * 查询节点对应配置下拉框数据
    //  */
    // PointTree.getNodeConfigData = function(sysname){
    //
    //     $.ajax({
    //         //selectNodesConfigSetting使用f_sys_name_old查询，修改为/selectDesignNodesConfig（f_sys_name）
    //         url : _ctx +"/view/basedatamanage/eqmanage/selectDesignNodesConfig",
    //         type : "post",
    //         data : {
    //             f_sys_name :sysname
    //         },
    //         success : function(result){
    //             var $table = $("#design_textbox_relative_table");
    //             if(result.list && result.list[0]){
    //
    //                 $table.html("");
    //                 var head = '<tr><td>点状态</td>  <td>关系</td>  <td>显示文本</td>  <td>颜色</td></tr>';
    //                 var colorHtml = '<td><select class="textbox_color"><option value="#fff">白色</option><option value="green">绿色</option><option value="red">红色</option><option value="gray">灰色</option><option value="yellow">黄色</option><option value="orange">橙色</option></select></td>';
    //                 //配置下拉框
    //                 var configHtml = '';
    //                 for(var i = 0; i < result.list.length; i++){
    //                     configHtml += '<option value="'+result.list[i].F_VALUE+'">'+result.list[i].F_DESC+'</option>';
    //                 }
    //                 var content = "";
    //                 for(var k = 0; k < result.list.length; k++){
    //                     content+=' <tr><td><select class="textbox_relative_select textbox_state">';
    //                     // for(var i = 0; i < result.list.length; i++){
    //                     //     $table.append('<option value="'+result.list[i].F_VALUE+'">'+result.list[i].F_DESC+'</option>');
    //                     // }
    //                     content += configHtml;
    //                     content += '</select></td><td>等于</td><td><input class="layui-input textbox_text" value="'+result.list[k].F_DESC+'"></td>';
    //                     content+= colorHtml;
    //                     content+= '</tr>';
    //                     // $table.append(configHtml);
    //                     // $table.append();
    //                     // $table.append(colorHtml);
    //                     // $table.append('</tr>');
    //                 }
    //                 $table.append(head+content);
    //             }else{
    //                 //如果没有配置数据，显示默认数据
    //                 $table.html($("#design_textbox_default_content").html());
    //             }
    //
    //             //下拉框默认选中
    //             var selectArray = $table.find("select.textbox_state");
    //             for(i = 0; i < selectArray.length; i++){
    //                 $(selectArray[i]).find("option:eq("+i+")").prop("selected",true);
    //             }
    //
    //             $table.show();
    //             form.render();
    //         },
    //         error:function(){
    //             layer.msg("操作失败",{icon:2});
    //         }
    //     });
    // }



});
