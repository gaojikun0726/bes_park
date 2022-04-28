package com.efounder.JEnterprise.config.db.table;

import java.util.Map;
import java.util.TreeMap;

/**
 *   
 * 类名称：TablesPKEnum
 * 类描述：数据表配置类
 * 创建人：wujf
 * 修改人：wujf
 * 修改时间：2018年1月3日 下午4:37:48
 * 修改备注：
 * @version 1.0.0 
 *
 */
public enum TablesPKEnum {

//    T_NEWS("T_NEWS", "1001"), 
//    T_SYS_PERMISSION("T_SYS_PERMISSION", "1002"), 
//  T_SYS_ROLE("T_SYS_ROLE", "1003"), 
//  T_SYS_ROLE_PERMISSION("T_SYS_ROLE_PERMISSION", "1004"), 
  T_SYS_USER("ES_USER", "1005"), 
  T_SYS_USER_ROLE("ES_USER_ROLE_RLGL", "1006");

    TablesPKEnum(String tableName, String tableCode) {
        this.tableName = tableName;
        this.tableCode = tableCode;
    }
    
    public static Map<String,Key> getTables() {
        TablesPKEnum[] tables = TablesPKEnum.values();
        Map<String,Key> map = new TreeMap<>();
        Key key = null;
        for (TablesPKEnum tablePk : tables) {
            key = new Key();
            key.setTableName(tablePk.tableName);
            key.setTableCode(tablePk.tableCode);
            map.put(tablePk.tableName , key);
        }
        return map;
    }

    private String tableName;
    private String tableCode;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableCode() {
        return tableCode;
    }

    public void setTableCode(String tableCode) {
        this.tableCode = tableCode;
    }

}
