package com.efounder.JEnterprise.controller.basedatamanage.eqmanage;


import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.util.DateUtil;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESSbTreeNodeType;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BesSbtreeNodetypeService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/view/basedatamanage/eqmanage")
public class BesSbtreeNodetypeController {
	
	@Autowired
	private BesSbtreeNodetypeService besSbtreeNodetypeService;
	private static final Logger log = LoggerFactory.getLogger(BesSbtreeNodetypeController.class);
	
	/**
	 * 初始化节点类型列表页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/allbessbtreenodeType", method = RequestMethod.GET)
	public String allintefaceAdapter() {
		log.info("#初始化设备树节点类型");
		return "besview/basedatamanage/eqmanage/bessbtreenodeType";
	}
	
	/**
	 * 分页查询 
	 * @param keywords
	 * @param pageNum
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/getbessbtreenodeType", method = RequestMethod.POST)
	public String list_page(
			@RequestParam(value = "keywords", required = false) String keywords,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "bars", required = false) Integer bars,
			ModelMap map) {
		log.info("#分页查询 pageNum={} , keywords={}", pageNum, keywords);
		PageInfo<BESSbTreeNodeType> page = besSbtreeNodetypeService.selBesSbtreeNodetypepage(pageNum,bars, keywords);
		map.put("pageSize", page.getPageSize());
		map.put("page", page);
		String jsonString = JSONObject.toJSONString(page.getList());
		map.put("pageList", jsonString);
		map.put("keywords", keywords);
		return "besview/basedatamanage/eqmanage/bessbtreenodeType_page";
	}
	
	@RequestMapping(value = "/delsbtreeNodetype", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject delBesSbtreeNodetype(String f_node_type) {
		log.info("#删除设备树节点类型型号信息");
		ISSPReturnObject returnObject = besSbtreeNodetypeService.delBesSbtreeNodetype(f_node_type);
		return returnObject;
	}
	
	/**
	 * 添加设备树节点类型
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/addbesSbtreeNodetype" , method = RequestMethod.POST)
	@ResponseBody
	ISSPReturnObject insBesSbtreeNodetype(@RequestBody BESSbTreeNodeType besSbTreeNodeType) {
		log.info("#添加节点类型列表信息");
		String date = DateUtil.getCurrTime();
		String f_crdate = date;
		String f_chdate = date;
		besSbTreeNodeType.setF_chdate(f_chdate);
		besSbTreeNodeType.setF_crdate(f_crdate);
		return besSbtreeNodetypeService.insBesSbtreeNodetype(besSbTreeNodeType);
	}
	
	/**
	 * 设备树节点类型单行查询
	 * 
	 * @param fGuid
	 * @return
	 */
	@RequestMapping(value = "/querybesSbtreeNodetype", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject queryBES(String f_node_type) {
		log.info("查询单行设备树节点类型信息");
		return besSbtreeNodetypeService.selectBesSbtreeNodetype(f_node_type);
	}
	
	/**
	 * 设备树节点类型信息
	 * 
	 * @param isspEquipmentadapter
	 * @return
	 */
	@RequestMapping(value = "/updatebesSbtreeNodetype", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject updISSPEquipmentadapter(BESSbTreeNodeType besSbTreeNodeType) {
		log.info("更新设备树节点类型信息");
		String date = DateUtil.getCurrTime();
		String f_chdate = date;
		besSbTreeNodeType.setF_chdate(f_chdate);
		return besSbtreeNodetypeService.updateBesSbtreeNodetype(besSbTreeNodeType);
	}

	/**
	* @Author:         YangChao
	* @CreateDate:     2019/1/25 10:58
	* @Description:    文件上传后台接收方法
	*/
	@RequestMapping(value = "/treeFileUpload", method = RequestMethod.POST)
	@ResponseBody
	public Object treeFileUpload(HttpServletRequest request, @RequestParam(required = false, value = "file") MultipartFile multipartFile) throws Exception {
		ISSPReturnObject returnObject = besSbtreeNodetypeService.impExcel(request, multipartFile);
		return returnObject;
	}
	
}
