package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.core.common.constant.CacheNameCode;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BESAmmeterMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESAmmeter;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 电表配置定义缓存
 * @author xiepufeng
 * @date 2020/6/20 10:21
 */
@Component
public class AmmeterCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<String, BESAmmeter> ammeterCache;

    @Resource
    private BESAmmeterMapper besAmmeterMapper;

    @Override
    public void run(String... args) throws Exception
    {
        loadCache();
    }

    /**
     * 1. 从数据库中查询出全部”电表配置定义“数据。
     * 2. 获取一个缓存管理器”EhCacheManager“。
     * 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     * 4. 把“电表配置定义”数据对象循环添加到缓存对象。
     */
    public void loadCache()
    {
        // 1. 从数据库中查询出全部”电表配置定义“数据。
        List<BESAmmeter> ammeterList = besAmmeterMapper.loadAll();

        if (ammeterList == null)
        {
            return;
        }

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        ammeterCache = ehCacheManager.getCache(CacheNameCode.AMMETER_CACHE);

        if (null == ammeterCache)
        {
            return;
        }

        ammeterCache.clear();

        // 4. 把“电表配置定义”数据对象循环添加到缓存对象。
        for (BESAmmeter ammeter : ammeterList)
        {
            ammeterCache.put(ammeter.getfSysName(), ammeter);
        }
    }

    // 添加一条数据到缓存中
    public Boolean addOneAmmeterCache(BESAmmeter ammeter)
    {

        if(null == ammeter)
        {
            return false;
        }

        String key = ammeter.getfSysName();

        if(null == key)
        {
            return false;
        }

        Cache<String, BESAmmeter> ammeterCache =  this.getAmmeterCache();

        BESAmmeter ammeterModel = ammeterCache.get(key);

        if(null != ammeterModel)
        {
            return false;
        }

        ammeterCache.put(key, ammeter);

        return true;
    }

    // 删除缓存中的一条数据
    public Boolean deleteOneAmmeterCache(String key)
    {
        if (null == key)
        {
            return false;
        }

        Cache<String, BESAmmeter> ammeterCache =  this.getAmmeterCache();

        BESAmmeter ammeterModel = ammeterCache.get(key);

        if(null == ammeterModel)
        {
            return false;
        }

        ammeterCache.remove(key);

        return true;
    }

    // 更新更新缓存中的一条数据
    public Boolean updateOneAmmeterCache(BESAmmeter ammeter)
    {

        if(null == ammeter)
        {
            return false;
        }

        String key = ammeter.getfSysName();

        if(null == key)
        {
            return false;
        }

        Cache<String, BESAmmeter> ammeterCache =  this.getAmmeterCache();

        BESAmmeter ammeterModel = ammeterCache.get(key);

        if(null == ammeterModel)
        {
            return false;
        }

        ammeterCache.put(key, ammeter);

        return true;
    }

    public BESAmmeter getCachedElement(String key) throws CacheException
    {
        if (ammeterCache == null || ammeterCache.size() == 0 || key == null)
        {
            return null;
        }

        return ammeterCache.get(key);
    }

    public BESAmmeter getCachedElementBySbid(String sbid)
    {
        if (ammeterCache == null || ammeterCache.size() == 0 || sbid == null)
        {
            return null;
        }

        Collection<BESAmmeter> besAmmeters = ammeterCache.values();

        for (BESAmmeter besAmmeter : besAmmeters) {
            if (sbid.equals(besAmmeter.getfSbid()))
            {
                return besAmmeter;
            }
        }

        return null;
    }

    public Cache<String, BESAmmeter> getAmmeterCache()
    {
        return ammeterCache;
    }

    public void setAmmeterCache(Cache<String, BESAmmeter> ammeterCache)
    {
        this.ammeterCache = ammeterCache;
    }

}
