package com.efounder.JEnterprise.service.thetimer;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.service.ESBaseService;
import com.github.pagehelper.PageInfo;
/**
 * 定时器配置service接口
 * @author LvSihan
 *
 */
public interface ISSPTimerConfigurationService extends ESBaseService{

	PageInfo<Object> getTimerConfigurationList(Integer bars,Integer pageNum, String keywords);

	/**
	 * 获取设备适配器
	 * @return
	 */
	public ISSPReturnObject getEquipmentadapter();
	/**
	 * 获取服务接口
	 * @param F_ADAPTERGUID 
	 * @return
	 */
	public ISSPReturnObject getIntefaceadapter(String F_ADAPTERGUID);
	/**
	 * 获取任务类型
	 * @return
	 */
	public ISSPReturnObject getTimertype();
	/**
	 * 添加定时任务
	 * @param isspTimerList 
	 * @return
	 */
	public ISSPReturnObject addTimerConfiguration(String F_TIMER_NAME, String F_TIMER_TYPEBH, String F_LOOP_TIME,
			String F_FIXED_TIME, String F_VAR_TIME, String F_EQADAPTER_GUID, String F_INTERFACE_GUID);

	ISSPReturnObject del_TimerConfiguration(String F_TIMER_BH);

	ISSPReturnObject getTimerConfigurationById(String F_TIMER_BH);

	ISSPReturnObject updateTimerConfiguration(String F_TIMER_BH,String f_TIMER_NAME, String f_TIMER_TYPEBH, String f_LOOP_TIME,
			String f_FIXED_TIME, String f_VAR_TIME, String f_EQADAPTER_GUID, String f_INTERFACE_GUID);

}
