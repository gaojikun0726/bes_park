<html>
<head>
    <#--<link href="${ctx}/static/css/nav.css" rel="stylesheet">-->
    <#--<link href="${ctx}/static/css/style.css" rel="stylesheet">-->
    <#--<link href="${ctx}/static/css/skinColour_blue.css" rel="stylesheet" id="skinColour"><!--换肤&ndash;&gt;-->
<#--<#include "view/app/pageDesign/plugin/display_plugin_system.ftl"/>-->
    <#--<script src="${ctx}/static/js/jquery/jquery-2.1.1.min.js"></script>-->
    <script>
        var _ctx = '${ctx}';
    </script>
        <script src="${ctx}/static/layui/lay/modules/layer.js" type="text/javascript"></script>
        <link rel="stylesheet" href="${ctx}/static/pageDesign/module/css/designFrame.css">
</head>
<body id="designFrameBody" style="margin:0!important;">
<input type="hidden" id="areaId" value="${areaId!}">
<!-- 信息表格模块 -->
<div class="information-model">

			<span class="Subtitle">
				<i class="fa fa-user" aria-hidden="true"></i>&nbsp;页面配置>>>
			</span>

    <!-- 按钮 -->
    <button class="btn btn-primary design_frame_btn" id="design_mode" onclick="DesignFrame.designMode()">进入设计模式</button>
    <button class="btn btn-primary design_frame_btn" id="run_mode" onclick="DesignFrame.runMode()" style="display: none">进入运行模式</button>
    <#--<button class="btn btn-primary design_frame_btn" id="restoreBtn"  style="display: none;">还原缩放</button>-->
    <#--onclick="restoreTransform()"-->
</div>
<#--<div class="design_frame_title">-->

<#--</div>-->

<iframe id="design_frame_one" src="" frameborder="0" scrolling="no" width="100%" style="height:calc(100% - 35px)"></iframe>

<script src="${ctx}/static/pageDesign/module/saveDesign.js" type="text/javascript"></script>
<script>
    var DesignFrame = {};
    //进入设计模式
    DesignFrame.designMode = function(){
        var areaId = $("#areaId").val();
        $("#design_frame_one").prop("src",_ctx + "/view/pageDesign/view?areaId="+areaId);
        $("#design_mode").hide();
        $("#run_mode").show();
        $("#restoreBtn").hide();
    }
    //进入运行模式
    DesignFrame.runMode = function(){
        DesignFrame.saveDesignContent();
        //ajax请求完毕时执行
        // DesignFrame.initPage();
    }

    /**
     * 加载页面内容
     */
    DesignFrame.initPage = function(){
        var areaId = $("#areaId").val();
        $("#design_frame_one").prop("src",_ctx + "/view/pageDesign/displayView?areaId="+areaId);
        $("#design_mode").show();
        $("#run_mode").hide();
    }
    /**
     * 切换之前是否保存页面
     */
    DesignFrame.saveDesignContent = function(){
        layer.confirm('是否保存当前页面?', {title:' 保存提示'}, function(index){
            SaveDesign.saveContent($("#areaId").val());
            $.when(SaveDesign.method).done(function () {
                //要执行的操作
                DesignFrame.initPage();
            });
            layer.close(index);
        },function(index){
            layer.close(index);
            DesignFrame.initPage();

        });
    }

    $(function(){
        // DesignFrame.initPage();
    });
</script>
</body>

</html>