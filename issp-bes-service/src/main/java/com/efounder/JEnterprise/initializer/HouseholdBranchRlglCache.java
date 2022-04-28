package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.core.common.constant.CacheNameCode;
import com.efounder.JEnterprise.mapper.basedatamanage.energyinformation.BESBranchConfMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.energyinformation.BesHouseholdBranchRlglMapper;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESBranch_Ammeter_Rlgl;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BesHouseholdBranchRlgl;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * 分户支路关联定义缓存
 * @author xiepufeng
 * @date 2020/6/20 10:21
 */
@Component
public class HouseholdBranchRlglCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<String, Map<String, BesHouseholdBranchRlgl>> householdBranchRlglCache;

    @Resource
    private BesHouseholdBranchRlglMapper besHouseholdBranchRlglMapper;

    @Override
    public void run(String... args) throws Exception
    {
        loadCache();
    }

    /**
     * 1. 从数据库中查询出全部”分户支路关联定义“数据。
     * 2. 获取一个缓存管理器”EhCacheManager“。
     * 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     * 4. 把“分户支路关联定义”数据对象循环添加到缓存对象。
     */
    public void loadCache()
    {

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        householdBranchRlglCache = ehCacheManager.getCache(CacheNameCode.HOUSEHOLD_BRANCH_RLGL);

        updateAll();

    }

    public void updateAll()
    {
        householdBranchRlglCache.clear();

        // 1. 从数据库中查询出全部”分户支路关联定义“数据。
        List<BesHouseholdBranchRlgl> householdBranchRlglList = besHouseholdBranchRlglMapper.queryAll();

        if (householdBranchRlglList == null)
        {
            return;
        }

        // 4. 把“分户支路关联定义”数据对象循环添加到缓存对象。

        householdBranchRlglList.forEach(item -> {

            String fZlbh = item.getfZlbh();
            String fFhbh = item.getfFhbh();

            Map<String, BesHouseholdBranchRlgl> besHouseholdBranchRlglMap = householdBranchRlglCache.get(fFhbh);

            if (besHouseholdBranchRlglMap == null) {
                besHouseholdBranchRlglMap = new HashMap<>();
                householdBranchRlglCache.put(fFhbh, besHouseholdBranchRlglMap);
            }

            besHouseholdBranchRlglMap.put(fZlbh, item);

        });
    }

    // 添加一条数据到缓存中
    public Boolean putOneCache(BesHouseholdBranchRlgl householdBranchRlgl)
    {

        if(null == householdBranchRlgl)
        {
            return false;
        }

        String fZlbh = householdBranchRlgl.getfZlbh();
        String fFhbh = householdBranchRlgl.getfFhbh();

        if(null == fZlbh || fFhbh == null)
        {
            return false;
        }

        Map<String, BesHouseholdBranchRlgl> besHouseholdBranchRlglMap = householdBranchRlglCache.get(fFhbh);

        if(null == besHouseholdBranchRlglMap)
        {
            besHouseholdBranchRlglMap = new HashMap<>();

            householdBranchRlglCache.put(fFhbh, besHouseholdBranchRlglMap);
        }


        besHouseholdBranchRlglMap.put(fZlbh, householdBranchRlgl);

        return true;
    }

    // 删除缓存中的一条数据
    public Boolean deleteOneCache(String fFhbh, String fZlbh)
    {
        if (null == fZlbh || null == fFhbh)
        {
            return false;
        }

        Map<String, BesHouseholdBranchRlgl> besHouseholdBranchRlglMap = householdBranchRlglCache.get(fFhbh);

        if(null == besHouseholdBranchRlglMap)
        {
            return false;
        }

        BesHouseholdBranchRlgl householdBranchRlgl = besHouseholdBranchRlglMap.get(fZlbh);

        if(null == householdBranchRlgl)
        {
            return false;
        }

        besHouseholdBranchRlglMap.remove(fZlbh);

        return true;
    }

    // 删除缓存根据分户编号
    public Boolean deleteCacheByfFhbh(String fFhbh)
    {
        if (null == fFhbh)
        {
            return false;
        }

        Map<String, BesHouseholdBranchRlgl> besHouseholdBranchRlglMap = householdBranchRlglCache.get(fFhbh);

        if(null == besHouseholdBranchRlglMap || besHouseholdBranchRlglMap.isEmpty())
        {
            return false;
        }

        householdBranchRlglCache.remove(fFhbh);

        return true;
    }

    public Map<String, BesHouseholdBranchRlgl> getCachedElement(String fFhbh) throws CacheException
    {
        if (householdBranchRlglCache == null || householdBranchRlglCache.size() == 0 || fFhbh == null)
        {
            return null;
        }

        return householdBranchRlglCache.get(fFhbh);
    }

    public List<BesHouseholdBranchRlgl> getCachedElementByfZlbh(String fZlbh) throws CacheException
    {
        if (householdBranchRlglCache == null || householdBranchRlglCache.size() == 0 || fZlbh == null)
        {
            return null;
        }

        Collection<Map<String, BesHouseholdBranchRlgl>> collection =  householdBranchRlglCache.values();

        if (collection == null || collection.isEmpty()) return null;

        List<BesHouseholdBranchRlgl> besHouseholdBranchRlgls = new ArrayList<>();

        collection.forEach(item -> {
            item.forEach((key, value) -> {
                if (fZlbh.equals(value.getfZlbh()))
                {
                    besHouseholdBranchRlgls.add(value);
                }
            });
        });

        return besHouseholdBranchRlgls;
    }

    public Cache<String, Map<String, BesHouseholdBranchRlgl>> getHouseholdBranchRlglCache() {
        return householdBranchRlglCache;
    }

    public void setHouseholdBranchRlglCache(Cache<String, Map<String, BesHouseholdBranchRlgl>> householdBranchRlglCache) {
        this.householdBranchRlglCache = householdBranchRlglCache;
    }
}
