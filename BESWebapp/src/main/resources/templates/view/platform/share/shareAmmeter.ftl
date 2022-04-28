<html>
<head>
    <script>
        var _ctx = '${ctx}';
    </script>
    <link rel="stylesheet" href="${ctx}/static/platform/css/common.css">
    <link rel="stylesheet" href="${ctx}/static/platform/css/equipmentList.css">
</head>
<body>
<div class="share_ammeter_content_div">
    <!-- 信息表格模块 -->
    <div class="information-model">

			<span class="Subtitle">
				<i class="fa fa-user" aria-hidden="true"></i>&nbsp;公摊仪表列表>>>
			</span>

    <#--<!-- 增加按钮 &ndash;&gt;-->
    <#--<a href="javascript:void(-1);" class="btn btn-primary toLeft">-->
    <#--<i class="fa fa-plus" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;增加-->
    <#--</a>-->

    </div>
    <div class="layui_content_div">
        <form class="layui-form">
            <div class="layui-form-item">
                <div class="query_item">
                    <label class="layui-form-label">仪表名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="share_ammeter_name" required  placeholder="请输入仪表名称" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="query_item">
                    <label class="layui-form-label">仪表类型</label>
                    <div class="layui-input-block">
                        <select name="share_ammeter_type"></select>
                    </div>
                </div>
                <#--<div class="query_item">-->
                    <#--<label class="layui-form-label">是否是公摊仪表</label>-->
                    <#--<div class="layui-input-block">-->
                        <#--<select name="meter_area">-->
                            <#--<option value="">全部</option>-->
                            <#--<option value="0">普通仪表</option>-->
                            <#--<option value="1">公摊仪表</option>-->
                        <#--</select>-->
                    <#--</div>-->
                <#--</div>-->
                <div class="query_item">
                    <label class="layui-form-label">仪表归属范围</label>
                    <div class="layui-input-block">
                        <select name="share_meter_area">
                            <option value="">全部</option>
                            <option value="0">普通仪表</option>
                            <option value="1">公摊仪表</option>
                        </select>
                    </div>
                </div>
                <div class="queryBtnDiv">
                    <button class="layui-btn" type="button" onclick="ShareAmmeter.search()">查询</button>
                    <button class="layui-btn" type="reset" onclick="ShareAmmeter.reset()">重置</button>
                    <button class="layui-btn" type="button" onclick="ShareAmmeter.shareConfig()">设置公摊仪表</button>
                    <button class="layui-btn" type="button" onclick="ShareAmmeter.plainConfig()">设置普通仪表</button>
                    <#--<button class="layui-btn" type="button" onclick="ShareAmmeter.edit()">修改</button>-->
                    <#--<button class="layui-btn" type="button" onclick="ShareAmmeter.delete()">删除</button>-->
                </div>
            </div>
        </form>
        <table id="share_ammeter_table" lay-filter="share_ammeter_table"></table>
    </div>
</div>

<script type="text/javascript" src="${ctx}/static/platform/js/share/shareAmmeter.js"></script>
</body>

</html>
