package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.efounder.JEnterprise.constant.CacheNameCode;
import com.efounder.JEnterprise.model.usercenter.ESRole;
import com.efounder.JEnterprise.service.usercenter.ESRoleService;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 对”角色“进行缓存
 * 执行时机：项目启动后执行
 * 实现接口：CommandLineRunner
 *      重写方法：void run(String... args)
 *      执行时机：项目启动后调用
 * @author xiepufeng
 */
@Component
public class RoleCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<String, ESRole> roleCache;

    // ”角色“ Service类
    @Resource
    private ESRoleService roleService;

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
     * 1. 从数据库中查询出全部”角色“数据。
     * 2. 获取一个缓存管理器”RedisCacheManager“。
     * 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     * 4. 把“角色”数据对象循环添加到缓存对象。
     */
    public void loadCache()
    {
        // 1. 从数据库中查询出全部”角色“数据。
        List<ESRole> roleList = roleService.findRoles();

        if (roleList == null)
        {
            return;
        }

        // 2. 获取一个缓存管理器”RedisCacheManager“。
        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        roleCache = ehCacheManager.getCache(CacheNameCode.ROLE_CACHE);
        if (null == roleCache)
        {
            return;
        }

        roleCache.clear();

        // 4. 把“角色”数据对象循环添加到缓存对象。
        for (ESRole role : roleList)
        {
            roleCache.put(role.getF_rolebh(), role);
        }
    }

    // 添加一条数据到缓存中
    public Boolean addOneRoleCache(ESRole role)
    {

        if(null == role)
        {
            return false;
        }

        String key = role.getF_rolebh();

        if(null == key)
        {
            return false;
        }

        Cache<String, ESRole> roleCache =  this.getRoleCache();

        ESRole roleModel = roleCache.get(key);

        if(null != roleModel)
        {
            return false;
        }

        roleCache.put(key, role);

        return true;
    }

    // 删除缓存中的一条数据
    public Boolean deleteOneRoleCache(String key)
    {
        if (null == key)
        {
            return false;
        }

        Cache<String, ESRole> roleCache =  this.getRoleCache();

        ESRole roleModel = roleCache.get(key);

        if(null == roleModel)
        {
            return false;
        }

        roleCache.remove(key);

        return true;
    }

    // 更新更新缓存中的一条数据
    public Boolean updateOneRoleCache(ESRole role)
    {

        if(null == role)
        {
            return false;
        }

        String key = role.getF_rolebh();

        if(null == key)
        {
            return false;
        }

        Cache<String, ESRole> roleCache =  this.getRoleCache();

        ESRole roleModel = roleCache.get(key);

        if(null == roleModel)
        {
            return false;
        }

        roleCache.put(key, role);

        return true;
    }

    // 根据 key 从缓存中获取一条数据
    public ESRole getCachedElement(String key) throws CacheException
    {
        if (roleCache == null || roleCache.size() == 0 || key == null)
        {
            return null;
        }

        return roleCache.get(key);
    }

    // 获取角色的所有缓存
    public Cache<String, ESRole> getRoleCache()
    {
        return roleCache;
    }

    public void setRoleCache(Cache<String, ESRole> roleCache)
    {
        this.roleCache = roleCache;
    }

}
