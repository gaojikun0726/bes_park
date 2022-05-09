<#--

 空调机组配置页面
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
    #tree_device_type_modal_airconditioningunitconfig {
        position: absolute;
        left:71%;
        top: 11.5%;
    }
</style>

<!-- 信息表格模块 -->
<div class="information-model">

			<span class="Subtitle">
				<i class="fa fa-user" aria-hidden="true"></i>&nbsp;空调机组配置列表>>>
			</span>

    <!-- 增加按钮 -->
    <a href="javascript:void(-1);" id="airconditioningunit_config_add" class="btn btn-primary toLeft">
        <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;增加
    </a>

</div>

<!---分页列表----->
<div class="ibox" id="airconditioningunit_page" style="height:90%">
</div>

<#-- 添加模态框 -->
<div class="modal fade" id="airconditioningunit_modal_add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" style="margin-top: 8%;">
        <div class="modal-content" style="width: 700px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 增加空调机组配置信息</h4>
            </div>
            <div class="modal-body">
                <form id="airconditioningunit_add_form" name="airconditioningunit_add_form" class="form-horizontal">
                    <div  style="height: 70%;overflow-x: hidden;overflow-y: auto;">

                        <div class="form-group">
                            <label class="col-sm-3 control-label">空调机组名称
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input style="width: 390px"
                                       type="text"
                                       id="airconditioningunit_name_add"
                                       name="airconditioningunit_name_add"
                                       class="form-control"
                                       placeholder="请输入名称"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">空调机组位置
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input style="width: 390px"
                                       type="text"
                                       id="airconditioningunit_address_add"
                                       name="airconditioningunit_address_add"
                                       class="form-control"
                                       placeholder="请输入空调机组位置"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">新风机启停
                            </label>
                            <div class="col-sm-8">
                                <input id="new_fan_start_and_stop_hidden_add"
                                       name="new_fan_start_and_stop_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="new_fan_start_and_stop_add"
                                       name="new_fan_start_and_stop_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">送风机运行状态
                            </label>
                            <div class="col-sm-8">
                                <input id="operation_status_of_blower_hidden_add"
                                       name="operation_status_of_blower_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="operation_status_of_blower_add"
                                       name="operation_status_of_blower_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">送风机手自动状态
                            </label>
                            <div class="col-sm-8">
                                <input id="manual_automatic_state_of_blower_hidden_add"
                                       name="manual_automatic_state_of_blower_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="manual_automatic_state_of_blower_add"
                                       name="manual_automatic_state_of_blower_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">送风机故障状态
                            </label>
                            <div class="col-sm-8">
                                <input id="blower_failure_status_hidden_add"
                                       name="blower_failure_status_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="blower_failure_status_add"
                                       name="blower_failure_status_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div><div class="form-group">
                            <label class="col-sm-3 control-label">新风阀开度控制
                            </label>
                            <div class="col-sm-8">
                                <input id="fresh_air_valve_opening_control_hidden_add"
                                       name="fresh_air_valve_opening_control_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="fresh_air_valve_opening_control_add"
                                       name="fresh_air_valve_opening_control_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div><div class="form-group">
                            <label class="col-sm-3 control-label">新风阀开度反馈
                            </label>
                            <div class="col-sm-8">
                                <input id="fresh_air_valve_opening_feedback_hidden_add"
                                       name="fresh_air_valve_opening_feedback_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="fresh_air_valve_opening_feedback_add"
                                       name="fresh_air_valve_opening_feedback_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div><div class="form-group">
                            <label class="col-sm-3 control-label">新风阀开
                            </label>
                            <div class="col-sm-8">
                                <input id="fresh_air_valve_open_hidden_add"
                                       name="fresh_air_valve_open_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="fresh_air_valve_open_add"
                                       name="fresh_air_valve_open_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div><div class="form-group">
                            <label class="col-sm-3 control-label">新风阀关
                            </label>
                            <div class="col-sm-8">
                                <input id="fresh_air_valve_closed_hidden_add"
                                       name="fresh_air_valve_closed_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="fresh_air_valve_closed_add"
                                       name="fresh_air_valve_closed_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div><div class="form-group">
                            <label class="col-sm-3 control-label">新风阀开到位
                            </label>
                            <div class="col-sm-8">
                                <input id="fresh_air_valve_open_in_place_hidden_add"
                                       name="fresh_air_valve_open_in_place_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="fresh_air_valve_open_in_place_add"
                                       name="fresh_air_valve_open_in_place_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">新风阀关到位
                            </label>
                            <div class="col-sm-8">
                                <input id="fresh_air_valve_closed_in_place_hidden_add"
                                       name="fresh_air_valve_closed_in_place_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="fresh_air_valve_closed_in_place_add"
                                       name="fresh_air_valve_closed_in_place_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">回风机启停
                            </label>
                            <div class="col-sm-8">
                                <input id="return_fan_start_and_stop_hidden_add"
                                       name="return_fan_start_and_stop_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="return_fan_start_and_stop_add"
                                       name="return_fan_start_and_stop_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">回风机运行状态
                            </label>
                            <div class="col-sm-8">
                                <input id="operation_status_of_return_fan_hidden_add"
                                       name="operation_status_of_return_fan_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="operation_status_of_return_fan_add"
                                       name="operation_status_of_return_fan_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">回风机手自动状态
                            </label>
                            <div class="col-sm-8">
                                <input id="manual_automatic_state_of_the_return_fan_hidden_add"
                                       name="manual_automatic_state_of_the_return_fan_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="manual_automatic_state_of_the_return_fan_add"
                                       name="manual_automatic_state_of_the_return_fan_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">回风机故障状态
                            </label>
                            <div class="col-sm-8">
                                <input id="return_fan_failure_status_hidden_add"
                                       name="return_fan_failure_status_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="return_fan_failure_status_add"
                                       name="return_fan_failure_status_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">排风阀开度控制
                            </label>
                            <div class="col-sm-8">
                                <input id="exhaust_valve_opening_control_hidden_add"
                                       name="exhaust_valve_opening_control_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="exhaust_valve_opening_control_add"
                                       name="exhaust_valve_opening_control_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">排风阀开度反馈
                            </label>
                            <div class="col-sm-8">
                                <input id="exhaust_valve_opening_feedback_hidden_add"
                                       name="exhaust_valve_opening_feedback_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="exhaust_valve_opening_feedback_add"
                                       name="exhaust_valve_opening_feedback_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">排风阀开
                            </label>
                            <div class="col-sm-8">
                                <input id="exhaust_valve_open_hidden_add"
                                       name="exhaust_valve_open_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="exhaust_valve_open_add"
                                       name="exhaust_valve_open_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">排风阀关
                            </label>
                            <div class="col-sm-8">
                                <input id="exhaust_valve_closed_hidden_add"
                                       name="exhaust_valve_closed_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="exhaust_valve_closed_add"
                                       name="exhaust_valve_closed_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">排风阀开到位
                            </label>
                            <div class="col-sm-8">
                                <input id="the_exhaust_valve_is_open_in_place_hidden_add"
                                       name="the_exhaust_valve_is_open_in_place_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="the_exhaust_valve_is_open_in_place_add"
                                       name="the_exhaust_valve_is_open_in_place_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">排风阀关到位
                            </label>
                            <div class="col-sm-8">
                                <input id="the_exhaust_valve_is_closed_in_place_hidden_add"
                                       name="the_exhaust_valve_is_closed_in_place_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="the_exhaust_valve_is_closed_in_place_add"
                                       name="the_exhaust_valve_is_closed_in_place_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">送风温度
                            </label>
                            <div class="col-sm-8">
                                <input id="supply_air_temperature_hidden_add"
                                       name="supply_air_temperature_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="supply_air_temperature_add"
                                       name="supply_air_temperature_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">送风湿度
                            </label>
                            <div class="col-sm-8">
                                <input id="supply_air_humidity_hidden_add"
                                       name="supply_air_humidity_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="supply_air_humidity_add"
                                       name="supply_air_humidity_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">新风温度
                            </label>
                            <div class="col-sm-8">
                                <input id="fresh_air_temperature_hidden_add"
                                       name="fresh_air_temperature_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="fresh_air_temperature_add"
                                       name="fresh_air_temperature_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">新风湿度
                            </label>
                            <div class="col-sm-8">
                                <input id="fresh_air_humidity_hidden_add"
                                       name="fresh_air_humidity_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="fresh_air_humidity_add"
                                       name="fresh_air_humidity_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">回风温度
                            </label>
                            <div class="col-sm-8">
                                <input id="return_air_temperature_hidden_add"
                                       name="return_air_temperature_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="return_air_temperature_add"
                                       name="return_air_temperature_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">回风湿度
                            </label>
                            <div class="col-sm-8">
                                <input id="return_air_humidity_hidden_add"
                                       name="return_air_humidity_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="return_air_humidity_add"
                                       name="return_air_humidity_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">二氧化碳
                            </label>
                            <div class="col-sm-8">
                                <input id="carbon_dioxide_hidden_add"
                                       name="carbon_dioxide_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="carbon_dioxide_add"
                                       name="carbon_dioxide_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">加湿开度控制
                            </label>
                            <div class="col-sm-8">
                                <input id="humidification_opening_control_hidden_add"
                                       name="humidification_opening_control_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="humidification_opening_control_add"
                                       name="humidification_opening_control_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">加湿开度反馈
                            </label>
                            <div class="col-sm-8">
                                <input id="humidification_opening_feedback_hidden_add"
                                       name="humidification_opening_feedback_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="humidification_opening_feedback_add"
                                       name="humidification_opening_feedback_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">加湿开
                            </label>
                            <div class="col-sm-8">
                                <input id="humidification_on_hidden_add"
                                       name="humidification_on_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="humidification_on_add"
                                       name="humidification_on_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">加湿关
                            </label>
                            <div class="col-sm-8">
                                <input id="humidification_off_hidden_add"
                                       name="humidification_off_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="humidification_off_add"
                                       name="humidification_off_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">加湿开到位
                            </label>
                            <div class="col-sm-8">
                                <input id="humidification_valve_open_in_place_hidden_add"
                                       name="humidification_valve_open_in_place_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="humidification_valve_open_in_place_add"
                                       name="humidification_valve_open_in_place_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">加湿关到位
                            </label>
                            <div class="col-sm-8">
                                <input id="humidification_valve_closed_in_place_hidden_add"
                                       name="humidification_valve_closed_in_place_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="humidification_valve_closed_in_place_add"
                                       name="humidification_valve_closed_in_place_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">防冻报警
                            </label>
                            <div class="col-sm-8">
                                <input id="antifreeze_alarm_hidden_add"
                                       name="antifreeze_alarm_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="antifreeze_alarm_add"
                                       name="antifreeze_alarm_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">初效过滤网压差报警
                            </label>
                            <div class="col-sm-8">
                                <input id="initial_filter_screen_pressure_difference_alarm_hidden_add"
                                       name="initial_filter_screen_pressure_difference_alarm_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="initial_filter_screen_pressure_difference_alarm_add"
                                       name="initial_filter_screen_pressure_difference_alarm_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">中效过滤网压差报警
                            </label>
                            <div class="col-sm-8">
                                <input id="mediumefficiency_filter_pressure_difference_alarm_hidden_add"
                                       name="mediumefficiency_filter_pressure_difference_alarm_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="mediumefficiency_filter_pressure_difference_alarm_add"
                                       name="mediumefficiency_filter_pressure_difference_alarm_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">回风过滤网压差报警
                            </label>
                            <div class="col-sm-8">
                                <input id="return_air_filter_pressure_difference_alarm_hidden_add"
                                       name="return_air_filter_pressure_difference_alarm_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="return_air_filter_pressure_difference_alarm_add"
                                       name="return_air_filter_pressure_difference_alarm_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">送风机压差
                            </label>
                            <div class="col-sm-8">
                                <input id="pressure_difference_of_blower_hidden_add"
                                       name="pressure_difference_of_blower_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="pressure_difference_of_blower_add"
                                       name="pressure_difference_of_blower_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">回风机压差
                            </label>
                            <div class="col-sm-8">
                                <input id="return_fan_pressure_difference_hidden_add"
                                       name="return_fan_pressure_difference_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="return_fan_pressure_difference_add"
                                       name="return_fan_pressure_difference_add"
                                       class="form-control"
                                       placeholder="请选择对应点位"
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
<div class="modal fade" id="airconditioningunit_modal_edit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" style="margin-top: 8%;">
        <div class="modal-content" style="width: 700px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 增加空调机组配置信息</h4>
            </div>
            <div class="modal-body">
                <form id="airconditioningunit_edit_form" name="airconditioningunit_edit_form" class="form-horizontal">
                    <div  style="height: 70%;overflow-x: hidden;overflow-y: auto;">

                        <input type="hidden" id="id_airconditioningunit_edit" name="id_airconditioningunit_edit"/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">空调机组名称
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input style="width: 390px"
                                       type="text"
                                       id="airconditioningunit_name_edit"
                                       name="airconditioningunit_name_edit"
                                       class="form-control"
                                       placeholder="请输入名称"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">空调机组位置
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input style="width: 390px"
                                       type="text"
                                       id="airconditioningunit_address_edit"
                                       name="airconditioningunit_address_edit"
                                       class="form-control"
                                       placeholder="请输入空调机组位置"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">新风机启停
                            </label>
                            <div class="col-sm-8">
                                <input id="new_fan_start_and_stop_hidden_edit"
                                       name="new_fan_start_and_stop_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="new_fan_start_and_stop_edit"
                                       name="new_fan_start_and_stop_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">送风机运行状态
                            </label>
                            <div class="col-sm-8">
                                <input id="operation_status_of_blower_hidden_edit"
                                       name="operation_status_of_blower_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="operation_status_of_blower_edit"
                                       name="operation_status_of_blower_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">送风机手自动状态
                            </label>
                            <div class="col-sm-8">
                                <input id="manual_automatic_state_of_blower_hidden_edit"
                                       name="manual_automatic_state_of_blower_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="manual_automatic_state_of_blower_edit"
                                       name="manual_automatic_state_of_blower_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">送风机故障状态
                            </label>
                            <div class="col-sm-8">
                                <input id="blower_failure_status_hidden_edit"
                                       name="blower_failure_status_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="blower_failure_status_edit"
                                       name="blower_failure_status_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div><div class="form-group">
                            <label class="col-sm-3 control-label">新风阀开度控制
                            </label>
                            <div class="col-sm-8">
                                <input id="fresh_air_valve_opening_control_hidden_edit"
                                       name="fresh_air_valve_opening_control_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="fresh_air_valve_opening_control_edit"
                                       name="fresh_air_valve_opening_control_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div><div class="form-group">
                            <label class="col-sm-3 control-label">新风阀开度反馈
                            </label>
                            <div class="col-sm-8">
                                <input id="fresh_air_valve_opening_feedback_hidden_edit"
                                       name="fresh_air_valve_opening_feedback_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="fresh_air_valve_opening_feedback_edit"
                                       name="fresh_air_valve_opening_feedback_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div><div class="form-group">
                            <label class="col-sm-3 control-label">新风阀开
                            </label>
                            <div class="col-sm-8">
                                <input id="fresh_air_valve_open_hidden_edit"
                                       name="fresh_air_valve_open_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="fresh_air_valve_open_edit"
                                       name="fresh_air_valve_open_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div><div class="form-group">
                            <label class="col-sm-3 control-label">新风阀关
                            </label>
                            <div class="col-sm-8">
                                <input id="fresh_air_valve_closed_hidden_edit"
                                       name="fresh_air_valve_closed_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="fresh_air_valve_closed_edit"
                                       name="fresh_air_valve_closed_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div><div class="form-group">
                            <label class="col-sm-3 control-label">新风阀开到位
                            </label>
                            <div class="col-sm-8">
                                <input id="fresh_air_valve_open_in_place_hidden_edit"
                                       name="fresh_air_valve_open_in_place_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="fresh_air_valve_open_in_place_edit"
                                       name="fresh_air_valve_open_in_place_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">新风阀关到位
                            </label>
                            <div class="col-sm-8">
                                <input id="fresh_air_valve_closed_in_place_hidden_edit"
                                       name="fresh_air_valve_closed_in_place_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="fresh_air_valve_closed_in_place_edit"
                                       name="fresh_air_valve_closed_in_place_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">回风机启停
                            </label>
                            <div class="col-sm-8">
                                <input id="return_fan_start_and_stop_hidden_edit"
                                       name="return_fan_start_and_stop_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="return_fan_start_and_stop_edit"
                                       name="return_fan_start_and_stop_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">回风机运行状态
                            </label>
                            <div class="col-sm-8">
                                <input id="operation_status_of_return_fan_hidden_edit"
                                       name="operation_status_of_return_fan_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="operation_status_of_return_fan_edit"
                                       name="operation_status_of_return_fan_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">回风机手自动状态
                            </label>
                            <div class="col-sm-8">
                                <input id="manual_automatic_state_of_the_return_fan_hidden_edit"
                                       name="manual_automatic_state_of_the_return_fan_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="manual_automatic_state_of_the_return_fan_edit"
                                       name="manual_automatic_state_of_the_return_fan_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">回风机故障状态
                            </label>
                            <div class="col-sm-8">
                                <input id="return_fan_failure_status_hidden_edit"
                                       name="return_fan_failure_status_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="return_fan_failure_status_edit"
                                       name="return_fan_failure_status_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">排风阀开度控制
                            </label>
                            <div class="col-sm-8">
                                <input id="exhaust_valve_opening_control_hidden_edit"
                                       name="exhaust_valve_opening_control_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="exhaust_valve_opening_control_edit"
                                       name="exhaust_valve_opening_control_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">排风阀开度反馈
                            </label>
                            <div class="col-sm-8">
                                <input id="exhaust_valve_opening_feedback_hidden_edit"
                                       name="exhaust_valve_opening_feedback_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="exhaust_valve_opening_feedback_edit"
                                       name="exhaust_valve_opening_feedback_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">排风阀开
                            </label>
                            <div class="col-sm-8">
                                <input id="exhaust_valve_open_hidden_edit"
                                       name="exhaust_valve_open_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="exhaust_valve_open_edit"
                                       name="exhaust_valve_open_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">排风阀关
                            </label>
                            <div class="col-sm-8">
                                <input id="exhaust_valve_closed_hidden_edit"
                                       name="exhaust_valve_closed_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="exhaust_valve_closed_edit"
                                       name="exhaust_valve_closed_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">排风阀开到位
                            </label>
                            <div class="col-sm-8">
                                <input id="the_exhaust_valve_is_open_in_place_hidden_edit"
                                       name="the_exhaust_valve_is_open_in_place_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="the_exhaust_valve_is_open_in_place_edit"
                                       name="the_exhaust_valve_is_open_in_place_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">排风阀关到位
                            </label>
                            <div class="col-sm-8">
                                <input id="the_exhaust_valve_is_closed_in_place_hidden_edit"
                                       name="the_exhaust_valve_is_closed_in_place_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="the_exhaust_valve_is_closed_in_place_edit"
                                       name="the_exhaust_valve_is_closed_in_place_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">送风温度
                            </label>
                            <div class="col-sm-8">
                                <input id="supply_air_temperature_hidden_edit"
                                       name="supply_air_temperature_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="supply_air_temperature_edit"
                                       name="supply_air_temperature_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">送风湿度
                            </label>
                            <div class="col-sm-8">
                                <input id="supply_air_humidity_hidden_edit"
                                       name="supply_air_humidity_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="supply_air_humidity_edit"
                                       name="supply_air_humidity_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">新风温度
                            </label>
                            <div class="col-sm-8">
                                <input id="fresh_air_temperature_hidden_edit"
                                       name="fresh_air_temperature_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="fresh_air_temperature_edit"
                                       name="fresh_air_temperature_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">新风湿度
                            </label>
                            <div class="col-sm-8">
                                <input id="fresh_air_humidity_hidden_edit"
                                       name="fresh_air_humidity_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="fresh_air_humidity_edit"
                                       name="fresh_air_humidity_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">回风温度
                            </label>
                            <div class="col-sm-8">
                                <input id="return_air_temperature_hidden_edit"
                                       name="return_air_temperature_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="return_air_temperature_edit"
                                       name="return_air_temperature_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">回风湿度
                            </label>
                            <div class="col-sm-8">
                                <input id="return_air_humidity_hidden_edit"
                                       name="return_air_humidity_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="return_air_humidity_edit"
                                       name="return_air_humidity_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">二氧化碳
                            </label>
                            <div class="col-sm-8">
                                <input id="carbon_dioxide_hidden_edit"
                                       name="carbon_dioxide_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="carbon_dioxide_edit"
                                       name="carbon_dioxide_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">加湿开度控制
                            </label>
                            <div class="col-sm-8">
                                <input id="humidification_opening_control_hidden_edit"
                                       name="humidification_opening_control_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="humidification_opening_control_edit"
                                       name="humidification_opening_control_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">加湿开度反馈
                            </label>
                            <div class="col-sm-8">
                                <input id="humidification_opening_feedback_hidden_edit"
                                       name="humidification_opening_feedback_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="humidification_opening_feedback_edit"
                                       name="humidification_opening_feedback_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">加湿开
                            </label>
                            <div class="col-sm-8">
                                <input id="humidification_on_hidden_edit"
                                       name="humidification_on_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="humidification_on_edit"
                                       name="humidification_on_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">加湿关
                            </label>
                            <div class="col-sm-8">
                                <input id="humidification_off_hidden_edit"
                                       name="humidification_off_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="humidification_off_edit"
                                       name="humidification_off_edit"
                                       class="form-control"
                                       placeholder="请选择空调机组模式"
                                >
                            </div>
                        </div>








                        <div class="form-group">
                            <label class="col-sm-3 control-label">加湿开到位
                            </label>
                            <div class="col-sm-8">
                                <input id="humidification_valve_open_in_place_hidden_edit"
                                       name="humidification_valve_open_in_place_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="humidification_valve_open_in_place_edit"
                                       name="humidification_valve_open_in_place_edit"
                                       class="form-control"
                                       placeholder="请选择空调机组风速"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">加湿关到位
                            </label>
                            <div class="col-sm-8">
                                <input id="humidification_valve_closed_in_place_hidden_edit"
                                       name="humidification_valve_closed_in_place_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="humidification_valve_closed_in_place_edit"
                                       name="humidification_valve_closed_in_place_edit"
                                       class="form-control"
                                       placeholder="请选择空调机组开关"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">防冻报警
                            </label>
                            <div class="col-sm-8">
                                <input id="antifreeze_alarm_hidden_edit"
                                       name="antifreeze_alarm_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="antifreeze_alarm_edit"
                                       name="antifreeze_alarm_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">初效过滤网压差报警
                            </label>
                            <div class="col-sm-8">
                                <input id="initial_filter_screen_pressure_difference_alarm_hidden_edit"
                                       name="initial_filter_screen_pressure_difference_alarm_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="initial_filter_screen_pressure_difference_alarm_edit"
                                       name="initial_filter_screen_pressure_difference_alarm_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">中效过滤网压差报警
                            </label>
                            <div class="col-sm-8">
                                <input id="mediumefficiency_filter_pressure_difference_alarm_hidden_edit"
                                       name="mediumefficiency_filter_pressure_difference_alarm_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="mediumefficiency_filter_pressure_difference_alarm_edit"
                                       name="mediumefficiency_filter_pressure_difference_alarm_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">回风过滤网压差报警
                            </label>
                            <div class="col-sm-8">
                                <input id="return_air_filter_pressure_difference_alarm_hidden_edit"
                                       name="return_air_filter_pressure_difference_alarm_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="return_air_filter_pressure_difference_alarm_edit"
                                       name="return_air_filter_pressure_difference_alarm_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">送风机压差
                            </label>
                            <div class="col-sm-8">
                                <input id="pressure_difference_of_blower_hidden_edit"
                                       name="pressure_difference_of_blower_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="pressure_difference_of_blower_edit"
                                       name="pressure_difference_of_blower_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">回风机压差
                            </label>
                            <div class="col-sm-8">
                                <input id="return_fan_pressure_difference_hidden_edit"
                                       name="return_fan_pressure_difference_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="return_fan_pressure_difference_edit"
                                       name="return_fan_pressure_difference_edit"
                                       class="form-control"
                                       placeholder="请选择对应点位"
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
<div class="modal fade" id="tree_device_type_modal_airconditioningunitconfig" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" style="margin-top: 8%;">
        <div class="modal-content" style="width: 350px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 请选择设备类型</h4>
            </div>
            <div class="modal-body" style="height: 600px">
                <div style="position: relative" class="zc_search find">
                    <input type="text" class="find-style" id="tree_device_type_search_airconditioningunit_config_input" name="eqTypeInfo" placeholder="设备类型">
                    <button id="tree_device_type_search_airconditioningunit_config_button"><i class="fa fa-search" aria-hidden="true"></i></button>
                </div>

                <#--设备类型树-->
                <div id="tree_device_airconditioningunit_config_type" class="Information_area ztree"></div>

            </div>
        </div><!-- end modal-content -->
    </div>
</div> <!-- end addParkBasicInfo -->

<script type="text/javascript">

    var airconditioningUnit = (function($, window, document) {

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
                showPagingPage('airconditioningunit_page', data);

            });

        });

        // 初始化设备类型树
        function initTree() {
            tree = new Tree({
                container: 'tree_device_airconditioningunit_config_type',
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
        function queryAirconditioningConfig(obj, callback) {

            if(typeof callback !== 'function'){
                return;
            }

            obj = obj || {};

            $.ajax({
                type    : "POST",
                url     : _ctx + "/view/app/airconditioningUnitConfig/query",
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
        $('#tree_device_type_search_airconditioningunit_config_input').keydown(function (e){
            if(e.which !== 13){
                return;
            }

            tree.search('tree_device_type_search_airconditioningunit_config_input', 'f_nick_name');

        });

        // 触发搜索点击事件， 以系统别名搜索设备树展开并以红色字体标注
        $('#tree_device_type_search_airconditioningunit_config_button').click(function () {

            tree.search('tree_device_type_search_airconditioningunit_config_input', 'f_nick_name');
        });

        // 显示添加模态框
        $('#airconditioningunit_config_add').click(function () {
            $('#airconditioningunit_modal_add').modal('show');
            modalAddActive = true;

        });

        // 显示编辑模态框事件
        $('#airconditioningunit_modal_edit').on('show.bs.modal', function () {
            modalEditActive = true;
        });

        // 添加输入框得到焦点时显示设备类型模态框
        $(
            '#new_fan_start_and_stop_add,'+
            '#operation_status_of_blower_add,'+
            '#manual_automatic_state_of_blower_add,'+
            '#blower_failure_status_add,'+
            '#fresh_air_valve_opening_control_add,'+
            '#fresh_air_valve_opening_feedback_add,'+
            '#fresh_air_valve_open_add,'+
            '#fresh_air_valve_closed_add,'+
            '#fresh_air_valve_open_in_place_add,'+
            '#fresh_air_valve_closed_in_place_add,'+
            '#return_fan_start_and_stop_add,'+
            '#operation_status_of_return_fan_add,'+
            '#manual_automatic_state_of_the_return_fan_add,'+
            '#return_fan_failure_status_add,'+
            '#exhaust_valve_opening_control_add,'+
            '#exhaust_valve_opening_feedback_add,'+
            '#exhaust_valve_open_add,'+
            '#exhaust_valve_closed_add,'+
            '#the_exhaust_valve_is_open_in_place_add,'+
            '#the_exhaust_valve_is_closed_in_place_add,'+
            '#supply_air_temperature_add,'+
            '#supply_air_humidity_add,'+
            '#fresh_air_temperature_add,'+
            '#fresh_air_humidity_add,'+
            '#return_air_temperature_add,'+
            '#return_air_humidity_add,'+
            '#carbon_dioxide_add,'+
            '#humidification_opening_control_add,'+
            '#humidification_opening_feedback_add,'+
            '#humidification_on_add,'+
            '#humidification_off_add,'+
            '#humidification_valve_open_in_place_add,'+
            '#humidification_valve_closed_in_place_add,'+
            '#antifreeze_alarm_add,'+
            '#initial_filter_screen_pressure_difference_alarm_add,'+
            '#mediumefficiency_filter_pressure_difference_alarm_add,'+
            '#return_air_filter_pressure_difference_alarm_add,'+
            '#pressure_difference_of_blower_add,'+
            '#return_fan_pressure_difference_add,'+

            '#new_fan_start_and_stop_edit,'+
            '#operation_status_of_blower_edit,'+
            '#manual_automatic_state_of_blower_edit,'+
            '#blower_failure_status_edit,'+
            '#fresh_air_valve_opening_control_edit,'+
            '#fresh_air_valve_opening_feedback_edit,'+
            '#fresh_air_valve_open_edit,'+
            '#fresh_air_valve_closed_edit,'+
            '#fresh_air_valve_open_in_place_edit,'+
            '#fresh_air_valve_closed_in_place_edit,'+
            '#return_fan_start_and_stop_edit,'+
            '#operation_status_of_return_fan_edit,'+
            '#manual_automatic_state_of_the_return_fan_edit,'+
            '#return_fan_failure_status_edit,'+
            '#exhaust_valve_opening_control_edit,'+
            '#exhaust_valve_opening_feedback_edit,'+
            '#exhaust_valve_open_edit,'+
            '#exhaust_valve_closed_edit,'+
            '#the_exhaust_valve_is_open_in_place_edit,'+
            '#the_exhaust_valve_is_closed_in_place_edit,'+
            '#supply_air_temperature_edit,'+
            '#supply_air_humidity_edit,'+
            '#fresh_air_temperature_edit,'+
            '#fresh_air_humidity_edit,'+
            '#return_air_temperature_edit,'+
            '#return_air_humidity_edit,'+
            '#carbon_dioxide_edit,'+
            '#humidification_opening_control_edit,'+
            '#humidification_opening_feedback_edit,'+
            '#humidification_on_edit,'+
            '#humidification_off_edit,'+
            '#humidification_valve_open_in_place_edit,'+
            '#humidification_valve_closed_in_place_edit,'+
            '#antifreeze_alarm_edit,'+
            '#initial_filter_screen_pressure_difference_alarm_edit,'+
            '#mediumefficiency_filter_pressure_difference_alarm_edit,'+
            '#return_air_filter_pressure_difference_alarm_edit,'+
            '#pressure_difference_of_blower_edit,'+
            '#return_fan_pressure_difference_edit'

        ).focus(function (obj) {
            inputObj = obj.target;
            $('#tree_device_type_modal_airconditioningunitconfig').modal('show');
        });


        // 当添加模态框关闭时同时关闭设备类型模态框, 清空表单数据
        $('#airconditioningunit_modal_add').on('hide.bs.modal', function () {
            modalAddActive = false;
            inputObj = null;
            $('#tree_device_type_modal_airconditioningunitconfig').modal('hide');

            $('#id_airconditioningunit_add')                   .val('');
            $('#airconditioningunit_name_add')                 .val('');
            $('#airconditioningunit_address_add')              .val('');

            $('#new_fan_start_and_stop_add').val('');
            $('#operation_status_of_blower_add').val('');
            $('#manual_automatic_state_of_blower_add').val('');
            $('#blower_failure_status_add').val('');
            $('#fresh_air_valve_opening_control_add').val('');
            $('#fresh_air_valve_opening_feedback_add').val('');
            $('#fresh_air_valve_open_add').val('');
            $('#fresh_air_valve_closed_add').val('');
            $('#fresh_air_valve_open_in_place_add').val('');
            $('#fresh_air_valve_closed_in_place_add').val('');
            $('#return_fan_start_and_stop_add').val('');
            $('#operation_status_of_return_fan_add').val('');
            $('#manual_automatic_state_of_the_return_fan_add').val('');
            $('#return_fan_failure_status_add').val('');
            $('#exhaust_valve_opening_control_add').val('');
            $('#exhaust_valve_opening_feedback_add').val('');
            $('#exhaust_valve_open_add').val('');
            $('#exhaust_valve_closed_add').val('');
            $('#the_exhaust_valve_is_open_in_place_add').val('');
            $('#the_exhaust_valve_is_closed_in_place_add').val('');
            $('#supply_air_temperature_add').val('');
            $('#supply_air_humidity_add').val('');
            $('#fresh_air_temperature_add').val('');
            $('#fresh_air_humidity_add').val('');
            $('#return_air_temperature_add').val('');
            $('#return_air_humidity_add').val('');
            $('#carbon_dioxide_add').val('');
            $('#humidification_opening_control_add').val('');
            $('#humidification_opening_feedback_add').val('');
            $('#humidification_on_add').val('');
            $('#humidification_off_add').val('');
            $('#humidification_valve_open_in_place_add').val('');
            $('#humidification_valve_closed_in_place_add').val('');
            $('#antifreeze_alarm_add').val('');
            $('#initial_filter_screen_pressure_difference_alarm_add').val('');
            $('#mediumefficiency_filter_pressure_difference_alarm_add').val('');
            $('#return_air_filter_pressure_difference_alarm_add').val('');
            $('#pressure_difference_of_blower_add').val('');
            $('#return_fan_pressure_difference_add').val('');

            $('#new_fan_start_and_stop_hidden_add').val('');
            $('#operation_status_of_blower_hidden_add').val('');
            $('#manual_automatic_state_of_blower_hidden_add').val('');
            $('#blower_failure_status_hidden_add').val('');
            $('#fresh_air_valve_opening_control_hidden_add').val('');
            $('#fresh_air_valve_opening_feedback_hidden_add').val('');
            $('#fresh_air_valve_open_hidden_add').val('');
            $('#fresh_air_valve_closed_hidden_add').val('');
            $('#fresh_air_valve_open_in_place_hidden_add').val('');
            $('#fresh_air_valve_closed_in_place_hidden_add').val('');
            $('#return_fan_start_and_stop_hidden_add').val('');
            $('#operation_status_of_return_fan_hidden_add').val('');
            $('#manual_automatic_state_of_the_return_fan_hidden_add').val('');
            $('#return_fan_failure_status_hidden_add').val('');
            $('#exhaust_valve_opening_control_hidden_add').val('');
            $('#exhaust_valve_opening_feedback_hidden_add').val('');
            $('#exhaust_valve_open_hidden_add').val('');
            $('#exhaust_valve_closed_hidden_add').val('');
            $('#the_exhaust_valve_is_open_in_place_hidden_add').val('');
            $('#the_exhaust_valve_is_closed_in_place_hidden_add').val('');
            $('#supply_air_temperature_hidden_add').val('');
            $('#supply_air_humidity_hidden_add').val('');
            $('#fresh_air_temperature_hidden_add').val('');
            $('#fresh_air_humidity_hidden_add').val('');
            $('#return_air_temperature_hidden_add').val('');
            $('#return_air_humidity_hidden_add').val('');
            $('#carbon_dioxide_hidden_add').val('');
            $('#humidification_opening_control_hidden_add').val('');
            $('#humidification_opening_feedback_hidden_add').val('');
            $('#humidification_on_hidden_add').val('');
            $('#humidification_off_hidden_add').val('');
            $('#humidification_valve_open_in_place_hidden_add').val('');
            $('#humidification_valve_closed_in_place_hidden_add').val('');
            $('#antifreeze_alarm_hidden_add').val('');
            $('#initial_filter_screen_pressure_difference_alarm_hidden_add').val('');
            $('#mediumefficiency_filter_pressure_difference_alarm_hidden_add').val('');
            $('#return_air_filter_pressure_difference_alarm_hidden_add').val('');
            $('#pressure_difference_of_blower_hidden_add').val('');
            $('#return_fan_pressure_difference_hidden_add').val('');



            // 重置添加验证表单
            addValidate.resetForm()
        });

        // 关闭编辑模态框时清空输入框
        $("#airconditioningunit_modal_edit").on('hidden.bs.modal', function(event) {

            inputObj = null;
            modalEditActive = false;
            $('#tree_device_type_modal_airconditioningunitconfig').modal('hide');

            $('#id_airconditioningunit_edit')                   .val('');
            $('#airconditioningunit_name_edit')                 .val('');
            $('#airconditioningunit_address_edit')              .val('');
            $('#new_fan_start_and_stop_edit').val('');
            $('#operation_status_of_blower_edit').val('');
            $('#manual_automatic_state_of_blower_edit').val('');
            $('#blower_failure_status_edit').val('');
            $('#fresh_air_valve_opening_control_edit').val('');
            $('#fresh_air_valve_opening_feedback_edit').val('');
            $('#fresh_air_valve_open_edit').val('');
            $('#fresh_air_valve_closed_edit').val('');
            $('#fresh_air_valve_open_in_place_edit').val('');
            $('#fresh_air_valve_closed_in_place_edit').val('');
            $('#return_fan_start_and_stop_edit').val('');
            $('#operation_status_of_return_fan_edit').val('');
            $('#manual_automatic_state_of_the_return_fan_edit').val('');
            $('#return_fan_failure_status_edit').val('');
            $('#exhaust_valve_opening_control_edit').val('');
            $('#exhaust_valve_opening_feedback_edit').val('');
            $('#exhaust_valve_open_edit').val('');
            $('#exhaust_valve_closed_edit').val('');
            $('#the_exhaust_valve_is_open_in_place_edit').val('');
            $('#the_exhaust_valve_is_closed_in_place_edit').val('');
            $('#supply_air_temperature_edit').val('');
            $('#supply_air_humidity_edit').val('');
            $('#fresh_air_temperature_edit').val('');
            $('#fresh_air_humidity_edit').val('');
            $('#return_air_temperature_edit').val('');
            $('#return_air_humidity_edit').val('');
            $('#carbon_dioxide_edit').val('');
            $('#humidification_opening_control_edit').val('');
            $('#humidification_opening_feedback_edit').val('');
            $('#humidification_on_edit').val('');
            $('#humidification_off_edit').val('');
            $('#humidification_valve_open_in_place_edit').val('');
            $('#humidification_valve_closed_in_place_edit').val('');
            $('#antifreeze_alarm_edit').val('');
            $('#initial_filter_screen_pressure_difference_alarm_edit').val('');
            $('#mediumefficiency_filter_pressure_difference_alarm_edit').val('');
            $('#return_air_filter_pressure_difference_alarm_edit').val('');
            $('#pressure_difference_of_blower_edit').val('');
            $('#return_fan_pressure_difference_edit').val('');

            $('#new_fan_start_and_stop_hidden_edit').val('');
            $('#operation_status_of_blower_hidden_edit').val('');
            $('#manual_automatic_state_of_blower_hidden_edit').val('');
            $('#blower_failure_status_hidden_edit').val('');
            $('#fresh_air_valve_opening_control_hidden_edit').val('');
            $('#fresh_air_valve_opening_feedback_hidden_edit').val('');
            $('#fresh_air_valve_open_hidden_edit').val('');
            $('#fresh_air_valve_closed_hidden_edit').val('');
            $('#fresh_air_valve_open_in_place_hidden_edit').val('');
            $('#fresh_air_valve_closed_in_place_hidden_edit').val('');
            $('#return_fan_start_and_stop_hidden_edit').val('');
            $('#operation_status_of_return_fan_hidden_edit').val('');
            $('#manual_automatic_state_of_the_return_fan_hidden_edit').val('');
            $('#return_fan_failure_status_hidden_edit').val('');
            $('#exhaust_valve_opening_control_hidden_edit').val('');
            $('#exhaust_valve_opening_feedback_hidden_edit').val('');
            $('#exhaust_valve_open_hidden_edit').val('');
            $('#exhaust_valve_closed_hidden_edit').val('');
            $('#the_exhaust_valve_is_open_in_place_hidden_edit').val('');
            $('#the_exhaust_valve_is_closed_in_place_hidden_edit').val('');
            $('#supply_air_temperature_hidden_edit').val('');
            $('#supply_air_humidity_hidden_edit').val('');
            $('#fresh_air_temperature_hidden_edit').val('');
            $('#fresh_air_humidity_hidden_edit').val('');
            $('#return_air_temperature_hidden_edit').val('');
            $('#return_air_humidity_hidden_edit').val('');
            $('#carbon_dioxide_hidden_edit').val('');
            $('#humidification_opening_control_hidden_edit').val('');
            $('#humidification_opening_feedback_hidden_edit').val('');
            $('#humidification_on_hidden_edit').val('');
            $('#humidification_off_hidden_edit').val('');
            $('#humidification_valve_open_in_place_hidden_edit').val('');
            $('#humidification_valve_closed_in_place_hidden_edit').val('');
            $('#antifreeze_alarm_hidden_edit').val('');
            $('#initial_filter_screen_pressure_difference_alarm_hidden_edit').val('');
            $('#mediumefficiency_filter_pressure_difference_alarm_hidden_edit').val('');
            $('#return_air_filter_pressure_difference_alarm_hidden_edit').val('');
            $('#pressure_difference_of_blower_hidden_edit').val('');
            $('#return_fan_pressure_difference_hidden_edit').val('');
            // 重置表单
            editValidate.resetForm()

        });

        // 当设备树模态框关闭时折叠设备树、清空搜索框内容
        $('#tree_device_type_modal_airconditioningunitconfig').on('hide.bs.modal', function () {
            tree.tree.expandAll( false );
            $('#tree_device_type_search_airconditioningunit_config_input').val('');
        });


        /**
         * 编辑点击事件
         *
         */
        $(document).on('click', '#airconditioningUnitConfigTable button.edit', function () {

            var id = $(this).data('id');

            queryAirconditioningConfig({id}, function (obj) {

                var data = obj && obj.data && obj.data[0];

                if(!data){
                    return;
                }
                $('#id_airconditioningunit_edit')                    .val(data.id                   || '');
                $('#airconditioningunit_name_edit')                  .val(data.name                 || '');
                $('#airconditioningunit_address_edit')               .val(data.address          || '');
                //展示数据
                $('#new_fan_start_and_stop_edit').val(data.new_fan_start_and_stop_display ||'');
                $('#operation_status_of_blower_edit').val(data.operation_status_of_blower_display ||'');
                $('#manual_automatic_state_of_blower_edit').val(data.manual_automatic_state_of_blower_display ||'');
                $('#blower_failure_status_edit').val(data.blower_failure_status_display ||'');
                $('#fresh_air_valve_opening_control_edit').val(data.fresh_air_valve_opening_control_display ||'');
                $('#fresh_air_valve_opening_feedback_edit').val(data.fresh_air_valve_opening_feedback_display ||'');
                $('#fresh_air_valve_open_edit').val(data.fresh_air_valve_open_display ||'');
                $('#fresh_air_valve_closed_edit').val(data.fresh_air_valve_closed_display ||'');
                $('#fresh_air_valve_open_in_place_edit').val(data.fresh_air_valve_open_in_place_display ||'');
                $('#fresh_air_valve_closed_in_place_edit').val(data.fresh_air_valve_closed_in_place_display ||'');
                $('#return_fan_start_and_stop_edit').val(data.return_fan_start_and_stop_display ||'');
                $('#operation_status_of_return_fan_edit').val(data.operation_status_of_return_fan_display ||'');
                $('#manual_automatic_state_of_the_return_fan_edit').val(data.manual_automatic_state_of_the_return_fan_display ||'');
                $('#return_fan_failure_status_edit').val(data.return_fan_failure_status_display ||'');
                $('#exhaust_valve_opening_control_edit').val(data.exhaust_valve_opening_control_display ||'');
                $('#exhaust_valve_opening_feedback_edit').val(data.exhaust_valve_opening_feedback_display ||'');
                $('#exhaust_valve_open_edit').val(data.exhaust_valve_open_display ||'');
                $('#exhaust_valve_closed_edit').val(data.exhaust_valve_closed_display ||'');
                $('#the_exhaust_valve_is_open_in_place_edit').val(data.the_exhaust_valve_is_open_in_place_display ||'');
                $('#the_exhaust_valve_is_closed_in_place_edit').val(data.the_exhaust_valve_is_closed_in_place_display ||'');
                $('#supply_air_temperature_edit').val(data.supply_air_temperature_display ||'');
                $('#supply_air_humidity_edit').val(data.supply_air_humidity_display ||'');
                $('#fresh_air_temperature_edit').val(data.fresh_air_temperature_display ||'');
                $('#fresh_air_humidity_edit').val(data.fresh_air_humidity_display ||'');
                $('#return_air_temperature_edit').val(data.return_air_temperature_display ||'');
                $('#return_air_humidity_edit').val(data.return_air_humidity_display ||'');
                $('#carbon_dioxide_edit').val(data.carbon_dioxide_display ||'');
                $('#humidification_opening_control_edit').val(data.humidification_opening_control_display ||'');
                $('#humidification_opening_feedback_edit').val(data.humidification_opening_feedback_display ||'');
                $('#humidification_on_edit').val(data.humidification_on_display ||'');
                $('#humidification_off_edit').val(data.humidification_off_display ||'');
                $('#humidification_valve_open_in_place_edit').val(data.humidification_valve_open_in_place_display ||'');
                $('#humidification_valve_closed_in_place_edit').val(data.humidification_valve_closed_in_place_display ||'');
                $('#antifreeze_alarm_edit').val(data.antifreeze_alarm_display ||'');
                $('#initial_filter_screen_pressure_difference_alarm_edit').val(data.initial_filter_screen_pressure_difference_alarm_display ||'');
                $('#mediumefficiency_filter_pressure_difference_alarm_edit').val(data.mediumefficiency_filter_pressure_difference_alarm_display ||'');
                $('#return_air_filter_pressure_difference_alarm_edit').val(data.return_air_filter_pressure_difference_alarm_display ||'');
                $('#pressure_difference_of_blower_edit').val(data.pressure_difference_of_blower_display ||'');
                $('#return_fan_pressure_difference_edit').val(data.return_fan_pressure_difference_display ||'');
                //真正有用的数据（隐藏数据）
                $('#new_fan_start_and_stop_hidden_edit').val(data.new_fan_start_and_stop ||'');
                $('#operation_status_of_blower_hidden_edit').val(data.operation_status_of_blower ||'');
                $('#manual_automatic_state_of_blower_hidden_edit').val(data.manual_automatic_state_of_blower ||'');
                $('#blower_failure_status_hidden_edit').val(data.blower_failure_status ||'');
                $('#fresh_air_valve_opening_control_hidden_edit').val(data.fresh_air_valve_opening_control ||'');
                $('#fresh_air_valve_opening_feedback_hidden_edit').val(data.fresh_air_valve_opening_feedback ||'');
                $('#fresh_air_valve_open_hidden_edit').val(data.fresh_air_valve_open ||'');
                $('#fresh_air_valve_closed_hidden_edit').val(data.fresh_air_valve_closed ||'');
                $('#fresh_air_valve_open_in_place_hidden_edit').val(data.fresh_air_valve_open_in_place ||'');
                $('#fresh_air_valve_closed_in_place_hidden_edit').val(data.fresh_air_valve_closed_in_place ||'');
                $('#return_fan_start_and_stop_hidden_edit').val(data.return_fan_start_and_stop ||'');
                $('#operation_status_of_return_fan_hidden_edit').val(data.operation_status_of_return_fan ||'');
                $('#manual_automatic_state_of_the_return_fan_hidden_edit').val(data.manual_automatic_state_of_the_return_fan ||'');
                $('#return_fan_failure_status_hidden_edit').val(data.return_fan_failure_status ||'');
                $('#exhaust_valve_opening_control_hidden_edit').val(data.exhaust_valve_opening_control ||'');
                $('#exhaust_valve_opening_feedback_hidden_edit').val(data.exhaust_valve_opening_feedback ||'');
                $('#exhaust_valve_open_hidden_edit').val(data.exhaust_valve_open ||'');
                $('#exhaust_valve_closed_hidden_edit').val(data.exhaust_valve_closed ||'');
                $('#the_exhaust_valve_is_open_in_place_hidden_edit').val(data.the_exhaust_valve_is_open_in_place ||'');
                $('#the_exhaust_valve_is_closed_in_place_hidden_edit').val(data.the_exhaust_valve_is_closed_in_place ||'');
                $('#supply_air_temperature_hidden_edit').val(data.supply_air_temperature ||'');
                $('#supply_air_humidity_hidden_edit').val(data.supply_air_humidity ||'');
                $('#fresh_air_temperature_hidden_edit').val(data.fresh_air_temperature ||'');
                $('#fresh_air_humidity_hidden_edit').val(data.fresh_air_humidity ||'');
                $('#return_air_temperature_hidden_edit').val(data.return_air_temperature ||'');
                $('#return_air_humidity_hidden_edit').val(data.return_air_humidity ||'');
                $('#carbon_dioxide_hidden_edit').val(data.carbon_dioxide ||'');
                $('#humidification_opening_control_hidden_edit').val(data.humidification_opening_control ||'');
                $('#humidification_opening_feedback_hidden_edit').val(data.humidification_opening_feedback ||'');
                $('#humidification_on_hidden_edit').val(data.humidification_on ||'');
                $('#humidification_off_hidden_edit').val(data.humidification_off ||'');
                $('#humidification_valve_open_in_place_hidden_edit').val(data.humidification_valve_open_in_place ||'');
                $('#humidification_valve_closed_in_place_hidden_edit').val(data.humidification_valve_closed_in_place ||'');
                $('#antifreeze_alarm_hidden_edit').val(data.antifreeze_alarm ||'');
                $('#initial_filter_screen_pressure_difference_alarm_hidden_edit').val(data.initial_filter_screen_pressure_difference_alarm ||'');
                $('#mediumefficiency_filter_pressure_difference_alarm_hidden_edit').val(data.mediumefficiency_filter_pressure_difference_alarm ||'');
                $('#return_air_filter_pressure_difference_alarm_hidden_edit').val(data.return_air_filter_pressure_difference_alarm ||'');
                $('#pressure_difference_of_blower_hidden_edit').val(data.pressure_difference_of_blower ||'');
                $('#return_fan_pressure_difference_hidden_edit').val(data.return_fan_pressure_difference ||'');



            });
        });

        /**
         * 编辑点击事件
         *
         */
        $(document).on('click', '#airconditioningUnitConfigTable button.delete', function () {

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
                    deleteAirconditioningConfig(id);
                }
            );

        });

        // 添加输入框验证
        var addValidate = $("#airconditioningunit_add_form").validate({
            rules: {
                airconditioningunit_name_add:{
                    required: true,
                    maxlength: 50

                }

            },
            messages: {
                airconditioningunit_name_add:{
                    required: '请输入名称'
                }
            },

            submitHandler: function(formData) {
                addSubmit(formData);
            }

        });



        // 编辑输入框验证
        var editValidate = $("#airconditioningunit_edit_form").validate({
            rules: {
                airconditioningunit_name_edit:{
                    required: true,
                    maxlength: 50

                }

            },
            messages: {
                airconditioningunit_name_edit:{
                    required: '请输入名称'
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
                url     : _ctx + "/view/app/airconditioningUnitConfig/create",
                dataType: "json",
                data: {
                    //展示用的数据
                    name                 : formData.airconditioningunit_name_add,
                    address            : formData.airconditioningunit_address_add,
                    new_fan_start_and_stop_display:formData.new_fan_start_and_stop_add,
                    operation_status_of_blower_display:formData.operation_status_of_blower_add,
                    manual_automatic_state_of_blower_display:formData.manual_automatic_state_of_blower_add,
                    blower_failure_status_display:formData.blower_failure_status_add,
                    fresh_air_valve_opening_control_display:formData.fresh_air_valve_opening_control_add,
                    fresh_air_valve_opening_feedback_display:formData.fresh_air_valve_opening_feedback_add,
                    fresh_air_valve_open_display:formData.fresh_air_valve_open_add,
                    fresh_air_valve_closed_display:formData.fresh_air_valve_closed_add,
                    fresh_air_valve_open_in_place_display:formData.fresh_air_valve_open_in_place_add,
                    fresh_air_valve_closed_in_place_display:formData.fresh_air_valve_closed_in_place_add,
                    return_fan_start_and_stop_display:formData.return_fan_start_and_stop_add,
                    operation_status_of_return_fan_display:formData.operation_status_of_return_fan_add,
                    manual_automatic_state_of_the_return_fan_display:formData.manual_automatic_state_of_the_return_fan_add,
                    return_fan_failure_status_display:formData.return_fan_failure_status_add,
                    exhaust_valve_opening_control_display:formData.exhaust_valve_opening_control_add,
                    exhaust_valve_opening_feedback_display:formData.exhaust_valve_opening_feedback_add,
                    exhaust_valve_open_display:formData.exhaust_valve_open_add,
                    exhaust_valve_closed_display:formData.exhaust_valve_closed_add,
                    the_exhaust_valve_is_open_in_place_display:formData.the_exhaust_valve_is_open_in_place_add,
                    the_exhaust_valve_is_closed_in_place_display:formData.the_exhaust_valve_is_closed_in_place_add,
                    supply_air_temperature_display:formData.supply_air_temperature_add,
                    supply_air_humidity_display:formData.supply_air_humidity_add,
                    fresh_air_temperature_display:formData.fresh_air_temperature_add,
                    fresh_air_humidity_display:formData.fresh_air_humidity_add,
                    return_air_temperature_display:formData.return_air_temperature_add,
                    return_air_humidity_display:formData.return_air_humidity_add,
                    carbon_dioxide_display:formData.carbon_dioxide_add,
                    humidification_opening_control_display:formData.humidification_opening_control_add,
                    humidification_opening_feedback_display:formData.humidification_opening_feedback_add,
                    humidification_on_display:formData.humidification_on_add,
                    humidification_off_display:formData.humidification_off_add,
                    humidification_valve_open_in_place_display:formData.humidification_valve_open_in_place_add,
                    humidification_valve_closed_in_place_display:formData.humidification_valve_closed_in_place_add,
                    antifreeze_alarm_display:formData.antifreeze_alarm_add,
                    initial_filter_screen_pressure_difference_alarm_display:formData.initial_filter_screen_pressure_difference_alarm_add,
                    mediumefficiency_filter_pressure_difference_alarm_display:formData.mediumefficiency_filter_pressure_difference_alarm_add,
                    return_air_filter_pressure_difference_alarm_display:formData.return_air_filter_pressure_difference_alarm_add,
                    pressure_difference_of_blower_display:formData.pressure_difference_of_blower_add,
                    return_fan_pressure_difference_display:formData.return_fan_pressure_difference_add,

                    //真实有用的数据
                    new_fan_start_and_stop:formData.new_fan_start_and_stop_hidden_add,
                    operation_status_of_blower:formData.operation_status_of_blower_hidden_add,
                    manual_automatic_state_of_blower:formData.manual_automatic_state_of_blower_hidden_add,
                    blower_failure_status:formData.blower_failure_status_hidden_add,
                    fresh_air_valve_opening_control:formData.fresh_air_valve_opening_control_hidden_add,
                    fresh_air_valve_opening_feedback:formData.fresh_air_valve_opening_feedback_hidden_add,
                    fresh_air_valve_open:formData.fresh_air_valve_open_hidden_add,
                    fresh_air_valve_closed:formData.fresh_air_valve_closed_hidden_add,
                    fresh_air_valve_open_in_place:formData.fresh_air_valve_open_in_place_hidden_add,
                    fresh_air_valve_closed_in_place:formData.fresh_air_valve_closed_in_place_hidden_add,
                    return_fan_start_and_stop:formData.return_fan_start_and_stop_hidden_add,
                    operation_status_of_return_fan:formData.operation_status_of_return_fan_hidden_add,
                    manual_automatic_state_of_the_return_fan:formData.manual_automatic_state_of_the_return_fan_hidden_add,
                    return_fan_failure_status:formData.return_fan_failure_status_hidden_add,
                    exhaust_valve_opening_control:formData.exhaust_valve_opening_control_hidden_add,
                    exhaust_valve_opening_feedback:formData.exhaust_valve_opening_feedback_hidden_add,
                    exhaust_valve_open:formData.exhaust_valve_open_hidden_add,
                    exhaust_valve_closed:formData.exhaust_valve_closed_hidden_add,
                    the_exhaust_valve_is_open_in_place:formData.the_exhaust_valve_is_open_in_place_hidden_add,
                    the_exhaust_valve_is_closed_in_place:formData.the_exhaust_valve_is_closed_in_place_hidden_add,
                    supply_air_temperature:formData.supply_air_temperature_hidden_add,
                    supply_air_humidity:formData.supply_air_humidity_hidden_add,
                    fresh_air_temperature:formData.fresh_air_temperature_hidden_add,
                    fresh_air_humidity:formData.fresh_air_humidity_hidden_add,
                    return_air_temperature:formData.return_air_temperature_hidden_add,
                    return_air_humidity:formData.return_air_humidity_hidden_add,
                    carbon_dioxide:formData.carbon_dioxide_hidden_add,
                    humidification_opening_control:formData.humidification_opening_control_hidden_add,
                    humidification_opening_feedback:formData.humidification_opening_feedback_hidden_add,
                    humidification_on:formData.humidification_on_hidden_add,
                    humidification_off:formData.humidification_off_hidden_add,
                    humidification_valve_open_in_place:formData.humidification_valve_open_in_place_hidden_add,
                    humidification_valve_closed_in_place:formData.humidification_valve_closed_in_place_hidden_add,
                    antifreeze_alarm:formData.antifreeze_alarm_hidden_add,
                    initial_filter_screen_pressure_difference_alarm:formData.initial_filter_screen_pressure_difference_alarm_hidden_add,
                    mediumefficiency_filter_pressure_difference_alarm:formData.mediumefficiency_filter_pressure_difference_alarm_hidden_add,
                    return_air_filter_pressure_difference_alarm:formData.return_air_filter_pressure_difference_alarm_hidden_add,
                    pressure_difference_of_blower:formData.pressure_difference_of_blower_hidden_add,
                    return_fan_pressure_difference:formData.return_fan_pressure_difference_hidden_add


                },
                success: function (result) {

                    var status = result && result.status;

                    if(status === "1"){

                        getPagingPage({}, function (data) {
                            showPagingPage('airconditioningunit_page', data);

                        });

                        $('#airconditioningunit_modal_add').modal('hide');

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
                url     : _ctx + "/view/app/airconditioningUnitConfig/update",
                dataType: "json",
                data: {
                    //展示用的数据
                    id                   : formData.id_airconditioningunit_edit,
                    name                 : formData.airconditioningunit_name_edit,
                    address            : formData.airconditioningunit_address_edit,
                    new_fan_start_and_stop_display:formData.new_fan_start_and_stop_edit,
                    operation_status_of_blower_display:formData.operation_status_of_blower_edit,
                    manual_automatic_state_of_blower_display:formData.manual_automatic_state_of_blower_edit,
                    blower_failure_status_display:formData.blower_failure_status_edit,
                    fresh_air_valve_opening_control_display:formData.fresh_air_valve_opening_control_edit,
                    fresh_air_valve_opening_feedback_display:formData.fresh_air_valve_opening_feedback_edit,
                    fresh_air_valve_open_display:formData.fresh_air_valve_open_edit,
                    fresh_air_valve_closed_display:formData.fresh_air_valve_closed_edit,
                    fresh_air_valve_open_in_place_display:formData.fresh_air_valve_open_in_place_edit,
                    fresh_air_valve_closed_in_place_display:formData.fresh_air_valve_closed_in_place_edit,
                    return_fan_start_and_stop_display:formData.return_fan_start_and_stop_edit,
                    operation_status_of_return_fan_display:formData.operation_status_of_return_fan_edit,
                    manual_automatic_state_of_the_return_fan_display:formData.manual_automatic_state_of_the_return_fan_edit,
                    return_fan_failure_status_display:formData.return_fan_failure_status_edit,
                    exhaust_valve_opening_control_display:formData.exhaust_valve_opening_control_edit,
                    exhaust_valve_opening_feedback_display:formData.exhaust_valve_opening_feedback_edit,
                    exhaust_valve_open_display:formData.exhaust_valve_open_edit,
                    exhaust_valve_closed_display:formData.exhaust_valve_closed_edit,
                    the_exhaust_valve_is_open_in_place_display:formData.the_exhaust_valve_is_open_in_place_edit,
                    the_exhaust_valve_is_closed_in_place_display:formData.the_exhaust_valve_is_closed_in_place_edit,
                    supply_air_temperature_display:formData.supply_air_temperature_edit,
                    supply_air_humidity_display:formData.supply_air_humidity_edit,
                    fresh_air_temperature_display:formData.fresh_air_temperature_edit,
                    fresh_air_humidity_display:formData.fresh_air_humidity_edit,
                    return_air_temperature_display:formData.return_air_temperature_edit,
                    return_air_humidity_display:formData.return_air_humidity_edit,
                    carbon_dioxide_display:formData.carbon_dioxide_edit,
                    humidification_opening_control_display:formData.humidification_opening_control_edit,
                    humidification_opening_feedback_display:formData.humidification_opening_feedback_edit,
                    humidification_on_display:formData.humidification_on_edit,
                    humidification_off_display:formData.humidification_off_edit,
                    humidification_valve_open_in_place_display:formData.humidification_valve_open_in_place_edit,
                    humidification_valve_closed_in_place_display:formData.humidification_valve_closed_in_place_edit,
                    antifreeze_alarm_display:formData.antifreeze_alarm_edit,
                    initial_filter_screen_pressure_difference_alarm_display:formData.initial_filter_screen_pressure_difference_alarm_edit,
                    mediumefficiency_filter_pressure_difference_alarm_display:formData.mediumefficiency_filter_pressure_difference_alarm_edit,
                    return_air_filter_pressure_difference_alarm_display:formData.return_air_filter_pressure_difference_alarm_edit,
                    pressure_difference_of_blower_display:formData.pressure_difference_of_blower_edit,
                    return_fan_pressure_difference_display:formData.return_fan_pressure_difference_edit,

                    //真实有用的数据
                    new_fan_start_and_stop:formData.new_fan_start_and_stop_hidden_edit,
                    operation_status_of_blower:formData.operation_status_of_blower_hidden_edit,
                    manual_automatic_state_of_blower:formData.manual_automatic_state_of_blower_hidden_edit,
                    blower_failure_status:formData.blower_failure_status_hidden_edit,
                    fresh_air_valve_opening_control:formData.fresh_air_valve_opening_control_hidden_edit,
                    fresh_air_valve_opening_feedback:formData.fresh_air_valve_opening_feedback_hidden_edit,
                    fresh_air_valve_open:formData.fresh_air_valve_open_hidden_edit,
                    fresh_air_valve_closed:formData.fresh_air_valve_closed_hidden_edit,
                    fresh_air_valve_open_in_place:formData.fresh_air_valve_open_in_place_hidden_edit,
                    fresh_air_valve_closed_in_place:formData.fresh_air_valve_closed_in_place_hidden_edit,
                    return_fan_start_and_stop:formData.return_fan_start_and_stop_hidden_edit,
                    operation_status_of_return_fan:formData.operation_status_of_return_fan_hidden_edit,
                    manual_automatic_state_of_the_return_fan:formData.manual_automatic_state_of_the_return_fan_hidden_edit,
                    return_fan_failure_status:formData.return_fan_failure_status_hidden_edit,
                    exhaust_valve_opening_control:formData.exhaust_valve_opening_control_hidden_edit,
                    exhaust_valve_opening_feedback:formData.exhaust_valve_opening_feedback_hidden_edit,
                    exhaust_valve_open:formData.exhaust_valve_open_hidden_edit,
                    exhaust_valve_closed:formData.exhaust_valve_closed_hidden_edit,
                    the_exhaust_valve_is_open_in_place:formData.the_exhaust_valve_is_open_in_place_hidden_edit,
                    the_exhaust_valve_is_closed_in_place:formData.the_exhaust_valve_is_closed_in_place_hidden_edit,
                    supply_air_temperature:formData.supply_air_temperature_hidden_edit,
                    supply_air_humidity:formData.supply_air_humidity_hidden_edit,
                    fresh_air_temperature:formData.fresh_air_temperature_hidden_edit,
                    fresh_air_humidity:formData.fresh_air_humidity_hidden_edit,
                    return_air_temperature:formData.return_air_temperature_hidden_edit,
                    return_air_humidity:formData.return_air_humidity_hidden_edit,
                    carbon_dioxide:formData.carbon_dioxide_hidden_edit,
                    humidification_opening_control:formData.humidification_opening_control_hidden_edit,
                    humidification_opening_feedback:formData.humidification_opening_feedback_hidden_edit,
                    humidification_on:formData.humidification_on_hidden_edit,
                    humidification_off:formData.humidification_off_hidden_edit,
                    humidification_valve_open_in_place:formData.humidification_valve_open_in_place_hidden_edit,
                    humidification_valve_closed_in_place:formData.humidification_valve_closed_in_place_hidden_edit,
                    antifreeze_alarm:formData.antifreeze_alarm_hidden_edit,
                    initial_filter_screen_pressure_difference_alarm:formData.initial_filter_screen_pressure_difference_alarm_hidden_edit,
                    mediumefficiency_filter_pressure_difference_alarm:formData.mediumefficiency_filter_pressure_difference_alarm_hidden_edit,
                    return_air_filter_pressure_difference_alarm:formData.return_air_filter_pressure_difference_alarm_hidden_edit,
                    pressure_difference_of_blower:formData.pressure_difference_of_blower_hidden_edit,
                    return_fan_pressure_difference:formData.return_fan_pressure_difference_hidden_edit

                },
                success: function (result) {

                    var status = result && result.status;

                    if(status === "1"){

                        $('#airconditioningunit_modal_edit').modal('hide');

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
                            showPagingPage('airconditioningunit_page', data);
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

        function deleteAirconditioningConfig(id) {

            if (!id) {
                console.warn('无效参数！');
                return;
            }

            $.ajax({
                type    : "POST",
                url     : _ctx + "/view/app/airconditioningUnitConfig/delete",
                dataType: "json",
                data: {
                    id : id,

                },
                success: function (result) {

                    var status = result && result.status;

                    if(status === "1"){

                        getPagingPage({}, function (data) {
                            showPagingPage('airconditioningunit_page', data);
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
                url     : _ctx + '/view/app/airconditioningUnitConfig/getPaging',
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
