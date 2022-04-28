package com.efounder.JEnterprise.controller.basedatamanage.eqmanage;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BeSceneInfo;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BesceneConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author sunzhiyuan
 * @Date:2020/8/31 11:23
 */
@Controller
@RequestMapping(value = "/view/basedatamanage/eqmanage")
public class BesceneConfigController {

    private static final Logger log = LoggerFactory.getLogger(BesceneConfigController.class);

    @Autowired
    BesceneConfigService besceneConfigService;
    //初始化界面
    @RequestMapping(value = "/bes_sceneConfig", method = RequestMethod.GET)
    public String getScene(){
        log.info("BesceneConfigController初始化場景配置list頁面");
        return "/besview/basedatamanage/eqmanage/eqconfiguration/bes_sceneCofig";
    }

    /**
     * 生成场景配置树
     * @return
     */
    @RequestMapping(value = "/getSceneTree", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getSceneTree() {
        log.info("# BESCjpzController 生成场景配置树" );
        return besceneConfigService.getSceneTree();
    }

    /**
     * 插入控制场景
     * @return
     */
    @RequestMapping(value = "/insertContScene", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject insertContScene(BeSceneInfo beSceneInfo) {
        log.info("#BesceneConfigController插入控制场景" );
        ISSPReturnObject returnObject =  besceneConfigService.insertContScene(beSceneInfo);
        return returnObject;
    }

    /**
     * 插入采集场景
     * @return
     */
    @RequestMapping(value = "/insertCollScene", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject insertCollScene(BeSceneInfo beSceneInfo) {
        log.info("#BesceneConfigController插入采集场景" );
        ISSPReturnObject returnObject =  besceneConfigService.insertCollScene(beSceneInfo);
        return returnObject;
    }

    /**
     * 修改场景名称
     * @return
     */
    @RequestMapping(value = "/editSceneInfo", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject editSceneInfo(BeSceneInfo beSceneInfo) {
        log.info("#BesceneConfigController修改场景名称" );
        ISSPReturnObject returnObject =  besceneConfigService.editSceneInfo(beSceneInfo);
        return returnObject;
    }

    /**
     * 删除该场景
     * @return
     */
    @RequestMapping(value = "/deleteSceneInfo", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject deleteSceneInfo(String id) {
        log.info("#BesceneConfigController删除场景名称" );
        ISSPReturnObject returnObject =  besceneConfigService.deleteSceneInfo(id);
        return returnObject;
    }

    /**
     * 查询该场景下的指令信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryTableParam", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject queryTableParam(String id){
        log.info("#BesceneConfigController查询当前场景下的指令信息");
        ISSPReturnObject returnObject = besceneConfigService.queryTableParam(id);
        return returnObject;
    }

    /**
     * 新增点位信息
     * @param obj
     * @return
     */
    @RequestMapping(value = "/insertSceneModeAndScene", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject insertSceneModeAndScene(@RequestBody JSONObject obj){
        log.info("#BesceneConfigController新增点位信息");
        ISSPReturnObject returnObject = besceneConfigService.insertScenePoint(obj);
        return returnObject;
    }

    /**
     * 查询该模式下的值和单位
     * @param
     * @return
     */
    @RequestMapping(value = "/getValueAndUnitById", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getValueAndUnitById(@RequestBody JSONObject obj){
        log.info("#BesceneConfigController查询该模式下的值和单位");
        ISSPReturnObject returnObject = besceneConfigService.selectValueAndUnitById(obj);
        return  returnObject;
    }


    /**控制场景
     * 新增该模式下的值和单位
     * @param
     * @return
     */
    @RequestMapping(value = "/insertPointValueUnitAndIssue", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject insertPointValueUnitAndIssue(@RequestBody JSONObject obj){
       log.info("#BesceneConfigController新增该模式下的值和单位");
       ISSPReturnObject returnObject = besceneConfigService.insertPointValueUnitAndIssue(obj);
        return  returnObject;
    }

    /**采集场景
     * 新增该模式下的值和单位
     * @param
     * @return
     */
    @RequestMapping(value = "/insertCollectPointValueUnitAndIssue", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject insertCollectPointValueUnitAndIssue(@RequestBody JSONObject obj){
        log.info("#BesceneConfigController新增模式下的值和单位");
        ISSPReturnObject returnObject = besceneConfigService.insertCollectPointValueUnitAndIssue(obj);
        return returnObject;
    }


    /**
     * 新增场景内的其他信息
     * @param obj
     * @return
     */
    @RequestMapping(value = "/saveSceneInfo", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject saveSceneInfo(@RequestBody JSONObject obj){
        log.info("#BesceneConfigController新增场景内的其他信息");
        ISSPReturnObject returnObject = besceneConfigService.saveSceneInfomation(obj);
        return returnObject;
    }

    /**
     * 新增场景内的其他信息
     * @param sceneId
     * @return
     */
    @RequestMapping(value = "/selectModeInfoBySceneId", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject selectModeInfoBySceneId(String sceneId){
        log.info("#BesceneConfigController新增场景内的其他信息");
        return besceneConfigService.selectModeInfoBySceneId(sceneId);
    }

    /**
     * 删除模式数据
     * @param modeId
     * @return
     */
    @RequestMapping(value = "/deletModeInfomation", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject deletModeInfomation(String modeId, String sceneId){
        log.info("#BesceneConfigController删除模式数据");
        return besceneConfigService.deleteUnderModeInfo(modeId,sceneId);
    }


    /**
     * 查询上位机数据
     * @param modeId
     * @return
     */
    @RequestMapping(value = "/queryContrastLocalInfo", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject queryContrastLocalInfo(String modeId){
        log.info("#BesceneConfigController查询上位机数据");
        return besceneConfigService.queryContrastLocalInfo(modeId);
    }

    /**
     * 查询下位机数据
     * @param modeId
     * @return
     */
    @RequestMapping(value = "/queryContrastUnderInfo", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject queryContrastUnderInfo(String modeId, String sceneId, String type){
        log.info("#BesceneConfigController查询下位机数据");
        return besceneConfigService.queryContrastUnderInfo(modeId,sceneId,type);
    }

    /**
     * 同步控制场景内模式的信息
     * @param obj
     * @return
     */
    @RequestMapping(value = "/synchroPointValueUnitAndIssue", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject synchroPointValueUnitAndIssue(@RequestBody JSONObject obj){
        log.info("#BesceneConfigController同步控制场景内模式的信息");
        return besceneConfigService.synchroPointValueUnitAndIssue(obj);
    }


    /**
     * 删除场景模式
     * @param modeId
     * @return
     */
    @RequestMapping(value = "/deletCollectModeInfomation", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject deletCollectModeInfomation(String modeId, String sceneId){
        log.info("#BesceneConfigController删除采集场景下模式的数据");
        return  besceneConfigService.deletCollectModeInfomation(modeId,sceneId);
    }
    /**
     *
     * @Description: 添加场景模式下指令
     *
     * @auther: wanghongjie
     * @date: 9:05 2021/8/12
     * @param:
     * @return:
     *
     */
    @RequestMapping(value = "/addCollectMode", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject addCollectMode(@RequestBody JSONObject obj){
        log.info("#BesceneConfigController添加场景模式下指令");
        return  besceneConfigService.addCollectMode(obj);
    }

    /**
     *
     * @Description: 修改场景模式下指令
     *
     * @auther: wanghongjie
     * @date: 11:48 2021/8/9
     * @param: [modeId, sceneId]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @RequestMapping(value = "/updateCollectMode", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject updateCollectMode(@RequestBody JSONObject obj){
        log.info("#BesceneConfigController修改场景模式下指令");
        return  besceneConfigService.updateCollectMode(obj);
    }

}
