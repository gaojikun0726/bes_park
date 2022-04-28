package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.core.common.constant.CacheNameCode;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BesCollectorMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesCollector;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 采集器配置定义缓存
 * @author xiepufeng
 * @date 2020/6/20 10:21
 */
@Component
public class CollectorCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<String, BesCollector> collectorCache;

    @Resource
    private BesCollectorMapper besCollectorMapper;

    @Override
    public void run(String... args) throws Exception
    {
        loadCache();
    }

    /**
     * 1. 从数据库中查询出全部”采集器配置定义“数据。
     * 2. 获取一个缓存管理器”EhCacheManager“。
     * 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     * 4. 把“采集器配置定义”数据对象循环添加到缓存对象。
     */
    public void loadCache()
    {
        // 1. 从数据库中查询出全部”采集器配置定义“数据。
        List<BesCollector> collectorList = besCollectorMapper.loadAll();

        if (collectorList == null)
        {
            return;
        }

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        collectorCache = ehCacheManager.getCache(CacheNameCode.COLLECTOR_CACHE);

        if (null == collectorCache)
        {
            return;
        }

        collectorCache.clear();

        // 4. 把“采集器配置定义”数据对象循环添加到缓存对象。
        for (BesCollector collector : collectorList)
        {
            collector.setfOnlineState("0"); // 在线状态设置为离线
            collectorCache.put(collector.getfSysName(), collector);
        }
    }

    // 添加一条数据到缓存中
    public Boolean addOneCollectorCache(BesCollector collector)
    {

        if(null == collector)
        {
            return false;
        }

        String key = collector.getfSysName();

        if(null == key)
        {
            return false;
        }

        Cache<String, BesCollector> collectorCache =  this.getCollectorCache();

        BesCollector collectorModel = collectorCache.get(key);

        if(null != collectorModel)
        {
            return false;
        }

        collectorCache.put(key, collector);

        return true;
    }

    // 删除缓存中的一条数据
    public Boolean deleteOneCollectorCache(String key)
    {
        if (null == key)
        {
            return false;
        }

        Cache<String, BesCollector> collectorCache =  this.getCollectorCache();

        BesCollector collectorModel = collectorCache.get(key);

        if(null == collectorModel)
        {
            return false;
        }

        collectorCache.remove(key);

        return true;
    }

    // 更新更新缓存中的一条数据
    public Boolean updateOneCollectorCache(BesCollector collector)
    {

        if(null == collector)
        {
            return false;
        }

        String key = collector.getfSysName();

        if(null == key)
        {
            return false;
        }

        Cache<String, BesCollector> collectorCache =  this.getCollectorCache();

        BesCollector collectorModel = collectorCache.get(key);

        if(null == collectorModel)
        {
            return false;
        }

        collectorCache.put(key, collector);

        return true;
    }

    public BesCollector getCachedElement(String key) throws CacheException
    {
        if (collectorCache == null || collectorCache.size() == 0 || key == null)
        {
            return null;
        }

        return collectorCache.get(key);
    }

    public Cache<String, BesCollector> getCollectorCache()
    {
        return collectorCache;
    }

    public void setCollectorCache(Cache<String, BesCollector> collectorCache)
    {
        this.collectorCache = collectorCache;
    }

    public BesCollector getCollectorByChannelId(String ip) {

        if (collectorCache == null || collectorCache.size() == 0 || ip == null)
        {
            return null;
        }

        Collection<BesCollector> besCollectors = collectorCache.values();

        if (besCollectors == null || besCollectors.isEmpty())
        {
            return null;
        }

        for (BesCollector besCollector : besCollectors) {

            String channelId = besCollector.getfChannelId();

            if (ip.equals(channelId))
            {
                return besCollector;
            }

        }

        return null;

    }
}
