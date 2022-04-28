package com.efounder.JEnterprise.controller.applicationmanage;

import com.core.common.ISSPReturnObject;
import com.core.config.db.OperationConfig;
import com.efounder.JEnterprise.model.applicationmanage.ESXtList;
import com.efounder.JEnterprise.service.applicationmanage.ESXtListService;
import com.framework.common.utils.OperationLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;
/**
 * @author menghaixiao
 * 系统定义
 *
 */
@Controller
@RequestMapping(value = "/view/systemcenter")
public class ESXtListController {
	
	private static final Logger log = LoggerFactory.getLogger(ESXtListController.class);
	
	@Autowired
	private ESXtListService esXtService;
	@Autowired
	private OperationConfig operationConfig;
	
	/**
	  * 初始化到主界面
	  * @param map
	  * @return
	  */
	 @RequestMapping(value = "/esXtList", method = RequestMethod.GET)
	 public String getEsXtlistshow(ModelMap map){
		 log.info("ESXtListController获取信息");
		return "isspview/applicationmanage/esXtList";
	 }
	 
	 /**
	  * 数据全量展示
	  * @return
	  */
	 @RequestMapping(value = "/loadAllEsXt", method = RequestMethod.GET)
	 @ResponseBody
	 public ISSPReturnObject getAllEsXtList(){
		 ISSPReturnObject returnData = new ISSPReturnObject();
		 List<ESXtList> list = new ArrayList<ESXtList>();
		 list = esXtService.findXts();
		 returnData.setList(list);
		 return returnData;
	 }
	 
	 @RequestMapping(value = "/loadEsXtSearch", method = RequestMethod.GET)
	 @ResponseBody
	 public ISSPReturnObject getSearchList(String keywords){
		 ISSPReturnObject returnData = new ISSPReturnObject();
		 List<ESXtList> list = new ArrayList<ESXtList>();
		 list = esXtService.getXtListByKeywords(keywords);
		 returnData.setList(list);
		 return returnData;
	 }
	 
	 /**
	  * 添加系统
	  * @param eSXt
	  * @param model
	  * @return
	  */
	 @RequestMapping(value = "/esxt_add", method = RequestMethod.POST)
	 @ResponseBody
	 @Transactional
	 public ISSPReturnObject addESXt(@RequestBody ESXtList eSXt, ModelMap model){
		 Map<String, String> result = new HashMap<>();
		 ISSPReturnObject returnData = new ISSPReturnObject();
		 boolean isSuccess = esXtService.addXt(eSXt);
		 try {
			 if("1".equals(operationConfig.getSysDataBaseUseable())){
				 OperationLog.insert(eSXt.getF_xtbh(), "es_xt_list");
			 }
			 if(isSuccess) {
				 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				 result.put("f_xtbh", eSXt.getF_xtbh());
				 result.put("f_xtmc", eSXt.getF_xtmc());
		    	 result.put("f_remark", eSXt.getF_remark());
		    	 result.put("f_crdate", sdf.format(new Date()));
				 result.put("f_chdate", sdf.format(new Date()));
				 returnData.setData(result);
				 returnData.setStatus("1");
				 returnData.setMsg("添加系统成功");
			 }else {
				 returnData.setStatus("0");
				 returnData.setMsg("添加系统失败");
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return returnData;
	 }
	 
	 @RequestMapping(value = "/esxt_del", method = RequestMethod.POST)
	 @ResponseBody
	 @Transactional
	 public ISSPReturnObject delEsxt(@RequestBody ESXtList eSXt) {
		 ISSPReturnObject iSSPReturnObject = new ISSPReturnObject();
		 try {
			 if("1".equals(operationConfig.getSysDataBaseUseable())){
				 OperationLog.delete(eSXt.getF_xtbh(), "es_xt_list");
			 }
			iSSPReturnObject = esXtService.delEsxt(eSXt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return iSSPReturnObject;
	 }
	 
	 @RequestMapping(value = "/esxt_upd", method = RequestMethod.POST)
	 @ResponseBody
	 @Transactional
	 public ISSPReturnObject updEsxt(@RequestBody ESXtList eSXt) {
		 ISSPReturnObject iSSPReturnObject = esXtService.editEsxt(eSXt);
		 return iSSPReturnObject;
	 }
	
	 @RequestMapping(value = "/esxt_loadEditObj", method = RequestMethod.POST)
	 @ResponseBody
	 public ISSPReturnObject getEsxtByBh(@RequestBody ESXtList eSXt) {
		 ISSPReturnObject returnData = new ISSPReturnObject();
		 ESXtList obj = esXtService.findXtById(eSXt.getF_xtbh());
		 returnData.setData(obj);
		 return returnData;
	 }
}
