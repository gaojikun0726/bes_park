package com.efounder.JEnterprise.mapper.systemcenter.interfaceconfig;

import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.MessageMonitoringModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: wanghongjie
 * @Description:消息监听
 * @Date: Created in 14:48 2020/12/10
 * @Modified By:
 */
@Mapper
public interface MessageMonitoringMapper {

    /**
     * 查询所有消息监听信息
     * @return
     */
    List<MessageMonitoringModel> findAll();

    List<MessageMonitoringModel> queryPage(@Param("param") String param);


    /**
     *
     * @Description: 添加消息监听配置
     *
     * @auther: wanghongjie
     * @date: 16:58 2020/12/10
     * @param messageMonitoringModel
     */
    Boolean create(MessageMonitoringModel messageMonitoringModel);

    /**
     *
     * @Description: 查询消息监听信息
     *
     * @auther: wanghongjie
     * @date: 9:22 2020/12/11
     * @param: [id]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    MessageMonitoringModel query(Integer id);

    /**
     *
     * @Description: 修改消息监听信息
     *
     * @auther: wanghongjie
     * @date: 10:00 2020/12/11
     * @param: [messageMonitoringModel]
     * @return: java.lang.Boolean
     *
     */
    Boolean update(MessageMonitoringModel messageMonitoringModel);

    /**
     *
     * @Description: 删除消息监听信息
     *
     * @auther: wanghongjie
     * @date: 11:09 2020/12/11
     * @param: [messageMonitoringModel]
     * @return: java.lang.Boolean
     *
     */
    Boolean delete(MessageMonitoringModel messageMonitoringModel);
}
