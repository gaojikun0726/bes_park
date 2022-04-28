package com.efounder.JEnterprise.controller.thetimer;

import com.core.common.ISSPReturnObject;
import com.core.common.util.JsonMapper;
import com.efounder.JEnterprise.model.thetimer.ISSPTimerManage;
import com.efounder.JEnterprise.service.thetimer.ISSPTimerManageService;
import com.efounder.JEnterprise.timer.TimerManager;
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
 * 定时器管理
 * @author LvSihan
 *
 */
@RequestMapping(value = "view/TimerManage")
@Controller
public class ISSPTimerManageController {
	
	private static final Logger log = LoggerFactory.getLogger(ISSPTimerManageController.class);
	@Autowired
	private ISSPTimerManageService isspTimerManageService;
//	@Autowired
//	private MySQLDBObject mysqlDBObject;
	/**
	 * 初始化定时器管理页面
	 * @return
	 */
	@RequestMapping(value = "/getTimerManage" ,method = RequestMethod.GET)
	public String getTimerManage() {
		log.info("#初始化定时器管理页面");
		return "isspview/scheduledtask/timermanage";
	}
	
	/**
	 * 查询定时器管理列表,分页
	 * @return
	 */
	@RequestMapping(value = "/getTimerManageList" ,method = RequestMethod.POST)
	public String getTimerManageList(@RequestParam(value = "keywords", required = false) String keywords,
			@RequestParam(value = "pageNum", required = false) Integer pageNum, ModelMap map,Integer bars) {
		log.info("#分页查询 pageNum={} , keywords={}", pageNum, keywords);
		PageInfo<ISSPTimerManage> page = isspTimerManageService.getTimerManageList(bars,pageNum, keywords);
		map.put("page", page);
		map.put("pageSize", page.getPageSize()+"");
		//将数据转化为json字符串
		map.put("dataset", JsonMapper.toJsonString(page.getList()));
		return "isspview/scheduledtask/timermanage_page";
	}
	
	/**
	 * 启动定时任务
	 * @param isspTimerManage
	 * @return
	 */
	@RequestMapping(value = "/startTimerTask" ,method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject startTimerTask(ISSPTimerManage isspTimerManage) {
		log.info("#启动定时任务");
		ISSPReturnObject returnObject = isspTimerManageService.startTimerTask(isspTimerManage);
		return returnObject;
	}
	/**
	 * 立即启动定时任务
	 * @param isspTimerManage
	 * @return
	 */
	@RequestMapping(value = "/startTimerTaskNow" ,method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject startTimerTaskNow(ISSPTimerManage isspTimerManage) {
		log.info("#立即启动定时任务");
		ISSPReturnObject returnObject = isspTimerManageService.startTimerTaskNow(isspTimerManage);
		return returnObject;
	}
	/**
	 * 停止定时任务
	 * @param isspTimerManage
	 * @return
	 */
	@RequestMapping(value = "/stopTimerTask" ,method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject stopTimerTask(ISSPTimerManage isspTimerManage) {
		log.info("#立即启动定时任务");
		ISSPReturnObject returnObject = isspTimerManageService.stopTimerTask(isspTimerManage);
		return returnObject;		
	}
	/**
	 * 启动定时器
	 * @return
	 */
	@RequestMapping(value = "/startTimer" ,method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject startTimer() {
		log.info("#启动定时器");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		if(TimerManager.isTimerState()) {
			returnObject.setStatus("0");
			returnObject.setMsg("定时器已在运行");
		}else {
			//获取数据库连接
//			Connection conn = mysqlDBObject.getConnection();
//			TimerManager.setConn(conn);
			TimerManager.getInstance().loadTimerTask();
			TimerManager.getInstance().startTimer();
			returnObject.setStatus("1");
			returnObject.setMsg("定时器启动成功");
		}
		return returnObject;
	}
	/**
	 * 停止定时器
	 * @return
	 */
	@RequestMapping(value = "/stopTimer" ,method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject stopTimer() {
		log.info("#停止定时器");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		if(TimerManager.isTimerState()) {
			TimerManager.getInstance().stopTimer();
			returnObject.setStatus("1");
			returnObject.setMsg("定时器停止成功");
		}else {
			returnObject.setStatus("0");
			returnObject.setMsg("定时器未启动");
		}
		return returnObject;
	}
	/**
	 * 获取定时器状态
	 * @return
	 */
	@RequestMapping(value = "/getTimerState" ,method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getTimerState() {
		log.info("#获取定时器开关状态");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		if(TimerManager.isTimerState()) {
			returnObject.setStatus("0");
			returnObject.setMsg("定时器已在运行");
		}else {
			returnObject.setStatus("1");
			returnObject.setMsg("定时器未运行");
		}
		return returnObject;
	}
}
