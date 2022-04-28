package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.core.common.constant.CacheNameCode;
import com.efounder.JEnterprise.mapper.collectorJob.BESSubAlarmMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesAoPoint;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesCollector;
import com.efounder.JEnterprise.model.dataAnalysises.BesBranchConfAlarmConfiguration;
import com.efounder.JEnterprise.model.dataAnalysises.BesBranchConfParameter;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author: wanghongjie
 * @Description:
 * @Date: Created in 16:21 2021/6/3
 * @Modified By:支路报警规则
 */
@Component
public class BranchConfParameterCache implements CommandLineRunner {

    // shiro 缓存机制接口
    private Cache<String, List<BesBranchConfParameter>> branchConfParameterCache;

    @Resource
    private BESSubAlarmMapper besSubAlarmMapper;
    @Override
    public void run(String... args) throws Exception {
        loadCache();
    }

    /**
     * 1. 从数据库中查询出全部”支路报警规则“数据。
     * 2. 获取一个缓存管理器”EhCacheManager“。
     * 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     * 4. 把“支路报警规则”数据对象循环添加到缓存对象。
     */
    public void loadCache()
    {
        // 1. 从数据库中查询出全部”支路报警规则“数据。
        List<BesBranchConfParameter> besBranchConfParameterList = besSubAlarmMapper.loadAllBranchConfParam();

        if (besBranchConfParameterList == null)
        {
            return;
        }

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        branchConfParameterCache = ehCacheManager.getCache(CacheNameCode.BRANCH_CONF_PARAMETER);

        branchConfParameterCache.clear();

        Map<String,List<BesBranchConfParameter>> map = new HashMap<>();

        // 4. 把“支路报警规则”数据对象循环添加到缓存对象。
        for (BesBranchConfParameter besBranchConfParameter : besBranchConfParameterList)
        {
            if (map.containsKey(besBranchConfParameter.getfAlertid())) {
                map.get(besBranchConfParameter.getfAlertid()).add(besBranchConfParameter);
            } else {
                List<BesBranchConfParameter> tmpList = new ArrayList<>();
                tmpList.add(besBranchConfParameter);
                map.put(besBranchConfParameter.getfAlertid(), tmpList);
            }
        }
        map.forEach((key, value) -> branchConfParameterCache.put(key,value));
    }

    // 删除缓存中的一条数据
    public Boolean deleteOneBranchConfParamCache(String key) {
        if (key == null) {
            return false;
        }
        Cache<String, List<BesBranchConfParameter>> branchConfAlarmConfigurationCache =  this.getBranchBesBranchConfParamCache();
        List<BesBranchConfParameter> besBranchConfParameters = branchConfAlarmConfigurationCache.get(key);

        if(null == besBranchConfParameters)
        {
            return false;
        }

        branchConfParameterCache.remove(key);

        return true;

    }

    public List<BesBranchConfParameter> getCachedElement(String key) throws CacheException
    {
        if (branchConfParameterCache == null || branchConfParameterCache.size() == 0 || key == null)
        {
            return null;
        }

        return branchConfParameterCache.get(key);
    }

    public Set<String> getCachedElementByFAmmsysName(String f_ammsys_name) throws CacheException
    {
        if (branchConfParameterCache == null || branchConfParameterCache.size() == 0 || f_ammsys_name == null)
        {
            return null;
        }
        Collection<List<BesBranchConfParameter>> branchConfParameterCacheValue = branchConfParameterCache.values();
        if (branchConfParameterCacheValue == null || branchConfParameterCacheValue.isEmpty())
        {
            return null;
        }
        Set<String> set = new HashSet<>();
        for (List<BesBranchConfParameter> besCollector : branchConfParameterCacheValue) {
            for (BesBranchConfParameter besBranchConfParameter : besCollector ) {
                if (besBranchConfParameter.getfAmmsysName().equals(f_ammsys_name)) {
                    set.add(besBranchConfParameter.getfAlertid());
                }
            }
        }

        return set;
    }

    public Cache<String, List<BesBranchConfParameter>> getBranchBesBranchConfParamCache()
    {
        return branchConfParameterCache;
    }
}
