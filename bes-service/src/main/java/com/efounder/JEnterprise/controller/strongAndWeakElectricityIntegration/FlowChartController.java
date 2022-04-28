package com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.BESFlowType;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.FlowChartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 类名称：FlowChartController
 *  类描述：流程图监控页面
 *   创建人：Yangjx
 *   创建时间：2019/8/27
 *   修改人：
 * 
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/view/strongAndWeakElectricityIntegration/flowChart")
public class FlowChartController {
	private static final Logger log = LoggerFactory.getLogger(FlowChartController.class);


	@Autowired
	private FlowChartService flowChartService;
	
	/*
	 * 初始加载页面 未传参
	 */
   /* @GetMapping("/showInitPage")
    public String showInitPage() {
        log.info("#FlowChartController 进入流程图监控页面");
        return "view/strongAndWeakElectricityIntegration/flowChartNew";
    }*/

	/*
	 * 初始加载页面 未传参
	 */
    @GetMapping("/showInitPage")
    public String showInitPage() {
        log.info("#FlowChartController 进入流程图监控页面");
        return "view/strongAndWeakElectricityIntegration/flowChartNew";
    }

	/*
	 * 初始加载页面 未传参
	 */
	@GetMapping("/initFlowTypeInfo")
	public String initFlowTypeInfo() {
		log.info("#FlowChartController 进入流程图管理页面");
		/*return "view/strongAndWeakElectricityIntegration/flowType";*/
		return null;
	}

	/**
	 * 添加流程图信息
	 * @param besFlowType
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addFlowType", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject addFlowType(BESFlowType besFlowType, ModelMap model) {
		log.info("#FlowChartController添加流程图信息");
		ISSPReturnObject returnObject = flowChartService.addFlowType(besFlowType);
		return returnObject;
	}


	/**
	 * 修改流程图信息
	 * @param besFlowType
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editFlowType", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject editFlowType(BESFlowType besFlowType, ModelMap model) {
		log.info("#FlowChartController修改流程图信息");
		ISSPReturnObject returnObject = flowChartService.editFlowType(besFlowType);
		return returnObject;
	}

	@RequestMapping(value = "/getFlowTypeInfo", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getFlowTypeInfo(ModelMap model) {
		log.info("#FlowChartController查询流程图信息");
		ISSPReturnObject returnObject = flowChartService.getFlowTypeInfo();
		return returnObject;
	}

	/**
	 * 删除流程图
	 * @param moduletype
	 * @return
	 */
	@RequestMapping(value = "/deleteFlowType", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject deleteFlowType(BESFlowType besFlowType) {
		log.info("#FlowChartController删除流程图信息");
		ISSPReturnObject returnObject = flowChartService.deleteFlowType(besFlowType);
		return returnObject;
	}
    
}













