package com.efounder.JEnterprise.mapper.dataAnalysises;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.dataAnalysises.BesHouseholdPlanData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BesHouseholdPlanDataMapper extends BaseMapper<String, BesHouseholdPlanData>{

	/**
	 *
	 * @param besHouseholdPlanData
	 * @return
	 * 获取能源计划配置统计数据
	 */
	public List<BesHouseholdPlanData> getHouseholdPlanData(BesHouseholdPlanData besHouseholdPlanData);
	/**
	 *
	 * @param besHouseholdPlanData
	 * @return
	 * 更新分户数据
	 */
	public boolean updateHouseholdPlan(BesHouseholdPlanData besHouseholdPlanData);
	
	/**
	 *
	 * @param besHouseholdPlanData
	 * @return
	 * 更新能源计划配置数据
	 */
	public boolean updateHouseholdPlanData(BesHouseholdPlanData besHouseholdPlanData);


	
}