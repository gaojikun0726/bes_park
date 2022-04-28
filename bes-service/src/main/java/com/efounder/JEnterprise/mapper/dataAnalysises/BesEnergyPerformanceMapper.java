package com.efounder.JEnterprise.mapper.dataAnalysises;


import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.dataAnalysises.BesEnergyPerformance;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 能源绩效考核
 * @author liuzhen
 *
 */
@Mapper
public interface BesEnergyPerformanceMapper extends BaseMapper<String, BesEnergyPerformance>{

	
	/**
	 * @param besEnergyPerformance
	 * @return
	 * 获取能源绩效考核数据
	 */
	public List<BesEnergyPerformance> searchEnergyPerformance(BesEnergyPerformance besEnergyPerformance);

	public List<BesEnergyPerformance> searchEnergyPlanPerformance(BesEnergyPerformance besEnergyPerformance);


}