package com.efounder.JEnterprise.zhdg.Scheduled;

import com.efounder.JEnterprise.zhdg.service.ISebDynamicAgreeHandleService;
import com.efounder.JEnterprise.zhdg.service.WarnRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * zhdg--定时任务
 */
@Component
public class zhdgScheduled {

    @Value("${task.switch}")// 获取定时任务开关
    private String taskSwitch;
    @Autowired
    private ISebDynamicAgreeHandleService dynamicAgreeHandleService;
    @Autowired
    private WarnRecordService recordService;

//    @Autowired
//    private PointService pointService;

    /**
     * 定时--修改点位在线状态
     * 避免集群部署多个服务器同时执行定时任务修改数据库的气象状态，每个服务只修改自己的内部缓存数据。
     * 原因：【服务器主备部署配置】netty模块的灯杆数据信息只向一个主服务器推送，如果每个服务都执行定时任务向数据库保存状态，
     * 那除了主服务器可以保存正常的接收数据外，其余备服务器保存的数据都不正确，可能会把主服务器保存的数据覆盖。
     */
    @Scheduled(fixedRate = 5000)
    private void updatePointStatus() throws Exception{
        if(taskSwitch.equals("open")){
            dynamicAgreeHandleService.updatePointStatus();
        }
    }

    /** 保存报警信息到报警历史记录表 */
    @Scheduled(fixedRate = 300000)
    public void insertRealWarn() throws Exception {
        // 1.0 实时报警插入报警历史表
        recordService.saveRealWarn();
        // 2.0 -- 处理报警信息--清空实时报警
        dynamicAgreeHandleService.cleanWarn();

//        //每隔5分钟保存一次气象状态缓存数据
//        pointService.saveWeatherStatus(SebDynamicAgreeHandleServiceImpl.weatherStatusMap);
    }


}
