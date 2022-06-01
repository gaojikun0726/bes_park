package com.efounder.JEnterprise.service.basedatamanage.eqmanage.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.domain.SysJob;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BesTimeTaskSyncMapper;
import com.efounder.JEnterprise.mapper.quartz.SysJobPlanMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesSyncLog;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesTimeTaskSync;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesTimeTaskSyncSb;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BesTimeTaskSyncService;
import com.efounder.constants.ScheduleConstants;
import com.efounder.util.ScheduleUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.efounder.util.ScheduleUtils.getJobKey;

/**
 * 定时同步设备树
 **/

@Service("BesTimeTaskSyncService")

public class BesTimeTaskSyncServiceImpl implements BesTimeTaskSyncService {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    BesTimeTaskSyncService besTimeTaskSyncService;

    @Autowired
    private BesTimeTaskSyncMapper besTimeTaskSyncMapper;

    @Autowired
    SysJobPlanMapper sysJobPlanMapper;

    /**
     * 查询定时同步任务
     */
    @Override
    public ISSPReturnObject queryPage(String keywords) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        List<Map<String,Object>> syncList = besTimeTaskSyncMapper.queryPage(keywords);
        returnObject.setList(syncList);
        returnObject.setStatus("1");
        return returnObject;
    }

    /**
     * 添加采集计划
     * @param object
     * @return
     */
    @Override
    public ISSPReturnObject querySbList(JSONObject object) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        String syncId = object.getString("syncId");
        List<Map<String,Object>> syncList = besTimeTaskSyncMapper.querySbList(syncId);
        if (syncList.size() > 0) {
            returnObject.setList(syncList);
            returnObject.setStatus("1");
        } else {
            returnObject.setStatus("0");
        }
        return returnObject;
    }


    /**
     * 添加采集计划
     * @param object
     * @return
     */
    @Override
    public ISSPReturnObject insertTimeTaskSync(JSONObject object) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        BesTimeTaskSync besTimeTaskSync = new BesTimeTaskSync();

        //获取最大Id
        String maxId = besTimeTaskSyncMapper.selectSyncMaxId();

        String syncId = getAutoIncreaseCol(maxId);

        besTimeTaskSync.setF_id(syncId);                 //id
        besTimeTaskSync.setF_job_name((String) object.get("f_job_name"));   //任务名称
        besTimeTaskSync.setF_cron((String) object.get("f_cron"));           //cron表达式
        besTimeTaskSync.setF_status((String) object.get("f_status"));       //状态(0未启用,1已启用)
        besTimeTaskSync.setF_remark((String) object.get("f_remark"));       //备注

        //关联的设备

        List<BesTimeTaskSyncSb> sbList = (List<BesTimeTaskSyncSb>) object.get("sbList");

        if (sbList == null || sbList.size() == 0) {
            returnObject.setStatus("0");
            returnObject.setMsg("未选择设备,请检查");
            return returnObject;
        }

        //添加到定时同步任务表
        Boolean flag = besTimeTaskSyncMapper.insertSyncInfo(besTimeTaskSync);


        if (flag){
            //添加到定时同步设备表
            this.insertSbInfo(syncId,sbList);

            returnObject.setData(syncId);
            returnObject.setStatus("1");
            returnObject.setMsg("添加定时同步任务成功");
        }else {
            returnObject.setStatus("0");
            returnObject.setMsg("添加定时同步任务失败");
        }
        return returnObject;
    }

    /**
     * 获取该列加1后的值(当前值以多个0开头时保留前面的多个0)
     * @param col 该列当前最大值
     * @return
     */
    private String getAutoIncreaseCol(String col) {
        if (col == null || "".equals(col)) {
            return "1";
        }
        String regex = "^([0]+)([\\d]*)";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(new StringBuffer(col));
        if (matcher.find()) {
            return matcher.group(1) + (Integer.parseInt(matcher.group(2)) + 1);
        } else {
            return String.valueOf(Integer.parseInt(col) + 1);
        }
    }

    /**
     * 同步任务关联设备
     * @param sbList 设备集合
     * @return
     */
    private void insertSbInfo(String syncId, List<BesTimeTaskSyncSb> sbList) {

        for(int i = 0;i<sbList.size();i++){
            BesTimeTaskSyncSb besTimeTaskSyncSb = JSON.parseObject(JSON.toJSONString(sbList.get(i)),BesTimeTaskSyncSb.class);
            besTimeTaskSyncSb.setF_sync_id(syncId);
            besTimeTaskSyncMapper.insertSbInfo(besTimeTaskSyncSb);
        }

        /*for (BesTimeTaskSyncSb besTimeTaskSyncSb : sbList){
            besTimeTaskSyncSb.setF_sync_id(syncId);
            besTimeTaskSyncMapper.insertSbInfo(besTimeTaskSyncSb);
        }*/
    }

    /**
     * 定时同步任务 添加JobId
     * @param object
     * @return
     */
    @Override
    public ISSPReturnObject insertTimeTaskSyncJobId(JSONObject object) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        String jobId = (String) object.get("jobId");

        String syncId = (String) object.get("syncId");

        Boolean flag = besTimeTaskSyncMapper.insertTimeTaskSyncJobId(jobId,syncId);

        if (flag){
            returnObject.setStatus("1");
        }else {
            returnObject.setStatus("0");
        }
        return returnObject;
    }

    /**
     * 获取定时任务数据
     * @param syncId
     * @return
     */
    public ISSPReturnObject selectTimeTaskSyncInfo(String syncId) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        Map<String,Object> timeTaskSyncSbInfo = besTimeTaskSyncMapper.selectTimeTaskSyncInfo(syncId);
        if (timeTaskSyncSbInfo != null) {
            //设备列表
            List<Map<String,Object>> sbList = besTimeTaskSyncMapper.querySbList(syncId);
            timeTaskSyncSbInfo.put("sbList",sbList);
            returnObject.setStatus("1");
            returnObject.setMap(timeTaskSyncSbInfo);
        } else {
            returnObject.setStatus("0");
        }
        return returnObject;
    }

    /**
     * 删除定时任务
     * @param object
     * @return
     */
    @Override
    public ISSPReturnObject deleteTimeTaskSync(JSONObject object) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        Map<String,Object> dataMap = (Map<String, Object>) object.get("data");
        String syncId = (String) dataMap.get("f_id") ;
        //删除任务
        Boolean flag = besTimeTaskSyncMapper.deleteSyncInfo(syncId);
        if (flag){
            //删除该任务下的设备
            besTimeTaskSyncMapper.deleteSyncSbList(syncId);

            //查询定时任务调度表中是否有数据
            String invokeTarget = "besTask.executeTimeTaskSyncInfo";
            Map<String,Object> jobInfo = besTimeTaskSyncMapper.selectSysJobInfo(syncId,invokeTarget);
            if (jobInfo != null){
                returnObject = this.deleteJob(jobInfo);
            } else {
                returnObject.setStatus("1");
            }
        }else {
            returnObject.setStatus("0");
        }
        return returnObject;
    }

    /**
     *
     * @Description: 删除定时任务
     *
     * @param: [map]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    public ISSPReturnObject deleteJob(Map<String,Object> map){

        ISSPReturnObject returnObject = new ISSPReturnObject();

        String jobId = (String) map.get("job_id");

        String jobGroup = (String) map.get("job_group");

        //删除定时任务
        try {
            scheduler.deleteJob(getJobKey(jobId, jobGroup));
            besTimeTaskSyncMapper.deleteJobInfo(jobId);
            returnObject.setStatus("1");
        } catch (SchedulerException e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }
        return returnObject;
    }

    /**
     *
     * @Description: 修改定时任务
     *
     * @param: [object]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @Transactional
    @Override
    public ISSPReturnObject updateTimeTaskSync(JSONObject object) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        ISSPReturnObject returnObj = new ISSPReturnObject();

        Map<String,Object> map = new HashMap<>();
        Boolean SysJobMessage;//定时任务调度表中信息是否存在

        String syncId = object.getString("f_id");//任务ID
        String invokeTarget = "besTask.executeTimeTaskSyncInfo";  //定时任务调用目标

        //查询定时任务调度表中是否有数据
        Map<String,Object> jobInfo = besTimeTaskSyncMapper.selectSysJobInfo(syncId,invokeTarget);
        if(jobInfo == null) {//当前所修改的在定时任务调度表中不存在
            SysJobMessage = false;
        } else {//当前所修改的在定时任务调度表中存在
            SysJobMessage = true;
        }


        try {
            //修改bes_time_task_sync表数据
            BesTimeTaskSync besTimeTaskSync = new BesTimeTaskSync();
            besTimeTaskSync.setF_id(syncId);
            besTimeTaskSync.setF_job_name(object.getString("f_job_name"));
            besTimeTaskSync.setF_remark(object.getString("f_remark"));
            besTimeTaskSync.setF_cron(object.getString("f_cron"));
            Boolean bes_time_task_sync = besTimeTaskSyncMapper.updateTimeTaskSync(besTimeTaskSync);
            if (!bes_time_task_sync) {
                returnObject.setMsg("修改失败");
                returnObject.setStatus("0");
                return returnObject;
            } else {
                //先删除关联的点位,再重新添加
                besTimeTaskSyncMapper.deleteSyncSbList(syncId);
                List<BesTimeTaskSyncSb> sbList = (List<BesTimeTaskSyncSb>) object.get("sbList");
                this.insertSbInfo(syncId,sbList);
            }


            SysJob sysJob = new SysJob();
            String f_id_new = UUIDUtil.getRandom32BeginTimePK();
            sysJob.setJobId(f_id_new);
            sysJob.setSyncId(syncId);
            sysJob.setJobName(object.getString("f_job_name"));
            sysJob.setJobGroup("DEFAULT");
            sysJob.setInvokeTarget("besTask.executeTimeTaskSyncInfo");
            sysJob.setCronExpression(object.getString("f_cron"));
            sysJob.setMisfirePolicy("3");
            sysJob.setConcurrent("0");
            sysJob.setCreateBy("admin");
            sysJob.setUpdateBy("admin");
            sysJob.setStatus(ScheduleConstants.Status.PAUSE.getValue());

            object.put("f_job_id",f_id_new);

            String jobId = f_id_new;

            if (SysJobMessage) {//定时任务调度表中是否存在,存在的话,修改定时任务调度
                //sys_job 修改定时任务调度表数据
                besTimeTaskSyncMapper.updateSysJobInfo(object);
                besTimeTaskSyncMapper.insertTimeTaskSyncJobId(jobId,syncId);
                map.put("job_id",jobInfo.get("job_id"));
                map.put("job_group",jobInfo.get("job_group"));
                //删除定时任务
                returnObj = deleteJob(map);

                if ("1".equals(returnObj.getStatus())){
                    returnObject.setMsg("修改成功");
                    returnObject.setStatus("1");
                    ScheduleUtils.createScheduleJob(scheduler,sysJob);
                    return returnObject;

                }else {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    returnObject.setMsg("修改失败");
                    returnObject.setStatus("0");
                    return returnObject;
                }
            } else {//不存在的话,新增定时任务调度
                //添加定时任务

                int rows = sysJobPlanMapper.insertSysJobSyncInfo(sysJob);

                if (rows > 0){
                    returnObject.setMsg("修改成功");
                    returnObject.setStatus("1");
                    ScheduleUtils.createScheduleJob(scheduler,sysJob);
                    return returnObject;

                }else {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    returnObject.setMsg("修改失败");
                    returnObject.setStatus("0");
                    return returnObject;
                }
            }

        } catch (Exception e) {

        }
        return returnObject;
    }

    /**
     * 查询分页数据
     * @param pageSize
     * @param pageNum
     * @param besSyncLog
     * @return
     */
    @Override
    public PageInfo<BesSyncLog> getSyncLogPage(Integer pageSize, Integer pageNum,BesSyncLog besSyncLog) {
        if (pageNum == null){
            pageNum = 1;
        }

        if (pageSize == null) {
            pageSize = Constants.PAGE_SIZE;
        }

        PageHelper.startPage(pageNum, pageSize);


        // 紧跟着的第一个select方法会被分页
        List<BesSyncLog> list = besTimeTaskSyncMapper.getSyncLogPage(besSyncLog);
        // 用PageInfo对结果进行包装
        PageInfo<BesSyncLog> page = new PageInfo<>(list);
        return page;

    }
}
