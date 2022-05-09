<#--
 xiepufeng

 电表配置页面
-->

<style>
    .ztree li a.curSelectedNode {
        padding-top: 0px;
        background-color: rgba(31, 255, 8, 0);
        color: black;
        height: 16px;
        /*border: 1px #FFB951 solid;*/
        opacity: 0.8;
    }
    #tree_device_type_modal_fresconfig {
        position: absolute;
        left:71%;
        top: 11.5%;
    }
</style>

<!-- 信息表格模块 -->
<div class="information-model">

			<span class="Subtitle">
				<i class="fa fa-user" aria-hidden="true"></i>&nbsp;新风配置列表>>>
			</span>

    <!-- 增加按钮 -->
    <a href="javascript:void(-1);" id="fres_config_add" class="btn btn-primary toLeft">
        <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;增加
    </a>

</div>

<!---分页列表----->
<div class="ibox" id="fres_page" style="height:90%">
</div>

<#-- 添加模态框 -->
<div class="modal fade" id="fres_modal_add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" style="margin-top: 8%;">
        <div class="modal-content" style="width: 700px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 增加新风配置信息</h4>
            </div>
            <div class="modal-body">
                <form id="fres_add_form" name="fres_add_form" class="form-horizontal">
                    <div  style="height: 70%;overflow-x: hidden;overflow-y: auto;">

                        <div class="form-group">
                            <label class="col-sm-3 control-label">名称
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input style="width: 390px"
                                       type="text"
                                       id="fres_name_add"
                                       name="fres_name_add"
                                       class="form-control"
                                       placeholder="请输入名称"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">位置
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input style="width: 390px"
                                       type="text"
                                       id="freshair_address_add"
                                       name="freshair_address_add"
                                       class="form-control"
                                       placeholder="请输入位置"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">机组启停
                            </label>
                            <div class="col-sm-8">
                                <input id="unit_start_and_stop_add_hidden"
                                       name="unit_start_and_stop_add_hidden"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="unit_start_and_stop_add"
                                       name="unit_start_and_stop_add"
                                       class="form-control"
                                       placeholder="请选择机组启停"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">机组运行状态
                            </label>
                            <div class="col-sm-8">
                                <input id="unit_operating_status_add_hidden"
                                       name="unit_operating_status_add_hidden"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="unit_operating_status_add"
                                       name="unit_operating_status_add"
                                       class="form-control"
                                       placeholder="请选择机组运行状态"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">机组手自动状态
                            </label>
                            <div class="col-sm-8">
                                <input id="crew_manual_status_add_hidden"
                                       name="crew_manual_status_add_hidden"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="crew_manual_status_add"
                                       name="crew_manual_status_add"
                                       class="form-control"
                                       placeholder="请选择机组手自动状态"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">机组故障状态
                            </label>
                            <div class="col-sm-8">
                                <input id="unit_fault_status_add_hidden"
                                       name="unit_fault_status_add_hidden"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="unit_fault_status_add"
                                       name="unit_fault_status_add"
                                       class="form-control"
                                       placeholder="新风机组故障状态"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">风阀开度控制
                            </label>
                            <div class="col-sm-8">
                                <input id="damper_opening_control_add_hidden"
                                       name="damper_opening_control_add_hidden"
                                       type="hidden"
                                >
                                <input
                                        style="width: 390px"
                                        type="text"
                                        id="damper_opening_control_add"
                                        name="damper_opening_control_add"
                                        class="form-control"
                                        placeholder="请选择风阀开度控制"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">风阀开度反馈
                            </label>
                            <div class="col-sm-8">
                                <input id="damper_opening_feedback_add_hidden"
                                       name="damper_opening_feedback_add_hidden"
                                       type="hidden"
                                >
                                <input
                                        style="width: 390px"
                                        type="text"
                                        id="damper_opening_feedback_add"
                                        name="damper_opening_feedback_add"
                                        class="form-control"
                                        placeholder="请选择风阀开度反馈"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">风阀开
                            </label>
                            <div class="col-sm-8">
                                <input id="damper_open_add_hidden"
                                       name="damper_open_add_hidden"
                                       type="hidden"
                                >
                                <input
                                        style="width: 390px"
                                        type="text"
                                        id="damper_open_add"
                                        name="damper_open_add"
                                        class="form-control"
                                        placeholder="请选择风阀开"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">风阀关
                            </label>
                            <div class="col-sm-8">
                                <input id="damper_off_add_hidden"
                                       name="damper_off_add_hidden"
                                       type="hidden"
                                >
                                <input
                                        style="width: 390px"
                                        type="text"
                                        id="damper_off_add"
                                        name="damper_off_add"
                                        class="form-control"
                                        placeholder="请选择风阀关"
                                >
                            </div>
                        </div><div class="form-group">
                            <label class="col-sm-3 control-label">风阀开到位
                            </label>
                            <div class="col-sm-8">
                                <input id="air_valve_opens_in_place_add_hidden"
                                       name="air_valve_opens_in_place_add_hidden"
                                       type="hidden"
                                >
                                <input
                                        style="width: 390px"
                                        type="text"
                                        id="air_valve_opens_in_place_add"
                                        name="air_valve_opens_in_place_add"
                                        class="form-control"
                                        placeholder="请选择风阀开到位"
                                >
                            </div>
                        </div><div class="form-group">
                            <label class="col-sm-3 control-label">风阀关到位
                            </label>
                            <div class="col-sm-8">
                                <input id="air_valve_closed_in_place_add_hidden"
                                       name="air_valve_closed_in_place_add_hidden"
                                       type="hidden"
                                >
                                <input
                                        style="width: 390px"
                                        type="text"
                                        id="air_valve_closed_in_place_add"
                                        name="air_valve_closed_in_place_add"
                                        class="form-control"
                                        placeholder="请选择风阀关到位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">水阀开度控制
                            </label>
                            <div class="col-sm-8">
                                <input id="water_valve_opening_control_add_hidden"
                                       name="water_valve_opening_control_add_hidden"
                                       type="hidden"
                                >
                                <input
                                        style="width: 390px"
                                        type="text"
                                        id="water_valve_opening_control_add"
                                        name="water_valve_opening_control_add"
                                        class="form-control"
                                        placeholder="请选择水阀开度控制"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">水阀开度反馈
                            </label>
                            <div class="col-sm-8">
                                <input id="water_valve_opening_feedback_add_hidden"
                                       name="water_valve_opening_feedback_add_hidden"
                                       type="hidden"
                                >
                                <input
                                        style="width: 390px"
                                        type="text"
                                        id="water_valve_opening_feedback_add"
                                        name="water_valve_opening_feedback_add"
                                        class="form-control"
                                        placeholder="请选择水阀开度反馈"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">送风温度
                            </label>
                            <div class="col-sm-8">
                                <input id="supply_air_temperature_add_hidden"
                                       name="supply_air_temperature_add_hidden"
                                       type="hidden"
                                >
                                <input
                                        style="width: 390px"
                                        type="text"
                                        id="supply_air_temperature_add"
                                        name="supply_air_temperature_add"
                                        class="form-control"
                                        placeholder="请选择送风温度"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">送风湿度
                            </label>
                            <div class="col-sm-8">
                                <input id="supply_air_humidity_add_hidden"
                                       name="supply_air_humidity_add_hidden"
                                       type="hidden"
                                >
                                <input
                                        style="width: 390px"
                                        type="text"
                                        id="supply_air_humidity_add"
                                        name="supply_air_humidity_add"
                                        class="form-control"
                                        placeholder="请选择送风湿度"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">新风温度
                            </label>
                            <div class="col-sm-8">
                                <input id="fresh_air_temperature_add_hidden"
                                       name="fresh_air_temperature_add_hidden"
                                       type="hidden"
                                >
                                <input
                                        style="width: 390px"
                                        type="text"
                                        id="fresh_air_temperature_add"
                                        name="fresh_air_temperature_add"
                                        class="form-control"
                                        placeholder="请选择新风温度"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">新风湿度
                            </label>
                            <div class="col-sm-8">
                                <input id="fresh_air_humidity_add_hidden"
                                       name="fresh_air_humidity_add_hidden"
                                       type="hidden"
                                >
                                <input
                                        style="width: 390px"
                                        type="text"
                                        id="fresh_air_humidity_add"
                                        name="fresh_air_humidity_add"
                                        class="form-control"
                                        placeholder="请选择新风湿度"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">加湿开度控制
                            </label>
                            <div class="col-sm-8">
                                <input id="humidification_opening_control_add_hidden"
                                       name="humidification_opening_control_add_hidden"
                                       type="hidden"
                                >
                                <input
                                        style="width: 390px"
                                        type="text"
                                        id="humidification_opening_control_add"
                                        name="humidification_opening_control_add"
                                        class="form-control"
                                        placeholder="请选择加湿开度控制"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">加湿开度反馈
                            </label>
                            <div class="col-sm-8">
                                <input id="humidification_opening_feedback_add_hidden"
                                       name="humidification_opening_feedback_add_hidden"
                                       type="hidden"
                                >
                                <input
                                        style="width: 390px"
                                        type="text"
                                        id="humidification_opening_feedback_add"
                                        name="humidification_opening_feedback_add"
                                        class="form-control"
                                        placeholder="请选择加湿开度反馈"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">加湿开
                            </label>
                            <div class="col-sm-8">
                                <input id="humidification_on_add_hidden"
                                       name="humidification_on_add_hidden"
                                       type="hidden"
                                >
                                <input
                                        style="width: 390px"
                                        type="text"
                                        id="humidification_on_add"
                                        name="humidification_on_add"
                                        class="form-control"
                                        placeholder="请选择加湿开"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">加湿关
                            </label>
                            <div class="col-sm-8">
                                <input id="humidification_off_add_hidden"
                                       name="humidification_off_add_hidden"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="humidification_off_add"
                                       name="humidification_off_add"
                                       class="form-control"
                                       placeholder="请选择加湿关"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">二氧化碳
                            </label>
                            <div class="col-sm-8">
                                <input id="carbon_dioxide_add_hidden"
                                       name="carbon_dioxide_add_hidden"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="carbon_dioxide_add"
                                       name="carbon_dioxide_add"
                                       class="form-control"
                                       placeholder="请选择二氧化碳"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">防冻报警
                            </label>
                            <div class="col-sm-8">
                                <input id="antifreeze_alarm_add_hidden"
                                       name="antifreeze_alarm_add_hidden"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="antifreeze_alarm_add"
                                       name="antifreeze_alarm_add"
                                       class="form-control"
                                       placeholder="请选择防冻报警"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">过滤网压差报警
                            </label>
                            <div class="col-sm-8">
                                <input id="filter_screen_pressure_difference_alarm_add_hidden"
                                       name="filter_screen_pressure_difference_alarm_add_hidden"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="filter_screen_pressure_difference_alarm_add"
                                       name="filter_screen_pressure_difference_alarm_add"
                                       class="form-control"
                                       placeholder="请选择过滤网压差报警"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">风机压差
                            </label>
                            <div class="col-sm-8">
                                <input id="fan_pressure_difference_add_hidden"
                                       name="fan_pressure_difference_add_hidden"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="fan_pressure_difference_add"
                                       name="fan_pressure_difference_add"
                                       class="form-control"
                                       placeholder="请选择风机压差"
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

<#-- 编辑模态框 -->
<div class="modal fade" id="fres_modal_edit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" style="margin-top: 8%;">
        <div class="modal-content" style="width: 700px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 编辑新风配置信息</h4>
            </div>
            <div class="modal-body">
                <form id="fres_edit_form" name="fres_edit_form" class="form-horizontal">
                    <div  style="height: 70%;overflow-x: hidden;overflow-y: auto;">

                        <input type="hidden" id="id_fres_edit" name="id_fres_edit"/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">名称
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input style="width: 390px"
                                       type="text"
                                       id="fres_name_edit"
                                       name="fres_name_edit"
                                       class="form-control"
                                       placeholder="请输入名称"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">位置
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input style="width: 390px"
                                       type="text"
                                       id="freshair_address_edit"
                                       name="freshair_address_edit"
                                       class="form-control"
                                       placeholder="请输入位置"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">机组启停
                            </label>
                            <div class="col-sm-8">
                                <input id="unit_start_and_stop_edit_hidden"
                                       name="unit_start_and_stop_edit_hidden"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="unit_start_and_stop_edit"
                                       name="unit_start_and_stop_edit"
                                       class="form-control"
                                       placeholder="请选择机组启停"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">机组运行状态
                            </label>
                            <div class="col-sm-8">
                                <input id="unit_operating_status_edit_hidden"
                                       name="unit_operating_status_edit_hidden"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="unit_operating_status_edit"
                                       name="unit_operating_status_edit"
                                       class="form-control"
                                       placeholder="请选择机组运行状态"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">机组手自动状态
                            </label>
                            <div class="col-sm-8">
                                <input id="crew_manual_status_edit_hidden"
                                       name="crew_manual_status_edit_hidden"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="crew_manual_status_edit"
                                       name="crew_manual_status_edit"
                                       class="form-control"
                                       placeholder="请选择机组手自动状态"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">机组故障状态
                            </label>
                            <div class="col-sm-8">
                                <input id="unit_fault_status_edit_hidden"
                                       name="unit_fault_status_edit_hidden"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="unit_fault_status_edit"
                                       name="unit_fault_status_edit"
                                       class="form-control"
                                       placeholder="新风机组故障状态"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">风阀开度控制
                            </label>
                            <div class="col-sm-8">
                                <input id="damper_opening_control_edit_hidden"
                                       name="damper_opening_control_edit_hidden"
                                       type="hidden"
                                >
                                <input
                                        style="width: 390px"
                                        type="text"
                                        id="damper_opening_control_edit"
                                        name="damper_opening_control_edit"
                                        class="form-control"
                                        placeholder="请选择风阀开度控制"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">风阀开度反馈
                            </label>
                            <div class="col-sm-8">
                                <input id="damper_opening_feedback_edit_hidden"
                                       name="damper_opening_feedback_edit_hidden"
                                       type="hidden"
                                >
                                <input
                                        style="width: 390px"
                                        type="text"
                                        id="damper_opening_feedback_edit"
                                        name="damper_opening_feedback_edit"
                                        class="form-control"
                                        placeholder="请选择风阀开度反馈"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">风阀开
                            </label>
                            <div class="col-sm-8">
                                <input id="damper_open_edit_hidden"
                                       name="damper_open_edit_hidden"
                                       type="hidden"
                                >
                                <input
                                        style="width: 390px"
                                        type="text"
                                        id="damper_open_edit"
                                        name="damper_open_edit"
                                        class="form-control"
                                        placeholder="请选择风阀开"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">风阀关
                            </label>
                            <div class="col-sm-8">
                                <input id="damper_off_edit_hidden"
                                       name="damper_off_edit_hidden"
                                       type="hidden"
                                >
                                <input
                                        style="width: 390px"
                                        type="text"
                                        id="damper_off_edit"
                                        name="damper_off_edit"
                                        class="form-control"
                                        placeholder="请选择风阀关"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">风阀开到位
                            </label>
                            <div class="col-sm-8">
                                <input id="air_valve_opens_in_place_edit_hidden"
                                       name="air_valve_opens_in_place_edit_hidden"
                                       type="hidden"
                                >
                                <input
                                        style="width: 390px"
                                        type="text"
                                        id="air_valve_opens_in_place_edit"
                                        name="air_valve_opens_in_place_edit"
                                        class="form-control"
                                        placeholder="请选择风阀开到位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">风阀关到位
                            </label>
                            <div class="col-sm-8">
                                <input id="air_valve_closed_in_place_edit_hidden"
                                       name="air_valve_closed_in_place_edit_hidden"
                                       type="hidden"
                                >
                                <input
                                        style="width: 390px"
                                        type="text"
                                        id="air_valve_closed_in_place_edit"
                                        name="air_valve_closed_in_place_edit"
                                        class="form-control"
                                        placeholder="请选择风阀关到位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">水阀开度控制
                            </label>
                            <div class="col-sm-8">
                                <input id="water_valve_opening_control_edit_hidden"
                                       name="water_valve_opening_control_edit_hidden"
                                       type="hidden"
                                >
                                <input
                                        style="width: 390px"
                                        type="text"
                                        id="water_valve_opening_control_edit"
                                        name="water_valve_opening_control_edit"
                                        class="form-control"
                                        placeholder="请选择水阀开度控制"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">水阀开度反馈
                            </label>
                            <div class="col-sm-8">
                                <input id="water_valve_opening_feedback_edit_hidden"
                                       name="water_valve_opening_feedback_edit_hidden"
                                       type="hidden"
                                >
                                <input
                                        style="width: 390px"
                                        type="text"
                                        id="water_valve_opening_feedback_edit"
                                        name="water_valve_opening_feedback_edit"
                                        class="form-control"
                                        placeholder="请选择水阀开度反馈"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">送风温度
                            </label>
                            <div class="col-sm-8">
                                <input id="supply_air_temperature_edit_hidden"
                                       name="supply_air_temperature_edit_hidden"
                                       type="hidden"
                                >
                                <input
                                        style="width: 390px"
                                        type="text"
                                        id="supply_air_temperature_edit"
                                        name="supply_air_temperature_edit"
                                        class="form-control"
                                        placeholder="请选择送风温度"
                                >
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-sm-3 control-label">送风湿度
                            </label>
                            <div class="col-sm-8">
                                <input id="supply_air_humidity_edit_hidden"
                                       name="supply_air_humidity_edit_hidden"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="supply_air_humidity_edit"
                                       name="supply_air_humidity_edit"
                                       class="form-control"
                                       placeholder="请选择送风湿度"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">新风温度
                            </label>
                            <div class="col-sm-8">
                                <input id="fresh_air_temperature_edit_hidden"
                                       name="fresh_air_temperature_edit_hidden"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="fresh_air_temperature_edit"
                                       name="fresh_air_temperature_edit"
                                       class="form-control"
                                       placeholder="请选择新风温度"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">新风湿度
                            </label>
                            <div class="col-sm-8">
                                <input id="fresh_air_humidity_edit_hidden"
                                       name="fresh_air_humidity_edit_hidden"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="fresh_air_humidity_edit"
                                       name="fresh_air_humidity_edit"
                                       class="form-control"
                                       placeholder="请选择新风湿度"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">加湿开度控制
                            </label>
                            <div class="col-sm-8">
                                <input id="humidification_opening_control_edit_hidden"
                                       name="humidification_opening_control_edit_hidden"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="humidification_opening_control_edit"
                                       name="humidification_opening_control_edit"
                                       class="form-control"
                                       placeholder="请选择加湿开度控制"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">加湿开度反馈
                            </label>
                            <div class="col-sm-8">
                                <input id="humidification_opening_feedback_edit_hidden"
                                       name="humidification_opening_feedback_edit_hidden"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="humidification_opening_feedback_edit"
                                       name="humidification_opening_feedback_edit"
                                       class="form-control"
                                       placeholder="请选择加湿开度反馈"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">加湿开
                            </label>
                            <div class="col-sm-8">
                                <input id="humidification_on_edit_hidden"
                                       name="humidification_on_edit_hidden"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="humidification_on_edit"
                                       name="humidification_on_edit"
                                       class="form-control"
                                       placeholder="请选择加湿开"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">加湿关
                            </label>
                            <div class="col-sm-8">
                                <input id="humidification_off_edit_hidden"
                                       name="humidification_off_edit_hidden"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="humidification_off_edit"
                                       name="humidification_off_edit"
                                       class="form-control"
                                       placeholder="请选择加湿关"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">二氧化碳
                            </label>
                            <div class="col-sm-8">
                                <input id="carbon_dioxide_edit_hidden"
                                       name="carbon_dioxide_edit_hidden"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="carbon_dioxide_edit"
                                       name="carbon_dioxide_edit"
                                       class="form-control"
                                       placeholder="请选择二氧化碳"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">防冻报警
                            </label>
                            <div class="col-sm-8">
                                <input id="antifreeze_alarm_edit_hidden"
                                       name="antifreeze_alarm_edit_hidden"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="antifreeze_alarm_edit"
                                       name="antifreeze_alarm_edit"
                                       class="form-control"
                                       placeholder="请选择防冻报警"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">过滤网压差报警
                            </label>
                            <div class="col-sm-8">
                                <input id="filter_screen_pressure_difference_alarm_edit_hidden"
                                       name="filter_screen_pressure_difference_alarm_edit_hidden"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="filter_screen_pressure_difference_alarm_edit"
                                       name="filter_screen_pressure_difference_alarm_edit"
                                       class="form-control"
                                       placeholder="请选择过滤网压差报警"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">风机压差
                            </label>
                            <div class="col-sm-8">
                                <input id="fan_pressure_difference_edit_hidden"
                                       name="fan_pressure_difference_edit_hidden"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="fan_pressure_difference_edit"
                                       name="fan_pressure_difference_edit"
                                       class="form-control"
                                       placeholder="请选择风机压差"
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

<#-- 设备类型模态框 -->
<#-- wanghongjie  修改所有的id名称,加上电表的标识 -->
<div class="modal fade" id="tree_device_type_modal_fresconfig" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" style="margin-top: 8%;">
        <div class="modal-content" style="width: 350px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 请选择设备类型</h4>
            </div>
            <div class="modal-body" style="height: 600px">
                <div style="position: relative" class="zc_search find">
                    <input type="text" class="find-style" id="tree_device_type_search_fres_config_input" name="eqTypeInfo" placeholder="设备类型">
                    <button id="tree_device_type_search_fres_config_button"><i class="fa fa-search" aria-hidden="true"></i></button>
                </div>

                <#--设备类型树-->
                <div id="tree_device_fres_config_type" class="Information_area ztree"></div>

            </div>
        </div><!-- end modal-content -->
    </div>
</div> <!-- end addParkBasicInfo -->

<script type="text/javascript">

    var fresStrongWeakElectrical = (function($, window, document) {

        var ctx = '${ctx}';
        var tree;// 树对象
        var modalAddActive = false; // 保存添加模态框是否是显示状态 true 显示 | false 隐藏
        var modalEditActive = false; // 保存编辑模态框是否是显示状态 true 显示 | false 隐藏
        var inputObj; // 模态框内输入框保存对象
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
                showPagingPage('fres_page', data);

            });

        });

        // 初始化设备类型树
        function initTree() {
            tree = new Tree({
                container: 'tree_device_fres_config_type',
                idKey    : 'f_sys_name',
                pIdKey   : 'f_psys_name',
                name     : 'f_nick_name',
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
                        inputObj.value = node.f_nick_name;
                        $(inputObj).prev().val(node.f_sys_name);
                    }
                }
            });
        }

        // 获取设备树信息
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

        // 查询设备信息
        function queryfresConfig(obj, callback) {

            if(typeof callback !== 'function'){
                return;
            }

            obj = obj || {};

            $.ajax({
                type    : "POST",
                url     : _ctx + "/view/BESFreshairconfigController/selectFreshairByID",
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


        //触发搜索的回车事件， 以系统别名搜索设备树展开并以红色字体标注
        $('#tree_device_type_search_fres_config_input').keydown(function (e){
            if(e.which !== 13){
                return;
            }

            tree.search('tree_device_type_search_fres_config_input', 'f_nick_name');

        });

        // 触发搜索点击事件， 以系统别名搜索设备树展开并以红色字体标注
        $('#tree_device_type_search_fres_config_button').click(function () {

            tree.search('tree_device_type_search_fres_config_input', 'f_nick_name');
        });

        // 显示添加模态框
        $('#fres_config_add').click(function () {
            $('#fres_modal_add').modal('show');
            modalAddActive = true;

        });

        // 显示编辑模态框事件
        $('#fres_modal_edit').on('show.bs.modal', function () {
            modalEditActive = true;
        });

        // 添加输入框得到焦点时显示设备类型模态框
        $(
            '#unit_start_and_stop_add,'+
            '#unit_operating_status_add,'+
            '#crew_manual_status_add,'+
            '#unit_fault_status_add,'+
            '#damper_opening_control_add,'+
            '#damper_opening_feedback_add,'+
            '#damper_open_add,'+
            '#damper_off_add,'+
            '#air_valve_opens_in_place_add,'+
            '#air_valve_closed_in_place_add,'+
            '#water_valve_opening_control_add,'+
            '#water_valve_opening_feedback_add,'+
            '#supply_air_temperature_add,'+
            '#supply_air_humidity_add,'+
            '#fresh_air_temperature_add,'+
            '#fresh_air_humidity_add,'+
            '#humidification_opening_control_add,'+
            '#humidification_opening_feedback_add,'+
            '#humidification_on_add,'+
            '#humidification_off_add,'+
            '#carbon_dioxide_add,'+
            '#antifreeze_alarm_add,'+
            '#filter_screen_pressure_difference_alarm_add,'+
            '#fan_pressure_difference_add,'+


            '#unit_start_and_stop_edit,'+
            '#unit_operating_status_edit,'+
            '#crew_manual_status_edit,'+
            '#unit_fault_status_edit,'+
            '#damper_opening_control_edit,'+
            '#damper_opening_feedback_edit,'+
            '#damper_open_edit,'+
            '#damper_off_edit,'+
            '#air_valve_opens_in_place_edit,'+
            '#air_valve_closed_in_place_edit,'+
            '#water_valve_opening_control_edit,'+
            '#water_valve_opening_feedback_edit,'+
            '#supply_air_temperature_edit,'+
            '#supply_air_humidity_edit,'+
            '#fresh_air_temperature_edit,'+
            '#fresh_air_humidity_edit,'+
            '#humidification_opening_control_edit,'+
            '#humidification_opening_feedback_edit,'+
            '#humidification_on_edit,'+
            '#humidification_off_edit,'+
            '#carbon_dioxide_edit,'+
            '#antifreeze_alarm_edit,'+
            '#filter_screen_pressure_difference_alarm_edit,'+
            '#fan_pressure_difference_edit'


        ).focus(function (obj) {
            inputObj = obj.target;
            $('#tree_device_type_modal_fresconfig').modal('show');
        });


        // 当添加模态框关闭时同时关闭设备类型模态框, 清空表单数据
        $('#fres_modal_add').on('hide.bs.modal', function () {
            modalAddActive = false;
            inputObj = null;
            $('#tree_device_type_modal_fresconfig').modal('hide');

            $('#fres_name_add')                                  .val('');
            $('#freshair_address_add')                         .val('');

            $('#unit_start_and_stop_add_hidden').val('');
            $('#unit_operating_status_add_hidden').val('');
            $('#crew_manual_status_add_hidden').val('');
            $('#unit_fault_status_add_hidden').val('');
            $('#damper_opening_control_add_hidden').val('');
            $('#damper_opening_feedback_add_hidden').val('');
            $('#damper_open_add_hidden').val('');
            $('#damper_off_add_hidden').val('');
            $('#air_valve_opens_in_place_add_hidden').val('');
            $('#air_valve_closed_in_place_add_hidden').val('');
            $('#water_valve_opening_control_add_hidden').val('');
            $('#water_valve_opening_feedback_add_hidden').val('');
            $('#supply_air_temperature_add_hidden').val('');
            $('#supply_air_humidity_add_hidden').val('');
            $('#fresh_air_temperature_add_hidden').val('');
            $('#fresh_air_humidity_add_hidden').val('');
            $('#humidification_opening_control_add_hidden').val('');
            $('#humidification_opening_feedback_add_hidden').val('');
            $('#humidification_on_add_hidden').val('');
            $('#humidification_off_add_hidden').val('');
            $('#carbon_dioxide_add_hidden').val('');
            $('#antifreeze_alarm_add_hidden').val('');
            $('#filter_screen_pressure_difference_alarm_add_hidden').val('');
            $('#fan_pressure_difference_add_hidden').val('');

            $('unit_start_and_stop_add').val('');
            $('unit_operating_status_add').val('');
            $('crew_manual_status_add').val('');
            $('unit_fault_status_add').val('');
            $('damper_opening_control_add').val('');
            $('damper_opening_feedback_add').val('');
            $('damper_open_add').val('');
            $('damper_off_add').val('');
            $('air_valve_opens_in_place_add').val('');
            $('air_valve_closed_in_place_add').val('');
            $('water_valve_opening_control_add').val('');
            $('water_valve_opening_feedback_add').val('');
            $('supply_air_temperature_add').val('');
            $('supply_air_humidity_add').val('');
            $('fresh_air_temperature_add').val('');
            $('fresh_air_humidity_add').val('');
            $('humidification_opening_control_add').val('');
            $('humidification_opening_feedback_add').val('');
            $('humidification_on_add').val('');
            $('humidification_off_add').val('');
            $('carbon_dioxide_add').val('');
            $('antifreeze_alarm_add').val('');
            $('filter_screen_pressure_difference_alarm_add').val('');
            $('fan_pressure_difference_add').val('');



            // 重置添加验证表单
            addValidate.resetForm()
        });

        // 关闭编辑模态框时清空输入框
        $("#fres_modal_edit").on('hidden.bs.modal', function(event) {

            inputObj = null;
            modalEditActive = false;
            $('#tree_device_type_modal_fresconfig').modal('hide');

            $('#fres_name_edit')                             .val('');
            $('#freshair_address_edit')                    .val('');

            $('#unit_start_and_stop_edit_hidden').val('');
            $('#unit_operating_status_edit_hidden').val('');
            $('#crew_manual_status_edit_hidden').val('');
            $('#unit_fault_status_edit_hidden').val('');
            $('#damper_opening_control_edit_hidden').val('');
            $('#damper_opening_feedback_edit_hidden').val('');
            $('#damper_open_edit_hidden').val('');
            $('#damper_off_edit_hidden').val('');
            $('#air_valve_opens_in_place_edit_hidden').val('');
            $('#air_valve_closed_in_place_edit_hidden').val('');
            $('#water_valve_opening_control_edit_hidden').val('');
            $('#water_valve_opening_feedback_edit_hidden').val('');
            $('#supply_air_temperature_edit_hidden').val('');
            $('#supply_air_humidity_edit_hidden').val('');
            $('#fresh_air_temperature_edit_hidden').val('');
            $('#fresh_air_humidity_edit_hidden').val('');
            $('#humidification_opening_control_edit_hidden').val('');
            $('#humidification_opening_feedback_edit_hidden').val('');
            $('#humidification_on_edit_hidden').val('');
            $('#humidification_off_edit_hidden').val('');
            $('#carbon_dioxide_edit_hidden').val('');
            $('#antifreeze_alarm_edit_hidden').val('');
            $('#filter_screen_pressure_difference_alarm_edit_hidden').val('');
            $('#fan_pressure_difference_edit_hidden').val('');

            $('unit_start_and_stop_edit').val('');
            $('unit_operating_status_edit').val('');
            $('crew_manual_status_edit').val('');
            $('unit_fault_status_edit').val('');
            $('damper_opening_control_edit').val('');
            $('damper_opening_feedback_edit').val('');
            $('damper_open_edit').val('');
            $('damper_off_edit').val('');
            $('air_valve_opens_in_place_edit').val('');
            $('air_valve_closed_in_place_edit').val('');
            $('water_valve_opening_control_edit').val('');
            $('water_valve_opening_feedback_edit').val('');
            $('supply_air_temperature_edit').val('');
            $('supply_air_humidity_edit').val('');
            $('fresh_air_temperature_edit').val('');
            $('fresh_air_humidity_edit').val('');
            $('humidification_opening_control_edit').val('');
            $('humidification_opening_feedback_edit').val('');
            $('humidification_on_edit').val('');
            $('humidification_off_edit').val('');
            $('carbon_dioxide_edit').val('');
            $('antifreeze_alarm_edit').val('');
            $('filter_screen_pressure_difference_alarm_edit').val('');
            $('fan_pressure_difference_edit').val('');


            // 重置表单
            editValidate.resetForm()

        });

        // 当设备树模态框关闭时折叠设备树、清空搜索框内容
        $('#tree_device_type_modal_fresconfig').on('hide.bs.modal', function () {
            tree.tree.expandAll( false );
            $('#tree_device_type_search_fres_config_input').val('');
        });


        /**
         * 编辑点击事件
         *
         */
        $(document).on('click', '#freshConfigTable button.edit', function () {

            var id = $(this).data('id');

            queryfresConfig({id}, function (obj) {

                var data = obj && obj.data && obj.data[0];

                if(!data){
                    return;
                }

                $('#id_fres_edit')                   .val(data.id                   || '');
                $('#fres_name_edit')                 .val(data.name                 || '');
                $('#freshair_address_edit')              .val(data.freshair_address          || '');

                $('#unit_start_and_stop_edit').val(data.unit_start_and_stop_display||'');
                $('#unit_operating_status_edit').val(data.unit_operating_status_display||'');
                $('#crew_manual_status_edit').val(data.crew_manual_status_display||'');
                $('#unit_fault_status_edit').val(data.unit_fault_status_display||'');
                $('#damper_opening_control_edit').val(data.damper_opening_control_display||'');
                $('#damper_opening_feedback_edit').val(data.damper_opening_feedback_display||'');
                $('#damper_open_edit').val(data.damper_open_display||'');
                $('#damper_off_edit').val(data.damper_off_display||'');
                $('#air_valve_opens_in_place_edit').val(data.air_valve_opens_in_place_display||'');
                $('#air_valve_closed_in_place_edit').val(data.air_valve_closed_in_place_display||'');
                $('#water_valve_opening_control_edit').val(data.water_valve_opening_control_display||'');
                $('#water_valve_opening_feedback_edit').val(data.water_valve_opening_feedback_display||'');
                $('#supply_air_temperature_edit').val(data.supply_air_temperature_display||'');
                $('#supply_air_humidity_edit').val(data.supply_air_humidity_display||'');
                $('#fresh_air_temperature_edit').val(data.fresh_air_temperature_display||'');
                $('#fresh_air_humidity_edit').val(data.fresh_air_humidity_display||'');
                $('#humidification_opening_control_edit').val(data.humidification_opening_control_display||'');
                $('#humidification_opening_feedback_edit').val(data.humidification_opening_feedback_display||'');
                $('#humidification_on_edit').val(data.humidification_on_display||'');
                $('#humidification_off_edit').val(data.humidification_off_display||'');
                $('#carbon_dioxide_edit').val(data.carbon_dioxide_display||'');
                $('#antifreeze_alarm_edit').val(data.antifreeze_alarm_display||'');
                $('#filter_screen_pressure_difference_alarm_edit').val(data.filter_screen_pressure_difference_alarm_display||'');
                $('#fan_pressure_difference_edit').val(data.fan_pressure_difference_display||'');

                $('#unit_start_and_stop_edit_hidden').val(data.unit_start_and_stop||'');
                $('#unit_operating_status_edit_hidden').val(data.unit_operating_status||'');
                $('#crew_manual_status_edit_hidden').val(data.crew_manual_status||'');
                $('#unit_fault_status_edit_hidden').val(data.unit_fault_status||'');
                $('#damper_opening_control_edit_hidden').val(data.damper_opening_control||'');
                $('#damper_opening_feedback_edit_hidden').val(data.damper_opening_feedback||'');
                $('#damper_open_edit_hidden').val(data.damper_open||'');
                $('#damper_off_edit_hidden').val(data.damper_off||'');
                $('#air_valve_opens_in_place_edit_hidden').val(data.air_valve_opens_in_place||'');
                $('#air_valve_closed_in_place_edit_hidden').val(data.air_valve_closed_in_place||'');
                $('#water_valve_opening_control_edit_hidden').val(data.water_valve_opening_control||'');
                $('#water_valve_opening_feedback_edit_hidden').val(data.water_valve_opening_feedback||'');
                $('#supply_air_temperature_edit_hidden').val(data.supply_air_temperature||'');
                $('#supply_air_humidity_edit_hidden').val(data.supply_air_humidity||'');
                $('#fresh_air_temperature_edit_hidden').val(data.fresh_air_temperature||'');
                $('#fresh_air_humidity_edit_hidden').val(data.fresh_air_humidity||'');
                $('#humidification_opening_control_edit_hidden').val(data.humidification_opening_control||'');
                $('#humidification_opening_feedback_edit_hidden').val(data.humidification_opening_feedback||'');
                $('#humidification_on_edit_hidden').val(data.humidification_on||'');
                $('#humidification_off_edit_hidden').val(data.humidification_off||'');
                $('#carbon_dioxide_edit_hidden').val(data.carbon_dioxide||'');
                $('#antifreeze_alarm_edit_hidden').val(data.antifreeze_alarm||'');
                $('#filter_screen_pressure_difference_alarm_edit_hidden').val(data.filter_screen_pressure_difference_alarm||'');
                $('#fan_pressure_difference_edit_hidden').val(data.fan_pressure_difference||'');



            });
        });

        /**
         * 编辑点击事件
         *
         */
        $(document).on('click', '#freshConfigTable button.delete', function () {

            var id = $(this).data('id');

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
                    deletefresConfig(id);
                }
            );

        });
        // 添加输入框验证
        var addValidate = $("#fres_add_form").validate({
            rules: {
                fres_name_add:{
                    required: true,
                    maxlength: 50

                },
                freshair_address_add:{
                    required: true,
                    maxlength: 50

                }

            },
            messages: {
                fres_name_add:{
                    required: '请输入名称'
                },
                freshair_address_add:{
                    required: '请输入位置'
                }
            },

            submitHandler: function(formData) {
                addSubmit(formData);
            }

        });



        // 编辑输入框验证
        var editValidate = $("#fres_edit_form").validate({
            rules: {
                fres_name_edit:{
                    required: true,
                    maxlength: 50

                },
                freshair_address_edit:{
                    required: true,
                    maxlength: 50

                }
            },
            messages: {
                fres_name_edit:{
                    required: '请输入名称'
                },
                freshair_address_edit:{
                    required: '请输入位置'
                }
            },
            submitHandler: function(formData) {
                editSubmit(formData);
            }

        });



        // 提交表单信息
        function addSubmit(formData){

            if(!formData){
                return;
            }

            //获取表单数据
            var formData = form2js(formData ,null, null, null, null, true);

            $.ajax({
                type    : "POST",
                url     : _ctx + "/view/BESFreshairconfigController/insertFreshair",
                dataType: "json",
                data: {
                    name                 : formData.fres_name_add,
                    freshair_address          : formData.freshair_address_add,

                    unit_start_and_stop:formData.unit_start_and_stop_add_hidden,
                    unit_operating_status:formData.unit_operating_status_add_hidden,
                    crew_manual_status:formData.crew_manual_status_add_hidden,
                    unit_fault_status:formData.unit_fault_status_add_hidden,
                    damper_opening_control:formData.damper_opening_control_add_hidden,
                    damper_opening_feedback:formData.damper_opening_feedback_add_hidden,
                    damper_open:formData.damper_open_add_hidden,
                    damper_off:formData.damper_off_add_hidden,
                    air_valve_opens_in_place:formData.air_valve_opens_in_place_add_hidden,
                    air_valve_closed_in_place:formData.air_valve_closed_in_place_add_hidden,
                    water_valve_opening_control:formData.water_valve_opening_control_add_hidden,
                    water_valve_opening_feedback:formData.water_valve_opening_feedback_add_hidden,
                    supply_air_temperature:formData.supply_air_temperature_add_hidden,
                    supply_air_humidity:formData.supply_air_humidity_add_hidden,
                    fresh_air_temperature:formData.fresh_air_temperature_add_hidden,
                    fresh_air_humidity:formData.fresh_air_humidity_add_hidden,
                    humidification_opening_control:formData.humidification_opening_control_add_hidden,
                    humidification_opening_feedback:formData.humidification_opening_feedback_add_hidden,
                    humidification_on:formData.humidification_on_add_hidden,
                    humidification_off:formData.humidification_off_add_hidden,
                    carbon_dioxide:formData.carbon_dioxide_add_hidden,
                    antifreeze_alarm:formData.antifreeze_alarm_add_hidden,
                    filter_screen_pressure_difference_alarm:formData.filter_screen_pressure_difference_alarm_add_hidden,
                    fan_pressure_difference:formData.fan_pressure_difference_add_hidden,

                    unit_start_and_stop_display:formData.unit_start_and_stop_add,
                    unit_operating_status_display:formData.unit_operating_status_add,
                    crew_manual_status_display:formData.crew_manual_status_add,
                    unit_fault_status_display:formData.unit_fault_status_add,
                    damper_opening_control_display:formData.damper_opening_control_add,
                    damper_opening_feedback_display:formData.damper_opening_feedback_add,
                    damper_open_display:formData.damper_open_add,
                    damper_off_display:formData.damper_off_add,
                    air_valve_opens_in_place_display:formData.air_valve_opens_in_place_add,
                    air_valve_closed_in_place_display:formData.air_valve_closed_in_place_add,
                    water_valve_opening_control_display:formData.water_valve_opening_control_add,
                    water_valve_opening_feedback_display:formData.water_valve_opening_feedback_add,
                    supply_air_temperature_display:formData.supply_air_temperature_add,
                    supply_air_humidity_display:formData.supply_air_humidity_add,
                    fresh_air_temperature_display:formData.fresh_air_temperature_add,
                    fresh_air_humidity_display:formData.fresh_air_humidity_add,
                    humidification_opening_control_display:formData.humidification_opening_control_add,
                    humidification_opening_feedback_display:formData.humidification_opening_feedback_add,
                    humidification_on_display:formData.humidification_on_add,
                    humidification_off_display:formData.humidification_off_add,
                    carbon_dioxide_display:formData.carbon_dioxide_add,
                    antifreeze_alarm_display:formData.antifreeze_alarm_add,
                    filter_screen_pressure_difference_alarm_display:formData.filter_screen_pressure_difference_alarm_add,
                    fan_pressure_difference_display:formData.fan_pressure_difference_add

                },
                success: function (result) {

                    var status = result && result.status;

                    if(status === "1"){

                        getPagingPage({}, function (data) {
                            showPagingPage('fres_page', data);

                        });

                        $('#fres_modal_add').modal('hide');

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

        // 更新表单信息
        function editSubmit(formData){

            if(!formData){
                return;
            }

            //获取表单数据
            var formData = form2js( formData, null, null, null, null, true);
            $.ajax({
                type    : "POST",
                url     : _ctx + "/view/BESFreshairconfigController/updateFreshair",
                dataType: "json",
                data: {
                    id                   : formData.id_fres_edit,
                    name                 : formData.fres_name_edit,
                    freshair_address          : formData.freshair_address_edit,

                    unit_start_and_stop:formData.unit_start_and_stop_edit_hidden,
                    unit_operating_status:formData.unit_operating_status_edit_hidden,
                    crew_manual_status:formData.crew_manual_status_edit_hidden,
                    unit_fault_status:formData.unit_fault_status_edit_hidden,
                    damper_opening_control:formData.damper_opening_control_edit_hidden,
                    damper_opening_feedback:formData.damper_opening_feedback_edit_hidden,
                    damper_open:formData.damper_open_edit_hidden,
                    damper_off:formData.damper_off_edit_hidden,
                    air_valve_opens_in_place:formData.air_valve_opens_in_place_edit_hidden,
                    air_valve_closed_in_place:formData.air_valve_closed_in_place_edit_hidden,
                    water_valve_opening_control:formData.water_valve_opening_control_edit_hidden,
                    water_valve_opening_feedback:formData.water_valve_opening_feedback_edit_hidden,
                    supply_air_temperature:formData.supply_air_temperature_edit_hidden,
                    supply_air_humidity:formData.supply_air_humidity_edit_hidden,
                    fresh_air_temperature:formData.fresh_air_temperature_edit_hidden,
                    fresh_air_humidity:formData.fresh_air_humidity_edit_hidden,
                    humidification_opening_control:formData.humidification_opening_control_edit_hidden,
                    humidification_opening_feedback:formData.humidification_opening_feedback_edit_hidden,
                    humidification_on:formData.humidification_on_edit_hidden,
                    humidification_off:formData.humidification_off_edit_hidden,
                    carbon_dioxide:formData.carbon_dioxide_edit_hidden,
                    antifreeze_alarm:formData.antifreeze_alarm_edit_hidden,
                    filter_screen_pressure_difference_alarm:formData.filter_screen_pressure_difference_alarm_edit_hidden,
                    fan_pressure_difference:formData.fan_pressure_difference_edit_hidden,

                    unit_start_and_stop_display:formData.unit_start_and_stop_edit,
                    unit_operating_status_display:formData.unit_operating_status_edit,
                    crew_manual_status_display:formData.crew_manual_status_edit,
                    unit_fault_status_display:formData.unit_fault_status_edit,
                    damper_opening_control_display:formData.damper_opening_control_edit,
                    damper_opening_feedback_display:formData.damper_opening_feedback_edit,
                    damper_open_display:formData.damper_open_edit,
                    damper_off_display:formData.damper_off_edit,
                    air_valve_opens_in_place_display:formData.air_valve_opens_in_place_edit,
                    air_valve_closed_in_place_display:formData.air_valve_closed_in_place_edit,
                    water_valve_opening_control_display:formData.water_valve_opening_control_edit,
                    water_valve_opening_feedback_display:formData.water_valve_opening_feedback_edit,
                    supply_air_temperature_display:formData.supply_air_temperature_edit,
                    supply_air_humidity_display:formData.supply_air_humidity_edit,
                    fresh_air_temperature_display:formData.fresh_air_temperature_edit,
                    fresh_air_humidity_display:formData.fresh_air_humidity_edit,
                    humidification_opening_control_display:formData.humidification_opening_control_edit,
                    humidification_opening_feedback_display:formData.humidification_opening_feedback_edit,
                    humidification_on_display:formData.humidification_on_edit,
                    humidification_off_display:formData.humidification_off_edit,
                    carbon_dioxide_display:formData.carbon_dioxide_edit,
                    antifreeze_alarm_display:formData.antifreeze_alarm_edit,
                    filter_screen_pressure_difference_alarm_display:formData.filter_screen_pressure_difference_alarm_edit,
                    fan_pressure_difference_display:formData.fan_pressure_difference_edit

                },
                success: function (result) {

                    var status = result && result.status;

                    if(status === "1"){

                        $('#fres_modal_edit').modal('hide');

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
                            showPagingPage('fres_page', data);
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

        function deletefresConfig(id) {

            if (!id) {
                console.warn('无效参数！');
                return;
            }

            $.ajax({
                type    : "POST",
                url     : _ctx + "/view/BESFreshairconfigController/deleteFreshairByID",
                dataType: "json",
                data: {
                    id : id,

                },
                success: function (result) {

                    var status = result && result.status;

                    if(status === "1"){

                        getPagingPage({}, function (data) {
                            showPagingPage('fres_page', data);
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
                url     : _ctx + '/view/BESFreshairconfigController/selectFreshair',
                type    : "post",
                data    : param,
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
