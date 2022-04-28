package com.efounder.JEnterprise.controller.systemparameters;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.service.systemparameters.ESGlobalVarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 类名称：ESGlobalVarController
 * 类描述：全局参数
 * 创建人：huangxianbo
 * 修改人：huangxianbo
 * 修改时间：2018年7月24日
 * @version 1.0.0 
 */
@Controller
@RequestMapping(value = "/isspview/systemparameters")
public class ESGlobalVarController {
	private static final Logger log = LoggerFactory.getLogger(ESGlobalVarController.class);
	
	@Autowired
	private ESGlobalVarService esGlobalVarService;
	
	/**
	 * 根据全局参数key获取当前页面刷新时间
	 * @param key 全局参数对应的key值
	 * @return
	 */
	@RequestMapping(value = "/globalVarByKey", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getGlobalVarByKey(String key) {
		log.info("# ESGlobalVarController 获取全局参数");
		ISSPReturnObject returnObject = esGlobalVarService.getGlobalVarByKey(key);	
		return returnObject;
	}
	
}
