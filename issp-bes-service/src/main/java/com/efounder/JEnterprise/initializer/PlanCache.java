package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.core.common.constant.CacheNameCode;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BesplanConfigMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesPlan;
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
 * 计划配置定义缓存
 * @author xiepufeng
 * @date 2020/6/20 10:21
 */
@Component
public class PlanCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<String, BesPlan> planCache;

    @Resource
    private BesplanConfigMapper besplanConfigMapper;

    @Override
    public void run(String... args) throws Exception
    {
        loadCache();
    }

    /**
     * 1. 从数据库中查询出全部”计划配置定义“数据。
     * 2. 获取一个缓存管理器”EhCacheManager“。
     * 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     * 4. 把“计划配置定义”数据对象循环添加到缓存对象。
     */
    public void loadCache()
    {
        // 1. 从数据库中查询出全部”计划配置定义“数据。
        List<BesPlan> planList = besplanConfigMapper.loadPlanAll();

        if (planList == null)
        {
            return;
        }

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        planCache = ehCacheManager.getCache(CacheNameCode.PLAN_CACHE);

        planCache.clear();

        // 4. 把“计划配置定义”数据对象循环添加到缓存对象。
        for (BesPlan besPlan : planList)
        {
            planCache.put(besPlan.getF_id(), besPlan);
        }
    }

    // 添加一条数据到缓存中
    public Boolean addOneplanCache(BesPlan plan)
    {

        if(null == plan)
        {
            return false;
        }

        String key = plan.getF_id();

        if(null == key)
        {
            return false;
        }

        Cache<String, BesPlan> planCache =  this.getplanCache();

        BesPlan aiPointModel = planCache.get(key);

        if(null != aiPointModel)
        {
            return false;
        }

        planCache.put(key, plan);

        return true;
    }

    // 删除缓存中的一条数据
    public Boolean deleteOneplanCache(String key)
    {
        if (null == key)
        {
            return false;
        }

        Cache<String, BesPlan> planCache =  this.getplanCache();

        BesPlan besPlan = planCache.get(key);

        if(null == besPlan)
        {
            return false;
        }

        planCache.remove(key);

        return true;
    }

    // 更新更新缓存中的一条数据
    /**
     *
     * @Description: 根据计划id更改计划启用状态
     *
     * @auther: wanghongjie
     * @date: 9:28 2021/8/20
     * @param: [plan]
     * @return: java.lang.Boolean
     *
     */
    public Boolean updateOneplanByPlanIdCache(String planId,String f_invoke)
    {

        if(planCache == null || planCache.size() == 0 || null == planId || f_invoke == null)
        {
            return false;
        }
        Collection<BesPlan> BesPlans = planCache.values();

        if (null == BesPlans || BesPlans.isEmpty())
        {
            return false;
        }

        for (BesPlan BesPlan : BesPlans) {
            if (planId.equals(BesPlan.getF_id()))
            {
                BesPlan besPlan = planCache.get(planId);
                besPlan.setF_invoke(f_invoke);
                planCache.put(planId, besPlan);
            }
        }
        return true;
    }

    /**
     *
     * @Description: 根据计划id更改计划信息
     *
     * @auther: wanghongjie
     * @date: 9:28 2021/8/20
     * @param: [plan]
     * @return: java.lang.Boolean
     *
     */
    public Boolean updateOneplanByIdCache(BesPlan plan)
    {

        if(planCache == null || planCache.size() == 0 || null == plan)
        {
            return false;
        }
        Collection<BesPlan> BesPlans = planCache.values();

        if (null == BesPlans || BesPlans.isEmpty())
        {
            return false;
        }

        for (BesPlan BesPlan : BesPlans) {
            if (plan.getF_id().equals(BesPlan.getF_id()))
            {
                BesPlan besPlan = planCache.get(plan.getF_id());
                besPlan.setF_sysname(plan.getF_sysname());
                besPlan.setF_nickname(plan.getF_nickname());
                besPlan.setF_sceneInfoId(plan.getF_sceneInfoId());
                besPlan.setF_scenetype(plan.getF_scenetype());
                besPlan.setF_modeInfoId(plan.getF_modeInfoId());
                besPlan.setF_active(plan.getF_active());
                besPlan.setF_planId(plan.getF_planId());
                planCache.put(plan.getF_id(), besPlan);
            }
        }
        return true;
    }

    public BesPlan getCachedElement(String key) throws CacheException
    {
        if (planCache == null || planCache.size() == 0 || key == null)
        {
            return null;
        }

        return planCache.get(key);
    }

    public Boolean updateOneplanCache(BesPlan plan)
    {

        if(null == plan)
        {
            return false;
        }

        String key = plan.getF_id();

        if(null == key)
        {
            return false;
        }

        Cache<String, BesPlan> planCache =  this.getplanCache();

        BesPlan besPlan = planCache.get(key);

        if(null == besPlan)
        {
            return false;
        }

        planCache.put(key, plan);

        return true;
    }

    /**
     *
     * @Description: 根据模式id查询所有的计划
     *
     * @auther: wanghongjie
     * @date: 8:50 2021/8/17
     * @param: [modeId]
     * @return: java.util.List<com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesPlan>
     *
     */
    public List<BesPlan> selectPlanByModeIdCache(String modeId) {
        List<BesPlan> besPlanList = new ArrayList<>();
        if (planCache == null || planCache.size() == 0 || modeId == null)
        {
            return null;
        }
        Collection<BesPlan> BesPlans = planCache.values();

        if (null == BesPlans || BesPlans.isEmpty())
        {
            return null;
        }

        for (BesPlan BesPlan : BesPlans) {
            if (modeId.equals(BesPlan.getF_modeInfoId()))
            {
                besPlanList.add(BesPlan);
            }
        }

        if (besPlanList.size() > 0) {
            return besPlanList;
        }else {
            return null;
        }
    }

    /**
     *
     * @Description: 根据场景id查询所有的计划
     *
     * @auther: wanghongjie
     * @date: 8:50 2021/8/17
     * @param: [modeId]
     * @return: java.util.List<com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesPlan>
     *
     */
    public List<BesPlan> selectPlanBySceneIdCache(String sceneId) {
        List<BesPlan> besPlanList = new ArrayList<>();
        if (planCache == null || planCache.size() == 0 || sceneId == null)
        {
            return null;
        }
        Collection<BesPlan> BesPlans = planCache.values();

        if (null == BesPlans || BesPlans.isEmpty())
        {
            return null;
        }

        for (BesPlan BesPlan : BesPlans) {
            if (sceneId.equals(BesPlan.getF_sceneInfoId()))
            {
                besPlanList.add(BesPlan);
            }
        }

        if (besPlanList.size() > 0) {
            return besPlanList;
        }else {
            return null;
        }
    }

    public List<BesPlan> getCachedElementByModeId(String modeId) throws CacheException
    {
        List<BesPlan> besPlanList = new ArrayList<>();

        if (planCache == null || planCache.size() == 0 || modeId == null)
        {
            return null;
        }

        Collection<BesPlan> BesPlans = planCache.values();

        if (null == BesPlans || BesPlans.isEmpty())
        {
            return null;
        }

        for (BesPlan BesPlan : BesPlans) {
            if (modeId.equals(BesPlan.getF_modeInfoId()))
            {
                besPlanList.add(BesPlan);
            }
        }

        if (besPlanList.size() > 0) {
            return besPlanList;
        }else {
            return null;
        }

    }


    public Cache<String, BesPlan> getplanCache()
    {
        return planCache;
    }

    public void setplanCache(Cache<String, BesPlan> planCache)
    {
        this.planCache = planCache;
    }


}
