package com.efounder.JEnterprise.controller.dataAnalysises.houseHoldAppraisal;


import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.dataAnalysises.BesEnergyPerformance;
import com.efounder.JEnterprise.model.dataAnalysises.BesHouseholdPlanData;
import com.efounder.JEnterprise.service.dataAnalysises.BesEnergyPerformanceService;
import com.efounder.JEnterprise.service.dataAnalysises.BesHouseholdPlanDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 分户绩效考核能源计划分配
 * @author liuzhen
 *
 */
@Controller
@RequestMapping(value = "/view/dataAnalysises/houseHoldAppraisal")
public class BESHousePlanDataController {	
	private static final Logger log = LoggerFactory.getLogger(BESHousePlanDataController.class);
	
	@Autowired
	private BesHouseholdPlanDataService besHouseholdPlanDataService;
	@Autowired
	private BesEnergyPerformanceService  besEnergyPerformanceService;
	
	/**
	 * 初始化到分户绩效考核能源计划分配
	 * 
	 * @param map
	 * @return 
	 */
	@RequestMapping(value = "/energy_plan", method = RequestMethod.GET)
	public String energy_plan(ModelMap map) {
		log.info("分户绩效考核能源计划分配");
		return "view/dataAnalysis/houseHoldAppraisal/energy_plan";
	}
	
	/**
	 * 初始化到分户绩效考核能源绩效考核
	 * 
	 * @param map
	 * @return 
	 */
	@RequestMapping(value = "/energy_performance", method = RequestMethod.GET)
	public String energy_performance(ModelMap map) {
		log.info("分户绩效考核能源绩效考核");
		return "view/dataAnalysis/houseHoldAppraisal/energy_performance";
	}
	
	
	/**
	 * 获取能源绩效考核数据
	 * @param besSubitemData
	 * @return
	 */
	@RequestMapping(value = "/getEnergyPerformanceData", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getEnergyPerformanceData (BesEnergyPerformance besEnergyPermormance){
		log.info("#BESHousePlanDataController获取能源绩效考核数据");
		ISSPReturnObject returnObject =  besEnergyPerformanceService.getEnergyPerformance(besEnergyPermormance);
		return returnObject;
	}
	
	/**
	 * 获取分户用能趋势统计分析数据
	 * @param besSubitemData
	 * @return
	 */
	@RequestMapping(value = "/getHouseholdPlanData", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getHouseholdPlanData (BesHouseholdPlanData besHouseholdPlanData){
		log.info("#BESHousePlanDataController获取能源计划分配数据");
		ISSPReturnObject returnObject =  besHouseholdPlanDataService.getHouseholdPlanData(besHouseholdPlanData);
		return returnObject;
	}
	
	/**
	 * 更新分户用数据
	 * @param besSubitemData
	 * @return
	 */
	@RequestMapping(value = "/updateHouseholdData", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject updateHouseholdData (BesHouseholdPlanData besHouseholdPlanData){
		log.info("#BESHousePlanDataController更新分户数据");
		ISSPReturnObject returnObject =  besHouseholdPlanDataService.updateHouseholdPlan(besHouseholdPlanData);
		return returnObject;
	}
	
	/**
	 * 更新能源计划分配数据
	 * @param besSubitemData
	 * @return
	 */
	@RequestMapping(value = "/updateHouseholdPlanData", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject updateHouseholdPlanData (BesHouseholdPlanData besHouseholdPlanData){
		log.info("#BESHousePlanDataController更新能源计划分配数据");
		ISSPReturnObject returnObject =  besHouseholdPlanDataService.updateHouseholdPlanData(besHouseholdPlanData);
		return returnObject;
	}
}
