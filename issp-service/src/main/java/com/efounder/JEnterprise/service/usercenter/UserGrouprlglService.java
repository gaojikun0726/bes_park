package com.efounder.JEnterprise.service.usercenter;

import com.efounder.JEnterprise.model.usercenter.UserGrouprlgl;

import java.util.List;
/**
 * 
 * 类名称：UserGroupRlgl
 * 类描述：用户组和用户关系接口
 * 创建人：gongfanfei
 * 修改人：gongfanfei
 * 修改时间：2018年4月28日 
 * 修改备注：
 * @version 1.0.0 
 *
 */
public interface UserGrouprlglService {
	/**
	  * 新增用户组
	  * @param usergrouprlgl
	  * @return
	  */
	 public boolean addUserGroupRlgl(UserGrouprlgl usergrouprlgl);
	 /**
	  * 删除用户组
	  * @param usergrouprlgl
	  * @return
	  */
	 public boolean delUserGroupRlgl(UserGrouprlgl usergrouprlgl);
	 /**
	  * 删除用户组对应所有用户
	  * @param usergrouprlgl
	  * @return
	  */
	 public boolean leftMoveAllUsers(UserGrouprlgl usergrouprlgl);
	 /**
	  * 根据用户组编号查看用户组关系列表
	  * @param f_zbh
	  * @return
	  */
	 public List<UserGrouprlgl> findChildRlglByZbh(String f_zbh);
	 /**
	  * 根据用户组编号查看用户组成员对象
	  * @param f_zbh
	  * @return
	  */
	 public UserGrouprlgl findGroupRlglByZbh(String f_zbh);
}
