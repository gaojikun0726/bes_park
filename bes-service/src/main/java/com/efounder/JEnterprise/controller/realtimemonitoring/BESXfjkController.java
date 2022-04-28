package com.efounder.JEnterprise.controller.realtimemonitoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 新风机组
 */
@Controller
@RequestMapping(value = "/view/realtimemonitoring/BESXfjk")
public class BESXfjkController {
	private static final Logger log = LoggerFactory.getLogger(BESXfjkController.class);

	/**
	 * 初始化到主界面
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/xfjk", method = RequestMethod.GET)
	public String getZnzm(ModelMap map) {
		log.info("获取信息---->照明监控");
		return "view/realtimemonitoring/xfjk";
	}
	/**
	 * 选中节点加载不同的监控界面
	 * @param variableUrl
	 * @param f_sys_name
	 * @param nodeTabName
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/{variableUrl}", method = RequestMethod.POST)
	public String getAttrInfo(@PathVariable String variableUrl, String f_sys_name, String nodeTabName, ModelMap map) {
		log.info("BESZmjkController选中节点加载不同的监控界面 ");
//		map.put("test", "aaaa");

		return "view/realtimemonitoring/xfjk/" + variableUrl;
	}
}
