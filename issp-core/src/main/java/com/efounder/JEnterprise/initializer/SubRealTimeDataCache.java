package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.efounder.JEnterprise.constant.CacheNameCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 客户端实时数据发布定义缓存
 * @author xiepufeng
 * @date 2020/6/20 10:21
 */
@Component
public class SubRealTimeDataCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<String, Set<String>> subRealTimeDataCache;

    @Override
    public void run(String... args) throws Exception
    {
        initCache();
    }

    /**
     * 1. 从数据库中查询出全部”客户端实时数据发布定义“数据。
     * 2. 获取一个缓存管理器”EhCacheManager“。
     * 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     * 4. 把“客户端实时数据发布定义”数据对象循环添加到缓存对象。
     */
    public void initCache()
    {

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        subRealTimeDataCache = ehCacheManager.getCache(CacheNameCode.SUB_REALTIME_DATA);
    }

    // 添加一个订阅到缓存
    public Boolean subscribeCache(String event)
    {
        if (event == null)
        {
            return false;
        }

        Set<String> subRealTimeDataSet = subRealTimeDataCache.get(event);

        String sessionId = SecurityUtils.getSubject().getSession().getId().toString();

        if (subRealTimeDataSet == null)
        {
            subRealTimeDataSet = new HashSet<>();

            subRealTimeDataSet.add(sessionId);

            subRealTimeDataCache.put(event, subRealTimeDataSet);

            return true;
        }

        if (subRealTimeDataSet.contains(sessionId))
        {
            return true;
        }

        subRealTimeDataSet.add(sessionId);

        return true;

    }

    // 取消一个订阅到缓存
    public Boolean unsubscribeCache(String event)
    {
        if (event == null)
        {
            return false;
        }

        String sessionId = SecurityUtils.getSubject().getSession().getId().toString();

        Set<String> subRealTimeDataSet = subRealTimeDataCache.get(event);

        if (subRealTimeDataSet == null || subRealTimeDataSet.isEmpty())
        {
            subRealTimeDataCache.remove(event);
            return true;
        }

        if (!subRealTimeDataSet.contains(sessionId))
        {
            return true;
        }

        if (subRealTimeDataSet.remove(sessionId))
        {

            if (subRealTimeDataSet.isEmpty())
            {
                subRealTimeDataCache.remove(event);
            }

            return true;
        }

        return false;
    }

    // 根据 sessionId 取消订阅到缓存
    public void unsubscribeCacheBySessionId(String sessionId)
    {
        if (sessionId == null)
        {
            return;
        }

        Collection<String> events = subRealTimeDataCache.keys();

        for (String event : events) {

            Set<String> subRealTimeDataSet = subRealTimeDataCache.get(event);

            if (subRealTimeDataSet == null || subRealTimeDataSet.isEmpty())
            {
                subRealTimeDataCache.remove(event);
                continue;
            }

            if (!subRealTimeDataSet.contains(sessionId))
            {
                continue;
            }

            if (subRealTimeDataSet.remove(sessionId))
            {
                if (subRealTimeDataSet.isEmpty())
                {
                    subRealTimeDataCache.remove(event);
                }
            }
            
        }

    }

    public Set<String> getSubRealTimeDataCacheByEvent(String event)
    {
        if (event == null)
        {
            return null;
        }

        return subRealTimeDataCache.get(event);

    }

    public Cache<String, Set<String>> getSubRealTimeDataCache() {
        return subRealTimeDataCache;
    }

    public void setSubRealTimeDataCache(Cache<String, Set<String>> subRealTimeDataCache) {
        this.subRealTimeDataCache = subRealTimeDataCache;
    }
}
