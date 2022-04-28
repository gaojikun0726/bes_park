package com.efounder.JEnterprise.model.auth;

import com.core.common.BaseEntity;

/**
 *   
 * 类名称：UserRole
 * 类描述：用户与角色关系对象
 * 创建人：wujf
 * 修改人：wujf
 * 修改时间：2018年1月2日 下午6:47:25
 * 修改备注：
 * @version 1.0.0 
 *
 */
public class UserRole implements BaseEntity<String> {

    private static final long serialVersionUID = -56720255622342923L;

    private String id;

    /** 用户ID **/
    private String userId;

    /** 角色ID **/
    private String roleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

}
