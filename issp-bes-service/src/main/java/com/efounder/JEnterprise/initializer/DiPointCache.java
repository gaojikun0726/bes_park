package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.core.common.constant.CacheNameCode;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BesAoPointMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BesDiPointMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESSbPzStruct;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesAiPoint;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesAoPoint;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesDiPoint;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * DI配置定义缓存
 * @author xiepufeng
 * @date 2020/6/20 10:21
 */
@Component
public class DiPointCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<String, BesDiPoint> diPointCache;

    @Resource
    private BesDiPointMapper besDiPointMapper;

    @Override
    public void run(String... args) throws Exception
    {
        loadCache();
    }

    /**
     * 1. 从数据库中查询出全部”AI配置定义“数据。
     * 2. 获取一个缓存管理器”EhCacheManager“。
     * 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     * 4. 把“AI配置定义”数据对象循环添加到缓存对象。
     */
    public void loadCache()
    {
        // 1. 从数据库中查询出全部”Ao配置定义“数据。
        List<BesDiPoint> diPointList = besDiPointMapper.loadAll();

        if (diPointList == null)
        {
            return;
        }

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        diPointCache = ehCacheManager.getCache(CacheNameCode.DI_POINT_CACHE);

        diPointCache.clear();

        // 4. 把“Ao配置定义”数据对象循环添加到缓存对象。
        for (BesDiPoint diPoint : diPointList)
        {
            diPointCache.put(diPoint.getfSysName(), diPoint);
        }
    }

    // 添加一条数据到缓存中
    public Boolean addOneDiPointCache(BesDiPoint diPoint)
    {

        if(null == diPoint)
        {
            return false;
        }

        String key = diPoint.getfSysName();

        if(null == key)
        {
            return false;
        }

        Cache<String, BesDiPoint> diPointCache =  this.getDiPointCache();

        BesDiPoint diPointModel = diPointCache.get(key);

        if(null != diPointModel)
        {
            return false;
        }

        diPointCache.put(key, diPoint);

        return true;
    }

    // 删除缓存中的一条数据
    public Boolean deleteOneDiPointCache(String key)
    {
        if (null == key)
        {
            return false;
        }

        Cache<String, BesDiPoint> diPointCache =  this.getDiPointCache();

        BesDiPoint diPointModel = diPointCache.get(key);

        if(null == diPointModel)
        {
            return false;
        }

        diPointCache.remove(key);

        return true;
    }

    // 更新更新缓存中的一条数据
    public Boolean updateOneDiPointCache(BesDiPoint diPoint)
    {

        if(null == diPoint)
        {
            return false;
        }

        String key = diPoint.getfSysName();

        if(null == key)
        {
            return false;
        }

        Cache<String, BesDiPoint> diPointCache =  this.getDiPointCache();

        BesDiPoint diPointModel = diPointCache.get(key);

        if(null == diPointModel)
        {
            return false;
        }

        diPointCache.put(key, diPoint);

        return true;
    }

    public BesDiPoint getCachedElement(String key) throws CacheException
    {
        if (diPointCache == null || diPointCache.size() == 0 || key == null)
        {
            return null;
        }

        return diPointCache.get(key);
    }

    public BesDiPoint getCachedElementBySbId(String sbId) throws CacheException
    {
        if (diPointCache == null || diPointCache.size() == 0 || sbId == null)
        {
            return null;
        }

        Collection<BesDiPoint> besDiPoints = diPointCache.values();

        if (null == besDiPoints || besDiPoints.isEmpty())
        {
            return null;
        }

        for (BesDiPoint besDiPoint : besDiPoints) {
            if (sbId.equals(besDiPoint.getfStructId()))
            {
                return besDiPoint;
            }
        }

        return null;

    }

    public BesDiPoint getCachedElementBySysNameOld(String sysNameOld) throws CacheException
    {
        if (diPointCache == null || diPointCache.size() == 0 || sysNameOld == null)
        {
            return null;
        }

        Collection<BesDiPoint> besDiPoints = diPointCache.values();

        if (null == besDiPoints || besDiPoints.isEmpty())
        {
            return null;
        }

        for (BesDiPoint besDiPoint : besDiPoints) {
            if (sysNameOld.equals(besDiPoint.getfSysNameOld()))
            {
                return besDiPoint;
            }
        }

        return null;

    }


    public Cache<String, BesDiPoint> getDiPointCache()
    {
        return diPointCache;
    }

    public void setDiPointCache(Cache<String, BesDiPoint> diPointCache)
    {
        this.diPointCache = diPointCache;
    }

}
