package com.efounder.JEnterprise.mapper.interfaceUtil;

import com.efounder.JEnterprise.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BESSbpzInterfaceMapper extends BaseMapper<String, Object>{

	List<Map<String, Object>> getDdcInfo(@Param("checkDataLimit") String checkDataLimit);

	List<Map<String, Object>> getKtpzInfo(@Param("checkDataLimit") String checkDataLimit);

	List<Map<String, Object>> getKtpzList(@Param("ktName") String name,@Param("checkDataLimit") String checkDataLimit);

	List<Map<String, Object>> getWkqpzInfo(@Param("checkDataLimit") String checkDataLimit);

	List<Map<String, Object>> getWkqpzList(@Param("wkqName") String name,@Param("checkDataLimit") String checkDataLimit);

	List<Map<String, Object>> getSingleLevelList(@Param("fSysName") String fSysName,@Param("checkDataLimit") String checkDataLimit);

	List<Map<String, Object>> getThirdLevelList(String valueOf);

	List<Map<String, Object>> getFourthLevelList(String valueOf);


	List<Map<String, Object>> getNext(@Param("fSysName") String fSysName);

	List<Map<String, Object>> getPLevel(@Param("f_PSYS_NAME") String f_PSYS_NAME);

	List<Map<String, Object>> getMonitoring(@Param("checkDataLimit") String checkDataLimit);

	List<Map<String, Object>> getFreshairconfigList(@Param("checkDataLimit") String checkDataLimit);

	List<Map<String, Object>> getFreshairconfig(@Param("windName") String name,@Param("checkDataLimit") String checkDataLimit);

	List<Map<String, Object>> getAlloffList(@Param("systemName") String systemName);
	Map<String, Object> getAOoffList(@Param("systemName") String systemName);
	Map<String, Object> getDOoffList(@Param("systemName") String systemName);
	Map<String, Object> getAIoffList(@Param("systemName") String systemName);
	Map<String, Object> getDIoffList(@Param("systemName") String systemName);
	Map<String, Object> getPOoffList(@Param("systemName") String systemName);

    /**
     * 获取所有灯光配置信息
     * @return
     */
    List<Map<String,Object>> getLightList(@Param("checkDataLimit") String checkDataLimit);

    /**
     * 根据灯光名称获取灯光配置信息
     * @param lightName
     * @return
     */
    List<Map<String, Object>> getLight(@Param("lightName") String lightName,@Param("checkDataLimit") String checkDataLimit);

	/**
	 * 获取所有可调灯光配置信息
	 * @return
	 */
	List<Map<String,Object>> getTgLightList(@Param("checkDataLimit") String checkDataLimit);

	/**
	 * 根据可调灯光名称获取可调灯光配置信息
	 * @param lightName
	 * @return
	 */
	List<Map<String, Object>> getTgLight(@Param("lightName") String lightName,@Param("checkDataLimit") String checkDataLimit);

	/**
	 * 获取全部电动窗帘
	 * @param checkDataLimit
	 * @return
	 */
	List<Map<String, Object>> getDdclInfo(@Param("checkDataLimit") String checkDataLimit);

	/**
	 * 获取单个电动窗帘
	 * @param name
	 * @param checkDataLimit
	 * @return
	 */
	List<Map<String, Object>> getDdclList(@Param("CLName") String name,@Param("checkDataLimit") String checkDataLimit);

	/**
	 * 获取所有插座配置信息
	 * @return
	 */
	List<Map<String,Object>> getSocketList(@Param("checkDataLimit") String checkDataLimit);

	/**
	 * 根据插座名称获取插座配置信息
	 * @param socketName
	 * @return
	 */
	List<Map<String, Object>> getSocket(@Param("socketName") String socketName,@Param("checkDataLimit") String checkDataLimit);

	/**
	 * 获取所有组合式空调机组配置信息
	 * @return
	 */
	List<Map<String,Object>> getAirconditioningUnitList(@Param("checkDataLimit") String checkDataLimit);

	/**
	 * 根据组合式空调机组名称获取组合式空调机组配置信息
	 * @param airconditioningUnitName
	 * @return
	 */
	List<Map<String, Object>> getAirconditioningUnit(@Param("airconditioningUnitName") String airconditioningUnitName,@Param("checkDataLimit") String checkDataLimit);


}
