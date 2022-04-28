<html>
<head>

    <!--jstree-->
    <script src="${ctx}/static/pageDesign/jstree/jstree.js"></script>
    <link rel="stylesheet" href="${ctx}/static/pageDesign/jstree/themes/default/style.css">
    <link rel="stylesheet" href="${ctx}/static/pageDesign/module/css/common.css">
    <link rel="stylesheet" href="${ctx}/static/platform/css/positionInfo.css">
    <link rel="stylesheet" href="${ctx}/static/platform/css/common.css">
    <link rel="stylesheet" href="${ctx}/static/platform/css/equipmentList.css">
    <script>
        var _ctx = '${ctx}';
    </script>
    <style type="text/css">
        .transfer_div{
            text-align: center;
            margin: 15px;
        }
        .transfer_div .layui-form-checkbox{
            display:inline-block;
        }
    </style>
    <style>
        .includeCss{
            float: left;
            width: 510px;
            height: 480px;
            border: 1px solid rgba(121, 194, 218, 0.44);
            -webkit-border-radius: 6px;
            -moz-border-radius: 6px;
            border-radius: 6px;
            margin: 5px 0;
            padding: 10px 0 10px 0;
            background-color: #0c2939;
        }
        .notIncludeCss{
            float: left;
            width: 510px;
            height: 480px;
            border: 1px solid rgba(121, 194, 218, 0.44);
            -webkit-border-radius: 6px;
            -moz-border-radius: 6px;
            border-radius: 6px;
            margin: 5px 0;
            padding: 10px 0 10px 0;
            background-color: #0c2939;
        }
        .zc_search_special_branch{
            margin-left: 2vw;
            width: 16.2vw;
            height: 2.9vh;
            border-radius: 4px;
            display: flex !important;
            justify-content: center;
            align-items: center;
            /*background: #001b3a;*/
            border: 1px solid lightblue;
        }
    </style>
</head>
<body>
<div class="design_area_tree_div leftarea information_left position_tree_leftarea">
    <!-- 信息表格模块 -->
    <div class="information-model design_area_nav">

			<span class="Subtitle">
				<i class="fa fa-user" aria-hidden="true"></i>&nbsp;区域位置信息>>>
			</span>

    </div>
    <div id="positionInfoTree"></div>
</div>
<div class="design_area_right_div information_right position_tree_right">
    <div class="postion_content_div">
        <!-- 信息表格模块 -->
        <div class="information-model">

			<span class="Subtitle">
				<i class="fa fa-user" aria-hidden="true"></i>&nbsp;仪表房间配置列表>>>
			</span>

        </div>
        <div class="layui_content_div">
            <form class="layui-form">
                <div class="layui-form-item">
                    <#--<div class="query_item">-->
                        <#--<label class="layui-form-label">区域编号</label>-->
                        <#--<div class="layui-input-block">-->
                            <#--<input type="text" name="position_code"  placeholder="请输入区域编号" autocomplete="off" class="layui-input">-->
                        <#--</div>-->
                    <#--</div>-->
                    <div class="query_item">
                        <label class="layui-form-label">区域名称</label>
                        <div class="layui-input-block">
                            <input type="text" name="position_name"  placeholder="请输入区域名称" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="query_item">
                        <label class="layui-form-label">区域类型</label>
                        <div class="layui-input-block">
                            <select name="position_type"></select>
                        </div>
                    </div>
                    <div class="queryBtnDiv">
                        <button class="layui-btn" type="button" onclick="PositionInfo.search()">查询</button>
                        <button class="layui-btn" type="reset" onclick="PositionInfo.reset()">重置</button>
                    </div>
                </div>
            </form>
            <table id="ammeter_position_table" lay-filter="ammeter_position_table"></table>
        </div>
    </div>


</div>

<!----包含支路电表--->
<div class="modal fade" id="position_ammeter_config" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content"  style="width:1185px;left: -288px" >
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title">关联电表配置</h4>
            </div>
            <div class="modal-body" style="height:600px;">
                <div style="float:left;width:54.5%"><button class="btn btn-md" style="cursor:default"><span>未选择</span></button></div>
                <div style="float:left;width:40%"><button class="btn btn-md" style="cursor:default"><span>已选择</span></button></div>
                <!----未选择table+搜索）--->
                <div class="notIncludeCss" >

                    <!-- 搜索框 -->
                    <div class="zc_search_special_branch find">
                        <input type="text" class = "zc_search_special_specialyle"  id="remainAmmeterKeywords" placeholder="电表编号、名称">
                        <button id="remainAmmeterSearch" onclick="PositionInfo.queryRemainAmmeter()"><i class="fa fa-search" aria-hidden="true"></i></button>
                    </div>
                    <div id="remain_ammeter_table" style="margin-top:10px;overflow: auto;" >
                    </div>
                </div>
                <!----未选择用户结束--->


                <!----操作开始--->
                <div style="width:100px;height:400px;float:left">
                    <div style="margin-top:200px;margin-left:23px;"><button id="position_ammeter_right" type="button" onclick="PositionInfo.moveRight()" class="btn btn-primary">>></button></div>
                    <div style="margin-top:20px;margin-left:23px;"><button id="position_ammeter_left" type="button"  onclick="PositionInfo.moveLeft()" class="btn btn-primary"><<</button></div>
                </div>
                <!----操作结束--->


                <!----包含用户开始--->
                <div class="includeCss">

                    <!-- 搜索框 -->
                    <div class="zc_search_special_branch find">
                        <input type="text" class = "zc_search_special_specialyle"  id="containAmmeterKeywords" placeholder="电表编号、名称">
                        <button id="containAmmeterSearch" onclick="PositionInfo.queryContainAmmeter()"><i class="fa fa-search" aria-hidden="true"></i></button>
                    </div>
                    <div id="contain_ammeter_table" style="overflow: auto;margin-top:10px;" >
                    </div>
                    <div>
                        <!----包含用户结束--->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/platform/js/position/positionInfo.js"></script>
<script>
</script>
</body>

</html>
