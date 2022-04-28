package com.efounder.JEnterprise.controller.applicationmanage;

import com.core.common.ISSPReturnObject;
import com.core.common.util.JsonMapper;
import com.efounder.JEnterprise.model.applicationmanage.ESHomeModule;
import com.efounder.JEnterprise.service.applicationmanage.ESHomeModuleService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 
 * @author menghaixiao
 * 模块定义
 */
@Controller
@RequestMapping(value = "/view/esHomeModule")
public class EsHomeModuleController {

	private static final Logger log = LoggerFactory.getLogger(EsHomeModuleController.class);
	
	@Autowired
	private ESHomeModuleService esHomeModuleService;
	
	/**
	  * 初始化到主界面
	  * @param map
	  * @return
	  */
	 @RequestMapping(value = "/show", method = RequestMethod.GET)
	 public String getEsXtlistshow(ModelMap map){
		 log.info("EsHomeModuleController获取信息");
		 
		 PageInfo<ESHomeModule> page = esHomeModuleService.findByPage(null,null, null);
		 map.addAttribute("page", page);
		return "isspview/applicationmanage/esHomeModule";
	 }
	 
	 /**
	  * 分页、条件查询
	  * @param keywords
	  * @param pageNum
	  * @param map
	  * @return
	  */
	 @RequestMapping(value = "/showPage", method = RequestMethod.POST)
	 public String listPage(@RequestParam(value = "keywords", required = false) String keywords,
				@RequestParam(value = "pageNum", required = false) Integer pageNum, ModelMap map,Integer bars) {
		 PageInfo<ESHomeModule> page = esHomeModuleService.findByPage(bars,pageNum, keywords);
		 map.put("page", page);
		 map.put("pageSize", page.getPageSize()+"");
		 map.put("dataset", JsonMapper.toJsonString(page.getList()));
		 map.put("keywords", keywords);
		 return "isspview/applicationmanage/esHomeModule_page";
	 }
	 
	 /**
	  * 添加模块信息
	  * @param esHomeModule
	  * @return
	  */
	 @RequestMapping(value = "/add", method = RequestMethod.POST)
	 @ResponseBody
	 @Transactional
	 public ISSPReturnObject addEsHomeModule(@RequestBody ESHomeModule esHomeModule) {
		 return esHomeModuleService.addESHomeModule(esHomeModule);
	 }
	 
	 /**
	  * 删除模块信息
	  * @param guid
	  * @return
	  */
	 @RequestMapping(value = "/delete", method = RequestMethod.POST)
	 @ResponseBody
	 @Transactional
	 public ISSPReturnObject delEsHomeModule(@RequestParam(name="guid") String guid) {
		 return esHomeModuleService.delESHomeModule(guid);
	 }
	 
	 /**
	  * 更新模块信息
	  * @param esHomeModule
	  * @return
	  */
	 @RequestMapping(value = "/update", method = RequestMethod.POST)
	 @ResponseBody
	 @Transactional
	 public ISSPReturnObject updEsHomeModule(@RequestBody ESHomeModule esHomeModule) {
		 return esHomeModuleService.updESHomeModule(esHomeModule);
	 }
	 
	 /**
	  * 根据guid获取模块
	  * @param guid
	  * @return
	  */
	 @RequestMapping(value = "/loadEditObj", method = RequestMethod.POST)
	 @ResponseBody
	 public ISSPReturnObject loadEditObj(@RequestParam(name="guid") String guid) {
		 return esHomeModuleService.loadESHomeModuleByGuid(guid);
	 }
}
