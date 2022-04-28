package com.efounder.JEnterprise.service.subrealtimedatamanage.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.initializer.SbPzStructCache;
import com.efounder.JEnterprise.initializer.SubRealTimeDataCache;
import com.efounder.JEnterprise.service.subrealtimedatamanage.SubRealTimeDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 客户端实时数据订阅
 */
@Service
public class SubRealTimeDataServiceImpl implements SubRealTimeDataService {

    @Resource
    private SubRealTimeDataCache subRealTimeDataCache;

    @Resource
    private SbPzStructCache sbPzStructCache;

    /**
     * 客户端订阅消息
     * @param event
     * @return
     */
    @Override
    public ISSPReturnObject subscribe(String event) {

        ISSPReturnObject result = new ISSPReturnObject();

        if (event == null) {
            result.setStatus("0");
            result.setMsg("参数错误！");
            return result;
        }

        if (sbPzStructCache.getCachedElement(event) == null) {
            result.setStatus("0");
            result.setMsg("无效订阅事件！");
            return result;
        }

        if (!subRealTimeDataCache.subscribeCache(event)) {
            result.setStatus("0");
            result.setMsg("订阅失败！");
            return result;
        }

        result.setStatus("1");
        result.setMsg("订阅成功！");
        return result;
    }

    /**
     * 客户端取消订阅
     * @param event
     * @return
     */
    @Override
    public ISSPReturnObject unsubscribe(String event) {

        ISSPReturnObject result = new ISSPReturnObject();

        if (event == null) {
            result.setStatus("0");
            result.setMsg("参数错误！");
            return result;
        }

        if (sbPzStructCache.getCachedElement(event) == null) {
            result.setStatus("0");
            result.setMsg("无效订阅事件！");
            return result;
        }

        if (!subRealTimeDataCache.unsubscribeCache(event)) {
            result.setStatus("0");
            result.setMsg("取消订阅失败！");
            return result;
        }

        result.setStatus("1");
        result.setMsg("取消订阅成功！");
        return result;
    }
}
