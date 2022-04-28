package com.framework.common.utils;

import com.core.ApplicationContextProvider;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.initializer.GroupCache;
import com.efounder.JEnterprise.initializer.RoleCache;
import com.efounder.JEnterprise.initializer.RoleGroupCache;
import com.efounder.JEnterprise.model.basedatamanage.zzjg.ESZzjg;
import com.efounder.JEnterprise.model.scopedata.RoleGroupModel;
import com.efounder.JEnterprise.model.scopedata.ScopeDataModel;
import com.efounder.JEnterprise.model.usercenter.ESRole;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.session.Session;

import java.util.*;


/**
 * 数据权限工具类
 *
 * @author xiepufeng
 */
public class ScopeDataUtil
{
    public static List<String> getGroupIdScope()
    {
        Set<String> scopeData = new HashSet<>();

        Session session = SecurityUtils.getSubject().getSession();

        if (null == session)
        {
            return null;
        }

        @SuppressWarnings("unchecked")
        Set<String> roleIds = (Set<String>) session.getAttribute(Constants.ROLE_CODE);

        if (null == roleIds || roleIds.isEmpty())
        {
            return null;
        }

        RoleCache roleCache = ApplicationContextProvider.getBean(RoleCache.class);

        GroupCache groupCacheInitializer = Objects.requireNonNull(ApplicationContextProvider.getBean(GroupCache.class));

        Cache<String, ESZzjg> groupCache = groupCacheInitializer.getGroupCache();

        for (String roleId : roleIds)
        {
            ESRole roleModel = roleCache.getCachedElement(roleId);

            if (null == roleModel)
            {
                continue;
            }

            Integer scopeType = roleModel.getScopeType();

            if (scopeType == null)
            {
                continue;
            }

            switch (scopeType)
            {
                case ScopeDataModel.FULL:
                {
                    Set<String> groupIds = groupCache.keys();

                    scopeData.addAll(groupIds);

                    break;
                }
                case ScopeDataModel.CUSTOMIZED:
                {
                    Cache<Integer, RoleGroupModel> roleGroupCache = Objects.requireNonNull(ApplicationContextProvider.getBean(RoleGroupCache.class)).getRoleGroupCache();

                    Collection<RoleGroupModel> roleGroupModels = roleGroupCache.values();

                    roleGroupModels.forEach(roleGroupModel ->
                    {
                        if (roleId.equals(roleGroupModel.getRoleId()))
                        {
                            scopeData.add(roleGroupModel.getGroupId());
                        }
                    });

                    break;
                }
                case ScopeDataModel.INCLUDE_LOCAL_BELOW:
                {
                    String groupId = (String) session.getAttribute(Constants.GROUP_CODE);

                    if (null != groupId)
                    {
                        scopeData.add(groupId);

                        List<ESZzjg> groupModelList = groupCacheInitializer.getCascadeSubordinateGroup(groupId);

                        if (groupModelList != null)
                        {
                            groupModelList.forEach((groupModel -> scopeData.add(groupModel.getF_zzjgbh())));
                        }

                    }

                    break;
                }
                case ScopeDataModel.LOCAL:
                {
                    String groupId = (String) session.getAttribute(Constants.GROUP_CODE);
                    if (null != groupId)
                    {
                        scopeData.add(groupId);
                    }
                    break;
                }
            }
        }


        return new ArrayList<>(scopeData);
    }

    public static String getUserIdScope()
    {

        Session session = SecurityUtils.getSubject().getSession();

        if (null == session)
        {
            return null;
        }

        RoleCache roleCache = ApplicationContextProvider.getBean(RoleCache.class);

        @SuppressWarnings("unchecked")
        Set<String> roleIds = (Set<String>) session.getAttribute(Constants.ROLE_CODE);

        if (null == roleIds || roleIds.isEmpty())
        {
            return null;
        }


        for (String roleId : roleIds)
        {
            ESRole roleModel = roleCache.getCachedElement(roleId);

            if (null == roleModel)
            {
                continue;
            }

            Integer scopeType = roleModel.getScopeType();

            Integer SELF = ScopeDataModel.SELF;

            if (SELF.equals(scopeType))
            {
                return (String) session.getAttribute(Constants.USERNAME);
            }

        }

        return null;

    }

}
