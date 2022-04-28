package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.core.common.constant.CacheNameCode;
import com.efounder.JEnterprise.mapper.basedatamanage.enegrycollectionmanage.BESElectric_Coll_RlglMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.energyinformation.BESBranchConfMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BESAmmeterMapper;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESElectric_Coll_Rlgl;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESBranch_Ammeter_Rlgl;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * 支路电表关联定义缓存
 * @author xiepufeng
 * @date 2020/6/20 10:21
 */
@Component
public class BranchAmmeteRrlglCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<String, Map<String, BESBranch_Ammeter_Rlgl>> branchAmmeterRlglCache;

    @Resource
    private BESBranchConfMapper besBranchConfMapper;

    @Override
    public void run(String... args) throws Exception
    {
        loadCache();
    }

    /**
     * 1. 从数据库中查询出全部”支路电表关联定义“数据。
     * 2. 获取一个缓存管理器”EhCacheManager“。
     * 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     * 4. 把“支路电表关联定义”数据对象循环添加到缓存对象。
     */
    public void loadCache()
    {

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        branchAmmeterRlglCache = ehCacheManager.getCache(CacheNameCode.BRANCH_AMMETER_RLGL);

        updateAll();

    }

    public void updateAll()
    {
        branchAmmeterRlglCache.clear();

        // 1. 从数据库中查询出全部”支路电表关联定义“数据。
        List<BESBranch_Ammeter_Rlgl> branchAmmeterRlglList = besBranchConfMapper.loadAllBranchAmmeterRlgl();

        if (branchAmmeterRlglList == null)
        {
            return;
        }

        // 4. 把“支路电表关联定义”数据对象循环添加到缓存对象。

        branchAmmeterRlglList.forEach(item -> {

            String fZlbh = item.getfZlbh();
            String fDbsysName = item.getfDbsysName();

            Map<String, BESBranch_Ammeter_Rlgl> besBranchAmmeterRlglMap = branchAmmeterRlglCache.get(fZlbh);

            if (besBranchAmmeterRlglMap == null) {
                besBranchAmmeterRlglMap = new HashMap<>();
                branchAmmeterRlglCache.put(fZlbh, besBranchAmmeterRlglMap);
            }

            besBranchAmmeterRlglMap.put(fDbsysName, item);

        });
    }

    // 添加一条数据到缓存中
    public Boolean putOneCache(BESBranch_Ammeter_Rlgl branchAmmeterRlgl)
    {

        if(null == branchAmmeterRlgl)
        {
            return false;
        }

        String fZlbh = branchAmmeterRlgl.getfZlbh();
        String fDbsysName = branchAmmeterRlgl.getfDbsysName();

        if(null == fZlbh || fDbsysName == null)
        {
            return false;
        }

        Map<String, BESBranch_Ammeter_Rlgl> besBranchAmmeterRlglMap = branchAmmeterRlglCache.get(fZlbh);

        if(null == besBranchAmmeterRlglMap)
        {
            besBranchAmmeterRlglMap = new HashMap<>();

            branchAmmeterRlglCache.put(fZlbh, besBranchAmmeterRlglMap);
        }


        besBranchAmmeterRlglMap.put(fDbsysName, branchAmmeterRlgl);

        return true;
    }

    // 删除缓存中的一条数据
    public Boolean deleteOneCache(String fZlbh, String fDbsysName)
    {
        if (null == fZlbh || null == fDbsysName)
        {
            return false;
        }

        Map<String, BESBranch_Ammeter_Rlgl> besBranchAmmeterRlglMap = branchAmmeterRlglCache.get(fZlbh);

        if(null == besBranchAmmeterRlglMap)
        {
            return false;
        }

        BESBranch_Ammeter_Rlgl branchAmmeterRlgl = besBranchAmmeterRlglMap.get(fDbsysName);

        if(null == branchAmmeterRlgl)
        {
            return false;
        }

        besBranchAmmeterRlglMap.remove(fDbsysName);

        return true;
    }

    // 删除缓存根据支路编号
    public Boolean deleteCacheByfZlbh(String fZlbh)
    {
        if (null == fZlbh)
        {
            return false;
        }

        Map<String, BESBranch_Ammeter_Rlgl> besBranchAmmeterRlglMap = branchAmmeterRlglCache.get(fZlbh);

        if(null == besBranchAmmeterRlglMap || besBranchAmmeterRlglMap.isEmpty())
        {
            return false;
        }

        branchAmmeterRlglCache.remove(fZlbh);

        return true;
    }

    public Map<String, BESBranch_Ammeter_Rlgl> getCachedElement(String fZlbh) throws CacheException
    {
        if (branchAmmeterRlglCache == null || branchAmmeterRlglCache.size() == 0 || fZlbh == null)
        {
            return null;
        }

        return branchAmmeterRlglCache.get(fZlbh);
    }

    public List<BESBranch_Ammeter_Rlgl> getCachedElementByfDbsysName(String fDbsysName) throws CacheException
    {
        if (branchAmmeterRlglCache == null || branchAmmeterRlglCache.size() == 0 || fDbsysName == null)
        {
            return null;
        }

        Collection<Map<String, BESBranch_Ammeter_Rlgl>> collection =  branchAmmeterRlglCache.values();

        if (collection == null || collection.isEmpty()) return null;

        List<BESBranch_Ammeter_Rlgl> besBranchAmmeterRlgls = new ArrayList<>();

        collection.forEach(item -> {
            item.forEach((key, value) -> {
                if (fDbsysName.equals(value.getfDbsysName()))
                {
                    besBranchAmmeterRlgls.add(value);
                }
            });
        });

        return besBranchAmmeterRlgls;
    }

    public Cache<String, Map<String, BESBranch_Ammeter_Rlgl>> getBranchAmmeterRlglCache() {
        return branchAmmeterRlglCache;
    }

    public void setBranchAmmeterRlglCache(Cache<String, Map<String, BESBranch_Ammeter_Rlgl>> branchAmmeterRlglCache) {
        this.branchAmmeterRlglCache = branchAmmeterRlglCache;
    }
}
