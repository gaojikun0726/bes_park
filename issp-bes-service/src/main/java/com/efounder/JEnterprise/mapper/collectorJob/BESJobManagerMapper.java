package com.efounder.JEnterprise.mapper.collectorJob;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.collectorJob.BESSysConf;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Mapper
public interface BESJobManagerMapper extends BaseMapper<String, Object>{

	public List<Map<String, Object>> queryJob();

	public List<Map<String, Object>> queryCollector();

	public String queryCollectorName(@Param(value="collectorId") String collectorId);

	public List<Map<String, Object>> queryAmmeter(@Param(value="collectorname") String collectorname);

	public List<Map<String, Object>> queryAmmeterByCollectorName(@Param(value="sysName") String sysName);

	public String queryCollectorNameByIPAdd(@Param(value = "ipaddress") String ipaddress,
			@Param(value = "collection_port") String collection_port);
	/**
	 * 通过采集器id查询园区编号
	 * @param id
	 * @return
	 */
	public String queryParkidByCollectorId(@Param(value="id") String id);
	/**
	 * 通过电表编号查询园区编号
	 * @param id
	 * @return
	 */
	public String queryParkidByMeterId(@Param(value="f_sys_name") String f_sys_name);

	public List<Map<String, Object>> queryEnectricId(@Param(value="id") String id);
	
	public Map<String, Object> queryCollector(@Param(value="fsysname") String fsysname);

	public Map<String, Object> queryCollectorStatus(@Param(value="id") String id,@Param(value="collectorId") String collectorId);

	public void insertCollectorStatus(@Param(value = "fId") String fId, @Param(value = "fCollector_id") String fCollector_id,
			@Param(value = "fSuccess_time") String fSuccess_time, @Param(value = "fStatus") String fStatus);

	public void updateCollectorStatus(@Param(value = "fSuccess_time") String fSuccess_time,
			@Param(value = "fStatus") String fStatus,@Param(value = "fId") String fId);

	public void updateCalculateTime(BESSysConf besSysConf);

	public void deleteLastData();

	public void insertLastData();

	public void deleteRealtimeData();

	public void insertRealtimeData(@Param("lstDto") List<Map<String, Object>> lstDto);

	public void insertCalculateData(@Param("lstCalcDto") List<Map<String, Object>> lstCalcDto);

	public List<Map<String, Object>> queryAllPark();

	public void insertEnectricData(@Param("lstDto") List<Map<String, Object>> lstDto);

	public Map<String, Object> querySysConf();

	public Map<String, Object> queryAmmeterInfo(@Param("f_sys_name") String f_sys_name);

	public Map<String, Object> queryCollectorIdByMeterId(@Param("f_sys_name") String f_sys_name);

	// 根据电表系统名称获取所属采集器
	public Map<String, Object> queryCollectorByAmmeterSysName(@Param("f_sys_name") String f_sys_name);
	/**
	 * 通过电表查询总线端口
	 * @param F_SYS_NAME
	 * @return
	 */
	public String queryBusProtByMeterId(@Param("F_SYS_NAME") String F_SYS_NAME);

	public String queryBusPortBySysName(@Param("F_SYS_NAME") String F_SYS_NAME);

	public List<Map<String, Object>> queryAmmeterByBusSysName(@Param("F_SYS_NAME") String F_SYS_NAME);

	public String queryCollectorPortByDto(@Param("F_SYS_NAME") String F_SYS_NAME);

	public Map<String, Object> queryCollectorInfoByAmmeterId(@Param("f_sys_name") String f_sys_name);
	/**
	 * 查询该电表的采集参数信息
	 * @param collector_method_id
	 * @return
	 */
	public List<Map<String, Object>> queryAmmeter_params_schemeid(@Param("collector_method_id") String collector_method_id);
	/**
	 * 通过电表id删除电表对应采集方案下的电能参数关系
	 * @param meterId
	 */
	public void delMeterRelParam(String meterId);

	public List<Map<String, Object>> queryEnectricDataByMeterIdASC(@Param("f_sys_name") String f_sys_name);
	/**
	 * 新增采集方案对应电能参数
	 * @param f_sys_name
	 * @param string
	 */
	public void saveMeterRelParmas(@Param("f_sys_name")String f_sys_name,@Param("F_CJFABH")String F_CJFABH,@Param("F_NHBH")String F_NHBH);
	/**
	 * 查询电表类型对应的电表名称
	 * @param string
	 * @return
	 */
	public String queryAmmeterModelById(@Param("MeterType")int MeterType);
	/**
	 * 查询通信波特率编号对应的名称
	 * @param ComRate
	 * @return
	 */
	public String queryComRateById(@Param("ComRate")int ComRate);
	/**
	 * 查询通信协议编号对应的通信协议名称
	 * @param ComAgreementType
	 * @return
	 */
	public String queryComAgreementTypeById(@Param("ComAgreementType")int ComAgreementType);
	/**
	 * 	查询采集方案编号对应的采集方案名称
	 * @param CollectMethodID
	 * @return
	 */
	public String queryCollectorMetnodById(@Param("CollectMethodID")int CollectMethodID);
	/**
	 * 通过电表id查询对应的采集采集参数信息
	 * @param f_sys_name
	 * @return
	 */
	public List<Map<String, Object>> queryElectricParamsByAmmeter(@Param("f_sys_name")String f_sys_name);
	/**
	 * 查询电表所在的采集器
	 * @param string
	 * @return
	 */
	public Map<String, Object> querycollectorInfoByAmmeter(String f_psys_name);
	/**
	 * 查询电表最后一次保存的数据
	 * @param uuid
	 * @return
	 */
	public List<Map<String,String>> queryAmmeterLastCalculateData(String uuid);

	/**
	 * 将差值数据记录到差值记录表
	 * @param lstDto
	 */
	public void insertMonitoringData(@Param("lstDto") List<Map<String, Object>> lstDto);


	/**
	 * 从原始表中获取最后一条数据
	 */
	 List<Map<String,String>> queryLastData(@Param("f_dbsys_name") String f_dbsys_name,@Param("f_dnbh") String f_dnbh,@Param("f_type") String f_type);

	 List<Map<String,String>> queryLastDataList(List lstDto);
	/**
	 * 通过ip地址查询采集器信息
	 * @param IPAddr
	 * @return
	 */
	public Map<String, Object> getCollectorInfo(String IPAddr);

	/**
	 * 通过channelId地址查询采集器信息
	 * @param channelId
	 * @return
	 */
	public Map<String, Object> getCollectorByChannelId(String channelId);

	/**
	 * 根据采集器名称获取电表，同步采集器向下位机批量同步时使用
	 * @param collectorname 采集器系统名称
	 * add by liuzhen at 20190108
	 */
	public List<Map<String, Object>>  queryAmmeterByCollector(String collectorname);
	/**
	 * 查询总线下所有电表
	 * @param string
	 * @return
	 */
	public List<Map<String, Object>> queryAmmeterFromBus(String f_sys_name);

	/**
	 * 获取所有 opc key
	 * @return
	 */
	List<Map<String, Object>> getOpcKeyAll();
    /**
     * 查询区域下的虚点能耗电表
     * @param string
     * @return
     */
	public List<Map<String, Object>> queryReginAmmeter(@Param("f_sys_name")String f_sys_name);

	/**
	 *
	 * @Description: 查询点位所属的DDC控制器
	 *
	 * @auther: wanghongjie
	 * @date: 10:27 2020/6/29
	 * @param: [s]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	Map<String, Object> queryDDCByPointSysName(@Param("f_sys_name") String f_sys_name);

	/**
	 *
	 * @Description: 查询DDC控制器的信息
	 *
	 * @auther: wanghongjie
	 * @date: 14:07 2020/6/29
	 * @param: [s]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	Map<String, Object> queryDDCBySysName(@Param("f_sys_name") String f_sys_name);

	/**
	 *
	 * @Description:根据模块名称查询所属的ddc的channelId
	 *
	 * @auther: wanghongjie
	 * @date: 9:06 2020/7/2
	 * @param: [f_sys_name]
	 * @return: java.lang.String
	 *
	 */
    String querychannelIdByModule(@Param("f_sys_name") String f_sys_name);

    /**
     *
     * @Description: 根据模块名称查询模块的信息
     *
     * @auther: wanghongjie
     * @date: 9:59 2020/7/2
     * @param: [f_sys_name]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     *
     */
	Map<String, Object> queryModuleBySysName(@Param("f_sys_name") String f_sys_name);
	/**
	 *
	 * @Description: 根据channelId地址查询表 bes_ddc ddc是否存在
	 *
	 * @auther: wanghongjie
	 * @date: 16:56 2020/7/6
	 * @param: [ip]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	Map<String, Object> getDDCByChannelId(String channelId);

	/**
	 *
	 * @Description: 根据模块的id查询表 bes_module 模块是否存在
	 *
	 * @auther: wanghongjie
	 * @date: 11:32 2020/7/7
	 * @param: [id]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	Map<String, Object> getModuleById(String f_struct_id);

	/**
	 *
	 * @Description: 查询当前DDC下的模块
	 *
	 * @auther: wanghongjie
	 * @date: 16:08 2020/7/13
	 * @param: [f_sys_name]
	 * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *
	 */
	List<Map<String, Object>> queryModule(String f_sys_name);

	/**
	 *
	 * @Description: 查询DDC关联的虚点
	 *
	 * @auther: wanghongjie
	 * @date: 17:52 2020/7/13
	 * @param: [f_sys_name]
	 * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *
	 */
	List<Map<String, Object>> queryVpoint(String f_sys_name);
	/**
	 *
	 * @Description: 查询DDC关联的实点
	 *
	 * @auther: wanghongjie
	 * @date: 17:56 2020/7/13
	 * @param: [f_sys_name]
	 * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *
	 */
	List<Map<String, Object>> queryPoint(String f_sys_name);

	/**
	 * 根据电表名称查询出所有最大日期的能耗数据
	 * @param ammeterNames
	 * @return
	 */
	List<Map<String, Object>> queryEnergyByAmmeterNames(@Param("ammeterNames") Set<String> ammeterNames);

}
