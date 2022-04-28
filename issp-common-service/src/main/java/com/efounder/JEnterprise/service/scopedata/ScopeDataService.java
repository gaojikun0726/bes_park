package com.efounder.JEnterprise.service.scopedata;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.scopedata.RoleGroupModel;
import com.efounder.JEnterprise.model.scopedata.ScopeDataModel;

import java.util.List;

/**
 * @author xiepufeng
 * @date 2021/1/5 14:37
 */
public interface ScopeDataService
{
    ISSPReturnObject query(ScopeDataModel request);

    ISSPReturnObject update(ScopeDataModel request);

    List<RoleGroupModel> findAll();
}
