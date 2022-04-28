package com.efounder.JEnterprise.mapper.quartz;


import com.efounder.JEnterprise.domain.SysJob;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author sunzhiyuan
 * @Data 2021/1/9 10:26
 */
@Mapper
public interface SysJobPlanMapper {

    List<Map<String,Object>> selectPointInfomationByModeId(String modeId);

    Map<String,Object> selectPlanInfomation(String planId);

    Map<String,Object> selectTaskInfomation(String planId);

    Map<String,Object> queryPointTypeByPointId(String pointId);

    Map<String,Object> queryDiPointDateList(String pointId);

    Map<String,Object> queryDoPointDateList(String pointId);

    Map<String,Object> queryAiPointDateList(String pointId);

    Map<String,Object> queryAoPointDateList(String pointId);

    Map<String,Object> queryVpointDateList(String pointId);

    int insertSysJobTaskInfo(SysJob sysJob);

    Map<String,Object> queryTimeTaskList(String planId);

    List<Map<String,Object>> queryVariatTaskList();

    Map<String,Object> queryPlanModeIdAndSceneId(String planId);

    Boolean InsertPointHistoryInfo(Map<String, Object> typePointMap);

    String selectPointHistoryDataMaxId();

    Map<String,Object> getSysJobInfoById(String f_jobId);

    Boolean updateJob(@Param("status") String status, @Param("jobId") String jobId);

    Boolean updateStopPlanStatus(@Param("planId") String planId, @Param("f_invoke") String f_invoke);

    int insertSysJobSyncInfo(SysJob sysJob);

    /**
     *
     * @Description: 删除定时任务
     *
     * @auther: wanghongjie
     * @date: 8:31 2021/8/2
     * @param: [status, jobId]
     * @return: java.lang.Boolean
     *
     */
    Boolean deleteJob(String jobId);

    Map<String,Object> selectTimeTaskSyncInfomation(String syncId);

    List<Map<String,Object>> queryTimeTaskSyncSbList(String syncId);

    Boolean updateTimeTaskSyncStatus(@Param("syncId") String syncId, @Param("f_status") String f_status);

}
