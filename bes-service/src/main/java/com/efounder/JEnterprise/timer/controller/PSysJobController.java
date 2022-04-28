package com.efounder.JEnterprise.timer.controller;

import com.alibaba.fastjson.JSONObject;
import com.efounder.JEnterprise.domain.SysJob;
import com.efounder.JEnterprise.platform.util.ConstantValue;
import com.efounder.JEnterprise.timer.manage.PduStatusManage;
import com.efounder.JEnterprise.timer.service.PSysJobService;
import com.efounder.exception.TaskException;
import com.efounder.util.CronUtils;
import com.efounder.util.StringUtils;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * describe: 定时任务管理
 *
 * @author zs
 * @date 2021/12/9
 */
@Controller
@RequestMapping("/view/timer/manage")
public class PSysJobController {

    /**
     * 返回页面路径前缀
     */
    private final String prefix = "/view/timer/manage";


    @Resource
    private PSysJobService sysJobService;

    @Resource
    private PduStatusManage pduStatusManage;


    /**
     * 定时任务管理页面
     * @return
     */
    @RequestMapping("/pageView")
    public String view(){
        return prefix + "/timerManage";
    }


    /**
     * 查询定时任务列表
     * @param request
     * @return
     */
    @RequestMapping("/queryList")
    @ResponseBody
    public Map queryList(HttpServletRequest request){
        //任务名称
        String taskName = request.getParameter("taskName");
        //任务类型
        String taskType = request.getParameter("taskType");
        //任务状态
        String taskStatus = request.getParameter("taskStatus");

        //排序字段
        String field = request.getParameter("field");
        //排序顺序
        String order = request.getParameter("order");

        String pageNumber = request.getParameter("pageNumber");
        String pageSize = request.getParameter("pageSize");
        if(StringUtils.isEmpty(pageNumber)){
            pageNumber = String.valueOf(ConstantValue.PAGE_NUM);
        }
        if(StringUtils.isEmpty(pageSize)){
            pageSize = String.valueOf(ConstantValue.PAGE_SIZE);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("taskName",taskName);
        map.put("taskType",taskType);
        map.put("taskStatus",taskStatus);
        map.put("field",field);
        map.put("order",order);
        return sysJobService.queryList(map,Integer.valueOf(pageNumber),Integer.valueOf(pageSize));
    }


    /**
     * 校验cron表达式是否有效
     */
    @PostMapping("/checkCronExpressionIsValid")
    @ResponseBody
    public boolean checkCronExpressionIsValid(SysJob job)
    {
        return sysJobService.checkCronExpressionIsValid(job.getCronExpression());
    }


    /**
     * 新增保存调度
     */
    @PostMapping("/add")
    @ResponseBody
    public JSONObject addSave(@Validated SysJob job) throws SchedulerException, TaskException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",false);
        String msg = "";
        if (!CronUtils.isValid(job.getCronExpression())) {
            msg = "新增任务'" + job.getJobName() + "'失败，Cron表达式不正确";
            jsonObject.put("msg",msg);
            return jsonObject;
        }
//        else if (StringUtils.containsIgnoreCase(job.getInvokeTarget(), Constants.LOOKUP_RMI)) {
//            msg = "新增任务'" + job.getJobName() + "'失败，目标字符串不允许'rmi://'调用";
//            jsonObject.put("msg",msg);
//            return jsonObject;
//        } else if (StringUtils.containsIgnoreCase(job.getInvokeTarget(), Constants.LOOKUP_LDAP)) {
//            msg = "新增任务'" + job.getJobName() + "'失败，目标字符串不允许'ldap://'调用";
//            jsonObject.put("msg",msg);
//            return jsonObject;
//        } else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), new String[] { Constants.HTTP, Constants.HTTPS })) {
//            msg = "新增任务'" + job.getJobName() + "'失败，目标字符串不允许'http(s)//'调用";
//            jsonObject.put("msg",msg);
//            return jsonObject;
//        } else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), Constants.JOB_ERROR_STR)) {
//            msg = "新增任务'" + job.getJobName() + "'失败，目标字符串存在违规";
//            jsonObject.put("msg",msg);
//            return jsonObject;
//        }

//        job.setInvokeTarget(job.getJobGroup());
//        job.setCreateBy(getLoginName());
        String jobId = job.getJobId();

        if(StringUtils.isEmpty(jobId)){
            sysJobService.insertJob(job);
        }else{
            sysJobService.updateJob(job);
        }

        //更新计划任务缓存
        pduStatusManage.initData();

        jsonObject.put("result",true);
        return jsonObject;
    }

    /**
     * 删除任务
     * @param ids 任务编号
     * @return
     * @throws SchedulerException
     */
    @PostMapping("/delete")
    @ResponseBody
    public JSONObject delete(@RequestBody String[] ids) throws SchedulerException {
        JSONObject jsonObject = new JSONObject();
        sysJobService.deleteJobByIds(ids);

        //更新计划任务缓存
        pduStatusManage.initData();

        jsonObject.put("result",true);
        return jsonObject;
    }


    /**
     * 根据jobId查询任务信息
     * @param jobId jobId
     * @return
     */
    @RequestMapping("/getJobInfoById")
    @ResponseBody
    public SysJob getJobInfoById(String jobId){
        return sysJobService.selectJobById(jobId);
    }


    /**
     * 任务调度状态修改
     */
    @PostMapping("/changeStatus")
    @ResponseBody
    public JSONObject changeStatus(SysJob job) throws SchedulerException
    {
        SysJob newJob = sysJobService.selectJobById(job.getJobId());
        newJob.setStatus(job.getStatus());
        int num = sysJobService.changeStatus(newJob);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",true);
        return jsonObject;
    }

    /**
     * 任务调度立即执行一次
     */
    @PostMapping("/run")
    @ResponseBody
    public JSONObject run(SysJob job) throws SchedulerException
    {
        sysJobService.run(job);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",true);
        return jsonObject;
    }

    /**
     * 查询cron表达式近5次的执行时间
     */
    @GetMapping("/queryCronExpression")
    @ResponseBody
    public JSONObject queryCronExpression(@RequestParam(value = "cronExpression", required = false) String cronExpression) {
        JSONObject jsonObject = new JSONObject();
        if (sysJobService.checkCronExpressionIsValid(cronExpression)) {
            List<String> dateList = CronUtils.getRecentTriggerTime(cronExpression);
            jsonObject.put("data",dateList);
            jsonObject.put("result",true);
        } else {
            jsonObject.put("msg","表达式无效");
            jsonObject.put("result",false);
        }
        return jsonObject;
    }

}
