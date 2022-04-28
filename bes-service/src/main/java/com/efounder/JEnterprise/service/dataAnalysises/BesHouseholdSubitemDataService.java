package com.efounder.JEnterprise.service.dataAnalysises;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.dataAnalysises.BesHouseholdSubitemData;
/**
 * 分户用能分户分项同比环比分析
 * @author liuzhen
 *
 */
public interface BesHouseholdSubitemDataService {

	
	/**
	 *
	 * @param besHouseholdSubitemData
	 * @return
	 * 获取分户用能分户分项同比环比分析计划
	 */
    ISSPReturnObject getHouseholdSubitemData(BesHouseholdSubitemData besHouseholdSubitemData);
    
   
    
}
