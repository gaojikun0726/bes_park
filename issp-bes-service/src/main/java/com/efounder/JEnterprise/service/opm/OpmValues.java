package com.efounder.JEnterprise.service.opm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * describe: 中台接口配置
 * bes_service中的无法引入，重新定义一份
 * @author zs
 * @date 2020/12/1
 */
@Component
public class OpmValues {

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

    @Value("${opm.appId}")
    public String appId;

    @Value("${opm.clientSecret}")
    public String clientSecret;

    /**
     * 报警推送接口
     */
    @Value("${opm.push_device_alarm.alarm_url}")
    public String alarmUrl;

    /**
     * 报警类型查询接口
     */
    @Value("${opm.push_device_alarm.alarm_type_url}")
    public String alarmTypeUrl;


    /**
     * 报警类型--设备报警编码
     */
    @Value("${opm.push_device_alarm.device_alarm_code}")
    public String deviceAlarmCode;

}
