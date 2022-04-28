<#--

 页面设计展示
-->
<!doctype html>
<html lang="en">
<head>
        <#include "view/app/pageDesign/plugin/display_plugin_system.ftl"/>
   <link href="${ctx}/static/pageDesign/module/css/common.css" rel="stylesheet">
    <link href="${ctx}/static/pageDesign/module/css/displayPage.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/static/pageDesign/libgif/libgif.js"></script>
    <script type="text/javascript" src="${ctx}/static/pageDesign/libgif/rubbable.js"></script>
    <link href="${ctx}/static/pageDesign/module/css/designElement.css" rel="stylesheet"/>
    <link href="${ctx}/static/pageDesign/module/css/lowConditioner.css" rel="stylesheet"/>
    <link href="${ctx}/static/pageDesign/module/css/middleConditioner.css" rel="stylesheet"/>
    <link href="${ctx}/static/pageDesign/module/css/conditionerMedia.css" rel="stylesheet"/>
    <link href="${ctx}/static/pageDesign/module/css/legend.css" rel="stylesheet"/>
    <link href="${ctx}/static/pageDesign/module/css/electric_curtain.css" rel="stylesheet"/>
<#--snap插件-->
    <script src="${ctx}/static/pageDesign/draw/snap/snap.svg.js"></script>

</head>
<body class="design_content">
<input type="hidden" id="areaId" value="${areaId!}">
   <div id="designDiv" style="position:relative">
     <div id="design_area_demo" class="design_area">

         <#--页面上半部分：&ndash;&gt;-->
         <div style="display:none;" id = "topContent">
             <#--空调运行能耗信息-->
             <div id = "yxnh"  style="float:left;margin-left:40px;width: 310px;height: 380px;margin-top: 35px;">

             </div>
             <div  id = "ynzb" style="float:left;width: 1220px;height: 420px;margin-top:17px;margin-left:40px;">


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


     </div>
       <svg id="design_svg" width="100%" height="100%" style="top:0;left:0;">

       </svg>
    <#include "view/app/pageDesign/designWin/more/legendWin.ftl"/>
</div>

<#include "view/app/pageDesign/showWin/displayWin.ftl"/>
<#-- 温控器设置弹窗-->
<#include "view/app/pageDesign/designWin/systemSetting.ftl">
<#--低档温控器界面-->
<#include "view/app/pageDesign/showWin/showConditioner.ftl">
<#--窗帘弹窗-->
<#include "view/app/pageDesign/showWin/showCurtain.ftl">

<script type="text/javascript" src="${ctx}/static/pageDesign/module/design/more/legend.js"></script>

   <script type="text/javascript">
       if ('getContext' in document.createElement('canvas')) {
           HTMLImageElement.prototype.play = function() {
               if (this.storeCanvas) {
                   // 移除存储的canvas
                   this.storeCanvas.parentElement.removeChild(this.storeCanvas);
                   this.storeCanvas = null;
                   // 透明度还原
                   this.style.opacity = '1';
               }
               if (this.storeUrl) {
                   this.src = this.storeUrl;
               }
           };
           HTMLImageElement.prototype.stop = function() {
               var canvas = document.createElement('canvas');
               // 尺寸
               var width = this.width, height = this.height;
               // var w = $(this).css('width');
               // if (width && height) {
               // 存储之前的地址
               if (!this.storeUrl) {
                   this.storeUrl = this.src;
               }
               // canvas大小
               canvas.width = width;
               canvas.height = height;
               // 绘制图片帧（第一帧）
               canvas.getContext('2d').drawImage(this, 0, 0, width, height);
               // canvas.getContext('2d').drawImage(this,0, 0, width,height,0,0,width,height);
               // 重置当前图片
               // try {
               //     this.src = canvas.toDataURL("image/gif");
               // } catch(e) {
                   // 跨域
                   // this.removeAttribute('src');
                   // 载入canvas元素
               $(canvas).attr("style",$(this).attr("style")).css("opacity","1");
               $(canvas).addClass("design_img");
                   // canvas.style.position = 'absolute';
                   // 前面插入图片
                   this.parentElement.insertBefore(canvas, this);
                   // 隐藏原图
                   this.style.opacity = '0';
                   // 存储canvas
                   this.storeCanvas = canvas;
               // }
               // }
           };
       }


   </script>

<#--echart插件-->
<script src="${ctx}/static/js/plugins/echarts/echarts.min.js"></script>
<script src="${ctx}/static/js/plugins/echarts/chart/issp-bar.js"></script>
<script src="${ctx}/static/js/plugins/echarts/chart/issp-line.js"></script>
<script src="${ctx}/static/js/plugins/echarts/chart/issp-pie.js"></script>
<script src="${ctx}/static/js/plugins/echarts/chart/issp-gauge.js"></script>
<script src="${ctx}/static/js/plugins/echarts/chart/issp-tempgauge.js"></script>

<script type="text/javascript" src="${ctx}/static/pageDesign/module/show/commonMethod.js"></script>
   <script type="text/javascript" src="${ctx}/static/pageDesign/module/displayPage.js"></script>
<script type="text/javascript" src="${ctx}/static/pageDesign/module/show/pointEvent.js"></script><#--点位置按钮事件-->
   <script type="text/javascript" src="${ctx}/static/pageDesign/module/show/channelEvent.js"></script><#--单通道按钮事件-->
 <script type="text/javascript" src="${ctx}/static/pageDesign/module/show/sceneEvent.js"></script><#--多场景按钮事件-->
<script type="text/javascript" src="${ctx}/static/pageDesign/module/show/debugEvent.js"></script>
<script type="text/javascript" src="${ctx}/static/pageDesign/module/show/flowEvent.js"></script><#--流程图事件-->
<script type="text/javascript" src="${ctx}/static/js/strongAndWeakElectricityIntegration/energyEfficiencyEvent.js"></script><#--系统能效解析配置数据-->
<script type="text/javascript" src="${ctx}/static/pageDesign/module/show/tempIconEvent.js"></script><#--温控器图标事件-->
<script type="text/javascript" src="${ctx}/static/pageDesign/module/show/tempListEvent.js"></script><#--温控器列表事件-->

   <script type="text/javascript" src="${ctx}/static/pageDesign/module/show/textboxEvent.js"></script>
   <script type="text/javascript" src="${ctx}/static/pageDesign/module/show/labelEvent.js"></script>
   <script type="text/javascript" src="${ctx}/static/pageDesign/module/show/imgEvent.js"></script>

<script src="${ctx}/static/pageDesign/module/design/showGraph.js" type="text/javascript"></script>
<#--<script src="${ctx}/static/pageDesign/module/show/transform.js" type="text/javascript"></script>-->
<#--下位机推送数据的订阅事件--改为使用父页面的此文件-->
<#--<script src="${ctx}/static/js/pubsub.js"></script>-->
<script src="${ctx}/static/pageDesign/module/design/realTimeData.js"></script>
<script src="${ctx}/static/pageDesign/module/show/showConditioner.js"></script>
<script src="${ctx}/static/pageDesign/module/show/showMiddleCondtioner.js"></script>
<#--低档温控器实时回调方法-->
<script src="${ctx}/static/pageDesign/module/show/lowConditionerRefresh.js"></script>
<script src="${ctx}/static/pageDesign/module/show/middleConditionerRefresh.js"></script>
<#--窗帘-->
<script src="${ctx}/static/pageDesign/module/show/more/curtainEvent.js"></script>
</body>
</html>
