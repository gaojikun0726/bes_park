package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.core.common.constant.CacheNameCode;
import com.efounder.JEnterprise.mapper.basedatamanage.energyinformation.BESSubitem_Branch_RlglMapper;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESSubitem_Branch_Rlgl;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BesHouseholdBranchRlgl;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * 分项支路关联定义缓存
 * @author xiepufeng
 * @date 2020/6/20 10:21
 */
@Component
public class SubitemBranchRlglCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<String, Map<String, BESSubitem_Branch_Rlgl>> subitemBranchRlglCache;

    @Resource
    private BESSubitem_Branch_RlglMapper besSubitemBranchRlglMapper;

    @Override
    public void run(String... args) throws Exception
    {
        loadCache();
    }

    /**
     * 1. 从数据库中查询出全部”分项支路关联定义“数据。
     * 2. 获取一个缓存管理器”EhCacheManager“。
     * 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     * 4. 把“分项支路关联定义”数据对象循环添加到缓存对象。
     */
    public void loadCache()
    {

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        subitemBranchRlglCache = ehCacheManager.getCache(CacheNameCode.SUBITEM_BRANCH_RLGL);

        updateAll();

    }

    public void updateAll()
    {
        subitemBranchRlglCache.clear();

        // 1. 从数据库中查询出全部”分项支路关联定义“数据。
        List<BESSubitem_Branch_Rlgl> subitemBranchRlglList = besSubitemBranchRlglMapper.queryAll();

        if (subitemBranchRlglList == null)
        {
            return;
        }

        // 4. 把“分项支路关联定义”数据对象循环添加到缓存对象。

        subitemBranchRlglList.forEach(item -> {

            String fFxbh = item.getfFxbh();
            String fZlbh = item.getfZlbh();

            Map<String, BESSubitem_Branch_Rlgl> besSubitemBranchRlglMap = subitemBranchRlglCache.get(fFxbh);

            if (besSubitemBranchRlglMap == null) {
                besSubitemBranchRlglMap = new HashMap<>();
                subitemBranchRlglCache.put(fFxbh, besSubitemBranchRlglMap);
            }

            besSubitemBranchRlglMap.put(fZlbh, item);

        });
    }

    // 添加一条数据到缓存中
    public Boolean putOneCache(BESSubitem_Branch_Rlgl subitemBranchRlgl)
    {

        if(null == subitemBranchRlgl)
        {
            return false;
        }

        String fFxbh = subitemBranchRlgl.getfFxbh();
        String fZlbh = subitemBranchRlgl.getfZlbh();

        if(null == fZlbh || fFxbh == null)
        {
            return false;
        }

        Map<String, BESSubitem_Branch_Rlgl> besSubitemBranchRlglMap = subitemBranchRlglCache.get(fFxbh);

        if(null == besSubitemBranchRlglMap)
        {
            besSubitemBranchRlglMap = new HashMap<>();

            subitemBranchRlglCache.put(fFxbh, besSubitemBranchRlglMap);
        }


        besSubitemBranchRlglMap.put(fZlbh, subitemBranchRlgl);

        return true;
    }

    // 删除缓存中的一条数据
    public Boolean deleteOneCache(String fFxbh, String fZlbh)
    {
        if (null == fZlbh || null == fFxbh)
        {
            return false;
        }

        Map<String, BESSubitem_Branch_Rlgl> besSubitemBranchRlglMap = subitemBranchRlglCache.get(fFxbh);

        if(null == besSubitemBranchRlglMap)
        {
            return false;
        }

        BESSubitem_Branch_Rlgl subitemBranchRlgl = besSubitemBranchRlglMap.get(fZlbh);

        if(null == subitemBranchRlgl)
        {
            return false;
        }

        besSubitemBranchRlglMap.remove(fZlbh);

        return true;
    }

    // 删除缓存根据分项编号
    public Boolean deleteCacheByfFxbh(String fFxbh)
    {
        if (null == fFxbh)
        {
            return false;
        }

        Map<String, BESSubitem_Branch_Rlgl> besSubitemBranchRlglMap = subitemBranchRlglCache.get(fFxbh);

        if(null == besSubitemBranchRlglMap || besSubitemBranchRlglMap.isEmpty())
        {
            return false;
        }

        subitemBranchRlglCache.remove(fFxbh);

        return true;
    }

    public Map<String, BESSubitem_Branch_Rlgl> getCachedElement(String fFxbh) throws CacheException
    {
        if (subitemBranchRlglCache == null || subitemBranchRlglCache.size() == 0 || fFxbh == null)
        {
            return null;
        }

        return subitemBranchRlglCache.get(fFxbh);
    }

    public List<BESSubitem_Branch_Rlgl> getCachedElementByfZlbh(String fZlbh) throws CacheException
    {
        if (subitemBranchRlglCache == null || subitemBranchRlglCache.size() == 0 || fZlbh == null)
        {
            return null;
        }

        Collection<Map<String, BESSubitem_Branch_Rlgl>> collection =  subitemBranchRlglCache.values();

        if (collection == null || collection.isEmpty()) return null;

        List<BESSubitem_Branch_Rlgl> besSubitemBranchRlgls = new ArrayList<>();

        collection.forEach(item -> {
            item.forEach((key, value) -> {
                if (fZlbh.equals(value.getfZlbh()))
                {
                    besSubitemBranchRlgls.add(value);
                }
            });
        });

        return besSubitemBranchRlgls;
    }

    public Cache<String, Map<String, BESSubitem_Branch_Rlgl>> getSubitemBranchRlglCache() {
        return subitemBranchRlglCache;
    }

    public void setSubitemBranchRlglCache(Cache<String, Map<String, BESSubitem_Branch_Rlgl>> subitemBranchRlglCache) {
        this.subitemBranchRlglCache = subitemBranchRlglCache;
    }
}
