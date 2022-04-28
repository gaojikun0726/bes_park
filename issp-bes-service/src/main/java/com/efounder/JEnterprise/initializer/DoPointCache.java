package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.core.common.constant.CacheNameCode;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BesDiPointMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BesDoPointMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesAiPoint;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesDiPoint;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesDoPoint;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * DO配置定义缓存
 * @author xiepufeng
 * @date 2020/6/20 10:21
 */
@Component
public class DoPointCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<String, BesDoPoint> doPointCache;

    @Resource
    private BesDoPointMapper besDoPointMapper;

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
        List<BesDoPoint> doPointList = besDoPointMapper.loadAll();

        if (doPointList == null)
        {
            return;
        }

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        doPointCache = ehCacheManager.getCache(CacheNameCode.DO_POINT_CACHE);

        doPointCache.clear();

        // 4. 把“Ao配置定义”数据对象循环添加到缓存对象。
        for (BesDoPoint doPoint : doPointList)
        {
            doPointCache.put(doPoint.getfSysName(), doPoint);
        }
    }

    // 添加一条数据到缓存中
    public Boolean addOneDoPointCache(BesDoPoint doPoint)
    {

        if(null == doPoint)
        {
            return false;
        }

        String key = doPoint.getfSysName();

        if(null == key)
        {
            return false;
        }

        Cache<String, BesDoPoint> doPointCache =  this.getDoPointCache();

        BesDoPoint doPointModel = doPointCache.get(key);

        if(null != doPointModel)
        {
            return false;
        }

        doPointCache.put(key, doPoint);

        return true;
    }

    // 删除缓存中的一条数据
    public Boolean deleteOneDoPointCache(String key)
    {
        if (null == key)
        {
            return false;
        }

        Cache<String, BesDoPoint> doPointCache =  this.getDoPointCache();

        BesDoPoint doPointModel = doPointCache.get(key);

        if(null == doPointModel)
        {
            return false;
        }

        doPointCache.remove(key);

        return true;
    }

    // 更新更新缓存中的一条数据
    public Boolean updateOneDoPointCache(BesDoPoint doPoint)
    {

        if(null == doPoint)
        {
            return false;
        }

        String key = doPoint.getfSysName();

        if(null == key)
        {
            return false;
        }

        Cache<String, BesDoPoint> doPointCache =  this.getDoPointCache();

        BesDoPoint doPointModel = doPointCache.get(key);

        if(null == doPointModel)
        {
            return false;
        }

        doPointCache.put(key, doPoint);

        return true;
    }

    public BesDoPoint getCachedElement(String key) throws CacheException
    {
        if (doPointCache == null || doPointCache.size() == 0 || key == null)
        {
            return null;
        }

        return doPointCache.get(key);
    }

    public BesDoPoint getCachedElementBySbId(String sbId) throws CacheException
    {
        if (doPointCache == null || doPointCache.size() == 0 || sbId == null)
        {
            return null;
        }

        Collection<BesDoPoint> besDoPoints = doPointCache.values();

        if (null == besDoPoints || besDoPoints.isEmpty())
        {
            return null;
        }

        for (BesDoPoint besDoPoint : besDoPoints) {
            if (sbId.equals(besDoPoint.getfStructId()))
            {
                return besDoPoint;
            }
        }

        return null;

    }
    public BesDoPoint getCachedElementBySysNameOld(String sysNameOld) throws CacheException
    {
        if (doPointCache == null || doPointCache.size() == 0 || sysNameOld == null)
        {
            return null;
        }

        Collection<BesDoPoint> besDoPoints = doPointCache.values();

        if (null == besDoPoints || besDoPoints.isEmpty())
        {
            return null;
        }

        for (BesDoPoint besDoPoint : besDoPoints) {
            if (sysNameOld.equals(besDoPoint.getfSysNameOld()))
            {
                return besDoPoint;
            }
        }

        return null;

    }



    public Cache<String, BesDoPoint> getDoPointCache()
    {
        return doPointCache;
    }

    public void setDoPointCache(Cache<String, BesDoPoint> doPointCache)
    {
        this.doPointCache = doPointCache;
    }

}
