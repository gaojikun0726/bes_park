package com.efounder.JEnterprise.service.dataAnalysises;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.dataAnalysises.BesQstjfxData;

/**
* @author  杨超
* @version 创建时间：2018年10月29日 下午3:35:52
* @parameter 
* @version 1.0
*/
public interface BesZlthbfxService {

	// 同环比拼装table根据查询条件
    ISSPReturnObject thb_pin_table(BesQstjfxData dto);

    ISSPReturnObject thbPinTable(BesQstjfxData dto);

    ISSPReturnObject thbPinTabledw(BesQstjfxData dto);


    /**
     *
     * @Description: 加载能源统计分析--支路用能--用能统计分析
     *
     * @auther: wanghongjie
     * @date: 11:50 2021/5/11
     * @param: [besQstjfxData]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject statisAnalyOfEnergyConsumption(BesQstjfxData besQstjfxData);

    ISSPReturnObject statisAnalyOfEnergyConsumptionDep(BesQstjfxData besQstjfxData);

    /**
     *
     * @Description: 加载能源统计分析--支路用能--同比环比分析
     *
     * @auther: wanghongjie
     * @date: 16:21 2021/5/12
     * @param: [besQstjfxData]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject yoyAndMoMAnalysis(BesQstjfxData besQstjfxData);
}
