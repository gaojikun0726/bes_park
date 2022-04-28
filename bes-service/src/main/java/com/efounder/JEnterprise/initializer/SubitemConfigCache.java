package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.core.common.constant.CacheNameCode;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESSubitemConf;
import com.efounder.JEnterprise.service.basedatamanage.energyinformation.BESSubitemConfService;
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
 * 分项配置定义缓存
 * @author xiepufeng
 * @date 2020/6/20 10:21
 */
@Component
public class SubitemConfigCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<String, BESSubitemConf> subitemConfCache;

    @Resource
    private BESSubitemConfService besSubitemConfService;

    @Override
    public void run(String... args) throws Exception
    {
        loadCache();
    }

    /**
     * 1. 从数据库中查询出全部”分项配置定义“数据。
     * 2. 获取一个缓存管理器”EhCacheManager“。
     * 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     * 4. 把“分项配置定义”数据对象循环添加到缓存对象。
     */
    public void loadCache()
    {
        // 1. 从数据库中查询出全部”分项配置定义“数据。
        List<BESSubitemConf> subitemConfList = besSubitemConfService.findAll();

        if (subitemConfList == null)
        {
            return;
        }

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        subitemConfCache = ehCacheManager.getCache(CacheNameCode.SUBITEM_CONFIG_CACHE);

        if (null == subitemConfCache)
        {
            return;
        }

        subitemConfCache.clear();

        // 4. 把“分项配置定义”数据对象循环添加到缓存对象。
        for (BESSubitemConf subitemConf : subitemConfList)
        {
            subitemConfCache.put(subitemConf.getfFxbh(), subitemConf);
        }
    }

    // 添加一条数据到缓存中
    public Boolean addOneSubitemConfCache(BESSubitemConf subitemConf)
    {

        if(null == subitemConf)
        {
            return false;
        }

        String key = subitemConf.getfFxbh();

        if(null == key)
        {
            return false;
        }

        Cache<String, BESSubitemConf> subitemConfCache =  this.getSubitemConfCache();

        BESSubitemConf subitemConfModel = subitemConfCache.get(key);

        if(null != subitemConfModel)
        {
            return false;
        }

        subitemConfCache.put(key, subitemConf);

        return true;
    }

    // 删除缓存中的一条数据
    public Boolean deleteOneSubitemConfCache(String key)
    {
        if (null == key)
        {
            return false;
        }

        Cache<String, BESSubitemConf> subitemConfCache =  this.getSubitemConfCache();

        BESSubitemConf subitemConfModel = subitemConfCache.get(key);

        if(null == subitemConfModel)
        {
            return false;
        }

        subitemConfCache.remove(key);

        return true;
    }

    // 更新更新缓存中的一条数据
    public Boolean updateOneSubitemConfCache(BESSubitemConf subitemConf)
    {

        if(null == subitemConf)
        {
            return false;
        }

        String key = subitemConf.getfFxbh();

        if(null == key)
        {
            return false;
        }

        Cache<String, BESSubitemConf> subitemConfCache =  this.getSubitemConfCache();

        BESSubitemConf subitemConfModel = subitemConfCache.get(key);

        if(null == subitemConfModel)
        {
            return false;
        }

        subitemConfCache.put(key, subitemConf);

        return true;
    }

    public BESSubitemConf getCachedElement(String key) throws CacheException
    {
        if (subitemConfCache == null || subitemConfCache.size() == 0 || key == null)
        {
            return null;
        }

        return subitemConfCache.get(key);
    }

    public List<BESSubitemConf> getCachedElementByBudingId(String budingId)
    {
        if (subitemConfCache == null || subitemConfCache.size() == 0 || budingId == null)
        {
            return null;
        }

        Collection<BESSubitemConf> subitemConfCollection =  subitemConfCache.values();

        if (subitemConfCollection == null || subitemConfCollection.isEmpty()) return null;

        List<BESSubitemConf> besSubitemConfs = new ArrayList<>();

        subitemConfCollection.forEach(besSubitemConf -> {
            if (budingId.equals(besSubitemConf.getfBudingId()))
            {
                besSubitemConfs.add(besSubitemConf);
            }
        });

        return besSubitemConfs;
    }

    public BESSubitemConf getCachedElementByBudingIdAndSubitemCode(String budingId, String subitemCode)
    {
        if (subitemConfCache == null || subitemConfCache.size() == 0 || budingId == null || subitemCode == null)
        {
            return null;
        }

        Collection<BESSubitemConf> subitemConfCollection =  subitemConfCache.values();

        if (subitemConfCollection == null || subitemConfCollection.isEmpty()) return null;


        for (BESSubitemConf besSubitemConf : subitemConfCollection) {
            if (budingId.equals(besSubitemConf.getfBudingId()) && subitemCode.equals(besSubitemConf.getfSubitemCode()))
            {
                return besSubitemConf;
            }
        }

        return null;
    }

    public Cache<String, BESSubitemConf> getSubitemConfCache()
    {
        return subitemConfCache;
    }

    public void setSubitemConfCache(Cache<String, BESSubitemConf> subitemConfCache)
    {
        this.subitemConfCache = subitemConfCache;
    }

}
