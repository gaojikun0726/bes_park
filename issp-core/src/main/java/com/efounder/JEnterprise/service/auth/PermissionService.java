package com.efounder.JEnterprise.service.auth;

import com.efounder.JEnterprise.config.shiro.vo.PermissionMenuVo;
import com.efounder.JEnterprise.model.auth.PermissionMenu;
import com.efounder.JEnterprise.model.auth.PermissionModule;

import java.util.List;
import java.util.Map;


public interface PermissionService {
	
	/**
     * 查询用户所能访问的所有模块
     *
     * @param userId
     *            用户ID
     * @return permissions 菜单
     */
    public List<PermissionModule> getPermissionModules(String userId);
    
    /**
     * 查询对应平台的所有菜单
     * @return
     */
    public List<PermissionModule> getHomeModules();
    
    /**
     * 根据系统编码查询对应平台的所有菜单
     * @param xtbh
     * @return
     */
    public List<PermissionModule> getHomeModulesByXtbh(String xtbh);

    /**
     * 查询用户所能访问的所有菜单
     *
     * @param userId
     *            用户ID
     * @param moduleList 
     * @return permissions 菜单
     */
    public Map getPermissionMenus(String userId, List<PermissionModule> moduleList, String limitSQL);

    /**
     * 查询用户所能访问的所有模块+菜单
     *
     * @param userId 用户ID
     * @return permissions 菜单
     */
    List<PermissionMenuVo> findModuleMenuByUserId(String userId, String limitSQL);
    
    /**
     * 基于模块查询用户所能访问的所有菜单
     *
     * @param userId
     *            用户ID
     * @param moduleList 
     * @return permissions 菜单
     */
    public List<PermissionMenuVo> getPermissionMenusByModuleGUID(String userId, String mkGuid, String limitSQL) ;
    /**
     * 根据模块编号查询模块对象
     * @author gongfanfei
     */
    public PermissionModule getPermissionMenusByMkbh(String mkbh) ;

    /**
     * 添加 菜单
     *
     * @param permission
     *            菜单项
     */
    public void addPermission(PermissionMenu permission);
}
