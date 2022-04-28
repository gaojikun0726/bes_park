package com.efounder.JEnterprise.service.sysmanage;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.sysmanage.BesSchedule;
import com.efounder.JEnterprise.model.sysmanage.BesScheduleinfo;
import com.github.pagehelper.PageInfo;

import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;

public interface BESScheduleConfigService {
	/**
	 * 获取计划任务树
	 * @return
	 */
	public ISSPReturnObject getScheduleTree();

	/**
	 * 计划任务分页列表
	 * @param pageNum 
	 * @param keywords 
	 * @param fId 
	 * @return
	 */
	public PageInfo<BesSchedule> getScheduleList(String keywords, String fId, Integer pageNum);
	/**
	 * 新增计划任务类型
	 * @param besScheduleinfo
	 * @return
	 */
	public ISSPReturnObject add_schedule(BesScheduleinfo besScheduleinfo);
	/**
	 * 删除计划任务
	 * @param fId
	 * @return
	 */
	public ISSPReturnObject del_schedule(String fId);
	/**
	 * 新增计划任务详情
	 * @param besSchedule
	 * @return
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	public ISSPReturnObject add_scheduleTask(BesSchedule besSchedule) throws UnsupportedEncodingException, RemoteException, ServiceException;
	/**
	 * 获取一条计划详情
	 * @param fSysname
	 * @return
	 */
	public ISSPReturnObject get_scheduleTask(String fSysname);
	/**
	 * 更新计划详情
	 * @param besSchedule
	 * @return
	 */
	public ISSPReturnObject editscheduleTask(BesSchedule besSchedule);
	/**
	 * 删除计划详情
	 * @param fSysName
	 * @return
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	public ISSPReturnObject del_scheduletask(String fSysname) throws UnsupportedEncodingException, RemoteException, ServiceException;
	/**
	 * 场景模式下拉框
	 * @param fZoneid
	 * @return
	 */
	public ISSPReturnObject loadZoneMode(String fZoneid);
	/**
	 * 同步计划详情
	 * @param fSysName
	 * @return
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	public ISSPReturnObject syn_scheduletask(String fSysName) throws UnsupportedEncodingException, RemoteException, ServiceException;
	/**
	 * 获取下位机计划详情
	 * @param fId
	 * @return
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	public ISSPReturnObject get_underscheduleTask(String fSysName) throws UnsupportedEncodingException, RemoteException, ServiceException;

}
