package com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration.deviceConfig;

import com.core.common.util.JsonMapper;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.deviceConfig.ChilledWaterModel;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.deviceConfig.ChilledWaterService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 *冷却水配置
 *王红杰
 *
 * */

@Controller
@RequestMapping(value = "/view/strongAndWeakElectricityIntegration/deviceConfig/waterCoolant")
public class WaterCoolantController {
    private static final Logger log = LoggerFactory.getLogger(WaterCoolantController.class);
    @Resource
     private ChilledWaterService chilledWaterService;

    @GetMapping("page")
    public String showPage() {
        log.info("#WaterCoolantController 进入冷却水配置页面");
        return "view/strongAndWeakElectricityIntegration/deviceConfig/waterCoolant";
    }

    /**
     *
     * 筛选查询，分页查询
     */

    @RequestMapping(value = "/getPaging", method = RequestMethod.POST)
    public String getPaging(ModelMap map, Integer pageSize, Integer pageNum, ChilledWaterModel chilledWaterModel) {

        PageInfo<Object> page = chilledWaterService.getPaging(pageSize, pageNum, chilledWaterModel);
        map.put("page", page);
        map.put("dataset", JsonMapper.toJsonString(page.getList()));
        map.put("pageSize", page.getPageSize() + "");
        return "/view/strongAndWeakElectricityIntegration/deviceConfig/waterCoolantPage";
    }
}
