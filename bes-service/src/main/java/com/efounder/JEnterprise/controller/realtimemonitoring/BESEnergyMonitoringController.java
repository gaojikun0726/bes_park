package com.efounder.JEnterprise.controller.realtimemonitoring;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.service.realtimemonitoring.BESEnergyMonitoringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 能效监控
 * 
 * @author LvSihan 
 * @date 2018年12月8日  
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/view/realtimemonitoring/BESEnergyMonitoring")
public class BESEnergyMonitoringController {
	private static final Logger log = LoggerFactory.getLogger(BESEnergyMonitoringController.class);
	@Autowired
	private BESEnergyMonitoringService besEnergyMonitoringService;
	/**
	 * 初始化能效监控页面
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/EnergyMonitoring", method = RequestMethod.GET)
	public String getZzjg(ModelMap map) {
		log.info("初始化能效监控页面");
		return "view/realtimemonitoring/energymonitoring";
	}
	/**
	 * 通过支路ID查询电表数据
	 * @param f_zlbh
	 * @return
	 */
	@RequestMapping(value = "/queryAmmeterData", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject queryAmmeterData(String f_zlbh) {
		log.info("通过支路ID查询电表数据");
		ISSPReturnObject returnObject = besEnergyMonitoringService.queryAmmeterData(f_zlbh);
		return returnObject;		
	}
	/**
	 * 加载echart数据
	 * @param f_zlbh
	 * @return
	 */
	@RequestMapping(value = "/loadAmmeterlinechart", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject loadAmmeterlinechart(String f_zlbh) {
		log.info("通过支路ID查询电表数据");
		ISSPReturnObject returnObject = besEnergyMonitoringService.loadAmmeterlinechart(f_zlbh);
		return returnObject;		
	}
}
