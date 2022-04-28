package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.core.common.constant.CacheNameCode;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BesplanConfigMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesPlan;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesTimeTask;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 计划配置公式定义缓存
 * @author xiepufeng
 * @date 2020/6/20 10:21
 */
@Component
public class TimeTaskCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<String, BesTimeTask> timeTaskCache;

    @Resource
    private BesplanConfigMapper besplanConfigMapper;

    @Override
    public void run(String... args) throws Exception
    {
        loadCache();
    }

    /**
     * 1. 从数据库中查询出全部”计划配置公式定义“数据。
     * 2. 获取一个缓存管理器”EhCacheManager“。
     * 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     * 4. 把“计划配置定义”数据对象循环添加到缓存对象。
     */
    public void loadCache()
    {
        // 1. 从数据库中查询出全部”计划配置公式定义“数据。
        List<BesTimeTask> planList = besplanConfigMapper.loadTimeTaskAll();

        if (planList == null)
        {
            return;
        }

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        timeTaskCache = ehCacheManager.getCache(CacheNameCode.TIME_TASK_CACHE);

        timeTaskCache.clear();

        // 4. 把“计划配置定义”数据对象循环添加到缓存对象。
        for (BesTimeTask besTimeTask : planList)
        {
            timeTaskCache.put(besTimeTask.getF_id(), besTimeTask);
        }
    }

    // 添加一条数据到缓存中
    public Boolean addOnetimeTaskCache(BesTimeTask besTimeTask)
    {

        if(null == besTimeTask)
        {
            return false;
        }

        String key = besTimeTask.getF_id();

        if(null == key)
        {
            return false;
        }

        Cache<String, BesTimeTask> timeTaskCache =  this.gettimeTaskCache();

        BesTimeTask timeTask = timeTaskCache.get(key);

        if(null != timeTask)
        {
            return false;
        }

        timeTaskCache.put(key, besTimeTask);

        return true;
    }

    // 删除缓存中的一条数据
    public Boolean deleteOnetimeTaskCache(String key)
    {
        if (null == key)
        {
            return false;
        }

        Cache<String, BesTimeTask> timeTaskCache =  this.gettimeTaskCache();

        BesTimeTask besTimeTask = timeTaskCache.get(key);

        if(null == besTimeTask)
        {
            return false;
        }

        timeTaskCache.remove(key);

        return true;
    }
    // 删除缓存中的一条数据
    public Boolean deleteOnetimeTaskCacheByPId(String pId)
    {
        if (timeTaskCache == null || timeTaskCache.size() == 0 || pId == null)
        {
            return false;
        }
        Collection<BesTimeTask> besTimeTasks = timeTaskCache.values();

        if (null == besTimeTasks || besTimeTasks.isEmpty())
        {
            return false;
        }

        for (BesTimeTask timeTask : besTimeTasks) {
            if (pId.equals(timeTask.getF_pId()))
            {
                timeTaskCache.remove(timeTask.getF_id());
            }
        }

        return true;
    }

    // 更新更新缓存中的一条数据
    public Boolean updateOnetimeTaskCache(BesTimeTask besTimeTask)
    {

        if (timeTaskCache == null || timeTaskCache.size() == 0 || besTimeTask == null)
        {
            return false;
        }

        String pId = besTimeTask.getF_pId();

        Collection<BesTimeTask> besTimeTasks = timeTaskCache.values();

        if (null == besTimeTasks || besTimeTasks.isEmpty())
        {
            return false;
        }

        for (BesTimeTask timeTask : besTimeTasks) {
            if (pId.equals(timeTask.getF_pId()))
            {
                besTimeTask.setF_id(timeTask.getF_id());
                timeTaskCache.put(timeTask.getF_id(), besTimeTask);
            }
        }

        return true;

    }

    public BesTimeTask getCachedElement(String key) throws CacheException
    {
        if (timeTaskCache == null || timeTaskCache.size() == 0 || key == null)
        {
            return null;
        }

        return timeTaskCache.get(key);
    }

    /**
     *
     * @Description: 根据模式id查询所有的计划公式
     *
     * @auther: wanghongjie
     * @date: 8:50 2021/8/17
     * @param: [modeId]
     * @return: java.util.List<com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesTimeTask>
     *
     */
    public List<BesTimeTask> selectPlanByModeIdCache(String modeId) {
        List<BesTimeTask> besTimeTaskList = new ArrayList<>();
        if (timeTaskCache == null || timeTaskCache.size() == 0 || modeId == null)
        {
            return null;
        }
        Collection<BesTimeTask> besTimeTasks = timeTaskCache.values();

        if (null == besTimeTasks || besTimeTasks.isEmpty())
        {
            return null;
        }

        for (BesTimeTask timeTask : besTimeTasks) {
            if (modeId.equals(timeTask.getF_modeId()))
            {
                besTimeTaskList.add(timeTask);
            }
        }

        if (besTimeTaskList.size() > 0) {
            return besTimeTaskList;
        }else {
            return null;
        }
    }

    public BesTimeTask getCachedElementByPId(String pId) throws CacheException
    {
        if (timeTaskCache == null || timeTaskCache.size() == 0 || pId == null)
        {
            return null;
        }

        Collection<BesTimeTask> besTimeTasks = timeTaskCache.values();

        if (null == besTimeTasks || besTimeTasks.isEmpty())
        {
            return null;
        }

        for (BesTimeTask timeTask : besTimeTasks) {
            if (pId.equals(timeTask.getF_pId()))
            {
                return timeTask;
            }
        }

        return null;

    }


    public Cache<String, BesTimeTask> gettimeTaskCache()
    {
        return timeTaskCache;
    }

    public void settimeTaskCache(Cache<String, BesTimeTask> timeTaskCache)
    {
        this.timeTaskCache = timeTaskCache;
    }


}
