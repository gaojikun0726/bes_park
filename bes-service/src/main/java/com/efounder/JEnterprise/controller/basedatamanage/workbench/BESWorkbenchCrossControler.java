package com.efounder.JEnterprise.controller.basedatamanage.workbench;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.BesWorkbench.BESWorkbenchCrossType;
import com.efounder.JEnterprise.service.basedatamanage.workbench.BESWorkbenchCrossService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* @author  liwenjei
* @version 创建时间：2018年11月29日 下午3:15
* @parameter 
* @version 1.0
*/
@Controller
@RequestMapping(value = "/view/basedatamanage/workbench")

public class BESWorkbenchCrossControler {
	private static final Logger log = LoggerFactory.getLogger(BESWorkbenchCrossControler.class);
	@Autowired
	private BESWorkbenchCrossService workbenchcrossservice;
	
	/**
	 * 工作台cross配置初始化页面
	 * @return
	 */
	@RequestMapping(value = "/getWorkbenchCross" ,method = RequestMethod.GET)
	public String getDatecenter (ModelMap model) {
		log.info("# BESWorkbenchCross #初始化‘工作台cross配置’界面");
		return "/view/basedatamanage/workbench/workbenchcross/workbenchcross";
	}
	
	/**
	 * 回显方法  使用ID查询
	 * */
	@RequestMapping(value = "/getWorkbench" ,method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getWorkbench(String bh) {
		log.info("回显查询");
		return workbenchcrossservice.getWorkbench(bh);
	}
	
	/**
	 * 点击左侧树刷新右侧表格
	 */
	@RequestMapping(value = "/getWorkbenchTable" ,method = RequestMethod.POST)
	@ResponseBody
		public ISSPReturnObject getWorkbenchTable(String f_gztzzjg_id) {
			return workbenchcrossservice.getWorkbenchTable(f_gztzzjg_id);
	}
	
	
	/**
	 * 添加
	 */
	@RequestMapping(value = "/add_WorkbenchCross", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject add_WorkbenchCross(BESWorkbenchCrossType besworkbenchcrosstype) {
		log.info("#添加");
		return workbenchcrossservice.add_WorkbenchCross(besworkbenchcrosstype);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "/del_WorkbenchCross", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject del_WorkbenchCross(String id) {
		log.info("#删除");
		return workbenchcrossservice.del_WorkbenchCross(id);
	}
	
	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit_WorkbenchCross", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject edit_WorkbenchCross(BESWorkbenchCrossType besworkbenchcrosstype) {
		log.info("更新");
		return workbenchcrossservice.edit_WorkbenchCross(besworkbenchcrosstype);
	}

}
