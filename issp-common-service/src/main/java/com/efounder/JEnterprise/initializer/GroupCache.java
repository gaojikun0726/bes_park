package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.efounder.JEnterprise.constant.CacheNameCode;
import com.efounder.JEnterprise.model.basedatamanage.zzjg.ESZzjg;
import com.efounder.JEnterprise.service.basedatamanage.zzjg.ESZzjgService;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * 对”组织“进行缓存
 * 执行时机：项目启动后执行
 * 实现接口：CommandLineRunner
 * 重写方法：void run(String... args)
 * 执行时机：项目启动后调用
 *
 * @author xiepufeng
 */
@Component
public class GroupCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<String, ESZzjg> groupCache;

    // ”组织“ Service类
    @Resource
    private ESZzjgService groupService;

    /**
     * 项目启动后调用
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception
    {
        loadCache();
    }

    /**
     * 1. 从数据库中查询出全部”组织“数据。
     * 2. 获取一个缓存管理器”RedisCacheManager“。
     * 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     * 4. 把“组织”数据对象循环添加到缓存对象。
     */
    public void loadCache()
    {
        // 1. 从数据库中查询出全部”组织“数据。
        List<ESZzjg> groupList = groupService.findAll();

        if (groupList == null)
        {
            return;
        }

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        groupCache = ehCacheManager.getCache(CacheNameCode.GROUP_CACHE);
        if (null == groupCache)
        {
            return;
        }

        groupCache.clear();

        // 4. 把“组织”数据对象循环添加到缓存对象。
        for (ESZzjg group : groupList)
        {
            groupCache.put(group.getF_zzjgbh(), group);
        }
    }

    // 添加一条数据到缓存中
    public Boolean addOneGroupCache(ESZzjg group)
    {

        if (null == group)
        {
            return false;
        }

        String key = group.getF_zzjgbh();

        if (null == key)
        {
            return false;
        }

        Cache<String, ESZzjg> groupCache = this.getGroupCache();

        ESZzjg groupModel = groupCache.get(key);

        if (null != groupModel)
        {
            return false;
        }

        groupCache.put(key, group);

        return true;
    }

    // 删除缓存中的一条数据
    public Boolean deleteOneGroupCache(String key)
    {
        if (null == key)
        {
            return false;
        }

        Cache<String, ESZzjg> groupCache = this.getGroupCache();

        ESZzjg groupModel = groupCache.get(key);

        if (null == groupModel)
        {
            return false;
        }

        groupCache.remove(key);

        return true;
    }

    // 更新更新缓存中的一条数据
    public Boolean updateOneGroupCache(ESZzjg group)
    {

        if (null == group)
        {
            return false;
        }

        String key = group.getF_id();

        if (null == key)
        {
            return false;
        }

        Cache<String, ESZzjg> groupCache = this.getGroupCache();

        ESZzjg groupModel = groupCache.get(key);

        if (null == groupModel)
        {
            return false;
        }

        groupCache.put(key, group);

        return true;
    }

    // 根据 key 从缓存中获取一条数据
    public ESZzjg getCachedElement(String key) throws CacheException
    {
        if (groupCache == null || groupCache.size() == 0 || key == null)
        {
            return null;
        }

        return groupCache.get(key);
    }

    // 获取组织的所有缓存
    public Cache<String, ESZzjg> getGroupCache()
    {
        return groupCache;
    }

    /**
     * 获取下级组织信息
     *
     * @param groupId
     * @return
     */
    public List<ESZzjg> getSubordinateGroup(String groupId)
    {
        if (null == groupId)
        {
            return null;
        }

        Collection<ESZzjg> groupModels = groupCache.values();

        if (null == groupModels || groupModels.isEmpty())
        {
            return null;
        }

        List<ESZzjg> groupModelList = new ArrayList<>();

        for (ESZzjg groupModel : groupModels)
        {
            if (groupId.equals(groupModel.getF_pzzjgbh()))
            {
                groupModelList.add(groupModel);
            }
        }

        return groupModelList;

    }


    /**
     * 获取下级级联组织信息
     *
     * @param groupId
     * @return
     */
    public List<ESZzjg> getCascadeSubordinateGroup(String groupId)
    {
        if (null == groupId)
        {
            return null;
        }

        Collection<ESZzjg> groupModels = groupCache.values();

        if (null == groupModels || groupModels.isEmpty())
        {
            return null;
        }

        Map<String, List<ESZzjg>> groupModelMap = new HashMap<>();

        groupModels.forEach(groupModel ->
        {
            String parentId = groupModel.getF_pzzjgbh();

            List<ESZzjg> groupModelList = groupModelMap.computeIfAbsent(parentId, k -> new ArrayList<>());

            groupModelList.add(groupModel);

        });

        List<ESZzjg> groupModelList = new ArrayList<>();

        getUnderGroupModel(groupModelList, groupModelMap, groupId);

        return groupModelList;
    }


    private void getUnderGroupModel(List<ESZzjg> groupModelList, Map<String, List<ESZzjg>> groupModelMap, String groupId)
    {
        if (groupModelMap == null
                || groupModelMap.isEmpty()
                || groupModelList == null
                || groupId == null)
        {
            return;
        }

        List<ESZzjg> childGroupModelList = groupModelMap.get(groupId);

        if (childGroupModelList != null && !childGroupModelList.isEmpty())
        {
            childGroupModelList.forEach(groupModel ->
            {
                getUnderGroupModel(groupModelList, groupModelMap, groupModel.getF_zzjgbh());

            });

            groupModelList.addAll(childGroupModelList);
        }

    }

    public void setGroupCache(Cache<String, ESZzjg> groupCache)
    {
        this.groupCache = groupCache;
    }

}
