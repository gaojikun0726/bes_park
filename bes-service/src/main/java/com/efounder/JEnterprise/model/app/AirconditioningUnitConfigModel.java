package com.efounder.JEnterprise.model.app;

import lombok.Data;


/**
 *
 * 电表配置
 *
 * @author xiepufeng
 *
 */
@Data
public class AirconditioningUnitConfigModel{

    private Integer    id;                   // 主键
    private String     name;                 // 空调名称
    private String     address;          // 空调位置
    //展示用
    private String     new_fan_start_and_stop_display;//展示用新风机启停
    private String     operation_status_of_blower_display;//展示用送风机运行状态（运行、停止）
    private String     manual_automatic_state_of_blower_display;//展示用送风机手自动状态
    private String     blower_failure_status_display;//展示用送风机故障状态（正常、故障）
    private String     fresh_air_valve_opening_control_display;//展示用新风阀开度控制
    private String     fresh_air_valve_opening_feedback_display;//展示用新风阀开度反馈
    private String     fresh_air_valve_open_display;//展示用新风阀开（开启、关闭）
    private String     fresh_air_valve_closed_display;//展示用新风阀关（开启、关闭）
    private String     fresh_air_valve_open_in_place_display;//展示用新风阀开到位（正常、异常）
    private String     fresh_air_valve_closed_in_place_display;//展示用新风阀关到位（正常、异常）
    private String     return_fan_start_and_stop_display;//展示用回风机启停
    private String     operation_status_of_return_fan_display;//展示用回风机运行状态（运行、停止）
    private String     manual_automatic_state_of_the_return_fan_display;//展示用回风机手自动状态
    private String     return_fan_failure_status_display;//展示用回风机故障状态（正常、故障）
    private String     exhaust_valve_opening_control_display;//展示用排风阀开度控制
    private String     exhaust_valve_opening_feedback_display;//展示用排风阀开度反馈
    private String     exhaust_valve_open_display;//展示用排风阀开（开启、关闭）
    private String     exhaust_valve_closed_display;//展示用排风阀关（开启、关闭）
    private String     the_exhaust_valve_is_open_in_place_display;//展示用排风阀开到位（正常、异常）
    private String     the_exhaust_valve_is_closed_in_place_display;//展示用排风阀关到位（正常、异常）
    private String     supply_air_temperature_display;//展示用送风温度（℃）
    private String     supply_air_humidity_display;//展示用送风湿度（%）
    private String     fresh_air_temperature_display;//展示用新风温度（℃）
    private String     fresh_air_humidity_display;//展示用新风湿度（%）
    private String     return_air_temperature_display;//展示用回风温度（℃）
    private String     return_air_humidity_display;//展示用回风湿度（%）
    private String     carbon_dioxide_display;//展示用二氧化碳(CO2)（ppm）
    private String     humidification_opening_control_display;//展示用加湿开度控制
    private String     humidification_opening_feedback_display;//展示用加湿开度反馈
    private String     humidification_on_display;//展示用加湿开（开启、关闭）
    private String     humidification_off_display;//展示用加湿关（开启、关闭）
    private String     humidification_valve_open_in_place_display;//展示用加湿开到位（正常、异常）
    private String     humidification_valve_closed_in_place_display;//展示用加湿关到位（正常、异常）
    private String     antifreeze_alarm_display;//展示用防冻报警（正常、异常）
    private String     initial_filter_screen_pressure_difference_alarm_display;//展示用初效过滤网压差报警（正常、异常）
    private String     mediumefficiency_filter_pressure_difference_alarm_display;//展示用中效过滤网压差报警（正常、异常）
    private String     return_air_filter_pressure_difference_alarm_display;//展示用回风过滤网压差报警（正常、异常）
    private String     pressure_difference_of_blower_display;//展示用送风机压差（正常、异常）
    private String     return_fan_pressure_difference_display;//展示用回风机压差（正常、异常）

    //提供对外用
    private String     new_fan_start_and_stop;//新风机启停
    private String     operation_status_of_blower;//送风机运行状态（运行、停止）
    private String     manual_automatic_state_of_blower;//送风机手自动状态
    private String     blower_failure_status;//送风机故障状态（正常、故障）
    private String     fresh_air_valve_opening_control;//新风阀开度控制
    private String     fresh_air_valve_opening_feedback;//新风阀开度反馈
    private String     fresh_air_valve_open;//新风阀开（开启、关闭）
    private String     fresh_air_valve_closed;//新风阀关（开启、关闭）
    private String     fresh_air_valve_open_in_place;//新风阀开到位（正常、异常）
    private String     fresh_air_valve_closed_in_place;//新风阀关到位（正常、异常）
    private String     return_fan_start_and_stop;//回风机启停
    private String     operation_status_of_return_fan;//回风机运行状态（运行、停止）
    private String     manual_automatic_state_of_the_return_fan;//回风机手自动状态
    private String     return_fan_failure_status;//回风机故障状态（正常、故障）
    private String     exhaust_valve_opening_control;//排风阀开度控制
    private String     exhaust_valve_opening_feedback;//排风阀开度反馈
    private String     exhaust_valve_open;//排风阀开（开启、关闭）
    private String     exhaust_valve_closed;//排风阀关（开启、关闭）
    private String     the_exhaust_valve_is_open_in_place;//排风阀开到位（正常、异常）
    private String     the_exhaust_valve_is_closed_in_place;//排风阀关到位（正常、异常）
    private String     supply_air_temperature;//送风温度（℃）
    private String     supply_air_humidity;//送风湿度（%）
    private String     fresh_air_temperature;//新风温度（℃）
    private String     fresh_air_humidity;//新风湿度（%）
    private String     return_air_temperature;//回风温度（℃）
    private String     return_air_humidity;//回风湿度（%）
    private String     carbon_dioxide;//二氧化碳(CO2)（ppm）
    private String     humidification_opening_control;//加湿开度控制
    private String     humidification_opening_feedback;//加湿开度反馈
    private String     humidification_on;//加湿开（开启、关闭）
    private String     humidification_off;//加湿关（开启、关闭）
    private String     humidification_valve_open_in_place;//加湿开到位（正常、异常）
    private String     humidification_valve_closed_in_place;//加湿关到位（正常、异常）
    private String     antifreeze_alarm;//防冻报警（正常、异常）
    private String     initial_filter_screen_pressure_difference_alarm;//初效过滤网压差报警（正常、异常）
    private String     mediumefficiency_filter_pressure_difference_alarm;//中效过滤网压差报警（正常、异常）
    private String     return_air_filter_pressure_difference_alarm;//回风过滤网压差报警（正常、异常）
    private String     pressure_difference_of_blower;//送风机压差（正常、异常）
    private String     return_fan_pressure_difference;//回风机压差（正常、异常）


}
