<html>
<head>

    <!--jstree-->
    <script src="${ctx}/static/pageDesign/jstree/jstree.js"></script>
    <link rel="stylesheet" href="${ctx}/static/pageDesign/jstree/themes/default/style.css">
    <link rel="stylesheet" href="${ctx}/static/pageDesign/module/css/common.css">
    <link rel="stylesheet" href="${ctx}/static/pageDesign/module/css/designArea.css">
    <script>
        var _ctx = '${ctx}';
    </script>
</head>
<body>
<div class="design_area_tree_div leftarea">
    <!-- 信息表格模块 -->
    <div class="information-model design_area_nav">

			<span class="Subtitle">
				<i class="fa fa-user" aria-hidden="true"></i>&nbsp;楼宇自控>>>
			</span>
        <div class="area_control_div">
            <img width="26px" class="area_control show_area" onclick="DesignArea.showArea()" src="${ctx}/static/pageDesign/icon/navigation/show_area4.png">
            <img width="26px" class="area_control hide_area" onclick="DesignArea.hideArea()" src="${ctx}/static/pageDesign/icon/navigation/hide_area3.png">
        </div>

    </div>
    <div id="areaTree"></div>
</div>
<div class="design_area_right_div">
    <#--<iframe id="design_area_frame" src="" frameborder="0" scrolling="no" width="100%" style="height:calc(100% - 33px)"></iframe>-->
    <#include "view/app/pageDesign/designFrame.ftl"/>
</div>

<#include "view/app/pageDesign/designAreaWin.ftl"/>
<script type="text/javascript" src="${ctx}/static/pageDesign/module/designArea.js"></script>
<script>
</script>
</body>

</html>
