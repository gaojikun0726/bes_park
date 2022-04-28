package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.core.common.constant.CacheNameCode;
import com.efounder.JEnterprise.mapper.basedatamanage.enegrycollectionmanage.BESCollMethodMapper;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESCollMethod;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 采集方案定义缓存
 * @author xiepufeng
 * @date 2020/6/20 10:21
 */
@Component
public class CollMethodCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<String, BESCollMethod> collMethodCache;

    @Resource
    private BESCollMethodMapper besCollMethodMapper;

    @Override
    public void run(String... args) throws Exception
    {
        loadCache();
    }

    /**
     * 1. 从数据库中查询出全部”采集方案定义“数据。
     * 2. 获取一个缓存管理器”EhCacheManager“。
     * 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     * 4. 把“采集方案定义”数据对象循环添加到缓存对象。
     */
    public void loadCache()
    {
        // 1. 从数据库中查询出全部”采集方案定义“数据。
        List<BESCollMethod> collMethodList = besCollMethodMapper.loadAll();

        if (collMethodList == null)
        {
            return;
        }

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        collMethodCache = ehCacheManager.getCache(CacheNameCode.COLL_METHOD);

        collMethodCache.clear();

        // 4. 把“采集方案定义”数据对象循环添加到缓存对象。
        for (BESCollMethod collMethod : collMethodList)
        {
            collMethodCache.put(collMethod.getfCjfabh(), collMethod);
        }
    }

    // 添加一条数据到缓存中
    public Boolean addOneCollMethodCache(BESCollMethod collMethod)
    {

        if(null == collMethod)
        {
            return false;
        }

        String key = collMethod.getfCjfabh();

        if(null == key)
        {
            return false;
        }

        Cache<String, BESCollMethod> collMethodCache =  this.getCollMethodCache();

        BESCollMethod collMethodModel = collMethodCache.get(key);

        if(null != collMethodModel)
        {
            return false;
        }

        collMethodCache.put(key, collMethod);

        return true;
    }

    // 删除缓存中的一条数据
    public Boolean deleteOneCollMethodCache(String key)
    {
        if (null == key)
        {
            return false;
        }

        Cache<String, BESCollMethod> collMethodCache =  this.getCollMethodCache();

        BESCollMethod collMethodModel = collMethodCache.get(key);

        if(null == collMethodModel)
        {
            return false;
        }

        collMethodCache.remove(key);

        return true;
    }

    // 更新更新缓存中的一条数据
    public Boolean updateOneCollMethodCache(BESCollMethod collMethod)
    {

        if(null == collMethod)
        {
            return false;
        }

        String key = collMethod.getfCjfabh();

        if(null == key)
        {
            return false;
        }

        Cache<String, BESCollMethod> collMethodCache =  this.getCollMethodCache();

        BESCollMethod collMethodModel = collMethodCache.get(key);

        if(null == collMethodModel)
        {
            return false;
        }

        collMethodCache.put(key, collMethod);

        return true;
    }

    public BESCollMethod getCachedElement(String key) throws CacheException
    {
        if (collMethodCache == null || collMethodCache.size() == 0 || key == null)
        {
            return null;
        }

        return collMethodCache.get(key);
    }


    public Cache<String, BESCollMethod> getCollMethodCache()
    {
        return collMethodCache;
    }

    public void setCollMethodCache(Cache<String, BESCollMethod> collMethodCache)
    {
        this.collMethodCache = collMethodCache;
    }

}
