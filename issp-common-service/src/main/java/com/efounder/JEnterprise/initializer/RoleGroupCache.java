package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.efounder.JEnterprise.constant.CacheNameCode;
import com.efounder.JEnterprise.model.scopedata.RoleGroupModel;
import com.efounder.JEnterprise.service.scopedata.ScopeDataService;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 对”角色组织表“进行缓存
 * 执行时机：项目启动后执行
 * 实现接口：CommandLineRunner
 *      重写方法：void run(String... args)
 *      执行时机：项目启动后调用
 * @author xiepufeng
 */
@Component
public class RoleGroupCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<Integer, RoleGroupModel> roleGroupCache;

    // ”角色组织表“ Service类
    @Resource
    private ScopeDataService roleGroupService;

    /**
     * 项目启动后调用
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception
    {
        loadCache();
    }

    /**
     * 1. 从数据库中查询出全部”角色组织表“数据。
     * 2. 获取一个缓存管理器”RedisCacheManager“。
     * 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     * 4. 把“角色组织表”数据对象循环添加到缓存对象。
     */
    public void loadCache()
    {
        // 1. 从数据库中查询出全部”角色组织表“数据。
        List<RoleGroupModel> roleGroupList = roleGroupService.findAll();

        if (roleGroupList == null)
        {
            return;
        }

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);


        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        roleGroupCache = ehCacheManager.getCache(CacheNameCode.ROLE_GROUP_CACHE);
        if (null == roleGroupCache)
        {
            return;
        }

        roleGroupCache.clear();

        // 4. 把“角色组织表”数据对象循环添加到缓存对象。
        for (RoleGroupModel roleGroup : roleGroupList)
        {
            roleGroupCache.put(roleGroup.getId(), roleGroup);
        }
    }

    // 添加一条数据到缓存中
    public Boolean addOneRoleGroupCache(RoleGroupModel roleGroup)
    {

        if(null == roleGroup)
        {
            return false;
        }

        Integer key = roleGroup.getId();

        if(null == key)
        {
            return false;
        }

        Cache<Integer, RoleGroupModel> roleGroupCache =  this.getRoleGroupCache();

        RoleGroupModel roleGroupModel = roleGroupCache.get(key);

        if(null != roleGroupModel)
        {
            return false;
        }

        roleGroupCache.put(key, roleGroup);

        return true;
    }

    // 删除缓存中的一条数据
    public Boolean deleteOneRoleGroupCache(Integer key)
    {
        if (null == key)
        {
            return false;
        }

        Cache<Integer, RoleGroupModel> roleGroupCache =  this.getRoleGroupCache();

        RoleGroupModel roleGroupModel = roleGroupCache.get(key);

        if(null == roleGroupModel)
        {
            return false;
        }

        roleGroupCache.remove(key);

        return true;
    }

    // 更新更新缓存中的一条数据
    public Boolean updateOneRoleGroupCache(RoleGroupModel roleGroup)
    {

        if(null == roleGroup)
        {
            return false;
        }

        Integer key = roleGroup.getId();

        if(null == key)
        {
            return false;
        }

        Cache<Integer, RoleGroupModel> roleGroupCache =  this.getRoleGroupCache();

        RoleGroupModel roleGroupModel = roleGroupCache.get(key);

        if(null == roleGroupModel)
        {
            return false;
        }

        roleGroupCache.put(key, roleGroup);

        return true;
    }

    // 更新缓存中所有数据
    public void updateAllRoleGroupCache()
    {

        // 1. 从数据库中查询出全部”角色组织表“数据。
        List<RoleGroupModel> roleGroupList = roleGroupService.findAll();

        if (roleGroupList == null)
        {
            return;
        }


        roleGroupCache.clear();

        // 2. 把“角色组织表”数据对象循环添加到缓存对象。
        for (RoleGroupModel roleGroup : roleGroupList)
        {
            roleGroupCache.put(roleGroup.getId(), roleGroup);
        }

    }

    // 根据 key 从缓存中获取一条数据
    public RoleGroupModel getCachedElement(Integer key) throws CacheException
    {
        if (roleGroupCache == null || roleGroupCache.size() == 0 || key == null)
        {
            return null;
        }

        return roleGroupCache.get(key);
    }

    // 获取角色组织表的所有缓存
    public Cache<Integer, RoleGroupModel> getRoleGroupCache()
    {
        return roleGroupCache;
    }

    public void setRoleGroupCache(Cache<Integer, RoleGroupModel> roleGroupCache)
    {
        this.roleGroupCache = roleGroupCache;
    }

}
