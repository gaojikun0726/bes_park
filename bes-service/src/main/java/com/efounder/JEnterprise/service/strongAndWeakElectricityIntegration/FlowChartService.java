package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration;


import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.BESFlowType;

public interface FlowChartService {


	ISSPReturnObject addFlowType(BESFlowType besFlowType);

	/**
	 * 查询流程图信息
	 * @return
	 */
	ISSPReturnObject getFlowTypeInfo();

	/**
	 * 修改流程图信息
	 * @param besFlowType
	 * @return
	 */
    ISSPReturnObject editFlowType(BESFlowType besFlowType);

	/**
	 * 删除流程图
	 * @param besFlowType
	 * @return
	 */
    ISSPReturnObject deleteFlowType(BESFlowType besFlowType);
}
