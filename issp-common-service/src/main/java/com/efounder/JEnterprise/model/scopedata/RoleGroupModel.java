package com.efounder.JEnterprise.model.scopedata;

import java.io.Serializable;

/**
 * 角色组织关联表
 */
public class RoleGroupModel implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 组织机构编号
     */
    private String groupId;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getRoleId()
    {
        return roleId;
    }

    public void setRoleId(String roleId)
    {
        this.roleId = roleId;
    }

    public String getGroupId()
    {
        return groupId;
    }

    public void setGroupId(String groupId)
    {
        this.groupId = groupId;
    }
}
