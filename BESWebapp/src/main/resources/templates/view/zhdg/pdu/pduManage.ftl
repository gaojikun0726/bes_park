<html>
<head>
    <script>
        var _ctx = '${ctx}';
    </script>
    <link rel="stylesheet" href="${ctx}/static/platform/css/common.css">
    <link rel="stylesheet" href="${ctx}/static/platform/css/equipmentList.css">
    <style>
        .pdu_online{
            color:#00FF00;
        }
        .pdu_offline{
            color:#C0C0C0;
        }
        .switch_on{
            color:#0d8ddb;
        }
    </style>
    <#--<link rel="stylesheet" href="${ctx}/static/css/bootstrap/bootstrap-switch.min.css">-->
</head>
<body>
<div class="pdu_manage_content_div">
    <!-- 信息表格模块 -->
    <div class="information-model">

			<span class="Subtitle">
				<i class="fa fa-user" aria-hidden="true"></i>&nbsp;PDU状态管理>>>
			</span>

    </div>
    <div class="layui_content_div">
        <#--<table id="pdu_manage_table" lay-filter="pdu_manage_table"></table>-->

            <#--    background: #f2f2f2;-->
        <table id="pdu_manage_table" class="table table-hover table-bordered"></table>
    </div>
</div>

<#--<script type="text/javascript" src="${ctx}/static/js/Bootstrap/bootstrap-switch.js"></script>-->
<script type="text/javascript" src="${ctx}/static/dict/dictCommon.js"></script>
<script type="text/javascript" src="${ctx}/static/zhdg/pdu/pduManage.js"></script>
<script type="text/javascript" src="${ctx}/static/zhdg/pdu/websocket.js"></script>
</body>

</html>
