package com.efounder.JEnterprise.controller.basedatamanage.eqmanage;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.core.common.util.JsonMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesSyncLog;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesTimeTaskSync;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceTypeModel;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BesTimeTaskSyncService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

/**
 * 定时同步设备树
 */
@Controller
@RequestMapping(value = "/view/basedatamanage/eqmanage/besTimeTaskSync")
public class BesTimeTakSyncController {

    private static final Logger log = LoggerFactory.getLogger(BesTimeTakSyncController.class);

    @Autowired
    BesTimeTaskSyncService besTimeTaskSyncService;


    /**
     * 初始化定时同步设备页面
     * @return
     */
    @RequestMapping(value = "initializePage", method = RequestMethod.GET)
    public String showInitPage() {
        log.info("#BesTimeTakSyncController 初始化定时同步设备页面");
        return "besview/basedatamanage/eqmanage/eqconfiguration/besTimeTaskSync";
    }
    /**
     * 查询分页
     * @return
     */
    @RequestMapping(value = "/queryPage", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getTimeTaskSyncList(@RequestParam(value = "keywords", required = false)String keywords) {
        log.info("#BesTimeTakSyncController 查询定时同步任务");
        ISSPReturnObject returnObject = besTimeTaskSyncService.queryPage(keywords);
        return returnObject;

    }

    /**
     * 查询定时任务下的点位
     * @return
     */
    @RequestMapping(value = "/querySbList", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject querySbList(@RequestBody JSONObject obj){
        log.info("#BesTimeTakSyncController 查询定时任务下的点位");
        ISSPReturnObject returnObject = besTimeTaskSyncService.querySbList(obj);
        return  returnObject;
    }

    /**
     * cron表达式生成器
     * @return
     */
    @RequestMapping(value = "/cronPage", method = RequestMethod.POST)
    public String cronPage(){
        return "/besview/basedatamanage/eqmanage/eqconfiguration/timeTaskSyncCron";
    }

    /**
     * 新增定时任务信息
     * @param object
     * @return
     */
    @RequestMapping(value = "/insertTimeTaskSync", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject insertTimeTaskSync(@RequestBody JSONObject object){
        log.info("#BesTimeTakSyncController新增定时任务信息");
        return besTimeTaskSyncService.insertTimeTaskSync(object);
    }

    /**
     * 定时同步任务 添加jobId
     * @param
     * @return
     */
    @RequestMapping(value = "/insertTimeTaskSyncJobId", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject insertTimeTaskSyncJobId(@RequestBody JSONObject object){
        log.info("#BesTimeTakSyncController定时同步任务 添加jobId");
        return besTimeTaskSyncService.insertTimeTaskSyncJobId(object);
    }

    /**
     * 获取定时任务数据
     * @param
     * @return
     */
    @RequestMapping(value = "/selectTimeTaskSyncInfo", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject selectTimeTaskSyncInfo(String syncId){
        log.info("#BesTimeTakSyncController 获取定时任务数据");
        return besTimeTaskSyncService.selectTimeTaskSyncInfo(syncId);
    }

    /**
     * 删除定时同步任务
     * @param object
     * @return
     */
    @RequestMapping(value = "/deleteTimeTaskSync", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject deleteTimeTaskSync(@RequestBody JSONObject object){
        log.info("#BesTimeTakSyncController 删除定时同步任务");
        return besTimeTaskSyncService.deleteTimeTaskSync(object);
    }

    /**
     *
     * @Description: 修改定时同步任务
     * @param:
     * @return:
     *
     */
    @RequestMapping(value = "/updateTimeTaskSync", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject updateTimeTaskSync(@RequestBody JSONObject obj) throws ParseException {
        log.info("#BesTimeTakSyncController 修改定时同步任务" );
        return besTimeTaskSyncService.updateTimeTaskSync(obj);
    }

    /**
     *
     * 筛选查询执行记录，分页查询
     */
    @RequestMapping(value = "/getSyncLogPage", method = RequestMethod.POST)
    public String getSyncLogPage(ModelMap map, Integer pageSize, Integer pageNum, BesSyncLog besSyncLog) {

        PageInfo<BesSyncLog> page = besTimeTaskSyncService.getSyncLogPage(pageSize, pageNum,besSyncLog);
        map.put("page", page);
        map.put("dataset", JsonMapper.toJsonString(page.getList()));
        map.put("pageSize", page.getPageSize() + "");
        return "besview/basedatamanage/eqmanage/eqconfiguration/syncLogPage";
    }

}
