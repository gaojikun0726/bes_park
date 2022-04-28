package com.efounder.JEnterprise.model.scopedata;

import java.io.Serializable;

public class ScopeDataModel implements Serializable
{

    private static final long serialVersionUID = 1L;

    /**
     * 全部数据权限
     */
    public static final int FULL = 1;

    /**
     * 自定数据权限
     */
    public static final int CUSTOMIZED = 2;

    /**
     * 本组织及以下数据权限
     */
    public static final int INCLUDE_LOCAL_BELOW = 3;

    /**
     * 本组织数据权限
     */
    public static final int LOCAL = 4;

    /**
     * 仅本人数据权限
     */
    public static final int SELF = 5;

    private Integer scope;

    private String roleId;

    private String[] groupIds;

    public Integer getScope()
    {
        return scope;
    }

    public void setScope(Integer scope)
    {
        this.scope = scope;
    }

    public String getRoleId()
    {
        return roleId;
    }

    public void setRoleId(String roleId)
    {
        this.roleId = roleId;
    }

    public String[] getGroupIds()
    {
        return groupIds;
    }

    public void setGroupIds(String[] groupIds)
    {
        this.groupIds = groupIds;
    }

    public static boolean isScopeType(Integer scopeType)
    {
      if (scopeType == null)
      {
          return true;
      }

        switch(scopeType){
            case FULL:
                return true;
            case CUSTOMIZED:
                return true;
            case INCLUDE_LOCAL_BELOW:
                return true;
            case LOCAL:
                return true;
            case SELF:
                return true;
            default:
                return false;
        }

    }
}
