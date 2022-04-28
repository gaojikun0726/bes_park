package com.efounder.JEnterprise.controller.basedatamanage;

import com.core.common.ISSPReturnObject;
import com.core.common.util.JsonMapper;
import com.core.config.db.OperationConfig;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESEquipmentBrand;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BES_EquipmentBrandService;
import com.framework.common.utils.OperationLog;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/** 
 * 类名称：BESEquipmentBrandController
 * 类描述：品牌字典Controller
 * 创建人：sunhao
 * 修改人：sunhao
 * 修改时间：2018年6月26日 
 * 修改备注：
 * @version 1.0.0 
 *
 */
@RequestMapping(value = "/view/brand")
@Controller
public class BESEquipmentBrandController {
	private static final Logger log = LoggerFactory.getLogger(BESEquipmentBrandController.class);
	
	@Autowired 
	private BES_EquipmentBrandService equipmentBrandService;
	@Autowired
	private OperationConfig operationConfig;
	
	
	@RequestMapping(value = "/depa", method = RequestMethod.GET)
	public String getESDepartment(ModelMap map) {
		log.info("# BESEquipmentBrandController获取信息");
		return "view/basedatamanage/eqmanage/eqmanage_ppzd";
	}
	/**
	 * 分页查询
	 * @param keywords
	 * @param pageNum
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/equipmentBrand_page", method = RequestMethod.POST)
	public String list_page(@RequestParam(value = "keywords", required = false) String keywords,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,Integer bars, ModelMap map) {
		log.info("#分页查询 pageNum={} , keywords={}", pageNum, keywords);
		PageInfo<BESEquipmentBrand> page = equipmentBrandService.findEquipmentBrandByPage(bars,pageNum, keywords);
		map.put("page", page);
		//将数据转化为json字符串 add by wujf at 20180623
		map.put("dataset", JsonMapper.toJsonString(page.getList()));
		map.put("keywords", keywords);
		map.put("pageSize", page.getPageSize()+"");
		return "view/basedatamanage/eqmanage/eqmanagePpzd_page";
	}
	/**
	 * 根据关键字查询信息
	 * @param keywords
	 * @param pageNum
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/findByKeywords", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject findESDepartmentByKeywords(@RequestParam(value = "keywords", required = false) String keywords) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<BESEquipmentBrand> list = new ArrayList<BESEquipmentBrand>();
		list = equipmentBrandService.findEquipmentBrandByKeywords(keywords);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getF_brandbh() == null|| "".equals(list.get(i).getF_brandbh())) {
				list.get(i).setF_brandbh(null);
			}
		}
		returnObject.setList(list);
		return returnObject;
	}
	/**
	 * 添加信息
	 * @param json
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addEquipmentBrand", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject addUser(@RequestBody BESEquipmentBrand equipmentBrand, ModelMap model) {
		log.info("#BESEquipmentBrandController添加信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			String f_id =  UUIDUtil.getRandom32BeginTimePK();
			equipmentBrand.setId(f_id);
			boolean isSucc = equipmentBrandService.addEquipmentBrand(equipmentBrand);
			if("1".equals(operationConfig.getSysDataBaseUseable())){
					OperationLog.insert(equipmentBrand.getId(), "bes_equipment_brand");
			}
			if (isSucc) {
				returnObject.setStatus("1");
				returnObject.setMsg("添加信息成功");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				equipmentBrand.setF_chdate(sdf.format(new Date()));
				equipmentBrand.setF_crdate(sdf.format(new Date()));
				returnObject.setData(equipmentBrand);
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("添加信息失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;

	}
	/**
	 * 删除信息
	 * @param f_brandbh
	 * @return
	 */
	@RequestMapping(value = "/deleteEquipmentBrand", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject delUser(@RequestBody BESEquipmentBrand equipmentBrand, ModelMap model) {
		log.info("#BESEquipmentBrandController删除信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			if("1".equals(operationConfig.getSysDataBaseUseable())){
					OperationLog.delete(equipmentBrand.getId(), "bes_equipment_brand");
			}
			boolean isSucc = equipmentBrandService.deleteByBrandbh(equipmentBrand);
			if (isSucc) {
				returnObject.setStatus("1");
				returnObject.setMsg("删除用户成功");
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("删除用户失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;
	}
	/**
	 * 更新修改信息
	 * @param 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateEquipmentBrand", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject updateESDepartment(BESEquipmentBrand equipmentBrand, ModelMap model) {

		log.info("#BESEquipmentBrandController更新修改信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			Map<String, Object> startMap = null;
			boolean isSucc = true;
			if("1".equals(operationConfig.getSysDataBaseUseable())){
				startMap = OperationLog.updateStart(equipmentBrand.getId(), "bes_equipment_brand");
				isSucc = equipmentBrandService.upDateEquipmentBrand(equipmentBrand);
				OperationLog.updateEnd(equipmentBrand.getId(), "bes_equipment_brand", startMap);
			}else{
				isSucc = equipmentBrandService.upDateEquipmentBrand(equipmentBrand);
			}
			if (isSucc) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				equipmentBrand.setF_chdate(sdf.format(new Date()));
				returnObject.setStatus("1");
				returnObject.setMsg("更新修改成功");
				returnObject.setData(equipmentBrand);
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("更新修改失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;
	}
	
	/**
	 * ajax加载编辑对象
	 * @param f_zzjgbh
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/loadeditobj", method = RequestMethod.POST)
	@ResponseBody
	public BESEquipmentBrand loadedit(@RequestBody BESEquipmentBrand EquipmentBrand) {
		log.info("# ajax加载编辑组织机构对象");
		BESEquipmentBrand equipmentBrand = equipmentBrandService.findEquipmentBrandByBrandbh(EquipmentBrand.getId());
		return equipmentBrand;
	}
	
	/**
	 * 获取品牌列表
	 * @param
	 * @return
	 * 2018/7/12
	 * tianjiangwei
	 */
	
	@RequestMapping(value = "/loadbrand", method = RequestMethod.GET)
	@ResponseBody
	public List<BESEquipmentBrand> getBrand() {
		List<BESEquipmentBrand> list = new ArrayList<BESEquipmentBrand>();
		list = equipmentBrandService.getBrand();
		return list;
	}
	
}
