package com.efounder.JEnterprise.service.systemcenter.interfaceconfig.Impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.initializer.MessageMonitoringCache;
import com.efounder.JEnterprise.mapper.systemcenter.interfaceconfig.MessageMonitoringMapper;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.MessageMonitoringModel;
import com.efounder.JEnterprise.service.systemcenter.interfaceconfig.MessageMonitoringService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: wanghongjie
 * @Description:
 * @Date: Created in 14:45 2020/12/10
 * @Modified By:消息监听
 */
@Service
public class MessageMonitoringServiceImpl implements MessageMonitoringService {

    @Resource
    private MessageMonitoringMapper messageMonitoringMapper;

    @Resource
    private MessageMonitoringCache messageMonitoringCache;


    /**
     * 查询所有消息监听信息
     * @return
     */
    @Override
    public List<MessageMonitoringModel> findAll()
    {
        return messageMonitoringMapper.findAll();
    }

    @Override
    public PageInfo<MessageMonitoringModel> queryPage(Integer pageNum, String param) {
        if (pageNum == null)
            pageNum = 1;
        PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
        List<MessageMonitoringModel> list = messageMonitoringMapper.queryPage(param);
        return new PageInfo<>(list);
    }

    /**
     *
     * @Description: 添加消息监听
     *
     * @auther: wanghongjie
     * @date: 16:57 2020/12/10
     * @param: [messageMonitoringModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @Override
    public ISSPReturnObject create(MessageMonitoringModel messageMonitoringModel) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        String httpCallback = messageMonitoringModel.getHttpCallback();
        Integer eventType = messageMonitoringModel.getEventType();
        if (httpCallback == null || httpCallback.isEmpty() ||
        eventType == null ) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }
        Boolean getDeviceType = messageMonitoringMapper.create(messageMonitoringModel);
        if (getDeviceType) {
            // 加入缓存
            messageMonitoringCache.addOneMessageMonitoringCache(messageMonitoringModel);
            returnObject.setStatus("1");
            returnObject.setMsg("添加成功");
        }else {
            returnObject.setStatus("0");
            returnObject.setMsg("添加失败");
        }


        return returnObject;
    }

    /**
     *
     * @Description: 查询消息监听信息
     *
     * @auther: wanghongjie
     * @date: 9:22 2020/12/11
     * @param: [messageMonitoringModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @Override
    public ISSPReturnObject query(MessageMonitoringModel messageMonitoringModel) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        Integer id = messageMonitoringModel.getId();
        if (id == null) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }
        MessageMonitoringModel messageMonitoring = messageMonitoringMapper.query(messageMonitoringModel.getId());
        returnObject.setData(messageMonitoring);
        return returnObject;
    }

    /**
     *
     * @Description: 修改消息监听信息
     *
     * @auther: wanghongjie
     * @date: 10:00 2020/12/11
     * @param: [messageMonitoringModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @Override
    public ISSPReturnObject update(MessageMonitoringModel messageMonitoringModel) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (messageMonitoringModel == null)
        {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        Integer id = messageMonitoringModel.getId();
        String httpCallback = messageMonitoringModel.getHttpCallback();
        Integer eventType = messageMonitoringModel.getEventType();

        if (httpCallback == null || httpCallback.isEmpty() ||
                eventType == null || id == null) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }
        Boolean MessageMonitoring = messageMonitoringMapper.update(messageMonitoringModel);
        if (MessageMonitoring) {
            // 更新缓存
            messageMonitoringCache.updateOneMessageMonitoringCache( messageMonitoringMapper.query(id));
            returnObject.setStatus("1");
            returnObject.setMsg("修改成功");
        } else {
            returnObject.setStatus("0");
            returnObject.setMsg("修改失败");
        }

        return returnObject;
    }

    /**
     *
     * @Description: 删除消息监听信息
     *
     * @auther: wanghongjie
     * @date: 11:09 2020/12/11
     * @param: [messageMonitoringModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @Override
    public ISSPReturnObject delete(MessageMonitoringModel messageMonitoringModel) {
        ISSPReturnObject returnObject = new ISSPReturnObject();

        Integer id = messageMonitoringModel.getId();
        if (id == null) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }
        Boolean del = messageMonitoringMapper.delete(messageMonitoringModel);
        if (del) {
            // 删除缓存
            messageMonitoringCache.deleteOneMessageMonitoringCache(id);
            returnObject.setStatus("1");
            returnObject.setMsg("删除成功");
        } else {
            returnObject.setStatus("0");
            returnObject.setMsg("删除失败");
        }
        return returnObject;
    }
}
