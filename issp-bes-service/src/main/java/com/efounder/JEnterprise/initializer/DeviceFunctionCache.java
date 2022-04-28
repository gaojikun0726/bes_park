package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.core.common.constant.CacheNameCode;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionModel;
import com.efounder.JEnterprise.service.systemcenter.interfaceconfig.DeviceFunctionService;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 设备功能定义缓存
 * @author xiepufeng
 * @date 2020/6/20 10:21
 */
@Component
public class DeviceFunctionCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<Integer, DeviceFunctionModel> deviceFunctionCache;

    @Resource
    private DeviceFunctionService deviceFunctionService;

    @Override
    public void run(String... args) throws Exception
    {
        loadCache();
    }

    /**
     * 1. 从数据库中查询出全部”设备功能定义“数据。
     * 2. 获取一个缓存管理器”EhCacheManager“。
     * 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     * 4. 把“设备功能定义”数据对象循环添加到缓存对象。
     */
    public void loadCache()
    {
        // 1. 从数据库中查询出全部”设备功能定义“数据。
        List<DeviceFunctionModel> deviceFunctionList = deviceFunctionService.findAll();

        if (deviceFunctionList == null)
        {
            return;
        }

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        deviceFunctionCache = ehCacheManager.getCache(CacheNameCode.DEVICE_FUNCTION_CACHE);

        if (null == deviceFunctionCache)
        {
            return;
        }

        deviceFunctionCache.clear();

        // 4. 把“设备功能定义”数据对象循环添加到缓存对象。
        for (DeviceFunctionModel deviceFunction : deviceFunctionList)
        {
            deviceFunctionCache.put(deviceFunction.getId(), deviceFunction);
        }
    }

    // 添加一条数据到缓存中
    public Boolean addOneDeviceFunctionCache(DeviceFunctionModel deviceFunction)
    {

        if(null == deviceFunction)
        {
            return false;
        }

        Integer key = deviceFunction.getId();

        if(null == key)
        {
            return false;
        }

        Cache<Integer, DeviceFunctionModel> deviceFunctionCache =  this.getDeviceFunctionCache();

        DeviceFunctionModel deviceFunctionModel = deviceFunctionCache.get(key);

        if(null != deviceFunctionModel)
        {
            return false;
        }

        deviceFunctionCache.put(key, deviceFunction);

        return true;
    }

    // 删除缓存中的一条数据
    public Boolean deleteOneDeviceFunctionCache(Integer key)
    {
        if (null == key)
        {
            return false;
        }

        Cache<Integer, DeviceFunctionModel> deviceFunctionCache =  this.getDeviceFunctionCache();

        DeviceFunctionModel deviceFunctionModel = deviceFunctionCache.get(key);

        if(null == deviceFunctionModel)
        {
            return false;
        }

        deviceFunctionCache.remove(key);

        return true;
    }

    // 更新更新缓存中的一条数据
    public Boolean updateOneDeviceFunctionCache(DeviceFunctionModel deviceFunction)
    {

        if(null == deviceFunction)
        {
            return false;
        }

        Integer key = deviceFunction.getId();

        if(null == key)
        {
            return false;
        }

        Cache<Integer, DeviceFunctionModel> deviceFunctionCache =  this.getDeviceFunctionCache();

        DeviceFunctionModel deviceFunctionModel = deviceFunctionCache.get(key);

        if(null == deviceFunctionModel)
        {
            return false;
        }

        deviceFunctionCache.put(key, deviceFunction);

        return true;
    }

    public DeviceFunctionModel getCachedElement(Integer key) throws CacheException
    {
        if (deviceFunctionCache == null || deviceFunctionCache.size() == 0 || key == null)
        {
            return null;
        }

        return deviceFunctionCache.get(key);
    }

    public Cache<Integer, DeviceFunctionModel> getDeviceFunctionCache()
    {
        return deviceFunctionCache;
    }

    public void setDeviceFunctionCache(Cache<Integer, DeviceFunctionModel> deviceFunctionCache)
    {
        this.deviceFunctionCache = deviceFunctionCache;
    }

}
