package com.efounder.JEnterprise.mapper.auth;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.auth.PermissionMenu;
import com.efounder.JEnterprise.model.auth.PermissionModule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *   
 * 类名称：PermissionMapper
 * 类描述：菜单Mapper
 * 创建人：wujf
 * 修改人：wujf
 * 修改时间：2018年1月3日 下午6:30:14
 * 修改备注：
 * @version 1.0.0 
 *
 */
@Mapper
public interface PermissionMapper extends BaseMapper<String, PermissionMenu> {
	/**
     * 查询用户所能访问的所有模块
     *
     * @param userId
     *            用户
     * @return permissions 菜单
     */
	public List<PermissionModule> findPermissionModuleByUserId(Map mapParam);
	/**
	 * 根据系统编号查询对应的所有模块
	 *
	 * @return permissions 菜单
	 */
	public List<PermissionModule> findPermissionModuleByXtbh(Map mapParam);
	/**
	 * 根据模块编号查询对应模块对象
	 *
	 * @return permissions 菜单
	 */
	public PermissionModule findModuleObjByMkbh(@Param("mkbh") String mkbh);

    /**
     * 查询用户所能访问的所有菜单
     *
     * @param userId
     *            用户
     * @return permissions 菜单
     */
    public List<PermissionMenu> findPermissionMenuByUserId(Map mapParam);//String userId,String mkid


	/**
	 * 查询该用户下所能访问的所有模块+菜单
	 * @param mapParam
	 * @return
	 */
	List<PermissionMenu> findModuleMenuByUserId(Map mapParam);


	/**
	 * 查询用户所能访问的所有模块
	 * @param mapParam
	 * @return
	 */
	List<PermissionMenu> findModuleByUserId(Map mapParam);

	/**
	 * 单独查询首页菜单信息
	 * @param mapParam
	 * @return
	 */
	List<PermissionMenu> findFirstPageInfo(Map mapParam);

    /**
     * 根据菜单KEY查询菜单
     *
     * @param permissionKey
     *            菜单KEY
     * @return
     */
    public PermissionMenu findPermissionMenuByKey(String permissionKey);
    
}
