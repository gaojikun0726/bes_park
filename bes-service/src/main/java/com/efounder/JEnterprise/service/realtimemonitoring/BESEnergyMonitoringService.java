package com.efounder.JEnterprise.service.realtimemonitoring;

import com.core.common.ISSPReturnObject;

public interface BESEnergyMonitoringService {

	/**
	 * 通过支路ID查询电表数据
	 * @param f_zlbh
	 * @return
	 */
	ISSPReturnObject queryAmmeterData(String f_zlbh);
	/**
	 * 加载echart所需数据
	 * @param f_zlbh
	 * @return
	 */
	ISSPReturnObject loadAmmeterlinechart(String f_zlbh);

}
