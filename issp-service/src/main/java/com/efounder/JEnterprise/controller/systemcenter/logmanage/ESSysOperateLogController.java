package com.efounder.JEnterprise.controller.systemcenter.logmanage;

import com.core.common.util.JsonMapper;
import com.efounder.JEnterprise.model.systemcenter.logmanage.ESSysLoginLog;
import com.efounder.JEnterprise.model.systemcenter.logmanage.ESSysOperateLog;
import com.efounder.JEnterprise.service.systemcenter.logmanage.ESSysOperateLogService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description 系统操作日志
 * @author liuhoujun
 * @date 2018/10/30
 */
@RequestMapping(value = "/view/sysmanage/logmanage")
@Controller
public class ESSysOperateLogController {
	private static final Logger log = LoggerFactory.getLogger(ESSysOperateLogController.class);

    @Autowired
	private ESSysOperateLogService esSysOperateLogService;

	

	/**
	 * @description 初始化到系统登录日志 界面
	 * @param map
	 * @return sysOperate_log.ftl
	 */
	
	@RequestMapping(value = "/SysOperateLog_page", method = RequestMethod.GET)
	public String getSysOperateLogPage() {
		log.info("#ESSysOperateLogController 初始加载系统登录日志 界面");
		return "/isspview/systemparameters/logmanage/sysOperate_log";
	}

	/**
	 * 
	 * 筛选查询，分页查询
	 */
	@RequestMapping(value = "/loadOperationByKey", method = RequestMethod.POST)
	public String loadOperationByKey(ModelMap map,Integer bars, Integer pageNum,String f_comment,String f_name,String f_type,String f_sTime,String f_eTime) {

		log.info("#BESBattery_Alarm_RecordController 根据条件查询操作日志列表");
		PageInfo<ESSysOperateLog> page = esSysOperateLogService.getlmcxList(bars, pageNum, f_comment, f_name,f_type, f_sTime, f_eTime);
		map.put("page", page);
		map.put("dataset", JsonMapper.toJsonString(page.getList()));
		String pageN =null;
		if(String.valueOf(page.getPageNum()).indexOf(",")!=-1)
			pageN = String.valueOf(page.getPageNum()).replaceAll(",","");
		else
			pageN = String.valueOf(page.getPageNum());
		map.put("pageNum", pageN);
		map.put("f_comment", f_comment);
		map.put("f_name", f_name);
		map.put("f_type", f_type);
		map.put("f_sTime",f_sTime);
		map.put("f_eTime", f_eTime);
		return "/isspview/systemparameters/logmanage/sysOperate_log_page";
    }


	@RequestMapping(value = "/loadsysOperateloglistss", method = RequestMethod.POST)
	public String loadsysOperateloglistss(ModelMap map, Integer pageNum, String keywords,Integer bars) {
		log.info("#ESSysOperateLogController  操作日志分页加载");

		//进行分页查询
		try {
			PageInfo<ESSysLoginLog> page = esSysOperateLogService.loadsysOperateloglistss(bars,pageNum,keywords);
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
		return "/isspview/systemparameters/logmanage/sysOperate_log_page";
	}
	
	/**
	 * @Description 加载所有角色信息
	 * @return
	 */
/*	@RequestMapping(value = "/loadAllrole", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getOperateList() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESRole> list = new ArrayList<ESRole>();
		list = esSysOperateLogService.findOperate();
		returnObject.setList(list);
		return returnObject;
	}*/
}
