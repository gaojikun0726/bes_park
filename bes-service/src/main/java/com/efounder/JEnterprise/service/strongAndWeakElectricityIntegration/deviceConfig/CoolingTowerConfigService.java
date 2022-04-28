package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.deviceConfig;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.deviceConfig.CoolingTowerConfigModel;
import com.github.pagehelper.PageInfo;

/**
 * @author xiepufeng
 * @date 2019/12/16 14:31
 */
public interface CoolingTowerConfigService {

    ISSPReturnObject create(CoolingTowerConfigModel coolingTowerConfigModel);

    PageInfo<Object> getPaging(Integer pageSize, Integer pageNum, CoolingTowerConfigModel coolingTowerConfigModel);

    ISSPReturnObject query(CoolingTowerConfigModel coolingTowerConfigModel);

    ISSPReturnObject update(CoolingTowerConfigModel coolingTowerConfigModel);

    ISSPReturnObject delete(CoolingTowerConfigModel coolingTowerConfigModel);

    ISSPReturnObject queryTownCooling(CoolingTowerConfigModel coolingTowerConfigModel);
}
