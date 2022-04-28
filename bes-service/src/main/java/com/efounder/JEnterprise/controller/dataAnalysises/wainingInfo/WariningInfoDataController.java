package com.efounder.JEnterprise.controller.dataAnalysises.wainingInfo;

import com.core.common.ISSPReturnObject;
import com.core.common.util.JsonMapper;
import com.efounder.JEnterprise.model.dataAnalysises.BesWainingInfo;
import com.efounder.JEnterprise.service.dataAnalysises.BesWainingInfoService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * author:liuzhen
 * 报警信息统计
 * time:2018/11/29
 */
@Controller
@RequestMapping(value = "/view/dataAnalysises/wainingInfo")
public class WariningInfoDataController {
    private static final Logger log = LoggerFactory.getLogger(WariningInfoDataController.class);

    @Autowired
    private BesWainingInfoService   besWainingInfoService;
    /**
     * 初始进入报警信息查询页面
     *
     * @return
     */
    @RequestMapping(value = "/initWarningInfoData", method = RequestMethod.GET)
    public String initWarningInfoData() {
        log.info("WariningInfoDataController初始进入报警信息查询页面");
        return "/view/dataAnalysis/wainingInfo/wainingInfoData";
    }

    /**
     * 初始进入报警统计页面
     *
     * @return
     */
    @RequestMapping(value = "/initWarningInfoDataCount", method = RequestMethod.GET)
    public String initWarningInfoDataCount() {
        log.info("WariningInfoDataController初始进入报警信息统计页面");
        return "/view/dataAnalysis/wainingInfo/alarmDataCount";
    }

    /**
     * 初始进入系统报警页面
     *
     * @return
     */
    @RequestMapping(value = "/initSysWarningInfoDataCount", method = RequestMethod.GET)
    public String initSysWarningInfoDataCount() {
        log.info("WariningInfoDataController初始进入系统报警页面");
        return "/view/dataAnalysis/systemAlarm/systemWainingInfoData";
    }

    /**
     * 初始进入报警统计页面
     *
     * @return
     */
    @RequestMapping(value = "/initHbWarningInfoDataCount", method = RequestMethod.GET)
    public String initHbWarningInfoDataCount() {
        log.info("WariningInfoDataController初始进入报警信息统计页面");
        return "/view/dataAnalysis/wainingInfo/hbAlarmDataCount";
    }
    /**
     * 获取报警信息记录
     *
     * @param map
     * @param pageNum
     * @param besWainingInfo
     * @return
     */
    @RequestMapping(value = "/getWarningInfoData", method = RequestMethod.POST)
    public String getWarningInfoData(ModelMap map, Integer pageNum, BesWainingInfo besWainingInfo) {
        log.info("#WariningInfoDataController 获取报警信息列表");
        PageInfo<BesWainingInfo> page = besWainingInfoService.searchWainingInfo(pageNum,
                besWainingInfo);
        map.put("page", page);
        map.put("ftype", besWainingInfo.getFType());
        map.put("endtime", besWainingInfo.getEndTime());
        map.put("starttime", besWainingInfo.getStartTime());
        map.put("flevel", besWainingInfo.getFLevel());
        map.put("fyqbh", besWainingInfo.getFYqbh());
        map.put("state", besWainingInfo.getFOpearation());
        map.put("dataset", JsonMapper.toJsonString(page.getList()));
        return "view/dataAnalysis/wainingInfo/wainingInfoData_page";
    }
    /**
     * 获取系统报警信息记录
     *
     * @param map
     * @param pageNum
     * @param besWainingInfo
     * @return
     */
    @RequestMapping(value = "/getSysWarningInfoData", method = RequestMethod.POST)
    public String getSysWarningInfoData(ModelMap map, Integer pageNum, BesWainingInfo besWainingInfo) {
        log.info("#WariningInfoDataController 获取系统报警信息列表");
        PageInfo<BesWainingInfo> page = besWainingInfoService.searchWainingInfo(pageNum,
                besWainingInfo);
        map.put("page", page);
        map.put("ftype", besWainingInfo.getFType());
        map.put("endtime", besWainingInfo.getEndTime());
        map.put("starttime", besWainingInfo.getStartTime());
        map.put("flevel", besWainingInfo.getFLevel());
        map.put("fyqbh", besWainingInfo.getFYqbh());
        map.put("state", besWainingInfo.getFOpearation());
        map.put("dataset", JsonMapper.toJsonString(page.getList()));
        return "view/dataAnalysis/systemAlarm/systemWainingInfoData_page";
    }


    /**
     * 饼状图数据封装接口
     * @param besWainingInfo
     * @return
     */
    @RequestMapping(value = "/getWarningDataCount", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getWarningInfoDataCount(BesWainingInfo besWainingInfo) {
        ISSPReturnObject isspReturnObject = besWainingInfoService.searchWainingInfoCount(besWainingInfo);
        return isspReturnObject;
    }

    /**
     * 折线图数据封装接口
     * @param besWainingInfo
     * @return
     */
    @RequestMapping(value = "/getWarningLineData", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getWarningLineData(BesWainingInfo besWainingInfo) {
        ISSPReturnObject isspReturnObject = besWainingInfoService.searchWainingData(besWainingInfo);
        return isspReturnObject;
    }


    /**
     * 报警信息环比饼状图数据封装接口
     * @param besWainingInfo
     * @return
     */
    @RequestMapping(value = "/getHbWarningDataCount", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getHbWarningDataCount(BesWainingInfo besWainingInfo) {
        ISSPReturnObject isspReturnObject = besWainingInfoService.searchHbWainingPieData(besWainingInfo);
        return isspReturnObject;
    }

    /**
     * 报警信息环比柱状图数据封装接口
     * @param besWainingInfo
     * @return
     */
    @RequestMapping(value = "/getHbWarningBarData", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getHbWarningBarData(BesWainingInfo besWainingInfo) {
        ISSPReturnObject isspReturnObject = besWainingInfoService.searchHbWainingBarData(besWainingInfo);
        return isspReturnObject;
    }



}
