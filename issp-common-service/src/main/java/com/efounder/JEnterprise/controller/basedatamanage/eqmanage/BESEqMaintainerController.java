package com.efounder.JEnterprise.controller.basedatamanage.eqmanage;

import com.core.common.ISSPReturnObject;
import com.core.common.util.JsonMapper;
import com.core.config.db.OperationConfig;
import com.efounder.JEnterprise.common.util.DateUtil;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESEqMaintainer;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BESEqMaintainerService;
import com.framework.common.utils.OperationLog;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 维护商字典
 * @author liuhoujun/yujieying
 *
 */
@Controller
@RequestMapping(value = "/view/basedatamanage/eqmanage")
public class BESEqMaintainerController {
	
	@Autowired
	private BESEqMaintainerService besEqMaintainerService;
	@Autowired
	private OperationConfig operationConfig;
	
	/**
	  * 初始化到主界面
	  * @param map
	  * @return
	  */
	 @RequestMapping(value = "/besEqMaintainer_show", method = RequestMethod.GET)
	 public String getZzjg(){
		 /*PageInfo<BESEqMaintainer> page = besEqMaintainerService.findByPage(null, null);
		 map.put("page", page);
		 map.put("dataset", JsonMapper.toJsonString(page.getList()));*/
		return "view/basedatamanage/eqmanage/besEqMaintainer";
	 }
	
	@RequestMapping(value = "/besEqMaintainer_showPage", method = RequestMethod.POST)
	public String listPage(Integer bars,ModelMap map, Integer pageNum, String keywords){
		  //进行分页查询
				PageInfo<BESEqMaintainer> page = besEqMaintainerService.findByPage(bars,pageNum, keywords);
				map.put("page", page);
				map.put("keywords", keywords);
				map.put("dataset", JsonMapper.toJsonString(page.getList()));
				 map.put("pageSize", page.getPageSize()+"");
		return "view/basedatamanage/eqmanage/besEqMaintainer_page";
	}
	
	/**
	 * 添加维护商信息
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "/besEqMaintainer_add", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject add(BESEqMaintainer vo){
		ISSPReturnObject returnObject = new ISSPReturnObject();
		String f_guid =  UUIDUtil.getRandom32BeginTimePK();
		vo.setF_id(f_guid);
		String date = DateUtil.getCurrTime();
		vo.setF_crdate(date);
		returnObject= besEqMaintainerService.addBESEqMaintainer(vo);
		try {
			String sysDataBaseUseable = operationConfig.getSysDataBaseUseable();
			if ("1".equals(sysDataBaseUseable)) {
				OperationLog.insert(vo.getF_id(), "bes_eq_maintainer");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return returnObject;
	}
	
	/**
	 * 修改维护商信息
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "/besEqMaintainer_update", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject update( BESEqMaintainer vo){
		ISSPReturnObject returnObject = new ISSPReturnObject();
		String date = DateUtil.getCurrTime();
		vo.setF_chdate(date);
		// 修改前后都执行
		try {
			String sysDataBaseUseable = operationConfig.getSysDataBaseUseable();
			if ("1".equals(sysDataBaseUseable)) {
				Map<String, Object> startMap = OperationLog.updateStart(vo.getF_id(), "bes_eq_maintainer");
				returnObject= besEqMaintainerService.updBESEqMaintainer(vo);
				OperationLog.updateEnd(vo.getF_id(), "bes_eq_maintainer", startMap);
			} else {
				returnObject= besEqMaintainerService.updBESEqMaintainer(vo);;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return  returnObject;
	}
	
	/**
	 * 删除维护商信息
	 * @param f_id
	 * @return
	 */
	@RequestMapping(value = "/besEqMaintainer_delete", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public ISSPReturnObject delete(String f_id){
		ISSPReturnObject returnData = new ISSPReturnObject();
		try {
			String sysDataBaseUseable = operationConfig.getSysDataBaseUseable();
			if("1".equals(operationConfig.getSysDataBaseUseable())){
					OperationLog.delete(f_id, "bes_eq_maintainer");
			}
		 returnData =besEqMaintainerService.delBESEqMaintainer(f_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnData;
	}
	
	/**
	 * 根据id获取维护商信息
	 * @param f_id
	 * @return
	 */
	@RequestMapping(value = "/loadByFid", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject loadByFid( String f_id){
	
				ISSPReturnObject returnObject=besEqMaintainerService.findBESEqMaintainerByFid(f_id);
				return returnObject;
	}
}
