package com.efounder.JEnterprise.service.key;

import com.efounder.JEnterprise.config.db.table.Key;

import java.util.List;



/**
 *   
 * 类名称：KeyService
 * 类描述：主键生成
 * 创建人：wujf
 * 修改人：wujf
 * 修改时间：2018年1月3日 下午4:39:42
 * 修改备注：
 * @version 1.0.0 
 *
 */
public interface KeyService {

    /**
     * 查询表名及表的主键字段名
     * 
     * @param keys
     * @return 返回key集合
     */
    public List<Key> getTableValues(List<Key> keys);

    /**
     * @return 返回key集合(只存储表名,主键最大值)
     */
    public List<Key> getTables();
}