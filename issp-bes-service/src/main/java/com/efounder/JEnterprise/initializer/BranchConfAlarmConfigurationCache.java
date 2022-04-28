package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.core.common.constant.CacheNameCode;
import com.efounder.JEnterprise.mapper.collectorJob.BESSubAlarmMapper;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESElectricParams;
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
 * @Modified By:支路报警配置缓存
 */
@Component
public class BranchConfAlarmConfigurationCache implements CommandLineRunner {

    // shiro 缓存机制接口
    private Cache<String, List<BesBranchConfAlarmConfiguration>> besBranchConfAlarmConfigurationCache;

    @Resource
    private BESSubAlarmMapper besSubAlarmMapper;
    @Override
    public void run(String... args) throws Exception {
        loadCache();
    }

    /**
     * 1. 从数据库中查询出全部”支路报警配置缓存“数据。
     * 2. 获取一个缓存管理器”EhCacheManager“。
     * 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     * 4. 把“支路报警配置缓存”数据对象循环添加到缓存对象。
     */
    public void loadCache()
    {
        // 1. 从数据库中查询出全部”支路报警配置“数据。
        List<BesBranchConfAlarmConfiguration> electricCollRlglList = besSubAlarmMapper.loadAll();

        if (electricCollRlglList == null)
        {
            return;
        }

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        besBranchConfAlarmConfigurationCache = ehCacheManager.getCache(CacheNameCode.BRANCH_CONF_ALARM_CONFIGURATION);

        besBranchConfAlarmConfigurationCache.clear();

        Map<String,List<BesBranchConfAlarmConfiguration>> map = new HashMap<>();

        // 4. 把“支路报警配置”数据对象循环添加到缓存对象。
        for (BesBranchConfAlarmConfiguration besBranchConfAlarmConfiguration : electricCollRlglList)
        {
            if (map.containsKey(besBranchConfAlarmConfiguration.getfAmmsysName())) {
                map.get(besBranchConfAlarmConfiguration.getfAmmsysName()).add(besBranchConfAlarmConfiguration);
            } else {
                List<BesBranchConfAlarmConfiguration> tmpList = new ArrayList<>();
                tmpList.add(besBranchConfAlarmConfiguration);
                map.put(besBranchConfAlarmConfiguration.getfAmmsysName(), tmpList);
            }
        }

        map.forEach((key, value) -> besBranchConfAlarmConfigurationCache.put(key,value));
    }

    // 删除缓存中的一条数据
    public Boolean deleteOneBranchConfAlarmConfCache(String key,String zlbh) {
        if (key == null) {
            return false;
        }
        Cache<String, List<BesBranchConfAlarmConfiguration>> branchConfAlarmConfigurationCache =  this.getBranchConfAlarmConfigurationCache();
        List<BesBranchConfAlarmConfiguration> besBranchConfAlarmConfigurations = branchConfAlarmConfigurationCache.get(key);

        if(null == besBranchConfAlarmConfigurations)
        {
            return false;
        }
        for (int i = 0; i < besBranchConfAlarmConfigurations.size(); i++) {
            if (zlbh.equals(besBranchConfAlarmConfigurations.get(i).getfZlbh())) {
                besBranchConfAlarmConfigurationCache.get(key).remove(besBranchConfAlarmConfigurations.get(i));
            }
        }
        return true;

    }

    public List<BesBranchConfAlarmConfiguration> getCachedElement(String key) throws CacheException
    {
        if (besBranchConfAlarmConfigurationCache == null || besBranchConfAlarmConfigurationCache.size() == 0 || key == null)
        {
            return null;
        }

        return besBranchConfAlarmConfigurationCache.get(key);
    }

    public Cache<String, List<BesBranchConfAlarmConfiguration>> getBranchConfAlarmConfigurationCache()
    {
        return besBranchConfAlarmConfigurationCache;
    }
}
