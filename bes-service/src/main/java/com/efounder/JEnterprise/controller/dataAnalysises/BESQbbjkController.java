package com.efounder.JEnterprise.controller.dataAnalysises;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Alvin 情报板监控
 */
@Controller
@RequestMapping(value = "/view/dataAnalysis")
public class BESQbbjkController {
	private static final Logger log = LoggerFactory.getLogger(BESQbbjkController.class);

	/**
	 * 初始化到主界面
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/qbbjk", method = RequestMethod.GET)
	public String getZzjg(ModelMap map) {
		log.info("获取信息---->情报板监控");
		return "view/dataAnalysis/qbbjk";
	}

}
