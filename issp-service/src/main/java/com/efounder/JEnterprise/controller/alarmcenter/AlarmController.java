package com.efounder.JEnterprise.controller.alarmcenter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AlarmController {
	
	@RequestMapping(value = "/alarmMonitor", method = { RequestMethod.POST, RequestMethod.GET })
	public String alarmMonitor() {
		 return "view/alarm/alarmMonitor";
	}
	 
	@RequestMapping(value = "/myAlarmTask", method = { RequestMethod.POST, RequestMethod.GET })
	public String myAlarmTask() {
		 return "view/alarm/mytask";
	}	

}
