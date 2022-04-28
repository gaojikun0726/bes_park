<#-----
描述：组织机构
作者：gongfanfei
---->


<!-- 组织机构树模块 -->
<div class="leftarea information_left">
    <div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;请选择部门>>>
		</span>
    </div>
    <div id="tree_zzjg" class="Information_area"></div>
</div>
<!-- 信息表格模块 -->
<div class="information_right">
    <div class="information_size">
        <div class="information-model">
			<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;部门支路电表配置列表>>>
			</span>
            <!-- 增加按钮 -->
            <a id="addZzjg_zzjg" href="javascript:void(-1);" onclick="organizationmanage_zzjg.show_addmodal();"
               class="btn btn-primary toLeft"> <i class="fa fa-plus" style="margin-top: 2.5px;margin-left: 2px;"
                                                  aria-hidden="true"></i>&nbsp;编辑
            </a>
            <!-- 搜索框 -->
            <div class="zc_search find">
                <input type="text" class="find-style" id="zzjginfo" name="zzjginfo" placeholder="支路/电表编号、名称">
                <button id="queryuserBtn" onclick="organizationmanage_zzjg.searchzzjgZLDB()"><i class="fa fa-search"
                                                                                                aria-hidden="true"></i>
                </button>
            </div>
        </div>
        <div id="zzjgTable" class="Information_area"></div>
    </div>
</div>
<!-- 信息表格模块end -->

<!---添加组织机构信息开始----->
<div class="modal fade" id="modal-form-addzzjg" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content" style="width:1150px;height:900px;overflow-y: auto;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;添加部门支路电表配置</h4>
            </div>
            <div class="modal-body">
                <#--<form role="form" id="addZzjg" name="addZzjg" class="form-horizontal">-->
                <div id="depPeopleNumberDiv">
                    <h4 class="modal-title">人数:</h4>
                    <br>
                    <div>
                        <input type="number" id="depPeopleNumber" name="depPeopleNumber" class="input-sm form-control"
                               placeholder="请输入人数(必填)"
                               autocomplete="off" oninput="if(value>10000)value=10000;if(value<0)value=0"
                               onblur="organizationmanage_zzjg.checkDepPeopleNumber(this)"/>
                    </div>
                </div>
                <HR id="depPeopleNumberDivHR">
                <div>
                    <h4 class="modal-title">包含支路:</h4>
                    <div class="modal-body" style="height:600px;">
                        <div style="float:left;width:57%">
                            <button class="btn btn-md" style="cursor:default"><span>未选择</span></button>
                        </div>
                        <div style="float:left;width:40%">
                            <button class="btn btn-md" style="cursor:default"><span>已选择</span></button>
                        </div>
                        <!--未选择table+搜索）- -->
                        <div class="notIncludeCss" style="width:400px;">
                            <div style="padding:2px 2px 2px 10px;height:6%;">
                                <div style="float:left;">
                                    <input type="text" class="input-sm form-control"
                                           style="width: calc(100%);padding: 4px 30px;" id="notincludeBrcKeywords"
                                           name="notincludeBrcKeywords" value="" placeholder="支路编号、名称">
                                </div>
                                <div style="float:left;">
                                    <span class="input-group-btn" style="width: 60px;">
                                        <button class="btn btn-primary btn-sm m-b-none" id="queryNotIncludeBrc"
                                                onclick="organizationmanage_zzjg.searchNotIncludeBrc()">
                                            <i class="fa fa-search"></i> 搜索
                                        </button>
                                    </span>
                                </div>
                            </div>
                            <div id="householdBrc_noInclude" style="width:400px;margin-top:10px;overflow: auto;">
                            </div>
                        </div>
                        <!-- 未选择用户结束- -->
                        <!--操作开始- -->
                        <div style="width:100px;height:400px;float:left">
                            <div id="BrcrightMove" style="margin-top:200px;margin-left:23px;">
                                <button id="householdConf_right" type="button"
                                        onclick="organizationmanage_zzjg.BrcrightMove()"
                                        class="btn btn-primary">>>
                                </button>
                            </div>
                            <div id="BrcleftMove" style="margin-top:20px;margin-left:23px;">
                                <button id="householdConf_left" type="button"
                                        onclick="organizationmanage_zzjg.BrcleftMove()"
                                        class="btn btn-primary"><<
                                </button>
                            </div>
                        </div>
                        <!--操作结束- -->
                        <!--包含用户开始- -->
                        <div class="includeCss" style="width:500px;">
                            <div id="householdBrc_include" style="overflow: auto;margin-top:10px;">
                            </div>
                            <div>
                            </div>
                        </div>
                        <!--包含用户结束- -->
                    </div>
                </div>
                <HR>
                <div>
                    <h4 class="modal-title">包含电表:</h4>
                    <div class="modal-body" style="height:600px;">
                        <div style="float:left;width:57%">
                            <button class="btn btn-md" style="cursor:default"><span>未选择</span></button>
                        </div>
                        <div style="float:left;width:40%">
                            <button class="btn btn-md" style="cursor:default"><span>已选择</span></button>
                        </div>
                        <!--未选择table+搜索）-->
                        <div class="notIncludeCss" style="width:400px;">
                            <div style="padding:2px 2px 2px 10px;height:6%;">
                                <div style="float:left;">
                                    <input type="text" class="input-sm form-control"
                                           style="width: calc(100%);padding: 4px 30px;" id="notincludeBrcKeywordsDB"
                                           name="notincludeBrcKeywordsDB" value="" placeholder="电表编号、名称">
                                </div>
                                <div style="float:left;">
                                    <span class="input-group-btn" style="width: 60px;">
                                        <button class="btn btn-primary btn-sm m-b-none" id="queryNotIncludeBrc"
                                                onclick="organizationmanage_zzjg.searchNotIncludeBrcDB()">
                                            <i class="fa fa-search"></i> 搜索
                                        </button>
                                    </span>
                                </div>
                            </div>
                            <div id="wattHourMeter_noInclude" style="margin-top:10px;overflow: auto;">
                            </div>
                        </div>
                        <!-- 未选择用户结束-->
                        <!--操作开始-->
                        <div style="width:100px;height:400px;float:left">
                            <div id="BrcrightMoveDB" style="margin-top:200px;margin-left:23px;">
                                <button id="wattHourMeter_right" type="button"
                                        onclick="organizationmanage_zzjg.BrcrightMoveDB()"
                                        class="btn btn-primary">>>
                                </button>
                            </div>
                            <div id="BrcleftMoveDB" style="margin-top:20px;margin-left:23px;">
                                <button id="wattHourMeter_left" type="button"
                                        onclick="organizationmanage_zzjg.BrcleftMoveDB()"
                                        class="btn btn-primary"><<
                                </button>
                            </div>
                        </div>
                        <!--操作结束-->
                        <!--包含用户开始-->
                        <div class="includeCss" style="width:500px;">
                            <div id="wattHourMeter_include" style="overflow: auto;margin-top:10px;">
                            </div>
                            <div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group m-t-sm">
                    <div class="col-sm-6 col-sm-push-3 display_flex">
                        <button class="btn btn-md btn-primary" type="submit"
                                onclick="organizationmanage_zzjg.add_depInfo();"><strong><i class="fa fa-check"
                                                                                            aria-hidden="true"></i>&nbsp;确定</strong>
                        </button>
                        <button class="btn btn-white m-l-sm" type="button" data-dismiss="modal"><i
                                    class="fa fa-times" aria-hidden="true"></i>&nbsp;取消
                        </button>
                    </div>
                </div>
                <#--</form>-->
            </div>
        </div>
    </div>
</div>

<!----编辑组织结构--->
<#--<div class="modal fade" id="editZzjgForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp;编辑组织机构信息</h4>
            </div>
            <div class="modal-body">
                <form id="editzzjgForm" name="editzzjgForm" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="zzjg_edit_f_zzjgbh">组织机构编号 <span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="zzjg_edit_f_zzjgbh" name="zzjg_edit_f_zzjgbh" required
                                   class="form-control" readonly="readonly">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="zzjg_edit_f_zzjgmc">组织机构名称<span
                                    class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="zzjg_edit_f_zzjgmc" name="zzjg_edit_f_zzjgmc" required
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="zzjg_edit_f_remark">备注</label>
                        <div class="col-sm-8">
                            <input type="text" id="zzjg_edit_f_remark" name="zzjg_edit_f_remark" class="form-control">
                        </div>
                    </div>
                    <div class="form-group m-t-sm">
                        <div class="col-sm-6 col-sm-push-4 display_flex">
                            <button class="btn btn-md btn-primary " type="submit">
                                <strong><i class="fa fa-check" aria-hidden="true"></i>&nbsp;确定</strong>
                            </button>
                            <button type="button" class="btn btn-white m-l-sm" data-dismiss="modal"><i
                                        class="fa fa-times" aria-hidden="true"></i>&nbsp;取消
                            </button>
                        </div>
                    </div>


                </form>
            </div>
        </div>
    </div>
</div>-->


<!---添加组织机构信息结束----->

<script type="text/javascript">
    var organizationmanage_zzjg = (function ($, window, document, undefined) {
        var _ctx = '${ctx}';
        var _zzjgbh = "00";
        var _zzjginfo1 = "";
        var _zzjgJs = "0";
        var _curRow = null;
        var _includecurRow = null;//“已选择”table对应行
        var _notincludecurRow = null;//“未选择”table对应行
        var _includecurRowDB = null;//“已选择”table对应行
        var _notincludecurRowDB = null;//“未选择”table对应行
        var addDepList = [];//支路List
        //设置格式
        var optIconFunction = function (cell, formatterParams) { //plain text value
            var idZzjg = cell.getRow().getData().F_ID;
            var Zzjgbh = cell.getRow().getData().f_zzjgbh;
            /*<button class='btn btn-white btn-sm edit' data-id=" + idZzjg + " data-toggle='modal' data-target='#editZzjgForm'><i class='fa fa-pencil' ></i> 编辑</button>*/
            return "<div class='btn-group '>"
                + "<button class='btn btn-white btn-sm delete' data-id=" + idZzjg + "><i class='fa fa-trash'></i>  删除</button></div>"
        };


        $('#selectBranchInfo').change(function () {
            var val = $("#selectBranchInfo").val();
            var text = $("#selectBranchInfo").text();
            var assistantName = "";

            if (options.length > 0) {

                for (var i = 0; i < options.length; i++) {

                    assistantName += options[i].text;

                    assistantName += ","

                }

                assistantName = assistantName.substr(0, assistantName.length - 1);

                $("#assistantDirector").val(assistantName);

            }
        });

        //创建并设置table属性
        $("#zzjgTable").tabulator({
            height: "100%",
            layout: "fitColumns",//fitColumns  fitDataFill
            columnVertAlign: "bottom", //align header contents to bottom of cell
            tooltips: true,
            //selectable:true,
            movableColumns: true,
            columns: [
                {
                    title: "序号",
                    field: "id",
                    width: 80,
                    formatter: "rownum",
                    frozen: false,
                    sorter: "string",
                    headerSort: false,
                    align: "center"
                }, //frozen column
                {
                    title: "名称",
                    field: "F_MC",
                    minWidth: 150,
                    sorter: "string",
                    editor: false,
                    headerSort: false,
                    align: "left"
                }, //never hide this column
                {
                    title: "系数",
                    field: "F_XS",
                    width: 100,
                    sorter: "string",
                    editor: false,
                    headerSort: false,
                    align: "left"
                }, //never hide this column
                {
                    title: "类型",
                    field: "F_TYPE",
                    width: 100,
                    sorter: "string",
                    editor: false,
                    headerSort: false,
                    align: "left"
                },
                // {
                //     title: "包含电表",
                //     field: "F_DBSYS_NAME",
                //     minWidth: 150,
                //     sorter: "string",
                //     editor: false,
                //     headerSort: false,
                //     align: "left"
                // }, //hide this column first
                // {
                //     title: "电表系数",
                //     field: "F_DBXS",
                //     width: 100,
                //     sorter: "string",
                //     editor: false,
                //     headerSort: false,
                //     align: "left"
                // },
                {
                    title: "创建时间",
                    field: "CREATE_TIME",
                    width: 180,
                    sorter: "date",
                    align: "center",
                    editable: false,
                    headerSort: false
                },
                {
                    title: "修改时间",
                    field: "UPDATE_TIME",
                    width: 180,
                    sorter: "date",
                    align: "center",
                    editor: false,
                    headerSort: false
                },
                {
                    title: "操作",
                    field: "opt",
                    width: 180,
                    tooltip: false,
                    tooltipsHeader: false,
                    align: "center",
                    formatter: optIconFunction,
                    headerSort: false
                },
            ],
            rowClick: function (e, row) {
                _curRow = row;
                var id = _curRow.getData().f_zzjgbh;
                var choiseNode = $('#tree_zzjg').treeview('findNodes', [_curRow.getData().f_zzjgbh, 'id']);
                if (choiseNode.length > 1) {
                    for (var i = 0; i < choiseNode.length; i++) {
                        if (choiseNode[i].id == id) {
                            $('#tree_zzjg').treeview('searchByNode', choiseNode[i]);//搜索设置高亮
                        }
                    }
                } else {
                    $('#tree_zzjg').treeview('searchByNode', choiseNode[0]);//搜索设置高亮
                }
            },
        });

        $(window).resize(function () {
            $("#zzjgTable").tabulator("redraw");
        });


        //Clear table on "Empty the table" button click
        $("#clearZzjg").click(function () {
            $("#zzjgTable").tabulator("clearData");
        });
        //加载树
        $(function () {
            $.ajax({
                type: "post",
                url: _ctx + "/view/datacenter/zzjgTreeZC",
                dataType: "json",
                beforeSend: function () {
                    showLoad();
                },
                success: function (result) {
                    if (result.hasOwnProperty("list")) {//判断result返回结果是否包含list
                        result.list[0].text = '2号楼';
                        var nodesList = result.list[0].nodes;
                        var newNodesList = new Array();
                        for (let i = 0; i < nodesList.length; i++) {
                            if (nodesList[i].text == '山东正晨科技股份有限公司' || nodesList[i].text == '山东正威检测科技有限公司' || nodesList[i].text == '山东邦展建筑设计有限公司' || nodesList[i].text == '山东高速材料科技有限公司') {
                                newNodesList.push(nodesList[i]);
                            }
                        }
                        result.list[0].nodes = newNodesList;
                        if (result.list.length > 0) {//如果包含判断是否长度大于0
                            $('#tree_zzjg').treeview({
                                data: result.list,         // 数据源
                                highlightSelected: true,    //是否高亮选中
                                levels: 2,
                                enableLinks: true,//必须在节点属性给出href属性
                                color: "#4a4747",
                                onNodeSelected: function (event, nodeData) {
                                    $('#tree_zzjg').treeview('clearSearch');//清除搜索选中高亮
                                    _zzjgbh = nodeData.id;
                                    _zzjginfo1 = nodeData.id;
                                    _zzjgJs = nodeData.level;
                                    $.ajax({
                                        url: _ctx + "/view/basedatamanage/energyinformation/getDepartmentList",
                                        contentType: "application/json; charset=utf-8",
                                        type: "get",
                                        beforeSend: function () {
                                            showLoad();
                                        },
                                        data: {
                                            f_zzjgbh: nodeData.id,
                                            f_js: nodeData.js
                                        },
                                        success: function (result) {
                                            if (result.hasOwnProperty("list")) {
                                                $("#zzjgTable").tabulator("setData", result.list);
                                            } else {
                                                $("#zzjgTable").tabulator("setData", []);
                                            }
                                        },
                                        complete: function () {
                                            hiddenLoad();
                                        },
                                        error: function (result) {
                                            swal("查询失败", "", "error");
                                        }
                                    });
                                }
                            });
                            var firstNode = $("#tree_zzjg").treeview('findNodes', [result.list[0].id, 'id']);
                            $("#tree_zzjg").treeview("selectNode", firstNode);//将第一个节点设置为选中状态
                        }
                    }
                },
                complete: function () {
                    hiddenLoad();
                },
                error: function (nodeData) {
                    swal(nodeData.msg, "", "error");
                }
            });
            // getSelectBranchInfo(_zzjgbh,'')
            // getSelectBranchInfoChoose(_zzjgbh),
            // getSelectElectricityMeterInfo(_zzjgbh,'')
            // getSelectElectricityMeterInfoChoose(_zzjgbh,)
        });

        function getDepPeopleNumber(_zzjgbh) {
            $.ajax({
                type: "POST",
                dataType: 'json',
                url: '${ctx}/view/basedatamanage/energyinformation/getDepPeopleNumber',
                data: ({
                    F_DEP_ID: _zzjgbh
                }),
                success: function (result) {
                    if (result.hasOwnProperty('data') == false) {
                        $("#depPeopleNumber").val(0)
                    } else {
                        $("#depPeopleNumber").val(result.data.F_NUMBER)
                    }
                }
            });
        }

        function getSelectBranchInfo(_zzjgbh, keywords) {
            $.ajax({
                type: "POST",
                dataType: 'json',
                url: '${ctx}/view/basedatamanage/energyinformation/getSelectBranchInfo',
                data: ({
                    keywords: keywords,
                    bmbh: _zzjgbh
                }),
                success: function (result) {
                    // var ops = "<option value=''>请选择支路</option>";
                    // for (var i = 0; i < result.list.length; i++) {
                    //     var obj = result.list[i];
                    //     ops += '<option value="' + obj.F_ZLBH + '">';
                    //     ops += obj.F_ZLMC;
                    //     ops += '</option>';
                    // }
                    // $("#selectBranchInfo").append(ops);
                    // $("#householdZZJGEdit").append(ops);
                    //填充“未选择”数据
                    if (result.hasOwnProperty('list') == false) {
                        $("#householdBrc_noInclude").tabulator("setData", []);
                    } else {
                        $("#householdBrc_noInclude").tabulator("setData", result.list);
                    }
                    var noinclude_group_data = $("#householdBrc_noInclude").tabulator("getData");
                    if (noinclude_group_data.length == 0) {
                        $("#householdConf_right").attr('disabled', true);
                        $("#householdConf_left").attr('disabled', false);
                    } else {
                        $("#householdConf_right").attr('disabled', false);
                    }
                }
            });
        }

        function getSelectBranchInfoChoose(_zzjgbh, _zzjgJs) {
            $.ajax({
                type: "POST",
                dataType: 'json',
                url: '${ctx}/view/basedatamanage/energyinformation/getSelectBranchInfoChoose',
                data: ({
                    bmbh: _zzjgbh
                }),
                success: function (result) {
                    //填充“未选择”数据
                    if (result.hasOwnProperty('list')) {
                        $("#householdBrc_include").tabulator("setData", result.list);
                        for (let i = 0; i < result.list.length; i++) {
                            var addList = [{'F_DEP_ID': 1}]
                            addList[0]['F_ZLXS'] = result.list[i].F_ZLXS
                            addList[0]['F_ZLBH'] = result.list[i].F_ZLBH
                            addList[0]['F_DEP_ID'] = _zzjgbh;
                            addList[0]['F_LEVEL'] = _zzjgJs;
                            addDepList.push(addList[0]);
                        }
                    } else {
                        $("#householdBrc_include").tabulator("setData", []);
                    }
                }
            });
        }

        function getSelectElectricityMeterInfo(_zzjgbh, keywords) {
            $.ajax({
                type: "POST",
                dataType: 'json',
                url: '${ctx}/view/basedatamanage/energyinformation/getSelectElectricityMeterInfo',
                data: ({
                    keywords: keywords,
                    bmbh: _zzjgbh
                }),
                success: function (result) {
                    if (result.hasOwnProperty('list') == false) {
                        $("#wattHourMeter_noInclude").tabulator("setData", []);
                    } else {
                        for (let i = 0; i < result.list.length; i++) {
                            for (let j = 0; j < addDepList.length; j++) {
                                if (addDepList[j].F_SYS_NAME == result.list[i].F_SYS_NAME) {
                                    result.list.splice(i, 1);
                                }
                            }
                        }
                        $("#wattHourMeter_noInclude").tabulator("setData", result.list);
                    }
                    var noinclude_group_data = $("#wattHourMeter_noInclude").tabulator("getData");
                    if (noinclude_group_data.length == 0) {
                        $("#wattHourMeter_right").attr('disabled', true);
                        $("#wattHourMeter_left").attr('disabled', false);
                    } else {
                        $("#wattHourMeter_right").attr('disabled', false);
                    }
                }
            });
        }

        function getSelectElectricityMeterInfoChoose(_zzjgbh, _zzjgJs) {
            $.ajax({
                type: "POST",
                dataType: 'json',
                url: '${ctx}/view/basedatamanage/energyinformation/getSelectElectricityMeterInfoChoose',
                data: ({
                    bmbh: _zzjgbh
                }),
                success: function (result) {
                    //填充“未选择”数据
                    if (result.hasOwnProperty('list')) {
                        $("#wattHourMeter_include").tabulator("setData", result.list);
                        for (let i = 0; i < result.list.length; i++) {
                            var addList = [{'F_DEP_ID': 1}]
                            addList[0]['F_DBXS'] = result.list[i].F_DBXS;
                            addList[0]['F_SYS_NAME'] = result.list[i].F_SYS_NAME
                            addList[0]['F_DEP_ID'] = _zzjgbh;
                            addList[0]['F_LEVEL'] = _zzjgJs;
                            addDepList.push(addList[0]);
                        }
                    } else {
                        $("#wattHourMeter_include").tabulator("setData", []);
                    }
                }
            });
        }

        //分项添加支路
        $(document).on('click', '#householdBrc_noInclude button.insertBranch', function () {
            var f_yhbh = $(this).data("id");
            $("#householdConf_left").attr('disabled', false);
            $("#householdConf_right").attr('disabled', false);
            $.ajax({
                url: _ctx + "/view/basedatamanage/energyinformation/getSelectBranchInfoById",
                type: "post",
                data: ({
                    fZlbh: f_yhbh
                }),
                success: function (data) {
                    if (data.list.length > 0) {
                        $.ajax({
                            url: _ctx + "/view/basedatamanage/energyinformation/getSelectBranchCount",
                            type: "post",
                            data: ({
                                fZlbh: f_yhbh,
                                F_DEP_ID: _zzjgbh,
                                F_LEVEL: _zzjgJs
                            }),
                            success: function (data1) {
                                $("#householdBrc_noInclude").tabulator("deleteRow", _notincludecurRow);
                                data.list[0]['F_ZLXS'] = data1.list[0].F_ZLXS;
                                data.list[0]['F_DEP_ID'] = _zzjgbh;
                                //在已选择表格中添加该条数据
                                $('#householdBrc_include').tabulator("addRow", {
                                    F_ZLBH: data.list[0].F_ZLBH,
                                    F_ZLMC: data.list[0].F_ZLMC,
                                    F_ZLXS: data.list[0].F_ZLXS
                                });
                                //向变量数组添加指定元素
                                var addList = [{'F_DEP_ID': 1}]
                                addList[0]['F_ZLXS'] = data1.list[0].F_ZLXS
                                addList[0]['F_ZLBH'] = data.list[0].F_ZLBH
                                addList[0]['F_DEP_ID'] = _zzjgbh;
                                addList[0]['F_LEVEL'] = _zzjgJs;
                                addDepList.push(/*data.list[0]*/addList[0]);
                                //未包含用户表格：
                                var noinclude_group_data = $("#householdBrc_noInclude").tabulator("getData");
                                $("#householdBrc_noInclude").tabulator("setData", noinclude_group_data);
                                //已包含用户表格：
                                var include_group_data = $("#householdBrc_include").tabulator("getData");
                                $("#householdBrc_include").tabulator("setData", include_group_data);
                                if (noinclude_group_data.length == 0) {
                                    $("#householdConf_right").attr('disabled', true);
                                    $("#householdConf_left").attr('disabled', false);
                                }
                            },
                        });
                    } else {
                        swal(data.msg, "", "error");
                    }
                },
                error: function (data) {
                    swal(data.msg, "", "error");
                }
            });
        });

        //移除分项中的支路
        $(document).on('click', '#householdBrc_include button.deleteBranch', function () {
            var f_yhbh = $(this).data("id");
            $("#householdConf_left").attr('disabled', false);
            $("#householdConf_right").attr('disabled', false);
            $.ajax({
                url: _ctx + "/view/basedatamanage/energyinformation/getSelectBranchInfoById",
                type: "post",
                data: ({
                    fZlbh: f_yhbh
                }),
                success: function (data) {
                    if (data.list.length > 0) {
                        //swal(data.msg, "", "success");
                        //在已选择表格中删除该条数据
                        $("#householdBrc_include").tabulator("deleteRow", _includecurRow);
                        //在未选择表格中添加该条数据
                        $('#householdBrc_noInclude').tabulator("addRow", {
                            F_ZLBH: data.list[0].F_ZLBH,
                            F_ZLMC: data.list[0].F_ZLMC
                        });
                        //删除变量数组指定元素
                        for (let i = 0; i < addDepList.length; i++) {
                            if (addDepList[i].F_ZLBH == data.list[0].F_ZLBH) {
                                addDepList.splice(i, 1);
                                break;
                            }
                        }
                        //未包含用户表格：
                        var noinclude_group_data = $("#householdBrc_noInclude").tabulator("getData");
                        $("#householdBrc_noInclude").tabulator("setData", noinclude_group_data);
                        //已包含用户表格：
                        var include_group_data = $("#householdBrc_include").tabulator("getData");
                        $("#householdBrc_include").tabulator("setData", include_group_data);
                        if (include_group_data.length == 0) {
                            $("#householdConf_left").attr('disabled', true);
                            $("#householdConf_right").attr('disabled', false);
                        }
                    } else {
                        swal(data.msg, "", "error");
                    }
                },
                error: function (data) {
                    swal(data.msg, "", "error");
                }
            });
        });

        //分项添加电表
        $(document).on('click', '#wattHourMeter_noInclude button.insertBranch', function () {
            var f_yhbh = $(this).data("id");
            $("#wattHourMeter_left").attr('disabled', false);
            $("#wattHourMeter_right").attr('disabled', false);
            $.ajax({
                url: _ctx + "/view/basedatamanage/energyinformation/getSelectElectricityMeterInfoById",
                type: "post",
                data: ({
                    fZlbh: f_yhbh
                }),
                success: function (data) {
                    if (data.list.length > 0) {

                        $.ajax({
                            url: _ctx + "/view/basedatamanage/energyinformation/getSelectElectricityMeterCount",
                            type: "post",
                            data: ({
                                fZlbh: f_yhbh,
                                F_DEP_ID: _zzjgbh,
                                F_LEVEL: _zzjgJs
                            }),
                            success: function (data1) {
                                data.list[0]['F_DBXS'] = data1.list[0].F_DBXS;
                                data.list[0]['F_DEP_ID'] = _zzjgbh;
                                //在未选择表格中删除该条数据
                                $("#wattHourMeter_noInclude").tabulator("deleteRow", _notincludecurRowDB);
                                //在已选择表格中添加该条数据
                                $('#wattHourMeter_include').tabulator("addRow", {
                                    F_SYS_NAME: data.list[0].F_SYS_NAME,
                                    F_NICK_NAME: data.list[0].F_NICK_NAME,
                                    F_DBXS: data.list[0].F_DBXS
                                });
                                //向变量数组添加指定元素
                                var addList = [{'F_DEP_ID': 1}]
                                addList[0]['F_DBXS'] = data1.list[0].F_DBXS;
                                addList[0]['F_SYS_NAME'] = data.list[0].F_SYS_NAME
                                addList[0]['F_DEP_ID'] = _zzjgbh;
                                addList[0]['F_LEVEL'] = _zzjgJs;
                                addDepList.push(/*data.list[0]*/addList[0]);
                                //未包含用户表格：
                                var noinclude_group_data = $("#wattHourMeter_noInclude").tabulator("getData");
                                $("#wattHourMeter_noInclude").tabulator("setData", noinclude_group_data);
                                //已包含用户表格：
                                var include_group_data = $("#wattHourMeter_include").tabulator("getData");
                                $("#wattHourMeter_include").tabulator("setData", include_group_data);
                                if (noinclude_group_data.length == 0) {
                                    $("#wattHourMeter_right").attr('disabled', true);
                                    $("#wattHourMeter_left").attr('disabled', false);
                                }
                            }
                        });
                    } else {
                        swal(data.msg, "", "error");
                    }
                },
                error: function (data) {
                    swal(data.msg, "", "error");
                }
            });
        });

        //移除分项中的电表
        $(document).on('click', '#wattHourMeter_include button.deleteBranch', function () {
            var f_yhbh = $(this).data("id");
            $("#wattHourMeter_left").attr('disabled', false);
            $("#wattHourMeter_right").attr('disabled', false);
            $.ajax({
                url: _ctx + "/view/basedatamanage/energyinformation/getSelectElectricityMeterInfoById",
                type: "post",
                data: ({
                    fZlbh: f_yhbh
                }),
                success: function (data) {
                    if (data.list.length > 0) {
                        //swal(data.msg, "", "success");
                        //在已选择表格中删除该条数据
                        $("#wattHourMeter_include").tabulator("deleteRow", _includecurRowDB);
                        //在未选择表格中添加该条数据
                        $('#wattHourMeter_noInclude').tabulator("addRow", {
                            F_SYS_NAME: data.list[0].F_SYS_NAME,
                            F_NICK_NAME: data.list[0].F_NICK_NAME
                        });
                        //删除变量数组指定元素
                        for (let i = 0; i < addDepList.length; i++) {
                            if (addDepList[i].F_SYS_NAME == data.list[0].F_SYS_NAME) {
                                addDepList.splice(i, 1);
                                break;
                            }
                        }
                        //未包含用户表格：
                        var noinclude_group_data = $("#wattHourMeter_noInclude").tabulator("getData");
                        $("#wattHourMeter_noInclude").tabulator("setData", noinclude_group_data);
                        //已包含用户表格：
                        var include_group_data = $("#wattHourMeter_include").tabulator("getData");
                        $("#wattHourMeter_include").tabulator("setData", include_group_data);
                        if (include_group_data.length == 0) {
                            $("#wattHourMeter_left").attr('disabled', true);
                            $("#wattHourMeter_right").attr('disabled', false);
                        }
                    } else {
                        swal(data.msg, "", "error");
                    }
                },
                error: function (data) {
                    swal(data.msg, "", "error");
                }
            });
        });

        //支路穿梭框
        //设置“未选择”格式
        var optIconNoIncludeFunc = function (cell, formatterParams) {
            var idyhbh = cell.getRow().getData().F_ZLBH;
            return "<button class='btn btn-white btn-sm insertBranch' data-id=" + idyhbh + "><i class='fa fa-arrow-circle-right'></i></button>"
        };
        //设置“已选择”格式
        var optIconInclude = function (cell, formatterParams) {
            var idyhbh = cell.getRow().getData().F_ZLBH;
            return "<button class='btn btn-white btn-sm deleteBranch' data-id=" + idyhbh + "><i class='fa fa-trash'></i></button>"
        };
        //创建并设置“未选择”table属性
        $("#householdBrc_noInclude").tabulator({
            height: "93.3%",
            layout: "fitColumns",//fitColumns  fitDataFill
            columnVertAlign: "bottom", //align header contents to bottom of cell
            tooltips: true,
            //selectable:true,
            //placeholder:"无数据，请添加用户",
            movableColumns: true,
            columns: [
                {title: "序号", width: 50, formatter: "rownum", frozen: false, sorter: "number", headerSort: false},
                {
                    title: "支路编号",
                    field: "F_ZLBH",
                    width: 85,
                    sorter: "string",
                    editor: false,
                    align: "center",
                    headerSort: false
                },
                {
                    title: "支路名称",
                    field: "F_ZLMC",
                    width: 150,
                    sorter: "string",
                    editor: false,
                    align: "center",
                    headerSort: false
                },
                {
                    title: "操作",
                    field: "opt",
                    width: 80,
                    tooltip: false,
                    tooltipsHeader: false,
                    align: "center",
                    formatter: optIconNoIncludeFunc,
                    headerSort: false
                },
            ],
            rowClick: function (e, not_row) {
                _notincludecurRow = not_row;
            },
        });
        //创建并设置“已选择”table属性
        $("#householdBrc_include").tabulator({
            height: "93.3%",
            layout: "fitColumns",
            columnVertAlign: "bottom", //align header contents to bottom of cell
            tooltips: true,
            //selectable:true,
            //placeholder:"无数据，请添加用户",
            //movableColumns:true,
            columns: [
                {title: "序号", width: 50, formatter: "rownum", frozen: false, sorter: "number", headerSort: false},
                {
                    title: "支路编号",
                    field: "F_ZLBH",
                    width: 85,
                    sorter: "string",
                    editor: false,
                    align: "center",
                    headerSort: false
                },
                {
                    title: "支路名称",
                    field: "F_ZLMC",
                    width: 150,
                    sorter: "string",
                    editor: false,
                    align: "center",
                    headerSort: false
                },
                {
                    title: "操作",
                    field: "opt",
                    width: 80,
                    tooltip: false,
                    tooltipsHeader: false,
                    align: "center",
                    formatter: optIconInclude,
                    headerSort: false
                },
                {
                    title: "支路系数",
                    field: "F_ZLXS",
                    width: 80,
                    sorter: "string",
                    defaultContent: 1,
                    editor: false,
                    align: "center",
                    headerSort: false
                },
            ],
            rowClick: function (e, in_row) {
                _includecurRow = in_row;
            },
        });
        //电表穿梭框
        //设置“未选择”格式
        var optIconNoIncludeFuncDB = function (cell, formatterParams) {
            var idyhbh = cell.getRow().getData().F_SYS_NAME;
            return "<button class='btn btn-white btn-sm insertBranch' data-id=" + idyhbh + "><i class='fa fa-arrow-circle-right'></i></button>"
        };
        //设置“已选择”格式
        var optIconIncludeDB = function (cell, formatterParams) {
            var idyhbh = cell.getRow().getData().F_SYS_NAME;
            return "<button class='btn btn-white btn-sm deleteBranch' data-id=" + idyhbh + "><i class='fa fa-trash'></i></button>"
        };
        //创建并设置“未选择”table属性
        $("#wattHourMeter_noInclude").tabulator({
            height: "93.3%",
            layout: "fitColumns",//fitColumns  fitDataFill
            columnVertAlign: "bottom", //align header contents to bottom of cell
            tooltips: true,
            //selectable:true,
            //placeholder:"无数据，请添加用户",
            movableColumns: true,
            columns: [
                {title: "序号", width: 50, formatter: "rownum", frozen: false, sorter: "number", headerSort: false},
                {
                    title: "电表编号",
                    field: "F_SYS_NAME",
                    width: 150,
                    sorter: "string",
                    editor: false,
                    align: "center",
                    headerSort: false
                },
                {
                    title: "电表名称",
                    field: "F_NICK_NAME",
                    width: 85,
                    sorter: "string",
                    editor: false,
                    align: "center",
                    headerSort: false
                },
                {
                    title: "操作",
                    field: "opt",
                    width: 80,
                    tooltip: false,
                    tooltipsHeader: false,
                    align: "center",
                    formatter: optIconNoIncludeFuncDB,
                    headerSort: false
                },
            ],
            rowClick: function (e, not_row) {
                _notincludecurRowDB = not_row;
            },
        });
        //创建并设置“已选择”table属性
        $("#wattHourMeter_include").tabulator({
            height: "93.3%",
            layout: "fitColumns",
            columnVertAlign: "bottom", //align header contents to bottom of cell
            tooltips: true,
            columns: [
                {title: "序号", width: 50, formatter: "rownum", frozen: false, sorter: "number", headerSort: false},
                {
                    title: "电表编号",
                    field: "F_SYS_NAME",
                    width: 150,
                    sorter: "string",
                    editor: false,
                    align: "center",
                    headerSort: false
                },
                {
                    title: "电表名称",
                    field: "F_NICK_NAME",
                    width: 85,
                    sorter: "string",
                    editor: false,
                    align: "center",
                    headerSort: false
                },
                {
                    title: "操作",
                    field: "opt",
                    width: 80,
                    tooltip: false,
                    tooltipsHeader: false,
                    align: "center",
                    formatter: optIconIncludeDB,
                    headerSort: false
                },
                {
                    title: "电表系数",
                    field: "F_DBXS",
                    width: 80,
                    sorter: "string",
                    defaultContent: 1,
                    editor: false,
                    align: "center",
                    headerSort: false
                },
            ],
            rowClick: function (e, in_row) {
                _includecurRowDB = in_row;
            },
        });


        //触发搜索的回车时间
        $("#zzjginfo").focus(function () {
            $(this).keydown(function (e) {
                if (e.which == "13") {
                    organizationmanage_zzjg.searchzzjg();//触发该事件
                }
            })
        });

        //添加组织机构表单验证
        var zzjgValidator = $("#addZzjg").validate({
            rules: {
                zzjg_add_f_zzjgmc: {
                    required: true,
                    isNormal: true,
                },
                zzjg_add_f_remark: {
                    isNormal: true,
                }
            },
            messages: {

                zzjg_add_f_zzjgmc: {
                    required: "请输入组织机构名称",
                    isNormal: "请输入合法字符"
                },
                zzjg_add_f_remark: {
                    isNormal: "请输入合法字符"
                }
            },
            submitHandler: function (nodeData) {
                addformZzjg(nodeData);
            }
        });

        // //新增保存
        // function addformZzjg(nodeData) {
        //
        //     $.ajax({
        //         url: _ctx + "/view/datacenter/zzjg_add",
        //         type: "post",
        //         contentType: "application/json; charset=utf-8",
        //         data: JSON.stringify({
        //             f_zzjgmc: $("#zzjg_add_f_zzjgmc").val(),
        //             f_remark: $("#zzjg_add_f_remark").val(),
        //             f_pzzjgbh: _zzjgbh,
        //             f_js: _zzjgJs
        //         }),
        //         beforeSend: function () {
        //             showLoad();
        //         },
        //         success: function (result) {
        //             if (result.status == '1') {
        //                 $('#modal-form-addzzjg').modal('hide');//关闭编辑窗口
        //                 swal({
        //                     title: result.msg,// 展示的标题
        //                     text: "",// 内容
        //                     type: "success",
        //                     showCloseButton: false, // 展示关闭按钮
        //                     allowOutsideClick: false,
        //                     showConfirmButton: false,
        //                     timer: 1000
        //                 });
        //                 //在表格中添加数据
        //                 $('#zzjgTable').tabulator("addRow", {
        //                     f_id: result.data.f_id,
        //                     f_zzjgbh: result.data.f_zzjgbh,
        //                     f_zzjgmc: result.data.f_zzjgmc,
        //                     f_remark: result.data.f_remark,
        //                     f_crdate: result.data.f_crdate,
        //                     f_chdate: result.data.f_chdate
        //                 });
        //                 //在树上添加
        //                 var pNode = $("#tree_zzjg").treeview("getSelected");
        //                 $("#tree_zzjg").treeview("addNode", [{
        //                     id: result.data.f_zzjgbh,
        //                     text: result.data.f_zzjgmc,
        //                     pid: pNode[0].id,
        //                     js: result.js
        //                 }, pNode]);
        //             } else {
        //                 swal(result.msg, "", "error");
        //             }
        //         },
        //         complete: function () {
        //             hiddenLoad();
        //         },
        //         error: function (result) {
        //             swal(result.msg, "", "error");
        //         }
        //     });
        //     return false;
        // }

        //居中显示（添加）
        $('#modal-form-addzzjg').on('show.bs.modal', function () {
            // 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零
            //模态框拖动********************
            $(this).draggable({
                handle: ".modal-header"
            });
            $(this).css("overflow", "hidden");
            //*************************************
            $(this).css('display', 'block');
            var modalHeight = $(window).height() / 2 - $('#modal-form-addzzjg .modal-dialog').height() / 2;
            $(this).find('.modal-dialog').css({
                'margin-top': modalHeight
            });
        })
        //居中显示（编辑）
        $('#editZzjgForm').on('show.bs.modal', function () {
            $(this).css('display', 'block');
            var modalHeight = $(window).height() / 2 - $('#editZzjgForm .modal-dialog').height() / 2;
            $(this).find('.modal-dialog').css({
                'margin-top': modalHeight
            });
        })
        //关闭模态框清空表单值
        $("#modal-form-addzzjg").on('hidden.bs.modal', function (event) {
            // $(this).find("input").val("");
            // zzjgValidator.resetForm();
        });

        //删除数据
        $(document).on('click', '#zzjgTable button.delete', function () {
            var id = $(this).data("id");
            $.ajax({
                url: _ctx + "/view/basedatamanage/energyinformation/del_Department",
                type: "post",
                data: ({
                    id: id
                }),
                success: function (result) {
                    swal("删除成功", "", "success");
                    organizationmanage_zzjg.searchzzjg();
                }

            });
            // var f_id = organizationmanage_zzjg.getCurRow().getData().f_id;
            // var dNode = $('#tree_zzjg').treeview('findNodes', [id, 'id']);
            // if (dNode.length > 1) {
            //     for (var i = 0; i < dNode.length; i++) {
            //         if (dNode[i].id == id) {
            //             var booljuge = dNode[i].hasOwnProperty('nodes');
            //             if (booljuge == true) {
            //                 swal({
            //                     title: "请您先删除子机构！",
            //                     text: "经检测，您要删除的机构包含子机构!",
            //                     type: "warning",
            //                     showCancelButton: false,
            //                     confirmButtonColor: "#1ab394",
            //                     confirmButtonText: "关闭",
            //                     closeOnConfirm: false
            //                 });
            //             } else {
            //                 deletezzjg(f_id, dNode[i]);//删除
            //             }
            //         }
            //     }
            //
            //
            // } else {
            //     deletezzjg(f_id, dNode[0]);
            // }
        });
        //关闭模态框清空表单值
        $("#editZzjgForm").on('hidden.bs.modal', function (event) {
            $(this).find("input").val("");
            zzjgValidatoredit.resetForm();
        });
        //表单验证
        var zzjgValidatoredit = $("#editzzjgForm").validate({
            //zzjg_edit_f_zzjgbh
            //zzjg_edit_f_zzjgmc
            //zzjg_edit_f_remark
            rules: {
                zzjg_edit_f_zzjgmc: {
                    required: true,
                    isNormal: true,
                },
                zzjg_edit_f_remark: {
                    isNormal: true,
                }
            },
            messages: {
                zzjg_edit_f_zzjgmc: {
                    required: "请输入组织机构名称",
                    isNormal: "请输入合法字符"
                },
                zzjg_edit_f_remark: {
                    isNormal: "请输入合法字符"
                }
            },
            submitHandler: function (form) {
                editzzjgForm(form);
            }
        });

        //验证码在模态框出现前加载
        $("#editZzjgForm").on('show.bs.modal', function (event) {
            var fid = _curRow.getData().f_id;
            //模态框拖动********************
            $(this).draggable({
                handle: ".modal-header"
            });
            $(this).css("overflow", "hidden");
            //*************************************
            $.ajax({
                url: _ctx + "/view/datacenter/loadeditobj",
                type: "post",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify({
                    f_id: fid
                }),
                beforeSend: function () {
                    showLoad();
                },
                success: function (result) {
                    console.log(result);
                    $("#zzjg_edit_f_zzjgbh").val(result.f_zzjgbh);
                    $("#zzjg_edit_f_zzjgmc").val(result.f_zzjgmc);
                    $("#zzjg_edit_f_remark").val(result.f_remark);
                },
                complete: function () {
                    hiddenLoad();
                },
            });
        });

//编辑组织机构
//         function editzzjgForm(form) {
//             var fid = _curRow.getData().f_id;
//             $.ajax({
//                 url: _ctx + "/view/datacenter/zzjg_edit",
//                 type: "post",
//                 data: ({
//                     f_id: fid,
//                     f_zzjgbh: $("#zzjg_edit_f_zzjgbh").val(),
//                     f_zzjgmc: $("#zzjg_edit_f_zzjgmc").val(),
//                     f_remark: $("#zzjg_edit_f_remark").val()
//                 }),
//                 beforeSend: function () {
//                     showLoad();
//                 },
//                 success: function (result) {
//                     if (result.status == '1') {
//                         $('#editZzjgForm').modal('hide');//关闭编辑窗口
//                         swal({
//                             title: result.msg,// 展示的标题
//                             text: "",// 内容
//                             type: "success",
//                             showCloseButton: false, // 展示关闭按钮
//                             allowOutsideClick: false,
//                             showConfirmButton: false,
//                             timer: 1000
//                         });
//                         $('#zzjgTable').tabulator("updateRow", _curRow, {
//                             f_zzjgmc: result.data.f_zzjgmc,
//                             f_remark: result.data.f_remark,
//                             f_chdate: result.data.f_chdate
//                         });
//                         //在树上修改
//                         var parentNode = $('#tree_zzjg').treeview('findNodes', [_curRow.getData().f_id, 'fid']);
//                         if (parentNode.length > 1) {
//                             for (var i = 0; i < parentNode.length; i++) {
//                                 if (parentNode[i].id == _curRow.getData().f_id) {
//                                     parentNode = parentNode[i];
//                                 }
//                             }
//                         } else {
//                             parentNode = parentNode[0];
//                         }
//                         var newNode = {text: _curRow.getData().f_zzjgmc};
//                         $('#tree_zzjg').treeview('updateNode', [parentNode, newNode]);
//                     } else {
//                         swal("修改失败!", result.msg, "error");
//                     }
//                 },
//                 complete: function () {
//                     hiddenLoad();
//                 },
//                 error: function (result) {
//                     swal("修改失败!", result.msg, "error");
//                 }
//             });
//         }
//
//         function deletezzjg(id, node) {
//             swal({
//                 title: "您确定要删除吗?",
//                 text: "信息删除后将不可恢复!",
//                 type: "warning",
//                 showCancelButton: true,
//                 confirmButtonColor: "#1ab394",
//                 confirmButtonText: "确定",
//                 closeOnConfirm: false
//             }, function () {
//                 setTimeout(function () {
//                         $.ajax({
//                             url: _ctx + "/view/datacenter/zzjg_del",
//                             contentType: "application/json; charset=utf-8",
//                             type: "post",
//                             data: JSON.stringify({
//                                 f_id: id
//                             }),
//                             beforeSend: function () {
//                                 showLoad();
//                             },
//                             success: function (data) {
//                                 if (data.status == '1') {
//                                     swal({
//                                         title: data.msg,// 展示的标题
//                                         text: "",// 内容
//                                         type: "success",
//                                         showCloseButton: false, // 展示关闭按钮
//                                         allowOutsideClick: false,
//                                         showConfirmButton: false,
//                                         timer: 1000
//                                     });
//                                     //在表格中删除该条数据
//                                     $("#zzjgTable").tabulator("deleteRow", _curRow);
//                                     //在树上删除该条数据
//                                     $("#tree_zzjg").treeview("removeNode", node, {silent: true});
//                                 } else {
//                                     swal(data.msg, "", "error");
//                                 }
//                             },
//                             complete: function () {
//                                 hiddenLoad();
//                             },
//                             error: function (data) {
//                                 swal(data.msg, "", "error");
//                             }
//                         });
//                     }, 100
//                 )
//             });
//         }

        //加载未选择用户表
        function loadNoIncludeBrc(id, keywords) {

            $.ajax({
                url: _ctx + "/view/basedatamanage/efficiencyanalysis/loadNoBrc",
                type: "post",
                data: ({
                    fFhbh: id,
                    keywords: keywords
                }),
                success: function (data) {

                    //填充“未选择”数据
                    if (data.hasOwnProperty('list') == false) {
                        $("#householdBrc_noInclude").tabulator("setData", []);
                    } else {
                        $("#householdBrc_noInclude").tabulator("setData", data.list);
                    }
                    var noinclude_group_data = $("#householdBrc_noInclude").tabulator("getData");
                    if (noinclude_group_data.length == 0) {
                        $("#householdConf_right").attr('disabled', true);
                        $("#householdConf_left").attr('disabled', false);
                    } else {
                        $("#householdConf_right").attr('disabled', false);
                    }
                },
                error: function (data) {
                    swal("修改失败!", data.msg, "error");
                }
            });
        };

        return {
            checkDepPeopleNumber: function () {

                var re = /^[1-9]+[0-9]*]*$/;//判断正整数
                if (!re.test($('#depPeopleNumber').val())) {
                    swal("请输入正确的部门人数", "", "error");
                    return;
                }
                if ($('#depPeopleNumber').val() == "" || $('#depPeopleNumber').val() == "0" || $('#depPeopleNumber').val() == 0) {
                    swal("请输入部门人数", "", "error");
                    return;
                }

            },
            //保存提交
            add_depInfo: function () {
                // if (_zzjgJs == '3') {
                    if ($('#depPeopleNumber').val() == "" || $('#depPeopleNumber').val() == "0" || $('#depPeopleNumber').val() == 0) {
                        swal("请输入部门人数", "", "error");
                        return;
                    }
                    var re = /^[1-9]+[0-9]*]*$/;//判断正整数
                    if (!re.test($('#depPeopleNumber').val())) {
                        swal("请输入正确的部门人数", "", "error");
                        return;
                    }
                // }
                if (addDepList.length == 0) {
                    swal("请添加支路或电表数据", "", "error");
                    return;
                } else {
                    $.ajax({
                        url: _ctx + "/view/basedatamanage/energyinformation/add_Department",
                        type: "post",
                        data: {"list": JSON.stringify(addDepList)},
                        success: function () {
                            addDepList = []
                            organizationmanage_zzjg.searchzzjg();
                            $('#modal-form-addzzjg').modal('hide');
                            swal("编辑成功", "", "success");
                        },
                        error: function (result) {
                            swal(result.msg, "", "error");
                        }
                    });
                    var number = $('#depPeopleNumber').val();
                    $.ajax({
                        url: _ctx + "/view/basedatamanage/energyinformation/setDepPeopleNumber",
                        type: "post",
                        data: {
                            F_DEP_ID: _zzjgbh,
                            number: number
                        },
                        success: function () {
                            $.ajax({
                                type: "post",
                                url: _ctx + "/view/datacenter/zzjgTreeZC",
                                dataType: "json",
                                beforeSend: function () {
                                    showLoad();
                                },
                                success: function (result) {
                                    if (result.hasOwnProperty("list")) {//判断result返回结果是否包含list
                                        result.list[0].text = '2号楼';
                                        var nodesList = result.list[0].nodes;
                                        var newNodesList = new Array();
                                        for (let i = 0; i < nodesList.length; i++) {
                                            if (nodesList[i].text == '山东正晨科技股份有限公司' || nodesList[i].text == '山东正威检测科技有限公司' || nodesList[i].text == '山东邦展建筑设计有限公司' || nodesList[i].text == '山东高速材料科技有限公司') {
                                                newNodesList.push(nodesList[i]);
                                            }
                                        }
                                        result.list[0].nodes = newNodesList;
                                        if (result.list.length > 0) {//如果包含判断是否长度大于0
                                            $('#tree_zzjg').treeview({
                                                data: result.list,         // 数据源
                                                highlightSelected: true,    //是否高亮选中
                                                levels: 2,
                                                enableLinks: true,//必须在节点属性给出href属性
                                                color: "#4a4747",
                                                onNodeSelected: function (event, nodeData) {
                                                    $('#tree_zzjg').treeview('clearSearch');//清除搜索选中高亮
                                                    _zzjgbh = nodeData.id;
                                                    _zzjginfo1 = nodeData.id;
                                                    _zzjgJs = nodeData.level;
                                                    $.ajax({
                                                        url: _ctx + "/view/basedatamanage/energyinformation/getDepartmentList",
                                                        contentType: "application/json; charset=utf-8",
                                                        type: "get",
                                                        beforeSend: function () {
                                                            showLoad();
                                                        },
                                                        data: {
                                                            f_zzjgbh: nodeData.id,
                                                            f_js: nodeData.js
                                                        },
                                                        success: function (result) {
                                                            if (result.hasOwnProperty("list")) {
                                                                $("#zzjgTable").tabulator("setData", result.list);
                                                            } else {
                                                                $("#zzjgTable").tabulator("setData", []);
                                                            }
                                                        },
                                                        complete: function () {
                                                            hiddenLoad();
                                                        },
                                                        error: function (result) {
                                                            swal("查询失败", "", "error");
                                                        }
                                                    });
                                                }
                                            });
                                            var firstNode = $("#tree_zzjg").treeview('findNodes', [result.list[0].id, 'id']);
                                            $("#tree_zzjg").treeview("selectNode", firstNode);//将第一个节点设置为选中状态
                                        }
                                    }
                                },
                                complete: function () {
                                    hiddenLoad();
                                },
                                error: function (nodeData) {
                                    swal(nodeData.msg, "", "error");
                                }
                            });
                        },
                        error: function (result) {

                        }
                    });
                }

            },

            //搜索
            searchzzjg: function () {
                var zzjginfo = $("#zzjginfo").val();
                $("#zzjgTable").tabulator("setData", _ctx + '/view/basedatamanage/energyinformation/getDepartmentList?f_zzjgbh=' + _zzjgbh);
            },
            searchzzjgZLDB: function () {
                var zzjginfo = $("#zzjginfo").val();
                $("#zzjgTable").tabulator("setData", _ctx + '/view/basedatamanage/energyinformation/getDepartmentAllList?f_zzjgbh=' + _zzjginfo1 + '&zzjginfo=' + zzjginfo);
            },
            getCurRow: function () {
                return _curRow;
            },
            //验证增加模态框是否弹出
            show_addmodal: function () {
                addDepList = []
                $("#householdBrc_include").tabulator("setData", []);
                $("#wattHourMeter_include").tabulator("setData", []);
                // $('#selectBranchInfo').val('');
                // $('#selectElectricityMeterInfo').val('');
                var node = $('#tree_zzjg').treeview('getSelected');
                // if (node.length == 0 || node[0].level != '3') {
                //     $("#depPeopleNumberDiv").hide();
                //     $("#depPeopleNumberDivHR").hide();
                // } else {
                //     $("#depPeopleNumberDiv").show();
                //     $("#depPeopleNumberDivHR").show();
                // }
                // if (node.length == 0 || node[0].level != '3') {//凡是1节点，note.length都为1；无选择节点，为0；
                //     swal({
                //         title: "请选择部门",
                //         text: "经检测，您未选择部门节点!",
                //         type: "warning",
                //         showCancelButton: false,
                //         confirmButtonColor: "#1ab394",
                //         confirmButtonText: "关闭",
                //         closeOnConfirm: false
                //     });
                // } else {
                getDepPeopleNumber(_zzjgbh)
                getSelectBranchInfo(_zzjgbh, '')
                getSelectBranchInfoChoose(_zzjgbh, _zzjgJs)
                getSelectElectricityMeterInfo(_zzjgbh, '')
                getSelectElectricityMeterInfoChoose(_zzjgbh, _zzjgJs)
                $('#modal-form-addzzjg').modal('show');
                // }
            },

            //搜索已包含支路
            searchIncludeBrc: function () {
                var includeBrcKeywords = $("#includeBrcKeywords").val();
                getSelectBranchInfo(_zzjgbh, includeBrcKeywords);
                // loadIncludeBrc(_fhbh, includeBrcKeywords);
                // $("#householdBrc_include").tabulator("setData", _ctx+'/view/basedatamanage/energyinformation/getSelectBranchInfo?&keywords='+includeBrcKeywords);
            },

            //搜索未包含支路
            searchNotIncludeBrc: function () {
                var notincludeBrcKeywords = $("#notincludeBrcKeywords").val();
                getSelectBranchInfo(_zzjgbh, notincludeBrcKeywords);
                // loadNoIncludeBrc(_fhbh, notincludeBrcKeywords);
                // $("#householdBrc_noInclude").tabulator("setData", _ctx+'/view/basedatamanage/energyinformation/getSelectBranchInfo?&keywords='+notincludeBrcKeywords);
            },

            //搜索已包含电表
            searchIncludeBrcDB: function () {
                var includeBrcKeywords = $("#includeBrcKeywordsDB").val();
                getSelectElectricityMeterInfo(_zzjgbh, includeBrcKeywords);
                // loadIncludeBrc(_fhbh, includeBrcKeywords);
                // $("#householdBrc_include").tabulator("setData", _ctx+'/view/basedatamanage/energyinformation/getSelectBranchInfo?&keywords='+includeBrcKeywords);
            },

            //搜索未包含电表
            searchNotIncludeBrcDB: function () {
                var notincludeBrcKeywords = $("#notincludeBrcKeywordsDB").val();
                getSelectElectricityMeterInfo(_zzjgbh, notincludeBrcKeywords);
                // loadNoIncludeBrc(_fhbh, notincludeBrcKeywords);
                // $("#householdBrc_noInclude").tabulator("setData", _ctx+'/view/basedatamanage/energyinformation/getSelectBranchInfo?&keywords='+notincludeBrcKeywords);
            },

            //支路全部右移
            BrcrightMove: function () {
                var householdConf_tem = $("#householdBrc_noInclude").tabulator("getData");
                if (householdConf_tem.length < 1) {
                    $("#householdConf_right").attr('disabled', true);
                } else {
                    $.ajax({
                        url: _ctx + "/view/basedatamanage/energyinformation/getSelectBranchInfo",
                        type: "post",
                        // data:({
                        //     fFhbh:_fhbh
                        // }),
                        success: function (result) {
                            $("#householdBrc_noInclude").tabulator("setData", []);
                            $("#householdBrc_include").tabulator("setData", result.list);
                            $("#householdConf_right").attr('disabled', true);
                            $("#householdConf_left").attr('disabled', false);
                        },
                        error: function (result) {
                            swal(result.msg, "", "error");
                        }
                    });
                }
            },
            //支路全部左移
            BrcleftMove: function () {
                var householdConf_tem = $("#householdBrc_include").tabulator("getData");
                if (householdConf_tem.length < 1) {
                    $("#householdConf_left").attr('disabled', true);
                } else {
                    $.ajax({
                        url: _ctx + "/view/basedatamanage/energyinformation/getSelectBranchInfo",
                        type: "post",
                        // data:({
                        //     fFhbh:_fhbh
                        // }),
                        success: function (result) {
                            $("#householdBrc_include").tabulator("setData", []);
                            $("#householdBrc_noInclude").tabulator("setData", result.list);
                            $("#householdConf_right").attr('disabled', false);
                            $("#householdConf_left").attr('disabled', true);
                        },
                        error: function (result) {
                            swal(result.msg, "", "error");
                        }
                    });
                }
            },

            //电表全部右移
            BrcrightMoveDB: function () {
                var householdConf_tem = $("#wattHourMeter_noInclude").tabulator("getData");
                if (householdConf_tem.length < 1) {
                    $("#wattHourMeter_right").attr('disabled', true);
                } else {
                    $.ajax({
                        url: _ctx + "/view/basedatamanage/energyinformation/getSelectElectricityMeterInfo",
                        type: "post",
                        success: function (result) {
                            $("#wattHourMeter_noInclude").tabulator("setData", []);
                            $("#wattHourMeter_include").tabulator("setData", result.list);
                            $("#wattHourMeter_right").attr('disabled', true);
                            $("#wattHourMeter_left").attr('disabled', false);
                        },
                        error: function (result) {
                            swal(result.msg, "", "error");
                        }
                    });
                }
            },
            //电表全部左移
            BrcleftMoveDB: function () {
                var householdConf_tem = $("#wattHourMeter_include").tabulator("getData");
                if (householdConf_tem.length < 1) {
                    $("#wattHourMeter_left").attr('disabled', true);
                } else {
                    $.ajax({
                        url: _ctx + "/view/basedatamanage/energyinformation/getSelectElectricityMeterInfo",
                        type: "post",
                        success: function (result) {
                            $("#wattHourMeter_include").tabulator("setData", []);
                            $("#wattHourMeter_noInclude").tabulator("setData", result.list);
                            $("#wattHourMeter_right").attr('disabled', false);
                            $("#wattHourMeter_left").attr('disabled', true);
                        },
                        error: function (result) {
                            swal(result.msg, "", "error");
                        }
                    });
                }
            },

        }


    })(jQuery, window, document);
</script>