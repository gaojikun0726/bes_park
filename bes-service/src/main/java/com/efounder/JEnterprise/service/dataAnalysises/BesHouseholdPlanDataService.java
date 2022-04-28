package com.efounder.JEnterprise.service.dataAnalysises;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.dataAnalysises.BesHouseholdPlanData;
/**
 * 能源计划分配
 * @author liuzhen
 *
 */
public interface BesHouseholdPlanDataService {

	
	/**
	 *
	 * @param besHouseholdplanData
	 * @return
	 * 获取能源计划分配分析数据
	 */
    ISSPReturnObject getHouseholdPlanData(BesHouseholdPlanData besHouseholdplanData);
    
    /**
	 *
	 * @param besHouseholdplanData
	 * @return
	 * 更新分户数据
	 */
   public ISSPReturnObject updateHouseholdPlan(BesHouseholdPlanData besHouseholdplanData);
   

	/**
	 *
	 * @param besHouseholdplanData
	 * @return
	 * 更新能源计划配置数据
	 */
	public ISSPReturnObject updateHouseholdPlanData(BesHouseholdPlanData besHouseholdplanData);
    
}
