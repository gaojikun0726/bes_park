package com.efounder.JEnterprise.service.thetimer.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.mapper.thetimer.ISSPTimerConfigurationMapper;
import com.efounder.JEnterprise.service.thetimer.ISSPTimerConfigurationService;
import com.framework.common.utils.OperationLog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 定时器配置实现类
 * @author LvSihan
 *
 */
@Service("ISSPTimerConfigurationService")
public class ISSPTimerConfigurationServiceImpl implements ISSPTimerConfigurationService{
	private static final Logger log = LoggerFactory.getLogger(ISSPTimerConfigurationServiceImpl.class);
	@Autowired
	private ISSPTimerConfigurationMapper isspTimerConfigurationMapper;
	
	
	@Override
	public PageInfo<Object> getTimerConfigurationList(Integer bars,Integer pageNum, String keywords) {
		if (pageNum == null)
			pageNum = 1;
		if (bars == null) {
			bars = Constants.PAGE_SIZE;
		}
		PageHelper.startPage(pageNum,bars );
		// 紧跟着的第一个select方法会被分页
		List<Object> TimerConfigurationList = isspTimerConfigurationMapper.getTimerConfigurationList(keywords,null);
		// 用PageInfo对结果进行包装
		PageInfo<Object> page = new PageInfo<Object>(TimerConfigurationList);
		// 测试PageInfo全部属性
		// PageInfo包含了非常全面的分页属性
		log.info("# 查询默认数据库 page.toString()={}", page.toString());
		return page;
	}


	@Override
	public ISSPReturnObject getEquipmentadapter() {
		log.info("#获取设备适配器");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<Object> list = isspTimerConfigurationMapper.getEquipmentadapter();
			returnObject.setList(list);
			returnObject.setMsg("获取适配器列表成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取适配器列表失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}


	@Override
	public ISSPReturnObject getIntefaceadapter(String F_ADAPTERGUID) {
		log.info("#获取服务接口");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<Object> list = isspTimerConfigurationMapper.getIntefaceadapter(F_ADAPTERGUID);
			returnObject.setList(list);
			returnObject.setMsg("获取服务接口列表成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取服务接口列表失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}


	@Override
	public ISSPReturnObject getTimertype() {
		log.info("#获取任务类型");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<Object> list = isspTimerConfigurationMapper.getTimertype();
			returnObject.setList(list);
			returnObject.setMsg("获取任务类型列表成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取任务类型列表失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}


	@Override
	public ISSPReturnObject addTimerConfiguration(String F_TIMER_NAME, String F_TIMER_TYPEBH, String F_LOOP_TIME,
			String F_FIXED_TIME, String F_VAR_TIME, String F_EQADAPTER_GUID, String F_INTERFACE_GUID) {
		log.info("#添加定时任务");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		if(F_FIXED_TIME.equals("")) {
			F_FIXED_TIME = "0";
		}
		if(F_LOOP_TIME.equals("")) {
			F_LOOP_TIME = "0";
		}
		if(F_VAR_TIME.equals("")) {
			F_VAR_TIME = "0";
		}
		String F_TIMER_BH = UUIDUtil.getRandom32BeginTimePK();
		boolean flag = isspTimerConfigurationMapper.addTimerList(F_TIMER_BH,F_TIMER_NAME,F_TIMER_TYPEBH,F_LOOP_TIME,F_FIXED_TIME,F_VAR_TIME);
		//添加之后添加
		try {
			OperationLog.insert(F_TIMER_BH, "issp_timer_list");
		String manageMsg = "";
		String eq_join_taskMsg = "";
		if(flag) {
			boolean timer_manage_flag = isspTimerConfigurationMapper.addTimerManage(F_TIMER_BH, F_TIMER_NAME, "0");
			if(!timer_manage_flag) {
				manageMsg = "定时器管理表添加失败";
			}
			boolean eq_join_task_flag = isspTimerConfigurationMapper.addEqJoinTask(UUIDUtil.getRandom32BeginTimePK(),
					F_TIMER_BH, F_TIMER_NAME, F_EQADAPTER_GUID, F_INTERFACE_GUID);
			if(!eq_join_task_flag) {
				eq_join_taskMsg = "定时任务管理表添加失败";
			}
			List<Object> TimerConfigurationList = isspTimerConfigurationMapper.getTimerConfigurationList(null,F_TIMER_BH);
			returnObject.setData(TimerConfigurationList);
			returnObject.setStatus("1");
			returnObject.setMsg("添加定时任务成功");
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg("添加定时任务失败,"+manageMsg+eq_join_taskMsg);
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;
	}


	@Override
	public ISSPReturnObject del_TimerConfiguration(String F_TIMER_BH) {
		log.info("#删除定时任务");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		//删除之前删除
		try {
			OperationLog.delete(F_TIMER_BH, "issp_timer_list");
		boolean flag = isspTimerConfigurationMapper.delTimerList(F_TIMER_BH);
		isspTimerConfigurationMapper.delTimerManage(F_TIMER_BH);
		isspTimerConfigurationMapper.delEqJoinTask(F_TIMER_BH);
		if(flag) {
			returnObject.setStatus("1");
			returnObject.setMsg("删除定时任务成功");
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg("删除定时任务失败");
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;
	}


	@Override
	public ISSPReturnObject getTimerConfigurationById(String F_TIMER_BH) {
		log.info("#查询定时任务");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<Object> TimerConfigurationList = isspTimerConfigurationMapper.getTimerConfigurationList(null,F_TIMER_BH);
			returnObject.setData(TimerConfigurationList);
			returnObject.setStatus("1");
			returnObject.setMsg("查询定时任务成功");
		} catch (Exception e) {
			returnObject.setStatus("0");
			returnObject.setMsg("查询定时任务失败");
		}
		return returnObject;
	}


	@Override
	public ISSPReturnObject updateTimerConfiguration(String F_TIMER_BH,String f_TIMER_NAME, String f_TIMER_TYPEBH, String f_LOOP_TIME,
			String f_FIXED_TIME, String f_VAR_TIME, String f_EQADAPTER_GUID, String f_INTERFACE_GUID) {
		log.info("#更新定时任务");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		if(f_FIXED_TIME.equals("")) {
			f_FIXED_TIME = "0";
		}
		if(f_LOOP_TIME.equals("")) {
			f_LOOP_TIME = "0";
		}
		if(f_VAR_TIME.equals("")) {
			f_VAR_TIME = "0";
		}
		Map<String, Object> startMap;
		//修改前后都执行
		try {
			startMap = OperationLog.updateStart(F_TIMER_BH, "issp_timer_list");
		boolean flag = isspTimerConfigurationMapper.updateTimerList(F_TIMER_BH,f_TIMER_NAME,f_TIMER_TYPEBH,f_LOOP_TIME,f_FIXED_TIME,f_VAR_TIME);
		OperationLog.updateEnd(F_TIMER_BH, "issp_timer_list", startMap);
		String manageMsg = "";
		String eq_join_taskMsg = "";
		if(flag) {
			boolean timer_manage_flag = isspTimerConfigurationMapper.updateTimerManage(F_TIMER_BH, f_TIMER_NAME);
			if(!timer_manage_flag) {
				manageMsg = "定时器管理表更新失败";
			}
			boolean eq_join_task_flag = isspTimerConfigurationMapper.updateEqJoinTask(F_TIMER_BH, f_TIMER_NAME,
					f_EQADAPTER_GUID, f_INTERFACE_GUID);
			if(!eq_join_task_flag) {
				eq_join_taskMsg = "定时任务管理表更新失败";
			}
			List<Object> TimerConfigurationList = isspTimerConfigurationMapper.getTimerConfigurationList(null,F_TIMER_BH);
			returnObject.setData(TimerConfigurationList);
			returnObject.setStatus("1");
			returnObject.setMsg("更新定时任务成功");
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg("更新定时任务失败,"+manageMsg+eq_join_taskMsg);
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;
	}
}
