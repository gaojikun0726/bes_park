package com.efounder.JEnterprise.commhandler;

import com.core.ApplicationContextProvider;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.initializer.PlanCache;
import com.efounder.JEnterprise.initializer.SbPzStructCache;
import com.efounder.JEnterprise.initializer.ScenePointCache;
import com.efounder.JEnterprise.initializer.TimeTaskCache;
import com.efounder.JEnterprise.mapper.dataAnalysises.BesWainingRealInfoMapper;
import com.efounder.JEnterprise.mapper.quartz.SysJobPlanMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESSbPzStruct;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BeScenePoint;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesPlan;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesTimeTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wanghongjie
 * @Description:计划编排数据保存类
 * @Date: Created in 15:05 2021/8/19
 * @Modified By:
 */
public class PlanningHandler {
    private static final Logger log = LoggerFactory.getLogger(PlanningHandler.class);

    private SysJobPlanMapper sysJobPlanMapper = ApplicationContextProvider.getBean(SysJobPlanMapper.class);

    //设备树配置定义缓存
    private static SbPzStructCache sbPzStructCache = ApplicationContextProvider.getBean(SbPzStructCache.class);

    //模块点位配置定义缓存
    private static ScenePointCache scenePointCache = ApplicationContextProvider.getBean(ScenePointCache.class);

    //计划配置定义缓存
    private static PlanCache planCache = ApplicationContextProvider.getBean(PlanCache.class);

    //计划公式配置定义缓存
    private static TimeTaskCache timeTaskCache = ApplicationContextProvider.getBean(TimeTaskCache.class);

    public void planingHandler(Integer id, String values) {

        Map<String,Object> typeDiPointMap = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String F_CRDATE = simpleDateFormat.format(new Date());

        //根据点位id获取模式信息
        List<BeScenePoint>  beScenePointList = scenePointCache.getCachedElementByPointId(String.valueOf(id));
        if (beScenePointList == null) {
            return;
        }

        for (BeScenePoint beScenePoint : beScenePointList) {
            //获取模式id
            String modeId = beScenePoint.getF_scenemodeId();
            //根据模式id获取计划信息
            List<BesPlan>  besPlanList = planCache.getCachedElementByModeId(modeId);
            if (besPlanList == null) {
                continue;
            }
            for (BesPlan besPlan : besPlanList) {


                if ("0".equals(besPlan.getF_invoke())) {//当计划设置不执行的时候,直接跳出当前循环
                    continue;
                }
                //获取计划id
                String planId = besPlan.getF_id();
                //根据计划id查询出计划所关联的公式信息
                BesTimeTask besTimeTask = timeTaskCache.getCachedElementByPId(planId);

                if (besTimeTask == null || "1".equals(besTimeTask.getF_tasktype())) {//当计划类型是"1:时间"的时候,跳出本次循环,0的时候是变化量
                    continue;
                }

                String taskId = besTimeTask.getF_id();
                String timename = besTimeTask.getF_timename();
                int f_specificvalue = Integer.valueOf(besTimeTask.getF_specificvalue());
                //获取设备树当前点位的值
                BESSbPzStruct besSbPzStruct =  sbPzStructCache.getCachedElementById(String.valueOf(id));

                String init_val = besSbPzStruct.getF_init_val();

                int val = Integer.valueOf(init_val) - Integer.valueOf(values);

                val = Math.abs(val);

                if (val >= f_specificvalue) {

                    String F_ID = UUIDUtil.getRandom32BeginTimePK();

                    typeDiPointMap.put("F_CRDATE",F_CRDATE);//创建时间

                    typeDiPointMap.put("F_PLANID",planId);//计划id

                    typeDiPointMap.put("F_TASKID",taskId);//计划公式id

                    typeDiPointMap.put("F_TIMENAME",timename);//定时任务名称

                    typeDiPointMap.put("F_GUID","0");

                    typeDiPointMap.put("F_STRUCT_ID",String.valueOf(id));//点位id

                    typeDiPointMap.put("F_SYS_NAME_OLD",besSbPzStruct.getF_sys_name_old());//点位系统名称(往下位机发送的名称)

                    typeDiPointMap.put("F_SYS_NAME",besSbPzStruct.getF_sys_name());//点位名称(系统自动生成的名称)

                    typeDiPointMap.put("F_INIT_VAL",values);//值

                    typeDiPointMap.put("F_ID",F_ID);//uuid

                    sysJobPlanMapper.InsertPointHistoryInfo(typeDiPointMap);
                }
            }
        }
    }

}
