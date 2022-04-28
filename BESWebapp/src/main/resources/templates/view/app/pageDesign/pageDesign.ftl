<#--

 页面设计器
-->
<!doctype html>
<html lang="en">
<head>
    <#--引入公共css/js-->
    <#include "view/app/pageDesign/plugin/design_plugin_system.ftl"/>
<#--<script>-->
<#--var _ctx = '${ctx}';-->
<#--</script>-->

    <#--<script src="${ctx}/static/pageDesign/jquery/jquery-1.9.1.js"></script>-->
    <#--<script src="${ctx}/static/pageDesign/jquery-ui-1.12.1/jquery-ui.min.js"></script>-->
    <#--<link href="${ctx}/static/pageDesign/jquery-ui-1.12.1/jquery-ui.min.css" rel="stylesheet"/>-->
    <link href="${ctx}/static/pageDesign/jquery-contextmenu/2.6.3/jquery.contextMenu.min.css" rel="stylesheet">
    <script src="${ctx}/static/pageDesign/jquery-contextmenu/2.6.3/jquery.contextMenu.min.js"></script>
    <#--<link href="${ctx}/static/layui/css/layui.css" rel="stylesheet">-->
    <#--<script src="${ctx}/static/layui/layui.all.js"></script>-->

        <#--&lt;#&ndash;调色盘&ndash;&gt;-->
    <#--<script src="${ctx}/static/pageDesign/color/spectrum/spectrum.js"></script>-->
    <#--<link href="${ctx}/static/pageDesign/color/spectrum/spectrum.css" rel="stylesheet">-->
    <#--<script src="${ctx}/static/pageDesign/color/spectrum/docs.js"></script>-->

    <#--<link href="${ctx}/static/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">-->
    <#--<script src="${ctx}/static/js/utility/tree.js"></script>&lt;#&ndash;ztree 封装&ndash;&gt;-->
    <#--<script src="${ctx}/static/ztree/js/jquery.ztree.all.js"></script>-->

    <link href="${ctx}/static/pageDesign/module/css/pageDesign.css" rel="stylesheet">
    <link href="${ctx}/static/pageDesign/module/css/common.css" rel="stylesheet">
        <#--低档温控器样式-->
        <link href="${ctx}/static/pageDesign/module/css/lowConditioner.css" rel="stylesheet">
        <#--中档温控器样式-->
        <link href="${ctx}/static/pageDesign/module/css/middleConditioner.css" rel="stylesheet">
        <link href="${ctx}/static/pageDesign/module/css/conditionerMedia.css" rel="stylesheet"/>
<#--snap插件-->
    <script src="${ctx}/static/pageDesign/draw/snap/snap.svg.js"></script>
        <link href="${ctx}/static/pageDesign/module/css/designElement.css" rel="stylesheet"/>
        <link href="${ctx}/static/pageDesign/module/css/enlarge.css" rel="stylesheet"/>
        <link href="${ctx}/static/pageDesign/module/css/electric_curtain.css" rel="stylesheet"/>
</head>
<body class="design_content">
<input type="hidden" id="areaId" value="${areaId!}">
<input type = "hidden" id = "designTextValue" /><#--用来存放页面选中的文本-->
<input type = "hidden" id = "designIdValue" /><#--用来存放页面选中的id-->

<div id="designDiv" style="position:relative">
        <div id="design_area_demo" class="design_area">
            <#--页面上半部分：&ndash;&gt;-->
            <div style="display:none;" id = "topContent">
                <#--空调运行能耗信息-->
                <div id = "yxnh"  style="float:left;margin-left:40px;width: 310px;height: 380px;margin-top: 35px;">

                </div>
                <div  id = "ynzb"  style="float:left;width: 1220px;height: 420px;margin-top:17px;margin-left:40px;">

                </div>
            </div>
      <#--页面下半部分：设备能耗占比，系统COP，其他属性 &ndash;&gt;-->
            <div style="display:none;" id = "bottomContent">
                <div  style="float:left;margin-left:40px;width: 493px;height: 340px;margin-top:30px;">
                    <#--系统COP-->
                    <div id="systemEnergyEfficiencyCopLine" style="width: 100%; height: 100%;"></div>
                </div>
                <div  style="float:left;width: 493px;height: 340px;margin-top:30px;margin-left:40px;">
                   <#--设备能耗占比-->
                    <div id="equipmentEnergyConsumptionRatio" style="width: 100%; height: 100%;">

                    </div>
                </div>
                <div  style="float:left;width: 493px;height: 340px;margin-top:30px;margin-left:40px;">
                    <table id = "anotherProperty" style="height:80%;width:90%;margin-left:20px;">

                    </table>
                </div>

            </div>



            <div id="design_back_div" >
            </div>
        </div>
    <svg id="design_svg" width="100%" height="100%" style="top:0;left:0;">
        <g id="design_shape_container" style="display: none;">
            <path id="design_shape_path_left" d="M291 248L291 383" stroke="#1e609d"
                  style="stroke-dasharray: 4, 3; opacity: 0.5; cursor: move;"></path>
            <rect id="design_shape_rect_w" x="287" y="311.5" width="8" height="8" fill="#4f80ff" shape-id="1594289421024"
                  style="pointer-events: all; cursor: w-resize;" class="rect_w"></rect>
            <path id="design_shape_path_bottom" d="M291 383L405 383" stroke="#1e609d"
                  style="stroke-dasharray: 4, 3; opacity: 0.5; cursor: move;"></path>
            <rect id="design_shape_rect_s" x="344" y="379" width="8" height="8" fill="#4f80ff" shape-id="1594289421024"
                  style="pointer-events: all; cursor: s-resize;" class="rect_s"></rect>
            <path id="design_shape_path_top" d="M291 248L405 248" stroke="#1e609d"
                  style="stroke-dasharray: 4, 3; opacity: 0.5; cursor: n-resize; pointer-events: all;"></path>
            <rect id="design_shape_rect_n" x="344" y="244" width="8" height="8" fill="#4f80ff" shape-id="1594289421024"
                  style="pointer-events: all; cursor: n-resize;" class="rect_n"></rect>
            <path id="design_shape_path_right" d="M405 248L405 383" stroke="#1e609d"
                  style="stroke-dasharray: 4, 3; opacity: 0.5; cursor: e-resize;"></path>
            <rect id="design_shape_rect_e" x="401" y="311.5" width="8" height="8" fill="#4f80ff" shape-id="1594289421024"
                  style="pointer-events: all; cursor: e-resize;" class="rect_e"></rect>
        </g>

    </svg>
</div>


<#--工具栏-->
<#include "view/app/pageDesign/toolbar.ftl"/>
<#--属性页-->
<#include "view/app/pageDesign/designWin/attributeWin.ftl"/>
<#--引入调试弹窗-->
<#include "view/app/pageDesign/designWin/debugWin.ftl"/>

<#--单通道按钮弹窗-->
<#include "view/app/pageDesign/designWin/channelBtn.ftl"/>

<#--场景按钮弹窗-->
<#include "view/app/pageDesign/designWin/sceneBtnWin.ftl"/>

<#--点位置按钮弹窗-->
<#include "view/app/pageDesign/designWin/pointBtn.ftl"/>

<#--流程图关联点位弹窗-->
<#include "view/app/pageDesign/designWin/flowLable.ftl"/>

<#--温控器设置列表弹窗-->
<#include "view/app/pageDesign/designWin/tempList.ftl"/>

<#--温控器设置图标弹窗-->
<#include "view/app/pageDesign/designWin/tempIcon.ftl"/>

<#--图片、背景图片弹窗-->
<#include "view/app/pageDesign/designWin/imgWin.ftl">

<#--背景区域弹窗-->
<#include "view/app/pageDesign/designWin/areaWin.ftl">

<#--标签弹窗-->
<#include "view/app/pageDesign/designWin/addLabel.ftl">
<#--文本框弹窗-->
<#include "view/app/pageDesign/designWin/addTextbox.ftl">

<#--字体设置弹窗-->
<#include "view/app/pageDesign/designWin/textSetting.ftl"/>
<#--放大缩小页面弹窗-->
<#include "view/app/pageDesign/designWin/more/enlargeWin.ftl"/>
<#--模板页面-->
<#include "view/app/pageDesign/designWin/template.ftl"/>
<#--温控器选择页面-->
<#include "view/app/pageDesign/designWin/selectTempWin.ftl"/>
<#--引入树相关弹窗-->
<#include "view/app/pageDesign/designWin/treeWin.ftl">
<#--导入cad弹窗-->
<#include "view/app/pageDesign/designWin/cadWin.ftl">
<#--添加表格弹窗-->
<#include "view/app/pageDesign/designWin/addTable.ftl">
<#--低档温控器弹窗-->
<#include "view/app/pageDesign/designWin/lowConditionerWin.ftl">
<#--低档温控器界面-->
<#include "view/app/pageDesign/showWin/showConditioner.ftl">
<#--系统能效按钮弹窗-->
<#include "view/strongAndWeakElectricityIntegration/energyEfficiencyBtn.ftl"/><#--系统能效按钮页面-->

<#--电动窗帘弹窗-->
<#include "view/app/pageDesign/designWin/more/curtainWin.ftl">

<#--&lt;#&ndash;echart插件&ndash;&gt;-->
<#--<script src="${ctx}/static/js/plugins/echarts/echarts.min.js"></script>-->
<#--<script src="${ctx}/static/js/plugins/echarts/chart/issp-bar.js"></script>-->
<#--<script src="${ctx}/static/js/plugins/echarts/chart/issp-line.js"></script>-->
<#--<script src="${ctx}/static/js/plugins/echarts/chart/issp-pie.js"></script>-->
<#--<script src="${ctx}/static/js/plugins/echarts/chart/issp-gauge.js"></script>-->
<#--<script src="${ctx}/static/js/plugins/echarts/chart/issp-tempgauge.js"></script>-->

<script src="${ctx}/static/pageDesign/module/pageDesign.js" type="text/javascript"></script>
<script src="${ctx}/static/pageDesign/module/commonCheck.js" type="text/javascript"></script>
<script src="${ctx}/static/pageDesign/module/design/pointTree.js"></script>
<#--多选节点树-->
<script src="${ctx}/static/pageDesign/module/design/checkboxPointTree.js"></script>
<script src="${ctx}/static/pageDesign/module/design/debugBtn.js" type="text/javascript"></script>
<script src="${ctx}/static/pageDesign/module/design/enlargeText.js" type="text/javascript"></script>
<script src="${ctx}/static/pageDesign/module/design/textSetting.js" type="text/javascript"></script>
<script src="${ctx}/static/pageDesign/module/design/copyModule.js" type="text/javascript"></script>
<script src="${ctx}/static/pageDesign/module/design/reduceText.js" type="text/javascript"></script>
<script src="${ctx}/static/pageDesign/module/design/channelBtn.js" type="text/javascript"></script>
<script src="${ctx}/static/pageDesign/module/design/pointBtn.js" type="text/javascript"></script>
<script src="${ctx}/static/pageDesign/module/design/flowLable.js" type="text/javascript"></script>
<script src="${ctx}/static/js/strongAndWeakElectricityIntegration/designEnergyEfficiencyEvent.js" type="text/javascript"></script><#--系统能效-->
<script src="${ctx}/static/pageDesign/module/design/sceneBtn.js" type="text/javascript"></script>
<script src="${ctx}/static/pageDesign/module/design/addImg.js" type="text/javascript"></script>
<script src="${ctx}/static/pageDesign/module/design/backImg.js" type="text/javascript"></script>
<script src="${ctx}/static/pageDesign/module/design/backArea.js" type="text/javascript"></script>
<script src="${ctx}/static/pageDesign/module/design/backColor.js" type="text/javascript"></script>
<script src="${ctx}/static/pageDesign/module/design/addLabel.js" type="text/javascript"></script>
<script src="${ctx}/static/pageDesign/module/design/addTextbox.js" type="text/javascript"></script>
<script src="${ctx}/static/pageDesign/module/design/controllerTemp.js" type="text/javascript"></script>
<script src="${ctx}/static/pageDesign/module/design/tempList.js" type="text/javascript"></script>
<script src="${ctx}/static/pageDesign/module/design/tempIcon.js" type="text/javascript"></script>
<script src="${ctx}/static/pageDesign/module/design/svgShape.js" type="text/javascript"></script>
<script src="${ctx}/static/pageDesign/module/design/showGraph.js" type="text/javascript"></script>
<script src="${ctx}/static/pageDesign/module/design/importCad.js" type="text/javascript"></script>
<script src="${ctx}/static/pageDesign/module/design/attributeWin.js" type="text/javascript"></script>
<script src="${ctx}/static/pageDesign/module/design/addTable.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/static/js/strongAndWeakElectricityIntegration/addEnergyEfficiency.js"></script>
<script type="text/javascript" src="${ctx}/static/js/strongAndWeakElectricityIntegration/energyEfficiencyEvent.js"></script><#--系统能效解析配置数据-->
<#--textboxEvent.js中的方法，解析config配置数据-->
<script type="text/javascript" src="${ctx}/static/pageDesign/module/show/textboxEvent.js"></script>
<#--引用labelEvent.js中的方法，解析config配置数据-->
<script type="text/javascript" src="${ctx}/static/pageDesign/module/show/labelEvent.js"></script>
<#--引用imgEvent.js中的方法，解析config配置数据-->
<script type="text/javascript" src="${ctx}/static/pageDesign/module/show/imgEvent.js"></script>
<#--复制粘贴js-->
<script type="text/javascript" src="${ctx}/static/pageDesign/module/design/copyPaste.js"></script>
<#--低档温控器js-->
<script type="text/javascript" src="${ctx}/static/pageDesign/module/design/lowConditioner.js"></script>
<#--放大缩小页面js-->
<script type="text/javascript" src="${ctx}/static/pageDesign/module/design/more/enlargePage.js"></script>
<#--电动窗帘js-->
<script src="${ctx}/static/pageDesign/module/design/more/addCurtain.js" type="text/javascript"></script>
</body>
</html>
