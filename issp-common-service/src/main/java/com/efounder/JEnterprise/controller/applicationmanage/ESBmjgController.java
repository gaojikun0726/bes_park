package com.efounder.JEnterprise.controller.applicationmanage;

import com.core.common.ISSPReturnObject;
import com.core.common.util.JsonMapper;
import com.efounder.JEnterprise.common.util.DateUtil;
import com.efounder.JEnterprise.model.applicationmanage.ESBmjg;
import com.efounder.JEnterprise.service.applicationmanage.ESBmjgService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/isspview/applicationmanage")
public class ESBmjgController {
	private static final Logger log = LoggerFactory.getLogger(ESBmjgController.class);
	@Autowired
	private ESBmjgService esbmjgService;
	/**
	 * 初始化编码结构列表页
	 * 
	 * @return isspview/applicationmanage/ESBmjg
	 */
	@RequestMapping(value = "/allesbmjg", method = RequestMethod.GET)
	public String allintefaceAdapter() {
		log.info("#初始化编码结构表");
		return "isspview/systemparameters/esBmjg";
	}

	/**
	 * 分页查询
	 * 
	 * @param keywords
	 * @param pageNum
	 * @param map
	 * @return isspview/applicationmanage/ESBmjg_page
	 */
	@RequestMapping(value = "/esBmjg_page", method = RequestMethod.POST)
	public String list_page(String keywords, Integer pageNum, Integer bars, ModelMap map) {
		log.info("#分页查询 pageNum={} , keywords={}", pageNum, keywords);
		PageInfo<ESBmjg> page = esbmjgService.selBmjgpage(bars,pageNum, keywords);
		map.put("page", page);
		map.put("dataset", JsonMapper.toJsonString(page.getList()));
		map.put("keywords", keywords);
		map.put("pageSize", page.getPageSize()+"");
		return "isspview/systemparameters/esBmjg_page";
	}

	/**
	 * 添加编码结构信息
	 * 
	 * @param esbmjg
	 * @return esbmjgService.insBmjg(esbmjg)
	 */
	@RequestMapping(value = "/insBmjg", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject insBmjg(@RequestBody ESBmjg esbmjg) {
		log.info("添加编码结构信息");
		esbmjg.setCrdate(DateUtil.getCurrTime());
		esbmjg.setChdate(DateUtil.getCurrTime());
		return esbmjgService.insBmjg(esbmjg);
	}

	@RequestMapping(value = "/delBmjg", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject delBmjg(String tableName) {
		log.info("删除编码结构信息");
		return esbmjgService.delBmjg(tableName);
	}

	/**
	 * 查询编码结构信息
	 * 
	 * @param fTabn
	 * @return esbmjgService.selectBmjg(fTabn)
	 */
	@RequestMapping(value = "/selectBmjg", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject selectBmjg(String tableName) {
		log.info("查询单行编码结构信息");
		return esbmjgService.selectBmjg(tableName);
	}

	/**
	 * 修改编码结构信息
	 * 
	 * @param esbmjg
	 * @return esbmjgService.updBmjg(esbmjg)
	 */
	@RequestMapping(value = "/updBmjg", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject updBmjg(ESBmjg esbmjg) {
		esbmjg.setChdate(DateUtil.getCurrTime());
		return esbmjgService.updBmjg(esbmjg);
	}
}
