package com.efounder.JEnterprise.controller.quartz;

/**
 * @author sunzhiyuan
 * @Data 2021/1/12 16:56
 */
import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESStrategy;
import com.efounder.JEnterprise.service.quartz.SysJobPlanTaskService;
import com.efounder.exception.TaskException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author sunzhiyuan
 * @Data 2020/9/27 9:56
 */
@Controller
@RequestMapping(value = "/quartz")
public class SysJobPlanTaskController {

    private static final Logger log = LoggerFactory.getLogger(SysJobPlanTaskController.class);

    @Autowired
    SysJobPlanTaskService sysJobPlanTaskService;

    //添加时间类型的定时数据
    @RequestMapping(value = "/insertTimeTaskInfomation", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject insertTimeTaskInfomation(@RequestBody JSONObject object) throws SchedulerException, TaskException {
        log.info("#SysJobPlanTaskController新增时间类型的定时信息");
        return sysJobPlanTaskService.insertTimeTaskInfomation(object);
    }

    //执行定时任务
    @RequestMapping(value = "/executeInfomation", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject executeInfomation(String jobId,String planId){
        log.info("#BesplanConfigController执行定时任务信息");
        return sysJobPlanTaskService.executeInfomation(jobId,planId);
    }

    //暂停定时任务
    @RequestMapping(value = "/stopInfomation", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject stopInfomation(String jobId,String planId){
        log.info("#BesplanConfigController执行定时任务信息");
        return sysJobPlanTaskService.stopInfomation(jobId,planId);
    }

    //添加定时同步设备树任务
    @RequestMapping(value = "/insertTimeTaskSyncInfomation", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject insertTimeTaskSyncInfomation(@RequestBody JSONObject object) throws SchedulerException, TaskException {
        log.info("#SysJobPlanTaskController 新增定时同步设备树任务");
        return sysJobPlanTaskService.insertTimeTaskSyncInfomation(object);
    }

    //执行定时同步设备树任务
    @RequestMapping(value = "/executeTimeTaskSyncInfomation", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject executeTimeTaskSyncInfomation(String jobId,String syncId){
        log.info("#SysJobPlanTaskController 执行定时同步设备树任务");
        return sysJobPlanTaskService.executeTimeTaskSyncInfomation(jobId,syncId);
    }

    //暂停定时同步设备树任务
    @RequestMapping(value = "/stopTimeTaskSyncInfomation", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject stopTimeTaskSyncInfomation(String jobId,String syncId){
        log.info("#SysJobPlanTaskController 暂停定时同步设备树任务");
        return sysJobPlanTaskService.stopTimeTaskSyncInfomation(jobId,syncId);
    }


}
