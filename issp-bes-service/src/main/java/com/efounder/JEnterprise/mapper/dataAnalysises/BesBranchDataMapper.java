package com.efounder.JEnterprise.mapper.dataAnalysises;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.dataAnalysises.BesBranchData;
import com.efounder.JEnterprise.model.dataAnalysises.BesHouseholdData;
import com.efounder.JEnterprise.model.dataAnalysises.BesQstjfxData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 支路数据 Mapper 接口
 * 
 * @author LvSihan
 *
 */
@Mapper
public interface BesBranchDataMapper extends BaseMapper<String, BesBranchData> {

	BesBranchData queryBranchExists(BesBranchData besBranchData);

	public void saveBranchData(BesBranchData besBranchData);

	public void updateBranch(BesBranchData besBranchData);

	List<Map<String, Object>> queryBranchData(@Param(value = "startTime") String startTime,
			@Param(value = "endTime") String endTime, @Param(value = "type") String type);
	
	//获取tab-list信息
	List<Map<String,String>> getZl_tablist();
	
	//支路树
	List<Map<String,String>> loadAll(@Param(value="fZzjgbh")String fZzjgbh,@Param(value="fnybh")String fnybh,
									 @Param("groupbh") List<String> groupbh,@Param("userId") String userId);

	List<Map<String,String>> loadAllDep(@Param(value="fZzjgbh")String fZzjgbh,@Param(value="fnybh")String fnybh,
									 @Param("groupbh") List<String> groupbh,@Param("userId") String userId);
	
	//拼装xlist
	Map<String,String> pin_xmap(@Param(value="zlbh")String zlid);
	
	//1.时间颗粒度 时 表头
	List<Map<String,Object>> sjkld_s_bt(@Param(value="sjgs")String sjgs,@Param(value="zlbh")String [] zlid,@Param(value="time_start")String time_start,@Param(value="time_end")String time_end,@Param(value="sjkld")String sjkld,@Param(value="nhlx")String nhlx);

	//1.1 
    List<Map<String, Object>> sjkld_s_bt_s(@Param(value = "sjgs") String sjgs, @Param(value = "zlbh") String[] zlid,
            @Param(value = "btsj") String btsj, @Param(value = "sjkld") String sjkld,
            @Param(value = "nhlx") String nhlx, @Param(value = "time_start") String time_start,
            @Param(value = "time_end") String time_end);

	//1.3
	Map<String, String> sjkld_s_bt_nr(@Param(value = "zlid") String zlid, @Param(value = "sj") String sj, @Param(value = "sjkld") String sjkld, @Param(value = "nhlx") String nhlx,
			@Param(value = "dwhs") String dwhs);


	// 获取支路名称 根据支路id
	String getzlmc(String zlid);

    // 重写数据分析支路趋势统计分析逻辑1.0 先根据条件将所有数据查询出来
    List<Map<String, Object>> bqAllData(BesQstjfxData dto);

    List<Map<String, Object>> tqAllData(BesQstjfxData dto);

	Boolean updateBranchData(BesBranchData besBranchData);

	/**
	 *
	 * @Description: 根据支路编号和时间查询数据库bes_branch_data表中是否有数据
	 *
	 * @auther: wanghongjie
	 * @date: 15:52 2020/9/24
	 * @param: [s, replace]
	 * @return: java.lang.Boolean
	 *
	 */
	List<Map<String,Object>> selectWhetherFdata(@Param("zlbh") String zlbh,@Param("time") String time,@Param("f_type") String f_type);

	List<Map<String, Object>> getBranchData(@Param("time") String time,@Param("type") String type);

	/**
	 *
	 * @Description: 加载能源统计分析--支路用能--用能统计分析
	 *
	 * @auther: wanghongjie
	 * @date: 11:53 2021/5/11
	 * @param: [besQstjfxData]
	 * @return: java.util.List<com.efounder.JEnterprise.model.dataAnalysises.BesHouseholdData>
	 *
	 */
	List<BesHouseholdData> searchstatisAnalyOfEnergyConsumptionData(BesQstjfxData besQstjfxData);

	List<BesBranchData> searchstatisAnalyOfEnergyConsumptionDataDep(@Param("fZlbh") String fZlbh,@Param("fType") String fType,@Param("nhlx") String nhlx,@Param("time_start") String time_start,@Param("time_end") String time_end,@Param("branchList") List<Map<String,Object>> branchList ,@Param("ammeterList") List<Map<String,Object>> ammeterList);

	/**
	 *
	 * @Description: 获取本期数据
	 *
	 * @auther: wanghongjie
	 * @date: 16:35 2021/5/12
	 * @param: [besQstjfxData]
	 * @return: java.util.List<com.efounder.JEnterprise.model.dataAnalysises.BesHouseholdData>
	 *
	 */
    List<BesHouseholdData> yoyAndMoMAnalysisBQ(BesQstjfxData besQstjfxData);

    /**
     *
     * @Description: 获得同期数据
     *
     * @auther: wanghongjie
     * @date: 16:36 2021/5/12
     * @param: [besQstjfxData]
     * @return: java.util.List<com.efounder.JEnterprise.model.dataAnalysises.BesHouseholdData>
     *
     */
	List<BesHouseholdData> yoyAndMoMAnalysisTQ(BesQstjfxData besQstjfxData);

	List<Map<String,Object>> queryAllBranch(@Param("list") List<String> list);

	List<Map<String,Object>> queryAllBranchByDep(@Param("str") String str);

	List<Map<String,Object>> queryAllAmmeter(@Param("list") List<String> list);

	List<Map<String,Object>> queryAllAmmeterByDep(@Param("str") String str);

	List<Map<String,Object>> queryAllDepByStrategy(@Param("strategyId") String strategyId);

	List<Map<String,Object>> queryAllBranchByDepList(@Param("DepId") String DepId);

	List<Map<String,Object>> queryAllAmmeterByDepList(@Param("DepId") String DepId);

	List<Map<String,Object>> queryDepDataByList(@Param("fType") String fType,@Param("nhlx") String nhlx,@Param("time_start") String time_start,@Param("time_end") String time_end,@Param("branchList") List<Map<String,Object>> branchList,@Param("ammeterList") List<Map<String,Object>> ammeterList);

}