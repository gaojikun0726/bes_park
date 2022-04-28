package com.efounder.JEnterprise.controller.systemcenter.logmanage;

import com.core.common.util.JsonMapper;
import com.efounder.JEnterprise.model.systemcenter.logmanage.ESSysLoginLog;
import com.efounder.JEnterprise.service.systemcenter.logmanage.ESSysLoginLogService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description 系统登录日志
 * @author yujieying
 * @date 2018/09/26
 *
 */
@RequestMapping(value = "/view/sysmanage/logmanage")
@Controller
public class ESSysLoginLogController {

	private static final Logger log = LoggerFactory.getLogger(ESSysLoginLogController.class);

    @Autowired
	private ESSysLoginLogService esSysLoginLogService;

	

	/**
	 * @description 初始化到系统登录日志 界面
	 * @param map
	 * @return user.ftl
	 */
	
	@RequestMapping(value = "SysLoginLog_page", method = RequestMethod.GET)
	public String getSysLoginLogPage() {
		log.info("#ESSysLoginLogController 初始加载系统登录日志 界面");
		return "/isspview/systemparameters/logmanage/sysLogin_log";
	}
	/**
	 * 根据搜索条件分页显示信息  
	 * @return
	 */
	@RequestMapping(value = "/loadsysLoginloglist1", method = RequestMethod.POST)
	public String loadsysLoginlogByKey(ModelMap map, Integer pageNum, String keywords,Integer bars) {
		log.info("#ESSysLoginLogController系统登录日志分页加载");
		  
         //进行分页查询
		try {
			PageInfo<ESSysLoginLog> page = esSysLoginLogService.loadsysLoginlogByKey(bars,pageNum,keywords);
			map.put("page", page);
			map.put("pageSize", page.getPageSize()+"");
			map.put("dataset", JsonMapper.toJsonString(page.getList()));
			String pageN =null;
			if(String.valueOf(page.getPageNum()).indexOf(",")!=-1)
				pageN = String.valueOf(page.getPageNum()).replaceAll(",","");
			else
				pageN = String.valueOf(page.getPageNum());
			map.put("pageNum", pageN);
			map.put("keywords", keywords);
		} catch (Exception e) {
		}
		return "/isspview/systemparameters/logmanage/sysLogin_log_page";
	}
	
	
}
