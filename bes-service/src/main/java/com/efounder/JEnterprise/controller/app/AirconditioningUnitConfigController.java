package com.efounder.JEnterprise.controller.app;

import com.core.common.ISSPReturnObject;
import com.core.common.util.JsonMapper;
import com.efounder.JEnterprise.model.app.AirconditioningUnitConfigModel;
import com.efounder.JEnterprise.service.app.AirconditioningUnitConfigService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 空调机组配置
 * @author wzx
 */
@Controller
@RequestMapping(value = "/view/app/airconditioningUnitConfig")
public class AirconditioningUnitConfigController {
	private static final Logger log = LoggerFactory.getLogger(AirconditioningUnitConfigController.class);

    @Resource
	private AirconditioningUnitConfigService airconditioningUnitConfigService;

   @GetMapping("/page")
    public String showPage() {
        log.info("#AirconditioningUnitConfigController 进入空调监控页面");
        return "view/app/airconditioningUnitConfig";
    }

    /**
     * 添加空调机组配置
     * */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject create(AirconditioningUnitConfigModel airconditioningUnitConfigModel) {
        log.info("#添加空调机组配置");
        return airconditioningUnitConfigService.create(airconditioningUnitConfigModel);
    }


    /**
     *
     * 筛选查询，分页查询
     */
    @RequestMapping(value = "/getPaging", method = RequestMethod.POST)
    public String getPaging(ModelMap map, Integer pageSize, Integer pageNum, AirconditioningUnitConfigModel airconditioningUnitConfigModel) {

        PageInfo<Object> page = airconditioningUnitConfigService.getPaging(pageSize, pageNum, airconditioningUnitConfigModel);
        map.put("page", page);
        map.put("dataset", JsonMapper.toJsonString(page.getList()));
        map.put("pageSize", page.getPageSize() + "");
        return "/view/app/airconditioningUnitConfigPage";
    }

    /**
     *  查询电表配置信息
     * */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject query(AirconditioningUnitConfigModel airconditioningUnitConfigModel) {
        log.info("#查询空调机组配置信息");
        return airconditioningUnitConfigService.query(airconditioningUnitConfigModel);
    }

    /**
     * 更新电表配置信息
     * */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject update(AirconditioningUnitConfigModel airconditioningUnitConfigModel) {
        log.info("#更新空调机组配置信息");
        return airconditioningUnitConfigService.update(airconditioningUnitConfigModel);
    }

    /**
     * 更新电表配置信息
     * */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject delete(AirconditioningUnitConfigModel airconditioningUnitConfigModel) {
        log.info("#删除空调机组配置信息");
        return airconditioningUnitConfigService.delete(airconditioningUnitConfigModel);
    }

}













