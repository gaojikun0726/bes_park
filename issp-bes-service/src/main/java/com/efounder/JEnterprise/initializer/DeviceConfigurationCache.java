package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.core.common.constant.CacheNameCode;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceConfigurationModel;
import com.efounder.JEnterprise.service.systemcenter.interfaceconfig.DeviceConfigurationService;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 设备定义缓存
 * @author xiepufeng
 * @date 2020/6/20 10:21
 */
@Component
public class DeviceConfigurationCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<String, DeviceConfigurationModel> deviceConfigurationCache;

    @Resource
    private DeviceConfigurationService deviceConfigurationService;

    @Override
    public void run(String... args) throws Exception
    {
        loadCache();
    }

    /**
     * 1. 从数据库中查询出全部”设备定义“数据。
     * 2. 获取一个缓存管理器”EhCacheManager“。
     * 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     * 4. 把“设备定义”数据对象循环添加到缓存对象。
     */
    public void loadCache()
    {
        // 1. 从数据库中查询出全部”设备定义“数据。
        List<DeviceConfigurationModel> deviceConfigurationList = deviceConfigurationService.findAll();

        if (deviceConfigurationList == null)
        {
            return;
        }

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        deviceConfigurationCache = ehCacheManager.getCache(CacheNameCode.DEVICE_CONFIGURATION_CACHE);

        if (null == deviceConfigurationCache)
        {
            return;
        }

        deviceConfigurationCache.clear();

        // 4. 把“设备定义”数据对象循环添加到缓存对象。
        for (DeviceConfigurationModel deviceConfiguration : deviceConfigurationList)
        {
            deviceConfigurationCache.put(deviceConfiguration.getId(), deviceConfiguration);
        }
    }

    // 添加一条数据到缓存中
    public Boolean addOneDeviceConfigurationCache(DeviceConfigurationModel deviceConfiguration)
    {

        if(null == deviceConfiguration)
        {
            return false;
        }

        String key = deviceConfiguration.getId();

        if(null == key)
        {
            return false;
        }

        Cache<String, DeviceConfigurationModel> deviceConfigurationCache =  this.getDeviceConfigurationCache();

        DeviceConfigurationModel deviceConfigurationModel = deviceConfigurationCache.get(key);

        if(null != deviceConfigurationModel)
        {
            return false;
        }

        deviceConfigurationCache.put(key, deviceConfiguration);

        return true;
    }

    // 删除缓存中的一条数据
    public Boolean deleteOneDeviceConfigurationCache(String key)
    {
        if (null == key)
        {
            return false;
        }

        Cache<String, DeviceConfigurationModel> deviceConfigurationCache =  this.getDeviceConfigurationCache();

        DeviceConfigurationModel deviceConfigurationModel = deviceConfigurationCache.get(key);

        if(null == deviceConfigurationModel)
        {
            return false;
        }

        deviceConfigurationCache.remove(key);

        return true;
    }

    // 更新更新缓存中的一条数据
    public Boolean updateOneDeviceConfigurationCache(DeviceConfigurationModel deviceConfigurationModel)
    {

        if(null == deviceConfigurationModel)
        {
            return false;
        }

        String key = deviceConfigurationModel.getId();

        if(null == key)
        {
            return false;
        }

        Cache<String, DeviceConfigurationModel> deviceConfigurationCache =  this.getDeviceConfigurationCache();

        DeviceConfigurationModel deviceConfiguration = deviceConfigurationCache.get(key);

        if(null == deviceConfiguration)
        {
            return false;
        }

        deviceConfigurationCache.put(key, deviceConfigurationModel);

        return true;
    }

    public DeviceConfigurationModel getCachedElement(String key) throws CacheException
    {
        if (deviceConfigurationCache == null || deviceConfigurationCache.size() == 0 || key == null)
        {
            return null;
        }

        return deviceConfigurationCache.get(key);
    }

    public Cache<String, DeviceConfigurationModel> getDeviceConfigurationCache()
    {
        return deviceConfigurationCache;
    }

    public void setDeviceConfigurationCache(Cache<String, DeviceConfigurationModel> deviceConfigurationCache)
    {
        this.deviceConfigurationCache = deviceConfigurationCache;
    }

}
