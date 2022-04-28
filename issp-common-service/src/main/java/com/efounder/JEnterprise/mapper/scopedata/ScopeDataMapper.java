package com.efounder.JEnterprise.mapper.scopedata;

import com.efounder.JEnterprise.model.scopedata.RoleGroupModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xiepufeng
 * @date 2021/1/5 18:47
 */
@Mapper
public interface ScopeDataMapper
{
    /**
     * 删除根据角色id
     * @param roleId
     */
    void deleteByRoleId(String roleId);

    /**
     * 批量添加
     * @param roleGroupModels
     */
    void insertList(List<RoleGroupModel> roleGroupModels);

    /**
     * 获取所有数据权限信息
     * @return
     */
    List<RoleGroupModel> findAll();

    /**
     * 根据角色id获取权限信息
     * @return
     */
    List<RoleGroupModel> findByRoleId(String roleId);
}
