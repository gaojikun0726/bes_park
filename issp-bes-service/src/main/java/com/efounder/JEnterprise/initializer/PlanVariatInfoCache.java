package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.core.common.constant.CacheNameCode;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BesplanConfigMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesTimeTask;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.PlanVariatInfoModel;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author sunzhiyuan
 * @Data 2021/1/26 9:50
 */
@Component
public class PlanVariatInfoCache implements CommandLineRunner {

    // shiro 缓存机制接口
    private Cache<String, PlanVariatInfoModel> planVariatInfoCache;

    @Resource
    private BesplanConfigMapper besplanConfigMapper;

    //private ComPlanVariatInfoHandler comPlanVariatInfoHandler =  ApplicationContextProvider.getBean(ComPlanVariatInfoHandler.class);

    @Override
    public void run(String... args) throws Exception {
        loadCache();
    }

    /**
     * 1. 从数据库中查询出全部”采集类型点位“数据。
     * 2. 获取一个缓存管理器”EhCacheManager“。
     * 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     * 4. 把“采集类型点位”数据对象循环添加到缓存对象。
     */
    public void loadCache(){
        // 1. 从数据库中查询出全部”采集类型点位“数据。
        List<Map<String,Object>> variatList = besplanConfigMapper.queryVariatTaskList();

        //planVariatInfoCache.clear();

        for (int i =0; i < variatList.size(); i++){

            PlanVariatInfoModel planVariatInfoModel = new PlanVariatInfoModel();

            Map<String,Object> variatMap = variatList.get(i);

            String planId = (String) variatList.get(i).get("f_pId");

            Map<String,Object> sceneIdAndModeId = besplanConfigMapper.selectModeIdAndSceneId(planId);

            if(sceneIdAndModeId!=null){

                String modeId = (String) sceneIdAndModeId.get("f_modeInfoId");

                planVariatInfoModel.setF_modeId(modeId);

                planVariatInfoModel.setF_taskId((String) variatList.get(i).get("f_id"));

                List<Map<String,Object>> pointList = besplanConfigMapper.selectPointInfomationByModeId(modeId);

                planVariatInfoModel.setF_timename((String) variatList.get(i).get("f_timename"));

                planVariatInfoModel.setF_specificvalue((String) variatList.get(i).get("f_specificvalue"));

                planVariatInfoModel.setF_planId(planId);

                String key = planId;

                planVariatInfoModel.setF_tasktype((String) variatList.get(i).get("f_tasktype"));

                planVariatInfoModel.setPointList(pointList);

                // 2. 获取一个缓存管理器”EhCacheManager“。
                EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

                // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
                planVariatInfoCache = ehCacheManager.getCache(CacheNameCode.PLAN_VARIAT_INFO_CACHE);
                planVariatInfoCache.clear();

                // 4. 把“根据变化量采集”数据对象循环添加到缓存对象。
                planVariatInfoCache.put(key,planVariatInfoModel);
            }

        }
    }

    /**
     * 添加一条数据到缓存中
     * @param besTimeTask
     * @return
     */
    public Boolean addOneVariatInfo(BesTimeTask besTimeTask){

        PlanVariatInfoModel planVariatInfoModel = new PlanVariatInfoModel();

        planVariatInfoModel.setF_taskId(besTimeTask.getF_id());

        planVariatInfoModel.setF_planId(besTimeTask.getF_pId());

        planVariatInfoModel.setF_tasktype(besTimeTask.getF_tasktype());

        planVariatInfoModel.setF_specificvalue(besTimeTask.getF_specificvalue());

        planVariatInfoModel.setF_timename(besTimeTask.getF_timename());

        planVariatInfoModel.setF_modeId(besTimeTask.getF_modeId());

        String planId = besTimeTask.getF_pId();

        String key = planId;

        //Map<String,Object> map = besplanConfigMapper.selectModeIdAndSceneId(planId);

        String modeId = besTimeTask.getF_modeId();

        if (modeId == null){

            return false;

        }else {

            List<Map<String,Object>> pointList = besplanConfigMapper.selectPointInfomationByModeId(modeId);

            planVariatInfoModel.setPointList(pointList);
        }

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        planVariatInfoCache = ehCacheManager.getCache(CacheNameCode.PLAN_VARIAT_INFO_CACHE);

        Cache<String, PlanVariatInfoModel> variatCache = this.getPlanVariatInfoCache();

        variatCache.put(key,planVariatInfoModel);

        return true;
    }

    /**
     * 删除一条缓存数据
     * @param key
     * @return
     */
    public Boolean deleteOneVariatInfoCatch(String key){

        if (null == key)
        {
            return false;
        }

        Cache<String, PlanVariatInfoModel> variatCache =  this.getPlanVariatInfoCache();

        PlanVariatInfoModel planVariatInfoModel = variatCache.get(key);

        if(null == planVariatInfoModel)
        {
            return false;
        }

        variatCache.remove(key);

        return true;
    }


    public PlanVariatInfoModel getCachedElement(String key) throws CacheException
    {
        if (planVariatInfoCache == null || planVariatInfoCache.size() == 0 || key == null)
        {
            return null;
        }

        return planVariatInfoCache.get(key);
    }


    public Cache<String, PlanVariatInfoModel> getPlanVariatInfoCache()
    {
        return planVariatInfoCache;
    }

    public void setPlanVariatInfoCache(Cache<String, PlanVariatInfoModel> planVariatInfoCache)
    {
        this.planVariatInfoCache = planVariatInfoCache;
    }

}



