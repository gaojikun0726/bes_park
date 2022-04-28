package com.efounder.JEnterprise.controller.thetimer;

import com.core.common.ISSPReturnObject;
import com.core.common.util.JsonMapper;
import com.efounder.JEnterprise.service.thetimer.ISSPTimerConfigurationService;
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

/**
 * 定时任务配置
 * @author LvSihan
 *
 */
@RequestMapping(value = "view/TimerConfiguration")
@Controller
public class ISSPTimerConfigurationController {
	private static final Logger log = LoggerFactory.getLogger(ISSPTimerConfigurationController.class);
	@Autowired
	private ISSPTimerConfigurationService isspTimerConfigurationService;
	/**
	 * 初始化定时器配置页面
	 * @return
	 */
	@RequestMapping(value = "/getTimerConfiguration" ,method = RequestMethod.GET)
	public String getTimerConfiguration() {
		log.info("#初始化定时器配置页面");
		return "isspview/scheduledtask/timerconfiguration";
	}
	
	/**
	 * 查询定时器配置列表,分页
	 * @return
	 */
	@RequestMapping(value = "/getTimerConfigurationList" ,method = RequestMethod.POST)
	public String getTimerConfigurationList(@RequestParam(value = "keywords", required = false) String keywords,
			@RequestParam(value = "pageNum", required = false) Integer pageNum, ModelMap map,Integer bars) {
		log.info("#分页查询 pageNum={} , keywords={}", pageNum, keywords);
		PageInfo<Object> page = isspTimerConfigurationService.getTimerConfigurationList(bars,pageNum, keywords);
		map.put("page", page);
		map.put("pageSize", page.getPageSize()+"");
		//将数据转化为json字符串
		map.put("dataset", JsonMapper.toJsonString(page.getList()));
		return "isspview/scheduledtask/timerconfiguration_page";
	}
	
	/**
	 * 通过id查询定时任务
	 * @return
	 */
	@RequestMapping(value = "/getTimerConfigurationById" ,method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getTimerConfigurationById(String F_TIMER_BH) {
		log.info("#获取设备适配器");
		ISSPReturnObject returnObject = isspTimerConfigurationService.getTimerConfigurationById(F_TIMER_BH);
		return returnObject;
	}
	
	/**
	 * 查询设备适配器
	 * @return
	 */
	@RequestMapping(value = "/getEquipmentadapter" ,method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getEquipmentadapter() {
		log.info("#获取设备适配器");
		ISSPReturnObject returnObject = isspTimerConfigurationService.getEquipmentadapter();
		return returnObject;
	}

	/**
	 * 查询服务接口
	 * @return
	 */
	@RequestMapping(value = "/getIntefaceadapter" ,method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getIntefaceadapter(String F_ADAPTERGUID) {
		log.info("#获取服务接口");
		ISSPReturnObject returnObject = isspTimerConfigurationService.getIntefaceadapter(F_ADAPTERGUID);
		return returnObject;
	}	
	
	/**
	 * 查询任务类型
	 * @return
	 */
	@RequestMapping(value = "/getTimertype" ,method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getTimertype() {
		log.info("#获取任务类型");
		ISSPReturnObject returnObject = isspTimerConfigurationService.getTimertype();
		return returnObject;
	}
	
	/**
	 * 添加定时任务
	 * @return
	 */
	@RequestMapping(value = "/addTimerConfiguration" ,method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject addTimerConfiguration(String F_TIMER_NAME, String F_TIMER_TYPEBH, String F_LOOP_TIME,
			String F_FIXED_TIME, String F_VAR_TIME, String F_EQADAPTER_GUID, String F_INTERFACE_GUID) {
		log.info("#添加定时任务");
		ISSPReturnObject returnObject = isspTimerConfigurationService.addTimerConfiguration(F_TIMER_NAME,
				F_TIMER_TYPEBH, F_LOOP_TIME, F_FIXED_TIME, F_VAR_TIME, F_EQADAPTER_GUID, F_INTERFACE_GUID);
		return returnObject;
	}
	
	/**
	 * 编辑定时任务
	 * @return
	 */
	@RequestMapping(value = "/updateTimerConfiguration" ,method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject updateTimerConfiguration(String F_TIMER_BH,String F_TIMER_NAME, String F_TIMER_TYPEBH, String F_LOOP_TIME,
			String F_FIXED_TIME, String F_VAR_TIME, String F_EQADAPTER_GUID, String F_INTERFACE_GUID) {
		log.info("#添加定时任务");
		ISSPReturnObject returnObject = isspTimerConfigurationService.updateTimerConfiguration(F_TIMER_BH,F_TIMER_NAME,
				F_TIMER_TYPEBH, F_LOOP_TIME, F_FIXED_TIME, F_VAR_TIME, F_EQADAPTER_GUID, F_INTERFACE_GUID);
		return returnObject;
	}
	
	/**
	 * 删除定时任务
	 * @return
	 */
	@RequestMapping(value = "/del_TimerConfiguration" ,method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject del_TimerConfiguration(String F_TIMER_BH) {
		log.info("#删除定时任务");
		ISSPReturnObject returnObject = isspTimerConfigurationService.del_TimerConfiguration(F_TIMER_BH);
		return returnObject;
	}
}
