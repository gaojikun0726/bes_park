package com.efounder.JEnterprise.service.quartz;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESStrategy;
import com.efounder.exception.TaskException;
import org.quartz.SchedulerException;

/**
 * @author sunzhiyuan
 * @Data 2021/1/12 17:01
 */
public interface SysJobPlanTaskService {

    public ISSPReturnObject insertTimeTaskInfomation(JSONObject object) throws SchedulerException, TaskException;

    public ISSPReturnObject executeInfomation(String jobId, String planId);

    public ISSPReturnObject stopInfomation(String jobId, String planId);

    public ISSPReturnObject insertTimeTaskSyncInfomation(JSONObject object) throws SchedulerException, TaskException;

    public ISSPReturnObject executeTimeTaskSyncInfomation(String jobId, String syncId);

    public ISSPReturnObject stopTimeTaskSyncInfomation(String jobId, String syncId);

}
