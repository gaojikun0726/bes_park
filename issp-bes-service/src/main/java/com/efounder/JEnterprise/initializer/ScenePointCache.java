package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.core.common.constant.CacheNameCode;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BesceneConfigMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BeScenePoint;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BeScenePoint;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 模式点位定义缓存
 * @author wanghongjie
 * @date 2021/08/09 10:21
 */
@Component
public class ScenePointCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<String, BeScenePoint> scenePointCache;

    @Autowired
    private BesceneConfigMapper besceneConfigMapper;

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
        // 1. 从数据库中查询出全部”点位关联模式定义“数据。
        List<BeScenePoint> scenePointList = besceneConfigMapper.loadScenePointAll();

        if (scenePointList == null)
        {
            return;
        }

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        scenePointCache = ehCacheManager.getCache(CacheNameCode.SCENE_POINT_CACHE);

        scenePointCache.clear();

        // 4. 把“Ao配置定义”数据对象循环添加到缓存对象。
        for (BeScenePoint scenePoint : scenePointList)
        {
            scenePointCache.put(String.valueOf(scenePoint.getF_id()), scenePoint);
        }
    }

    // 添加一条数据到缓存中
    public Boolean addOneScenePointCache(BeScenePoint BeScenePoint)
    {

        if(null == BeScenePoint)
        {
            return false;
        }

        String key = String.valueOf(BeScenePoint.getF_id());

        if(null == key)
        {
            return false;
        }

        Cache<String, BeScenePoint> scenePointCache =  this.getscenePointCache();

        BeScenePoint sceneMode = scenePointCache.get(key);

        if(null != sceneMode)
        {
            return false;
        }

        scenePointCache.put(key, BeScenePoint);

        return true;
    }

    // 删除缓存中的一条数据
    public Boolean deleteOneScenePointCache(String key)
    {
        if (null == key)
        {
            return false;
        }

        Cache<String, BeScenePoint> scenePointCache =  this.getscenePointCache();

        BeScenePoint BeScenePoint = scenePointCache.get(key);

        if(null == BeScenePoint)
        {
            return false;
        }

        scenePointCache.remove(key);

        return true;
    }

    // 更新更新缓存中的一条数据
    public Boolean updateOneScenePointCache(BeScenePoint BeScenePoint)
    {

        if(null == BeScenePoint)
        {
            return false;
        }

        String key = String.valueOf(BeScenePoint.getF_id());

        if(null == key)
        {
            return false;
        }

        Cache<String, BeScenePoint> scenePointCache =  this.getscenePointCache();

        BeScenePoint scenePoint = scenePointCache.get(key);

        if(null == scenePoint)
        {
            return false;
        }

        scenePointCache.put(key, BeScenePoint);

        return true;
    }

    // 更新更新缓存中的一条数据
    public Boolean updateOneScenePointValueCache(Map<String,Object> BeScenePointValue)
    {
        if(null == BeScenePointValue)
        {
            return false;
        }

        String key = String.valueOf(BeScenePointValue.get("f_id"));

        if(null == key)
        {
            return false;
        }

        Cache<String, BeScenePoint> scenePointCache =  this.getscenePointCache();

        BeScenePoint scenePoint = scenePointCache.get(key);

        if(null == scenePoint)
        {
            return false;
        }
        scenePoint.setF_value((String) BeScenePointValue.get("f_value"));
        scenePoint.setF_unit((String) BeScenePointValue.get("f_unit"));

        scenePointCache.put(key, scenePoint);

        return true;
    }

    public BeScenePoint getCachedElement(String key) throws CacheException
    {
        if (scenePointCache == null || scenePointCache.size() == 0 || key == null)
        {
            return null;
        }

        return scenePointCache.get(key);
    }

    /**
     *
     * @Description: 根据场景id查询模式关联的点位
     *
     * @auther: wanghongjie
     * @date: 14:57 2021/8/9
     * @param: [sceneInfoId]
     * @return: com.efounder.JEnterprise.model.basedatamanage.eqmanage.BeScenePoint
     *
     */
    public List<BeScenePoint> getCachedElementBySceneInfoId(String sceneInfoId) throws CacheException
    {
        List<BeScenePoint> sceneModeList = new ArrayList<>();
        if (scenePointCache == null || scenePointCache.size() == 0 || sceneInfoId == null)
        {
            return null;
        }

        Collection<BeScenePoint> sceneModes = scenePointCache.values();

        if (null == sceneModes || sceneModes.isEmpty())
        {
            return null;
        }

        for (BeScenePoint sceneMode : sceneModes) {
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

    /**
     *
     * @Description: 根据模式id查询模式关联的点位
     *
     * @auther: wanghongjie
     * @date: 14:57 2021/8/9
     * @param: [sceneInfoId]
     * @return: com.efounder.JEnterprise.model.basedatamanage.eqmanage.BeScenePoint
     *
     */
    public List<BeScenePoint> getCachedElementByScenemodeId(String scenemodeId) throws CacheException
    {
        List<BeScenePoint> sceneModeList = new ArrayList<>();
        if (scenePointCache == null || scenePointCache.size() == 0 || scenemodeId == null)
        {
            return null;
        }

        Collection<BeScenePoint> beScenePoints = scenePointCache.values();

        if (null == beScenePoints || beScenePoints.isEmpty())
        {
            return null;
        }

        for (BeScenePoint beScenePoint : beScenePoints) {
            if (scenemodeId.equals(beScenePoint.getF_scenemodeId()))
            {
                sceneModeList.add(beScenePoint);

            }
        }
        if (sceneModeList.size() > 0) {
            return sceneModeList;
        }else {
            return null;
        }
    }

    /**
     *
     * @Description: 根据点位id查询模式关联的点位
     *
     * @auther: wanghongjie
     * @date: 14:57 2021/8/9
     * @param: [sceneInfoId]
     * @return: com.efounder.JEnterprise.model.basedatamanage.eqmanage.BeScenePoint
     *
     */
    public List<BeScenePoint> getCachedElementByPointId(String pointId) throws CacheException
    {
        List<BeScenePoint> sceneModeList = new ArrayList<>();
        if (scenePointCache == null || scenePointCache.size() == 0 || pointId == null)
        {
            return null;
        }

        Collection<BeScenePoint> sceneModes = scenePointCache.values();

        if (null == sceneModes || sceneModes.isEmpty())
        {
            return null;
        }

        for (BeScenePoint sceneMode : sceneModes) {
            if (pointId.equals(sceneMode.getF_pointId()))
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


    public Cache<String, BeScenePoint> getscenePointCache()
    {
        return scenePointCache;
    }

    public void setscenePointCache(Cache<String, BeScenePoint> scenePointCache)
    {
        this.scenePointCache = scenePointCache;
    }

}
