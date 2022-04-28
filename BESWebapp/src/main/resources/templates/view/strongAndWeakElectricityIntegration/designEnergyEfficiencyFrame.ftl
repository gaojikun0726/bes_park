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
<input type="hidden" id="areaId_xtnx" value="${areaId!}">
<!-- 信息表格模块 -->
<div class="information-model design_frame_title">

			<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;系统能效>>>
			</span>

    <!-- 按钮 -->
    <button class="btn btn-primary design_frame_btn" id="designEnergyEfficiency_mode" onclick="DesignEnergyEfficiencyFrame.designMode()">编辑</button>
    <button class="btn btn-primary design_frame_btn" id="runEnergyEfficiency_mode" onclick="DesignEnergyEfficiencyFrame.runMode()" style="display: none">保存</button>
    <#--<button class="btn btn-primary design_frame_btn" id="restoreBtn"  style="display: none;">还原缩放</button>-->
    <#--onclick="restoreTransform()"-->
</div>


<iframe id="design_frame_energyEfficiency" src="" frameborder="0" scrolling="no" width="100%" style="height:calc(100% - 35px)"></iframe>

<script src="${ctx}/static/pageDesign/module/saveDesign.js" type="text/javascript"></script>
<script>
    var DesignEnergyEfficiencyFrame = {};

    $(function(){
      // DesignFlowFrame.initPage();
      var sbgl = $("#leftMenu").text();
      if (sbgl.indexOf("设备管理") != -1) {
        $("#designEnergyEfficiency_mode").show();
      } else {//如果是用户登录
        $("#designEnergyEfficiency_mode").hide();
      }
    });


    var areaId = "daf-a6c9-4156-a856-ab31299d9d4e_xtnx";
    //进入设计模式
    DesignEnergyEfficiencyFrame.designMode = function(){
        $("#design_frame_energyEfficiency").prop("src",_ctx + "/view/pageDesign/view?areaId="+areaId);
        $("#designEnergyEfficiency_mode").hide();
        $("#runEnergyEfficiency_mode").show();
        /*$("#restoreBtn").hide();*/
    }
    //进入运行模式
    DesignEnergyEfficiencyFrame.runMode = function(){
        $("#areaId_xtnx").val(areaId);
        DesignEnergyEfficiencyFrame.saveDesignContent();
        //ajax请求完毕时执行
        // DesignFrame.initPage();
    }

    /**
     * 加载页面内容
     */
    DesignEnergyEfficiencyFrame.initPage = function(){
        $("#design_frame_energyEfficiency").prop("src",_ctx + "/view/pageDesign/displayView?areaId="+areaId);
        $("#designEnergyEfficiency_mode").show();
        $("#runEnergyEfficiency_mode").hide();
    }
    /**
     * 切换之前是否保存页面
     */
    DesignEnergyEfficiencyFrame.saveDesignContent = function(){
        layer.confirm('是否保存当前页面?', {title:' 保存提示'}, function(index){
            SaveDesign.saveContent($("#areaId_xtnx").val());
            $.when(SaveDesign.method).done(function () {
                //要执行的操作
                DesignEnergyEfficiencyFrame.initPage();
            });
            layer.close(index);
        },function(index){
            layer.close(index);
            DesignEnergyEfficiencyFrame.initPage();

        });
    }

</script>
</body>

</html>