package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.core.common.constant.CacheNameCode;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.OriginalData;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 电表原始数据缓存缓存
 * @author xiepufeng
 * @date 2020/6/20 10:21
 */
@Component
public class OriginalDataCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<String, Map<String, OriginalData>> originalDataCache;

    @Override
    public void run(String... args) throws Exception
    {
        loadCache();
    }

    /**
     * 1. 从数据库中查询出全部”电表原始数据缓存“数据。
     * 2. 获取一个缓存管理器”EhCacheManager“。
     * 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     * 4. 把“电表原始数据缓存”数据对象循环添加到缓存对象。
     */
    public void loadCache()
    {
        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        originalDataCache = ehCacheManager.getCache(CacheNameCode.ORIGINAL_DATA);

        originalDataCache.clear();
    }


    // 更新更新缓存中的一条数据
    public Boolean putOneOriginalDataCache(OriginalData originalData)
    {

        if (originalData == null)
        {
            return null;
        }

        String sysName = originalData.getfDbsysName();
        String fDnbh = originalData.getfDnbh();

        if(null == sysName || fDnbh == null)
        {
            return false;
        }

        Map<String, OriginalData> originalDataModelMap = originalDataCache.get(sysName);

        if(null == originalDataModelMap)
        {
            originalDataModelMap = new HashMap<>();

            originalDataCache.put(sysName, originalDataModelMap);
        }

        originalDataModelMap.put(fDnbh, originalData);

        return true;
    }

    public Map<String, OriginalData> getCachedElementBySysName(String sysName) throws CacheException
    {
        if (originalDataCache == null || originalDataCache.size() == 0 || sysName == null)
        {
            return null;
        }

        return originalDataCache.get(sysName);
    }

    public Cache<String, Map<String, OriginalData>> getOriginalDataCache() {
        return originalDataCache;
    }

    public void setOriginalDataCache(Cache<String, Map<String, OriginalData>> originalDataCache) {
        this.originalDataCache = originalDataCache;
    }
}
