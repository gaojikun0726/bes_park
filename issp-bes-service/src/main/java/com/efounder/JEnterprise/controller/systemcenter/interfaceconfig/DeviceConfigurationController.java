package com.efounder.JEnterprise.controller.systemcenter.interfaceconfig;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceConfigurationModel;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionModel;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionPointModel;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceTypeModel;
import com.efounder.JEnterprise.service.systemcenter.interfaceconfig.DeviceConfigurationService;
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
import java.util.Map;

/**
 * @Author: wanghongjie
 * @Description:设备配置（接口管理模块）
 * @Date: Created in 14:30 2020/12/10
 * @Modified By:设备配置
 */
@RequestMapping(value = "/view/sysmanage/interfaceconfig/deviceConfiguration")
@Controller
public class DeviceConfigurationController {
    private static final Logger log = LoggerFactory.getLogger(DeviceConfigurationController.class);

    @Autowired
    private DeviceConfigurationService deviceConfigurationService;
    /**
     * 初始化设备配置页面
     * @return
     */
    @RequestMapping(value = "initializePage", method = RequestMethod.GET)
    public String showInitPage() {
        log.info("#DeviceConfigurationController 初始化设备配置页面");
        return "view/sysmanage/interfaceconfig/deviceConfiguration";
    }

    /**
     *
     * @param keywords
     * @param pageNum
     * @param map
     * @return
     */
    @RequestMapping(value = "/queryPage", method = RequestMethod.POST)
    public String getDeviceConfigurationList(@RequestParam(value = "deviceTypeId", required = false)String deviceTypeId,
                                             @RequestParam(value = "positionId", required = false)String positionId,
                                             @RequestParam(value = "param", required = false)String param,
                                  @RequestParam(value = "pageNum", required = false)Integer pageNum, ModelMap map) {
        log.info("#分页查询：设备配置");
        PageInfo<DeviceConfigurationModel> page = deviceConfigurationService.queryPage(deviceTypeId,positionId,pageNum,param);
        map.put("page", page);
        String jsonString = JSONObject.toJSONString(page.getList());
        map.put("pageList", jsonString);
        map.put("keywords", param);
        map.put("deviceTypeId", deviceTypeId);
        map.put("pageNum", pageNum);
        return "view/sysmanage/interfaceconfig/deviceConfigurationPage";

    }
/**
 *
 * @Description: 查询当前设备类型下的设备功能和功能点位
 *
 * @auther: wanghongjie
 * @date: 15:41 2021/5/27
 * @param: [deviceTypeId, param, pageNum, map]
 * @return: java.lang.String
 *
 */
    @RequestMapping(value = "/queryPage_function", method = RequestMethod.POST)
    public String getDeviceConfiguration_functionList(@RequestParam(value = "deviceTypeId", required = false)String deviceTypeId,
                                                      @RequestParam(value = "deviceId", required = false)String deviceId,
                                             @RequestParam(value = "param", required = false)String param,
                                             @RequestParam(value = "pageNum", required = false)Integer pageNum, ModelMap map) {
        if (deviceId.equals("空")) {
            String jsonString = JSONObject.toJSONString("");
            map.put("pageList", jsonString);
        } else {
            PageInfo<DeviceFunctionModel> page = deviceConfigurationService.queryPage_function(deviceId,deviceTypeId,pageNum,param);
            map.put("page", page);
            String jsonString = JSONObject.toJSONString(page.getList());
            map.put("pageList", jsonString);
        }

//        map.put("keywords", param);
        return "view/sysmanage/interfaceconfig/deviceConfiguration_FunctionPage";

    }
    
    /**
     *
     * @Description: 获取所有的设备类型
     * 
     * @auther: wanghongjie
     * @date: 16:14 2020/12/10
     * @param: []
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @RequestMapping(value = "/getDeviceType", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getDeviceType() {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        List<DeviceTypeModel> getDeviceType = deviceConfigurationService.getDeviceType();
        returnObject.setList(getDeviceType);
        return returnObject;
    }

    /**
     * 添加设备配置
     * */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject create(DeviceConfigurationModel deviceConfigurationModel) {

        log.info("#添加设备配置");
        return deviceConfigurationService.create(deviceConfigurationModel);
    }


    /**
     *  查询设备配置信息
     * */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public ISSPReturnObject query(DeviceConfigurationModel deviceConfigurationModel) {
        log.info("#查询设备配置信息");
        return deviceConfigurationService.query(deviceConfigurationModel);
    }

    /**
     *
     * @Description: 修改设备配置信息
     *
     * @auther: wanghongjie
     * @date: 9:58 2020/12/11
     * @param: [deviceConfigurationModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject update(DeviceConfigurationModel deviceConfigurationModel) {
        log.info("#修改设备配置信息");
        return deviceConfigurationService.update(deviceConfigurationModel);
    }

    /**
     *
     * @Description: 删除设备配置信息
     *
     * @auther: wanghongjie
     * @date: 11:08 2020/12/11
     * @param:
     * @return:
     *
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject delete(DeviceConfigurationModel deviceConfigurationModel) {
        log.info("#删除设备配置信息");
        return deviceConfigurationService.delete(deviceConfigurationModel);
    }

    /**
     *
     * @Description: 删除配置的功能点位
     *
     * @auther: wanghongjie
     * @date: 10:09 2021/7/1
     * @param: [deviceConfigurationModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @RequestMapping(value = "/deletePoint", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject deletePoint(String deviceFunctionPointID) {
        log.info("#删除配置的功能点位");
        return deviceConfigurationService.deletePoint(deviceFunctionPointID);
    }

    /**
     *
     * @Description: 根据功能id查询关联的点位信息
     *
     * @auther: wanghongjie
     * @date: 9:21 2021/5/28
     * @param:
     * @return:
     *
     */
    @RequestMapping(value = "/queryPoint", method = RequestMethod.GET)
    @ResponseBody
    public ISSPReturnObject queryPoint(DeviceFunctionPointModel deviceFunctionPointModel) {
        log.info("#根据功能id查询关联的点位信息");
        return deviceConfigurationService.queryPoint(deviceFunctionPointModel);
    }

    /**
     *
     * @Description: 获取区域
     *
     * @return: com.core.common.ISSPReturnObject
     */
    @RequestMapping(value = "/queryPosition", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject queryPosition() {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        List<Map<String,Object>> positionList = deviceConfigurationService.queryPosition();
        returnObject.setList(positionList);
        return returnObject;
    }

}
