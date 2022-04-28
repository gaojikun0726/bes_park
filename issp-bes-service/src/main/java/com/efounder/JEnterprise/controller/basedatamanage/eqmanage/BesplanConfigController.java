package com.efounder.JEnterprise.controller.basedatamanage.eqmanage;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesPlanInfo;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BesplanConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.text.ParseException;

/**
 * @author sunzhiyuan
 * @Data 2020/9/27 9:56
 */
@Controller
@RequestMapping(value = "/view/basedatamanage/eqmanage")
public class BesplanConfigController {

    private static final Logger log = LoggerFactory.getLogger(BesceneConfigController.class);

    @Autowired
    BesplanConfigService besplanConfigService;

    //初始化界面
    @RequestMapping(value = "/bes_planConfig", method = RequestMethod.GET)
    public String getPlan(){
        log.info("BesplanConfigController初始化場景配置list頁面");
        return "/besview/basedatamanage/eqmanage/eqconfiguration/bes_planConfig";
    }

    @RequestMapping(value = "/cronPage", method = RequestMethod.POST)
    public String cronPage(){
        return "/besview/basedatamanage/eqmanage/eqconfiguration/cron";
    }

    /**
     * 生成计划配置树
     * @return
     */
    @RequestMapping(value = "/getPlanTree", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getPlanTree() {
        log.info("#BesplanConfigController 生成场景配置树" );
        return besplanConfigService.getPlanTree();
    }


    /**
     * 插入控制场景
     * @return
     */
    @RequestMapping(value = "/insertContPlan", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject insertContScene(BesPlanInfo besPlanInfo) {
        log.info("#BesplanConfigController插入控制场景" );
        ISSPReturnObject returnObject = besplanConfigService.insertContPlan(besPlanInfo);
        return returnObject;
    }

    /**
     * 插入控制计划
     * @return
     */
    @RequestMapping(value = "/insertCollectPlan", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject insertCollectPlan(BesPlanInfo besPlanInfo) {
        log.info("#BesplanConfigController插入采集场景" );
        ISSPReturnObject returnObject = besplanConfigService.insertCollectPlan(besPlanInfo);
        return returnObject;
    }

    /**
     * 修改场景计划
     * @return
     */
    @RequestMapping(value = "/editPlanInfo", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject editPlanInfo(BesPlanInfo besPlanInfo){
        log.info("#BesplanConfigController修改名称" );
        ISSPReturnObject returnObject = besplanConfigService.editPlanInfo(besPlanInfo);
        return returnObject;
    }

    /**
     * 删除该计划
     * @return
     */
    @RequestMapping(value = "/deletePlanInfo", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject deletePlanInfo(String id) {
        log.info("#BesplanConfigController删除" );
        ISSPReturnObject returnObject =  besplanConfigService.deletePlanInfo(id);
        return returnObject;
    }

    /**
     * 新增控制计划
     * @return
     */
    @RequestMapping(value = "/insertPlanInfo", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject insertPlanInfo(@RequestBody JSONObject obj) throws ParseException {
        log.info("#BesplanConfigController新增控制计划" );
        return besplanConfigService.insertPlanInfo(obj);
    }

    /**
     *
     * @Description: 修改控制计划
     *
     * @auther: wanghongjie
     * @date: 15:16 2021/8/17
     * @param:
     * @return:
     *
     */
    @RequestMapping(value = "/updatePlanInfo", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject updatePlanInfo(@RequestBody JSONObject obj) throws ParseException {
        log.info("#BesplanConfigController修改控制计划" );
        return besplanConfigService.updatePlanInfo(obj);
    }

    /**
     * 查询所有计划
     * @return
     */
    @RequestMapping(value = "/selectAllPlanInfo", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject selectAllPlan(){
        log.info("#BesplanConfigController查询所有计划");
        return besplanConfigService.selectAllPlan();
    }

    /**
     * 同步控制计划
     * @return
     */
    @RequestMapping(value = "/synchronization", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject synchronization(@RequestBody JSONObject obj){
        log.info("#BesplanConfigController同步控制计划");
        return besplanConfigService.synchronizationInfo(obj);
    }

    /**
     *查询所有的控制场景
     * @return
     */
    @RequestMapping(value = "/selectOptionByScene", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject selectOptionByScene(String type){
        log.info("#BesplanConfigController查询所有场景");
        ISSPReturnObject returnObject  =  besplanConfigService.selectDisrepeatSceneMode(type);
        return returnObject;
    }

    /**
     * 根据场景Id查询所有模式类型
     * @return
     */
    @RequestMapping(value = "/selectOptionByMode", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject selectOptionByMode(String f_sceneInfoId){
        log.info("#BesplanConfigController根据场景Id查询所有模式类型");
        ISSPReturnObject returnObject  =  besplanConfigService.selectOptionByMode(f_sceneInfoId);
        return returnObject;
    }

    /**
     * 根据Id查询计划信息
     * @return
     */
    @RequestMapping(value = "/selectPlanInfomation", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject selectPlanInfomation(String nodeId){
        log.info("#BesplanConfigController根据计划Id查询所有计划信息");
        ISSPReturnObject returnObject  = besplanConfigService.selectPlanInfomation(nodeId);
        return returnObject;
    }

    /**
     * 根据Id查询采集计划信息
     * @return
     */
    @RequestMapping(value = "/selectCollectPlanInfomation", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject selectCollectPlanInfomation(String nodeId){
        log.info("#BesplanConfigController根据计划Id查询所有计划信息");
        ISSPReturnObject returnObject  = besplanConfigService.selectCollectPlanInfomation(nodeId);
        return returnObject;
    }


    /**
     *查询下位机信息
     * @return
     */
    @RequestMapping(value = "/selectUnderInfomation", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject selectUnderInfomation(String fId, String planId) throws UnsupportedEncodingException, RemoteException, ServiceException {
        log.info("#BesplanConfigController查询下位机信息");
        return besplanConfigService.selectUnderInfomation(fId,planId);
    }

    /**
     *删除该条计划信息
     * @param fId
     * @return
     */
    @RequestMapping(value = "/deletePlanInfomation", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject deletePlanInfomation(String fId){
        log.info("#BesplanConfigController删除计划信息");
        return besplanConfigService.deletePlanInfomation(fId);
    }

    /**
     *删除该条计划信息
     * @param sceneId
     * @param modeID
     * @return
     */
    @RequestMapping(value = "/selectPlanInfomationByPlanId", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject selectPlanInfomationByPlanId(String sceneId, String modeID){
        log.info("#BesplanConfigController查询计划信息");
        return besplanConfigService.selectPlanInfomationByPlanId(sceneId,modeID);
    }

    /**
     * 新增定时任务信息
     * @param object
     * @return
     */
    @RequestMapping(value = "/insertTimeTaskInfomation", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject insertTimeTaskInfomation(@RequestBody JSONObject object){
        log.info("#BesplanConfigController新增计划和定时任务信息");
        return besplanConfigService.insertTimeTaskInfomation(object);
    }

    /**
     *
     * @Description: 修改定时任务信息
     *
     * @auther: wanghongjie
     * @date: 11:05 2021/7/31
     * @param: [object]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @RequestMapping(value = "/updateTimeTaskInfomation", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject updateTimeTaskInfomation(@RequestBody JSONObject object){
        log.info("#BesplanConfigController修改计划和定时任务信息");
        return besplanConfigService.updateTimeTaskInfomation(object);
    }

    /**
     * 删除采集计划信息
     * @param object
     * @return
     */
    @RequestMapping(value = "/deleteInfomation", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject deleteCollectInfomation(@RequestBody JSONObject object){
        log.info("#BesplanConfigController删除采集计划信息");
        return besplanConfigService.deleteCollectInfomation(object);
    }

    /**
     * 添加采集计划信息
     * @param
     * @return
     */
    @RequestMapping(value = "/insertTimeTaskJobId", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject insertTimeTaskJobId(@RequestBody JSONObject object){
        log.info("#BesplanConfigController添加采集计划JobId");
        return besplanConfigService.insertTimeTaskJobId(object);
    }

    /**
     * 执行采集计划信息
     * @param
     * @return
     */
    @RequestMapping(value = "/executeInfomation", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject executeInfomation(String taskId,String planId){
        log.info("#BesplanConfigController执行采集计划");
        return besplanConfigService.executeInfomation(taskId,planId);
    }

    /**
     * 停止采集计划信息
     * @param
     * @return
     */
    @RequestMapping(value = "/stopInfomation", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject stopInfomation(String taskId,String planId){
        log.info("#BesplanConfigController停止采集计划");
        return besplanConfigService.stopInfomation(taskId,planId);
    }

    /**
     * 获取定时任务数据
     * @param
     * @return
     */
    @RequestMapping(value = "/selectTimeTaskInfoByPlanId", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject selectTimeTaskInfoByPlanId(String planId){
        log.info("#BesplanConfigController获取定时任务数据");
        return besplanConfigService.selectTimeTaskInfoByPlanId(planId);
    }

}
