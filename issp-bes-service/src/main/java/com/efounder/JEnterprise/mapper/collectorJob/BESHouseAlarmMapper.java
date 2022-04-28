package com.efounder.JEnterprise.mapper.collectorJob;

import com.efounder.JEnterprise.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Mapper
public interface BESHouseAlarmMapper extends BaseMapper<String, Object>{
	
	/**
	 * 按园区编号查询支路报警规则
	 * @param yqbh
	 * @return
	 */
	String  selectParkNameByYqbh(@Param(value="yqbh")String yqbh);

	/**
	 * 查询月份能耗真实数据 是否存在
	 * @return
	 */
	List<Map<String, Object>> selectDateBytypeAndId(@Param(value="startTime")String startTime,@Param(value="endTime")String endTime,@Param(value="yqbh")String yqbh);
	/**
	 * 查询月份能耗真实数据 是否存在查询月份能耗计划 是否存在
	 * @return
	 */
	List<Map<String, Object>> selectPlanInfoByMon(@Param(value="pmonth")String pmonth,@Param(value="pyear")String pyear,@Param(value="yqbh")String yqbh);

	/**
	 * 通过父节点查询
	 * @return
	 */
	Map<String, Object> selectAllId(@Param(value="parentid")String parentid);

	String selectEnergyTypeNameByDto(String houseid);

	List<Map<String, Object>> queryAllPark();
}
