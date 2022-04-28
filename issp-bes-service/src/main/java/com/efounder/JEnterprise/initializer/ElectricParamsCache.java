package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.core.common.constant.CacheNameCode;
import com.efounder.JEnterprise.mapper.basedatamanage.enegrycollectionmanage.BESElectricParamsMapper;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESElectricParams;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 采集参数定义缓存
 * @author xiepufeng
 * @date 2020/6/20 10:21
 */
@Component
public class ElectricParamsCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<String, BESElectricParams> electricParamsCache;

    @Resource
    private BESElectricParamsMapper besElectricParamsMapper;

    @Override
    public void run(String... args) throws Exception
    {
        loadCache();
    }

    /**
     * 1. 从数据库中查询出全部”采集参数定义“数据。
     * 2. 获取一个缓存管理器”EhCacheManager“。
     * 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     * 4. 把“采集参数定义”数据对象循环添加到缓存对象。
     */
    public void loadCache()
    {
        // 1. 从数据库中查询出全部”采集参数定义“数据。
        List<BESElectricParams> electricParamsList = besElectricParamsMapper.loadAll();

        if (electricParamsList == null)
        {
            return;
        }

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        electricParamsCache = ehCacheManager.getCache(CacheNameCode.ELECTRIC_PARAMS);

        electricParamsCache.clear();

        // 4. 把“采集参数定义”数据对象循环添加到缓存对象。
        for (BESElectricParams electricParams : electricParamsList)
        {
            electricParamsCache.put(electricParams.getfDnbh(), electricParams);
        }
    }

    // 添加一条数据到缓存中
    public Boolean addOneElectricParamsCache(BESElectricParams electricParams)
    {

        if(null == electricParams)
        {
            return false;
        }

        String key = electricParams.getfDnbh();

        if(null == key)
        {
            return false;
        }

        Cache<String, BESElectricParams> electricParamsCache =  this.getElectricParamsCache();

        BESElectricParams electricParamsModel = electricParamsCache.get(key);

        if(null != electricParamsModel)
        {
            return false;
        }

        electricParamsCache.put(key, electricParams);

        return true;
    }

    // 删除缓存中的一条数据
    public Boolean deleteOneElectricParamsCache(String key)
    {
        if (null == key)
        {
            return false;
        }

        Cache<String, BESElectricParams> electricParamsCache =  this.getElectricParamsCache();

        BESElectricParams electricParamsModel = electricParamsCache.get(key);

        if(null == electricParamsModel)
        {
            return false;
        }

        electricParamsCache.remove(key);

        return true;
    }

    // 更新更新缓存中的一条数据
    public Boolean updateOneElectricParamsCache(BESElectricParams electricParams)
    {

        if(null == electricParams)
        {
            return false;
        }

        String key = electricParams.getfDnbh();

        if(null == key)
        {
            return false;
        }

        Cache<String, BESElectricParams> electricParamsCache =  this.getElectricParamsCache();

        BESElectricParams electricParamsModel = electricParamsCache.get(key);

        if(null == electricParamsModel)
        {
            return false;
        }

        electricParamsCache.put(key, electricParams);

        return true;
    }

    public BESElectricParams getCachedElement(String key) throws CacheException
    {
        if (electricParamsCache == null || electricParamsCache.size() == 0 || key == null)
        {
            return null;
        }

        return electricParamsCache.get(key);
    }


    public Cache<String, BESElectricParams> getElectricParamsCache()
    {
        return electricParamsCache;
    }

    public void setElectricParamsCache(Cache<String, BESElectricParams> electricParamsCache)
    {
        this.electricParamsCache = electricParamsCache;
    }

}
