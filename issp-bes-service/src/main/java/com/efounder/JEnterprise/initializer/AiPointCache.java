package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.core.common.constant.CacheNameCode;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BesAiPointMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesAiPoint;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesVirtualPoint;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * AI配置定义缓存
 * @author xiepufeng
 * @date 2020/6/20 10:21
 */
@Component
public class AiPointCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<String, BesAiPoint> aiPointCache;

    @Resource
    private BesAiPointMapper besAiPointMapper;

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
        // 1. 从数据库中查询出全部”AI配置定义“数据。
        List<BesAiPoint> aiPointList = besAiPointMapper.loadAll();

        if (aiPointList == null)
        {
            return;
        }

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        aiPointCache = ehCacheManager.getCache(CacheNameCode.AI_POINT_CACHE);

        aiPointCache.clear();

        // 4. 把“AI配置定义”数据对象循环添加到缓存对象。
        for (BesAiPoint aiPoint : aiPointList)
        {
            aiPointCache.put(aiPoint.getF_sys_name(), aiPoint);
        }
    }

    // 添加一条数据到缓存中
    public Boolean addOneAiPointCache(BesAiPoint aiPoint)
    {

        if(null == aiPoint)
        {
            return false;
        }

        String key = aiPoint.getF_sys_name();

        if(null == key)
        {
            return false;
        }

        Cache<String, BesAiPoint> aiPointCache =  this.getAiPointCache();

        BesAiPoint aiPointModel = aiPointCache.get(key);

        if(null != aiPointModel)
        {
            return false;
        }

        aiPointCache.put(key, aiPoint);

        return true;
    }

    // 删除缓存中的一条数据
    public Boolean deleteOneAiPointCache(String key)
    {
        if (null == key)
        {
            return false;
        }

        Cache<String, BesAiPoint> aiPointCache =  this.getAiPointCache();

        BesAiPoint aiPointModel = aiPointCache.get(key);

        if(null == aiPointModel)
        {
            return false;
        }

        aiPointCache.remove(key);

        return true;
    }

    // 更新更新缓存中的一条数据
    public Boolean updateOneAiPointCache(BesAiPoint aiPoint)
    {

        if(null == aiPoint)
        {
            return false;
        }

        String key = aiPoint.getF_sys_name();

        if(null == key)
        {
            return false;
        }

        Cache<String, BesAiPoint> aiPointCache =  this.getAiPointCache();

        BesAiPoint aiPointModel = aiPointCache.get(key);

        if(null == aiPointModel)
        {
            return false;
        }

        aiPointCache.put(key, aiPoint);

        return true;
    }

    public BesAiPoint getCachedElement(String key) throws CacheException
    {
        if (aiPointCache == null || aiPointCache.size() == 0 || key == null)
        {
            return null;
        }

        return aiPointCache.get(key);
    }

    public BesAiPoint getCachedElementBySbId(String sbId) throws CacheException
    {
        if (aiPointCache == null || aiPointCache.size() == 0 || sbId == null)
        {
            return null;
        }

        Collection<BesAiPoint> besAiPoints = aiPointCache.values();

        if (null == besAiPoints || besAiPoints.isEmpty())
        {
            return null;
        }

        for (BesAiPoint besAiPoint : besAiPoints) {
            if (sbId.equals(besAiPoint.getF_struct_id()))
            {
                return besAiPoint;
            }
        }

        return null;

    }

    public BesAiPoint getCachedElementBySysNameOld(String sysNameOld) throws CacheException
    {
        if (aiPointCache == null || aiPointCache.size() == 0 || sysNameOld == null)
        {
            return null;
        }

        Collection<BesAiPoint> besAiPoints = aiPointCache.values();

        if (null == besAiPoints || besAiPoints.isEmpty())
        {
            return null;
        }

        for (BesAiPoint besAiPoint : besAiPoints) {
            if (sysNameOld.equals(besAiPoint.getF_sys_name_old()))
            {
                return besAiPoint;
            }
        }

        return null;

    }


    public Cache<String, BesAiPoint> getAiPointCache()
    {
        return aiPointCache;
    }

    public void setAiPointCache(Cache<String, BesAiPoint> aiPointCache)
    {
        this.aiPointCache = aiPointCache;
    }

}
