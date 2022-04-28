package com.efounder.JEnterprise.mapper.realtimemonitoring;

import com.efounder.JEnterprise.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BESEnergyMonitoringMapper extends BaseMapper<String, Object>{
	/**
	 * 查询支路下所有电表数据
	 * @param f_zlbh
	 * @return
	 */
	List<Map<String, String>> queryAmmeterData(@Param(value="f_zlbh")String f_zlbh,@Param(value="dateString") String dateString);
	/**
	 * 查询支路下所有电表信息
	 * @param f_zlbh
	 * @return
	 */
	List<Map<String, String>> queryAmmeterList(String f_zlbh);
	/**
	 * 查询电表今日数据
	 * @param f_sys_name
	 * @param dateString
	 * @return
	 */
	List<Map<String, String>> queryAmmeterDataList(@Param(value="ammeters")List<String> ammeters ,@Param(value="dateString") String dateString,
												   @Param(value="dnbh") String dnbh);

}
