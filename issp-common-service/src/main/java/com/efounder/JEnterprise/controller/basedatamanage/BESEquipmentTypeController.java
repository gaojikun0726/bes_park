package com.efounder.JEnterprise.controller.basedatamanage;

import com.core.common.ISSPReturnObject;
import com.core.common.util.JsonMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESEquipmentBrand;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESEquipmentType;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BESEquipmentTypeService;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BES_EquipmentBrandService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 设备型号定义
 * @author LvSihan
 *
 */
@Controller
@RequestMapping(value = "/view/basedatamanage/eqmanage")
public class BESEquipmentTypeController {
	private static final Logger log = LoggerFactory.getLogger(BESEquipmentTypeController.class);

	@Autowired
	private BESEquipmentTypeService equipmentTypeService;
	
	@Autowired
	private BES_EquipmentBrandService besrandservice;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

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
	 * 获取品牌id与名称
	 * 下拉框
	 * @author suhang
	 * @return
	 */
	@RequestMapping(value = "/getbrand", method = RequestMethod.GET)
	@ResponseBody
	public List<BESEquipmentBrand> getBrand() {
		List<BESEquipmentBrand> list = new ArrayList<BESEquipmentBrand>();
		list = besrandservice.getBrand();
		return list;
	}

	/**
	 * 分页查询 分页与设备树关联
	 * @author suhang
	 * @param f_sblxbh
	 * @param pageNum
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/equipmentType_page", method = RequestMethod.POST)
	public String list_page( String f_sblxbh, String keywords,Integer pageNum, ModelMap map,Integer bars) {
		log.info("#分页查询 pageNum={} , f_sblxbh={}", pageNum, f_sblxbh);
		PageInfo<BESEquipmentType> page = equipmentTypeService.findemTByPage(bars,pageNum, f_sblxbh,keywords);
		map.put("page", page);
		 map.put("pageSize", page.getPageSize()+"");
		//将数据转化为json字符串 add by wujf at 20180623
		String jsonString = JsonMapper.toJsonString(page.getList());
		String jsonrepalce = jsonString.replace("\\n", "");//获取json时 有字符串出现\n字符 前端不能解析 通过replac处理掉该字符
		map.put("dataset", jsonrepalce);
		String pageN =null;
		if(String.valueOf(page.getPageNum()).indexOf(",")!=-1)
			pageN = String.valueOf(page.getPageNum()).replaceAll(",","");
		else
			pageN = String.valueOf(page.getPageNum());
		map.put("pageNum", pageN);
		map.put("f_sblxbh", f_sblxbh);
		map.put("keywords", keywords);
		
		//map.put("zzjgbh", zzjgbh);
		return "view/basedatamanage/eqmanage/equipmentType_page";
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
		log.info("#BESEquipmentTypeController更新修改设备型号信息");
		ISSPReturnObject returnObject = equipmentTypeService.upEmt(equipmenttype);
		log.info("#BESEquipmentTypeController更新修改设备型号信息");
		return returnObject;
	}
	
	/**
	 * 查找子类
	 * @param fType
	 * @return
	 */
	@RequestMapping(value = "/seleqtypechildren", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject seleqtypechildren (String f_sblxbh) {
		log.info("查找设备类型子类设备型号");
		//统一返回格式
		ISSPReturnObject returnObject = equipmentTypeService.seleqtypechildren(f_sblxbh);
		return returnObject;
	}
	/**
	 * 根据设备型号编号查询设备类型编号
	 * @param f_type
	 * @return
	 */

@RequestMapping(value = "/convertSblxbh",method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getbh(String f_type) {
		log.info("#BESEquipmentTypeController ajax型号编号查询设备类型编号");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<BESEquipmentType> list =  equipmentTypeService.getbh(f_type);
		returnObject.setList(list);
		return returnObject;
	}


}
