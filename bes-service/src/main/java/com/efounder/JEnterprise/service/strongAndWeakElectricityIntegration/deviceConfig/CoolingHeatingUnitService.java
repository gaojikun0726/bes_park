package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.deviceConfig;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.deviceConfig.CoolingHeatingUnitModel;
import com.github.pagehelper.PageInfo;

/**
 * @author xiepufeng
 * @date 2019/12/16 14:31
 */
public interface CoolingHeatingUnitService {

    ISSPReturnObject create(CoolingHeatingUnitModel coolingHeatingUnitModel);

    PageInfo<Object> getPaging(Integer pageSize, Integer pageNum, CoolingHeatingUnitModel coolingHeatingUnitModel);

    ISSPReturnObject query(CoolingHeatingUnitModel coolingHeatingUnitModel);

    ISSPReturnObject update(CoolingHeatingUnitModel coolingHeatingUnitModel);

    ISSPReturnObject coolingHeatingUnit(CoolingHeatingUnitModel coolingHeatingUnitModel);

    ISSPReturnObject delete(CoolingHeatingUnitModel coolingHeatingUnitModel);


}
