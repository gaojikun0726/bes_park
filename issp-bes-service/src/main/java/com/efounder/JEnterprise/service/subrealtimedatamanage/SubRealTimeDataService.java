package com.efounder.JEnterprise.service.subrealtimedatamanage;

import com.core.common.ISSPReturnObject;

/**
 * 客户端实时数据订阅
 */
public interface SubRealTimeDataService {


    ISSPReturnObject subscribe(String event);

    ISSPReturnObject unsubscribe(String event);
}
