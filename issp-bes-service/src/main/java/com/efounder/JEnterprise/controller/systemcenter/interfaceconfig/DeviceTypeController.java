package com.efounder.JEnterprise.controller.systemcenter.interfaceconfig;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceTypeModel;
import com.efounder.JEnterprise.service.systemcenter.interfaceconfig.DeviceTypeService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 设备类型（接口管理模块）
 * @author xiepufeng
 * @date 2020/12/8 20:23
 */
@RequestMapping(value = "/view/sysmanage/interfaceconfig/deviceType")
@Controller
public class DeviceTypeController
{
    private static final Logger log = LoggerFactory.getLogger(DeviceTypeController.class);

    @Resource
    private DeviceTypeService deviceTypeService;

    /**
     * 初始化设备类型页面
     * @return
     */
    @RequestMapping(value = "initializePage", method = RequestMethod.GET)
    public String showInitPage() {
        log.info("#DeviceTypeController 初始化设备类型页面");
        return "view/sysmanage/interfaceconfig/deviceType";
    }

    /**
     *
     * @param keywords
     * @param pageNum
     * @param map
     * @return
     */
    @RequestMapping(value = "/queryPage", method = RequestMethod.POST)
    public String getProtocolList(@RequestParam(value = "keywords", required = false)String keywords,
                                  @RequestParam(value = "pageNum", required = false)Integer pageNum,ModelMap map) {
        log.info("#分页查询：设备类型");
        PageInfo<DeviceTypeModel> page = deviceTypeService.queryPage(keywords,pageNum);
        map.put("page", page);
        String jsonString = JSONObject.toJSONString(page.getList());
        map.put("pageList", jsonString);
        map.put("keywords", keywords);
        return "view/sysmanage/interfaceconfig/deviceTypePage";

    }

    /**
     * 添加设备类型
     * */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject create(DeviceTypeModel deviceTypeModel) {

        log.info("#添加设备类型");
        return deviceTypeService.create(deviceTypeModel);
    }


    /**
     *  查询设备类型信息
     * */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public ISSPReturnObject query(DeviceTypeModel deviceTypeModel) {
        log.info("#查询设备类型信息");
        return deviceTypeService.query(deviceTypeModel);
    }

    /**
     * 更新设备类型
     * */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject update(DeviceTypeModel deviceTypeModel) {

        log.info("#更新设备类型");
        return deviceTypeService.update(deviceTypeModel);
    }

    /**
     * 删除设备类型信息
     *
     * */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public ISSPReturnObject delete(DeviceTypeModel deviceTypeModel) {

        log.info("#删除设备类型信息");
        return deviceTypeService.delete(deviceTypeModel);
    }
}
