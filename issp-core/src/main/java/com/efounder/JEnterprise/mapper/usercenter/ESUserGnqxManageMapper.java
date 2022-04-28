package com.efounder.JEnterprise.mapper.usercenter;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import com.efounder.JEnterprise.model.usercenter.ESUserGnqxManage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户功能权限Mapper
 * @author gongfanfei
 * @datetiem 2018年5月16日
 */
@Mapper
public interface ESUserGnqxManageMapper extends BaseMapper<String, ESUserGnqxManage>{

	/**
	 * 获取所有用户功能权限信息
	 * @return
	 */
	List<ESUserGnqxManage> findUserGnqx();
	
	/**
	 * 根据用户编号取功能权限数据
	 * @return
	 */
	List<ESUserGnqxManage> findUserGnqxByYhbh(@Param("f_yhbh")String f_yhbh,@Param("usergnqxTb")String usergnqxTb);
	
	/**
	 * 增加用户功能权限
	 * @param role
	 * @return
	 */
	boolean addUserGnqx(@Param("list")List<ESUserGnqxManage> gnqxList,@Param("tablename")String tablename);

	/**
	 * 删除用户功能权限
	 * mkguid
	 * @param role
	 * @return
	 */
	boolean delUserGnqx(@Param("list")List<ESUserGnqxManage> f_gnzdid,@Param("tablename")String tablename,@Param("f_yhbh")String f_yhbh);

	/**
	 * 删除用户关联的功能权限
	 * @param user
	 * @return
	 */
	Integer delGnqxByUser(ESUser user);


	/**
	 * 删除功能字典时删除关联的用户功能权限
	 * @param map
	 * @return
	 */
	Integer delRelativeGnqx(Map map);

	/**
	 * 更新用户功能权限
	 * @param role
	 * @return
	 */
	boolean upUserGnqx(@Param("list")List<ESUserGnqxManage> gnqxList,@Param("tablename")String tablename,@Param("f_yhbh")String f_yhbh);
	
}
