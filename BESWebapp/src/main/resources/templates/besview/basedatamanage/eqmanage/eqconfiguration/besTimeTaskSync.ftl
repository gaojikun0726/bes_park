<style type="text/css">

    #syncConfig_content .layui-laydate, .layui-laydate-hint {
        border: 1px solid #d2d2d2;
        box-shadow: 0 2px 4px rgba(0, 0, 0, .12);
        background-color: #fff;
        color: #666;
        width: 630px;
    }

    .syncTableContant {
        width: 100%;
        /*margin-top: 8px;*/
        position: relative;
    }
    .syncLog-datecheck{
        border: 1px solid rgb(54, 108, 144);
        color: rgb(11, 34, 76);
        width: 9vw;
        height: 2.9vh;
        text-align: left;
        padding-left: 12px;
        border-radius: 4px;
    }
    .syncLog-input{
        border: 1px solid rgb(54, 108, 144);
        color: rgb(11, 34, 76);
        width: 9vw;
        height: 2.9vh;
        text-align: left;
        padding-left: 12px;
        border-radius: 4px;
    }


</style>


<div class="information-model" >
		<span class="Subtitle">
			<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;定时同步设备列表>>>
		</span>
    <!-- 增加按钮 -->
    <a id="addTimeTaskSync" data-toggle="modal" href="#timeTaskSyncModalAdd" class="btn btn-primary toLeft">
        <i class="fa fa-plus" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>增加
    </a>
    <!-- 执行日志按钮 -->
    <a id="querySyncLog" data-toggle="modal" href="#timeTaskSyncLog" style="width: 4.5vw;" class="btn btn-primary toLeft">
        <i class="fa fa-file-text" style="margin-top: 2.5px;margin-left: 2px;margin-right: 2px;" aria-hidden="true"></i>执行记录
    </a>

    <!-- 搜索框 -->
    <div class="zc_search find">
        <input type="text" class="find-style" id="timeTaskSyncSearchKeywords" name="timeTaskSyncSearchKeywords"
               placeholder="任务名称">
        <button id="queryTimeTaskSync" onclick="timeTaskSync.search()">
            <i class="fa fa-search" aria-hidden="true"></i>
        </button>
    </div>
</div>

<!--分页列表-->
<div class="syncTableContant" id="timeTaskSyncTableParam">
    <table class="layui-hide" id="timeTaskSyncTable" lay-filter="demoCollectSyncEvent" ></table>
    <script type="text/html" id="rowTimeTaskSyncToolBar">
        <#--<a class="layui-btn layui-btn-xs" lay-event="collectExecute" id="collectExecuteBtn">执行</a>
            <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="collectStop" id="collectStopBtn">停止</a> -->
        <a class="layui-btn layui-btn-xs" lay-event="collectUpdate">修改</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="collectDelete">删除</a>
    </script>
</div>

<script type="text/html" id="switchTimeTaskSync">
    <#--    执行停止-->
    <input type="checkbox" name="f_status" value={{d.f_id}} {{d.f_status=="1"?"checked":""}} lay-skin="switch"
           lay-text="执行|停止" lay-filter="executeStopTimeTaskSync">

</script>

<!---执行记录开始----->
<div class="modal fade" id="timeTaskSyncLog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static" data-keyboard="false" style="margin-right: 10%;">
    <div class="modal-dialog">
        <div class="modal-content" style="height: 50%;width: 1000px;margin-top: 20%">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title">&nbsp;执行记录</h4>
            </div>
            <!-- 搜索框 -->
            <div style="margin-top: 20px">

                <label style="margin-left: 20px">任务名称：</label>
                <input type="text" id="syncLogFSyncName" name="syncLogFSyncName" class="syncLog-input"
                       placeholder="">

                <label style="margin-left: 20px">点位名称：</label>
                <input type="text" id="syncLogFPointName" name="syncLogFPointName" class="syncLog-input"
                       placeholder="">

                <label style="margin-left: 20px">下发时间：</label>
                <input  id="syncLogFSyncTime" type="text"  name="start" class="syncLog-datecheck"
                        onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />

                <#--<button id="queryTimeTaskSyncLog" onclick="timeTaskSync.syncLogSearch()">
                    <i class="fa fa-search" aria-hidden="true"></i>
                </button>-->

                <button type="button" class="btn btn-sm btn-primary no-margins" style="float: right"
                        onclick="timeTaskSync.syncLogReset()">
                    <i class="fa fa-refresh"></i>&nbsp;重置
                </button>
                <button type="button" style="float: right" class="btn btn-sm btn-primary no-margins"
                        onclick="timeTaskSync.syncLogSearch()">
                    <i class="fa fa-spinner"></i>&nbsp;查询
                </button>
            </div>
            <div class="ibox" id="sync_log_page" style="height:600px;margin-top: 10px"></div>

        </div>
    </div>
</div>
<!-- 执行记录结束 -->


<!---添加设备类型开始----->
<div class="modal fade" id="timeTaskSyncModalAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static" data-keyboard="false" style="width: 600px;margin-left: 35%">
    <div class="modal-dialog">
        <div class="modal-content" style="height: 100px;width: 600px;margin-top: 15%">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;编辑定时同步</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="timeTaskSyncFormAdd" name="timeTaskSyncFormAdd" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">任务名称<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="timeTaskSyncJobNameAdd" name="timeTaskSyncJobNameAdd" placeholder="请输入任务名称" maxlength="50"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">设备<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <textarea  class='form-control' id="timeTaskSyncEquipmentNameAdd" name="timeTaskSyncEquipmentNameAdd" placeholder="请选择设备" />
                        </div>
                    </div>
                    <#--<div class="form-group">
                        <label class="col-sm-3 control-label">cron表达式</label>
                        <div class="col-sm-8">
                            <input type="text" id="timeTaskSyncCronAdd" name="timeTaskSyncCronAdd" placeholder="请输入cron表达式"
                                   class="form-control">
                        </div>
                    </div>-->
                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-sm-3 control-label">配置cron表达式<span class="text-danger"></span></label>
                        <div class="col-sm-8">
                            <input type="text" id="timeTaskSyncCronAdd" name="timeTaskSyncCronAdd" class="form-control" placeholder="配置cron表达式">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">备注</label>
                        <div class="col-sm-8">
                            <input type="text" id="timeTaskSyncRemarkAdd" name="timeTaskSyncRemarkAdd" maxlength="50"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group m-t-sm">
                        <div class="col-sm-6 col-sm-push-3 display_flex">
                            <button class="btn btn-md btn-primary" type="button" onclick="timeTaskSync.submitSyncInfo()"><strong>确定</strong></button>
                            <button class="btn btn-white m-l-sm" type="button" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- 添加设备类型结束 -->

<!----编辑设备类型--->
<div class="modal fade" id="timeTaskSyncModalEdit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static" data-keyboard="false" style="width: 600px;margin-left: 35%">
    <div class="modal-dialog">
        <div class="modal-content" style="height: 100px;width: 600px;margin-top: 15%">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp;编辑定时同步</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="timeTaskSyncFormEdit" name="timeTaskSyncFormEdit" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">任务名称<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="hidden" id="timeTaskSyncIdEdit" name="timeTaskSyncIdEdit"/>
                            <input type="text" id="timeTaskSyncJobNameEdit" name="timeTaskSyncJobNameEdit" placeholder="请输入任务名称" maxlength="50"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">设备<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <textarea  class='form-control' id="timeTaskSyncEquipmentNameEdit" name="timeTaskSyncEquipmentNameEdit" placeholder="请选择设备" />
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 20px">
                        <label class="col-sm-3 control-label">配置cron表达式<span class="text-danger"></span></label>
                        <div class="col-sm-8">
                            <input type="text" id="timeTaskSyncCronEdit" name="timeTaskSyncCronEdit" class="form-control" placeholder="配置cron表达式">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">备注</label>
                        <div class="col-sm-8">
                            <input type="text" id="timeTaskSyncRemarkEdit" name="timeTaskSyncRemarkEdit" maxlength="500"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group m-t-sm">
                        <div class="col-sm-6 col-sm-push-3 display_flex">
                            <button class="btn btn-md btn-primary" type="button" onclick="timeTaskSync.submitUpdateSyncInfo()"><strong>确定</strong></button>
                            <button class="btn btn-white m-l-sm" type="button" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>
<!----编辑设备类型结束--->

<!--点位树-->
<link href="${ctx}/static/ztree/css/metroStyle/metroStyle.css" rel="stylesheet">
<div class="modal fade" style="width:40%;margin-left:59.5%;margin-top: 4.4%" id="timeTaskSyncTreeModel" tabindex="-1"
     role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" style="margin-top:6px;margin-right: 40px">
        <div class="modal-content" style="width: 350px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">请选择点位</h4>
            </div>
            <div class="modal-body" style="height: 500px">
                <#--设备类型树-->
                <div id="timeTaskSyncTree" class="Information_area ztree"></div>
            </div>
        </div>
    </div>
</div>
<!--点位树结束-->

<!--cron表达式-->
<div class="modal fade" id="timeTaskSyncCronModal" tabindex="-1" role="dialog" data-backdrop="static">
    <div class="modal-dialog" style="width: 46vw;margin-top: 0px;">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp; cron表达式生成</h4>
            </div>
            <div class="modal-body" style="height: 100%;overflow-y: auto;">
                <div id="dataMonitor">
                    <div id="cron_model" style="width: 100%;height: 96%"></div>

                </div>
            </div>
        </div><!-- end modal-content -->
    </div>
</div>
<!--cron表达式结束-->


<script type="text/javascript">
    ;
    var timeTaskSync = (function ($, window, document, undefined)
    {
        var _ctx = '${ctx}';

        var tabNotice;
        var cr = "1";//当前页数

        var timeTaskSyncModelType = '';

        var sbList = []; //存储当前的选择
        var pointId = []; //点位ID
        var arrNameAndNickName = []; //名称+别名


        Array.prototype.push2 =function(){

            for(var i=0; i<arguments.length; i++){
                var ele = arguments[i];
                if(this.indexOf(ele) == -1){
                    this.push(ele);
                }
            }
        };
        //加载树
        $(function () {
            equip_tree();//点位树
        })
        //点位树初次加载
        function equip_tree() {
            $.ajax({
                type: "post",
                url: "${ctx}/view/basedatamanage/eqmanage/austere_devices_tree",
                dataType: "json",
                async: true,
                success: function (result) {
                    var status = result && result.status;

                    if (status !== '1') {
                        return;
                    }

                    var treeList = result.list || [];

                    var tree = new Tree({
                        container: 'timeTaskSyncTree',
                        idKey: 'f_sys_name',
                        pIdKey: 'f_psys_name',
                        name: 'f_nick_name',
                        setting: {
                            view: {
                                showIcon: false,
                                fontCss: function (treeId, treeNode) {
                                    return (treeNode.highlight)
                                        ? {color: '#A60000', 'font-weight': 'bold'}
                                        : {color: '#000000', 'font-weight': 'normal'};
                                }
                            },
                        },
                        callback: function (node) {
                            //2:DDC, 3:IP路由器, 9:模块, 10:AI, 11:AO, 12:DI, 13:DO, 26:采集器, 28:电表
                            if (node.f_type == "2" || node.f_type == "3" || node.f_type == "26") {

                                let i = pointId.indexOf(node.f_id);
                                if (i > -1) {//已添加
                                    return;
                                }

                                pointId.push(node.f_id);
                                var map = {
                                    f_point_id:node.f_id,
                                    f_point_name:node.f_sys_name,
                                    f_point_psys_name:node.f_psys_name,
                                    f_point_type:node.f_type,
                                    f_point_all_name:node.f_sys_name_old + "(" + node.f_nick_name + ")"
                                };
                                sbList.push(map);
                                arrNameAndNickName.push(node.f_sys_name_old + "(" + node.f_nick_name + ")")
                                //文本框展示
                                if (timeTaskSyncModelType == 'add') {
                                    $('#timeTaskSyncEquipmentNameAdd').val(arrNameAndNickName.join(' \n '));
                                    autoTextarea(document.getElementById("timeTaskSyncEquipmentNameAdd"));
                                } else if (timeTaskSyncModelType == 'update'){
                                    $('#timeTaskSyncEquipmentNameEdit').val(arrNameAndNickName.join(' \n '));
                                    autoTextarea(document.getElementById("timeTaskSyncEquipmentNameEdit"));
                                }

                            } else {
                                swal({
                                    title: '请选择正确的点位',// 展示的标题
                                    type: "warning",
                                    showCloseButton: false, // 展示关闭按钮
                                    allowOutsideClick: false,
                                    showConfirmButton: false,
                                    timer: 1000
                                })
                                return;
                            }
                        }
                    })
                    tree.loadData(treeList);
                }
            })
        }


        //添加定时任务 提交
        function submitSyncInfo(){
            let timeTaskSyncJobNameAdd = $("#timeTaskSyncJobNameAdd").val();//任务名称
            let timeTaskSyncEquipmentNameAdd = $("#timeTaskSyncEquipmentNameAdd").val();//设备名称
            let timeTaskSyncCronAdd = $("#timeTaskSyncCronAdd").val();//cron表达式
            let timeTaskSyncRemarkAdd = $("#timeTaskSyncRemarkAdd").val();//备注

            if (timeTaskSyncJobNameAdd == '' || timeTaskSyncJobNameAdd == null) {
                swal({
                    title: '请输入任务名称!',// 展示的标题
                    text: "",// 内容
                    type: "warning",
                    showCloseButton: false, // 展示关闭按钮
                    allowOutsideClick: false,
                    showConfirmButton: false,
                    timer: 1000
                });
                return;
            }
            if (timeTaskSyncEquipmentNameAdd == '' || timeTaskSyncEquipmentNameAdd == null) {
                swal({
                    title: '请选择设备!',// 展示的标题
                    text: "",// 内容
                    type: "warning",
                    showCloseButton: false, // 展示关闭按钮
                    allowOutsideClick: false,
                    showConfirmButton: false,
                    timer: 1000
                });
                return;
            }
            if (timeTaskSyncCronAdd == '' || timeTaskSyncCronAdd == null) {
                swal({
                    title: '请配置cron表达式!',// 展示的标题
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
                url: "${ctx}/view/basedatamanage/eqmanage/besTimeTaskSync/insertTimeTaskSync",
                type: "post",
                dataType: "json",
                async: false,
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify({
                    f_job_name: timeTaskSyncJobNameAdd,//任务名称
                    f_cron: timeTaskSyncCronAdd,      //cron表达式
                    f_status: '1',                    //状态(0未启用,1已启用)
                    f_remark: timeTaskSyncRemarkAdd,  //备注
                    sbList:sbList                     //设备列表
                }),
                success: function (result) {
                    if (result.status == "1") {
                        executeTimeTaskSyncInfo(result.data);
                        $('#timeTaskSyncModalAdd').modal('hide');
                        refreshTable()
                    } else {
                        swal({
                            title: result.msg,
                            text: "",// 内容
                            type: "error",
                            showCloseButton: false, // 展示关闭按钮
                            allowOutsideClick: false,
                            showConfirmButton: false,
                            timer: 2000
                        });
                    }
                },
                error: function () {
                    swal({
                        title: "添加定时同步任务失败",
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

        //修改定时任务 提交
        function submitUpdateSyncInfo(){
            let timeTaskSyncIdEdit = $("#timeTaskSyncIdEdit").val();//任务id
            let timeTaskSyncJobNameEdit = $("#timeTaskSyncJobNameEdit").val();//任务名称
            let timeTaskSyncEquipmentNameEdit = $("#timeTaskSyncEquipmentNameEdit").val();//设备名称
            let timeTaskSyncCronEdit = $("#timeTaskSyncCronEdit").val();//cron表达式
            let timeTaskSyncRemarkEdit = $("#timeTaskSyncRemarkEdit").val();//备注

            if (timeTaskSyncIdEdit == null || timeTaskSyncIdEdit == '') {
                return;
            }

            if (timeTaskSyncJobNameEdit == '' || timeTaskSyncJobNameEdit == null) {
                swal({
                    title: '请输入任务名称!',// 展示的标题
                    text: "",// 内容
                    type: "warning",
                    showCloseButton: false, // 展示关闭按钮
                    allowOutsideClick: false,
                    showConfirmButton: false,
                    timer: 1000
                });
                return;
            }
            if (timeTaskSyncEquipmentNameEdit == '' || timeTaskSyncEquipmentNameEdit == null) {
                swal({
                    title: '请选择设备!',// 展示的标题
                    text: "",// 内容
                    type: "warning",
                    showCloseButton: false, // 展示关闭按钮
                    allowOutsideClick: false,
                    showConfirmButton: false,
                    timer: 1000
                });
                return;
            }
            if (timeTaskSyncCronEdit == '' || timeTaskSyncCronEdit == null) {
                swal({
                    title: '请配置cron表达式!',// 展示的标题
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
                url: "${ctx}/view/basedatamanage/eqmanage/besTimeTaskSync/updateTimeTaskSync",
                type: "post",
                dataType: "json",
                async: false,
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify({
                    f_id: timeTaskSyncIdEdit,  //任务id
                    f_job_name: timeTaskSyncJobNameEdit,//任务名称
                    f_cron: timeTaskSyncCronEdit,      //cron表达式
                    f_remark: timeTaskSyncRemarkEdit,  //备注
                    sbList:sbList                     //设备列表
                }),
                success: function (result) {
                    $('#timeTaskSyncModalEdit').modal('hide');
                    if (result.status == "1") {
                        swal({
                            title: result.msg,
                            text: "",// 内容
                            type: "success",
                            showCloseButton: false, // 展示关闭按钮
                            allowOutsideClick: false,
                            showConfirmButton: false,
                            timer: 2000
                        });
                    } else {
                        swal({
                            title: result.msg,
                            text: "",// 内容
                            type: "error",
                            showCloseButton: false, // 展示关闭按钮
                            allowOutsideClick: false,
                            showConfirmButton: false,
                            timer: 2000
                        });
                    }
                    refreshTable()
                },
                error: function () {
                    swal({
                        title: "修改定时同步任务失败",
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

        //在quartz添加时间类型的定时任务
        function executeTimeTaskSyncInfo(syncId) {

            $.ajax({
                url: "${ctx}/quartz/insertTimeTaskSyncInfomation",
                type: "post",
                dataType: "json",
                async: false,
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify({
                    syncId: syncId,
                }),
                success: function (result) {

                    if (result.status == "1") {
                        var jobId = result.data;
                        $.ajax({
                            url: "${ctx}/view/basedatamanage/eqmanage/besTimeTaskSync/insertTimeTaskSyncJobId",
                            type: "post",
                            dataType: "json",
                            async: false,
                            contentType: "application/json; charset=utf-8",
                            data: JSON.stringify({
                                jobId: jobId,
                                syncId: syncId
                            }),
                            success: function (result) {
                                swal({
                                    title: "定时同步任务添加成功",
                                    text: "",// 内容
                                    type: "success",
                                    showCloseButton: false, // 展示关闭按钮
                                    allowOutsideClick: false,
                                    confirmButtonColor: "#1ab394",
                                    showConfirmButton: false,
                                    timer: 2000
                                });
                            },
                            error: function () {
                                swal({
                                    title: "定时同步任务添加失败",
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
                    } else {
                        swal({
                            title: "定时任务添加失败",
                            text: "",// 内容
                            type: "error",
                            showCloseButton: false, // 展示关闭按钮
                            allowOutsideClick: false,
                            confirmButtonColor: "#1ab394",
                            showConfirmButton: false,
                            timer: 2000
                        });
                    }
                    // queryCollectTableParam(nodeId);
                    $('#addTimeTaskConfig').modal('hide');
                },
                error: function () {
                    swal({
                        title: "定时任务添加失败",
                        text: "",// 内容
                        type: "error",
                        showCloseButton: false, // 展示关闭按钮
                        allowOutsideClick: false,
                        confirmButtonColor: "#1ab394",
                        showConfirmButton: false,
                        timer: 2000
                    });
                    $('#addTimeTaskConfig').modal('hide');
                }
            })
        }

        //添加时点位树展示
        $('#timeTaskSyncEquipmentNameAdd').focus(function () {
            if (document.querySelectorAll("#timeTaskSyncTree li").length == 0) {
                swal({
                    title: '正在初始化设备树，请稍后点击！',// 展示的标题
                    type: "warning",
                    showCloseButton: false, // 展示关闭按钮
                    allowOutsideClick: false,
                    showConfirmButton: false,
                    timer: 2000
                });
                $('#timeTaskSyncEquipmentNameAdd').blur();
                return
            }
            $('#timeTaskSyncTreeModel').modal('show');
            document.getElementById("timeTaskSyncEquipmentNameAdd").style.height = 40 + "px";
        });
        //修改时点位树展示
        $('#timeTaskSyncEquipmentNameEdit').focus(function () {

            //清空设备
            pointId.length = 0;
            sbList.length = 0;
            arrNameAndNickName.length = 0;
            $('#timeTaskSyncEquipmentNameEdit').val("")

            if (document.querySelectorAll("#timeTaskSyncTree li").length == 0) {
                swal({
                    title: '正在初始化设备树，请稍后点击！',// 展示的标题
                    type: "warning",
                    showCloseButton: false, // 展示关闭按钮
                    allowOutsideClick: false,
                    showConfirmButton: false,
                    timer: 2000
                });
                $('#timeTaskSyncEquipmentNameEdit').blur();
                return
            }
            $('#timeTaskSyncTreeModel').modal('show');
            document.getElementById("timeTaskSyncEquipmentNameEdit").style.height = 40 + "px";
        });

        //cron表达式
        $('#timeTaskSyncCronAdd,#timeTaskSyncCronEdit').focus(function () {
            $('#timeTaskSyncTreeModel').modal('hide'); //点位树关闭
            $('#timeTaskSyncCronModal').modal('show');
        });

        //表达式生成页面加载之前,先确定页面所在的位置以及查询所加载页面的url
        $("#timeTaskSyncCronModal").on('show.bs.modal', function (event) {
            //垂直居中显示
            $(this).css('display', 'block');
            $(this).css("overflow", "hidden"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的
            loadPageByMold();
        });

        //根据文本框内容自适应高度
        function autoTextarea(elem, extra, maxHeight) {
            extra = extra || 0;
            var isFirefox = !!document.getBoxObjectFor || 'mozInnerScreenX' in window,
                isOpera = !!window.opera && !!window.opera.toString().indexOf('Opera'),
                addEvent = function (type, callback) {
                    elem.addEventListener ?
                        elem.addEventListener(type, callback, false) :
                        elem.attachEvent('on' + type, callback);
                },
                getStyle = elem.currentStyle ? function (name) {
                    var val = elem.currentStyle[name];
                    if (name === 'height' && val.search(/px/i) !== 1) {
                        var rect = elem.getBoundingClientRect();
                        return rect.bottom - rect.top -
                            parseFloat(getStyle('paddingTop')) -
                            parseFloat(getStyle('paddingBottom')) + 'px';
                    };
                    return val;
                } : function (name) {
                    return getComputedStyle(elem, null)[name];
                },
                minHeight = parseFloat(getStyle('height'));
            elem.style.resize = 'none';
            var change = function () {
                var scrollTop, height,
                    padding = 0,
                    style = elem.style;
                if (elem._length === elem.value.length) return;
                elem._length = elem.value.length;
                if (!isFirefox && !isOpera) {
                    padding = parseInt(getStyle('paddingTop')) + parseInt(getStyle('paddingBottom'));
                };
                scrollTop = document.body.scrollTop || document.documentElement.scrollTop;
                elem.style.height = minHeight + 'px';
                if (elem.scrollHeight > minHeight) {
                    if (maxHeight && elem.scrollHeight > maxHeight) {
                        height = maxHeight;
                        style.overflowY = 'auto';
                    } else {
                        height = elem.scrollHeight;
                        style.overflowY = 'hidden';
                    };
                    style.height = height + extra + 'px';
                    // scrollTop += parseInt(style.height) - elem.currHeight;
                    // document.body.scrollTop = scrollTop;
                    // document.documentElement.scrollTop = scrollTop;
                    elem.currHeight = parseInt(style.height);
                };
            };
            addEvent('propertychange', change);
            addEvent('input', change);
            addEvent('focus', change);
            change();
        };

        //显示cron表达式页面
        function loadPageByMold() {

            $.ajax({
                url: _ctx + "/view/basedatamanage/eqmanage/besTimeTaskSync/cronPage",
                type: "post",
                success: function (result) {
                    $("#cron_model").empty();
                    $("#cron_model").html(result);
                },
                error: function (result) {
                }
            });
        }


        function refreshTable(param)
        {
            getPagingPage(param)
        }

        // 获取分页页面
        function getPagingPage(param) {
            param = param || "";

            $.ajax({
                type: "post",
                dataType: "json",
                async: false,
                url: "${ctx}/view/basedatamanage/eqmanage/besTimeTaskSync/queryPage",
                data: {
                    keywords: param
                },
                success: function (result) {
                    var syncData = result.list;
                    if (syncData == undefined) {
                        const list = [];
                        initSyncTable(list);
                    } else {
                        initSyncTable(syncData);
                    }
                },
                error: function () {
                }
            })

        }

        //控制计划table初始化
        function initSyncTable(data) {

            if (typeof cr == 'undefined' || data.length <= 17) {
                cr = 1;
            } else if (data.length > 17 && data.length <= 34) {
                cr = 2;
            } else if (data.length > 34 && data.length <= 51) {
                cr = 3;
            } else if (data.length > 51 && data.length <= 68) {
                cr = 4;
            } else if (data.length > 68 && data.length <= 85) {
                cr = 5;
            } else if (data.length > 85 && data.length <= 102) {
                cr = 6;
            } else if (data.length > 102 && data.length <= 119) {
                cr = 7;
            } else if (data.length > 119 && data.length <= 136) {
                cr = 8;
            } else if (data.length > 136 && data.length <= 154) {
                cr = 9;
            } else if (data.length > 154 && data.length <= 171) {
                cr = 10;
            }

            if (cr*17 > data.length) {
                if (data.length <= 17) {
                    cr = 1;
                } else if (data.length > 17 && data.length <= 34) {
                    cr = 2;
                } else if (data.length > 34 && data.length <= 51) {
                    cr = 3;
                } else if (data.length > 51 && data.length <= 68) {
                    cr = 4;
                } else if (data.length > 68 && data.length <= 85) {
                    cr = 5;
                } else if (data.length > 85 && data.length <= 102) {
                    cr = 6;
                } else if (data.length > 102 && data.length <= 119) {
                    cr = 7;
                } else if (data.length > 119 && data.length <= 136) {
                    cr = 8;
                } else if (data.length > 136 && data.length <= 154) {
                    cr = 9;
                } else if (data.length > 154 && data.length <= 171) {
                    cr = 10;
                }
            }

            layui.use(['opTable'], function () {
                var collectForm = layui.form;
                tabNotice = layui.opTable.render({
                    elem: '#timeTaskSyncTable'
                    , data: data
                    , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                    // 第一列显示展开
                    , openColumnIndex: 0
                    // 不单独占一列
                    , isAloneColumn: false
                    , opOrientation: 'h'
                    , page: {
                        limit: 17,
                        curr: cr
                    }
                    // , limit: Number.MAX_VALUE // 数据表格默认全部显示
                    , cols: [[
                        {title: "任务名称", field: "f_job_name"},
                        {title: "cron", field: "f_cron"},
                        {title: "备注", field: "f_remark"},
                        , {field: 'f_status', title: '是否执行', width: 99, templet: '#switchTimeTaskSync', unresize: true}
                        , {field: 'sign', title: '操作', align: 'center', toolbar: '#rowTimeTaskSyncToolBar'}
                    ]]
                    ,done:function(res,curr,count){ // 隐藏列
                        cr = curr
                        // $(".layui-table-box").find("[data-field='f_id']").css("display","none");
                    }
                    //  展开的列配置
                    , openTable: function (itemData) {
                            if (typeof itemData.f_id != 'undefined') {
                                let syncId = itemData.f_id.toString();
                                getSbList((syncId),function (object) {
                                    resultData = object;
                                })

                                return {
                                    elem: '#child_2_' + itemData.LAY_INDEX
                                    , id: 'child_2_' + itemData.LAY_INDEX
                                    , data: resultData
                                    , openVisible: false
                                    , cols: [[
                                        {field: 'f_point_all_name', title: '点'},
                                        {field: 'f_point_psys_name', title: '父节点'}
                                    ]]
                                    ,done:function () {
                                    }
                                }
                            }
                        }
                    , skin: 'line' //表格风格
                    , even: false
                })
                //监听执行停止操作
                collectForm.on('switch(executeStopTimeTaskSync)', function (obj) {

                    let execute; //1 执行 2 不执行

                    let syncId = this.value;

                    let flag = obj.elem.checked;

                    if (flag) {
                        execute = "1";
                    } else {
                        execute = "2";
                    }

                    $.ajax({
                        type: "post",
                        url: "${ctx}/view/basedatamanage/eqmanage/besTimeTaskSync/selectTimeTaskSyncInfo",
                        dataType: "json",
                        async: false,
                        data: {
                            syncId: syncId
                        },
                        success: function (result) {
                            var status = result.status;
                            if (status == "1") {
                                executeTimeTaskSync(result.map, execute);
                            } else {
                                swal({
                                    title: '查询数据有错！',// 展示的标题
                                    type: "error",
                                    showCloseButton: false, // 展示关闭按钮
                                    allowOutsideClick: false,
                                    showConfirmButton: false,
                                    timer: 1500
                                });
                            }
                        },
                        error: function () {
                            swal({
                                title: '查询数据有错！',// 展示的标题
                                type: "error",
                                showCloseButton: false, // 展示关闭按钮
                                allowOutsideClick: false,
                                showConfirmButton: false,
                                timer: 1500
                            });
                        }
                    })
                });
            })
        }

        //改变执行状态 execute(1执行 ,2不执行)
        function executeTimeTaskSync(syncInfo, execute) {

            var jobId = syncInfo.f_job_id;
            if (execute === "1") {//执行
                $.ajax({
                    type: "post",
                    dataType: "json",
                    async: false,
//                      contentType: 'application/json; charset=UTF-8',
                    url: "${ctx}/quartz/executeTimeTaskSyncInfomation",
                    data: {
                        jobId: jobId,
                        syncId: syncInfo.f_id
                    },
                    success: function (result) {

                        var status = result.status;
                        if (status == "1") {
                            swal({
                                title: "定时任务运行!",
                                text: "",// 内容
                                type: "success",
                                showCloseButton: false, // 展示关闭按钮
                                allowOutsideClick: false,
                                confirmButtonColor: "#1ab394",
                                showConfirmButton: false,
                                timer: 1500
                            });
                        } else {
                            swal({
                                title: "定时任务运行失败!",
                                text: "",// 内容
                                type: "error",
                                showCloseButton: false, // 展示关闭按钮
                                allowOutsideClick: false,
                                confirmButtonColor: "#1ab394",
                                showConfirmButton: false,
                                timer: 1500
                            });
                        }
                    },
                    error: function () {
                        swal({
                            title: "定时任务运行失败!",
                            text: "",// 内容
                            type: "error",
                            showCloseButton: false, // 展示关闭按钮
                            allowOutsideClick: false,
                            confirmButtonColor: "#1ab394",
                            showConfirmButton: false,
                            timer: 1500
                        });
                    }
                })
            } else {//不执行

                $.ajax({
                    type: "post",
                    dataType: "json",
                    async: false,
//                      contentType: 'application/json; charset=UTF-8',
                    url: "${ctx}/quartz/stopTimeTaskSyncInfomation",
                    data: {
                        jobId: jobId,
                        syncId: syncInfo.f_id
                    },
                    success: function (result) {

                        var status = result.status;
                        if (status == "1") {
                            swal({
                                title: "定时任务停止!",
                                text: "",// 内容
                                type: "warning",
                                showCloseButton: false, // 展示关闭按钮
                                allowOutsideClick: false,
                                confirmButtonColor: "#1ab394",
                                showConfirmButton: false,
                                timer: 1500
                            });
                        } else {
                            swal({
                                title: "定时任务停止失败!",
                                text: "",// 内容
                                type: "error",
                                showCloseButton: false, // 展示关闭按钮
                                allowOutsideClick: false,
                                confirmButtonColor: "#1ab394",
                                showConfirmButton: false,
                                timer: 1500
                            });
                        }
                    },
                    error: function () {
                        swal({
                            title: "定时任务停止失败!",
                            text: "",// 内容
                            type: "error",
                            showCloseButton: false, // 展示关闭按钮
                            allowOutsideClick: false,
                            confirmButtonColor: "#1ab394",
                            showConfirmButton: false,
                            timer: 1500
                        });
                    },
                })
            }
        }

        //根据模式id和场景id获取点位信息
        function getSbList (syncId,callback) {
            let resultData = [];
            $.ajax({
                url: "${ctx}/view/basedatamanage/eqmanage/besTimeTaskSync/querySbList",
                type: "post",
                dataType: "json",
                async: false,
                contentType: 'application/json; charset=UTF-8',
                data: JSON.stringify({
                    syncId: syncId,
                }),
                success: function (result) {
                    if (result.status == '0') {
                        resultData = [];
                    } else {
                        resultData = result.list;
                    }
                    callback(resultData);

                },
            })
        }

        //监听行工具事件 修改和删除
        layui.table.on('tool(demoCollectSyncEvent)', function (obj) {

            //获取当前信息的计划id
            f_id = obj.data.f_id;
            //获取当前计划是否执行的值
            f_job_id = obj.data.f_job_id;

            if (obj.event === 'collectDelete') {//删除
                swal({
                    title: "确认删除" + obj.data.f_job_name + "吗?",
                    text: "",// 内容
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#1ab394",
                    confirmButtonText: "确定",
                    closeOnConfirm: false
                }, function () {
                    $.ajax({
                        type: "post",
                        dataType: "json",
                        async: false,
                        contentType: 'application/json; charset=UTF-8',
                        url: "${ctx}/view/basedatamanage/eqmanage/besTimeTaskSync/deleteTimeTaskSync",
                        data: JSON.stringify({
                            data: obj.data
                        }),
                        success: function (result) {
                            var status = result.status;
                            if (status == "1") {
                                swal({
                                    title: "任务删除成功!",
                                    text: "",// 内容
                                    type: "success",
                                    showCloseButton: false, // 展示关闭按钮
                                    allowOutsideClick: false,
                                    confirmButtonColor: "#1ab394",
                                    showConfirmButton: false,
                                    timer: 1500
                                });
                                refreshTable()
                            } else {
                                swal({
                                    title: "任务删除失败!",
                                    text: "",// 内容
                                    type: "error",
                                    showCloseButton: false, // 展示关闭按钮
                                    allowOutsideClick: false,
                                    confirmButtonColor: "#1ab394",
                                    showConfirmButton: false,
                                    timer: 1500
                                });
                            }
                        },
                        error: function () {
                            swal({
                                title: "任务删除失败!",
                                text: "",// 内容
                                type: "error",
                                showCloseButton: false, // 展示关闭按钮
                                allowOutsideClick: false,
                                confirmButtonColor: "#1ab394",
                                showConfirmButton: false,
                                timer: 1500
                            });
                        }
                    })
                });
            } else if (obj.event === 'collectUpdate') {//修改
                //弹出修改模态框
                $('#timeTaskSyncModalEdit').modal('show');

                timeTaskSyncModelType = 'update';
                querySyncInfo(obj.data.f_id);

            }

        })
        function querySyncInfo(syncId){
            $.ajax({
                type: "post",
                url: "${ctx}/view/basedatamanage/eqmanage/besTimeTaskSync/selectTimeTaskSyncInfo",
                dataType: "json",
                async: false,
                data: {
                    syncId: syncId
                },
                success: function (result) {
                    var status = result.status;
                    var syncInfo = result.map;
                    if (status == "1") {
                        $('#timeTaskSyncIdEdit').val(syncInfo.f_id);
                        $('#timeTaskSyncJobNameEdit').val(syncInfo.f_job_name);
                        $('#timeTaskSyncCronEdit').val(syncInfo.f_cron);
                        $('#timeTaskSyncRemarkEdit').val(syncInfo.f_remark);
                        sbList = [];
                        pointId = [];
                        arrNameAndNickName = [];
                        if (syncInfo.sbList.length > 0){
                            syncInfo.sbList.forEach(item =>{
                                sbList.push(item);
                                pointId.push(item.f_point_id)
                                arrNameAndNickName.push(item.f_point_all_name)
                                $('#timeTaskSyncEquipmentNameEdit').val(arrNameAndNickName.join(' \n '));
                                autoTextarea(document.getElementById("timeTaskSyncEquipmentNameEdit"));
                            })
                        } else {
                            $('#timeTaskSyncEquipmentNameEdit').val("");
                            autoTextarea(document.getElementById("timeTaskSyncEquipmentNameEdit"));
                        }

                    } else {
                        swal({
                            title: '查询数据有错！',// 展示的标题
                            type: "error",
                            showCloseButton: false, // 展示关闭按钮
                            allowOutsideClick: false,
                            showConfirmButton: false,
                            timer: 1500
                        });
                    }
                },
                error: function () {
                    swal({
                        title: '查询数据有错！',// 展示的标题
                        type: "error",
                        showCloseButton: false, // 展示关闭按钮
                        allowOutsideClick: false,
                        showConfirmButton: false,
                        timer: 1500
                    });
                }
            })
        }

        // 添加模态框开启时处理事件
        $('#timeTaskSyncLog').on('show.bs.modal', function () {

            getSyncLogPage({}, function (data) {
                showPagingPage('sync_log_page', data);
            });

        });

        // 获取分页页面
        function getSyncLogPage(param, callback){
            if(typeof callback !== 'function'){
                return;
            }

            param = param || {};

            $.ajax({
                url     : _ctx + '/view/basedatamanage/eqmanage/besTimeTaskSync/getSyncLogPage',
                type    : "post",
                data    : param,
                success : function(result) {
                    callback(result);
                },

                error : function(result) {
                    console.log(result)
                }
            });

        }

        // 显示分页
        function showPagingPage(pageId, page){
            if(!page || !pageId){
                return;
            }

            $('#' + pageId).html(page);
        }

        // 添加模态框开启时处理事件
        $('#timeTaskSyncModalAdd').on('show.bs.modal', function () {
            timeTaskSyncModelType = 'add';
            pointId.length = 0;
            arrNameAndNickName.length = 0;
            sbList.length = 0;
            // 清空表单
            $('#timeTaskSyncJobNameAdd').val('');
            $('#timeTaskSyncEquipmentNameAdd').val('');
            $('#timeTaskSyncCronAdd').val('');
            $('#timeTaskSyncRemarkAdd').val('');
        });
        // 添加模态框关闭时处理事件
        $('#timeTaskSyncModalAdd').on('hide.bs.modal', function () {
            timeTaskSyncModelType = '';
            pointId.length = 0;
            arrNameAndNickName.length = 0;
            sbList.length = 0;
            // 清空表单
            $('#timeTaskSyncJobNameAdd').val('');
            $('#timeTaskSyncEquipmentNameAdd').val('');
            $('#timeTaskSyncCronAdd').val('');
            $('#timeTaskSyncRemarkAdd').val('');

            $('#timeTaskSyncTreeModel').modal('hide'); //点位树关闭
            $('#timeTaskSyncCronModal').modal('hide');  //cron生成器关闭

        });


        //修改模态框开启时处理事件
        $('#timeTaskSyncModalEdit').on('show.bs.modal', function () {
            timeTaskSyncModelType = 'update';
            pointId.length = 0;
            arrNameAndNickName.length = 0;
            sbList.length = 0;
            // 清空表单
            $('#timeTaskSyncJobNameEdit').val('');
            $('#timeTaskSyncEquipmentNameEdit').val('');
            $('#timeTaskSyncCronEdit').val('');
            $('#timeTaskSyncRemarkEdit').val('');

            document.getElementById("timeTaskSyncEquipmentNameEdit").style.height = 40 + "px";
        });
        // 修改模态框关闭时处理事件
        $('#timeTaskSyncModalEdit').on('hide.bs.modal', function () {
            timeTaskSyncModelType = '';
            pointId.length = 0;
            arrNameAndNickName.length = 0;
            sbList.length = 0;
            // 清空表单
            $('#timeTaskSyncJobNameEdit').val('');
            $('#timeTaskSyncEquipmentNameEdit').val('');
            $('#timeTaskSyncCronEdit').val('');
            $('#timeTaskSyncRemarkEdit').val('');

            $('#timeTaskSyncTreeModel').modal('hide'); //点位树关闭
            $('#timeTaskSyncCronModal').modal('hide');  //cron生成器关闭
        });


        // 根据条件搜索
        function search()
        {
            var keywords = $('#timeTaskSyncSearchKeywords').val();

            refreshTable(keywords);
        }

        // 根据条件搜索
        function syncLogSearch()
        {
            var f_sync_name = $('#syncLogFSyncName').val();
            var f_point_name = $('#syncLogFPointName').val();
            var f_sync_time = $('#syncLogFSyncTime').val();

            getSyncLogPage({
                f_sync_name:f_sync_name,
                f_point_name:f_point_name,
                f_sync_time:f_sync_time
            },
                function (data) {
                showPagingPage('sync_log_page', data);
            });
        }

        function syncLogReset()
        {
            $('#syncLogFSyncName').val("");
            $('#syncLogFPointName').val("");
            $('#syncLogFSyncTime').val("");

            getSyncLogPage({},
                function (data) {
                    showPagingPage('sync_log_page', data);
                });
        }


        return {
            submitSyncInfo: function () {
                submitSyncInfo();
            },
            submitUpdateSyncInfo: function () {
                submitUpdateSyncInfo();
            },
            cron: function (cron) {
                if (timeTaskSyncModelType == "add"){
                    $("#timeTaskSyncCronAdd").val(cron);
                } else if (timeTaskSyncModelType == "update"){
                    $("#timeTaskSyncCronEdit").val(cron);
                }
            },
            search,
            syncLogSearch,
            syncLogReset,
            pageInit: function()
            {
                getPagingPage();
            }
        }

    })(jQuery, window, document);
    timeTaskSync.pageInit();
</script>