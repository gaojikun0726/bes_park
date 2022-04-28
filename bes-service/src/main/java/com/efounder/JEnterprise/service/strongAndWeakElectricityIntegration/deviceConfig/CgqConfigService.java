package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.deviceConfig;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.deviceConfig.CgqConfigModel;
import com.github.pagehelper.PageInfo;

/**
 * @author wzx
 * @date 2020年8月10日17:53:59
 */
public interface CgqConfigService {

    ISSPReturnObject create(CgqConfigModel cgqConfigModel);

    PageInfo<Object> getPaging(Integer pageSize, Integer pageNum, CgqConfigModel cgqConfigModel);

    ISSPReturnObject query(CgqConfigModel cgqConfigModel);

    ISSPReturnObject queryByType(String cgqType,CgqConfigModel cgqConfigModel);

    ISSPReturnObject update(CgqConfigModel cgqConfigModel);

    ISSPReturnObject delete(CgqConfigModel cgqConfigModel);
}
