package com.efounder.JEnterprise.mapper.usercenter;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.usercenter.ESUserSjqxManage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户用户数据权限Mapper
 * @author gongfanfei
 * @datetiem 2018年5月2日
 */
@Mapper
public interface ESUserSjqxManageMapper extends BaseMapper<String, ESUserSjqxManage>{

	/**
	 * 获取所有用户数据权限信息
	 * @return
	 */
	List<ESUserSjqxManage> findUserSjqx();
	
	/**
	 * 查询用户数据权限信息
	 * @param f_yhbh
	 * @param f_qxbh
	 * @param columns
	 * @param tableName
	 * @param usersjqxTb
	 * @return
	 */
	List<ESUserSjqxManage> findUserSjqxList(@Param("f_yhbh")String f_yhbh,@Param("f_qxbh")String f_qxbh,@Param("columns")String columns,@Param("tableName")String tableName,@Param("usersjqxTb")String usersjqxTb);
	
	/**
	 * 根据条件获取所有用户数据权限信息
	 * @param f_yhbh
	 * @param f_qxbh
	 * @param f_bhzd
	 * @return
	 */
	List<ESUserSjqxManage> findUserSjqxByThreeId(@Param("f_yhbh")String f_yhbh,@Param("f_qxbh")String f_qxbh,@Param("f_bhzd")String f_bhzd);
	

	/**
	 * 根据用户数据权限ID获取用户数据权限信息
	 * @param f_yhbh
	 * @param f_qxbh
	 * @param f_bhzd
	 * @return
	 */
	ESUserSjqxManage findUserSjqxById(@Param("f_yhbh")String f_yhbh,@Param("f_qxbh")String f_qxbh,@Param("f_bhzd")String f_bhzd);

	
	/**
	 * 分页查询
	 * @param keywords
	 * @return
	 */
	List<ESUserSjqxManage> findUserSjqxByKey(@Param("keywords") String keywords);

	/**
	 * 批量增加用户数据权限
	 * @param esusersjqxmanage
	 * @param usersjqxTb
	 * @return
	 */
	boolean addUserSjqx(@Param("list")List<ESUserSjqxManage> esusersjqxmanage,@Param("usersjqxTb")String usersjqxTb);

	/**
	 * 删除用户数据权限
	 * @param f_yhbh
	 * @param f_qxbh
	 * @param usersjqxTb
	 * @return
	 */
	boolean delUserSjqx(@Param("f_yhbh")String f_yhbh,@Param("f_qxbh")String f_qxbh,@Param("usersjqxTb")String usersjqxTb);
	
	/**
	 * 根据条件批量删除用户数据权限
	 * @param delsjList
	 * @param f_yhbh
	 * @param f_qxbh
	 * @param usersjqxTb
	 * @return
	 */
	boolean delPartUserSjqx(@Param("list")List<String> delsjList,@Param("f_yhbh")String f_yhbh,@Param("f_qxbh")String f_qxbh,@Param("usersjqxTb")String usersjqxTb);

	/**
	 * 更新用户数据权限
	 * @param userSjqx
	 * @return
	 */
	boolean upUserSjqx(ESUserSjqxManage userSjqx);
	
	/**
	 * 根据表名查询字段
	 * @param columns
	 * @param tableName
	 * @return
	 */
	List<?> getcolumnsBytableName(@Param("columns")String columns,@Param("tableName")String tableName);
}
