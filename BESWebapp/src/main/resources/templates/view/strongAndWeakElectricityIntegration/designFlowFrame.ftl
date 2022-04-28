<html>
<head>

    <script>
        var _ctx = '${ctx}';
    </script>
    <style>
        .design_frame_title{
            height:30px;
        }
        .design_frame_btn{
            width:120px;
            margin-left:25px;
            border-radius: 15px;
        }
        /*.information-model {*/
           /*height: 3.5vh;*/
            /*display:block;*/
            /*align-items: center;*/
        /*}*/
    </style>
        <script src="${ctx}/static/layui/lay/modules/layer.js" type="text/javascript"></script>
</head>
<body id="designFrameBody" style="margin:0!important;">
<input type="hidden" id="areaId_lct" value="${areaId!}">
<input type="hidden" id="flowName" value="${flowName!}">
<!-- 信息表格模块 -->
<div class="information-model design_frame_title">

			<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;流程图>>>
			</span>

    <!-- 按钮 -->
    <button class="btn btn-primary design_frame_btn" style= "width:76px;" id="designFlow_mode" onclick="DesignFlowFrame.designMode()">编辑</button>
    <button class="btn btn-primary design_frame_btn" style= "width:76px;" id="runFlow_mode" onclick="DesignFlowFrame.runMode()" style="display: none">保存</button>
    <button class="btn btn-primary design_frame_btn" style = "position: fixed;top: 100px;left: 712px;width: 76px;height: 32px;" id="addFlow_type" onclick="AddFlowType.openAddFlowTypeWin()" style="display: none">新增</button>
<#--
    <button class="btn btn-primary design_frame_btn" style = "position: fixed;top: 100px;left: 388px;width: 76px;height: 32px;" id="addFlow_type" onclick="AddFlowType.deleteFlowTypeWin()" style="display: none">删除</button>
-->
    <button class="btn btn-primary design_frame_btn" style = "position: fixed;top: 100px;left: 797px;width: 76px;height: 32px;" id="editFlow_type" onclick="AddFlowType.openEditFlowTypeWin()" style="display: none">修改</button>
    <button class="btn btn-primary design_frame_btn" style = "position: fixed;top: 100px;left: 883px;width: 76px;height: 32px;" id="delFlow_type" onclick="AddFlowType.openDelFlowTypeWin()" style="display: none">删除</button>
    <span style = "position: fixed;top: 104px;left: 496px;">请选择流程图：</span>
    <select id="selFlowOptions"   onchange="DesignFlowFrame.selFlowOptions_update()" style = "position: fixed;top: 100px;left: 608px;width: 120px;height: 32px;border-radius: 15px;border-color: #518f9a; background-color: rgb(42, 123, 193);">

    </select>

</div>
<#--<div class="design_frame_title">-->

<#--</div>-->

<iframe id="design_frame_two" src="" frameborder="0" scrolling="no" width="100%" style="height:calc(100% - 35px)"></iframe>
<#--引入调试弹窗-->
<#include "view/strongAndWeakElectricityIntegration/flowType.ftl"/>
<script src="${ctx}/static/pageDesign/module/saveDesign.js" type="text/javascript"></script>
<script>
    var DesignFlowFrame = {};

    $(function(){
        // DesignFlowFrame.initPage();
        var sbgl = $("#leftMenu").text();
        if (sbgl.indexOf("设备管理") != -1) {
            $("#designFlow_mode").show();
        } else {//如果是用户登录
            $("#designFlow_mode").hide();
        }
    });

    DesignFlowFrame.selFlowOptions_update = function() {
        var sbgl = $("#leftMenu").text();
        if (sbgl.indexOf("设备管理") != -1) {
                $("#designFlow_mode").show();

            AddFlowType.selFlowOptions();
            } else {//如果是用户登录

            $("#designFlow_mode").hide();

            $("#areaId_lct").val($("#selFlowOptions").val());
            $("#flowName").val($("#selFlowOptions").find("option:selected").text());
            var areaId=$("#areaId_lct").val();
            var path = "displayView";
            var src = _ctx+"/view/pageDesign/"+path+"?areaId="+areaId;
            $("#design_frame_two").attr("src",src);
            $("#runFlow_mode").hide();
            $("#addFlow_type").hide();//添加流程图类型按钮隐藏
            $("#editFlow_type").hide();//编辑流程图类型按钮隐藏
            $("#delFlow_type").hide();//删除流程图类型按钮隐藏
            }
    }
    //进入设计模式
    DesignFlowFrame.designMode = function(){
        var areaId = $("#areaId_lct").val();
        $("#design_frame_two").prop("src",_ctx + "/view/pageDesign/view?areaId="+areaId);
        $("#designFlow_mode").hide();
        $("#runFlow_mode").show();
        $("#addFlow_type").show();//添加流程图类型按钮显示
        $("#editFlow_type").show();//编辑流程图类型按钮显示
        $("#delFlow_type").show();//删除流程图类型按钮显示


        /*$("#restoreBtn").hide();*/
    }
    //进入运行模式
    DesignFlowFrame.runMode = function(){
        DesignFlowFrame.saveDesignContent();
        //ajax请求完毕时执行
        // DesignFrame.initPage();
    }

    /**
     * 加载页面内容
     */
    DesignFlowFrame.initPage = function(){
        var areaId = $("#areaId_lct").val();
        $("#design_frame_two").prop("src",_ctx + "/view/pageDesign/displayView?areaId="+areaId);
        $("#designFlow_mode").show();
        $("#runFlow_mode").hide();
        $("#addFlow_type").hide();//添加流程图类型按钮隐藏
        $("#editFlow_type").hide();//编辑流程图类型按钮隐藏
        $("#delFlow_type").hide();//删除流程图类型按钮隐藏
    }
    /**
     * 切换之前是否保存页面
     */
    DesignFlowFrame.saveDesignContent = function(){
        layer.confirm('是否保存当前页面?', {title:' 保存提示'}, function(index){
            var areaId = $("#areaId_lct").val();
            SaveDesign.saveContent(areaId);
            $.when(SaveDesign.method).done(function () {
                //要执行的操作
                DesignFlowFrame.initPage();
            });
            layer.close(index);
        },function(index){
            layer.close(index);
            DesignFlowFrame.initPage();

        });
    }



</script>
</body>

</html>