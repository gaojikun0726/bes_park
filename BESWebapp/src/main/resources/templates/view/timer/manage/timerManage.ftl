<html>
<head>
    <script>
        var _ctx = '${ctx}';
    </script>
    <link rel="stylesheet" href="${ctx}/static/platform/css/common.css">
    <link rel="stylesheet" href="${ctx}/static/platform/css/equipmentList.css">
    <style>
        .partOneInput{
            width:70%;
            display: inline-block;
        }
        .partTwoInput{
            width: 28%;
            display: inline-block;
            text-align: center;
        }

        #cronExpressionModal .nav > li.active{
            border-left:none;
        }
    </style>
</head>
<body>
<div class="timer_manage_content_div">
    <!-- 信息表格模块 -->
    <div class="information-model">

			<span class="Subtitle">
				<i class="fa fa-user" aria-hidden="true"></i>&nbsp;定时任务管理>>>
			</span>

    </div>
    <div class="layui_content_div">
        <form class="layui-form" id="timerManageQueryForm">
            <div class="layui-form-item">
                <div class="query_item">
                    <label class="layui-form-label">任务名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="task_name" required  placeholder="请输入任务名称" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="query_item">
                    <label class="layui-form-label">任务类型</label>
                    <div class="layui-input-block">
                        <select name="task_type">
                        </select>
                    </div>
                </div>
                <div class="query_item">
                    <label class="layui-form-label">任务状态</label>
                    <div class="layui-input-block">
                        <select name="task_status">
                        </select>
                    </div>
                </div>

                <div class="queryBtnDiv">
                    <button class="layui-btn" type="button" onclick="TimerManage.search()">查询</button>
                    <button class="layui-btn" type="reset" onclick="TimerManage.reset()">重置</button>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="rightDiv">
                <#--onclick="TimerManage.add()"-->
                    <button data-toggle="modal"  href="#addTaskManageModal" class="layui-btn" type="button">新增</button>
                    <button onclick="TimerManage.edit()" class="layui-btn" type="button">修改</button>
                    <button onclick="TimerManage.delete()" class="layui-btn" type="button">删除</button>
                </div>
            </div>
        </form>
        <table id="timer_manage_table" lay-filter="timer_manage_table"></table>
    </div>
</div>
<!----新增或修改弹窗--->
<div class="modal fade" id="addTaskManageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button  aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 定时任务</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="addTaskManageForm" name="addTaskManageForm" class="form-horizontal">
                    <input type="hidden" id="taskId">
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="taskName">任务名称： <span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="taskName" name="taskName"  required class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="taskType">任务类型： <span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                        <select id="taskType" name="taskType" class="form-control"></select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="invokeTarget">调用目标字符串： <span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="invokeTarget" name="invokeTarget"  required class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="cronExpression">cron表达式： <span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <#--partOneInput-->
                            <input type="text" id="cronExpression" name="cronExpression"   required class="form-control">
                            <#--<button id="createCronBtn" type="button" data-toggle="modal"  href="#cronExpressionModal" class="layui-btn partTwoInput">生成表达式</button>-->
                        </div>
                    </div>
                    <#--<div class="form-group">-->
                        <#--<label class="col-sm-3 control-label" for="paramCode"></label>-->
                        <#--<div class="col-sm-8">-->
                            <#--<input type="text" id="paramCode" name="paramCode" readonly  required class="form-control" placeholder="生成表达式">-->
                        <#--</div>-->
                    <#--</div>-->
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="errorStrategy">错误策略：<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <select id="errorStrategy" name="errorStrategy" class="form-control"></select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="concurrent">是否并发： <span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <select id="concurrent" name="concurrent" class="form-control"></select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="taskStatus">任务状态： <span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <select id="taskStatus" name="taskStatus" class="form-control"></select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="taskRemark">备注说明： </label>
                        <div class="col-sm-8">
                            <input type="text" id="taskRemark" name="taskRemark"   required class="form-control">
                        </div>
                    </div>
                    <div class="form-group m-t-sm">
                        <div class="col-sm-6 col-sm-push-4 display_flex">
                            <button class="btn btn-md btn-primary " type="submit">
                                <strong>确定</strong>
                            </button>
                            <button type="button" class="btn btn-white m-l-sm" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!----cron表达式弹窗--待完成 --->
<div class="modal fade" id="cronExpressionModal" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content"  style="width:1185px;left: -288px;top:100px;" >
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title">Cron表达式生成器</h4>
            </div>
            <div class="modal-body" style="height:600px;">
                <ul class="nav nav-pills" id="myTab">
                    <li class="active"><a href="#second">秒</a></li>
                    <li><a href="#minute">分钟</a></li>
                    <li><a href="#hour">小时</a></li>
                    <li><a href="#day">日</a></li>
                    <li><a href="#month">月</a></li>
                    <li><a href="#week">周</a></li>
                    <li><a href="#year">年</a></li>
                </ul>

                <div class="tab-content">
                    <div class="tab-pane active" id="second">秒</div>
                    <div class="tab-pane" id="minute">分钟</div>
                    <div class="tab-pane" id="hour">小时</div>
                    <div class="tab-pane" id="day">日</div>
                    <div class="tab-pane" id="month">月</div>
                    <div class="tab-pane" id="week">周</div>
                    <div class="tab-pane" id="year">年</div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${ctx}/static/dict/dictCommon.js"></script>
<script type="text/javascript" src="${ctx}/static/timer/manage/timerManage.js"></script>
<script>
    // $(function () {
    //     $('#myTab a:first').tab('show');
    // });
    // $('#myTab a').click(function (e) {
    //     e.preventDefault();
    //     $(this).tab('show');
    // })
</script>
</body>

</html>
