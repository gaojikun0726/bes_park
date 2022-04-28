package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.core.common.constant.CacheNameCode;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionPointModel;
import com.efounder.JEnterprise.service.systemcenter.interfaceconfig.DeviceFunctionPointService;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 设备功能点位定义缓存
 *
 * @author xiepufeng
 * @date 2020/6/20 10:21
 */
@Component
public class DeviceFunctionPointCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<Integer, DeviceFunctionPointModel> deviceFunctionPointCache;

    @Resource
    private DeviceFunctionPointService besDeviceFunctionPointService;

    @Override
    public void run(String... args) throws Exception
    {
        loadCache();
    }

    /**
     * 1. 从数据库中查询出全部”设备功能点位定义“数据。
     * 2. 获取一个缓存管理器”EhCacheManager“。
     * 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     * 4. 把“设备功能点位定义”数据对象循环添加到缓存对象。
     */
    public void loadCache()
    {
        // 1. 从数据库中查询出全部”设备功能点位定义“数据。
        List<DeviceFunctionPointModel> deviceFunctionPointList = besDeviceFunctionPointService.findAll();

        if (deviceFunctionPointList == null)
        {
            return;
        }

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        deviceFunctionPointCache = ehCacheManager.getCache(CacheNameCode.DEVICE_FUNCTION_POINT_CACHE);

        if (null == deviceFunctionPointCache)
        {
            return;
        }

        deviceFunctionPointCache.clear();

        // 4. 把“设备功能点位定义”数据对象循环添加到缓存对象。
        for (DeviceFunctionPointModel deviceFunctionPoint : deviceFunctionPointList)
        {
            deviceFunctionPointCache.put(deviceFunctionPoint.getId(), deviceFunctionPoint);
        }
    }

    // 添加一条数据到缓存中
    public Boolean addOneDeviceFunctionPointCache(DeviceFunctionPointModel deviceFunctionPoint)
    {

        if (null == deviceFunctionPoint)
        {
            return false;
        }

        Integer key = deviceFunctionPoint.getId();

        if (null == key)
        {
            return false;
        }

        Cache<Integer, DeviceFunctionPointModel> deviceFunctionPointCache = this.getDeviceFunctionPointCache();

        DeviceFunctionPointModel deviceFunctionPointModel = deviceFunctionPointCache.get(key);

        if (null != deviceFunctionPointModel)
        {
            return false;
        }


        deviceFunctionPointCache.put(key, deviceFunctionPoint);

        return true;
    }

    // 删除缓存中的一条数据
    public Boolean deleteOneDeviceFunctionPointCache(Integer key)
    {
        if (null == key)
        {
            return false;
        }

        Cache<Integer, DeviceFunctionPointModel> deviceFunctionPointCache = this.getDeviceFunctionPointCache();

        DeviceFunctionPointModel deviceFunctionPointModel = deviceFunctionPointCache.get(key);

        if (null == deviceFunctionPointModel)
        {
            return false;
        }

        deviceFunctionPointCache.remove(key);

        return true;
    }

    // 更新更新缓存中的一条数据
    public Boolean updateOneDeviceFunctionPointCache(DeviceFunctionPointModel deviceFunctionPoint)
    {

        if (null == deviceFunctionPoint)
        {
            return false;
        }

        Integer key = deviceFunctionPoint.getId();

        if (null == key)
        {
            return false;
        }

        Cache<Integer, DeviceFunctionPointModel> deviceFunctionPointCache = this.getDeviceFunctionPointCache();

        DeviceFunctionPointModel deviceFunctionPointModel = deviceFunctionPointCache.get(key);

        if (null == deviceFunctionPointModel)
        {
            return false;
        }

        deviceFunctionPointCache.put(key, deviceFunctionPoint);

        return true;
    }

    public DeviceFunctionPointModel getCachedElement(Integer key) throws CacheException
    {
        if (deviceFunctionPointCache == null || deviceFunctionPointCache.size() == 0 || key == null)
        {
            return null;
        }

        return deviceFunctionPointCache.get(key);
    }

    /**
     * 根据设备id 和功能id获取缓存数据
     *
     * @param deviceId
     * @return
     */
    public DeviceFunctionPointModel getByDeviceIdAndDeviceFunctionId(String deviceId, Integer deviceFunctionId)
    {
        if (deviceId == null || deviceFunctionId == null)
        {
            return null;
        }


        if (deviceFunctionPointCache == null)
        {
            return null;
        }

        Collection<DeviceFunctionPointModel> deviceFunctionPointModels = deviceFunctionPointCache.values();

        if (deviceFunctionPointModels == null)
        {
            return null;
        }


        for (DeviceFunctionPointModel deviceFunctionPointModel : deviceFunctionPointModels) {
            if (deviceId.equals(deviceFunctionPointModel.getDeviceId()) && deviceFunctionId.equals(deviceFunctionPointModel.getDeviceFunctionId()))
            {
                return deviceFunctionPointModel;
            }
        }


        return null;

    }
    /**
     * 根据设备id 获取缓存数据
     *
     * @param deviceId
     * @return
     */
    public List<DeviceFunctionPointModel> getByDeviceId(String deviceId)
    {
        if (deviceId == null)
        {
            return null;
        }


        if (deviceFunctionPointCache == null)
        {
            return null;
        }

        Collection<DeviceFunctionPointModel> deviceFunctionPointModels = deviceFunctionPointCache.values();

        if (deviceFunctionPointModels == null)
        {
            return null;
        }

        List<DeviceFunctionPointModel> list = new ArrayList<>();


        for (DeviceFunctionPointModel deviceFunctionPointModel : deviceFunctionPointModels) {
            if (deviceId.equals(deviceFunctionPointModel.getDeviceId()))
            {
                list.add(deviceFunctionPointModel);
            }
        }


        return list;

    }

    /**
     * 根据功能id 获取缓存数据
     *
     * @param deviceFunctionId
     * @return
     */
    public List<DeviceFunctionPointModel> getByDeviceFunctionId(Integer deviceFunctionId)
    {
        if (deviceFunctionId == null)
        {
            return null;
        }


        if (deviceFunctionPointCache == null)
        {
            return null;
        }

        Collection<DeviceFunctionPointModel> deviceFunctionPointModels = deviceFunctionPointCache.values();

        if (deviceFunctionPointModels == null)
        {
            return null;
        }

        List<DeviceFunctionPointModel> list = new ArrayList<>();


        for (DeviceFunctionPointModel deviceFunctionPointModel : deviceFunctionPointModels) {
            if (deviceFunctionId.equals(deviceFunctionPointModel.getDeviceFunctionId()))
            {
                list.add(deviceFunctionPointModel);
            }
        }


        return list;

    }

    /**
     * 根据点位系统名称 获取缓存数据
     *
     * @param deviceFunctionId
     * @return
     */
    public List<DeviceFunctionPointModel> getByDeviceFunctionSysName(String sysName)
    {
        if (sysName == null)
        {
            return null;
        }


        if (deviceFunctionPointCache == null)
        {
            return null;
        }

        Collection<DeviceFunctionPointModel> deviceFunctionPointModels = deviceFunctionPointCache.values();

        if (deviceFunctionPointModels == null)
        {
            return null;
        }

        List<DeviceFunctionPointModel> list = new ArrayList<>();


        for (DeviceFunctionPointModel deviceFunctionPointModel : deviceFunctionPointModels) {
            if (sysName.equals(deviceFunctionPointModel.getSysName()))
            {
                list.add(deviceFunctionPointModel);
            }
        }


        return list;

    }

    public Cache<Integer, DeviceFunctionPointModel> getDeviceFunctionPointCache()
    {
        return deviceFunctionPointCache;
    }

    public void setDeviceFunctionPointCache(Cache<Integer, DeviceFunctionPointModel> deviceFunctionPointCache)
    {
        this.deviceFunctionPointCache = deviceFunctionPointCache;
    }

}
