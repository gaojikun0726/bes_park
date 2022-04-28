package com.efounder.JEnterprise.mapper.collectorJob;

import java.util.List;
import java.util.Map;

import com.efounder.JEnterprise.model.dataAnalysises.BesBranchConfAlarmConfiguration;
import com.efounder.JEnterprise.model.dataAnalysises.BesBranchConfParameter;
import org.apache.ibatis.annotations.Mapper;

import com.efounder.JEnterprise.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BESSubAlarmMapper extends BaseMapper<String, Object>{
	
	/**
	 * 按园区编号查询支路报警规则
	 * @param parkId
	 * @return
	 */
	List<Map<String, Object>> SelectAlarmSub(String parkId);

	/**
	 * 查询能源编号对应的能源名称
	 * @param F_NYBH
	 * @return
	 */
	public String selectEnergyTypeName(String F_NYBH);
	/**
	 * 查询报警规则所用到的电表电能参数
	 * @param F_ALERTID
	 * @return
	 */
	List<Map<String, Object>> SelectAlarmParmeSub(String F_ALERTID);
	/**
	 * 
	 * @param F_AMMSYS_NAME
	 * @param F_ELEDNBH
	 * @return
	 */
	Map<String, Object> SelectAlarmParmeData(@Param(value="F_AMMSYS_NAME") String F_AMMSYS_NAME,@Param(value="F_ELEDNBH") String F_ELEDNBH);

	List<Map<String, Object>> queryMailByPark(String parkid);
	/**
	 * 查询支路报警类型
	 * @param id
	 * @return
	 */
	String selectSubAlarm(String id);
	/**
	 *
	 * @Description: 查询当前电表的配置报警信息
	 *
	 * @auther: wanghongjie
	 * @date: 10:31 2020/6/2
	 * @param: [id]
	 * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *
	 */
	List<Map<String, Object>> selectId(String f_sys_name_old);
	/**
	 *
	 * @Description: 查询当前电表所包含的电能参数
	 *
	 * @auther: wanghongjie
	 * @date: 9:46 2020/6/3
	 * @param:
	 * @return:
	 *
	 */
	List<Map<String, Object>> SelectAlarmParmeF_nhbh(String meterID);
	/**
	 *
	 * @Description: 获取当前电表的变比  根据上传的sbid查询
	 *
	 * @auther: wanghongjie
	 * @date: 9:54 2020/6/12
	 * @param: [meterID]
	 * @return: java.lang.String
	 *
	 */
	String SelectAlarmParmeF_percentage(String meterID);

	/**
	 *
	 * @Description: 根据id查询是否有这个点位
	 *
	 * @auther: wanghongjie
	 * @date: 10:11 2020/7/27
	 * @param: [pointId]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	Map<String, Object> sbpzStructPointMap(Integer pointId);

	/**
	 *
	 * @Description: 查询实点点位在相应表中的数据
	 *
	 * @auther: wanghongjie
	 * @date: 10:40 2020/7/27
	 * @param: [tabName, pointId]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	Map<String, Object> selectPointMap(@Param("tabName") String tabName,@Param("pointId") String pointId);
	/**
	 *
	 * @Description:查询虚点点位在相应表中的数据
	 *
	 * @auther: wanghongjie
	 * @date: 10:53 2020/7/27
	 * @param: [tabName, valueOf]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	Map<String, Object> selectVPointMap(@Param("pointId") String pointId);

	/**
	 *
	 * @Description: 查询当前点位所属的模块
	 *
	 * @auther: wanghongjie
	 * @date: 14:57 2020/7/27
	 * @param: [pointId]
	 * @return: java.lang.String
	 *
	 */
	String selectModuleName(Integer pointId);

	/**
	 *
	 * @Description: 查询点位所属的控制器
	 *
	 * @auther: wanghongjie
	 * @date: 15:00 2020/7/27
	 * @param: [pointId]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	Map<String, Object> selectDDCPointMap(Integer pointId);

	/**
	 *
	 * @Description: 如果是AI点的电表,则向电表数据表 bes_calculate_data 查询当前AI点的数据
	 *
	 * @auther: wanghongjie
	 * @date: 14:05 2020/9/30
	 * @param: [f_ammsys_name]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	Map<String, Object> selectCalculateDataByAIPoint(String f_ammsys_name);

	/**
	 *
	 * @Description: 根据id查询电表数据表
	 *
	 * @auther: wanghongjie
	 * @date: 15:16 2021/1/23
	 * @param: [meterID]
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 *
	 */
	Map<String, Object> selectAmmeterMap(String meterID);

	/**
	 *
	 * @Description: 支路报警配置
	 *
	 * @auther: wanghongjie
	 * @date: 17:03 2021/6/3
	 * @param: []
	 * @return: java.util.List<com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESElectric_Coll_Rlgl>
	 *
	 */
	List<BesBranchConfAlarmConfiguration> loadAll();

	/**
	 *
	 * @Description: 从数据库中查询出全部”支路报警规则“数据。
	 *
	 * @auther: wanghongjie
	 * @date: 9:30 2021/6/4
	 * @param: []
	 * @return: java.util.List<com.efounder.JEnterprise.model.dataAnalysises.BesBranchConfParameter>
	 *
	 */
    List<BesBranchConfParameter> loadAllBranchConfParam();
}
