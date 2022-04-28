package com.efounder.JEnterprise.mapper.usercenter;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.usercenter.ESUserRoleSjqxManage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色角色数据权限Mapper
 * @author gongfanfei
 * @datetiem 2018年5月15日
 */
@Mapper
public interface ESRoleSjqxManageMapper extends BaseMapper<String, ESUserRoleSjqxManage>{

	/**
	 * 获取所有角色数据权限信息
	 * @return
	 */
	List<ESUserRoleSjqxManage> findUserRoleSjqx();
	
	/**
	 * 三个主键联合取所有角色数据权限信息
	 * @return
	 */
	List<ESUserRoleSjqxManage> findUserRoleSjqxByThreeId(@Param("f_roleguid")String f_roleguid,@Param("f_qxbh")String f_qxbh,@Param("f_bhzd")String f_bhzd);
	
	/**
	 * 根据角色数据权限ID获取角色数据权限信息columns,tableName,classifysjqxTb
	 * @param id
	 * @return
	 */
	public List<ESUserRoleSjqxManage> findUserRoleSjqxById(@Param("f_roleguid")String f_roleguid,@Param("f_qxbh")String f_qxbh,@Param("columns")String columns,@Param("tableName")String tableName,@Param("classifysjqxTb")String classifysjqxTb);

	/**
	 * 分页查询
	 * @param keywords
	 * @return
	 */
	List<ESUserRoleSjqxManage> findUserRoleSjqxByKey(@Param("keywords") String keywords);

	/**
	 * 增加角色数据权限
	 * 
	 * @param role
	 * @return
	 */
	boolean addUserRoleSjqx(@Param("list")List<ESUserRoleSjqxManage> userRoleSjqx,@Param("classifysjqxTb") String classifysjqxTb);

	/**
	 * 删除角色数据权限
	 * 
	 * @param role
	 * @return
	 */
	boolean delUserRoleSjqx(@Param("f_roleguid")String f_roleguid,@Param("f_qxbh")String f_qxbh,@Param("classifysjqxTb")String classifysjqxTb);

	/**
	 * 更新角色数据权限
	 * 
	 * @param role
	 * @return
	 */
	boolean upUserRoleSjqx(ESUserRoleSjqxManage userRoleSjqx);
	
	/**
	 * 根据表名查询字段
	 * 
	 * @return@Param("id")String id, @Param("code")String code
	 */
	List<?> getcolumnsBytableName(@Param("columns")String columns,@Param("tableName")String tableName);
}
