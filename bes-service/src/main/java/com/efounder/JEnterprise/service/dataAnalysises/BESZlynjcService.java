package com.efounder.JEnterprise.service.dataAnalysises;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.dataAnalysises.BesOriginalData;

import javax.servlet.http.HttpServletRequest;

/**
* @author  杨超
* @version 创建时间：2018年11月1日 上午10:45:29
* @parameter 
* @version 1.0
*/
public interface BESZlynjcService {

	// 根据条件pin-table
	ISSPReturnObject pin_table(BesOriginalData dto);

	// 实时展示数据
	ISSPReturnObject zl_sscscx_sssj(HttpServletRequest request);

	// 分时段统计
	ISSPReturnObject zl_fsdtj_jcsj(HttpServletRequest request);

	// 分户 分时段统计
	ISSPReturnObject fh_fsdtj_jcsj(HttpServletRequest request);

	// 分项 分时段统计
	ISSPReturnObject fx_fsdtj_jcsj(HttpServletRequest request);

	// 选择电表下拉列表
	ISSPReturnObject zl_cscx_Select(HttpServletRequest request);

	// 电能参数查询
	ISSPReturnObject zl_cscx_Select_dncs(HttpServletRequest request);

	// 电能参数查询统计分析
	ISSPReturnObject zl_cscx_dncs_tjfx(HttpServletRequest request);

    ISSPReturnObject expExcel(HttpServletRequest request) throws Exception;

    //根据能源类型查询能源单位
	ISSPReturnObject getUnitByEnergyType(String energyType);
}
