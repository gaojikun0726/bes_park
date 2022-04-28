package com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 类名称：SensorController
 *  类描述：运行依据展示页面
 *   创建人：Yangjx
 *   创建时间：2019/9/26
 *   修改人：
 * 
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/view/strongAndWeakElectricityIntegration/operationBasis")
public class OperationBasisController {
	private static final Logger log = LoggerFactory.getLogger(OperationBasisController.class);
	/*
	 * 初始加载页面 未传参
	 */
    @GetMapping("/showInitPage")
    public String showInitPage() {
        log.info("#OperationBasisController 进入运行依据页面");
        return "view/strongAndWeakElectricityIntegration/operationBasis";
    }
    
}













