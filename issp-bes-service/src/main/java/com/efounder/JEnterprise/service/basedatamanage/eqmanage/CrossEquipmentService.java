package com.efounder.JEnterprise.service.basedatamanage.eqmanage;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.service.ESBaseService;
import org.springframework.stereotype.Service;

import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.Map;

@Service
public interface CrossEquipmentService extends ESBaseService{
	/**
	 * 重置逻辑点（对应下位机的删除逻辑点）
	 * @param pointMap 
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	public ISSPReturnObject reSetPoint(String f_sys_name, String f_psys_name, String f_guid, Map<String, Object> pointMap) throws UnsupportedEncodingException, RemoteException, ServiceException;
	/**
	 * 设置逻辑点的值（点开关）
	 * @param obj
	 * @return
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	public ISSPReturnObject setCrossPointInfo(JSONObject obj) throws UnsupportedEncodingException, RemoteException, ServiceException;
	/**
	 * 远程操作Cross楼控系统DDC控制器(设置时间2/重启3/重置4/远程升级30)
	 * @param fIp
	 * @param fPort
	 * @param fIndex
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	ISSPReturnObject operCrossDDC(String fSysName,String fIp, String fPort, int fIndex)
			throws UnsupportedEncodingException, RemoteException, ServiceException;
	/**
	 * 获取Cross楼控系统DDC时间
	 * @param fIp
	 * @param fPort
	 * @param fIndex
	 * @return
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	public ISSPReturnObject getCrossDDCTime(String fSysName,String fIp, String fPort, int fIndex) throws UnsupportedEncodingException, RemoteException, ServiceException;
	/**
	 * 设置DDC控制器的所有参数
	 * @param f_sys_name
	 * @return
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	public ISSPReturnObject synCrossDDC(String f_sys_name) throws UnsupportedEncodingException, RemoteException, ServiceException;
	/**
	 * Cross获取DDC控制器的所有配置参数
	 * @param f_guid
	 * @param fIp
	 * @param fPort
	 * @return
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	public ISSPReturnObject compareCrossDDC(String fSysName,String f_guid, String fIp, String fPort) throws UnsupportedEncodingException, RemoteException, ServiceException;
	/**
	 * Cross设置模块所有信息
	 * @param f_sys_name
	 * @param f_psys_name
	 * @param nodeLevel
	 * @return
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	public ISSPReturnObject synCrossModule(String f_sys_name, String f_psys_name, String f_node_attribution) throws UnsupportedEncodingException, RemoteException, ServiceException;
	/**
	 * Cross获取模块所有信息
	 * @param f_sys_name
	 * @param f_psys_name
	 * @return
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	public ISSPReturnObject compareCrossModule(String f_sys_name, String f_psys_name,String f_node_attribution) throws UnsupportedEncodingException, RemoteException, ServiceException;
	/**
	 * Cross设置逻辑点所有信息
	 * @param f_sys_name
	 * @param f_psys_name
	 * @return
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	public ISSPReturnObject synCrossPoint(String f_sys_name, String f_psys_name) throws UnsupportedEncodingException, RemoteException, ServiceException;
	/**
	 * Cross调试逻辑点数据
	 * @param obj
	 * @return
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	public ISSPReturnObject debugCrossPointInfo(JSONObject obj) throws UnsupportedEncodingException, RemoteException, ServiceException;
	public ISSPReturnObject debugCrossPointInfoxd(JSONObject obj) throws UnsupportedEncodingException, RemoteException, ServiceException;
	/**
	 * Cross获取逻辑点信息
	 * @param f_sys_name
	 * @param f_psys_name
	 * @param f_id
	 * @param tabname
	 * @return
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	public ISSPReturnObject compareCrossPoint(String f_sys_name, String f_psys_name, String f_sbid,String f_flag) throws UnsupportedEncodingException, RemoteException, ServiceException;


	/**
	 * 虚点数据对比
	 * @param f_sys_name
	 * @return
	 */
    ISSPReturnObject compareVirtualPoint(String f_sys_name);

    /**
     *
     * @Description: DDC远程升级
     *
     * @auther: wanghongjie
     * @date: 10:37 2020/8/14
     * @param: [fSysName]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject remoteUpgradeDdc(String fSysName);
}
