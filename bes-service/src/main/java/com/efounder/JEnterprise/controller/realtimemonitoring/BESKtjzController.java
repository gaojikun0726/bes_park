package com.efounder.JEnterprise.controller.realtimemonitoring;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BESSbdyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Alvin 空调机组
 */
@Controller
@RequestMapping(value = "/view/realtimemonitoring/BESKtjz")
public class BESKtjzController {
	private static final Logger log = LoggerFactory.getLogger(BESKtjzController.class);
	@Autowired
	private BESSbdyService besSbdyService;
	/**
	 * 初始化到主界面
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/ktjz", method = RequestMethod.GET)
	public String getZzjg(ModelMap map) {
		log.info("获取信息---->空调机组");
		return "view/realtimemonitoring/ktjk2";
	}
	/**
	 * 初始化到主界面
	 *
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/ktjk2", method = RequestMethod.GET)
	public String getKtjk2(ModelMap map) {
		log.info("获取信息---->空调机组");
		return "view/realtimemonitoring/ktjk2";
	}

	/**
	 * 通过f_sys_name查询一个逻辑点的信息
	 * @param f_sys_name
	 * @return
	 */
	@RequestMapping(value = "/getPointInfo", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getPointInfo(String f_sys_name,String tabname) {
		ISSPReturnObject returnObject = besSbdyService.getPointInfo(f_sys_name,tabname);
		return returnObject;
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
		log.info("BESKtjkController选中节点加载不同的监控界面 ");
		return "view/realtimemonitoring/ktjk/" + variableUrl;
	}

}
