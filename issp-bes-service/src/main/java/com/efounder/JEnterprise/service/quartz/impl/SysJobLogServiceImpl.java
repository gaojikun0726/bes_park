package com.efounder.JEnterprise.service.quartz.impl;

import com.efounder.JEnterprise.domain.SysJobLog;
import com.efounder.JEnterprise.mapper.quartz.SysJobLogMapper;
import com.efounder.JEnterprise.service.quartz.ISysJobLogService;
import com.efounder.util.config.Convert;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定时任务调度日志信息 服务层
 *
 * @author sunzhiyuan
 */
@Service("ISysJobLogService")
public class SysJobLogServiceImpl implements ISysJobLogService
{
    @Resource
    private SysJobLogMapper jobLogMapper;

    /**
     * 获取quartz调度器日志的计划任务
     *
     * @param jobLog 调度日志信息
     * @return 调度任务日志集合
     */
    @Override
    public List<SysJobLog> selectJobLogList(SysJobLog jobLog)
    {
        return jobLogMapper.selectJobLogList(jobLog);
    }

    /**
     * 通过调度任务日志ID查询调度信息
     *
     * @param jobLogId 调度任务日志ID
     * @return 调度任务日志对象信息
     */
    @Override
    public SysJobLog selectJobLogById(Long jobLogId)
    {
        return jobLogMapper.selectJobLogById(jobLogId);
    }

    /**
     * 新增任务日志
     *
     * @param jobLog 调度日志信息
     */
    @Override
    public void addJobLog(SysJobLog jobLog)
    {
        jobLogMapper.insertJobLog(jobLog);
    }

    /**
     * 批量删除调度日志信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteJobLogByIds(String ids)
    {
        return jobLogMapper.deleteJobLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除任务日志
     *
     * @param jobId 调度日志ID
     */
    @Override
    public int deleteJobLogById(Long jobId)
    {
        return jobLogMapper.deleteJobLogById(jobId);
    }

    /**
     * 清空任务日志
     */
    @Override
    public void cleanJobLog()
    {
        jobLogMapper.cleanJobLog();
    }


    /**
     * 查询列表
     *
     * @param map        参数
     * @param pageNumber 页码
     * @param pageSize   页大小
     * @return
     */
    @Override
    public Map queryList(Map<String, Object> map, Integer pageNumber, Integer pageSize) {
        Map<String,Object> result = new HashMap<>(2);

        PageHelper.startPage(pageNumber,pageSize);
        List<SysJobLog> list = jobLogMapper.queryList(map);
        PageInfo<SysJobLog> page = new PageInfo<>(list);

        result.put("rows",list);
        result.put("total",page.getTotal());
        result.put("code",0);
        result.put("data",list);
        result.put("count",page.getTotal());
        return result;
    }
}
