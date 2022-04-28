package com.efounder.JEnterprise.controller.basedatamanage.energyinformation;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESStrategy;
import com.efounder.JEnterprise.service.basedatamanage.energyinformation.BESStrategyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author LiuWenGe
 *
 */
@Controller
@RequestMapping(value = "/view/basedatamanage/energyinformation")
public class BESStrategyController {
	private static final Logger log = LoggerFactory.getLogger(BESStrategyController.class);

	@Autowired
	BESStrategyService besStrategyService;
	/**
	 * 初始化‘报表生成策略配置’界面
	 * @return
	 */
	@RequestMapping(value = "/getStrategy",method = RequestMethod.GET)
	public String getEnergyType () {
		log.info("# BESStrategyController #初始化‘报表生成策略配置’界面");
		return "view/basedatamanage/energyinformation/strategy";
	}

	/**
	 * 生成策略配置树
	 * @return
	 */
	@RequestMapping(value = "/getStrategyTree", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getStrategyTree() {
		log.info("# BESStrategyController 生成策略配置树" );
		return besStrategyService.getStrategyTree();
	}

	/**
	 * 查询该策略的配置信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/queryTableParam", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject queryTableParam(String id){
		log.info("#BESStrategyController 查询该策略的配置信息");
		ISSPReturnObject returnObject = besStrategyService.queryTableParam(id);
		return returnObject;
	}

	/**
	 * 修改策略名称
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/updateStrategyName", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject updateStrategyName(String id,String name){
		log.info("#BESStrategyController 修改策略名称");
		ISSPReturnObject returnObject = besStrategyService.updateStrategyName(id,name);
		return returnObject;
	}

	/**
	 * 修改策略名称
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteStrategy", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject deleteStrategy(String id){
		log.info("#BESStrategyController 删除策略配置");
		ISSPReturnObject returnObject = besStrategyService.deleteStrategy(id);
		return returnObject;
	}

	/**
	 * 生成支路配置树
	 *
	 * @return
	 */
	/*@RequestMapping(value = "/branch_tree", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getTree(String fZzjgbh, String fnybh) {
		log.info("#BESStrategyController 生成支路配置树");
		ISSPReturnObject returnObject = besStrategyService.getTree(fZzjgbh, fnybh);
		return returnObject;
	}*/

	/**
	 * 添加策略配置
	 * @param besStrategy
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject add(BESStrategy besStrategy){
		log.info("#BESStrategyController 添加策略配置");
		ISSPReturnObject returnObject = besStrategyService.add(besStrategy);
		return returnObject;
	}

	/**
	 * 修改策略配置
	 * @param object
	 * @return
	 */
	@RequestMapping(value = "/updateStrategy", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject updateStrategy(@RequestBody JSONObject object){
		log.info("#BESStrategyController 修改策略配置");
		ISSPReturnObject returnObject = besStrategyService.update(object);
		return returnObject;
	}

	/**
	 * cron表达式生成器
	 * @return
	 */
	@RequestMapping(value = "/cronPage", method = RequestMethod.POST)
	public String cronPage(){
		return "view/basedatamanage/energyinformation/strategyCron";
	}

}
