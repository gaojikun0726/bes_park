package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.core.common.constant.CacheNameCode;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BesDdcMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesDdc;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 控制器配置定义缓存
 * @author xiepufeng
 * @date 2020/6/20 10:21
 */
@Component
public class DdcCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<String, BesDdc> ddcCache;

    @Resource
    private BesDdcMapper besDdcMapper;

    @Override
    public void run(String... args) throws Exception
    {
        loadCache();
    }

    /**
     * 1. 从数据库中查询出全部”控制器配置定义“数据。
     * 2. 获取一个缓存管理器”EhCacheManager“。
     * 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     * 4. 把“控制器配置定义”数据对象循环添加到缓存对象。
     */
    public void loadCache()
    {
        // 1. 从数据库中查询出全部”控制器配置定义“数据。
        List<BesDdc> ddcList = besDdcMapper.loadAll();

        if (ddcList == null)
        {
            return;
        }

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        ddcCache = ehCacheManager.getCache(CacheNameCode.DDC_CACHE);

        if (null == ddcCache)
        {
            return;
        }

        ddcCache.clear();

        // 4. 把“控制器配置定义”数据对象循环添加到缓存对象。
        for (BesDdc ddc : ddcList)
        {
            ddc.setfDdcOnlinestate("0"); // 状态设置为离线
            ddcCache.put(ddc.getfSysName(), ddc);
        }
    }

    // 添加一条数据到缓存中
    public Boolean addOneDdcCache(BesDdc ddc)
    {

        if(null == ddc)
        {
            return false;
        }

        String key = ddc.getfSysName();

        if(null == key)
        {
            return false;
        }

        Cache<String, BesDdc> ddcCache =  this.getDdcCache();

        BesDdc ddcModel = ddcCache.get(key);

        if(null != ddcModel)
        {
            return false;
        }

        ddcCache.put(key, ddc);

        return true;
    }

    // 删除缓存中的一条数据
    public Boolean deleteOneDdcCache(String key)
    {
        if (null == key)
        {
            return false;
        }

        Cache<String, BesDdc> ddcCache =  this.getDdcCache();

        BesDdc ddcModel = ddcCache.get(key);

        if(null == ddcModel)
        {
            return false;
        }

        ddcCache.remove(key);

        return true;
    }

    // 更新更新缓存中的一条数据
    public Boolean updateOneDdcCache(BesDdc ddc)
    {

        if(null == ddc)
        {
            return false;
        }

        String key = ddc.getfSysName();

        if(null == key)
        {
            return false;
        }

        Cache<String, BesDdc> ddcCache =  this.getDdcCache();

        BesDdc ddcModel = ddcCache.get(key);

        if(null == ddcModel)
        {
            return false;
        }

        ddcCache.put(key, ddc);

        return true;
    }

    public BesDdc getCachedElement(String key) throws CacheException
    {
        if (ddcCache == null || ddcCache.size() == 0 || key == null)
        {
            return null;
        }

        return ddcCache.get(key);
    }

    public BesDdc getDdcByChannelId(String ip) {

        if (ddcCache == null || ddcCache.size() == 0 || ip == null)
        {
            return null;
        }

        Collection<BesDdc> besDdcs = ddcCache.values();

        if (besDdcs == null || besDdcs.isEmpty())
        {
            return null;
        }

        for (BesDdc besDdc : besDdcs) {

            String channelId = besDdc.getfChannelId();

            if (ip.equals(channelId))
            {
                return besDdc;
            }

        }

        return null;

    }

    public Cache<String, BesDdc> getDdcCache()
    {
        return ddcCache;
    }

    public void setDdcCache(Cache<String, BesDdc> ddcCache)
    {
        this.ddcCache = ddcCache;
    }

}
