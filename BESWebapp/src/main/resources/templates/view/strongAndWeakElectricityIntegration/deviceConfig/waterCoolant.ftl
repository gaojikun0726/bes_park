<#--
 xiepufeng

 冷却水配置页面
-->

<style>
    .ztree li a.curSelectedNode {
        padding-top: 0px;
        background-color: rgba(31, 255, 8, 0);
        color: black;
        height: 16px;
        border: 1px #FFB951 solid;
        opacity: 0.8;
    }

    .form-control_device_config{display:block;width:94%;height:34px;padding:6px 12px;font-size:14px;line-height:1.42857143;color:#555;background-color:#fff;background-image:none;border:1px solid #ccc;border-radius:4px;-webkit-box-shadow:inset 0 1px 1px rgba(0,0,0,.075);box-shadow:inset 0 1px 1px rgba(0,0,0,.075);-webkit-transition:border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;-o-transition:border-color ease-in-out .15s,box-shadow ease-in-out .15s;transition:border-color ease-in-out .15s,box-shadow ease-in-out .15s}

    #tree_device_type_modal_water_coolant {
        position: absolute;
        left:71%;
        top: 11.5%;
    }
</style>

<div class="information-model">
			<span class="Subtitle">
				<i class="fa fa-user" aria-hidden="true"></i>&nbsp;冷却水配置列表>>>
			</span>
    <!-- 增加按钮 -->
    <a href="javascript:void(-1);" id="water_coolant_add" class="btn btn-primary toLeft">
        <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;增加
    </a>
</div>

<!---分页列表----->
<div class="ibox" id="water_coolant_page" style="height:90%">
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">模态框（Modal）标题</h4>
            </div>
            <div class="modal-body">在这里添加一些文本</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary">提交更改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<#-- 添加模态框 -->
<div class="modal fade" id="water_coolant_modal_add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" style="margin-top: 8%;">
        <div class="modal-content" style="width: 700px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 增加冷却水配置信息</h4>
            </div>
            <div class="modal-body">
                <form id="water_coolant_add_form" name="water_coolant_add_form" class="form-horizontal">
                    <div  style="height: 70%;overflow-x: hidden;overflow-y: auto;">

                        <div class="form-group">
                            <label class="col-sm-3 control-label">名称
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input style="width: 390px"
                                       type="text"
                                       id="name_add_lq"
                                       name="name_add_lq"
                                       class="form-control"
                                       placeholder="请输入名称"
                                >
                            </div>
                        </div>
                        <#--能耗累计-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">能耗累计
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="nhlj_hidden_add_lq"
                                       name="nhlj_hidden_add_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="nhlj_add_lq"
                                       name="nhlj_add_lq"
                                       class="form-control"
                                       placeholder="请配置能耗累计"
                                >
                            </div>
                        </div>
                        <#--瞬时功率-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">瞬时功率
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="ssgl_hidden_add_lq"
                                       name="ssgl_hidden_add_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="ssgl_add_lq"
                                       name="ssgl_add_lq"
                                       class="form-control"
                                       placeholder="请配置瞬时功率"
                                >
                            </div>
                        </div>
                        <#--A相电流-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">A相电流
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="axdl_hidden_add_lq"
                                       name="axdl_hidden_add_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="axdl_add_lq"
                                       name="axdl_add_lq"
                                       class="form-control"
                                       placeholder="请配置A相电流"
                                >
                            </div>
                        </div>
                        <#--B相电流-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">B相电流
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="bxdl_hidden_add_lq"
                                       name="bxdl_hidden_add_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="bxdl_add_lq"
                                       name="bxdl_add_lq"
                                       class="form-control"
                                       placeholder="请配置B相电流"
                                >
                            </div>
                        </div>
                        <#--C相电流-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">C相电流
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="cxdl_hidden_add_lq"
                                       name="cxdl_hidden_add_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="cxdl_add_lq"
                                       name="cxdl_add_lq"
                                       class="form-control"
                                       placeholder="请配置C相电流"
                                >
                            </div>
                        </div>
                        <#--冷冻设备工作模式-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">冷却设备工作模式
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="sbgzms_hidden_add_lq"
                                       name="sbgzms_hidden_add_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="sbgzms_add_lq"
                                       name="sbgzms_add_lq"
                                       class="form-control"
                                       placeholder="请配置冷却设备工作模式"
                                >
                            </div>
                        </div>
                        <#--冷冻设备故障状态-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">冷却设备故障状态
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="sbgzzt_hidden_add_lq"
                                       name="sbgzzt_hidden_add_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="sbgzzt_add_lq"
                                       name="sbgzzt_add_lq"
                                       class="form-control"
                                       placeholder="请配置冷却设备故障状态"
                                >
                            </div>
                        </div>
                        <#--冷冻设备请求信号-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">冷却设备请求信号
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="sbqqxh_hidden_add_lq"
                                       name="sbqqxh_hidden_add_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="sbqqxh_add_lq"
                                       name="sbqqxh_add_lq"
                                       class="form-control"
                                       placeholder="请配置冷却设备请求信号"
                                >
                            </div>
                        </div>
                        <#--设备运行时间-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">设备运行时间
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="sbyxsj_hidden_add_lq"
                                       name="sbyxsj_hidden_add_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="sbyxsj_add_lq"
                                       name="sbyxsj_add_lq"
                                       class="form-control"
                                       placeholder="请配置设备运行时间"
                                >
                            </div>
                        </div>
                        <#--水阀开到位-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">水阀开到位
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="sfkdw_hidden_add_lq"
                                       name="sfkdw_hidden_add_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="sfkdw_add_lq"
                                       name="sfkdw_add_lq"
                                       class="form-control"
                                       placeholder="请配置水阀开到位"
                                >
                            </div>
                        </div>
                        <#--水阀关到位-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">水阀关到位
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="sfgdw_hidden_add_lq"
                                       name="sfgdw_hidden_add_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="sfgdw_add_lq"
                                       name="sfgdw_add_lq"
                                       class="form-control"
                                       placeholder="请配置水阀关到位"
                                >
                            </div>
                        </div>
                        <#--变频器运行状态-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">变频器运行状态
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="bpyxzt_hidden_add_lq"
                                       name="bpyxzt_hidden_add_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="bpyxzt_add_lq"
                                       name="bpyxzt_add_lq"
                                       class="form-control"
                                       placeholder="请配置变频器运行状态"
                                >
                            </div>
                        </div>
                        <#--变频器故障状态-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">变频器故障状态
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="bpgzzt_hidden_add_lq"
                                       name="bpgzzt_hidden_add_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="bpgzzt_add_lq"
                                       name="bpgzzt_add_lq"
                                       class="form-control"
                                       placeholder="请配置变频器故障状态"
                                >
                            </div>
                        </div>
                        <#--变频器频率反馈-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">变频器频率反馈
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="bpqplfk_hidden_add_lq"
                                       name="bpqplfk_hidden_add_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="bpqplfk_add_lq"
                                       name="bpqplfk_add_lq"
                                       class="form-control"
                                       placeholder="请配置变频器频率反馈"
                                >
                            </div>
                        </div>
                        <#--水流状态-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">水流状态
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="slzt_hidden_add_lq"
                                       name="slzt_hidden_add_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="slzt_add_lq"
                                       name="slzt_add_lq"
                                       class="form-control"
                                       placeholder="请配置水流状态"
                                >
                            </div>
                        </div>
                        <#--水阀故障状态-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">水阀故障状态
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="sfgzzt_hidden_add_lq"
                                       name="sfgzzt_hidden_add_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="sfgzzt_add_lq"
                                       name="sfgzzt_add_lq"
                                       class="form-control"
                                       placeholder="请配置水阀故障状态"
                                >
                            </div>
                        </div>
                        <#--水泵运行状态-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">水泵运行状态
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="sbyxzt_hidden_add_lq"
                                       name="sbyxzt_hidden_add_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="sbyxzt_add_lq"
                                       name="sbyxzt_add_lq"
                                       class="form-control"
                                       placeholder="请配置水泵运行状态"
                                >
                            </div>
                        </div>
                        <#--运行指示灯-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">运行指示灯
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="yxzsd_hidden_add_lq"
                                       name="yxzsd_hidden_add_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="yxzsd_add_lq"
                                       name="yxzsd_add_lq"
                                       class="form-control"
                                       placeholder="请配置运行指示灯"
                                >
                            </div>
                        </div>
                        <#--故障指示灯-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">故障指示灯
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="gzzsd_hidden_add_lq"
                                       name="gzzsd_hidden_add_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="gzzsd_add_lq"
                                       name="gzzsd_add_lq"
                                       class="form-control"
                                       placeholder="请配置故障指示灯"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">电表编号<span class="text-danger">*</span></label>
                            <div class="col-sm-8">
                                <select id="Electric_meter_number_add_lq" name="Electric_meter_number_add_lq" required class="form-control_device_config">

                                </select>
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

<#-- 编辑模态框 -->
<div class="modal fade" id="water_coolant_modal_edit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" style="margin-top: 8%;">
        <div class="modal-content" style="width: 700px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 编辑冷却水配置信息</h4>
            </div>
            <div class="modal-body">
                <form id="water_coolant_edit_form" name="water_coolant_edit_form" class="form-horizontal">
                    <div  style="height: 70%;overflow-x: hidden;overflow-y: auto;">

                        <input type="hidden" id="id_edit_lq" name="id_edit_lq"/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">名称
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input style="width: 390px"
                                       type="text"
                                       id="name_edit_lq"
                                       name="name_edit_lq"
                                       class="form-control"
                                       placeholder="请输入名称"
                                >
                            </div>
                        </div>

                        <#--能耗累计-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">能耗累计
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="nhlj_hidden_edit_lq"
                                       name="nhlj_hidden_edit_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="nhlj_edit_lq"
                                       name="nhlj_edit_lq"
                                       class="form-control"
                                       placeholder="请配置能耗累计"
                                >
                            </div>
                        </div>
                        <#--瞬时功率-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">瞬时功率
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="ssgl_hidden_edit_lq"
                                       name="ssgl_hidden_edit_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="ssgl_edit_lq"
                                       name="ssgl_edit_lq"
                                       class="form-control"
                                       placeholder="请配置瞬时功率"
                                >
                            </div>
                        </div>
                        <#--A相电流-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">A相电流
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="axdl_hidden_edit_lq"
                                       name="axdl_hidden_edit_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="axdl_edit_lq"
                                       name="axdl_edit_lq"
                                       class="form-control"
                                       placeholder="请配置A相电流"
                                >
                            </div>
                        </div>
                        <#--B相电流-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">B相电流
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="bxdl_hidden_edit_lq"
                                       name="bxdl_hidden_edit_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="bxdl_edit_lq"
                                       name="bxdl_edit_lq"
                                       class="form-control"
                                       placeholder="请配置B相电流"
                                >
                            </div>
                        </div>
                        <#--C相电流-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">C相电流
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="cxdl_hidden_edit_lq"
                                       name="cxdl_hidden_edit_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="cxdl_edit_lq"
                                       name="cxdl_edit_lq"
                                       class="form-control"
                                       placeholder="请配置C相电流"
                                >
                            </div>
                        </div>
                        <#--冷冻设备工作模式-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">冷却设备工作模式
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="sbgzms_hidden_edit_lq"
                                       name="sbgzms_hidden_edit_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="sbgzms_edit_lq"
                                       name="sbgzms_edit_lq"
                                       class="form-control"
                                       placeholder="请配置冷却设备工作模式"
                                >
                            </div>
                        </div>
                        <#--冷冻设备故障状态-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">冷却设备故障状态
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="sbgzzt_hidden_edit_lq"
                                       name="sbgzzt_hidden_edit_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="sbgzzt_edit_lq"
                                       name="sbgzzt_edit_lq"
                                       class="form-control"
                                       placeholder="请配置冷却设备故障状态"
                                >
                            </div>
                        </div>
                        <#--冷冻设备请求信号-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">冷却设备请求信号
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="sbqqxh_hidden_edit_lq"
                                       name="sbqqxh_hidden_edit_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="sbqqxh_edit_lq"
                                       name="sbqqxh_edit_lq"
                                       class="form-control"
                                       placeholder="请配置冷却设备请求信号"
                                >
                            </div>
                        </div>
                        <#--设备运行时间-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">设备运行时间
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="sbyxsj_hidden_edit_lq"
                                       name="sbyxsj_hidden_edit_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="sbyxsj_edit_lq"
                                       name="sbyxsj_edit_lq"
                                       class="form-control"
                                       placeholder="请配置设备运行时间"
                                >
                            </div>
                        </div>

                        <#--水阀开到位-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">水阀开到位
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="sfkdw_hidden_edit_lq"
                                       name="sfkdw_hidden_edit_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="sfkdw_edit_lq"
                                       name="sfkdw_edit_lq"
                                       class="form-control"
                                       placeholder="请配置水阀开到位"
                                >
                            </div>
                        </div>
                        <#--水阀关到位-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">水阀关到位
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="sfgdw_hidden_edit_lq"
                                       name="sfgdw_hidden_edit_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="sfgdw_edit_lq"
                                       name="sfgdw_edit_lq"
                                       class="form-control"
                                       placeholder="请配置水阀关到位"
                                >
                            </div>
                        </div>
                        <#--变频器运行状态-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">变频器运行状态
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="bpyxzt_hidden_edit_lq"
                                       name="bpyxzt_hidden_edit_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="bpyxzt_edit_lq"
                                       name="bpyxzt_edit_lq"
                                       class="form-control"
                                       placeholder="请配置变频器运行状态"
                                >
                            </div>
                        </div>
                        <#--变频器故障状态-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">变频器故障状态
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="bpgzzt_hidden_edit_lq"
                                       name="bpgzzt_hidden_edit_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="bpgzzt_edit_lq"
                                       name="bpgzzt_edit_lq"
                                       class="form-control"
                                       placeholder="请配置变频器故障状态"
                                >
                            </div>
                        </div>
                        <#--变频器频率反馈-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">变频器频率反馈
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="bpqplfk_hidden_edit_lq"
                                       name="bpqplfk_hidden_edit_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="bpqplfk_edit_lq"
                                       name="bpqplfk_edit_lq"
                                       class="form-control"
                                       placeholder="请配置变频器频率反馈"
                                >
                            </div>
                        </div>
                        <#--水流状态-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">水流状态
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="slzt_hidden_edit_lq"
                                       name="slzt_hidden_edit_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="slzt_edit_lq"
                                       name="slzt_edit_lq"
                                       class="form-control"
                                       placeholder="请配置水流状态"
                                >
                            </div>
                        </div>
                        <#--水阀故障状态-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">水阀故障状态
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="sfgzzt_hidden_edit_lq"
                                       name="sfgzzt_hidden_edit_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="sfgzzt_edit_lq"
                                       name="sfgzzt_edit_lq"
                                       class="form-control"
                                       placeholder="请配置当前状态"
                                >
                            </div>
                        </div>
                        <#--水泵运行状态-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">水泵运行状态
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="sbyxzt_hidden_edit_lq"
                                       name="sbyxzt_hidden_edit_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="sbyxzt_edit_lq"
                                       name="sbyxzt_edit_lq"
                                       class="form-control"
                                       placeholder="请配置水阀故障状态"
                                >
                            </div>
                        </div>
                        <#--运行指示灯-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">运行指示灯
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="yxzsd_hidden_edit_lq"
                                       name="yxzsd_hidden_edit_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="yxzsd_edit_lq"
                                       name="yxzsd_edit_lq"
                                       class="form-control"
                                       placeholder="请配置运行指示灯"
                                >
                            </div>
                        </div>
                        <#--故障指示灯-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">故障指示灯
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="gzzsd_hidden_edit_lq"
                                       name="gzzsd_hidden_edit_lq"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="gzzsd_edit_lq"
                                       name="gzzsd_edit_lq"
                                       class="form-control"
                                       placeholder="请配置故障指示灯"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">电表编号<span class="text-danger">*</span></label>
                            <div class="col-sm-8">
                                <select id="Electric_meter_number_edit_lq" name="Electric_meter_number_edit_lq" required class="form-control_device_config">

                                </select>
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

<#-- 设备类型模态框 -->
<div class="modal fade" id="tree_device_type_modal_water_coolant" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" style="margin-top: 8%;">
        <div class="modal-content" style="width: 350px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 请选择设备类型</h4>
            </div>
            <div class="modal-body" style="height: 450px">
                <div style="position: relative" class="zc_search find">
                    <input type="text" class="find-style" id="tree_device_type_search_water_coolant_input" name="eqTypeInfo" placeholder="设备类型">
                    <button id="tree_device_type_search_water_coolant_button"><i class="fa fa-search" aria-hidden="true"></i></button>
                </div>

                <#--设备类型树-->
                <div id="tree_device_type_water_coolant" class="Information_area ztree"></div>

            </div>
        </div><!-- end modal-content -->
    </div>
</div> <!-- end addParkBasicInfo -->

<script type="text/javascript">
    var water_coolant=(function ($, window, document) {
        var ctx = '${ctx}';
        var tree;// 树对象
        var modalAddActive = false; // 保存添加模态框是否是显示状态 true 显示 | false 隐藏
        var modalEditActive = false; // 保存编辑模态框是否是显示状态 true 显示 | false 隐藏
        var inputObj; // 模态框内输入框保存对象
        var add_or_edit;//添加的时候,默认为1,修改的时候,默认为2

        $(function () {
            initTree(); // 初始化树

            // 获取设备类型数据并把数据加载到树中
            getAustereDevicesTree(function (data) {

                var status = data.status;

                if(status !== '1') {
                    return;
                }

                var treeList = data.list || [];

                tree.loadData(treeList);
            });
            getPagingPage({}, function (data) {
                showPagingPage('water_coolant_page', data);

            });
        });
        // 初始化设备类型树
        function initTree() {
            tree = new Tree({
                container: 'tree_device_type_water_coolant',
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
                callback: function (node) {
                    if ((modalAddActive || modalEditActive) && inputObj) {
                        var  cid = node.cid;
                        if (add_or_edit == 1){//添加的时候
                            let add = isItRedundantAdd(cid);
                            if (add == 0) {//添加的点位不重复
                                inputObj.value = node.f_nick_name;
                                $(inputObj).prev().val(node.f_sys_name);
                            }else {//添加的点位重复
                                swal({
                                    title : '此点位已存在!',// 展示的标题
                                    text : "",// 内容
                                    type : "warning",
                                    showCloseButton : false, // 展示关闭按钮
                                    allowOutsideClick : false,
                                    showConfirmButton : false,
                                    timer : 1000
                                });
                                return;
                            }

                        }else if (add_or_edit == 2){//修改的时候
                            let edit = isItRedundantEdit(cid);
                            if (edit == 0) {//添加的点位不重复
                                inputObj.value = node.f_nick_name;
                                $(inputObj).prev().val(node.f_sys_name);
                            }else {//添加的点位重复
                                swal({
                                    title : '此点位已存在!',// 展示的标题
                                    text : "",// 内容
                                    type : "warning",
                                    showCloseButton : false, // 展示关闭按钮
                                    allowOutsideClick : false,
                                    showConfirmButton : false,
                                    timer : 1000
                                });
                                return;
                            }
                        }
                    }
                }
            });
        }

        function isItRedundantEdit(data) {
            let nhlj    = document.forms["water_coolant_edit_form"].elements["nhlj_hidden_edit_lq"].value;
            let ssgl    = document.forms["water_coolant_edit_form"].elements["ssgl_hidden_edit_lq"].value;
            let axdl    = document.forms["water_coolant_edit_form"].elements["axdl_hidden_edit_lq"].value;
            let bxdl    = document.forms["water_coolant_edit_form"].elements["bxdl_hidden_edit_lq"].value;
            let cxdl    = document.forms["water_coolant_edit_form"].elements["cxdl_hidden_edit_lq"].value;
            let sbgzms  = document.forms["water_coolant_edit_form"].elements["sbgzms_hidden_edit_lq"].value;
            let sbgzzt  = document.forms["water_coolant_edit_form"].elements["sbgzzt_hidden_edit_lq"].value;
            let sbqqxh  = document.forms["water_coolant_edit_form"].elements["sbqqxh_hidden_edit_lq"].value;
            let sbyxsj  = document.forms["water_coolant_edit_form"].elements["sbyxsj_hidden_edit_lq"].value;
            let sfkdw   = document.forms["water_coolant_edit_form"].elements["sfkdw_hidden_edit_lq"].value;
            let sfgdw   = document.forms["water_coolant_edit_form"].elements["sfgdw_hidden_edit_lq"].value;
            let bpyxzt  = document.forms["water_coolant_edit_form"].elements["bpyxzt_hidden_edit_lq"].value;
            let bpgzzt  = document.forms["water_coolant_edit_form"].elements["bpgzzt_hidden_edit_lq"].value;
            let bpqplf  = document.forms["water_coolant_edit_form"].elements["bpqplfk_hidden_edit_lq"].value;
            let slzt    = document.forms["water_coolant_edit_form"].elements["slzt_hidden_edit_lq"].value;
            let sfgzzt  = document.forms["water_coolant_edit_form"].elements["sfgzzt_hidden_edit_lq"].value;
            let sbyxzt  = document.forms["water_coolant_edit_form"].elements["sbyxzt_hidden_edit_lq"].value;
            let yxzsd   = document.forms["water_coolant_edit_form"].elements["yxzsd_hidden_edit_lq"].value;
            let gzzsd   = document.forms["water_coolant_edit_form"].elements["gzzsd_hidden_edit_lq"].value;

            if (data == nhlj  || data == ssgl || data == axdl || data == bxdl || data == cxdl || data == sbgzms || data == sbgzzt || data == sbqqxh
                || data == sbyxsj || data == sfkdw || data == sfgdw || data == bpyxzt || data == bpgzzt || data == bpqplf || data == slzt || data == sfgzzt
                || data == sbyxzt || data == yxzsd || data == gzzsd) {
                return 1;
            } else {
                return 0;
            }
        }

        function isItRedundantAdd(data) {
            let nhlj    = document.forms["water_coolant_add_form"].elements["nhlj_hidden_add_lq"].value;
            let ssgl    = document.forms["water_coolant_add_form"].elements["ssgl_hidden_add_lq"].value;
            let axdl    = document.forms["water_coolant_add_form"].elements["axdl_hidden_add_lq"].value;
            let bxdl    = document.forms["water_coolant_add_form"].elements["bxdl_hidden_add_lq"].value;
            let cxdl    = document.forms["water_coolant_add_form"].elements["cxdl_hidden_add_lq"].value;
            let sbgzms  = document.forms["water_coolant_add_form"].elements["sbgzms_hidden_add_lq"].value;
            let sbgzzt  = document.forms["water_coolant_add_form"].elements["sbgzzt_hidden_add_lq"].value;
            let sbqqxh  = document.forms["water_coolant_add_form"].elements["sbqqxh_hidden_add_lq"].value;
            let sbyxsj  = document.forms["water_coolant_add_form"].elements["sbyxsj_hidden_add_lq"].value;
            let sfkdw   = document.forms["water_coolant_add_form"].elements["sfkdw_hidden_add_lq"].value;
            let sfgdw   = document.forms["water_coolant_add_form"].elements["sfgdw_hidden_add_lq"].value;
            let bpyxzt  = document.forms["water_coolant_add_form"].elements["bpyxzt_hidden_add_lq"].value;
            let bpgzzt  = document.forms["water_coolant_add_form"].elements["bpgzzt_hidden_add_lq"].value;
            let bpqplf  = document.forms["water_coolant_add_form"].elements["bpqplfk_hidden_add_lq"].value;
            let slzt    = document.forms["water_coolant_add_form"].elements["slzt_hidden_add_lq"].value;
            let sfgzzt  = document.forms["water_coolant_add_form"].elements["sfgzzt_hidden_add_lq"].value;
            let sbyxzt  = document.forms["water_coolant_add_form"].elements["sbyxzt_hidden_add_lq"].value;
            let yxzsd   = document.forms["water_coolant_add_form"].elements["yxzsd_hidden_add_lq"].value;
            let gzzsd   = document.forms["water_coolant_add_form"].elements["gzzsd_hidden_add_lq"].value;

            if (data == nhlj  || data == ssgl || data == axdl || data == bxdl || data == cxdl || data == sbgzms || data == sbgzzt || data == sbqqxh
                || data == sbyxsj || data == sfkdw || data == sfgdw || data == bpyxzt || data == bpgzzt || data == bpqplf || data == slzt || data == sfgzzt
                || data == sbyxzt || data == yxzsd || data == gzzsd) {
                return 1;
            } else {
                return 0;
            }
        }

        // 获取冷却水配置信息
        function getAustereDevicesTree(callback){

            if(typeof callback !== 'function'){
                return;
            }

            $.ajax({
                type    : "post",
                url     : ctx + "/view/basedatamanage/eqmanage/austere_devices_tree",
                dataType: "json",
                success: function (result) {
                    callback(result);
                }
            });
        }

        // 查询设备信息    回显
        function queryWaterCoolantUnit(obj, callback) {

            if(typeof callback !== 'function'){
                return;
            }
            obj.F_TYPE_ID="2";

            obj = obj || {};

            $.ajax({
                type    : "POST",
                url     : _ctx + "/view/strongAndWeakElectricityIntegration/deviceConfig/chilledWater/query",//冷却水和冷冻水共用一个
                dataType: "json",
                data    :  obj,
                success: function (result) {

                    callback(result);

                },
                error: function (result) {

                    console.warn(result)
                }
            });


        }
        $(function(){
            Electric_meter_number_add_lq('add');
            Electric_meter_number_add_lq('edit');
        });
        // 显示添加模态框
        $('#water_coolant_add').click(function () {
            $('#water_coolant_modal_add').modal('show');
            modalAddActive = true;
            add_or_edit = 1;

        });
        // 显示编辑模态框事件
        $('#water_coolant_modal_edit').on('show.bs.modal', function () {
            modalEditActive = true;
            add_or_edit = 2;


        });

        //触发搜索的回车事件， 以系统别名搜索设备树展开并以红色字体标注
        $('#tree_device_type_search_water_coolant_input').keydown(function (e){
            if(e.which !== 13){
                return;
            }

            tree.search('tree_device_type_search_water_coolant_input', 'f_nick_name');

        });

        // 触发搜索点击事件， 以系统别名搜索设备树展开并以红色字体标注
        $('#tree_device_type_search_water_coolant_button').click(function () {

            tree.search('tree_device_type_search_water_coolant_input', 'f_nick_name');
        });

        // 添加输入框得到焦点时显示设备类型模态框
        $(
            '#nhlj_add_lq, '    + '#ssgl_add_lq, '     + '#axdl_add_lq, '    + '#bxdl_add_lq, ' + '#cxdl_add_lq, '   + '#sbgzms_add_lq, ' +
            '#sbgzzt_add_lq, ' + '#sbqqxh_add_lq, ' + '#sbyxsj_add_lq, ' + '#sfkdw_add_lq, '  + '#sfgdw_add_lq, '  + '#bpyxzt_add_lq, ' +
            '#bpgzzt_add_lq, '   + '#bpqplfk_add_lq, '   + '#slzt_add_lq, '   + '#sfgzzt_add_lq, '   + '#sbyxzt_add_lq, '    +  '#yxzsd_add_lq, ' +
            '#gzzsd_add_lq, ' +

            '#nhlj_edit_lq, '    + '#ssgl_edit_lq, '     + '#axdl_edit_lq, '    + '#bxdl_edit_lq, ' + '#cxdl_edit_lq, '   + '#sbgzms_edit_lq, ' +
            '#sbgzzt_edit_lq, ' + '#sbqqxh_edit_lq, ' + '#sbyxsj_edit_lq, ' + '#sfkdw_edit_lq, '  + '#sfgdw_edit_lq, '  + '#bpyxzt_edit_lq, ' +
            '#bpgzzt_edit_lq, '   + '#bpqplfk_edit_lq, '   + '#slzt_edit_lq, '   + '#sfgzzt_edit_lq, '   + '#sbyxzt_edit_lq, '    +  '#yxzsd_edit_lq, ' +
            '#gzzsd_edit_lq '

        ).focus(function (obj) {

            inputObj = obj.target;
            $('#tree_device_type_modal_water_coolant').modal('show');
        });

        // 当添加模态框关闭时同时关闭设备类型模态框, 清空表单数据
        $('#water_coolant_modal_add').on('hide.bs.modal', function () {
            modalAddActive = false;
            inputObj = null;
            $('#tree_device_type_modal_water_coolant').modal('hide');
            $('#name_add_lq').val('');             $('#ssgl_add_lq').val('');             $('#axdl_add_lq').val('');
            $('#bxdl_add_lq').val('');             $('#cxdl_add_lq').val('');             $('#sbgzms_add_lq').val('');
            $('#sbgzzt_add_lq') .val('');          $('#sbqqxh_add_lq').val('');           $('#sbyxsj_add_lq').val('');
            $('#sfkdw_add_lq') .val('');           $('#sfgdw_add_lq').val('');            $('#bpyxzt_add_lq').val('');
            $('#bpgzzt_add_lq').val('');           $('#bpqplfk_add_lq').val('');          $('#slzt_add_lq').val('');
            $('#sfgzzt_add_lq').val('');           $('#sbyxzt_add_lq').val('');           $('#yxzsd_add_lq').val('');
            $('#gzzsd_add_lq').val('');            $('#nhlj_add_lq').val('');

            $('#ssgl_hidden_add_lq').val('');      $('#axdl_hidden_add_lq').val('');
            $('#bxdl_hidden_add_lq').val('');      $('#cxdl_hidden_add_lq').val('');      $('#sbgzms_hidden_add_lq').val('');
            $('#sbgzzt_hidden_add_lq') .val('');   $('#sbqqxh_hidden_add_lq').val('');    $('#sbyxsj_hidden_add_lq').val('');
            $('#sfkdw_hidden_add_lq') .val('');    $('#sfgdw_hidden_add_lq').val('');     $('#bpyxzt_hidden_add_lq').val('');
            $('#bpgzzt_hidden_add_lq').val('');    $('#bpqplfk_hidden_add_lq').val('');   $('#slzt_hidden_add_lq').val('');
            $('#sfgzzt_hidden_add_lq').val('');    $('#sbyxzt_hidden_add_lq').val('');    $('#yxzsd_hidden_add_lq').val('');
            $('#gzzsd_hidden_add_lq').val('');     $('#nhlj_hidden_add_lq').val('');
            $('#Electric_meter_number_add_lq_ld') .val('');

            // 重置添加验证表单
            addValidate.resetForm()
        });

        // 关闭编辑模态框时清空输入框
        $("#water_coolant_modal_edit").on('hidden.bs.modal', function(event) {

            inputObj = null;
            modalEditActive = false;
            $('#tree_device_type_modal_water_coolant').modal('hide');

            $('#name_edit_lq').val('');             $('#ssgl_edit_lq').val('');             $('#axdl_edit_lq').val('');
            $('#bxdl_edit_lq').val('');             $('#cxdl_edit_lq').val('');             $('#sbgzms_edit_lq').val('');
            $('#sbgzzt_edit_lq') .val('');          $('#sbqqxh_edit_lq').val('');           $('#sbyxsj_edit_lq').val('');
            $('#sfkdw_edit_lq') .val('');           $('#sfgdw_edit_lq').val('');            $('#bpyxzt_edit_lq').val('');
            $('#bpgzzt_edit_lq').val('');           $('#bpqplfk_edit_lq').val('');          $('#slzt_edit_lq').val('');
            $('#sfgzzt_edit_lq').val('');           $('#sbyxzt_edit_lq').val('');           $('#yxzsd_edit_lq').val('');
            $('#gzzsd_edit_lq').val('');            $('#nhlj_edit_lq').val('');

            $('#ssgl_hidden_edit_lq').val('');      $('#axdl_hidden_edit_lq').val('');
            $('#bxdl_hidden_edit_lq').val('');      $('#cxdl_hidden_edit_lq').val('');      $('#sbgzms_hidden_edit_lq').val('');
            $('#sbgzzt_hidden_edit_lq') .val('');   $('#sbqqxh_hidden_edit_lq').val('');    $('#sbyxsj_hidden_edit_lq').val('');
            $('#sfkdw_hidden_edit_lq') .val('');    $('#sfgdw_hidden_edit_lq').val('');     $('#bpyxzt_hidden_edit_lq').val('');
            $('#bpgzzt_hidden_edit_lq').val('');    $('#bpqplfk_hidden_edit_lq').val('');   $('#slzt_hidden_edit_lq').val('');
            $('#sfgzzt_hidden_edit_lq').val('');    $('#sbyxzt_hidden_edit_lq').val('');    $('#yxzsd_hidden_edit_lq').val('');
            $('#gzzsd_hidden_edit_lq').val('');     $('#nhlj_hidden_edit_lq').val('');
            $('#Electric_meter_number_edit_lq_ld') .val('');

            // 重置表单
            editValidate.resetForm()

        });


        // 当设备树模态框关闭时折叠设备树、清空搜索框内容
        $('#tree_device_type_modal_water_coolant').on('hide.bs.modal', function () {
            tree.tree.expandAll( false );
            $('#tree_device_type_search_water_coolant_input').val('');
        })


        // 提交表单信息
        function addSubmit(formData){

            if(!formData){
                return;
            }
            //Electric_meter_number_add_lq('add');

            //获取表单数据
            var formData = form2js( $(formData)[ 0 ],null, null, null, null, true);

            $.ajax({
                type    : "POST",
                url     : _ctx + "/view/strongAndWeakElectricityIntegration/deviceConfig/chilledWater/create",//冷却水和冷冻水共用一个
                dataType: "json",
                data: {
                    f_NAME                      : formData.name_add_lq,
                    f_NHLJ                      : formData.nhlj_hidden_add_lq,
                    f_NHLJ_alias                : formData.nhlj_add_lq,
                    f_SSGL                      : formData.ssgl_hidden_add_lq,
                    f_SSGL_alias                : formData.ssgl_add_lq,
                    f_AXDL                      : formData.axdl_hidden_add_lq,
                    f_AXDL_alias                : formData.axdl_add_lq,
                    f_BXDL                      : formData.bxdl_hidden_add_lq,
                    f_BXDL_alias                : formData.bxdl_add_lq,
                    f_CXDL                      : formData.cxdl_hidden_add_lq,
                    f_CXDL_alias                : formData.cxdl_add_lq,
                    f_SBGZMS                    : formData.sbgzms_hidden_add_lq,
                    f_SBGZMS_alias              : formData.sbgzms_add_lq,
                    f_SBGZZT                    : formData.sbgzzt_hidden_add_lq,
                    f_SBGZZT_alias              : formData.sbgzzt_add_lq,
                    f_SBQQXH                    : formData.sbqqxh_hidden_add_lq,
                    f_SBQQXH_alias              : formData.sbqqxh_add_lq,
                    f_SBYXSJ                    : formData.sbyxsj_hidden_add_lq,
                    f_SBYXSJ_alias              : formData.sbyxsj_add_lq,
                    f_SFKDW                     : formData.sfkdw_hidden_add_lq,
                    f_SFKDW_alias               : formData.sfkdw_add_lq,
                    f_SFGDW                     : formData.sfgdw_hidden_add_lq,
                    f_SFGDW_alias               : formData.sfgdw_add_lq,
                    f_BPYXZT                    : formData.bpyxzt_hidden_add_lq,
                    f_BPYXZT_alias              : formData.bpyxzt_add_lq,
                    f_BPGZZT                    : formData.bpgzzt_hidden_add_lq,
                    f_BPGZZT_alias              : formData.bpgzzt_add_lq,
                    f_BPQPLFK                   : formData.bpqplfk_hidden_add_lq,
                    f_BPQPLFK_alias             : formData.bpqplfk_add_lq,
                    f_SLZT                      : formData.slzt_hidden_add_lq,
                    f_SLZT_alias                : formData.slzt_add_lq,
                    f_SFGZZT                    : formData.sfgzzt_hidden_add_lq,
                    f_SFGZZT_alias              : formData.sfgzzt_add_lq,
                    f_SBYXZT                    : formData.sbyxzt_hidden_add_lq,
                    f_SBYXZT_alias              : formData.sbyxzt_add_lq,
                    f_YXZSD                     : formData.yxzsd_hidden_add_lq,
                    f_YXZSD_alias               : formData.yxzsd_add_lq,
                    f_GZZSD                     : formData.gzzsd_hidden_add_lq,
                    f_GZZSD_alias               : formData.gzzsd_add_lq,

                    f_TYPE_ID                   : "2",
                    f_Electric_meter_number     : formData.Electric_meter_number_add_lq,
                },
                success: function (result) {

                    var status = result && result.status;

                    if(status === "1"){

                        getPagingPage({}, function (data) {
                            showPagingPage('water_coolant_page', data);

                        });

                        $('#water_coolant_modal_add').modal('hide');

                        swal({
                            title             : "添加成功",// 展示的标题
                            text              : "", // 内容
                            type              : "success",
                            showCloseButton   : false, // 展示关闭按钮
                            allowOutsideClick : false,
                            showConfirmButton : false,
                            timer             : 1000
                        });


                    }else {
                        swal({
                            title             : "添加失败",// 展示的标题
                            text              : result.msg, // 内容
                            type              : "warning",
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

        //select标签查询
        function Electric_meter_number_add_lq(keywords){
            $.ajax({
                type: "POST",
                url: _ctx + '/view/strongAndWeakElectricityIntegration/deviceConfig/chilledWater/electricMeterNnumber',
                data:"",
                success: function(data){
                    var ops="<option value=''>请选择电表编号</option>";
                    for(var i=0;i<data.list.length;i++){
                        var obj=data.list[i];
                        ops+='<option value="'+obj.id+'">';
                        ops+=obj.id+'('+obj.name+')';
                        ops+='</option>';
                    }
                    if(keywords == 'add'){
                        $("#Electric_meter_number_add_lq").append(ops);
                    }else{
                        $("#Electric_meter_number_edit_lq").append(ops);
                    }
                },
                error:function(msg){
                    alert( "select列表查询失败!" );
                }
            });
        }

        // 更新表单信息
        function editSubmit(formData){

            if(!formData){
                return;
            }

            //获取表单数据
            var formData = form2js( $(formData)[ 0 ],null, null, null, null, true);

            $.ajax({
                type    : "POST",
                url     : _ctx + "/view/strongAndWeakElectricityIntegration/deviceConfig/chilledWater/update",//冷却水和冷冻水共用一个
                dataType: "json",
                data: {
                    f_ID                        : formData.id_edit_lq,
                    f_NAME                      : formData.name_edit_lq,
                    f_NHLJ                      : formData.nhlj_hidden_edit_lq,
                    f_NHLJ_alias                : formData.nhlj_edit_lq,
                    f_SSGL                      : formData.ssgl_hidden_edit_lq,
                    f_SSGL_alias                : formData.ssgl_edit_lq,
                    f_AXDL                      : formData.axdl_hidden_edit_lq,
                    f_AXDL_alias                : formData.axdl_edit_lq,
                    f_BXDL                      : formData.bxdl_hidden_edit_lq,
                    f_BXDL_alias                : formData.bxdl_edit_lq,
                    f_CXDL                      : formData.cxdl_hidden_edit_lq,
                    f_CXDL_alias                : formData.cxdl_edit_lq,
                    f_SBGZMS                    : formData.sbgzms_hidden_edit_lq,
                    f_SBGZMS_alias              : formData.sbgzms_edit_lq,
                    f_SBGZZT                    : formData.sbgzzt_hidden_edit_lq,
                    f_SBGZZT_alias              : formData.sbgzzt_edit_lq,
                    f_SBQQXH                    : formData.sbqqxh_hidden_edit_lq,
                    f_SBQQXH_alias              : formData.sbqqxh_edit_lq,
                    f_SBYXSJ                    : formData.sbyxsj_hidden_edit_lq,
                    f_SBYXSJ_alias              : formData.sbyxsj_edit_lq,
                    f_SFKDW                     : formData.sfkdw_hidden_edit_lq,
                    f_SFKDW_alias               : formData.sfkdw_edit_lq,
                    f_SFGDW                     : formData.sfgdw_hidden_edit_lq,
                    f_SFGDW_alias               : formData.sfgdw_edit_lq,
                    f_BPYXZT                    : formData.bpyxzt_hidden_edit_lq,
                    f_BPYXZT_alias              : formData.bpyxzt_edit_lq,
                    f_BPGZZT                    : formData.bpgzzt_hidden_edit_lq,
                    f_BPGZZT_alias              : formData.bpgzzt_edit_lq,
                    f_BPQPLFK                   : formData.bpqplfk_hidden_edit_lq,
                    f_BPQPLFK_alias             : formData.bpqplfk_edit_lq,
                    f_SLZT                      : formData.slzt_hidden_edit_lq,
                    f_SLZT_alias                : formData.slzt_edit_lq,
                    f_SFGZZT                    : formData.sfgzzt_hidden_edit_lq,
                    f_SFGZZT_alias              : formData.sfgzzt_edit_lq,
                    f_SBYXZT                    : formData.sbyxzt_hidden_edit_lq,
                    f_SBYXZT_alias              : formData.sbyxzt_edit_lq,
                    f_YXZSD                     : formData.yxzsd_hidden_edit_lq,
                    f_YXZSD_alias               : formData.yxzsd_edit_lq,
                    f_GZZSD                     : formData.gzzsd_hidden_edit_lq,
                    f_GZZSD_alias               : formData.gzzsd_edit_lq,
                    f_TYPE_ID                      : "2",
                    f_Electric_meter_number        : formData.Electric_meter_number_edit_lq,
                },
                success: function (result) {

                    var status = result && result.status;

                    if(status === "1"){

                        $('#water_coolant_modal_edit').modal('hide');

                        swal({
                            title             : "编辑成功",// 展示的标题
                            text              : "", // 内容
                            type              : "success",
                            showCloseButton   : false, // 展示关闭按钮
                            allowOutsideClick : false,
                            showConfirmButton : false,
                            timer             : 1000
                        });

                        getPagingPage({}, function (data) {
                            showPagingPage('water_coolant_page', data);
                        });

                    }else {
                        swal({
                            title             : "编辑失败",// 展示的标题
                            text              : result.msg, // 内容
                            type              : "warning",
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

        // 添加输入框验证
        var addValidate = $("#water_coolant_add_form").validate({
            rules: {
                name_add_lq:{
                    required: true,
                    maxlength: 50

                }
            },
            messages: {
                name_add_lq:{
                    required: '请输入名称'
                }
            },

            submitHandler: function(formData) {
                //Electric_meter_number_add('add');
                addSubmit(formData);
            }

        });

        // 修改的输入框验证
        var editValidate = $("#water_coolant_edit_form").validate({
            rules: {
                name_edit_lq:{
                    required: true,
                    maxlength: 50

                }
            },
            messages: {
                name_edit_lq:{
                    required: '请输入名称'
                }
            },
            submitHandler: function(formData) {
                editSubmit(formData);
            }

        });

        /**
         * 编辑点击事件
         *
         */
        $(document).on('click', '#waterCoolantTable button.edit', function () {

            var F_ID = $(this).data('id');

            queryWaterCoolantUnit({F_ID}, function (obj) {

                var data = obj && obj.data && obj.data[0];

                if(!data){
                    return;
                }
                $('#id_edit_lq')                                   .val(data.f_ID             || '');
                $('#name_edit_lq')                                 .val(data.f_NAME           || '');
                $('#nhlj_hidden_edit_lq')                          .val(data.f_NHLJ           || '');
                $('#nhlj_edit_lq')                                 .val(data.f_NHLJ_alias     || '');
                $('#ssgl_hidden_edit_lq')                          .val(data.f_SSGL           || '');
                $('#ssgl_edit_lq')                                 .val(data.f_SSGL_alias     || '');
                $('#axdl_hidden_edit_lq')                          .val(data.f_AXDL           || '');
                $('#axdl_edit_lq')                                 .val(data.f_AXDL_alias     || '');
                $('#bxdl_hidden_edit_lq')                          .val(data.f_BXDL           || '');
                $('#bxdl_edit_lq')                                 .val(data.f_BXDL_alias     || '');
                $('#cxdl_hidden_edit_lq')                          .val(data.f_CXDL           || '');
                $('#cxdl_edit_lq')                                 .val(data.f_CXDL_alias     || '');
                $('#sbgzms_hidden_edit_lq')                        .val(data.f_SBGZMS         || '');
                $('#sbgzms_edit_lq')                               .val(data.f_SBGZMS_alias   || '');
                $('#sbgzzt_hidden_edit_lq')                        .val(data.f_SBGZZT         || '');
                $('#sbgzzt_edit_lq')                               .val(data.f_SBGZZT_alias   || '');
                $('#sbqqxh_hidden_edit_lq')                        .val(data.f_SBQQXH         || '');
                $('#sbqqxh_edit_lq')                               .val(data.f_SBQQXH_alias   || '');
                $('#sbyxsj_hidden_edit_lq')                        .val(data.f_SBYXSJ         || '');
                $('#sbyxsj_edit_lq')                               .val(data.f_SBYXSJ_alias   || '');
                $('#sfkdw_hidden_edit_lq')                         .val(data.f_SFKDW          || '');
                $('#sfkdw_edit_lq')                                .val(data.f_SFKDW_alias    || '');
                $('#sfgdw_hidden_edit_lq')                         .val(data.f_SFGDW          || '');
                $('#sfgdw_edit_lq')                                .val(data.f_SFGDW_alias    || '');
                $('#bpyxzt_hidden_edit_lq')                        .val(data.f_BPYXZT         || '');
                $('#bpyxzt_edit_lq')                               .val(data.f_BPYXZT_alias   || '');
                $('#bpgzzt_hidden_edit_lq')                        .val(data.f_BPGZZT         || '');
                $('#bpgzzt_edit_lq')                               .val(data.f_BPGZZT_alias   || '');
                $('#bpqplfk_hidden_edit_lq')                       .val(data.f_BPQPLFK        || '');
                $('#bpqplfk_edit_lq')                              .val(data.f_BPQPLFK_alias  || '');
                $('#slzt_hidden_edit_lq')                          .val(data.f_SLZT           || '');
                $('#slzt_edit_lq')                                 .val(data.f_SLZT_alias     || '');
                $('#sfgzzt_hidden_edit_lq')                        .val(data.f_SFGZZT         || '');
                $('#sfgzzt_edit_lq')                               .val(data.f_SFGZZT_alias   || '');
                $('#sbyxzt_hidden_edit_lq')                        .val(data.f_SBYXZT         || '');
                $('#sbyxzt_edit_lq')                               .val(data.f_SBYXZT_alias   || '');
                $('#yxzsd_hidden_edit_lq')                         .val(data.f_YXZSD          || '');
                $('#yxzsd_edit_lq')                                .val(data.f_YXZSD_alias    || '');
                $('#gzzsd_hidden_edit_lq')                         .val(data.f_GZZSD          || '');
                $('#gzzsd_edit_lq')                                .val(data.f_GZZSD_alias    || '');
                $('#Electric_meter_number_edit_lq')                .val(data.f_Electric_meter_number || '');

            });
        });

        /**
         * 删除点击事件
         *
         */
        $(document).on('click', '#waterCoolantTable button.delete', function () {

            var F_ID = $(this).data('id');

            swal(
                {
                    title : "您确定要删除吗?",
                    text : "信息删除后将不可恢复!",
                    type : "warning",
                    showCancelButton : true,
                    confirmButtonColor : "#1ab394",
                    confirmButtonText : "确定",
                    closeOnConfirm : false
                },function () {
                    deleteWaterCoolantUnit(F_ID, function () {


                    })
                }
            );

        });

        //删除表单信息
        function deleteWaterCoolantUnit(F_ID) {

            if (!F_ID) {
                console.warn('无效参数！');
                return;
            }

            $.ajax({
                type    : "POST",
                url     : _ctx + "/view/strongAndWeakElectricityIntegration/deviceConfig/chilledWater/delete",//冷却水和冷冻水共用一个
                dataType: "json",
                data: {
                    F_ID : F_ID,
                    f_TYPE_ID: '2'

                },
                success: function (result) {

                    var status = result && result.status;

                    if(status === "1"){

                        getPagingPage({}, function (data) {
                            showPagingPage('water_coolant_page', data);
                        });

                        swal({
                            title : "删除成功",// 展示的标题
                            text : "",// 内容
                            type: "success",
                            showCloseButton : false, // 展示关闭按钮
                            allowOutsideClick : false,
                            showConfirmButton : false,
                            timer: 1000
                        });

                    }else {

                        swal("删除失败!",null,"error");
                    }

                },
                error: function (result) {
                    swal("删除失败!",null,"error");
                    console.warn(result)
                }
            });

        }

        // 获取分页页面
        function getPagingPage(param, callback){

            if(typeof callback !== 'function'){
                return;
            }

            param = param || {};

            $.ajax({
                url     : _ctx + '/view/strongAndWeakElectricityIntegration/deviceConfig/waterCoolant/getPaging',
                type    : "post",
                data    : {
                    param :param,
                    F_TYPE_ID: "2",
                },
                success : function(result) {
                    callback(result);
                },

                error : function(result) {
                    console.log(result)
                }
            });

        }

        // 显示分页
        function showPagingPage(pageId, page){

            if(!page || !pageId){
                return;
            }

            $('#' + pageId).html(page);
        }

    })(jQuery, window, document);
</script>
