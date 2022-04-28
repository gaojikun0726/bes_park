package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.core.common.constant.CacheNameCode;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BesDiPointMapper;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BesceneConfigMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BeSceneMode;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesDiPoint;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 场景模式定义缓存
 * @author wanghongjie
 * @date 2021/08/09 10:21
 */
@Component
public class SceneModeCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<String, BeSceneMode> sceneModeCache;

    @Autowired
    private BesceneConfigMapper besceneConfigMapper;

    @Override
    public void run(String... args) throws Exception
    {
        loadCache();
    }

    /**
     * 1. 从数据库中查询出全部”模式配置定义“数据。
     * 2. 获取一个缓存管理器”EhCacheManager“。
     * 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     * 4. 把“模式配置定义”数据对象循环添加到缓存对象。
     */
    public void loadCache()
    {
        // 1. 从数据库中查询出全部”场景模式定义“数据。
        List<BeSceneMode> sceneModeList = besceneConfigMapper.loadSceneModeAll();

        if (sceneModeList == null)
        {
            return;
        }

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        sceneModeCache = ehCacheManager.getCache(CacheNameCode.SCENE_MODE_CACHE);

        sceneModeCache.clear();

        // 4. 把“模式配置定义”数据对象循环添加到缓存对象。
        for (BeSceneMode sceneMode : sceneModeList)
        {
            sceneModeCache.put(sceneMode.getF_id(), sceneMode);
        }
    }

    // 添加一条数据到缓存中
    public Boolean addOneSceneModeCache(BeSceneMode beSceneMode)
    {

        if(null == beSceneMode)
        {
            return false;
        }

        String key = beSceneMode.getF_id();

        if(null == key)
        {
            return false;
        }

        Cache<String, BeSceneMode> sceneModeCache =  this.getSceneModeCache();

        BeSceneMode sceneMode = sceneModeCache.get(key);

        if(null != sceneMode)
        {
            return false;
        }

        sceneModeCache.put(key, beSceneMode);

        return true;
    }

    // 删除缓存中的一条数据
    public Boolean deleteOneSceneModeCache(String key)
    {
        if (null == key)
        {
            return false;
        }

        Cache<String, BeSceneMode> sceneModeCache =  this.getSceneModeCache();

        BeSceneMode beSceneMode = sceneModeCache.get(key);

        if(null == beSceneMode)
        {
            return false;
        }

        sceneModeCache.remove(key);

        return true;
    }

    // 更新更新缓存中的一条数据
    public Boolean updateOneSceneModeCache(BeSceneMode beSceneMode)
    {

        if(null == beSceneMode)
        {
            return false;
        }

        String key = beSceneMode.getF_id();

        if(null == key)
        {
            return false;
        }

        Cache<String, BeSceneMode> sceneModeCache =  this.getSceneModeCache();

        BeSceneMode sceneMode = sceneModeCache.get(key);

        if(null == sceneMode)
        {
            return false;
        }

        sceneModeCache.put(key, beSceneMode);

        return true;
    }

    public BeSceneMode getCachedElement(String key) throws CacheException
    {
        if (sceneModeCache == null || sceneModeCache.size() == 0 || key == null)
        {
            return null;
        }

        return sceneModeCache.get(key);
    }

    /**
     *
     * @Description: 根据场景id查询模式
     *
     * @auther: wanghongjie
     * @date: 14:57 2021/8/9
     * @param: [sceneInfoId]
     * @return: com.efounder.JEnterprise.model.basedatamanage.eqmanage.BeSceneMode
     *
     */
    public List<BeSceneMode> getCachedElementById(String sceneInfoId) throws CacheException
    {
        List<BeSceneMode> sceneModeList = new ArrayList<>();
        if (sceneModeCache == null || sceneModeCache.size() == 0 || sceneInfoId == null)
        {
            return null;
        }

        Collection<BeSceneMode> sceneModes = sceneModeCache.values();

        if (null == sceneModes || sceneModes.isEmpty())
        {
            return null;
        }

        for (BeSceneMode sceneMode : sceneModes) {
            if (sceneInfoId.equals(sceneMode.getF_sceneInfoId()))
            {
                sceneModeList.add(sceneMode);

            }
        }
        if (sceneModeList.size() > 0) {
            return sceneModeList;
        }else {
            return null;
        }
    }


    public Cache<String, BeSceneMode> getSceneModeCache()
    {
        return sceneModeCache;
    }

    public void setSceneModeCache(Cache<String, BeSceneMode> sceneModeCache)
    {
        this.sceneModeCache = sceneModeCache;
    }

}
