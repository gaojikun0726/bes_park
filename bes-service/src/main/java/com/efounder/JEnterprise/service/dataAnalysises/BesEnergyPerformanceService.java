package com.efounder.JEnterprise.service.dataAnalysises;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.dataAnalysises.BesEnergyPerformance;
/**
 * 能源计划分配
 * @author liuzhen
 *
 */
public interface BesEnergyPerformanceService {

	
	/**
	 *
	 * @param besEnergyPerformance
	 * @return
	 * 获取能源绩效考核数据
	 */
    ISSPReturnObject getEnergyPerformance(BesEnergyPerformance besEnergyPerformance);
    
    
    
}
