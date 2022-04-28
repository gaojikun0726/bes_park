package com.efounder.JEnterprise.controller.basedatamanage.warningAndAlarm;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.warningAndAlarm.AlarmType;
import com.efounder.JEnterprise.service.basedatamanage.warningAndAlarm.AlarmTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * author:liuzhen
 * 功能：警告类型模块
 * 时间：2018/11/27
 */
@Controller
@RequestMapping(value = "/view/basedatamanage/warningAndAlarm")
public class AlarmTypeController {
    private static final Logger log = LoggerFactory.getLogger(AlarmTypeController.class);

    @Autowired
    private AlarmTypeService alarmTypeService;
    /**
     * 初始化警告类型页面
     * @return  跳转的页面
     */
    @RequestMapping(value = "/alarmType", method = RequestMethod.GET)
    public String initAlarmType() {
        log.info("# AlarmTypeController初始化警告类型页面");
        return "view/basedatamanage/warningAndAlarm/alarm_type";

    }


    /**
     * 获取警报类型
     * @return
     */
    @RequestMapping(value = "/getAlarmType", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getAlarmType (AlarmType alarmType) {
        log.info("AlarmTypeController获取获取警报类型");
        //统一返回格式
        ISSPReturnObject returnObject = alarmTypeService.getAlarmTypeList(alarmType);
        return returnObject;
    }

    /**
     * 获取警报类型
     * @return
     */
    @RequestMapping(value = "/getAlarmTypeBySearch", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getAlarmTypeBySearch (AlarmType alarmType) {
        log.info("AlarmTypeController获取获取警报类型");
        //统一返回格式
        ISSPReturnObject returnObject = alarmTypeService.getAlarmTypeListBySearch(alarmType);
        return returnObject;
    }


    /**
     * 新增警报类型
     * @return
     */
    @RequestMapping(value = "/insertAlarmType", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject insertAlarmType (AlarmType alarmType) {
        log.info("AlarmTypeController 插入警报类型");
        //统一返回格式
        ISSPReturnObject returnObject = alarmTypeService.insertAlarmType(alarmType);
        return returnObject;
    }

    /**
     * 删除警报类型
     * @return
     */
    @RequestMapping(value = "/deleteAlarmType", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject deleteAlarmType (AlarmType alarmType) {
        log.info("AlarmTypeController 删除警报类型");
        //统一返回格式
        ISSPReturnObject returnObject = alarmTypeService.deleteAlarmType(alarmType);
        return returnObject;
    }


    /**
     * 删除警报类型
     * @return
     */
    @RequestMapping(value = "/updateAlarmType", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject updateAlarmType (AlarmType alarmType) {
        log.info("AlarmTypeController 更新警报类型");
        //统一返回格式
        ISSPReturnObject returnObject = alarmTypeService.updateAlarmType(alarmType);
        return returnObject;
    }

}

