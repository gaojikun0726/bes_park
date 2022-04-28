package com.efounder.JEnterprise.model.auth;

import com.core.common.BaseEntity;

/**
 * 
 * 类名称：RolePermission
 * 类描述：角色与菜单关系对象
 * 创建人：wujf
 * 修改人：wujf
 * 修改时间：2018年1月2日 下午6:48:13
 * 修改备注：
 * @version 1.0.0 
 *
 */
public class RolePermission implements BaseEntity<String> {

    private static final long serialVersionUID = -7948507636703811294L;

    private String id;

    /** 角色ID **/
    private String roleId;

    /** 菜单ID **/
    private String permissionId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

}
