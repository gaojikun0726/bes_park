package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.core.common.constant.CacheNameCode;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceTypeModel;
import com.efounder.JEnterprise.service.systemcenter.interfaceconfig.DeviceTypeService;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 设备类型定义缓存
 * @author xiepufeng
 * @date 2020/6/20 10:21
 */
@Component
public class DeviceTypeCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<Integer, DeviceTypeModel> deviceTypeCache;

    @Resource
    private DeviceTypeService deviceTypeService;

    @Override
    public void run(String... args) throws Exception
    {
        loadCache();
    }

    /**
     * 1. 从数据库中查询出全部”设备类型定义“数据。
     * 2. 获取一个缓存管理器”EhCacheManager“。
     * 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     * 4. 把“设备类型定义”数据对象循环添加到缓存对象。
     */
    public void loadCache()
    {
        // 1. 从数据库中查询出全部”设备类型定义“数据。
        List<DeviceTypeModel> deviceTypeList = deviceTypeService.findAll();

        if (deviceTypeList == null)
        {
            return;
        }

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        deviceTypeCache = ehCacheManager.getCache(CacheNameCode.DEVICE_TYPE_CACHE);

        if (null == deviceTypeCache)
        {
            return;
        }

        deviceTypeCache.clear();

        // 4. 把“设备类型定义”数据对象循环添加到缓存对象。
        for (DeviceTypeModel deviceType : deviceTypeList)
        {
            deviceTypeCache.put(deviceType.getId(), deviceType);
        }
    }

    // 添加一条数据到缓存中
    public Boolean addOneDeviceTypeCache(DeviceTypeModel deviceTypeModel)
    {

        if(null == deviceTypeModel)
        {
            return false;
        }

        Integer key = deviceTypeModel.getId();

        if(null == key)
        {
            return false;
        }

        Cache<Integer, DeviceTypeModel> deviceTypeCache =  this.getDeviceTypeCache();

        DeviceTypeModel deviceType = deviceTypeCache.get(key);

        if(null != deviceType)
        {
            return false;
        }

        deviceTypeCache.put(key, deviceTypeModel);

        return true;
    }

    // 删除缓存中的一条数据
    public Boolean deleteOneDeviceTypeCache(Integer key)
    {
        if (null == key)
        {
            return false;
        }

        Cache<Integer, DeviceTypeModel> deviceTypeCache =  this.getDeviceTypeCache();

        DeviceTypeModel deviceTypeModel = deviceTypeCache.get(key);

        if(null == deviceTypeModel)
        {
            return false;
        }

        deviceTypeCache.remove(key);

        return true;
    }

    // 更新更新缓存中的一条数据
    public Boolean updateOneDeviceTypeCache(DeviceTypeModel deviceTypeModel)
    {

        if(null == deviceTypeModel)
        {
            return false;
        }

        Integer key = deviceTypeModel.getId();

        if(null == key)
        {
            return false;
        }

        Cache<Integer, DeviceTypeModel> deviceTypeCache =  this.getDeviceTypeCache();

        DeviceTypeModel deviceType = deviceTypeCache.get(key);

        if(null == deviceType)
        {
            return false;
        }

        deviceTypeCache.put(key, deviceTypeModel);

        return true;
    }

    public DeviceTypeModel getCachedElement(Integer key) throws CacheException
    {
        if (deviceTypeCache == null || deviceTypeCache.size() == 0 || key == null)
        {
            return null;
        }

        return deviceTypeCache.get(key);
    }

    public Cache<Integer, DeviceTypeModel> getDeviceTypeCache()
    {
        return deviceTypeCache;
    }

    public void setDeviceTypeCache(Cache<Integer, DeviceTypeModel> deviceTypeCache)
    {
        this.deviceTypeCache = deviceTypeCache;
    }

}
