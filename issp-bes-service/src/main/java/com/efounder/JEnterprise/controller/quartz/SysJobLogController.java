package com.efounder.JEnterprise.controller.quartz;

import com.efounder.JEnterprise.service.quartz.ISysJobLogService;
import com.efounder.constants.ConstantValue;
import com.efounder.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * describe: 定时任务执行日志
 *
 * @author zs
 * @date 2021/12/9
 */
@Controller
@RequestMapping("/view/timer/log")
public class SysJobLogController
{
    /**
     * 返回页面路径前缀
     */
    private final String prefix = "/view/timer/manage";


    @Resource
    private ISysJobLogService sysJobLogService;

    /**
     * 定时任务日志页面
     * @return
     */
    @RequestMapping("/pageView")
    public String view(){
        return prefix + "/timerLog";
    }


    /**
     * 查询定时任务日志列表
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
        //开始时间
        String startTime = request.getParameter("startTime");
        //结束时间
        String endTime = request.getParameter("endTime");

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
        map.put("jobName",taskName);
        map.put("jobGroup",taskType);
        map.put("status",taskStatus);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("field",field);
        map.put("order",order);
        return sysJobLogService.queryList(map,Integer.valueOf(pageNumber),Integer.valueOf(pageSize));
    }
}
