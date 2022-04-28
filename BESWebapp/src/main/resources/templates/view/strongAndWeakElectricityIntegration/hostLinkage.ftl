<#--
 xiepufeng
 主机联动
-->
<style>

    /* 主机矩形 */
    .host-rectangle {
        min-width: 180px;
        height: 100px;
        border-radius: 15px;
        float: left;
        border: solid 1px #3CA0D7;
        margin-left: 20px;
        margin-top: 20px;
        cursor: pointer;
    }

    .host-rectangle i {
        margin:10px;
        float:left;
        /*color:red;*/
    }

    .device-rectangle { /* 设备关联 */
        float:left;
        min-width: 180px;
        height: 50px;
        border-radius: 5px;
        border-style: solid;
        border-color: #2b8bc6;
        border-width: 1px;
        margin: 10px;
        cursor: pointer;
    }
    .device-rectangle i {
        margin:8px;
        float:left;
    }

        /*3D圆形稍立体设置*/
    .host-linkage-circle-3d {
        width: 100px;
        height: 100px;
        background: linear-gradient(to bottom, #4eb5e5 0%, #389ed5 100%);
        border: none;
        border-radius: 50px;
        position: relative;
        border-bottom: 8px solid #2b8bc6;
        text-shadow: 1px 1px 1px rgba(0, 0, 0, .4);
        font-size: 15px;
        text-align: center;
        box-shadow: 0px 5px 0px rgba(0, 0, 0, .2);
        cursor: pointer;
        color:#fff;
        line-height:100px
    }

    /* 设置圆形立体点击稍稍位移 */
    .host-linkage-circle-3d:active {
        box-shadow: 0px 2px 0px rgba(0, 0, 0, .2);
        top: 3px;
    }

    .host-linkage-circle-3d-red {
        background: linear-gradient(to bottom, #f00 0%, #ff9999 100%);
        border-bottom: 8px solid #ff3300;
    }

    .ztree li a.curSelectedNode {
        padding-top: 0px;
        background-color: rgba(31, 255, 8, 0);
        color: black;
        height: 16px;
        border: 1px #FFB951 solid;
        opacity: 0.8;
    }

    #host_linkage_tree_modal {
        left:60%;
        top: 10%;
    }

    /*面板标题*/
    .panel-default > .panel-heading {
        color: #8fe3f7;
        background-color: #094865;
        border-color: #226692;
    }
    .panel {
        margin-bottom: 20px;
        background-color: #001b3a;
        border: 1px solid transparent;
        border-radius: 4px;
    }

    .panel-default {
        border-color: #2d7fb0;
    }


</style>

<#-- Start 左边页面区域 -->
<div style="width:60%;" class="leftarea information_left">

    <#-- Start 上边页面区域 -->
    <div class="information_size" style="height: 50%;border-bottom: solid 2px #366c90;">

        <#-- Start 上边框区域 -->
        <div class="information-model">
	   		<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;主机列表&gt;&gt;&gt;
			</span>
        <div id="host_linkage_Whether_to_show">
            <!-- Start 增加主机按钮 -->
            <a href="javascript:void(-1);" id="host_linkage_button_add" class="btn btn-primary toLeft">
                <i class="fa fa-plus" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;添加
            </a>
            <!-- End 增加主机按钮 -->

            <!-- Start 编辑主机按钮 -->
            <a style="display: none" href="javascript:void(-1);" id="host_linkage_button_edit" class="btn btn-primary toLeft">
                <i class="fa fa-pencil" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;编辑
            </a>
            <!-- End 编辑主机按钮 -->

            <!-- Start 删除主机按钮 -->
            <a style="display: none" href="javascript:void(-1);" id="host_linkage_button_delete" class="btn btn-primary toLeft">
                <i class="fa fa-trash" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;删除
            </a>

            <#--Start 机组下拉框-->
            <div style = "display:inline-block ;margin-left: 15px;">
                <select
                        id='machineSetSelect'
                        class="form-control"
                        style="height:2.8vh;width:115%;padding: 0px 12px; border: 1px solid #bbb;background-color:rgb(216, 239, 255);color:#000000;"
                >
                </select>
            </div>
            <#--End 机组下拉框-->

        </div>
            <!-- End 删除主机按钮 -->
        </div>
        <#-- End 上边框区域 -->

        <!-- Start 主体部分（主机） -->
        <div id="host_rectangle_container" style="height: 90%; overflow-y: scroll">
        </div>
        <!-- End 主体部分（主机） -->

    </div>
    <#-- End 上边页面区域 -->

    <#-- Start 下边页面区域 -->
    <div class="information_size" style="height: 50%">

        <#-- Start 上边框区域 -->
        <div class="information-model" style="height:3.3vh">
     		<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;设备列表&gt;&gt;&gt;
			</span>
          <div id="host_linkage_Whether_to_show_second">
            <!-- Start 增加设备按钮 -->
            <a style="display: none" href="javascript:void(-1);" id="host_linkage_device_button_add" class="btn btn-primary toLeft">
                <i class="fa fa-plus" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;添加
            </a>
            <!-- End 增加设备按钮 -->

            <!-- Start 编辑设备按钮 -->
            <a style="display: none" href="javascript:void(-1);" id="host_linkage_device_button_edit" class="btn btn-primary toLeft">
                <i class="fa fa-pencil" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;编辑
            </a>
            <!-- End 编辑设备按钮 -->

            <!-- Start 删除设备按钮 -->
            <a style="display: none" href="javascript:void(-1);" id="host_linkage_device_button_delete" class="btn btn-primary toLeft">
                <i class="fa fa-trash" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;删除
            </a>
            <!-- End 删除设备按钮 -->
          </div>

        </div>
        <#-- End 上边框区域 -->

        <#-- Start 主体部分（设备） -->
        <div id="device_rectangle_container" style="height: 90%; overflow-y: scroll">
        </div>
        <#-- End 主体部分（设备） -->

    </div>
    <#-- End 下边页面区域 -->

</div>
<#-- End 左边页面区域 -->

<#-- Start 右边页面区域 -->
<div class="information_right" style="width:40%;">
    <div class="information-model">
        <span class="Subtitle"> <i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;操控面板&gt;&gt;&gt;
        </span>
    </div>

    <#-- Start 群控单机控制 -->
    <div>

        <div class="panel panel-default" style="margin-bottom: 0px;">
            <div class="panel-heading">
                <h3 class="panel-title">
                    运行控制模式
                </h3>
            </div>
            <div class="panel-body">

                <div class="row">
                    <div class="col-md-7">

                        <div class="btn-toolbar" role="toolbar">
                            <button id="host_linkage_single_control_button" type="button" class="btn btn-primary">单机</button>
                            <button id="host_linkage_group_control_button" type="button" class="btn btn-primary">群控</button>
                            <button id="host_linkage_join_point_button" type="button" class="btn btn-default">模式关联</button>
                        </div>

                    </div>
                    <div class="col-md-5">
                        <div>
                            <span>运行模式：</span>
                            <span id="host_linkage_current_mode"></span>
                        </div>
                    </div>
                </div

            </div>
        </div>
    </div>
    <#-- End 群控单机控制 -->

    <#-- Start 群控关联设定 -->
    <div>

        <div class="panel panel-default" style="margin-bottom: 0px;">
            <div class="panel-heading">
                <h3 class="panel-title">
                    群控关联设定
                </h3>
            </div>
            <div class="panel-body">

                <div class="row">
                    <div class="col-md-7">
                        <div class="btn-toolbar" role="toolbar">
                            <button id="host_linkage_start_stop_group" type="button" class="btn btn-default">启停关联</button>
                            <button id="host_linkage_start_stop_state_group" type="button" class="btn btn-default">状态关联</button>
                        </div>
                    </div>
                    <div class="col-md-5">
                        <div>
                            <span>启停状态：</span>
                            <span id="host_linkage_group_start_stop_state"></span>
                        </div>
                    </div>
                </div

            </div>
        </div>
    </div>
    <#-- End 群控关联设定 -->

    <!-- Start 主机列表筛选 -->
    <div>

        <div class="panel panel-default" style="margin-bottom: 0px;">
            <div class="panel-heading">
                <h3 class="panel-title">
                    主机列表筛选
                </h3>
            </div>
            <div class="panel-body">

                <div class="row">
                    <div class="col-md-7">

                        <div class="btn-toolbar" role="toolbar">
                            <button id="host_linkage_list_all" type="button" class="btn btn-primary">全部</button>
                            <button id="host_linkage_list_active" type="button" class="btn btn-primary">已运行</button>
                            <button id="host_linkage_list_inactive" type="button" class="btn btn-primary">已停止</button>
                            <button id="host_linkage_list_fault" type="button" class="btn btn-primary">已故障</button>
                        </div>

                    </div>
                    <div class="col-md-5">
                        <div>
                            <span>筛选条件：</span>
                            <span id="host_linkage_current_filter"></span>
                        </div>
                    </div>
                </div

            </div>
        </div>
    </div>
    <!-- End 主机列表筛选 -->

    <!-- Start 设备列表筛选 -->
    <div>

        <div class="panel panel-default" style="margin-bottom: 0px;">
            <div class="panel-heading">
                <h3 class="panel-title">
                    设备列表筛选
                </h3>
            </div>
            <div class="panel-body">

                <div class="row">
                    <div class="col-md-7">

                        <div class="btn-toolbar" role="toolbar">
                            <button id="host_linkage_device_list_all" type="button" class="btn btn-primary">全部</button>
                        </div>

                    </div>
                    <div class="col-md-5">
                        <div>
                            <span>筛选条件：</span>
                            <span id="host_linkage_device_current_filter"></span>
                        </div>
                    </div>
                </div

            </div>
        </div>
    </div>
    <!-- End 设备列表筛选 -->

    <!-- Start 启停操作 -->
    <div id="host_linkage_start_stop_operation_button" style="display: none;">

        <div class="panel panel-default" style="margin-bottom: 0px;">
            <div class="panel-heading">
                <h3 class="panel-title">
                    启停控制
                </h3>
            </div>
            <div class="panel-body">
                <div class="col-md-7">
                    <div style="overflow:hidden">
                        <div style="float:left;margin-left:40px;margin-top:5px;">
                            <div class="host-linkage-circle-3d">
                                <div id="host_linkage_start_operation_button">启&nbsp;动</div>
                            </div>
                        </div>
                        <div style="float:left;margin-left:40px;margin-top:5px;">
                            <div class="host-linkage-circle-3d host-linkage-circle-3d-red">
                                <div id="host_linkage_stop_operation_button">停&nbsp;止</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-5">
                    <div id="host_linkage_start_stop_their_host" style="margin-top: 40px;">
                    </div>
                </div>

            </div>
        </div>
    </div>
    <!-- End 启停操作 -->

</div>
<#-- End 右边页面区域 -->

<#-- Start 添加主机模态框 -->
<div class="modal fade"
     id="host_linkage_modal_add"
     tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel"
     data-backdrop="static">
    <div class="modal-dialog" style="margin-top: 8%;">
        <div class="modal-content" style="width: 550px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 添加主机</h4>
            </div>
            <div class="modal-body">
                <form id="host_linkage_add_form" name="host_linkage_add_form" class="form-horizontal">
                    <div  style="height: 70%;overflow-x: hidden;overflow-y: auto;">

                        <div class="form-group">
                            <label class="col-sm-3 control-label">主机名称
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-9">
                                <input
                                       type="text"
                                       id="host_linkage_name_add"
                                       name="host_linkage_name_add"
                                       class="form-control"
                                       placeholder="请输入主机名称"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">控制点位
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-9">
                                <input id="host_linkage_control_point_add"
                                       name="host_linkage_control_point_add"
                                       type="hidden"
                                >
                                <input
                                       type="text"
                                       id="host_linkage_control_point_display_add"
                                       name="host_linkage_control_point_display_add"
                                       class="form-control"
                                       placeholder="请选择控制点位"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">状态点位
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-9">
                                <input id="host_linkage_state_point_add"
                                       name="host_linkage_state_point_add"
                                       type="hidden"
                                >
                                <input
                                       type="text"
                                       id="host_linkage_state_point_display_add"
                                       name="host_linkage_state_point_display_add"
                                       class="form-control"
                                       placeholder="请选择状态点位"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">备注&nbsp;&nbsp;
                            </label>
                            <div class="col-sm-9">
                                <input
                                       type="text"
                                       id="host_linkage_comments_add"
                                       name="host_linkage_comments_add"
                                       class="form-control"
                                       placeholder=""
                                >
                            </div>
                        </div>

                    </div>
                    <div class="form-group m-t-sm">
                        <div class="col-sm-6 col-sm-push-3 display_flex">
                            <button class="btn btn-md btn-primary" type="submit"><strong>确定</strong></button>
                            <button class="btn btn-white m-l-sm" type="button" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div><!-- end modal-content -->
    </div>
</div>
<#-- End 添加主机模态框 -->

<#-- Start 编辑主机模态框 -->
<div class="modal fade"
     id="host_linkage_modal_edit"
     tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel"
     data-backdrop="static">
    <div class="modal-dialog" style="margin-top: 8%;">
        <div class="modal-content" style="width: 550px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp; 编辑主机</h4>
            </div>
            <div class="modal-body">
                <form id="host_linkage_edit_form" name="host_linkage_edit_form" class="form-horizontal">
                    <div  style="height: 70%;overflow-x: hidden;overflow-y: auto;">

                        <div class="form-group">
                            <label class="col-sm-3 control-label">主机名称
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-9">
                                <input
                                       type="text"
                                       id="host_linkage_name_edit"
                                       name="host_linkage_name_edit"
                                       class="form-control"
                                       placeholder="请输入主机名称"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">控制点位
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-9">
                                <input id="host_linkage_point_edit"
                                       name="host_linkage_point_edit"
                                       type="hidden"
                                >
                                <input
                                       type="text"
                                       id="host_linkage_point_display_edit"
                                       name="host_linkage_point_display_edit"
                                       class="form-control"
                                       placeholder="请选择控制点位"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">状态点位
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-9">
                                <input id="host_linkage_state_point_edit"
                                       name="host_linkage_state_point_edit"
                                       type="hidden"
                                >
                                <input
                                        type="text"
                                        id="host_linkage_state_point_display_edit"
                                        name="host_linkage_state_point_display_edit"
                                        class="form-control"
                                        placeholder="请选择状态点位"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">备注&nbsp;&nbsp;
                            </label>
                            <div class="col-sm-9">
                                <input
                                       type="text"
                                       id="host_linkage_comments_edit"
                                       name="host_linkage_comments_edit"
                                       class="form-control"
                                       placeholder=""
                                >
                            </div>
                        </div>

                    </div>
                    <div class="form-group m-t-sm">
                        <div class="col-sm-6 col-sm-push-3 display_flex">
                            <button class="btn btn-md btn-primary" type="submit"><strong>确定</strong></button>
                            <button class="btn btn-white m-l-sm" type="button" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div><!-- end modal-content -->
    </div>
</div>
<#-- End 编辑主机模态框 -->

<#-- Start 添加设备模态框 -->
<div class="modal fade"
     id="host_linkage_device_modal_add"
     tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel"
     data-backdrop="static">
    <div class="modal-dialog" style="margin-top: 8%;">
        <div class="modal-content" style="width: 550px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 添加设备</h4>
            </div>
            <div class="modal-body">
                <form id="host_linkage_device_add_form" name="host_linkage_device_add_form" class="form-horizontal">
                    <div  style="height: 70%;overflow-x: hidden;overflow-y: auto;">

                        <div class="form-group">
                            <label class="col-sm-3 control-label">设备名称
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-9">
                                <input
                                       type="text"
                                       id="host_linkage_device_name_add"
                                       name="host_linkage_device_name_add"
                                       class="form-control"
                                       placeholder="请输入设备名称"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">所属点位
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-9">
                                <input id="host_linkage_device_point_add"
                                       name="host_linkage_device_point_add"
                                       type="hidden"
                                >
                                <input
                                       type="text"
                                       id="host_linkage_device_point_display_add"
                                       name="host_linkage_device_point_display_add"
                                       class="form-control"
                                       placeholder="请选择所属点位"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">故障反馈
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-9">
                                <select class="form-control"
                                        id="host_linkage_device_fault_point_add"
                                        name="host_linkage_device_fault_point_add"
                                >
                                    <option value="0">否</option>
                                    <option value="1">是</option>
                                </select>
                            </div>
                        </div>

                        <div id="host_linkage_device_fault_fine_value_add_div" class="form-group" style="display: none;">
                            <label class="col-sm-3 control-label">正常值
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-9">
                                <input
                                        type="text"
                                        id="host_linkage_device_fault_fine_value_add"
                                        name="host_linkage_device_fault_fine_value_add"
                                        class="form-control"
                                        placeholder="请输入一个故障点的正常值"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">备注&nbsp;&nbsp;
                            </label>
                            <div class="col-sm-9">
                                <input
                                       type="text"
                                       id="host_linkage_device_comments_add"
                                       name="host_linkage_device_comments_add"
                                       class="form-control"
                                       placeholder=""
                                >
                            </div>
                        </div>

                    </div>
                    <div class="form-group m-t-sm">
                        <div class="col-sm-6 col-sm-push-3 display_flex">
                            <button class="btn btn-md btn-primary" type="submit"><strong>确定</strong></button>
                            <button class="btn btn-white m-l-sm" type="button" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div><!-- end modal-content -->
    </div>
</div>
<#-- End 添加设备模态框 -->

<#-- Start 编辑设备模态框 -->
<div class="modal fade"
     id="host_linkage_device_modal_edit"
     tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel"
     data-backdrop="static">
    <div class="modal-dialog" style="margin-top: 8%;">
        <div class="modal-content" style="width: 550px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp; 编辑设备</h4>
            </div>
            <div class="modal-body">
                <form id="host_linkage_device_edit_form" name="host_linkage_device_edit_form" class="form-horizontal">
                    <div  style="height: 70%;overflow-x: hidden;overflow-y: auto;">

                        <div class="form-group">
                            <label class="col-sm-3 control-label">设备名称
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-9">
                                <input
                                       type="text"
                                       id="host_linkage_device_name_edit"
                                       name="host_linkage_device_name_edit"
                                       class="form-control"
                                       placeholder="请输入设备名称"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">所属点位
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-9">
                                <input id="host_linkage_device_point_edit"
                                       name="host_linkage_device_point_edit"
                                       type="hidden"
                                >
                                <input
                                       type="text"
                                       id="host_linkage_device_point_display_edit"
                                       name="host_linkage_device_point_display_edit"
                                       class="form-control"
                                       placeholder="请选择所属点位"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">故障反馈
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-9">
                                <select class="form-control"
                                        id="host_linkage_device_fault_point_edit"
                                        name="host_linkage_device_fault_point_edit"
                                >
                                    <option value="0">否</option>
                                    <option value="1">是</option>
                                </select>
                            </div>
                        </div>

                        <div id="host_linkage_device_fault_fine_value_edit_div" class="form-group" style="display: none;">
                            <label class="col-sm-3 control-label">正常值
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-9">
                                <input
                                        type="text"
                                        id="host_linkage_device_fault_fine_value_edit"
                                        name="host_linkage_device_fault_fine_value_edit"
                                        class="form-control"
                                        placeholder="请输入一个故障点的正常值"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">备注&nbsp;&nbsp;
                            </label>
                            <div class="col-sm-9">
                                <input
                                       type="text"
                                       id="host_linkage_device_comments_edit"
                                       name="host_linkage_device_comments_edit"
                                       class="form-control"
                                       placeholder=""
                                >
                            </div>
                        </div>

                    </div>
                    <div class="form-group m-t-sm">
                        <div class="col-sm-6 col-sm-push-3 display_flex">
                            <button class="btn btn-md btn-primary" type="submit"><strong>确定</strong></button>
                            <button class="btn btn-white m-l-sm" type="button" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div><!-- end modal-content -->
    </div>
</div>
<#-- End 编辑设备模态框 -->

<#-- Start tree 模态框 -->
<div class="modal fade" id="host_linkage_tree_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" style="margin-top: 8%;">
        <div class="modal-content" style="width: 350px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 请选择所属点位</h4>
            </div>
            <div class="modal-body" style="height: 450px">

            <#-- Start 搜索-->
                <div style="position: relative" class="zc_search find">
                    <input type="text" class="find-style" id="host_linkage_tree_search_input" name="eqTypeInfo" placeholder="">
                    <button id="host_linkage_tree_search_button"><i class="fa fa-search" aria-hidden="true"></i></button>
                </div>
            <#-- End 搜索-->

            <#-- Start tree 容器-->
                <div id="host_linkage_tree_container" class="Information_area ztree"></div>
            <#-- End tree 容器-->

            </div>
        </div><!-- end modal-content -->
    </div>
</div> <!-- end addParkBasicInfo -->
<#-- End tree 模态框 -->

<script type="text/javascript">

    var hostLinkage = (function ($, window, document) {

        // 主机过滤条件定义
        var hostFilterType = {
            ALL: 1, // 全部
            RUN: 2, // 已运行
            STOPPED: 3, // 3已停止
            FAULTED: 4 // 已故障
        };

        // 设备过滤条件定义
        var deviceFilterType = {
            ALL: 1, // 全部
            SLAVE: 2, // 当前主机
        };

        // 主机告警状态
        var hostAlarmState = {
            YES: 1,
            NO: 2
        };

        // 单机群控模式
        var runModel = {
            SINGLE: '0', // 单机模式
            GROUP: '255' // 群控模式

        };

        // 点类型
        var pointType = {
            MODEL: 1, // 模式关联
            START_STOP: 2, // 群控模式启停关联
            START_STOP_STATE: 3 // 群控模式启停状态关联
        };

        var tree;// 树对象
        var checkedHostId = null; // 选中的主机id
        var checkedDeviceId = null; // 选中的设备id
        var checkedHostRectangleDiv = null; // 选中的主机div
        var checkedDeviceRectangleDiv = null; // 选中的设备div
        var hostData = {}; // 主机信息
        var deviceData = {}; // 设备信息
        var runningModeObj =  null; // 运行模式信息
        var startStopGroupInfo =  null; // 启停关联信息信息
        var startStopStateGroupInfo =  null; // 启停状态关联信息信息
        var treeCallback = function () {}; // 点击树节点回调回调
        var filterTypeHost = hostFilterType.ALL; // 筛选类型 1 全部 2 已运行 3已停止 4已故障
        var filterTypeDevice = deviceFilterType.ALL; // 筛选类型 1 全部 2 当前主机
        var pointValueConfig = {}; // 点值设置

        var flag = new Date().getTime();

        $(function () {

            // 加载机组下拉列表
            loadMachineSetSelect();

            // 加载主机列表
            // 主机列表加载成功后加载设备列表
            loadHostRectangleList(loadDeviceRectangleList);

            // 初始化树
            initTree();

            // 获取设备信息并把数据加载到树中
            getTreeDataHandle();

            // 获取运行控制模式信息
            getRunningModeInfo();

            // 获取获取群控的启停信息
            getGroupStartStopInfo();

            // 获取获取群控的启停状态信息
            getGroupStartStopStateInfo();

            // 显示全部主机文本提示
            showByFilterTypeHostText();

            // 显示全部设备文本提示
            showByFilterTypeDeviceText();

            var sbgl = $("#leftMenu").text();
            if (sbgl.indexOf("设备管理") != -1) {
                $("#host_linkage_Whether_to_show").show();
                $("#host_linkage_Whether_to_show_second").show();
                $("#host_linkage_join_point_button").show();
                $("#host_linkage_start_stop_group").show();
                $("#host_linkage_start_stop_state_group").show();
            } else {
                $("#host_linkage_Whether_to_show").hide();
                $("#host_linkage_Whether_to_show_second").hide();
                $("#host_linkage_join_point_button").hide();
                $("#host_linkage_start_stop_group").hide();
                $("#host_linkage_start_stop_state_group").hide();
            }

        });


        /*************************************** 初始化（start） ********************************************************/
        
        // 加载机组下拉列表
        function loadMachineSetSelect()
        {
            queryMachineSet(null, function (obj)
            {
                var list = obj && obj.data && obj.data;

                if (!Array.isArray(list))
                {
                    return;
                }

                var html = [];

                html.push('<option value="">全部机组</option>');

                list.forEach((item) => {
                    html.push('<option value="' + item.id + '">' + item.name + '</option>')
                });

                $('#machineSetSelect').html(html.join(''));
            });
        }

        // 加载主机列表
        function loadHostRectangleList(callback){

            queryHost(null, function (obj) {

                var status = obj && obj.status;

                if (status !== '1') {
                    return;
                }

                var hostList = obj.data || [];
                var pointStates = [];

                for(var i = 0; i < hostList.length; i++) {
                    hostData[hostList[i].id] = hostList[i];
                    pointStates.push(hostList[i].pointState);
                }

                // 从数据库中获取实时数据
                queryRealTimeData(pointStates, function (result)
                {
                    var list = result && result.list;
                    var pointValueMap = new Map();

                    if (Array.isArray(list))
                    {
                        for (var item of list)
                        {
                            pointValueMap.put(item.f_sys_name, item.f_init_val);
                        }
                    }

                    for(var i = 0; i < hostList.length; i++) {

                        var id = hostList[i].id;
                        var pointState = hostList[i].pointState;

                        // 运行状态保存到缓存
                        hostData[id].runState = pointValueMap.get(pointState);

                        PubSub.subscribe(pointState, hostLinkage.hostSubCallback);

                        // 把主机页面元素添加到指定位置
                        addHostRectangleToContainer(null, id);
                    }

                    if (typeof callback === 'function')
                    {
                        callback();
                    }
                });
            });
        }

        // 加载设备列表
        function loadDeviceRectangleList(){

            queryDevice(null, function (obj) {

                var status = obj && obj.status;

                if (status !== '1') {
                    return;
                }

                var deviceList = obj.data || [];
                var points = [];

                for(var i = 0; i < deviceList.length; i++) {
                    deviceData[deviceList[i].id] = deviceList[i];
                    points.push(deviceList[i].point);
                }

                // 从数据库中获取实时数据
                queryRealTimeData(points, function (result)
                {

                    var list = result && result.list;
                    var pointMap = new Map();

                    if (Array.isArray(list))
                    {
                        for (var item of list)
                        {
                            pointMap.put(item.f_sys_name, item);
                        }
                    }

                    getPointValueConfig(function ()
                    {
                        for(var i = 0; i < deviceList.length; i++) {

                            var id = deviceList[i].id;
                            var point = deviceList[i].point;

                            // 运行状态保存到缓存
                            var pointItem;
                            if (pointItem = pointMap.get(point))
                            {
                                deviceData[id].value = pointItem.f_init_val;
                                deviceData[id].unit = pointItem.unit || '';
                            }

                            PubSub.subscribe(point, hostLinkage.deviceSubCallback);

                            // 把设备页面元素添加到指定位置
                            addDeviceRectangleToContainer(null, id);
                        }

                        // 更新故障状态
                        updateFaultState();
                    });
                });
            });
        }

        // 初始化设备类型树
        function initTree() {
            tree = new Tree({
                container: 'host_linkage_tree_container',
                idKey: 'f_sys_name',
                pIdKey: 'f_psys_name',
                name: 'f_nick_name',
                setting: {
                    view: {
                        showIcon: false,
                        fontCss: function ( treeId, treeNode ) {
                            return ( treeNode.highlight )
                                    ? {color:'#A60000', 'font-weight':'bold'}
                                    : {color:'#D1E3F9', 'font-weight':'normal'};
                        }
                    }
                },
                callback: function (node){
                    if (typeof treeCallback === 'function')
                    {
                        treeCallback(node);
                    }

                }
            });
        }

        // 获取设备信息并把数据加载到树中
        function getTreeDataHandle()
        {
            queryTreeData(function (data) {

                var status = data.status;

                if(status !== '1') {
                    return;
                }

                var treeList = data.list || [];

                tree.loadData(treeList);
            });
        }

        // 获取运行控制模式信息
        function getRunningModeInfo()
        {
            queryJoinPoint({ type: pointType.MODEL}, function (result)
            {
                var status = result.status;

                if(status !== '1') {
                    return;
                }

                var data = result.data || [];

                var length = data.length;

                if (length === 0)
                {
                    $('#host_linkage_current_mode').text('未设定').css('color', 'red');
                    return;
                }

                var machineSetId1 = $('#machineSetSelect').val();

                runningModeObj = null;

                for (var i = 0; i < length; i++)
                {

                    var machineSetId = data[i].machineSetId;

                    if (machineSetId != machineSetId1)
                    {
                        continue;
                    }

                    // 缓存运行模式信息
                    runningModeObj = data[i];

                    queryRealTimeData([runningModeObj.joinPoint], function (result)
                    {
                        var ddcList = result.list || [];

                        for(var i = 0; i < ddcList.length; i++) {

                            var val = ddcList[i].f_init_val;

                            updateModelState(val);

                            startStopPanelShowHide();
                        }
                    });
                }

                PubSub.subscribe(runningModeObj.joinPoint, hostLinkage.runningModeRealTimeDataCallback, false);
            })
        }
        // 获取获取群控的启停信息
        function getGroupStartStopInfo(callback)
        {
            queryJoinPoint({ type: pointType.START_STOP}, function (result)
            {
                var status = result.status;

                if(status !== '1') {
                    return;
                }

                var data = result.data || [];

                var length = data.length;

                if (length === 0)
                {
                    return;
                }

                var machineSetId1 = $('#machineSetSelect').val();

                startStopGroupInfo = null;

                for (var i = 0; i < length; i++)
                {
                    var machineSetId = data[i].machineSetId;

                    if (machineSetId != machineSetId1)
                    {
                        continue;
                    }

                    // 缓存运行模式信息
                    startStopGroupInfo = data[i];
                }
                
                if (typeof callback === 'function')
                {
                    callback();
                }
            })
        }

        // 获取获取群控的启停状态信息
        function getGroupStartStopStateInfo()
        {
            queryJoinPoint({ type: pointType.START_STOP_STATE}, function (result)
            {
                var status = result.status;

                if(status !== '1') {
                    return;
                }

                var data = result.data || [];

                var length = data.length;

                if (length === 0)
                {
                    $('#host_linkage_group_start_stop_state').text('未设定').css('color', 'red');
                    return;
                }

                var machineSetId1 = $('#machineSetSelect').val();

                startStopStateGroupInfo = null;

                for (var i = 0; i < length; i++)
                {
                    var machineSetId = data[i].machineSetId;

                    if (machineSetId != machineSetId1)
                    {
                        continue;
                    }

                    // 缓存运行模式信息
                    startStopStateGroupInfo = data[i];

                    queryRealTimeData([startStopStateGroupInfo.joinPoint], function (result)
                    {
                        var ddcList = result.list || [];

                        for(var i = 0; i < ddcList.length; i++) {

                            var val = ddcList[i].f_init_val;

                            updateGroupStartStopState(val);
                        }
                    });
                }

                PubSub.subscribe(startStopStateGroupInfo.joinPoint, hostLinkage.groupStartStopRealTimeDataCallback, false);
            })
        }

        /*************************************** 初始化（end） **********************************************************/

        /*************************************** 事件（start） *********************************************************/

        // 机组选中事件
        $('#machineSetSelect').change(function ()
        {

            $('#host_linkage_current_mode').text('');
            $('#host_linkage_group_start_stop_state').text('');
            $('#host_linkage_start_stop_operation_button').hide();

            // 根据筛选条件显示主机div
            showByFilterTypeHostDiv();

            // 获取运行控制模式信息
            getRunningModeInfo();

            // 获取获取群控的启停信息
            getGroupStartStopInfo();

            // 获取获取群控的启停状态信息
            getGroupStartStopStateInfo();
        });

        // 添加主机按钮点击事件
        $('#host_linkage_button_add').click(function () {
            $('#host_linkage_modal_add').modal('show');
        });

        // 编辑主机按钮点击事件
        $('#host_linkage_button_edit').click(function () {

            queryHost({ id: checkedHostId }, function (obj) {

                var status = obj && obj.status;

                if (status !== '1') {
                    return;
                }

                var data = obj.data && obj.data[0] || [{}];

                var name = data.name || '';
                var point = data.point || '';
                var pointState = data.pointState || '';
                var pointDisplay = data.pointDisplay || '';
                var pointStateDisplay = data.pointStateDisplay || '';
                var comments = data.comments || '';

                $('#host_linkage_name_edit')               .val(name);
                $('#host_linkage_point_edit')              .val(point);
                $('#host_linkage_state_point_edit')        .val(pointState);
                $('#host_linkage_point_display_edit')      .val(pointDisplay);
                $('#host_linkage_state_point_display_edit').val(pointStateDisplay);
                $('#host_linkage_comments_edit')           .val(comments);

            });

            $('#host_linkage_modal_edit').modal('show');
        });

        // 编辑设备按钮点击事件
        $('#host_linkage_device_button_edit').click(function () {

            queryDevice({ id: checkedDeviceId }, function (obj) {

                var status = obj && obj.status;

                if (status !== '1') {
                    return;
                }

                var data = obj.data && obj.data[0] || [{}];

                var name         = data.name         || '';
                var point        = data.point        || '';
                var pointDisplay = data.pointDisplay || '';
                var comments     = data.comments     || '';

                var isFaultPoint = data.isFaultPoint;

                $('#host_linkage_device_fault_point_edit')  .val(isFaultPoint);
                $('#host_linkage_device_name_edit')         .val(name);
                $('#host_linkage_device_point_edit')        .val(point);
                $('#host_linkage_device_point_display_edit').val(pointDisplay);
                $('#host_linkage_device_comments_edit')     .val(comments);

                if (isFaultPoint === 0)
                {
                    $('#host_linkage_device_fault_fine_value_edit_div').hide();
                }else if (isFaultPoint === 1)
                {
                    $('#host_linkage_device_fault_fine_value_edit_div').show();
                    $('#host_linkage_device_fault_fine_value_edit').val(data.faultFineValue);
                }

            });

            $('#host_linkage_device_modal_edit').modal('show');
        });

        // 删除主机按钮点击事件
        $('#host_linkage_button_delete').click(function () {

            swal
            (
                    {
                        title : '您确定要删除吗?',
                        text : '信息删除后将不可恢复!',
                        type : 'warning',
                        showCancelButton : true,
                        confirmButtonColor : '#1ab394',
                        confirmButtonText : '确定',
                        closeOnConfirm : false
                    },function () {
                        deleteHost(checkedHostId, function (obj) {
                            deleteHostAfterHandle(obj);
                        });
                    }
            );

        });

        // 删除设备按钮点击事件
        $('#host_linkage_device_button_delete').click(function () {

            swal
            (
                    {
                        title : '您确定要删除吗?',
                        text : '信息删除后将不可恢复!',
                        type : 'warning',
                        showCancelButton : true,
                        confirmButtonColor : '#1ab394',
                        confirmButtonText : '确定',
                        closeOnConfirm : false
                    },function () {
                        deleteDevice(checkedDeviceId, function (obj) {
                            deleteDeviceAfterHandle(obj, checkedDeviceId);
                            updateFaultState();
                        });
                    }
            );

        });

        // 添加设备按钮点击事件
        $('#host_linkage_device_button_add').click(function () {
            $('#host_linkage_device_modal_add').modal('show');
        });

        // 触发搜索的回车事件， 以系统别名搜索设备树展开并以红色字体标注
        $('#host_linkage_tree_search_input').keydown(function (e){
            if(e.which !== 13){
                return;
            }

            tree.search('host_linkage_tree_search_input', 'f_nick_name');

        });

        // 触发搜索点击事件， 以系统别名搜索设备树展开并以红色字体标注
        $('#host_linkage_tree_search_button').click(function () {

            tree.search('host_linkage_tree_search_input', 'f_nick_name');
        });

        // 控制点位输入框得到焦点时显示 tree 模态框（主机）
        $('#host_linkage_control_point_display_add, #host_linkage_point_display_edit').focus(function (obj) {

            treeCallback = function(node)
            {
                // 主机关联点处理
                hostJoinPointHandle(node, obj.target);

            };

            var f_sys_name = $('#host_linkage_point_edit').val();

            // 回显选中的节点
            if (f_sys_name)
            {
                tree.searchPrecise(f_sys_name, 'f_sys_name');
            }


            $('#host_linkage_tree_modal').modal('show');
        });

        // 状态点位输入框得到焦点时显示 tree 模态框（主机）
        $('#host_linkage_state_point_display_add, #host_linkage_state_point_display_edit').focus(function (obj) {

            treeCallback = function(node)
            {
                // 主机状态关联点处理
                hostStateJoinPointHandle(node, obj.target);

            };

            var f_sys_name = $('#host_linkage_state_point_edit').val();

            // 回显选中的节点
            if (f_sys_name)
            {
                tree.searchPrecise(f_sys_name, 'f_sys_name');
            }


            $('#host_linkage_tree_modal').modal('show');
        });

        // 所属点位输入框得到焦点时显示 tree 模态框（设备）
        $('#host_linkage_device_point_display_add, #host_linkage_device_point_display_edit').focus(function (obj) {

            treeCallback = function(node)
            {
                // 设备关联点处理
                deviceJoinPointHandle(node, obj.target);
            };

            var f_sys_name = $('#host_linkage_device_point_edit').val();

            // 回显选中的节点
            if (f_sys_name)
            {
                tree.searchPrecise(f_sys_name, 'f_sys_name');
            }

            $('#host_linkage_tree_modal').modal('show');
        });

        // 当 tree 模态框关闭时折叠 tree、清空搜索框内容
        $('#host_linkage_tree_modal').on('hide.bs.modal', function () {
            tree.tree.expandAll( false );
            $('#host_linkage_tree_search_input').val('');
        });

        // 模态框完全关闭事件
        $('#host_linkage_tree_modal').on('hidden.bs.modal', function () {

            // 清除选中的节点
            tree.clearCheckedNode();

            // 当模态框完全隐藏时恢复模态框默认位置
            $('#host_linkage_tree_modal').css({ left: '60%'});
        });

        // 启动联动按钮点击事件
        $('#host_linkage_start_operation_button').click(function () {
            startDevices();
        });

        // 停止联动按钮点击事件
        $('#host_linkage_stop_operation_button').click(function () {
            stopDevices();
        });

        // 模式关联点击事件
        $('#host_linkage_join_point_button').click(function ()
        {

            if (!$('#machineSetSelect').val())
            {
                swal({
                    title             : '请选择所属机组',// 展示的标题
                    // text              : '', // 内容
                    type              : 'warning',
                    showCloseButton   : false, // 展示关闭按钮
                    allowOutsideClick : false,
                    showConfirmButton : false,
                    timer             : 1000
                });

                return;
            }

            treeCallback = function(node)
            {
                joinPointHandle(node);
            };

            // 回显选中的节点
            if (runningModeObj && runningModeObj.joinPoint)
            {
                tree.searchPrecise(runningModeObj.joinPoint, 'f_sys_name');
            }

            $('#host_linkage_tree_modal').css({ left: '10%'});

            $('#host_linkage_tree_modal').modal('show');
        });

        // 群控启停关联点击事件
        $('#host_linkage_start_stop_group').click(function ()
        {
            if (!$('#machineSetSelect').val())
            {
                swal({
                    title             : '请选择所属机组',// 展示的标题
                    // text              : '', // 内容
                    type              : 'warning',
                    showCloseButton   : false, // 展示关闭按钮
                    allowOutsideClick : false,
                    showConfirmButton : false,
                    timer             : 1000
                });

                return;
            }

            treeCallback = function(node)
            {
                startStopGroupJoinPointHandle(node);
            };

            // 回显选中的节点
            if (startStopGroupInfo && startStopGroupInfo.joinPoint)
            {
                tree.searchPrecise(startStopGroupInfo.joinPoint, 'f_sys_name');
            }

            $('#host_linkage_tree_modal').css({ left: '10%'});

            $('#host_linkage_tree_modal').modal('show');
        });

        // 群控启停状态关联点击事件
        $('#host_linkage_start_stop_state_group').click(function ()
        {
            if (!$('#machineSetSelect').val())
            {
                swal({
                    title             : '请选择所属机组',// 展示的标题
                    // text              : '', // 内容
                    type              : 'warning',
                    showCloseButton   : false, // 展示关闭按钮
                    allowOutsideClick : false,
                    showConfirmButton : false,
                    timer             : 1000
                });

                return;
            }

            treeCallback = function(node)
            {
                startStopStateGroupJoinPointHandle(node);
            };

            // 回显选中的节点
            if (startStopStateGroupInfo && startStopStateGroupInfo.joinPoint)
            {
                tree.searchPrecise(startStopStateGroupInfo.joinPoint, 'f_sys_name');
            }

            $('#host_linkage_tree_modal').css({ left: '10%'});

            $('#host_linkage_tree_modal').modal('show');
        });

        // 单机按钮点击事件
        $('#host_linkage_single_control_button').click(function ()
        {

            if (!runningModeObj)
            {
                swal({
                    title : '当前模式未设定，请关联点设定模式',// 展示的标题
                    text : '',// 内容
                    type: 'warning',
                    showCloseButton : true, // 展示关闭按钮
                    allowOutsideClick : false,
                    confirmButtonText : '确定',
                    closeOnConfirm : false
                });

                return;
            }

            var model = runningModeObj.model;

            if (model === runModel.SINGLE)
            {
                return;
            }


            // 判断是否在群控启停运行
            if (startStopStateGroupInfo && startStopStateGroupInfo.startStopState === '255')
            {
                swal({
                    title : '群控启动正在运行，不允许切换',// 展示的标题
                    text : '如若切换请关闭群控启动',// 内容
                    type: 'warning',
                    showCloseButton : true, // 展示关闭按钮
                    allowOutsideClick : false,
                    confirmButtonText : '确定',
                    closeOnConfirm : false
                });
                return
            }

            swal({
                title : '您确定要切换到单机模式吗?',
                text : '',
                type : 'warning',
                showCancelButton : true,
                confirmButtonText : '确定',
                closeOnConfirm : true
            }, function() {

                var joinPoint = runningModeObj.joinPoint;

                startStopControl(joinPoint, 0);

            })

        });

        // 群控按钮点击事件
        $('#host_linkage_group_control_button').click(function ()
        {

            if (!runningModeObj)
            {
                swal({
                    title : '当前模式未设定，请关联点设定模式',// 展示的标题
                    text : '',// 内容
                    type: 'warning',
                    showCloseButton : true, // 展示关闭按钮
                    allowOutsideClick : false,
                    confirmButtonText : '确定',
                    closeOnConfirm : false
                });

                return;
            }

            var model = runningModeObj.model;

            // 是群控模式直接返回
            if (model === runModel.GROUP)
            {
                return;
            }

            // 判断是否有正在运行的主机，如果有则提示用户，有主机正在运行不允许切换
            if (isHasRunHost())
            {
                swal({
                    title : '有主机正在运行不允许切换到群控模式',// 展示的标题
                    text : '如若切换请关闭所有主机',// 内容
                    type: 'warning',
                    showCloseButton : true, // 展示关闭按钮
                    allowOutsideClick : false,
                    confirmButtonText : '确定',
                    closeOnConfirm : false
                });
                return
            }


            swal({
                title : '您确定要切换到群控模式吗?',
                text : '',
                type : 'warning',
                showCancelButton : true,
                confirmButtonText : '确定',
                closeOnConfirm : true
            }, function() {

                var joinPoint = runningModeObj.joinPoint;

                startStopControl(joinPoint, runModel.GROUP);

            })

        });

        // 点击主机列表筛选按钮（全部）
        $('#host_linkage_list_all').click(function ()
        {
            filterHostListAll();
        });

        // 点击主机列表筛选按钮（已运行）
        $('#host_linkage_list_active').click(function ()
        {
            filterHostListActive();
        });

        // 点击主机列表筛选按钮（已停止）
        $('#host_linkage_list_inactive').click(function ()
        {
            filterHostListInactive();
        });

        // 点击主机列表筛选按钮（已故障）
        $('#host_linkage_list_fault').click(function ()
        {
            filterHostListFault();
        });

        // 点击设备列表筛选按钮（全部）
        $('#host_linkage_device_list_all').click(function ()
        {
            filterDeviceListAll();
        });

        // 添加主机模态框关闭时处理事件 1、清空表单；2、重置验证表单；3、关闭 tree 模态框
        $('#host_linkage_modal_add').on('hide.bs.modal', function () {

            // 关闭 tree 模态框
            $('#host_linkage_tree_modal').modal('hide');

            // 清空表单
            $('#host_linkage_name_add')                 .val('');
            $('#host_linkage_control_point_add')        .val('');
            $('#host_linkage_state_point_add')          .val('');
            $('#host_linkage_control_point_display_add').val('');
            $('#host_linkage_state_point_display_add')  .val('');
            $('#host_linkage_comments_add')             .val('');

            // 重置添加验证表单
            addHostValidate.resetForm()
        });

        // 编辑主机模态框关闭时处理事件 1、清空表单；2、重置验证表单；3、关闭 tree 模态框
        $('#host_linkage_modal_edit').on('hide.bs.modal', function () {

            // 关闭 tree 模态框
            $('#host_linkage_tree_modal').modal('hide');

            // 清空表单
            $('#host_linkage_name_edit')               .val('');
            $('#host_linkage_point_edit')              .val('');
            $('#host_linkage_state_point_edit')        .val('');
            $('#host_linkage_point_display_edit')      .val('');
            $('#host_linkage_state_point_display_edit').val('');
            $('#host_linkage_comments_edit')           .val('');

            // 重置添加验证表单
            editHostValidate.resetForm()
        });

        // 当添加设备模态框关闭时同时关闭 tree 模态框, 清空表单数据
        $('#host_linkage_device_modal_add').on('hide.bs.modal', function () {

            $('#host_linkage_tree_modal').modal('hide');

            $('#host_linkage_device_name_add')            .val('');
            $('#host_linkage_device_point_add')           .val('');
            $('#host_linkage_device_point_display_add')   .val('');
            $('#host_linkage_device_fault_point_add')     .val('0');
            $('#host_linkage_device_fault_fine_value_add').val('');
            $('#host_linkage_device_comments_add')        .val('');

            $('#host_linkage_device_fault_fine_value_add_div').hide();


            // 重置添加验证表单
            addDeviceValidate.resetForm()
        });

        // 当编辑设备模态框关闭时同时关闭 tree 模态框, 清空表单数据
        $('#host_linkage_device_modal_edit').on('hide.bs.modal', function () {
            // pointInputObj = null;
            $('#host_linkage_tree_modal').modal('hide');

            $('#host_linkage_device_name_edit')            .val('');
            $('#host_linkage_device_point_edit')           .val('');
            $('#host_linkage_device_point_display_edit')   .val('');
            $('#host_linkage_device_fault_point_edit')     .val('0');
            $('#host_linkage_device_fault_fine_value_edit').val('');
            $('#host_linkage_device_comments_edit')        .val('');

            $('#host_linkage_device_fault_fine_value_edit_div').hide();

            // 重置添加验证表单
            editDeviceValidate.resetForm()
        });

        // 添加设备故障反馈点下拉框数据变化事件
        $('#host_linkage_device_fault_point_add').change(function (obj)
        {
            var value = obj.target.value;

            var elem = $('#host_linkage_device_fault_fine_value_add_div');

            switch (value)
            {
                case '0':
                    elem.hide();
                    break;
                case '1':
                    elem.show();
                    break;
            }

        });

        // 编辑设备故障反馈点下拉框数据变化事件
        $('#host_linkage_device_fault_point_edit').change(function (obj)
        {
            var value = obj.target.value;

            var elem = $('#host_linkage_device_fault_fine_value_edit_div');

            switch (value)
            {
                case '0':
                    elem.hide();
                    break;
                case '1':
                    elem.show();
                    break;
            }

        });

        /*************************************** 事件（end） ***********************************************************/


        /*************************************** 请求接口（start） ******************************************************/
        // 查询实时数据
        function queryRealTimeData(sysNames, callback)
        {
            if (!Array.isArray(sysNames) || sysNames.length <= 0 || typeof callback !== 'function')
            {
                return;
            }

            $.ajax({
                type    : 'POST',
                url     : _ctx + '/view/basedatamanage/eqmanage/sbdy_treeIconForJK',
                dataType: 'json',
                data:{
                    f_sys_name: sysNames.toString()
                },
                success: function (result) {

                    callback(result);

                },
                error: function (result) {

                    console.warn(result)
                }
            });

        }

        // 获取设备树信息
        function queryTreeData(callback){

            if(typeof callback !== 'function'){
                return;
            }

            $.ajax({
                type    : 'post',
                url     : _ctx + '/view/basedatamanage/eqmanage/austere_devices_tree',
                dataType: 'json',
                success: function (result) {
                    callback(result);
                }
            });
        }

        // 查询机组
        function queryMachineSet(obj, callback)
        {
            if (typeof callback !== 'function')
            {
                return;
            }

            obj = obj || {};

            $.ajax({
                type: "GET",
                url: _ctx + "/view/strongAndWeakElectricityIntegration/deviceConfig/machineSet/query",
                dataType: "json",
                data: obj,
                success: function (result)
                {

                    callback(result);

                },
                error: function (result)
                {

                    console.warn(result)
                }
            });

        }

        // 查询主机信息
        function queryHost(obj, callback) {

            if(typeof callback !== 'function'){
                return;
            }

            obj = obj || {};

            $.ajax({
                type    : 'GET',
                url     : _ctx + '/view/strongAndWeakElectricityIntegration/hostLinkage/queryHost',
                dataType: 'json',
                data    :  obj,
                success: function (result) {

                    callback(result);

                },
                error: function (result) {

                    console.warn(result);
                }
            });


        }

        // 删除主机信息
        function deleteHost(id, callback) {

            if (!id) {
                console.warn('无效参数！');
                return;
            }

            $.ajax({
                type    : 'GET',
                url     : _ctx + '/view/strongAndWeakElectricityIntegration/hostLinkage/deleteHost',
                dataType: 'json',
                data: {
                    id : id,
                },
                success: function (result) {

                    if(typeof callback === 'function'){
                        callback(result);
                    }

                },
                error: function (result) {
                    swal('删除失败!',null,'error');
                    console.warn(result)
                }
            });

        }

        // 删除设备信息
        function deleteDevice(id, callback) {

            if (!id) {
                console.warn('无效参数！');
                return;
            }

            $.ajax({
                type    : 'GET',
                url     : _ctx + '/view/strongAndWeakElectricityIntegration/hostLinkage/deleteDevice',
                dataType: 'json',
                data: {
                    id : id,
                },
                success: function (result) {

                    if(typeof callback === 'function'){
                        callback(result);
                    }

                },
                error: function (result) {
                    swal('删除失败!',null,'error');
                    console.warn(result)
                }
            });

        }

        // 查询设备信息
        function queryDevice(obj, callback) {

            if(typeof callback !== 'function'){
                return;
            }

            obj = obj || {};

            $.ajax({
                type    : 'GET',
                url     : _ctx + '/view/strongAndWeakElectricityIntegration/hostLinkage/queryDevice',
                dataType: 'json',
                data    :  obj,
                success: function (result) {

                    callback(result);

                },
                error: function (result) {

                    console.warn(result);
                }
            });
        }

        // 查询关联点信息
        function queryJoinPoint(obj, callback)
        {
            if(typeof callback !== 'function'){
                return;
            }

            obj = obj || {};

            $.ajax({
                type    : 'GET',
                url     : _ctx + '/view/strongAndWeakElectricityIntegration/hostLinkage/queryJoinPoint',
                dataType: 'json',
                data    : obj,
                success: function (result) {

                    callback(result);

                },
                error: function (result) {

                    console.warn(result);
                }
            });
        }

        // 创建关联点
        function createJoinPoint(obj, callback)
        {
            if(typeof callback !== 'function'){
                return;
            }

            obj = obj || {};

            $.ajax({
                type    : 'POST',
                url     : _ctx + '/view/strongAndWeakElectricityIntegration/hostLinkage/createJoinPoint',
                dataType: 'json',
                data    : obj,
                success: function (result) {

                    callback(result);

                },
                error: function (result) {

                    console.warn(result);
                }
            });
        }

        // 更新运行模式信息
        function updateJoinPoint(obj, callback)
        {
            if(typeof callback !== 'function'){
                return;
            }

            obj = obj || {};

            $.ajax({
                type    : 'POST',
                url     : _ctx + '/view/strongAndWeakElectricityIntegration/hostLinkage/updateJoinPoint',
                dataType: 'json',
                data    : obj,
                success: function (result) {

                    callback(result);

                },
                error: function (result) {

                    console.warn(result);
                }
            });
        }

        // 启停控制
        function startStopControl(sysName, val) {

            if (!sysName || val == null) {
                return;
            }

            $.ajax({
                url : _ctx + '/api/debugPointInfo',
                type : 'post',
                contentType : 'application/json; charset=utf-8',
                dataType : 'json',
                data : JSON.stringify({
                    f_sys_name : sysName,
                    f_init_val : val,
                    f_work_mode: '1'
                }),
                success : function(result) {

                    var status = result.status;

                    if (status === '1')
                    {
                        swal({
                            title : '命令下发成功',// 展示的标题
                            text : '',// 内容
                            type: 'success',
                            showCloseButton : false, // 展示关闭按钮
                            allowOutsideClick : false,
                            showConfirmButton : false,
                            timer: 1000
                        });
                    }else
                    {
                        swal({
                            title : '命令下发失败',// 展示的标题
                            text : '',// 内容
                            type: 'warning',
                            showCloseButton : false, // 展示关闭按钮
                            allowOutsideClick : false,
                            showConfirmButton : false,
                            timer: 1000
                        });
                    }
                    addGljl(status);
                },
                error : function(result) {
                    // layer.msg('操作失败');
                }
            });
        }
        // 管理记录记录操作
        function addGljl(status){
            $.ajax({
                type    : "POST",
                url     : _ctx + '/view/strongAndWeakElectricityIntegration/hostLinkage/addGljl?status=' + status,
                dataType: 'json'
                });
        }
        // 查询点值映射配置
        function queryPointValueConfig(sysName, callback)
        {
            if (typeof callback !== 'function')
            {
                return;
            }

            var ajaxParam = {
                type    : "POST",
                url     : _ctx + "/btnEventController/getSettingsInfo",
                dataType: "json",
                success: function (result) {
                    callback(result);
                }
            };

            if (sysName)
            {
                ajaxParam.data = {
                    f_sys_name: sysName
                }
            }

            $.ajax(ajaxParam);
        }

        /****************************************** 请求接口（end） *****************************************************/


        /******************************************* 表单验证（start） **************************************************/

        // 添加主机输入框验证
        var addHostValidate = $('#host_linkage_add_form').validate({
            rules: {
                host_linkage_name_add:{
                    required: true,
                    maxlength: 50

                },
                host_linkage_control_point_display_add:{
                    required: true

                },
                host_linkage_state_point_display_add:{
                    required: true

                },
                host_linkage_comments_add:{
                    maxlength: 200

                }
            },
            messages: {
                host_linkage_name_add:{
                    required: '请输入主机名称'
                },
                host_linkage_control_point_display_add:{
                    required: '请选择控制点位'
                },
                host_linkage_state_point_display_add:{
                    required: '请选择状态点位'
                },
            },
            submitHandler: function(formData) {
                addHostSubmit(formData);
            }

        });

        // 编辑主机输入框验证
        var editHostValidate = $('#host_linkage_edit_form').validate({
            rules: {
                host_linkage_name_edit:{
                    required: true,
                    maxlength: 50

                },
                host_linkage_point_display_edit:{
                    required: true

                },
                host_linkage_state_point_display_edit:{
                    required: true

                },
                host_linkage_comments_edit:{
                    maxlength: 200

                }
            },
            messages: {
                host_linkage_name_edit:{
                    required: '请输入主机名称'
                },
                host_linkage_point_display_edit:{
                    required: '请选择控制点位'
                },
                host_linkage_state_point_display_edit:{
                    required: '请选择状态点位'
                },
            },
            submitHandler: function(formData) {
                editHostSubmit(formData);
            }

        });

        // 添加设备输入框验证
        var addDeviceValidate = $('#host_linkage_device_add_form').validate({
            rules: {
                host_linkage_device_name_add:{
                    required: true,
                    maxlength: 50
                },
                host_linkage_device_point_display_add:{
                    required: true,
                    maxlength: 50
                },
                host_linkage_device_fault_point_add:{
                    required: true
                },
                host_linkage_device_fault_fine_value_add:{
                    required: true,
                    digits:true
                },
                host_linkage_device_comments_add:{
                    maxlength: 200
                }
            },
            messages: {
                host_linkage_device_name_add:{
                    required: '请输入设备名称'
                },
                host_linkage_device_point_display_add:{
                    required: '请选择所属点位'
                },
                host_linkage_device_fault_point_add:{
                    required: '请选择是否为故障反馈点'
                },
                host_linkage_device_fault_fine_value_add:{
                    required: '请输入一个故障点的正常值'
                }
            },
            submitHandler: function(formData) {
                addDeviceSubmit(formData);
            }

        });

        // 编辑设备输入框验证
        var editDeviceValidate = $('#host_linkage_device_edit_form').validate({
            rules: {
                host_linkage_device_name_edit:{
                    required: true,
                    maxlength: 50

                },
                host_linkage_device_point_display_edit:{
                    required: true,
                    maxlength: 50

                },
                host_linkage_device_fault_point_edit:{
                    required: true
                },
                host_linkage_device_fault_fine_value_edit:{
                    required: true,
                    digits:true
                },
                host_linkage_device_comments_edit:{
                    maxlength: 200

                }
            },
            messages: {
                host_linkage_device_name_edit:{
                    required: '请输入设备名称'
                },
                host_linkage_device_point_display_edit:{
                    required: '请选择所属点位'
                },
                host_linkage_device_fault_point_edit:{
                    required: '请选择是否为故障反馈点'
                },
                host_linkage_device_fault_fine_value_edit:{
                    required: '请输入一个故障点的正常值'
                }
            },
            submitHandler: function(formData) {
                editDeviceSubmit(formData);
            }

        });

        /***************************************** 表单验证（end） ******************************************************/


        /***************************************** 表单提交（start） ****************************************************/
        // 提交表单信息（主机）
        function addHostSubmit(formData){

            if(!formData){
                return;
            }

            var machineSetId = $('#machineSetSelect').val();

            if (!machineSetId)
            {
                swal({
                    title             : '请选择所属机组',// 展示的标题
                    // text              : '', // 内容
                    type              : 'warning',
                    showCloseButton   : false, // 展示关闭按钮
                    allowOutsideClick : false,
                    showConfirmButton : false,
                    timer             : 1000
                });

                return;
            }

            //获取表单数据
            var formData = form2js(formData ,null, null, null, null, true);

            $.ajax({
                type    : 'POST',
                url     : _ctx + '/view/strongAndWeakElectricityIntegration/hostLinkage/createHost',
                dataType: 'json',
                data: {
                    machineSetId     : machineSetId,
                    name             : formData.host_linkage_name_add,
                    pointDisplay     : formData.host_linkage_control_point_display_add,
                    pointStateDisplay: formData.host_linkage_state_point_display_add,
                    point            : formData.host_linkage_control_point_add,
                    pointState       : formData.host_linkage_state_point_add,
                    comments         : formData.host_linkage_comments_add

                },
                success: function (result) {

                    var status = result && result.status;

                    if(status === '1'){

                        var data = result.data;

                        // 主机信息缓存
                        hostDataCache(data);

                        queryRealTimeData([data.pointState], function (realTimeData)
                        {
                            var list = realTimeData && realTimeData.list;

                            if (Array.isArray(list))
                            {
                               hostData[data.id].runState = list[0].f_init_val;
                            }

                            // 把主机页面元素添加到指定位置
                            addHostRectangleToContainer(null, data.id);
                        });

                        // 添加订阅消息
                        PubSub.subscribe(data.pointState, hostLinkage.hostSubCallback);

                        $('#host_linkage_modal_add').modal('hide');

                        swal({
                            title             : '添加成功',// 展示的标题
                            text              : '', // 内容
                            type              : 'success',
                            showCloseButton   : false, // 展示关闭按钮
                            allowOutsideClick : false,
                            showConfirmButton : false,
                            timer             : 1000
                        });


                    }else {
                        swal({
                            title             : '添加失败',// 展示的标题
                            text              : result.msg, // 内容
                            type              : 'warning',
                            showCloseButton   : false, // 展示关闭按钮
                            allowOutsideClick : false,
                            showConfirmButton : false,
                            timer             : 1000
                        });
                    }

                },
                error: function (result) {

                    console.warn(result)
                }
            });

        }

        // 编辑表单信息（主机）
        function editHostSubmit(formData) {
            if(!formData){
                return;
            }

            //获取表单数据
            var formData = form2js(formData ,null, null, null, null, true);

            var data ={
                id               : checkedHostId,
                name             : formData.host_linkage_name_edit,
                pointDisplay     : formData.host_linkage_point_display_edit,
                pointStateDisplay: formData.host_linkage_state_point_display_edit,
                point            : formData.host_linkage_point_edit,
                pointState       : formData.host_linkage_state_point_edit,
                comments         : formData.host_linkage_comments_edit

            };

            $.ajax({
                type    : 'POST',
                url     : _ctx + '/view/strongAndWeakElectricityIntegration/hostLinkage/updateHost',
                dataType: 'json',
                data: data,
                success: function (result) {

                    var status = result && result.status;

                    if(status === '1'){

                        // 主机信息缓存
                        hostDataCache(data);

                        queryRealTimeData([data.pointState], function (realTimeData)
                        {
                            var list = realTimeData && realTimeData.list;

                            if (Array.isArray(list))
                            {
                                hostData[checkedHostId].runState = list[0].f_init_val;
                            }

                            // 更新主机页面显示
                            updateHostRectangle(checkedHostId);
                        });

                        // 添加订阅消息
                        PubSub.subscribe(data.pointState, hostLinkage.hostSubCallback);

                        // 隐藏编辑模态框
                        $('#host_linkage_modal_edit').modal('hide');

                        swal({
                            title             : '编辑成功',// 展示的标题
                            text              : '', // 内容
                            type              : 'success',
                            showCloseButton   : false, // 展示关闭按钮
                            allowOutsideClick : false,
                            showConfirmButton : false,
                            timer             : 1000
                        });


                    }else {
                        swal({
                            title             : '编辑失败',// 展示的标题
                            text              : result.msg, // 内容
                            type              : 'warning',
                            showCloseButton   : false, // 展示关闭按钮
                            allowOutsideClick : false,
                            showConfirmButton : false,
                            timer             : 1000
                        });
                    }

                },
                error: function (result) {

                    console.warn(result)
                }
            });
        }

        // 提交表单信息（设备）
        function addDeviceSubmit(formData){

            if(!formData){
                return;
            }

            //获取表单数据
            var formData = form2js(formData ,null, null, null, null, true);

            $.ajax({
                type    : 'POST',
                url     : _ctx + '/view/strongAndWeakElectricityIntegration/hostLinkage/createDevice',
                dataType: 'json',
                data: {
                    hostId        : checkedHostId, // 所属主机
                    name          : formData.host_linkage_device_name_add, // 设备名称
                    pointDisplay  : formData.host_linkage_device_point_display_add, // 点的显示名称
                    point         : formData.host_linkage_device_point_add, // 关联的点位
                    isFaultPoint  : formData.host_linkage_device_fault_point_add, // 是否为故障反馈设备 0 否 1 事
                    faultFineValue: formData.host_linkage_device_fault_fine_value_add, // 故障反馈正常值
                    comments      : formData.host_linkage_device_comments_add // 备注

                },
                success: function (result) {

                    var status = result && result.status;

                    if(status === '1'){

                        var data = result.data;

                        // 设备信息缓存
                        deviceDataCache(data);

                        queryRealTimeData([formData.host_linkage_device_point_add], function (realTimeData)
                        {
                            var list = realTimeData && realTimeData.list;

                            if (Array.isArray(list))
                            {
                                deviceData[data.id].value = list[0].f_init_val;
                                deviceData[data.id].unit = list[0].unit || '';
                            }

                            // 把主机页面元素添加到指定位置
                            addDeviceRectangleToContainer(null, data.id);

                            // 更新故障状态
                            updateFaultState();
                        });

                        // 添加订阅消息
                        PubSub.subscribe(data.point, hostLinkage.deviceSubCallback);

                        // 关闭模态框
                        $('#host_linkage_device_modal_add').modal('hide');

                        swal({
                            title             : '添加成功',// 展示的标题
                            text              : '', // 内容
                            type              : 'success',
                            showCloseButton   : false, // 展示关闭按钮
                            allowOutsideClick : false,
                            showConfirmButton : false,
                            timer             : 1000
                        });


                    }else {
                        swal({
                            title             : '添加失败',// 展示的标题
                            text              : result.msg, // 内容
                            type              : 'warning',
                            showCloseButton   : false, // 展示关闭按钮
                            allowOutsideClick : false,
                            showConfirmButton : false,
                            timer             : 1000
                        });
                    }

                },
                error: function (result) {

                    console.warn(result)
                }
            });

        }

        // 编辑表单信息（设备）
        function editDeviceSubmit(formData){

            if(!formData){
                return;
            }

            //获取表单数据
            var formData = form2js(formData ,null, null, null, null, true);

            var data = {
                id            : checkedDeviceId, // 设备id
                name          : formData.host_linkage_device_name_edit, // 设备名称
                pointDisplay  : formData.host_linkage_device_point_display_edit,// 点的显示名称
                point         : formData.host_linkage_device_point_edit, // 关联的点位
                isFaultPoint  : formData.host_linkage_device_fault_point_edit, // 是否为故障反馈设备 0 否 1 事
                faultFineValue: formData.host_linkage_device_fault_fine_value_edit, // 故障反馈正常值
                comments      : formData.host_linkage_device_comments_edit // 备注

            };

            $.ajax({
                type    : 'POST',
                url     : _ctx + '/view/strongAndWeakElectricityIntegration/hostLinkage/updateDevice',
                dataType: 'json',
                data: data,
                success: function (result) {

                    var status = result && result.status;

                    if(status === '1'){

                        // 设备信息缓存
                        deviceDataCache(data);

                        queryRealTimeData([data.point], function (realTimeData)
                        {
                            var list = realTimeData && realTimeData.list;

                            if (Array.isArray(list))
                            {
                                deviceData[data.id].value = list[0].f_init_val;
                                deviceData[data.id].unit = list[0].unit || '';
                            }

                            // 更新设备页面显示
                            updateDeviceRectangle(data.id);

                            // 更新故障状态
                            updateFaultState();
                        });

                        // 添加订阅消息
                        PubSub.subscribe(data.point, hostLinkage.deviceSubCallback);

                        $('#host_linkage_device_modal_edit').modal('hide');

                        swal({
                            title             : '编辑成功',// 展示的标题
                            text              : '', // 内容
                            type              : 'success',
                            showCloseButton   : false, // 展示关闭按钮
                            allowOutsideClick : false,
                            showConfirmButton : false,
                            timer             : 1000
                        });


                    }else {
                        swal({
                            title             : '编辑失败',// 展示的标题
                            text              : result.msg, // 内容
                            type              : 'warning',
                            showCloseButton   : false, // 展示关闭按钮
                            allowOutsideClick : false,
                            showConfirmButton : false,
                            timer             : 1000
                        });
                    }

                },
                error: function (result) {

                    console.warn(result)
                }
            });

        }

        /***************************************** 表单提交（end） ******************************************************/


        /******************************************* 外部调用（start）***************************************************/

        // 点击主机联动div
        function checkedHostRectangleHandle(obj)
        {
            // 选中主机时需要改变的样式
            checkedHostRectangleStyleHandle(obj);
            // 选中主机时需要存储的信息
            checkedHostRectangleStoreHandle(obj);
            // 选中主机时需要显示的页面元素
            checkedHostRectangleShowHandle();
            // 选中主机时加载出和它相关联的设备
            checkedHostRectangleShowLinkDeviceHandle();
            // 选中主机时控制启停控制控制面板需要显示的名称
            checkedHostRectangleShowTitle();
        }

        // 点击设备div
        function checkedDeviceRectangleHandle(obj)
        {
            // 选中设备时需要改变的样式
            checkedDeviceRectangleStyleHandle(obj);
            // 选中设备时需要显示的页面元素
            checkedDeviceRectangleShowHandle();
            // 选中设备时需要存储的信息
            checkedDeviceRectangleStoreHandle(obj);
        }

        // 主机运行状态订阅回调函数
        function hostSubCallback(data)
        {
            if (!data) {
                return;
            }

            var sysName = data.name;
            var val = data.value;

            var siblingsDiv = $('#' + sysName + flag).children().eq(1).children();

            siblingsDiv.eq(2).html(hostStateHtml(val));

            for (var id in hostData)
            {
                var hostDatum = hostData[id];

                if (hostDatum.pointState === sysName)
                {
                    hostDatum.value = val;
                }
            }

        }

        // 设备状态订阅回调函数
        function deviceSubCallback(data)
        {

            if (!data) {
                return;
            }

            var sysName = data.name;
            var val = data.value;
            var unit = data.unit || '';

            var siblingsDiv = $('#' + sysName + flag).children().eq(1).children();

            siblingsDiv.eq(2).text(pointValueConfig[sysName] && pointValueConfig[sysName][val] || val + unit);

            for (var id in deviceData)
            {
                if (deviceData[id].point === sysName)
                {
                    deviceData[id].value = val;
                }
            }

            // 更新故障状态
            updateFaultState();
        }

        // 运行控制模式实时数据处理
        function runningModeRealTimeDataCallback(data)
        {
            if (!data) {
                return;
            }

            var val = data.value;

            updateModelState(val);

            startStopPanelShowHide();
        }
        // 群控实时启停状态实时数据回调
        function groupStartStopRealTimeDataCallback(data)
        {
            if (!data) {
                return;
            }

            var val = data.value;

            updateGroupStartStopState(val);
        }

        /*************************************** 外部调用（end）*********************************************************/


        /*************************************** 主体逻辑处理（start） ***************************************************/
        // 删除主机后的逻辑处理
        function deleteHostAfterHandle(result) {

            var status = result && result.status;

            if(status === '1'){

                delete hostData[checkedHostId]; // 删除缓存中的数据

                if (checkedHostRectangleDiv != null) {
                    $(checkedHostRectangleDiv).remove();
                    checkedHostRectangleDiv = null;
                    checkedHostId = null;
                    // 主机不被选中时需要隐藏的页面元素
                    uncheckedHostRectangleHideHandle();
                }

                swal({
                    title : '删除成功',// 展示的标题
                    text : '',// 内容
                    type: 'success',
                    showCloseButton : false, // 展示关闭按钮
                    allowOutsideClick : false,
                    showConfirmButton : false,
                    timer: 1000
                });

            }else {

                swal({
                    title             : '删除失败',   // 展示的标题
                    text              : result.msg,  // 内容
                    type              : 'warning',
                    showCloseButton   : true         // 展示关闭按钮

                });

            }
        }

        // 删除设备后的逻辑处理
        function deleteDeviceAfterHandle(result, id) {

            var status = result && result.status;

            if(status === '1'){

                // 删除设备信息
                delete deviceData[id];

                if (checkedDeviceRectangleDiv != null) {
                    // 删除页面元素
                    $(checkedDeviceRectangleDiv).remove();
                    checkedDeviceRectangleDiv = null;
                    checkedDeviceId = null;
                    // 隐藏设备编辑和删除按钮
                    hideDeviceRectangleDeleteAndEditButton();
                }

                swal({
                    title : '删除成功',// 展示的标题
                    text : '',// 内容
                    type: 'success',
                    showCloseButton : false, // 展示关闭按钮
                    allowOutsideClick : false,
                    showConfirmButton : false,
                    timer: 1000
                });

            }else {

                swal({
                    title             : '删除失败',   // 展示的标题
                    text              : result.msg,  // 内容
                    type              : 'warning',
                    showCloseButton   : true         // 展示关闭按钮

                });

            }
        }

        // 选中主机时需要改变的样式
        function checkedHostRectangleStyleHandle(obj) {

            if (!obj) {
                return;
            }

            $('.host-rectangle').css('border-width', '1px');

            $(obj).css({
                'border-width': '3px'
            });

        }

        // 选中设备时需要改变的样式
        function checkedDeviceRectangleStyleHandle(obj) {

            if (!obj) {
                return;
            }

            $('.device-rectangle').css('border-width', '1px');

            $(obj).css({
                'border-width': '3px'
            });

        }

        // 选中主机时需要显示的页面元素
        function checkedHostRectangleShowHandle() {

            // 当是单机运行模式的时候才会显示启停控制面板
            startStopPanelShowHide();

            $('#host_linkage_button_edit').show();
            $('#host_linkage_button_delete').show();
            $('#host_linkage_device_button_add').show();
        }

        // 选中设备时需要显示的页面元素
        function checkedDeviceRectangleShowHandle() {
            $('#host_linkage_device_button_edit').show();
            $('#host_linkage_device_button_delete').show();
        }

        // 主机不被选中时需要隐藏的页面元素
        function uncheckedHostRectangleHideHandle() {

            startStopPanelShowHide();

            $('#host_linkage_button_edit').hide();
            $('#host_linkage_button_delete').hide();
            $('#host_linkage_device_button_add').hide();
        }

        // 选中主机时需要存储的信息
        function checkedHostRectangleStoreHandle(obj) {

            if (!obj) {
                console.warn('非法参数！');
                return;
            }

            checkedHostRectangleDiv = obj;
            checkedHostId = $(obj).find(':hidden').text();

        }

        // 选中设备时需要存储的信息
        function checkedDeviceRectangleStoreHandle(obj) {

            if (!obj) {
                console.warn('非法参数！');
                return;
            }

            checkedDeviceRectangleDiv = obj;
            checkedDeviceId = $(obj).find(':hidden').text();

        }

        // 选中主机时加载出和它相关联的设备
        function checkedHostRectangleShowLinkDeviceHandle() {

            // 隐藏设备编辑和删除按钮
            hideDeviceRectangleDeleteAndEditButton();

            // 筛选显示当前主机下的设备
            filterDeviceListCurrentHost();
        }

        // 选中主机时控制启停控制控制面板需要显示的名称
        function checkedHostRectangleShowTitle()
        {
            
            if (runningModeObj == null || runningModeObj.model !== runModel.SINGLE)
            {
                return;
            }

            var hostDatum = hostData[checkedHostId];

            var hostName = (hostDatum && hostDatum.name) || '';

            $('#host_linkage_start_stop_their_host').html(
                '<span>主机：</span>' +
                    '<span style="color: rgb(13, 202, 238);">' + hostName + '</span>');
        }

        // 隐藏设备编辑和删除按钮
        function hideDeviceRectangleDeleteAndEditButton() {
            $('#host_linkage_device_button_edit').hide();
            $('#host_linkage_device_button_delete').hide();
        }

        // 把主机页面元素添加到指定位置
        function addHostRectangleToContainer(container, id) {

            if (!id) {
                console.warn('参数错误！');
                return;
            }

            var hostName = hostData[id].name;
            var state = hostData[id].runState;

            var container = container || 'host_rectangle_container';

            state = hostStateHtml(state);

            var titleHostName = hostName;
            /*if (hostName.length > 6) {
                hostName = hostName.substring(0, 5) + '...';
            }*/

            var pointState = hostData[id].pointState;

            $('#' + container).append(' <div  id="' + pointState + flag + '" class="host-rectangle" onclick="hostLinkage.checkedHostRectangleHandle(this)">' +
                    '                <i class="fa fa-life-ring fa-2x" aria-hidden="true"></i>' +
                    '                <div style="float:left;margin-left:10px;line-height:30px;">' +
                    '                    <div style="display: none">' + id + '</div>' +
                    '                    <div style="margin-top:10px;margin-right: 10px;" title="' + titleHostName + '">' + hostName + '</div>' +
                    '                    <div>' + state + '</div>' +
                    '                </div>' +
                    '            </div>');

        }

        // 把设备页面元素添加到指定位置
        function addDeviceRectangleToContainer(container, id) {

            if (!id) {
                console.warn('参数错误！');
                return;
            }

            var deviceDatum = deviceData[id];

            var deviceName = deviceDatum.name;
            var point = deviceDatum.point;
            var value = deviceDatum.value;

            var container = container || 'device_rectangle_container';

            if (pointValueConfig[point] && pointValueConfig[point][value])
            {
                value = pointValueConfig[point][value];
            }else
            {
                var unit = deviceDatum.unit || '';

                if (unit)
                {
                    value += unit
                }
            }

            value = value || '设备未关联';

            var titleValue = value;

           /* if (value.length > 7) {
                value = value.substring(0, 6) + '...';
            }*/

            var titleDeviceName = deviceName;
            /*if (deviceName.length > 6) {
                deviceName = deviceName.substring(0, 5) + '...';
            }*/

            $('#' + container).append('<div id="' + point + flag + '" class="device-rectangle" onclick="hostLinkage.checkedDeviceRectangleHandle(this)">' +
                    '                <i class="fa fa-life-ring fa-2x" aria-hidden="true"></i>' +
                    '                <div style="float:left;margin-left:10px;margin-top: 4px;">' +
                    '                    <div style="display: none">' + id + '</div>' +
                    '                    <div title="' + titleDeviceName + '" style="margin-right: 10px;">' + deviceName + '</div>' +
                    '                    <div title="' + titleValue + '" style="color:#ffffff;margin-right: 10px;">' + value + '</div>' +
                    '                </div>' +
                    '            </div>');
        }

        // 更新主机页面显示
        function updateHostRectangle(id) {

            if (!id) {
                console.warn('参数错误！');
                return;
            }

            var hostName = hostData[id].name;
            var pointState = hostData[id].pointState;

            var titleHostName = hostName;
            /*if (hostName.length > 6) {
                hostName = hostName.substring(0, 5) + '...';
            }*/

            var siblingsDiv = $(checkedHostRectangleDiv).children().eq(1).children();

            $(checkedHostRectangleDiv).attr('id', pointState + flag);

            siblingsDiv.eq(1).html(hostName);
            siblingsDiv.eq(1).attr('title', titleHostName);

            var state = hostData[id].runState;

            siblingsDiv.eq(2).html(hostStateHtml(state));

        }

        // 更新设备页面显示
        function updateDeviceRectangle(id) {


            if (!id) {
                console.warn('参数错误！');
                return;
            }

            var deviceDatum = deviceData[id];

            var deviceName = deviceDatum.name;
            var point = deviceDatum.point;
            var value = deviceDatum.value;

            if (pointValueConfig[point] && pointValueConfig[point][value])
            {
                value = pointValueConfig[point][value];
            }else
            {
                var unit = deviceDatum.unit || '';

                if (unit)
                {
                    value += unit
                }
            }


            var titleValue = value;

            /*if (value.length > 7) {
                value = value.substring(0, 6) + '...';
            }*/

            var titleDeviceName = deviceName;
           /* if (deviceName.length > 6) {
                deviceName = deviceName.substring(0, 5) + '...';
            }*/

            var siblingsDiv = $(checkedDeviceRectangleDiv).children().eq(1).children();

            $(checkedDeviceRectangleDiv).attr('id', point + flag);

            siblingsDiv.eq(1).text(deviceName).attr('title', titleDeviceName);

            siblingsDiv.eq(2).text(value).attr('title', titleValue);
        }

        // 主机状态 html 样式
        function hostStateHtml(state)
        {
            if (state === '255') {
                return '<span style="color:#1fff08;">已运行</span>';
            } else if (state === '0') {
                return '<span style="color:red;">已停止</span>';
            }else {
                console.warn('无效的主机状态，期望值是 0 或者 255 实际值' + state );
                return '';
            }
        }

        // 主机信息缓存
        function hostDataCache(data)
        {
            if (!data)
            {
                return;
            }

            var id = data.id;

            if (id == null)
            {
                return;
            }

            var hostDatum = hostData[id];

            var name = data.name;
            var machineSetId = data.machineSetId;
            var pointDisplay = data.pointDisplay;
            var point = data.point;
            var pointStateDisplay = data.pointStateDisplay;
            var pointState = data.pointState;
            var comments = data.comments;

            if (!pointDisplay)
            {
                return;
            }

            if (!point)
            {
                return;
            }

            if (!hostDatum)
            {
                hostData[id] = data;
                return;
            }

            if (name)
            {
                hostDatum.name = name;
            }
            if (comments != null)
            {
                hostDatum.comments = comments;
            }


            hostDatum.machineSetId = machineSetId;
            hostDatum.pointDisplay = pointDisplay;
            hostDatum.pointStateDisplay = pointStateDisplay;
            hostDatum.point = point;
            hostDatum.pointState = pointState;

        }

        // 设备信息缓存
        function deviceDataCache(data)
        {
            if (!data)
            {
                return;
            }

            var id = data.id;

            if (id == null)
            {
                return;
            }

            var deviceDatum = deviceData[id];

            var name = data.name;
            var pointDisplay = data.pointDisplay;
            var point = data.point;
            var isFaultPoint = data.isFaultPoint;
            var faultFineValue = data.faultFineValue;
            var comments = data.comments;

            if (!pointDisplay)
            {
                return;
            }

            if (!point)
            {
                return;
            }

            if (isFaultPoint == null)
            {
                return;
            }

            if (!deviceDatum)
            {
                deviceData[id] = data;
                return;
            }

            if (name)
            {
                deviceDatum.name = name;
            }
            if (comments != null)
            {
                deviceDatum.comments = comments;
            }
            if (faultFineValue != null)
            {
                deviceDatum.faultFineValue = faultFineValue;
            }

            deviceDatum.isFaultPoint = isFaultPoint;
            deviceDatum.pointDisplay = pointDisplay;
            deviceDatum.point = point;

        }

        // 启动联动
        function startDevices() {

            var model = runningModeObj.model;

            if (model === runModel.SINGLE)
            {
                var point = hostData[checkedHostId].point;
                startStopControl(point, 255);
                return;
            }

            if (model === runModel.GROUP)
            {
                var point = startStopGroupInfo.joinPoint;
                startStopControl(point, 255);
            }
        }

        // 停止联动
        function stopDevices() {
            var model = runningModeObj.model;

            if (model === runModel.SINGLE)
            {
                var point = hostData[checkedHostId].point;
                startStopControl(point, 0);
                return;
            }

            if (model === runModel.GROUP)
            {
                var point = startStopGroupInfo.joinPoint;
                startStopControl(point, 0);
            }

        }

        // 更新模式状态
        function updateModelState(value)
        {
            if (typeof value !== 'string')
            {
                return;
            }

            if (value === '255') {

                runningModeObj.model = '255';

                $('#host_linkage_current_mode').text('群控').css('color', '#1fff08');

            }else if (value === '0') {

                runningModeObj.model = '0';

                $('#host_linkage_current_mode').text('单机').css('color', '#1fff08');
            }

        }

        // 更新群控启停状态
        function updateGroupStartStopState(value)
        {
            if (typeof value !== 'string')
            {
                return;
            }

            if (value === '255') {

                startStopStateGroupInfo.startStopState = '255';

                $('#host_linkage_group_start_stop_state').text('已运行').css('color', '#1fff08');


            }else if (value === '0') {

                startStopStateGroupInfo.startStopState = '0';

                $('#host_linkage_group_start_stop_state').text('已停止').css('color', 'red');
            }

        }

        // 模式关联关联点处理
        function joinPointHandle(node)
        {
            if (!node)
            {
                return;
            }

            var V_POINT = '16'; // 虚点类型
            var DO = '13'; // do 点类型
            var type = node.f_type;

            if (type !== V_POINT && type !== DO)
            {
                swal({
                    title : '必须关联一个虚点或者DO点类型',// 展示的标题
                    text : '',// 内容
                    type: 'warning',
                    showCloseButton : true, // 展示关闭按钮
                    allowOutsideClick : false,
                    confirmButtonText : '确定',
                    closeOnConfirm : false
                });

                return;
            }

            var f_nick_name = node.f_nick_name;

            if ('DO类型' === f_nick_name)
            {
                swal({
                    title : '不能关联一个未配置的DO点类型',// 展示的标题
                    text : '',// 内容
                    type: 'warning',
                    showCloseButton : true, // 展示关闭按钮
                    allowOutsideClick : false,
                    confirmButtonText : '确定',
                    closeOnConfirm : false
                });
                return;
            }

            swal({
                title : '您确定要关联该点吗?',
                text : '',
                type : 'warning',
                showCancelButton : true,
                confirmButtonText : '确定',
                closeOnConfirm : true
            }, function() {

                // 所属机组id
                var machineSetId = $('#machineSetSelect').val();

                var f_sys_name = node.f_sys_name;

                // 回显选中的节点
                tree.searchPrecise(f_sys_name, 'f_sys_name');

                var joinPoint = runningModeObj && runningModeObj.joinPoint;

                if (!joinPoint)
                {
                    createJoinPoint({
                        joinPoint: f_sys_name,
                        type: pointType.MODEL,
                        machineSetId: machineSetId
                    }, function (result)
                    {
                        if (result && result.status === '1')
                        {
                            setTimeout(function ()
                            {
                                swal({
                                    title : '关联成功',// 展示的标题
                                    text : '', // 内容
                                    type: 'success',
                                    showCloseButton : false, // 展示关闭按钮
                                    allowOutsideClick : false,
                                    showConfirmButton : false,
                                    timer: 1000
                                });
                            }, 200);

                            getRunningModeInfo();

                        }else
                        {
                            setTimeout(function ()
                            {
                                swal({
                                    title : '关联失败',// 展示的标题
                                    text : '', // 内容
                                    type: 'warning',
                                    showCloseButton : false, // 展示关闭按钮
                                    allowOutsideClick : false,
                                    showConfirmButton : false,
                                    timer: 1000
                                });
                            }, 200);
                        }

                    });

                    return;
                }

                if (joinPoint !== f_sys_name)
                {

                    var id = runningModeObj.id;

                    updateJoinPoint({
                        id: id,
                        joinPoint: f_sys_name,
                        type: pointType.MODEL,
                        machineSetId: machineSetId
                    }, function (result)
                    {
                        if (result && result.status === '1')
                        {
                            setTimeout(function ()
                            {
                                swal({
                                    title : '修改成功',// 展示的标题
                                    text : '', // 内容
                                    type: 'success',
                                    showCloseButton : false, // 展示关闭按钮
                                    allowOutsideClick : false,
                                    showConfirmButton : false,
                                    timer: 1000
                                });
                            }, 200);

                            getRunningModeInfo();
                        }else
                        {
                            setTimeout(function ()
                            {
                                swal({
                                    title : '修改失败',// 展示的标题
                                    text : '', // 内容
                                    type: 'warning',
                                    showCloseButton : false, // 展示关闭按钮
                                    allowOutsideClick : false,
                                    showConfirmButton : false,
                                    timer: 1000
                                });
                            }, 200);
                        }

                    })
                }

            });

        }
        // 启停关联关联点处理
        function startStopGroupJoinPointHandle(node)
        {
            if (!node)
            {
                return;
            }

            var V_POINT = '16'; // 虚点类型
            var DO = '13'; // do 点类型
            var type = node.f_type;

            if (type !== V_POINT && type !== DO)
            {
                swal({
                    title : '必须关联一个虚点或者DO点类型',// 展示的标题
                    text : '',// 内容
                    type: 'warning',
                    showCloseButton : true, // 展示关闭按钮
                    allowOutsideClick : false,
                    confirmButtonText : '确定',
                    closeOnConfirm : false
                });

                return;
            }

            var f_nick_name = node.f_nick_name;

            if ('DO类型' === f_nick_name)
            {
                swal({
                    title : '不能关联一个未配置的DO点类型',// 展示的标题
                    text : '',// 内容
                    type: 'warning',
                    showCloseButton : true, // 展示关闭按钮
                    allowOutsideClick : false,
                    confirmButtonText : '确定',
                    closeOnConfirm : false
                });
                return;
            }

            swal({
                title : '您确定要关联该点吗?',
                text : '',
                type : 'warning',
                showCancelButton : true,
                confirmButtonText : '确定',
                closeOnConfirm : true
            }, function() {

                var machineSetId = $('#machineSetSelect').val();

                var f_sys_name = node.f_sys_name;

                // 回显选中的节点
                tree.searchPrecise(f_sys_name, 'f_sys_name');

                var joinPoint = startStopGroupInfo && startStopGroupInfo.joinPoint;

                if (!joinPoint)
                {
                    createJoinPoint({
                        joinPoint: f_sys_name,
                        type: pointType.START_STOP,
                        machineSetId: machineSetId
                    }, function (result)
                    {
                        if (result && result.status === '1')
                        {
                            setTimeout(function ()
                            {
                                swal({
                                    title : '关联成功',// 展示的标题
                                    text : '', // 内容
                                    type: 'success',
                                    showCloseButton : false, // 展示关闭按钮
                                    allowOutsideClick : false,
                                    showConfirmButton : false,
                                    timer: 1000
                                });
                            }, 200);


                            getGroupStartStopInfo(startStopPanelShowHide);


                        }else
                        {
                            setTimeout(function ()
                            {
                                swal({
                                    title : '关联失败',// 展示的标题
                                    text : '', // 内容
                                    type: 'warning',
                                    showCloseButton : false, // 展示关闭按钮
                                    allowOutsideClick : false,
                                    showConfirmButton : false,
                                    timer: 1000
                                });
                            }, 200);
                        }

                    });

                    return;
                }

                if (joinPoint !== f_sys_name)
                {

                    var id = startStopGroupInfo.id;

                    updateJoinPoint({
                        id: id,
                        joinPoint: f_sys_name,
                        type: pointType.START_STOP,
                        machineSetId: machineSetId
                    }, function (result)
                    {
                        if (result && result.status === '1')
                        {
                            setTimeout(function ()
                            {
                                swal({
                                    title : '修改成功',// 展示的标题
                                    text : '', // 内容
                                    type: 'success',
                                    showCloseButton : false, // 展示关闭按钮
                                    allowOutsideClick : false,
                                    showConfirmButton : false,
                                    timer: 1000
                                });
                            }, 200);

                            // 获取获取群控的启停信息
                            getGroupStartStopInfo();
                        }else
                        {
                            setTimeout(function ()
                            {
                                swal({
                                    title : '修改失败',// 展示的标题
                                    text : '', // 内容
                                    type: 'warning',
                                    showCloseButton : false, // 展示关闭按钮
                                    allowOutsideClick : false,
                                    showConfirmButton : false,
                                    timer: 1000
                                });
                            }, 200);
                        }

                    })
                }

            });

        }
        // 启停状态关联关联点处理
        function startStopStateGroupJoinPointHandle(node)
        {
            if (!node)
            {
                return;
            }


            var V_POINT = '16'; // 虚点类型
            var DI = '12'; // do 点类型
            var type = node.f_type;

            if (type !== V_POINT && type !== DI)
            {
                swal({
                    title : '必须关联一个虚点或者DI点类型',// 展示的标题
                    text : '',// 内容
                    type: 'warning',
                    showCloseButton : true, // 展示关闭按钮
                    allowOutsideClick : false,
                    confirmButtonText : '确定',
                    closeOnConfirm : false
                });

                return;
            }

            var f_nick_name = node.f_nick_name;

            if ('DI类型' === f_nick_name)
            {
                swal({
                    title : '不能关联一个未配置的DI点类型',// 展示的标题
                    text : '',// 内容
                    type: 'warning',
                    showCloseButton : true, // 展示关闭按钮
                    allowOutsideClick : false,
                    confirmButtonText : '确定',
                    closeOnConfirm : false
                });

                return;
            }

            swal({
                title : '您确定要关联该点吗?',
                text : '',
                type : 'warning',
                showCancelButton : true,
                confirmButtonText : '确定',
                closeOnConfirm : true
            }, function() {

                var machineSetId = $('#machineSetSelect').val();

                var f_sys_name = node.f_sys_name;

                // 回显选中的节点
                tree.searchPrecise(f_sys_name, 'f_sys_name');

                var joinPoint = startStopStateGroupInfo && startStopStateGroupInfo.joinPoint;

                if (!joinPoint)
                {
                    createJoinPoint({
                        joinPoint: f_sys_name,
                        type: pointType.START_STOP_STATE,
                        machineSetId: machineSetId
                    }, function (result)
                    {
                        if (result && result.status === '1')
                        {
                            setTimeout(function ()
                            {
                                swal({
                                    title : '关联成功',// 展示的标题
                                    text : '', // 内容
                                    type: 'success',
                                    showCloseButton : false, // 展示关闭按钮
                                    allowOutsideClick : false,
                                    showConfirmButton : false,
                                    timer: 1000
                                });
                            }, 200);


                            getGroupStartStopStateInfo();

                        }else
                        {
                            setTimeout(function ()
                            {
                                swal({
                                    title : '关联失败',// 展示的标题
                                    text : '', // 内容
                                    type: 'warning',
                                    showCloseButton : false, // 展示关闭按钮
                                    allowOutsideClick : false,
                                    showConfirmButton : false,
                                    timer: 1000
                                });
                            }, 200);
                        }

                    });

                    return;
                }

                if (joinPoint !== f_sys_name)
                {

                    var id = startStopGroupInfo.id;

                    updateJoinPoint({
                        id: id,
                        joinPoint: f_sys_name,
                        type: pointType.START_STOP_STATE,
                        machineSetId: machineSetId
                    }, function (result)
                    {
                        if (result && result.status === '1')
                        {
                            setTimeout(function ()
                            {
                                swal({
                                    title : '修改成功',// 展示的标题
                                    text : '', // 内容
                                    type: 'success',
                                    showCloseButton : false, // 展示关闭按钮
                                    allowOutsideClick : false,
                                    showConfirmButton : false,
                                    timer: 1000
                                });
                            }, 200);

                            // 获取获取群控的启停信息
                            getGroupStartStopStateInfo();
                        }else
                        {
                            setTimeout(function ()
                            {
                                swal({
                                    title : '修改失败',// 展示的标题
                                    text : '', // 内容
                                    type: 'warning',
                                    showCloseButton : false, // 展示关闭按钮
                                    allowOutsideClick : false,
                                    showConfirmButton : false,
                                    timer: 1000
                                });
                            }, 200);
                        }

                    })
                }

            });

        }

        // 主机控制点位关联点处理
        function hostJoinPointHandle(node, elem)
        {
            if (!node || !elem)
            {
                return;
            }

            var V_POINT = '16'; // 虚点类型
            var DO = '13'; // do 点类型
            var pointType = node.f_type;

            if (pointType !== V_POINT && pointType !== DO)
            {
                swal({
                    title : '必须关联一个虚点或者DO点类型',// 展示的标题
                    text : '',// 内容
                    type: 'warning',
                    showCloseButton : true, // 展示关闭按钮
                    allowOutsideClick : false,
                    confirmButtonText : '确定',
                    closeOnConfirm : false
                });

                return;
            }

            var f_nick_name = node.f_nick_name;

            if ('DO类型' === f_nick_name)
            {
                swal({
                    title : '不能关联一个未配置的DO点类型',// 展示的标题
                    text : '',// 内容
                    type: 'warning',
                    showCloseButton : true, // 展示关闭按钮
                    allowOutsideClick : false,
                    confirmButtonText : '确定',
                    closeOnConfirm : false
                });

                return;
            }



            var f_sys_name = node.f_sys_name;

            if ($('#host_linkage_state_point_add').val() === f_sys_name)
            {
                swal({
                    title : '该点位已关联，不能重复关联',// 展示的标题
                    text : '',// 内容
                    type: 'warning',
                    showCloseButton : true, // 展示关闭按钮
                    allowOutsideClick : false,
                    confirmButtonText : '确定',
                    closeOnConfirm : false
                });

                return;
            }

            for (var item in hostData)
            {
                if (hostData[item].point === f_sys_name || hostData[item].pointState === f_sys_name)
                {
                    swal({
                        title : '该点位已关联，不能重复关联',// 展示的标题
                        text : '',// 内容
                        type: 'warning',
                        showCloseButton : true, // 展示关闭按钮
                        allowOutsideClick : false,
                        confirmButtonText : '确定',
                        closeOnConfirm : false
                    });

                    return;
                }
            }

            for (var item in deviceData)
            {
                if (deviceData[item].point === f_sys_name)
                {
                    swal({
                        title : '该点位已设备关联，不能重复关联',// 展示的标题
                        text : '',// 内容
                        type: 'warning',
                        showCloseButton : true, // 展示关闭按钮
                        allowOutsideClick : false,
                        confirmButtonText : '确定',
                        closeOnConfirm : false
                    });

                    return;
                }
            }

            elem.value = f_nick_name;
            $(elem).prev().val(f_sys_name);
        }
        // 主机状态点位关联点处理
        function hostStateJoinPointHandle(node, elem)
        {
            if (!node || !elem)
            {
                return;
            }

            var V_POINT = '16'; // 虚点类型
            var DI = '12'; // do 点类型
            var pointType = node.f_type;

            if (pointType !== V_POINT && pointType !== DI)
            {
                swal({
                    title : '必须关联一个虚点或者DI点类型',// 展示的标题
                    text : '',// 内容
                    type: 'warning',
                    showCloseButton : true, // 展示关闭按钮
                    allowOutsideClick : false,
                    confirmButtonText : '确定',
                    closeOnConfirm : false
                });

                return;
            }

            var f_nick_name = node.f_nick_name;

            if ('DI类型' === f_nick_name)
            {
                swal({
                    title : '不能关联一个未配置的DI点类型',// 展示的标题
                    text : '',// 内容
                    type: 'warning',
                    showCloseButton : true, // 展示关闭按钮
                    allowOutsideClick : false,
                    confirmButtonText : '确定',
                    closeOnConfirm : false
                });

                return;
            }

            var f_sys_name = node.f_sys_name;

            if ($('#host_linkage_control_point_add').val() === f_sys_name)
            {
                swal({
                    title : '该点位已关联，不能重复关联',// 展示的标题
                    text : '',// 内容
                    type: 'warning',
                    showCloseButton : true, // 展示关闭按钮
                    allowOutsideClick : false,
                    confirmButtonText : '确定',
                    closeOnConfirm : false
                });

                return;
            }

            for (var item in hostData)
            {
                if (hostData[item].pointState === f_sys_name || hostData[item].pointState === f_sys_name)
                {
                    swal({
                        title : '该点位已关联，不能重复关联',// 展示的标题
                        text : '',// 内容
                        type: 'warning',
                        showCloseButton : true, // 展示关闭按钮
                        allowOutsideClick : false,
                        confirmButtonText : '确定',
                        closeOnConfirm : false
                    });

                    return;
                }
            }

            for (var item in deviceData)
            {
                if (deviceData[item].point === f_sys_name)
                {
                    swal({
                        title : '该点位已设备关联，不能重复关联',// 展示的标题
                        text : '',// 内容
                        type: 'warning',
                        showCloseButton : true, // 展示关闭按钮
                        allowOutsideClick : false,
                        confirmButtonText : '确定',
                        closeOnConfirm : false
                    });

                    return;
                }
            }

            elem.value = f_nick_name;
            $(elem).prev().val(f_sys_name);
        }

        // 设备关联点处理
        function deviceJoinPointHandle(node, elem)
        {
            if (!node || !elem)
            {
                return;
            }

            var V_POINT = '16'; // 虚点类型
            var AI = '10'; // ai 点类型
            var DI = '12'; // di 点类型
            var pointType = node.f_type;

            if (pointType !== V_POINT && pointType !== AI && pointType !== DI)
            {
                swal({
                    title : '必须关联一个虚点或者AI点或者DI点类型',// 展示的标题
                    text : '',// 内容
                    type: 'warning',
                    showCloseButton : true, // 展示关闭按钮
                    allowOutsideClick : false,
                    confirmButtonText : '确定',
                    closeOnConfirm : false
                });

                return;
            }

            var f_nick_name = node.f_nick_name;

            if ('AI类型' === f_nick_name)
            {
                swal({
                    title : '不能关联一个未配置的AI点类型',// 展示的标题
                    text : '',// 内容
                    type: 'warning',
                    showCloseButton : true, // 展示关闭按钮
                    allowOutsideClick : false,
                    confirmButtonText : '确定',
                    closeOnConfirm : false
                });

                return;
            }
            if ('DI类型' === f_nick_name)
            {
                swal({
                    title : '不能关联一个未配置的DI点类型',// 展示的标题
                    text : '',// 内容
                    type: 'warning',
                    showCloseButton : true, // 展示关闭按钮
                    allowOutsideClick : false,
                    confirmButtonText : '确定',
                    closeOnConfirm : false
                });

                return;
            }

            var f_sys_name = node.f_sys_name;

            for (var item in deviceData)
            {
                if (deviceData[item].point === f_sys_name)
                {
                    swal({
                        title : '该点位已关联，不能重复关联',// 展示的标题
                        text : '',// 内容
                        type: 'warning',
                        showCloseButton : true, // 展示关闭按钮
                        allowOutsideClick : false,
                        confirmButtonText : '确定',
                        closeOnConfirm : false
                    });

                    return;
                }
            }

            for (var item in hostData)
            {
                if (hostData[item].point === f_sys_name
                        || hostData[item].pointState === f_sys_name)
                {
                    swal({
                        title : '该点位已主机关联，不能重复关联',// 展示的标题
                        text : '',// 内容
                        type: 'warning',
                        showCloseButton : true, // 展示关闭按钮
                        allowOutsideClick : false,
                        confirmButtonText : '确定',
                        closeOnConfirm : false
                    });

                    return;
                }
            }

            elem.value = node.f_nick_name;
            $(elem).prev().val(node.f_sys_name);
        }

        // 判断是否有正在运行的主机
        function isHasRunHost()
        {
            for (var id in hostData)
            {
                if (hostData[id].runState === '255')
                {
                    return true;
                }

            }

            return false;
        }

        // 筛选只显示所有主机
        function filterHostListAll()
        {
            filterTypeHost = hostFilterType.ALL;
            showByFilterTypeHostText();
            showByFilterTypeHostDiv();
        }

        // 筛选只显示当前运行的主机
        function filterHostListActive()
        {
            filterTypeHost = hostFilterType.RUN;
            // 根据筛选类型显示文字提示（主机）
            showByFilterTypeHostText();
            showByFilterTypeHostDiv();
        }

        // 筛选只显示当前停止运行的主机
        function filterHostListInactive()
        {
            filterTypeHost = hostFilterType.STOPPED;
            showByFilterTypeHostText();
            showByFilterTypeHostDiv();
        }

        // 筛选只显示当前故障的主机
        function filterHostListFault()
        {
            filterTypeHost = hostFilterType.FAULTED;
            showByFilterTypeHostText();
            showByFilterTypeHostDiv();
        }

        // 筛选显示全部设备
        function filterDeviceListAll()
        {
            filterTypeDevice = deviceFilterType.ALL;
            showByFilterTypeDeviceText();
            showByFilterTypeDeviceDiv();
        }

        // 筛选显示当前主机下的设备
        function filterDeviceListCurrentHost()
        {
            filterTypeDevice = deviceFilterType.SLAVE;
            showByFilterTypeDeviceText();
            showByFilterTypeDeviceDiv();
        }

        // 根据筛选类型显示文字提示（主机）
        function showByFilterTypeHostText()
        {
            var pageElem = $('#host_linkage_current_filter');

            switch (filterTypeHost)
            {
                case hostFilterType.ALL: // 全部

                    pageElem.text('全部').css('color', '#0dcaee');

                    break;
                case hostFilterType.RUN: // 已运行

                    pageElem.text('已运行').css('color', '#1fff08');

                    break;
                case hostFilterType.STOPPED: // 已停止

                    pageElem.text('已停止').css('color', 'red');

                    break;
                case hostFilterType.FAULTED: // 已故障

                    pageElem.text('已故障').css('color', 'red');

                    break;
            }
        }

        // 根据筛选类型显示文字提示（设备）
        function showByFilterTypeDeviceText()
        {
            var pageElem = $('#host_linkage_device_current_filter');

            switch (filterTypeDevice)
            {
                case deviceFilterType.ALL: // 全部

                    pageElem.text('全部').css('color', '#0dcaee');

                    break;
                case deviceFilterType.SLAVE: // 当前主机

                    pageElem.text('当前主机').css('color', '#0dcaee');

                    break;
            }
        }

        // 根据筛选条件显示主机div
        function showByFilterTypeHostDiv()
        {

            var machineSetSelectValue = $('#machineSetSelect').val();

            switch (filterTypeHost)
            {
                case hostFilterType.ALL: // 全部

                    for (var id in hostData)
                    {

                        if (machineSetSelectValue === '' || machineSetSelectValue == hostData[id].machineSetId)
                        {
                            $('#' + hostData[id].pointState + flag).show();
                        }else
                        {
                            $('#' + hostData[id].pointState + flag).hide();
                        }
                    }

                    break;
                case hostFilterType.RUN: // 已运行

                    for (var id in hostData)
                    {
                        if (hostData[id].runState === '255' && (machineSetSelectValue === '' || machineSetSelectValue == hostData[id].machineSetId))
                        {
                            $('#' + hostData[id].pointState + flag).show();
                        }else
                        {
                            $('#' + hostData[id].pointState + flag).hide();
                        }
                    }

                    break;
                case hostFilterType.STOPPED: // 已停止

                    for (var id in hostData)
                    {
                        if (hostData[id].runState === '0' && (machineSetSelectValue === '' || machineSetSelectValue == hostData[id].machineSetId))
                        {
                            $('#' + hostData[id].pointState + flag).show();
                        }else
                        {
                            $('#' + hostData[id].pointState + flag).hide();
                        }
                    }
                    break;
                case hostFilterType.FAULTED: // 已故障

                    for (var id in hostData)
                    {
                        if (hostData[id].alarmState === hostAlarmState.YES && (machineSetSelectValue === '' || machineSetSelectValue == hostData[id].machineSetId))
                        {
                            $('#' + hostData[id].pointState + flag).show();
                        }else
                        {
                            $('#' + hostData[id].pointState + flag).hide();
                        }
                    }

                    break;
            }

        }

        // 根据筛选条件显示设备div
        function showByFilterTypeDeviceDiv()
        {

            // 取消设备选中状态
            cancelDeviceSelected();

            switch (filterTypeDevice)
            {
                case deviceFilterType.ALL: // 全部

                    for (var id in deviceData)
                    {
                        $('#' + deviceData[id].point + flag).show();
                    }

                    // 取消主机选中状态
                    cancelHostSelected();

                    break;
                case deviceFilterType.SLAVE: // 当前主机下

                    if (checkedHostId == null)
                    {
                        break;
                    }

                    for (var id in deviceData)
                    {

                        if (deviceData[id].hostId == checkedHostId)
                        {
                            $('#' + deviceData[id].point + flag).show();
                        }else
                        {
                            $('#' + deviceData[id].point + flag).hide();
                        }
                    }

                    break;
            }
        }

        // 获取所有点值映射配置
        function getPointValueConfig(callback)
        {
            queryPointValueConfig(null, function (result)
            {
                var list = result && result.list;

                if (!Array.isArray(list))
                {
                    return;
                }

                for (let i = 0; i < list.length; i++)
                {

                    var f_sys_name = list[i].f_sys_name;
                    var f_value = list[i].f_value;
                    var f_desc = list[i].f_desc;

                    if (pointValueConfig[f_sys_name])
                    {
                        pointValueConfig[f_sys_name][f_value] = f_desc;

                    }else
                    {
                        pointValueConfig[f_sys_name] = {
                            [f_value] : f_desc
                        }
                    }
                }

                if (typeof callback === 'function')
                {
                    callback();
                }
            });
        }

        // 取消主机选中状态
        function cancelHostSelected()
        {
            $(checkedHostRectangleDiv).css('border-width', '1px');

            checkedHostRectangleDiv = null;
            checkedHostId = null;
            // 主机不被选中时需要隐藏的页面元素
            uncheckedHostRectangleHideHandle();
        }
        // 取消设备选中状态
        function cancelDeviceSelected()
        {
            $(checkedDeviceRectangleDiv).css('border-width', '1px');

            checkedDeviceRectangleDiv = null;
            checkedDeviceId = null;
            // 隐藏设备编辑和删除按钮
            hideDeviceRectangleDeleteAndEditButton();
        }

        // 更新故障状态
        function updateFaultState()
        {
            var hostFaultSet = new Set();

            for (var deviceId in deviceData)
            {
                var deviceDatum = deviceData[deviceId];
                var isFaultPoint = deviceDatum.isFaultPoint;
                var hostId = deviceDatum.hostId;
                var devicePoint = deviceDatum.point;
                var value = deviceDatum.value;

                if (isFaultPoint == 1)
                {
                    // 故障正常值
                    var faultFineValue = deviceDatum.faultFineValue;

                    if (faultFineValue != value)
                    {
                        hostFaultSet.add(hostId.toString());

                        $('#' + devicePoint + flag).css('border-color', 'red');
                    }
                    else
                    {
                        $('#' + devicePoint + flag).css('border-color', '#2b8bc6');
                    }
                }
                else
                {
                    $('#' + devicePoint + flag).css('border-color', '#2b8bc6');
                }
            }

            for (var id in hostData)
            {
                var hostDatum = hostData[id];
                var hostPoint = hostDatum.pointState;

                if (hostFaultSet.has(id))
                {
                    hostDatum.alarmState = hostAlarmState.YES;
                    $('#' + hostPoint + flag).css('border-color', 'red');
                }
                else
                {
                    hostDatum.alarmState = hostAlarmState.NO;
                    $('#' + hostPoint + flag).css('border-color', '#2b8bc6');
                }
            }

        }

        // 启停控制面板显示与隐藏
        function startStopPanelShowHide()
        {
            var elem = $('#host_linkage_start_stop_operation_button');

            var machineSetSelectValue = $('#machineSetSelect').val();

            if (runningModeObj && runningModeObj.machineSetId == machineSetSelectValue)
            {
                var machineSetId = hostData[checkedHostId] && hostData[checkedHostId].machineSetId;

                if (runningModeObj.model === runModel.SINGLE && machineSetId == machineSetSelectValue)
                {
                    var hostDatum = hostData[checkedHostId];

                    var hostName = (hostDatum && hostDatum.name) || '';

                    $('#host_linkage_start_stop_their_host').html(
                            '<span>主机：</span>' +
                            '<span style="color: rgb(13, 202, 238);">' + hostName + '</span>');

                    elem.show();
                    return;
                }

                if (startStopGroupInfo && runningModeObj.model === runModel.GROUP)
                {
                    $('#host_linkage_start_stop_their_host').empty();

                    elem.show();
                    return;
                }

            }

            elem.hide();
        }

        /*************************************** 主体逻辑处理（end） *****************************************************/

        return {

            // 点击主机联动div
            checkedHostRectangleHandle,
            // 点击主机联动div
            checkedDeviceRectangleHandle,
            // 主机运行状态订阅回调函数
            hostSubCallback,
            // 设备状态订阅回调函数
            deviceSubCallback,
            // 运行控制模式实时数据处理
            runningModeRealTimeDataCallback,
            // 群控实时启停状态实时数据回调
            groupStartStopRealTimeDataCallback,
        }


    })(jQuery, window, document);
</script>
