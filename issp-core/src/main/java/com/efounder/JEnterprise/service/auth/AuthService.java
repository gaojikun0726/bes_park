package com.efounder.JEnterprise.service.auth;

import com.efounder.JEnterprise.model.usercenter.ESRole;
import com.efounder.JEnterprise.model.usercenter.ESUser;

import java.util.List;

/**
 * 
 * 类名称：AuthService
 * 类描述：组装权限接口
 * 创建人：wujf
 * 修改人：wujf
 * 修改时间：2018年1月3日 下午6:33:14
 * 修改备注：
 * @version 1.0.0 
 *
 */
public interface AuthService {

    /**
     * 根据用户名查询用户
     *
     * @param username
     *            用户名
     * @return user 用户
     */
    public ESUser findUserByName(String username);

    /**
     * 根据角色编码查询角色
     * 
     * @param roleCode
     *            角色编码
     * @return 角色对象
     */
    public ESRole findRoleByRoleCode(String roleCode);

    /**
     * 根据角色编码查询用户
     *
     * @param roleCode
     *            角色编码
     * @return user 用户
     */
    public List<ESUser> findUserByRoleCode(String roleCode);

}
