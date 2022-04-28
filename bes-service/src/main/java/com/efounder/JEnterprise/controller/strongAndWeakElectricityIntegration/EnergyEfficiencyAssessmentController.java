package com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.EnergyEfficiencyAssessmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 类名称：EnergyEfficiencyAssessmentController
 *  类描述：能效评估监控页面
 *   创建人：Yangjx
 *   创建时间：2019/8/12
 *   修改人：
 * 
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/view/strongAndWeakElectricityIntegration/energyEfficiencyAssessment")
public class EnergyEfficiencyAssessmentController {
	private static final Logger log = LoggerFactory.getLogger(EnergyEfficiencyAssessmentController.class);

	@Autowired
	private EnergyEfficiencyAssessmentService energyEfficiencyAssessmentService;
	/*
	 * 初始加载页面 未传参
	 */
//	@RequestMapping(value = "/showInitPage", method = RequestMethod.GET)
//	public String getalertGrade() {
//		log.info("#AlertGradeController 初始化报警等级页面");
//		return "view/alarmandwarning/alertgrade";
//	}
   @GetMapping("/showInitPage")
    public String showInitPage() {
        log.info("#EnergyEfficiencyAssessmentController 进入能效评估监控页面");
        return "view/strongAndWeakElectricityIntegration/energyEfficiencyAssessment";
    }

	/**
	 * 查询系统能耗配置信息
	 * @param sysnameArray
	 * @return
	 */
	@RequestMapping(value = "/queryEnergyEfficiencyListInfo")
	@ResponseBody
	public ISSPReturnObject queryEnergyEfficiencyListInfo(@RequestBody String[] sysnameArray) {
		log.info("#EnergyEfficiencyAssessmentController查询系统能耗配置信息");
		ISSPReturnObject returnObject = energyEfficiencyAssessmentService.queryEnergyEfficiencyListInfo(sysnameArray);
		return returnObject;
	}

	/**
	 * 查询实时系统能耗配置信息
	 * @param sysnameArray
	 * @return
	 */
	@RequestMapping(value = "/queryRealTimeEnergyEfficiencyBtnInfo")
	@ResponseBody
	public ISSPReturnObject queryRealTimeEnergyEfficiencyBtnInfo(@RequestBody String[] sysnameArray) {
		log.info("#EnergyEfficiencyAssessmentController查询实时系统能耗配置信息");
		ISSPReturnObject returnObject = energyEfficiencyAssessmentService.queryRealTimeEnergyEfficiencyBtnInfo(sysnameArray);
		return returnObject;
	}


	/**
	 * 查询饼图点位关联及计算公式相关信息
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryPieRelativeInfo")
	public ISSPReturnObject queryPieRelativeInfo(@RequestBody Map map){
		ISSPReturnObject isspReturnObject = null;
		isspReturnObject = energyEfficiencyAssessmentService.queryPieRelativeInfo(map);
		return isspReturnObject;
	}

	/**
	 * 查询饼图所有点位关联及计算公式相关信息
	 * @param sysnameArray
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryAllPieRelativeInfo")
	public ISSPReturnObject queryAllPieRelativeInfo(@RequestBody String[] piePointArray){
		ISSPReturnObject isspReturnObject = null;
		isspReturnObject = energyEfficiencyAssessmentService.queryAllPieRelativeInfo(piePointArray);
		return isspReturnObject;
	}


	/**
	 * 新增饼图点位关联及计算公式相关信息
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addPieRelativeInfo")
	public ISSPReturnObject addPieRelativeInfo(@RequestBody Map map){
		return energyEfficiencyAssessmentService.addPieRelativeInfo(map);
	}

	/**
	 * 查询列表关联点位信息
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryLableRelativeInfo")
	public ISSPReturnObject queryLableRelativeInfo(@RequestBody Map map){
		return energyEfficiencyAssessmentService.queryLableRelativeInfo(map);
	}


}













