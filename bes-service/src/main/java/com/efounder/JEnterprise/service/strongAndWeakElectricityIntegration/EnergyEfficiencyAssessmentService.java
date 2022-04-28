package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration;


import com.core.common.ISSPReturnObject;

import java.util.Map;

public interface EnergyEfficiencyAssessmentService {
	/**
	 * 查询系统能效按钮关联点位信息
	 * @param sysnameArray
	 * @return
	 */
	ISSPReturnObject queryEnergyEfficiencyListInfo(String[] sysnameArray);

	/**
	 * 查询饼图点位关联及计算公式相关信息
	 * @param map
	 * @return
	 */
    ISSPReturnObject queryPieRelativeInfo(Map map);

	/**
	 * 饼图点位关联及计算公式相关信息
	 * @param map
	 * @return
	 */
	ISSPReturnObject addPieRelativeInfo(Map map);

	/**
	 * 饼图所有点位关联及计算公式信息
	 * @param sysnameArray
	 * @return
	 */
    ISSPReturnObject queryAllPieRelativeInfo(String[] piePointArray);

	/**
	 * 查询列表关联点位信息
	 * @param map
	 * @return
	 */
	ISSPReturnObject queryLableRelativeInfo(Map map);

	/**
	 * 查询实时能耗配置信息
	 * @param sysnameArray
	 * @return
	 */
	ISSPReturnObject queryRealTimeEnergyEfficiencyBtnInfo(String[] sysnameArray);
}
