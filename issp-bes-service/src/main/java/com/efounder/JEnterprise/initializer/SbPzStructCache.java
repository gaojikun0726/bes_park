package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.core.common.constant.CacheNameCode;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BESSbdyMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESSbPzStruct;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 设备树配置定义缓存
 * @author xiepufeng
 * @date 2020/6/20 10:21
 */
@Component
@Order(value=1)
public class SbPzStructCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<String, BESSbPzStruct> sbPzStructCache;

    private Map<String, BESSbPzStruct> sbPzStructCacheKeyId;

    @Resource
    private BESSbdyMapper besSbPzStructMapper;

    @Override
    public void run(String... args) throws Exception
    {
        loadCache();
    }

    /**
     * 1. 从数据库中查询出全部”设备树配置定义“数据。
     * 2. 获取一个缓存管理器”EhCacheManager“。
     * 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     * 4. 把“设备树配置定义”数据对象循环添加到缓存对象。
     */
    public void loadCache()
    {
        // 1. 从数据库中查询出全部”设备树配置定义“数据。
        List<BESSbPzStruct> sbPzStructList = besSbPzStructMapper.findAll();

        if (sbPzStructList == null)
        {
            return;
        }

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        sbPzStructCache = ehCacheManager.getCache(CacheNameCode.SB_PZ_STRUCT_CACHE);

        sbPzStructCache.clear();

        sbPzStructCacheKeyId = new ConcurrentHashMap<>();

        // 4. 把“设备树配置定义”数据对象循环添加到缓存对象。
        sbPzStructList.forEach(sbPzStruct -> {
            // 项目启动设置离线状态
            sbPzStruct.setF_status("0");
            sbPzStructCache.put(sbPzStruct.getF_sys_name(), sbPzStruct);
            sbPzStructCacheKeyId.put(sbPzStruct.getF_id(), sbPzStruct);
        });

    }

    // 添加一条数据到缓存中
    public Boolean addOneSbPzStructCache(BESSbPzStruct sbPzStruct)
    {

        if(null == sbPzStruct)
        {
            return false;
        }

        String key = sbPzStruct.getF_sys_name();

        if(null == key)
        {
            return false;
        }

        Cache<String, BESSbPzStruct> sbPzStructCache =  this.getSbPzStructCache();

        BESSbPzStruct sbPzStructModel = sbPzStructCache.get(key);

        if(null != sbPzStructModel)
        {
            return false;
        }

        sbPzStructCache.put(key, sbPzStruct);
        if (sbPzStruct.getF_id() != null) {
            sbPzStructCacheKeyId.put(sbPzStruct.getF_id(), sbPzStruct);
        }

        return true;
    }

    // 删除缓存中的一条数据
    public Boolean deleteOneSbPzStructCache(String key)
    {
        if (null == key)
        {
            return false;
        }

        Cache<String, BESSbPzStruct> sbPzStructCache =  this.getSbPzStructCache();

        BESSbPzStruct sbPzStructModel = sbPzStructCache.get(key);

        if(null == sbPzStructModel)
        {
            return false;
        }

        sbPzStructCache.remove(key);
        sbPzStructCacheKeyId.remove(sbPzStructModel.getF_id());

        return true;
    }

    // 级联删除
    public void deleteCascade(String sysName)
    {
        List<BESSbPzStruct> besSbPzStructs = getCascadeSubordinate(sysName);

        if (besSbPzStructs != null && !besSbPzStructs.isEmpty())
        {
            besSbPzStructs.forEach(item -> {
                deleteOneSbPzStructCache(item.getF_sys_name());
            });
        }

    }

    // 更新更新缓存中的一条数据
    public Boolean updateOneSbPzStructCache(BESSbPzStruct sbPzStruct)
    {

        if(null == sbPzStruct)
        {
            return false;
        }

        String key = sbPzStruct.getF_sys_name();

        if(null == key)
        {
            return false;
        }

        Cache<String, BESSbPzStruct> sbPzStructCache =  this.getSbPzStructCache();

        BESSbPzStruct sbPzStructModel = sbPzStructCache.get(key);

        if(null == sbPzStructModel)
        {
            return false;
        }

        sbPzStructCache.put(key, sbPzStruct);
        sbPzStructCacheKeyId.put(sbPzStruct.getF_id(), sbPzStruct);

        return true;
    }

    /**
     * 获取下级级联信息
     *
     * @param sysName 系统名称
     * @return
     */
    public List<BESSbPzStruct> getCascadeSubordinate(String sysName)
    {
        if (null == sysName)
        {
            return null;
        }

        Collection<BESSbPzStruct> sbPzStructModels = sbPzStructCache.values();

        if (null == sbPzStructModels || sbPzStructModels.isEmpty())
        {
            return null;
        }

        Map<String, List<BESSbPzStruct>> sbPzStructModelMap = new HashMap<>();

        sbPzStructModels.forEach(sbPzStructModel ->
        {
            sbPzStructModelMap.computeIfAbsent(sbPzStructModel.getPId(), k -> new ArrayList<>()).add(sbPzStructModel);

        });

        List<BESSbPzStruct> sbPzStructModelList = new ArrayList<>();

        sbPzStructModelList.add(getCachedElement(sysName));

        getUnderModel(sbPzStructModelList, sbPzStructModelMap, sysName);

        return sbPzStructModelList;
    }


    private void getUnderModel(List<BESSbPzStruct> sbPzStructModelList, Map<String, List<BESSbPzStruct>> sbPzStructModelMap, String sysName)
    {
        if (sbPzStructModelMap == null
                || sbPzStructModelMap.isEmpty()
                || sbPzStructModelList == null
                || sysName == null)
        {
            return;
        }

        List<BESSbPzStruct> childModelList = sbPzStructModelMap.get(sysName);

        if (childModelList != null && !childModelList.isEmpty())
        {
            childModelList.forEach(sbPzStructModel ->
            {
                getUnderModel(sbPzStructModelList, sbPzStructModelMap, sbPzStructModel.getF_sys_name());

            });

            sbPzStructModelList.addAll(childModelList);
        }

    }


    public BESSbPzStruct getCachedElement(String key) throws CacheException
    {
        if (sbPzStructCache == null || sbPzStructCache.size() == 0 || key == null)
        {
            return null;
        }

        return sbPzStructCache.get(key);
    }

    public BESSbPzStruct getCachedElementById(String id) throws CacheException
    {
        if (sbPzStructCache == null || sbPzStructCache.size() == 0 || id == null)
        {
            return null;
        }

        /*Collection<BESSbPzStruct> sbPzStructModels = sbPzStructCache.values();

        if (null == sbPzStructModels || sbPzStructModels.isEmpty())
        {
            return null;
        }

        for (BESSbPzStruct sbPzStructModel : sbPzStructModels) {
            if (id.equals(sbPzStructModel.getF_id()))
            {
                return sbPzStructModel;
            }
        }*/

        return sbPzStructCacheKeyId.get(id);

    }

    public BESSbPzStruct getCachedElementBySysNameOld(String sysNameOld) throws CacheException
    {
        if (sbPzStructCache == null || sbPzStructCache.size() == 0 || sysNameOld == null)
        {
            return null;
        }

        Collection<BESSbPzStruct> sbPzStructModels = sbPzStructCache.values();

        if (null == sbPzStructModels || sbPzStructModels.isEmpty())
        {
            return null;
        }

        for (BESSbPzStruct sbPzStructModel : sbPzStructModels) {
            if (sysNameOld.equals(sbPzStructModel.getF_sys_name_old()))
            {
                return sbPzStructModel;
            }
        }

        return null;

    }

    /**
     * 根据父系统名称获取节点信息
     * @param pSysName
     * @return
     * @throws CacheException
     */
    public List<BESSbPzStruct> getCachedElementByPSysName(String pSysName) throws CacheException
    {
        if (sbPzStructCache == null || sbPzStructCache.size() == 0 || pSysName == null)
        {
            return null;
        }

        List<BESSbPzStruct> besSbPzStructs = new ArrayList<>();

        Collection<BESSbPzStruct> sbPzStructModels = sbPzStructCache.values();

        if (null == sbPzStructModels || sbPzStructModels.isEmpty())
        {
            return besSbPzStructs;
        }

        for (BESSbPzStruct sbPzStructModel : sbPzStructModels) {
            if (pSysName.equals(sbPzStructModel.getF_psys_name()))
            {
                besSbPzStructs.add(sbPzStructModel);
            }
        }

        return besSbPzStructs;

    }

    public Cache<String, BESSbPzStruct> getSbPzStructCache()
    {
        return sbPzStructCache;
    }

    public void setSbPzStructCache(Cache<String, BESSbPzStruct> sbPzStructCache)
    {
        this.sbPzStructCache = sbPzStructCache;
    }


}
