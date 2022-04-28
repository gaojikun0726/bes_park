<html>
<head>
	<#--<script src="${ctx}/static/js/jquery/jquery-2.1.1.min.js"></script>-->


	<script src="${ctx}/static/pageDesign/jstree/jstree.js"></script>
	<link rel="stylesheet" href="${ctx}/static/pageDesign/jstree/themes/default/style.css">
	<link rel="stylesheet" href="${ctx}/static/pageDesign/module/css/common.css">

	<script>
		var _ctx = '${ctx}';
	</script>
	<style type="text/css">

	</style>
</head>
<body>
<div style="height: 100%;">
	<#--<iframe id="design_area_frame" src="" frameborder="0" scrolling="no" width="100%" style="height:calc(100% - 33px)"></iframe>-->
<#--	<#include "view/app/pageDesign/designFrame.ftl"/>-->
	<#include "view/strongAndWeakElectricityIntegration/designFlowFrame.ftl"/>
</div>

<#--<#include "view/app/pageDesign/designAreaWin.ftl"/>-->
<script type="text/javascript" src="${ctx}/static/js/strongAndWeakElectricityIntegration/designFlowArea.js"></script>
<script type="text/javascript" src="${ctx}/static/js/strongAndWeakElectricityIntegration/addFlowType.js"></script>
<script>
</script>
</body>

</html>
