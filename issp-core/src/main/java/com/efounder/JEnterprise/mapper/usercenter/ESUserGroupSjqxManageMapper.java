package com.efounder.JEnterprise.mapper.usercenter;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.usercenter.ESUserGroupSjqxManage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户组数据权限Mapper
 * @author gongfanfei
 * @datetiem 2018年5月15日
 */
@Mapper
public interface ESUserGroupSjqxManageMapper extends BaseMapper<String, ESUserGroupSjqxManage>{

	/**
	 * 获取所有用户组数据权限信息
	 * @return
	 */
	List<ESUserGroupSjqxManage> findUserGroupSjqx();
	
	/**
	 * 根据条件所有用户组数据权限信息
	 * @param f_zbh 用户组编号
	 * @param f_qxbh 权限编号
	 * @param f_bhzd 编号字段
	 * @return
	 */
	List<ESUserGroupSjqxManage> findUserGroupSjqxByThreeId(@Param("f_zbh")String f_zbh,@Param("f_qxbh")String f_qxbh,@Param("f_bhzd")String f_bhzd);
	
	/**
	 * 根据条件获取用户组数据权限信息
	 * @param f_zbh 用户组编号
	 * @param f_qxbh 权限编号
	 * @param columns 列
	 * @param tableName 表名
	 * @param classifysjqxTb 存储表
	 * @return
	 */
	List<ESUserGroupSjqxManage> findUserGroupSjqxById(@Param("f_zbh")String f_zbh,@Param("f_qxbh")String f_qxbh,@Param("columns")String columns,
			@Param("tableName")String tableName,@Param("classifysjqxTb")String classifysjqxTb);
	
	/**
	 * 分页查询
	 * @param keywords
	 * @return
	 */
	List<ESUserGroupSjqxManage> findUserGroupSjqxByKey(@Param("keywords") String keywords);

	/**
	 * 增加用户组数据权限
	 * @param esuserGroupsjqxmanage
	 * @param classifysjqxTb
	 * @return
	 */
	boolean addUserGroupSjqx(@Param("list")List<ESUserGroupSjqxManage> esuserGroupsjqxmanage,@Param("classifysjqxTb") String classifysjqxTb);
	
	/**
	 * 删除用户组数据权限
	 * @param f_zbh
	 * @param f_qxbh
	 * @param classifysjqxTb
	 * @return
	 */
	boolean delUserGroupSjqx(@Param("f_zbh")String f_zbh,@Param("f_qxbh")String f_qxbh,@Param("classifysjqxTb")String classifysjqxTb);

	/**
	 * 更新用户组数据权限
	 * @param group
	 * @return
	 */
	boolean upUserGroupSjqx(ESUserGroupSjqxManage userGroupSjqx);
	
	/**
	 * 根据表名查询字段
	 * @param columns
	 * @param tableName
	 * @return
	 */
	List<?> getcolumnsBytableName(@Param("columns")String columns,@Param("tableName")String tableName);
	
	
	
}
