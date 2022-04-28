package com.efounder.JEnterprise.controller.basedatamanage.warningAndAlarm;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.warningAndAlarm.AlarmLevel;
import com.efounder.JEnterprise.service.basedatamanage.warningAndAlarm.AlarmLevelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * author:liuzhen
 * 功能：警告等级模块
 * 时间：2018/11/27
 */
@Controller
@RequestMapping(value = "/view/basedatamanage/warningAndAlarm")
public class AlarmLevelController {
    private static final Logger log = LoggerFactory.getLogger(AlarmLevelController.class);

    @Autowired
    private AlarmLevelService alarmLevelService;
    /**
     * 初始化警告等级页面
     * @return  跳转的页面
     */
    @RequestMapping(value = "/alarmLevel", method = RequestMethod.GET)
    public String initAlarmLevel() {
        log.info("# AlarmLevelController初始化警告等级页面");
        return "view/basedatamanage/warningAndAlarm/alarm_level";

    }


    /**
     * 获取警报类型
     * @return
     */
    @RequestMapping(value = "/getAlarmLevel", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getAlarmLevel (AlarmLevel alarmLevel) {
        log.info("AlarmLevelController获取获取警报等级");
        //统一返回格式
        ISSPReturnObject returnObject = alarmLevelService.getAlarmLevelList(alarmLevel);
        return returnObject;
    }

    /**
     * 获取警报类型
     * @return
     */
    @RequestMapping(value = "/getAlarmLevelBySearch", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getAlarmLevelBySearch (AlarmLevel alarmLevel) {
        log.info("AlarmLevelController获取获取警报等级");
        //统一返回格式
        ISSPReturnObject returnObject = alarmLevelService.getAlarmLevelListBySearch(alarmLevel);
        return returnObject;
    }


    /**
     * 新增警报类型
     * @return
     */
    @RequestMapping(value = "/insertAlarmLevel", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject insertAlarmLevel (AlarmLevel alarmLevel) {
        log.info("AlarmLevelController 插入警报等级");
        //统一返回格式
        ISSPReturnObject returnObject = alarmLevelService.insertAlarmLevel(alarmLevel);
        return returnObject;
    }

    /**
     * 删除警报类型
     * @return
     */
    @RequestMapping(value = "/deleteAlarmLevel", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject deleteAlarmLevel (AlarmLevel alarmLevel) {
        log.info("AlarmTypeController 删除警报的等级");
        //统一返回格式
        ISSPReturnObject returnObject = alarmLevelService.deleteAlarmLevel(alarmLevel);
        return returnObject;
    }


    /**
     * 删除警报类型
     * @return
     */
    @RequestMapping(value = "/updateAlarmLevel", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject updateAlarmLevel (AlarmLevel alarmLevel) {
        log.info("AlarmLevelController 更新警报等级");
        //统一返回格式
        ISSPReturnObject returnObject = alarmLevelService.updateAlarmLevel(alarmLevel);
        return returnObject;
    }

}

