<style type="text/css">
    .leftTree {
        float: left;
        width: 20% !important;
        /* margin-left: -10px; */
        position: relative;
        box-sizing: border-box;
        height: 96%;
        border-right: solid 1px rgb(54, 108, 144);
        display: block;
    }

    .sceneTop {
        height: 4%;
        display: block;
        border-bottom: solid 1px rgb(54, 108, 144);
        display: block;
    }

    .centerContent {
        float: left;
        width: 80%;
        position: relative;
        /*margin-top: 4%;*/
        height: 93%;
        display: none;
    }

    .contentTop1 {
        white-space: nowrap;
        overflow: hidden;
        width: 100%;
        height: 25%;
    }

    .contentBottom {
        white-space: nowrap;
        overflow: hidden;
        width: 100%;
        height: 518px;
    }

    .tableSceneContent {
        margin-top: 25px;
        width: 90%;
        height: 92%;
        margin-left: 60px;
        float: left;
        /*background-color: #00264d;*/
        overflow-y: auto;
        overflow-x: hidden;
        position: relative;
    }

    .tableRight {
        margin-left: 40px;
        width: 10%;
        height: 160px;
        float: left;
        background-color: #00264d;
        border: solid 0px #ffffff;
    }

    /*样式前加上前缀，避免影响其他页面样式*/
    #strategy_content .layui-table-cell {
        min-height: 30px;
        height: initial;
    }

    #strategy_content .layui-table-cell, #strategy_content .layui-table-tool-panel li {
        white-space: initial;
        /*为了让字母和数字也换行*/
        word-break: break-word;
    }

    #strategy_content .layui-form-select dl {
        max-height: 200px;
    }

    .valueAndUnit {
        width: 100%;
        height: 200px;
    }

    .valueAndUnitClass {
        width: 100%;
        height: 200px;
        font-size: 20px;
        color: black;
    }

    #strategy_content .layui-table-cell {
        overflow: visible;
    }

    #strategy_content .layui-table-box {
        overflow: visible;
    }

    #strategy_content .layui-table-body {
        overflow: visible;
    }

    /* 设置下拉框的高度与表格单元相同 */
    #strategy_content td .layui-form-select {
        margin-left: -15px;
        margin-right: -15px;
    }

    .tab-pane active {
        height: 96%;
    }

    .ztree li a.curSelectedNode {
        boder:0 !important;
    }
</style>


<#--左侧树-->
<div class="leftarea information_left">
    <div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;策略配置树>>>
		</span>
    </div>
    <div id="strategyTree" class="ztree"></div>
</div>

<div id="strategy_content" class="information_right" style="display: none;">
    <div class="information-model">
			<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;配置详情>>>
			</span>
    </div>

    <div style="height: calc(95%);">
        <form role="form" class="form-horizontal" style="height: 100%">
            <div class="vertical-timeline-block contentTop1" style="height: 20%">
                <div class="form-group col-sm-4" style="margin-top: 20px">
                    <label class="col-sm-3 control-label">策略名称:</label>
                    <div class="col-sm-8">
                        <input type="text" id="f_strategyName" name="f_strategyName"
                               class="form-control" style="width: 200px" disabled>
                    </div>
                </div>

                <div class="form-group col-sm-4" style="margin-top: 20px">
                    <label class="col-sm-3 control-label">报表时间:</label>
                    <div class="col-sm-8">
                        <select id="f_strategyRange" name="f_strategyRange" style="width: 200px" class="form-control">
                            <option value="1">本天</option>
                            <option value="2">本周</option>
                            <option value="3">本月</option>
                            <option value="4">本季</option>
                            <option value="5">本年</option>
                            <#--<option value="6">上日</option>
                            <option value="7">上周</option>
                            <option value="8">上月</option>
                            <option value="9">上季</option>
                            <option value="10">上年</option>-->
                        </select>
                    </div>
                </div>

                <div class="form-group col-sm-4" style="margin-top: 20px">
                    <label class="col-sm-3 control-label">邮箱地址:</label>
                    <div class="col-sm-8">
                        <input type="text" id="f_strategyEmail" name="f_strategyEmail"
                               class="form-control" style="width: 200px">
                    </div>
                </div>

                <div class="form-group col-sm-4" style="margin-top: 20px">
                    <label class="col-sm-3 control-label">执行类型:</label>
                    <div class="col-sm-8">
                        <select id="f_strategyCronJobType" name="f_strategyCronJobType" style="width: 200px"
                                class="form-control">
                            <option value="0">每分钟</option>
                            <option value="1">每小时</option>
                            <option value="2">每天</option>
                            <#--<option value="3">每周</option>-->
                            <option value="4">每月</option>
                            <option value="5">每年</option>
                            <option value="6">指定固定日期时间</option>

                        </select>
                    </div>
                </div>

                <div class="form-group col-sm-4" style="margin-top: 20px">
                    <label class="col-sm-3 control-label">执行时间:</label>
                    <div class="col-sm-8">
                        <#--<input type="text" name="f_strategyCronStartDate" id="f_strategyCronStartDate" lay-verify="datetime"
                                autocomplete="off" class="form-control"
                               style="width: 200px">-->
                        <input type="text" style="width: 200px" class="form-control"
                               onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'})"
                               name="f_strategyCronStartDate" id="f_strategyCronStartDate" placeholder="开始日期">
                    </div>
                </div>

                <#--<div class="form-group col-sm-4" style="margin-top: 20px">
                    <label class="col-sm-3 control-label">cron表达式:</label>
                    <div class="col-sm-8">
                        <input type="text" id="f_strategyCron" name="f_strategyCron"
                               class="form-control" style="width: 200px">
                    </div>
                </div>-->

                <div class="form-group col-sm-4" style="margin-top: 20px">
                    <label class="col-sm-3 control-label">状态:</label>
                    <div class="col-sm-8">
                        <select id="f_strategyStatus" name="f_strategyStatus" style="width: 200px" class="form-control">
                            <option value="1">开启</option>
                            <option value="0">关闭</option>
                        </select>
                    </div>
                </div>

            </div>

            <#--层级树和部门树-->
            <div style="height: 70%;white-space:nowrap;overflow:auto;">
                <div id="zlTreeModel"
                     style="width:40%; height:98%;border: 2px solid  #CDCDCD;float:left;display: none;margin-right: 5%;">
                    <div style="height: 25px;width: 100%">
                        <span class="zl_sxtj_span">请选择层级>>> </span>
                        <label style="margin-left: 60%">
                            <input id="zlCascade" type="checkbox">是否级联
                        </label>
                    </div>
                    <div id="zlTree" style="width:100%;height:95%;overflow-y: auto;overflow-x: auto;"></div>
                </div>

                <div id="bmTreeModel" style="width:40%; height:98%;border: 2px solid  #CDCDCD;float:left;display: none">
                    <div style="height: 25px;width: 100%">
                        <span class="zl_sxtj_span">请选择部门>>> </span>
                        <label style="margin-left: 60%">
                            <input id="bmCascade" type="checkbox">是否级联
                        </label>
                    </div>
                    <div id="bmTree" style="width:100%;height:95%;overflow-y: auto;overflow-x: auto;"></div>

                </div>
            </div>

            <div style="width:100%;margin-top: 10px;font-size: 15px;float:left">
                <button class="layui-btn" type="button" id="" style="margin-left: 35%;"
                        onclick="basedatamanage_eqmanage_strategy.btn_exp()">报表预览
                </button>
                <button class="layui-btn" type="button" id="" style="margin-left: 10px;"
                        onclick="basedatamanage_eqmanage_strategy.saveStrategy()">保存策略
                </button>
            </div>
        </form>
    </div>

</div>

<link href="${ctx}/static/ztree/css/metroStyle/metroStyle.css" rel="stylesheet">

<script src="${ctx}/static/layui/lay/modules/layer.js" type="text/javascript"></script>


<!--cron表达式-->
<div class="modal fade" id="strategyCronModal" tabindex="-1" role="dialog" data-backdrop="static">
    <div class="modal-dialog" style="width: 46vw;margin-top: 0px;">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp; cron表达式生成</h4>
            </div>
            <div class="modal-body" style="height: 100%;overflow-y: auto;">
                <div id="strategy_dataMonitor">
                    <div id="strategy_cron_model" style="width: 100%;height: 96%"></div>

                </div>
            </div>
        </div><!-- end modal-content -->
    </div>
</div>
<!--cron表达式结束-->


<script type="text/javascript">
    ;
    var basedatamanage_eqmanage_strategy = (function ($, window, document, undefined) {
        var _ctx = '${ctx}';
        var strategyId;
        var strategyPId;
        var type;
        var modeList = []; //模式信息的List
        var sceneList = []; //场景信息的List

        var zlCascadeType = false;//是否级联
        var checkNodes = [];//支路选中的节点数组
        var deWhetherToManClick = true;//判断是否手动点击树节点
        var firstBranchNode = [];


        var bmCascadeType = false;//是否级联
        var bmCheckNodes = [];//支路选中的节点数组
        var bmDeWhetherToManClick = true;//判断是否手动点击树节点
        var bmFirstBranchNode = [];

        var zlAllName = '';
        var bmAllName = '';

        Array.prototype.push2 = function () {

            for (var i = 0; i < arguments.length; i++) {
                var ele = arguments[i];
                if (this.indexOf(ele) == -1) {
                    this.push(ele);
                }
            }
        };

        //加载树形结构
        $(function () {
            strategy_tree();//初始化场景树

            let treeObj = $.fn.zTree.getZTreeObj("strategyTree");
            treeObj.expandAll(true);
            let node = treeObj.getNodeByParam("f_id", "1");
            treeObj.selectNode(node);

            // setTimeout(equip_tree,20000);
            //支路树
            zl_tree();
            //部门树
            bm_tree();
        })

        //左侧树
        function strategy_tree() {
            $.ajax({
                type: "post",
                url: "${ctx}/view/basedatamanage/energyinformation/getStrategyTree",
                dataType: "json",
                async: false,
                success: function (result) {

                    var status = result && result.status;

                    if (status !== '1') {
                        return;
                    }
                    //debugger
                    var data = result.data;

                    if (!Array.isArray(data)) {
                        return;
                    }
                    data.forEach((item) => {
                        if (item.f_status=='1' && item.f_type=='1') {
                            item["icon"] = "/energy/static/ztree/css/zTreeStyle/img/diy/kai.png";
                        }else{
                            item["icon"] = "/energy/static/ztree/css/zTreeStyle/img/diy/guan.png";
                        }
                    })
                    // debugger
                    var strategyTree = new Tree({
                        container: 'strategyTree',
                        idKey: 'f_id',
                        pIdKey: 'f_pId',
                        name: 'f_name',
                        f_type: 'f_type',
                        showCheckbox: true,
                        selected: false,
                        noRemoveBtn: true,
                        noEditBtn: true,
                        setting: {
                            view: {
                                showIcon: function (treeId, treeNode) {
                                    return (treeNode.f_type==1)
                                        ? true
                                        : false;
                                },
                                // selectedMulti: true,
                                txtSelectedEnable: false,
                                fontCss: function (treeId, treeNode) {
                                    return (treeNode.highlight)
                                        ? {color: '#A60000', 'font-weight': 'bold'}
                                        : { color: '#8FE3F7',"font-family": "none","li.line-height": "30px",'font-weight': 'normal'};
                                },
                                addHoverDom: addHoverDom, //当鼠标移动到节点上时，显示用户自定义控件
                                removeHoverDom: removeHoverDom, //离开节点时的操作
                            },
                            check: {
                                enable: false,
                                chkboxType: {"Y": "", "N": ""}   //勾选 checkbox 对于父子节点的关联关系
                            },
                            edit: {
                                drag: {
                                    isCopy: false,
                                    isMove: false
                                },
                                enable: true,
                                editNameSelectAll: false,
                                showRemoveBtn: showRemoveBtn,
                                showRenameBtn: showRenameBtn
                            },
                            async: {
                                enable: false,
                                dataFilter: filter
                            },
                            callback: {
                                onClick: pointDosth,
                                // beforeDrag: beforeDrag,
                                // beforeEditName: beforeEditName,
                                beforeRemove: beforeRemove,
                                //beforeRename: beforeRename,
                                // onRemove: zTreeOnRemove,
                                onRename: onRename,
                                //onCheck:zTreeOnCheck(nodes)
                                setting: {
                                    data: {
                                        enable: false
                                    }
                                }
                            }
                        }
                    })
                    strategyTree.loadData(data);

                },
                error: function (nodeData) {
                    swal(nodeData.msg, "", "error");
                }
            })
        }

        //节点点击事件 将Id放全局变量里面
        function pointDosth(e, treeId, treeNode) {
            if (treeNode.level === 0) {
                $('#strategy_content').hide();
            } else {
                $('#strategy_content').show();
            }
            modeList.length = 0;
            strategyId = treeNode.f_id;
            strategyPId = treeNode.f_pId;
            type = treeNode.f_type;
            BooPagination = true;
            queryTableParam(strategyId);
        }

        //控制根节点 主节点的增加编辑按钮是否展示
        function showRenameBtn(treeId, treeNode) {
            var id = treeNode.f_id;
            if (id === '0' || id === '1' || id === '2' || id === '3') {
                return false;
            } else {
                return true;
            }
        }

        function filter(event, treeId, parentNode, childNodes) {
            if (!childNodes) return null;
            return childNodes;
        }

        //控制根节点 主节点的增加删除按钮是否展示
        function showRemoveBtn(treeId, treeNode) {
            var id = treeNode.f_id;
            if (id === '0' || id === '1' || id === '2' || id === '3') {
                return false;
            } else {
                return true;
            }
        }

        //控制根节点 主节点的增加按钮是否展示
        function addHoverDom(treeId, treeNode) {
            var id = treeNode.f_id;
            if (id === '1' || id === '2' || id === '3') {
                insertNode(treeId, treeNode);
                return false;
            } else {
                return false;
            }
        }

        //新增节点
        function insertNode(treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("strategyTree"); //获取树形图里面的数据
            var sObj = $("#" + treeNode.tId + "_span"); //获取节点信息
            if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
            var addStr = "<span class='button add' id='addBtn_" + treeNode.tId + "' title='新增一个场景' οnfοcus='this.blur();'></span>"; //定义添加按钮
            sObj.after(addStr); //加载添加按钮
            var btn = $("#addBtn_" + treeNode.tId);
            if (treeNode.f_type === "0") {
                // var newCount = 1;
                //绑定添加事件，并定义添加操作
                if (btn) btn.bind("click", function () {

                    const fname = "新建策略"; //新增节点的名字
                    const pId = treeNode.f_id;
                    $.ajax({
                        type: "post",
                        dataType: "json",
                        async: false,
                        url: "${ctx}//view/basedatamanage/energyinformation/add",
                        data: {
                            f_name: fname,
                            f_type: "1",
                            f_pId: pId,
                            f_status: '0',
                            f_cron_start_date: '2022-04-24 12:00:00',
                            f_cron_job_type: 2
                        },
                        success: function (result) {
                            if (result.status === '1') {
                                var id = result.data;
                                zTree.addNodes(treeNode, {f_id: id, f_pId: pId, f_name: fname, f_type: "1",icon : "/energy/static/ztree/css/zTreeStyle/img/diy/guan.png"})
                            } else {
                                swal({
                                    title: "策略添加失败",
                                    text: "",// 内容
                                    type: "warning",
                                    showCloseButton: false, // 展示关闭按钮
                                    allowOutsideClick: false,
                                    confirmButtonColor: "#1ab394",
                                    showConfirmButton: false,
                                    timer: 2000
                                });
                            }
                        },
                        error: function () {
                            swal({
                                title: "策略添加失败",
                                text: "",// 内容
                                type: "warning",
                                showCloseButton: false, // 展示关闭按钮
                                allowOutsideClick: false,
                                confirmButtonColor: "#1ab394",
                                showConfirmButton: false,
                                timer: 2000
                            });
                        }
                    })
                });
            }
        }

        //修改名称
        function onRename(e, treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("strategyTree");
            nodes = zTree.getSelectedNodes(),
                treeNode = nodes[0];
            var id = treeNode.f_id;
            var name = treeNode.f_name;
            $("#f_strategyName").val(name);//自动填充名称

            $.ajax({
                type: "post",
                dataType: "json",
                async: false,
                url: "${ctx}/view/basedatamanage/energyinformation/updateStrategyName",
                data: {
                    id: id,
                    name: name
                },
                success: function (result) {
                    if (result.status === '1') {
                        if (nodes.length > 0) {
                            nodes.name = name
                            zTree.updateNode(nodes[0]);
                        }
                    } else {
                        swal({
                            title: "策略名称修改失败",
                            text: "",// 内容
                            type: "warning",
                            showCloseButton: false, // 展示关闭按钮
                            allowOutsideClick: false,
                            confirmButtonColor: "#1ab394",
                            showConfirmButton: false,
                            timer: 2000
                        });
                    }
                },
                error: function () {
                    swal({
                        title: "策略名称修改失败",
                        text: "",// 内容
                        type: "warning",
                        showCloseButton: false, // 展示关闭按钮
                        allowOutsideClick: false,
                        confirmButtonColor: "#1ab394",
                        showConfirmButton: false,
                        timer: 2000
                    });
                }
            })
        }

        //ztree删除节点之前判断是否允许删除节点（这个方法做了一些修改，因为ztree自带的弹窗不是很美观好用）
        function beforeRemove(treeId, treeNode) {
            swal({
                title: "确认删除" + treeNode.f_name + "吗?",
                text: "",// 内容
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#1ab394",
                confirmButtonText: "确定",
                closeOnConfirm: false
            }, function () {
                zTreeOnRemove(treeNode);


            });
            return false;
        }

        //删除此节点
        function zTreeOnRemove(treeNode) {

            var id = treeNode.f_id;
            $.ajax({
                type: "post",
                dataType: "json",
                async: false,
                url: "${ctx}/view/basedatamanage/energyinformation/deleteStrategy",
                data: {
                    id: id,
                },
                success: function (result) {
                    if (result.status === '1') {
                        swal({
                            title: result.msg,
                            text: "",// 内容
                            type: "success",
                            showCloseButton: false, // 展示关闭按钮
                            allowOutsideClick: false,
                            confirmButtonColor: "#1ab394",
                            showConfirmButton: false,
                            timer: 1500
                        });
                        let zTree = $.fn.zTree.getZTreeObj("strategyTree");
                        zTree.removeNode(treeNode);
                        $('#strategy_content').hide();
                    } else {
                        swal({
                            title: result.msg,
                            text: "",// 内容
                            type: "error",
                            showCloseButton: false, // 展示关闭按钮
                            allowOutsideClick: false,
                            confirmButtonColor: "#1ab394",
                            showConfirmButton: false,
                            timer: 2000
                        });
                    }
                },
                error: function () {
                    swal({
                        title: "删除失败",
                        text: "",// 内容
                        type: "error",
                        showCloseButton: false, // 展示关闭按钮
                        allowOutsideClick: false,
                        confirmButtonColor: "#1ab394",
                        showConfirmButton: false,
                        timer: 2000
                    });
                }
            })
        }

        //销毁
        function removeHoverDom(treeId, treeNode) {
            $("#addBtn_" + treeNode.tId).unbind().remove();
        }


        //加载支路树
        function zl_tree(fnybh, refreshType, AllChecked) {
            $.ajax({
                type: "post",
                url: _ctx + "/view/dataAnalysis/branch_tree",
                data: {"fnybh": '01000'},
                dataType: "json",
                success: function (result) {
                    //只取2号楼的数据
                    result.list[0].nodes.forEach((item) => {
                        //git提交测试
                        if (item.text == '二号楼') {
                            firstBranchNode.push(item)
                        }
                    })

                    if (firstBranchNode.length > 0) {//返回tree成功
                        $('#zlTree').treeview({
                            data: firstBranchNode,         // 数据源
                            highlightSelected: true,    //是否高亮选中
                            levels: 1,
                            enableLinks: true,//必须在节点属性给出href属性
                            wrapNodeText: true,
                            color: "#4a4747",
                            showCheckbox: true,
                            // hierarchicalCheck:zlCascadeType,//级联勾选
                            propagateCheckEvent: true,
                            onNodeChecked: function (event, nodeData) {//选中方法
                                //级联的情况下,选中时默认将子节点和父节点选中（级联的情况下,不级联的时候默认选中选择的节点）
                                // debugger
                                if (deWhetherToManClick) {
                                    deWhetherToManClick = false;//判断是否手动点击树节点(点击过后首先设置为false)
                                    nodeChecked(nodeData);
                                }

                            },
                            onNodeUnchecked: function (event, nodeData) {//取消方法
                                if (deWhetherToManClick) {

                                    deWhetherToManClick = false;//判断是否手动点击树节点(点击过后首先设置为false)
                                    unnodeChecked(nodeData);
                                }
                            },
                        });
                        //禁用首级
                        $('#zlTree').treeview('disableNode', [firstBranchNode, {silent: false}]);
                        //展开首级
                        $('#zlTree').treeview('expandNode', [firstBranchNode, {silent: false}]);
                    } else {//树查询失败
                        $('#zlTree').treeview('remove');//移除列表树容器。
                        $('#zlTree').treeview('uncheckAll', {silent: true});//清空所有check
                    }
                },
                error: function (nodeData) {
                    swal(nodeData.msg, "", "error");
                }
            });
        }

        //支路树选中方法
        function nodeChecked(nodeData) {
            let childNodes = [];//选中节点的子节点集合
            checkNodes.length = 0;//清空选中的节点的数组
            checkNodes.push(firstBranchNode); //选中2号楼

            //获取设备树所有节点
            let allNodes = $("#zlTree").treeview('getNodes');
            //获取所有选中的节点
            checkNodes = $("#zlTree").treeview('getChecked');

            if (zlCascadeType) {//级联
                // debugger
                //获取父节点
                let fatherNodeId = nodeData.pid;
                allNodes.forEach((node, i) => {
                    if (node.nodeTreeId == fatherNodeId) {
                        fatherNodeId = node;
                        fatherNodes(fatherNodeId, allNodes);
                    }
                })

                //获取子节点(如果有的话)
                if (Array.isArray(nodeData.nodes)) {
                    childNodes = nodeData.nodes;
                    childNodesFunction(childNodes, allNodes);
                }

                checkNodes.forEach((node, i) => {
                    $('#zlTree').treeview('checkNode', [node, {silent: false}]);
                })
            }
            deWhetherToManClick = true;//判断是否手动点击树节点(点击的逻辑走完后,设置成true)
        }

        //支路树获取父节点集合，将父节点集合放到选中的节点集合里面
        function fatherNodes(fatherNodeId, allNodes) {
            checkNodes.push(fatherNodeId);
            let result = [];
            let obj = {};
            for (var i = 0; i < checkNodes.length; i++) {
                if (!obj[checkNodes[i].nodeTreeId]) {
                    result.push(checkNodes[i]);
                    obj[checkNodes[i].nodeTreeId] = true;
                }
            }
            checkNodes = result;
            //判断父节点是否含有父节点
            if (typeof fatherNodeId.pid != 'undefined') {//有的话,递归
                allNodes.forEach((node, i) => {
                    if (node.nodeTreeId == fatherNodeId.pid) {
                        fatherNodeId = node;
                        fatherNodes(fatherNodeId, allNodes);
                    }
                })
            }
        }

        //支路树获取子节点集合，将子节点集合放到选中的节点集合里面
        function childNodesFunction(childNodes, allNodes) {
            //遍历子节点集合,判断子节点中是否含有子节点
            childNodes.forEach((childNode, i) => {
                checkNodes.push(childNode);
                if (Array.isArray(childNode.nodes)) {
                    childNodesFunction(childNode.nodes, allNodes);
                }
            })
            let result = [];
            let obj = {};
            for (var i = 0; i < checkNodes.length; i++) {
                if (!obj[checkNodes[i].nodeTreeId]) {
                    result.push(checkNodes[i]);
                    obj[checkNodes[i].nodeTreeId] = true;
                }
            }
            checkNodes = result;
        }

        //支路树取消方法
        function unnodeChecked(nodeData) {
            let childNodes = [];//选中节点的子节点集合
            checkNodes.length = 0;//清空选中的节点的数组
            //获取设备树所有节点
            let allNodes = $("#zlTree").treeview('getNodes');
            //获取所有选中的节点
            checkNodes = $("#zlTree").treeview('getChecked');
            if (zlCascadeType) {//级联

                /*//获取父节点
                let fatherNodeId = nodeData.pid;
                allNodes.forEach((node,i) => {
                    if (node.nodeTreeId != '062' && node.nodeTreeId == fatherNodeId) {
                        fatherNodeId = node;
                        unfatherNodes(fatherNodeId,allNodes);
                    }
                })*/

                //获取子节点(如果有的话)
                if (Array.isArray(nodeData.nodes)) {
                    childNodes = nodeData.nodes;
                    unchildNodesFunction(childNodes, allNodes);
                }

                checkNodes.forEach((node, i) => {
                    $('#zlTree').treeview('checkNode', [node, {silent: false}]);
                })
            }
            deWhetherToManClick = true;//判断是否手动点击树节点(点击的逻辑走完后,设置成true)
        }

        //支路树获取父节点集合
        function unfatherNodes(fatherNodeId, allNodes) {
            checkNodes.splice(fatherNodeId);
            $('#zlTree').treeview('uncheckNode', [fatherNodeId, {silent: true}]);
            //判断父节点是否含有父节点
            if (typeof fatherNodeId.pid != 'undefined' && fatherNodeId.pid != '062') {//有的话,递归
                allNodes.forEach((node, i) => {
                    if (node.nodeTreeId == fatherNodeId.pid) {
                        fatherNodeId = node;
                        unfatherNodes(fatherNodeId, allNodes);
                    }
                })
            }
        }

        //支路树获取子节点集合
        function unchildNodesFunction(childNodes, allNodes) {
            //遍历子节点集合,判断子节点中是否含有子节点
            childNodes.forEach((childNode, i) => {
                checkNodes.forEach((checkNode, index) => {
                    if (checkNode.id == childNode.id) {
                        checkNodes.splice(index, 1);
                    }
                })
                $('#zlTree').treeview('uncheckNode', [childNode, {silent: true}]);
                // checkNodes.push(childNode);
                if (Array.isArray(childNode.nodes)) {
                    unchildNodesFunction(childNode.nodes, allNodes);
                }
            })
        }

        //支路树是否级联 点击事件
        $("#zlCascade").click(function () {
            //只有当点击是否级联的时候不刷新数据
            // deWhetherToManClick = !deWhetherToManClick;
            let refreshType = false;
            let AllChecked = $("#zlTree").treeview('getChecked');
            if ($('#zlCascade').is(':checked')) {
                zlCascadeType = true;
                deWhetherToManClick = true;
                // left_tree(fnybh, refreshType, AllChecked);
            } else {
                zlCascadeType = false;
                // left_tree(fnybh, refreshType, AllChecked);
            }

        });


        //加载部门树
        function bm_tree(refreshType, AllChecked) {
            $.ajax({
                type: "post",
                url: _ctx + "/view/dataAnalysis/branch_tree_dep",
                data: {"fnybh": '01000'},
                dataType: "json",
                success: function (result) {

                    if (result.hasOwnProperty("list")) {//返回tree成功
                        result.list[0].text = '二号楼';

                        bmFirstBranchNode.push(result.list[0])


                        $('#bmTree').treeview({
                            data: result.list,         // 数据源
                            highlightSelected: true,    //是否高亮选中
                            levels: 1,
                            enableLinks: true,//必须在节点属性给出href属性
                            wrapNodeText: true,
                            color: "#4a4747",
                            showCheckbox: true,
                            // hierarchicalCheck:cascadeType,//级联勾选
                            propagateCheckEvent: true,
                            onNodeChecked: function (event, nodeData) {//选中方法
                                //级联的情况下,选中时默认将子节点和父节点选中（级联的情况下,不级联的时候默认选中选择的节点）
                                if (bmDeWhetherToManClick) {
                                    bmDeWhetherToManClick = false;//判断是否手动点击树节点(点击过后首先设置为false)
                                    bmNodeChecked(nodeData);
                                }
                            },
                            onNodeUnchecked: function (event, nodeData) {//取消方法

                                if (bmDeWhetherToManClick) {
                                    bmDeWhetherToManClick = false;//判断是否手动点击树节点(点击过后首先设置为false)
                                    bmUnnodeChecked(nodeData);
                                }

                            },
                        });
                        //禁用首级
                        $('#bmTree').treeview('disableNode', [bmFirstBranchNode, {silent: false}]);
                        //展开首级
                        $('#bmTree').treeview('expandNode', [bmFirstBranchNode, {silent: false}]);
                    } else {//树查询失败
                        swal({
                            title: '当前能源下暂无部门配置!',// 展示的标题
                            text: "",// 内容
                            type: "warning",
                            showCloseButton: false, // 展示关闭按钮
                            allowOutsideClick: false,
                            showConfirmButton: false,
                            timer: 1000
                        });
                        $('#bmTree').treeview('remove');//移除列表树容器。
                        $('#bmTree').treeview('uncheckAll', {silent: true});//清空所有check
                    }
                },
                error: function (nodeData) {
                    swal(nodeData.msg, "", "error");
                }
            });
        }

        //部门树选中方法
        function bmNodeChecked(nodeData) {
            let childNodes = [];//选中节点的子节点集合
            bmCheckNodes.length = 0;//清空选中的节点的数组
            bmCheckNodes.push(bmFirstBranchNode); //选中2号楼

            //获取部门树所有节点
            let allNodes = $("#bmTree").treeview('getNodes');
            //获取所有选中的节点
            bmCheckNodes = $("#bmTree").treeview('getChecked');

            if (bmCascadeType) {//级联
                //获取父节点
                let fatherNodeId = nodeData.pid;
                allNodes.forEach((node, i) => {
                    if (node.nodeTreeId == fatherNodeId) {
                        fatherNodeId = node;
                        bmFatherNodes(fatherNodeId, allNodes);
                    }
                })

                //获取子节点(如果有的话)
                if (Array.isArray(nodeData.nodes)) {
                    childNodes = nodeData.nodes;
                    bmChildNodesFunction(childNodes, allNodes);
                }

                bmCheckNodes.forEach((node, i) => {
                    $('#bmTree').treeview('checkNode', [node, {silent: false}]);
                })
            }
            bmDeWhetherToManClick = true;//判断是否手动点击树节点(点击的逻辑走完后,设置成true)
        }

        //部门树获取父节点集合，将父节点集合放到选中的节点集合里面
        function bmFatherNodes(fatherNodeId, allNodes) {
            bmCheckNodes.push(fatherNodeId);
            let result = [];
            let obj = {};
            for (var i = 0; i < bmCheckNodes.length; i++) {
                if (!obj[bmCheckNodes[i].nodeTreeId]) {
                    result.push(bmCheckNodes[i]);
                    obj[bmCheckNodes[i].nodeTreeId] = true;
                }
            }
            bmCheckNodes = result;
            //判断父节点是否含有父节点
            if (typeof fatherNodeId.pid != 'undefined') {//有的话,递归
                allNodes.forEach((node, i) => {
                    if (node.nodeTreeId == fatherNodeId.pid) {
                        fatherNodeId = node;
                        bmFatherNodes(fatherNodeId, allNodes);
                    }
                })
            }
        }

        //部门树获取子节点集合，将子节点集合放到选中的节点集合里面
        function bmChildNodesFunction(childNodes, allNodes) {
            //遍历子节点集合,判断子节点中是否含有子节点
            childNodes.forEach((childNode, i) => {
                bmCheckNodes.push(childNode);
                if (Array.isArray(childNode.nodes)) {
                    bmChildNodesFunction(childNode.nodes, allNodes);
                }
            })
            let result = [];
            let obj = {};
            for (var i = 0; i < bmCheckNodes.length; i++) {
                if (!obj[bmCheckNodes[i].nodeTreeId]) {
                    result.push(bmCheckNodes[i]);
                    obj[bmCheckNodes[i].nodeTreeId] = true;
                }
            }
            bmCheckNodes = result;
        }

        //部门树取消方法
        function bmUnnodeChecked(nodeData) {
            let childNodes = [];//选中节点的子节点集合
            bmCheckNodes.length = 0;//清空选中的节点的数组
            //获取设备树所有节点
            let allNodes = $("#bmTree").treeview('getNodes');
            //获取所有选中的节点
            bmCheckNodes = $("#bmTree").treeview('getChecked');
            if (bmCascadeType) {//级联

                /*//获取父节点
                let fatherNodeId = nodeData.pid;
                allNodes.forEach((node,i) => {
                    if (node.nodeTreeId != '062' && node.nodeTreeId == fatherNodeId) {
                        fatherNodeId = node;
                        unfatherNodes(fatherNodeId,allNodes);
                    }
                })*/

                //获取子节点(如果有的话)
                if (Array.isArray(nodeData.nodes)) {
                    childNodes = nodeData.nodes;
                    bmUnchildNodesFunction(childNodes, allNodes);
                }

                bmCheckNodes.forEach((node, i) => {
                    $('#bmTree').treeview('checkNode', [node, {silent: false}]);
                })
            }
            bmDeWhetherToManClick = true;//判断是否手动点击树节点(点击的逻辑走完后,设置成true)
        }


        //部门树获取子节点集合
        function bmUnchildNodesFunction(childNodes, allNodes) {
            //遍历子节点集合,判断子节点中是否含有子节点
            childNodes.forEach((childNode, i) => {
                bmCheckNodes.forEach((checkNode, index) => {
                    if (checkNode.id == childNode.id) {
                        bmCheckNodes.splice(index, 1);
                    }
                })
                $('#bmTree').treeview('uncheckNode', [childNode, {silent: true}]);
                // checkNodes.push(childNode);
                if (Array.isArray(childNode.nodes)) {
                    bmUnchildNodesFunction(childNode.nodes, allNodes);
                }
            })
        }

        //部门树是否级联 点击事件
        $("#bmCascade").click(function () {
            //只有当点击是否级联的时候不刷新数据
            // deWhetherToManClick = !deWhetherToManClick;
            let refreshType = false;
            let AllChecked = $("#bmTree").treeview('getChecked');
            if ($('#bmCascade').is(':checked')) {
                bmCascadeType = true;
                bmDeWhetherToManClick = true;
                // left_tree(fnybh, refreshType, AllChecked);
            } else {
                bmCascadeType = false;
                // left_tree(fnybh, refreshType, AllChecked);
            }
        });

        function btn_exp(){
            var fname = "支路报表信息.xls";
            var path = "file\\expExcel\\支路报表信息.xls";
            //FileDownload("${ctx}/file/newFileDownload",fname,path);
            FileDownload(_ctx + filePath.loadPath,fname,path);
        }

        function saveStrategy() {

            let f_name = $("#f_strategyName").val(); //策略名称
            let f_cron = $("#f_strategyCron").val();  //策略cron表达式
            let f_status = $("#f_strategyStatus").val();  //策略状态
            let f_range = $("#f_strategyRange").val();  //策略报表时间
            let f_email = $("#f_strategyEmail").val();  //接收邮箱
            let f_cron_start_date = $("#f_strategyCronStartDate").val();  //执行时间
            let f_cron_job_type = $("#f_strategyCronJobType").val();  //执行类型

            //选中的层级
            let branchList = []
            let branchNode;

            if (checkNodes.length > 0) {
                checkNodes.forEach((node, i) => {
                    // branchList.push(node.id)
                    zlAllName = '';
                    branchNode = {};

                    //
                    queryParentNode(node, '')
                    // debugger
                    //完整路径
                    zlAllName = zlAllName + node.text
                    zlAllName = zlAllName.substring(2, zlAllName.length)

                    branchNode = {
                        'f_zlmc': zlAllName,
                        'f_zlbh': node.id,
                        'f_level': node.level
                    }

                    branchList.push(branchNode)
                })
            }
            //选中的部门
            let departmentList = []
            let departmentNode;
            if (bmCheckNodes.length > 0) {
                bmCheckNodes.forEach((node, i) => {
                    // departmentList.push(node.id)
                    bmAllName = '';
                    departmentNode = {};

                    //
                    queryBmParentNode(node, '')
                    // debugger
                    //完整路径
                    bmAllName = bmAllName + node.text
                    bmAllName = bmAllName.substring(2, bmAllName.length)

                    departmentNode = {
                        'f_zlmc': bmAllName,
                        'f_department_id': node.id,
                        'f_level': node.level
                    }

                    departmentList.push(departmentNode)
                })
            }

            //验证字段是否有未填的
            if (f_name == '' || f_name == null) {
                swal({
                    title: '请添加策略名称!',// 展示的标题
                    text: "",// 内容
                    type: "warning",
                    showCloseButton: false, // 展示关闭按钮
                    allowOutsideClick: false,
                    showConfirmButton: false,
                    timer: 1000
                });
                return;
            }
            if (f_range == '' || f_range == null) {
                swal({
                    title: '请选择报表时间!',// 展示的标题
                    text: "",// 内容
                    type: "warning",
                    showCloseButton: false, // 展示关闭按钮
                    allowOutsideClick: false,
                    showConfirmButton: false,
                    timer: 1000
                });
                return;
            }
            if (f_email == '' || f_email == null) {
                swal({
                    title: '请输入邮箱地址!',// 展示的标题
                    text: "",// 内容
                    type: "warning",
                    showCloseButton: false, // 展示关闭按钮
                    allowOutsideClick: false,
                    showConfirmButton: false,
                    timer: 1000
                });
                return;
            }
            if (f_cron_job_type == '' || f_cron_job_type == null) {
                swal({
                    title: '请选择执行类型!',// 展示的标题
                    text: "",// 内容
                    type: "warning",
                    showCloseButton: false, // 展示关闭按钮
                    allowOutsideClick: false,
                    showConfirmButton: false,
                    timer: 1000
                });
                return;
            }
            if (f_cron_start_date == '' || f_cron_start_date == null) {
                swal({
                    title: '请选择执行时间!',// 展示的标题
                    text: "",// 内容
                    type: "warning",
                    showCloseButton: false, // 展示关闭按钮
                    allowOutsideClick: false,
                    showConfirmButton: false,
                    timer: 1000
                });
                return;
            }
            if (f_status == '' || f_status == null) {
                swal({
                    title: '请选择状态!',// 展示的标题
                    text: "",// 内容
                    type: "warning",
                    showCloseButton: false, // 展示关闭按钮
                    allowOutsideClick: false,
                    showConfirmButton: false,
                    timer: 1000
                });
                return;
            }


            //根据pid(1默认,2层级,3部门),判断是否选中了相应的层级\部门
            if (strategyPId == '1') {
                if (branchList.length < 1) {
                    swal({
                        title: '请选择层级!',// 展示的标题
                        text: "",// 内容
                        type: "warning",
                        showCloseButton: false, // 展示关闭按钮
                        allowOutsideClick: false,
                        showConfirmButton: false,
                        timer: 1000
                    });
                    return;
                } else if (departmentList.length < 1) {
                    swal({
                        title: '请选择部门!',// 展示的标题
                        text: "",// 内容
                        type: "warning",
                        showCloseButton: false, // 展示关闭按钮
                        allowOutsideClick: false,
                        showConfirmButton: false,
                        timer: 1000
                    });
                    return;
                }
            } else if (strategyPId == '2' && branchList.length < 1) {
                swal({
                    title: '请选择层级!',// 展示的标题
                    text: "",// 内容
                    type: "warning",
                    showCloseButton: false, // 展示关闭按钮
                    allowOutsideClick: false,
                    showConfirmButton: false,
                    timer: 1000
                });
                return;
            } else if (strategyPId == '3' && departmentList.length < 1) {
                swal({
                    title: '请选择部门!',// 展示的标题
                    text: "",// 内容
                    type: "warning",
                    showCloseButton: false, // 展示关闭按钮
                    allowOutsideClick: false,
                    showConfirmButton: false,
                    timer: 1000
                });
                return;
            }


            $.ajax({
                type: "post",
                url: "${ctx}/view/basedatamanage/energyinformation/updateStrategy",
                dataType: "json",
                async: false,
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify({
                    f_id: strategyId,
                    f_name: f_name,
                    f_type: '1',
                    f_pId: strategyPId,
                    f_cron: f_cron,
                    f_status: f_status,
                    f_range: f_range,
                    f_email: f_email,
                    branchList: branchList,
                    departmentList: departmentList,
                    f_cron_start_date: f_cron_start_date,
                    f_cron_job_type: f_cron_job_type
                }),
                success: function (result) {
                    var data = result.map;
                    sceneList = data;
                    var status = result && result.status;

                    swal({
                        title: result.msg,// 展示的标题
                        type: "success",
                        showCloseButton: false, // 展示关闭按钮
                        allowOutsideClick: false,
                        showConfirmButton: false,
                        timer: 1000
                    });

                    queryTableParam(strategyId);

                },
                complete: function () {
                    hiddenLoad();
                },
                error: function () {
                    swal({
                        title: '根据id查询数据有错！',// 展示的标题
                        type: "error",
                        showCloseButton: false, // 展示关闭按钮
                        allowOutsideClick: false,
                        showConfirmButton: false,
                        timer: 1000
                    });
                }
            })

        }

        function queryParentNode(node, allName) {

            let pNode = $('#zlTree').treeview('getParents', [node])[0];
            if (typeof (pNode) != "undefined" && pNode != null && pNode != '') {
                if (typeof (pNode.pid) != "undefined" && pNode.pid != null && pNode.pid != '') {
                    allName = pNode.text + '--' + allName;
                    queryParentNode(pNode, allName);
                } else {
                    zlAllName = '--' + allName
                }
            } else {
                zlAllName = '--' + allName
            }
        }

        function queryBmParentNode(node, allName) {

            let pNode = $('#bmTree').treeview('getParents', [node])[0];
            if (typeof (pNode) != "undefined" && pNode != null && pNode != '') {
                if (typeof (pNode.pid) != "undefined" && pNode.pid != null && pNode.pid != '') {
                    allName = pNode.text + '--' + allName;
                    queryBmParentNode(pNode, allName);
                } else {
                    bmAllName = '--' + allName
                }
            } else {
                bmAllName = '--' + allName
            }
        }


        //根据id查询table内数据
        function queryTableParam(id) {

            //支路树与部门树 取消级联选择
            $("#zlCascade").prop("checked", false)
            zlCascadeType = false;
            $("#bmCascade").prop("checked", false)
            bmCascadeType = false;

            $.ajax({
                type: "post",
                url: "${ctx}/view/basedatamanage/energyinformation/queryTableParam",
                dataType: "json",
                async: false,
                data: {
                    "id": id
                },
                success: function (result) {
                    // debugger
                    var data = result.map;
                    sceneList = data;
                    var status = result && result.status;

                    $("#f_strategyName").val(data.f_name);//名称
                    $("#f_strategyCron").val(data.f_cron);//表达式
                    $("#f_strategyRange").val(data.f_range);//报表时间
                    $("#f_strategyEmail").val(data.f_email);//邮箱
                    $("#f_strategyStatus").val(data.f_status);//状态
                    $("#f_strategyCronJobType").val(data.f_cron_job_type);//执行类型
                    $("#f_strategyCronStartDate").val(data.f_cron_start_date);//执行时间


                    if (data.f_pId != null && data.f_pId == '1') {
                        $("#zlTreeModel").css('display', 'block');
                        $("#bmTreeModel").css('display', 'block');

                    } else if (data.f_pId != null && data.f_pId == '2') {
                        $("#zlTreeModel").css('display', 'block');
                        $("#bmTreeModel").css('display', 'none');
                    } else {
                        $("#zlTreeModel").css('display', 'none');
                        $("#bmTreeModel").css('display', 'block');
                    }


                    //支路树清空所有check
                    $('#zlTree').treeview('uncheckAll', {silent: true});
                    checkNodes = []
                    //支路树选中首级
                    $('#zlTree').treeview('checkNode', [firstBranchNode, {silent: false}]);

                    //部门树清空所有check
                    $('#bmTree').treeview('uncheckAll', {silent: true});
                    bmCheckNodes = []
                    //部门树选中首级
                    $('#bmTree').treeview('checkNode', [bmFirstBranchNode, {silent: false}]);


                    var checkedBranchList = []
                    var checkedDepartmentList = []
                    data.strategyBranch = Array.from(data.strategyBranch);
                    data.strategyDepartment = Array.from(data.strategyDepartment);
                    if (data.f_pId != null && data.f_pId == '1') {
                        // debugger
                        //在支路树中查出关联的支路node
                        data.strategyBranch.forEach((item, i) => {
                            checkedBranchList.push($("#zlTree").treeview('findNodes', [item, 'nodeTreeId'])[0])
                        })
                        //选中node
                        checkedBranchList.forEach((item, i) => {
                            $("#zlTree").treeview('checkNode', [item, {silent: false}])
                        })


                        //在部门树中查出关联的支路node
                        data.strategyDepartment.forEach((item, i) => {
                            checkedDepartmentList.push($("#bmTree").treeview('findNodes', [item, 'nodeTreeId'])[0])
                        })
                        //部门树选中node
                        checkedDepartmentList.forEach((item, i) => {
                            $("#bmTree").treeview('checkNode', [item, {silent: false}])
                        })


                    } else if (data.f_pId != null && data.f_pId == '2') {
                        //在支路树中查出关联的支路node
                        data.strategyBranch.forEach((item, i) => {
                            checkedBranchList.push($("#zlTree").treeview('findNodes', [item, 'nodeTreeId'])[0])
                        })
                        //选中node
                        checkedBranchList.forEach((item, i) => {
                            $("#zlTree").treeview('checkNode', [item, {silent: false}])
                        })
                    } else {
                        // debugger
                        //在部门树中查出关联的支路node
                        data.strategyDepartment.forEach((item, i) => {
                            checkedDepartmentList.push($("#bmTree").treeview('findNodes', [item, 'nodeTreeId'])[0])
                        })
                        //部门树选中node
                        checkedDepartmentList.forEach((item, i) => {
                            $("#bmTree").treeview('checkNode', [item, {silent: false}])
                        })
                    }


                },
                complete: function () {
                    hiddenLoad();
                },
                error: function () {
                    swal({
                        title: '根据id查询数据有错！',// 展示的标题
                        type: "success",
                        showCloseButton: false, // 展示关闭按钮
                        allowOutsideClick: false,
                        showConfirmButton: false,
                        timer: 1000
                    });
                }
            })
        }


        //cron表达式
        $('#f_strategyCron').focus(function () {
            $('#strategyCronModal').modal('show');
        });

        //表达式生成页面加载之前,先确定页面所在的位置以及查询所加载页面的url
        $("#strategyCronModal").on('show.bs.modal', function (event) {
            //垂直居中显示
            $(this).css('display', 'block');
            $(this).css("overflow", "hidden"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的
            loadPageByMold();
        });

        //显示cron表达式页面
        function loadPageByMold() {

            $.ajax({
                url: _ctx + "/view/basedatamanage/energyinformation/cronPage",
                type: "post",
                success: function (result) {
                    $("#strategy_cron_model").empty();
                    $("#strategy_cron_model").html(result);
                },
                error: function (result) {
                }
            });
        }


        return {

            btn_exp : function(){
                debugger
                if (strategyPId == '1'){
                    var branchName = "支路报表预览.xls";
                    var branchPath = "file\\expExcel\\支路报表预览.xls";
                    FileDownload(_ctx + filePath.loadPath,branchName,branchPath);

                    var departmentName = "部门报表预览.xls";
                    var departmentPath = "file\\expExcel\\部门报表预览.xls";
                    //FileDownload("${ctx}/file/newFileDownload",fname,path);
                    FileDownload(_ctx + filePath.loadPath,departmentName,departmentPath);
                } else if (strategyPId == '2'){
                    var branchName = "支路报表预览.xls";
                    var branchPath = "file\\expExcel\\支路报表预览.xls";
                    FileDownload(_ctx + filePath.loadPath,branchName,branchPath);
                } else {
                    var departmentName = "部门报表预览.xls";
                    var departmentPath = "file\\expExcel\\部门报表预览.xls";
                    //FileDownload("${ctx}/file/newFileDownload",fname,path);
                    FileDownload(_ctx + filePath.loadPath,departmentName,departmentPath);
                }

            },
            saveStrategy: function () {
                saveStrategy();
                strategy_tree();
            },
            cron: function (cron) {
                $("#f_strategyCron").val(cron);
            },
        }
    })(jQuery, window, document);
</script>
