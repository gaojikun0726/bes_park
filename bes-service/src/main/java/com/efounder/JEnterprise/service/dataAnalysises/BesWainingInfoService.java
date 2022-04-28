package com.efounder.JEnterprise.service.dataAnalysises;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.dataAnalysises.BesWainingInfo;
import com.github.pagehelper.PageInfo;

/**
 * 报警信息统计
 * @author liuzhen
 * time:2018/11/29
 */
public interface BesWainingInfoService {

    /**
     * 分页查询报警信息方法
     * @param pageNum
     * @param besWainingInfo
     * @return
     */
     PageInfo<BesWainingInfo> searchWainingInfo(Integer pageNum, BesWainingInfo besWainingInfo);

    /**
     *
     * @param besWainingInfo
     * @return
     * 获取统计数据
     */
     ISSPReturnObject searchWainingInfoCount(BesWainingInfo besWainingInfo);

    /**
     *
     * @param besWainingInfo
     * @return
     * 获取统计数据-折线图显示
     */
     ISSPReturnObject searchWainingData(BesWainingInfo besWainingInfo);


    /**
     *
     * @param besWainingInfo
     * @return
     * 获取报警环比统计数据-饼状图显示
     */
    ISSPReturnObject searchHbWainingPieData(BesWainingInfo besWainingInfo);


    /**
     *
     * @param besWainingInfo
     * @return
     * 获取报警环比统计数据-柱状图显示
     */
    ISSPReturnObject searchHbWainingBarData(BesWainingInfo besWainingInfo);

}
