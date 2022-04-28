package com.efounder.JEnterprise.config.db.database;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 *   
 * 类名称：DynamicDataSource
 * 类描述：动态数据源
 * 创建人：wujf
 * 修改人：wujf
 * 修改时间：2018年1月3日 下午6:57:34
 * 修改备注：
 * @version 1.0.0 
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }

}
