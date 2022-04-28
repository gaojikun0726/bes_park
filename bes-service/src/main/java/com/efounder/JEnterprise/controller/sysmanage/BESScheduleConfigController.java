package com.efounder.JEnterprise.controller.sysmanage;


import com.core.common.ISSPReturnObject;
import com.core.common.util.JsonMapper;
import com.efounder.JEnterprise.model.sysmanage.BesSchedule;
import com.efounder.JEnterprise.model.sysmanage.BesScheduleinfo;
import com.efounder.JEnterprise.service.sysmanage.BESScheduleConfigService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;

@Controller
@RequestMapping(value = "/view/sysmanage")
public class BESScheduleConfigController {
	private static final Logger log = LoggerFactory.getLogger(BESScheduleConfigController.class);
	
	@Autowired
	private BESScheduleConfigService besScheduleConfigService;
	/**
	 * 初始进入计划任务配置页面
	 * @return
	 */
	@RequestMapping(value = "/getSchedule", method = RequestMethod.GET)
	public String getSchedule() {
		log.info("#BESScheduleConfigController初始进入计划任务配置页面");
		return "view/sysmanage/scheduletask/ScheduleConfig";
	}
	
	/**
	 * 生成计划任务树
	 * @return
	 */
	@RequestMapping(value = "/getSchedule", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getScheduleTree() {
		log.info("BESScheduleConfigController生成计划任务树");
		ISSPReturnObject returnObject = besScheduleConfigService.getScheduleTree();
		return returnObject;
	}
	/**
	 * 查询计划任务分页列表
	 * @param keywords
	 * @param pageNum
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/getScheduleList", method = RequestMethod.POST)
	public String getScheduleList(@RequestParam(value = "keywords", required = false)String keywords,@RequestParam(value = "fId", required = false)String fId,
			@RequestParam(value = "pageNum", required = false)Integer pageNum,ModelMap map) {
		log.info("BESScheduleConfigController查询计划任务分页列表");
		PageInfo<BesSchedule> page = besScheduleConfigService.getScheduleList(keywords, fId,pageNum);
		map.put("page", page);
		map.put("dataset", JsonMapper.toJsonString(page.getList()));
		return "view/sysmanage/scheduletask/ScheduleConfig_page";
	}
	/**
	 * 新建计划任务类型
	 * @return
	 */
	@RequestMapping(value = "/add_schedule", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject add_schedule(BesScheduleinfo besScheduleinfo) {
		log.info("BESScheduleConfigController新建计划任务节点");
		ISSPReturnObject returnObject = besScheduleConfigService.add_schedule(besScheduleinfo);
		return returnObject;
	}
	/**
	 * 删除计划任务类型
	 * @param fId
	 * @return
	 */
	@RequestMapping(value = "/del_schedule", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject del_schedule(String fId) {
		log.info("BESScheduleConfigController删除计划任务");
		ISSPReturnObject returnObject = besScheduleConfigService.del_schedule(fId);
		return returnObject;
	}
	/**
	 * 新增计划任务详情
	 * @param besSchedule
	 * @return
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/add_scheduleTask", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject add_scheduleTask(@RequestBody BesSchedule besSchedule) throws UnsupportedEncodingException, RemoteException, ServiceException {
		log.info("BESScheduleConfigController新增计划任务详情");
		ISSPReturnObject returnObject = besScheduleConfigService.add_scheduleTask(besSchedule);
		return returnObject;
	}
	/**
	 * 查询一条计划详情
	 * @param fSysName
	 * @return
	 */
	@RequestMapping(value = "/get_scheduleTask", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject get_scheduleTask(String fSysName) {
		log.info("BESScheduleConfigController获取一条计划任务详情");
		ISSPReturnObject returnObject = besScheduleConfigService.get_scheduleTask(fSysName);
		return returnObject;
	}
	/**
	 * 更新计划详情
	 * @param besSchedule
	 * @return
	 */
	@RequestMapping(value = "/editscheduleTask", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject editscheduleTask(@RequestBody BesSchedule besSchedule) {
		log.info("BESScheduleConfigController更新计划任务详情");
		ISSPReturnObject returnObject = besScheduleConfigService.editscheduleTask(besSchedule);
		return returnObject;
	}
	/**
	 * 删除计划详情
	 * @param fSysName
	 * @return
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/del_scheduletask", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject del_scheduletask(String fSysName) throws UnsupportedEncodingException, RemoteException, ServiceException {
		log.info("BESScheduleConfigController删除计划详情");
		ISSPReturnObject returnObject = besScheduleConfigService.del_scheduletask(fSysName);
		return returnObject;
	}
	/**
	 * 加载场景模式下拉框
	 * @param fZoneid
	 * @return
	 */
	@RequestMapping(value = "/loadZoneMode", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject loadZoneMode(String fZoneid) {
		log.info("BESScheduleConfigController加载场景模式选择下拉框");
		ISSPReturnObject returnObject = besScheduleConfigService.loadZoneMode(fZoneid);
		return returnObject;
	}
	/**
	 * 同步计划详情
	 * @param fSysName
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/syn_scheduletask", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject syn_scheduletask(String fSysName) throws UnsupportedEncodingException, RemoteException, ServiceException {
		log.info("BESScheduleConfigController同步计划详情");
		ISSPReturnObject returnObject = besScheduleConfigService.syn_scheduletask(fSysName);
		return returnObject;
	}
	/**
	 * 获取下位机计划详情
	 * @return
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/get_underscheduleTask", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject get_underscheduleTask(String fSysName) throws UnsupportedEncodingException, RemoteException, ServiceException {
		log.info("BESScheduleConfigController获取下位机计划详情");
		ISSPReturnObject returnObject = besScheduleConfigService.get_underscheduleTask(fSysName);
		return returnObject;
	}
}
