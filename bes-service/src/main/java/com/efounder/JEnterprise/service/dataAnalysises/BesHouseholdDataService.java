package com.efounder.JEnterprise.service.dataAnalysises;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.dataAnalysises.BesHouseholdData;

import javax.servlet.http.HttpServletRequest;

/**
 * 分户用能趋势分析接口
 * @author liuzhen
 *
 */
public interface BesHouseholdDataService {

	
	/**
	 *
	 * @param besHouseholdData
	 * @return
	 * 获取分户用能趋势统计分析数据
	 */
    ISSPReturnObject getHouseholdData(BesHouseholdData besHouseholdData, HttpServletRequest request);
    
    /**
	 *
	 * @param besHouseholdData
	 * @return
	 * 获取分户用能同比环比分析数据
	 */
    ISSPReturnObject getTbhbHouseholdData(BesHouseholdData besHouseholdData);

	/**
	 *
	 * @param besHouseholdData
	 * @return
	 * 获取分户用能趋势统计分析数据
	 */
	ISSPReturnObject getHouseholdEveryData(BesHouseholdData besHouseholdData, HttpServletRequest request);

	/**
	 * 获取分户年数据及月峰值
	 * @param request
	 * @return
	 */
	ISSPReturnObject getHouseDataYearData(HttpServletRequest request);


	/**
	 * 获取分户用能趋势统计分析数据
	 * @param besHouseholdData
	 * @return
	 */
	ISSPReturnObject queryHouseHoldData(BesHouseholdData besHouseholdData);
}
