package com.efounder.JEnterprise.zhdg.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * describe: 智慧灯杆接口相关配置
 *
 * @author zs
 * @date 2021/5/21
 */
@Component
public class LightPoleConfig {

    /**
     * 智慧灯杆大屏请求统一接口
     */
    @Value("${lightPole.screen.url}")
    public String screenUrl;

    /**
     * 音柱广播控制接口
     */
    @Value("${lightPole.sound.broadcast.playControl}")
    public String playControl;


    /**
     * PDU开关控制url
     */
    @Value("${lightPole.pdu.switch.control_url}")
    public String switchControlUrl;

    /**
     * PDU开关状态获取url
     */
    @Value("${lightPole.pdu.switch.status_url}")
    public String switchStatusUrl;

    /**
     * PDU推送开关状态url
     */
    @Value("${lightPole.pdu.switch.push_status_url}")
    public String pushSwitchStatusUrl;


    /**
     * 向信息发布推送显示屏的开关状态url
     */
    @Value("${lightPole.pdu.switch.push_screen_status_url}")
    public String pushScreenSwitchStatusUrl;

    /**
     * 获取在线设备数据
     */
    @Value("${lightPole.pdu.switch.get_online_data_url}")
    public String getOnlineDataUrl;
}
