package com.efounder.JEnterprise.config.db.database;

/**
 *   
 * 类名称：DataSourceEnum
 * 类描述：数据源枚举常量类
 * 创建人：wujf
 * 修改人：wujf
 * 修改时间：2018年1月3日 下午4:33:05
 * 修改备注：
 * @version 1.0.0 
 *
 */
public enum DataSourceEnum {

    DB1("db1", "数据源1"), 
    DB2("db2", "数据源2");

    private DataSourceEnum(String key, String description) {
        this.key = key;
        this.description = description;
    }

    /** 数据源对应的key(用于在Spring配置文件中指定数据源Map中的key使用) */
    private String key;

    /** 说明 */
    private String description;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
