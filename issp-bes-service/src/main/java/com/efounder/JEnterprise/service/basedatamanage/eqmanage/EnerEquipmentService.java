package com.efounder.JEnterprise.service.basedatamanage.eqmanage;

import com.core.common.ISSPReturnObject;
import com.core.common.metatype.Dto;
import com.efounder.JEnterprise.service.ESBaseService;
import org.springframework.stereotype.Service;

import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.Map;
/**
 * 能耗采集系统
 * 与下位机通讯接口
 * 
 * @author LvSihan 
 * @date 2018年11月7日  
 * @version 1.0
 */
@Service
public interface EnerEquipmentService extends ESBaseService {

	/**
	 * 同步采集器数据
	 * 
	 * @param f_sys_name
	 * @param  isMoreFlag 是否批量上传标志
	 * @return
	 * @throws ServiceException
	 * @throws RemoteException
	 * @throws UnsupportedEncodingException
	 */
	public ISSPReturnObject synEnergyCollector(String f_sys_name)
			throws UnsupportedEncodingException, RemoteException, ServiceException;

	/**
	 * 远程操作能耗系统collector控制器(设置时间2/重启3/重置4/远程升级30)
	 * 
	 * @param fIp
	 * @param fPort
	 * @param fIndex
	 * @return
	 * @throws ServiceException
	 * @throws RemoteException
	 * @throws UnsupportedEncodingException
	 */
	public ISSPReturnObject operEnergyCollector(String fSysName, String fIp, String fPort, int fIndex)
			throws UnsupportedEncodingException, RemoteException, ServiceException;

	/**
	 * 获取能耗系统collector控制器时间
	 * 
	 * @param fSysName
	 * @param fIp
	 * @param fPort
	 * @param fIndex
	 * @return
	 * @throws ServiceException
	 * @throws RemoteException
	 * @throws UnsupportedEncodingException
	 */
	public ISSPReturnObject getEnergyCollectorTime(String fSysName, String fIp, String fPort, int fIndex)
			throws UnsupportedEncodingException, RemoteException, ServiceException;

	/**
	 * 获取能耗采集器的所有参数
	 * 
	 * @param fSysName
	 * @param f_guid
	 * @param fIp
	 * @param fPort
	 * @return
	 * @throws ServiceException
	 * @throws RemoteException
	 * @throws UnsupportedEncodingException
	 */
	public ISSPReturnObject compareEnergyCollector(String fSysName, String f_guid, String fIp, String fPort)
			throws UnsupportedEncodingException, RemoteException, ServiceException;

	/**
	 * 设置电表的所有数据（同步）
	 * 
	 * @param f_sys_name
	 * @param f_psys_name
	 * @param nodeLevel
	 * @return
	 * @throws ServiceException
	 * @throws RemoteException
	 * @throws UnsupportedEncodingException
	 */
/*	public ISSPReturnObject synEnergyAmmeter(String f_sys_name, String f_psys_name, String f_node_attribution)
			throws UnsupportedEncodingException, RemoteException, ServiceException;*/
	/**
	 * 获取电表的所有数据
	 * @param f_sys_name
	 * @param f_psys_name
	 * @return
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	public ISSPReturnObject compareEnergyAmmeter(String f_sys_name, String f_psys_name,String f_node_attribution) throws UnsupportedEncodingException, RemoteException, ServiceException;
	/**
	 * 电表电能参数
	 * @param f_sys_name
	 * @return
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 * @throws ServiceException 
	 */
	public ISSPReturnObject compareEnectric(String f_sys_name) throws UnsupportedEncodingException, RemoteException, ServiceException;
	/**
	 * 获取能耗数据
	 * @param f_sys_name
	 * @param selectday
	 * @return
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 * @throws ServiceException 
	 */
	public ISSPReturnObject queryMeterHisData(String f_sys_name, String selectday) throws UnsupportedEncodingException, RemoteException, ServiceException;
	/**
	 * 解析能耗数据
	 * @param electricData
	 * @param gridata
	 * @param meteruuid
	 * @param histime
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	public ISSPReturnObject queryMeterHisDataGrid(String electricData, String gridata, String meteruuid, String histime,
			String type) throws Exception;

	public String analysisRawData(String enectricId, String RawData,String percentage,String unit);

	public Dto operEnergyController(Map<String, Object> eqMap, String index) throws UnsupportedEncodingException, RemoteException, ServiceException;
	/**
	 * 获取上位机电能参数
	 * @param f_sys_name
	 * @return
	 */
	public ISSPReturnObject queryElectricParamsByAmmeter(String f_sys_name);

		/* Start add by xiepufeng at 2020年5月18日
	reason 1、原来远程升级和设置时间重启功能耦合在一起了，为了减少耦合每个功能都独立出来
	       2、上位机下位机通信升级
	*/
	/**
	 * 能耗采集器远程升级
	 * @param fSysName
	 * @return
	 */
    ISSPReturnObject remoteUpgradeEdc(String fSysName);

	/**
	 * 设置能耗采集器时间
	 * @param fSysName
	 * @return
	 */
	ISSPReturnObject setTimeEdc(String fSysName);

	/**
	 * 重启能耗采集器
	 * @param fSysName
	 * @return
	 */
	ISSPReturnObject restartEdc(String fSysName);


    /*End add by xiepufeng at 2020年5月18日*/

	/*Start add by xiepufeng at 2020年5月19日 remark 获取能耗采集器时间*/

	/**
	 * 获取能耗采集器时间
	 * @param fSysName
	 * @return
	 */
	ISSPReturnObject getTimeEdc(String fSysName);

    /*End add by xiepufeng at 2020年5月19日 remark 获取能耗采集器时间*/

	/*Start add by xiepufeng at 2020年5月19日*/
	/**
	 * 重置能耗采集器
	 * @param fSysName
	 * @return
	 */
	ISSPReturnObject resetEdc(String fSysName);
	/*End add by xiepufeng at 2020年5月19日*/

	/*Start add by xiepufeng at 2020年5月21日*/
	/**
	 * 给下位机下发指令，获取控制器参数
	 * @param fSysName
	 * @return
	 */
	ISSPReturnObject getControllerParam(String fSysName);

    /*End add by xiepufeng at 2020年5月21日*/

	/**
	 * 同步电表参数
	 * @param f_sys_name
	 * @return
	 */
	ISSPReturnObject synAmmeter(String f_sys_name);

	/**
	 * 给下位机下发指令，获取电表参数
	 * @param f_sys_name
	 * @return
	 */
    ISSPReturnObject getAmmeterParam(String f_sys_name);

	/**
	 * 给下位机下发指令，获取电表实时数据
	 * @param f_sys_name
	 * @return
	 */
    ISSPReturnObject getAmmeterRealTimeData(String f_sys_name);

	/**
	 * 给下位机下发指令，获取电表历史数据
	 * @param fSysName
	 * @return
	 */
    ISSPReturnObject getAmmeterHistoryData(String fSysName, Integer selectDay);
}
