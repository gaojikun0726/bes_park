package com.efounder.JEnterprise.mapper.auth;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.auth.RolePermission;
import org.apache.ibatis.annotations.Mapper;


/**
 *   
 * 类名称：RolePermissionMapper
 * 类描述：角色与菜单关系Mapper
 * 创建人：wujf
 * 修改人：wujf
 * 修改时间：2018年1月3日 下午6:28:46
 * 修改备注：
 * @version 1.0.0 
 *
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<String, RolePermission> {

    public RolePermission findRolePermission(RolePermission per);

}
