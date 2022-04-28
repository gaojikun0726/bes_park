package com.efounder.JEnterprise.mapper.dataAnalysises;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.dataAnalysises.BesOriginalData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BesOriginalDataMapper extends BaseMapper<String, BesOriginalData> {
	// 根据条件pin_table 二级支路 下所有的电表
	List<BesOriginalData> two_pin_table(@Param(value = "start") String start, @Param(value = "end") String end, @Param(value = "two_zlids") String zlids);

	// 三级支路 所有传过来id的电表
	List<BesOriginalData> three_pin_table(@Param(value = "start") String start, @Param(value = "end") String end, @Param(value = "amtterids") String amtterids);

	// 查询实时数据
	List<Map<String, String>> getsssjlist_two(@Param(value = "amtterids") String amtterids, @Param(value = "dnbh") String dnbh);

	// 利用列转行查询二级节点下所有支路id
	String[] getzlids(@Param(value = "zlid") String zlid);

	// 对所有支路查询所有电表
	String[] getamterids(@Param(value = "zlids") String[] zlids);

	// 根据电表id查询相关数据
	Map<String, String> getdbxx(@Param(value = "ammeterid") String ammeterid);

	// 获取起始数据
	String getStrat_data(@Param(value = "start") String start, @Param(value = "ammeterid") String ammeterid);

	// 获取终止数据
	String getEnd_data(@Param(value = "end") String end, @Param(value = "ammeterid") String ammeterid);

	// 获取支路ids 下所有电表
	List<Map<String, String>> getamtterlist(@Param(value = "zlids") String[] zlids);
	
	// 分时段统计2级
	Map<String, String> Select_fsdtj(@Param(value = "zlid") String zlid, @Param(value = "start") String start, @Param(value = "end") String end);

	// 分时段统计非2级
	Map<String, String> Select_fsdtj_notwo(@Param(value = "zlid") String zlid, @Param(value = "start") String start, @Param(value = "end") String end);

	// 获取支路名称
	String getzlmc(String zlid);

	// 获取支路级数
	String getjs(@Param(value = "zlid") String zlid);

	// 获取分户支路名称
	String getfhzlmc(String zlid);

	// 获取分项支路名称
	String getfxzlmc(String zlid);

	// 获取电表list
	List<Map<String, String>> Select_ammeterlist(@Param(value = "zlidList") String[] zlidList);

	// 根据电表id获取电能参数
	List<Map<String, String>> zl_cscx_Select_dncs(@Param(value = "ammeterid") String ammeterid);

	// 电能参数统计分析
    List<Map<String, String>> dncsList(Map<String, Object> map);

	// 取data 根绝时间
	String getdate(@Param(value = "ammeterid") String ammeterid, @Param(value = "dncsid") String dncsid, @Param(value = "time") String time);

	// 队能参数名称
	String getdncsname(@Param(value = "dncsid") String dncsid);

	// 分户用能分时段统计
	Map<String, String> fh_Select_fsdtj(@Param(value = "zlid") String zlid, @Param(value = "start") String start, @Param(value = "end") String end);

	// 分项用能分时段统计
	Map<String, String> fx_Select_fsdtj(@Param(value = "zlid") String zlid, @Param(value = "start") String start, @Param(value = "end") String end);

	// 先查询时间段内有数据的起始时间
	List<Map<String, String>> getqslist(@Param(value = "start") String start, @Param(value = "end") String end, @Param(value = "dbid") String dbid);

	// 先查询时间段内有数据的起始时间
	List<Map<String, String>> getByStartTimeAndEndTimeAndSysName(@Param(value = "start") String start,
																 @Param(value = "end") String end,
																 @Param(value = "dbid") List dbid);

	//
	List<Map<String, String>> getheaderList(@Param(value = "fnybh") String fnybh);

	/**
	 *
	 * @Description: 根据能源类型查询能源单位
	 *
	 * @auther: wanghongjie
	 * @date: 9:58 2021/1/11
	 * @param: [energyType]
	 * @return: java.lang.String
	 *
	 */
    String getUnitByEnergyType(String energyType);

	/**
	 *
	 * @Description: 根据电表系统名称查询当前电表的电能参数
	 *
	 * @auther: wanghongjie
	 * @date: 9:40 2021/3/13
	 * @param: [amttername]
	 * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *
	 * @param amttername
	 */
	List<Map<String, Object>> selectElectric_paramsList(@Param(value = "amttername") String[] amttername);

}