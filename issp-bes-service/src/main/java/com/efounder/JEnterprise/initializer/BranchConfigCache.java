package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.core.common.constant.CacheNameCode;
import com.efounder.JEnterprise.mapper.basedatamanage.energyinformation.BESBranchConfMapper;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESBranchConf;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 支路配置定义缓存
 * @author xiepufeng
 * @date 2020/6/20 10:21
 */
@Component
public class BranchConfigCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<String, BESBranchConf> branchConfCache;

    @Resource
    private BESBranchConfMapper besBranchConfMapper;

    @Override
    public void run(String... args) throws Exception
    {
        loadCache();
    }

    /**
     * 1. 从数据库中查询出全部”支路配置定义“数据。
     * 2. 获取一个缓存管理器”EhCacheManager“。
     * 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     * 4. 把“支路配置定义”数据对象循环添加到缓存对象。
     */
    public void loadCache()
    {
        // 1. 从数据库中查询出全部”支路配置定义“数据。
        List<BESBranchConf> branchConfList = besBranchConfMapper.loadAll(null, null, null);

        if (branchConfList == null)
        {
            return;
        }

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        branchConfCache = ehCacheManager.getCache(CacheNameCode.BRANCH_CONFIG_CACHE);

        if (null == branchConfCache)
        {
            return;
        }

        branchConfCache.clear();

        // 4. 把“支路配置定义”数据对象循环添加到缓存对象。
        for (BESBranchConf branchConf : branchConfList)
        {
            branchConfCache.put(branchConf.getfZlbh(), branchConf);
        }
    }

    // 添加一条数据到缓存中
    public Boolean addOneBranchConfCache(BESBranchConf branchConf)
    {

        if(null == branchConf)
        {
            return false;
        }

        String key = branchConf.getfZlbh();

        if(null == key)
        {
            return false;
        }

        Cache<String, BESBranchConf> branchConfCache =  this.getBranchConfCache();

        BESBranchConf branchConfModel = branchConfCache.get(key);

        if(null != branchConfModel)
        {
            return false;
        }

        branchConfCache.put(key, branchConf);

        return true;
    }

    // 删除缓存中的一条数据
    public Boolean deleteOneBranchConfCache(String key)
    {
        if (null == key)
        {
            return false;
        }

        Cache<String, BESBranchConf> branchConfCache =  this.getBranchConfCache();

        BESBranchConf branchConfModel = branchConfCache.get(key);

        if(null == branchConfModel)
        {
            return false;
        }

        branchConfCache.remove(key);

        return true;
    }

    // 更新更新缓存中的一条数据
    public Boolean updateOneBranchConfCache(BESBranchConf branchConf)
    {

        if(null == branchConf)
        {
            return false;
        }

        String key = branchConf.getfZlbh();

        if(null == key)
        {
            return false;
        }

        Cache<String, BESBranchConf> branchConfCache =  this.getBranchConfCache();

        BESBranchConf branchConfModel = branchConfCache.get(key);

        if(null == branchConfModel)
        {
            return false;
        }

        branchConfCache.put(key, branchConf);

        return true;
    }

    public BESBranchConf getCachedElement(String key) throws CacheException
    {
        if (branchConfCache == null || branchConfCache.size() == 0 || key == null)
        {
            return null;
        }

        return branchConfCache.get(key);
    }

    public Cache<String, BESBranchConf> getBranchConfCache()
    {
        return branchConfCache;
    }

    public void setBranchConfCache(Cache<String, BESBranchConf> branchConfCache)
    {
        this.branchConfCache = branchConfCache;
    }

}
