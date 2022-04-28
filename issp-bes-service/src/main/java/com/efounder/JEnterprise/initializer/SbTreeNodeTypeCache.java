package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.core.common.constant.CacheNameCode;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BesSbtreeNodetypeMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESSbTreeNodeType;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 设备树节点类型定义缓存
 * @author xiepufeng
 * @date 2020/6/20 10:21
 */
@Component
public class SbTreeNodeTypeCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<String, BESSbTreeNodeType> sbTreeNodeTypeCache;

    @Resource
    private BesSbtreeNodetypeMapper besSbtreeNodetypeMapper;

    @Override
    public void run(String... args) throws Exception
    {
        loadCache();
    }

    /**
     * 1. 从数据库中查询出全部”设备树节点类型定义“数据。
     * 2. 获取一个缓存管理器”EhCacheManager“。
     * 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     * 4. 把“设备树节点类型定义”数据对象循环添加到缓存对象。
     */
    public void loadCache()
    {
        // 1. 从数据库中查询出全部”设备树节点类型定义“数据。
        List<BESSbTreeNodeType> sbTreeNodeTypeList = besSbtreeNodetypeMapper.findAll();

        if (sbTreeNodeTypeList == null)
        {
            return;
        }

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        sbTreeNodeTypeCache = ehCacheManager.getCache(CacheNameCode.SB_TREE_NODE_TYPE_CACHE);

        sbTreeNodeTypeCache.clear();

        // 4. 把“设备树节点类型定义”数据对象循环添加到缓存对象。
        sbTreeNodeTypeList.forEach(sbTreeNodeType -> {
            sbTreeNodeTypeCache.put(sbTreeNodeType.getF_node_type(), sbTreeNodeType);
        });

    }

    // 添加一条数据到缓存中
    public Boolean addOneSbTreeNodeTypeCache(BESSbTreeNodeType sbTreeNodeType)
    {

        if(null == sbTreeNodeType)
        {
            return false;
        }

        String key = sbTreeNodeType.getF_node_type();

        if(null == key)
        {
            return false;
        }

        Cache<String, BESSbTreeNodeType> sbTreeNodeTypeCache =  this.getSbTreeNodeTypeCache();

        BESSbTreeNodeType sbTreeNodeTypeModel = sbTreeNodeTypeCache.get(key);

        if(null != sbTreeNodeTypeModel)
        {
            return false;
        }

        sbTreeNodeTypeCache.put(key, sbTreeNodeType);

        return true;
    }

    // 删除缓存中的一条数据
    public Boolean deleteOneSbTreeNodeTypeCache(String key)
    {
        if (null == key)
        {
            return false;
        }

        Cache<String, BESSbTreeNodeType> sbTreeNodeTypeCache =  this.getSbTreeNodeTypeCache();

        BESSbTreeNodeType sbTreeNodeTypeModel = sbTreeNodeTypeCache.get(key);

        if(null == sbTreeNodeTypeModel)
        {
            return false;
        }

        sbTreeNodeTypeCache.remove(key);

        return true;
    }

    // 更新更新缓存中的一条数据
    public Boolean updateOneSbTreeNodeTypeCache(BESSbTreeNodeType sbTreeNodeType)
    {

        if(null == sbTreeNodeType)
        {
            return false;
        }

        String key = sbTreeNodeType.getF_node_type();

        if(null == key)
        {
            return false;
        }

        Cache<String, BESSbTreeNodeType> sbTreeNodeTypeCache =  this.getSbTreeNodeTypeCache();

        BESSbTreeNodeType sbTreeNodeTypeModel = sbTreeNodeTypeCache.get(key);

        if(null == sbTreeNodeTypeModel)
        {
            return false;
        }

        sbTreeNodeTypeCache.put(key, sbTreeNodeType);

        return true;
    }

    public BESSbTreeNodeType getCachedElement(String key) throws CacheException
    {
        if (sbTreeNodeTypeCache == null || sbTreeNodeTypeCache.size() == 0 || key == null)
        {
            return null;
        }

        return sbTreeNodeTypeCache.get(key);
    }

    public Cache<String, BESSbTreeNodeType> getSbTreeNodeTypeCache()
    {
        return sbTreeNodeTypeCache;
    }

    public void setSbTreeNodeTypeCache(Cache<String, BESSbTreeNodeType> sbTreeNodeTypeCache)
    {
        this.sbTreeNodeTypeCache = sbTreeNodeTypeCache;
    }

}
