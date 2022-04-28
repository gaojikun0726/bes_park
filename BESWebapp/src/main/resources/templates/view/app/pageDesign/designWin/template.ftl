<#--
 页面设计器
-->
<!doctype html>
<html lang="en">
<head>
    <#--引入公共css/js-->
    <#include "view/app/pageDesign/plugin/design_plugin_system.ftl"/>
    <link href="${ctx}/static/pageDesign/jquery-contextmenu/2.6.3/jquery.contextMenu.min.css" rel="stylesheet">
    <script src="${ctx}/static/pageDesign/jquery-contextmenu/2.6.3/jquery.contextMenu.min.js"></script>

    <#--调色盘-->
    <script src="${ctx}/static/pageDesign/color/spectrum/spectrum.js"></script>
    <link href="${ctx}/static/pageDesign/color/spectrum/spectrum.css" rel="stylesheet">
    <script src="${ctx}/static/pageDesign/color/spectrum/docs.js"></script>

    <link href="${ctx}/static/pageDesign/module/css/pageDesign.css" rel="stylesheet">
    <link href="${ctx}/static/pageDesign/module/css/common.css" rel="stylesheet">

    <#--snap插件-->
    <script src="${ctx}/static/pageDesign/draw/snap/snap.svg.js"></script>
    <link href="${ctx}/static/pageDesign/module/css/designElement.css" rel="stylesheet"/>
</head>
<body class="design_content">
<input type="hidden" id="moduleAreaId" value="6x15b500-7aa4-409e-952c-494c19efff0d">
<div id="designModuleDiv" style="display:none;">
    <div id="designModule_back_div">
        <link href="${ctx}/static/pageDesign/module/css/designElement.css" rel="stylesheet"/>

        <style>

        </style>

        <input type = "hidden" value = "" id = "symbolChildPage">
        <div id="designModuleDiv" style="position:relative">

            <div id="designModule_area_demo" class="design_area">
            </div>

        </div>


        <#--工具栏-->
        <#include "view/app/pageDesign/moduleToolbar.ftl"/>

        <div id ="designModule_win_btn_div" class="design_win_btn_div" style ="position: absolute; bottom: 21px;left: 38%;">
            <button class="btn btn-md btn-primary" type="button" onclick="CopyModuleLabel.confirmBtn(0)"><strong>保存</strong></button>
            <button class="btn btn-md btn-primary" type="button" onclick="CopyModuleLabel.copyModuleBtn()"><strong>复制</strong></button>
            <button class="btn btn-white m-l-sm" type="button" onclick="CopyModuleLabel.closeWin()">关闭</button>
        </div>


    </div>
</div>


</body>
</html>