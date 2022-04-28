package com.efounder.JEnterprise.service.basedatamanage.eqmanage;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.service.ESBaseService;
import org.springframework.stereotype.Service;

import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;

@Service
public interface LampEquipmentService extends ESBaseService{

	/**
	 * 远程操作Lamp照明系统DDC控制器(设置时间2/重启3/重置4/远程升级30)
	 * @param fIp
	 * @param fPort
	 * @param fIndex
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	ISSPReturnObject operLampDDC(String fSysName,String fIp, String fPort, int fIndex) throws UnsupportedEncodingException, RemoteException, ServiceException;

	/**
	 * 获取Lamp照明系统DDC时间
	 * @param fIp
	 * @param fPort
	 * @param fIndex
	 * @return
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	ISSPReturnObject getLampDDCTime(String fSysName,String fIp, String fPort, int fIndex) throws UnsupportedEncodingException, RemoteException, ServiceException;
	/**
	 * 设置IP路由器的所有参数
	 * @param f_sys_name
	 * @param isMoreFlag 是否批量上传标志
	 * @return
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	ISSPReturnObject synLampDDC(String f_sys_name) throws UnsupportedEncodingException, RemoteException, ServiceException;
	/**
	 * Lamp获取IP路由器的所有配置参数
	 * @param f_guid
	 * @param fIp
	 * @param fPort
	 * @return
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 * @throws ServiceException 
	 */
	ISSPReturnObject compareLampDDC(String fSysName,String f_guid, String fIp, String fPort) throws UnsupportedEncodingException, RemoteException, ServiceException;
	/**
	 * Lamp设置模块所有信息
	 * @param f_sys_name
	 * @param f_psys_name
	 * @param nodeLevel
	 * @return
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	ISSPReturnObject synLampModule(String f_sys_name, String f_psys_name, String f_node_attribution) throws UnsupportedEncodingException, RemoteException, ServiceException;
	/**
	 * Lamp获取模块所有信息
	 * @param f_sys_name
	 * @param f_psys_name
	 * @param nodeLevel
	 * @return
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	ISSPReturnObject compareLampModule(String f_sys_name, String f_psys_name,String nodeLevel,String f_node_attribution,String PNodeType) throws UnsupportedEncodingException, RemoteException, ServiceException;
	/**
	 * Lamp设置逻辑点所有信息
	 * @param f_sys_name
	 * @param f_psys_name
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	ISSPReturnObject synLampPoint(String f_sys_name, String f_psys_name) throws UnsupportedEncodingException, RemoteException, ServiceException;
	/**
	 * Lamp重置逻辑点信息
	 * @param f_sys_name
	 * @param f_psys_name
	 * @param f_guid
	 * @return
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	ISSPReturnObject reSetLampPoint(String f_sys_name, String f_psys_name, String f_guid) throws UnsupportedEncodingException, RemoteException, ServiceException;
	/**
	 * Lamp获取逻辑点信息
	 * @param f_sys_name
	 * @param f_psys_name
	 * @param f_guid
	 * @param tabname
	 * @return
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	ISSPReturnObject compareLampPoint(String f_sys_name, String f_psys_name, String f_sbid,String f_flag) throws UnsupportedEncodingException, RemoteException, ServiceException;
	/**
	 * Lamp调试逻辑点数据
	 * @param obj
	 * @return
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	ISSPReturnObject debugLampPointInfo(JSONObject obj) throws UnsupportedEncodingException, RemoteException, ServiceException;
	/**
	 * Lamp设置逻辑点数据（开关）
	 * @param obj
	 * @return
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	ISSPReturnObject setLampPointInfo(JSONObject obj) throws UnsupportedEncodingException, RemoteException, ServiceException;
	/**
	 * 获取数据库中逻辑点的参数
	 * @param f_sys_name
	 * @param f_psys_name
	 * @return
	 */
	ISSPReturnObject getPointInfo(String f_sys_name, String f_psys_name);
	ISSPReturnObject getPointInfo1(String f_sys_name,String f_guid,String f_id,String f_node_type);
	ISSPReturnObject getPointInfoAI(String f_sys_name,String f_sys_name_old);
	ISSPReturnObject getPointInfoSBDY(String f_sys_name);
	ISSPReturnObject f_node_attribution(String f_sys_name,String tabname);

	
	/*
	 *
	 * @Description: 判断当前系统名称在相应的四种点位表里面有没有,有的话将f_sys_name输入框设置成不能输入
	 * 
	 * @auther: wanghongjie
	 * @date: 16:16 2020/4/11 
	 * @param: 
	 * @return: 
	 *
	 */
	ISSPReturnObject getInfo_f_sys_name(String f_sys_name, String tabName);

	/**
	 * 调试开关按钮
	 * @param obj
	 * @return
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	ISSPReturnObject debugLampPointList(JSONObject obj) throws UnsupportedEncodingException, RemoteException, ServiceException;


	/**
	 * 获取虚点信息
	 * @param f_sys_name
	 * @return
	 */
    ISSPReturnObject getVirtualPointInfo(String f_sys_name);
}
