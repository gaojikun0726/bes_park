package com.efounder.JEnterprise.mapper.collectorJob;

import com.efounder.JEnterprise.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BESCalculateDataMapper extends BaseMapper<String, Object>{

	public Map<String, Object> queryParkById(@Param("parkId") String parkId);

	public List<Map<String, Object>> queryAllEnergyType();

	public List<Map<String, Object>> queryHouseParam(@Param("parkid")   String parkid);

	public List<Map<String, Object>> queryBranch(@Param("equipment_set") String equipment_set,
			@Param("parentid") String parentid, @Param("parkId") String parkId);

	public List<Map<String, Object>> queryAmmeter(@Param("F_ZLBH") String F_ZLBH);

	public Map<String, Object> queryAmmeterStartData(@Param("startTime") String startTime, @Param("uuid") String uuid);

	public Map<String, Object> queryAmmeterEndData(@Param("startTime") String startTime,
			@Param("endTime") String endTime, @Param("uuid") String uuid);

	public Map<String, Object> queryAmmeterStartData2(@Param("startTime") String startTime, @Param("uuid") String uuid);

	public Map<String, Object> queryBranchExists(@Param("id") String id, @Param("time") String time,@Param("type")  String type);

	public List<Map<String, Object>> queryEnergyTypeByParkid(@Param("parkId") String parkId);

	public Map<String, Object> queryEnergySumData(@Param("type") String type, @Param("time") String time, @Param("energyid") String energyid,
			@Param("parkId") String parkId);

	public List<Map<String, Object>> queryGrade(@Param("id") String id,@Param("parentid") String parentid);

	public Map<String, Object> queryGradeSumData(@Param("type")  String type, @Param("time") String time, @Param("fFxbh") String fFxbh/*, @Param("parkId") String parkId*/);

	public List<Map<String, Object>> queryHouseHold(@Param("parkId") String parkId);

	public Map<String, Object> queryRelBranchCount(@Param("id") String id);

	public Map<String, Object> queryHouseholdSumData(@Param("type") String type, @Param("time") String time, @Param("id") String id);

	public List<Map<String, Object>> queryHouseBranch(@Param("id")  String id);

	public List<Map<String, String>> queryBranchGradeData(@Param("id") String id,@Param("time") String time,@Param("type") String type);
	/**
	 * 查询上次采集时间
	 * @return
	 */
	public Map<String, Object> queryLastTime();
	/**
	 * 查询本次数据
	 * @param string
	 * @param parkId
	 * @return
	 */
	public List<Map<String, Object>> queryCurData(@Param("energyid") String energyid,@Param("parkid")  String parkid);
	/**
	 * 查询上次数据
	 * @param energyid
	 * @param parkid
	 * @return
	 */
	public List<Map<String, Object>> queryLastData(@Param("energyid") String energyid,@Param("parkid")  String parkid);

	public boolean intAmmeterData(@Param("f_sys_name") String f_sys_name,@Param("f_data")  String f_data,@Param("f_yqbh") String f_yqbh,@Param("f_id")  String f_id);


	List<Map<String, Object>> queryBranchAmmeterRlglBySysName(@Param("f_sys_name") String f_sys_name);

	/**
	 * 根据支路编号查询关联的分项信息
	 * @param fZlbh
	 * @return
	 */
	List<Map<String, Object>> querySBRListByZlbh(@Param("fZlbh") String fZlbh);
	/**
	 * 根据支路编号查询关联的分户信息
	 * @param fZlbh
	 * @return
	 */
	List<Map<String, Object>> queryFhbhByZlbh(@Param("fZlbh") String fZlbh);

	/**
	 * 查询电表数据根据时间
	 * @param startTime 开始采集时间
	 * @param endTime 结束采集时间
	 * @return
	 */
	List<Map<String, Object>> queryAmmeterDataByTime(@Param("startTime") String startTime, @Param("endTime") String endTime);

}
