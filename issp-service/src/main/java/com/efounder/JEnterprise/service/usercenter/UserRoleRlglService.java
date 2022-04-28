package com.efounder.JEnterprise.service.usercenter;

import com.efounder.JEnterprise.model.usercenter.UserRoleRlgl;

import java.util.List;
/**
 * 
 * 类名称：UserRoleRlgl
 * 类描述：角色和用户关系接口
 * 创建人：gongfanfei
 * 修改人：gongfanfei
 * 修改时间：2018年4月28日 
 * 修改备注：
 * @version 1.0.0 
 *
 */
public interface UserRoleRlglService {
	/**
	  * 新增用户组
	  * @param usergrouprlgl
	  * @return
	  */
	 public boolean addUserRoleRlgl(UserRoleRlgl userrolerlgl);
	 /**
	  * 删除用户组
	  * @param usergrouprlgl
	  * @return
	  */
	 public boolean delUserRoleRlgl(UserRoleRlgl userrolerlgl);
	 /**
	  * 删除用户组对应所有用户
	  * @param usergrouprlgl
	  * @return
	  */
	 public boolean leftMoveAllUsers(UserRoleRlgl userrolerlgl);
	 /**
	  * 根据用户组编号查看用户组关系列表
	  * @param f_roleguiid
	  * @return
	  */
	 public List<UserRoleRlgl> findChildRlglByRolebh(String f_roleguid);
	 /**
	  * 根据用户组编号查看用户组成员对象
	  * @param f_roleguiid
	  * @return
	  */
	 public UserRoleRlgl findRoleRlglByZbh(String f_roleguid);
}
