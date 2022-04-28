package com.efounder.JEnterprise.service.thetimer.impl;


import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.mapper.thetimer.ISSPTimerManageMapper;
import com.efounder.JEnterprise.model.thetimer.ISSPTimerList;
import com.efounder.JEnterprise.model.thetimer.ISSPTimerManage;
import com.efounder.JEnterprise.model.thetimer.ISSPTimerType;
import com.efounder.JEnterprise.service.thetimer.ISSPTimerManageService;
import com.efounder.JEnterprise.timer.TimerManager;
import com.efounder.JEnterprise.timer.TimerStub;
import com.efounder.JEnterprise.timer.TimerUtil;
import com.efounder.JEnterprise.timer.state.TimerStubStateRUNNING;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service("ISSPTimerManageService")
public class ISSPTimerManageServiceImpl implements ISSPTimerManageService{
	private static final Logger log = LoggerFactory.getLogger(ISSPTimerManageServiceImpl.class);
	@Autowired
	private ISSPTimerManageMapper isspTimerManageMapper;



	/**
	 * 获取定时器管理列表，分页
	 */
	@Override
	public PageInfo<ISSPTimerManage> getTimerManageList(Integer bars,Integer pageNum, String keywords) {		
		if (pageNum == null)
			pageNum = 1;
		if (bars == null) {
			bars = Constants.PAGE_SIZE;
		}
		PageHelper.startPage(pageNum,bars );
		// 紧跟着的第一个select方法会被分页
		List<ISSPTimerManage> TimerManageList = isspTimerManageMapper.getTimerManageList(keywords);
		// 用PageInfo对结果进行包装
		PageInfo<ISSPTimerManage> page = new PageInfo<ISSPTimerManage>(TimerManageList);
		// 测试PageInfo全部属性
		// PageInfo包含了非常全面的分页属性
		log.info("# 查询默认数据库 page.toString()={}", page.toString());
		return page;
	}

	/**
	 * 启动定时任务(设置任务状态为'1'(运行)，将该任务加载到轮询队列)
	 */
	public ISSPReturnObject startTimerTask(ISSPTimerManage isspTimerManage) {
		ISSPReturnObject returnObject = new ISSPReturnObject();		
		//获取该条任务
		ISSPTimerManage reTimerManage = isspTimerManageMapper.getTimerManage(isspTimerManage);
		//通过定时器编号获取该条定时器信息
		ISSPTimerList reTimer = isspTimerManageMapper.getTimerList(isspTimerManage.getfTimerBh());			
		//通过定时器类型编号 获取定时器类型
		ISSPTimerType reTimerType = isspTimerManageMapper.getTimerType(reTimer.getfTimerTypebh());

		//判断任务状态：'0'停止,'1'可运行
		if(reTimerManage.getfTimerState().equals("0")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String fTimerState = "1";
			isspTimerManage.setfTimerState(fTimerState);
			isspTimerManage.setfStartTime(sdf.format(new Date()));
			boolean flag = isspTimerManageMapper.upTimerManage(isspTimerManage);
			
			TimerStub ts = new TimerStub();
			// ID
			ts.setTimerID(reTimer.getfTimerBh());
			// caption
			ts.setTimerCaption(reTimer.getfTimerName());
			// 循环时间
			ts.setLoopTimer(Integer.parseInt(reTimer.getfLoopTime()));
			// 初始状态是运行
			ts.setState(new TimerStubStateRUNNING());
			// 定时类型:loop循环执行(默认方式),date固定时间执行...,day每天执行,week每周执行,month每月执行
			String timerType = reTimerType.getfType();
			if (timerType == null || timerType.trim().length() == 0)
				timerType = ts._TIMER_TYPE_LOOP_;
			ts.setTimerType(timerType);
			// 开始时间，一般是时分秒
			ts.setFixedTime(reTimer.getfFixedTime());
			// 变量时间
			ts.setVarTime(reTimer.getfVarTime());
			// 固定时间，计算出固定时间来，兼容
			if (ts._TIMER_TYPE_DATE_.equals(ts.getTimerType())) {
				String fixedTimer = ts.getVarTime() + " " + ts.getFixedTime();
				ts.setFixedTimer(TimerUtil.paraseDate(fixedTimer));
			}
			TimerManager.getTimerStubList().add(ts);
			if (flag) {
				returnObject.setData(isspTimerManage);
				returnObject.setStatus("1");
				returnObject.setMsg("启动成功");
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("启动失败");
			}
		}else if(reTimerManage.getfTimerState().equals("1")) {
			returnObject.setStatus("0");
			returnObject.setMsg("该任务已处于启动状态");
		}
		return returnObject;
	}
	/**
	 * 停止定时任务(设置任务状态为'0'(停止)，将该任务从轮询队列移除)
	 */
	public ISSPReturnObject stopTimerTask(ISSPTimerManage isspTimerManage) {
		ISSPReturnObject returnObject = new ISSPReturnObject();		
		//获取该条任务
		ISSPTimerManage reTimerManage = isspTimerManageMapper.getTimerManage(isspTimerManage);
		//判断任务状态：'1'运行,'0'停止
		if(reTimerManage.getfTimerState().equals("0")) {
			for(int i=0;i<TimerManager.getTimerStubList().size();i++) {
				TimerStub ts = new TimerStub();
				ts=TimerManager.getTimerStubList().get(i);
				if(ts.getTimerID().equals(isspTimerManage.getfTimerBh())) {
					TimerManager.getTimerStubList().remove(i);
				}
			}
			
			returnObject.setStatus("0");
			returnObject.setMsg("该任务已处于停止状态");
		}else if(reTimerManage.getfTimerState().equals("1")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String fTimerState = "0";
			isspTimerManage.setfTimerState(fTimerState);
			isspTimerManage.setfStopTime(sdf.format(new Date()));
			boolean flag = isspTimerManageMapper.upTimerManage(isspTimerManage);
			for(int i=0;i<TimerManager.getTimerStubList().size();i++) {
				TimerStub ts = new TimerStub();
				ts=TimerManager.getTimerStubList().get(i);
				if(ts.getTimerID().equals(isspTimerManage.getfTimerBh())) {
					TimerManager.getTimerStubList().remove(i);
				}
			}
			if (flag) {
				returnObject.setData(isspTimerManage);
				returnObject.setStatus("1");
				returnObject.setMsg("停止成功");
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("停止失败");
			}
		}
		return returnObject;
	}

	/**
	 * 立即执行一次定时任务，并设置该任务为可运行状态
	 */
	public ISSPReturnObject startTimerTaskNow(ISSPTimerManage isspTimerManage) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
//		TimerStub timerStub = new TimerStub();		
		TimerManager timerManager = new TimerManager();
		//通过定时器编号获取该条定时器信息
		ISSPTimerList reTimer = isspTimerManageMapper.getTimerList(isspTimerManage.getfTimerBh());			
		//通过定时器类型编号 获取定时器类型
		ISSPTimerType reTimerType = isspTimerManageMapper.getTimerType(reTimer.getfTimerTypebh());
		//获取数据库连接
//		Connection conn = mysqlDBObject.getConnection();
//		TimerManager.setConn(conn);
		//调用启动方法
		timerManager.loadScheduleTask(reTimer,reTimerType,null);
		//设置该条定时任务-开始
		TimerStub ts = new TimerStub();
		// ID
		ts.setTimerID(reTimer.getfTimerBh());
		// caption
		ts.setTimerCaption(reTimer.getfTimerName());
		// 循环时间
		ts.setLoopTimer(Integer.parseInt(reTimer.getfLoopTime()));
		// 初始状态是运行
		ts.setState(new TimerStubStateRUNNING());
		// 定时类型:loop循环执行(默认方式),date固定时间执行...,day每天执行,week每周执行,month每月执行
		String timerType = reTimerType.getfType();
		if (timerType == null || timerType.trim().length() == 0)
			timerType = ts._TIMER_TYPE_LOOP_;
		ts.setTimerType(timerType);
		// 开始时间，一般是时分秒
		ts.setFixedTime(reTimer.getfFixedTime());
		// 变量时间
		ts.setVarTime(reTimer.getfVarTime());
		// 固定时间，计算出固定时间来，兼容
		if (ts._TIMER_TYPE_DATE_.equals(ts.getTimerType())) {
			String fixedTimer = ts.getVarTime() + " " + ts.getFixedTime();
			ts.setFixedTimer(TimerUtil.paraseDate(fixedTimer));
		}
		//设置该条定时任务-结束			
		//修改任务状态为 可运行状态
		ISSPTimerManage reTimerManage = isspTimerManageMapper.getTimerManage(isspTimerManage);
		if(reTimerManage.getfTimerState().equals("0")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			isspTimerManage.setfStartTime(sdf.format(new Date()));
			String fTimerState = "1";
			isspTimerManage.setfTimerState(fTimerState);
			boolean flag = isspTimerManageMapper.upTimerManage(isspTimerManage);			
			//移除定时器中的相同ID的定时任务，并将此次立即执行的任务添加到定时器，以保证定时器中定时任务不重复
			for(int i=0;i<TimerManager.getTimerStubList().size();i++) {
				TimerStub tstub = new TimerStub();
				tstub=TimerManager.getTimerStubList().get(i);
				if(tstub.getTimerID().equals(isspTimerManage.getfTimerBh())) {
					TimerManager.getTimerStubList().remove(i);
				}
			}
			TimerManager.getTimerStubList().add(ts);

			if (flag) {
				returnObject.setData(isspTimerManage);
				returnObject.setStatus("1");
				returnObject.setMsg("成功");
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("失败");
			}
		}else {
			//移除定时器中的相同ID的定时任务，并将此次立即执行的任务添加到定时器，以保证定时器中定时任务不重复
			for(int i=0;i<TimerManager.getTimerStubList().size();i++) {
				TimerStub tstub = new TimerStub();
				tstub=TimerManager.getTimerStubList().get(i);
				if(tstub.getTimerID().equals(isspTimerManage.getfTimerBh())) {
					TimerManager.getTimerStubList().remove(i);
				}
			}
			TimerManager.getTimerStubList().add(ts);
			returnObject.setData(isspTimerManage);
			returnObject.setStatus("1");
			returnObject.setMsg("成功");
		}		
		return returnObject;
	}
}
