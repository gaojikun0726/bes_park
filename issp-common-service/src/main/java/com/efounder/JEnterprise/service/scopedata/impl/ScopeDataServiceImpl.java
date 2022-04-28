package com.efounder.JEnterprise.service.scopedata.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.initializer.GroupCache;
import com.efounder.JEnterprise.initializer.RoleCache;
import com.efounder.JEnterprise.initializer.RoleGroupCache;
import com.efounder.JEnterprise.mapper.scopedata.ScopeDataMapper;
import com.efounder.JEnterprise.mapper.usercenter.ESRoleMapper;
import com.efounder.JEnterprise.model.basedatamanage.zzjg.ESZzjg;
import com.efounder.JEnterprise.model.scopedata.RoleGroupModel;
import com.efounder.JEnterprise.model.scopedata.ScopeDataModel;
import com.efounder.JEnterprise.model.usercenter.ESRole;
import com.efounder.JEnterprise.service.scopedata.ScopeDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiepufeng
 * @date 2021/1/5 17:46
 */
@Service
public class ScopeDataServiceImpl implements ScopeDataService
{

    @Resource
    private ESRoleMapper esRoleMapper;

    @Resource
    private ScopeDataMapper scopeDataMapper;

    @Resource
    private GroupCache groupCache;

    @Resource
    private RoleGroupCache roleGroupCache;

    @Resource
    private RoleCache roleCache;


    @Override
    public List<RoleGroupModel> findAll()
    {
        return scopeDataMapper.findAll();
    }

    @Override
    public ISSPReturnObject query(ScopeDataModel request)
    {
        ISSPReturnObject result = new ISSPReturnObject();

        // 如果请求参数对象不存在，则返回参数错误错误信息
        if (request == null)
        {
            result.setStatus("0");
            result.setMsg("请求参数不存在");
            return result;
        }

        // 参数角色id
        String roleId = request.getRoleId();

        // 角色id为必须参数，不存在则返回错误码参数错误
        if (roleId == null)
        {
            result.setStatus("0");
            result.setMsg("角色编号是必须的");
            return result;
        }

        // 根据 roleId 查询所以关联的组织信息
        List<RoleGroupModel> roleGroupModels = scopeDataMapper.findByRoleId(roleId);

        result.setData(roleGroupModels);
        result.setStatus("1");
        return result;
    }

    @Override
    @Transactional
    public ISSPReturnObject update(ScopeDataModel request)
    {

        ISSPReturnObject result = new ISSPReturnObject();

        // 如果请求参数对象不存在，则返回参数错误错误信息
        if (request == null)
        {
            result.setStatus("0");
            result.setMsg("请求参数不存在");
            return result;
        }

        // 参数角色id
        String roleId = request.getRoleId();

        // 角色id为必须参数，不存在则返回错误码参数错误
        if (roleId == null)
        {
            result.setStatus("0");
            result.setMsg("角色 id 是必须的");
            return result;
        }

        // 根据角色id查询角色是否存在
        ESRole roleModel = esRoleMapper.findRoleByRoleBh(roleId);

        // 判断对象是否存在，不存在则返回错误码，记录不存在
        if (roleModel == null)
        {
            result.setStatus("0");
            result.setMsg("角色不存在");
            return result;
        }

        // 参数数据权限类型
        Integer scopeRequest = request.getScope();

        if (!ScopeDataModel.isScopeType(scopeRequest))
        {
            result.setStatus("0");
            result.setMsg("参数错误");
            return result;
        }

        Integer scopeModel = roleModel.getScopeType();

        if (null == scopeRequest && null == scopeModel)
        {
            result.setStatus("1");
            result.setMsg("更新成功");
            return result;
        }

        Integer customized = ScopeDataModel.CUSTOMIZED;

        // 如果没有数据更新，直接返回成功
        if (null != scopeModel
                && scopeModel.equals(scopeRequest)
                && !scopeModel.equals(customized))
        {
            result.setStatus("1");
            result.setMsg("更新成功");
            return result;
        }

        // 根据角色id 删除组织角色关联信息
        scopeDataMapper.deleteByRoleId(roleId);

        // 更新自定义数据权限
        if (customized.equals(scopeRequest))
        {

            String[] groupIds = request.getGroupIds();

            List<RoleGroupModel> roleGroupModels = new ArrayList<>();

            for (String groupId : groupIds)
            {

                ESZzjg groupModel = groupCache.getCachedElement(groupId);

                if (null == groupModel)
                {
                    result.setStatus("0");
                    result.setMsg("组织机构不存在");
                    return result;
                }

                RoleGroupModel roleGroupModel = new RoleGroupModel();

                roleGroupModel.setGroupId(groupId);
                roleGroupModel.setRoleId(roleId);

                roleGroupModels.add(roleGroupModel);
            }

            if (!roleGroupModels.isEmpty())
            {
                scopeDataMapper.insertList(roleGroupModels);
            }
        }

        if (scopeModel == null || !scopeModel.equals(scopeRequest))
        {
            roleModel.setScopeType(scopeRequest);
            esRoleMapper.update(roleModel);
            // 更新角色缓存
             roleCache.updateOneRoleCache(roleModel);
        }

        // 刷新缓存数据
        roleGroupCache.updateAllRoleGroupCache();

        result.setStatus("1");
        result.setMsg("更新成功");
        return result;
    }

}
