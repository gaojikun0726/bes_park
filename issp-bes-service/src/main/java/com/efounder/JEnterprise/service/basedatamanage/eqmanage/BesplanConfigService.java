package com.efounder.JEnterprise.service.basedatamanage.eqmanage;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesPlanInfo;

import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.text.ParseException;

/**
 * @author sunzhiyuan
 * @Data 2020/9/29 9:33
 */
public interface BesplanConfigService {

    ISSPReturnObject getPlanTree();

    ISSPReturnObject insertContPlan(BesPlanInfo besPlanInfo);

    ISSPReturnObject insertCollectPlan(BesPlanInfo besPlanInfo);

    ISSPReturnObject editPlanInfo(BesPlanInfo besPlanInfo);

    ISSPReturnObject deletePlanInfo(String id);

    /**
     * 获取场景树
     * @return
     */
    ISSPReturnObject getSceneTreeFromPlan();

    /**
     *
     * @Description: 新增控制计划
     *
     * @auther: wanghongjie
     * @date: 15:17 2021/8/17
     * @param: [object]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject insertPlanInfo(JSONObject object) throws ParseException;

    /**
     *
     * @Description:修改控制计划
     *
     * @auther: wanghongjie
     * @date: 15:17 2021/8/17
     * @param: []
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject updatePlanInfo(JSONObject object) throws ParseException;

    ISSPReturnObject selectAllPlan();

    /**
     *
     * @Description: 同步控制计划
     *
     * @auther: wanghongjie
     * @date: 15:38 2021/8/17
     * @param: [object]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject synchronizationInfo(JSONObject object);

    ISSPReturnObject selectDisrepeatSceneMode(String type);

    ISSPReturnObject selectOptionByMode(String f_sceneInfoId);

    ISSPReturnObject selectPlanInfomation(String nodeId);

    ISSPReturnObject selectCollectPlanInfomation(String nodeId);

    ISSPReturnObject selectUnderInfomation(String fId, String planId) throws UnsupportedEncodingException, RemoteException, ServiceException;

    ISSPReturnObject deletePlanInfomation(String fId);

    ISSPReturnObject selectPlanInfomationByPlanId(String sceneId, String modeID);

    ISSPReturnObject insertTimeTaskInfomation(JSONObject object);

    ISSPReturnObject deleteCollectInfomation(JSONObject object);

    ISSPReturnObject insertTimeTaskJobId(JSONObject object);

    ISSPReturnObject executeInfomation(String taskId, String planId);

    ISSPReturnObject stopInfomation(String taskId, String planId);

    ISSPReturnObject selectTimeTaskInfoByPlanId(String planId);

    /**
     *
     * @Description: 修改定时任务信息
     *
     * @auther: wanghongjie
     * @date: 11:07 2021/7/31
     * @param: [object]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject updateTimeTaskInfomation(JSONObject object);
}
