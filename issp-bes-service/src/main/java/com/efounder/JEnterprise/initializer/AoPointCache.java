package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.core.common.constant.CacheNameCode;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BesAiPointMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BesAoPointMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesAiPoint;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesAoPoint;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * AO配置定义缓存
 * @author xiepufeng
 * @date 2020/6/20 10:21
 */
@Component
public class AoPointCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<String, BesAoPoint> aoPointCache;

    @Resource
    private BesAoPointMapper besAoPointMapper;

    @Override
    public void run(String... args) throws Exception
    {
        loadCache();
    }

    /**
     * 1. 从数据库中查询出全部”AI配置定义“数据。
     * 2. 获取一个缓存管理器”EhCacheManager“。
     * 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     * 4. 把“AI配置定义”数据对象循环添加到缓存对象。
     */
    public void loadCache()
    {
        // 1. 从数据库中查询出全部”Ao配置定义“数据。
        List<BesAoPoint> aoPointList = besAoPointMapper.loadAll();

        if (aoPointList == null)
        {
            return;
        }

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        aoPointCache = ehCacheManager.getCache(CacheNameCode.AO_POINT_CACHE);

        aoPointCache.clear();

        // 4. 把“Ao配置定义”数据对象循环添加到缓存对象。
        for (BesAoPoint aoPoint : aoPointList)
        {
            aoPointCache.put(aoPoint.getfSysName(), aoPoint);
        }
    }

    // 添加一条数据到缓存中
    public Boolean addOneAoPointCache(BesAoPoint aoPoint)
    {

        if(null == aoPoint)
        {
            return false;
        }

        String key = aoPoint.getfSysName();

        if(null == key)
        {
            return false;
        }

        Cache<String, BesAoPoint> aoPointCache =  this.getAoPointCache();

        BesAoPoint aoPointModel = aoPointCache.get(key);

        if(null != aoPointModel)
        {
            return false;
        }

        aoPointCache.put(key, aoPoint);

        return true;
    }

    // 删除缓存中的一条数据
    public Boolean deleteOneAoPointCache(String key)
    {
        if (null == key)
        {
            return false;
        }

        Cache<String, BesAoPoint> aoPointCache =  this.getAoPointCache();

        BesAoPoint aoPointModel = aoPointCache.get(key);

        if(null == aoPointModel)
        {
            return false;
        }

        aoPointCache.remove(key);

        return true;
    }

    // 更新更新缓存中的一条数据
    public Boolean updateOneAoPointCache(BesAoPoint aoPoint)
    {

        if(null == aoPoint)
        {
            return false;
        }

        String key = aoPoint.getfSysName();

        if(null == key)
        {
            return false;
        }

        Cache<String, BesAoPoint> aoPointCache =  this.getAoPointCache();

        BesAoPoint aoPointModel = aoPointCache.get(key);

        if(null == aoPointModel)
        {
            return false;
        }

        aoPointCache.put(key, aoPoint);

        return true;
    }

    public BesAoPoint getCachedElement(String key) throws CacheException
    {
        if (aoPointCache == null || aoPointCache.size() == 0 || key == null)
        {
            return null;
        }

        return aoPointCache.get(key);
    }

    public BesAoPoint getCachedElementBySbId(String sbId) throws CacheException
    {
        if (aoPointCache == null || aoPointCache.size() == 0 || sbId == null)
        {
            return null;
        }

        Collection<BesAoPoint> besAoPoints = aoPointCache.values();

        if (null == besAoPoints || besAoPoints.isEmpty())
        {
            return null;
        }

        for (BesAoPoint besAoPoint : besAoPoints) {
            if (sbId.equals(besAoPoint.getfStructId()))
            {
                return besAoPoint;
            }
        }

        return null;

    }

    public BesAoPoint getCachedElementBySysNameOld(String sysNameOld) throws CacheException
    {
        if (aoPointCache == null || aoPointCache.size() == 0 || sysNameOld == null)
        {
            return null;
        }

        Collection<BesAoPoint> besAoPoints = aoPointCache.values();

        if (null == besAoPoints || besAoPoints.isEmpty())
        {
            return null;
        }

        for (BesAoPoint besAoPoint : besAoPoints) {
            if (sysNameOld.equals(besAoPoint.getfSysNameOld()))
            {
                return besAoPoint;
            }
        }

        return null;

    }


    public Cache<String, BesAoPoint> getAoPointCache()
    {
        return aoPointCache;
    }

    public void setAoPointCache(Cache<String, BesAoPoint> aoPointCache)
    {
        this.aoPointCache = aoPointCache;
    }

}
