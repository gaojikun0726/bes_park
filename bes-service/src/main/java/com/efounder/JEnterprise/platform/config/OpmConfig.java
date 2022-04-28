package com.efounder.JEnterprise.platform.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.apache.commons.lang.ArrayUtils;

/**
 * describe: 中台对接配置数据
 *
 * @author zs
 * @date 2020/11/25
 */
@Component
public class OpmConfig {

    /**
     * 外部系统登录使用的用户名
     */
    @Value("${opm.loginName}")
    public String loginName;


    @Value("${opm.access_ip}")
    public String accessIp;

    @Value("${opm.get_token.token_url}")
    public String tokenUrl;
//
//    @Value("${opm.get_token.username}")
//    public String username;
//
//    @Value("${opm.get_token.password}")
//    public String password;
//
//    @Value("${opm.get_token.uuid}")
//    public String uuid;
//
//    @Value("${opm.get_token.captcha}")
//    public String captcha;

    @Value("${opm.get_position.position_url}")
    public String positionUrl;

    @Value("${opm.appId}")
    public String appId;

    @Value("${opm.clientSecret}")
    public String clientSecret;

//    @Value("${opm.get_position.syncTimeStamp}")
//    public String syncTimeStamp;

//    @Value("${opm.get_user.user_url}")
//    public String userUrl;
//
//    @Value("${opm.get_role.role_url}")
//    public String roleUrl;

    @Value("${opm.get_dept.dept_url}")
    public String deptUrl;

//    @Value("${opm.get_role_menu.role_menu_url}")
//    public String roleMenuUrl;

    @Value("${opm.get_assets.all_assets_url}")
    public String allAssetsUrl;

    @Value("${opm.get_assets.assets_url}")
    public String assetsUrl;

    @Value("${opm.get_assets.category_code.conditioner_code}")
    public String conditionerCode;

    @Value("${opm.get_assets.category_code.electric_curtain_code}")
    public String curtainCode;

    @Value("${opm.get_assets.category_code.light_code}")
    public String lightCode;

    @Value("${opm.get_assets.category_code.fresh_air_code}")
    public String freshAirCode;

    @Value("${opm.get_assets.category_code.temperature_controller_code}")
    public String temperatureCode;

    @Value("${opm.get_assets.category_code.adjust_light}")
    public String adjustLightCode;

    @Value("${opm.get_assets.category_code.air_handing_unit_code}")
    public String airUnitCode;

    @Value("${opm.get_assets.category_code.socket_code}")
    public String socketCode;

    @Value("${opm.get_assets.category_code.light_pole_code}")
    public String lightPoleCode;

//    @Value("${opm.get_assets.category_code.well_lid_code}")
//    public String wellLidCode;

    @Value("${opm.get_assets.category_code.meter_code}")
    public String[] meterCode;

    @Value("${opm.push_device_alarm.alarm_url}")
    public String alarmUrl;

    /**
     * 返回设备编码数组
     * @return
     */
    public String[] getCodeArray(){
        String[] codeArray = {conditionerCode,curtainCode,lightCode,freshAirCode,temperatureCode,
            adjustLightCode,airUnitCode,socketCode,lightPoleCode};
        String[] result = (String[]) ArrayUtils.addAll(codeArray, meterCode);
        return result;
    }
}
