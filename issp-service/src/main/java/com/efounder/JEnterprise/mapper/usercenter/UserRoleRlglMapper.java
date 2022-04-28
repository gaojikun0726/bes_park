package com.efounder.JEnterprise.mapper.usercenter;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.usercenter.UserRoleRlgl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/** 
 * 类名称：UserRoleRlglMapper
 * 类描述：用户组和用户关系mapper接口
 * 创建人：gongfanfei
 * 修改人：gongfanfei
 * 修改时间：2018年4月28日 下午1:44:15
 * 修改备注：
 * @version 1.0.0 
 *
 */
@Mapper
public interface UserRoleRlglMapper extends BaseMapper<String, UserRoleRlgl>{
	/**
	 * 查询用户组
	 * @author Gongfanfei
	 * @param f_zbh
	 * @return
	 */
	List<UserRoleRlgl> findUserRoleRlglByPage(@Param("f_zmc") String f_zmc);
	
	/**
	 * 查询子类
	 * @author Gongfanfei
	 * @param f_roleguid
	 * @return
	 */
	List<UserRoleRlgl> findChildByRolebh(@Param("f_roleguid") String f_roleguid);
	/**
	 * @author gongfanfei
	 * 加载用户组
	 * @return
	 */
	List<UserRoleRlgl> loadAll();
	/**
	 * 删除用户
	 * @param f_yhbh
	 * @return
	 */
	boolean deleteByYhbh(@Param("f_yhbh") String f_yhbh);
	/**
	 * 删除用户组对应下的所有用户（左移）
	 * @param f_rolebh
	 * @return
	 */
	boolean leftMoveByRolebh(@Param("f_roleguid") String f_roleguid);
}
