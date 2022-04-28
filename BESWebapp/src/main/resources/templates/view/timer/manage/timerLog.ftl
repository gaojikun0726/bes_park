<html>
<head>
    <script>
        var _ctx = '${ctx}';
    </script>
    <link rel="stylesheet" href="${ctx}/static/platform/css/common.css">
    <link rel="stylesheet" href="${ctx}/static/platform/css/equipmentList.css">
    <style>
        .query_item.long{
            width: 24%;
        }

    </style>
</head>
<body>
<div class="timer_log_content_div">
    <!-- 信息表格模块 -->
    <div class="information-model">

			<span class="Subtitle">
				<i class="fa fa-user" aria-hidden="true"></i>&nbsp;定时任务日志管理>>>
			</span>

    </div>
    <div class="layui_content_div">
        <form class="layui-form" id="timerLogQueryForm">
            <div class="layui-form-item">
                <div class="query_item">
                    <label class="layui-form-label">任务名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="task_name_log" required  placeholder="请输入任务名称" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="query_item">
                    <label class="layui-form-label">任务类型</label>
                    <div class="layui-input-block">
                        <select name="task_type_log">
                        </select>
                    </div>
                </div>
                <div class="query_item">
                    <label class="layui-form-label">执行状态</label>
                    <div class="layui-input-block">
                        <select name="task_status_log">
                        </select>
                    </div>
                </div>
                <div class="query_item long">
                    <label class="layui-form-label">执行时间</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input" name="create_time_log" id="create_time_log" placeholder="请选择">
                    </div>
                </div>

                <div class="queryBtnDiv">
                    <button class="layui-btn" type="button" onclick="TimerLog.search()">查询</button>
                    <button class="layui-btn" type="reset" onclick="TimerLog.reset()">重置</button>
                </div>
            </div>

        </form>
        <table id="timer_log_table" lay-filter="timer_log_table"></table>
    </div>
</div>


<script type="text/javascript" src="${ctx}/static/dict/dictCommon.js"></script>
<script type="text/javascript" src="${ctx}/static/timer/manage/timerLog.js"></script>
</body>

</html>
