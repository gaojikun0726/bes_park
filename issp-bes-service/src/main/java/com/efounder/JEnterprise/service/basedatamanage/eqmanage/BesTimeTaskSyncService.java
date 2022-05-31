package com.efounder.JEnterprise.service.basedatamanage.eqmanage;

import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesSyncLog;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesTimeTaskSync;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceTypeModel;
import com.github.pagehelper.PageInfo;

import java.text.ParseException;
import java.util.Map;

/**
* 定时同步设备树
**/

public interface BesTimeTaskSyncService {

    /**
     * 获取分页
     * @return
     */
    ISSPReturnObject queryPage(String keywords);

    ISSPReturnObject insertTimeTaskSync(JSONObject object);

    ISSPReturnObject insertTimeTaskSyncJobId(JSONObject object);

    ISSPReturnObject querySbList(JSONObject object);

    ISSPReturnObject selectTimeTaskSyncInfo(String syncId);

    ISSPReturnObject deleteTimeTaskSync(JSONObject object);

    ISSPReturnObject updateTimeTaskSync(JSONObject object) throws ParseException;

    PageInfo<BesSyncLog> getSyncLogPage(Integer pageSize, Integer pageNum,BesSyncLog besSyncLog);


}
