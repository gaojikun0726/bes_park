package com.efounder.JEnterprise.mapper.usercenter;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.usercenter.ESUserGroupGnqxManage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户组功能权限Mapper
 * @author gongfanfei
 * @datetiem 2018年5月21日
 */
@Mapper
public interface ESUserGroupGnqxManageMapper extends BaseMapper<String, ESUserGroupGnqxManage>{

	/**
	 * 获取所有用户组功能权限信息
	 * @return
	 */
	List<ESUserGroupGnqxManage> findUserGroupGnqx();
	
	/**
	 * 主键联合取所有用户组功能权限信息
	 * @return
	 */
	List<ESUserGroupGnqxManage> findUserGroupGnqxByThreeId(@Param("f_zbh")String f_zbh,@Param("f_qxbh")String f_qxbh,@Param("f_sjbh")String f_sjbh);
	
	 
	/**
	 * 根据用户组编号、用户组功能权限存储表获取用户组功能权限信息
	 * @param f_zbh
	 * @param classifygnqxTb
	 * @return
	 */
	List<ESUserGroupGnqxManage> findUserGroupGnqxByYhbhAndQxbh(@Param("f_zbh")String f_zbh,@Param("classifygnqxTb")String classifygnqxTb);
	
	/**
	 * 分页查询
	 * @param keywords
	 * @return
	 */
	List<ESUserGroupGnqxManage> findUserGroupGnqxByKey(@Param("keywords") String keywords);

	/**
	 * 增加用户组功能权限
	 * @param role
	 * @return
	 */
	boolean addUserGroupGnqx(ESUserGroupGnqxManage userGroupGnqx);

	/**
	 * 删除用户组功能权限
	 * @param role
	 * @return
	 */
	boolean delUserGroupGnqx(@Param("f_zbh")String f_zbh,@Param("f_qxbh")String f_qxbh,@Param("guid")String guid);

	/**
	 * 更新用户组功能权限
	 * @param role
	 * @return
	 */
	boolean upUserGroupGnqx(ESUserGroupGnqxManage userGroupGnqx);
	
	/**
	 * 批量删除功能权限
	 * @param deleteGgnqxList
	 * @param tablename
	 * @param f_zbh
	 * @return
	 */
	boolean delUserGroupGnqx(@Param("list")List<ESUserGroupGnqxManage> deleteGgnqxList,@Param("tablename")String tablename,@Param("f_zbh")String f_zbh);
	
	/**
	 * 批量添加功能权限
	 * @param gnqxList
	 * @param tablename
	 * @return
	 */
	boolean addUserGroupGnqx(@Param("list")List<ESUserGroupGnqxManage> gnqxList,@Param("tablename")String tablename);
	
	/**
	 * 批量更新数据权限
	 * @param gnqxList
	 * @param tablename
	 * @param f_zbh
	 * @return
	 */
	boolean upUserGroupGnqx(@Param("list")List<ESUserGroupGnqxManage> gnqxList,@Param("tablename")String tablename,@Param("f_zbh")String f_zbh);
	
}
