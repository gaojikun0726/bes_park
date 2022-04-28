package com.efounder.JEnterprise.mapper.usercenter;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.usercenter.ESUserRoleGnqxManage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色功能权限Mapper
 * @author gongfanfei
 * @datetiem 2018年5月21日
 */
@Mapper
public interface ESUserRoleGnqxManageMapper extends BaseMapper<String, ESUserRoleGnqxManage>{

	/**
	 * 获取所有角色功能权限信息
	 * @return
	 */
	List<ESUserRoleGnqxManage> findUserRoleGnqx();
	
	/**
	 * 根据角色功能权限ID以及存储表名获取角色功能权限信息
	 * @param f_roleguid
	 * @param classifygnqxTb
	 * @return
	 */
	List<ESUserRoleGnqxManage> findUserRoleGnqxByRoleId(@Param("f_rolebh")String f_roleguid,@Param("classifygnqxTb")String classifygnqxTb);

	/**
	 * 增加角色功能权限
	 * @param gnqxList
	 * @param tablename
	 * @return
	 */
	boolean addUserRoleGnqx(@Param("list")List<ESUserRoleGnqxManage> gnqxList,@Param("tablename")String tablename);

	/**
	 * 删除角色功能权限
	 * @param gnqxList
	 * @param tablename
	 * @param f_rolebh
	 * @return
	 */
	boolean delUserRoleGnqx(@Param("list")List<ESUserRoleGnqxManage> gnqxList,@Param("tablename")String tablename,@Param("f_rolebh")String f_rolebh);

	/**
	 * 更新角色功能权限
	 * @param gnqxList
	 * @param tablename
	 * @param f_rolebh
	 * @return
	 */
	boolean upUserRoleGnqx(@Param("list")List<ESUserRoleGnqxManage> gnqxList,@Param("tablename")String tablename,@Param("f_rolebh")String f_rolebh);
	
}
