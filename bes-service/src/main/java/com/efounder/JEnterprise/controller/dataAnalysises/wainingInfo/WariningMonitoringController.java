package com.efounder.JEnterprise.controller.dataAnalysises.wainingInfo;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.core.common.util.JsonMapper;
import com.efounder.JEnterprise.model.dataAnalysises.BesWainingInfo;
import com.efounder.JEnterprise.service.dataAnalysises.BesWainingRealService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 杨超
 * @version 创建时间：2018年12月3日 上午9:45:51
 * @parameter 报警监控
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/view/dataAnalysises/wainingInfo")
public class WariningMonitoringController {
    private static final Logger log = LoggerFactory.getLogger(WariningMonitoringController.class);

    @Autowired
    private BesWainingRealService beswainingrealservice;

    /**
     * 
     * @Title: initWarningMonitor
     * @Description:初始化报警监控页面
     * @param: @return
     * @return: String
     * @throws
     */
    @RequestMapping(value = "/initWarningMonitor", method = RequestMethod.GET)
    public String initWarningMonitor() {
        log.info("#WariningMonitoringController初始进入报警监控页面");
        return "/view/dataAnalysis/warningmonitor/warningMonitor";
    }

    /**
     * 
     * @Title: initWarningDispose   
     * @Description:初始化报警处理页面
     * @return: String      
     * @throws
     */
    @RequestMapping(value = "/initWarningDispose", method = RequestMethod.GET)
    public String initWarningDispose() {
        log.info("#WariningMonitoringController初始进入报警处理页面");
        return "/view/dataAnalysis/warningdispose/warningDispose";
    }

    /**
     * 
     * @Title: getWarningRealInfoData
     * @Description:获取报警监控信息
     * @return: String
     * @throws
     */
    @RequestMapping(value = "/getWarningRealInfoData", method = RequestMethod.POST)
    public String getWarningRealInfoData(ModelMap map, Integer pageNum, BesWainingInfo besWainingInfo) {
        log.info("#WariningMonitoringController 获取报警监控信息列表");
        PageInfo<BesWainingInfo> page = beswainingrealservice.getWarningRealInfoData(pageNum,
                besWainingInfo);
        map.put("page", page);
        map.put("ftype", besWainingInfo.getFType());
        map.put("endtime", besWainingInfo.getEndTime());
        map.put("starttime", besWainingInfo.getStartTime());
        map.put("flevel", besWainingInfo.getFLevel());
        map.put("fyqbh", besWainingInfo.getFYqbh());
        map.put("state", besWainingInfo.getFOpearation());
        map.put("dataset", JsonMapper.toJsonString(page.getList()));
        String pageN = null;
        if (String.valueOf(page.getPageNum()).indexOf(",") != -1)
            pageN = String.valueOf(page.getPageNum()).replaceAll(",", "");
        else
            pageN = String.valueOf(page.getPageNum());
        map.put("pageNum", pageN);
        return "view/dataAnalysis/warningmonitor/warningMonitor_page";
    }

    /*@RequestMapping(value = "/getWarningRealInfoData_1", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getWarningRealInfoData_1(ModelMap map, Integer pageNum, BesWainingInfo besWainingInfo) {
        ISSPReturnObject isspReturnObject = new ISSPReturnObject();
        log.info("#WariningMonitoringController 获取报警监控信息列表");
        PageInfo<BesWainingInfo> page = beswainingrealservice.getWarningRealInfoData(pageNum,
                besWainingInfo);
        map.put("page", page);
        map.put("ftype", besWainingInfo.getFType());
        map.put("endtime", besWainingInfo.getEndTime());
        map.put("starttime", besWainingInfo.getStartTime());
        map.put("flevel", besWainingInfo.getFLevel());
        map.put("fyqbh", besWainingInfo.getFYqbh());
        map.put("state", besWainingInfo.getFOpearation());
        map.put("dataset", JsonMapper.toJsonString(page.getList()));
        String pageN = null;
        if (String.valueOf(page.getPageNum()).indexOf(",") != -1)
            pageN = String.valueOf(page.getPageNum()).replaceAll(",", "");
        else
            pageN = String.valueOf(page.getPageNum());
        map.put("pageNum", pageN);
        isspReturnObject.setData(page.getList());
        return isspReturnObject;
    }*/

    /**
     * 
     * @Title: getWarningDisposeInfoData
     * @Description:报警处理分页数据
     * @return: String
     * @throws
     */
    @RequestMapping(value = "/getWarningDisposeInfoData", method = RequestMethod.POST)
    public String getWarningDisposeInfoData(ModelMap map, Integer pageNum, BesWainingInfo besWainingInfo) {
        log.info("#WariningMonitoringController 获取报警处理信息列表");
        PageInfo<BesWainingInfo> page = beswainingrealservice.getWarningRealInfoData(pageNum, besWainingInfo);
        map.put("page", page);
        map.put("ftype", besWainingInfo.getFType());
        map.put("endtime", besWainingInfo.getEndTime());
        map.put("starttime", besWainingInfo.getStartTime());
        map.put("flevel", besWainingInfo.getFLevel());
        map.put("fyqbh", besWainingInfo.getFYqbh());
        map.put("state", besWainingInfo.getFOpearation());
        map.put("dataset", JsonMapper.toJsonString(page.getList()));
        return "view/dataAnalysis/warningdispose/warningDispose_page";
    }

    /**
     * 
     * @Title: WarningDsipose
     * @Description:报警处理
     * @return: ISSPReturnObject
     * @throws
     */
    @RequestMapping(value = "/WarningDsipose", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject WarningDsipose(HttpServletRequest request) {
        ISSPReturnObject isspReturnObject = beswainingrealservice.WarningDsipose(request);
        return isspReturnObject;
    }
    /**
     *
     * @Description: 批量处理报警消息
     *
     * @auther: wanghongjie
     * @date: 11:20 2020/6/6
     * @param: [besWainingInfos]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @RequestMapping(value = "/WarningDsiposeList", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject WarningDsiposeList(@RequestBody List<BesWainingInfo> besWainingInfos) {
        System.out.println(besWainingInfos);
        ISSPReturnObject isspReturnObject = beswainingrealservice.WarningDsiposeList(besWainingInfos);
        return isspReturnObject;
    }

    /**
     *
     * @Description: 全部处理报警信息
     *
     * @auther: wanghongjie
     * @date: 10:53 2020/7/1
     * @param: [obj]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @RequestMapping(value = "/WarningDsiposeAll", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject WarningDsiposeAll(@RequestBody JSONObject obj) {
        log.info("#WariningMonitoringController  全部处理报警信息");
        ISSPReturnObject isspReturnObject = beswainingrealservice.WarningDsiposeAll(obj);
        return isspReturnObject;
    }

/**
 *
 * @Description: 点击首页告警图标，弹框获取告警未恢复详细信息
 * 
 * @auther: wanghongjie
 * @date: 11:04 2020/5/27
 * @param: 
 * @return: 
 *
 */
@RequestMapping(value = "/loadAlarmByRecoverState", method = RequestMethod.POST)
public String getAlarmWarnInfoByRecoverState(ModelMap map, Integer pageNum, Integer bars,String f_operation,String type) {
    log.info("#BESBattery_Alarm_RecordController 根据条件查询告警列表");
    List<String> zzId = new ArrayList<>();

    PageInfo<BesWainingInfo> page = beswainingrealservice.getAlarmWarnInfoByRecoverState(bars, pageNum,f_operation,type);
    ISSPReturnObject returnObject = beswainingrealservice.getNoRecoverCount(f_operation);
    map.put("warnCount",returnObject.getData());
    map.put("page", page);
    map.put("pageSize", page.getPageSize() + "");
    map.put("dataset", JsonMapper.toJsonString(page.getList()));
    map.put("type",type);
    String pageN = null;
    if (String.valueOf(page.getPageNum()).indexOf(",") != -1)
        pageN = String.valueOf(page.getPageNum()).replaceAll(",", "");
    else
        pageN = String.valueOf(page.getPageNum());
    map.put("pageNum", pageN);
    map.put("f_operation", f_operation);
    return "view/basedatamanage/warningAndAlarm/waininginfo_top_page";
}
/**
 *
 * @Description:获取当前未恢复条数信息
 *
 * @auther: wanghongjie
 * @date: 15:37 2020/5/27
 * @param:
 * @return:
 *
 */
@RequestMapping(value = "/noRecoverCount", method = RequestMethod.GET)
@ResponseBody
public ISSPReturnObject getNoRecoverCount(String f_operation) {
    log.info("# BESBattery_Alarm_RecordController 刷新当前未恢复条数信息");
    ISSPReturnObject returnObject = beswainingrealservice.getNoRecoverCount(f_operation);
    return returnObject;
}
}
