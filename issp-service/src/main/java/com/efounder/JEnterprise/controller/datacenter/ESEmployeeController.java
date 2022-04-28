package com.efounder.JEnterprise.controller.datacenter;

import com.efounder.JEnterprise.model.datacenter.ESEmployee;
import com.efounder.JEnterprise.service.datacenter.ESEmployeeService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/view/esEmployee")
public class ESEmployeeController {
	
	private static final Logger log = LoggerFactory.getLogger(ESEmployeeController.class);
	
	@Autowired
	private ESEmployeeService esEmployeeService;
	
	/**
	  * 初始化到主界面
	  * @param map
	  * @return
	  */
	 @RequestMapping(value = "/employee", method = RequestMethod.GET)
	 public String getZzjg(ModelMap map){
		 PageInfo<ESEmployee> page = esEmployeeService.findByPage(null, null);
		 map.put("page", page);
		return "view/datacenter/employee";
	 }
	 
	 /**
	  * 分页
	  * @param keywords
	  * @param pageNum
	  * @param map
	  * @return
	  */
	 @RequestMapping(value = "/showPage", method = RequestMethod.POST)
	 public String listPage(@RequestParam(value = "keywords", required = false) String keywords,
				@RequestParam(value = "pageNum", required = false) Integer pageNum, ModelMap map) {
		 PageInfo<ESEmployee> page = esEmployeeService.findByPage(pageNum, keywords);
		 map.put("page", page);
		 map.put("keywords", keywords);
		 return "view/datacenter/employee_page";
	 }
}
