package com.efounder.JEnterprise.service.dataAnalysises;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.dataAnalysises.BESSubitemData;
import com.github.pagehelper.PageInfo;

import java.util.List;
/**
 * 趋势分析接口
 * @author LvSihan
 *
 */
public interface BESSubitemDataService {

	ISSPReturnObject getSubitemData(BESSubitemData besSubitemData);

	public PageInfo<BESSubitemData> getSubitemDataTab(Integer pageNum, String keywords);

	List<BESSubitemData> getSubitemDataList(BESSubitemData besSubitemData);
	/**
	 *
	 * @param besSubitemData
	 * @return
	 * 获取分项用能趋势统计数据
	 */
	ISSPReturnObject getQstjSubitemData(BESSubitemData besSubitemData);
	/**
	 *
	 * @param besSubitemData
	 * @return
	 * 获取分项用能同比环比数据
	 */
    ISSPReturnObject getTbhbSubitemData(BESSubitemData besSubitemData);

    ISSPReturnObject getSubentryData(BESSubitemData besSubitemData);

}
