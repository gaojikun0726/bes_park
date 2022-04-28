package com.efounder.JEnterprise.mapper.app;

import com.efounder.JEnterprise.model.app.SocketConfigModel;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESSocket;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * describe: 插座配置
 *
 * @author wzx
 * @date 2020-9-24 9:47:24
 */
@Mapper
public interface SocketConfigMapper {

    /**
     * 查询插座配置列表
     * @param map
     * @return
     */
    List<SocketConfigModel> querySocketConfigList(Map map);


    /**
     * 删除
     * @param id 主键
     */
    Integer delete(String id);

    /**
     * 修改
     * @param socketConfig
     * @return
     */
    Integer update(SocketConfigModel socketConfig);

    /**
     * 新增
     * @param socketConfig
     * @return
     */
    Integer insert(SocketConfigModel socketConfig);

    /**
     * 查询
     * @param id
     * @return
     */
    List<SocketConfigModel> queryOne(String id);

    /**
     * 新增插座
     * @param BESSocket
     * @return
     */
    Boolean insertSocket(BESSocket BESSocket);
}
