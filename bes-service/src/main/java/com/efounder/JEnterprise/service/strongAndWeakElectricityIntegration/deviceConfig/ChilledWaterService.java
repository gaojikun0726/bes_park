
package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.deviceConfig;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.deviceConfig.ChilledWaterModel;
import com.github.pagehelper.PageInfo;

public interface ChilledWaterService {
    PageInfo<Object> getPaging(Integer pageSize, Integer pageNum, ChilledWaterModel chilledWaterModel);
    ISSPReturnObject query(ChilledWaterModel chilledWaterModel);
    ISSPReturnObject queryColdCooling(ChilledWaterModel chilledWaterModel);
    ISSPReturnObject creat(ChilledWaterModel chilledWaterModel);
    ISSPReturnObject update(ChilledWaterModel chilledWaterModel);
    ISSPReturnObject delete(ChilledWaterModel chilledWaterModel);
    ISSPReturnObject detailedinformation(String f_equipment_id);
     ISSPReturnObject electricMeterNnumber();


    String queryColdWarmWaterWithF_BPYXZT(String coldWarmWaterID);
}

