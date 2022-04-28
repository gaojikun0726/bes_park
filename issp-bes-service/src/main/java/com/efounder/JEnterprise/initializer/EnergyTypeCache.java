package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.core.common.constant.CacheNameCode;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESEnergyType;
import com.efounder.JEnterprise.service.basedatamanage.enegrycollectionmanage.BESEnergyTypeService;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 能源类型定义缓存
 * @author xiepufeng
 * @date 2020/6/20 10:21
 */
@Component
public class EnergyTypeCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<String, BESEnergyType> energyTypeCache;

    @Resource
    private BESEnergyTypeService besEnergyTypeService;

    @Override
    public void run(String... args) throws Exception
    {
        loadCache();
    }

    /**
     * 1. 从数据库中查询出全部”能源类型定义“数据。
     * 2. 获取一个缓存管理器”EhCacheManager“。
     * 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     * 4. 把“能源类型定义”数据对象循环添加到缓存对象。
     */
    public void loadCache()
    {
        // 1. 从数据库中查询出全部”能源类型定义“数据。
        List<BESEnergyType> energyTypeList = besEnergyTypeService.findAll();

        if (energyTypeList == null)
        {
            return;
        }

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        energyTypeCache = ehCacheManager.getCache(CacheNameCode.ENERGY_TYPE_CACHE);

        if (null == energyTypeCache)
        {
            return;
        }

        energyTypeCache.clear();

        // 4. 把“能源类型定义”数据对象循环添加到缓存对象。
        for (BESEnergyType energyType : energyTypeList)
        {
            energyTypeCache.put(energyType.getfNybh(), energyType);
        }
    }

    // 添加一条数据到缓存中
    public Boolean addOneEnergyTypeCache(BESEnergyType energyType)
    {

        if(null == energyType)
        {
            return false;
        }

        String key = energyType.getfNybh();

        if(null == key)
        {
            return false;
        }

        Cache<String, BESEnergyType> energyTypeCache =  this.getEnergyTypeCache();

        BESEnergyType energyTypeModel = energyTypeCache.get(key);

        if(null != energyTypeModel)
        {
            return false;
        }

        energyTypeCache.put(key, energyType);

        return true;
    }

    // 删除缓存中的一条数据
    public Boolean deleteOneEnergyTypeCache(String key)
    {
        if (null == key)
        {
            return false;
        }

        Cache<String, BESEnergyType> energyTypeCache =  this.getEnergyTypeCache();

        BESEnergyType energyTypeModel = energyTypeCache.get(key);

        if(null == energyTypeModel)
        {
            return false;
        }

        energyTypeCache.remove(key);

        return true;
    }

    // 更新更新缓存中的一条数据
    public Boolean updateOneEnergyTypeCache(BESEnergyType energyType)
    {

        if(null == energyType)
        {
            return false;
        }

        String key = energyType.getfNybh();

        if(null == key)
        {
            return false;
        }

        Cache<String, BESEnergyType> energyTypeCache =  this.getEnergyTypeCache();

        BESEnergyType energyTypeModel = energyTypeCache.get(key);

        if(null == energyTypeModel)
        {
            return false;
        }

        energyTypeCache.put(key, energyType);

        return true;
    }

    public BESEnergyType getCachedElement(String key) throws CacheException
    {
        if (energyTypeCache == null || energyTypeCache.size() == 0 || key == null)
        {
            return null;
        }

        return energyTypeCache.get(key);
    }

    public Cache<String, BESEnergyType> getEnergyTypeCache()
    {
        return energyTypeCache;
    }

    public void setEnergyTypeCache(Cache<String, BESEnergyType> energyTypeCache)
    {
        this.energyTypeCache = energyTypeCache;
    }

}
