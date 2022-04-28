package com.efounder.JEnterprise.controller.basedatamanage.eqmanage;

import com.core.common.ISSPReturnObject;
import com.core.common.util.JsonMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESEquipmentType;
import com.efounder.JEnterprise.service.GztService;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BESEquipmentTypeService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 设备型号定义
 * @author LvSihan
 *
 */
@Controller
@RequestMapping(value = "/view/basedatamanage/beseqmanage")
public class BESEquipmentTController {
	private static final Logger log = LoggerFactory.getLogger(BESEquipmentTController.class);

	@Autowired
	private BESEquipmentTypeService equipmentTypeService;
	
	@Autowired
	private GztService gztservice;

	/**
	 * 初始化设备设备型号定义
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/equipmentType", method = RequestMethod.GET)
	public String getEquipmentType() {
		log.info("# BESEquipmentTypeController初始化设备设备型号定义");		
		return "view/basedatamanage/eqmanage/equipmentType";
		
	}
	
	/**
	 * 生成左侧设备类型树
	 * @param fZzjgbh
	 * @return
	 */
	@RequestMapping(value = "/sblx_tree", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject sblx_tree () {
		log.info("BESEquipmentTypeController设备类型树");
		//统一返回格式
		ISSPReturnObject returnObject = equipmentTypeService.sblx_tree();
		return returnObject;
	}
	/**
	 * 工作台组织机构树
	 * @return
	 */
	@RequestMapping(value = "/Gztzzjg_tree", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject Gztzzjg_tree () {
		log.info("工作台 GHEEViewController 组织机构树");
		//统一返回格式
		ISSPReturnObject returnObject = gztservice.gztzzjg_tree();
		return returnObject;
	}
	

	/**
	 * 分页查询
	 * 
	 * @param keywords
	 * @param pageNum
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/equipmentType_page", method = RequestMethod.POST)
	public String list_page(@RequestParam(value = "keywords", required = false) String keywords,
			@RequestParam(value = "pageNum", required = false) Integer pageNum, ModelMap map,@RequestParam(value="treeId",required=false)String treeId) {
		log.info("#分页查询 pageNum={} , keywords={}", pageNum, keywords);
		PageInfo<BESEquipmentType> page = equipmentTypeService.findSblxPage(pageNum, keywords, treeId);
		map.put("page", page);
		//将数据转化为json字符串 add by wujf at 20180623
		map.put("dataset", JsonMapper.toJsonString(page.getList()));
		map.put("keywords", keywords);
		return "view/basedatamanage/eqmanage/equipmentType_page";
	}

	//树table
	@RequestMapping(value = "/tree_table", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject tree_table (String treeId) {
		ISSPReturnObject obj=equipmentTypeService.tree_table(treeId);
		return obj;
	}

	/**
	 * 添加设备型号信息
	 * 
	 * @param json
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/equipmentType_add", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject addEquipmentType(BESEquipmentType equipmenttype, ModelMap model) {
		log.info("#BESEquipmentTypeController添加设备型号信息");
		ISSPReturnObject returnObject = equipmentTypeService.addEquipmentType(equipmenttype);
		return returnObject;
	}

	/**
	 * 删除设备型号信息
	 * 
	 * @param f_type
	 * @return
	 */
	@RequestMapping(value = "/equipmentType_del", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject delEquipmentType(BESEquipmentType equipmenttype) {
		log.info("#BESEquipmentTypeController删除设备型号信息");
		ISSPReturnObject returnObject = equipmentTypeService.delEquipmentType(equipmenttype);
		return returnObject;
	}

	/**
	 * 通过id加载设备型号信息
	 * 
	 * @param f_type
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/equipmentType_edit", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject editEquipmentType(String f_type) {
		log.info("#BESEquipmentTypeController通过f_type加载设备型号信息");
		ISSPReturnObject returnObject = equipmentTypeService.findEmtById(f_type);
		return returnObject;
	}

	/**
	 * 更新修改用户信息
	 * 
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/equipmentType_update", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject upEmt(BESEquipmentType equipmenttype, ModelMap model) {
		ISSPReturnObject returnObject = equipmentTypeService.upEmt(equipmenttype);
		log.info("#BESEquipmentTypeController更新修改设备型号信息");
		return returnObject;
	}

}
