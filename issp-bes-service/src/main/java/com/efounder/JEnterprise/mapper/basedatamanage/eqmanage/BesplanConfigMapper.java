package com.efounder.JEnterprise.mapper.basedatamanage.eqmanage;

import com.alibaba.fastjson.JSONObject;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author sunzhiyuan
 * @Data 2020/9/29 9:38
 */
@Mapper
public interface BesplanConfigMapper {

    //获取计划树
    List<BesPlanInfo> getPlanTree();

    String queryMaxId();

    Boolean insertConplan(BesPlanInfo besPlanInfo);

    Boolean insertCollectPlan(BesPlanInfo besPlanInfo);

    Boolean editPlanInfo(BesPlanInfo besPlanInfo);

    Boolean deletePlanInfo(String id);

    List<BeSceneInfo> getSceneTreeFromPlan();

    Boolean insertPlanInfo(BesPlan besPlan);

    Boolean updatePlanInfo(BesPlan besPlan);

    String selectPlanMaxId();

    String selectTimeTaskMaxId();

    List<BesPlan> selectAllPlan();

    List<Map<String,Object>> selectAllSceneInfo(String type);

    List<Map<String,Object>> selectOptionByMode(String f_sceneInfoId);

    List<Map<String,Object>> selectPlanInfomation(String nodeId);

    List<Map<String,Object>> selectPlanCollectInfo(String nodeId);

    List<Map<String,Object>> selectTimeTaskInfo(String planId);

    BeSceneInfo selectSceneInfomation(String sceneInfoId);

    BeSceneMode selectModeInfomation(String modeInfoId);

    List<String> selectPointInfomation(String f_pointId);

    String selectSceneTypeBySceneId(String f_sceneInfoId);

    Boolean deletePlanInfomation(String fId);

    Boolean deleteTaskInfomation(String f_taskId);

    Boolean deleteQuartzSysJobInfo(String JobId);

    String selectTimeTaskJobId(String f_taskId);

    List<Map<String,Object>> selectPointValueInfo(String modeId);

    /**
     *
     * @Description: 根据点位获取照明的ip地址
     *
     * @auther: wanghongjie
     * @date: 16:20 2021/8/14
     * @param: [f_id]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     *
     */
    Map<String,Object> queryChannelByIdLDC(String f_id);

    /**
     *
     * @Description: 根据点位获取楼控的ip地址
     *
     * @auther: wanghongjie
     * @date: 16:20 2021/8/14
     * @param: [f_id]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     *
     */
    Map<String,Object> queryChannelByIdDDC(String f_id);

    String selectPlanInfomationBySceneId(String sceneId);

    String selectPlanInfomationByModeId(String modeID);

    String selectBesPlanByPlanId(String fId);

    BesTimeTask selectTimeTaskById(String taskId);

    List<Map<String,Object>> selectPointInfomationByModeId(String modeId);

    Map<String,Object> queryPointTypeByPointId(String pointId);

    Map<String,Object> queryDiPointDateList(String pointId);

    Map<String,Object> queryDoPointDateList(String pointId);

    Map<String,Object> queryAiPointDateList(String pointId);

    Map<String,Object> queryAoPointDateList(String pointId);

    Map<String,Object> queryVpointDateList(String pointId);

    Boolean updateTimeTaskInfomation(BesTimeTask besTimeTask);

    Boolean insertCollectPlanInfo(BesPlan besPlan);

    Boolean insertBesTimeTaskInfo(BesTimeTask besTimeTask);

    List<Map<String,Object>> queryVariatTaskList();

    Map<String,Object> selectModeIdAndSceneId(String planId);

    List<Map<String,Object>> queryTotalPointList();

    Boolean InsertPointVariatHistoryInfo(Map<String,Object> typePointMap);

    List<Map<String,Object>> queryToatalCollectPlan();

    Boolean insertTimeTaskJobId(@Param("jobId") String jobId,@Param("taskId") String taskId);

    Boolean executePlanInfoByPlanId(@Param("planId") String planId,@Param("f_invoke") String f_invoke);

    Boolean stopPlanInfoByPlanId(@Param("planId") String planId,@Param("f_invoke") String f_invoke);

    Map<String,Object> selectTimeTaskInfoByPlanId(String planId);

    Boolean insertPlanSynchroState(@Param("maxId") String maxId,@Param("synchro") String synchro);

    Map<String,Object> selectSchroStateById(String fId);

    /**
     *
     * @Description: 修改bes_plan表
     *
     * @auther: wanghongjie
     * @date: 14:01 2021/7/31
     * @param: [object]
     * @return: java.lang.Boolean
     *
     */
    Boolean updatePlan(@Param("object") JSONObject object);

    /**
     *
     * @Description: 修改bes_timetask表
     *
     * @auther: wanghongjie
     * @date: 14:33 2021/7/31
     * @param: [object]
     * @return: java.lang.Boolean
     *
     */
    Boolean updateTimeTask(@Param("object") JSONObject object);

    /**
     *
     * @Description: 修改定时任务调度表数据
     *
     * @auther: wanghongjie
     * @date: 14:43 2021/7/31
     * @param: [object]
     * @return: java.lang.Boolean
     *
     */
    Boolean updateSysJob(@Param("object") JSONObject object);

    /**
     *
     * @Description: 删除定时任务调度表数据
     *
     * @auther: wanghongjie
     * @date: 14:44 2021/7/31
     * @param: [object]
     * @return: java.lang.Boolean
     *
     */
    Boolean deleteSysJob(@Param("object") JSONObject object);

    /**
     *
     * @Description: 根据f_id查询定时任务调度表中是否有数据
     *
     * @auther: wanghongjie
     * @date: 9:33 2021/8/2
     * @param: [f_id]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     *
     */
    Map<String, Object> selectSysJob(String f_id);

    /**
     *
     * @Description: 查询出全部”计划配置定义“数据
     *
     * @auther: wanghongjie
     * @date: 17:55 2021/8/16
     * @param: []
     * @return: java.util.List<com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesPlan>
     *
     */
    List<BesPlan> loadPlanAll();

    /**
     *
     * @Description: 查询出全部”计划配置公式定义“数据。
     *
     * @auther: wanghongjie
     * @date: 15:24 2021/8/19
     * @param: []
     * @return: java.util.List<com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesTimeTask>
     *
     */
    List<BesTimeTask> loadTimeTaskAll();

    /**
     *
     * @Description: 删除计划公式
     *
     * @auther: wanghongjie
     * @date: 16:29 2021/8/19
     * @param: [fId]
     * @return: java.lang.Boolean
     *
     */
    Boolean deleteTaskInfomationByPId(String fId);

    /**
     *
     * @Description: 根据计划id删除sys_job定时任务调度表中数据
     *
     * @auther: wanghongjie
     * @date: 17:00 2021/8/19
     * @param: [valueOf]
     * @return: void
     *
     */
    void deleteSysJobByPId(String fId);

    /**
     *
     * @Description: 根据模式id和类型id查询所有的计划数据(类型id 1:控制计划 2:采集计划)
     *
     * @auther: wanghongjie
     * @date: 11:47 2021/8/20
     * @param: [f_modeInfoId, f_planType]
     * @return: java.util.List<com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesPlan>
     *
     */
    BesPlan selectPlanByModeId(@Param("f_modeInfoId") String f_modeInfoId,@Param("scenetype") String scenetype);

    /**
     *
     * @Description: 根据模式id查询模式点位表中的数据
     *
     * @auther: wanghongjie
     * @date: 15:06 2021/8/23
     * @param: [modeId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     *
     */
    List<Map<String, Object>> selectScenePointBymodeId(String modeId);
}
