package com.efounder.JEnterprise.mapper.dataAnalysises;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.dataAnalysises.BesHouseholdData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BesHouseholdDataMapper extends BaseMapper<String, BesHouseholdData>{

	BesHouseholdData queryHouseholdExists(BesHouseholdData besHouseholdData);

	public void saveHouseholdData(BesHouseholdData besHouseholdData);

	public void updateHousehold(BesHouseholdData besHouseholdData);

	Boolean updateHouseholdData(BesHouseholdData besHouseholdData);
	/**
	 *
	 * @param besHouseholdData
	 * @return
	 * 获取分户用能趋势统计数据
	 */
	public List<BesHouseholdData> searchHouseholdData(BesHouseholdData besHouseholdData);

	/**
	 *
	 * @Description: 查询水,电,气下园区的数据
	 *
	 * @auther: wanghongjie
	 * @date: 11:20 2020/7/18
	 * @param: [fNybh, fCjsj_start]
	 * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *
	 */
	List<Map<String, Object>> searchHouseholdDataByPark(@Param("yqbh") String yqbh,@Param("fNybh") String fNybh,@Param("fCjsj_start") String fCjsj_start);

	/**
	 *
	 * @Description: 今日同比的本期
	 *
	 * @auther: wanghongjie
	 * @date: 15:18 2020/7/20
	 * @param: [fNybh, fCjsj_start]
	 * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *
	 */
	List<Map<String, Object>> searchHouseholdDataByParkBQ(@Param("yqbh") String yqbh,@Param("fNybh") String fNybh,@Param("fCjsj_start") String fCjsj_start,@Param("fType") String fType);
	/**
	 *
	 * @Description: 今日同比的同期
	 *
	 * @auther: wanghongjie
	 * @date: 16:32 2020/7/20
	 * @param:
	 * @return:
	 *
	 */
	List<Map<String, Object>> searchHouseholdDataByParkTQ(@Param("yqbh") String yqbh,@Param("fNybh") String fNybh,@Param("ftbhb_sametime_start") String ftbhb_sametime_start,@Param("fType") String fType);

	/**
	 *
	 * @Description: 根据能源编号查询能源名称
	 *
	 * @auther: wanghongjie
	 * @date: 11:07 2020/7/21
	 * @param: [fNybh]
	 * @return: java.lang.String
	 *
	 */
	Map<String,Object> selectEnergyInformationByNybh(@Param("fNybh") String fNybh);
	
	/**
	 *
	 * @Description: 本月同比本期
	 * 
	 * @auther: wanghongjie
	 * @date: 14:17 2020/7/21
	 * @param: [fNybh, fCjsj_start, fType]
	 * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *
	 */
	List<Map<String, Object>> searchHouseholdDataByParkMonthBQ(@Param("yqbh") String yqbh,@Param("fNybh") String fNybh,@Param("fCjsj_start") String fCjsj_start,@Param("fType") String fType);

	/**
	 *
	 * @Description: 本月同比同期
	 *
	 * @auther: wanghongjie
	 * @date: 14:18 2020/7/21
	 * @param: [fNybh, ftbhb_sametime_start, fType]
	 * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *
	 */
	List<Map<String, Object>> searchHouseholdDataByParkMonthTQ(@Param("yqbh") String yqbh,@Param("fNybh")String fNybh,@Param("ftbhb_sametime_start") String ftbhb_sametime_start,@Param("fType") String fType);


	/**
	 * 根据分户编号查询月能源数据
	 * @param
	 * @param fNybh
	 * @param
	 * @return
	 */
	List<Map<String, Object>> GetHouseMouthData(@Param("fNybh") String fNybh,@Param("cjsj_start") String cjsj_start,@Param("cjsj_end") String cjsj_end);



	/**
	 * 分户获取本年数据最高
	 * @param fhbh
	 * @param yqbh
	 * @param
	 * @return
	 */
	String selectHouseHoldDataByYear(@Param("fhbh") String fhbh,@Param("yqbh") String yqbh,@Param("firstDay") String firstDay);


	/**
	 * 获取本年月峰值
	 * @param fhbh
	 * @param yqbh
	 * @param firstDay
	 * @param endData
	 * @return
	 */
	String selectHouseHoldByHighMonth(@Param("fhbh") String fhbh,@Param("yqbh") String yqbh,@Param("firstDay") String firstDay,@Param("endData") String endData);
	/**
	 *
	 * @Description: //首先根据分户编号,查询当天的时间和值
	 *
	 * @auther: wanghongjie
	 * @date: 9:33 2021/4/27
	 * @param: [fFhbhs, fCjsj_start, f_type]
	 * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *
	 */
    List<Map<String, Object>> selectRankEnergyList(@Param("fFhbhs") String[] fFhbhs,@Param("fCjsj_start") String fCjsj_start,@Param("f_type") String f_type);

    /**
     *
     * @Description: 本期
     *
     * @auther: wanghongjie
     * @date: 14:19 2021/4/27
     * @param: [fFhbhs, s, firstTime]
     * @return: java.lang.String
     *
     */
    String selectThisPeriod(@Param("fFhbhs") String[] fFhbhs,@Param("type") String type,@Param("firstTime") String firstTime);



	/**
     *
     * @Description: 同期
     *
     * @auther: wanghongjie
     * @date: 14:19 2021/4/27
     * @param:
     * @return:
     *
     */
	String selectCorrTimePeriod(@Param("fFhbhs")String[] fFhbhs,@Param("type") String type,@Param("secondTime") String secondTime);

	/**
	 *
	 * @Description: 本年能耗
	 *
	 * @auther: wanghongjie
	 * @date: 18:26 2021/4/27
	 * @param: [fhbhs, firstDay, s]
	 * @return: java.lang.String
	 *
	 */
    String selectEnergyConsumptionThisYear(@Param("fhbhs") String[] fhbhs,@Param("firstDay") String firstDay,@Param("type") String type);

    /**
     *
     * @Description: 本年峰值能耗
     *
     * @auther: wanghongjie
     * @date: 18:31 2021/4/27
     * @param: [fFhbhs, firstDay, endData, s]
     * @return: java.lang.String
     *
     */
	String peakEnergyConsumptionThisYear(@Param("fhbhs") String[] fFhbhs,@Param("firstDay") String firstDay,@Param("endData") String endData,@Param("type") String type);
}
