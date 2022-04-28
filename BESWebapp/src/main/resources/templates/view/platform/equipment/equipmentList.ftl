<html>
<head>
<#--通过iframe嵌套解决单页应用id重复的问题，可以自定义样式-->
    <script>
        var _ctx = '${ctx}';
    </script>
      <#--<#include "view/platform/common/plugin.ftl"/>-->
    <link rel="stylesheet" href="${ctx}/static/platform/css/common.css">
    <link rel="stylesheet" href="${ctx}/static/platform/css/equipmentList.css">
    <#--<script type="text/javascript" src="${ctx}/static/platform/js/equipmentList.js"></script>-->
</head>
<body>
<div class="content_div">
    <!-- 信息表格模块 -->
    <div class="information-model">

			<span class="Subtitle">
				<i class="fa fa-user" aria-hidden="true"></i>&nbsp;设备列表>>>
			</span>

    <#--<!-- 增加按钮 &ndash;&gt;-->
    <#--<a href="javascript:void(-1);" class="btn btn-primary toLeft">-->
    <#--<i class="fa fa-plus" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;增加-->
    <#--</a>-->

    </div>
    <div class="layui_content_div">
        <form class="layui-form">
            <div class="layui-form-item">
                <div class="query_item">
                <#--<div class="layui-col-xs3 layui-col-sm3 layui-col-md2">-->
                    <label class="layui-form-label">设备编号</label>
                    <div class="layui-input-block">
                        <input type="text" name="device_code" required  placeholder="请输入设备编号" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="query_item">
                <#--<div class="layui-col-xs3 layui-col-sm3 layui-col-md2">-->
                    <label class="layui-form-label">设备名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="device_name" required  placeholder="请输入设备名称" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="query_item">
                <#--<div class="layui-col-xs3 layui-col-sm3 layui-col-md2">-->
                    <label class="layui-form-label">设备类型名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="device_type_name" required  placeholder="请输入设备类型名称" autocomplete="off" class="layui-input">
                    </div>
                </div>
            <#--<div class="layui-col-xs4 layui-col-sm4 layui-col-md5">-->
                <div class="queryBtnDiv">
                    <button class="layui-btn" type="button" onclick="EquipmentList.search()">查询</button>
                    <button class="layui-btn" type="reset" onclick="EquipmentList.reset()">重置</button>
                    <button class="layui-btn" type="button" onclick="EquipmentList.add()">新增</button>
                    <button class="layui-btn" type="button" onclick="EquipmentList.edit()">修改</button>
                    <button class="layui-btn" type="button" onclick="EquipmentList.delete()">删除</button>
                </div>
                <div class="controlBtnDiv" style="text-align: right;">
                    <a id="showMore" title="更多" onclick="EquipmentList.showMore()"><i class="layui-icon">&#xe61a;</i> 更多 </a>
                    <a id="hideMore" title="收起" onclick="EquipmentList.hideMore()" style="display:none;"><i class="layui-icon">&#xe619;</i>收起  </a>
                </div>
            </div>
            <div class="moreQueryDiv" style="display: none">
                <div class="layui-form-item">
                    <div class="query_item">
                        <label class="layui-form-label">设备状态</label>
                        <div class="layui-input-block">
                            <select name="device_status">
                                <option value=""></option>
                                <option value="0">正常</option>
                                <option value="1">报警</option>
                                <option value="2">离线</option>
                            </select>
                        </div>
                    </div>
                    <div class="query_item">
                        <label class="layui-form-label">设备类型编号</label>
                        <div class="layui-input-block">
                            <input type="text" name="device_type_code" placeholder="请输入设备类型编号" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                </div>
            </div>

        </form>
        <table id="equipment_list_table" lay-filter="equipment_list_table"></table>
    </div>



</div>

<#--新增/修改弹窗-->
<div class="equipment_add_div" style="display: none;">
    <div class="add_div_content">
        <form class="layui-form">
            <input type="hidden" name="device_id">
            <div class="layui-form-item">
                <label class="layui-form-label">设备编号</label>
                <div class="layui-input-block">
                    <input type="text" name="device_code_add" required  lay-verify="required" placeholder="请输入设备编号" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">设备名称</label>
                <div class="layui-input-block">
                    <input type="text" name="device_name_add" required  lay-verify="required" placeholder="请输入设备名称" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">设备类型编号</label>
                <div class="layui-input-block">
                    <input type="text" name="device_type_code_add" required  lay-verify="required" placeholder="请输入设备类型编号" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">设备类型名称</label>
                <div class="layui-input-block">
                    <input type="text" name="device_type_name_add" required  lay-verify="required" placeholder="请输入设备类型名称" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="btnDiv">
                    <button class="layui-btn" type="button" onclick="EquipmentList.addEquipment()">保存</button>
                <#--<button type="button" class="layui-btn" onclick>取消</button>-->
                </div>
            </div>
        </form>
    </div>

</div>

<#--<div class="design_area_tree_div leftarea information_left">-->
    <#--<!-- 信息表格模块 &ndash;&gt;-->
    <#--<div class="information-model design_area_nav">-->

			<#--<span class="Subtitle">-->
				<#--<i class="fa fa-user" aria-hidden="true"></i>&nbsp;区域管理>>>-->
			<#--</span>-->
    <#--</div>-->
    <#--&lt;#&ndash;<div id="areaTree"></div>&ndash;&gt;-->
<#--</div>-->
<#--<div class="design_area_right_div information_right">-->
   <#---->
<#--</div>-->
<script type="text/javascript" src="${ctx}/static/platform/js/equipmentList.js"></script>
</body>

</html>
