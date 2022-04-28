package com.efounder.JEnterprise.service.systemcenter.interfaceconfig;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.MessageMonitoringModel;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: wanghongjie
 * @Description:消息监听（接口管理模块）
 * @Date: Created in 14:42 2020/12/10
 * @Modified By:
 */
public interface MessageMonitoringService {

    /**
     * 查询所有消息监听信息
     * @return
     */
    List<MessageMonitoringModel> findAll();

    PageInfo<MessageMonitoringModel> queryPage(Integer pageNum, String param);

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
    ISSPReturnObject create(MessageMonitoringModel messageMonitoringModel);

    /**
     *
     * @Description: 查询消息监听信息
     *
     * @auther: wanghongjie
     * @date: 9:15 2020/12/11
     * @param: [messageMonitoringModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject query(MessageMonitoringModel messageMonitoringModel);

    /**
     *
     * @Description: 修改消息监听信息
     *
     * @auther: wanghongjie
     * @date: 9:58 2020/12/11
     * @param: [messageMonitoringModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject update(MessageMonitoringModel messageMonitoringModel);

    /**
     *
     * @Description: 删除消息监听信息
     *
     * @auther: wanghongjie
     * @date: 11:08 2020/12/11
     * @param: [messageMonitoringModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject delete(MessageMonitoringModel messageMonitoringModel);
}
