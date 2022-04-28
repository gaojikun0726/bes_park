package com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration;

import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.BESFlowType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/** 
 * 类名称：FlowChartMapper
 * 类描述：
 * 创建人：tjw
 * @version 1.0.0 
 *
 */
 @Mapper
 public interface FlowChartMapper {

	/**
	 * 添加流程图信息
	 * @param besFlowType
	 */
	boolean addFlowType(BESFlowType besFlowType);

	/**
	 * 查询流程图信息
	 * @return
	 */
	List<BESFlowType> getFlowTypeInfo();

	/**
	 * 修改流程图信息
	 * @param besFlowType
	 * @return
	 */
	boolean editFlowType(BESFlowType besFlowType);

	/**
	 * 删除流程类型
	 * @param besFlowType
	 * @return
	 */
	boolean deleteFlowType(BESFlowType besFlowType);

	/**
	 * 删除流程图内容
	 * @param besFlowType
	 * @return
	 */
	boolean deleteFlowChart(BESFlowType besFlowType);
}
