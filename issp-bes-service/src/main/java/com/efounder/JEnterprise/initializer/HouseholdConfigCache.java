package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.core.common.constant.CacheNameCode;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BesHouseholdConf;
import com.efounder.JEnterprise.service.basedatamanage.energyinformation.BesHouseholdConfService;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * 分户配置定义缓存
 * @author xiepufeng
 * @date 2020/6/20 10:21
 */
@Component
public class HouseholdConfigCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<String, BesHouseholdConf> householdConfCache;

    @Resource
    private BesHouseholdConfService besHouseholdConfService;

    @Override
    public void run(String... args) throws Exception
    {
        loadCache();
    }

    /**
     * 1. 从数据库中查询出全部”分户配置定义“数据。
     * 2. 获取一个缓存管理器”EhCacheManager“。
     * 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     * 4. 把“分户配置定义”数据对象循环添加到缓存对象。
     */
    public void loadCache()
    {
        // 1. 从数据库中查询出全部”分户配置定义“数据。
        List<BesHouseholdConf> householdConfList = besHouseholdConfService.findAll();

        if (householdConfList == null)
        {
            return;
        }

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        householdConfCache = ehCacheManager.getCache(CacheNameCode.HOUSEHOLD_CONFIG_CACHE);

        if (null == householdConfCache)
        {
            return;
        }

        householdConfCache.clear();

        // 4. 把“分户配置定义”数据对象循环添加到缓存对象。
        for (BesHouseholdConf householdConf : householdConfList)
        {
            householdConfCache.put(householdConf.getfFhbh(), householdConf);
        }
    }

    // 添加一条数据到缓存中
    public Boolean addOneHouseholdConfCache(BesHouseholdConf householdConf)
    {

        if(null == householdConf)
        {
            return false;
        }

        String key = householdConf.getfFhbh();

        if(null == key)
        {
            return false;
        }

        Cache<String, BesHouseholdConf> householdConfCache =  this.getHouseholdConfCache();

        BesHouseholdConf householdConfModel = householdConfCache.get(key);

        if(null != householdConfModel)
        {
            return false;
        }

        householdConfCache.put(key, householdConf);

        return true;
    }

    // 删除缓存中的一条数据
    public Boolean deleteOneHouseholdConfCache(String key)
    {
        if (null == key)
        {
            return false;
        }

        Cache<String, BesHouseholdConf> householdConfCache =  this.getHouseholdConfCache();

        BesHouseholdConf householdConfModel = householdConfCache.get(key);

        if(null == householdConfModel)
        {
            return false;
        }

        householdConfCache.remove(key);

        return true;
    }

    // 更新更新缓存中的一条数据
    public Boolean updateOneHouseholdConfCache(BesHouseholdConf householdConf)
    {

        if(null == householdConf)
        {
            return false;
        }

        String key = householdConf.getfFhbh();

        if(null == key)
        {
            return false;
        }

        Cache<String, BesHouseholdConf> householdConfCache =  this.getHouseholdConfCache();

        BesHouseholdConf householdConfModel = householdConfCache.get(key);

        if(null == householdConfModel)
        {
            return false;
        }

        householdConfCache.put(key, householdConf);

        return true;
    }


    /**
     * 获取下级级联分户信息
     *
     * @param fFhbh
     * @return
     */
    public List<BesHouseholdConf> getCascadeSubordinateHouseholdConf(String fFhbh)
    {
        if (null == fFhbh)
        {
            return null;
        }

        Collection<BesHouseholdConf> besHouseholdConfs = householdConfCache.values();

        if (null == besHouseholdConfs || besHouseholdConfs.isEmpty())
        {
            return null;
        }

        Map<String, List<BesHouseholdConf>> besHouseholdConfMap = new HashMap<>();

        besHouseholdConfs.forEach(besHouseholdConf ->
        {
            String pfhbh = besHouseholdConf.getfPfhbh();

            List<BesHouseholdConf> besHouseholdConfList = besHouseholdConfMap.computeIfAbsent(pfhbh, k -> new ArrayList<>());

            besHouseholdConfList.add(besHouseholdConf);

        });

        List<BesHouseholdConf> besHouseholdConfList = new ArrayList<>();

        getUnderBesHouseholdConf(besHouseholdConfList, besHouseholdConfMap, fFhbh);

        return besHouseholdConfList;
    }


    private void getUnderBesHouseholdConf(List<BesHouseholdConf> besHouseholdConfList, Map<String, List<BesHouseholdConf>> besHouseholdConfMap, String fFhbh)
    {
        if (besHouseholdConfMap == null || besHouseholdConfMap.isEmpty() || fFhbh == null)
        {
            return;
        }

        List<BesHouseholdConf> childBesHouseholdConfList = besHouseholdConfMap.get(fFhbh);

        if (childBesHouseholdConfList != null && !childBesHouseholdConfList.isEmpty())
        {
            childBesHouseholdConfList.forEach(besHouseholdConf ->
            {
                getUnderBesHouseholdConf(besHouseholdConfList, besHouseholdConfMap, besHouseholdConf.getfFhbh());

            });

            besHouseholdConfList.addAll(childBesHouseholdConfList);
        }

    }

    public BesHouseholdConf getCachedElement(String key) throws CacheException
    {
        if (householdConfCache == null || householdConfCache.size() == 0 || key == null)
        {
            return null;
        }

        return householdConfCache.get(key);
    }

    public Cache<String, BesHouseholdConf> getHouseholdConfCache()
    {
        return householdConfCache;
    }

    public void setHouseholdConfCache(Cache<String, BesHouseholdConf> householdConfCache)
    {
        this.householdConfCache = householdConfCache;
    }

}
