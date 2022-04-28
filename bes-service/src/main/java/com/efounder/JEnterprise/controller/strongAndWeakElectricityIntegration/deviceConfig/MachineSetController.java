package com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration.deviceConfig;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.deviceConfig.MachineSetModel;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.deviceConfig.MachineSetService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 机组
 * xiepufeng
 */
@Controller
@RequestMapping(value = "/view/strongAndWeakElectricityIntegration/deviceConfig/machineSet")
public class MachineSetController
{
	private static final Logger log = LoggerFactory.getLogger(MachineSetController.class);

    @Resource
    private MachineSetService machineSetService;

   @GetMapping("/showInitPage")
    public String showInitPage() {
        log.info("#MachineSetController 进入机组页面");
        return "view/strongAndWeakElectricityIntegration/deviceConfig/machineSet";
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
        log.info("#分页查询：机组");
        PageInfo<MachineSetModel> page = machineSetService.queryPage(keywords,pageNum);
        map.put("page", page);
        String jsonString = JSONObject.toJSONString(page.getList());
        map.put("pageList", jsonString);
        map.put("keywords", keywords);
        return "view/strongAndWeakElectricityIntegration/deviceConfig/machineSetPage";

    }

    /**
     * 添加机组
     * */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject createHost(MachineSetModel machineSetModel) {

        log.info("#添加机组");
        return machineSetService.create(machineSetModel);
    }


    /**
     *  查询机组信息
     * */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public ISSPReturnObject query(MachineSetModel machineSetModel) {
        log.info("#查询机组信息");
        return machineSetService.query(machineSetModel);
    }

    /**
     * 更新机组
     * */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject update(MachineSetModel machineSetModel) {

        log.info("#更新机组");
        return machineSetService.update(machineSetModel);
    }

    /**
     * 删除机组信息
     *
     * */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public ISSPReturnObject delete(MachineSetModel machineSetModel) {

        log.info("#删除机组信息");
        return machineSetService.delete(machineSetModel);
    }

}