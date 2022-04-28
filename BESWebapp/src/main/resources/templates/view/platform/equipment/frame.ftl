<html>
<head>
<#--通过iframe嵌套解决单页应用id重复的问题，可以自定义样式-->
    <script>
        var _ctx = '${ctx}';
    </script>
</head>
<body>
<div class="information-model">

			<span class="Subtitle">
				<i class="fa fa-user" aria-hidden="true"></i>&nbsp;设备列表>>>
			</span>

    <!-- 按钮 -->
    <button class="btn btn-primary design_frame_btn" id="design_mode" onclick="DesignFrame.designMode()">进入设计模式</button>
    <button class="btn btn-primary design_frame_btn" id="run_mode" onclick="DesignFrame.runMode()" style="display: none">进入运行模式</button>
</div>
<#--<div class="leftarea information_left">-->
    <#--<!-- 信息表格模块 &ndash;&gt;-->
    <#--<div class="information-model">-->

			<#--<span class="Subtitle">-->
				<#--<i class="fa fa-user" aria-hidden="true"></i>&nbsp;设备列表>>>-->
			<#--</span>-->
    <#--</div>-->
<#--</div>-->
<iframe src="${ctx}/equipmentList/view" frameborder="0" scrolling="no" width="100%" style="height:calc(100% - 33px)"></iframe>
</body>

</html>
