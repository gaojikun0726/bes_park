package com.efounder.JEnterprise.controller.systemcenter.interfaceconfig;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.*;
import com.efounder.JEnterprise.service.systemcenter.interfaceconfig.DeviceExceptionLogService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Author: wanghongjie
 * @Description:设备异常日志
 * @Date: Created in 14:30 2020/12/10
 * @Modified By:设备异常日志
 */
@RequestMapping(value = "/view/sysmanage/interfaceconfig/deviceExceptionLog")
@Controller
public class DeviceExceptionLogController {
    private static final Logger log = LoggerFactory.getLogger(DeviceConfigurationController.class);

    @Autowired
    private DeviceExceptionLogService deviceExceptionLogService;

    /**
     * 初始化设备异常页面
     * @return
     */
    @RequestMapping(value = "initializePage", method = RequestMethod.GET)
    public String showInitPage() {
        log.info("#DeviceConfigurationController 初始化设备配置页面");
        return "view/sysmanage/interfaceconfig/deviceExceptionLog";
    }

    /**
     *
     * @param keywords
     * @param pageNum
     * @param map
     * @return
     */
    @RequestMapping(value = "/queryPage", method = RequestMethod.POST)
    public String queryPage(@RequestParam(value = "deviceTypeId", required = false)String deviceTypeId,
                                             @RequestParam(value = "positionId", required = false)String positionId,
                                             @RequestParam(value = "param", required = false)String param,
                                             @RequestParam(value = "pageNum", required = false)Integer pageNum, ModelMap map) {
        log.info("#分页查询：设备异常日志");
        PageInfo<DeviceExceptionLogModel> page = deviceExceptionLogService.queryPage(deviceTypeId,positionId,pageNum,param);
        map.put("page", page);
        String jsonString = JSONObject.toJSONString(page.getList());
        map.put("pageList", jsonString);
        map.put("keywords", param);
        map.put("deviceTypeId", deviceTypeId);
        map.put("pageNum", pageNum);
        return "view/sysmanage/interfaceconfig/deviceExceptionLogPage";

    }

    /**
     *
     * @param deviceTypeId
     * @param positionId
     * @return
     */
    @RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject deleteAll(@RequestParam(value = "deviceTypeId", required = false)String deviceTypeId,
                                      @RequestParam(value = "positionId", required = false)String positionId) {
        log.info("#批量删除：设备异常日志");
        return deviceExceptionLogService.deleteAll(deviceTypeId,positionId);
    }

    /**
     *
     * @Description: 删除设备异常日志
     *
     * @param:
     * @return:
     *
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject delete(DeviceExceptionLogModel deviceExceptionLogModel) {
        log.info("#删除设备配置信息");
        return deviceExceptionLogService.delete(deviceExceptionLogModel);
    }


}
