package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.core.common.constant.CacheNameCode;
import com.efounder.JEnterprise.mapper.basedatamanage.enegrycollectionmanage.BESElectric_Coll_RlglMapper;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESElectric_Coll_Rlgl;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * 采集方案采集参数关联定义缓存
 * @author xiepufeng
 * @date 2020/6/20 10:21
 */
@Component
public class ElectricCollRlglCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<String, BESElectric_Coll_Rlgl> electricCollRlglCache;

    @Resource
    private BESElectric_Coll_RlglMapper besElectricCollRlglMapper;

    @Override
    public void run(String... args) throws Exception
    {
        loadCache();
    }

    /**
     * 1. 从数据库中查询出全部”采集方案采集参数关联定义“数据。
     * 2. 获取一个缓存管理器”EhCacheManager“。
     * 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     * 4. 把“采集方案采集参数关联定义”数据对象循环添加到缓存对象。
     */
    public void loadCache()
    {
        // 1. 从数据库中查询出全部”采集方案采集参数关联定义“数据。
        List<BESElectric_Coll_Rlgl> electricCollRlglList = besElectricCollRlglMapper.loadAll();

        if (electricCollRlglList == null)
        {
            return;
        }

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        electricCollRlglCache = ehCacheManager.getCache(CacheNameCode.ELECTRIC_COLL_RLGL);

        electricCollRlglCache.clear();

        // 4. 把“采集方案采集参数关联定义”数据对象循环添加到缓存对象。
        for (BESElectric_Coll_Rlgl electricCollRlgl : electricCollRlglList)
        {
            electricCollRlglCache.put(electricCollRlgl.getfCjfabh() + electricCollRlgl.getfNhbh(), electricCollRlgl);
        }
    }

    // 添加一条数据到缓存中
    public Boolean addOneCollMethodCache(BESElectric_Coll_Rlgl electricCollRlgl)
    {

        if(null == electricCollRlgl)
        {
            return false;
        }

        String cjfabh = electricCollRlgl.getfCjfabh();
        String nhbh = electricCollRlgl.getfNhbh();

        if(null == cjfabh || nhbh == null)
        {
            return false;
        }

        String key = cjfabh + nhbh;

        Cache<String, BESElectric_Coll_Rlgl> electricCollRlglCache =  this.getCollMethodCache();

        BESElectric_Coll_Rlgl electricCollRlglModel = electricCollRlglCache.get(key);

        if(null != electricCollRlglModel)
        {
            return false;
        }

        electricCollRlglCache.put(key, electricCollRlgl);

        return true;
    }

    // 更新更新缓存中的一条数据
    public Boolean updateOneAmmeterCache(BESElectric_Coll_Rlgl electricCollRlgl)
    {

        if(null == electricCollRlgl)
        {
            return false;
        }

        String cjfabh = electricCollRlgl.getfCjfabh();
        String nhbh = electricCollRlgl.getfNhbh();

        if(null == cjfabh || nhbh == null)
        {
            return false;
        }

        String key = cjfabh + nhbh;

        BESElectric_Coll_Rlgl besElectricCollRlgl = electricCollRlglCache.get(key);

        if(null == besElectricCollRlgl)
        {
            return false;
        }

        electricCollRlglCache.put(key, electricCollRlgl);

        return true;
    }

    // 删除缓存中的一条数据
    public Boolean deleteOneCollMethodCache(String cjfabh, String nhbh)
    {
        if (null == cjfabh || null == nhbh)
        {
            return false;
        }

        String key = cjfabh + nhbh;

        Cache<String, BESElectric_Coll_Rlgl> electricCollRlglCache =  this.getCollMethodCache();

        BESElectric_Coll_Rlgl electricCollRlglModel = electricCollRlglCache.get(key);

        if(null == electricCollRlglModel)
        {
            return false;
        }

        electricCollRlglCache.remove(key);

        return true;
    }

    // 删除缓存根据采集方案编号
    public Boolean deleteCollMethodCacheByCjfabh(String cjfabh)
    {
        if (null == cjfabh)
        {
            return false;
        }

        Collection<BESElectric_Coll_Rlgl> besElectricCollRlgls = electricCollRlglCache.values();

        if(null == besElectricCollRlgls || besElectricCollRlgls.isEmpty())
        {
            return false;
        }

        besElectricCollRlgls.forEach(item -> {
            if (cjfabh.equals(item.getfCjfabh()))
            {
                electricCollRlglCache.remove(item.getfCjfabh() + item.getfNhbh());
            }
        });

        return true;
    }


    public BESElectric_Coll_Rlgl getCachedElement(String key) throws CacheException
    {
        if (electricCollRlglCache == null || electricCollRlglCache.size() == 0 || key == null)
        {
            return null;
        }

        return electricCollRlglCache.get(key);
    }

    public List<BESElectric_Coll_Rlgl> getCachedElementByCjfabh(String cjfabh) {

        if (cjfabh == null)
        {
            return null;
        }

        if (electricCollRlglCache == null || electricCollRlglCache.size() == 0)
        {
            return null;
        }

        Collection<BESElectric_Coll_Rlgl> besElectricCollRlglList = electricCollRlglCache.values();

        if(null == besElectricCollRlglList || besElectricCollRlglList.isEmpty())
        {
            return null;
        }

        List<BESElectric_Coll_Rlgl> besElectricCollRlgls = new ArrayList<>();

        besElectricCollRlglList.forEach(item -> {
            if (cjfabh.equals(item.getfCjfabh()))
            {
                besElectricCollRlgls.add(item);
            }
        });

        // 排序根据电能编号
        besElectricCollRlgls.sort(Comparator.comparingInt(o -> Integer.parseInt(o.getfNhbh())));

        return besElectricCollRlgls;
    }


    public Cache<String, BESElectric_Coll_Rlgl> getCollMethodCache()
    {
        return electricCollRlglCache;
    }

    public void setCollMethodCache(Cache<String, BESElectric_Coll_Rlgl> electricCollRlglCache)
    {
        this.electricCollRlglCache = electricCollRlglCache;
    }

}
