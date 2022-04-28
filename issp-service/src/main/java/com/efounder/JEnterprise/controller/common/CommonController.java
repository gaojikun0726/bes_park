package com.efounder.JEnterprise.controller.common;

import com.efounder.JEnterprise.model.applicationmanage.ESXtList;
import com.efounder.JEnterprise.model.auth.PermissionModule;
import com.efounder.JEnterprise.service.applicationmanage.ESXtListService;
import com.efounder.JEnterprise.service.auth.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/view/common")
public class CommonController {
	@Autowired
	private ESXtListService esXtService;
	@Autowired
	private PermissionService permissionService;
	 /**
     * @author gongfanfei
     * @param
     * @desciption 系统列表
     */
    @RequestMapping(value = "/loadAllXt", method = RequestMethod.POST)
    @ResponseBody
    public List<ESXtList> getXtList() {
    	List<ESXtList> list = new ArrayList<ESXtList>();
    	list = esXtService.findXts();
    	return list;
    }
    /**
	 * 模块列表
	 */
	@RequestMapping(value = "/module_list", method = RequestMethod.POST)
	@ResponseBody
	public List<PermissionModule> loadModulelist(String xtbh) {
		List<PermissionModule> list = new ArrayList<PermissionModule>();
		list = permissionService.getHomeModulesByXtbh(xtbh);
		return list;
	}
	
}
