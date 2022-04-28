package com.efounder.JEnterprise.mapper.quartz;

import com.efounder.JEnterprise.domain.SysJob;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* 调度任务信息 数据层
* @author sunzhiyuan
*/
@Mapper
public interface SysJobMapper
{
    /**
     * 查询调度任务日志集合
     * @param job 调度信息
     * @return 操作日志集合
     * @param job
     * @return
     */
    public List<SysJob> selectJobList(SysJob job);

    /**
     * 查询所有调度任务
     * @return 调度任务列表
     */
    public List<SysJob> selectJobAll();

    /**
     *通过调度ID查询调度任务信息
     * @param jobId 调度ID
     * @return 角色对象信息
     */
    public SysJob selectJobById(String jobId);

    /**
     * 通过调度ID删除调度任务信息
     * @param jobId 调度ID
     * @return 结果
     */
    Boolean deleteJobById(String jobId);

    /**
     * 批量删除调度任务信息
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteJobByIds(String[] ids);

    /**
     * 修改调度任务信息
     * @param job 调度任务信息
     * @return 结果
     */
    public Boolean updateJob(SysJob job);

    /**
     * 新增调度任务信息
     * @param job 调度任务信息
     * @return 结果
     */
    public Boolean insertJob(SysJob job);
}

