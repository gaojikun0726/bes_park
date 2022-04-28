package com.efounder.JEnterprise.service.app;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.app.AirconditioningUnitConfigModel;
import com.github.pagehelper.PageInfo;

/**
 * @author wzx
 * @date  2020-5-12 14:14:45
 */
public interface AirconditioningUnitConfigService {

    ISSPReturnObject create(AirconditioningUnitConfigModel airconditioningUnitConfigModel);

    PageInfo<Object> getPaging(Integer pageSize, Integer pageNum, AirconditioningUnitConfigModel airconditioningUnitConfigModel);

    ISSPReturnObject query(AirconditioningUnitConfigModel airconditioningUnitConfigModel);

    ISSPReturnObject update(AirconditioningUnitConfigModel airconditioningUnitConfigModel);

    ISSPReturnObject delete(AirconditioningUnitConfigModel airconditioningUnitConfigModel);
}
