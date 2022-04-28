package com.efounder.JEnterprise.controller.basedatamanage.eqmanage;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESModuleType;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BESModuleTypeService;
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

import java.util.ArrayList;
import java.util.List;

/**
 * 模块类型定义
 * @author LvSihan
 *
 */
@Controller
@RequestMapping(value = "/view/basedatamanage/eqmanage")
public class BESModuleTypeController {
	private static final Logger log = LoggerFactory.getLogger(BESModuleTypeController.class);

	@Autowired
	private BESModuleTypeService moduleTypeService;

	/**
	 * 初始化模块类型页面
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/moduleType", method = RequestMethod.GET)
	public String getModuleType() {
		log.info("# BESModuleTypeController获取模块型号信息");
		return "besview/basedatamanage/eqmanage/moduleType";

	}

	/**
	 * 分页查询 
	 * @param keywords
	 * @param pageNum
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/moduleType_page", method = RequestMethod.POST)
	public String list_page(@RequestParam(value = "keywords", required = false) String keywords,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "bars", required = false) Integer bars, ModelMap map) {
		log.info("#分页查询 pageNum={} , keywords={}", pageNum, keywords);
		PageInfo<BESModuleType> page = moduleTypeService.findmtByPage(bars,pageNum, keywords);
		//List<BESModuleType> list = getfModulepointType(page.getList());
		map.put("pageSize", page.getPageSize());
		map.put("page", page);
		String jsonString = JSONObject.toJSONString(page.getList());
		map.put("pageList", jsonString);
		map.put("keywords", keywords);
		return "besview/basedatamanage/eqmanage/moduleType_page";
	}


	/**
	 * 添加设备型号信息
	 * @param json
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/moduleType_add", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject addModuleType(BESModuleType moduletype, ModelMap model) {
		log.info("#BESModuleTypeController添加模块型号信息");
		ISSPReturnObject returnObject = moduleTypeService.addModuleType(moduletype);
		return returnObject;
	}

	/**
	 * 删除设备型号信息
	 * 
	 * @param f_type
	 * @return
	 */
	@RequestMapping(value = "/moduleType_del", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject delModuleType(BESModuleType moduletype) {
		log.info("#BESModuleTypeController删除模块型号信息");
		ISSPReturnObject returnObject = moduleTypeService.delModuleType(moduletype);
		return returnObject;
	}

	/**
	 * 查询单条模块型号信息
	 * @param fModuleType
	 * @return
	 */
	@RequestMapping(value = "/moduleType_edit", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject editModuleType(@RequestParam(value = "fModuleType", required = false) String fModuleType) {
		log.info("#BESModuleTypeController通过fModuleType加载模块型号信息");
		ISSPReturnObject returnObject = moduleTypeService.selectByPrimaryKey(fModuleType);
		return returnObject;
	}

	/**
	 * 更新修改模块型号
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/moduleType_update", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject upModuleType(BESModuleType moduletype, ModelMap model) {
		log.info("#BESModuleTypeController更新修改模块型号信息");
		ISSPReturnObject returnObject = moduleTypeService.upModuleType(moduletype);
		return returnObject;
	}
	
	/**
	 * 下拉框选择列表
	 * @param moduletype
	 * @return
	 */
	@RequestMapping(value = "/selectList",method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject selectList(BESModuleType moduletype) {
		log.info("#BESModuleTypeController获取添加，编辑窗口中的下拉框列表");
		ISSPReturnObject returnObject = moduleTypeService.selectList(moduletype);//添加，编辑窗口中的select列表
		return returnObject;		
	}
	
	
	/**
	 * 模块点集合下拉框列表展示
	 * @param pagelist
	 * @return
	 */
	public List<BESModuleType> getfModulepointType(List<BESModuleType> pagelist) {
		List<BESModuleType> mt =  pagelist;

 		for (int i = 0; i < mt.size(); i++) {
 			List<BESModuleType> ClString = new ArrayList<BESModuleType>();
			String fPointTypeCl = mt.get(i).getfPointTypeCl();
			BESModuleType moduleTy  = new BESModuleType();
			final StringBuffer sb = new StringBuffer("{");

			for (int j = 0; j < fPointTypeCl.length(); j++) {
				String[] array = fPointTypeCl.split("");
				String fId = array[j];
				int m = j+1;
				moduleTy.setfId(fId);			
				moduleTy.setfModulepointType(mt.get(i).getfModulepointType());			
				moduleTy.setfDescription(mt.get(i).getfDescription());			
				moduleTy.setfModuleType(mt.get(i).getfModuleType());			
				moduleTy.setfPointAmount(mt.get(i).getfPointAmount());			
				moduleTy.setfPointTypeCl(mt.get(i).getfPointTypeCl());
				//模块类型表中的点类型以“2456”保存，需单独查询出“2”“4”“5”“6”对应的型号，便于展示。
				ClString.addAll(j, moduleTypeService.selectPointTypeCl(moduleTy));
				
				sb.append("\""+m+"--" +ClString.get(j).getfModulepointType()+"\",");
			}
			sb.append("}");
			moduleTy.setLinshi(sb);
			mt.set(i, moduleTy);
		}
		return mt;
	}
}
