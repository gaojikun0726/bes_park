package com.efounder.JEnterprise.service.quartz.impl;


import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.domain.SysJob;
import com.efounder.JEnterprise.mapper.quartz.SysJobMapper;
import com.efounder.JEnterprise.service.quartz.ISysJobService;
import com.efounder.constants.ScheduleConstants;
import com.efounder.exception.TaskException;
import com.efounder.util.CronUtils;
import com.efounder.util.ScheduleUtils;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 定时任务调度信息 服务层
 *
 * @author sunzhiyuan
 */
@Service("ISysJobService")
public class SysJobServiceImpl implements ISysJobService
{
    @Autowired
    private Scheduler scheduler;

    @Autowired
    private SysJobMapper jobMapper;


    /**
     * 项目启动时，初始化定时器 主要是防止手动修改数据库导致未同步到定时任务处理（注：不能手动修改数据库ID和任务组名，否则会导致脏数据）
     */
    @PostConstruct
    public void init() throws SchedulerException, TaskException{
        scheduler.clear();
        List<SysJob> jobList = jobMapper.selectJobAll();
        for (SysJob job : jobList)
        {
            ScheduleUtils.createScheduleJob(scheduler, job);
        }
    }

    /**
     * 获取quartz调度器的计划任务列表
     *
     * @param job 调度信息
     * @return
     */
    @Override
    public ISSPReturnObject selectJobList(SysJob job) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        List<SysJob> list = jobMapper.selectJobList(job);

        returnObject.setList(list);

        return returnObject;
    }

    /**
     * 通过调度任务ID查询调度信息
     *
     * @param jobId 调度任务ID
     * @return 调度任务对象信息
     */
    @Override
    public ISSPReturnObject selectJobById(String jobId) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        SysJob sysJob =jobMapper.selectJobById(jobId);

        returnObject.setData(sysJob);

        return returnObject;
    }

    /**
     * 批量删除调度信息
     *
     * @param jobIds 需要删除的任务ID
     * @return 结果
     */
    @Override
    @Transactional
    public ISSPReturnObject deleteJobByIds(String[] jobIds) throws SchedulerException {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        for (String jobId : jobIds)
        {
            SysJob job = jobMapper.selectJobById(jobId);
            returnObject = deleteJob(job);
        }

        return returnObject;
    }

    /**
     * 删除任务后，所对应的trigger也将被删除
     *
     * @param job 调度信息
     */
    @Override
    @Transactional
    public ISSPReturnObject deleteJob(SysJob job) throws SchedulerException{

        ISSPReturnObject returnObject = new ISSPReturnObject();

        String jobId = job.getJobId();
        String jobGroup = job.getJobGroup();
        Boolean flag = jobMapper.deleteJobById(jobId);
        if (flag){
            returnObject.setStatus("1");
            scheduler.deleteJob(ScheduleUtils.getJobKey(jobId, jobGroup));
        }
        return returnObject;
    }

    @Override
    @Transactional
    public ISSPReturnObject changeStatus(SysJob job) throws SchedulerException {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        String status = job.getStatus();

        String status1 = null;

        String status2 = null;

        if (ScheduleConstants.Status.NORMAL.getValue().equals(status))
        {
            returnObject = resumeJob(job);
            status1 = returnObject.getStatus();
        }
        else if (ScheduleConstants.Status.PAUSE.getValue().equals(status))
        {
            returnObject = pauseJob(job);
            status2 = returnObject.getStatus();
        }
        if (status1.equals("1") && status2.equals("1")){

            returnObject.setStatus("1");
        }

        return returnObject;
    }

    /**
     * 立即运行任务
     *
     * @param job 调度信息
     */
    @Override
    @Transactional
    public ISSPReturnObject run(SysJob job) throws SchedulerException {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        String jobId = job.getJobId();
        String jobGroup = job.getJobGroup();
        returnObject = selectJobById(job.getJobId());
        SysJob properties = (SysJob) returnObject.getData();
        if (properties!=null){
            returnObject.setStatus("1");
        }
        // 参数
        JobDataMap dataMap = new JobDataMap();
        dataMap.put(ScheduleConstants.TASK_PROPERTIES, properties);
        scheduler.triggerJob(ScheduleUtils.getJobKey(jobId, jobGroup), dataMap);
        return returnObject;
    }

    /**
     * 新增任务
     *
     * @param job 调度信息 调度信息
     */
    @Override
    @Transactional
    public ISSPReturnObject insertJob(SysJob job) throws SchedulerException, TaskException {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        job.setStatus(ScheduleConstants.Status.NORMAL.getValue());
        Boolean flag = jobMapper.insertJob(job);
        if (flag)
        {
            returnObject.setStatus("1");
            ScheduleUtils.createScheduleJob(scheduler, job);
        }
        return returnObject;
    }

    /**
     * 更新任务的时间表达式
     *
     * @param job 调度信息
     */
    @Override
    @Transactional
    public ISSPReturnObject updateJob(SysJob job) throws SchedulerException, TaskException {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        returnObject = selectJobById(job.getJobId());

        SysJob properties = (SysJob)returnObject.getData();

        Boolean flag = jobMapper.updateJob(job);

        if (flag){
            returnObject.setStatus("1");
            updateSchedulerJob(job, properties.getJobGroup());
        }
        return returnObject;
    }

    /**
     * 暂停任务
     *
     * @param job 调度信息
     */
    @Override
    @Transactional
    public ISSPReturnObject pauseJob(SysJob job) throws SchedulerException {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        String jobId = job.getJobId();
        String jobGroup = job.getJobGroup();

        job.setStatus(ScheduleConstants.Status.PAUSE.getValue());

        Boolean flag = jobMapper.updateJob(job);
        if (flag)
        {
            returnObject.setStatus("1");
            scheduler.pauseJob(ScheduleUtils.getJobKey(jobId, jobGroup));
        }
        return returnObject;
    }

    /**
     * 恢复任务
     *
     * @param job 调度信息
     */
    @Override
    @Transactional
    public ISSPReturnObject resumeJob(SysJob job) throws SchedulerException
    {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        String jobId = job.getJobId();
        String jobGroup = job.getJobGroup();
        job.setStatus(ScheduleConstants.Status.NORMAL.getValue());
        Boolean flag = jobMapper.updateJob(job);
        if (flag)
        {
            returnObject.setStatus("1");
            scheduler.resumeJob(ScheduleUtils.getJobKey(jobId, jobGroup));
        }
        return returnObject;
    }

    /**
     * 更新任务
     *
     * @param job 任务对象
     * @param jobGroup 任务组名
     */
    public void updateSchedulerJob(SysJob job, String jobGroup) throws SchedulerException, TaskException
    {
        String jobId = job.getJobId();
        // 判断是否存在
        JobKey jobKey = ScheduleUtils.getJobKey(jobId, jobGroup);
        if (scheduler.checkExists(jobKey))
        {
            // 防止创建时存在数据问题 先移除，然后在执行创建操作
            scheduler.deleteJob(jobKey);
        }
        ScheduleUtils.createScheduleJob(scheduler, job);
    }

    /**
     * 校验cron表达式是否有效
     *
     * @param cronExpression 表达式
     * @return 结果
     */
    @Override
    public boolean checkCronExpressionIsValid(String cronExpression)
    {
        return CronUtils.isValid(cronExpression);
    }
}
