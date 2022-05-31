package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.core.common.constant.CacheNameCode;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BesplanConfigMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesAiPoint;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesSyncLog;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesTimeTask;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 定时同步设备树执行日志缓存
 * @author liuwg
 * @date 2022/05/30 10:41
 */
@Component
public class SyncLogCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<String, BesSyncLog> syncLogCache;

    @Resource
    private BesplanConfigMapper besplanConfigMapper;

    @Override
    public void run(String... args) throws Exception
    {
        loadCache();
    }

    /**
     * 1. 获取一个缓存管理器”EhCacheManager“。
     * 2. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     */
    public void loadCache()
    {

        // 1. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 2. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        syncLogCache = ehCacheManager.getCache(CacheNameCode.SYNC_LOG_CACHE);

        syncLogCache.clear();

    }

    // 添加一条数据到缓存中
    public Boolean addOneSyncLogCache(BesSyncLog besSyncLog)
    {

        if(null == besSyncLog)
        {
            return false;
        }

        String key = besSyncLog.getF_point_ip();

        if(null == key)
        {
            return false;
        }

        Cache<String, BesSyncLog> syncLogCache =  this.getSyncLogCache();

        syncLogCache.put(key, besSyncLog);

        return true;
    }

    // 删除缓存中的一条数据
    public Boolean deleteOneSyncLogCache(String key)
    {
        if (null == key)
        {
            return false;
        }

        Cache<String, BesSyncLog> syncLogCache =  this.getSyncLogCache();

        BesSyncLog besSyncLog = syncLogCache.get(key);

        if(null == besSyncLog)
        {
            return false;
        }

        syncLogCache.remove(key);

        return true;
    }

    // 更新更新缓存中的一条数据
    public Boolean updateOneSyncLogCache(BesSyncLog besSyncLog)
    {

        if(null == besSyncLog)
        {
            return false;
        }

        String key = besSyncLog.getF_point_ip();

        if(null == key)
        {
            return false;
        }

        Cache<String, BesSyncLog> syncLogCache =  this.getSyncLogCache();

        BesSyncLog syncLogModel = syncLogCache.get(key);

        if(null == syncLogModel)
        {
            return false;
        }

        syncLogCache.put(key, besSyncLog);

        return true;

    }

    public BesSyncLog getCachedElement(String key) throws CacheException
    {
        if (syncLogCache == null || syncLogCache.size() == 0 || key == null)
        {
            return null;
        }

        return syncLogCache.get(key);
    }




    public Cache<String, BesSyncLog> getSyncLogCache()
    {
        return syncLogCache;
    }

    public void setSyncLogCache(Cache<String, BesSyncLog> syncLogCache)
    {
        this.syncLogCache = syncLogCache;
    }


}
