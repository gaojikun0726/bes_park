package com.efounder.JEnterprise.controller.systemcenter.interfaceconfig;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionModel;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionValueModel;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceTypeModel;
import com.efounder.JEnterprise.service.systemcenter.interfaceconfig.DeviceFunctionService;
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
 * @Description:设备功能（接口管理模块）
 * @Date: Created in 14:18 2020/12/14
 * @Modified By:
 */
@RequestMapping(value = "/view/sysmanage/interfaceconfig/deviceFunction")
@Controller
public class DeviceFunctionController {
    private static final Logger log = LoggerFactory.getLogger(DeviceFunctionController.class);

    @Autowired
    private DeviceFunctionService deviceFunctionService;
    /**
     * 初始化设备功能页面
     * @return
     */
    @RequestMapping(value = "initializePage", method = RequestMethod.GET)
    public String showInitPage() {
        log.info("#DeviceFunctionController 初始化设备功能页面");
        return "view/sysmanage/interfaceconfig/deviceFunction";
    }

    /**
     *
     * @param pageNum
     * @param map
     * @return
     */
    @RequestMapping(value = "/queryPage", method = RequestMethod.POST)
    public String getDeviceFunctionList(@RequestParam(value = "deviceTypeId", required = false)String deviceTypeId,
                                             @RequestParam(value = "param", required = false)String param,
                                             @RequestParam(value = "pageNum", required = false)Integer pageNum, ModelMap map) {
        log.info("#分页查询：设备功能");
        PageInfo<DeviceFunctionModel> page = deviceFunctionService.queryPage(deviceTypeId,pageNum,param);
        map.put("page", page);
        String jsonString = JSONObject.toJSONString(page.getList());
        map.put("pageList", jsonString);
        map.put("keywords", param);
        map.put("deviceTypeId", deviceTypeId);
        map.put("pageNum", pageNum);
        return "view/sysmanage/interfaceconfig/deviceFunctionPage";

    }
    /**
     *
     * @Description: 加载功能值的页面
     *
     * @auther: wanghongjie
     * @date: 14:45 2021/5/28
     * @param: [deviceTypeId, param, pageNum, map]
     * @return: java.lang.String
     *
     */
    @RequestMapping(value = "/query_valuePage", method = RequestMethod.POST)
    public String getDeviceFunctionValueList(@RequestParam(value = "deviceFunctionId", required = false)String deviceFunctionId,
                                        @RequestParam(value = "param", required = false)String param,
                                        @RequestParam(value = "pageNum", required = false)Integer pageNum, ModelMap map) {
        log.info("#分页查询：加载功能值的页面");
        PageInfo<DeviceFunctionValueModel> page = deviceFunctionService.query_valuePage(deviceFunctionId);
        map.put("page", page);
        String jsonString = JSONObject.toJSONString(page.getList());
        map.put("pageList", jsonString);
        map.put("keywords", param);
        return "view/sysmanage/interfaceconfig/deviceFunction_valuePage";

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
        List<DeviceTypeModel> getDeviceType = deviceFunctionService.getDeviceType();
        returnObject.setList(getDeviceType);
        return returnObject;
    }

    /**
     * 添加设备功能
     * */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject createHost(DeviceFunctionModel deviceFunctionModel) {

        log.info("#添加设备类型");
        return deviceFunctionService.create(deviceFunctionModel);
    }


    /**
     *  查询设备功能信息
     * */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public ISSPReturnObject query(DeviceFunctionModel deviceFunctionModel) {
        log.info("#查询设备类型信息");
        return deviceFunctionService.query(deviceFunctionModel);
    }

    /**
     *
     * @Description: 修改设备功能信息
     *
     * @auther: wanghongjie
     * @date: 9:58 2020/12/11
     * @param: [deviceConfigurationModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject update(DeviceFunctionModel deviceFunctionModel) {
        log.info("#修改设备功能信息");
        return deviceFunctionService.update(deviceFunctionModel);
    }

    /**
     *
     * @Description: 删除设备功能信息
     *
     * @auther: wanghongjie
     * @date: 11:08 2020/12/11
     * @param:
     * @return:
     *
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject delete(DeviceFunctionModel deviceFunctionModel) {
        log.info("#删除设备功能信息");
        return deviceFunctionService.delete(deviceFunctionModel);
    }

    /**
     *
     * @Description: 添加功能值
     *
     * @auther: wanghongjie
     * @date: 15:27 2021/5/28
     * @param:
     * @return:
     *
     */
    @RequestMapping(value = "/create_value",method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject create_value(DeviceFunctionValueModel deviceFunctionValueModel) {
        log.info("#添加功能值");
        return deviceFunctionService.create_value(deviceFunctionValueModel);
    }

    /**
     *
     * @Description: 回显功能值
     *
     * @auther: wanghongjie
     * @date: 15:27 2021/5/28
     * @param:
     * @return:
     *
     */
    @RequestMapping(value = "/query_value",method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject query_value(DeviceFunctionValueModel deviceFunctionValueModel) {
        log.info("#回显功能值");
        return deviceFunctionService.query_value(deviceFunctionValueModel);
    }

    /**
     *
     * @Description: 修改功能值
     *
     * @auther: wanghongjie
     * @date: 16:51 2021/5/28
     * @param:
     * @return:
     *
     */
    @RequestMapping(value = "/update_value",method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject update_value(DeviceFunctionValueModel deviceFunctionValueModel) {
        log.info("#修改功能值");
        return deviceFunctionService.update_value(deviceFunctionValueModel);
    }

    /**
     *
     * @Description: 删除功能值
     *
     * @auther: wanghongjie
     * @date: 16:51 2021/5/28
     * @param:
     * @return:
     *
     */
    @RequestMapping(value = "/delete_value",method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject delete_value(DeviceFunctionValueModel deviceFunctionValueModel) {
        log.info("#删除功能值");
        return deviceFunctionService.delete_value(deviceFunctionValueModel);
    }

}
