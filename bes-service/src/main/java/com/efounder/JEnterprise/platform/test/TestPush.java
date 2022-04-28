package com.efounder.JEnterprise.platform.test;

import com.efounder.JEnterprise.service.opm.PushDeviceAlarm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * describe: 告警信息推送方法手动调用
 *
 * @author zs
 * @date 2020/11/30
 */
@Controller
@RequestMapping("/push")
public class TestPush {

    @Resource
    private PushDeviceAlarm pushDeviceAlarm;

    @RequestMapping("/alarm")
    public void pushAlarm(String sysname,String alertMsg){
        //传一个配置好的点位sysname
        pushDeviceAlarm.pushDeviceOfflineInfo(sysname,alertMsg);
    }
}
