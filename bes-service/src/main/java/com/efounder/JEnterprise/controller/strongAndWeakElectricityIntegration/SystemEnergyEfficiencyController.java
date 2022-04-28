package com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 类名称：SystemEnergyEfficiencyController
 *  类描述：能效评估监控页面
 *   创建人：Yangjx
 *   创建时间：2019/9/05
 *   修改人：
 * 
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/view/strongAndWeakElectricityIntegration/systemEnergyEfficiency")
public class SystemEnergyEfficiencyController {
	private static final Logger log = LoggerFactory.getLogger(SystemEnergyEfficiencyController.class);
	
	/*
	 * 初始加载页面 未传参
	 */
   @GetMapping("/showInitPage")
   public String showInitPage() {
       log.info("#SystemEnergyEfficiencyController 进入系统能效页面");
       return "view/strongAndWeakElectricityIntegration/systemEnergyEfficiencyNew";
   }
    /*public String showInitPage() {
        log.info("#SystemEnergyEfficiencyController 进入系统能效页面");
        return "view/strongAndWeakElectricityIntegration/systemEnergyEfficiency";
    }*/
   
}













