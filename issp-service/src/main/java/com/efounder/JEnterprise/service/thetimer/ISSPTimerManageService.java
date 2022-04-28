package com.efounder.JEnterprise.service.thetimer;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.thetimer.ISSPTimerManage;
import com.efounder.JEnterprise.service.ESBaseService;
import com.github.pagehelper.PageInfo;

/**
 * 定时器管理接口
 * @author LvSihan
 *
 */
public interface ISSPTimerManageService extends ESBaseService{

	public PageInfo<ISSPTimerManage> getTimerManageList(Integer bars,Integer pageNum, String keywords);

	public ISSPReturnObject startTimerTask(ISSPTimerManage isspTimerManage);

	public ISSPReturnObject startTimerTaskNow(ISSPTimerManage isspTimerManage);

	public ISSPReturnObject stopTimerTask(ISSPTimerManage isspTimerManage);


}
