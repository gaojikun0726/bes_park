package com.efounder.JEnterprise.controller.datacenter;

import com.core.config.db.OperationConfig;
import com.efounder.JEnterprise.controller.usercenter.ESUserController;
import com.efounder.JEnterprise.model.basedatamanage.zzjg.ESZzjg;
import com.efounder.JEnterprise.service.basedatamanage.zzjg.ESZzjgService;
import com.framework.common.utils.OperationLog;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(value = "/view/datacenter")
public class ESBmzdController {

	 private static final Logger log = LoggerFactory.getLogger(ESUserController.class);
	 
	 @Autowired
	 private ESZzjgService esZzjgService;
	 @Autowired
		private OperationConfig operationConfig;
	 
	 @InitBinder
	 public void initBinder (WebDataBinder binder){
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 dateFormat.setLenient(false);
		 binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	 }
	 /**
	  * 初始化到主界面
	  * @param map
	  * @return
	  */
	 @RequestMapping(value = "/bmzd", method = RequestMethod.GET)
	 public String getZzjg(ModelMap map){
		 log.info("ESBmzdControllerr获取信息");
		return "view/datacenter/bmzd";
	 }
	
	 
	 @RequestMapping(value= "/bmzd_add",method = RequestMethod.POST)
	 @ResponseBody 
	 public Map<String,String> addBmzd(ESZzjg zzjg, ModelMap model){
		 Map<String,String> result = new HashMap<>();
		 log.info("#ESZzjgController添加组织机构信息");
		 boolean isSucc = esZzjgService.addZzjg(zzjg);
		 try {
			 if("1".equals(operationConfig.getSysDataBaseUseable())){ 
				 OperationLog.insert(zzjg.getF_id(), "es_zzjg");
			 }
			 if (isSucc) {
				result.put("status", "1");
				result.put("msg", "添加组织机构成功");
			} else {
				result.put("status", "0");
				result.put("msg", "添加失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return result;
	 }
}
