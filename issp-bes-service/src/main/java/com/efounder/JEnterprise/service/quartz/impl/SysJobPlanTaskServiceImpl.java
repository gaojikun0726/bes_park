package com.efounder.JEnterprise.service.quartz.impl;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.domain.SysJob;
import com.efounder.JEnterprise.initializer.PlanCache;
import com.efounder.JEnterprise.mapper.basedatamanage.energyinformation.BESStrategyMapper;
import com.efounder.JEnterprise.mapper.quartz.SysJobPlanMapper;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESStrategy;
import com.efounder.JEnterprise.service.quartz.SysJobPlanTaskService;
import com.efounder.constants.ScheduleConstants;
import com.efounder.exception.TaskException;
import com.efounder.util.ScheduleUtils;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static com.efounder.util.ScheduleUtils.getJobKey;

/**
 * @author sunzhiyuan
 * @Data 2021/1/12 17:01
 */
@Service("SysJobPlanTaskService")
public class SysJobPlanTaskServiceImpl implements SysJobPlanTaskService {

    @Autowired
    private SysJobPlanMapper sysJobPlanMapper;

    @Autowired
    private BESStrategyMapper besStrategyMapper;

    @Autowired
    private Scheduler scheduler;

    //计划缓存
    @Autowired
    private PlanCache planCache;

    //添加时间类型的定时数据
    @Override
    @Transactional
    public ISSPReturnObject insertTimeTaskInfomation(JSONObject object) throws SchedulerException, TaskException {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        SysJob sysJob = new SysJob();

        String planId = (String) object.get("planId");

        Map<String,Object> taskList = sysJobPlanMapper.selectTaskInfomation(planId);

        String f_id = UUIDUtil.getRandom32BeginTimePK();

        sysJob.setJobId(f_id);

        sysJob.setPlanId(planId);

        sysJob.setJobName((String) taskList.get("f_timename"));

        sysJob.setJobGroup("DEFAULT");

        sysJob.setInvokeTarget("besTask.executePlanTaskInfo");

        sysJob.setCronExpression((String) taskList.get("f_cronstartexpre"));

        sysJob.setMisfirePolicy("3");

        sysJob.setConcurrent("0");

        sysJob.setStatus("1");

        sysJob.setCreateBy("admin");

        sysJob.setUpdateBy("admin");

        sysJob.setRemark((String) taskList.get("f_convertmin"));

        sysJob.setPlanId(planId);

        int rows = sysJobPlanMapper.insertSysJobTaskInfo(sysJob);

        sysJob.setStatus(ScheduleConstants.Status.NORMAL.getValue());

        if (rows > 0){
            returnObject.setStatus("1");
            returnObject.setData(f_id);
            ScheduleUtils.createScheduleJob(scheduler,sysJob);
        }else {
            returnObject.setStatus("0");
        }
        return returnObject;
    }


    /**
     * 执行定时任务
     * @param jobId
     * @return
     */
    @Override
    public ISSPReturnObject executeInfomation(String jobId,String planId) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        String f_invoke = "1";

        Boolean flag = sysJobPlanMapper.updateStopPlanStatus(planId,f_invoke);
        if (!flag) {
            planCache.updateOneplanByPlanIdCache(planId,f_invoke);
            returnObject.setStatus("0");
            return returnObject;
        }

        Map<String,Object> map1 = sysJobPlanMapper.getSysJobInfoById(jobId);

        returnObject = resumeJob(map1);

        return returnObject;
    }

    /**
     * 恢复任务
     *
     * @param map 调度信息
     */
    @Transactional
    public ISSPReturnObject resumeJob(Map<String,Object> map){

        ISSPReturnObject returnObject = new ISSPReturnObject();

        String jobId = (String) map.get("job_id");

        String jobGroup = (String) map.get("job_group");

        String status = "1";

        map.put("status",status);

        Boolean flag = sysJobPlanMapper.updateJob(status,jobId);

        if (flag) {
            returnObject.setStatus("1");
            try {
                scheduler.resumeJob(getJobKey(jobId, jobGroup));
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }else {
            returnObject.setStatus("0");
        }
        return returnObject;
    }


    /**
     * 暂停定时任务
     * @param jobId
     * @return
     */
    @Override
    public ISSPReturnObject stopInfomation(String jobId,String planId) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        String f_invoke = "0";

        Boolean flag = sysJobPlanMapper.updateStopPlanStatus(planId,f_invoke);
        if (!flag) {
            planCache.updateOneplanByPlanIdCache(planId,f_invoke);
            returnObject.setStatus("0");
            return returnObject;
        }

        Map<String,Object> map1 = sysJobPlanMapper.getSysJobInfoById(jobId);

        returnObject = pauseJob(map1);

        return returnObject;
    }

    /**
     * 暂停任务
     *
     * @param map 调度信息
     */
    @Transactional
    public ISSPReturnObject pauseJob(Map<String,Object> map){

        ISSPReturnObject returnObject = new ISSPReturnObject();

        String jobId = (String) map.get("job_id");

        String jobGroup = (String) map.get("job_group");

        String status = "0";

        map.put("status",status);

        Boolean flag = sysJobPlanMapper.updateJob(status,jobId);

        if (flag) {

            returnObject.setStatus("1");
            try {
                scheduler.pauseJob(getJobKey(jobId, jobGroup));
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }else {
            returnObject.setStatus("0");
        }
        return returnObject;
    }

    //添加定时同步设备树任务
    @Override
    @Transactional
    public ISSPReturnObject insertTimeTaskSyncInfomation(JSONObject object) throws SchedulerException, TaskException {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        SysJob sysJob = new SysJob();

        String syncId = (String) object.get("syncId");

        Map<String,Object> syncInfo = sysJobPlanMapper.selectTimeTaskSyncInfomation(syncId);

        String f_id = UUIDUtil.getRandom32BeginTimePK();

        sysJob.setJobId(f_id);

        sysJob.setSyncId(syncId);

        sysJob.setJobName((String) syncInfo.get("f_job_name"));

        sysJob.setJobGroup("DEFAULT");

        sysJob.setInvokeTarget("besTask.executeTimeTaskSyncInfo");

        sysJob.setCronExpression((String) syncInfo.get("f_cron"));

        sysJob.setMisfirePolicy("3");

        sysJob.setConcurrent("0");

        sysJob.setStatus("1");

        sysJob.setCreateBy("admin");

        sysJob.setUpdateBy("admin");

        sysJob.setRemark((String) syncInfo.get("f_remark"));

        int rows = sysJobPlanMapper.insertSysJobSyncInfo(sysJob);

        sysJob.setStatus(ScheduleConstants.Status.NORMAL.getValue());

        if (rows > 0){
            returnObject.setStatus("1");
            Map<String,Object> map = new HashMap<>();
            map.put("jobId",f_id);
            map.put("syncId",syncId);
            returnObject.setData(f_id);
            returnObject.setMap(map);
            ScheduleUtils.createScheduleJob(scheduler,sysJob);
        }else {
            returnObject.setStatus("0");
        }
        return returnObject;
    }

    /**
     * 执行定时同步设备树任务
     * @param jobId
     * @return
     */
    @Override
    public ISSPReturnObject executeTimeTaskSyncInfomation(String jobId,String syncId) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        String f_status = "1";

        //修改bes_time_task_sync表状态
        sysJobPlanMapper.updateTimeTaskSyncStatus(syncId,f_status);

        //查询出sys_job执行任务表信息
        Map<String,Object> map1 = sysJobPlanMapper.getSysJobInfoById(jobId);

        //恢复执行
        returnObject = resumeJob(map1);

        return returnObject;
    }

    /**
     * 暂停定时同步设备树任务
     * @param jobId
     * @return
     */
    @Override
    public ISSPReturnObject stopTimeTaskSyncInfomation(String jobId,String syncId) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        String f_status = "0";

        //修改bes_time_task_sync表状态
        sysJobPlanMapper.updateTimeTaskSyncStatus(syncId,f_status);

        //查询出sys_job执行任务表信息
        Map<String,Object> map1 = sysJobPlanMapper.getSysJobInfoById(jobId);

        //暂停
        returnObject = pauseJob(map1);

        return returnObject;
    }




}
