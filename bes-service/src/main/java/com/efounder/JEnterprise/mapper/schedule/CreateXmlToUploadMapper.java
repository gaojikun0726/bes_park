package com.efounder.JEnterprise.mapper.schedule;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.schedule.BuildBaseInfo;
import com.efounder.JEnterprise.model.schedule.CommonAndDataCenterBaseInfo;
import com.efounder.JEnterprise.model.schedule.EnergyItemHourResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CreateXmlToUploadMapper extends BaseMapper<String, Object>{
//	List<Map<String, String>> queryAmmeterData(@Param(value="f_zlbh")String f_zlbh,@Param(value="dateString") String dateString);
//	List<Map<String, String>> queryAmmeterDataList(@Param(value="ammeters")List<String> ammeters ,@Param(value="dateString") String dateString,
//												   @Param(value="dnbh") String dnbh);
	/**
	 * 查询获取生成建筑文件信息的通用信息段和数据中心基本信息
	 * @return
	 */
	List<CommonAndDataCenterBaseInfo> getCommonAndDataCenterBaseInfo();
	
	/**
	 * 查询获取建筑物基础信息
	 * @return
	 */
	List<BuildBaseInfo> getBuildBaseInfo();
	
	String getBudingCode();

	/**
	 * 获取建筑物id集合用于生成能耗XML文件，注意：此sql暂时只适用于廉政
	 * @return
	 */
	List<String> getNodeCodeList();

	/**
	 * 获取建筑物id集合用于生成能耗XML文件
	 * @return
	 */
	List<String> getNodeCodeList1();
	
	/**
	 * 查出能耗分类分项编码统帅  TODO:此方法可以合至下方方法
	 * @return
	 */
	List<String> getZlbhList();

	/**
	 * 查出能耗分类分项编码 
	 * @return
	 */
	List<String> getZlbhListByZlbhTs(String zlbh);

	List<EnergyItemHourResult> getEnergyItemHourResultList(@Param(value="electricityZlbh")String electricityZlbh,
			@Param(value="waterZlbh")String waterZlbh, @Param(value="gasZlbh")String gasZlbh,@Param(value="startHour")String startHour);
}
