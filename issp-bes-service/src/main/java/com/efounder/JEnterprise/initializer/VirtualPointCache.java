package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.core.common.constant.CacheNameCode;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BesVirtualPointMapper;
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
 * 虚点配置定义缓存
 * @author xiepufeng
 * @date 2020/6/20 10:21
 */
@Component
public class VirtualPointCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<String, BesVirtualPoint> virtualPointCache;

    @Resource
    private BesVirtualPointMapper besVirtualPointMapper;

    @Override
    public void run(String... args) throws Exception
    {
        loadCache();
    }

    /**
     * 1. 从数据库中查询出全部”虚点配置定义“数据。
     * 2. 获取一个缓存管理器”EhCacheManager“。
     * 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     * 4. 把“虚点配置定义”数据对象循环添加到缓存对象。
     */
    public void loadCache()
    {
        // 1. 从数据库中查询出全部”虚点配置定义“数据。
        List<BesVirtualPoint> virtualPointList = besVirtualPointMapper.loadAll();

        if (virtualPointList == null)
        {
            return;
        }

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        virtualPointCache = ehCacheManager.getCache(CacheNameCode.VIRTUAL_POINT_CACHE);

        virtualPointCache.clear();

        // 4. 把“虚点配置定义”数据对象循环添加到缓存对象。
        for (BesVirtualPoint virtualPoint : virtualPointList)
        {
            virtualPointCache.put(virtualPoint.getF_sys_name(), virtualPoint);
        }
    }

    // 添加一条数据到缓存中
    public Boolean addOneVirtualPointCache(BesVirtualPoint virtualPoint)
    {

        if(null == virtualPoint)
        {
            return false;
        }

        String key = virtualPoint.getF_sys_name();

        if(null == key)
        {
            return false;
        }

        Cache<String, BesVirtualPoint> virtualPointCache =  this.getVirtualPointCache();

        BesVirtualPoint virtualPointModel = virtualPointCache.get(key);

        if(null != virtualPointModel)
        {
            return false;
        }

        virtualPointCache.put(key, virtualPoint);

        return true;
    }

    // 删除缓存中的一条数据
    public Boolean deleteOneVirtualPointCache(String key)
    {
        if (null == key)
        {
            return false;
        }

        Cache<String, BesVirtualPoint> virtualPointCache =  this.getVirtualPointCache();

        BesVirtualPoint virtualPointModel = virtualPointCache.get(key);

        if(null == virtualPointModel)
        {
            return false;
        }

        virtualPointCache.remove(key);

        return true;
    }

    // 更新更新缓存中的一条数据
    public Boolean updateOneVirtualPointCache(BesVirtualPoint virtualPoint)
    {

        if(null == virtualPoint)
        {
            return false;
        }

        String key = virtualPoint.getF_sys_name();

        if(null == key)
        {
            return false;
        }

        Cache<String, BesVirtualPoint> virtualPointCache =  this.getVirtualPointCache();

        BesVirtualPoint virtualPointModel = virtualPointCache.get(key);

        if(null == virtualPointModel)
        {
            return false;
        }

        virtualPointCache.put(key, virtualPoint);

        return true;
    }

    public BesVirtualPoint getCachedElement(String key) throws CacheException
    {
        if (virtualPointCache == null || virtualPointCache.size() == 0 || key == null)
        {
            return null;
        }

        return virtualPointCache.get(key);
    }

    public BesVirtualPoint getCachedElementBySbId(String sbId) throws CacheException
    {
        if (virtualPointCache == null || virtualPointCache.size() == 0 || sbId == null)
        {
            return null;
        }

        Collection<BesVirtualPoint> besVirtualPoints = virtualPointCache.values();

        if (null == besVirtualPoints || besVirtualPoints.isEmpty())
        {
            return null;
        }

        for (BesVirtualPoint besVirtualPoint : besVirtualPoints) {
            if (sbId.equals(besVirtualPoint.getF_sbid()))
            {
                return besVirtualPoint;
            }
        }

        return null;

    }


    public Cache<String, BesVirtualPoint> getVirtualPointCache()
    {
        return virtualPointCache;
    }

    public void setVirtualPointCache(Cache<String, BesVirtualPoint> virtualPointCache)
    {
        this.virtualPointCache = virtualPointCache;
    }

}
