package com.efounder.JEnterprise.controller.dataAnalysises.systemAlarm;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * 系统报警
 * @author liuzhen
 *
 */
@Controller
@RequestMapping(value = "/view/systemAlarm/dataAnalysises")
public class SystemAlarmController {	
	private static final Logger log = LoggerFactory.getLogger(SystemAlarmController.class);
	
	
	
	/**
	 * 初始化到系统报警界面
	 * 
	 * @param map
	 * @return 
	 */
	@RequestMapping(value = "/system_alarm", method = RequestMethod.GET)
	public String getQstjfx(ModelMap map) {
		log.info("系统报警界面");
		return "view/dataAnalysis/systemAlarm/system_alarm";
	}
	
	
}
