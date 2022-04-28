package com.efounder.JEnterprise.controller.quartz;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.domain.SysJob;
import com.efounder.JEnterprise.service.quartz.ISysJobService;
import com.efounder.constants.TableDataInfo;
import com.efounder.exception.TaskException;
import com.efounder.util.CronUtils;
import com.efounder.util.SecurityUtils;
import com.efounder.util.core.AjaxResult;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 调度任务信息操作处理
 *
 * @author sunzhiyuan
 */
@Controller
@RequestMapping("/monitor/job")
public class SysJobController
{
    @Autowired
    private ISysJobService jobService;

    /**
     * 查询定时任务列表
     */
    @PreAuthorize("@ss.hasPermi('monitor:job:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysJob sysJob)
    {
        return null;
    }

    /**
     * 获取定时任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('monitor:job:query')")
    @GetMapping(value = "/{jobId}")
    public AjaxResult getInfo(@PathVariable("jobId") String jobId)
    {
        return AjaxResult.success(jobService.selectJobById(jobId));
    }

    /**
     * 新增定时任务
     */
    @PostMapping
    public ISSPReturnObject add(@RequestBody SysJob sysJob) throws SchedulerException, TaskException
    {
        if (!CronUtils.isValid(sysJob.getCronExpression()))
        {
            ISSPReturnObject returnObject = new ISSPReturnObject();
            returnObject.setStatus("1");
            returnObject.setMsg("cron表达式不正确");
            return returnObject;
        }
        sysJob.setCreateBy(SecurityUtils.getUsername());
        return jobService.insertJob(sysJob);
    }

    /**
     * 修改定时任务
     */
    @PutMapping
    public ISSPReturnObject edit(@RequestBody SysJob sysJob) throws SchedulerException, TaskException
    {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        if (!CronUtils.isValid(sysJob.getCronExpression()))
        {
            returnObject.setStatus("1");
            returnObject.setMsg("cron表达式不正确");
            return returnObject;
        }
        sysJob.setUpdateBy(SecurityUtils.getUsername());
        return jobService.updateJob(sysJob);
    }

    /**
     * 定时任务状态修改
     */
    @PutMapping("/changeStatus")
    public ISSPReturnObject changeStatus(@RequestBody SysJob job) throws SchedulerException
    {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        returnObject = jobService.selectJobById(job.getJobId());
        SysJob sysJob = (SysJob) returnObject.getData();
        sysJob.setStatus(sysJob.getStatus());
        return jobService.changeStatus(sysJob);
    }

    /**
     * 定时任务立即执行一次
     */
    @PutMapping("/run")
    public ISSPReturnObject run(@RequestBody SysJob job) throws SchedulerException
    {
        jobService.run(job);
        return jobService.run(job);
    }

    /**
     * 删除定时任务
     */
    @DeleteMapping("/{jobIds}")
    public ISSPReturnObject remove(@PathVariable String[] jobIds) throws SchedulerException, TaskException
    {
        return jobService.deleteJobByIds(jobIds);
    }
}
