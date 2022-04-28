package com.efounder.JEnterprise.initializer;

import com.core.ApplicationContextProvider;
import com.core.common.constant.CacheNameCode;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.MessageMonitoringModel;
import com.efounder.JEnterprise.service.systemcenter.interfaceconfig.MessageMonitoringService;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 消息监听定义缓存
 * @author xiepufeng
 * @date 2020/6/20 10:21
 */
@Component
public class MessageMonitoringCache implements CommandLineRunner
{
    // shiro 缓存机制接口
    private Cache<Integer, MessageMonitoringModel> messageMonitoringCache;

    @Resource
    private MessageMonitoringService messageMonitoringService;

    @Override
    public void run(String... args) throws Exception
    {
        loadCache();
    }

    /**
     * 1. 从数据库中查询出全部”消息监听定义“数据。
     * 2. 获取一个缓存管理器”EhCacheManager“。
     * 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
     * 4. 把“消息监听定义”数据对象循环添加到缓存对象。
     */
    public void loadCache()
    {
        // 1. 从数据库中查询出全部”消息监听定义“数据。
        List<MessageMonitoringModel> messageMonitoringList = messageMonitoringService.findAll();

        if (messageMonitoringList == null)
        {
            return;
        }

        // 2. 获取一个缓存管理器”EhCacheManager“。
        EhCacheManager ehCacheManager = ApplicationContextProvider.getBean(EhCacheManager.class);

        // 3. 从缓存管理器中获取一个指定缓存名称的缓存对象。
        messageMonitoringCache = ehCacheManager.getCache(CacheNameCode.MESSAGE_MONITORING_CACHE);

        if (null == messageMonitoringCache)
        {
            return;
        }

        messageMonitoringCache.clear();

        // 4. 把“消息监听定义”数据对象循环添加到缓存对象。
        for (MessageMonitoringModel messageMonitoring : messageMonitoringList)
        {
            messageMonitoringCache.put(messageMonitoring.getId(), messageMonitoring);
        }
    }

    // 添加一条数据到缓存中
    public Boolean addOneMessageMonitoringCache(MessageMonitoringModel messageMonitoring)
    {

        if(null == messageMonitoring)
        {
            return false;
        }

        Integer key = messageMonitoring.getId();

        if(null == key)
        {
            return false;
        }

        Cache<Integer, MessageMonitoringModel> messageMonitoringCache =  this.getMessageMonitoringCache();

        MessageMonitoringModel messageMonitoringModel = messageMonitoringCache.get(key);

        if(null != messageMonitoringModel)
        {
            return false;
        }

        messageMonitoringCache.put(key, messageMonitoring);

        return true;
    }

    // 删除缓存中的一条数据
    public Boolean deleteOneMessageMonitoringCache(Integer key)
    {
        if (null == key)
        {
            return false;
        }

        Cache<Integer, MessageMonitoringModel> messageMonitoringCache =  this.getMessageMonitoringCache();

        MessageMonitoringModel messageMonitoringModel = messageMonitoringCache.get(key);

        if(null == messageMonitoringModel)
        {
            return false;
        }

        messageMonitoringCache.remove(key);

        return true;
    }

    // 更新更新缓存中的一条数据
    public Boolean updateOneMessageMonitoringCache(MessageMonitoringModel messageMonitoring)
    {

        if(null == messageMonitoring)
        {
            return false;
        }

        Integer key = messageMonitoring.getId();

        if(null == key)
        {
            return false;
        }

        Cache<Integer, MessageMonitoringModel> messageMonitoringCache =  this.getMessageMonitoringCache();

        MessageMonitoringModel messageMonitoringModel = messageMonitoringCache.get(key);

        if(null == messageMonitoringModel)
        {
            return false;
        }

        messageMonitoringCache.put(key, messageMonitoring);

        return true;
    }

    public MessageMonitoringModel getCachedElement(Integer key) throws CacheException
    {
        if (messageMonitoringCache == null || messageMonitoringCache.size() == 0 || key == null)
        {
            return null;
        }

        return messageMonitoringCache.get(key);
    }

    public Cache<Integer, MessageMonitoringModel> getMessageMonitoringCache()
    {
        return messageMonitoringCache;
    }

    public void setMessageMonitoringCache(Cache<Integer, MessageMonitoringModel> messageMonitoringCache)
    {
        this.messageMonitoringCache = messageMonitoringCache;
    }

}
