package com.efounder.JEnterprise.controller.systemcenter.interfaceconfig;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceConfigurationModel;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionPointModel;
import com.efounder.JEnterprise.service.systemcenter.interfaceconfig.DeviceFunctionPointService;
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

import java.util.List;

/**
 * @Author: wanghongjie
 * @Description:功能点位（接口管理模块）
 * @Date: Created in 14:18 2020/12/14
 * @Modified By:
 */
@RequestMapping(value = "/view/sysmanage/interfaceconfig/deviceFunctionPoint")
@Controller
public class DeviceFunctionPointController {
    private static final Logger log = LoggerFactory.getLogger(DeviceFunctionPointController.class);

    @Autowired
    private DeviceFunctionPointService deviceFunctionPointService;
    /**
     * 初始化功能点位页面
     * @return
     */
    @RequestMapping(value = "initializePage", method = RequestMethod.GET)
    public String showInitPage() {
        log.info("#DeviceFunctionController 初始化功能点位页面");
        return "view/sysmanage/interfaceconfig/deviceFunctionPoint";
    }

    /**
     *
     * @param pageNum
     * @param map
     * @return
     */
    @RequestMapping(value = "/queryPage", method = RequestMethod.POST)
    public String getDeviceFunctionPointList(@RequestParam(value = "deviceId", required = false)String deviceId,//设备id
                                             @RequestParam(value = "deviceFunctionId", required = false)String deviceFunctionId,//设备功能id
                                             @RequestParam(value = "param", required = false)String param,
                                             @RequestParam(value = "pageNum", required = false)Integer pageNum, ModelMap map) {
        log.info("#分页查询：功能点位");
        PageInfo<DeviceFunctionPointModel> page = deviceFunctionPointService.queryPage(deviceId,deviceFunctionId,pageNum,param);
        map.put("page", page);
        String jsonString = JSONObject.toJSONString(page.getList());
        map.put("pageList", jsonString);
        map.put("keywords", param);
        map.put("deviceId", deviceId);
        map.put("deviceFunctionId", deviceFunctionId);
        return "view/sysmanage/interfaceconfig/deviceFunctionPointPage";

    }

    /**
     *
     * @Description: 获取所有的设备
     *
     * @auther: wanghongjie
     * @date: 16:14 2020/12/10
     * @param: []
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @RequestMapping(value = "/getDevice", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getDevice() {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        List<DeviceConfigurationModel> getDeviceType = deviceFunctionPointService.getDevice();
        returnObject.setList(getDeviceType);
        return returnObject;
    }

    /**
     *
     * @Description: 获取所有的设备功能
     *
     * @auther: wanghongjie
     * @date: 16:14 2020/12/10
     * @param: []
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @RequestMapping(value = "/getDeviceFunction", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getDeviceFunction(DeviceConfigurationModel deviceConfigurationModel) {
        return  deviceFunctionPointService.getDeviceFunction(deviceConfigurationModel);
    }

    /**
     * 添加功能点位
     * */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject createHost(DeviceFunctionPointModel deviceFunctionPointModel) {

        log.info("#添加功能点位");
        return deviceFunctionPointService.create(deviceFunctionPointModel);
    }


    /**
     *  查询功能点位信息
     * */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public ISSPReturnObject query(DeviceFunctionPointModel deviceFunctionPointModel) {
        log.info("#查询设备类型信息");
        return deviceFunctionPointService.query(deviceFunctionPointModel);
    }

    /**
     *
     * @Description: 修改设备功能点位信息
     *
     * @auther: wanghongjie
     * @date: 9:58 2020/12/11
     * @param: [deviceConfigurationModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject update(DeviceFunctionPointModel deviceFunctionPointModel) {
        log.info("#修改功能点位信息");
        return deviceFunctionPointService.update(deviceFunctionPointModel);
    }

    /**
     *
     * @Description: 删除功能点位信息
     *
     * @auther: wanghongjie
     * @date: 11:08 2020/12/11
     * @param:
     * @return:
     *
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject delete(DeviceFunctionPointModel deviceFunctionPointModel) {
        log.info("#删除功能点位信息");
        return deviceFunctionPointService.delete(deviceFunctionPointModel);
    }
    
    /**
     *
     * @Description: 设备配置添加功能点位的逻辑
     * 
     * @auther: wanghongjie
     * @date: 10:10 2021/5/28
     * @param: 
     * @return: 
     *
     */
    @RequestMapping(value = "/add_update", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject add_update(DeviceFunctionPointModel deviceFunctionPointModel) {
        log.info("#设备配置添加功能点位的逻辑");
        return deviceFunctionPointService.add_update(deviceFunctionPointModel);
    }
}
