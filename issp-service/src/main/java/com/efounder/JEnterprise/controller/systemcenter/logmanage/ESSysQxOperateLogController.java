package com.efounder.JEnterprise.controller.systemcenter.logmanage;

import com.core.common.ISSPReturnObject;
import com.core.common.util.JsonMapper;
import com.efounder.JEnterprise.model.systemcenter.logmanage.ESSysLoginLog;
import com.efounder.JEnterprise.model.systemcenter.logmanage.ESSysQxOperateLog;
import com.efounder.JEnterprise.service.systemcenter.logmanage.ESSysQxOperateLogService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 系统权限日志
 * @author liuhoujun
 * @date 2018/11/6
 */
@RequestMapping(value = "/view/sysmanage/logmanage")
@Controller
public class ESSysQxOperateLogController {

	private static final Logger log = LoggerFactory.getLogger(ESSysQxOperateLogController.class);

    @Autowired
	private ESSysQxOperateLogService esSysQxOperateLogService;
    
    /**
	 * @description 初始化到系统登录日志 界面
	 * @param map
	 * @return sysQxOperate_log.ftl
	 */
	
	@RequestMapping(value = "/SysQxOperateLog_page", method = RequestMethod.GET)
	public String getSysOperateLogPage() {
		log.info("#ESSysQxOperateLogController 初始加载系统登录日志 界面");
		return "/isspview/systemparameters/logmanage/sysQxOperate_log";
	}
	
	/**
	 * 
	 * 筛选查询，分页查询
	 */
	@RequestMapping(value = "/loadQxOperationByKey", method = RequestMethod.POST)
	public String loadQxOperationByKe(ModelMap map,Integer bars,Integer pageNum,String fOpcontent,String fOpname,String fOptype,String f_sTime,String f_eTime) {

		log.info("#BESBattery_Alarm_RecordController 根据条件查询权限日志列表");
		PageInfo<ESSysQxOperateLog> page = esSysQxOperateLogService.getqxlmcxList(bars,pageNum, fOpcontent, fOpname, fOptype, f_sTime, f_eTime);
		map.put("page", page);
		map.put("dataset", JsonMapper.toJsonString(page.getList()));
		String pageN =null;
		if(String.valueOf(page.getPageNum()).indexOf(",")!=-1)
			pageN = String.valueOf(page.getPageNum()).replaceAll(",","");
		else
			pageN = String.valueOf(page.getPageNum());
		map.put("pageNum", pageN);
		map.put("fOpcontent", fOpcontent);
		map.put("fOpname", fOpname);
		map.put("fOptype", fOptype);
		map.put("f_sTime",f_sTime);
		map.put("f_eTime", f_eTime);
		return "/isspview/systemparameters/logmanage/sysQxOperate_log_page";
    }

    /**
     *
     * @Description: 权限日志信息查询
     *
     * @auther: wanghongjie
     * @date: 9:58 2020/9/25
     * @param: [map, pageNum, keywords, bars]
     * @return: java.lang.String
     *
     */
	@RequestMapping(value = "/loadsysQxOperateloglists", method = RequestMethod.POST)
	public String loadsysOperateloglistss(ModelMap map, Integer pageNum, String keywords,Integer bars) {
		log.info("#ESSysQxOperateLogController  权限日志分页加载");

		//进行分页查询
		try {
			PageInfo<ESSysLoginLog> page = esSysQxOperateLogService.loadsysQxOperateloglists(bars,pageNum,keywords);
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
		return "/isspview/systemparameters/logmanage/sysQxOperate_log_page";
	}


/**
 * 
 * 通过ID查询所需的数据
 */
	@RequestMapping(value = "/getlookqxopcontent", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getlookqxopcontent(String fid){
		return esSysQxOperateLogService.getlookqxopcontent(fid);
	}
}
